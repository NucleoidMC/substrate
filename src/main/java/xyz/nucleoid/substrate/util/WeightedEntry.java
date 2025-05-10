package xyz.nucleoid.substrate.util;

import net.minecraft.util.collection.Pool;
import net.minecraft.util.collection.Weighted;
import net.minecraft.util.collection.WeightedList;
import xyz.nucleoid.substrate.impl.mixin.WeightedListAccessor;

import java.util.ArrayList;
import java.util.List;

public interface WeightedEntry {


    public static <U> Pool<U> createPool(WeightedList<U> list) {
        var entries = ((WeightedListAccessor<U>) list).getEntries();
        List<Weighted<U>> result = new ArrayList<>();
        for (WeightedList.Entry<U> x : entries) {
            var uWeightedEntry = new Weighted<U>(x.getElement(), x.getWeight());
            result.add(uWeightedEntry);
        }
        return Pool.of(result);
    }
}
