package xyz.nucleoid.substrate.biome;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.Registry;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeResolver;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.biome.Climate;

public abstract class FakingBiomeSource extends BiomeSource {

    protected final Registry<Biome> biomeRegistry;
    protected final long seed;

    public FakingBiomeSource(Registry<Biome> biomeRegistry, long seed) {
        this.biomeRegistry = biomeRegistry;
        this.seed = seed;
    }

    @Override
    protected MapCodec<? extends BiomeSource> codec() {
        return MapCodec.unit(this);
    }

    @Override
    public BiomeResolver createResolver(Climate.Sampler noise){
        return (x, _, z) -> biomeRegistry.getOrThrow(getBiome(x << 2, z << 2).getFakingBiome());
    }

    public abstract BaseBiomeGen getBiome(int x, int z);
}
