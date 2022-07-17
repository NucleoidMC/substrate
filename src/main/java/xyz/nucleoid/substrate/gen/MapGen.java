package xyz.nucleoid.substrate.gen;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.ServerWorldAccess;

public interface MapGen {
    void generate(ServerWorldAccess world, BlockPos pos, Random random);
}
