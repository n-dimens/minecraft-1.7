package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class WorldGenMelon extends WorldGenerator
{
    private static final String __OBFID = "CL_00000424";

    public boolean func_76484_a(World p_76484_1_, Random p_76484_2_, int p_76484_3_, int p_76484_4_, int p_76484_5_)
    {
        for (int l = 0; l < 64; ++l)
        {
            int i1 = p_76484_3_ + p_76484_2_.nextInt(8) - p_76484_2_.nextInt(8);
            int j1 = p_76484_4_ + p_76484_2_.nextInt(4) - p_76484_2_.nextInt(4);
            int k1 = p_76484_5_ + p_76484_2_.nextInt(8) - p_76484_2_.nextInt(8);

            if (Blocks.MELON_BLOCK.func_149742_c(p_76484_1_, i1, j1, k1) && p_76484_1_.func_147439_a(i1, j1 - 1, k1) == Blocks.GRASS)
            {
                p_76484_1_.func_147465_d(i1, j1, k1, Blocks.MELON_BLOCK, 0, 2);
            }
        }

        return true;
    }
}