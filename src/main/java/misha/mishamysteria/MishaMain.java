package misha.mishamysteria;

import misha.mishamysteria.block.MishaBlocks;
import misha.mishamysteria.client.MishaGuiHandlers;
import misha.mishamysteria.item.MishaItems;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;

@Mod(modid = Tags.MOD_ID, name = Tags.MOD_NAME, version = Tags.VERSION)
@Mod.EventBusSubscriber
@SuppressWarnings("unused")
public class MishaMain {

    public static final Logger LOGGER = LogManager.getLogger(Tags.MOD_NAME);

    @Mod.Instance(Tags.MOD_ID)
    public static MishaMain instance;

    /**
     * <a href="https://cleanroommc.com/wiki/forge-mod-development/event#overview">
     *     Take a look at how many FMLStateEvents you can listen to via the @Mod.EventHandler annotation here
     * </a>
     */
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        LOGGER.info("Hello From {}!", Tags.MOD_NAME);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        // registering mod gui handler
        NetworkRegistry.INSTANCE.registerGuiHandler(MishaMain.instance, new MishaGuiHandlers());

    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        //register blocks here using registry
        IForgeRegistry<Block> registry = event.getRegistry();
        MishaBlocks.register(registry);
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        //register items here like we did with the blocks
        IForgeRegistry<Item> registry = event.getRegistry();
        MishaItems.register(registry);
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void initModels(ModelRegistryEvent event) {
        //register models here
        MishaBlocks.MISHA_BLOCKS_LIST.forEach(block -> registerModel(Item.getItemFromBlock(block)));

        MishaItems.MISHA_ITEMS_LIST.forEach(MishaMain::registerModel);
    }

    /**
     * Register model for item
     * Suppress warning since object should have registry name by default
     */
    @SuppressWarnings("ConstantConditions")
    private static void registerModel(Item item) {
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }

    public static class MishaCreativeTabs {
        public static final CreativeTabs MISHA_BLOCKS = new CreativeTabs( "mishaBlocks")
        {
            @SideOnly(Side.CLIENT)
            @Nonnull
            public ItemStack createIcon() { return new ItemStack(Item.getItemFromBlock(net.minecraft.init.Blocks.BRICK_BLOCK)); }
        };

        public static final CreativeTabs MISHA_ITEMS = new CreativeTabs("mishaItems")
        {
            @SideOnly(Side.CLIENT)
            @Nonnull
            public ItemStack createIcon() { return new ItemStack(Items.GOLDEN_HELMET); }
        };
    }

}
