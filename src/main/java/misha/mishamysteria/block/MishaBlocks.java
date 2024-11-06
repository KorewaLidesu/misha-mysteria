package misha.mishamysteria.block;

import misha.mishamysteria.abstracts.MishaBasicBlock;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

// Suppress due to object being queued via list or other mods using the object
@SuppressWarnings("unused")
public class MishaBlocks {

    // Modded blocks list
    public static final List<Block> MISHA_BLOCKS_LIST = new ArrayList<>();

    // Block instance
    public static final BlockWitchHat BLOCK_WITCH_HAT = new BlockWitchHat();
    public static final BlockCirno BLOCK_CIRNO = registerBlock(new BlockCirno());
    public static final BlockFumoCirno BLOCK_FUMO_CIRNO = registerBlock(new BlockFumoCirno());
    public static final BlockHandBasket BLOCK_HAND_BASKET = registerBlock(new BlockHandBasket(), MishaMain.MishaCreativeTabs.MISHA_ITEMS);
    public static final BlockWitchHat BLOCK_WITCH_HAT = registerBlock(new BlockWitchHat(), MishaMain.MishaCreativeTabs.MISHA_ITEMS);

    /**
     * Registering blocks via list
     * Suppress warning due to object should have registry name init on construction method
     */
    @SuppressWarnings("ConstantConditions")
    public static <T extends IForgeRegistryEntry.Impl<Block>> void register(IForgeRegistry<Block> registry) {
        MISHA_BLOCKS_LIST.forEach(it -> {
            registry.register(it);
            if (it instanceof IMishaTEProvider && ((IMishaTEProvider) it).getTileEntityClass() != null) {
                GameRegistry.registerTileEntity(((IMishaTEProvider) it).getTileEntityClass(), it.getRegistryName());
            }
        });
    }

    /**
     * Initialize item
     * Suppress warning due to object should have registry name init on construction method
     */
    @SuppressWarnings("ConstantConditions")
    private static <T extends Block> T registerBlock(T block) {
        return registerBlock(block, MishaMain.MishaCreativeTabs.MISHA_BLOCKS);
    }

    /**
     * Initialize item
     * Suppress warning due to object should have registry name init on construction method
     */
    @SuppressWarnings("ConstantConditions")
    private static <T extends Block> T registerBlock(@Nonnull T block, @Nonnull CreativeTabs creativeTab) {
        String block_name = ClassUtils.getSimpleClassName(block.getClass());
        block.setRegistryName(Tags.MOD_ID, block_name);
        block.setTranslationKey("misha." + block_name);
        block.setCreativeTab(creativeTab);
        MISHA_BLOCKS_LIST.add(block);
        return block;
    }

}
