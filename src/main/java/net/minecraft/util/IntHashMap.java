package net.minecraft.util;

import java.util.HashSet;
import java.util.Set;

public class IntHashMap
{
    private transient IntHashMap.Entry[] field_76055_a = new IntHashMap.Entry[16];
    private transient int field_76053_b;
    private int field_76054_c = 12;
    private final float field_76051_d = 0.75F;
    private transient volatile int field_76052_e;
    private Set field_76050_f = new HashSet();
    private static final String __OBFID = "CL_00001490";

    private static int func_76044_g(int p_76044_0_)
    {
        p_76044_0_ ^= p_76044_0_ >>> 20 ^ p_76044_0_ >>> 12;
        return p_76044_0_ ^ p_76044_0_ >>> 7 ^ p_76044_0_ >>> 4;
    }

    private static int func_76043_a(int p_76043_0_, int p_76043_1_)
    {
        return p_76043_0_ & p_76043_1_ - 1;
    }

    public Object func_76041_a(int p_76041_1_)
    {
        int j = func_76044_g(p_76041_1_);

        for (IntHashMap.Entry entry = this.field_76055_a[func_76043_a(j, this.field_76055_a.length)]; entry != null; entry = entry.field_76034_c)
        {
            if (entry.field_76035_a == p_76041_1_)
            {
                return entry.field_76033_b;
            }
        }

        return null;
    }

    public boolean func_76037_b(int p_76037_1_)
    {
        return this.func_76045_c(p_76037_1_) != null;
    }

    final IntHashMap.Entry func_76045_c(int p_76045_1_)
    {
        int j = func_76044_g(p_76045_1_);

        for (IntHashMap.Entry entry = this.field_76055_a[func_76043_a(j, this.field_76055_a.length)]; entry != null; entry = entry.field_76034_c)
        {
            if (entry.field_76035_a == p_76045_1_)
            {
                return entry;
            }
        }

        return null;
    }

    public void func_76038_a(int p_76038_1_, Object p_76038_2_)
    {
        this.field_76050_f.add(Integer.valueOf(p_76038_1_));
        int j = func_76044_g(p_76038_1_);
        int k = func_76043_a(j, this.field_76055_a.length);

        for (IntHashMap.Entry entry = this.field_76055_a[k]; entry != null; entry = entry.field_76034_c)
        {
            if (entry.field_76035_a == p_76038_1_)
            {
                entry.field_76033_b = p_76038_2_;
                return;
            }
        }

        ++this.field_76052_e;
        this.func_76040_a(j, p_76038_1_, p_76038_2_, k);
    }

    private void func_76047_h(int p_76047_1_)
    {
        IntHashMap.Entry[] aentry = this.field_76055_a;
        int j = aentry.length;

        if (j == 1073741824)
        {
            this.field_76054_c = Integer.MAX_VALUE;
        }
        else
        {
            IntHashMap.Entry[] aentry1 = new IntHashMap.Entry[p_76047_1_];
            this.func_76048_a(aentry1);
            this.field_76055_a = aentry1;
            this.field_76054_c = (int)((float)p_76047_1_ * this.field_76051_d);
        }
    }

    private void func_76048_a(IntHashMap.Entry[] p_76048_1_)
    {
        IntHashMap.Entry[] aentry = this.field_76055_a;
        int i = p_76048_1_.length;

        for (int j = 0; j < aentry.length; ++j)
        {
            IntHashMap.Entry entry = aentry[j];

            if (entry != null)
            {
                aentry[j] = null;
                IntHashMap.Entry entry1;

                do
                {
                    entry1 = entry.field_76034_c;
                    int k = func_76043_a(entry.field_76032_d, i);
                    entry.field_76034_c = p_76048_1_[k];
                    p_76048_1_[k] = entry;
                    entry = entry1;
                }
                while (entry1 != null);
            }
        }
    }

    public Object func_76049_d(int p_76049_1_)
    {
        this.field_76050_f.remove(Integer.valueOf(p_76049_1_));
        IntHashMap.Entry entry = this.func_76036_e(p_76049_1_);
        return entry == null ? null : entry.field_76033_b;
    }

    final IntHashMap.Entry func_76036_e(int p_76036_1_)
    {
        int j = func_76044_g(p_76036_1_);
        int k = func_76043_a(j, this.field_76055_a.length);
        IntHashMap.Entry entry = this.field_76055_a[k];
        IntHashMap.Entry entry1;
        IntHashMap.Entry entry2;

        for (entry1 = entry; entry1 != null; entry1 = entry2)
        {
            entry2 = entry1.field_76034_c;

            if (entry1.field_76035_a == p_76036_1_)
            {
                ++this.field_76052_e;
                --this.field_76053_b;

                if (entry == entry1)
                {
                    this.field_76055_a[k] = entry2;
                }
                else
                {
                    entry.field_76034_c = entry2;
                }

                return entry1;
            }

            entry = entry1;
        }

        return entry1;
    }

    public void func_76046_c()
    {
        ++this.field_76052_e;
        IntHashMap.Entry[] aentry = this.field_76055_a;

        for (int i = 0; i < aentry.length; ++i)
        {
            aentry[i] = null;
        }

        this.field_76053_b = 0;
    }

    private void func_76040_a(int p_76040_1_, int p_76040_2_, Object p_76040_3_, int p_76040_4_)
    {
        IntHashMap.Entry entry = this.field_76055_a[p_76040_4_];
        this.field_76055_a[p_76040_4_] = new IntHashMap.Entry(p_76040_1_, p_76040_2_, p_76040_3_, entry);

        if (this.field_76053_b++ >= this.field_76054_c)
        {
            this.func_76047_h(2 * this.field_76055_a.length);
        }
    }

    static class Entry
        {
            final int field_76035_a;
            Object field_76033_b;
            IntHashMap.Entry field_76034_c;
            final int field_76032_d;
            private static final String __OBFID = "CL_00001491";

            Entry(int p_i1552_1_, int p_i1552_2_, Object p_i1552_3_, IntHashMap.Entry p_i1552_4_)
            {
                this.field_76033_b = p_i1552_3_;
                this.field_76034_c = p_i1552_4_;
                this.field_76035_a = p_i1552_2_;
                this.field_76032_d = p_i1552_1_;
            }

            public final int func_76031_a()
            {
                return this.field_76035_a;
            }

            public final Object func_76030_b()
            {
                return this.field_76033_b;
            }

            public final boolean equals(Object p_equals_1_)
            {
                if (!(p_equals_1_ instanceof IntHashMap.Entry))
                {
                    return false;
                }
                else
                {
                    IntHashMap.Entry entry = (IntHashMap.Entry)p_equals_1_;
                    Integer integer = Integer.valueOf(this.func_76031_a());
                    Integer integer1 = Integer.valueOf(entry.func_76031_a());

                    if (integer == integer1 || integer != null && integer.equals(integer1))
                    {
                        Object object1 = this.func_76030_b();
                        Object object2 = entry.func_76030_b();

                        if (object1 == object2 || object1 != null && object1.equals(object2))
                        {
                            return true;
                        }
                    }

                    return false;
                }
            }

            public final int hashCode()
            {
                return IntHashMap.func_76044_g(this.field_76035_a);
            }

            public final String toString()
            {
                return this.func_76031_a() + "=" + this.func_76030_b();
            }
        }
}