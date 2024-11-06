package misha.mishamysteria.block;

import misha.mishamysteria.MishaMain;
import misha.mishamysteria.block.interfaces.IMishaTEProvider;
import misha.mishamysteria.client.MishaGuiHandlers;
import misha.mishamysteria.item.ItemWitchHat;
import misha.mishamysteria.tileentity.TileEntityWitchHat;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class BlockWitchHat extends BlockContainer implements IMishaTEProvider {

    public BlockWitchHat() {
        super(Material.CLOTH);
        setCreativeTab(MishaMain.MishaCreativeTabs.MISHA_ITEMS);
    }

    @Override
    public Class<? extends TileEntity> getTileEntityClass() {
        return TileEntityWitchHat.class;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityWitchHat();
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

    /**
     * Override to make sure block render correctly
     */
    @Override
    @Nonnull
    @SuppressWarnings("deprecation")
    public EnumBlockRenderType getRenderType(@Nonnull IBlockState state) {
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

    @Override
    public void harvestBlock(
            @Nonnull World currentWorld, @Nonnull EntityPlayer player,
            @Nonnull BlockPos pos, @Nonnull IBlockState state, @Nullable TileEntity blockTE,
            @Nonnull ItemStack stack) {
        if (blockTE instanceof TileEntityWitchHat)
        {
            IItemHandler itemHandler = blockTE.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);

            Item item = Item.getItemFromBlock(this);
            if (item == Items.AIR) return;
            ItemStack newHatStack = new ItemStack(item, 1, 0);
            IItemHandler stackItemHandler = newHatStack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);

            for (int i = 0; i < itemHandler.getSlots(); i++) {
                stackItemHandler.insertItem(i, itemHandler.getStackInSlot(i), false);
            }

            spawnAsEntity(currentWorld, pos, newHatStack);
        } else {
            super.harvestBlock(currentWorld, player, pos, state, blockTE, stack);
        }
    }

    /**
     * Called when the block is right-clicked by a player.
     */
    @Override
    public boolean onBlockActivated(
            @Nonnull World worldIn, BlockPos pos, @Nonnull IBlockState state,
            EntityPlayer playerIn, @Nonnull EnumHand hand,
            @Nonnull EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (!worldIn.isRemote)
            playerIn.openGui(MishaMain.instance, MishaGuiHandlers.WITCH_HAT.ordinal(), worldIn, pos.getX(), pos.getY(), pos.getZ());
        return true;
    }

}
