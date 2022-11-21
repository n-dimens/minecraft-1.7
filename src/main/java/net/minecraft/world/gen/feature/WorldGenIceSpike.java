package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class WorldGenIceSpike extends WorldGenerator
{
    private static final String __OBFID = "CL_00000417";

    public boolean func_76484_a(World p_76484_1_, Random p_76484_2_, int p_76484_3_, int p_76484_4_, int p_76484_5_)
    {
        while (p_76484_1_.func_147437_c(p_76484_3_, p_76484_4_, p_76484_5_) && p_76484_4_ > 2)
        {
            --p_76484_4_;
        }

        if (p_76484_1_.func_147439_a(p_76484_3_, p_76484_4_, p_76484_5_) != Blocks.SNOW)
        {
            return false;
        }
        else
        {
            p_76484_4_ += p_76484_2_.nextInt(4);
            int l = p_76484_2_.nextInt(4) + 7;
            int i1 = l / 4 + p_76484_2_.nextInt(2);

            if (i1 > 1 && p_76484_2_.nextInt(60) == 0)
            {
                p_76484_4_ += 10 + p_76484_2_.nextInt(30);
            }

            int j1;
            int k1;
            int l1;

            for (j1 = 0; j1 < l; ++j1)
            {
                float f = (1.0F - (float)j1 / (float)l) * (float)i1;
                k1 = MathHelper.func_76123_f(f);

                for (l1 = -k1; l1 <= k1; ++l1)
                {
                    float f1 = (float)MathHelper.func_76130_a(l1) - 0.25F;

                    for (int i2 = -k1; i2 <= k1; ++i2)
                    {
                        float f2 = (float)MathHelper.func_76130_a(i2) - 0.25F;

                        if ((l1 == 0 && i2 == 0 || f1 * f1 + f2 * f2 <= f * f) && (l1 != -k1 && l1 != k1 && i2 != -k1 && i2 != k1 || p_76484_2_.nextFloat() <= 0.75F))
                        {
                            Block block = p_76484_1_.func_147439_a(p_76484_3_ + l1, p_76484_4_ + j1, p_76484_5_ + i2);

                            if (block.func_149688_o() == Material.field_151579_a || block == Blocks.DIRT || block == Blocks.SNOW || block == Blocks.ICE)
                            {
                                this.func_150515_a(p_76484_1_, p_76484_3_ + l1, p_76484_4_ + j1, p_76484_5_ + i2, Blocks.PACKED_ICE);
                            }

                            if (j1 != 0 && k1 > 1)
                            {
                                block = p_76484_1_.func_147439_a(p_76484_3_ + l1, p_76484_4_ - j1, p_76484_5_ + i2);

                                if (block.func_149688_o() == Material.field_151579_a || block == Blocks.DIRT || block == Blocks.SNOW || block == Blocks.ICE)
                                {
                                    this.func_150515_a(p_76484_1_, p_76484_3_ + l1, p_76484_4_ - j1, p_76484_5_ + i2, Blocks.PACKED_ICE);
                                }
                            }
                        }
                    }
                }
            }

            j1 = i1 - 1;

            if (j1 < 0)
            {
                j1 = 0;
            }
            else if (j1 > 1)
            {
                j1 = 1;
            }

            for (int j2 = -j1; j2 <= j1; ++j2)
            {
                k1 = -j1;

                while (k1 <= j1)
                {
                    l1 = p_76484_4_ - 1;
                    int k2 = 50;

                    if (Math.abs(j2) == 1 && Math.abs(k1) == 1)
                    {
                        k2 = p_76484_2_.nextInt(5);
                    }

                    while (true)
                    {
                        if (l1 > 50)
                        {
                            Block block1 = p_76484_1_.func_147439_a(p_76484_3_ + j2, l1, p_76484_5_ + k1);

                            if (block1.func_149688_o() == Material.field_151579_a || block1 == Blocks.DIRT || block1 == Blocks.SNOW || block1 == Blocks.ICE || block1 == Blocks.PACKED_ICE)
                            {
                                this.func_150515_a(p_76484_1_, p_76484_3_ + j2, l1, p_76484_5_ + k1, Blocks.PACKED_ICE);
                                --l1;
                                --k2;

                                if (k2 <= 0)
                                {
                                    l1 -= p_76484_2_.nextInt(5) + 1;
                                    k2 = p_76484_2_.nextInt(5);
                                }

                                continue;
                            }
                        }

                        ++k1;
                        break;
                    }
                }
            }

            return true;
        }
    }
}