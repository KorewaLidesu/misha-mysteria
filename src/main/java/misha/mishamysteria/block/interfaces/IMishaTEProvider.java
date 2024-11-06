package misha.mishamysteria.block.interfaces;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.tileentity.TileEntity;

public interface IMishaTEProvider extends ITileEntityProvider {

    /**
     * Inherit/Override this method with desired TE class
     * Used to register TE class
     * @return TE class of this block
     */
    Class<? extends TileEntity> getTileEntityClass();

}
