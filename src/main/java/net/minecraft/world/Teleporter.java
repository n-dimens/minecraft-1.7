package net.minecraft.world;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.Direction;
import net.minecraft.util.LongHashMap;
import net.minecraft.util.MathHelper;

public class Teleporter
{
    private final WorldServer field_85192_a;
    private final Random field_77187_a;
    private final LongHashMap field_85191_c = new LongHashMap();
    private final List field_85190_d = new ArrayList();
    private static final String __OBFID = "CL_00000153";

    public Teleporter(WorldServer p_i1963_1_)
    {
        this.field_85192_a = p_i1963_1_;
        this.field_77187_a = new Random(p_i1963_1_.func_72905_C());
    }

    public void func_77185_a(Entity p_77185_1_, double p_77185_2_, double p_77185_4_, double p_77185_6_, float p_77185_8_)
    {
        if (this.field_85192_a.field_73011_w.field_76574_g != 1)
        {
            if (!this.func_77184_b(p_77185_1_, p_77185_2_, p_77185_4_, p_77185_6_, p_77185_8_))
            {
                this.func_85188_a(p_77185_1_);
                this.func_77184_b(p_77185_1_, p_77185_2_, p_77185_4_, p_77185_6_, p_77185_8_);
            }
        }
        else
        {
            int i = MathHelper.func_76128_c(p_77185_1_.field_70165_t);
            int j = MathHelper.func_76128_c(p_77185_1_.field_70163_u) - 1;
            int k = MathHelper.func_76128_c(p_77185_1_.field_70161_v);
            byte b0 = 1;
            byte b1 = 0;

            for (int l = -2; l <= 2; ++l)
            {
                for (int i1 = -2; i1 <= 2; ++i1)
                {
                    for (int j1 = -1; j1 < 3; ++j1)
                    {
                        int k1 = i + i1 * b0 + l * b1;
                        int l1 = j + j1;
                        int i2 = k + i1 * b1 - l * b0;
                        boolean flag = j1 < 0;
                        this.field_85192_a.func_147449_b(k1, l1, i2, flag ? Blocks.OBSIDIAN : Blocks.AIR);
                    }
                }
            }

            p_77185_1_.func_70012_b((double)i, (double)j, (double)k, p_77185_1_.field_70177_z, 0.0F);
            p_77185_1_.field_70159_w = p_77185_1_.field_70181_x = p_77185_1_.field_70179_y = 0.0D;
        }
    }

    public boolean func_77184_b(Entity p_77184_1_, double p_77184_2_, double p_77184_4_, double p_77184_6_, float p_77184_8_)
    {
        short short1 = 128;
        double d3 = -1.0D;
        int i = 0;
        int j = 0;
        int k = 0;
        int l = MathHelper.func_76128_c(p_77184_1_.field_70165_t);
        int i1 = MathHelper.func_76128_c(p_77184_1_.field_70161_v);
        long j1 = ChunkCoordIntPair.func_77272_a(l, i1);
        boolean flag = true;
        double d7;
        int l3;

        if (this.field_85191_c.func_76161_b(j1))
        {
            Teleporter.PortalPosition portalposition = (Teleporter.PortalPosition)this.field_85191_c.func_76164_a(j1);
            d3 = 0.0D;
            i = portalposition.field_71574_a;
            j = portalposition.field_71572_b;
            k = portalposition.field_71573_c;
            portalposition.field_85087_d = this.field_85192_a.func_82737_E();
            flag = false;
        }
        else
        {
            for (l3 = l - short1; l3 <= l + short1; ++l3)
            {
                double d4 = (double)l3 + 0.5D - p_77184_1_.field_70165_t;

                for (int l1 = i1 - short1; l1 <= i1 + short1; ++l1)
                {
                    double d5 = (double)l1 + 0.5D - p_77184_1_.field_70161_v;

                    for (int i2 = this.field_85192_a.func_72940_L() - 1; i2 >= 0; --i2)
                    {
                        if (this.field_85192_a.func_147439_a(l3, i2, l1) == Blocks.PORTAL)
                        {
                            while (this.field_85192_a.func_147439_a(l3, i2 - 1, l1) == Blocks.PORTAL)
                            {
                                --i2;
                            }

                            d7 = (double)i2 + 0.5D - p_77184_1_.field_70163_u;
                            double d8 = d4 * d4 + d7 * d7 + d5 * d5;

                            if (d3 < 0.0D || d8 < d3)
                            {
                                d3 = d8;
                                i = l3;
                                j = i2;
                                k = l1;
                            }
                        }
                    }
                }
            }
        }

        if (d3 >= 0.0D)
        {
            if (flag)
            {
                this.field_85191_c.func_76163_a(j1, new Teleporter.PortalPosition(i, j, k, this.field_85192_a.func_82737_E()));
                this.field_85190_d.add(Long.valueOf(j1));
            }

            double d11 = (double)i + 0.5D;
            double d6 = (double)j + 0.5D;
            d7 = (double)k + 0.5D;
            int i4 = -1;

            if (this.field_85192_a.func_147439_a(i - 1, j, k) == Blocks.PORTAL)
            {
                i4 = 2;
            }

            if (this.field_85192_a.func_147439_a(i + 1, j, k) == Blocks.PORTAL)
            {
                i4 = 0;
            }

            if (this.field_85192_a.func_147439_a(i, j, k - 1) == Blocks.PORTAL)
            {
                i4 = 3;
            }

            if (this.field_85192_a.func_147439_a(i, j, k + 1) == Blocks.PORTAL)
            {
                i4 = 1;
            }

            int j2 = p_77184_1_.func_82148_at();

            if (i4 > -1)
            {
                int k2 = Direction.field_71578_g[i4];
                int l2 = Direction.field_71583_a[i4];
                int i3 = Direction.field_71581_b[i4];
                int j3 = Direction.field_71583_a[k2];
                int k3 = Direction.field_71581_b[k2];
                boolean flag1 = !this.field_85192_a.func_147437_c(i + l2 + j3, j, k + i3 + k3) || !this.field_85192_a.func_147437_c(i + l2 + j3, j + 1, k + i3 + k3);
                boolean flag2 = !this.field_85192_a.func_147437_c(i + l2, j, k + i3) || !this.field_85192_a.func_147437_c(i + l2, j + 1, k + i3);

                if (flag1 && flag2)
                {
                    i4 = Direction.field_71580_e[i4];
                    k2 = Direction.field_71580_e[k2];
                    l2 = Direction.field_71583_a[i4];
                    i3 = Direction.field_71581_b[i4];
                    j3 = Direction.field_71583_a[k2];
                    k3 = Direction.field_71581_b[k2];
                    l3 = i - j3;
                    d11 -= (double)j3;
                    int k1 = k - k3;
                    d7 -= (double)k3;
                    flag1 = !this.field_85192_a.func_147437_c(l3 + l2 + j3, j, k1 + i3 + k3) || !this.field_85192_a.func_147437_c(l3 + l2 + j3, j + 1, k1 + i3 + k3);
                    flag2 = !this.field_85192_a.func_147437_c(l3 + l2, j, k1 + i3) || !this.field_85192_a.func_147437_c(l3 + l2, j + 1, k1 + i3);
                }

                float f1 = 0.5F;
                float f2 = 0.5F;

                if (!flag1 && flag2)
                {
                    f1 = 1.0F;
                }
                else if (flag1 && !flag2)
                {
                    f1 = 0.0F;
                }
                else if (flag1 && flag2)
                {
                    f2 = 0.0F;
                }

                d11 += (double)((float)j3 * f1 + f2 * (float)l2);
                d7 += (double)((float)k3 * f1 + f2 * (float)i3);
                float f3 = 0.0F;
                float f4 = 0.0F;
                float f5 = 0.0F;
                float f6 = 0.0F;

                if (i4 == j2)
                {
                    f3 = 1.0F;
                    f4 = 1.0F;
                }
                else if (i4 == Direction.field_71580_e[j2])
                {
                    f3 = -1.0F;
                    f4 = -1.0F;
                }
                else if (i4 == Direction.field_71577_f[j2])
                {
                    f5 = 1.0F;
                    f6 = -1.0F;
                }
                else
                {
                    f5 = -1.0F;
                    f6 = 1.0F;
                }

                double d9 = p_77184_1_.field_70159_w;
                double d10 = p_77184_1_.field_70179_y;
                p_77184_1_.field_70159_w = d9 * (double)f3 + d10 * (double)f6;
                p_77184_1_.field_70179_y = d9 * (double)f5 + d10 * (double)f4;
                p_77184_1_.field_70177_z = p_77184_8_ - (float)(j2 * 90) + (float)(i4 * 90);
            }
            else
            {
                p_77184_1_.field_70159_w = p_77184_1_.field_70181_x = p_77184_1_.field_70179_y = 0.0D;
            }

            p_77184_1_.func_70012_b(d11, d6, d7, p_77184_1_.field_70177_z, p_77184_1_.field_70125_A);
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean func_85188_a(Entity p_85188_1_)
    {
        byte b0 = 16;
        double d0 = -1.0D;
        int i = MathHelper.func_76128_c(p_85188_1_.field_70165_t);
        int j = MathHelper.func_76128_c(p_85188_1_.field_70163_u);
        int k = MathHelper.func_76128_c(p_85188_1_.field_70161_v);
        int l = i;
        int i1 = j;
        int j1 = k;
        int k1 = 0;
        int l1 = this.field_77187_a.nextInt(4);
        int i2;
        double d1;
        int k2;
        double d2;
        int i3;
        int j3;
        int k3;
        int l3;
        int i4;
        int j4;
        int k4;
        int l4;
        int i5;
        double d3;
        double d4;

        for (i2 = i - b0; i2 <= i + b0; ++i2)
        {
            d1 = (double)i2 + 0.5D - p_85188_1_.field_70165_t;

            for (k2 = k - b0; k2 <= k + b0; ++k2)
            {
                d2 = (double)k2 + 0.5D - p_85188_1_.field_70161_v;
                label274:

                for (i3 = this.field_85192_a.func_72940_L() - 1; i3 >= 0; --i3)
                {
                    if (this.field_85192_a.func_147437_c(i2, i3, k2))
                    {
                        while (i3 > 0 && this.field_85192_a.func_147437_c(i2, i3 - 1, k2))
                        {
                            --i3;
                        }

                        for (j3 = l1; j3 < l1 + 4; ++j3)
                        {
                            k3 = j3 % 2;
                            l3 = 1 - k3;

                            if (j3 % 4 >= 2)
                            {
                                k3 = -k3;
                                l3 = -l3;
                            }

                            for (i4 = 0; i4 < 3; ++i4)
                            {
                                for (j4 = 0; j4 < 4; ++j4)
                                {
                                    for (k4 = -1; k4 < 4; ++k4)
                                    {
                                        l4 = i2 + (j4 - 1) * k3 + i4 * l3;
                                        i5 = i3 + k4;
                                        int j5 = k2 + (j4 - 1) * l3 - i4 * k3;

                                        if (k4 < 0 && !this.field_85192_a.func_147439_a(l4, i5, j5).func_149688_o().func_76220_a() || k4 >= 0 && !this.field_85192_a.func_147437_c(l4, i5, j5))
                                        {
                                            continue label274;
                                        }
                                    }
                                }
                            }

                            d3 = (double)i3 + 0.5D - p_85188_1_.field_70163_u;
                            d4 = d1 * d1 + d3 * d3 + d2 * d2;

                            if (d0 < 0.0D || d4 < d0)
                            {
                                d0 = d4;
                                l = i2;
                                i1 = i3;
                                j1 = k2;
                                k1 = j3 % 4;
                            }
                        }
                    }
                }
            }
        }

        if (d0 < 0.0D)
        {
            for (i2 = i - b0; i2 <= i + b0; ++i2)
            {
                d1 = (double)i2 + 0.5D - p_85188_1_.field_70165_t;

                for (k2 = k - b0; k2 <= k + b0; ++k2)
                {
                    d2 = (double)k2 + 0.5D - p_85188_1_.field_70161_v;
                    label222:

                    for (i3 = this.field_85192_a.func_72940_L() - 1; i3 >= 0; --i3)
                    {
                        if (this.field_85192_a.func_147437_c(i2, i3, k2))
                        {
                            while (i3 > 0 && this.field_85192_a.func_147437_c(i2, i3 - 1, k2))
                            {
                                --i3;
                            }

                            for (j3 = l1; j3 < l1 + 2; ++j3)
                            {
                                k3 = j3 % 2;
                                l3 = 1 - k3;

                                for (i4 = 0; i4 < 4; ++i4)
                                {
                                    for (j4 = -1; j4 < 4; ++j4)
                                    {
                                        k4 = i2 + (i4 - 1) * k3;
                                        l4 = i3 + j4;
                                        i5 = k2 + (i4 - 1) * l3;

                                        if (j4 < 0 && !this.field_85192_a.func_147439_a(k4, l4, i5).func_149688_o().func_76220_a() || j4 >= 0 && !this.field_85192_a.func_147437_c(k4, l4, i5))
                                        {
                                            continue label222;
                                        }
                                    }
                                }

                                d3 = (double)i3 + 0.5D - p_85188_1_.field_70163_u;
                                d4 = d1 * d1 + d3 * d3 + d2 * d2;

                                if (d0 < 0.0D || d4 < d0)
                                {
                                    d0 = d4;
                                    l = i2;
                                    i1 = i3;
                                    j1 = k2;
                                    k1 = j3 % 2;
                                }
                            }
                        }
                    }
                }
            }
        }

        int k5 = l;
        int j2 = i1;
        k2 = j1;
        int l5 = k1 % 2;
        int l2 = 1 - l5;

        if (k1 % 4 >= 2)
        {
            l5 = -l5;
            l2 = -l2;
        }

        boolean flag;

        if (d0 < 0.0D)
        {
            if (i1 < 70)
            {
                i1 = 70;
            }

            if (i1 > this.field_85192_a.func_72940_L() - 10)
            {
                i1 = this.field_85192_a.func_72940_L() - 10;
            }

            j2 = i1;

            for (i3 = -1; i3 <= 1; ++i3)
            {
                for (j3 = 1; j3 < 3; ++j3)
                {
                    for (k3 = -1; k3 < 3; ++k3)
                    {
                        l3 = k5 + (j3 - 1) * l5 + i3 * l2;
                        i4 = j2 + k3;
                        j4 = k2 + (j3 - 1) * l2 - i3 * l5;
                        flag = k3 < 0;
                        this.field_85192_a.func_147449_b(l3, i4, j4, flag ? Blocks.OBSIDIAN : Blocks.AIR);
                    }
                }
            }
        }

        for (i3 = 0; i3 < 4; ++i3)
        {
            for (j3 = 0; j3 < 4; ++j3)
            {
                for (k3 = -1; k3 < 4; ++k3)
                {
                    l3 = k5 + (j3 - 1) * l5;
                    i4 = j2 + k3;
                    j4 = k2 + (j3 - 1) * l2;
                    flag = j3 == 0 || j3 == 3 || k3 == -1 || k3 == 3;
                    this.field_85192_a.func_147465_d(l3, i4, j4, (Block)(flag ? Blocks.OBSIDIAN : Blocks.PORTAL), 0, 2);
                }
            }

            for (j3 = 0; j3 < 4; ++j3)
            {
                for (k3 = -1; k3 < 4; ++k3)
                {
                    l3 = k5 + (j3 - 1) * l5;
                    i4 = j2 + k3;
                    j4 = k2 + (j3 - 1) * l2;
                    this.field_85192_a.func_147459_d(l3, i4, j4, this.field_85192_a.func_147439_a(l3, i4, j4));
                }
            }
        }

        return true;
    }

    public void func_85189_a(long p_85189_1_)
    {
        if (p_85189_1_ % 100L == 0L)
        {
            Iterator iterator = this.field_85190_d.iterator();
            long j = p_85189_1_ - 600L;

            while (iterator.hasNext())
            {
                Long olong = (Long)iterator.next();
                Teleporter.PortalPosition portalposition = (Teleporter.PortalPosition)this.field_85191_c.func_76164_a(olong.longValue());

                if (portalposition == null || portalposition.field_85087_d < j)
                {
                    iterator.remove();
                    this.field_85191_c.func_76159_d(olong.longValue());
                }
            }
        }
    }

    public class PortalPosition extends ChunkCoordinates
    {
        public long field_85087_d;
        private static final String __OBFID = "CL_00000154";

        public PortalPosition(int p_i1962_2_, int p_i1962_3_, int p_i1962_4_, long p_i1962_5_)
        {
            super(p_i1962_2_, p_i1962_3_, p_i1962_4_);
            this.field_85087_d = p_i1962_5_;
        }
    }
}