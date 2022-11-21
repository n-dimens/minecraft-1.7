package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.world.World;

public class WorldGenTrees extends WorldGenAbstractTree
{
    private final int field_76533_a;
    private final boolean field_76531_b;
    private final int field_76532_c;
    private final int field_76530_d;
    private static final String __OBFID = "CL_00000438";

    public WorldGenTrees(boolean p_i2027_1_)
    {
        this(p_i2027_1_, 4, 0, 0, false);
    }

    public WorldGenTrees(boolean p_i2028_1_, int p_i2028_2_, int p_i2028_3_, int p_i2028_4_, boolean p_i2028_5_)
    {
        super(p_i2028_1_);
        this.field_76533_a = p_i2028_2_;
        this.field_76532_c = p_i2028_3_;
        this.field_76530_d = p_i2028_4_;
        this.field_76531_b = p_i2028_5_;
    }

    public boolean func_76484_a(World p_76484_1_, Random p_76484_2_, int p_76484_3_, int p_76484_4_, int p_76484_5_)
    {
        int l = p_76484_2_.nextInt(3) + this.field_76533_a;
        boolean flag = true;

        if (p_76484_4_ >= 1 && p_76484_4_ + l + 1 <= 256)
        {
            byte b0;
            int k1;
            Block block;

            for (int i1 = p_76484_4_; i1 <= p_76484_4_ + 1 + l; ++i1)
            {
                b0 = 1;

                if (i1 == p_76484_4_)
                {
                    b0 = 0;
                }

                if (i1 >= p_76484_4_ + 1 + l - 2)
                {
                    b0 = 2;
                }

                for (int j1 = p_76484_3_ - b0; j1 <= p_76484_3_ + b0 && flag; ++j1)
                {
                    for (k1 = p_76484_5_ - b0; k1 <= p_76484_5_ + b0 && flag; ++k1)
                    {
                        if (i1 >= 0 && i1 < 256)
                        {
                            block = p_76484_1_.func_147439_a(j1, i1, k1);

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

                if ((block2 == Blocks.GRASS || block2 == Blocks.DIRT || block2 == Blocks.FARMLAND) && p_76484_4_ < 256 - l - 1)
                {
                    this.func_150515_a(p_76484_1_, p_76484_3_, p_76484_4_ - 1, p_76484_5_, Blocks.DIRT);
                    b0 = 3;
                    byte b1 = 0;
                    int l1;
                    int i2;
                    int j2;
                    int i3;

                    for (k1 = p_76484_4_ - b0 + l; k1 <= p_76484_4_ + l; ++k1)
                    {
                        i3 = k1 - (p_76484_4_ + l);
                        l1 = b1 + 1 - i3 / 2;

                        for (i2 = p_76484_3_ - l1; i2 <= p_76484_3_ + l1; ++i2)
                        {
                            j2 = i2 - p_76484_3_;

                            for (int k2 = p_76484_5_ - l1; k2 <= p_76484_5_ + l1; ++k2)
                            {
                                int l2 = k2 - p_76484_5_;

                                if (Math.abs(j2) != l1 || Math.abs(l2) != l1 || p_76484_2_.nextInt(2) != 0 && i3 != 0)
                                {
                                    Block block1 = p_76484_1_.func_147439_a(i2, k1, k2);

                                    if (block1.func_149688_o() == Material.field_151579_a || block1.func_149688_o() == Material.field_151584_j)
                                    {
                                        this.func_150516_a(p_76484_1_, i2, k1, k2, Blocks.LEAVES, this.field_76530_d);
                                    }
                                }
                            }
                        }
                    }

                    for (k1 = 0; k1 < l; ++k1)
                    {
                        block = p_76484_1_.func_147439_a(p_76484_3_, p_76484_4_ + k1, p_76484_5_);

                        if (block.func_149688_o() == Material.field_151579_a || block.func_149688_o() == Material.field_151584_j)
                        {
                            this.func_150516_a(p_76484_1_, p_76484_3_, p_76484_4_ + k1, p_76484_5_, Blocks.LOG, this.field_76532_c);

                            if (this.field_76531_b && k1 > 0)
                            {
                                if (p_76484_2_.nextInt(3) > 0 && p_76484_1_.func_147437_c(p_76484_3_ - 1, p_76484_4_ + k1, p_76484_5_))
                                {
                                    this.func_150516_a(p_76484_1_, p_76484_3_ - 1, p_76484_4_ + k1, p_76484_5_, Blocks.VINE, 8);
                                }

                                if (p_76484_2_.nextInt(3) > 0 && p_76484_1_.func_147437_c(p_76484_3_ + 1, p_76484_4_ + k1, p_76484_5_))
                                {
                                    this.func_150516_a(p_76484_1_, p_76484_3_ + 1, p_76484_4_ + k1, p_76484_5_, Blocks.VINE, 2);
                                }

                                if (p_76484_2_.nextInt(3) > 0 && p_76484_1_.func_147437_c(p_76484_3_, p_76484_4_ + k1, p_76484_5_ - 1))
                                {
                                    this.func_150516_a(p_76484_1_, p_76484_3_, p_76484_4_ + k1, p_76484_5_ - 1, Blocks.VINE, 1);
                                }

                                if (p_76484_2_.nextInt(3) > 0 && p_76484_1_.func_147437_c(p_76484_3_, p_76484_4_ + k1, p_76484_5_ + 1))
                                {
                                    this.func_150516_a(p_76484_1_, p_76484_3_, p_76484_4_ + k1, p_76484_5_ + 1, Blocks.VINE, 4);
                                }
                            }
                        }
                    }

                    if (this.field_76531_b)
                    {
                        for (k1 = p_76484_4_ - 3 + l; k1 <= p_76484_4_ + l; ++k1)
                        {
                            i3 = k1 - (p_76484_4_ + l);
                            l1 = 2 - i3 / 2;

                            for (i2 = p_76484_3_ - l1; i2 <= p_76484_3_ + l1; ++i2)
                            {
                                for (j2 = p_76484_5_ - l1; j2 <= p_76484_5_ + l1; ++j2)
                                {
                                    if (p_76484_1_.func_147439_a(i2, k1, j2).func_149688_o() == Material.field_151584_j)
                                    {
                                        if (p_76484_2_.nextInt(4) == 0 && p_76484_1_.func_147439_a(i2 - 1, k1, j2).func_149688_o() == Material.field_151579_a)
                                        {
                                            this.func_76529_b(p_76484_1_, i2 - 1, k1, j2, 8);
                                        }

                                        if (p_76484_2_.nextInt(4) == 0 && p_76484_1_.func_147439_a(i2 + 1, k1, j2).func_149688_o() == Material.field_151579_a)
                                        {
                                            this.func_76529_b(p_76484_1_, i2 + 1, k1, j2, 2);
                                        }

                                        if (p_76484_2_.nextInt(4) == 0 && p_76484_1_.func_147439_a(i2, k1, j2 - 1).func_149688_o() == Material.field_151579_a)
                                        {
                                            this.func_76529_b(p_76484_1_, i2, k1, j2 - 1, 1);
                                        }

                                        if (p_76484_2_.nextInt(4) == 0 && p_76484_1_.func_147439_a(i2, k1, j2 + 1).func_149688_o() == Material.field_151579_a)
                                        {
                                            this.func_76529_b(p_76484_1_, i2, k1, j2 + 1, 4);
                                        }
                                    }
                                }
                            }
                        }

                        if (p_76484_2_.nextInt(5) == 0 && l > 5)
                        {
                            for (k1 = 0; k1 < 2; ++k1)
                            {
                                for (i3 = 0; i3 < 4; ++i3)
                                {
                                    if (p_76484_2_.nextInt(4 - k1) == 0)
                                    {
                                        l1 = p_76484_2_.nextInt(3);
                                        this.func_150516_a(p_76484_1_, p_76484_3_ + Direction.field_71583_a[Direction.field_71580_e[i3]], p_76484_4_ + l - 5 + k1, p_76484_5_ + Direction.field_71581_b[Direction.field_71580_e[i3]], Blocks.COCOA, l1 << 2 | i3);
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

    private void func_76529_b(World p_76529_1_, int p_76529_2_, int p_76529_3_, int p_76529_4_, int p_76529_5_)
    {
        this.func_150516_a(p_76529_1_, p_76529_2_, p_76529_3_, p_76529_4_, Blocks.VINE, p_76529_5_);
        int i1 = 4;

        while (true)
        {
            --p_76529_3_;

            if (p_76529_1_.func_147439_a(p_76529_2_, p_76529_3_, p_76529_4_).func_149688_o() != Material.field_151579_a || i1 <= 0)
            {
                return;
            }

            this.func_150516_a(p_76529_1_, p_76529_2_, p_76529_3_, p_76529_4_, Blocks.VINE, p_76529_5_);
            --i1;
        }
    }
}