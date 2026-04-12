package xyz.nucleoid.substrate.gen;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.entity.ai.behavior.ShufflingList;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import xyz.nucleoid.substrate.util.WeightedEntry;

public final class GrassGen implements MapGen {
    public static final GrassGen INSTANCE = new GrassGen(new ShufflingList<BlockState>()
            .add(Blocks.SHORT_GRASS.defaultBlockState(), 32)
            .add(Blocks.DANDELION.defaultBlockState(), 1)
            .add(Blocks.POPPY.defaultBlockState(), 1), 16, 8, 4);
    private final WeightedList<BlockState> states;
    private final int count;
    private final int horizontalSpread;
    private final int verticalSpread;

    public GrassGen(ShufflingList<BlockState> states, int count, int horizontalSpread, int verticalSpread) {
        this.states = WeightedEntry.createPool(states);
        this.count = count;
        this.horizontalSpread = horizontalSpread;
        this.verticalSpread = verticalSpread;
    }

    @Override
    public void generate(ServerLevelAccessor world, BlockPos pos, RandomSource random) {
        BlockState state = this.states.getRandom(random).orElse(Blocks.AIR.defaultBlockState());

        for (int i = 0; i < this.count; i++) {
            int aX = random.nextInt(this.horizontalSpread) - random.nextInt(this.horizontalSpread);
            int aY = random.nextInt(this.verticalSpread) - random.nextInt(this.verticalSpread);
            int aZ = random.nextInt(this.horizontalSpread) - random.nextInt(this.horizontalSpread);
            BlockPos local = pos.offset(aX, aY, aZ);

            if (world.getBlockState(local.below()) == Blocks.GRASS_BLOCK.defaultBlockState() && world.getBlockState(local).isAir()) {
                world.setBlock(local, state, 3);
            }
        }
    }
}
