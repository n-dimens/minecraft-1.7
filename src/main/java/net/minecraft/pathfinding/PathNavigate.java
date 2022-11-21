package net.minecraft.pathfinding;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class PathNavigate
{
    private EntityLiving field_75515_a;
    private World field_75513_b;
    private PathEntity field_75514_c;
    private double field_75511_d;
    private IAttributeInstance field_75512_e;
    private boolean field_75509_f;
    private int field_75510_g;
    private int field_75520_h;
    private Vec3 field_75521_i = Vec3.func_72443_a(0.0D, 0.0D, 0.0D);
    private boolean field_75518_j = true;
    private boolean field_75519_k;
    private boolean field_75516_l;
    private boolean field_75517_m;
    private static final String __OBFID = "CL_00001627";

    public PathNavigate(EntityLiving p_i1671_1_, World p_i1671_2_)
    {
        this.field_75515_a = p_i1671_1_;
        this.field_75513_b = p_i1671_2_;
        this.field_75512_e = p_i1671_1_.func_110148_a(SharedMonsterAttributes.field_111265_b);
    }

    public void func_75491_a(boolean p_75491_1_)
    {
        this.field_75516_l = p_75491_1_;
    }

    public boolean func_75486_a()
    {
        return this.field_75516_l;
    }

    public void func_75498_b(boolean p_75498_1_)
    {
        this.field_75519_k = p_75498_1_;
    }

    public void func_75490_c(boolean p_75490_1_)
    {
        this.field_75518_j = p_75490_1_;
    }

    public boolean func_75507_c()
    {
        return this.field_75519_k;
    }

    public void func_75504_d(boolean p_75504_1_)
    {
        this.field_75509_f = p_75504_1_;
    }

    public void func_75489_a(double p_75489_1_)
    {
        this.field_75511_d = p_75489_1_;
    }

    public void func_75495_e(boolean p_75495_1_)
    {
        this.field_75517_m = p_75495_1_;
    }

    public float func_111269_d()
    {
        return (float)this.field_75512_e.func_111126_e();
    }

    public PathEntity func_75488_a(double p_75488_1_, double p_75488_3_, double p_75488_5_)
    {
        return !this.func_75485_k() ? null : this.field_75513_b.func_72844_a(this.field_75515_a, MathHelper.func_76128_c(p_75488_1_), (int)p_75488_3_, MathHelper.func_76128_c(p_75488_5_), this.func_111269_d(), this.field_75518_j, this.field_75519_k, this.field_75516_l, this.field_75517_m);
    }

    public boolean func_75492_a(double p_75492_1_, double p_75492_3_, double p_75492_5_, double p_75492_7_)
    {
        PathEntity pathentity = this.func_75488_a((double)MathHelper.func_76128_c(p_75492_1_), (double)((int)p_75492_3_), (double)MathHelper.func_76128_c(p_75492_5_));
        return this.func_75484_a(pathentity, p_75492_7_);
    }

    public PathEntity func_75494_a(Entity p_75494_1_)
    {
        return !this.func_75485_k() ? null : this.field_75513_b.func_72865_a(this.field_75515_a, p_75494_1_, this.func_111269_d(), this.field_75518_j, this.field_75519_k, this.field_75516_l, this.field_75517_m);
    }

    public boolean func_75497_a(Entity p_75497_1_, double p_75497_2_)
    {
        PathEntity pathentity = this.func_75494_a(p_75497_1_);
        return pathentity != null ? this.func_75484_a(pathentity, p_75497_2_) : false;
    }

    public boolean func_75484_a(PathEntity p_75484_1_, double p_75484_2_)
    {
        if (p_75484_1_ == null)
        {
            this.field_75514_c = null;
            return false;
        }
        else
        {
            if (!p_75484_1_.func_75876_a(this.field_75514_c))
            {
                this.field_75514_c = p_75484_1_;
            }

            if (this.field_75509_f)
            {
                this.func_75487_m();
            }

            if (this.field_75514_c.func_75874_d() == 0)
            {
                return false;
            }
            else
            {
                this.field_75511_d = p_75484_2_;
                Vec3 vec3 = this.func_75502_i();
                this.field_75520_h = this.field_75510_g;
                this.field_75521_i.field_72450_a = vec3.field_72450_a;
                this.field_75521_i.field_72448_b = vec3.field_72448_b;
                this.field_75521_i.field_72449_c = vec3.field_72449_c;
                return true;
            }
        }
    }

    public PathEntity func_75505_d()
    {
        return this.field_75514_c;
    }

    public void func_75501_e()
    {
        ++this.field_75510_g;

        if (!this.func_75500_f())
        {
            if (this.func_75485_k())
            {
                this.func_75508_h();
            }

            if (!this.func_75500_f())
            {
                Vec3 vec3 = this.field_75514_c.func_75878_a(this.field_75515_a);

                if (vec3 != null)
                {
                    this.field_75515_a.func_70605_aq().func_75642_a(vec3.field_72450_a, vec3.field_72448_b, vec3.field_72449_c, this.field_75511_d);
                }
            }
        }
    }

    private void func_75508_h()
    {
        Vec3 vec3 = this.func_75502_i();
        int i = this.field_75514_c.func_75874_d();

        for (int j = this.field_75514_c.func_75873_e(); j < this.field_75514_c.func_75874_d(); ++j)
        {
            if (this.field_75514_c.func_75877_a(j).field_75837_b != (int)vec3.field_72448_b)
            {
                i = j;
                break;
            }
        }

        float f = this.field_75515_a.field_70130_N * this.field_75515_a.field_70130_N;
        int k;

        for (k = this.field_75514_c.func_75873_e(); k < i; ++k)
        {
            if (vec3.func_72436_e(this.field_75514_c.func_75881_a(this.field_75515_a, k)) < (double)f)
            {
                this.field_75514_c.func_75872_c(k + 1);
            }
        }

        k = MathHelper.func_76123_f(this.field_75515_a.field_70130_N);
        int l = (int)this.field_75515_a.field_70131_O + 1;
        int i1 = k;

        for (int j1 = i - 1; j1 >= this.field_75514_c.func_75873_e(); --j1)
        {
            if (this.func_75493_a(vec3, this.field_75514_c.func_75881_a(this.field_75515_a, j1), k, l, i1))
            {
                this.field_75514_c.func_75872_c(j1);
                break;
            }
        }

        if (this.field_75510_g - this.field_75520_h > 100)
        {
            if (vec3.func_72436_e(this.field_75521_i) < 2.25D)
            {
                this.func_75499_g();
            }

            this.field_75520_h = this.field_75510_g;
            this.field_75521_i.field_72450_a = vec3.field_72450_a;
            this.field_75521_i.field_72448_b = vec3.field_72448_b;
            this.field_75521_i.field_72449_c = vec3.field_72449_c;
        }
    }

    public boolean func_75500_f()
    {
        return this.field_75514_c == null || this.field_75514_c.func_75879_b();
    }

    public void func_75499_g()
    {
        this.field_75514_c = null;
    }

    private Vec3 func_75502_i()
    {
        return Vec3.func_72443_a(this.field_75515_a.field_70165_t, (double)this.func_75503_j(), this.field_75515_a.field_70161_v);
    }

    private int func_75503_j()
    {
        if (this.field_75515_a.func_70090_H() && this.field_75517_m)
        {
            int i = (int)this.field_75515_a.field_70121_D.field_72338_b;
            Block block = this.field_75513_b.func_147439_a(MathHelper.func_76128_c(this.field_75515_a.field_70165_t), i, MathHelper.func_76128_c(this.field_75515_a.field_70161_v));
            int j = 0;

            do
            {
                if (block != Blocks.FLOWING_WATER && block != Blocks.WATER)
                {
                    return i;
                }

                ++i;
                block = this.field_75513_b.func_147439_a(MathHelper.func_76128_c(this.field_75515_a.field_70165_t), i, MathHelper.func_76128_c(this.field_75515_a.field_70161_v));
                ++j;
            }
            while (j <= 16);

            return (int)this.field_75515_a.field_70121_D.field_72338_b;
        }
        else
        {
            return (int)(this.field_75515_a.field_70121_D.field_72338_b + 0.5D);
        }
    }

    private boolean func_75485_k()
    {
        return this.field_75515_a.field_70122_E || this.field_75517_m && this.func_75506_l() || this.field_75515_a.func_70115_ae() && this.field_75515_a instanceof EntityZombie && this.field_75515_a.field_70154_o instanceof EntityChicken;
    }

    private boolean func_75506_l()
    {
        return this.field_75515_a.func_70090_H() || this.field_75515_a.func_70058_J();
    }

    private void func_75487_m()
    {
        if (!this.field_75513_b.func_72937_j(MathHelper.func_76128_c(this.field_75515_a.field_70165_t), (int)(this.field_75515_a.field_70121_D.field_72338_b + 0.5D), MathHelper.func_76128_c(this.field_75515_a.field_70161_v)))
        {
            for (int i = 0; i < this.field_75514_c.func_75874_d(); ++i)
            {
                PathPoint pathpoint = this.field_75514_c.func_75877_a(i);

                if (this.field_75513_b.func_72937_j(pathpoint.field_75839_a, pathpoint.field_75837_b, pathpoint.field_75838_c))
                {
                    this.field_75514_c.func_75871_b(i - 1);
                    return;
                }
            }
        }
    }

    private boolean func_75493_a(Vec3 p_75493_1_, Vec3 p_75493_2_, int p_75493_3_, int p_75493_4_, int p_75493_5_)
    {
        int l = MathHelper.func_76128_c(p_75493_1_.field_72450_a);
        int i1 = MathHelper.func_76128_c(p_75493_1_.field_72449_c);
        double d0 = p_75493_2_.field_72450_a - p_75493_1_.field_72450_a;
        double d1 = p_75493_2_.field_72449_c - p_75493_1_.field_72449_c;
        double d2 = d0 * d0 + d1 * d1;

        if (d2 < 1.0E-8D)
        {
            return false;
        }
        else
        {
            double d3 = 1.0D / Math.sqrt(d2);
            d0 *= d3;
            d1 *= d3;
            p_75493_3_ += 2;
            p_75493_5_ += 2;

            if (!this.func_75483_a(l, (int)p_75493_1_.field_72448_b, i1, p_75493_3_, p_75493_4_, p_75493_5_, p_75493_1_, d0, d1))
            {
                return false;
            }
            else
            {
                p_75493_3_ -= 2;
                p_75493_5_ -= 2;
                double d4 = 1.0D / Math.abs(d0);
                double d5 = 1.0D / Math.abs(d1);
                double d6 = (double)(l * 1) - p_75493_1_.field_72450_a;
                double d7 = (double)(i1 * 1) - p_75493_1_.field_72449_c;

                if (d0 >= 0.0D)
                {
                    ++d6;
                }

                if (d1 >= 0.0D)
                {
                    ++d7;
                }

                d6 /= d0;
                d7 /= d1;
                int j1 = d0 < 0.0D ? -1 : 1;
                int k1 = d1 < 0.0D ? -1 : 1;
                int l1 = MathHelper.func_76128_c(p_75493_2_.field_72450_a);
                int i2 = MathHelper.func_76128_c(p_75493_2_.field_72449_c);
                int j2 = l1 - l;
                int k2 = i2 - i1;

                do
                {
                    if (j2 * j1 <= 0 && k2 * k1 <= 0)
                    {
                        return true;
                    }

                    if (d6 < d7)
                    {
                        d6 += d4;
                        l += j1;
                        j2 = l1 - l;
                    }
                    else
                    {
                        d7 += d5;
                        i1 += k1;
                        k2 = i2 - i1;
                    }
                }
                while (this.func_75483_a(l, (int)p_75493_1_.field_72448_b, i1, p_75493_3_, p_75493_4_, p_75493_5_, p_75493_1_, d0, d1));

                return false;
            }
        }
    }

    private boolean func_75483_a(int p_75483_1_, int p_75483_2_, int p_75483_3_, int p_75483_4_, int p_75483_5_, int p_75483_6_, Vec3 p_75483_7_, double p_75483_8_, double p_75483_10_)
    {
        int k1 = p_75483_1_ - p_75483_4_ / 2;
        int l1 = p_75483_3_ - p_75483_6_ / 2;

        if (!this.func_75496_b(k1, p_75483_2_, l1, p_75483_4_, p_75483_5_, p_75483_6_, p_75483_7_, p_75483_8_, p_75483_10_))
        {
            return false;
        }
        else
        {
            for (int i2 = k1; i2 < k1 + p_75483_4_; ++i2)
            {
                for (int j2 = l1; j2 < l1 + p_75483_6_; ++j2)
                {
                    double d2 = (double)i2 + 0.5D - p_75483_7_.field_72450_a;
                    double d3 = (double)j2 + 0.5D - p_75483_7_.field_72449_c;

                    if (d2 * p_75483_8_ + d3 * p_75483_10_ >= 0.0D)
                    {
                        Block block = this.field_75513_b.func_147439_a(i2, p_75483_2_ - 1, j2);
                        Material material = block.func_149688_o();

                        if (material == Material.field_151579_a)
                        {
                            return false;
                        }

                        if (material == Material.field_151586_h && !this.field_75515_a.func_70090_H())
                        {
                            return false;
                        }

                        if (material == Material.field_151587_i)
                        {
                            return false;
                        }
                    }
                }
            }

            return true;
        }
    }

    private boolean func_75496_b(int p_75496_1_, int p_75496_2_, int p_75496_3_, int p_75496_4_, int p_75496_5_, int p_75496_6_, Vec3 p_75496_7_, double p_75496_8_, double p_75496_10_)
    {
        for (int k1 = p_75496_1_; k1 < p_75496_1_ + p_75496_4_; ++k1)
        {
            for (int l1 = p_75496_2_; l1 < p_75496_2_ + p_75496_5_; ++l1)
            {
                for (int i2 = p_75496_3_; i2 < p_75496_3_ + p_75496_6_; ++i2)
                {
                    double d2 = (double)k1 + 0.5D - p_75496_7_.field_72450_a;
                    double d3 = (double)i2 + 0.5D - p_75496_7_.field_72449_c;

                    if (d2 * p_75496_8_ + d3 * p_75496_10_ >= 0.0D)
                    {
                        Block block = this.field_75513_b.func_147439_a(k1, l1, i2);

                        if (!block.func_149655_b(this.field_75513_b, k1, l1, i2))
                        {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }
}