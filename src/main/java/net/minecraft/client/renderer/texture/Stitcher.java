package net.minecraft.client.renderer.texture;

import com.google.common.collect.Lists;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import net.minecraft.client.renderer.StitcherException;
import net.minecraft.util.MathHelper;

@SideOnly(Side.CLIENT)
public class Stitcher
{
    private final int field_147971_a;
    private final Set field_94319_a = new HashSet(256);
    private final List field_94317_b = new ArrayList(256);
    private int field_94318_c;
    private int field_94315_d;
    private final int field_94316_e;
    private final int field_94313_f;
    private final boolean field_94314_g;
    private final int field_94323_h;
    private static final String __OBFID = "CL_00001054";

    public Stitcher(int p_i45095_1_, int p_i45095_2_, boolean p_i45095_3_, int p_i45095_4_, int p_i45095_5_)
    {
        this.field_147971_a = p_i45095_5_;
        this.field_94316_e = p_i45095_1_;
        this.field_94313_f = p_i45095_2_;
        this.field_94314_g = p_i45095_3_;
        this.field_94323_h = p_i45095_4_;
    }

    public int func_110935_a()
    {
        return this.field_94318_c;
    }

    public int func_110936_b()
    {
        return this.field_94315_d;
    }

    public void func_110934_a(TextureAtlasSprite p_110934_1_)
    {
        Stitcher.Holder holder = new Stitcher.Holder(p_110934_1_, this.field_147971_a);

        if (this.field_94323_h > 0)
        {
            holder.func_94196_a(this.field_94323_h);
        }

        this.field_94319_a.add(holder);
    }

    public void func_94305_f()
    {
        Stitcher.Holder[] aholder = (Stitcher.Holder[])this.field_94319_a.toArray(new Stitcher.Holder[this.field_94319_a.size()]);
        Arrays.sort(aholder);
        Stitcher.Holder[] aholder1 = aholder;
        int i = aholder.length;

        for (int j = 0; j < i; ++j)
        {
            Stitcher.Holder holder = aholder1[j];

            if (!this.func_94310_b(holder))
            {
                String s = String.format("Unable to fit: %s - size: %dx%d - Maybe try a lowerresolution resourcepack?", new Object[] {holder.func_98150_a().func_94215_i(), Integer.valueOf(holder.func_98150_a().func_94211_a()), Integer.valueOf(holder.func_98150_a().func_94216_b())});
                throw new StitcherException(holder, s);
            }
        }

        if (this.field_94314_g)
        {
            this.field_94318_c = MathHelper.func_151236_b(this.field_94318_c);
            this.field_94315_d = MathHelper.func_151236_b(this.field_94315_d);
        }
    }

    public List func_94309_g()
    {
        ArrayList arraylist = Lists.newArrayList();
        Iterator iterator = this.field_94317_b.iterator();

        while (iterator.hasNext())
        {
            Stitcher.Slot slot = (Stitcher.Slot)iterator.next();
            slot.func_94184_a(arraylist);
        }

        ArrayList arraylist1 = Lists.newArrayList();
        Iterator iterator1 = arraylist.iterator();

        while (iterator1.hasNext())
        {
            Stitcher.Slot slot1 = (Stitcher.Slot)iterator1.next();
            Stitcher.Holder holder = slot1.func_94183_a();
            TextureAtlasSprite textureatlassprite = holder.func_98150_a();
            textureatlassprite.func_110971_a(this.field_94318_c, this.field_94315_d, slot1.func_94186_b(), slot1.func_94185_c(), holder.func_94195_e());
            arraylist1.add(textureatlassprite);
        }

        return arraylist1;
    }

    private static int func_147969_b(int p_147969_0_, int p_147969_1_)
    {
        return (p_147969_0_ >> p_147969_1_) + ((p_147969_0_ & (1 << p_147969_1_) - 1) == 0 ? 0 : 1) << p_147969_1_;
    }

    private boolean func_94310_b(Stitcher.Holder p_94310_1_)
    {
        for (int i = 0; i < this.field_94317_b.size(); ++i)
        {
            if (((Stitcher.Slot)this.field_94317_b.get(i)).func_94182_a(p_94310_1_))
            {
                return true;
            }

            p_94310_1_.func_94194_d();

            if (((Stitcher.Slot)this.field_94317_b.get(i)).func_94182_a(p_94310_1_))
            {
                return true;
            }

            p_94310_1_.func_94194_d();
        }

        return this.func_94311_c(p_94310_1_);
    }

    private boolean func_94311_c(Stitcher.Holder p_94311_1_)
    {
        int i = Math.min(p_94311_1_.func_94197_a(), p_94311_1_.func_94199_b());
        boolean flag = this.field_94318_c == 0 && this.field_94315_d == 0;
        boolean flag1;
        int j;

        if (this.field_94314_g)
        {
            j = MathHelper.func_151236_b(this.field_94318_c);
            int k = MathHelper.func_151236_b(this.field_94315_d);
            int l = MathHelper.func_151236_b(this.field_94318_c + i);
            int i1 = MathHelper.func_151236_b(this.field_94315_d + i);
            boolean flag2 = l <= this.field_94316_e;
            boolean flag3 = i1 <= this.field_94313_f;

            if (!flag2 && !flag3)
            {
                return false;
            }

            boolean flag4 = j != l;
            boolean flag5 = k != i1;

            if (flag4 ^ flag5)
            {
                flag1 = !flag4;
            }
            else
            {
                flag1 = flag2 && j <= k;
            }
        }
        else
        {
            boolean flag6 = this.field_94318_c + i <= this.field_94316_e;
            boolean flag7 = this.field_94315_d + i <= this.field_94313_f;

            if (!flag6 && !flag7)
            {
                return false;
            }

            flag1 = flag6 && (flag || this.field_94318_c <= this.field_94315_d);
        }

        j = Math.max(p_94311_1_.func_94197_a(), p_94311_1_.func_94199_b());

        if (MathHelper.func_151236_b((flag1 ? this.field_94315_d : this.field_94318_c) + j) > (flag1 ? this.field_94313_f : this.field_94316_e))
        {
            return false;
        }
        else
        {
            Stitcher.Slot slot;

            if (flag1)
            {
                if (p_94311_1_.func_94197_a() > p_94311_1_.func_94199_b())
                {
                    p_94311_1_.func_94194_d();
                }

                if (this.field_94315_d == 0)
                {
                    this.field_94315_d = p_94311_1_.func_94199_b();
                }

                slot = new Stitcher.Slot(this.field_94318_c, 0, p_94311_1_.func_94197_a(), this.field_94315_d);
                this.field_94318_c += p_94311_1_.func_94197_a();
            }
            else
            {
                slot = new Stitcher.Slot(0, this.field_94315_d, this.field_94318_c, p_94311_1_.func_94199_b());
                this.field_94315_d += p_94311_1_.func_94199_b();
            }

            slot.func_94182_a(p_94311_1_);
            this.field_94317_b.add(slot);
            return true;
        }
    }

    @SideOnly(Side.CLIENT)
    public static class Holder implements Comparable
        {
            private final TextureAtlasSprite field_98151_a;
            private final int field_94204_c;
            private final int field_94201_d;
            private final int field_147968_d;
            private boolean field_94202_e;
            private float field_94205_a = 1.0F;
            private static final String __OBFID = "CL_00001055";

            public Holder(TextureAtlasSprite p_i45094_1_, int p_i45094_2_)
            {
                this.field_98151_a = p_i45094_1_;
                this.field_94204_c = p_i45094_1_.func_94211_a();
                this.field_94201_d = p_i45094_1_.func_94216_b();
                this.field_147968_d = p_i45094_2_;
                this.field_94202_e = Stitcher.func_147969_b(this.field_94201_d, p_i45094_2_) > Stitcher.func_147969_b(this.field_94204_c, p_i45094_2_);
            }

            public TextureAtlasSprite func_98150_a()
            {
                return this.field_98151_a;
            }

            public int func_94197_a()
            {
                return this.field_94202_e ? Stitcher.func_147969_b((int)((float)this.field_94201_d * this.field_94205_a), this.field_147968_d) : Stitcher.func_147969_b((int)((float)this.field_94204_c * this.field_94205_a), this.field_147968_d);
            }

            public int func_94199_b()
            {
                return this.field_94202_e ? Stitcher.func_147969_b((int)((float)this.field_94204_c * this.field_94205_a), this.field_147968_d) : Stitcher.func_147969_b((int)((float)this.field_94201_d * this.field_94205_a), this.field_147968_d);
            }

            public void func_94194_d()
            {
                this.field_94202_e = !this.field_94202_e;
            }

            public boolean func_94195_e()
            {
                return this.field_94202_e;
            }

            public void func_94196_a(int p_94196_1_)
            {
                if (this.field_94204_c > p_94196_1_ && this.field_94201_d > p_94196_1_)
                {
                    this.field_94205_a = (float)p_94196_1_ / (float)Math.min(this.field_94204_c, this.field_94201_d);
                }
            }

            public String toString()
            {
                return "Holder{width=" + this.field_94204_c + ", height=" + this.field_94201_d + '}';
            }

            public int compareTo(Stitcher.Holder p_compareTo_1_)
            {
                int i;

                if (this.func_94199_b() == p_compareTo_1_.func_94199_b())
                {
                    if (this.func_94197_a() == p_compareTo_1_.func_94197_a())
                    {
                        if (this.field_98151_a.func_94215_i() == null)
                        {
                            return p_compareTo_1_.field_98151_a.func_94215_i() == null ? 0 : -1;
                        }

                        return this.field_98151_a.func_94215_i().compareTo(p_compareTo_1_.field_98151_a.func_94215_i());
                    }

                    i = this.func_94197_a() < p_compareTo_1_.func_94197_a() ? 1 : -1;
                }
                else
                {
                    i = this.func_94199_b() < p_compareTo_1_.func_94199_b() ? 1 : -1;
                }

                return i;
            }

            public int compareTo(Object p_compareTo_1_)
            {
                return this.compareTo((Stitcher.Holder)p_compareTo_1_);
            }
        }

    @SideOnly(Side.CLIENT)
    public static class Slot
        {
            private final int field_94192_a;
            private final int field_94190_b;
            private final int field_94191_c;
            private final int field_94188_d;
            private List field_94189_e;
            private Stitcher.Holder field_94187_f;
            private static final String __OBFID = "CL_00001056";

            public Slot(int p_i1277_1_, int p_i1277_2_, int p_i1277_3_, int p_i1277_4_)
            {
                this.field_94192_a = p_i1277_1_;
                this.field_94190_b = p_i1277_2_;
                this.field_94191_c = p_i1277_3_;
                this.field_94188_d = p_i1277_4_;
            }

            public Stitcher.Holder func_94183_a()
            {
                return this.field_94187_f;
            }

            public int func_94186_b()
            {
                return this.field_94192_a;
            }

            public int func_94185_c()
            {
                return this.field_94190_b;
            }

            public boolean func_94182_a(Stitcher.Holder p_94182_1_)
            {
                if (this.field_94187_f != null)
                {
                    return false;
                }
                else
                {
                    int i = p_94182_1_.func_94197_a();
                    int j = p_94182_1_.func_94199_b();

                    if (i <= this.field_94191_c && j <= this.field_94188_d)
                    {
                        if (i == this.field_94191_c && j == this.field_94188_d)
                        {
                            this.field_94187_f = p_94182_1_;
                            return true;
                        }
                        else
                        {
                            if (this.field_94189_e == null)
                            {
                                this.field_94189_e = new ArrayList(1);
                                this.field_94189_e.add(new Stitcher.Slot(this.field_94192_a, this.field_94190_b, i, j));
                                int k = this.field_94191_c - i;
                                int l = this.field_94188_d - j;

                                if (l > 0 && k > 0)
                                {
                                    int i1 = Math.max(this.field_94188_d, k);
                                    int j1 = Math.max(this.field_94191_c, l);

                                    if (i1 >= j1)
                                    {
                                        this.field_94189_e.add(new Stitcher.Slot(this.field_94192_a, this.field_94190_b + j, i, l));
                                        this.field_94189_e.add(new Stitcher.Slot(this.field_94192_a + i, this.field_94190_b, k, this.field_94188_d));
                                    }
                                    else
                                    {
                                        this.field_94189_e.add(new Stitcher.Slot(this.field_94192_a + i, this.field_94190_b, k, j));
                                        this.field_94189_e.add(new Stitcher.Slot(this.field_94192_a, this.field_94190_b + j, this.field_94191_c, l));
                                    }
                                }
                                else if (k == 0)
                                {
                                    this.field_94189_e.add(new Stitcher.Slot(this.field_94192_a, this.field_94190_b + j, i, l));
                                }
                                else if (l == 0)
                                {
                                    this.field_94189_e.add(new Stitcher.Slot(this.field_94192_a + i, this.field_94190_b, k, j));
                                }
                            }

                            Iterator iterator = this.field_94189_e.iterator();
                            Stitcher.Slot slot;

                            do
                            {
                                if (!iterator.hasNext())
                                {
                                    return false;
                                }

                                slot = (Stitcher.Slot)iterator.next();
                            }
                            while (!slot.func_94182_a(p_94182_1_));

                            return true;
                        }
                    }
                    else
                    {
                        return false;
                    }
                }
            }

            public void func_94184_a(List p_94184_1_)
            {
                if (this.field_94187_f != null)
                {
                    p_94184_1_.add(this);
                }
                else if (this.field_94189_e != null)
                {
                    Iterator iterator = this.field_94189_e.iterator();

                    while (iterator.hasNext())
                    {
                        Stitcher.Slot slot = (Stitcher.Slot)iterator.next();
                        slot.func_94184_a(p_94184_1_);
                    }
                }
            }

            public String toString()
            {
                return "Slot{originX=" + this.field_94192_a + ", originY=" + this.field_94190_b + ", width=" + this.field_94191_c + ", height=" + this.field_94188_d + ", texture=" + this.field_94187_f + ", subSlots=" + this.field_94189_e + '}';
            }
        }
}