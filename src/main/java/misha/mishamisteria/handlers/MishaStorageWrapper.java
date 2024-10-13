package misha.mishamisteria.handlers;

import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class MishaStorageWrapper extends ItemStackHandler implements ICapabilityProvider {

    private final List<Consumer<?>> onChangedEvent = new ArrayList<>();

    public MishaStorageWrapper(int size) {
        super(size);
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
        {
            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(this);
        }
        return null;
    }

    public void appendEvent(Consumer<?> onChangedEvent) {
        this.onChangedEvent.add(onChangedEvent);
    }

    @Override
    protected void onContentsChanged(int slot) {
        onChangedEvent.forEach(it -> it.accept(null));
        super.onContentsChanged(slot);
    }
}
