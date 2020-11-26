package xyz.nucleoid.substrate.biome;

import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;

public interface BaseBiomeGen {
	RegistryKey<Biome> getFakingBiome();
}
