package xyz.nucleoid.substrate.util;

import xyz.nucleoid.substrate.impl.mixin.WeightedListAccessor;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.util.random.Weighted;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.entity.ai.behavior.ShufflingList;

public interface WeightedEntry {


    public static <U> WeightedList<U> createPool(ShufflingList<U> list) {
        var entries = ((WeightedListAccessor<U>) list).getEntries();
        List<Weighted<U>> result = new ArrayList<>();
        for (ShufflingList.WeightedEntry<U> x : entries) {
            var uWeightedEntry = new Weighted<U>(x.getData(), x.getWeight());
            result.add(uWeightedEntry);
        }
        return WeightedList.of(result);
    }
}
