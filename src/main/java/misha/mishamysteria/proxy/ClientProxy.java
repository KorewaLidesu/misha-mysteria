package misha.mishamysteria.proxy;

import misha.mishamysteria.Tags;
import misha.mishamysteria.block.MishaBlocks;
import misha.mishamysteria.item.MishaItems;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy implements IProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        OBJLoader.INSTANCE.addDomain(Tags.MOD_ID);
    }

    @Override
    public void init(FMLInitializationEvent event) {

    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {

    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        MishaBlocks.MISHA_BLOCKS_LIST.forEach(ClientProxy::registerModel);
        MishaItems.MISHA_ITEMS_LIST.forEach(ClientProxy::registerModel);
    }

    /**
     * Register model for block
     * Suppress warning since object should have registry name by default
     */
    @SuppressWarnings("ConstantConditions")
    private static void registerModel(Block block) {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
    }

    /**
     * Register model for item
     * Suppress warning since object should have registry name by default
     */
    @SuppressWarnings("ConstantConditions")
    private static void registerModel(Item item) {
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }

}
