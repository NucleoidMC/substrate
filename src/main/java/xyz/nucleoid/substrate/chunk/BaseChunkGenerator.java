package xyz.nucleoid.substrate.chunk;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.MapCodec;
import xyz.nucleoid.substrate.biome.FakingBiomeSource;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.blending.Blender;

public class BaseChunkGenerator extends ChunkGenerator {
	private final FakingBiomeSource biomeSource;

	public BaseChunkGenerator(final FakingBiomeSource biomeSource) {
		super(biomeSource);
		this.biomeSource = biomeSource;
	}

	@Override
	protected MapCodec<? extends ChunkGenerator> codec() {
		return MapCodec.unit(this);
	}

	@Override
	public void applyCarvers(WorldGenRegion chunkRegion, long seed, RandomState noiseConfig, BiomeManager biomeAccess, StructureManager structureAccessor, ChunkAccess chunk) {

	}

	@Override
	public void buildSurface(final WorldGenRegion region, final StructureManager structures, final RandomState noiseConfig, final ChunkAccess chunk) {
	}

	@Override
	public void spawnOriginalMobs(final WorldGenRegion region) {
	}

	@Override
	public int getGenDepth() {
		return 0;
	}

	@Override
	public CompletableFuture<ChunkAccess> fillFromNoise(Blender blender, RandomState noiseConfig, StructureManager structureAccessor, ChunkAccess chunk) {
		return CompletableFuture.completedFuture(chunk);
	}

	@Override
	public int getSeaLevel() {
		return 0;
	}

	@Override
	public int getMinY() {
		return 0;
	}

	@Override
	public int getBaseHeight(final int x, final int z, final Heightmap.Types heightmap, final LevelHeightAccessor world, final RandomState noiseConfig) {
		return 0;
	}

	@Override
	public NoiseColumn getBaseColumn(final int x, final int z, final LevelHeightAccessor world, final RandomState noiseConfig) {
		return null;
	}

	@Override
	public void addDebugScreenInfo(List<String> text, RandomState noiseConfig, BlockPos pos) {

	}

}
