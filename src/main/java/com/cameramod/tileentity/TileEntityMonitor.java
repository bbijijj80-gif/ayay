package com.cameramod.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityMonitor extends TileEntity {
    private String connectedCameraId = "";
    private TileEntityCamera linkedCamera = null;
    private boolean hasSignal = false;
    
    // Данные для отображения (в реальном моде здесь была бы текстура/изображение)
    private int cameraRotation = 0;
    private boolean cameraActive = false;

    @Override
    public void update() {
        if (!world.isRemote && !connectedCameraId.isEmpty()) {
            // Поиск подключенной камеры
            if (linkedCamera == null || linkedCamera.isInvalid()) {
                findLinkedCamera();
            }
        }
    }

    private void findLinkedCamera() {
        if (world == null || connectedCameraId.isEmpty()) {
            return;
        }
        
        // Поиск камеры по всему миру (в реальном моде можно ограничить радиус)
        for (int x = -100; x <= 100; x++) {
            for (int y = 0; y < 256; y++) {
                for (int z = -100; z <= 100; z++) {
                    TileEntity te = world.getTileEntity(new net.minecraft.util.math.BlockPos(
                        pos.getX() + x, pos.getY() + y, pos.getZ() + z));
                    if (te instanceof TileEntityCamera) {
                        TileEntityCamera camera = (TileEntityCamera) te;
                        if (connectedCameraId.equals(camera.getCameraId())) {
                            linkedCamera = camera;
                            camera.addConnectedMonitor(this);
                            return;
                        }
                    }
                }
            }
        }
    }

    public void setConnectedCameraId(String id) {
        this.connectedCameraId = id;
        markDirty();
        findLinkedCamera();
    }

    public String getConnectedCameraId() {
        return connectedCameraId;
    }

    public void setCameraData(TileEntityCamera camera) {
        if (camera != null) {
            this.cameraRotation = camera.getRotation();
            this.cameraActive = camera.isActive();
            this.hasSignal = true;
            
            // Отправка обновления клиенту
            if (world != null && !world.isRemote) {
                world.notifyBlockUpdate(pos, getBlockState(), getBlockState(), 3);
            }
        }
    }

    public boolean hasSignal() {
        return hasSignal;
    }

    public int getCameraRotation() {
        return cameraRotation;
    }

    public boolean isCameraActive() {
        return cameraActive;
    }

    @Override
    public void invalidate() {
        super.invalidate();
        if (linkedCamera != null) {
            linkedCamera.removeConnectedMonitor(this);
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setString("connectedCameraId", connectedCameraId);
        compound.setBoolean("hasSignal", hasSignal);
        compound.setInteger("cameraRotation", cameraRotation);
        compound.setBoolean("cameraActive", cameraActive);
        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        connectedCameraId = compound.getString("connectedCameraId");
        hasSignal = compound.getBoolean("hasSignal");
        cameraRotation = compound.getInteger("cameraRotation");
        cameraActive = compound.getBoolean("cameraActive");
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        return writeToNBT(new NBTTagCompound());
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        writeToNBT(nbtTagCompound);
        return new SPacketUpdateTileEntity(pos, 1, nbtTagCompound);
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        readFromNBT(pkt.getNbtCompound());
    }
}
