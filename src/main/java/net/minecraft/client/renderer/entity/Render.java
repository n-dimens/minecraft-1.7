package net.minecraft.client.renderer.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public abstract class Render
{
    private static final ResourceLocation field_110778_a = new ResourceLocation("textures/misc/shadow.png");
    protected RenderManager field_76990_c;
    protected RenderBlocks field_147909_c = new RenderBlocks();
    protected float field_76989_e;
    protected float field_76987_f = 1.0F;
    private boolean field_147908_f = false;
    private static final String __OBFID = "CL_00000992";

    public abstract void func_76986_a(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_);

    protected abstract ResourceLocation func_110775_a(Entity p_110775_1_);

    public boolean func_147905_a()
    {
        return this.field_147908_f;
    }

    protected void func_110777_b(Entity p_110777_1_)
    {
        this.func_110776_a(this.func_110775_a(p_110777_1_));
    }

    protected void func_110776_a(ResourceLocation p_110776_1_)
    {
        this.field_76990_c.field_78724_e.func_110577_a(p_110776_1_);
    }

    private void func_76977_a(Entity p_76977_1_, double p_76977_2_, double p_76977_4_, double p_76977_6_, float p_76977_8_)
    {
        GL11.glDisable(GL11.GL_LIGHTING);
        IIcon iicon = Blocks.FIRE.func_149840_c(0);
        IIcon iicon1 = Blocks.FIRE.func_149840_c(1);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)p_76977_2_, (float)p_76977_4_, (float)p_76977_6_);
        float f1 = p_76977_1_.field_70130_N * 1.4F;
        GL11.glScalef(f1, f1, f1);
        Tessellator tessellator = Tessellator.field_78398_a;
        float f2 = 0.5F;
        float f3 = 0.0F;
        float f4 = p_76977_1_.field_70131_O / f1;
        float f5 = (float)(p_76977_1_.field_70163_u - p_76977_1_.field_70121_D.field_72338_b);
        GL11.glRotatef(-this.field_76990_c.field_78735_i, 0.0F, 1.0F, 0.0F);
        GL11.glTranslatef(0.0F, 0.0F, -0.3F + (float)((int)f4) * 0.02F);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        float f6 = 0.0F;
        int i = 0;
        tessellator.func_78382_b();

        while (f4 > 0.0F)
        {
            IIcon iicon2 = i % 2 == 0 ? iicon : iicon1;
            this.func_110776_a(TextureMap.field_110575_b);
            float f7 = iicon2.func_94209_e();
            float f8 = iicon2.func_94206_g();
            float f9 = iicon2.func_94212_f();
            float f10 = iicon2.func_94210_h();

            if (i / 2 % 2 == 0)
            {
                float f11 = f9;
                f9 = f7;
                f7 = f11;
            }

            tessellator.func_78374_a((double)(f2 - f3), (double)(0.0F - f5), (double)f6, (double)f9, (double)f10);
            tessellator.func_78374_a((double)(-f2 - f3), (double)(0.0F - f5), (double)f6, (double)f7, (double)f10);
            tessellator.func_78374_a((double)(-f2 - f3), (double)(1.4F - f5), (double)f6, (double)f7, (double)f8);
            tessellator.func_78374_a((double)(f2 - f3), (double)(1.4F - f5), (double)f6, (double)f9, (double)f8);
            f4 -= 0.45F;
            f5 -= 0.45F;
            f2 *= 0.9F;
            f6 += 0.03F;
            ++i;
        }

        tessellator.func_78381_a();
        GL11.glPopMatrix();
        GL11.glEnable(GL11.GL_LIGHTING);
    }

    private void func_76975_c(Entity p_76975_1_, double p_76975_2_, double p_76975_4_, double p_76975_6_, float p_76975_8_, float p_76975_9_)
    {
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        this.field_76990_c.field_78724_e.func_110577_a(field_110778_a);
        World world = this.func_76982_b();
        GL11.glDepthMask(false);
        float f2 = this.field_76989_e;

        if (p_76975_1_ instanceof EntityLiving)
        {
            EntityLiving entityliving = (EntityLiving)p_76975_1_;
            f2 *= entityliving.func_70603_bj();

            if (entityliving.func_70631_g_())
            {
                f2 *= 0.5F;
            }
        }

        double d8 = p_76975_1_.field_70142_S + (p_76975_1_.field_70165_t - p_76975_1_.field_70142_S) * (double)p_76975_9_;
        double d3 = p_76975_1_.field_70137_T + (p_76975_1_.field_70163_u - p_76975_1_.field_70137_T) * (double)p_76975_9_ + (double)p_76975_1_.func_70053_R();
        double d4 = p_76975_1_.field_70136_U + (p_76975_1_.field_70161_v - p_76975_1_.field_70136_U) * (double)p_76975_9_;
        int i = MathHelper.func_76128_c(d8 - (double)f2);
        int j = MathHelper.func_76128_c(d8 + (double)f2);
        int k = MathHelper.func_76128_c(d3 - (double)f2);
        int l = MathHelper.func_76128_c(d3);
        int i1 = MathHelper.func_76128_c(d4 - (double)f2);
        int j1 = MathHelper.func_76128_c(d4 + (double)f2);
        double d5 = p_76975_2_ - d8;
        double d6 = p_76975_4_ - d3;
        double d7 = p_76975_6_ - d4;
        Tessellator tessellator = Tessellator.field_78398_a;
        tessellator.func_78382_b();

        for (int k1 = i; k1 <= j; ++k1)
        {
            for (int l1 = k; l1 <= l; ++l1)
            {
                for (int i2 = i1; i2 <= j1; ++i2)
                {
                    Block block = world.func_147439_a(k1, l1 - 1, i2);

                    if (block.func_149688_o() != Material.field_151579_a && world.func_72957_l(k1, l1, i2) > 3)
                    {
                        this.func_147907_a(block, p_76975_2_, p_76975_4_ + (double)p_76975_1_.func_70053_R(), p_76975_6_, k1, l1, i2, p_76975_8_, f2, d5, d6 + (double)p_76975_1_.func_70053_R(), d7);
                    }
                }
            }
        }

        tessellator.func_78381_a();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDepthMask(true);
    }

    private World func_76982_b()
    {
        return this.field_76990_c.field_78722_g;
    }

    private void func_147907_a(Block p_147907_1_, double p_147907_2_, double p_147907_4_, double p_147907_6_, int p_147907_8_, int p_147907_9_, int p_147907_10_, float p_147907_11_, float p_147907_12_, double p_147907_13_, double p_147907_15_, double p_147907_17_)
    {
        Tessellator tessellator = Tessellator.field_78398_a;

        if (p_147907_1_.func_149686_d())
        {
            double d6 = ((double)p_147907_11_ - (p_147907_4_ - ((double)p_147907_9_ + p_147907_15_)) / 2.0D) * 0.5D * (double)this.func_76982_b().func_72801_o(p_147907_8_, p_147907_9_, p_147907_10_);

            if (d6 >= 0.0D)
            {
                if (d6 > 1.0D)
                {
                    d6 = 1.0D;
                }

                tessellator.func_78369_a(1.0F, 1.0F, 1.0F, (float)d6);
                double d7 = (double)p_147907_8_ + p_147907_1_.func_149704_x() + p_147907_13_;
                double d8 = (double)p_147907_8_ + p_147907_1_.func_149753_y() + p_147907_13_;
                double d9 = (double)p_147907_9_ + p_147907_1_.func_149665_z() + p_147907_15_ + 0.015625D;
                double d10 = (double)p_147907_10_ + p_147907_1_.func_149706_B() + p_147907_17_;
                double d11 = (double)p_147907_10_ + p_147907_1_.func_149693_C() + p_147907_17_;
                float f2 = (float)((p_147907_2_ - d7) / 2.0D / (double)p_147907_12_ + 0.5D);
                float f3 = (float)((p_147907_2_ - d8) / 2.0D / (double)p_147907_12_ + 0.5D);
                float f4 = (float)((p_147907_6_ - d10) / 2.0D / (double)p_147907_12_ + 0.5D);
                float f5 = (float)((p_147907_6_ - d11) / 2.0D / (double)p_147907_12_ + 0.5D);
                tessellator.func_78374_a(d7, d9, d10, (double)f2, (double)f4);
                tessellator.func_78374_a(d7, d9, d11, (double)f2, (double)f5);
                tessellator.func_78374_a(d8, d9, d11, (double)f3, (double)f5);
                tessellator.func_78374_a(d8, d9, d10, (double)f3, (double)f4);
            }
        }
    }

    public static void func_76978_a(AxisAlignedBB p_76978_0_, double p_76978_1_, double p_76978_3_, double p_76978_5_)
    {
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        Tessellator tessellator = Tessellator.field_78398_a;
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        tessellator.func_78382_b();
        tessellator.func_78373_b(p_76978_1_, p_76978_3_, p_76978_5_);
        tessellator.func_78375_b(0.0F, 0.0F, -1.0F);
        tessellator.func_78377_a(p_76978_0_.field_72340_a, p_76978_0_.field_72337_e, p_76978_0_.field_72339_c);
        tessellator.func_78377_a(p_76978_0_.field_72336_d, p_76978_0_.field_72337_e, p_76978_0_.field_72339_c);
        tessellator.func_78377_a(p_76978_0_.field_72336_d, p_76978_0_.field_72338_b, p_76978_0_.field_72339_c);
        tessellator.func_78377_a(p_76978_0_.field_72340_a, p_76978_0_.field_72338_b, p_76978_0_.field_72339_c);
        tessellator.func_78375_b(0.0F, 0.0F, 1.0F);
        tessellator.func_78377_a(p_76978_0_.field_72340_a, p_76978_0_.field_72338_b, p_76978_0_.field_72334_f);
        tessellator.func_78377_a(p_76978_0_.field_72336_d, p_76978_0_.field_72338_b, p_76978_0_.field_72334_f);
        tessellator.func_78377_a(p_76978_0_.field_72336_d, p_76978_0_.field_72337_e, p_76978_0_.field_72334_f);
        tessellator.func_78377_a(p_76978_0_.field_72340_a, p_76978_0_.field_72337_e, p_76978_0_.field_72334_f);
        tessellator.func_78375_b(0.0F, -1.0F, 0.0F);
        tessellator.func_78377_a(p_76978_0_.field_72340_a, p_76978_0_.field_72338_b, p_76978_0_.field_72339_c);
        tessellator.func_78377_a(p_76978_0_.field_72336_d, p_76978_0_.field_72338_b, p_76978_0_.field_72339_c);
        tessellator.func_78377_a(p_76978_0_.field_72336_d, p_76978_0_.field_72338_b, p_76978_0_.field_72334_f);
        tessellator.func_78377_a(p_76978_0_.field_72340_a, p_76978_0_.field_72338_b, p_76978_0_.field_72334_f);
        tessellator.func_78375_b(0.0F, 1.0F, 0.0F);
        tessellator.func_78377_a(p_76978_0_.field_72340_a, p_76978_0_.field_72337_e, p_76978_0_.field_72334_f);
        tessellator.func_78377_a(p_76978_0_.field_72336_d, p_76978_0_.field_72337_e, p_76978_0_.field_72334_f);
        tessellator.func_78377_a(p_76978_0_.field_72336_d, p_76978_0_.field_72337_e, p_76978_0_.field_72339_c);
        tessellator.func_78377_a(p_76978_0_.field_72340_a, p_76978_0_.field_72337_e, p_76978_0_.field_72339_c);
        tessellator.func_78375_b(-1.0F, 0.0F, 0.0F);
        tessellator.func_78377_a(p_76978_0_.field_72340_a, p_76978_0_.field_72338_b, p_76978_0_.field_72334_f);
        tessellator.func_78377_a(p_76978_0_.field_72340_a, p_76978_0_.field_72337_e, p_76978_0_.field_72334_f);
        tessellator.func_78377_a(p_76978_0_.field_72340_a, p_76978_0_.field_72337_e, p_76978_0_.field_72339_c);
        tessellator.func_78377_a(p_76978_0_.field_72340_a, p_76978_0_.field_72338_b, p_76978_0_.field_72339_c);
        tessellator.func_78375_b(1.0F, 0.0F, 0.0F);
        tessellator.func_78377_a(p_76978_0_.field_72336_d, p_76978_0_.field_72338_b, p_76978_0_.field_72339_c);
        tessellator.func_78377_a(p_76978_0_.field_72336_d, p_76978_0_.field_72337_e, p_76978_0_.field_72339_c);
        tessellator.func_78377_a(p_76978_0_.field_72336_d, p_76978_0_.field_72337_e, p_76978_0_.field_72334_f);
        tessellator.func_78377_a(p_76978_0_.field_72336_d, p_76978_0_.field_72338_b, p_76978_0_.field_72334_f);
        tessellator.func_78373_b(0.0D, 0.0D, 0.0D);
        tessellator.func_78381_a();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
    }

    public static void func_76980_a(AxisAlignedBB p_76980_0_)
    {
        Tessellator tessellator = Tessellator.field_78398_a;
        tessellator.func_78382_b();
        tessellator.func_78377_a(p_76980_0_.field_72340_a, p_76980_0_.field_72337_e, p_76980_0_.field_72339_c);
        tessellator.func_78377_a(p_76980_0_.field_72336_d, p_76980_0_.field_72337_e, p_76980_0_.field_72339_c);
        tessellator.func_78377_a(p_76980_0_.field_72336_d, p_76980_0_.field_72338_b, p_76980_0_.field_72339_c);
        tessellator.func_78377_a(p_76980_0_.field_72340_a, p_76980_0_.field_72338_b, p_76980_0_.field_72339_c);
        tessellator.func_78377_a(p_76980_0_.field_72340_a, p_76980_0_.field_72338_b, p_76980_0_.field_72334_f);
        tessellator.func_78377_a(p_76980_0_.field_72336_d, p_76980_0_.field_72338_b, p_76980_0_.field_72334_f);
        tessellator.func_78377_a(p_76980_0_.field_72336_d, p_76980_0_.field_72337_e, p_76980_0_.field_72334_f);
        tessellator.func_78377_a(p_76980_0_.field_72340_a, p_76980_0_.field_72337_e, p_76980_0_.field_72334_f);
        tessellator.func_78377_a(p_76980_0_.field_72340_a, p_76980_0_.field_72338_b, p_76980_0_.field_72339_c);
        tessellator.func_78377_a(p_76980_0_.field_72336_d, p_76980_0_.field_72338_b, p_76980_0_.field_72339_c);
        tessellator.func_78377_a(p_76980_0_.field_72336_d, p_76980_0_.field_72338_b, p_76980_0_.field_72334_f);
        tessellator.func_78377_a(p_76980_0_.field_72340_a, p_76980_0_.field_72338_b, p_76980_0_.field_72334_f);
        tessellator.func_78377_a(p_76980_0_.field_72340_a, p_76980_0_.field_72337_e, p_76980_0_.field_72334_f);
        tessellator.func_78377_a(p_76980_0_.field_72336_d, p_76980_0_.field_72337_e, p_76980_0_.field_72334_f);
        tessellator.func_78377_a(p_76980_0_.field_72336_d, p_76980_0_.field_72337_e, p_76980_0_.field_72339_c);
        tessellator.func_78377_a(p_76980_0_.field_72340_a, p_76980_0_.field_72337_e, p_76980_0_.field_72339_c);
        tessellator.func_78377_a(p_76980_0_.field_72340_a, p_76980_0_.field_72338_b, p_76980_0_.field_72334_f);
        tessellator.func_78377_a(p_76980_0_.field_72340_a, p_76980_0_.field_72337_e, p_76980_0_.field_72334_f);
        tessellator.func_78377_a(p_76980_0_.field_72340_a, p_76980_0_.field_72337_e, p_76980_0_.field_72339_c);
        tessellator.func_78377_a(p_76980_0_.field_72340_a, p_76980_0_.field_72338_b, p_76980_0_.field_72339_c);
        tessellator.func_78377_a(p_76980_0_.field_72336_d, p_76980_0_.field_72338_b, p_76980_0_.field_72339_c);
        tessellator.func_78377_a(p_76980_0_.field_72336_d, p_76980_0_.field_72337_e, p_76980_0_.field_72339_c);
        tessellator.func_78377_a(p_76980_0_.field_72336_d, p_76980_0_.field_72337_e, p_76980_0_.field_72334_f);
        tessellator.func_78377_a(p_76980_0_.field_72336_d, p_76980_0_.field_72338_b, p_76980_0_.field_72334_f);
        tessellator.func_78381_a();
    }

    public void func_76976_a(RenderManager p_76976_1_)
    {
        this.field_76990_c = p_76976_1_;
    }

    public void func_76979_b(Entity p_76979_1_, double p_76979_2_, double p_76979_4_, double p_76979_6_, float p_76979_8_, float p_76979_9_)
    {
        if (this.field_76990_c.field_78733_k.field_74347_j && this.field_76989_e > 0.0F && !p_76979_1_.func_82150_aj())
        {
            double d3 = this.field_76990_c.func_78714_a(p_76979_1_.field_70165_t, p_76979_1_.field_70163_u, p_76979_1_.field_70161_v);
            float f2 = (float)((1.0D - d3 / 256.0D) * (double)this.field_76987_f);

            if (f2 > 0.0F)
            {
                this.func_76975_c(p_76979_1_, p_76979_2_, p_76979_4_, p_76979_6_, f2, p_76979_9_);
            }
        }

        if (p_76979_1_.func_90999_ad())
        {
            this.func_76977_a(p_76979_1_, p_76979_2_, p_76979_4_, p_76979_6_, p_76979_9_);
        }
    }

    public FontRenderer func_76983_a()
    {
        return this.field_76990_c.func_78716_a();
    }

    public void func_94143_a(IIconRegister p_94143_1_) {}

    protected void func_147906_a(Entity p_147906_1_, String p_147906_2_, double p_147906_3_, double p_147906_5_, double p_147906_7_, int p_147906_9_)
    {
        double d3 = p_147906_1_.func_70068_e(this.field_76990_c.field_78734_h);

        if (d3 <= (double)(p_147906_9_ * p_147906_9_))
        {
            FontRenderer fontrenderer = this.func_76983_a();
            float f = 1.6F;
            float f1 = 0.016666668F * f;
            GL11.glPushMatrix();
            GL11.glTranslatef((float)p_147906_3_ + 0.0F, (float)p_147906_5_ + p_147906_1_.field_70131_O + 0.5F, (float)p_147906_7_);
            GL11.glNormal3f(0.0F, 1.0F, 0.0F);
            GL11.glRotatef(-this.field_76990_c.field_78735_i, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(this.field_76990_c.field_78732_j, 1.0F, 0.0F, 0.0F);
            GL11.glScalef(-f1, -f1, f1);
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glDepthMask(false);
            GL11.glDisable(GL11.GL_DEPTH_TEST);
            GL11.glEnable(GL11.GL_BLEND);
            OpenGlHelper.func_148821_a(770, 771, 1, 0);
            Tessellator tessellator = Tessellator.field_78398_a;
            byte b0 = 0;

            if (p_147906_2_.equals("deadmau5"))
            {
                b0 = -10;
            }

            GL11.glDisable(GL11.GL_TEXTURE_2D);
            tessellator.func_78382_b();
            int j = fontrenderer.func_78256_a(p_147906_2_) / 2;
            tessellator.func_78369_a(0.0F, 0.0F, 0.0F, 0.25F);
            tessellator.func_78377_a((double)(-j - 1), (double)(-1 + b0), 0.0D);
            tessellator.func_78377_a((double)(-j - 1), (double)(8 + b0), 0.0D);
            tessellator.func_78377_a((double)(j + 1), (double)(8 + b0), 0.0D);
            tessellator.func_78377_a((double)(j + 1), (double)(-1 + b0), 0.0D);
            tessellator.func_78381_a();
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            fontrenderer.func_78276_b(p_147906_2_, -fontrenderer.func_78256_a(p_147906_2_) / 2, b0, 553648127);
            GL11.glEnable(GL11.GL_DEPTH_TEST);
            GL11.glDepthMask(true);
            fontrenderer.func_78276_b(p_147906_2_, -fontrenderer.func_78256_a(p_147906_2_) / 2, b0, -1);
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glPopMatrix();
        }
    }
}