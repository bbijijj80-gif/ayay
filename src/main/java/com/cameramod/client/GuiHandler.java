package com.cameramod.client;

import com.cameramod.tileentity.TileEntityCamera;
import com.cameramod.tileentity.TileEntityMonitor;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == 0) {
            return new ContainerCamera(player, (TileEntityCamera) world.getTileEntity(new net.minecraft.util.math.BlockPos(x, y, z)));
        } else if (ID == 1) {
            return new ContainerMonitor(player, (TileEntityMonitor) world.getTileEntity(new net.minecraft.util.math.BlockPos(x, y, z)));
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == 0) {
            return new GuiCamera(player, (TileEntityCamera) world.getTileEntity(new net.minecraft.util.math.BlockPos(x, y, z)));
        } else if (ID == 1) {
            return new GuiMonitor(player, (TileEntityMonitor) world.getTileEntity(new net.minecraft.util.math.BlockPos(x, y, z)));
        }
        return null;
    }
}
