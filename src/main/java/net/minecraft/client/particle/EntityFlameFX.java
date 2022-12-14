package net.minecraft.client.particle;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.World;

@SideOnly(Side.CLIENT)
public class EntityFlameFX extends EntityFX
{
    private float field_70562_a;
    private static final String __OBFID = "CL_00000907";

    public EntityFlameFX(World p_i1209_1_, double p_i1209_2_, double p_i1209_4_, double p_i1209_6_, double p_i1209_8_, double p_i1209_10_, double p_i1209_12_)
    {
        super(p_i1209_1_, p_i1209_2_, p_i1209_4_, p_i1209_6_, p_i1209_8_, p_i1209_10_, p_i1209_12_);
        this.field_70159_w = this.field_70159_w * 0.009999999776482582D + p_i1209_8_;
        this.field_70181_x = this.field_70181_x * 0.009999999776482582D + p_i1209_10_;
        this.field_70179_y = this.field_70179_y * 0.009999999776482582D + p_i1209_12_;
        double d6 = p_i1209_2_ + (double)((this.randomizer.nextFloat() - this.randomizer.nextFloat()) * 0.05F);
        d6 = p_i1209_4_ + (double)((this.randomizer.nextFloat() - this.randomizer.nextFloat()) * 0.05F);
        d6 = p_i1209_6_ + (double)((this.randomizer.nextFloat() - this.randomizer.nextFloat()) * 0.05F);
        this.field_70562_a = this.field_70544_f;
        this.field_70552_h = this.field_70553_i = this.field_70551_j = 1.0F;
        this.field_70547_e = (int)(8.0D / (Math.random() * 0.8D + 0.2D)) + 4;
        this.field_70145_X = true;
        this.func_70536_a(48);
    }

    public void func_70539_a(Tessellator p_70539_1_, float p_70539_2_, float p_70539_3_, float p_70539_4_, float p_70539_5_, float p_70539_6_, float p_70539_7_)
    {
        float f6 = ((float)this.field_70546_d + p_70539_2_) / (float)this.field_70547_e;
        this.field_70544_f = this.field_70562_a * (1.0F - f6 * f6 * 0.5F);
        super.func_70539_a(p_70539_1_, p_70539_2_, p_70539_3_, p_70539_4_, p_70539_5_, p_70539_6_, p_70539_7_);
    }

    public int func_70070_b(float p_70070_1_)
    {
        float f1 = ((float)this.field_70546_d + p_70070_1_) / (float)this.field_70547_e;

        if (f1 < 0.0F)
        {
            f1 = 0.0F;
        }

        if (f1 > 1.0F)
        {
            f1 = 1.0F;
        }

        int i = super.func_70070_b(p_70070_1_);
        int j = i & 255;
        int k = i >> 16 & 255;
        j += (int)(f1 * 15.0F * 16.0F);

        if (j > 240)
        {
            j = 240;
        }

        return j | k << 16;
    }

    public float func_70013_c(float p_70013_1_)
    {
        float f1 = ((float)this.field_70546_d + p_70013_1_) / (float)this.field_70547_e;

        if (f1 < 0.0F)
        {
            f1 = 0.0F;
        }

        if (f1 > 1.0F)
        {
            f1 = 1.0F;
        }

        float f2 = super.func_70013_c(p_70013_1_);
        return f2 * f1 + (1.0F - f1);
    }

    public void func_70071_h_()
    {
        this.field_70169_q = this.field_70165_t;
        this.field_70167_r = this.field_70163_u;
        this.field_70166_s = this.field_70161_v;

        if (this.field_70546_d++ >= this.field_70547_e)
        {
            this.func_70106_y();
        }

        this.func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
        this.field_70159_w *= 0.9599999785423279D;
        this.field_70181_x *= 0.9599999785423279D;
        this.field_70179_y *= 0.9599999785423279D;

        if (this.field_70122_E)
        {
            this.field_70159_w *= 0.699999988079071D;
            this.field_70179_y *= 0.699999988079071D;
        }
    }
}