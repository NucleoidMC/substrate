package xyz.nucleoid.substrate.chunk;

import java.util.Collections;
import java.util.Optional;
import java.util.Random;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import xyz.nucleoid.substrate.biome.FakingBiomeSource;

import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockView;
import net.minecraft.world.ChunkRegion;
import net.minecraft.world.Heightmap;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeAccess;
import net.minecraft.world.biome.source.BiomeArray;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ProtoChunk;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.StructuresConfig;

public class WorldgenChunkGenerator extends ChunkGenerator {
	public static final Codec<WorldgenChunkGenerator> CODEC = RecordCodecBuilder.create(instance -> instance.group(
			FakingBiomeSource.CODEC.fieldOf("source").forGetter(generator -> generator.biomeSource)
	).apply(instance, WorldgenChunkGenerator::new));

	private final FakingBiomeSource biomeSource;

	public WorldgenChunkGenerator(FakingBiomeSource source) {
		super(source, new StructuresConfig(Optional.empty(), Collections.emptyMap()));

		this.biomeSource = source;
	}

	@Override
	protected Codec<? extends ChunkGenerator> getCodec() {
		return CODEC;
	}

	@Override
	public ChunkGenerator withSeed(long seed) {
		return this;
	}

	@Override
	public void populateBiomes(Registry<Biome> registry, Chunk chunk) {
		ChunkPos chunkPos = chunk.getPos();
		((ProtoChunk)chunk).setBiomes(new BiomeArray(registry, chunkPos, this.biomeSource));
	}

	@Override
	public void buildSurface(ChunkRegion region, Chunk chunk) {

	}

	@Override
	public void populateNoise(WorldAccess world, StructureAccessor accessor, Chunk chunk) {

	}

	@Override
	public void carve(long seed, BiomeAccess access, Chunk chunk, GenerationStep.Carver carver) {

	}

	@Override
	public void generateFeatures(ChunkRegion region, StructureAccessor accessor) {

	}

	@Override
	public int getHeight(int x, int z, Heightmap.Type heightmapType) {
		return 0;
	}

	@Override
	public BlockView getColumnSample(int x, int z) {
		return null;
	}
}
