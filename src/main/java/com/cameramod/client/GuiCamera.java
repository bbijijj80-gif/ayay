package com.cameramod.client;

import com.cameramod.tileentity.TileEntityCamera;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiCamera extends GuiContainer {
    private TileEntityCamera camera;
    private static final ResourceLocation TEXTURE = new ResourceLocation("cameramod:textures/gui/camera_gui.png");

    public GuiCamera(EntityPlayer player, TileEntityCamera camera) {
        super(new ContainerCamera(player, camera));
        this.camera = camera;
        this.xSize = 176;
        this.ySize = 166;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        mc.getTextureManager().bindTexture(TEXTURE);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        this.fontRenderer.drawString("Camera Settings", 8, 6, 4210752);
        
        if (camera != null) {
            String status = camera.isActive() ? "Active" : "Inactive";
            this.fontRenderer.drawString("Status: " + status, 8, 20, 4210752);
            this.fontRenderer.drawString("Rotation: " + camera.getRotation(), 8, 32, 4210752);
            this.fontRenderer.drawString("ID: " + camera.getCameraId(), 8, 44, 4210752);
        }
    }
}
