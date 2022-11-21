package net.minecraft.client.renderer.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderSquid extends RenderLiving
{
    private static final ResourceLocation field_110901_a = new ResourceLocation("textures/entity/squid.png");
    private static final String __OBFID = "CL_00001028";

    public RenderSquid(ModelBase p_i1268_1_, float p_i1268_2_)
    {
        super(p_i1268_1_, p_i1268_2_);
    }

    public void func_76986_a(EntitySquid p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
    {
        super.func_76986_a((EntityLiving)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }

    protected ResourceLocation func_110775_a(EntitySquid p_110775_1_)
    {
        return field_110901_a;
    }

    protected void func_77043_a(EntitySquid p_77043_1_, float p_77043_2_, float p_77043_3_, float p_77043_4_)
    {
        float f3 = p_77043_1_.field_70862_e + (p_77043_1_.field_70861_d - p_77043_1_.field_70862_e) * p_77043_4_;
        float f4 = p_77043_1_.field_70860_g + (p_77043_1_.field_70859_f - p_77043_1_.field_70860_g) * p_77043_4_;
        GL11.glTranslatef(0.0F, 0.5F, 0.0F);
        GL11.glRotatef(180.0F - p_77043_3_, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(f3, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(f4, 0.0F, 1.0F, 0.0F);
        GL11.glTranslatef(0.0F, -1.2F, 0.0F);
    }

    protected float func_77044_a(EntitySquid p_77044_1_, float p_77044_2_)
    {
        return p_77044_1_.field_70865_by + (p_77044_1_.field_70866_j - p_77044_1_.field_70865_by) * p_77044_2_;
    }

    public void func_76986_a(EntityLiving p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
    {
        this.func_76986_a((EntitySquid)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }

    protected float func_77044_a(EntityLivingBase p_77044_1_, float p_77044_2_)
    {
        return this.func_77044_a((EntitySquid)p_77044_1_, p_77044_2_);
    }

    protected void func_77043_a(EntityLivingBase p_77043_1_, float p_77043_2_, float p_77043_3_, float p_77043_4_)
    {
        this.func_77043_a((EntitySquid)p_77043_1_, p_77043_2_, p_77043_3_, p_77043_4_);
    }

    public void func_76986_a(EntityLivingBase p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
    {
        this.func_76986_a((EntitySquid)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }

    protected ResourceLocation func_110775_a(Entity p_110775_1_)
    {
        return this.func_110775_a((EntitySquid)p_110775_1_);
    }

    public void func_76986_a(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
    {
        this.func_76986_a((EntitySquid)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }
}