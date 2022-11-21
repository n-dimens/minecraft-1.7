package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class WorldGenTaiga1 extends WorldGenAbstractTree
{
    private static final String __OBFID = "CL_00000427";

    public WorldGenTaiga1()
    {
        super(false);
    }

    public boolean func_76484_a(World p_76484_1_, Random p_76484_2_, int p_76484_3_, int p_76484_4_, int p_76484_5_)
    {
        int l = p_76484_2_.nextInt(5) + 7;
        int i1 = l - p_76484_2_.nextInt(2) - 3;
        int j1 = l - i1;
        int k1 = 1 + p_76484_2_.nextInt(j1 + 1);
        boolean flag = true;

        if (p_76484_4_ >= 1 && p_76484_4_ + l + 1 <= 256)
        {
            int i2;
            int j2;
            int i3;

            for (int l1 = p_76484_4_; l1 <= p_76484_4_ + 1 + l && flag; ++l1)
            {
                boolean flag1 = true;

                if (l1 - p_76484_4_ < i1)
                {
                    i3 = 0;
                }
                else
                {
                    i3 = k1;
                }

                for (i2 = p_76484_3_ - i3; i2 <= p_76484_3_ + i3 && flag; ++i2)
                {
                    for (j2 = p_76484_5_ - i3; j2 <= p_76484_5_ + i3 && flag; ++j2)
                    {
                        if (l1 >= 0 && l1 < 256)
                        {
                            Block block = p_76484_1_.func_147439_a(i2, l1, j2);

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
                Block block1 = p_76484_1_.func_147439_a(p_76484_3_, p_76484_4_ - 1, p_76484_5_);

                if ((block1 == Blocks.GRASS || block1 == Blocks.DIRT) && p_76484_4_ < 256 - l - 1)
                {
                    this.func_150515_a(p_76484_1_, p_76484_3_, p_76484_4_ - 1, p_76484_5_, Blocks.DIRT);
                    i3 = 0;

                    for (i2 = p_76484_4_ + l; i2 >= p_76484_4_ + i1; --i2)
                    {
                        for (j2 = p_76484_3_ - i3; j2 <= p_76484_3_ + i3; ++j2)
                        {
                            int j3 = j2 - p_76484_3_;

                            for (int k2 = p_76484_5_ - i3; k2 <= p_76484_5_ + i3; ++k2)
                            {
                                int l2 = k2 - p_76484_5_;

                                if ((Math.abs(j3) != i3 || Math.abs(l2) != i3 || i3 <= 0) && !p_76484_1_.func_147439_a(j2, i2, k2).func_149730_j())
                                {
                                    this.func_150516_a(p_76484_1_, j2, i2, k2, Blocks.LEAVES, 1);
                                }
                            }
                        }

                        if (i3 >= 1 && i2 == p_76484_4_ + i1 + 1)
                        {
                            --i3;
                        }
                        else if (i3 < k1)
                        {
                            ++i3;
                        }
                    }

                    for (i2 = 0; i2 < l - 1; ++i2)
                    {
                        Block block2 = p_76484_1_.func_147439_a(p_76484_3_, p_76484_4_ + i2, p_76484_5_);

                        if (block2.func_149688_o() == Material.field_151579_a || block2.func_149688_o() == Material.field_151584_j)
                        {
                            this.func_150516_a(p_76484_1_, p_76484_3_, p_76484_4_ + i2, p_76484_5_, Blocks.LOG, 1);
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