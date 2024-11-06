package misha.mishamysteria;

import misha.mishamysteria.block.MishaBlocks;
import misha.mishamysteria.client.MishaGuiHandlers;
import misha.mishamysteria.item.MishaItems;
import misha.mishamysteria.proxy.IProxy;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
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

    @SidedProxy(clientSide = "misha.mishamysteria.proxy.ClientProxy", serverSide = "misha.mishamysteria.proxy.ServerProxy")
    public static IProxy proxy;

    /**
     * <a href="https://cleanroommc.com/wiki/forge-mod-development/event#overview">
     *     Take a look at how many FMLStateEvents you can listen to via the @Mod.EventHandler annotation here
     * </a>
     */
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        LOGGER.info("Hello From {}!", Tags.MOD_NAME);
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        // registering mod gui handler
        NetworkRegistry.INSTANCE.registerGuiHandler(MishaMain.instance, MishaGuiHandlers.INSTANCE);
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
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

    public static class MishaCreativeTabs {
        public static final CreativeTabs MISHA_BLOCKS = new CreativeTabs( "mishaBlocks")
        {
            @SideOnly(Side.CLIENT)
            @Nonnull
            public ItemStack createIcon() { return new ItemStack(Item.getItemFromBlock(MishaBlocks.BLOCK_CIRNO)); }
        };

        public static final CreativeTabs MISHA_ITEMS = new CreativeTabs("mishaItems")
        {
            @SideOnly(Side.CLIENT)
            @Nonnull
            public ItemStack createIcon() { return new ItemStack(MishaItems.WITCH_HAT); }
        };
    }

}
