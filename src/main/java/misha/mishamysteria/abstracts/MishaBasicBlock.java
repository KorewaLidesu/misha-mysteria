package misha.mishamysteria.abstracts;

import misha.mishamysteria.MishaMain;
import misha.mishamysteria.Tags;
import misha.mishamysteria.block.MishaBlocks;
import misha.mishamysteria.utils.ClassUtils;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;

public abstract class MishaBasicBlock extends Block {

    public MishaBasicBlock(Material materialIn) {
        super(materialIn);
        setCreativeTab(MishaMain.MishaCreativeTabs.MISHA_BLOCKS);

        // init registry name and translation key base on class name
        // can be modified to use variable instead
        String block_name = ClassUtils.getSimpleClassName(this.getClass());
        setRegistryName(Tags.MOD_ID, block_name);
        setTranslationKey("misha.block." + block_name);

        // Add to the blocks list to be registered on Forge registry
        MishaBlocks.MISHA_BLOCKS_LIST.add(this);
    }

    /**
     * Inherit/Override this method with desired TE class
     * Used to register TE class
     * @return TE class of this block
     */
    public Class<? extends TileEntity> getTileEntityClass() {
        return null;
    }
}
