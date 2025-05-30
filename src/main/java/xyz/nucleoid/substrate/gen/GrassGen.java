package xyz.nucleoid.substrate.gen;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.collection.Pool;
import net.minecraft.util.collection.Weighted;
import net.minecraft.util.collection.WeightedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.ServerWorldAccess;
import xyz.nucleoid.substrate.util.WeightedEntry;

public final class GrassGen implements MapGen {
    public static final GrassGen INSTANCE = new GrassGen(new WeightedList<BlockState>()
            .add(Blocks.SHORT_GRASS.getDefaultState(), 32)
            .add(Blocks.DANDELION.getDefaultState(), 1)
            .add(Blocks.POPPY.getDefaultState(), 1), 16, 8, 4);
    private final Pool<BlockState> states;
    private final int count;
    private final int horizontalSpread;
    private final int verticalSpread;

    public GrassGen(WeightedList<BlockState> states, int count, int horizontalSpread, int verticalSpread) {
        this.states = WeightedEntry.createPool(states);
        this.count = count;
        this.horizontalSpread = horizontalSpread;
        this.verticalSpread = verticalSpread;
    }

    @Override
    public void generate(ServerWorldAccess world, BlockPos pos, Random random) {
        BlockState state = this.states.getOrEmpty(random).orElse(Blocks.AIR.getDefaultState());

        for (int i = 0; i < this.count; i++) {
            int aX = random.nextInt(this.horizontalSpread) - random.nextInt(this.horizontalSpread);
            int aY = random.nextInt(this.verticalSpread) - random.nextInt(this.verticalSpread);
            int aZ = random.nextInt(this.horizontalSpread) - random.nextInt(this.horizontalSpread);
            BlockPos local = pos.add(aX, aY, aZ);

            if (world.getBlockState(local.down()) == Blocks.GRASS_BLOCK.getDefaultState() && world.getBlockState(local).isAir()) {
                world.setBlockState(local, state, 3);
            }
        }
    }
}
