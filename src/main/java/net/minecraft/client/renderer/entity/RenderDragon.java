package net.minecraft.client.renderer.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.client.model.ModelDragon;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderDragon extends RenderLiving
{
    private static final ResourceLocation field_110842_f = new ResourceLocation("textures/entity/enderdragon/dragon_exploding.png");
    private static final ResourceLocation field_110843_g = new ResourceLocation("textures/entity/endercrystal/endercrystal_beam.png");
    private static final ResourceLocation field_110845_h = new ResourceLocation("textures/entity/enderdragon/dragon_eyes.png");
    private static final ResourceLocation field_110844_k = new ResourceLocation("textures/entity/enderdragon/dragon.png");
    protected ModelDragon field_77084_b;
    private static final String __OBFID = "CL_00000988";

    public RenderDragon()
    {
        super(new ModelDragon(0.0F), 0.5F);
        this.field_77084_b = (ModelDragon)this.field_77045_g;
        this.func_77042_a(this.field_77045_g);
    }

    protected void func_77043_a(EntityDragon p_77043_1_, float p_77043_2_, float p_77043_3_, float p_77043_4_)
    {
        float f3 = (float)p_77043_1_.func_70974_a(7, p_77043_4_)[0];
        float f4 = (float)(p_77043_1_.func_70974_a(5, p_77043_4_)[1] - p_77043_1_.func_70974_a(10, p_77043_4_)[1]);
        GL11.glRotatef(-f3, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(f4 * 10.0F, 1.0F, 0.0F, 0.0F);
        GL11.glTranslatef(0.0F, 0.0F, 1.0F);

        if (p_77043_1_.field_70725_aQ > 0)
        {
            float f5 = ((float)p_77043_1_.field_70725_aQ + p_77043_4_ - 1.0F) / 20.0F * 1.6F;
            f5 = MathHelper.func_76129_c(f5);

            if (f5 > 1.0F)
            {
                f5 = 1.0F;
            }

            GL11.glRotatef(f5 * this.func_77037_a(p_77043_1_), 0.0F, 0.0F, 1.0F);
        }
    }

    protected void func_77036_a(EntityDragon p_77036_1_, float p_77036_2_, float p_77036_3_, float p_77036_4_, float p_77036_5_, float p_77036_6_, float p_77036_7_)
    {
        if (p_77036_1_.field_70995_bG > 0)
        {
            float f6 = (float)p_77036_1_.field_70995_bG / 200.0F;
            GL11.glDepthFunc(GL11.GL_LEQUAL);
            GL11.glEnable(GL11.GL_ALPHA_TEST);
            GL11.glAlphaFunc(GL11.GL_GREATER, f6);
            this.func_110776_a(field_110842_f);
            this.field_77045_g.func_78088_a(p_77036_1_, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, p_77036_7_);
            GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
            GL11.glDepthFunc(GL11.GL_EQUAL);
        }

        this.func_110777_b(p_77036_1_);
        this.field_77045_g.func_78088_a(p_77036_1_, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, p_77036_7_);

        if (p_77036_1_.field_70737_aN > 0)
        {
            GL11.glDepthFunc(GL11.GL_EQUAL);
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GL11.glColor4f(1.0F, 0.0F, 0.0F, 0.5F);
            this.field_77045_g.func_78088_a(p_77036_1_, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, p_77036_7_);
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glDepthFunc(GL11.GL_LEQUAL);
        }
    }

    public void func_76986_a(EntityDragon p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
    {
        BossStatus.func_82824_a(p_76986_1_, false);
        super.func_76986_a((EntityLiving)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);

        if (p_76986_1_.field_70992_bH != null)
        {
            float f2 = (float)p_76986_1_.field_70992_bH.field_70261_a + p_76986_9_;
            float f3 = MathHelper.func_76126_a(f2 * 0.2F) / 2.0F + 0.5F;
            f3 = (f3 * f3 + f3) * 0.2F;
            float f4 = (float)(p_76986_1_.field_70992_bH.field_70165_t - p_76986_1_.field_70165_t - (p_76986_1_.field_70169_q - p_76986_1_.field_70165_t) * (double)(1.0F - p_76986_9_));
            float f5 = (float)((double)f3 + p_76986_1_.field_70992_bH.field_70163_u - 1.0D - p_76986_1_.field_70163_u - (p_76986_1_.field_70167_r - p_76986_1_.field_70163_u) * (double)(1.0F - p_76986_9_));
            float f6 = (float)(p_76986_1_.field_70992_bH.field_70161_v - p_76986_1_.field_70161_v - (p_76986_1_.field_70166_s - p_76986_1_.field_70161_v) * (double)(1.0F - p_76986_9_));
            float f7 = MathHelper.func_76129_c(f4 * f4 + f6 * f6);
            float f8 = MathHelper.func_76129_c(f4 * f4 + f5 * f5 + f6 * f6);
            GL11.glPushMatrix();
            GL11.glTranslatef((float)p_76986_2_, (float)p_76986_4_ + 2.0F, (float)p_76986_6_);
            GL11.glRotatef((float)(-Math.atan2((double)f6, (double)f4)) * 180.0F / (float)Math.PI - 90.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef((float)(-Math.atan2((double)f7, (double)f5)) * 180.0F / (float)Math.PI - 90.0F, 1.0F, 0.0F, 0.0F);
            Tessellator tessellator = Tessellator.field_78398_a;
            RenderHelper.func_74518_a();
            GL11.glDisable(GL11.GL_CULL_FACE);
            this.func_110776_a(field_110843_g);
            GL11.glShadeModel(GL11.GL_SMOOTH);
            float f9 = 0.0F - ((float)p_76986_1_.field_70173_aa + p_76986_9_) * 0.01F;
            float f10 = MathHelper.func_76129_c(f4 * f4 + f5 * f5 + f6 * f6) / 32.0F - ((float)p_76986_1_.field_70173_aa + p_76986_9_) * 0.01F;
            tessellator.func_78371_b(5);
            byte b0 = 8;

            for (int i = 0; i <= b0; ++i)
            {
                float f11 = MathHelper.func_76126_a((float)(i % b0) * (float)Math.PI * 2.0F / (float)b0) * 0.75F;
                float f12 = MathHelper.func_76134_b((float)(i % b0) * (float)Math.PI * 2.0F / (float)b0) * 0.75F;
                float f13 = (float)(i % b0) * 1.0F / (float)b0;
                tessellator.func_78378_d(0);
                tessellator.func_78374_a((double)(f11 * 0.2F), (double)(f12 * 0.2F), 0.0D, (double)f13, (double)f10);
                tessellator.func_78378_d(16777215);
                tessellator.func_78374_a((double)f11, (double)f12, (double)f8, (double)f13, (double)f9);
            }

            tessellator.func_78381_a();
            GL11.glEnable(GL11.GL_CULL_FACE);
            GL11.glShadeModel(GL11.GL_FLAT);
            RenderHelper.func_74519_b();
            GL11.glPopMatrix();
        }
    }

    protected ResourceLocation func_110775_a(EntityDragon p_110775_1_)
    {
        return field_110844_k;
    }

    protected void func_77029_c(EntityDragon p_77029_1_, float p_77029_2_)
    {
        super.func_77029_c(p_77029_1_, p_77029_2_);
        Tessellator tessellator = Tessellator.field_78398_a;

        if (p_77029_1_.field_70995_bG > 0)
        {
            RenderHelper.func_74518_a();
            float f1 = ((float)p_77029_1_.field_70995_bG + p_77029_2_) / 200.0F;
            float f2 = 0.0F;

            if (f1 > 0.8F)
            {
                f2 = (f1 - 0.8F) / 0.2F;
            }

            Random random = new Random(432L);
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glShadeModel(GL11.GL_SMOOTH);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
            GL11.glDisable(GL11.GL_ALPHA_TEST);
            GL11.glEnable(GL11.GL_CULL_FACE);
            GL11.glDepthMask(false);
            GL11.glPushMatrix();
            GL11.glTranslatef(0.0F, -1.0F, -2.0F);

            for (int i = 0; (float)i < (f1 + f1 * f1) / 2.0F * 60.0F; ++i)
            {
                GL11.glRotatef(random.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(random.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(random.nextFloat() * 360.0F, 0.0F, 0.0F, 1.0F);
                GL11.glRotatef(random.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(random.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(random.nextFloat() * 360.0F + f1 * 90.0F, 0.0F, 0.0F, 1.0F);
                tessellator.func_78371_b(6);
                float f3 = random.nextFloat() * 20.0F + 5.0F + f2 * 10.0F;
                float f4 = random.nextFloat() * 2.0F + 1.0F + f2 * 2.0F;
                tessellator.func_78384_a(16777215, (int)(255.0F * (1.0F - f2)));
                tessellator.func_78377_a(0.0D, 0.0D, 0.0D);
                tessellator.func_78384_a(16711935, 0);
                tessellator.func_78377_a(-0.866D * (double)f4, (double)f3, (double)(-0.5F * f4));
                tessellator.func_78377_a(0.866D * (double)f4, (double)f3, (double)(-0.5F * f4));
                tessellator.func_78377_a(0.0D, (double)f3, (double)(1.0F * f4));
                tessellator.func_78377_a(-0.866D * (double)f4, (double)f3, (double)(-0.5F * f4));
                tessellator.func_78381_a();
            }

            GL11.glPopMatrix();
            GL11.glDepthMask(true);
            GL11.glDisable(GL11.GL_CULL_FACE);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glShadeModel(GL11.GL_FLAT);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glEnable(GL11.GL_ALPHA_TEST);
            RenderHelper.func_74519_b();
        }
    }

    protected int func_77032_a(EntityDragon p_77032_1_, int p_77032_2_, float p_77032_3_)
    {
        if (p_77032_2_ == 1)
        {
            GL11.glDepthFunc(GL11.GL_LEQUAL);
        }

        if (p_77032_2_ != 0)
        {
            return -1;
        }
        else
        {
            this.func_110776_a(field_110845_h);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glDisable(GL11.GL_ALPHA_TEST);
            GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glDepthFunc(GL11.GL_EQUAL);
            char c0 = 61680;
            int j = c0 % 65536;
            int k = c0 / 65536;
            OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, (float)j / 1.0F, (float)k / 1.0F);
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            return 1;
        }
    }

    public void func_76986_a(EntityLiving p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
    {
        this.func_76986_a((EntityDragon)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }

    protected int func_77032_a(EntityLivingBase p_77032_1_, int p_77032_2_, float p_77032_3_)
    {
        return this.func_77032_a((EntityDragon)p_77032_1_, p_77032_2_, p_77032_3_);
    }

    protected void func_77029_c(EntityLivingBase p_77029_1_, float p_77029_2_)
    {
        this.func_77029_c((EntityDragon)p_77029_1_, p_77029_2_);
    }

    protected void func_77043_a(EntityLivingBase p_77043_1_, float p_77043_2_, float p_77043_3_, float p_77043_4_)
    {
        this.func_77043_a((EntityDragon)p_77043_1_, p_77043_2_, p_77043_3_, p_77043_4_);
    }

    protected void func_77036_a(EntityLivingBase p_77036_1_, float p_77036_2_, float p_77036_3_, float p_77036_4_, float p_77036_5_, float p_77036_6_, float p_77036_7_)
    {
        this.func_77036_a((EntityDragon)p_77036_1_, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, p_77036_7_);
    }

    public void func_76986_a(EntityLivingBase p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
    {
        this.func_76986_a((EntityDragon)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }

    protected ResourceLocation func_110775_a(Entity p_110775_1_)
    {
        return this.func_110775_a((EntityDragon)p_110775_1_);
    }

    public void func_76986_a(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
    {
        this.func_76986_a((EntityDragon)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }
}