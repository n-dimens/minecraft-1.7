package net.minecraft.client.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMagmaCube;

@SideOnly(Side.CLIENT)
public class ModelMagmaCube extends ModelBase
{
    ModelRenderer[] field_78109_a = new ModelRenderer[8];
    ModelRenderer field_78108_b;
    private static final String __OBFID = "CL_00000842";

    public ModelMagmaCube()
    {
        for (int i = 0; i < this.field_78109_a.length; ++i)
        {
            byte b0 = 0;
            int j = i;

            if (i == 2)
            {
                b0 = 24;
                j = 10;
            }
            else if (i == 3)
            {
                b0 = 24;
                j = 19;
            }

            this.field_78109_a[i] = new ModelRenderer(this, b0, j);
            this.field_78109_a[i].func_78789_a(-4.0F, (float)(16 + i), -4.0F, 8, 1, 8);
        }

        this.field_78108_b = new ModelRenderer(this, 0, 16);
        this.field_78108_b.func_78789_a(-2.0F, 18.0F, -2.0F, 4, 4, 4);
    }

    public void func_78086_a(EntityLivingBase p_78086_1_, float p_78086_2_, float p_78086_3_, float p_78086_4_)
    {
        EntityMagmaCube entitymagmacube = (EntityMagmaCube)p_78086_1_;
        float f3 = entitymagmacube.field_70812_c + (entitymagmacube.field_70811_b - entitymagmacube.field_70812_c) * p_78086_4_;

        if (f3 < 0.0F)
        {
            f3 = 0.0F;
        }

        for (int i = 0; i < this.field_78109_a.length; ++i)
        {
            this.field_78109_a[i].field_78797_d = (float)(-(4 - i)) * f3 * 1.7F;
        }
    }

    public void func_78088_a(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_)
    {
        this.func_78087_a(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, p_78088_1_);
        this.field_78108_b.func_78785_a(p_78088_7_);

        for (int i = 0; i < this.field_78109_a.length; ++i)
        {
            this.field_78109_a[i].func_78785_a(p_78088_7_);
        }
    }
}