package misha.mishamysteria.tileentity;

import misha.mishamysteria.handlers.MishaStorageWrapper;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TileEntityWitchHat extends TileEntity {

    private MishaStorageWrapper inv;

    @Override
    @Nullable
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(inv);
        }
        return null;
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable net.minecraft.util.EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;
    }

    @Override
    public void readFromNBT(@Nonnull NBTTagCompound compound)
    {
        super.readFromNBT(compound);

        if (this.inv == null) this.inv = new MishaStorageWrapper(27);

        this.inv.deserializeNBT(compound.getCompoundTag("inv"));
    }

    @Override
    @Nonnull
    public NBTTagCompound writeToNBT(@Nonnull NBTTagCompound compound)
    {
        super.writeToNBT(compound);

        if (inv != null) compound.setTag("inv", this.inv.serializeNBT());

        return compound;
    }

    public void initInv(ItemStackHandler inv) {
        this.inv = (MishaStorageWrapper) inv;
    }
}
