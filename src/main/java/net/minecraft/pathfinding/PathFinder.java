package net.minecraft.pathfinding;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.IntHashMap;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;

public class PathFinder
{
    private IBlockAccess field_75868_a;
    private Path field_75866_b = new Path();
    private IntHashMap field_75867_c = new IntHashMap();
    private PathPoint[] field_75864_d = new PathPoint[32];
    private boolean field_75865_e;
    private boolean field_75862_f;
    private boolean field_75863_g;
    private boolean field_75869_h;
    private static final String __OBFID = "CL_00000576";

    public PathFinder(IBlockAccess p_i2137_1_, boolean p_i2137_2_, boolean p_i2137_3_, boolean p_i2137_4_, boolean p_i2137_5_)
    {
        this.field_75868_a = p_i2137_1_;
        this.field_75865_e = p_i2137_2_;
        this.field_75862_f = p_i2137_3_;
        this.field_75863_g = p_i2137_4_;
        this.field_75869_h = p_i2137_5_;
    }

    public PathEntity func_75856_a(Entity p_75856_1_, Entity p_75856_2_, float p_75856_3_)
    {
        return this.func_75857_a(p_75856_1_, p_75856_2_.field_70165_t, p_75856_2_.field_70121_D.field_72338_b, p_75856_2_.field_70161_v, p_75856_3_);
    }

    public PathEntity func_75859_a(Entity p_75859_1_, int p_75859_2_, int p_75859_3_, int p_75859_4_, float p_75859_5_)
    {
        return this.func_75857_a(p_75859_1_, (double)((float)p_75859_2_ + 0.5F), (double)((float)p_75859_3_ + 0.5F), (double)((float)p_75859_4_ + 0.5F), p_75859_5_);
    }

    private PathEntity func_75857_a(Entity p_75857_1_, double p_75857_2_, double p_75857_4_, double p_75857_6_, float p_75857_8_)
    {
        this.field_75866_b.func_75848_a();
        this.field_75867_c.func_76046_c();
        boolean flag = this.field_75863_g;
        int i = MathHelper.func_76128_c(p_75857_1_.field_70121_D.field_72338_b + 0.5D);

        if (this.field_75869_h && p_75857_1_.func_70090_H())
        {
            i = (int)p_75857_1_.field_70121_D.field_72338_b;

            for (Block block = this.field_75868_a.func_147439_a(MathHelper.func_76128_c(p_75857_1_.field_70165_t), i, MathHelper.func_76128_c(p_75857_1_.field_70161_v)); block == Blocks.field_150358_i || block == Blocks.field_150355_j; block = this.field_75868_a.func_147439_a(MathHelper.func_76128_c(p_75857_1_.field_70165_t), i, MathHelper.func_76128_c(p_75857_1_.field_70161_v)))
            {
                ++i;
            }

            flag = this.field_75863_g;
            this.field_75863_g = false;
        }
        else
        {
            i = MathHelper.func_76128_c(p_75857_1_.field_70121_D.field_72338_b + 0.5D);
        }

        PathPoint pathpoint2 = this.func_75854_a(MathHelper.func_76128_c(p_75857_1_.field_70121_D.field_72340_a), i, MathHelper.func_76128_c(p_75857_1_.field_70121_D.field_72339_c));
        PathPoint pathpoint = this.func_75854_a(MathHelper.func_76128_c(p_75857_2_ - (double)(p_75857_1_.field_70130_N / 2.0F)), MathHelper.func_76128_c(p_75857_4_), MathHelper.func_76128_c(p_75857_6_ - (double)(p_75857_1_.field_70130_N / 2.0F)));
        PathPoint pathpoint1 = new PathPoint(MathHelper.func_76141_d(p_75857_1_.field_70130_N + 1.0F), MathHelper.func_76141_d(p_75857_1_.field_70131_O + 1.0F), MathHelper.func_76141_d(p_75857_1_.field_70130_N + 1.0F));
        PathEntity pathentity = this.func_75861_a(p_75857_1_, pathpoint2, pathpoint, pathpoint1, p_75857_8_);
        this.field_75863_g = flag;
        return pathentity;
    }

    private PathEntity func_75861_a(Entity p_75861_1_, PathPoint p_75861_2_, PathPoint p_75861_3_, PathPoint p_75861_4_, float p_75861_5_)
    {
        p_75861_2_.field_75836_e = 0.0F;
        p_75861_2_.field_75833_f = p_75861_2_.func_75832_b(p_75861_3_);
        p_75861_2_.field_75834_g = p_75861_2_.field_75833_f;
        this.field_75866_b.func_75848_a();
        this.field_75866_b.func_75849_a(p_75861_2_);
        PathPoint pathpoint3 = p_75861_2_;

        while (!this.field_75866_b.func_75845_e())
        {
            PathPoint pathpoint4 = this.field_75866_b.func_75844_c();

            if (pathpoint4.equals(p_75861_3_))
            {
                return this.func_75853_a(p_75861_2_, p_75861_3_);
            }

            if (pathpoint4.func_75832_b(p_75861_3_) < pathpoint3.func_75832_b(p_75861_3_))
            {
                pathpoint3 = pathpoint4;
            }

            pathpoint4.field_75842_i = true;
            int i = this.func_75860_b(p_75861_1_, pathpoint4, p_75861_4_, p_75861_3_, p_75861_5_);

            for (int j = 0; j < i; ++j)
            {
                PathPoint pathpoint5 = this.field_75864_d[j];
                float f1 = pathpoint4.field_75836_e + pathpoint4.func_75832_b(pathpoint5);

                if (!pathpoint5.func_75831_a() || f1 < pathpoint5.field_75836_e)
                {
                    pathpoint5.field_75841_h = pathpoint4;
                    pathpoint5.field_75836_e = f1;
                    pathpoint5.field_75833_f = pathpoint5.func_75832_b(p_75861_3_);

                    if (pathpoint5.func_75831_a())
                    {
                        this.field_75866_b.func_75850_a(pathpoint5, pathpoint5.field_75836_e + pathpoint5.field_75833_f);
                    }
                    else
                    {
                        pathpoint5.field_75834_g = pathpoint5.field_75836_e + pathpoint5.field_75833_f;
                        this.field_75866_b.func_75849_a(pathpoint5);
                    }
                }
            }
        }

        if (pathpoint3 == p_75861_2_)
        {
            return null;
        }
        else
        {
            return this.func_75853_a(p_75861_2_, pathpoint3);
        }
    }

    private int func_75860_b(Entity p_75860_1_, PathPoint p_75860_2_, PathPoint p_75860_3_, PathPoint p_75860_4_, float p_75860_5_)
    {
        int i = 0;
        byte b0 = 0;

        if (this.func_75855_a(p_75860_1_, p_75860_2_.field_75839_a, p_75860_2_.field_75837_b + 1, p_75860_2_.field_75838_c, p_75860_3_) == 1)
        {
            b0 = 1;
        }

        PathPoint pathpoint3 = this.func_75858_a(p_75860_1_, p_75860_2_.field_75839_a, p_75860_2_.field_75837_b, p_75860_2_.field_75838_c + 1, p_75860_3_, b0);
        PathPoint pathpoint4 = this.func_75858_a(p_75860_1_, p_75860_2_.field_75839_a - 1, p_75860_2_.field_75837_b, p_75860_2_.field_75838_c, p_75860_3_, b0);
        PathPoint pathpoint5 = this.func_75858_a(p_75860_1_, p_75860_2_.field_75839_a + 1, p_75860_2_.field_75837_b, p_75860_2_.field_75838_c, p_75860_3_, b0);
        PathPoint pathpoint6 = this.func_75858_a(p_75860_1_, p_75860_2_.field_75839_a, p_75860_2_.field_75837_b, p_75860_2_.field_75838_c - 1, p_75860_3_, b0);

        if (pathpoint3 != null && !pathpoint3.field_75842_i && pathpoint3.func_75829_a(p_75860_4_) < p_75860_5_)
        {
            this.field_75864_d[i++] = pathpoint3;
        }

        if (pathpoint4 != null && !pathpoint4.field_75842_i && pathpoint4.func_75829_a(p_75860_4_) < p_75860_5_)
        {
            this.field_75864_d[i++] = pathpoint4;
        }

        if (pathpoint5 != null && !pathpoint5.field_75842_i && pathpoint5.func_75829_a(p_75860_4_) < p_75860_5_)
        {
            this.field_75864_d[i++] = pathpoint5;
        }

        if (pathpoint6 != null && !pathpoint6.field_75842_i && pathpoint6.func_75829_a(p_75860_4_) < p_75860_5_)
        {
            this.field_75864_d[i++] = pathpoint6;
        }

        return i;
    }

    private PathPoint func_75858_a(Entity p_75858_1_, int p_75858_2_, int p_75858_3_, int p_75858_4_, PathPoint p_75858_5_, int p_75858_6_)
    {
        PathPoint pathpoint1 = null;
        int i1 = this.func_75855_a(p_75858_1_, p_75858_2_, p_75858_3_, p_75858_4_, p_75858_5_);

        if (i1 == 2)
        {
            return this.func_75854_a(p_75858_2_, p_75858_3_, p_75858_4_);
        }
        else
        {
            if (i1 == 1)
            {
                pathpoint1 = this.func_75854_a(p_75858_2_, p_75858_3_, p_75858_4_);
            }

            if (pathpoint1 == null && p_75858_6_ > 0 && i1 != -3 && i1 != -4 && this.func_75855_a(p_75858_1_, p_75858_2_, p_75858_3_ + p_75858_6_, p_75858_4_, p_75858_5_) == 1)
            {
                pathpoint1 = this.func_75854_a(p_75858_2_, p_75858_3_ + p_75858_6_, p_75858_4_);
                p_75858_3_ += p_75858_6_;
            }

            if (pathpoint1 != null)
            {
                int j1 = 0;
                int k1 = 0;

                while (p_75858_3_ > 0)
                {
                    k1 = this.func_75855_a(p_75858_1_, p_75858_2_, p_75858_3_ - 1, p_75858_4_, p_75858_5_);

                    if (this.field_75863_g && k1 == -1)
                    {
                        return null;
                    }

                    if (k1 != 1)
                    {
                        break;
                    }

                    if (j1++ >= p_75858_1_.func_82143_as())
                    {
                        return null;
                    }

                    --p_75858_3_;

                    if (p_75858_3_ > 0)
                    {
                        pathpoint1 = this.func_75854_a(p_75858_2_, p_75858_3_, p_75858_4_);
                    }
                }

                if (k1 == -2)
                {
                    return null;
                }
            }

            return pathpoint1;
        }
    }

    private final PathPoint func_75854_a(int p_75854_1_, int p_75854_2_, int p_75854_3_)
    {
        int l = PathPoint.func_75830_a(p_75854_1_, p_75854_2_, p_75854_3_);
        PathPoint pathpoint = (PathPoint)this.field_75867_c.func_76041_a(l);

        if (pathpoint == null)
        {
            pathpoint = new PathPoint(p_75854_1_, p_75854_2_, p_75854_3_);
            this.field_75867_c.func_76038_a(l, pathpoint);
        }

        return pathpoint;
    }

    public int func_75855_a(Entity p_75855_1_, int p_75855_2_, int p_75855_3_, int p_75855_4_, PathPoint p_75855_5_)
    {
        return func_82565_a(p_75855_1_, p_75855_2_, p_75855_3_, p_75855_4_, p_75855_5_, this.field_75863_g, this.field_75862_f, this.field_75865_e);
    }

    public static int func_82565_a(Entity p_82565_0_, int p_82565_1_, int p_82565_2_, int p_82565_3_, PathPoint p_82565_4_, boolean p_82565_5_, boolean p_82565_6_, boolean p_82565_7_)
    {
        boolean flag3 = false;

        for (int l = p_82565_1_; l < p_82565_1_ + p_82565_4_.field_75839_a; ++l)
        {
            for (int i1 = p_82565_2_; i1 < p_82565_2_ + p_82565_4_.field_75837_b; ++i1)
            {
                for (int j1 = p_82565_3_; j1 < p_82565_3_ + p_82565_4_.field_75838_c; ++j1)
                {
                    Block block = p_82565_0_.field_70170_p.func_147439_a(l, i1, j1);

                    if (block.func_149688_o() != Material.field_151579_a)
                    {
                        if (block == Blocks.field_150415_aT)
                        {
                            flag3 = true;
                        }
                        else if (block != Blocks.field_150358_i && block != Blocks.field_150355_j)
                        {
                            if (!p_82565_7_ && block == Blocks.field_150466_ao)
                            {
                                return 0;
                            }
                        }
                        else
                        {
                            if (p_82565_5_)
                            {
                                return -1;
                            }

                            flag3 = true;
                        }

                        int k1 = block.func_149645_b();

                        if (p_82565_0_.field_70170_p.func_147439_a(l, i1, j1).func_149645_b() == 9)
                        {
                            int j2 = MathHelper.func_76128_c(p_82565_0_.field_70165_t);
                            int l1 = MathHelper.func_76128_c(p_82565_0_.field_70163_u);
                            int i2 = MathHelper.func_76128_c(p_82565_0_.field_70161_v);

                            if (p_82565_0_.field_70170_p.func_147439_a(j2, l1, i2).func_149645_b() != 9 && p_82565_0_.field_70170_p.func_147439_a(j2, l1 - 1, i2).func_149645_b() != 9)
                            {
                                return -3;
                            }
                        }
                        else if (!block.func_149655_b(p_82565_0_.field_70170_p, l, i1, j1) && (!p_82565_6_ || block != Blocks.field_150466_ao))
                        {
                            if (k1 == 11 || block == Blocks.field_150396_be || k1 == 32)
                            {
                                return -3;
                            }

                            if (block == Blocks.field_150415_aT)
                            {
                                return -4;
                            }

                            Material material = block.func_149688_o();

                            if (material != Material.field_151587_i)
                            {
                                return 0;
                            }

                            if (!p_82565_0_.func_70058_J())
                            {
                                return -2;
                            }
                        }
                    }
                }
            }
        }

        return flag3 ? 2 : 1;
    }

    private PathEntity func_75853_a(PathPoint p_75853_1_, PathPoint p_75853_2_)
    {
        int i = 1;
        PathPoint pathpoint2;

        for (pathpoint2 = p_75853_2_; pathpoint2.field_75841_h != null; pathpoint2 = pathpoint2.field_75841_h)
        {
            ++i;
        }

        PathPoint[] apathpoint = new PathPoint[i];
        pathpoint2 = p_75853_2_;
        --i;

        for (apathpoint[i] = p_75853_2_; pathpoint2.field_75841_h != null; apathpoint[i] = pathpoint2)
        {
            pathpoint2 = pathpoint2.field_75841_h;
            --i;
        }

        return new PathEntity(apathpoint);
    }
}