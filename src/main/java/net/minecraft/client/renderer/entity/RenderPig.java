package net.minecraft.client.renderer.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderPig extends RenderLiving
{
    private static final ResourceLocation field_110888_a = new ResourceLocation("textures/entity/pig/pig_saddle.png");
    private static final ResourceLocation field_110887_f = new ResourceLocation("textures/entity/pig/pig.png");
    private static final String __OBFID = "CL_00001019";

    public RenderPig(ModelBase p_i1265_1_, ModelBase p_i1265_2_, float p_i1265_3_)
    {
        super(p_i1265_1_, p_i1265_3_);
        this.func_77042_a(p_i1265_2_);
    }

    protected int func_77032_a(EntityPig p_77032_1_, int p_77032_2_, float p_77032_3_)
    {
        if (p_77032_2_ == 0 && p_77032_1_.func_70901_n())
        {
            this.func_110776_a(field_110888_a);
            return 1;
        }
        else
        {
            return -1;
        }
    }

    protected ResourceLocation func_110775_a(EntityPig p_110775_1_)
    {
        return field_110887_f;
    }

    protected int func_77032_a(EntityLivingBase p_77032_1_, int p_77032_2_, float p_77032_3_)
    {
        return this.func_77032_a((EntityPig)p_77032_1_, p_77032_2_, p_77032_3_);
    }

    protected ResourceLocation func_110775_a(Entity p_110775_1_)
    {
        return this.func_110775_a((EntityPig)p_110775_1_);
    }
}