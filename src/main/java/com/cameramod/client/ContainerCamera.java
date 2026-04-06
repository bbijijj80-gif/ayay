package com.cameramod.client;

import com.cameramod.tileentity.TileEntityCamera;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.Unpooled;

public class ContainerCamera extends Container {
    private TileEntityCamera camera;

    public ContainerCamera(EntityPlayer player, TileEntityCamera camera) {
        this.camera = camera;
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }
}
