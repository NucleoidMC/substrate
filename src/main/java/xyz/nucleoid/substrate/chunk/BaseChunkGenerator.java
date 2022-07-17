package xyz.nucleoid.substrate.chunk;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import net.minecraft.structure.StructureSet;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
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

	public BaseChunkGenerator(final Registry<StructureSet> structureSetRegistry, final FakingBiomeSource biomeSource) {
		super(structureSetRegistry, Optional.empty(), biomeSource);
		this.biomeSource = biomeSource;
	}

	@Override
	protected Codec<? extends ChunkGenerator> getCodec() {
		return new Codec<>() {
			@Override
			public <T> DataResult<T> encode(ChunkGenerator input, DynamicOps<T> ops, T prefix) {
				return DataResult.success(prefix);
			}

			@Override
			public <T> DataResult<Pair<ChunkGenerator, T>> decode(DynamicOps<T> ops, T input) {
				return DataResult.error("Decoding is not supported.");
			}
		};
	}

	@Override
	public void carve(final ChunkRegion region, final long seed, final NoiseConfig noiseConfig, final BiomeAccess world, final StructureAccessor structureAccessor, final Chunk chunk, final GenerationStep.Carver carverStep) {
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
	public CompletableFuture<Chunk> populateNoise(final Executor executor, final Blender blender, final NoiseConfig noiseConfig, final StructureAccessor structureAccessor, final Chunk chunk) {
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
	public void getDebugHudText(final List<String> text, final NoiseConfig noiseConfig, final BlockPos pos) {
	}
}
