package misha.mishamysteria.utils;

import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;

public class MishaEnumManager {

    public static final ItemArmor.ArmorMaterial MISHA_BACKPACK = EnumHelper.addArmorMaterial(
            "MISHA_BACKPACK", "mishamysteria:backpack", 0, new int[]{0,0,0,0}, 0, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F);

}
