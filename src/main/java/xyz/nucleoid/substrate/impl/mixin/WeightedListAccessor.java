package xyz.nucleoid.substrate.impl.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;
import net.minecraft.world.entity.ai.behavior.ShufflingList;

@Mixin(ShufflingList.class)
public interface WeightedListAccessor<U> {
    @Accessor
    List<ShufflingList.WeightedEntry<U>> getEntries();
}
