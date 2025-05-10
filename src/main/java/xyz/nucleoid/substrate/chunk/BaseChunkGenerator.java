package xyz.nucleoid.substrate.chunk;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.MapCodec;
import net.minecraft.registry.Registry;
import net.minecraft.structure.StructureSet;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ChunkRegion;
import net.minecraft.world.HeightLimitView;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.source.BiomeAccess;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.Blender;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.VerticalBlockSample;
import net.minecraft.world.gen.noise.NoiseConfig;
import xyz.nucleoid.substrate.biome.FakingBiomeSource;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class BaseChunkGenerator extends ChunkGenerator {
	private final FakingBiomeSource biomeSource;

	public BaseChunkGenerator(final FakingBiomeSource biomeSource) {
		super(biomeSource);
		this.biomeSource = biomeSource;
	}

	@Override
	protected MapCodec<? extends ChunkGenerator> getCodec() {
		return MapCodec.unit(this);
	}

	@Override
	public void carve(ChunkRegion chunkRegion, long seed, NoiseConfig noiseConfig, BiomeAccess biomeAccess, StructureAccessor structureAccessor, Chunk chunk) {

	}

	@Override
	public void buildSurface(final ChunkRegion region, final StructureAccessor structures, final NoiseConfig noiseConfig, final Chunk chunk) {
	}

	@Override
	public void populateEntities(final ChunkRegion region) {
	}

	@Override
	public int getWorldHeight() {
		return 0;
	}

	@Override
	public CompletableFuture<Chunk> populateNoise(Blender blender, NoiseConfig noiseConfig, StructureAccessor structureAccessor, Chunk chunk) {
		return CompletableFuture.completedFuture(chunk);
	}

	@Override
	public int getSeaLevel() {
		return 0;
	}

	@Override
	public int getMinimumY() {
		return 0;
	}

	@Override
	public int getHeight(final int x, final int z, final Heightmap.Type heightmap, final HeightLimitView world, final NoiseConfig noiseConfig) {
		return 0;
	}

	@Override
	public VerticalBlockSample getColumnSample(final int x, final int z, final HeightLimitView world, final NoiseConfig noiseConfig) {
		return null;
	}

	@Override
	public void appendDebugHudText(List<String> text, NoiseConfig noiseConfig, BlockPos pos) {

	}

}
