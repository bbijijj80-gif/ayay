package com.cameramod.block;

import com.cameramod.CameraMod;
import com.cameramod.tileentity.TileEntityMonitor;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockMonitor extends BlockContainer {
    public BlockMonitor(String name) {
        super(Material.IRON);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(CreativeTabs.REDSTONE);
        setHardness(1.0F);
        setResistance(5.0F);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityMonitor();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            TileEntity te = worldIn.getTileEntity(pos);
            if (te instanceof TileEntityMonitor) {
                playerIn.openGui(CameraMod.instance, 1, worldIn, pos.getX(), pos.getY(), pos.getZ());
            }
        }
        return true;
    }
}
