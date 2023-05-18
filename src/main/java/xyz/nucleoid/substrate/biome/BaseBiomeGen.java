package xyz.nucleoid.substrate.biome;

import net.minecraft.registry.RegistryKey;
import net.minecraft.world.biome.Biome;

public interface BaseBiomeGen {
	RegistryKey<Biome> getFakingBiome();
}
