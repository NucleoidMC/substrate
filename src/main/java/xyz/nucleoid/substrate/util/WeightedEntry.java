package xyz.nucleoid.substrate.util;

import net.minecraft.util.collection.Pool;
import net.minecraft.util.collection.Weight;
import net.minecraft.util.collection.Weighted;
import net.minecraft.util.collection.WeightedList;
import xyz.nucleoid.substrate.impl.mixin.WeightedListAccessor;

import java.util.ArrayList;
import java.util.List;

public record WeightedEntry<U>(U object, Weight weight) implements Weighted {
    @Override
    public Weight getWeight() {
        return this.weight;
    }


    public static <U> Pool<WeightedEntry<U>> createPool(WeightedList<U> list) {
        var entries = ((WeightedListAccessor<U>) list).getEntries();
        List<WeightedEntry<U>> result = new ArrayList<>();
        for (WeightedList.Entry<U> x : entries) {
            WeightedEntry<U> uWeightedEntry = new WeightedEntry<>(x.getElement(), Weight.of(x.getWeight()));
            result.add(uWeightedEntry);
        }
        return Pool.of(result);
    }
}
