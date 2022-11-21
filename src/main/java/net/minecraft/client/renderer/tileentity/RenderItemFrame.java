package net.minecraft.client.renderer.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureCompass;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.MapData;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderItemFrame extends Render
{
    private static final ResourceLocation field_110789_a = new ResourceLocation("textures/map/map_background.png");
    private final RenderBlocks field_147916_f = new RenderBlocks();
    private final Minecraft field_147917_g = Minecraft.func_71410_x();
    private IIcon field_94147_f;
    private static final String __OBFID = "CL_00001002";

    public void func_94143_a(IIconRegister p_94143_1_)
    {
        this.field_94147_f = p_94143_1_.func_94245_a("itemframe_background");
    }

    public void func_76986_a(EntityItemFrame p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
    {
        GL11.glPushMatrix();
        double d3 = p_76986_1_.field_70165_t - p_76986_2_ - 0.5D;
        double d4 = p_76986_1_.field_70163_u - p_76986_4_ - 0.5D;
        double d5 = p_76986_1_.field_70161_v - p_76986_6_ - 0.5D;
        int i = p_76986_1_.field_146063_b + Direction.field_71583_a[p_76986_1_.field_82332_a];
        int j = p_76986_1_.field_146064_c;
        int k = p_76986_1_.field_146062_d + Direction.field_71581_b[p_76986_1_.field_82332_a];
        GL11.glTranslated((double)i - d3, (double)j - d4, (double)k - d5);

        if (p_76986_1_.func_82335_i() != null && p_76986_1_.func_82335_i().func_77973_b() == Items.FILLED_MAP)
        {
            this.func_147915_b(p_76986_1_);
        }
        else
        {
            this.func_82403_a(p_76986_1_);
        }

        this.func_82402_b(p_76986_1_);
        GL11.glPopMatrix();
        this.func_147914_a(p_76986_1_, p_76986_2_ + (double)((float)Direction.field_71583_a[p_76986_1_.field_82332_a] * 0.3F), p_76986_4_ - 0.25D, p_76986_6_ + (double)((float)Direction.field_71581_b[p_76986_1_.field_82332_a] * 0.3F));
    }

    protected ResourceLocation func_110775_a(EntityItemFrame p_110775_1_)
    {
        return null;
    }

    private void func_147915_b(EntityItemFrame p_147915_1_)
    {
        GL11.glPushMatrix();
        GL11.glRotatef(p_147915_1_.field_70177_z, 0.0F, 1.0F, 0.0F);
        this.field_76990_c.field_78724_e.func_110577_a(TextureMap.field_110575_b);
        Block block = Blocks.PLANKS;
        float f = 0.0625F;
        float f1 = 1.0F;
        float f2 = f1 / 2.0F;
        GL11.glPushMatrix();
        this.field_147916_f.func_147770_b(0.0D, (double)(0.5F - f2 + 0.0625F), (double)(0.5F - f2 + 0.0625F), (double)f, (double)(0.5F + f2 - 0.0625F), (double)(0.5F + f2 - 0.0625F));
        this.field_147916_f.func_147757_a(this.field_94147_f);
        this.field_147916_f.func_147800_a(block, 0, 1.0F);
        this.field_147916_f.func_147771_a();
        this.field_147916_f.func_147762_c();
        GL11.glPopMatrix();
        this.field_147916_f.func_147757_a(Blocks.PLANKS.func_149691_a(1, 2));
        GL11.glPushMatrix();
        this.field_147916_f.func_147770_b(0.0D, (double)(0.5F - f2), (double)(0.5F - f2), (double)(f + 1.0E-4F), (double)(f + 0.5F - f2), (double)(0.5F + f2));
        this.field_147916_f.func_147800_a(block, 0, 1.0F);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        this.field_147916_f.func_147770_b(0.0D, (double)(0.5F + f2 - f), (double)(0.5F - f2), (double)(f + 1.0E-4F), (double)(0.5F + f2), (double)(0.5F + f2));
        this.field_147916_f.func_147800_a(block, 0, 1.0F);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        this.field_147916_f.func_147770_b(0.0D, (double)(0.5F - f2), (double)(0.5F - f2), (double)f, (double)(0.5F + f2), (double)(f + 0.5F - f2));
        this.field_147916_f.func_147800_a(block, 0, 1.0F);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        this.field_147916_f.func_147770_b(0.0D, (double)(0.5F - f2), (double)(0.5F + f2 - f), (double)f, (double)(0.5F + f2), (double)(0.5F + f2));
        this.field_147916_f.func_147800_a(block, 0, 1.0F);
        GL11.glPopMatrix();
        this.field_147916_f.func_147762_c();
        this.field_147916_f.func_147771_a();
        GL11.glPopMatrix();
    }

    private void func_82403_a(EntityItemFrame p_82403_1_)
    {
        GL11.glPushMatrix();
        GL11.glRotatef(p_82403_1_.field_70177_z, 0.0F, 1.0F, 0.0F);
        this.field_76990_c.field_78724_e.func_110577_a(TextureMap.field_110575_b);
        Block block = Blocks.PLANKS;
        float f = 0.0625F;
        float f1 = 0.75F;
        float f2 = f1 / 2.0F;
        GL11.glPushMatrix();
        this.field_147916_f.func_147770_b(0.0D, (double)(0.5F - f2 + 0.0625F), (double)(0.5F - f2 + 0.0625F), (double)(f * 0.5F), (double)(0.5F + f2 - 0.0625F), (double)(0.5F + f2 - 0.0625F));
        this.field_147916_f.func_147757_a(this.field_94147_f);
        this.field_147916_f.func_147800_a(block, 0, 1.0F);
        this.field_147916_f.func_147771_a();
        this.field_147916_f.func_147762_c();
        GL11.glPopMatrix();
        this.field_147916_f.func_147757_a(Blocks.PLANKS.func_149691_a(1, 2));
        GL11.glPushMatrix();
        this.field_147916_f.func_147770_b(0.0D, (double)(0.5F - f2), (double)(0.5F - f2), (double)(f + 1.0E-4F), (double)(f + 0.5F - f2), (double)(0.5F + f2));
        this.field_147916_f.func_147800_a(block, 0, 1.0F);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        this.field_147916_f.func_147770_b(0.0D, (double)(0.5F + f2 - f), (double)(0.5F - f2), (double)(f + 1.0E-4F), (double)(0.5F + f2), (double)(0.5F + f2));
        this.field_147916_f.func_147800_a(block, 0, 1.0F);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        this.field_147916_f.func_147770_b(0.0D, (double)(0.5F - f2), (double)(0.5F - f2), (double)f, (double)(0.5F + f2), (double)(f + 0.5F - f2));
        this.field_147916_f.func_147800_a(block, 0, 1.0F);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        this.field_147916_f.func_147770_b(0.0D, (double)(0.5F - f2), (double)(0.5F + f2 - f), (double)f, (double)(0.5F + f2), (double)(0.5F + f2));
        this.field_147916_f.func_147800_a(block, 0, 1.0F);
        GL11.glPopMatrix();
        this.field_147916_f.func_147762_c();
        this.field_147916_f.func_147771_a();
        GL11.glPopMatrix();
    }

    private void func_82402_b(EntityItemFrame p_82402_1_)
    {
        ItemStack itemstack = p_82402_1_.func_82335_i();

        if (itemstack != null)
        {
            EntityItem entityitem = new EntityItem(p_82402_1_.field_70170_p, 0.0D, 0.0D, 0.0D, itemstack);
            Item item = entityitem.func_92059_d().func_77973_b();
            entityitem.func_92059_d().field_77994_a = 1;
            entityitem.field_70290_d = 0.0F;
            GL11.glPushMatrix();
            GL11.glTranslatef(-0.453125F * (float)Direction.field_71583_a[p_82402_1_.field_82332_a], -0.18F, -0.453125F * (float)Direction.field_71581_b[p_82402_1_.field_82332_a]);
            GL11.glRotatef(180.0F + p_82402_1_.field_70177_z, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef((float)(-90 * p_82402_1_.func_82333_j()), 0.0F, 0.0F, 1.0F);

            switch (p_82402_1_.func_82333_j())
            {
                case 1:
                    GL11.glTranslatef(-0.16F, -0.16F, 0.0F);
                    break;
                case 2:
                    GL11.glTranslatef(0.0F, -0.32F, 0.0F);
                    break;
                case 3:
                    GL11.glTranslatef(0.16F, -0.16F, 0.0F);
            }

            if (item == Items.FILLED_MAP)
            {
                this.field_76990_c.field_78724_e.func_110577_a(field_110789_a);
                Tessellator tessellator = Tessellator.field_78398_a;
                GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
                float f = 0.0078125F;
                GL11.glScalef(f, f, f);

                switch (p_82402_1_.func_82333_j())
                {
                    case 0:
                        GL11.glTranslatef(-64.0F, -87.0F, -1.5F);
                        break;
                    case 1:
                        GL11.glTranslatef(-66.5F, -84.5F, -1.5F);
                        break;
                    case 2:
                        GL11.glTranslatef(-64.0F, -82.0F, -1.5F);
                        break;
                    case 3:
                        GL11.glTranslatef(-61.5F, -84.5F, -1.5F);
                }

                GL11.glNormal3f(0.0F, 0.0F, -1.0F);
                MapData mapdata = Items.FILLED_MAP.func_77873_a(entityitem.func_92059_d(), p_82402_1_.field_70170_p);
                GL11.glTranslatef(0.0F, 0.0F, -1.0F);

                if (mapdata != null)
                {
                    this.field_147917_g.field_71460_t.func_147701_i().func_148250_a(mapdata, true);
                }
            }
            else
            {
                if (item == Items.COMPASS)
                {
                    TextureManager texturemanager = Minecraft.func_71410_x().func_110434_K();
                    texturemanager.func_110577_a(TextureMap.field_110576_c);
                    TextureAtlasSprite textureatlassprite1 = ((TextureMap)texturemanager.func_110581_b(TextureMap.field_110576_c)).func_110572_b(Items.COMPASS.func_77650_f(entityitem.func_92059_d()).func_94215_i());

                    if (textureatlassprite1 instanceof TextureCompass)
                    {
                        TextureCompass texturecompass = (TextureCompass)textureatlassprite1;
                        double d0 = texturecompass.field_94244_i;
                        double d1 = texturecompass.field_94242_j;
                        texturecompass.field_94244_i = 0.0D;
                        texturecompass.field_94242_j = 0.0D;
                        texturecompass.func_94241_a(p_82402_1_.field_70170_p, p_82402_1_.field_70165_t, p_82402_1_.field_70161_v, (double)MathHelper.func_76142_g((float)(180 + p_82402_1_.field_82332_a * 90)), false, true);
                        texturecompass.field_94244_i = d0;
                        texturecompass.field_94242_j = d1;
                    }
                }

                RenderItem.field_82407_g = true;
                RenderManager.field_78727_a.func_147940_a(entityitem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
                RenderItem.field_82407_g = false;

                if (item == Items.COMPASS)
                {
                    TextureAtlasSprite textureatlassprite = ((TextureMap)Minecraft.func_71410_x().func_110434_K().func_110581_b(TextureMap.field_110576_c)).func_110572_b(Items.COMPASS.func_77650_f(entityitem.func_92059_d()).func_94215_i());

                    if (textureatlassprite.func_110970_k() > 0)
                    {
                        textureatlassprite.func_94219_l();
                    }
                }
            }

            GL11.glPopMatrix();
        }
    }

    protected void func_147914_a(EntityItemFrame p_147914_1_, double p_147914_2_, double p_147914_4_, double p_147914_6_)
    {
        if (Minecraft.func_71382_s() && p_147914_1_.func_82335_i() != null && p_147914_1_.func_82335_i().func_82837_s() && this.field_76990_c.field_147941_i == p_147914_1_)
        {
            float f = 1.6F;
            float f1 = 0.016666668F * f;
            double d3 = p_147914_1_.func_70068_e(this.field_76990_c.field_78734_h);
            float f2 = p_147914_1_.func_70093_af() ? 32.0F : 64.0F;

            if (d3 < (double)(f2 * f2))
            {
                String s = p_147914_1_.func_82335_i().func_82833_r();

                if (p_147914_1_.func_70093_af())
                {
                    FontRenderer fontrenderer = this.func_76983_a();
                    GL11.glPushMatrix();
                    GL11.glTranslatef((float)p_147914_2_ + 0.0F, (float)p_147914_4_ + p_147914_1_.field_70131_O + 0.5F, (float)p_147914_6_);
                    GL11.glNormal3f(0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(-this.field_76990_c.field_78735_i, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(this.field_76990_c.field_78732_j, 1.0F, 0.0F, 0.0F);
                    GL11.glScalef(-f1, -f1, f1);
                    GL11.glDisable(GL11.GL_LIGHTING);
                    GL11.glTranslatef(0.0F, 0.25F / f1, 0.0F);
                    GL11.glDepthMask(false);
                    GL11.glEnable(GL11.GL_BLEND);
                    GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
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
                    this.func_147906_a(p_147914_1_, s, p_147914_2_, p_147914_4_, p_147914_6_, 64);
                }
            }
        }
    }

    protected ResourceLocation func_110775_a(Entity p_110775_1_)
    {
        return this.func_110775_a((EntityItemFrame)p_110775_1_);
    }

    public void func_76986_a(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
    {
        this.func_76986_a((EntityItemFrame)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }
}