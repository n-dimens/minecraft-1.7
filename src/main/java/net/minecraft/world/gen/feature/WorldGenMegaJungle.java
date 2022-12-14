package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class WorldGenMegaJungle extends WorldGenHugeTrees
{
    private static final String __OBFID = "CL_00000420";

    public WorldGenMegaJungle(boolean p_i45456_1_, int p_i45456_2_, int p_i45456_3_, int p_i45456_4_, int p_i45456_5_)
    {
        super(p_i45456_1_, p_i45456_2_, p_i45456_3_, p_i45456_4_, p_i45456_5_);
    }

    public boolean func_76484_a(World p_76484_1_, Random p_76484_2_, int p_76484_3_, int p_76484_4_, int p_76484_5_)
    {
        int l = this.func_150533_a(p_76484_2_);

        if (!this.func_150537_a(p_76484_1_, p_76484_2_, p_76484_3_, p_76484_4_, p_76484_5_, l))
        {
            return false;
        }
        else
        {
            this.func_150543_c(p_76484_1_, p_76484_3_, p_76484_5_, p_76484_4_ + l, 2, p_76484_2_);

            for (int i1 = p_76484_4_ + l - 2 - p_76484_2_.nextInt(4); i1 > p_76484_4_ + l / 2; i1 -= 2 + p_76484_2_.nextInt(4))
            {
                float f = p_76484_2_.nextFloat() * (float)Math.PI * 2.0F;
                int j1 = p_76484_3_ + (int)(0.5F + MathHelper.func_76134_b(f) * 4.0F);
                int k1 = p_76484_5_ + (int)(0.5F + MathHelper.func_76126_a(f) * 4.0F);
                int l1;

                for (l1 = 0; l1 < 5; ++l1)
                {
                    j1 = p_76484_3_ + (int)(1.5F + MathHelper.func_76134_b(f) * (float)l1);
                    k1 = p_76484_5_ + (int)(1.5F + MathHelper.func_76126_a(f) * (float)l1);
                    this.func_150516_a(p_76484_1_, j1, i1 - 3 + l1 / 2, k1, Blocks.LOG, this.field_76520_b);
                }

                l1 = 1 + p_76484_2_.nextInt(2);
                int i2 = i1;

                for (int j2 = i1 - l1; j2 <= i2; ++j2)
                {
                    int k2 = j2 - i2;
                    this.func_150534_b(p_76484_1_, j1, j2, k1, 1 - k2, p_76484_2_);
                }
            }

            for (int l2 = 0; l2 < l; ++l2)
            {
                Block block = p_76484_1_.func_147439_a(p_76484_3_, p_76484_4_ + l2, p_76484_5_);

                if (block.func_149688_o() == Material.field_151579_a || block.func_149688_o() == Material.field_151584_j)
                {
                    this.func_150516_a(p_76484_1_, p_76484_3_, p_76484_4_ + l2, p_76484_5_, Blocks.LOG, this.field_76520_b);

                    if (l2 > 0)
                    {
                        if (p_76484_2_.nextInt(3) > 0 && p_76484_1_.func_147437_c(p_76484_3_ - 1, p_76484_4_ + l2, p_76484_5_))
                        {
                            this.func_150516_a(p_76484_1_, p_76484_3_ - 1, p_76484_4_ + l2, p_76484_5_, Blocks.VINE, 8);
                        }

                        if (p_76484_2_.nextInt(3) > 0 && p_76484_1_.func_147437_c(p_76484_3_, p_76484_4_ + l2, p_76484_5_ - 1))
                        {
                            this.func_150516_a(p_76484_1_, p_76484_3_, p_76484_4_ + l2, p_76484_5_ - 1, Blocks.VINE, 1);
                        }
                    }
                }

                if (l2 < l - 1)
                {
                    block = p_76484_1_.func_147439_a(p_76484_3_ + 1, p_76484_4_ + l2, p_76484_5_);

                    if (block.func_149688_o() == Material.field_151579_a || block.func_149688_o() == Material.field_151584_j)
                    {
                        this.func_150516_a(p_76484_1_, p_76484_3_ + 1, p_76484_4_ + l2, p_76484_5_, Blocks.LOG, this.field_76520_b);

                        if (l2 > 0)
                        {
                            if (p_76484_2_.nextInt(3) > 0 && p_76484_1_.func_147437_c(p_76484_3_ + 2, p_76484_4_ + l2, p_76484_5_))
                            {
                                this.func_150516_a(p_76484_1_, p_76484_3_ + 2, p_76484_4_ + l2, p_76484_5_, Blocks.VINE, 2);
                            }

                            if (p_76484_2_.nextInt(3) > 0 && p_76484_1_.func_147437_c(p_76484_3_ + 1, p_76484_4_ + l2, p_76484_5_ - 1))
                            {
                                this.func_150516_a(p_76484_1_, p_76484_3_ + 1, p_76484_4_ + l2, p_76484_5_ - 1, Blocks.VINE, 1);
                            }
                        }
                    }

                    block = p_76484_1_.func_147439_a(p_76484_3_ + 1, p_76484_4_ + l2, p_76484_5_ + 1);

                    if (block.func_149688_o() == Material.field_151579_a || block.func_149688_o() == Material.field_151584_j)
                    {
                        this.func_150516_a(p_76484_1_, p_76484_3_ + 1, p_76484_4_ + l2, p_76484_5_ + 1, Blocks.LOG, this.field_76520_b);

                        if (l2 > 0)
                        {
                            if (p_76484_2_.nextInt(3) > 0 && p_76484_1_.func_147437_c(p_76484_3_ + 2, p_76484_4_ + l2, p_76484_5_ + 1))
                            {
                                this.func_150516_a(p_76484_1_, p_76484_3_ + 2, p_76484_4_ + l2, p_76484_5_ + 1, Blocks.VINE, 2);
                            }

                            if (p_76484_2_.nextInt(3) > 0 && p_76484_1_.func_147437_c(p_76484_3_ + 1, p_76484_4_ + l2, p_76484_5_ + 2))
                            {
                                this.func_150516_a(p_76484_1_, p_76484_3_ + 1, p_76484_4_ + l2, p_76484_5_ + 2, Blocks.VINE, 4);
                            }
                        }
                    }

                    block = p_76484_1_.func_147439_a(p_76484_3_, p_76484_4_ + l2, p_76484_5_ + 1);

                    if (block.func_149688_o() == Material.field_151579_a || block.func_149688_o() == Material.field_151584_j)
                    {
                        this.func_150516_a(p_76484_1_, p_76484_3_, p_76484_4_ + l2, p_76484_5_ + 1, Blocks.LOG, this.field_76520_b);

                        if (l2 > 0)
                        {
                            if (p_76484_2_.nextInt(3) > 0 && p_76484_1_.func_147437_c(p_76484_3_ - 1, p_76484_4_ + l2, p_76484_5_ + 1))
                            {
                                this.func_150516_a(p_76484_1_, p_76484_3_ - 1, p_76484_4_ + l2, p_76484_5_ + 1, Blocks.VINE, 8);
                            }

                            if (p_76484_2_.nextInt(3) > 0 && p_76484_1_.func_147437_c(p_76484_3_, p_76484_4_ + l2, p_76484_5_ + 2))
                            {
                                this.func_150516_a(p_76484_1_, p_76484_3_, p_76484_4_ + l2, p_76484_5_ + 2, Blocks.VINE, 4);
                            }
                        }
                    }
                }
            }

            return true;
        }
    }

    private void func_150543_c(World p_150543_1_, int p_150543_2_, int p_150543_3_, int p_150543_4_, int p_150543_5_, Random p_150543_6_)
    {
        byte b0 = 2;

        for (int i1 = p_150543_4_ - b0; i1 <= p_150543_4_; ++i1)
        {
            int j1 = i1 - p_150543_4_;
            this.func_150535_a(p_150543_1_, p_150543_2_, i1, p_150543_3_, p_150543_5_ + 1 - j1, p_150543_6_);
        }
    }
}