package misha.mishamysteria.client.container;

import misha.mishamysteria.item.ItemWitchHat;
import misha.mishamysteria.tileentity.TileEntityWitchHat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

public class ContainerWitchHat extends Container {

    private final BlockPos blockPos;
    private final int numRows;

    public ContainerWitchHat(EntityPlayer player, World world, BlockPos blockPos, IItemHandler itemHandler) {
        this.blockPos = blockPos;

        this.numRows = itemHandler.getSlots() / 9;

        int i = (this.numRows - 4) * 18;

        for (int j = 0; j < this.numRows; ++j)
        {
            for (int k = 0; k < 9; ++k)
            {
                this.addSlotToContainer(new SlotItemHandler(itemHandler, k + j * 9, 8 + k * 18, 18 + j * 18) {

                    private final BlockPos pos = blockPos;

                    @Override
                    public void onSlotChange(@Nonnull ItemStack p_75220_1_, @Nonnull ItemStack p_75220_2_)
                    {
                        // Make sure block loaded first before retrieve TE
                        if (pos == null || !world.isBlockLoaded(pos)) return;

                        TileEntity te = world.getTileEntity(pos);
                        if (te instanceof TileEntityWitchHat) te.markDirty();
                    }
                });
            }
        }

        for (int l = 0; l < 3; ++l)
        {
            for (int j1 = 0; j1 < 9; ++j1)
            {
                this.addSlotToContainer(new Slot(player.inventory, j1 + l * 9 + 9, 8 + j1 * 18, 103 + l * 18 + i));
            }
        }

        for (int i1 = 0; i1 < 9; ++i1)
        {
            this.addSlotToContainer(new Slot(player.inventory, i1, 8 + i1 * 18, 161 + i));
        }
    }

    /**
     * Handle when the stack in slot {@code index} is shift-clicked. Normally this moves the stack between the player
     * inventory and the other inventory(s).
     */
    @Override
    @Nonnull
    public ItemStack transferStackInSlot(@Nonnull EntityPlayer playerIn, int index)
    {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index < this.numRows * 9)
            {
                if (!this.mergeItemStack(itemstack1, this.numRows * 9, this.inventorySlots.size(), true))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 0, this.numRows * 9, false))
            {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty())
            {
                slot.putStack(ItemStack.EMPTY);
            }
            else
            {
                slot.onSlotChanged();
            }
        }

        return itemstack;
    }

    @Override
    public boolean canInteractWith(@Nonnull EntityPlayer playerIn) {
        if (blockPos != null) {
            return true;
        }
        ItemStack stack = playerIn.getHeldItemMainhand();
        return !stack.isEmpty() && stack.getItem() instanceof ItemWitchHat;
    }

}
