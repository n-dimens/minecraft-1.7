package net.minecraft.world.gen;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class MapGenCaves extends MapGenBase
{
    private static final String __OBFID = "CL_00000393";

    protected void func_151542_a(long p_151542_1_, int p_151542_3_, int p_151542_4_, Block[] p_151542_5_, double p_151542_6_, double p_151542_8_, double p_151542_10_)
    {
        this.func_151541_a(p_151542_1_, p_151542_3_, p_151542_4_, p_151542_5_, p_151542_6_, p_151542_8_, p_151542_10_, 1.0F + this.field_75038_b.nextFloat() * 6.0F, 0.0F, 0.0F, -1, -1, 0.5D);
    }

    protected void func_151541_a(long p_151541_1_, int p_151541_3_, int p_151541_4_, Block[] p_151541_5_, double p_151541_6_, double p_151541_8_, double p_151541_10_, float p_151541_12_, float p_151541_13_, float p_151541_14_, int p_151541_15_, int p_151541_16_, double p_151541_17_)
    {
        double d4 = (double)(p_151541_3_ * 16 + 8);
        double d5 = (double)(p_151541_4_ * 16 + 8);
        float f3 = 0.0F;
        float f4 = 0.0F;
        Random random = new Random(p_151541_1_);

        if (p_151541_16_ <= 0)
        {
            int j1 = this.field_75040_a * 16 - 16;
            p_151541_16_ = j1 - random.nextInt(j1 / 4);
        }

        boolean flag2 = false;

        if (p_151541_15_ == -1)
        {
            p_151541_15_ = p_151541_16_ / 2;
            flag2 = true;
        }

        int k1 = random.nextInt(p_151541_16_ / 2) + p_151541_16_ / 4;

        for (boolean flag = random.nextInt(6) == 0; p_151541_15_ < p_151541_16_; ++p_151541_15_)
        {
            double d6 = 1.5D + (double)(MathHelper.func_76126_a((float)p_151541_15_ * (float)Math.PI / (float)p_151541_16_) * p_151541_12_ * 1.0F);
            double d7 = d6 * p_151541_17_;
            float f5 = MathHelper.func_76134_b(p_151541_14_);
            float f6 = MathHelper.func_76126_a(p_151541_14_);
            p_151541_6_ += (double)(MathHelper.func_76134_b(p_151541_13_) * f5);
            p_151541_8_ += (double)f6;
            p_151541_10_ += (double)(MathHelper.func_76126_a(p_151541_13_) * f5);

            if (flag)
            {
                p_151541_14_ *= 0.92F;
            }
            else
            {
                p_151541_14_ *= 0.7F;
            }

            p_151541_14_ += f4 * 0.1F;
            p_151541_13_ += f3 * 0.1F;
            f4 *= 0.9F;
            f3 *= 0.75F;
            f4 += (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 2.0F;
            f3 += (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 4.0F;

            if (!flag2 && p_151541_15_ == k1 && p_151541_12_ > 1.0F && p_151541_16_ > 0)
            {
                this.func_151541_a(random.nextLong(), p_151541_3_, p_151541_4_, p_151541_5_, p_151541_6_, p_151541_8_, p_151541_10_, random.nextFloat() * 0.5F + 0.5F, p_151541_13_ - ((float)Math.PI / 2F), p_151541_14_ / 3.0F, p_151541_15_, p_151541_16_, 1.0D);
                this.func_151541_a(random.nextLong(), p_151541_3_, p_151541_4_, p_151541_5_, p_151541_6_, p_151541_8_, p_151541_10_, random.nextFloat() * 0.5F + 0.5F, p_151541_13_ + ((float)Math.PI / 2F), p_151541_14_ / 3.0F, p_151541_15_, p_151541_16_, 1.0D);
                return;
            }

            if (flag2 || random.nextInt(4) != 0)
            {
                double d8 = p_151541_6_ - d4;
                double d9 = p_151541_10_ - d5;
                double d10 = (double)(p_151541_16_ - p_151541_15_);
                double d11 = (double)(p_151541_12_ + 2.0F + 16.0F);

                if (d8 * d8 + d9 * d9 - d10 * d10 > d11 * d11)
                {
                    return;
                }

                if (p_151541_6_ >= d4 - 16.0D - d6 * 2.0D && p_151541_10_ >= d5 - 16.0D - d6 * 2.0D && p_151541_6_ <= d4 + 16.0D + d6 * 2.0D && p_151541_10_ <= d5 + 16.0D + d6 * 2.0D)
                {
                    int i4 = MathHelper.func_76128_c(p_151541_6_ - d6) - p_151541_3_ * 16 - 1;
                    int l1 = MathHelper.func_76128_c(p_151541_6_ + d6) - p_151541_3_ * 16 + 1;
                    int j4 = MathHelper.func_76128_c(p_151541_8_ - d7) - 1;
                    int i2 = MathHelper.func_76128_c(p_151541_8_ + d7) + 1;
                    int k4 = MathHelper.func_76128_c(p_151541_10_ - d6) - p_151541_4_ * 16 - 1;
                    int j2 = MathHelper.func_76128_c(p_151541_10_ + d6) - p_151541_4_ * 16 + 1;

                    if (i4 < 0)
                    {
                        i4 = 0;
                    }

                    if (l1 > 16)
                    {
                        l1 = 16;
                    }

                    if (j4 < 1)
                    {
                        j4 = 1;
                    }

                    if (i2 > 248)
                    {
                        i2 = 248;
                    }

                    if (k4 < 0)
                    {
                        k4 = 0;
                    }

                    if (j2 > 16)
                    {
                        j2 = 16;
                    }

                    boolean flag3 = false;
                    int k2;
                    int j3;

                    for (k2 = i4; !flag3 && k2 < l1; ++k2)
                    {
                        for (int l2 = k4; !flag3 && l2 < j2; ++l2)
                        {
                            for (int i3 = i2 + 1; !flag3 && i3 >= j4 - 1; --i3)
                            {
                                j3 = (k2 * 16 + l2) * 256 + i3;

                                if (i3 >= 0 && i3 < 256)
                                {
                                    Block block = p_151541_5_[j3];

                                    if (block == Blocks.field_150358_i || block == Blocks.field_150355_j)
                                    {
                                        flag3 = true;
                                    }

                                    if (i3 != j4 - 1 && k2 != i4 && k2 != l1 - 1 && l2 != k4 && l2 != j2 - 1)
                                    {
                                        i3 = j4;
                                    }
                                }
                            }
                        }
                    }

                    if (!flag3)
                    {
                        for (k2 = i4; k2 < l1; ++k2)
                        {
                            double d13 = ((double)(k2 + p_151541_3_ * 16) + 0.5D - p_151541_6_) / d6;

                            for (j3 = k4; j3 < j2; ++j3)
                            {
                                double d14 = ((double)(j3 + p_151541_4_ * 16) + 0.5D - p_151541_10_) / d6;
                                int k3 = (k2 * 16 + j3) * 256 + i2;
                                boolean flag1 = false;

                                if (d13 * d13 + d14 * d14 < 1.0D)
                                {
                                    for (int l3 = i2 - 1; l3 >= j4; --l3)
                                    {
                                        double d12 = ((double)l3 + 0.5D - p_151541_8_) / d7;

                                        if (d12 > -0.7D && d13 * d13 + d12 * d12 + d14 * d14 < 1.0D)
                                        {
                                            Block block1 = p_151541_5_[k3];

                                            if (block1 == Blocks.field_150349_c)
                                            {
                                                flag1 = true;
                                            }

                                            if (block1 == Blocks.field_150348_b || block1 == Blocks.field_150346_d || block1 == Blocks.field_150349_c)
                                            {
                                                if (l3 < 10)
                                                {
                                                    p_151541_5_[k3] = Blocks.field_150353_l;
                                                }
                                                else
                                                {
                                                    p_151541_5_[k3] = null;

                                                    if (flag1 && p_151541_5_[k3 - 1] == Blocks.field_150346_d)
                                                    {
                                                        p_151541_5_[k3 - 1] = this.field_75039_c.func_72807_a(k2 + p_151541_3_ * 16, j3 + p_151541_4_ * 16).field_76752_A;
                                                    }
                                                }
                                            }
                                        }

                                        --k3;
                                    }
                                }
                            }
                        }

                        if (flag2)
                        {
                            break;
                        }
                    }
                }
            }
        }
    }

    protected void func_151538_a(World p_151538_1_, int p_151538_2_, int p_151538_3_, int p_151538_4_, int p_151538_5_, Block[] p_151538_6_)
    {
        int i1 = this.field_75038_b.nextInt(this.field_75038_b.nextInt(this.field_75038_b.nextInt(15) + 1) + 1);

        if (this.field_75038_b.nextInt(7) != 0)
        {
            i1 = 0;
        }

        for (int j1 = 0; j1 < i1; ++j1)
        {
            double d0 = (double)(p_151538_2_ * 16 + this.field_75038_b.nextInt(16));
            double d1 = (double)this.field_75038_b.nextInt(this.field_75038_b.nextInt(120) + 8);
            double d2 = (double)(p_151538_3_ * 16 + this.field_75038_b.nextInt(16));
            int k1 = 1;

            if (this.field_75038_b.nextInt(4) == 0)
            {
                this.func_151542_a(this.field_75038_b.nextLong(), p_151538_4_, p_151538_5_, p_151538_6_, d0, d1, d2);
                k1 += this.field_75038_b.nextInt(4);
            }

            for (int l1 = 0; l1 < k1; ++l1)
            {
                float f = this.field_75038_b.nextFloat() * (float)Math.PI * 2.0F;
                float f1 = (this.field_75038_b.nextFloat() - 0.5F) * 2.0F / 8.0F;
                float f2 = this.field_75038_b.nextFloat() * 2.0F + this.field_75038_b.nextFloat();

                if (this.field_75038_b.nextInt(10) == 0)
                {
                    f2 *= this.field_75038_b.nextFloat() * this.field_75038_b.nextFloat() * 3.0F + 1.0F;
                }

                this.func_151541_a(this.field_75038_b.nextLong(), p_151538_4_, p_151538_5_, p_151538_6_, d0, d1, d2, f2, f, f1, 0, 0, 1.0D);
            }
        }
    }
}