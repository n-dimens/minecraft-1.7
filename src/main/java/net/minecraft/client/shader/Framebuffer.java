package net.minecraft.client.shader;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.nio.ByteBuffer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureUtil;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class Framebuffer
{
    public int field_147622_a;
    public int field_147620_b;
    public int field_147621_c;
    public int field_147618_d;
    public boolean field_147619_e;
    public int field_147616_f;
    public int field_147617_g;
    public int field_147624_h;
    public float[] field_147625_i;
    public int field_147623_j;
    private static final String __OBFID = "CL_00000959";

    public Framebuffer(int p_i45078_1_, int p_i45078_2_, boolean p_i45078_3_)
    {
        this.field_147619_e = p_i45078_3_;
        this.field_147616_f = -1;
        this.field_147617_g = -1;
        this.field_147624_h = -1;
        this.field_147625_i = new float[4];
        this.field_147625_i[0] = 1.0F;
        this.field_147625_i[1] = 1.0F;
        this.field_147625_i[2] = 1.0F;
        this.field_147625_i[3] = 0.0F;
        this.func_147613_a(p_i45078_1_, p_i45078_2_);
    }

    public void func_147613_a(int p_147613_1_, int p_147613_2_)
    {
        if (!OpenGlHelper.func_148822_b())
        {
            this.field_147621_c = p_147613_1_;
            this.field_147618_d = p_147613_2_;
        }
        else
        {
            GL11.glEnable(GL11.GL_DEPTH_TEST);

            if (this.field_147616_f >= 0)
            {
                this.func_147608_a();
            }

            this.func_147605_b(p_147613_1_, p_147613_2_);
            this.func_147611_b();
            OpenGlHelper.func_153171_g(OpenGlHelper.field_153198_e, 0);
        }
    }

    public void func_147608_a()
    {
        if (OpenGlHelper.func_148822_b())
        {
            this.func_147606_d();
            this.func_147609_e();

            if (this.field_147624_h > -1)
            {
                OpenGlHelper.func_153184_g(this.field_147624_h);
                this.field_147624_h = -1;
            }

            if (this.field_147617_g > -1)
            {
                TextureUtil.func_147942_a(this.field_147617_g);
                this.field_147617_g = -1;
            }

            if (this.field_147616_f > -1)
            {
                OpenGlHelper.func_153171_g(OpenGlHelper.field_153198_e, 0);
                OpenGlHelper.func_153174_h(this.field_147616_f);
                this.field_147616_f = -1;
            }
        }
    }

    public void func_147605_b(int p_147605_1_, int p_147605_2_)
    {
        this.field_147621_c = p_147605_1_;
        this.field_147618_d = p_147605_2_;
        this.field_147622_a = p_147605_1_;
        this.field_147620_b = p_147605_2_;

        if (!OpenGlHelper.func_148822_b())
        {
            this.func_147614_f();
        }
        else
        {
            this.field_147616_f = OpenGlHelper.func_153165_e();
            this.field_147617_g = TextureUtil.func_110996_a();

            if (this.field_147619_e)
            {
                this.field_147624_h = OpenGlHelper.func_153185_f();
            }

            this.func_147607_a(9728);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.field_147617_g);
            GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA8, this.field_147622_a, this.field_147620_b, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, (ByteBuffer)null);
            OpenGlHelper.func_153171_g(OpenGlHelper.field_153198_e, this.field_147616_f);
            OpenGlHelper.func_153188_a(OpenGlHelper.field_153198_e, OpenGlHelper.field_153200_g, 3553, this.field_147617_g, 0);

            if (this.field_147619_e)
            {
                OpenGlHelper.func_153176_h(OpenGlHelper.field_153199_f, this.field_147624_h);
                OpenGlHelper.func_153186_a(OpenGlHelper.field_153199_f, 33190, this.field_147622_a, this.field_147620_b);
                OpenGlHelper.func_153190_b(OpenGlHelper.field_153198_e, OpenGlHelper.field_153201_h, OpenGlHelper.field_153199_f, this.field_147624_h);
            }

            this.func_147614_f();
            this.func_147606_d();
        }
    }

    public void func_147607_a(int p_147607_1_)
    {
        if (OpenGlHelper.func_148822_b())
        {
            this.field_147623_j = p_147607_1_;
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.field_147617_g);
            GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, (float)p_147607_1_);
            GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, (float)p_147607_1_);
            GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, 10496.0F);
            GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, 10496.0F);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
        }
    }

    public void func_147611_b()
    {
        int i = OpenGlHelper.func_153167_i(OpenGlHelper.field_153198_e);

        if (i != OpenGlHelper.field_153202_i)
        {
            if (i == OpenGlHelper.field_153203_j)
            {
                throw new RuntimeException("GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT");
            }
            else if (i == OpenGlHelper.field_153204_k)
            {
                throw new RuntimeException("GL_FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT");
            }
            else if (i == OpenGlHelper.field_153205_l)
            {
                throw new RuntimeException("GL_FRAMEBUFFER_INCOMPLETE_DRAW_BUFFER");
            }
            else if (i == OpenGlHelper.field_153206_m)
            {
                throw new RuntimeException("GL_FRAMEBUFFER_INCOMPLETE_READ_BUFFER");
            }
            else
            {
                throw new RuntimeException("glCheckFramebufferStatus returned unknown status:" + i);
            }
        }
    }

    public void func_147612_c()
    {
        if (OpenGlHelper.func_148822_b())
        {
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.field_147617_g);
        }
    }

    public void func_147606_d()
    {
        if (OpenGlHelper.func_148822_b())
        {
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
        }
    }

    public void func_147610_a(boolean p_147610_1_)
    {
        if (OpenGlHelper.func_148822_b())
        {
            OpenGlHelper.func_153171_g(OpenGlHelper.field_153198_e, this.field_147616_f);

            if (p_147610_1_)
            {
                GL11.glViewport(0, 0, this.field_147621_c, this.field_147618_d);
            }
        }
    }

    public void func_147609_e()
    {
        if (OpenGlHelper.func_148822_b())
        {
            OpenGlHelper.func_153171_g(OpenGlHelper.field_153198_e, 0);
        }
    }

    public void func_147604_a(float p_147604_1_, float p_147604_2_, float p_147604_3_, float p_147604_4_)
    {
        this.field_147625_i[0] = p_147604_1_;
        this.field_147625_i[1] = p_147604_2_;
        this.field_147625_i[2] = p_147604_3_;
        this.field_147625_i[3] = p_147604_4_;
    }

    public void func_147615_c(int p_147615_1_, int p_147615_2_)
    {
        if (OpenGlHelper.func_148822_b())
        {
            GL11.glColorMask(true, true, true, false);
            GL11.glDisable(GL11.GL_DEPTH_TEST);
            GL11.glDepthMask(false);
            GL11.glMatrixMode(GL11.GL_PROJECTION);
            GL11.glLoadIdentity();
            GL11.glOrtho(0.0D, (double)p_147615_1_, (double)p_147615_2_, 0.0D, 1000.0D, 3000.0D);
            GL11.glMatrixMode(GL11.GL_MODELVIEW);
            GL11.glLoadIdentity();
            GL11.glTranslatef(0.0F, 0.0F, -2000.0F);
            GL11.glViewport(0, 0, p_147615_1_, p_147615_2_);
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glDisable(GL11.GL_ALPHA_TEST);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glEnable(GL11.GL_COLOR_MATERIAL);
            this.func_147612_c();
            float f = (float)p_147615_1_;
            float f1 = (float)p_147615_2_;
            float f2 = (float)this.field_147621_c / (float)this.field_147622_a;
            float f3 = (float)this.field_147618_d / (float)this.field_147620_b;
            Tessellator tessellator = Tessellator.field_78398_a;
            tessellator.func_78382_b();
            tessellator.func_78378_d(-1);
            tessellator.func_78374_a(0.0D, (double)f1, 0.0D, 0.0D, 0.0D);
            tessellator.func_78374_a((double)f, (double)f1, 0.0D, (double)f2, 0.0D);
            tessellator.func_78374_a((double)f, 0.0D, 0.0D, (double)f2, (double)f3);
            tessellator.func_78374_a(0.0D, 0.0D, 0.0D, 0.0D, (double)f3);
            tessellator.func_78381_a();
            this.func_147606_d();
            GL11.glDepthMask(true);
            GL11.glColorMask(true, true, true, true);
        }
    }

    public void func_147614_f()
    {
        this.func_147610_a(true);
        GL11.glClearColor(this.field_147625_i[0], this.field_147625_i[1], this.field_147625_i[2], this.field_147625_i[3]);
        int i = 16384;

        if (this.field_147619_e)
        {
            GL11.glClearDepth(1.0D);
            i |= 256;
        }

        GL11.glClear(i);
        this.func_147609_e();
    }
}