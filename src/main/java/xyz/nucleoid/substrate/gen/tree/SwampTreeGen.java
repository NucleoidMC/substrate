package xyz.nucleoid.substrate.gen.tree;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import xyz.nucleoid.substrate.gen.GenHelper;
import xyz.nucleoid.substrate.gen.MapGen;

public final class SwampTreeGen implements MapGen {
	public static final SwampTreeGen INSTANCE = new SwampTreeGen(Blocks.OAK_LOG.defaultBlockState(), Blocks.OAK_LEAVES.defaultBlockState().setValue(BlockStateProperties.DISTANCE, 1));
	private final BlockState log;
	private final BlockState leaves;

	public SwampTreeGen(BlockState log, BlockState leaves) {
		this.log = log;
		this.leaves = leaves;
	}

	@Override
	public void generate(ServerLevelAccessor world, BlockPos pos, RandomSource random) {
		if (world.getBlockState(pos.below()) != Blocks.GRASS_BLOCK.defaultBlockState()) return;

		double maxRadius = 1 + ((random.nextDouble() - 0.5) * 0.2);
		int leafDistance = random.nextInt(3) + 3;

		BlockPos.MutableBlockPos mutable = pos.mutable();
		for (int y = 0; y < 6; y++) {
			world.setBlock(mutable, this.log, 3);

			mutable.move(Direction.UP);
		}

		mutable = pos.mutable();
		mutable.move(Direction.UP, leafDistance);

		for (int y = 0; y < 5; y++) {
			GenHelper.circle(mutable.mutable(), maxRadius * this.radius(y / 5.f), leafPos -> {
				if (world.getBlockState(leafPos).isAir()) {
					world.setBlock(leafPos, this.leaves, 0);
				}
			});

			mutable.move(Direction.UP);
		}
	}

	private double radius(double x) {
		return Math.max((-2.3 * (x * x * x)) + 2.5, 0);
	}
}
