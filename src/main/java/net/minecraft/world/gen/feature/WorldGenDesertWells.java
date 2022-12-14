package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class WorldGenDesertWells extends WorldGenerator
{
    private static final String __OBFID = "CL_00000407";

    public boolean func_76484_a(World p_76484_1_, Random p_76484_2_, int p_76484_3_, int p_76484_4_, int p_76484_5_)
    {
        while (p_76484_1_.func_147437_c(p_76484_3_, p_76484_4_, p_76484_5_) && p_76484_4_ > 2)
        {
            --p_76484_4_;
        }

        if (p_76484_1_.func_147439_a(p_76484_3_, p_76484_4_, p_76484_5_) != Blocks.SAND)
        {
            return false;
        }
        else
        {
            int l;
            int i1;

            for (l = -2; l <= 2; ++l)
            {
                for (i1 = -2; i1 <= 2; ++i1)
                {
                    if (p_76484_1_.func_147437_c(p_76484_3_ + l, p_76484_4_ - 1, p_76484_5_ + i1) && p_76484_1_.func_147437_c(p_76484_3_ + l, p_76484_4_ - 2, p_76484_5_ + i1))
                    {
                        return false;
                    }
                }
            }

            for (l = -1; l <= 0; ++l)
            {
                for (i1 = -2; i1 <= 2; ++i1)
                {
                    for (int j1 = -2; j1 <= 2; ++j1)
                    {
                        p_76484_1_.func_147465_d(p_76484_3_ + i1, p_76484_4_ + l, p_76484_5_ + j1, Blocks.SANDSTONE, 0, 2);
                    }
                }
            }

            p_76484_1_.func_147465_d(p_76484_3_, p_76484_4_, p_76484_5_, Blocks.FLOWING_WATER, 0, 2);
            p_76484_1_.func_147465_d(p_76484_3_ - 1, p_76484_4_, p_76484_5_, Blocks.FLOWING_WATER, 0, 2);
            p_76484_1_.func_147465_d(p_76484_3_ + 1, p_76484_4_, p_76484_5_, Blocks.FLOWING_WATER, 0, 2);
            p_76484_1_.func_147465_d(p_76484_3_, p_76484_4_, p_76484_5_ - 1, Blocks.FLOWING_WATER, 0, 2);
            p_76484_1_.func_147465_d(p_76484_3_, p_76484_4_, p_76484_5_ + 1, Blocks.FLOWING_WATER, 0, 2);

            for (l = -2; l <= 2; ++l)
            {
                for (i1 = -2; i1 <= 2; ++i1)
                {
                    if (l == -2 || l == 2 || i1 == -2 || i1 == 2)
                    {
                        p_76484_1_.func_147465_d(p_76484_3_ + l, p_76484_4_ + 1, p_76484_5_ + i1, Blocks.SANDSTONE, 0, 2);
                    }
                }
            }

            p_76484_1_.func_147465_d(p_76484_3_ + 2, p_76484_4_ + 1, p_76484_5_, Blocks.STONE_SLAB, 1, 2);
            p_76484_1_.func_147465_d(p_76484_3_ - 2, p_76484_4_ + 1, p_76484_5_, Blocks.STONE_SLAB, 1, 2);
            p_76484_1_.func_147465_d(p_76484_3_, p_76484_4_ + 1, p_76484_5_ + 2, Blocks.STONE_SLAB, 1, 2);
            p_76484_1_.func_147465_d(p_76484_3_, p_76484_4_ + 1, p_76484_5_ - 2, Blocks.STONE_SLAB, 1, 2);

            for (l = -1; l <= 1; ++l)
            {
                for (i1 = -1; i1 <= 1; ++i1)
                {
                    if (l == 0 && i1 == 0)
                    {
                        p_76484_1_.func_147465_d(p_76484_3_ + l, p_76484_4_ + 4, p_76484_5_ + i1, Blocks.SANDSTONE, 0, 2);
                    }
                    else
                    {
                        p_76484_1_.func_147465_d(p_76484_3_ + l, p_76484_4_ + 4, p_76484_5_ + i1, Blocks.STONE_SLAB, 1, 2);
                    }
                }
            }

            for (l = 1; l <= 3; ++l)
            {
                p_76484_1_.func_147465_d(p_76484_3_ - 1, p_76484_4_ + l, p_76484_5_ - 1, Blocks.SANDSTONE, 0, 2);
                p_76484_1_.func_147465_d(p_76484_3_ - 1, p_76484_4_ + l, p_76484_5_ + 1, Blocks.SANDSTONE, 0, 2);
                p_76484_1_.func_147465_d(p_76484_3_ + 1, p_76484_4_ + l, p_76484_5_ - 1, Blocks.SANDSTONE, 0, 2);
                p_76484_1_.func_147465_d(p_76484_3_ + 1, p_76484_4_ + l, p_76484_5_ + 1, Blocks.SANDSTONE, 0, 2);
            }

            return true;
        }
    }
}