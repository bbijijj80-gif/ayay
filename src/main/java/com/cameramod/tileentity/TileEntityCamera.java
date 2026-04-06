package com.cameramod.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class TileEntityCamera extends TileEntity implements ITickable {
    private String cameraId = "";
    private boolean active = false;
    private int rotation = 0;
    
    // Список подключенных мониторов
    private List<TileEntityMonitor> connectedMonitors = new ArrayList<>();

    @Override
    public void update() {
        if (!world.isRemote && active) {
            // Отправка данных на подключенные мониторы
            for (TileEntityMonitor monitor : connectedMonitors) {
                if (monitor != null && !monitor.isInvalid()) {
                    monitor.setCameraData(this);
                }
            }
        }
    }

    public void setActive(boolean active) {
        this.active = active;
        markDirty();
        if (world != null) {
            world.notifyBlockUpdate(pos, getBlockState(), getBlockState(), 3);
        }
    }

    public boolean isActive() {
        return active;
    }

    public void setRotation(int rotation) {
        this.rotation = rotation % 360;
        markDirty();
    }

    public int getRotation() {
        return rotation;
    }

    public String getCameraId() {
        return cameraId;
    }

    public void setCameraId(String cameraId) {
        this.cameraId = cameraId;
        markDirty();
    }

    public void addConnectedMonitor(TileEntityMonitor monitor) {
        if (!connectedMonitors.contains(monitor)) {
            connectedMonitors.add(monitor);
        }
    }

    public void removeConnectedMonitor(TileEntityMonitor monitor) {
        connectedMonitors.remove(monitor);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setBoolean("active", active);
        compound.setInteger("rotation", rotation);
        compound.setString("cameraId", cameraId);
        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        active = compound.getBoolean("active");
        rotation = compound.getInteger("rotation");
        cameraId = compound.getString("cameraId");
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
