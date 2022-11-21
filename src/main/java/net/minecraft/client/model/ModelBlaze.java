package net.minecraft.client.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

@SideOnly(Side.CLIENT)
public class ModelBlaze extends ModelBase
{
    private ModelRenderer[] field_78106_a = new ModelRenderer[12];
    private ModelRenderer field_78105_b;
    private static final String __OBFID = "CL_00000831";

    public ModelBlaze()
    {
        for (int i = 0; i < this.field_78106_a.length; ++i)
        {
            this.field_78106_a[i] = new ModelRenderer(this, 0, 16);
            this.field_78106_a[i].func_78789_a(0.0F, 0.0F, 0.0F, 2, 8, 2);
        }

        this.field_78105_b = new ModelRenderer(this, 0, 0);
        this.field_78105_b.func_78789_a(-4.0F, -4.0F, -4.0F, 8, 8, 8);
    }

    public int func_78104_a()
    {
        return 8;
    }

    public void func_78088_a(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_)
    {
        this.func_78087_a(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, p_78088_1_);
        this.field_78105_b.func_78785_a(p_78088_7_);

        for (int i = 0; i < this.field_78106_a.length; ++i)
        {
            this.field_78106_a[i].func_78785_a(p_78088_7_);
        }
    }

    public void func_78087_a(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_)
    {
        float f6 = p_78087_3_ * (float)Math.PI * -0.1F;
        int i;

        for (i = 0; i < 4; ++i)
        {
            this.field_78106_a[i].field_78797_d = -2.0F + MathHelper.func_76134_b(((float)(i * 2) + p_78087_3_) * 0.25F);
            this.field_78106_a[i].field_78800_c = MathHelper.func_76134_b(f6) * 9.0F;
            this.field_78106_a[i].field_78798_e = MathHelper.func_76126_a(f6) * 9.0F;
            ++f6;
        }

        f6 = ((float)Math.PI / 4F) + p_78087_3_ * (float)Math.PI * 0.03F;

        for (i = 4; i < 8; ++i)
        {
            this.field_78106_a[i].field_78797_d = 2.0F + MathHelper.func_76134_b(((float)(i * 2) + p_78087_3_) * 0.25F);
            this.field_78106_a[i].field_78800_c = MathHelper.func_76134_b(f6) * 7.0F;
            this.field_78106_a[i].field_78798_e = MathHelper.func_76126_a(f6) * 7.0F;
            ++f6;
        }

        f6 = 0.47123894F + p_78087_3_ * (float)Math.PI * -0.05F;

        for (i = 8; i < 12; ++i)
        {
            this.field_78106_a[i].field_78797_d = 11.0F + MathHelper.func_76134_b(((float)i * 1.5F + p_78087_3_) * 0.5F);
            this.field_78106_a[i].field_78800_c = MathHelper.func_76134_b(f6) * 5.0F;
            this.field_78106_a[i].field_78798_e = MathHelper.func_76126_a(f6) * 5.0F;
            ++f6;
        }

        this.field_78105_b.field_78796_g = p_78087_4_ / (180F / (float)Math.PI);
        this.field_78105_b.field_78795_f = p_78087_5_ / (180F / (float)Math.PI);
    }
}