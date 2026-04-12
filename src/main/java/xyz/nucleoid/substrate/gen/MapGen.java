package xyz.nucleoid.substrate.gen;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ServerLevelAccessor;

public interface MapGen {
    void generate(ServerLevelAccessor world, BlockPos pos, RandomSource random);
}
