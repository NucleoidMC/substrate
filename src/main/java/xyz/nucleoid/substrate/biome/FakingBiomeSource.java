package xyz.nucleoid.substrate.biome;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;


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
	protected Codec<? extends BiomeSource> getCodec() {
		return new Codec<>() {
			@Override
			public <T> DataResult<T> encode(BiomeSource input, DynamicOps<T> ops, T prefix) {
				return DataResult.success(prefix);
			}

			@Override
			public <T> DataResult<Pair<BiomeSource, T>> decode(DynamicOps<T> ops, T input) {
				return DataResult.error(() -> "Decoding is not supported.");
			}
		};
	}

	@Override
	public RegistryEntry<Biome> getBiome(final int x, final int y, final int z, final MultiNoiseUtil.MultiNoiseSampler noise) {
		return biomeRegistry.getEntry(getBiome(x << 2, z << 2).getFakingBiome()).get();
	}

	public abstract BaseBiomeGen getBiome(int x, int z);
}
