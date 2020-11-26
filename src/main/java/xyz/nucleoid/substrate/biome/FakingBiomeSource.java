package xyz.nucleoid.substrate.biome;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryLookupCodec;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;

public abstract class FakingBiomeSource extends BiomeSource {

	private final Registry<Biome> biomeRegistry;
	private final long seed;

	public FakingBiomeSource(Registry<Biome> biomeRegistry, long seed) {
		super(ImmutableList.of());
		this.biomeRegistry = biomeRegistry;
		this.seed = seed;
	}

	@Override
	protected Codec<? extends BiomeSource> getCodec() {
		return null;
	}

	@Override
	public BiomeSource withSeed(long seed) {
		return this;
	}

	@Override
	public Biome getBiomeForNoiseGen(int biomeX, int biomeY, int biomeZ) {
		return biomeRegistry.get(getBiome(biomeX << 2, biomeZ << 2).getFakingBiome());
	}

	public abstract BaseBiomeGen getBiome(int x, int z);
}
