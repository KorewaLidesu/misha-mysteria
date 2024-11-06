package misha.mishamysteria.item;

import misha.mishamysteria.MishaMain;
import misha.mishamysteria.block.MishaBlocks;
import misha.mishamysteria.client.MishaGuiHandlers;
import misha.mishamysteria.handlers.MishaStorageWrapper;
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
            // open gui
            player.openGui(MishaMain.instance, MishaGuiHandlers.WITCH_HAT.ordinal(), player.world, 0, -1, 0);
            return new ActionResult<>(EnumActionResult.SUCCESS, is);
        }

        return super.onItemRightClick(world, player, hand);
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
