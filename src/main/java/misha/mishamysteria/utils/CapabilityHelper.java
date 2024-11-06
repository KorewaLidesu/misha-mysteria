package misha.mishamysteria.utils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class CapabilityHelper {

    /**
     * Helper to get itemstack/block capability for item handler access
     * @return Item capability
     */
    public static IItemHandler getItemCapability(World world, EntityPlayer player, BlockPos blockPos) {
        return getCapability(world, player, blockPos, CapabilityItemHandler.ITEM_HANDLER_CAPABILITY);
    }

    /**
     * Helper to get itemstack/block capability for fluid handler access
     * @return Fluid capability
     */
    public static IFluidHandler getFluidCapability(World world, EntityPlayer player, BlockPos blockPos) {
        return getCapability(world, player, blockPos, CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY);
    }

    /**
     * Helper to get itemstack/block capability for energy handler access
     * @return Energy capability
     */
    public static IEnergyStorage getEnergyCapability(World world, EntityPlayer player, BlockPos blockPos) {
        return getCapability(world, player, blockPos, CapabilityEnergy.ENERGY);
    }

    /**
     * Helper to get itemstack/block capability
     * @param world Current world
     * @param player For player inventory access
     * @param blockPos Nullable, to check if dealing block or item
     * @return Capability
     */
    public static <T> T getCapability(World world, EntityPlayer player, BlockPos blockPos, Capability<T> capability) {
        if (blockPos == null) {
            return player.getHeldItemMainhand().getCapability(capability, null);
        } else {
            if (!world.isBlockLoaded(blockPos))
                return null;
            return world.getTileEntity(blockPos).getCapability(capability, null);
        }
    }

}
