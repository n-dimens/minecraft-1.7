package net.minecraft.client.renderer.texture;

import com.google.common.collect.Lists;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import net.minecraft.client.resources.data.AnimationFrame;
import net.minecraft.client.resources.data.AnimationMetadataSection;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.util.IIcon;
import net.minecraft.util.ReportedException;

@SideOnly(Side.CLIENT)
public class TextureAtlasSprite implements IIcon
{
    private final String field_110984_i;
    protected List field_110976_a = Lists.newArrayList();
    private AnimationMetadataSection field_110982_k;
    protected boolean field_130222_e;
    private boolean field_147966_k;
    protected int field_110975_c;
    protected int field_110974_d;
    protected int field_130223_c;
    protected int field_130224_d;
    private float field_110979_l;
    private float field_110980_m;
    private float field_110977_n;
    private float field_110978_o;
    protected int field_110973_g;
    protected int field_110983_h;
    private static final String __OBFID = "CL_00001062";

    protected TextureAtlasSprite(String p_i1282_1_)
    {
        this.field_110984_i = p_i1282_1_;
    }

    public void func_110971_a(int p_110971_1_, int p_110971_2_, int p_110971_3_, int p_110971_4_, boolean p_110971_5_)
    {
        this.field_110975_c = p_110971_3_;
        this.field_110974_d = p_110971_4_;
        this.field_130222_e = p_110971_5_;
        float f = (float)(0.009999999776482582D / (double)p_110971_1_);
        float f1 = (float)(0.009999999776482582D / (double)p_110971_2_);
        this.field_110979_l = (float)p_110971_3_ / (float)((double)p_110971_1_) + f;
        this.field_110980_m = (float)(p_110971_3_ + this.field_130223_c) / (float)((double)p_110971_1_) - f;
        this.field_110977_n = (float)p_110971_4_ / (float)p_110971_2_ + f1;
        this.field_110978_o = (float)(p_110971_4_ + this.field_130224_d) / (float)p_110971_2_ - f1;

        if (this.field_147966_k)
        {
            float f2 = 8.0F / (float)p_110971_1_;
            float f3 = 8.0F / (float)p_110971_2_;
            this.field_110979_l += f2;
            this.field_110980_m -= f2;
            this.field_110977_n += f3;
            this.field_110978_o -= f3;
        }
    }

    public void func_94217_a(TextureAtlasSprite p_94217_1_)
    {
        this.field_110975_c = p_94217_1_.field_110975_c;
        this.field_110974_d = p_94217_1_.field_110974_d;
        this.field_130223_c = p_94217_1_.field_130223_c;
        this.field_130224_d = p_94217_1_.field_130224_d;
        this.field_130222_e = p_94217_1_.field_130222_e;
        this.field_110979_l = p_94217_1_.field_110979_l;
        this.field_110980_m = p_94217_1_.field_110980_m;
        this.field_110977_n = p_94217_1_.field_110977_n;
        this.field_110978_o = p_94217_1_.field_110978_o;
    }

    public int func_130010_a()
    {
        return this.field_110975_c;
    }

    public int func_110967_i()
    {
        return this.field_110974_d;
    }

    public int func_94211_a()
    {
        return this.field_130223_c;
    }

    public int func_94216_b()
    {
        return this.field_130224_d;
    }

    public float func_94209_e()
    {
        return this.field_110979_l;
    }

    public float func_94212_f()
    {
        return this.field_110980_m;
    }

    public float func_94214_a(double p_94214_1_)
    {
        float f = this.field_110980_m - this.field_110979_l;
        return this.field_110979_l + f * (float)p_94214_1_ / 16.0F;
    }

    public float func_94206_g()
    {
        return this.field_110977_n;
    }

    public float func_94210_h()
    {
        return this.field_110978_o;
    }

    public float func_94207_b(double p_94207_1_)
    {
        float f = this.field_110978_o - this.field_110977_n;
        return this.field_110977_n + f * ((float)p_94207_1_ / 16.0F);
    }

    public String func_94215_i()
    {
        return this.field_110984_i;
    }

    public void func_94219_l()
    {
        ++this.field_110983_h;

        if (this.field_110983_h >= this.field_110982_k.func_110472_a(this.field_110973_g))
        {
            int i = this.field_110982_k.func_110468_c(this.field_110973_g);
            int j = this.field_110982_k.func_110473_c() == 0 ? this.field_110976_a.size() : this.field_110982_k.func_110473_c();
            this.field_110973_g = (this.field_110973_g + 1) % j;
            this.field_110983_h = 0;
            int k = this.field_110982_k.func_110468_c(this.field_110973_g);

            if (i != k && k >= 0 && k < this.field_110976_a.size())
            {
                TextureUtil.func_147955_a((int[][])this.field_110976_a.get(k), this.field_130223_c, this.field_130224_d, this.field_110975_c, this.field_110974_d, false, false);
            }
        }
    }

    public int[][] func_147965_a(int p_147965_1_)
    {
        return (int[][])this.field_110976_a.get(p_147965_1_);
    }

    public int func_110970_k()
    {
        return this.field_110976_a.size();
    }

    public void func_110966_b(int p_110966_1_)
    {
        this.field_130223_c = p_110966_1_;
    }

    public void func_110969_c(int p_110969_1_)
    {
        this.field_130224_d = p_110969_1_;
    }

    public void func_147964_a(BufferedImage[] p_147964_1_, AnimationMetadataSection p_147964_2_, boolean p_147964_3_)
    {
        this.func_130102_n();
        this.field_147966_k = p_147964_3_;
        int i = p_147964_1_[0].getWidth();
        int j = p_147964_1_[0].getHeight();
        this.field_130223_c = i;
        this.field_130224_d = j;

        if (p_147964_3_)
        {
            this.field_130223_c += 16;
            this.field_130224_d += 16;
        }

        int[][] aint = new int[p_147964_1_.length][];
        int k;

        for (k = 0; k < p_147964_1_.length; ++k)
        {
            BufferedImage bufferedimage = p_147964_1_[k];

            if (bufferedimage != null)
            {
                if (k > 0 && (bufferedimage.getWidth() != i >> k || bufferedimage.getHeight() != j >> k))
                {
                    throw new RuntimeException(String.format("Unable to load miplevel: %d, image is size: %dx%d, expected %dx%d", new Object[] {Integer.valueOf(k), Integer.valueOf(bufferedimage.getWidth()), Integer.valueOf(bufferedimage.getHeight()), Integer.valueOf(i >> k), Integer.valueOf(j >> k)}));
                }

                aint[k] = new int[bufferedimage.getWidth() * bufferedimage.getHeight()];
                bufferedimage.getRGB(0, 0, bufferedimage.getWidth(), bufferedimage.getHeight(), aint[k], 0, bufferedimage.getWidth());
            }
        }

        if (p_147964_2_ == null)
        {
            if (j != i)
            {
                throw new RuntimeException("broken aspect ratio and not an animation");
            }

            this.func_147961_a(aint);
            this.field_110976_a.add(this.func_147960_a(aint, i, j));
        }
        else
        {
            k = j / i;
            int j1 = i;
            int l = i;
            this.field_130224_d = this.field_130223_c;
            int i1;

            if (p_147964_2_.func_110473_c() > 0)
            {
                Iterator iterator = p_147964_2_.func_130073_e().iterator();

                while (iterator.hasNext())
                {
                    i1 = ((Integer)iterator.next()).intValue();

                    if (i1 >= k)
                    {
                        throw new RuntimeException("invalid frameindex " + i1);
                    }

                    this.func_130099_d(i1);
                    this.field_110976_a.set(i1, this.func_147960_a(func_147962_a(aint, j1, l, i1), j1, l));
                }

                this.field_110982_k = p_147964_2_;
            }
            else
            {
                ArrayList arraylist = Lists.newArrayList();

                for (i1 = 0; i1 < k; ++i1)
                {
                    this.field_110976_a.add(this.func_147960_a(func_147962_a(aint, j1, l, i1), j1, l));
                    arraylist.add(new AnimationFrame(i1, -1));
                }

                this.field_110982_k = new AnimationMetadataSection(arraylist, this.field_130223_c, this.field_130224_d, p_147964_2_.func_110469_d());
            }
        }
    }

    public void func_147963_d(int p_147963_1_)
    {
        ArrayList arraylist = Lists.newArrayList();

        for (int j = 0; j < this.field_110976_a.size(); ++j)
        {
            final int[][] aint = (int[][])this.field_110976_a.get(j);

            if (aint != null)
            {
                try
                {
                    arraylist.add(TextureUtil.func_147949_a(p_147963_1_, this.field_130223_c, aint));
                }
                catch (Throwable throwable)
                {
                    CrashReport crashreport = CrashReport.func_85055_a(throwable, "Generating mipmaps for frame");
                    CrashReportCategory crashreportcategory = crashreport.func_85058_a("Frame being iterated");
                    crashreportcategory.func_71507_a("Frame index", Integer.valueOf(j));
                    crashreportcategory.func_71500_a("Frame sizes", new Callable()
                    {
                        private static final String __OBFID = "CL_00001063";
                        public String call()
                        {
                            StringBuilder stringbuilder = new StringBuilder();
                            int[][] aint1 = aint;
                            int k = aint1.length;

                            for (int l = 0; l < k; ++l)
                            {
                                int[] aint2 = aint1[l];

                                if (stringbuilder.length() > 0)
                                {
                                    stringbuilder.append(", ");
                                }

                                stringbuilder.append(aint2 == null ? "null" : Integer.valueOf(aint2.length));
                            }

                            return stringbuilder.toString();
                        }
                    });
                    throw new ReportedException(crashreport);
                }
            }
        }

        this.func_110968_a(arraylist);
    }

    private void func_147961_a(int[][] p_147961_1_)
    {
        int[] aint1 = p_147961_1_[0];
        int i = 0;
        int j = 0;
        int k = 0;
        int l = 0;
        int i1;

        for (i1 = 0; i1 < aint1.length; ++i1)
        {
            if ((aint1[i1] & -16777216) != 0)
            {
                j += aint1[i1] >> 16 & 255;
                k += aint1[i1] >> 8 & 255;
                l += aint1[i1] >> 0 & 255;
                ++i;
            }
        }

        if (i != 0)
        {
            j /= i;
            k /= i;
            l /= i;

            for (i1 = 0; i1 < aint1.length; ++i1)
            {
                if ((aint1[i1] & -16777216) == 0)
                {
                    aint1[i1] = j << 16 | k << 8 | l;
                }
            }
        }
    }

    private int[][] func_147960_a(int[][] p_147960_1_, int p_147960_2_, int p_147960_3_)
    {
        if (!this.field_147966_k)
        {
            return p_147960_1_;
        }
        else
        {
            int[][] aint1 = new int[p_147960_1_.length][];

            for (int k = 0; k < p_147960_1_.length; ++k)
            {
                int[] aint2 = p_147960_1_[k];

                if (aint2 != null)
                {
                    int[] aint3 = new int[(p_147960_2_ + 16 >> k) * (p_147960_3_ + 16 >> k)];
                    System.arraycopy(aint2, 0, aint3, 0, aint2.length);
                    aint1[k] = TextureUtil.func_147948_a(aint3, p_147960_2_ >> k, p_147960_3_ >> k, 8 >> k);
                }
            }

            return aint1;
        }
    }

    private void func_130099_d(int p_130099_1_)
    {
        if (this.field_110976_a.size() <= p_130099_1_)
        {
            for (int j = this.field_110976_a.size(); j <= p_130099_1_; ++j)
            {
                this.field_110976_a.add((Object)null);
            }
        }
    }

    private static int[][] func_147962_a(int[][] p_147962_0_, int p_147962_1_, int p_147962_2_, int p_147962_3_)
    {
        int[][] aint1 = new int[p_147962_0_.length][];

        for (int l = 0; l < p_147962_0_.length; ++l)
        {
            int[] aint2 = p_147962_0_[l];

            if (aint2 != null)
            {
                aint1[l] = new int[(p_147962_1_ >> l) * (p_147962_2_ >> l)];
                System.arraycopy(aint2, p_147962_3_ * aint1[l].length, aint1[l], 0, aint1[l].length);
            }
        }

        return aint1;
    }

    public void func_130103_l()
    {
        this.field_110976_a.clear();
    }

    public boolean func_130098_m()
    {
        return this.field_110982_k != null;
    }

    public void func_110968_a(List p_110968_1_)
    {
        this.field_110976_a = p_110968_1_;
    }

    private void func_130102_n()
    {
        this.field_110982_k = null;
        this.func_110968_a(Lists.newArrayList());
        this.field_110973_g = 0;
        this.field_110983_h = 0;
    }

    public String toString()
    {
        return "TextureAtlasSprite{name=\'" + this.field_110984_i + '\'' + ", frameCount=" + this.field_110976_a.size() + ", rotated=" + this.field_130222_e + ", x=" + this.field_110975_c + ", y=" + this.field_110974_d + ", height=" + this.field_130224_d + ", width=" + this.field_130223_c + ", u0=" + this.field_110979_l + ", u1=" + this.field_110980_m + ", v0=" + this.field_110977_n + ", v1=" + this.field_110978_o + '}';
    }
}