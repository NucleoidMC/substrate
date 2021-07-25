package xyz.nucleoid.substrate.chunk;

import java.util.Collections;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import net.minecraft.world.*;
import net.minecraft.world.gen.chunk.VerticalBlockSample;
import xyz.nucleoid.substrate.biome.FakingBiomeSource;

import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeAccess;
import net.minecraft.world.biome.source.BiomeArray;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ProtoChunk;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.StructuresConfig;

public class BaseChunkGenerator extends ChunkGenerator {
	private final FakingBiomeSource biomeSource;

	public BaseChunkGenerator(FakingBiomeSource source) {
		super(source, new StructuresConfig(Optional.empty(), Collections.emptyMap()));

		this.biomeSource = source;
	}

	@Override
	protected Codec<? extends ChunkGenerator> getCodec() {
		return new Codec<ChunkGenerator>() {
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
	public ChunkGenerator withSeed(long seed) {
		return this;
	}

	@Override
	public void populateBiomes(Registry<Biome> registry, Chunk chunk) {
		ChunkPos chunkPos = chunk.getPos();
		((ProtoChunk)chunk).setBiomes(new BiomeArray(registry, chunk, chunkPos, this.biomeSource));
	}

	@Override
	public void buildSurface(ChunkRegion region, Chunk chunk) {

	}

	@Override
	public CompletableFuture<Chunk> populateNoise(Executor executor, StructureAccessor accessor, Chunk chunk) {
		return CompletableFuture.completedFuture(chunk);
	}

	@Override
	public int getHeight(int x, int z, Heightmap.Type heightmap, HeightLimitView world) {
		return 0;
	}

	@Override
	public VerticalBlockSample getColumnSample(int x, int z, HeightLimitView world) {
		return null;
	}


	@Override
	public void carve(long seed, BiomeAccess access, Chunk chunk, GenerationStep.Carver carver) {

	}

	@Override
	public void generateFeatures(ChunkRegion region, StructureAccessor accessor) {

	}
}
