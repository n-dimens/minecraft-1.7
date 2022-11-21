package net.minecraft.entity.boss;

import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEndPortal;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class EntityDragon extends EntityLiving implements IBossDisplayData, IEntityMultiPart, IMob
{
    public double field_70980_b;
    public double field_70981_c;
    public double field_70978_d;
    public double[][] field_70979_e = new double[64][3];
    public int field_70976_f = -1;
    public EntityDragonPart[] field_70977_g;
    public EntityDragonPart field_70986_h;
    public EntityDragonPart field_70987_i;
    public EntityDragonPart field_70985_j;
    public EntityDragonPart field_70984_by;
    public EntityDragonPart field_70982_bz;
    public EntityDragonPart field_70983_bA;
    public EntityDragonPart field_70990_bB;
    public float field_70991_bC;
    public float field_70988_bD;
    public boolean field_70989_bE;
    public boolean field_70994_bF;
    private Entity field_70993_bI;
    public int field_70995_bG;
    public EntityEnderCrystal field_70992_bH;
    private static final String __OBFID = "CL_00001659";

    public EntityDragon(World p_i1700_1_)
    {
        super(p_i1700_1_);
        this.field_70977_g = new EntityDragonPart[] {this.field_70986_h = new EntityDragonPart(this, "head", 6.0F, 6.0F), this.field_70987_i = new EntityDragonPart(this, "body", 8.0F, 8.0F), this.field_70985_j = new EntityDragonPart(this, "tail", 4.0F, 4.0F), this.field_70984_by = new EntityDragonPart(this, "tail", 4.0F, 4.0F), this.field_70982_bz = new EntityDragonPart(this, "tail", 4.0F, 4.0F), this.field_70983_bA = new EntityDragonPart(this, "wing", 4.0F, 4.0F), this.field_70990_bB = new EntityDragonPart(this, "wing", 4.0F, 4.0F)};
        this.func_70606_j(this.func_110138_aP());
        this.func_70105_a(16.0F, 8.0F);
        this.field_70145_X = true;
        this.field_70178_ae = true;
        this.field_70981_c = 100.0D;
        this.field_70158_ak = true;
    }

    protected void func_110147_ax()
    {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(200.0D);
    }

    protected void func_70088_a()
    {
        super.func_70088_a();
    }

    public double[] func_70974_a(int p_70974_1_, float p_70974_2_)
    {
        if (this.func_110143_aJ() <= 0.0F)
        {
            p_70974_2_ = 0.0F;
        }

        p_70974_2_ = 1.0F - p_70974_2_;
        int j = this.field_70976_f - p_70974_1_ * 1 & 63;
        int k = this.field_70976_f - p_70974_1_ * 1 - 1 & 63;
        double[] adouble = new double[3];
        double d0 = this.field_70979_e[j][0];
        double d1 = MathHelper.func_76138_g(this.field_70979_e[k][0] - d0);
        adouble[0] = d0 + d1 * (double)p_70974_2_;
        d0 = this.field_70979_e[j][1];
        d1 = this.field_70979_e[k][1] - d0;
        adouble[1] = d0 + d1 * (double)p_70974_2_;
        adouble[2] = this.field_70979_e[j][2] + (this.field_70979_e[k][2] - this.field_70979_e[j][2]) * (double)p_70974_2_;
        return adouble;
    }

    public void func_70636_d()
    {
        float f;
        float f1;

        if (this.field_70170_p.field_72995_K)
        {
            f = MathHelper.func_76134_b(this.field_70988_bD * (float)Math.PI * 2.0F);
            f1 = MathHelper.func_76134_b(this.field_70991_bC * (float)Math.PI * 2.0F);

            if (f1 <= -0.3F && f >= -0.3F)
            {
                this.field_70170_p.func_72980_b(this.field_70165_t, this.field_70163_u, this.field_70161_v, "mob.enderdragon.wings", 5.0F, 0.8F + this.field_70146_Z.nextFloat() * 0.3F, false);
            }
        }

        this.field_70991_bC = this.field_70988_bD;
        float f2;

        if (this.func_110143_aJ() <= 0.0F)
        {
            f = (this.field_70146_Z.nextFloat() - 0.5F) * 8.0F;
            f1 = (this.field_70146_Z.nextFloat() - 0.5F) * 4.0F;
            f2 = (this.field_70146_Z.nextFloat() - 0.5F) * 8.0F;
            this.field_70170_p.func_72869_a("largeexplode", this.field_70165_t + (double)f, this.field_70163_u + 2.0D + (double)f1, this.field_70161_v + (double)f2, 0.0D, 0.0D, 0.0D);
        }
        else
        {
            this.func_70969_j();
            f = 0.2F / (MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y) * 10.0F + 1.0F);
            f *= (float)Math.pow(2.0D, this.field_70181_x);

            if (this.field_70994_bF)
            {
                this.field_70988_bD += f * 0.5F;
            }
            else
            {
                this.field_70988_bD += f;
            }

            this.field_70177_z = MathHelper.func_76142_g(this.field_70177_z);

            if (this.field_70976_f < 0)
            {
                for (int i = 0; i < this.field_70979_e.length; ++i)
                {
                    this.field_70979_e[i][0] = (double)this.field_70177_z;
                    this.field_70979_e[i][1] = this.field_70163_u;
                }
            }

            if (++this.field_70976_f == this.field_70979_e.length)
            {
                this.field_70976_f = 0;
            }

            this.field_70979_e[this.field_70976_f][0] = (double)this.field_70177_z;
            this.field_70979_e[this.field_70976_f][1] = this.field_70163_u;
            double d0;
            double d1;
            double d2;
            double d10;
            float f12;

            if (this.field_70170_p.field_72995_K)
            {
                if (this.field_70716_bi > 0)
                {
                    d10 = this.field_70165_t + (this.field_70709_bj - this.field_70165_t) / (double)this.field_70716_bi;
                    d0 = this.field_70163_u + (this.field_70710_bk - this.field_70163_u) / (double)this.field_70716_bi;
                    d1 = this.field_70161_v + (this.field_110152_bk - this.field_70161_v) / (double)this.field_70716_bi;
                    d2 = MathHelper.func_76138_g(this.field_70712_bm - (double)this.field_70177_z);
                    this.field_70177_z = (float)((double)this.field_70177_z + d2 / (double)this.field_70716_bi);
                    this.field_70125_A = (float)((double)this.field_70125_A + (this.field_70705_bn - (double)this.field_70125_A) / (double)this.field_70716_bi);
                    --this.field_70716_bi;
                    this.func_70107_b(d10, d0, d1);
                    this.func_70101_b(this.field_70177_z, this.field_70125_A);
                }
            }
            else
            {
                d10 = this.field_70980_b - this.field_70165_t;
                d0 = this.field_70981_c - this.field_70163_u;
                d1 = this.field_70978_d - this.field_70161_v;
                d2 = d10 * d10 + d0 * d0 + d1 * d1;

                if (this.field_70993_bI != null)
                {
                    this.field_70980_b = this.field_70993_bI.field_70165_t;
                    this.field_70978_d = this.field_70993_bI.field_70161_v;
                    double d3 = this.field_70980_b - this.field_70165_t;
                    double d5 = this.field_70978_d - this.field_70161_v;
                    double d7 = Math.sqrt(d3 * d3 + d5 * d5);
                    double d8 = 0.4000000059604645D + d7 / 80.0D - 1.0D;

                    if (d8 > 10.0D)
                    {
                        d8 = 10.0D;
                    }

                    this.field_70981_c = this.field_70993_bI.field_70121_D.field_72338_b + d8;
                }
                else
                {
                    this.field_70980_b += this.field_70146_Z.nextGaussian() * 2.0D;
                    this.field_70978_d += this.field_70146_Z.nextGaussian() * 2.0D;
                }

                if (this.field_70989_bE || d2 < 100.0D || d2 > 22500.0D || this.field_70123_F || this.field_70124_G)
                {
                    this.func_70967_k();
                }

                d0 /= (double)MathHelper.func_76133_a(d10 * d10 + d1 * d1);
                f12 = 0.6F;

                if (d0 < (double)(-f12))
                {
                    d0 = (double)(-f12);
                }

                if (d0 > (double)f12)
                {
                    d0 = (double)f12;
                }

                this.field_70181_x += d0 * 0.10000000149011612D;
                this.field_70177_z = MathHelper.func_76142_g(this.field_70177_z);
                double d4 = 180.0D - Math.atan2(d10, d1) * 180.0D / Math.PI;
                double d6 = MathHelper.func_76138_g(d4 - (double)this.field_70177_z);

                if (d6 > 50.0D)
                {
                    d6 = 50.0D;
                }

                if (d6 < -50.0D)
                {
                    d6 = -50.0D;
                }

                Vec3 vec3 = Vec3.func_72443_a(this.field_70980_b - this.field_70165_t, this.field_70981_c - this.field_70163_u, this.field_70978_d - this.field_70161_v).func_72432_b();
                Vec3 vec32 = Vec3.func_72443_a((double)MathHelper.func_76126_a(this.field_70177_z * (float)Math.PI / 180.0F), this.field_70181_x, (double)(-MathHelper.func_76134_b(this.field_70177_z * (float)Math.PI / 180.0F))).func_72432_b();
                float f5 = (float)(vec32.func_72430_b(vec3) + 0.5D) / 1.5F;

                if (f5 < 0.0F)
                {
                    f5 = 0.0F;
                }

                this.field_70704_bt *= 0.8F;
                float f6 = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y) * 1.0F + 1.0F;
                double d9 = Math.sqrt(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y) * 1.0D + 1.0D;

                if (d9 > 40.0D)
                {
                    d9 = 40.0D;
                }

                this.field_70704_bt = (float)((double)this.field_70704_bt + d6 * (0.699999988079071D / d9 / (double)f6));
                this.field_70177_z += this.field_70704_bt * 0.1F;
                float f7 = (float)(2.0D / (d9 + 1.0D));
                float f8 = 0.06F;
                this.func_70060_a(0.0F, -1.0F, f8 * (f5 * f7 + (1.0F - f7)));

                if (this.field_70994_bF)
                {
                    this.func_70091_d(this.field_70159_w * 0.800000011920929D, this.field_70181_x * 0.800000011920929D, this.field_70179_y * 0.800000011920929D);
                }
                else
                {
                    this.func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
                }

                Vec3 vec31 = Vec3.func_72443_a(this.field_70159_w, this.field_70181_x, this.field_70179_y).func_72432_b();
                float f9 = (float)(vec31.func_72430_b(vec32) + 1.0D) / 2.0F;
                f9 = 0.8F + 0.15F * f9;
                this.field_70159_w *= (double)f9;
                this.field_70179_y *= (double)f9;
                this.field_70181_x *= 0.9100000262260437D;
            }

            this.field_70761_aq = this.field_70177_z;
            this.field_70986_h.field_70130_N = this.field_70986_h.field_70131_O = 3.0F;
            this.field_70985_j.field_70130_N = this.field_70985_j.field_70131_O = 2.0F;
            this.field_70984_by.field_70130_N = this.field_70984_by.field_70131_O = 2.0F;
            this.field_70982_bz.field_70130_N = this.field_70982_bz.field_70131_O = 2.0F;
            this.field_70987_i.field_70131_O = 3.0F;
            this.field_70987_i.field_70130_N = 5.0F;
            this.field_70983_bA.field_70131_O = 2.0F;
            this.field_70983_bA.field_70130_N = 4.0F;
            this.field_70990_bB.field_70131_O = 3.0F;
            this.field_70990_bB.field_70130_N = 4.0F;
            f1 = (float)(this.func_70974_a(5, 1.0F)[1] - this.func_70974_a(10, 1.0F)[1]) * 10.0F / 180.0F * (float)Math.PI;
            f2 = MathHelper.func_76134_b(f1);
            float f10 = -MathHelper.func_76126_a(f1);
            float f3 = this.field_70177_z * (float)Math.PI / 180.0F;
            float f11 = MathHelper.func_76126_a(f3);
            float f4 = MathHelper.func_76134_b(f3);
            this.field_70987_i.func_70071_h_();
            this.field_70987_i.func_70012_b(this.field_70165_t + (double)(f11 * 0.5F), this.field_70163_u, this.field_70161_v - (double)(f4 * 0.5F), 0.0F, 0.0F);
            this.field_70983_bA.func_70071_h_();
            this.field_70983_bA.func_70012_b(this.field_70165_t + (double)(f4 * 4.5F), this.field_70163_u + 2.0D, this.field_70161_v + (double)(f11 * 4.5F), 0.0F, 0.0F);
            this.field_70990_bB.func_70071_h_();
            this.field_70990_bB.func_70012_b(this.field_70165_t - (double)(f4 * 4.5F), this.field_70163_u + 2.0D, this.field_70161_v - (double)(f11 * 4.5F), 0.0F, 0.0F);

            if (!this.field_70170_p.field_72995_K && this.field_70737_aN == 0)
            {
                this.func_70970_a(this.field_70170_p.func_72839_b(this, this.field_70983_bA.field_70121_D.func_72314_b(4.0D, 2.0D, 4.0D).func_72317_d(0.0D, -2.0D, 0.0D)));
                this.func_70970_a(this.field_70170_p.func_72839_b(this, this.field_70990_bB.field_70121_D.func_72314_b(4.0D, 2.0D, 4.0D).func_72317_d(0.0D, -2.0D, 0.0D)));
                this.func_70971_b(this.field_70170_p.func_72839_b(this, this.field_70986_h.field_70121_D.func_72314_b(1.0D, 1.0D, 1.0D)));
            }

            double[] adouble1 = this.func_70974_a(5, 1.0F);
            double[] adouble = this.func_70974_a(0, 1.0F);
            f12 = MathHelper.func_76126_a(this.field_70177_z * (float)Math.PI / 180.0F - this.field_70704_bt * 0.01F);
            float f13 = MathHelper.func_76134_b(this.field_70177_z * (float)Math.PI / 180.0F - this.field_70704_bt * 0.01F);
            this.field_70986_h.func_70071_h_();
            this.field_70986_h.func_70012_b(this.field_70165_t + (double)(f12 * 5.5F * f2), this.field_70163_u + (adouble[1] - adouble1[1]) * 1.0D + (double)(f10 * 5.5F), this.field_70161_v - (double)(f13 * 5.5F * f2), 0.0F, 0.0F);

            for (int j = 0; j < 3; ++j)
            {
                EntityDragonPart entitydragonpart = null;

                if (j == 0)
                {
                    entitydragonpart = this.field_70985_j;
                }

                if (j == 1)
                {
                    entitydragonpart = this.field_70984_by;
                }

                if (j == 2)
                {
                    entitydragonpart = this.field_70982_bz;
                }

                double[] adouble2 = this.func_70974_a(12 + j * 2, 1.0F);
                float f14 = this.field_70177_z * (float)Math.PI / 180.0F + this.func_70973_b(adouble2[0] - adouble1[0]) * (float)Math.PI / 180.0F * 1.0F;
                float f15 = MathHelper.func_76126_a(f14);
                float f16 = MathHelper.func_76134_b(f14);
                float f17 = 1.5F;
                float f18 = (float)(j + 1) * 2.0F;
                entitydragonpart.func_70071_h_();
                entitydragonpart.func_70012_b(this.field_70165_t - (double)((f11 * f17 + f15 * f18) * f2), this.field_70163_u + (adouble2[1] - adouble1[1]) * 1.0D - (double)((f18 + f17) * f10) + 1.5D, this.field_70161_v + (double)((f4 * f17 + f16 * f18) * f2), 0.0F, 0.0F);
            }

            if (!this.field_70170_p.field_72995_K)
            {
                this.field_70994_bF = this.func_70972_a(this.field_70986_h.field_70121_D) | this.func_70972_a(this.field_70987_i.field_70121_D);
            }
        }
    }

    private void func_70969_j()
    {
        if (this.field_70992_bH != null)
        {
            if (this.field_70992_bH.field_70128_L)
            {
                if (!this.field_70170_p.field_72995_K)
                {
                    this.func_70965_a(this.field_70986_h, DamageSource.func_94539_a((Explosion)null), 10.0F);
                }

                this.field_70992_bH = null;
            }
            else if (this.field_70173_aa % 10 == 0 && this.func_110143_aJ() < this.func_110138_aP())
            {
                this.func_70606_j(this.func_110143_aJ() + 1.0F);
            }
        }

        if (this.field_70146_Z.nextInt(10) == 0)
        {
            float f = 32.0F;
            List list = this.field_70170_p.func_72872_a(EntityEnderCrystal.class, this.field_70121_D.func_72314_b((double)f, (double)f, (double)f));
            EntityEnderCrystal entityendercrystal = null;
            double d0 = Double.MAX_VALUE;
            Iterator iterator = list.iterator();

            while (iterator.hasNext())
            {
                EntityEnderCrystal entityendercrystal1 = (EntityEnderCrystal)iterator.next();
                double d1 = entityendercrystal1.func_70068_e(this);

                if (d1 < d0)
                {
                    d0 = d1;
                    entityendercrystal = entityendercrystal1;
                }
            }

            this.field_70992_bH = entityendercrystal;
        }
    }

    private void func_70970_a(List p_70970_1_)
    {
        double d0 = (this.field_70987_i.field_70121_D.field_72340_a + this.field_70987_i.field_70121_D.field_72336_d) / 2.0D;
        double d1 = (this.field_70987_i.field_70121_D.field_72339_c + this.field_70987_i.field_70121_D.field_72334_f) / 2.0D;
        Iterator iterator = p_70970_1_.iterator();

        while (iterator.hasNext())
        {
            Entity entity = (Entity)iterator.next();

            if (entity instanceof EntityLivingBase)
            {
                double d2 = entity.field_70165_t - d0;
                double d3 = entity.field_70161_v - d1;
                double d4 = d2 * d2 + d3 * d3;
                entity.func_70024_g(d2 / d4 * 4.0D, 0.20000000298023224D, d3 / d4 * 4.0D);
            }
        }
    }

    private void func_70971_b(List p_70971_1_)
    {
        for (int i = 0; i < p_70971_1_.size(); ++i)
        {
            Entity entity = (Entity)p_70971_1_.get(i);

            if (entity instanceof EntityLivingBase)
            {
                entity.func_70097_a(DamageSource.func_76358_a(this), 10.0F);
            }
        }
    }

    private void func_70967_k()
    {
        this.field_70989_bE = false;

        if (this.field_70146_Z.nextInt(2) == 0 && !this.field_70170_p.field_73010_i.isEmpty())
        {
            this.field_70993_bI = (Entity)this.field_70170_p.field_73010_i.get(this.field_70146_Z.nextInt(this.field_70170_p.field_73010_i.size()));
        }
        else
        {
            boolean flag = false;

            do
            {
                this.field_70980_b = 0.0D;
                this.field_70981_c = (double)(70.0F + this.field_70146_Z.nextFloat() * 50.0F);
                this.field_70978_d = 0.0D;
                this.field_70980_b += (double)(this.field_70146_Z.nextFloat() * 120.0F - 60.0F);
                this.field_70978_d += (double)(this.field_70146_Z.nextFloat() * 120.0F - 60.0F);
                double d0 = this.field_70165_t - this.field_70980_b;
                double d1 = this.field_70163_u - this.field_70981_c;
                double d2 = this.field_70161_v - this.field_70978_d;
                flag = d0 * d0 + d1 * d1 + d2 * d2 > 100.0D;
            }
            while (!flag);

            this.field_70993_bI = null;
        }
    }

    private float func_70973_b(double p_70973_1_)
    {
        return (float)MathHelper.func_76138_g(p_70973_1_);
    }

    private boolean func_70972_a(AxisAlignedBB p_70972_1_)
    {
        int i = MathHelper.func_76128_c(p_70972_1_.field_72340_a);
        int j = MathHelper.func_76128_c(p_70972_1_.field_72338_b);
        int k = MathHelper.func_76128_c(p_70972_1_.field_72339_c);
        int l = MathHelper.func_76128_c(p_70972_1_.field_72336_d);
        int i1 = MathHelper.func_76128_c(p_70972_1_.field_72337_e);
        int j1 = MathHelper.func_76128_c(p_70972_1_.field_72334_f);
        boolean flag = false;
        boolean flag1 = false;

        for (int k1 = i; k1 <= l; ++k1)
        {
            for (int l1 = j; l1 <= i1; ++l1)
            {
                for (int i2 = k; i2 <= j1; ++i2)
                {
                    Block block = this.field_70170_p.func_147439_a(k1, l1, i2);

                    if (block.func_149688_o() != Material.field_151579_a)
                    {
                        if (block != Blocks.field_150343_Z && block != Blocks.field_150377_bs && block != Blocks.field_150357_h && this.field_70170_p.func_82736_K().func_82766_b("mobGriefing"))
                        {
                            flag1 = this.field_70170_p.func_147468_f(k1, l1, i2) || flag1;
                        }
                        else
                        {
                            flag = true;
                        }
                    }
                }
            }
        }

        if (flag1)
        {
            double d1 = p_70972_1_.field_72340_a + (p_70972_1_.field_72336_d - p_70972_1_.field_72340_a) * (double)this.field_70146_Z.nextFloat();
            double d2 = p_70972_1_.field_72338_b + (p_70972_1_.field_72337_e - p_70972_1_.field_72338_b) * (double)this.field_70146_Z.nextFloat();
            double d0 = p_70972_1_.field_72339_c + (p_70972_1_.field_72334_f - p_70972_1_.field_72339_c) * (double)this.field_70146_Z.nextFloat();
            this.field_70170_p.func_72869_a("largeexplode", d1, d2, d0, 0.0D, 0.0D, 0.0D);
        }

        return flag;
    }

    public boolean func_70965_a(EntityDragonPart p_70965_1_, DamageSource p_70965_2_, float p_70965_3_)
    {
        if (p_70965_1_ != this.field_70986_h)
        {
            p_70965_3_ = p_70965_3_ / 4.0F + 1.0F;
        }

        float f1 = this.field_70177_z * (float)Math.PI / 180.0F;
        float f2 = MathHelper.func_76126_a(f1);
        float f3 = MathHelper.func_76134_b(f1);
        this.field_70980_b = this.field_70165_t + (double)(f2 * 5.0F) + (double)((this.field_70146_Z.nextFloat() - 0.5F) * 2.0F);
        this.field_70981_c = this.field_70163_u + (double)(this.field_70146_Z.nextFloat() * 3.0F) + 1.0D;
        this.field_70978_d = this.field_70161_v - (double)(f3 * 5.0F) + (double)((this.field_70146_Z.nextFloat() - 0.5F) * 2.0F);
        this.field_70993_bI = null;

        if (p_70965_2_.func_76346_g() instanceof EntityPlayer || p_70965_2_.func_94541_c())
        {
            this.func_82195_e(p_70965_2_, p_70965_3_);
        }

        return true;
    }

    public boolean func_70097_a(DamageSource p_70097_1_, float p_70097_2_)
    {
        return false;
    }

    protected boolean func_82195_e(DamageSource p_82195_1_, float p_82195_2_)
    {
        return super.func_70097_a(p_82195_1_, p_82195_2_);
    }

    protected void func_70609_aI()
    {
        ++this.field_70995_bG;

        if (this.field_70995_bG >= 180 && this.field_70995_bG <= 200)
        {
            float f = (this.field_70146_Z.nextFloat() - 0.5F) * 8.0F;
            float f1 = (this.field_70146_Z.nextFloat() - 0.5F) * 4.0F;
            float f2 = (this.field_70146_Z.nextFloat() - 0.5F) * 8.0F;
            this.field_70170_p.func_72869_a("hugeexplosion", this.field_70165_t + (double)f, this.field_70163_u + 2.0D + (double)f1, this.field_70161_v + (double)f2, 0.0D, 0.0D, 0.0D);
        }

        int i;
        int j;

        if (!this.field_70170_p.field_72995_K)
        {
            if (this.field_70995_bG > 150 && this.field_70995_bG % 5 == 0)
            {
                i = 1000;

                while (i > 0)
                {
                    j = EntityXPOrb.func_70527_a(i);
                    i -= j;
                    this.field_70170_p.func_72838_d(new EntityXPOrb(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, j));
                }
            }

            if (this.field_70995_bG == 1)
            {
                this.field_70170_p.func_82739_e(1018, (int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v, 0);
            }
        }

        this.func_70091_d(0.0D, 0.10000000149011612D, 0.0D);
        this.field_70761_aq = this.field_70177_z += 20.0F;

        if (this.field_70995_bG == 200 && !this.field_70170_p.field_72995_K)
        {
            i = 2000;

            while (i > 0)
            {
                j = EntityXPOrb.func_70527_a(i);
                i -= j;
                this.field_70170_p.func_72838_d(new EntityXPOrb(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, j));
            }

            this.func_70975_a(MathHelper.func_76128_c(this.field_70165_t), MathHelper.func_76128_c(this.field_70161_v));
            this.func_70106_y();
        }
    }

    private void func_70975_a(int p_70975_1_, int p_70975_2_)
    {
        byte b0 = 64;
        BlockEndPortal.field_149948_a = true;
        byte b1 = 4;

        for (int k = b0 - 1; k <= b0 + 32; ++k)
        {
            for (int l = p_70975_1_ - b1; l <= p_70975_1_ + b1; ++l)
            {
                for (int i1 = p_70975_2_ - b1; i1 <= p_70975_2_ + b1; ++i1)
                {
                    double d0 = (double)(l - p_70975_1_);
                    double d1 = (double)(i1 - p_70975_2_);
                    double d2 = d0 * d0 + d1 * d1;

                    if (d2 <= ((double)b1 - 0.5D) * ((double)b1 - 0.5D))
                    {
                        if (k < b0)
                        {
                            if (d2 <= ((double)(b1 - 1) - 0.5D) * ((double)(b1 - 1) - 0.5D))
                            {
                                this.field_70170_p.func_147449_b(l, k, i1, Blocks.field_150357_h);
                            }
                        }
                        else if (k > b0)
                        {
                            this.field_70170_p.func_147449_b(l, k, i1, Blocks.field_150350_a);
                        }
                        else if (d2 > ((double)(b1 - 1) - 0.5D) * ((double)(b1 - 1) - 0.5D))
                        {
                            this.field_70170_p.func_147449_b(l, k, i1, Blocks.field_150357_h);
                        }
                        else
                        {
                            this.field_70170_p.func_147449_b(l, k, i1, Blocks.field_150384_bq);
                        }
                    }
                }
            }
        }

        this.field_70170_p.func_147449_b(p_70975_1_, b0 + 0, p_70975_2_, Blocks.field_150357_h);
        this.field_70170_p.func_147449_b(p_70975_1_, b0 + 1, p_70975_2_, Blocks.field_150357_h);
        this.field_70170_p.func_147449_b(p_70975_1_, b0 + 2, p_70975_2_, Blocks.field_150357_h);
        this.field_70170_p.func_147449_b(p_70975_1_ - 1, b0 + 2, p_70975_2_, Blocks.field_150478_aa);
        this.field_70170_p.func_147449_b(p_70975_1_ + 1, b0 + 2, p_70975_2_, Blocks.field_150478_aa);
        this.field_70170_p.func_147449_b(p_70975_1_, b0 + 2, p_70975_2_ - 1, Blocks.field_150478_aa);
        this.field_70170_p.func_147449_b(p_70975_1_, b0 + 2, p_70975_2_ + 1, Blocks.field_150478_aa);
        this.field_70170_p.func_147449_b(p_70975_1_, b0 + 3, p_70975_2_, Blocks.field_150357_h);
        this.field_70170_p.func_147449_b(p_70975_1_, b0 + 4, p_70975_2_, Blocks.field_150380_bt);
        BlockEndPortal.field_149948_a = false;
    }

    protected void func_70623_bb() {}

    public Entity[] func_70021_al()
    {
        return this.field_70977_g;
    }

    public boolean func_70067_L()
    {
        return false;
    }

    public World func_82194_d()
    {
        return this.field_70170_p;
    }

    protected String func_70639_aQ()
    {
        return "mob.enderdragon.growl";
    }

    protected String func_70621_aR()
    {
        return "mob.enderdragon.hit";
    }

    protected float func_70599_aP()
    {
        return 5.0F;
    }
}