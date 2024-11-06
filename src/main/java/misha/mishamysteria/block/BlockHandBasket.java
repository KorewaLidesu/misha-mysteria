package misha.mishamysteria.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

public class BlockHandBasket extends Block {

    // blockstate for "facing" properties
    // 2 ways
    // if need more roration than that, just edit this :3
    public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

    public static final AxisAlignedBB[] BASKET_AABB = {
            // N + S
            new AxisAlignedBB(0.3125D, 0.0D, 0.1875D, 0.6875D, 0.4375D, 0.8125D),
            // W + E
            new AxisAlignedBB(0.1875D, 0.0D, 0.3125D, 0.8125D, 0.4375D, 0.6875D)
    };

    public BlockHandBasket() {
        super(Material.WOOD);
    }

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

    /**
     * Gets the render layer this block will render on. SOLID for solid blocks, CUTOUT or CUTOUT_MIPPED for on-off
     * transparency (glass, reeds), TRANSLUCENT for fully blended transparency (stained glass)
     */
    @SideOnly(Side.CLIENT)
    @Override
    @Nonnull
    public BlockRenderLayer getRenderLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    @Nonnull
    @SuppressWarnings("deprecation")
    public AxisAlignedBB getBoundingBox(IBlockState state, @Nonnull IBlockAccess source, @Nonnull BlockPos pos)
    {
        // 2 facing side implemented, so simple "mod 2" should return the correct hitbox
        return BASKET_AABB[state.getValue(FACING).getHorizontalIndex() % 2];
    }

    @Override
    public void onBlockPlacedBy(World world, @Nonnull BlockPos pos, IBlockState state, EntityLivingBase placer, @Nonnull ItemStack stack) {
        // Simply update blockstate on client side
        world.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing()), 2);
    }

    @Override
    @Nonnull
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING);
    }

    @Override
    @Nonnull
    @SuppressWarnings("deprecation")
    /*
     * Suppress warning since mod for 1.12.2 and the warning is for usage, not overriding
     */
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(FACING, EnumFacing.byHorizontalIndex(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(BlockHorizontal.FACING).getHorizontalIndex();
    }

}
