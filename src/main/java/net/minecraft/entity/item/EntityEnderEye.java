package net.minecraft.entity.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityEnderEye extends Entity
{
    private double field_70224_b;
    private double field_70225_c;
    private double field_70222_d;
    private int field_70223_e;
    private boolean field_70221_f;
    private static final String __OBFID = "CL_00001716";

    public EntityEnderEye(World p_i1757_1_)
    {
        super(p_i1757_1_);
        this.func_70105_a(0.25F, 0.25F);
    }

    protected void func_70088_a() {}

    @SideOnly(Side.CLIENT)
    public boolean func_70112_a(double p_70112_1_)
    {
        double d1 = this.field_70121_D.func_72320_b() * 4.0D;
        d1 *= 64.0D;
        return p_70112_1_ < d1 * d1;
    }

    public EntityEnderEye(World p_i1758_1_, double p_i1758_2_, double p_i1758_4_, double p_i1758_6_)
    {
        super(p_i1758_1_);
        this.field_70223_e = 0;
        this.func_70105_a(0.25F, 0.25F);
        this.func_70107_b(p_i1758_2_, p_i1758_4_, p_i1758_6_);
        this.field_70129_M = 0.0F;
    }

    public void func_70220_a(double p_70220_1_, int p_70220_3_, double p_70220_4_)
    {
        double d2 = p_70220_1_ - this.field_70165_t;
        double d3 = p_70220_4_ - this.field_70161_v;
        float f = MathHelper.func_76133_a(d2 * d2 + d3 * d3);

        if (f > 12.0F)
        {
            this.field_70224_b = this.field_70165_t + d2 / (double)f * 12.0D;
            this.field_70222_d = this.field_70161_v + d3 / (double)f * 12.0D;
            this.field_70225_c = this.field_70163_u + 8.0D;
        }
        else
        {
            this.field_70224_b = p_70220_1_;
            this.field_70225_c = (double)p_70220_3_;
            this.field_70222_d = p_70220_4_;
        }

        this.field_70223_e = 0;
        this.field_70221_f = this.field_70146_Z.nextInt(5) > 0;
    }

    @SideOnly(Side.CLIENT)
    public void func_70016_h(double p_70016_1_, double p_70016_3_, double p_70016_5_)
    {
        this.field_70159_w = p_70016_1_;
        this.field_70181_x = p_70016_3_;
        this.field_70179_y = p_70016_5_;

        if (this.field_70127_C == 0.0F && this.field_70126_B == 0.0F)
        {
            float f = MathHelper.func_76133_a(p_70016_1_ * p_70016_1_ + p_70016_5_ * p_70016_5_);
            this.field_70126_B = this.field_70177_z = (float)(Math.atan2(p_70016_1_, p_70016_5_) * 180.0D / Math.PI);
            this.field_70127_C = this.field_70125_A = (float)(Math.atan2(p_70016_3_, (double)f) * 180.0D / Math.PI);
        }
    }

    public void func_70071_h_()
    {
        this.field_70142_S = this.field_70165_t;
        this.field_70137_T = this.field_70163_u;
        this.field_70136_U = this.field_70161_v;
        super.func_70071_h_();
        this.field_70165_t += this.field_70159_w;
        this.field_70163_u += this.field_70181_x;
        this.field_70161_v += this.field_70179_y;
        float f = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
        this.field_70177_z = (float)(Math.atan2(this.field_70159_w, this.field_70179_y) * 180.0D / Math.PI);

        for (this.field_70125_A = (float)(Math.atan2(this.field_70181_x, (double)f) * 180.0D / Math.PI); this.field_70125_A - this.field_70127_C < -180.0F; this.field_70127_C -= 360.0F)
        {
            ;
        }

        while (this.field_70125_A - this.field_70127_C >= 180.0F)
        {
            this.field_70127_C += 360.0F;
        }

        while (this.field_70177_z - this.field_70126_B < -180.0F)
        {
            this.field_70126_B -= 360.0F;
        }

        while (this.field_70177_z - this.field_70126_B >= 180.0F)
        {
            this.field_70126_B += 360.0F;
        }

        this.field_70125_A = this.field_70127_C + (this.field_70125_A - this.field_70127_C) * 0.2F;
        this.field_70177_z = this.field_70126_B + (this.field_70177_z - this.field_70126_B) * 0.2F;

        if (!this.world.field_72995_K)
        {
            double d0 = this.field_70224_b - this.field_70165_t;
            double d1 = this.field_70222_d - this.field_70161_v;
            float f1 = (float)Math.sqrt(d0 * d0 + d1 * d1);
            float f2 = (float)Math.atan2(d1, d0);
            double d2 = (double)f + (double)(f1 - f) * 0.0025D;

            if (f1 < 1.0F)
            {
                d2 *= 0.8D;
                this.field_70181_x *= 0.8D;
            }

            this.field_70159_w = Math.cos((double)f2) * d2;
            this.field_70179_y = Math.sin((double)f2) * d2;

            if (this.field_70163_u < this.field_70225_c)
            {
                this.field_70181_x += (1.0D - this.field_70181_x) * 0.014999999664723873D;
            }
            else
            {
                this.field_70181_x += (-1.0D - this.field_70181_x) * 0.014999999664723873D;
            }
        }

        float f3 = 0.25F;

        if (this.func_70090_H())
        {
            for (int i = 0; i < 4; ++i)
            {
                this.world.func_72869_a("bubble", this.field_70165_t - this.field_70159_w * (double)f3, this.field_70163_u - this.field_70181_x * (double)f3, this.field_70161_v - this.field_70179_y * (double)f3, this.field_70159_w, this.field_70181_x, this.field_70179_y);
            }
        }
        else
        {
            this.world.func_72869_a("portal", this.field_70165_t - this.field_70159_w * (double)f3 + this.field_70146_Z.nextDouble() * 0.6D - 0.3D, this.field_70163_u - this.field_70181_x * (double)f3 - 0.5D, this.field_70161_v - this.field_70179_y * (double)f3 + this.field_70146_Z.nextDouble() * 0.6D - 0.3D, this.field_70159_w, this.field_70181_x, this.field_70179_y);
        }

        if (!this.world.field_72995_K)
        {
            this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
            ++this.field_70223_e;

            if (this.field_70223_e > 80 && !this.world.field_72995_K)
            {
                this.func_70106_y();

                if (this.field_70221_f)
                {
                    this.world.func_72838_d(new EntityItem(this.world, this.field_70165_t, this.field_70163_u, this.field_70161_v, new ItemStack(Items.ENDER_EYE)));
                }
                else
                {
                    this.world.func_72926_e(2003, (int)Math.round(this.field_70165_t), (int)Math.round(this.field_70163_u), (int)Math.round(this.field_70161_v), 0);
                }
            }
        }
    }

    public void func_70014_b(NBTTagCompound p_70014_1_) {}

    public void func_70037_a(NBTTagCompound p_70037_1_) {}

    @SideOnly(Side.CLIENT)
    public float func_70053_R()
    {
        return 0.0F;
    }

    public float func_70013_c(float p_70013_1_)
    {
        return 1.0F;
    }

    @SideOnly(Side.CLIENT)
    public int func_70070_b(float p_70070_1_)
    {
        return 15728880;
    }

    public boolean func_70075_an()
    {
        return false;
    }
}