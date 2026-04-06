package com.cameramod.client;

import com.cameramod.tileentity.TileEntityMonitor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

public class ContainerMonitor extends Container {
    private TileEntityMonitor monitor;

    public ContainerMonitor(EntityPlayer player, TileEntityMonitor monitor) {
        this.monitor = monitor;
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }
}
