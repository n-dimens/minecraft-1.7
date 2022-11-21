package net.minecraft.client.renderer.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelIronGolem;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

@SideOnly(Side.CLIENT)
public class RenderIronGolem extends RenderLiving
{
    private static final ResourceLocation field_110899_a = new ResourceLocation("textures/entity/iron_golem.png");
    private final ModelIronGolem field_77050_a;
    private static final String __OBFID = "CL_00001031";

    public RenderIronGolem()
    {
        super(new ModelIronGolem(), 0.5F);
        this.field_77050_a = (ModelIronGolem)this.field_77045_g;
    }

    public void func_76986_a(EntityIronGolem p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
    {
        super.func_76986_a((EntityLiving)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }

    protected ResourceLocation func_110775_a(EntityIronGolem p_110775_1_)
    {
        return field_110899_a;
    }

    protected void func_77043_a(EntityIronGolem p_77043_1_, float p_77043_2_, float p_77043_3_, float p_77043_4_)
    {
        super.func_77043_a(p_77043_1_, p_77043_2_, p_77043_3_, p_77043_4_);

        if ((double)p_77043_1_.field_70721_aZ >= 0.01D)
        {
            float f3 = 13.0F;
            float f4 = p_77043_1_.field_70754_ba - p_77043_1_.field_70721_aZ * (1.0F - p_77043_4_) + 6.0F;
            float f5 = (Math.abs(f4 % f3 - f3 * 0.5F) - f3 * 0.25F) / (f3 * 0.25F);
            GL11.glRotatef(6.5F * f5, 0.0F, 0.0F, 1.0F);
        }
    }

    protected void func_77029_c(EntityIronGolem p_77029_1_, float p_77029_2_)
    {
        super.func_77029_c(p_77029_1_, p_77029_2_);

        if (p_77029_1_.func_70853_p() != 0)
        {
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
            GL11.glPushMatrix();
            GL11.glRotatef(5.0F + 180.0F * this.field_77050_a.field_78177_c.field_78795_f / (float)Math.PI, 1.0F, 0.0F, 0.0F);
            GL11.glTranslatef(-0.6875F, 1.25F, -0.9375F);
            GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
            float f1 = 0.8F;
            GL11.glScalef(f1, -f1, f1);
            int i = p_77029_1_.func_70070_b(p_77029_2_);
            int j = i % 65536;
            int k = i / 65536;
            OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, (float)j / 1.0F, (float)k / 1.0F);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.func_110776_a(TextureMap.field_110575_b);
            this.field_147909_c.func_147800_a(Blocks.field_150328_O, 0, 1.0F);
            GL11.glPopMatrix();
            GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        }
    }

    public void func_76986_a(EntityLiving p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
    {
        this.func_76986_a((EntityIronGolem)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }

    protected void func_77029_c(EntityLivingBase p_77029_1_, float p_77029_2_)
    {
        this.func_77029_c((EntityIronGolem)p_77029_1_, p_77029_2_);
    }

    protected void func_77043_a(EntityLivingBase p_77043_1_, float p_77043_2_, float p_77043_3_, float p_77043_4_)
    {
        this.func_77043_a((EntityIronGolem)p_77043_1_, p_77043_2_, p_77043_3_, p_77043_4_);
    }

    public void func_76986_a(EntityLivingBase p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
    {
        this.func_76986_a((EntityIronGolem)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }

    protected ResourceLocation func_110775_a(Entity p_110775_1_)
    {
        return this.func_110775_a((EntityIronGolem)p_110775_1_);
    }

    public void func_76986_a(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
    {
        this.func_76986_a((EntityIronGolem)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }
}