package misha.mishamysteria.client;

import misha.mishamysteria.client.container.ContainerWitchHat;
import misha.mishamysteria.client.gui.GuiWitchHat;
import misha.mishamysteria.utils.CapabilityHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nullable;

public enum MishaGuiHandlers implements IGuiHandler {
    /**
     * Do not use this as GUI ID, only use for forge GUI registering
     */
    INSTANCE,

    // register GUI below
    WITCH_HAT,
    ;

    @Nullable
    @Override
    @SideOnly(Side.CLIENT)
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = y < 0 ? null : new BlockPos(x, y, z);
        switch (MishaGuiHandlers.values()[ID]) {
            case WITCH_HAT:
                IItemHandler itemHandler = CapabilityHelper.getItemCapability(world, player, pos);
                if (itemHandler != null) {
                    return new GuiWitchHat(player, world, pos, itemHandler);
                }
            default:
                player.sendMessage(new TextComponentTranslation("misha.gui.client_failed", MishaGuiHandlers.values()[ID].toString()));
        }

        return null;
    }

    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = y < 0 ? null : new BlockPos(x, y, z);
        switch (MishaGuiHandlers.values()[ID]) {
            case WITCH_HAT:
                IItemHandler itemHandler = CapabilityHelper.getItemCapability(world, player, pos);
                if (itemHandler != null) {
                    return new ContainerWitchHat(player, world, pos, itemHandler);
                }
            default:
                player.sendMessage(new TextComponentTranslation("misha.gui.server_failed", MishaGuiHandlers.values()[ID].toString()));
        }

        return null;
    }

}
