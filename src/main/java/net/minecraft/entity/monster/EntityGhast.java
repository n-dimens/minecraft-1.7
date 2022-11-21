package net.minecraft.entity.monster;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityGhast extends EntityFlying implements IMob
{
    public int field_70797_a;
    public double field_70795_b;
    public double field_70796_c;
    public double field_70793_d;
    private Entity field_70792_g;
    private int field_70798_h;
    public int field_70794_e;
    public int field_70791_f;
    private int field_92014_j = 1;
    private static final String __OBFID = "CL_00001689";

    public EntityGhast(World p_i1735_1_)
    {
        super(p_i1735_1_);
        this.func_70105_a(4.0F, 4.0F);
        this.field_70178_ae = true;
        this.field_70728_aV = 5;
    }

    @SideOnly(Side.CLIENT)
    public boolean func_110182_bF()
    {
        return this.field_70180_af.func_75683_a(16) != 0;
    }

    public boolean func_70097_a(DamageSource p_70097_1_, float p_70097_2_)
    {
        if (this.func_85032_ar())
        {
            return false;
        }
        else if ("fireball".equals(p_70097_1_.func_76355_l()) && p_70097_1_.func_76346_g() instanceof EntityPlayer)
        {
            super.func_70097_a(p_70097_1_, 1000.0F);
            ((EntityPlayer)p_70097_1_.func_76346_g()).func_71029_a(AchievementList.GHAST);
            return true;
        }
        else
        {
            return super.func_70097_a(p_70097_1_, p_70097_2_);
        }
    }

    protected void func_70088_a()
    {
        super.func_70088_a();
        this.field_70180_af.func_75682_a(16, Byte.valueOf((byte)0));
    }

    protected void func_110147_ax()
    {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(10.0D);
    }

    protected void func_70626_be()
    {
        if (!this.field_70170_p.field_72995_K && this.field_70170_p.field_73013_u == EnumDifficulty.PEACEFUL)
        {
            this.func_70106_y();
        }

        this.func_70623_bb();
        this.field_70794_e = this.field_70791_f;
        double d0 = this.field_70795_b - this.field_70165_t;
        double d1 = this.field_70796_c - this.field_70163_u;
        double d2 = this.field_70793_d - this.field_70161_v;
        double d3 = d0 * d0 + d1 * d1 + d2 * d2;

        if (d3 < 1.0D || d3 > 3600.0D)
        {
            this.field_70795_b = this.field_70165_t + (double)((this.field_70146_Z.nextFloat() * 2.0F - 1.0F) * 16.0F);
            this.field_70796_c = this.field_70163_u + (double)((this.field_70146_Z.nextFloat() * 2.0F - 1.0F) * 16.0F);
            this.field_70793_d = this.field_70161_v + (double)((this.field_70146_Z.nextFloat() * 2.0F - 1.0F) * 16.0F);
        }

        if (this.field_70797_a-- <= 0)
        {
            this.field_70797_a += this.field_70146_Z.nextInt(5) + 2;
            d3 = (double)MathHelper.func_76133_a(d3);

            if (this.func_70790_a(this.field_70795_b, this.field_70796_c, this.field_70793_d, d3))
            {
                this.field_70159_w += d0 / d3 * 0.1D;
                this.field_70181_x += d1 / d3 * 0.1D;
                this.field_70179_y += d2 / d3 * 0.1D;
            }
            else
            {
                this.field_70795_b = this.field_70165_t;
                this.field_70796_c = this.field_70163_u;
                this.field_70793_d = this.field_70161_v;
            }
        }

        if (this.field_70792_g != null && this.field_70792_g.field_70128_L)
        {
            this.field_70792_g = null;
        }

        if (this.field_70792_g == null || this.field_70798_h-- <= 0)
        {
            this.field_70792_g = this.field_70170_p.func_72856_b(this, 100.0D);

            if (this.field_70792_g != null)
            {
                this.field_70798_h = 20;
            }
        }

        double d4 = 64.0D;

        if (this.field_70792_g != null && this.field_70792_g.func_70068_e(this) < d4 * d4)
        {
            double d5 = this.field_70792_g.field_70165_t - this.field_70165_t;
            double d6 = this.field_70792_g.field_70121_D.field_72338_b + (double)(this.field_70792_g.field_70131_O / 2.0F) - (this.field_70163_u + (double)(this.field_70131_O / 2.0F));
            double d7 = this.field_70792_g.field_70161_v - this.field_70161_v;
            this.field_70761_aq = this.field_70177_z = -((float)Math.atan2(d5, d7)) * 180.0F / (float)Math.PI;

            if (this.func_70685_l(this.field_70792_g))
            {
                if (this.field_70791_f == 10)
                {
                    this.field_70170_p.func_72889_a((EntityPlayer)null, 1007, (int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v, 0);
                }

                ++this.field_70791_f;

                if (this.field_70791_f == 20)
                {
                    this.field_70170_p.func_72889_a((EntityPlayer)null, 1008, (int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v, 0);
                    EntityLargeFireball entitylargefireball = new EntityLargeFireball(this.field_70170_p, this, d5, d6, d7);
                    entitylargefireball.field_92057_e = this.field_92014_j;
                    double d8 = 4.0D;
                    Vec3 vec3 = this.func_70676_i(1.0F);
                    entitylargefireball.field_70165_t = this.field_70165_t + vec3.field_72450_a * d8;
                    entitylargefireball.field_70163_u = this.field_70163_u + (double)(this.field_70131_O / 2.0F) + 0.5D;
                    entitylargefireball.field_70161_v = this.field_70161_v + vec3.field_72449_c * d8;
                    this.field_70170_p.func_72838_d(entitylargefireball);
                    this.field_70791_f = -40;
                }
            }
            else if (this.field_70791_f > 0)
            {
                --this.field_70791_f;
            }
        }
        else
        {
            this.field_70761_aq = this.field_70177_z = -((float)Math.atan2(this.field_70159_w, this.field_70179_y)) * 180.0F / (float)Math.PI;

            if (this.field_70791_f > 0)
            {
                --this.field_70791_f;
            }
        }

        if (!this.field_70170_p.field_72995_K)
        {
            byte b1 = this.field_70180_af.func_75683_a(16);
            byte b0 = (byte)(this.field_70791_f > 10 ? 1 : 0);

            if (b1 != b0)
            {
                this.field_70180_af.func_75692_b(16, Byte.valueOf(b0));
            }
        }
    }

    private boolean func_70790_a(double p_70790_1_, double p_70790_3_, double p_70790_5_, double p_70790_7_)
    {
        double d4 = (this.field_70795_b - this.field_70165_t) / p_70790_7_;
        double d5 = (this.field_70796_c - this.field_70163_u) / p_70790_7_;
        double d6 = (this.field_70793_d - this.field_70161_v) / p_70790_7_;
        AxisAlignedBB axisalignedbb = this.field_70121_D.func_72329_c();

        for (int i = 1; (double)i < p_70790_7_; ++i)
        {
            axisalignedbb.func_72317_d(d4, d5, d6);

            if (!this.field_70170_p.func_72945_a(this, axisalignedbb).isEmpty())
            {
                return false;
            }
        }

        return true;
    }

    protected String func_70639_aQ()
    {
        return "mob.ghast.moan";
    }

    protected String func_70621_aR()
    {
        return "mob.ghast.scream";
    }

    protected String func_70673_aS()
    {
        return "mob.ghast.death";
    }

    protected Item droppingItem()
    {
        return Items.field_151016_H;
    }

    protected void func_70628_a(boolean p_70628_1_, int p_70628_2_)
    {
        int j = this.field_70146_Z.nextInt(2) + this.field_70146_Z.nextInt(1 + p_70628_2_);
        int k;

        for (k = 0; k < j; ++k)
        {
            this.func_145779_a(Items.field_151073_bk, 1);
        }

        j = this.field_70146_Z.nextInt(3) + this.field_70146_Z.nextInt(1 + p_70628_2_);

        for (k = 0; k < j; ++k)
        {
            this.func_145779_a(Items.field_151016_H, 1);
        }
    }

    protected float func_70599_aP()
    {
        return 10.0F;
    }

    public boolean func_70601_bi()
    {
        return this.field_70146_Z.nextInt(20) == 0 && super.func_70601_bi() && this.field_70170_p.field_73013_u != EnumDifficulty.PEACEFUL;
    }

    public int func_70641_bl()
    {
        return 1;
    }

    public void func_70014_b(NBTTagCompound p_70014_1_)
    {
        super.func_70014_b(p_70014_1_);
        p_70014_1_.func_74768_a("ExplosionPower", this.field_92014_j);
    }

    public void func_70037_a(NBTTagCompound p_70037_1_)
    {
        super.func_70037_a(p_70037_1_);

        if (p_70037_1_.func_150297_b("ExplosionPower", 99))
        {
            this.field_92014_j = p_70037_1_.func_74762_e("ExplosionPower");
        }
    }
}