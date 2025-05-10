package xyz.nucleoid.substrate.biome;

import com.mojang.serialization.MapCodec;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;

public abstract class FakingBiomeSource extends BiomeSource {

    protected final Registry<Biome> biomeRegistry;
    protected final long seed;

    public FakingBiomeSource(Registry<Biome> biomeRegistry, long seed) {
        this.biomeRegistry = biomeRegistry;
        this.seed = seed;
    }

    @Override
    protected MapCodec<? extends BiomeSource> getCodec() {
        return MapCodec.unit(this);
    }

    @Override
    public RegistryEntry<Biome> getBiome(final int x, final int y, final int z, final MultiNoiseUtil.MultiNoiseSampler noise) {
        return biomeRegistry.getOrThrow(getBiome(x << 2, z << 2).getFakingBiome());
    }

    public abstract BaseBiomeGen getBiome(int x, int z);
}
