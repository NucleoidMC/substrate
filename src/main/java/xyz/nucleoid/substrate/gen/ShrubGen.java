package xyz.nucleoid.substrate.gen;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public final class ShrubGen implements MapGen {
	public static final ShrubGen INSTANCE = new ShrubGen(Blocks.OAK_LOG.defaultBlockState(), Blocks.OAK_LEAVES.defaultBlockState().setValue(BlockStateProperties.DISTANCE, 1));
	private final BlockState log;
	private final BlockState leaves;

	public ShrubGen(BlockState log, BlockState leaves) {
		this.log = log;
		this.leaves = leaves;
	}

	@Override
	public void generate(ServerLevelAccessor world, BlockPos pos, RandomSource random) {
		if (world.getBlockState(pos.below()) != Blocks.GRASS_BLOCK.defaultBlockState()) return;

		world.setBlock(pos, this.log, 3);

		if (random.nextBoolean()) {
			pos = pos.above();
			world.setBlock(pos, this.log, 3);
		}

		for (Direction dir : Direction.values()) {
			BlockPos local = pos.relative(dir);

			if (world.getBlockState(local).isAir()) {
				world.setBlock(local, this.leaves, 3);
			}
		}
	}
}
