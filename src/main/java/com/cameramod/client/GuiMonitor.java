package com.cameramod.client;

import com.cameramod.tileentity.TileEntityMonitor;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiMonitor extends GuiContainer {
    private TileEntityMonitor monitor;
    private static final ResourceLocation TEXTURE = new ResourceLocation("cameramod:textures/gui/monitor_gui.png");

    public GuiMonitor(EntityPlayer player, TileEntityMonitor monitor) {
        super(new ContainerMonitor(player, monitor));
        this.monitor = monitor;
        this.xSize = 176;
        this.ySize = 166;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        mc.getTextureManager().bindTexture(TEXTURE);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
        
        // Если есть сигнал от камеры, рисуем "изображение"
        if (monitor != null && monitor.hasSignal()) {
            // В реальном моде здесь был бы рендер изображения с камеры
            drawRect(guiLeft + 8, guiTop + 20, guiLeft + 168, guiTop + 120, 0xFF333333);
        }
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        this.fontRenderer.drawString("Monitor", 8, 6, 4210752);
        
        if (monitor != null) {
            String status = monitor.hasSignal() ? "Signal: OK" : "No Signal";
            this.fontRenderer.drawString(status, 8, 20, 4210752);
            
            if (monitor.hasSignal()) {
                this.fontRenderer.drawString("Camera ID: " + monitor.getConnectedCameraId(), 8, 32, 4210752);
                this.fontRenderer.drawString("Rotation: " + monitor.getCameraRotation(), 8, 44, 4210752);
            }
        }
    }
}
