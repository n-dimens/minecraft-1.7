package net.minecraft.client.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Color;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.Direction;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.FoodStats;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.chunk.Chunk;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

@SideOnly(Side.CLIENT)
public class GuiIngame extends Gui
{
    protected static final ResourceLocation field_110329_b = new ResourceLocation("textures/misc/vignette.png");
    protected static final ResourceLocation field_110330_c = new ResourceLocation("textures/gui/widgets.png");
    protected static final ResourceLocation field_110328_d = new ResourceLocation("textures/misc/pumpkinblur.png");
    protected static final RenderItem field_73841_b = new RenderItem();
    protected final Random field_73842_c = new Random();
    protected final Minecraft field_73839_d;
    protected final GuiNewChat field_73840_e;
    protected final GuiStreamIndicator field_152127_m;
    protected int field_73837_f;
    protected String field_73838_g = "";
    protected int field_73845_h;
    protected boolean field_73844_j;
    public float field_73843_a = 1.0F;
    protected int field_92017_k;
    protected ItemStack field_92016_l;
    private static final String __OBFID = "CL_00000661";

    public GuiIngame(Minecraft p_i1036_1_)
    {
        this.field_73839_d = p_i1036_1_;
        this.field_73840_e = new GuiNewChat(p_i1036_1_);
        this.field_152127_m = new GuiStreamIndicator(this.field_73839_d);
    }

    public void func_73830_a(float p_73830_1_, boolean p_73830_2_, int p_73830_3_, int p_73830_4_)
    {
        ScaledResolution scaledresolution = new ScaledResolution(this.field_73839_d, this.field_73839_d.field_71443_c, this.field_73839_d.field_71440_d);
        int k = scaledresolution.func_78326_a();
        int l = scaledresolution.func_78328_b();
        FontRenderer fontrenderer = this.field_73839_d.field_71466_p;
        this.field_73839_d.field_71460_t.func_78478_c();
        GL11.glEnable(GL11.GL_BLEND);

        if (Minecraft.func_71375_t())
        {
            this.func_73829_a(this.field_73839_d.field_71439_g.func_70013_c(p_73830_1_), k, l);
        }
        else
        {
            OpenGlHelper.func_148821_a(770, 771, 1, 0);
        }

        ItemStack itemstack = this.field_73839_d.field_71439_g.field_71071_by.func_70440_f(3);

        if (this.field_73839_d.field_71474_y.field_74320_O == 0 && itemstack != null && itemstack.func_77973_b() == Item.func_150898_a(Blocks.field_150423_aK))
        {
            this.func_73836_a(k, l);
        }

        if (!this.field_73839_d.field_71439_g.func_70644_a(Potion.field_76431_k))
        {
            float f1 = this.field_73839_d.field_71439_g.field_71080_cy + (this.field_73839_d.field_71439_g.field_71086_bY - this.field_73839_d.field_71439_g.field_71080_cy) * p_73830_1_;

            if (f1 > 0.0F)
            {
                this.func_130015_b(f1, k, l);
            }
        }

        int i1;
        int j1;
        int k1;

        if (!this.field_73839_d.field_71442_b.func_78747_a())
        {
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.field_73839_d.func_110434_K().func_110577_a(field_110330_c);
            InventoryPlayer inventoryplayer = this.field_73839_d.field_71439_g.field_71071_by;
            this.field_73735_i = -90.0F;
            this.func_73729_b(k / 2 - 91, l - 22, 0, 0, 182, 22);
            this.func_73729_b(k / 2 - 91 - 1 + inventoryplayer.field_70461_c * 20, l - 22 - 1, 0, 22, 24, 22);
            this.field_73839_d.func_110434_K().func_110577_a(field_110324_m);
            GL11.glEnable(GL11.GL_BLEND);
            OpenGlHelper.func_148821_a(775, 769, 1, 0);
            this.func_73729_b(k / 2 - 7, l / 2 - 7, 0, 0, 16, 16);
            OpenGlHelper.func_148821_a(770, 771, 1, 0);
            this.field_73839_d.field_71424_I.func_76320_a("bossHealth");
            this.func_73828_d();
            this.field_73839_d.field_71424_I.func_76319_b();

            if (this.field_73839_d.field_71442_b.func_78755_b())
            {
                this.func_110327_a(k, l);
            }

            this.field_73839_d.field_71424_I.func_76320_a("actionBar");
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
            RenderHelper.func_74520_c();

            for (i1 = 0; i1 < 9; ++i1)
            {
                j1 = k / 2 - 90 + i1 * 20 + 2;
                k1 = l - 16 - 3;
                this.func_73832_a(i1, j1, k1, p_73830_1_);
            }

            RenderHelper.func_74518_a();
            GL11.glDisable(GL12.GL_RESCALE_NORMAL);
            this.field_73839_d.field_71424_I.func_76319_b();
            GL11.glDisable(GL11.GL_BLEND);
        }

        int l4;

        if (this.field_73839_d.field_71439_g.func_71060_bI() > 0)
        {
            this.field_73839_d.field_71424_I.func_76320_a("sleep");
            GL11.glDisable(GL11.GL_DEPTH_TEST);
            GL11.glDisable(GL11.GL_ALPHA_TEST);
            l4 = this.field_73839_d.field_71439_g.func_71060_bI();
            float f2 = (float)l4 / 100.0F;

            if (f2 > 1.0F)
            {
                f2 = 1.0F - (float)(l4 - 100) / 10.0F;
            }

            j1 = (int)(220.0F * f2) << 24 | 1052704;
            func_73734_a(0, 0, k, l, j1);
            GL11.glEnable(GL11.GL_ALPHA_TEST);
            GL11.glEnable(GL11.GL_DEPTH_TEST);
            this.field_73839_d.field_71424_I.func_76319_b();
        }

        l4 = 16777215;
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        i1 = k / 2 - 91;
        int l1;
        int i2;
        int j2;
        int k2;
        float f3;
        short short1;

        if (this.field_73839_d.field_71439_g.func_110317_t())
        {
            this.field_73839_d.field_71424_I.func_76320_a("jumpBar");
            this.field_73839_d.func_110434_K().func_110577_a(Gui.field_110324_m);
            f3 = this.field_73839_d.field_71439_g.func_110319_bJ();
            short1 = 182;
            l1 = (int)(f3 * (float)(short1 + 1));
            i2 = l - 32 + 3;
            this.func_73729_b(i1, i2, 0, 84, short1, 5);

            if (l1 > 0)
            {
                this.func_73729_b(i1, i2, 0, 89, l1, 5);
            }

            this.field_73839_d.field_71424_I.func_76319_b();
        }
        else if (this.field_73839_d.field_71442_b.func_78763_f())
        {
            this.field_73839_d.field_71424_I.func_76320_a("expBar");
            this.field_73839_d.func_110434_K().func_110577_a(Gui.field_110324_m);
            j1 = this.field_73839_d.field_71439_g.func_71050_bK();

            if (j1 > 0)
            {
                short1 = 182;
                l1 = (int)(this.field_73839_d.field_71439_g.field_71106_cc * (float)(short1 + 1));
                i2 = l - 32 + 3;
                this.func_73729_b(i1, i2, 0, 64, short1, 5);

                if (l1 > 0)
                {
                    this.func_73729_b(i1, i2, 0, 69, l1, 5);
                }
            }

            this.field_73839_d.field_71424_I.func_76319_b();

            if (this.field_73839_d.field_71439_g.field_71068_ca > 0)
            {
                this.field_73839_d.field_71424_I.func_76320_a("expLevel");
                boolean flag2 = false;
                l1 = flag2 ? 16777215 : 8453920;
                String s3 = "" + this.field_73839_d.field_71439_g.field_71068_ca;
                j2 = (k - fontrenderer.func_78256_a(s3)) / 2;
                k2 = l - 31 - 4;
                boolean flag1 = false;
                fontrenderer.func_78276_b(s3, j2 + 1, k2, 0);
                fontrenderer.func_78276_b(s3, j2 - 1, k2, 0);
                fontrenderer.func_78276_b(s3, j2, k2 + 1, 0);
                fontrenderer.func_78276_b(s3, j2, k2 - 1, 0);
                fontrenderer.func_78276_b(s3, j2, k2, l1);
                this.field_73839_d.field_71424_I.func_76319_b();
            }
        }

        String s2;

        if (this.field_73839_d.field_71474_y.field_92117_D)
        {
            this.field_73839_d.field_71424_I.func_76320_a("toolHighlight");

            if (this.field_92017_k > 0 && this.field_92016_l != null)
            {
                s2 = this.field_92016_l.func_82833_r();
                k1 = (k - fontrenderer.func_78256_a(s2)) / 2;
                l1 = l - 59;

                if (!this.field_73839_d.field_71442_b.func_78755_b())
                {
                    l1 += 14;
                }

                i2 = (int)((float)this.field_92017_k * 256.0F / 10.0F);

                if (i2 > 255)
                {
                    i2 = 255;
                }

                if (i2 > 0)
                {
                    GL11.glPushMatrix();
                    GL11.glEnable(GL11.GL_BLEND);
                    OpenGlHelper.func_148821_a(770, 771, 1, 0);
                    fontrenderer.func_78261_a(s2, k1, l1, 16777215 + (i2 << 24));
                    GL11.glDisable(GL11.GL_BLEND);
                    GL11.glPopMatrix();
                }
            }

            this.field_73839_d.field_71424_I.func_76319_b();
        }

        if (this.field_73839_d.func_71355_q())
        {
            this.field_73839_d.field_71424_I.func_76320_a("demo");
            s2 = "";

            if (this.field_73839_d.field_71441_e.func_82737_E() >= 120500L)
            {
                s2 = I18n.func_135052_a("demo.demoExpired", new Object[0]);
            }
            else
            {
                s2 = I18n.func_135052_a("demo.remainingTime", new Object[] {StringUtils.func_76337_a((int)(120500L - this.field_73839_d.field_71441_e.func_82737_E()))});
            }

            k1 = fontrenderer.func_78256_a(s2);
            fontrenderer.func_78261_a(s2, k - k1 - 10, 5, 16777215);
            this.field_73839_d.field_71424_I.func_76319_b();
        }

        int i3;
        int j3;
        int k3;

        if (this.field_73839_d.field_71474_y.field_74330_P)
        {
            this.field_73839_d.field_71424_I.func_76320_a("debug");
            GL11.glPushMatrix();
            fontrenderer.func_78261_a("Minecraft 1.7.10 (" + this.field_73839_d.field_71426_K + ")", 2, 2, 16777215);
            fontrenderer.func_78261_a(this.field_73839_d.func_71393_m(), 2, 12, 16777215);
            fontrenderer.func_78261_a(this.field_73839_d.func_71408_n(), 2, 22, 16777215);
            fontrenderer.func_78261_a(this.field_73839_d.func_71374_p(), 2, 32, 16777215);
            fontrenderer.func_78261_a(this.field_73839_d.func_71388_o(), 2, 42, 16777215);
            long i5 = Runtime.getRuntime().maxMemory();
            long j5 = Runtime.getRuntime().totalMemory();
            long k5 = Runtime.getRuntime().freeMemory();
            long l5 = j5 - k5;
            String s = "Used memory: " + l5 * 100L / i5 + "% (" + l5 / 1024L / 1024L + "MB) of " + i5 / 1024L / 1024L + "MB";
            i3 = 14737632;
            this.func_73731_b(fontrenderer, s, k - fontrenderer.func_78256_a(s) - 2, 2, 14737632);
            s = "Allocated memory: " + j5 * 100L / i5 + "% (" + j5 / 1024L / 1024L + "MB)";
            this.func_73731_b(fontrenderer, s, k - fontrenderer.func_78256_a(s) - 2, 12, 14737632);
            j3 = MathHelper.func_76128_c(this.field_73839_d.field_71439_g.field_70165_t);
            k3 = MathHelper.func_76128_c(this.field_73839_d.field_71439_g.field_70163_u);
            int l3 = MathHelper.func_76128_c(this.field_73839_d.field_71439_g.field_70161_v);
            this.func_73731_b(fontrenderer, String.format("x: %.5f (%d) // c: %d (%d)", new Object[] {Double.valueOf(this.field_73839_d.field_71439_g.field_70165_t), Integer.valueOf(j3), Integer.valueOf(j3 >> 4), Integer.valueOf(j3 & 15)}), 2, 64, 14737632);
            this.func_73731_b(fontrenderer, String.format("y: %.3f (feet pos, %.3f eyes pos)", new Object[] {Double.valueOf(this.field_73839_d.field_71439_g.field_70121_D.field_72338_b), Double.valueOf(this.field_73839_d.field_71439_g.field_70163_u)}), 2, 72, 14737632);
            this.func_73731_b(fontrenderer, String.format("z: %.5f (%d) // c: %d (%d)", new Object[] {Double.valueOf(this.field_73839_d.field_71439_g.field_70161_v), Integer.valueOf(l3), Integer.valueOf(l3 >> 4), Integer.valueOf(l3 & 15)}), 2, 80, 14737632);
            int i4 = MathHelper.func_76128_c((double)(this.field_73839_d.field_71439_g.field_70177_z * 4.0F / 360.0F) + 0.5D) & 3;
            this.func_73731_b(fontrenderer, "f: " + i4 + " (" + Direction.field_82373_c[i4] + ") / " + MathHelper.func_76142_g(this.field_73839_d.field_71439_g.field_70177_z), 2, 88, 14737632);

            if (this.field_73839_d.field_71441_e != null && this.field_73839_d.field_71441_e.func_72899_e(j3, k3, l3))
            {
                Chunk chunk = this.field_73839_d.field_71441_e.func_72938_d(j3, l3);
                this.func_73731_b(fontrenderer, "lc: " + (chunk.func_76625_h() + 15) + " b: " + chunk.func_76591_a(j3 & 15, l3 & 15, this.field_73839_d.field_71441_e.func_72959_q()).field_76791_y + " bl: " + chunk.func_76614_a(EnumSkyBlock.Block, j3 & 15, k3, l3 & 15) + " sl: " + chunk.func_76614_a(EnumSkyBlock.Sky, j3 & 15, k3, l3 & 15) + " rl: " + chunk.func_76629_c(j3 & 15, k3, l3 & 15, 0), 2, 96, 14737632);
            }

            this.func_73731_b(fontrenderer, String.format("ws: %.3f, fs: %.3f, g: %b, fl: %d", new Object[] {Float.valueOf(this.field_73839_d.field_71439_g.field_71075_bZ.func_75094_b()), Float.valueOf(this.field_73839_d.field_71439_g.field_71075_bZ.func_75093_a()), Boolean.valueOf(this.field_73839_d.field_71439_g.field_70122_E), Integer.valueOf(this.field_73839_d.field_71441_e.func_72976_f(j3, l3))}), 2, 104, 14737632);

            if (this.field_73839_d.field_71460_t != null && this.field_73839_d.field_71460_t.func_147702_a())
            {
                this.func_73731_b(fontrenderer, String.format("shader: %s", new Object[] {this.field_73839_d.field_71460_t.func_147706_e().func_148022_b()}), 2, 112, 14737632);
            }

            GL11.glPopMatrix();
            this.field_73839_d.field_71424_I.func_76319_b();
        }

        if (this.field_73845_h > 0)
        {
            this.field_73839_d.field_71424_I.func_76320_a("overlayMessage");
            f3 = (float)this.field_73845_h - p_73830_1_;
            k1 = (int)(f3 * 255.0F / 20.0F);

            if (k1 > 255)
            {
                k1 = 255;
            }

            if (k1 > 8)
            {
                GL11.glPushMatrix();
                GL11.glTranslatef((float)(k / 2), (float)(l - 68), 0.0F);
                GL11.glEnable(GL11.GL_BLEND);
                OpenGlHelper.func_148821_a(770, 771, 1, 0);
                l1 = 16777215;

                if (this.field_73844_j)
                {
                    l1 = Color.HSBtoRGB(f3 / 50.0F, 0.7F, 0.6F) & 16777215;
                }

                fontrenderer.func_78276_b(this.field_73838_g, -fontrenderer.func_78256_a(this.field_73838_g) / 2, -4, l1 + (k1 << 24 & -16777216));
                GL11.glDisable(GL11.GL_BLEND);
                GL11.glPopMatrix();
            }

            this.field_73839_d.field_71424_I.func_76319_b();
        }

        ScoreObjective scoreobjective = this.field_73839_d.field_71441_e.func_96441_U().func_96539_a(1);

        if (scoreobjective != null)
        {
            this.func_96136_a(scoreobjective, l, k, fontrenderer);
        }

        GL11.glEnable(GL11.GL_BLEND);
        OpenGlHelper.func_148821_a(770, 771, 1, 0);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glPushMatrix();
        GL11.glTranslatef(0.0F, (float)(l - 48), 0.0F);
        this.field_73839_d.field_71424_I.func_76320_a("chat");
        this.field_73840_e.func_146230_a(this.field_73837_f);
        this.field_73839_d.field_71424_I.func_76319_b();
        GL11.glPopMatrix();
        scoreobjective = this.field_73839_d.field_71441_e.func_96441_U().func_96539_a(0);

        if (this.field_73839_d.field_71474_y.field_74321_H.func_151470_d() && (!this.field_73839_d.func_71387_A() || this.field_73839_d.field_71439_g.field_71174_a.field_147303_b.size() > 1 || scoreobjective != null))
        {
            this.field_73839_d.field_71424_I.func_76320_a("playerList");
            NetHandlerPlayClient nethandlerplayclient = this.field_73839_d.field_71439_g.field_71174_a;
            List list = nethandlerplayclient.field_147303_b;
            i2 = nethandlerplayclient.field_147304_c;
            j2 = i2;

            for (k2 = 1; j2 > 20; j2 = (i2 + k2 - 1) / k2)
            {
                ++k2;
            }

            int i6 = 300 / k2;

            if (i6 > 150)
            {
                i6 = 150;
            }

            int l2 = (k - k2 * i6) / 2;
            byte b0 = 10;
            func_73734_a(l2 - 1, b0 - 1, l2 + i6 * k2, b0 + 9 * j2, Integer.MIN_VALUE);

            for (i3 = 0; i3 < i2; ++i3)
            {
                j3 = l2 + i3 % k2 * i6;
                k3 = b0 + i3 / k2 * 9;
                func_73734_a(j3, k3, j3 + i6 - 1, k3 + 8, 553648127);
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                GL11.glEnable(GL11.GL_ALPHA_TEST);

                if (i3 < list.size())
                {
                    GuiPlayerInfo guiplayerinfo = (GuiPlayerInfo)list.get(i3);
                    ScorePlayerTeam scoreplayerteam = this.field_73839_d.field_71441_e.func_96441_U().func_96509_i(guiplayerinfo.field_78831_a);
                    String s4 = ScorePlayerTeam.func_96667_a(scoreplayerteam, guiplayerinfo.field_78831_a);
                    fontrenderer.func_78261_a(s4, j3, k3, 16777215);

                    if (scoreobjective != null)
                    {
                        int j4 = j3 + fontrenderer.func_78256_a(s4) + 5;
                        int k4 = j3 + i6 - 12 - 5;

                        if (k4 - j4 > 5)
                        {
                            Score score = scoreobjective.func_96682_a().func_96529_a(guiplayerinfo.field_78831_a, scoreobjective);
                            String s1 = EnumChatFormatting.YELLOW + "" + score.func_96652_c();
                            fontrenderer.func_78261_a(s1, k4 - fontrenderer.func_78256_a(s1), k3, 16777215);
                        }
                    }

                    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                    this.field_73839_d.func_110434_K().func_110577_a(field_110324_m);
                    byte b1 = 0;
                    boolean flag3 = false;
                    byte b2;

                    if (guiplayerinfo.field_78829_b < 0)
                    {
                        b2 = 5;
                    }
                    else if (guiplayerinfo.field_78829_b < 150)
                    {
                        b2 = 0;
                    }
                    else if (guiplayerinfo.field_78829_b < 300)
                    {
                        b2 = 1;
                    }
                    else if (guiplayerinfo.field_78829_b < 600)
                    {
                        b2 = 2;
                    }
                    else if (guiplayerinfo.field_78829_b < 1000)
                    {
                        b2 = 3;
                    }
                    else
                    {
                        b2 = 4;
                    }

                    this.field_73735_i += 100.0F;
                    this.func_73729_b(j3 + i6 - 12, k3, 0 + b1 * 10, 176 + b2 * 8, 10, 8);
                    this.field_73735_i -= 100.0F;
                }
            }
        }

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
    }

    public void func_152126_a(float p_152126_1_, float p_152126_2_)
    {
        this.field_152127_m.func_152437_a((int)(p_152126_1_ - 10.0F), 10);
    }

    protected void func_96136_a(ScoreObjective p_96136_1_, int p_96136_2_, int p_96136_3_, FontRenderer p_96136_4_)
    {
        Scoreboard scoreboard = p_96136_1_.func_96682_a();
        Collection collection = scoreboard.func_96534_i(p_96136_1_);

        if (collection.size() <= 15)
        {
            int k = p_96136_4_.func_78256_a(p_96136_1_.func_96678_d());
            String s;

            for (Iterator iterator = collection.iterator(); iterator.hasNext(); k = Math.max(k, p_96136_4_.func_78256_a(s)))
            {
                Score score = (Score)iterator.next();
                ScorePlayerTeam scoreplayerteam = scoreboard.func_96509_i(score.func_96653_e());
                s = ScorePlayerTeam.func_96667_a(scoreplayerteam, score.func_96653_e()) + ": " + EnumChatFormatting.RED + score.func_96652_c();
            }

            int k1 = collection.size() * p_96136_4_.field_78288_b;
            int l1 = p_96136_2_ / 2 + k1 / 3;
            byte b0 = 3;
            int i2 = p_96136_3_ - k - b0;
            int l = 0;
            Iterator iterator1 = collection.iterator();

            while (iterator1.hasNext())
            {
                Score score1 = (Score)iterator1.next();
                ++l;
                ScorePlayerTeam scoreplayerteam1 = scoreboard.func_96509_i(score1.func_96653_e());
                String s1 = ScorePlayerTeam.func_96667_a(scoreplayerteam1, score1.func_96653_e());
                String s2 = EnumChatFormatting.RED + "" + score1.func_96652_c();
                int i1 = l1 - l * p_96136_4_.field_78288_b;
                int j1 = p_96136_3_ - b0 + 2;
                func_73734_a(i2 - 2, i1, j1, i1 + p_96136_4_.field_78288_b, 1342177280);
                p_96136_4_.func_78276_b(s1, i2, i1, 553648127);
                p_96136_4_.func_78276_b(s2, j1 - p_96136_4_.func_78256_a(s2), i1, 553648127);

                if (l == collection.size())
                {
                    String s3 = p_96136_1_.func_96678_d();
                    func_73734_a(i2 - 2, i1 - p_96136_4_.field_78288_b - 1, j1, i1 - 1, 1610612736);
                    func_73734_a(i2 - 2, i1 - 1, j1, i1, 1342177280);
                    p_96136_4_.func_78276_b(s3, i2 + k / 2 - p_96136_4_.func_78256_a(s3) / 2, i1 - p_96136_4_.field_78288_b, 553648127);
                }
            }
        }
    }

    protected void func_110327_a(int p_110327_1_, int p_110327_2_)
    {
        boolean flag = this.field_73839_d.field_71439_g.field_70172_ad / 3 % 2 == 1;

        if (this.field_73839_d.field_71439_g.field_70172_ad < 10)
        {
            flag = false;
        }

        int k = MathHelper.func_76123_f(this.field_73839_d.field_71439_g.func_110143_aJ());
        int l = MathHelper.func_76123_f(this.field_73839_d.field_71439_g.field_70735_aL);
        this.field_73842_c.setSeed((long)(this.field_73837_f * 312871));
        boolean flag1 = false;
        FoodStats foodstats = this.field_73839_d.field_71439_g.func_71024_bL();
        int i1 = foodstats.func_75116_a();
        int j1 = foodstats.func_75120_b();
        IAttributeInstance iattributeinstance = this.field_73839_d.field_71439_g.func_110148_a(SharedMonsterAttributes.field_111267_a);
        int k1 = p_110327_1_ / 2 - 91;
        int l1 = p_110327_1_ / 2 + 91;
        int i2 = p_110327_2_ - 39;
        float f = (float)iattributeinstance.func_111126_e();
        float f1 = this.field_73839_d.field_71439_g.func_110139_bj();
        int j2 = MathHelper.func_76123_f((f + f1) / 2.0F / 10.0F);
        int k2 = Math.max(10 - (j2 - 2), 3);
        int l2 = i2 - (j2 - 1) * k2 - 10;
        float f2 = f1;
        int i3 = this.field_73839_d.field_71439_g.func_70658_aO();
        int j3 = -1;

        if (this.field_73839_d.field_71439_g.func_70644_a(Potion.field_76428_l))
        {
            j3 = this.field_73837_f % MathHelper.func_76123_f(f + 5.0F);
        }

        this.field_73839_d.field_71424_I.func_76320_a("armor");
        int k3;
        int l3;

        for (k3 = 0; k3 < 10; ++k3)
        {
            if (i3 > 0)
            {
                l3 = k1 + k3 * 8;

                if (k3 * 2 + 1 < i3)
                {
                    this.func_73729_b(l3, l2, 34, 9, 9, 9);
                }

                if (k3 * 2 + 1 == i3)
                {
                    this.func_73729_b(l3, l2, 25, 9, 9, 9);
                }

                if (k3 * 2 + 1 > i3)
                {
                    this.func_73729_b(l3, l2, 16, 9, 9, 9);
                }
            }
        }

        this.field_73839_d.field_71424_I.func_76318_c("health");
        int i4;
        int j4;
        int k4;

        for (k3 = MathHelper.func_76123_f((f + f1) / 2.0F) - 1; k3 >= 0; --k3)
        {
            l3 = 16;

            if (this.field_73839_d.field_71439_g.func_70644_a(Potion.field_76436_u))
            {
                l3 += 36;
            }
            else if (this.field_73839_d.field_71439_g.func_70644_a(Potion.field_82731_v))
            {
                l3 += 72;
            }

            byte b0 = 0;

            if (flag)
            {
                b0 = 1;
            }

            i4 = MathHelper.func_76123_f((float)(k3 + 1) / 10.0F) - 1;
            j4 = k1 + k3 % 10 * 8;
            k4 = i2 - i4 * k2;

            if (k <= 4)
            {
                k4 += this.field_73842_c.nextInt(2);
            }

            if (k3 == j3)
            {
                k4 -= 2;
            }

            byte b1 = 0;

            if (this.field_73839_d.field_71441_e.func_72912_H().func_76093_s())
            {
                b1 = 5;
            }

            this.func_73729_b(j4, k4, 16 + b0 * 9, 9 * b1, 9, 9);

            if (flag)
            {
                if (k3 * 2 + 1 < l)
                {
                    this.func_73729_b(j4, k4, l3 + 54, 9 * b1, 9, 9);
                }

                if (k3 * 2 + 1 == l)
                {
                    this.func_73729_b(j4, k4, l3 + 63, 9 * b1, 9, 9);
                }
            }

            if (f2 > 0.0F)
            {
                if (f2 == f1 && f1 % 2.0F == 1.0F)
                {
                    this.func_73729_b(j4, k4, l3 + 153, 9 * b1, 9, 9);
                }
                else
                {
                    this.func_73729_b(j4, k4, l3 + 144, 9 * b1, 9, 9);
                }

                f2 -= 2.0F;
            }
            else
            {
                if (k3 * 2 + 1 < k)
                {
                    this.func_73729_b(j4, k4, l3 + 36, 9 * b1, 9, 9);
                }

                if (k3 * 2 + 1 == k)
                {
                    this.func_73729_b(j4, k4, l3 + 45, 9 * b1, 9, 9);
                }
            }
        }

        Entity entity = this.field_73839_d.field_71439_g.field_70154_o;
        int k5;

        if (entity == null)
        {
            this.field_73839_d.field_71424_I.func_76318_c("food");

            for (l3 = 0; l3 < 10; ++l3)
            {
                k5 = i2;
                i4 = 16;
                byte b4 = 0;

                if (this.field_73839_d.field_71439_g.func_70644_a(Potion.field_76438_s))
                {
                    i4 += 36;
                    b4 = 13;
                }

                if (this.field_73839_d.field_71439_g.func_71024_bL().func_75115_e() <= 0.0F && this.field_73837_f % (i1 * 3 + 1) == 0)
                {
                    k5 = i2 + (this.field_73842_c.nextInt(3) - 1);
                }

                if (flag1)
                {
                    b4 = 1;
                }

                k4 = l1 - l3 * 8 - 9;
                this.func_73729_b(k4, k5, 16 + b4 * 9, 27, 9, 9);

                if (flag1)
                {
                    if (l3 * 2 + 1 < j1)
                    {
                        this.func_73729_b(k4, k5, i4 + 54, 27, 9, 9);
                    }

                    if (l3 * 2 + 1 == j1)
                    {
                        this.func_73729_b(k4, k5, i4 + 63, 27, 9, 9);
                    }
                }

                if (l3 * 2 + 1 < i1)
                {
                    this.func_73729_b(k4, k5, i4 + 36, 27, 9, 9);
                }

                if (l3 * 2 + 1 == i1)
                {
                    this.func_73729_b(k4, k5, i4 + 45, 27, 9, 9);
                }
            }
        }
        else if (entity instanceof EntityLivingBase)
        {
            this.field_73839_d.field_71424_I.func_76318_c("mountHealth");
            EntityLivingBase entitylivingbase = (EntityLivingBase)entity;
            k5 = (int)Math.ceil((double)entitylivingbase.func_110143_aJ());
            float f3 = entitylivingbase.func_110138_aP();
            j4 = (int)(f3 + 0.5F) / 2;

            if (j4 > 30)
            {
                j4 = 30;
            }

            k4 = i2;

            for (int l5 = 0; j4 > 0; l5 += 20)
            {
                int l4 = Math.min(j4, 10);
                j4 -= l4;

                for (int i5 = 0; i5 < l4; ++i5)
                {
                    byte b2 = 52;
                    byte b3 = 0;

                    if (flag1)
                    {
                        b3 = 1;
                    }

                    int j5 = l1 - i5 * 8 - 9;
                    this.func_73729_b(j5, k4, b2 + b3 * 9, 9, 9, 9);

                    if (i5 * 2 + 1 + l5 < k5)
                    {
                        this.func_73729_b(j5, k4, b2 + 36, 9, 9, 9);
                    }

                    if (i5 * 2 + 1 + l5 == k5)
                    {
                        this.func_73729_b(j5, k4, b2 + 45, 9, 9, 9);
                    }
                }

                k4 -= 10;
            }
        }

        this.field_73839_d.field_71424_I.func_76318_c("air");

        if (this.field_73839_d.field_71439_g.func_70055_a(Material.field_151586_h))
        {
            l3 = this.field_73839_d.field_71439_g.func_70086_ai();
            k5 = MathHelper.func_76143_f((double)(l3 - 2) * 10.0D / 300.0D);
            i4 = MathHelper.func_76143_f((double)l3 * 10.0D / 300.0D) - k5;

            for (j4 = 0; j4 < k5 + i4; ++j4)
            {
                if (j4 < k5)
                {
                    this.func_73729_b(l1 - j4 * 8 - 9, l2, 16, 18, 9, 9);
                }
                else
                {
                    this.func_73729_b(l1 - j4 * 8 - 9, l2, 25, 18, 9, 9);
                }
            }
        }

        this.field_73839_d.field_71424_I.func_76319_b();
    }

    protected void func_73828_d()
    {
        if (BossStatus.field_82827_c != null && BossStatus.field_82826_b > 0)
        {
            --BossStatus.field_82826_b;
            FontRenderer fontrenderer = this.field_73839_d.field_71466_p;
            ScaledResolution scaledresolution = new ScaledResolution(this.field_73839_d, this.field_73839_d.field_71443_c, this.field_73839_d.field_71440_d);
            int i = scaledresolution.func_78326_a();
            short short1 = 182;
            int j = i / 2 - short1 / 2;
            int k = (int)(BossStatus.field_82828_a * (float)(short1 + 1));
            byte b0 = 12;
            this.func_73729_b(j, b0, 0, 74, short1, 5);
            this.func_73729_b(j, b0, 0, 74, short1, 5);

            if (k > 0)
            {
                this.func_73729_b(j, b0, 0, 79, k, 5);
            }

            String s = BossStatus.field_82827_c;
            fontrenderer.func_78261_a(s, i / 2 - fontrenderer.func_78256_a(s) / 2, b0 - 10, 16777215);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.field_73839_d.func_110434_K().func_110577_a(field_110324_m);
        }
    }

    protected void func_73836_a(int p_73836_1_, int p_73836_2_)
    {
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(false);
        OpenGlHelper.func_148821_a(770, 771, 1, 0);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        this.field_73839_d.func_110434_K().func_110577_a(field_110328_d);
        Tessellator tessellator = Tessellator.field_78398_a;
        tessellator.func_78382_b();
        tessellator.func_78374_a(0.0D, (double)p_73836_2_, -90.0D, 0.0D, 1.0D);
        tessellator.func_78374_a((double)p_73836_1_, (double)p_73836_2_, -90.0D, 1.0D, 1.0D);
        tessellator.func_78374_a((double)p_73836_1_, 0.0D, -90.0D, 1.0D, 0.0D);
        tessellator.func_78374_a(0.0D, 0.0D, -90.0D, 0.0D, 0.0D);
        tessellator.func_78381_a();
        GL11.glDepthMask(true);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

    protected void func_73829_a(float p_73829_1_, int p_73829_2_, int p_73829_3_)
    {
        p_73829_1_ = 1.0F - p_73829_1_;

        if (p_73829_1_ < 0.0F)
        {
            p_73829_1_ = 0.0F;
        }

        if (p_73829_1_ > 1.0F)
        {
            p_73829_1_ = 1.0F;
        }

        this.field_73843_a = (float)((double)this.field_73843_a + (double)(p_73829_1_ - this.field_73843_a) * 0.01D);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(false);
        OpenGlHelper.func_148821_a(0, 769, 1, 0);
        GL11.glColor4f(this.field_73843_a, this.field_73843_a, this.field_73843_a, 1.0F);
        this.field_73839_d.func_110434_K().func_110577_a(field_110329_b);
        Tessellator tessellator = Tessellator.field_78398_a;
        tessellator.func_78382_b();
        tessellator.func_78374_a(0.0D, (double)p_73829_3_, -90.0D, 0.0D, 1.0D);
        tessellator.func_78374_a((double)p_73829_2_, (double)p_73829_3_, -90.0D, 1.0D, 1.0D);
        tessellator.func_78374_a((double)p_73829_2_, 0.0D, -90.0D, 1.0D, 0.0D);
        tessellator.func_78374_a(0.0D, 0.0D, -90.0D, 0.0D, 0.0D);
        tessellator.func_78381_a();
        GL11.glDepthMask(true);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        OpenGlHelper.func_148821_a(770, 771, 1, 0);
    }

    protected void func_130015_b(float p_130015_1_, int p_130015_2_, int p_130015_3_)
    {
        if (p_130015_1_ < 1.0F)
        {
            p_130015_1_ *= p_130015_1_;
            p_130015_1_ *= p_130015_1_;
            p_130015_1_ = p_130015_1_ * 0.8F + 0.2F;
        }

        GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(false);
        OpenGlHelper.func_148821_a(770, 771, 1, 0);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, p_130015_1_);
        IIcon iicon = Blocks.field_150427_aO.func_149733_h(1);
        this.field_73839_d.func_110434_K().func_110577_a(TextureMap.field_110575_b);
        float f1 = iicon.func_94209_e();
        float f2 = iicon.func_94206_g();
        float f3 = iicon.func_94212_f();
        float f4 = iicon.func_94210_h();
        Tessellator tessellator = Tessellator.field_78398_a;
        tessellator.func_78382_b();
        tessellator.func_78374_a(0.0D, (double)p_130015_3_, -90.0D, (double)f1, (double)f4);
        tessellator.func_78374_a((double)p_130015_2_, (double)p_130015_3_, -90.0D, (double)f3, (double)f4);
        tessellator.func_78374_a((double)p_130015_2_, 0.0D, -90.0D, (double)f3, (double)f2);
        tessellator.func_78374_a(0.0D, 0.0D, -90.0D, (double)f1, (double)f2);
        tessellator.func_78381_a();
        GL11.glDepthMask(true);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

    protected void func_73832_a(int p_73832_1_, int p_73832_2_, int p_73832_3_, float p_73832_4_)
    {
        ItemStack itemstack = this.field_73839_d.field_71439_g.field_71071_by.field_70462_a[p_73832_1_];

        if (itemstack != null)
        {
            float f1 = (float)itemstack.field_77992_b - p_73832_4_;

            if (f1 > 0.0F)
            {
                GL11.glPushMatrix();
                float f2 = 1.0F + f1 / 5.0F;
                GL11.glTranslatef((float)(p_73832_2_ + 8), (float)(p_73832_3_ + 12), 0.0F);
                GL11.glScalef(1.0F / f2, (f2 + 1.0F) / 2.0F, 1.0F);
                GL11.glTranslatef((float)(-(p_73832_2_ + 8)), (float)(-(p_73832_3_ + 12)), 0.0F);
            }

            field_73841_b.func_82406_b(this.field_73839_d.field_71466_p, this.field_73839_d.func_110434_K(), itemstack, p_73832_2_, p_73832_3_);

            if (f1 > 0.0F)
            {
                GL11.glPopMatrix();
            }

            field_73841_b.func_77021_b(this.field_73839_d.field_71466_p, this.field_73839_d.func_110434_K(), itemstack, p_73832_2_, p_73832_3_);
        }
    }

    public void func_73831_a()
    {
        if (this.field_73845_h > 0)
        {
            --this.field_73845_h;
        }

        ++this.field_73837_f;
        this.field_152127_m.func_152439_a();

        if (this.field_73839_d.field_71439_g != null)
        {
            ItemStack itemstack = this.field_73839_d.field_71439_g.field_71071_by.func_70448_g();

            if (itemstack == null)
            {
                this.field_92017_k = 0;
            }
            else if (this.field_92016_l != null && itemstack.func_77973_b() == this.field_92016_l.func_77973_b() && ItemStack.func_77970_a(itemstack, this.field_92016_l) && (itemstack.func_77984_f() || itemstack.func_77960_j() == this.field_92016_l.func_77960_j()))
            {
                if (this.field_92017_k > 0)
                {
                    --this.field_92017_k;
                }
            }
            else
            {
                this.field_92017_k = 40;
            }

            this.field_92016_l = itemstack;
        }
    }

    public void func_73833_a(String p_73833_1_)
    {
        this.func_110326_a(I18n.func_135052_a("record.nowPlaying", new Object[] {p_73833_1_}), true);
    }

    public void func_110326_a(String p_110326_1_, boolean p_110326_2_)
    {
        this.field_73838_g = p_110326_1_;
        this.field_73845_h = 60;
        this.field_73844_j = p_110326_2_;
    }

    public GuiNewChat func_146158_b()
    {
        return this.field_73840_e;
    }

    public int func_73834_c()
    {
        return this.field_73837_f;
    }
}