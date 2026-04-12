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

public final class PoplarTreeGen implements MapGen {
    public static final PoplarTreeGen INSTANCE = new PoplarTreeGen(Blocks.OAK_LOG.defaultBlockState(), Blocks.OAK_LEAVES.defaultBlockState().setValue(BlockStateProperties.DISTANCE, 1));

    private final BlockState log;
    private final BlockState leaves;

    public PoplarTreeGen(BlockState log, BlockState leaves) {
        this.log = log;
        this.leaves = leaves;
    }

    @Override
    public void generate(ServerLevelAccessor world, BlockPos pos, RandomSource random) {
        if (world.getBlockState(pos.below()) != Blocks.GRASS_BLOCK.defaultBlockState()) return;

        double maxRadius = 2.6 + ((random.nextDouble() - 0.5) * 0.2);
        int leafDistance = random.nextInt(3) + 2;

        BlockPos.MutableBlockPos mutable = pos.mutable();
        for (int y = 0; y < 12; y++) {
            world.setBlock(mutable, this.log, 0);
            // Add branch blocks
            if (maxRadius * this.radius(y / 11.f) > 2.3) {
                Direction.Axis axis = this.getAxis(random);
                world.setBlock(mutable.relative(this.getDirection(axis, random)).above(leafDistance), this.log.setValue(BlockStateProperties.AXIS, axis), 0);
            }

            mutable.move(Direction.UP);
        }

        mutable = pos.mutable();
        mutable.move(Direction.UP, leafDistance);

        for (int y = 0; y < 12; y++) {
            GenHelper.circle(mutable.mutable(), maxRadius * this.radius(y / 11.f), leafPos -> {
                if (world.getBlockState(leafPos).isAir()) {
                    world.setBlock(leafPos, this.leaves, 0);
                }
            });

            mutable.move(Direction.UP);
        }
    }

    private double radius(double x) {
        return (-2 * (x * x * x)) + (1.9 * x) + 0.2;
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
