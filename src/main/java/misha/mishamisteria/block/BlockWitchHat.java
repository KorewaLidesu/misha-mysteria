package misha.mishamisteria.block;

import misha.mishamisteria.MishaMain;
import misha.mishamisteria.abstracts.MishaBasicBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

public class BlockWitchHat extends MishaBasicBlock {
    public BlockWitchHat() {
        super(Material.CLOTH);
        setCreativeTab(MishaMain.MishaCreativeTabs.MISHA_ITEMS);
    }

    // the block will render in the SOLID layer.  See http://greyminecraftcoder.blogspot.co.at/2014/12/block-rendering-18.html for more information.
    @SideOnly(Side.CLIENT)
    @Override
    @Nonnull
    public BlockRenderLayer getRenderLayer()
    {
        return BlockRenderLayer.SOLID;
    }

    // deprecated method allow to be inherited/overridden

    // used by the renderer to control lighting and visibility of other blocks.
    // set to false because this block doesn't fill the entire 1x1x1 space
    @Override
    @SuppressWarnings("deprecation")
    public boolean isOpaqueCube(@Nonnull IBlockState iBlockState) {
        return false;
    }

    // used by the renderer to control lighting and visibility of other blocks, also by
    // (eg) wall or fence to control whether the fence joins itself to this block
    // set to false because this block doesn't fill the entire 1x1x1 space
    @Override
    @SuppressWarnings("deprecation")
    public boolean isFullCube(@Nonnull IBlockState iBlockState) {
        return false;
    }

    // render using a BakedModel (mbe02_block_partial.json --> mbe02_block_partial_model.json)
    // not strictly required because the default (super method) is 3.
    @Override
    @Nonnull
    @SuppressWarnings("deprecation")
    public EnumBlockRenderType getRenderType(@Nonnull IBlockState iBlockState) {
        return EnumBlockRenderType.MODEL;
    }

    // by returning a null collision bounding box we stop the player from colliding with it
    @Override
    @SuppressWarnings("deprecation")
    public AxisAlignedBB getCollisionBoundingBox(@Nonnull IBlockState state, @Nonnull IBlockAccess worldIn, @Nonnull BlockPos pos)
    {
        return NULL_AABB;
    }
}
