package xyz.nucleoid.substrate.biome;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryLookupCodec;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;

public class FakingBiomeSource extends BiomeSource {
	public static final Codec<FakingBiomeSource> CODEC = RecordCodecBuilder.create(instance -> instance.group(
			RegistryLookupCodec.of(Registry.BIOME_KEY).forGetter(source -> source.biomeRegistry),
			Codec.LONG.fieldOf("seed").stable().forGetter(source -> source.seed))
			.apply(instance, instance.stable(FakingBiomeSource::new)));

	private final Registry<Biome> biomeRegistry;
	private final long seed;

	public FakingBiomeSource(Registry<Biome> biomeRegistry, long seed) {
		super(ImmutableList.of());
		this.biomeRegistry = biomeRegistry;
		this.seed = seed;
	}

	@Override
	protected Codec<? extends BiomeSource> getCodec() {
		return CODEC;
	}

	@Override
	public BiomeSource withSeed(long seed) {
		return new FakingBiomeSource(this.biomeRegistry, seed);
	}

	@Override
	public Biome getBiomeForNoiseGen(int biomeX, int biomeY, int biomeZ) {
		return biomeRegistry.get(getBiome(biomeX << 2, biomeZ << 2).getFakingBiome());
	}

	// This method is not actually abstract so that the codec works
	public BaseBiomeGen getBiome(int x, int z) {
		throw new AbstractMethodError("You must override this!");
	}
}
