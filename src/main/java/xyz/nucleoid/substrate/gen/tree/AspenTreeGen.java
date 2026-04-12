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

public final class AspenTreeGen implements MapGen {
	public static final AspenTreeGen INSTANCE = new AspenTreeGen(Blocks.BIRCH_LOG.defaultBlockState(), Blocks.BIRCH_LEAVES.defaultBlockState());
	private final BlockState log;
	private final BlockState leaves;

	public AspenTreeGen(BlockState log, BlockState leaves) {
		this.log = log;
		this.leaves = leaves;
	}

	@Override
	public void generate(ServerLevelAccessor world, BlockPos pos, RandomSource random) {
		if (world.getBlockState(pos.below()) != Blocks.GRASS_BLOCK.defaultBlockState()) return;

		double maxRadius = 2 + ((random.nextDouble() - 0.5) * 0.2);
		int leafDistance = random.nextInt(4) + 3;
		BlockPos.MutableBlockPos mutable = pos.mutable();

		for (int y = 0; y < 8; y++) {
			world.setBlock(mutable, this.log, 3);
			// Add branch blocks
			if (maxRadius * this.radius(y / 7.f) > 2.1) {
				Direction.Axis axis = this.getAxis(random);
				world.setBlock(mutable.relative(this.getDirection(axis, random)).above(leafDistance), this.log.setValue(BlockStateProperties.AXIS, axis), 3);
			}

			mutable.move(Direction.UP);
		}

		mutable = pos.mutable();
		mutable.move(Direction.UP, leafDistance);
		for (int y = 0; y < 8; y++) {
			GenHelper.circle(mutable.mutable(), maxRadius * this.radius(y / 7.f), leafPos -> {
				if (world.getBlockState(leafPos).isAir()) {
					world.setBlock(leafPos, this.leaves, 3);
				}
			});

			mutable.move(Direction.UP);
		}
	}

	private double radius(double x) {
		return -Math.pow(((1.4 * x) - 0.3), 2) + 1.2;
	}

	private Direction.Axis getAxis(RandomSource random) {
		return random.nextBoolean() ? Direction.Axis.X : Direction.Axis.Z;
	}

	private Direction getDirection(Direction.Axis axis, RandomSource random) {
		if (axis == Direction.Axis.X) {
			return random.nextBoolean() ? Direction.EAST : Direction.WEST;
		} else {
			return random.nextBoolean() ? Direction.NORTH : Direction.SOUTH;
		}
	}
}
