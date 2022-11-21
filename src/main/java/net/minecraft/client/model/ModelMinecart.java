package net.minecraft.client.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;

@SideOnly(Side.CLIENT)
public class ModelMinecart extends ModelBase
{
    public ModelRenderer[] field_78154_a = new ModelRenderer[7];
    private static final String __OBFID = "CL_00000844";

    public ModelMinecart()
    {
        this.field_78154_a[0] = new ModelRenderer(this, 0, 10);
        this.field_78154_a[1] = new ModelRenderer(this, 0, 0);
        this.field_78154_a[2] = new ModelRenderer(this, 0, 0);
        this.field_78154_a[3] = new ModelRenderer(this, 0, 0);
        this.field_78154_a[4] = new ModelRenderer(this, 0, 0);
        this.field_78154_a[5] = new ModelRenderer(this, 44, 10);
        byte b0 = 20;
        byte b1 = 8;
        byte b2 = 16;
        byte b3 = 4;
        this.field_78154_a[0].func_78790_a((float)(-b0 / 2), (float)(-b2 / 2), -1.0F, b0, b2, 2, 0.0F);
        this.field_78154_a[0].func_78793_a(0.0F, (float)b3, 0.0F);
        this.field_78154_a[5].func_78790_a((float)(-b0 / 2 + 1), (float)(-b2 / 2 + 1), -1.0F, b0 - 2, b2 - 2, 1, 0.0F);
        this.field_78154_a[5].func_78793_a(0.0F, (float)b3, 0.0F);
        this.field_78154_a[1].func_78790_a((float)(-b0 / 2 + 2), (float)(-b1 - 1), -1.0F, b0 - 4, b1, 2, 0.0F);
        this.field_78154_a[1].func_78793_a((float)(-b0 / 2 + 1), (float)b3, 0.0F);
        this.field_78154_a[2].func_78790_a((float)(-b0 / 2 + 2), (float)(-b1 - 1), -1.0F, b0 - 4, b1, 2, 0.0F);
        this.field_78154_a[2].func_78793_a((float)(b0 / 2 - 1), (float)b3, 0.0F);
        this.field_78154_a[3].func_78790_a((float)(-b0 / 2 + 2), (float)(-b1 - 1), -1.0F, b0 - 4, b1, 2, 0.0F);
        this.field_78154_a[3].func_78793_a(0.0F, (float)b3, (float)(-b2 / 2 + 1));
        this.field_78154_a[4].func_78790_a((float)(-b0 / 2 + 2), (float)(-b1 - 1), -1.0F, b0 - 4, b1, 2, 0.0F);
        this.field_78154_a[4].func_78793_a(0.0F, (float)b3, (float)(b2 / 2 - 1));
        this.field_78154_a[0].field_78795_f = ((float)Math.PI / 2F);
        this.field_78154_a[1].field_78796_g = ((float)Math.PI * 3F / 2F);
        this.field_78154_a[2].field_78796_g = ((float)Math.PI / 2F);
        this.field_78154_a[3].field_78796_g = (float)Math.PI;
        this.field_78154_a[5].field_78795_f = -((float)Math.PI / 2F);
    }

    public void func_78088_a(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_)
    {
        this.field_78154_a[5].field_78797_d = 4.0F - p_78088_4_;

        for (int i = 0; i < 6; ++i)
        {
            this.field_78154_a[i].func_78785_a(p_78088_7_);
        }
    }
}