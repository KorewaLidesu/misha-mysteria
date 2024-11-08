package misha.mishamysteria.item;

import misha.mishamysteria.MishaMain;
import misha.mishamysteria.Tags;
import misha.mishamysteria.block.MishaBlocks;
import misha.mishamysteria.utils.ClassUtils;
import net.minecraft.init.Items;
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
    public static final ItemBackpack BACKPACK = registerItem(new ItemBackpack());

    // Registering items via list
    public static void register(IForgeRegistry<Item> registry) {
        MISHA_ITEMS_LIST.forEach(registry::register);

        MishaBlocks.MISHA_BLOCKS_LIST.forEach(it -> {
            if (Item.getItemFromBlock(it) == Items.AIR) registry.register(new ItemBlock(it).setRegistryName(it.getRegistryName()));
        });
    }

    /**
     * Initialize item
     * Suppress warning due to object should have registry name init on construction method
     */
    @SuppressWarnings("ConstantConditions")
    public static <T extends Item> T registerItem(T item) {
        item.setCreativeTab(MishaMain.MishaCreativeTabs.MISHA_ITEMS);
        String item_name = item instanceof ItemBlock ?
                ((ItemBlock) item).getBlock().getRegistryName().getPath() : ClassUtils.getSimpleClassName(item.getClass());
        item.setRegistryName(Tags.MOD_ID, item_name);
        item.setTranslationKey("misha." + item_name);
        MISHA_ITEMS_LIST.add(item);
        return item;
    }
}
