package xyz.nucleoid.substrate.gen;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.entity.ai.behavior.ShufflingList;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import xyz.nucleoid.substrate.util.WeightedEntry;

public final class DiskGen implements MapGen {
    public static final DiskGen INSTANCE = new DiskGen(new ShufflingList<BlockState>()
            .add(Blocks.SAND.defaultBlockState(), 1)
            .add(Blocks.GRAVEL.defaultBlockState(), 1), 2, 5);
    private final WeightedList<BlockState> states;
    private final int baseSize;
    private final int randomSize;

    public DiskGen(ShufflingList<BlockState> states, int baseSize, int randomSize) {
        this.states = WeightedEntry.createPool(states);
        this.baseSize = baseSize;
        this.randomSize = randomSize;
    }

    @Override
    public void generate(ServerLevelAccessor world, BlockPos pos, RandomSource random) {

        int radius = random.nextInt(this.randomSize) + this.baseSize;
        int radiusSquared = radius * radius;

        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
        BlockState state = this.states.getRandom(random).orElse(Blocks.AIR.defaultBlockState());

        for (int x = pos.getX() - radius; x <= pos.getX() + radius; ++x) {
            for (int z = pos.getZ() - radius; z <= pos.getZ() + radius; ++z) {
                int localX = x - pos.getX();
                int localZ = z - pos.getZ();
                if (localX * localX + localZ * localZ <= radiusSquared) {
                    for (int y = pos.getY() - 2; y <= pos.getY() + 2; ++y) {
                        mutable.set(x, y, z);

                        if (world.getBlockState(mutable).is(Blocks.DIRT) || world.getBlockState(mutable).is(Blocks.GRASS_BLOCK)) {
                            world.setBlock(mutable, state, 3);

                            if (!world.getBlockState(mutable.above()).canSurvive(world, mutable)) {
                                world.setBlock(mutable.above(), Blocks.AIR.defaultBlockState(), 3);
                            }
                        }
                    }
                }
            }
        }
    }
}
