package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.world.World;

public class WorldGenSavannaTree extends WorldGenAbstractTree
{
    private static final String __OBFID = "CL_00000432";

    public WorldGenSavannaTree(boolean p_i45463_1_)
    {
        super(p_i45463_1_);
    }

    public boolean func_76484_a(World p_76484_1_, Random p_76484_2_, int p_76484_3_, int p_76484_4_, int p_76484_5_)
    {
        int l = p_76484_2_.nextInt(3) + p_76484_2_.nextInt(3) + 5;
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
                Block block3 = p_76484_1_.func_147439_a(p_76484_3_, p_76484_4_ - 1, p_76484_5_);

                if ((block3 == Blocks.GRASS || block3 == Blocks.DIRT) && p_76484_4_ < 256 - l - 1)
                {
                    this.func_150515_a(p_76484_1_, p_76484_3_, p_76484_4_ - 1, p_76484_5_, Blocks.DIRT);
                    int j3 = p_76484_2_.nextInt(4);
                    j1 = l - p_76484_2_.nextInt(4) - 1;
                    k1 = 3 - p_76484_2_.nextInt(3);
                    int k3 = p_76484_3_;
                    int l1 = p_76484_5_;
                    int i2 = 0;
                    int j2;
                    int k2;

                    for (j2 = 0; j2 < l; ++j2)
                    {
                        k2 = p_76484_4_ + j2;

                        if (j2 >= j1 && k1 > 0)
                        {
                            k3 += Direction.field_71583_a[j3];
                            l1 += Direction.field_71581_b[j3];
                            --k1;
                        }

                        Block block1 = p_76484_1_.func_147439_a(k3, k2, l1);

                        if (block1.func_149688_o() == Material.field_151579_a || block1.func_149688_o() == Material.field_151584_j)
                        {
                            this.func_150516_a(p_76484_1_, k3, k2, l1, Blocks.LOG_2, 0);
                            i2 = k2;
                        }
                    }

                    for (j2 = -1; j2 <= 1; ++j2)
                    {
                        for (k2 = -1; k2 <= 1; ++k2)
                        {
                            this.func_150525_a(p_76484_1_, k3 + j2, i2 + 1, l1 + k2);
                        }
                    }

                    this.func_150525_a(p_76484_1_, k3 + 2, i2 + 1, l1);
                    this.func_150525_a(p_76484_1_, k3 - 2, i2 + 1, l1);
                    this.func_150525_a(p_76484_1_, k3, i2 + 1, l1 + 2);
                    this.func_150525_a(p_76484_1_, k3, i2 + 1, l1 - 2);

                    for (j2 = -3; j2 <= 3; ++j2)
                    {
                        for (k2 = -3; k2 <= 3; ++k2)
                        {
                            if (Math.abs(j2) != 3 || Math.abs(k2) != 3)
                            {
                                this.func_150525_a(p_76484_1_, k3 + j2, i2, l1 + k2);
                            }
                        }
                    }

                    k3 = p_76484_3_;
                    l1 = p_76484_5_;
                    j2 = p_76484_2_.nextInt(4);

                    if (j2 != j3)
                    {
                        k2 = j1 - p_76484_2_.nextInt(2) - 1;
                        int l3 = 1 + p_76484_2_.nextInt(3);
                        i2 = 0;
                        int l2;
                        int i3;

                        for (l2 = k2; l2 < l && l3 > 0; --l3)
                        {
                            if (l2 >= 1)
                            {
                                i3 = p_76484_4_ + l2;
                                k3 += Direction.field_71583_a[j2];
                                l1 += Direction.field_71581_b[j2];
                                Block block2 = p_76484_1_.func_147439_a(k3, i3, l1);

                                if (block2.func_149688_o() == Material.field_151579_a || block2.func_149688_o() == Material.field_151584_j)
                                {
                                    this.func_150516_a(p_76484_1_, k3, i3, l1, Blocks.LOG_2, 0);
                                    i2 = i3;
                                }
                            }

                            ++l2;
                        }

                        if (i2 > 0)
                        {
                            for (l2 = -1; l2 <= 1; ++l2)
                            {
                                for (i3 = -1; i3 <= 1; ++i3)
                                {
                                    this.func_150525_a(p_76484_1_, k3 + l2, i2 + 1, l1 + i3);
                                }
                            }

                            for (l2 = -2; l2 <= 2; ++l2)
                            {
                                for (i3 = -2; i3 <= 2; ++i3)
                                {
                                    if (Math.abs(l2) != 2 || Math.abs(i3) != 2)
                                    {
                                        this.func_150525_a(p_76484_1_, k3 + l2, i2, l1 + i3);
                                    }
                                }
                            }
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

    private void func_150525_a(World p_150525_1_, int p_150525_2_, int p_150525_3_, int p_150525_4_)
    {
        Block block = p_150525_1_.func_147439_a(p_150525_2_, p_150525_3_, p_150525_4_);

        if (block.func_149688_o() == Material.field_151579_a || block.func_149688_o() == Material.field_151584_j)
        {
            this.func_150516_a(p_150525_1_, p_150525_2_, p_150525_3_, p_150525_4_, Blocks.LEAVES_2, 0);
        }
    }
}