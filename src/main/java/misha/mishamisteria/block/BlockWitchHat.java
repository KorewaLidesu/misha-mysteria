package misha.mishamisteria.block;

import misha.mishamisteria.MishaMain;
import misha.mishamisteria.abstracts.MishaBasicBlock;
import misha.mishamisteria.client.MishaGuiHandlers;
import misha.mishamisteria.item.ItemWitchHat;
import misha.mishamisteria.block.tileentity.TileEntityWitchHat;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class BlockWitchHat extends MishaBasicBlock implements ITileEntityProvider {
    public BlockWitchHat() {
        super(Material.CLOTH);
        setCreativeTab(MishaMain.MishaCreativeTabs.MISHA_ITEMS);
    }

    @Override
    public Class<? extends TileEntity> getTileEntityClass() {
        return TileEntityWitchHat.class;
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(@Nonnull World worldIn, int meta) {
        return new TileEntityWitchHat();
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

    @Override
    public void onBlockPlacedBy(World worldIn, @Nonnull BlockPos pos, @Nonnull IBlockState state, @Nonnull EntityLivingBase placer, ItemStack stack) {
        TileEntity tileentity = worldIn.getTileEntity(pos);
        if (stack.getItem() instanceof ItemWitchHat && tileentity instanceof TileEntityWitchHat) {
            ((TileEntityWitchHat)tileentity).initInv((ItemStackHandler) stack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null));
        }
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
    }

    /**
     * Called serverside after this block is replaced with another in Chunk, but before the Tile Entity is updated
     * Suppress warning since item handler should not be null
     */
    @SuppressWarnings("ConstantConditions")
    public void breakBlock(World worldIn, @Nonnull BlockPos pos, @Nonnull IBlockState state)
    {
        TileEntity tileentity = worldIn.getTileEntity(pos);

        if (tileentity instanceof TileEntityWitchHat)
        {
            IItemHandler itemHandler = tileentity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);

            Item item = Item.getItemFromBlock(this);
            if (item == Items.AIR) return;
            ItemStack stack = new ItemStack(item, 1, 0);
            IItemHandler stackItemHandler = stack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);

            for (int i = 0; i < itemHandler.getSlots(); i++) {
                stackItemHandler.insertItem(i, itemHandler.getStackInSlot(i), false);
            }

            // TODO: Make sure it is not drop by creative player
            spawnAsEntity(worldIn, pos, stack);
        }

        super.breakBlock(worldIn, pos, state);
    }

    @Override
    public void getDrops(@Nonnull NonNullList<ItemStack> drops, @Nonnull IBlockAccess world, @Nonnull BlockPos pos, @Nonnull IBlockState state, int fortune) {}

    /**
     * Called when the block is right-clicked by a player.
     */
    @Override
    public boolean onBlockActivated(
            @Nonnull World worldIn, BlockPos pos, @Nonnull IBlockState state,
            EntityPlayer playerIn, @Nonnull EnumHand hand,
            @Nonnull EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        playerIn.openGui(MishaMain.instance, MishaGuiHandlers.GuiWitchHat_ID, worldIn, pos.getX(), pos.getY(), pos.getZ());
        return true;
    }
}
