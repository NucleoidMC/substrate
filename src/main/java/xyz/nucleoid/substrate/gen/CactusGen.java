package xyz.nucleoid.substrate.gen;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;

public final class CactusGen implements MapGen {
    public static final CactusGen INSTANCE = new CactusGen(16, 8, 8);
    private final int count;
    private final int horizontalSpread;
    private final int verticalSpread;

    public CactusGen(int count, int horizontalSpread, int verticalSpread) {

        this.count = count;
        this.horizontalSpread = horizontalSpread;
        this.verticalSpread = verticalSpread;
    }

    public void generate(ServerLevelAccessor world, BlockPos pos, RandomSource random) {
        for(int i = 0; i < this.count; ++i) {
            int aX = random.nextInt(this.horizontalSpread) - random.nextInt(this.horizontalSpread);
            int aY = random.nextInt(this.verticalSpread) - random.nextInt(this.verticalSpread);
            int aZ = random.nextInt(this.horizontalSpread) - random.nextInt(this.horizontalSpread);
            BlockPos local = pos.offset(aX, aY, aZ);

            boolean canGenerate = true;
            for (Direction dir : GenHelper.HORIZONTALS) {
                if (!world.getBlockState(local.relative(dir)).isAir()) {
                    canGenerate = false;
                    break;
                }
            }

            if (canGenerate && (world.getBlockState(local.below()) == Blocks.SAND.defaultBlockState() || world.getBlockState(local.below()) == Blocks.CACTUS.defaultBlockState()) && world.getBlockState(local).isAir()) {
                world.setBlock(local, Blocks.CACTUS.defaultBlockState(), 3);
            }
        }
    }
}
