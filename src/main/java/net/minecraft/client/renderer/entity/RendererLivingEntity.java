package net.minecraft.client.renderer.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

@SideOnly(Side.CLIENT)
public abstract class RendererLivingEntity extends Render
{
    private static final Logger field_147923_a = LogManager.getLogger();
    private static final ResourceLocation field_110814_a = new ResourceLocation("textures/misc/enchanted_item_glint.png");
    protected ModelBase field_77045_g;
    protected ModelBase field_77046_h;
    private static final String __OBFID = "CL_00001012";

    public RendererLivingEntity(ModelBase p_i1261_1_, float p_i1261_2_)
    {
        this.field_77045_g = p_i1261_1_;
        this.field_76989_e = p_i1261_2_;
    }

    public void func_77042_a(ModelBase p_77042_1_)
    {
        this.field_77046_h = p_77042_1_;
    }

    private float func_77034_a(float p_77034_1_, float p_77034_2_, float p_77034_3_)
    {
        float f3;

        for (f3 = p_77034_2_ - p_77034_1_; f3 < -180.0F; f3 += 360.0F)
        {
            ;
        }

        while (f3 >= 180.0F)
        {
            f3 -= 360.0F;
        }

        return p_77034_1_ + p_77034_3_ * f3;
    }

    public void func_76986_a(EntityLivingBase p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
    {
        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_CULL_FACE);
        this.field_77045_g.field_78095_p = this.func_77040_d(p_76986_1_, p_76986_9_);

        if (this.field_77046_h != null)
        {
            this.field_77046_h.field_78095_p = this.field_77045_g.field_78095_p;
        }

        this.field_77045_g.field_78093_q = p_76986_1_.func_70115_ae();

        if (this.field_77046_h != null)
        {
            this.field_77046_h.field_78093_q = this.field_77045_g.field_78093_q;
        }

        this.field_77045_g.field_78091_s = p_76986_1_.func_70631_g_();

        if (this.field_77046_h != null)
        {
            this.field_77046_h.field_78091_s = this.field_77045_g.field_78091_s;
        }

        try
        {
            float f2 = this.func_77034_a(p_76986_1_.field_70760_ar, p_76986_1_.field_70761_aq, p_76986_9_);
            float f3 = this.func_77034_a(p_76986_1_.field_70758_at, p_76986_1_.field_70759_as, p_76986_9_);
            float f4;

            if (p_76986_1_.func_70115_ae() && p_76986_1_.field_70154_o instanceof EntityLivingBase)
            {
                EntityLivingBase entitylivingbase1 = (EntityLivingBase)p_76986_1_.field_70154_o;
                f2 = this.func_77034_a(entitylivingbase1.field_70760_ar, entitylivingbase1.field_70761_aq, p_76986_9_);
                f4 = MathHelper.func_76142_g(f3 - f2);

                if (f4 < -85.0F)
                {
                    f4 = -85.0F;
                }

                if (f4 >= 85.0F)
                {
                    f4 = 85.0F;
                }

                f2 = f3 - f4;

                if (f4 * f4 > 2500.0F)
                {
                    f2 += f4 * 0.2F;
                }
            }

            float f13 = p_76986_1_.field_70127_C + (p_76986_1_.field_70125_A - p_76986_1_.field_70127_C) * p_76986_9_;
            this.func_77039_a(p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_);
            f4 = this.func_77044_a(p_76986_1_, p_76986_9_);
            this.func_77043_a(p_76986_1_, f4, f2, p_76986_9_);
            float f5 = 0.0625F;
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
            GL11.glScalef(-1.0F, -1.0F, 1.0F);
            this.func_77041_b(p_76986_1_, p_76986_9_);
            GL11.glTranslatef(0.0F, -24.0F * f5 - 0.0078125F, 0.0F);
            float f6 = p_76986_1_.field_70722_aY + (p_76986_1_.field_70721_aZ - p_76986_1_.field_70722_aY) * p_76986_9_;
            float f7 = p_76986_1_.field_70754_ba - p_76986_1_.field_70721_aZ * (1.0F - p_76986_9_);

            if (p_76986_1_.func_70631_g_())
            {
                f7 *= 3.0F;
            }

            if (f6 > 1.0F)
            {
                f6 = 1.0F;
            }

            GL11.glEnable(GL11.GL_ALPHA_TEST);
            this.field_77045_g.func_78086_a(p_76986_1_, f7, f6, p_76986_9_);
            this.func_77036_a(p_76986_1_, f7, f6, f4, f3 - f2, f13, f5);
            int j;
            float f8;
            float f9;
            float f10;

            for (int i = 0; i < 4; ++i)
            {
                j = this.func_77032_a(p_76986_1_, i, p_76986_9_);

                if (j > 0)
                {
                    this.field_77046_h.func_78086_a(p_76986_1_, f7, f6, p_76986_9_);
                    this.field_77046_h.func_78088_a(p_76986_1_, f7, f6, f4, f3 - f2, f13, f5);

                    if ((j & 240) == 16)
                    {
                        this.func_82408_c(p_76986_1_, i, p_76986_9_);
                        this.field_77046_h.func_78088_a(p_76986_1_, f7, f6, f4, f3 - f2, f13, f5);
                    }

                    if ((j & 15) == 15)
                    {
                        f8 = (float)p_76986_1_.field_70173_aa + p_76986_9_;
                        this.func_110776_a(field_110814_a);
                        GL11.glEnable(GL11.GL_BLEND);
                        f9 = 0.5F;
                        GL11.glColor4f(f9, f9, f9, 1.0F);
                        GL11.glDepthFunc(GL11.GL_EQUAL);
                        GL11.glDepthMask(false);

                        for (int k = 0; k < 2; ++k)
                        {
                            GL11.glDisable(GL11.GL_LIGHTING);
                            f10 = 0.76F;
                            GL11.glColor4f(0.5F * f10, 0.25F * f10, 0.8F * f10, 1.0F);
                            GL11.glBlendFunc(GL11.GL_SRC_COLOR, GL11.GL_ONE);
                            GL11.glMatrixMode(GL11.GL_TEXTURE);
                            GL11.glLoadIdentity();
                            float f11 = f8 * (0.001F + (float)k * 0.003F) * 20.0F;
                            float f12 = 0.33333334F;
                            GL11.glScalef(f12, f12, f12);
                            GL11.glRotatef(30.0F - (float)k * 60.0F, 0.0F, 0.0F, 1.0F);
                            GL11.glTranslatef(0.0F, f11, 0.0F);
                            GL11.glMatrixMode(GL11.GL_MODELVIEW);
                            this.field_77046_h.func_78088_a(p_76986_1_, f7, f6, f4, f3 - f2, f13, f5);
                        }

                        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                        GL11.glMatrixMode(GL11.GL_TEXTURE);
                        GL11.glDepthMask(true);
                        GL11.glLoadIdentity();
                        GL11.glMatrixMode(GL11.GL_MODELVIEW);
                        GL11.glEnable(GL11.GL_LIGHTING);
                        GL11.glDisable(GL11.GL_BLEND);
                        GL11.glDepthFunc(GL11.GL_LEQUAL);
                    }

                    GL11.glDisable(GL11.GL_BLEND);
                    GL11.glEnable(GL11.GL_ALPHA_TEST);
                }
            }

            GL11.glDepthMask(true);
            this.func_77029_c(p_76986_1_, p_76986_9_);
            float f14 = p_76986_1_.func_70013_c(p_76986_9_);
            j = this.func_77030_a(p_76986_1_, f14, p_76986_9_);
            OpenGlHelper.func_77473_a(OpenGlHelper.field_77476_b);
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            OpenGlHelper.func_77473_a(OpenGlHelper.field_77478_a);

            if ((j >> 24 & 255) > 0 || p_76986_1_.field_70737_aN > 0 || p_76986_1_.field_70725_aQ > 0)
            {
                GL11.glDisable(GL11.GL_TEXTURE_2D);
                GL11.glDisable(GL11.GL_ALPHA_TEST);
                GL11.glEnable(GL11.GL_BLEND);
                GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                GL11.glDepthFunc(GL11.GL_EQUAL);

                if (p_76986_1_.field_70737_aN > 0 || p_76986_1_.field_70725_aQ > 0)
                {
                    GL11.glColor4f(f14, 0.0F, 0.0F, 0.4F);
                    this.field_77045_g.func_78088_a(p_76986_1_, f7, f6, f4, f3 - f2, f13, f5);

                    for (int l = 0; l < 4; ++l)
                    {
                        if (this.func_77035_b(p_76986_1_, l, p_76986_9_) >= 0)
                        {
                            GL11.glColor4f(f14, 0.0F, 0.0F, 0.4F);
                            this.field_77046_h.func_78088_a(p_76986_1_, f7, f6, f4, f3 - f2, f13, f5);
                        }
                    }
                }

                if ((j >> 24 & 255) > 0)
                {
                    f8 = (float)(j >> 16 & 255) / 255.0F;
                    f9 = (float)(j >> 8 & 255) / 255.0F;
                    float f15 = (float)(j & 255) / 255.0F;
                    f10 = (float)(j >> 24 & 255) / 255.0F;
                    GL11.glColor4f(f8, f9, f15, f10);
                    this.field_77045_g.func_78088_a(p_76986_1_, f7, f6, f4, f3 - f2, f13, f5);

                    for (int i1 = 0; i1 < 4; ++i1)
                    {
                        if (this.func_77035_b(p_76986_1_, i1, p_76986_9_) >= 0)
                        {
                            GL11.glColor4f(f8, f9, f15, f10);
                            this.field_77046_h.func_78088_a(p_76986_1_, f7, f6, f4, f3 - f2, f13, f5);
                        }
                    }
                }

                GL11.glDepthFunc(GL11.GL_LEQUAL);
                GL11.glDisable(GL11.GL_BLEND);
                GL11.glEnable(GL11.GL_ALPHA_TEST);
                GL11.glEnable(GL11.GL_TEXTURE_2D);
            }

            GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        }
        catch (Exception exception)
        {
            field_147923_a.error("Couldn\'t render entity", exception);
        }

        OpenGlHelper.func_77473_a(OpenGlHelper.field_77476_b);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        OpenGlHelper.func_77473_a(OpenGlHelper.field_77478_a);
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glPopMatrix();
        this.func_77033_b(p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_);
    }

    protected void func_77036_a(EntityLivingBase p_77036_1_, float p_77036_2_, float p_77036_3_, float p_77036_4_, float p_77036_5_, float p_77036_6_, float p_77036_7_)
    {
        this.func_110777_b(p_77036_1_);

        if (!p_77036_1_.func_82150_aj())
        {
            this.field_77045_g.func_78088_a(p_77036_1_, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, p_77036_7_);
        }
        else if (!p_77036_1_.func_98034_c(Minecraft.func_71410_x().field_71439_g))
        {
            GL11.glPushMatrix();
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.15F);
            GL11.glDepthMask(false);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GL11.glAlphaFunc(GL11.GL_GREATER, 0.003921569F);
            this.field_77045_g.func_78088_a(p_77036_1_, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, p_77036_7_);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
            GL11.glPopMatrix();
            GL11.glDepthMask(true);
        }
        else
        {
            this.field_77045_g.func_78087_a(p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, p_77036_7_, p_77036_1_);
        }
    }

    protected void func_77039_a(EntityLivingBase p_77039_1_, double p_77039_2_, double p_77039_4_, double p_77039_6_)
    {
        GL11.glTranslatef((float)p_77039_2_, (float)p_77039_4_, (float)p_77039_6_);
    }

    protected void func_77043_a(EntityLivingBase p_77043_1_, float p_77043_2_, float p_77043_3_, float p_77043_4_)
    {
        GL11.glRotatef(180.0F - p_77043_3_, 0.0F, 1.0F, 0.0F);

        if (p_77043_1_.field_70725_aQ > 0)
        {
            float f3 = ((float)p_77043_1_.field_70725_aQ + p_77043_4_ - 1.0F) / 20.0F * 1.6F;
            f3 = MathHelper.func_76129_c(f3);

            if (f3 > 1.0F)
            {
                f3 = 1.0F;
            }

            GL11.glRotatef(f3 * this.func_77037_a(p_77043_1_), 0.0F, 0.0F, 1.0F);
        }
        else
        {
            String s = EnumChatFormatting.func_110646_a(p_77043_1_.func_70005_c_());

            if ((s.equals("Dinnerbone") || s.equals("Grumm")) && (!(p_77043_1_ instanceof EntityPlayer) || !((EntityPlayer)p_77043_1_).func_82238_cc()))
            {
                GL11.glTranslatef(0.0F, p_77043_1_.field_70131_O + 0.1F, 0.0F);
                GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
            }
        }
    }

    protected float func_77040_d(EntityLivingBase p_77040_1_, float p_77040_2_)
    {
        return p_77040_1_.func_70678_g(p_77040_2_);
    }

    protected float func_77044_a(EntityLivingBase p_77044_1_, float p_77044_2_)
    {
        return (float)p_77044_1_.field_70173_aa + p_77044_2_;
    }

    protected void func_77029_c(EntityLivingBase p_77029_1_, float p_77029_2_) {}

    protected void func_85093_e(EntityLivingBase p_85093_1_, float p_85093_2_)
    {
        int i = p_85093_1_.func_85035_bI();

        if (i > 0)
        {
            EntityArrow entityarrow = new EntityArrow(p_85093_1_.field_70170_p, p_85093_1_.field_70165_t, p_85093_1_.field_70163_u, p_85093_1_.field_70161_v);
            Random random = new Random((long)p_85093_1_.func_145782_y());
            RenderHelper.func_74518_a();

            for (int j = 0; j < i; ++j)
            {
                GL11.glPushMatrix();
                ModelRenderer modelrenderer = this.field_77045_g.func_85181_a(random);
                ModelBox modelbox = (ModelBox)modelrenderer.field_78804_l.get(random.nextInt(modelrenderer.field_78804_l.size()));
                modelrenderer.func_78794_c(0.0625F);
                float f1 = random.nextFloat();
                float f2 = random.nextFloat();
                float f3 = random.nextFloat();
                float f4 = (modelbox.field_78252_a + (modelbox.field_78248_d - modelbox.field_78252_a) * f1) / 16.0F;
                float f5 = (modelbox.field_78250_b + (modelbox.field_78249_e - modelbox.field_78250_b) * f2) / 16.0F;
                float f6 = (modelbox.field_78251_c + (modelbox.field_78246_f - modelbox.field_78251_c) * f3) / 16.0F;
                GL11.glTranslatef(f4, f5, f6);
                f1 = f1 * 2.0F - 1.0F;
                f2 = f2 * 2.0F - 1.0F;
                f3 = f3 * 2.0F - 1.0F;
                f1 *= -1.0F;
                f2 *= -1.0F;
                f3 *= -1.0F;
                float f7 = MathHelper.func_76129_c(f1 * f1 + f3 * f3);
                entityarrow.field_70126_B = entityarrow.field_70177_z = (float)(Math.atan2((double)f1, (double)f3) * 180.0D / Math.PI);
                entityarrow.field_70127_C = entityarrow.field_70125_A = (float)(Math.atan2((double)f2, (double)f7) * 180.0D / Math.PI);
                double d0 = 0.0D;
                double d1 = 0.0D;
                double d2 = 0.0D;
                float f8 = 0.0F;
                this.field_76990_c.func_147940_a(entityarrow, d0, d1, d2, f8, p_85093_2_);
                GL11.glPopMatrix();
            }

            RenderHelper.func_74519_b();
        }
    }

    protected int func_77035_b(EntityLivingBase p_77035_1_, int p_77035_2_, float p_77035_3_)
    {
        return this.func_77032_a(p_77035_1_, p_77035_2_, p_77035_3_);
    }

    protected int func_77032_a(EntityLivingBase p_77032_1_, int p_77032_2_, float p_77032_3_)
    {
        return -1;
    }

    protected void func_82408_c(EntityLivingBase p_82408_1_, int p_82408_2_, float p_82408_3_) {}

    protected float func_77037_a(EntityLivingBase p_77037_1_)
    {
        return 90.0F;
    }

    protected int func_77030_a(EntityLivingBase p_77030_1_, float p_77030_2_, float p_77030_3_)
    {
        return 0;
    }

    protected void func_77041_b(EntityLivingBase p_77041_1_, float p_77041_2_) {}

    protected void func_77033_b(EntityLivingBase p_77033_1_, double p_77033_2_, double p_77033_4_, double p_77033_6_)
    {
        GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);

        if (this.func_110813_b(p_77033_1_))
        {
            float f = 1.6F;
            float f1 = 0.016666668F * f;
            double d3 = p_77033_1_.func_70068_e(this.field_76990_c.field_78734_h);
            float f2 = p_77033_1_.func_70093_af() ? 32.0F : 64.0F;

            if (d3 < (double)(f2 * f2))
            {
                String s = p_77033_1_.func_145748_c_().func_150254_d();

                if (p_77033_1_.func_70093_af())
                {
                    FontRenderer fontrenderer = this.func_76983_a();
                    GL11.glPushMatrix();
                    GL11.glTranslatef((float)p_77033_2_ + 0.0F, (float)p_77033_4_ + p_77033_1_.field_70131_O + 0.5F, (float)p_77033_6_);
                    GL11.glNormal3f(0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(-this.field_76990_c.field_78735_i, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(this.field_76990_c.field_78732_j, 1.0F, 0.0F, 0.0F);
                    GL11.glScalef(-f1, -f1, f1);
                    GL11.glDisable(GL11.GL_LIGHTING);
                    GL11.glTranslatef(0.0F, 0.25F / f1, 0.0F);
                    GL11.glDepthMask(false);
                    GL11.glEnable(GL11.GL_BLEND);
                    OpenGlHelper.func_148821_a(770, 771, 1, 0);
                    Tessellator tessellator = Tessellator.field_78398_a;
                    GL11.glDisable(GL11.GL_TEXTURE_2D);
                    tessellator.func_78382_b();
                    int i = fontrenderer.func_78256_a(s) / 2;
                    tessellator.func_78369_a(0.0F, 0.0F, 0.0F, 0.25F);
                    tessellator.func_78377_a((double)(-i - 1), -1.0D, 0.0D);
                    tessellator.func_78377_a((double)(-i - 1), 8.0D, 0.0D);
                    tessellator.func_78377_a((double)(i + 1), 8.0D, 0.0D);
                    tessellator.func_78377_a((double)(i + 1), -1.0D, 0.0D);
                    tessellator.func_78381_a();
                    GL11.glEnable(GL11.GL_TEXTURE_2D);
                    GL11.glDepthMask(true);
                    fontrenderer.func_78276_b(s, -fontrenderer.func_78256_a(s) / 2, 0, 553648127);
                    GL11.glEnable(GL11.GL_LIGHTING);
                    GL11.glDisable(GL11.GL_BLEND);
                    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                    GL11.glPopMatrix();
                }
                else
                {
                    this.func_96449_a(p_77033_1_, p_77033_2_, p_77033_4_, p_77033_6_, s, f1, d3);
                }
            }
        }
    }

    protected boolean func_110813_b(EntityLivingBase p_110813_1_)
    {
        return Minecraft.func_71382_s() && p_110813_1_ != this.field_76990_c.field_78734_h && !p_110813_1_.func_98034_c(Minecraft.func_71410_x().field_71439_g) && p_110813_1_.field_70153_n == null;
    }

    protected void func_96449_a(EntityLivingBase p_96449_1_, double p_96449_2_, double p_96449_4_, double p_96449_6_, String p_96449_8_, float p_96449_9_, double p_96449_10_)
    {
        if (p_96449_1_.func_70608_bn())
        {
            this.func_147906_a(p_96449_1_, p_96449_8_, p_96449_2_, p_96449_4_ - 1.5D, p_96449_6_, 64);
        }
        else
        {
            this.func_147906_a(p_96449_1_, p_96449_8_, p_96449_2_, p_96449_4_, p_96449_6_, 64);
        }
    }

    public void func_76986_a(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
    {
        this.func_76986_a((EntityLivingBase)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }
}