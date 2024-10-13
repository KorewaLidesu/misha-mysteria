package misha.mishamysteria.client;

import misha.mishamysteria.client.container.ContainerWitchHat;
import misha.mishamysteria.client.gui.GuiWitchHat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nullable;

public class MishaGuiHandlers implements IGuiHandler {

    public static final int GuiWitchHat_ID = 0;

    @Nullable
    @Override
    @SideOnly(Side.CLIENT)
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case GuiWitchHat_ID:
                IItemHandler itemHandler;
                if (y < 0) {
                    itemHandler = player.getHeldItemMainhand().getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
                } else {
                    itemHandler = world.getTileEntity(new BlockPos(x, y, z)).getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
                }
                if (itemHandler != null) {
                    return new GuiWitchHat(player, world, x, y, z, itemHandler);
                }

        }

        return null;
    }

    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case GuiWitchHat_ID:
                IItemHandler itemHandler;
                if (y < 0) {
                    itemHandler = player.getHeldItemMainhand().getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
                } else {
                    itemHandler = world.getTileEntity(new BlockPos(x, y, z)).getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
                }
                if (itemHandler != null) {
                    return new ContainerWitchHat(player, world, x, y, z, itemHandler);
                }
        }

        return null;
    }

}
