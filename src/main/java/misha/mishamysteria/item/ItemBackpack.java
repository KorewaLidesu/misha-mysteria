package misha.mishamysteria.item;

import misha.mishamysteria.render.ModelBackpack;
import misha.mishamysteria.utils.MishaEnumManager;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class ItemBackpack extends ItemArmor {

    public ItemBackpack() {
        super(MishaEnumManager.MISHA_BACKPACK, 0, EntityEquipmentSlot.CHEST);
        setMaxDamage(0);
        setMaxStackSize(1);
    }

    @SideOnly(Side.CLIENT)
    @Nullable
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default)
    {
        return new ModelBackpack();
    }

}
