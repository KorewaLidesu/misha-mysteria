package misha.mishamisteria.item;

import misha.mishamisteria.MishaMain;
import misha.mishamisteria.Tags;
import misha.mishamisteria.utils.ClassUtils;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.List;

// Suppress due to object being queued via list or other mods using the object
@SuppressWarnings("unused")
public class MishaItems {

    // Modded items list
    public static final List<Item> MISHA_ITEMS_LIST = new ArrayList<>();

    // Item instance
    public static final ItemWitchHat WITCH_HAT = registerItem(new ItemWitchHat());

    // Registering items via list
    public static void register(IForgeRegistry<Item> registry) {
        MISHA_ITEMS_LIST.forEach(registry::register);
    }

    /**
     * Initialize item
     * Suppress warning due to object should have registry name init on construction method
     */
    @SuppressWarnings("ConstantConditions")
    private static <T extends Item> T registerItem(T item) {
        item.setCreativeTab(MishaMain.MishaCreativeTabs.MISHA_ITEMS);
        String item_name = item instanceof ItemBlock ?
                ((ItemBlock) item).getBlock().getRegistryName().getPath() : ClassUtils.getSimpleClassName(item.getClass());
        item.setRegistryName(Tags.MOD_ID, item_name);
        item.setTranslationKey("misha.item." + item_name);
        MISHA_ITEMS_LIST.add(item);
        return item;
    }

}
