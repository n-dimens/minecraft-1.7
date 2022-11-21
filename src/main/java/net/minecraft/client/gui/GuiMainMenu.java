package net.minecraft.client.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.resources.I18n;
import net.minecraft.realms.RealmsBridge;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.demo.DemoWorldServer;
import net.minecraft.world.storage.ISaveFormat;
import net.minecraft.world.storage.WorldInfo;
import org.apache.commons.io.Charsets;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;
import org.lwjgl.util.glu.Project;

@SideOnly(Side.CLIENT)
public class GuiMainMenu extends GuiScreen implements GuiYesNoCallback
{
    private static final Logger field_146974_g = LogManager.getLogger();
    private static final Random field_73976_a = new Random();
    private float field_73974_b;
    private String field_73975_c;
    private GuiButton field_73973_d;
    private int field_73979_m;
    private DynamicTexture field_73977_n;
    private final Object field_104025_t = new Object();
    private String field_92025_p;
    private String field_146972_A;
    private String field_104024_v;
    private static final ResourceLocation field_110353_x = new ResourceLocation("texts/splashes.txt");
    private static final ResourceLocation field_110352_y = new ResourceLocation("textures/gui/title/minecraft.png");
    private static final ResourceLocation[] field_73978_o = new ResourceLocation[] {new ResourceLocation("textures/gui/title/background/panorama_0.png"), new ResourceLocation("textures/gui/title/background/panorama_1.png"), new ResourceLocation("textures/gui/title/background/panorama_2.png"), new ResourceLocation("textures/gui/title/background/panorama_3.png"), new ResourceLocation("textures/gui/title/background/panorama_4.png"), new ResourceLocation("textures/gui/title/background/panorama_5.png")};
    public static final String field_96138_a = "Please click " + EnumChatFormatting.UNDERLINE + "here" + EnumChatFormatting.RESET + " for more information.";
    private int field_92024_r;
    private int field_92023_s;
    private int field_92022_t;
    private int field_92021_u;
    private int field_92020_v;
    private int field_92019_w;
    private ResourceLocation field_110351_G;
    private static final String __OBFID = "CL_00001154";

    public GuiMainMenu()
    {
        this.field_146972_A = field_96138_a;
        this.field_73975_c = "missingno";
        BufferedReader bufferedreader = null;

        try
        {
            ArrayList arraylist = new ArrayList();
            bufferedreader = new BufferedReader(new InputStreamReader(Minecraft.func_71410_x().func_110442_L().func_110536_a(field_110353_x).func_110527_b(), Charsets.UTF_8));
            String s;

            while ((s = bufferedreader.readLine()) != null)
            {
                s = s.trim();

                if (!s.isEmpty())
                {
                    arraylist.add(s);
                }
            }

            if (!arraylist.isEmpty())
            {
                do
                {
                    this.field_73975_c = (String)arraylist.get(field_73976_a.nextInt(arraylist.size()));
                }
                while (this.field_73975_c.hashCode() == 125780783);
            }
        }
        catch (IOException ioexception1)
        {
            ;
        }
        finally
        {
            if (bufferedreader != null)
            {
                try
                {
                    bufferedreader.close();
                }
                catch (IOException ioexception)
                {
                    ;
                }
            }
        }

        this.field_73974_b = field_73976_a.nextFloat();
        this.field_92025_p = "";

        if (!GLContext.getCapabilities().OpenGL20 && !OpenGlHelper.func_153193_b())
        {
            this.field_92025_p = I18n.func_135052_a("title.oldgl1", new Object[0]);
            this.field_146972_A = I18n.func_135052_a("title.oldgl2", new Object[0]);
            this.field_104024_v = "https://help.mojang.com/customer/portal/articles/325948?ref=game";
        }
    }

    public void func_73876_c()
    {
        ++this.field_73979_m;
    }

    public boolean func_73868_f()
    {
        return false;
    }

    protected void func_73869_a(char p_73869_1_, int p_73869_2_) {}

    public void func_73866_w_()
    {
        this.field_73977_n = new DynamicTexture(256, 256);
        this.field_110351_G = this.field_146297_k.func_110434_K().func_110578_a("background", this.field_73977_n);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        if (calendar.get(2) + 1 == 11 && calendar.get(5) == 9)
        {
            this.field_73975_c = "Happy birthday, ez!";
        }
        else if (calendar.get(2) + 1 == 6 && calendar.get(5) == 1)
        {
            this.field_73975_c = "Happy birthday, Notch!";
        }
        else if (calendar.get(2) + 1 == 12 && calendar.get(5) == 24)
        {
            this.field_73975_c = "Merry X-mas!";
        }
        else if (calendar.get(2) + 1 == 1 && calendar.get(5) == 1)
        {
            this.field_73975_c = "Happy new year!";
        }
        else if (calendar.get(2) + 1 == 10 && calendar.get(5) == 31)
        {
            this.field_73975_c = "OOoooOOOoooo! Spooky!";
        }

        boolean flag = true;
        int i = this.field_146295_m / 4 + 48;

        if (this.field_146297_k.func_71355_q())
        {
            this.func_73972_b(i, 24);
        }
        else
        {
            this.func_73969_a(i, 24);
        }

        this.field_146292_n.add(new GuiButton(0, this.field_146294_l / 2 - 100, i + 72 + 12, 98, 20, I18n.func_135052_a("menu.options", new Object[0])));
        this.field_146292_n.add(new GuiButton(4, this.field_146294_l / 2 + 2, i + 72 + 12, 98, 20, I18n.func_135052_a("menu.quit", new Object[0])));
        this.field_146292_n.add(new GuiButtonLanguage(5, this.field_146294_l / 2 - 124, i + 72 + 12));
        Object object = this.field_104025_t;

        synchronized (this.field_104025_t)
        {
            this.field_92023_s = this.field_146289_q.func_78256_a(this.field_92025_p);
            this.field_92024_r = this.field_146289_q.func_78256_a(this.field_146972_A);
            int j = Math.max(this.field_92023_s, this.field_92024_r);
            this.field_92022_t = (this.field_146294_l - j) / 2;
            this.field_92021_u = ((GuiButton)this.field_146292_n.get(0)).field_146129_i - 24;
            this.field_92020_v = this.field_92022_t + j;
            this.field_92019_w = this.field_92021_u + 24;
        }
    }

    private void func_73969_a(int p_73969_1_, int p_73969_2_)
    {
        this.field_146292_n.add(new GuiButton(1, this.field_146294_l / 2 - 100, p_73969_1_, I18n.func_135052_a("menu.singleplayer", new Object[0])));
        this.field_146292_n.add(new GuiButton(2, this.field_146294_l / 2 - 100, p_73969_1_ + p_73969_2_ * 1, I18n.func_135052_a("menu.multiplayer", new Object[0])));
        this.field_146292_n.add(new GuiButton(14, this.field_146294_l / 2 - 100, p_73969_1_ + p_73969_2_ * 2, I18n.func_135052_a("menu.online", new Object[0])));
    }

    private void func_73972_b(int p_73972_1_, int p_73972_2_)
    {
        this.field_146292_n.add(new GuiButton(11, this.field_146294_l / 2 - 100, p_73972_1_, I18n.func_135052_a("menu.playdemo", new Object[0])));
        this.field_146292_n.add(this.field_73973_d = new GuiButton(12, this.field_146294_l / 2 - 100, p_73972_1_ + p_73972_2_ * 1, I18n.func_135052_a("menu.resetdemo", new Object[0])));
        ISaveFormat isaveformat = this.field_146297_k.func_71359_d();
        WorldInfo worldinfo = isaveformat.func_75803_c("Demo_World");

        if (worldinfo == null)
        {
            this.field_73973_d.field_146124_l = false;
        }
    }

    protected void func_146284_a(GuiButton p_146284_1_)
    {
        if (p_146284_1_.field_146127_k == 0)
        {
            this.field_146297_k.func_147108_a(new GuiOptions(this, this.field_146297_k.field_71474_y));
        }

        if (p_146284_1_.field_146127_k == 5)
        {
            this.field_146297_k.func_147108_a(new GuiLanguage(this, this.field_146297_k.field_71474_y, this.field_146297_k.func_135016_M()));
        }

        if (p_146284_1_.field_146127_k == 1)
        {
            this.field_146297_k.func_147108_a(new GuiSelectWorld(this));
        }

        if (p_146284_1_.field_146127_k == 2)
        {
            this.field_146297_k.func_147108_a(new GuiMultiplayer(this));
        }

        if (p_146284_1_.field_146127_k == 14)
        {
            this.func_140005_i();
        }

        if (p_146284_1_.field_146127_k == 4)
        {
            this.field_146297_k.func_71400_g();
        }

        if (p_146284_1_.field_146127_k == 11)
        {
            this.field_146297_k.func_71371_a("Demo_World", "Demo_World", DemoWorldServer.field_73071_a);
        }

        if (p_146284_1_.field_146127_k == 12)
        {
            ISaveFormat isaveformat = this.field_146297_k.func_71359_d();
            WorldInfo worldinfo = isaveformat.func_75803_c("Demo_World");

            if (worldinfo != null)
            {
                GuiYesNo guiyesno = GuiSelectWorld.func_152129_a(this, worldinfo.func_76065_j(), 12);
                this.field_146297_k.func_147108_a(guiyesno);
            }
        }
    }

    private void func_140005_i()
    {
        RealmsBridge realmsbridge = new RealmsBridge();
        realmsbridge.switchToRealms(this);
    }

    public void func_73878_a(boolean p_73878_1_, int p_73878_2_)
    {
        if (p_73878_1_ && p_73878_2_ == 12)
        {
            ISaveFormat isaveformat = this.field_146297_k.func_71359_d();
            isaveformat.func_75800_d();
            isaveformat.func_75802_e("Demo_World");
            this.field_146297_k.func_147108_a(this);
        }
        else if (p_73878_2_ == 13)
        {
            if (p_73878_1_)
            {
                try
                {
                    Class oclass = Class.forName("java.awt.Desktop");
                    Object object = oclass.getMethod("getDesktop", new Class[0]).invoke((Object)null, new Object[0]);
                    oclass.getMethod("browse", new Class[] {URI.class}).invoke(object, new Object[] {new URI(this.field_104024_v)});
                }
                catch (Throwable throwable)
                {
                    field_146974_g.error("Couldn\'t open link", throwable);
                }
            }

            this.field_146297_k.func_147108_a(this);
        }
    }

    private void func_73970_b(int p_73970_1_, int p_73970_2_, float p_73970_3_)
    {
        Tessellator tessellator = Tessellator.field_78398_a;
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glPushMatrix();
        GL11.glLoadIdentity();
        Project.gluPerspective(120.0F, 1.0F, 0.05F, 10.0F);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glPushMatrix();
        GL11.glLoadIdentity();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glDisable(GL11.GL_CULL_FACE);
        GL11.glDepthMask(false);
        OpenGlHelper.func_148821_a(770, 771, 1, 0);
        byte b0 = 8;

        for (int k = 0; k < b0 * b0; ++k)
        {
            GL11.glPushMatrix();
            float f1 = ((float)(k % b0) / (float)b0 - 0.5F) / 64.0F;
            float f2 = ((float)(k / b0) / (float)b0 - 0.5F) / 64.0F;
            float f3 = 0.0F;
            GL11.glTranslatef(f1, f2, f3);
            GL11.glRotatef(MathHelper.func_76126_a(((float)this.field_73979_m + p_73970_3_) / 400.0F) * 25.0F + 20.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(-((float)this.field_73979_m + p_73970_3_) * 0.1F, 0.0F, 1.0F, 0.0F);

            for (int l = 0; l < 6; ++l)
            {
                GL11.glPushMatrix();

                if (l == 1)
                {
                    GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
                }

                if (l == 2)
                {
                    GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
                }

                if (l == 3)
                {
                    GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
                }

                if (l == 4)
                {
                    GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
                }

                if (l == 5)
                {
                    GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                }

                this.field_146297_k.func_110434_K().func_110577_a(field_73978_o[l]);
                tessellator.func_78382_b();
                tessellator.func_78384_a(16777215, 255 / (k + 1));
                float f4 = 0.0F;
                tessellator.func_78374_a(-1.0D, -1.0D, 1.0D, (double)(0.0F + f4), (double)(0.0F + f4));
                tessellator.func_78374_a(1.0D, -1.0D, 1.0D, (double)(1.0F - f4), (double)(0.0F + f4));
                tessellator.func_78374_a(1.0D, 1.0D, 1.0D, (double)(1.0F - f4), (double)(1.0F - f4));
                tessellator.func_78374_a(-1.0D, 1.0D, 1.0D, (double)(0.0F + f4), (double)(1.0F - f4));
                tessellator.func_78381_a();
                GL11.glPopMatrix();
            }

            GL11.glPopMatrix();
            GL11.glColorMask(true, true, true, false);
        }

        tessellator.func_78373_b(0.0D, 0.0D, 0.0D);
        GL11.glColorMask(true, true, true, true);
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glPopMatrix();
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glPopMatrix();
        GL11.glDepthMask(true);
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
    }

    private void func_73968_a(float p_73968_1_)
    {
        this.field_146297_k.func_110434_K().func_110577_a(this.field_110351_G);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
        GL11.glCopyTexSubImage2D(GL11.GL_TEXTURE_2D, 0, 0, 0, 0, 0, 256, 256);
        GL11.glEnable(GL11.GL_BLEND);
        OpenGlHelper.func_148821_a(770, 771, 1, 0);
        GL11.glColorMask(true, true, true, false);
        Tessellator tessellator = Tessellator.field_78398_a;
        tessellator.func_78382_b();
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        byte b0 = 3;

        for (int i = 0; i < b0; ++i)
        {
            tessellator.func_78369_a(1.0F, 1.0F, 1.0F, 1.0F / (float)(i + 1));
            int j = this.field_146294_l;
            int k = this.field_146295_m;
            float f1 = (float)(i - b0 / 2) / 256.0F;
            tessellator.func_78374_a((double)j, (double)k, (double)this.field_73735_i, (double)(0.0F + f1), 1.0D);
            tessellator.func_78374_a((double)j, 0.0D, (double)this.field_73735_i, (double)(1.0F + f1), 1.0D);
            tessellator.func_78374_a(0.0D, 0.0D, (double)this.field_73735_i, (double)(1.0F + f1), 0.0D);
            tessellator.func_78374_a(0.0D, (double)k, (double)this.field_73735_i, (double)(0.0F + f1), 0.0D);
        }

        tessellator.func_78381_a();
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glColorMask(true, true, true, true);
    }

    private void func_73971_c(int p_73971_1_, int p_73971_2_, float p_73971_3_)
    {
        this.field_146297_k.func_147110_a().func_147609_e();
        GL11.glViewport(0, 0, 256, 256);
        this.func_73970_b(p_73971_1_, p_73971_2_, p_73971_3_);
        this.func_73968_a(p_73971_3_);
        this.func_73968_a(p_73971_3_);
        this.func_73968_a(p_73971_3_);
        this.func_73968_a(p_73971_3_);
        this.func_73968_a(p_73971_3_);
        this.func_73968_a(p_73971_3_);
        this.func_73968_a(p_73971_3_);
        this.field_146297_k.func_147110_a().func_147610_a(true);
        GL11.glViewport(0, 0, this.field_146297_k.field_71443_c, this.field_146297_k.field_71440_d);
        Tessellator tessellator = Tessellator.field_78398_a;
        tessellator.func_78382_b();
        float f1 = this.field_146294_l > this.field_146295_m ? 120.0F / (float)this.field_146294_l : 120.0F / (float)this.field_146295_m;
        float f2 = (float)this.field_146295_m * f1 / 256.0F;
        float f3 = (float)this.field_146294_l * f1 / 256.0F;
        tessellator.func_78369_a(1.0F, 1.0F, 1.0F, 1.0F);
        int k = this.field_146294_l;
        int l = this.field_146295_m;
        tessellator.func_78374_a(0.0D, (double)l, (double)this.field_73735_i, (double)(0.5F - f2), (double)(0.5F + f3));
        tessellator.func_78374_a((double)k, (double)l, (double)this.field_73735_i, (double)(0.5F - f2), (double)(0.5F - f3));
        tessellator.func_78374_a((double)k, 0.0D, (double)this.field_73735_i, (double)(0.5F + f2), (double)(0.5F - f3));
        tessellator.func_78374_a(0.0D, 0.0D, (double)this.field_73735_i, (double)(0.5F + f2), (double)(0.5F + f3));
        tessellator.func_78381_a();
    }

    public void func_73863_a(int p_73863_1_, int p_73863_2_, float p_73863_3_)
    {
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        this.func_73971_c(p_73863_1_, p_73863_2_, p_73863_3_);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        Tessellator tessellator = Tessellator.field_78398_a;
        short short1 = 274;
        int k = this.field_146294_l / 2 - short1 / 2;
        byte b0 = 30;
        this.func_73733_a(0, 0, this.field_146294_l, this.field_146295_m, -2130706433, 16777215);
        this.func_73733_a(0, 0, this.field_146294_l, this.field_146295_m, 0, Integer.MIN_VALUE);
        this.field_146297_k.func_110434_K().func_110577_a(field_110352_y);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        if ((double)this.field_73974_b < 1.0E-4D)
        {
            this.func_73729_b(k + 0, b0 + 0, 0, 0, 99, 44);
            this.func_73729_b(k + 99, b0 + 0, 129, 0, 27, 44);
            this.func_73729_b(k + 99 + 26, b0 + 0, 126, 0, 3, 44);
            this.func_73729_b(k + 99 + 26 + 3, b0 + 0, 99, 0, 26, 44);
            this.func_73729_b(k + 155, b0 + 0, 0, 45, 155, 44);
        }
        else
        {
            this.func_73729_b(k + 0, b0 + 0, 0, 0, 155, 44);
            this.func_73729_b(k + 155, b0 + 0, 0, 45, 155, 44);
        }

        tessellator.func_78378_d(-1);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)(this.field_146294_l / 2 + 90), 70.0F, 0.0F);
        GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
        float f1 = 1.8F - MathHelper.func_76135_e(MathHelper.func_76126_a((float)(Minecraft.func_71386_F() % 1000L) / 1000.0F * (float)Math.PI * 2.0F) * 0.1F);
        f1 = f1 * 100.0F / (float)(this.field_146289_q.func_78256_a(this.field_73975_c) + 32);
        GL11.glScalef(f1, f1, f1);
        this.func_73732_a(this.field_146289_q, this.field_73975_c, 0, -8, -256);
        GL11.glPopMatrix();
        String s = "Minecraft 1.7.10";

        if (this.field_146297_k.func_71355_q())
        {
            s = s + " Demo";
        }

        this.func_73731_b(this.field_146289_q, s, 2, this.field_146295_m - 10, -1);
        String s1 = "Copyright Mojang AB. Do not distribute!";
        this.func_73731_b(this.field_146289_q, s1, this.field_146294_l - this.field_146289_q.func_78256_a(s1) - 2, this.field_146295_m - 10, -1);

        if (this.field_92025_p != null && this.field_92025_p.length() > 0)
        {
            func_73734_a(this.field_92022_t - 2, this.field_92021_u - 2, this.field_92020_v + 2, this.field_92019_w - 1, 1428160512);
            this.func_73731_b(this.field_146289_q, this.field_92025_p, this.field_92022_t, this.field_92021_u, -1);
            this.func_73731_b(this.field_146289_q, this.field_146972_A, (this.field_146294_l - this.field_92024_r) / 2, ((GuiButton)this.field_146292_n.get(0)).field_146129_i - 12, -1);
        }

        super.func_73863_a(p_73863_1_, p_73863_2_, p_73863_3_);
    }

    protected void func_73864_a(int p_73864_1_, int p_73864_2_, int p_73864_3_)
    {
        super.func_73864_a(p_73864_1_, p_73864_2_, p_73864_3_);
        Object object = this.field_104025_t;

        synchronized (this.field_104025_t)
        {
            if (this.field_92025_p.length() > 0 && p_73864_1_ >= this.field_92022_t && p_73864_1_ <= this.field_92020_v && p_73864_2_ >= this.field_92021_u && p_73864_2_ <= this.field_92019_w)
            {
                GuiConfirmOpenLink guiconfirmopenlink = new GuiConfirmOpenLink(this, this.field_104024_v, 13, true);
                guiconfirmopenlink.func_146358_g();
                this.field_146297_k.func_147108_a(guiconfirmopenlink);
            }
        }
    }
}