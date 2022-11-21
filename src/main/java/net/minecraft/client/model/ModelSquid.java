package net.minecraft.client.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;

@SideOnly(Side.CLIENT)
public class ModelSquid extends ModelBase
{
    ModelRenderer field_78202_a;
    ModelRenderer[] field_78201_b = new ModelRenderer[8];
    private static final String __OBFID = "CL_00000861";

    public ModelSquid()
    {
        byte b0 = -16;
        this.field_78202_a = new ModelRenderer(this, 0, 0);
        this.field_78202_a.func_78789_a(-6.0F, -8.0F, -6.0F, 12, 16, 12);
        this.field_78202_a.field_78797_d += (float)(24 + b0);

        for (int i = 0; i < this.field_78201_b.length; ++i)
        {
            this.field_78201_b[i] = new ModelRenderer(this, 48, 0);
            double d0 = (double)i * Math.PI * 2.0D / (double)this.field_78201_b.length;
            float f = (float)Math.cos(d0) * 5.0F;
            float f1 = (float)Math.sin(d0) * 5.0F;
            this.field_78201_b[i].func_78789_a(-1.0F, 0.0F, -1.0F, 2, 18, 2);
            this.field_78201_b[i].field_78800_c = f;
            this.field_78201_b[i].field_78798_e = f1;
            this.field_78201_b[i].field_78797_d = (float)(31 + b0);
            d0 = (double)i * Math.PI * -2.0D / (double)this.field_78201_b.length + (Math.PI / 2D);
            this.field_78201_b[i].field_78796_g = (float)d0;
        }
    }

    public void func_78087_a(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_)
    {
        ModelRenderer[] amodelrenderer = this.field_78201_b;
        int i = amodelrenderer.length;

        for (int j = 0; j < i; ++j)
        {
            ModelRenderer modelrenderer = amodelrenderer[j];
            modelrenderer.field_78795_f = p_78087_3_;
        }
    }

    public void func_78088_a(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_)
    {
        this.func_78087_a(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, p_78088_1_);
        this.field_78202_a.func_78785_a(p_78088_7_);

        for (int i = 0; i < this.field_78201_b.length; ++i)
        {
            this.field_78201_b[i].func_78785_a(p_78088_7_);
        }
    }
}