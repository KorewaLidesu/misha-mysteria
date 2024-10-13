package misha.mishamysteria.block;

import misha.mishamysteria.abstracts.MishaBasicBlock;
import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.List;

// Suppress due to object being queued via list or other mods using the object
@SuppressWarnings("unused")
public class MishaBlocks {

    // Modded blocks list
    public static final List<MishaBasicBlock> MISHA_BLOCKS_LIST = new ArrayList<>();

    // Block instance
    public static final BlockWitchHat BLOCK_WITCH_HAT = new BlockWitchHat();

    /**
     * Registering blocks via list
     * Suppress warning due to object should have registry name init on construction method
     */
    @SuppressWarnings("ConstantConditions")
    public static void register(IForgeRegistry<Block> registry) {
        MISHA_BLOCKS_LIST.forEach(it -> {
            registry.register(it);
            if (it.getTileEntityClass() != null) {
                GameRegistry.registerTileEntity(it.getTileEntityClass(), it.getRegistryName());
            }
        });
    }

}
