package net.minecraft.client.particle;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.World;

@SideOnly(Side.CLIENT)
public class EntityPortalFX extends EntityFX
{
    private float field_70571_a;
    private double field_70574_aq;
    private double field_70573_ar;
    private double field_70572_as;
    private static final String __OBFID = "CL_00000921";

    public EntityPortalFX(World p_i1222_1_, double p_i1222_2_, double p_i1222_4_, double p_i1222_6_, double p_i1222_8_, double p_i1222_10_, double p_i1222_12_)
    {
        super(p_i1222_1_, p_i1222_2_, p_i1222_4_, p_i1222_6_, p_i1222_8_, p_i1222_10_, p_i1222_12_);
        this.field_70159_w = p_i1222_8_;
        this.field_70181_x = p_i1222_10_;
        this.field_70179_y = p_i1222_12_;
        this.field_70574_aq = this.field_70165_t = p_i1222_2_;
        this.field_70573_ar = this.field_70163_u = p_i1222_4_;
        this.field_70572_as = this.field_70161_v = p_i1222_6_;
        float f = this.field_70146_Z.nextFloat() * 0.6F + 0.4F;
        this.field_70571_a = this.field_70544_f = this.field_70146_Z.nextFloat() * 0.2F + 0.5F;
        this.field_70552_h = this.field_70553_i = this.field_70551_j = 1.0F * f;
        this.field_70553_i *= 0.3F;
        this.field_70552_h *= 0.9F;
        this.field_70547_e = (int)(Math.random() * 10.0D) + 40;
        this.field_70145_X = true;
        this.func_70536_a((int)(Math.random() * 8.0D));
    }

    public void func_70539_a(Tessellator p_70539_1_, float p_70539_2_, float p_70539_3_, float p_70539_4_, float p_70539_5_, float p_70539_6_, float p_70539_7_)
    {
        float f6 = ((float)this.field_70546_d + p_70539_2_) / (float)this.field_70547_e;
        f6 = 1.0F - f6;
        f6 *= f6;
        f6 = 1.0F - f6;
        this.field_70544_f = this.field_70571_a * f6;
        super.func_70539_a(p_70539_1_, p_70539_2_, p_70539_3_, p_70539_4_, p_70539_5_, p_70539_6_, p_70539_7_);
    }

    public int func_70070_b(float p_70070_1_)
    {
        int i = super.func_70070_b(p_70070_1_);
        float f1 = (float)this.field_70546_d / (float)this.field_70547_e;
        f1 *= f1;
        f1 *= f1;
        int j = i & 255;
        int k = i >> 16 & 255;
        k += (int)(f1 * 15.0F * 16.0F);

        if (k > 240)
        {
            k = 240;
        }

        return j | k << 16;
    }

    public float func_70013_c(float p_70013_1_)
    {
        float f1 = super.func_70013_c(p_70013_1_);
        float f2 = (float)this.field_70546_d / (float)this.field_70547_e;
        f2 = f2 * f2 * f2 * f2;
        return f1 * (1.0F - f2) + f2;
    }

    public void func_70071_h_()
    {
        this.field_70169_q = this.field_70165_t;
        this.field_70167_r = this.field_70163_u;
        this.field_70166_s = this.field_70161_v;
        float f = (float)this.field_70546_d / (float)this.field_70547_e;
        float f1 = f;
        f = -f + f * f * 2.0F;
        f = 1.0F - f;
        this.field_70165_t = this.field_70574_aq + this.field_70159_w * (double)f;
        this.field_70163_u = this.field_70573_ar + this.field_70181_x * (double)f + (double)(1.0F - f1);
        this.field_70161_v = this.field_70572_as + this.field_70179_y * (double)f;

        if (this.field_70546_d++ >= this.field_70547_e)
        {
            this.func_70106_y();
        }
    }
}