package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class WorldGenForest extends WorldGenAbstractTree
{
    private boolean field_150531_a;
    private static final String __OBFID = "CL_00000401";

    public WorldGenForest(boolean p_i45449_1_, boolean p_i45449_2_)
    {
        super(p_i45449_1_);
        this.field_150531_a = p_i45449_2_;
    }

    public boolean func_76484_a(World p_76484_1_, Random p_76484_2_, int p_76484_3_, int p_76484_4_, int p_76484_5_)
    {
        int l = p_76484_2_.nextInt(3) + 5;

        if (this.field_150531_a)
        {
            l += p_76484_2_.nextInt(7);
        }

        boolean flag = true;

        if (p_76484_4_ >= 1 && p_76484_4_ + l + 1 <= 256)
        {
            int j1;
            int k1;

            for (int i1 = p_76484_4_; i1 <= p_76484_4_ + 1 + l; ++i1)
            {
                byte b0 = 1;

                if (i1 == p_76484_4_)
                {
                    b0 = 0;
                }

                if (i1 >= p_76484_4_ + 1 + l - 2)
                {
                    b0 = 2;
                }

                for (j1 = p_76484_3_ - b0; j1 <= p_76484_3_ + b0 && flag; ++j1)
                {
                    for (k1 = p_76484_5_ - b0; k1 <= p_76484_5_ + b0 && flag; ++k1)
                    {
                        if (i1 >= 0 && i1 < 256)
                        {
                            Block block = p_76484_1_.func_147439_a(j1, i1, k1);

                            if (!this.func_150523_a(block))
                            {
                                flag = false;
                            }
                        }
                        else
                        {
                            flag = false;
                        }
                    }
                }
            }

            if (!flag)
            {
                return false;
            }
            else
            {
                Block block2 = p_76484_1_.func_147439_a(p_76484_3_, p_76484_4_ - 1, p_76484_5_);

                if ((block2 == Blocks.field_150349_c || block2 == Blocks.field_150346_d || block2 == Blocks.field_150458_ak) && p_76484_4_ < 256 - l - 1)
                {
                    this.func_150515_a(p_76484_1_, p_76484_3_, p_76484_4_ - 1, p_76484_5_, Blocks.field_150346_d);
                    int k2;

                    for (k2 = p_76484_4_ - 3 + l; k2 <= p_76484_4_ + l; ++k2)
                    {
                        j1 = k2 - (p_76484_4_ + l);
                        k1 = 1 - j1 / 2;

                        for (int l2 = p_76484_3_ - k1; l2 <= p_76484_3_ + k1; ++l2)
                        {
                            int l1 = l2 - p_76484_3_;

                            for (int i2 = p_76484_5_ - k1; i2 <= p_76484_5_ + k1; ++i2)
                            {
                                int j2 = i2 - p_76484_5_;

                                if (Math.abs(l1) != k1 || Math.abs(j2) != k1 || p_76484_2_.nextInt(2) != 0 && j1 != 0)
                                {
                                    Block block1 = p_76484_1_.func_147439_a(l2, k2, i2);

                                    if (block1.func_149688_o() == Material.field_151579_a || block1.func_149688_o() == Material.field_151584_j)
                                    {
                                        this.func_150516_a(p_76484_1_, l2, k2, i2, Blocks.field_150362_t, 2);
                                    }
                                }
                            }
                        }
                    }

                    for (k2 = 0; k2 < l; ++k2)
                    {
                        Block block3 = p_76484_1_.func_147439_a(p_76484_3_, p_76484_4_ + k2, p_76484_5_);

                        if (block3.func_149688_o() == Material.field_151579_a || block3.func_149688_o() == Material.field_151584_j)
                        {
                            this.func_150516_a(p_76484_1_, p_76484_3_, p_76484_4_ + k2, p_76484_5_, Blocks.field_150364_r, 2);
                        }
                    }

                    return true;
                }
                else
                {
                    return false;
                }
            }
        }
        else
        {
            return false;
        }
    }
}