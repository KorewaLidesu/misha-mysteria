package misha.mishamisteria.item;

import misha.mishamisteria.block.MishaBlocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class ItemWitchHat extends ItemBlock {

    public ItemWitchHat() {
        super(MishaBlocks.BLOCK_WITCH_HAT);
        setMaxDamage(0);
        setMaxStackSize(1);
    }

    @Override
    public EntityEquipmentSlot getEquipmentSlot(@Nonnull ItemStack stack)
    {
        return EntityEquipmentSlot.HEAD;
    }

}
