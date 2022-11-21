package net.minecraft.client.renderer.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public abstract class RenderLiving extends RendererLivingEntity
{
    private static final String __OBFID = "CL_00001015";

    public RenderLiving(ModelBase p_i1262_1_, float p_i1262_2_)
    {
        super(p_i1262_1_, p_i1262_2_);
    }

    protected boolean func_110813_b(EntityLiving p_110813_1_)
    {
        return super.func_110813_b(p_110813_1_) && (p_110813_1_.func_94059_bO() || p_110813_1_.func_94056_bM() && p_110813_1_ == this.field_76990_c.field_147941_i);
    }

    public void func_76986_a(EntityLiving p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
    {
        super.func_76986_a((EntityLivingBase)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
        this.func_110827_b(p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }

    private double func_110828_a(double p_110828_1_, double p_110828_3_, double p_110828_5_)
    {
        return p_110828_1_ + (p_110828_3_ - p_110828_1_) * p_110828_5_;
    }

    protected void func_110827_b(EntityLiving p_110827_1_, double p_110827_2_, double p_110827_4_, double p_110827_6_, float p_110827_8_, float p_110827_9_)
    {
        Entity entity = p_110827_1_.func_110166_bE();

        if (entity != null)
        {
            p_110827_4_ -= (1.6D - (double)p_110827_1_.field_70131_O) * 0.5D;
            Tessellator tessellator = Tessellator.field_78398_a;
            double d3 = this.func_110828_a((double)entity.field_70126_B, (double)entity.field_70177_z, (double)(p_110827_9_ * 0.5F)) * 0.01745329238474369D;
            double d4 = this.func_110828_a((double)entity.field_70127_C, (double)entity.field_70125_A, (double)(p_110827_9_ * 0.5F)) * 0.01745329238474369D;
            double d5 = Math.cos(d3);
            double d6 = Math.sin(d3);
            double d7 = Math.sin(d4);

            if (entity instanceof EntityHanging)
            {
                d5 = 0.0D;
                d6 = 0.0D;
                d7 = -1.0D;
            }

            double d8 = Math.cos(d4);
            double d9 = this.func_110828_a(entity.field_70169_q, entity.field_70165_t, (double)p_110827_9_) - d5 * 0.7D - d6 * 0.5D * d8;
            double d10 = this.func_110828_a(entity.field_70167_r + (double)entity.func_70047_e() * 0.7D, entity.field_70163_u + (double)entity.func_70047_e() * 0.7D, (double)p_110827_9_) - d7 * 0.5D - 0.25D;
            double d11 = this.func_110828_a(entity.field_70166_s, entity.field_70161_v, (double)p_110827_9_) - d6 * 0.7D + d5 * 0.5D * d8;
            double d12 = this.func_110828_a((double)p_110827_1_.field_70760_ar, (double)p_110827_1_.field_70761_aq, (double)p_110827_9_) * 0.01745329238474369D + (Math.PI / 2D);
            d5 = Math.cos(d12) * (double)p_110827_1_.field_70130_N * 0.4D;
            d6 = Math.sin(d12) * (double)p_110827_1_.field_70130_N * 0.4D;
            double d13 = this.func_110828_a(p_110827_1_.field_70169_q, p_110827_1_.field_70165_t, (double)p_110827_9_) + d5;
            double d14 = this.func_110828_a(p_110827_1_.field_70167_r, p_110827_1_.field_70163_u, (double)p_110827_9_);
            double d15 = this.func_110828_a(p_110827_1_.field_70166_s, p_110827_1_.field_70161_v, (double)p_110827_9_) + d6;
            p_110827_2_ += d5;
            p_110827_6_ += d6;
            double d16 = (double)((float)(d9 - d13));
            double d17 = (double)((float)(d10 - d14));
            double d18 = (double)((float)(d11 - d15));
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glDisable(GL11.GL_CULL_FACE);
            boolean flag = true;
            double d19 = 0.025D;
            tessellator.func_78371_b(5);
            int i;
            float f2;

            for (i = 0; i <= 24; ++i)
            {
                if (i % 2 == 0)
                {
                    tessellator.func_78369_a(0.5F, 0.4F, 0.3F, 1.0F);
                }
                else
                {
                    tessellator.func_78369_a(0.35F, 0.28F, 0.21000001F, 1.0F);
                }

                f2 = (float)i / 24.0F;
                tessellator.func_78377_a(p_110827_2_ + d16 * (double)f2 + 0.0D, p_110827_4_ + d17 * (double)(f2 * f2 + f2) * 0.5D + (double)((24.0F - (float)i) / 18.0F + 0.125F), p_110827_6_ + d18 * (double)f2);
                tessellator.func_78377_a(p_110827_2_ + d16 * (double)f2 + 0.025D, p_110827_4_ + d17 * (double)(f2 * f2 + f2) * 0.5D + (double)((24.0F - (float)i) / 18.0F + 0.125F) + 0.025D, p_110827_6_ + d18 * (double)f2);
            }

            tessellator.func_78381_a();
            tessellator.func_78371_b(5);

            for (i = 0; i <= 24; ++i)
            {
                if (i % 2 == 0)
                {
                    tessellator.func_78369_a(0.5F, 0.4F, 0.3F, 1.0F);
                }
                else
                {
                    tessellator.func_78369_a(0.35F, 0.28F, 0.21000001F, 1.0F);
                }

                f2 = (float)i / 24.0F;
                tessellator.func_78377_a(p_110827_2_ + d16 * (double)f2 + 0.0D, p_110827_4_ + d17 * (double)(f2 * f2 + f2) * 0.5D + (double)((24.0F - (float)i) / 18.0F + 0.125F) + 0.025D, p_110827_6_ + d18 * (double)f2);
                tessellator.func_78377_a(p_110827_2_ + d16 * (double)f2 + 0.025D, p_110827_4_ + d17 * (double)(f2 * f2 + f2) * 0.5D + (double)((24.0F - (float)i) / 18.0F + 0.125F), p_110827_6_ + d18 * (double)f2 + 0.025D);
            }

            tessellator.func_78381_a();
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glEnable(GL11.GL_CULL_FACE);
        }
    }

    protected boolean func_110813_b(EntityLivingBase p_110813_1_)
    {
        return this.func_110813_b((EntityLiving)p_110813_1_);
    }

    public void func_76986_a(EntityLivingBase p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
    {
        this.func_76986_a((EntityLiving)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }

    public void func_76986_a(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
    {
        this.func_76986_a((EntityLiving)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }
}