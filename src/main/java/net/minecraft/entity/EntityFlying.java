package net.minecraft.entity;

import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public abstract class EntityFlying extends EntityLiving
{
    private static final String __OBFID = "CL_00001545";

    public EntityFlying(World p_i1587_1_)
    {
        super(p_i1587_1_);
    }

    protected void func_70069_a(float p_70069_1_) {}

    protected void func_70064_a(double p_70064_1_, boolean p_70064_3_) {}

    public void func_70612_e(float p_70612_1_, float p_70612_2_)
    {
        if (this.func_70090_H())
        {
            this.func_70060_a(p_70612_1_, p_70612_2_, 0.02F);
            this.func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
            this.field_70159_w *= 0.800000011920929D;
            this.field_70181_x *= 0.800000011920929D;
            this.field_70179_y *= 0.800000011920929D;
        }
        else if (this.func_70058_J())
        {
            this.func_70060_a(p_70612_1_, p_70612_2_, 0.02F);
            this.func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
            this.field_70159_w *= 0.5D;
            this.field_70181_x *= 0.5D;
            this.field_70179_y *= 0.5D;
        }
        else
        {
            float f2 = 0.91F;

            if (this.field_70122_E)
            {
                f2 = this.world.func_147439_a(MathHelper.func_76128_c(this.field_70165_t), MathHelper.func_76128_c(this.field_70121_D.field_72338_b) - 1, MathHelper.func_76128_c(this.field_70161_v)).field_149765_K * 0.91F;
            }

            float f3 = 0.16277136F / (f2 * f2 * f2);
            this.func_70060_a(p_70612_1_, p_70612_2_, this.field_70122_E ? 0.1F * f3 : 0.02F);
            f2 = 0.91F;

            if (this.field_70122_E)
            {
                f2 = this.world.func_147439_a(MathHelper.func_76128_c(this.field_70165_t), MathHelper.func_76128_c(this.field_70121_D.field_72338_b) - 1, MathHelper.func_76128_c(this.field_70161_v)).field_149765_K * 0.91F;
            }

            this.func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
            this.field_70159_w *= (double)f2;
            this.field_70181_x *= (double)f2;
            this.field_70179_y *= (double)f2;
        }

        this.field_70722_aY = this.field_70721_aZ;
        double d1 = this.field_70165_t - this.field_70169_q;
        double d0 = this.field_70161_v - this.field_70166_s;
        float f4 = MathHelper.func_76133_a(d1 * d1 + d0 * d0) * 4.0F;

        if (f4 > 1.0F)
        {
            f4 = 1.0F;
        }

        this.field_70721_aZ += (f4 - this.field_70721_aZ) * 0.4F;
        this.field_70754_ba += this.field_70721_aZ;
    }

    public boolean func_70617_f_()
    {
        return false;
    }
}