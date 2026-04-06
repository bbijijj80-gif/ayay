package com.cameramod.block;

import com.cameramod.CameraMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class ModBlocks {
    public static BlockCamera camera;
    public static BlockMonitor monitor;

    public static void init() {
        camera = new BlockCamera("camera");
        monitor = new BlockMonitor("monitor");
    }

    public static void register() {
        registerBlock(camera);
        registerBlock(monitor);
    }

    private static void registerBlock(Block block) {
        GameRegistry.register(block);
        OreDictionary.registerOre("block" + block.getUnlocalizedName().substring(5), block);
        CameraMod.logger.info("Registered block: " + block.getUnlocalizedName());
    }
}
