package com.cameramod.tileentity;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModTileEntities {
    public static void init() {
        GameRegistry.registerTileEntity(TileEntityCamera.class, "tileentity_camera");
        GameRegistry.registerTileEntity(TileEntityMonitor.class, "tileentity_monitor");
    }
}
