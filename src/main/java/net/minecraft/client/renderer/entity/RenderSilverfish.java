package net.minecraft.client.renderer.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelSilverfish;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderSilverfish extends RenderLiving
{
    private static final ResourceLocation field_110882_a = new ResourceLocation("textures/entity/silverfish.png");
    private static final String __OBFID = "CL_00001022";

    public RenderSilverfish()
    {
        super(new ModelSilverfish(), 0.3F);
    }

    protected float func_77037_a(EntitySilverfish p_77037_1_)
    {
        return 180.0F;
    }

    public void func_76986_a(EntitySilverfish p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
    {
        super.func_76986_a((EntityLiving)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }

    protected ResourceLocation func_110775_a(EntitySilverfish p_110775_1_)
    {
        return field_110882_a;
    }

    protected int func_77032_a(EntitySilverfish p_77032_1_, int p_77032_2_, float p_77032_3_)
    {
        return -1;
    }

    public void func_76986_a(EntityLiving p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
    {
        this.func_76986_a((EntitySilverfish)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }

    protected float func_77037_a(EntityLivingBase p_77037_1_)
    {
        return this.func_77037_a((EntitySilverfish)p_77037_1_);
    }

    protected int func_77032_a(EntityLivingBase p_77032_1_, int p_77032_2_, float p_77032_3_)
    {
        return this.func_77032_a((EntitySilverfish)p_77032_1_, p_77032_2_, p_77032_3_);
    }

    public void func_76986_a(EntityLivingBase p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
    {
        this.func_76986_a((EntitySilverfish)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }

    protected ResourceLocation func_110775_a(Entity p_110775_1_)
    {
        return this.func_110775_a((EntitySilverfish)p_110775_1_);
    }

    public void func_76986_a(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
    {
        this.func_76986_a((EntitySilverfish)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }
}