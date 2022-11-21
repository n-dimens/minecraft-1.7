package net.minecraft.client.gui;

import com.ibm.icu.text.ArabicShaping;
import com.ibm.icu.text.ArabicShapingException;
import com.ibm.icu.text.Bidi;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class FontRenderer implements IResourceManagerReloadListener
{
    private static final ResourceLocation[] field_111274_c = new ResourceLocation[256];
    protected int[] field_78286_d = new int[256];
    public int field_78288_b = 9;
    public Random field_78289_c = new Random();
    protected byte[] field_78287_e = new byte[65536];
    private int[] field_78285_g = new int[32];
    protected final ResourceLocation field_111273_g;
    private final TextureManager field_78298_i;
    protected float field_78295_j;
    protected float field_78296_k;
    private boolean field_78293_l;
    private boolean field_78294_m;
    private float field_78291_n;
    private float field_78292_o;
    private float field_78306_p;
    private float field_78305_q;
    private int field_78304_r;
    private boolean field_78303_s;
    private boolean field_78302_t;
    private boolean field_78301_u;
    private boolean field_78300_v;
    private boolean field_78299_w;
    private static final String __OBFID = "CL_00000660";

    public FontRenderer(GameSettings p_i1035_1_, ResourceLocation p_i1035_2_, TextureManager p_i1035_3_, boolean p_i1035_4_)
    {
        this.field_111273_g = p_i1035_2_;
        this.field_78298_i = p_i1035_3_;
        this.field_78293_l = p_i1035_4_;
        p_i1035_3_.func_110577_a(this.field_111273_g);

        for (int i = 0; i < 32; ++i)
        {
            int j = (i >> 3 & 1) * 85;
            int k = (i >> 2 & 1) * 170 + j;
            int l = (i >> 1 & 1) * 170 + j;
            int i1 = (i >> 0 & 1) * 170 + j;

            if (i == 6)
            {
                k += 85;
            }

            if (p_i1035_1_.field_74337_g)
            {
                int j1 = (k * 30 + l * 59 + i1 * 11) / 100;
                int k1 = (k * 30 + l * 70) / 100;
                int l1 = (k * 30 + i1 * 70) / 100;
                k = j1;
                l = k1;
                i1 = l1;
            }

            if (i >= 16)
            {
                k /= 4;
                l /= 4;
                i1 /= 4;
            }

            this.field_78285_g[i] = (k & 255) << 16 | (l & 255) << 8 | i1 & 255;
        }

        this.func_98306_d();
    }

    public void func_110549_a(IResourceManager p_110549_1_)
    {
        this.func_111272_d();
    }

    private void func_111272_d()
    {
        BufferedImage bufferedimage;

        try
        {
            bufferedimage = ImageIO.read(Minecraft.func_71410_x().func_110442_L().func_110536_a(this.field_111273_g).func_110527_b());
        }
        catch (IOException ioexception)
        {
            throw new RuntimeException(ioexception);
        }

        int i = bufferedimage.getWidth();
        int j = bufferedimage.getHeight();
        int[] aint = new int[i * j];
        bufferedimage.getRGB(0, 0, i, j, aint, 0, i);
        int k = j / 16;
        int l = i / 16;
        byte b0 = 1;
        float f = 8.0F / (float)l;
        int i1 = 0;

        while (i1 < 256)
        {
            int j1 = i1 % 16;
            int k1 = i1 / 16;

            if (i1 == 32)
            {
                this.field_78286_d[i1] = 3 + b0;
            }

            int l1 = l - 1;

            while (true)
            {
                if (l1 >= 0)
                {
                    int i2 = j1 * l + l1;
                    boolean flag = true;

                    for (int j2 = 0; j2 < k && flag; ++j2)
                    {
                        int k2 = (k1 * l + j2) * i;

                        if ((aint[i2 + k2] >> 24 & 255) != 0)
                        {
                            flag = false;
                        }
                    }

                    if (flag)
                    {
                        --l1;
                        continue;
                    }
                }

                ++l1;
                this.field_78286_d[i1] = (int)(0.5D + (double)((float)l1 * f)) + b0;
                ++i1;
                break;
            }
        }
    }

    private void func_98306_d()
    {
        try
        {
            InputStream inputstream = Minecraft.func_71410_x().func_110442_L().func_110536_a(new ResourceLocation("font/glyph_sizes.bin")).func_110527_b();
            inputstream.read(this.field_78287_e);
        }
        catch (IOException ioexception)
        {
            throw new RuntimeException(ioexception);
        }
    }

    private float func_78278_a(int p_78278_1_, char p_78278_2_, boolean p_78278_3_)
    {
        return p_78278_2_ == 32 ? 4.0F : ("\u00c0\u00c1\u00c2\u00c8\u00ca\u00cb\u00cd\u00d3\u00d4\u00d5\u00da\u00df\u00e3\u00f5\u011f\u0130\u0131\u0152\u0153\u015e\u015f\u0174\u0175\u017e\u0207\u0000\u0000\u0000\u0000\u0000\u0000\u0000 !\"#$%&\'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u0000\u00c7\u00fc\u00e9\u00e2\u00e4\u00e0\u00e5\u00e7\u00ea\u00eb\u00e8\u00ef\u00ee\u00ec\u00c4\u00c5\u00c9\u00e6\u00c6\u00f4\u00f6\u00f2\u00fb\u00f9\u00ff\u00d6\u00dc\u00f8\u00a3\u00d8\u00d7\u0192\u00e1\u00ed\u00f3\u00fa\u00f1\u00d1\u00aa\u00ba\u00bf\u00ae\u00ac\u00bd\u00bc\u00a1\u00ab\u00bb\u2591\u2592\u2593\u2502\u2524\u2561\u2562\u2556\u2555\u2563\u2551\u2557\u255d\u255c\u255b\u2510\u2514\u2534\u252c\u251c\u2500\u253c\u255e\u255f\u255a\u2554\u2569\u2566\u2560\u2550\u256c\u2567\u2568\u2564\u2565\u2559\u2558\u2552\u2553\u256b\u256a\u2518\u250c\u2588\u2584\u258c\u2590\u2580\u03b1\u03b2\u0393\u03c0\u03a3\u03c3\u03bc\u03c4\u03a6\u0398\u03a9\u03b4\u221e\u2205\u2208\u2229\u2261\u00b1\u2265\u2264\u2320\u2321\u00f7\u2248\u00b0\u2219\u00b7\u221a\u207f\u00b2\u25a0\u0000".indexOf(p_78278_2_) != -1 && !this.field_78293_l ? this.func_78266_a(p_78278_1_, p_78278_3_) : this.func_78277_a(p_78278_2_, p_78278_3_));
    }

    protected float func_78266_a(int p_78266_1_, boolean p_78266_2_)
    {
        float f = (float)(p_78266_1_ % 16 * 8);
        float f1 = (float)(p_78266_1_ / 16 * 8);
        float f2 = p_78266_2_ ? 1.0F : 0.0F;
        this.field_78298_i.func_110577_a(this.field_111273_g);
        float f3 = (float)this.field_78286_d[p_78266_1_] - 0.01F;
        GL11.glBegin(GL11.GL_TRIANGLE_STRIP);
        GL11.glTexCoord2f(f / 128.0F, f1 / 128.0F);
        GL11.glVertex3f(this.field_78295_j + f2, this.field_78296_k, 0.0F);
        GL11.glTexCoord2f(f / 128.0F, (f1 + 7.99F) / 128.0F);
        GL11.glVertex3f(this.field_78295_j - f2, this.field_78296_k + 7.99F, 0.0F);
        GL11.glTexCoord2f((f + f3 - 1.0F) / 128.0F, f1 / 128.0F);
        GL11.glVertex3f(this.field_78295_j + f3 - 1.0F + f2, this.field_78296_k, 0.0F);
        GL11.glTexCoord2f((f + f3 - 1.0F) / 128.0F, (f1 + 7.99F) / 128.0F);
        GL11.glVertex3f(this.field_78295_j + f3 - 1.0F - f2, this.field_78296_k + 7.99F, 0.0F);
        GL11.glEnd();
        return (float)this.field_78286_d[p_78266_1_];
    }

    private ResourceLocation func_111271_a(int p_111271_1_)
    {
        if (field_111274_c[p_111271_1_] == null)
        {
            field_111274_c[p_111271_1_] = new ResourceLocation(String.format("textures/font/unicode_page_%02x.png", new Object[] {Integer.valueOf(p_111271_1_)}));
        }

        return field_111274_c[p_111271_1_];
    }

    private void func_78257_a(int p_78257_1_)
    {
        this.field_78298_i.func_110577_a(this.func_111271_a(p_78257_1_));
    }

    protected float func_78277_a(char p_78277_1_, boolean p_78277_2_)
    {
        if (this.field_78287_e[p_78277_1_] == 0)
        {
            return 0.0F;
        }
        else
        {
            int i = p_78277_1_ / 256;
            this.func_78257_a(i);
            int j = this.field_78287_e[p_78277_1_] >>> 4;
            int k = this.field_78287_e[p_78277_1_] & 15;
            float f = (float)j;
            float f1 = (float)(k + 1);
            float f2 = (float)(p_78277_1_ % 16 * 16) + f;
            float f3 = (float)((p_78277_1_ & 255) / 16 * 16);
            float f4 = f1 - f - 0.02F;
            float f5 = p_78277_2_ ? 1.0F : 0.0F;
            GL11.glBegin(GL11.GL_TRIANGLE_STRIP);
            GL11.glTexCoord2f(f2 / 256.0F, f3 / 256.0F);
            GL11.glVertex3f(this.field_78295_j + f5, this.field_78296_k, 0.0F);
            GL11.glTexCoord2f(f2 / 256.0F, (f3 + 15.98F) / 256.0F);
            GL11.glVertex3f(this.field_78295_j - f5, this.field_78296_k + 7.99F, 0.0F);
            GL11.glTexCoord2f((f2 + f4) / 256.0F, f3 / 256.0F);
            GL11.glVertex3f(this.field_78295_j + f4 / 2.0F + f5, this.field_78296_k, 0.0F);
            GL11.glTexCoord2f((f2 + f4) / 256.0F, (f3 + 15.98F) / 256.0F);
            GL11.glVertex3f(this.field_78295_j + f4 / 2.0F - f5, this.field_78296_k + 7.99F, 0.0F);
            GL11.glEnd();
            return (f1 - f) / 2.0F + 1.0F;
        }
    }

    public int func_78261_a(String p_78261_1_, int p_78261_2_, int p_78261_3_, int p_78261_4_)
    {
        return this.func_85187_a(p_78261_1_, p_78261_2_, p_78261_3_, p_78261_4_, true);
    }

    public int func_78276_b(String p_78276_1_, int p_78276_2_, int p_78276_3_, int p_78276_4_)
    {
        return this.func_85187_a(p_78276_1_, p_78276_2_, p_78276_3_, p_78276_4_, false);
    }

    public int func_85187_a(String p_85187_1_, int p_85187_2_, int p_85187_3_, int p_85187_4_, boolean p_85187_5_)
    {
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        this.func_78265_b();
        int l;

        if (p_85187_5_)
        {
            l = this.func_78258_a(p_85187_1_, p_85187_2_ + 1, p_85187_3_ + 1, p_85187_4_, true);
            l = Math.max(l, this.func_78258_a(p_85187_1_, p_85187_2_, p_85187_3_, p_85187_4_, false));
        }
        else
        {
            l = this.func_78258_a(p_85187_1_, p_85187_2_, p_85187_3_, p_85187_4_, false);
        }

        return l;
    }

    private String func_147647_b(String p_147647_1_)
    {
        try
        {
            Bidi bidi = new Bidi((new ArabicShaping(8)).shape(p_147647_1_), 127);
            bidi.setReorderingMode(0);
            return bidi.writeReordered(2);
        }
        catch (ArabicShapingException arabicshapingexception)
        {
            return p_147647_1_;
        }
    }

    private void func_78265_b()
    {
        this.field_78303_s = false;
        this.field_78302_t = false;
        this.field_78301_u = false;
        this.field_78300_v = false;
        this.field_78299_w = false;
    }

    private void func_78255_a(String p_78255_1_, boolean p_78255_2_)
    {
        for (int i = 0; i < p_78255_1_.length(); ++i)
        {
            char c0 = p_78255_1_.charAt(i);
            int j;
            int k;

            if (c0 == 167 && i + 1 < p_78255_1_.length())
            {
                j = "0123456789abcdefklmnor".indexOf(p_78255_1_.toLowerCase().charAt(i + 1));

                if (j < 16)
                {
                    this.field_78303_s = false;
                    this.field_78302_t = false;
                    this.field_78299_w = false;
                    this.field_78300_v = false;
                    this.field_78301_u = false;

                    if (j < 0 || j > 15)
                    {
                        j = 15;
                    }

                    if (p_78255_2_)
                    {
                        j += 16;
                    }

                    k = this.field_78285_g[j];
                    this.field_78304_r = k;
                    GL11.glColor4f((float)(k >> 16) / 255.0F, (float)(k >> 8 & 255) / 255.0F, (float)(k & 255) / 255.0F, this.field_78305_q);
                }
                else if (j == 16)
                {
                    this.field_78303_s = true;
                }
                else if (j == 17)
                {
                    this.field_78302_t = true;
                }
                else if (j == 18)
                {
                    this.field_78299_w = true;
                }
                else if (j == 19)
                {
                    this.field_78300_v = true;
                }
                else if (j == 20)
                {
                    this.field_78301_u = true;
                }
                else if (j == 21)
                {
                    this.field_78303_s = false;
                    this.field_78302_t = false;
                    this.field_78299_w = false;
                    this.field_78300_v = false;
                    this.field_78301_u = false;
                    GL11.glColor4f(this.field_78291_n, this.field_78292_o, this.field_78306_p, this.field_78305_q);
                }

                ++i;
            }
            else
            {
                j = "\u00c0\u00c1\u00c2\u00c8\u00ca\u00cb\u00cd\u00d3\u00d4\u00d5\u00da\u00df\u00e3\u00f5\u011f\u0130\u0131\u0152\u0153\u015e\u015f\u0174\u0175\u017e\u0207\u0000\u0000\u0000\u0000\u0000\u0000\u0000 !\"#$%&\'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u0000\u00c7\u00fc\u00e9\u00e2\u00e4\u00e0\u00e5\u00e7\u00ea\u00eb\u00e8\u00ef\u00ee\u00ec\u00c4\u00c5\u00c9\u00e6\u00c6\u00f4\u00f6\u00f2\u00fb\u00f9\u00ff\u00d6\u00dc\u00f8\u00a3\u00d8\u00d7\u0192\u00e1\u00ed\u00f3\u00fa\u00f1\u00d1\u00aa\u00ba\u00bf\u00ae\u00ac\u00bd\u00bc\u00a1\u00ab\u00bb\u2591\u2592\u2593\u2502\u2524\u2561\u2562\u2556\u2555\u2563\u2551\u2557\u255d\u255c\u255b\u2510\u2514\u2534\u252c\u251c\u2500\u253c\u255e\u255f\u255a\u2554\u2569\u2566\u2560\u2550\u256c\u2567\u2568\u2564\u2565\u2559\u2558\u2552\u2553\u256b\u256a\u2518\u250c\u2588\u2584\u258c\u2590\u2580\u03b1\u03b2\u0393\u03c0\u03a3\u03c3\u03bc\u03c4\u03a6\u0398\u03a9\u03b4\u221e\u2205\u2208\u2229\u2261\u00b1\u2265\u2264\u2320\u2321\u00f7\u2248\u00b0\u2219\u00b7\u221a\u207f\u00b2\u25a0\u0000".indexOf(c0);

                if (this.field_78303_s && j != -1)
                {
                    do
                    {
                        k = this.field_78289_c.nextInt(this.field_78286_d.length);
                    }
                    while (this.field_78286_d[j] != this.field_78286_d[k]);

                    j = k;
                }

                float f1 = this.field_78293_l ? 0.5F : 1.0F;
                boolean flag1 = (c0 == 0 || j == -1 || this.field_78293_l) && p_78255_2_;

                if (flag1)
                {
                    this.field_78295_j -= f1;
                    this.field_78296_k -= f1;
                }

                float f = this.func_78278_a(j, c0, this.field_78301_u);

                if (flag1)
                {
                    this.field_78295_j += f1;
                    this.field_78296_k += f1;
                }

                if (this.field_78302_t)
                {
                    this.field_78295_j += f1;

                    if (flag1)
                    {
                        this.field_78295_j -= f1;
                        this.field_78296_k -= f1;
                    }

                    this.func_78278_a(j, c0, this.field_78301_u);
                    this.field_78295_j -= f1;

                    if (flag1)
                    {
                        this.field_78295_j += f1;
                        this.field_78296_k += f1;
                    }

                    ++f;
                }

                Tessellator tessellator;

                if (this.field_78299_w)
                {
                    tessellator = Tessellator.field_78398_a;
                    GL11.glDisable(GL11.GL_TEXTURE_2D);
                    tessellator.func_78382_b();
                    tessellator.func_78377_a((double)this.field_78295_j, (double)(this.field_78296_k + (float)(this.field_78288_b / 2)), 0.0D);
                    tessellator.func_78377_a((double)(this.field_78295_j + f), (double)(this.field_78296_k + (float)(this.field_78288_b / 2)), 0.0D);
                    tessellator.func_78377_a((double)(this.field_78295_j + f), (double)(this.field_78296_k + (float)(this.field_78288_b / 2) - 1.0F), 0.0D);
                    tessellator.func_78377_a((double)this.field_78295_j, (double)(this.field_78296_k + (float)(this.field_78288_b / 2) - 1.0F), 0.0D);
                    tessellator.func_78381_a();
                    GL11.glEnable(GL11.GL_TEXTURE_2D);
                }

                if (this.field_78300_v)
                {
                    tessellator = Tessellator.field_78398_a;
                    GL11.glDisable(GL11.GL_TEXTURE_2D);
                    tessellator.func_78382_b();
                    int l = this.field_78300_v ? -1 : 0;
                    tessellator.func_78377_a((double)(this.field_78295_j + (float)l), (double)(this.field_78296_k + (float)this.field_78288_b), 0.0D);
                    tessellator.func_78377_a((double)(this.field_78295_j + f), (double)(this.field_78296_k + (float)this.field_78288_b), 0.0D);
                    tessellator.func_78377_a((double)(this.field_78295_j + f), (double)(this.field_78296_k + (float)this.field_78288_b - 1.0F), 0.0D);
                    tessellator.func_78377_a((double)(this.field_78295_j + (float)l), (double)(this.field_78296_k + (float)this.field_78288_b - 1.0F), 0.0D);
                    tessellator.func_78381_a();
                    GL11.glEnable(GL11.GL_TEXTURE_2D);
                }

                this.field_78295_j += (float)((int)f);
            }
        }
    }

    private int func_78274_b(String p_78274_1_, int p_78274_2_, int p_78274_3_, int p_78274_4_, int p_78274_5_, boolean p_78274_6_)
    {
        if (this.field_78294_m)
        {
            int i1 = this.func_78256_a(this.func_147647_b(p_78274_1_));
            p_78274_2_ = p_78274_2_ + p_78274_4_ - i1;
        }

        return this.func_78258_a(p_78274_1_, p_78274_2_, p_78274_3_, p_78274_5_, p_78274_6_);
    }

    private int func_78258_a(String p_78258_1_, int p_78258_2_, int p_78258_3_, int p_78258_4_, boolean p_78258_5_)
    {
        if (p_78258_1_ == null)
        {
            return 0;
        }
        else
        {
            if (this.field_78294_m)
            {
                p_78258_1_ = this.func_147647_b(p_78258_1_);
            }

            if ((p_78258_4_ & -67108864) == 0)
            {
                p_78258_4_ |= -16777216;
            }

            if (p_78258_5_)
            {
                p_78258_4_ = (p_78258_4_ & 16579836) >> 2 | p_78258_4_ & -16777216;
            }

            this.field_78291_n = (float)(p_78258_4_ >> 16 & 255) / 255.0F;
            this.field_78292_o = (float)(p_78258_4_ >> 8 & 255) / 255.0F;
            this.field_78306_p = (float)(p_78258_4_ & 255) / 255.0F;
            this.field_78305_q = (float)(p_78258_4_ >> 24 & 255) / 255.0F;
            GL11.glColor4f(this.field_78291_n, this.field_78292_o, this.field_78306_p, this.field_78305_q);
            this.field_78295_j = (float)p_78258_2_;
            this.field_78296_k = (float)p_78258_3_;
            this.func_78255_a(p_78258_1_, p_78258_5_);
            return (int)this.field_78295_j;
        }
    }

    public int func_78256_a(String p_78256_1_)
    {
        if (p_78256_1_ == null)
        {
            return 0;
        }
        else
        {
            int i = 0;
            boolean flag = false;

            for (int j = 0; j < p_78256_1_.length(); ++j)
            {
                char c0 = p_78256_1_.charAt(j);
                int k = this.func_78263_a(c0);

                if (k < 0 && j < p_78256_1_.length() - 1)
                {
                    ++j;
                    c0 = p_78256_1_.charAt(j);

                    if (c0 != 108 && c0 != 76)
                    {
                        if (c0 == 114 || c0 == 82)
                        {
                            flag = false;
                        }
                    }
                    else
                    {
                        flag = true;
                    }

                    k = 0;
                }

                i += k;

                if (flag && k > 0)
                {
                    ++i;
                }
            }

            return i;
        }
    }

    public int func_78263_a(char p_78263_1_)
    {
        if (p_78263_1_ == 167)
        {
            return -1;
        }
        else if (p_78263_1_ == 32)
        {
            return 4;
        }
        else
        {
            int i = "\u00c0\u00c1\u00c2\u00c8\u00ca\u00cb\u00cd\u00d3\u00d4\u00d5\u00da\u00df\u00e3\u00f5\u011f\u0130\u0131\u0152\u0153\u015e\u015f\u0174\u0175\u017e\u0207\u0000\u0000\u0000\u0000\u0000\u0000\u0000 !\"#$%&\'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u0000\u00c7\u00fc\u00e9\u00e2\u00e4\u00e0\u00e5\u00e7\u00ea\u00eb\u00e8\u00ef\u00ee\u00ec\u00c4\u00c5\u00c9\u00e6\u00c6\u00f4\u00f6\u00f2\u00fb\u00f9\u00ff\u00d6\u00dc\u00f8\u00a3\u00d8\u00d7\u0192\u00e1\u00ed\u00f3\u00fa\u00f1\u00d1\u00aa\u00ba\u00bf\u00ae\u00ac\u00bd\u00bc\u00a1\u00ab\u00bb\u2591\u2592\u2593\u2502\u2524\u2561\u2562\u2556\u2555\u2563\u2551\u2557\u255d\u255c\u255b\u2510\u2514\u2534\u252c\u251c\u2500\u253c\u255e\u255f\u255a\u2554\u2569\u2566\u2560\u2550\u256c\u2567\u2568\u2564\u2565\u2559\u2558\u2552\u2553\u256b\u256a\u2518\u250c\u2588\u2584\u258c\u2590\u2580\u03b1\u03b2\u0393\u03c0\u03a3\u03c3\u03bc\u03c4\u03a6\u0398\u03a9\u03b4\u221e\u2205\u2208\u2229\u2261\u00b1\u2265\u2264\u2320\u2321\u00f7\u2248\u00b0\u2219\u00b7\u221a\u207f\u00b2\u25a0\u0000".indexOf(p_78263_1_);

            if (p_78263_1_ > 0 && i != -1 && !this.field_78293_l)
            {
                return this.field_78286_d[i];
            }
            else if (this.field_78287_e[p_78263_1_] != 0)
            {
                int j = this.field_78287_e[p_78263_1_] >>> 4;
                int k = this.field_78287_e[p_78263_1_] & 15;

                if (k > 7)
                {
                    k = 15;
                    j = 0;
                }

                ++k;
                return (k - j) / 2 + 1;
            }
            else
            {
                return 0;
            }
        }
    }

    public String func_78269_a(String p_78269_1_, int p_78269_2_)
    {
        return this.func_78262_a(p_78269_1_, p_78269_2_, false);
    }

    public String func_78262_a(String p_78262_1_, int p_78262_2_, boolean p_78262_3_)
    {
        StringBuilder stringbuilder = new StringBuilder();
        int j = 0;
        int k = p_78262_3_ ? p_78262_1_.length() - 1 : 0;
        int l = p_78262_3_ ? -1 : 1;
        boolean flag1 = false;
        boolean flag2 = false;

        for (int i1 = k; i1 >= 0 && i1 < p_78262_1_.length() && j < p_78262_2_; i1 += l)
        {
            char c0 = p_78262_1_.charAt(i1);
            int j1 = this.func_78263_a(c0);

            if (flag1)
            {
                flag1 = false;

                if (c0 != 108 && c0 != 76)
                {
                    if (c0 == 114 || c0 == 82)
                    {
                        flag2 = false;
                    }
                }
                else
                {
                    flag2 = true;
                }
            }
            else if (j1 < 0)
            {
                flag1 = true;
            }
            else
            {
                j += j1;

                if (flag2)
                {
                    ++j;
                }
            }

            if (j > p_78262_2_)
            {
                break;
            }

            if (p_78262_3_)
            {
                stringbuilder.insert(0, c0);
            }
            else
            {
                stringbuilder.append(c0);
            }
        }

        return stringbuilder.toString();
    }

    private String func_78273_d(String p_78273_1_)
    {
        while (p_78273_1_ != null && p_78273_1_.endsWith("\n"))
        {
            p_78273_1_ = p_78273_1_.substring(0, p_78273_1_.length() - 1);
        }

        return p_78273_1_;
    }

    public void func_78279_b(String p_78279_1_, int p_78279_2_, int p_78279_3_, int p_78279_4_, int p_78279_5_)
    {
        this.func_78265_b();
        this.field_78304_r = p_78279_5_;
        p_78279_1_ = this.func_78273_d(p_78279_1_);
        this.func_78268_b(p_78279_1_, p_78279_2_, p_78279_3_, p_78279_4_, false);
    }

    private void func_78268_b(String p_78268_1_, int p_78268_2_, int p_78268_3_, int p_78268_4_, boolean p_78268_5_)
    {
        List list = this.func_78271_c(p_78268_1_, p_78268_4_);

        for (Iterator iterator = list.iterator(); iterator.hasNext(); p_78268_3_ += this.field_78288_b)
        {
            String s1 = (String)iterator.next();
            this.func_78274_b(s1, p_78268_2_, p_78268_3_, p_78268_4_, this.field_78304_r, p_78268_5_);
        }
    }

    public int func_78267_b(String p_78267_1_, int p_78267_2_)
    {
        return this.field_78288_b * this.func_78271_c(p_78267_1_, p_78267_2_).size();
    }

    public void func_78264_a(boolean p_78264_1_)
    {
        this.field_78293_l = p_78264_1_;
    }

    public boolean func_82883_a()
    {
        return this.field_78293_l;
    }

    public void func_78275_b(boolean p_78275_1_)
    {
        this.field_78294_m = p_78275_1_;
    }

    public List func_78271_c(String p_78271_1_, int p_78271_2_)
    {
        return Arrays.asList(this.func_78280_d(p_78271_1_, p_78271_2_).split("\n"));
    }

    String func_78280_d(String p_78280_1_, int p_78280_2_)
    {
        int j = this.func_78259_e(p_78280_1_, p_78280_2_);

        if (p_78280_1_.length() <= j)
        {
            return p_78280_1_;
        }
        else
        {
            String s1 = p_78280_1_.substring(0, j);
            char c0 = p_78280_1_.charAt(j);
            boolean flag = c0 == 32 || c0 == 10;
            String s2 = func_78282_e(s1) + p_78280_1_.substring(j + (flag ? 1 : 0));
            return s1 + "\n" + this.func_78280_d(s2, p_78280_2_);
        }
    }

    private int func_78259_e(String p_78259_1_, int p_78259_2_)
    {
        int j = p_78259_1_.length();
        int k = 0;
        int l = 0;
        int i1 = -1;

        for (boolean flag = false; l < j; ++l)
        {
            char c0 = p_78259_1_.charAt(l);

            switch (c0)
            {
                case 10:
                    --l;
                    break;
                case 167:
                    if (l < j - 1)
                    {
                        ++l;
                        char c1 = p_78259_1_.charAt(l);

                        if (c1 != 108 && c1 != 76)
                        {
                            if (c1 == 114 || c1 == 82 || func_78272_b(c1))
                            {
                                flag = false;
                            }
                        }
                        else
                        {
                            flag = true;
                        }
                    }

                    break;
                case 32:
                    i1 = l;
                default:
                    k += this.func_78263_a(c0);

                    if (flag)
                    {
                        ++k;
                    }
            }

            if (c0 == 10)
            {
                ++l;
                i1 = l;
                break;
            }

            if (k > p_78259_2_)
            {
                break;
            }
        }

        return l != j && i1 != -1 && i1 < l ? i1 : l;
    }

    private static boolean func_78272_b(char p_78272_0_)
    {
        return p_78272_0_ >= 48 && p_78272_0_ <= 57 || p_78272_0_ >= 97 && p_78272_0_ <= 102 || p_78272_0_ >= 65 && p_78272_0_ <= 70;
    }

    private static boolean func_78270_c(char p_78270_0_)
    {
        return p_78270_0_ >= 107 && p_78270_0_ <= 111 || p_78270_0_ >= 75 && p_78270_0_ <= 79 || p_78270_0_ == 114 || p_78270_0_ == 82;
    }

    private static String func_78282_e(String p_78282_0_)
    {
        String s1 = "";
        int i = -1;
        int j = p_78282_0_.length();

        while ((i = p_78282_0_.indexOf(167, i + 1)) != -1)
        {
            if (i < j - 1)
            {
                char c0 = p_78282_0_.charAt(i + 1);

                if (func_78272_b(c0))
                {
                    s1 = "\u00a7" + c0;
                }
                else if (func_78270_c(c0))
                {
                    s1 = s1 + "\u00a7" + c0;
                }
            }
        }

        return s1;
    }

    public boolean func_78260_a()
    {
        return this.field_78294_m;
    }
}