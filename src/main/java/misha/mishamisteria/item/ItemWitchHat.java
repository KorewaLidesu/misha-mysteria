package misha.mishamisteria.item;

import misha.mishamisteria.MishaMain;
import misha.mishamisteria.block.MishaBlocks;
import misha.mishamisteria.client.MishaGuiHandlers;
import misha.mishamisteria.handlers.MishaStorageWrapper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ItemWitchHat extends ItemBlock {

    public ItemWitchHat() {
        super(MishaBlocks.BLOCK_WITCH_HAT);
        setMaxDamage(0);
        setMaxStackSize(1);
    }

    @Override
    @Nonnull
    public ActionResult<ItemStack> onItemRightClick(World world, @Nonnull EntityPlayer player, @Nonnull EnumHand hand) {
        // Make sure we are interact on physical server side
        if (!world.isRemote) {
            ItemStack is = player.getHeldItem(hand);
            this.openStorage(player);
            return new ActionResult<>(EnumActionResult.SUCCESS, is);
        }

        return super.onItemRightClick(world, player, hand);
    }

    private void openStorage(EntityPlayer player) {
        player.openGui(MishaMain.instance, MishaGuiHandlers.GuiWitchHat_ID, player.world, 0, -1, 0);
    }

    @Override
    public EntityEquipmentSlot getEquipmentSlot(@Nonnull ItemStack stack)
    {
        return EntityEquipmentSlot.HEAD;
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(@Nonnull ItemStack stack, @Nullable NBTTagCompound nbt) {
        return new MishaStorageWrapper(27);
    }

}
