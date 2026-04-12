package xyz.nucleoid.substrate.biome;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;

public interface BaseBiomeGen {
	ResourceKey<Biome> getFakingBiome();
}
