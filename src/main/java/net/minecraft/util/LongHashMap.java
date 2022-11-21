package net.minecraft.util;

public class LongHashMap
{
    private transient LongHashMap.Entry[] field_76169_a = new LongHashMap.Entry[16];
    private transient int field_76167_b;
    private int field_76168_c = 12;
    private final float field_76165_d = 0.75F;
    private transient volatile int field_76166_e;
    private static final String __OBFID = "CL_00001492";

    private static int func_76155_g(long p_76155_0_)
    {
        return func_76157_a((int)(p_76155_0_ ^ p_76155_0_ >>> 32));
    }

    private static int func_76157_a(int p_76157_0_)
    {
        p_76157_0_ ^= p_76157_0_ >>> 20 ^ p_76157_0_ >>> 12;
        return p_76157_0_ ^ p_76157_0_ >>> 7 ^ p_76157_0_ >>> 4;
    }

    private static int func_76158_a(int p_76158_0_, int p_76158_1_)
    {
        return p_76158_0_ & p_76158_1_ - 1;
    }

    public int func_76162_a()
    {
        return this.field_76167_b;
    }

    public Object func_76164_a(long p_76164_1_)
    {
        int j = func_76155_g(p_76164_1_);

        for (LongHashMap.Entry entry = this.field_76169_a[func_76158_a(j, this.field_76169_a.length)]; entry != null; entry = entry.field_76149_c)
        {
            if (entry.field_76150_a == p_76164_1_)
            {
                return entry.field_76148_b;
            }
        }

        return null;
    }

    public boolean func_76161_b(long p_76161_1_)
    {
        return this.func_76160_c(p_76161_1_) != null;
    }

    final LongHashMap.Entry func_76160_c(long p_76160_1_)
    {
        int j = func_76155_g(p_76160_1_);

        for (LongHashMap.Entry entry = this.field_76169_a[func_76158_a(j, this.field_76169_a.length)]; entry != null; entry = entry.field_76149_c)
        {
            if (entry.field_76150_a == p_76160_1_)
            {
                return entry;
            }
        }

        return null;
    }

    public void func_76163_a(long p_76163_1_, Object p_76163_3_)
    {
        int j = func_76155_g(p_76163_1_);
        int k = func_76158_a(j, this.field_76169_a.length);

        for (LongHashMap.Entry entry = this.field_76169_a[k]; entry != null; entry = entry.field_76149_c)
        {
            if (entry.field_76150_a == p_76163_1_)
            {
                entry.field_76148_b = p_76163_3_;
                return;
            }
        }

        ++this.field_76166_e;
        this.func_76156_a(j, p_76163_1_, p_76163_3_, k);
    }

    private void func_76153_b(int p_76153_1_)
    {
        LongHashMap.Entry[] aentry = this.field_76169_a;
        int j = aentry.length;

        if (j == 1073741824)
        {
            this.field_76168_c = Integer.MAX_VALUE;
        }
        else
        {
            LongHashMap.Entry[] aentry1 = new LongHashMap.Entry[p_76153_1_];
            this.func_76154_a(aentry1);
            this.field_76169_a = aentry1;
            this.field_76168_c = (int)((float)p_76153_1_ * this.field_76165_d);
        }
    }

    private void func_76154_a(LongHashMap.Entry[] p_76154_1_)
    {
        LongHashMap.Entry[] aentry = this.field_76169_a;
        int i = p_76154_1_.length;

        for (int j = 0; j < aentry.length; ++j)
        {
            LongHashMap.Entry entry = aentry[j];

            if (entry != null)
            {
                aentry[j] = null;
                LongHashMap.Entry entry1;

                do
                {
                    entry1 = entry.field_76149_c;
                    int k = func_76158_a(entry.field_76147_d, i);
                    entry.field_76149_c = p_76154_1_[k];
                    p_76154_1_[k] = entry;
                    entry = entry1;
                }
                while (entry1 != null);
            }
        }
    }

    public Object func_76159_d(long p_76159_1_)
    {
        LongHashMap.Entry entry = this.func_76152_e(p_76159_1_);
        return entry == null ? null : entry.field_76148_b;
    }

    final LongHashMap.Entry func_76152_e(long p_76152_1_)
    {
        int j = func_76155_g(p_76152_1_);
        int k = func_76158_a(j, this.field_76169_a.length);
        LongHashMap.Entry entry = this.field_76169_a[k];
        LongHashMap.Entry entry1;
        LongHashMap.Entry entry2;

        for (entry1 = entry; entry1 != null; entry1 = entry2)
        {
            entry2 = entry1.field_76149_c;

            if (entry1.field_76150_a == p_76152_1_)
            {
                ++this.field_76166_e;
                --this.field_76167_b;

                if (entry == entry1)
                {
                    this.field_76169_a[k] = entry2;
                }
                else
                {
                    entry.field_76149_c = entry2;
                }

                return entry1;
            }

            entry = entry1;
        }

        return entry1;
    }

    private void func_76156_a(int p_76156_1_, long p_76156_2_, Object p_76156_4_, int p_76156_5_)
    {
        LongHashMap.Entry entry = this.field_76169_a[p_76156_5_];
        this.field_76169_a[p_76156_5_] = new LongHashMap.Entry(p_76156_1_, p_76156_2_, p_76156_4_, entry);

        if (this.field_76167_b++ >= this.field_76168_c)
        {
            this.func_76153_b(2 * this.field_76169_a.length);
        }
    }

    static class Entry
        {
            final long field_76150_a;
            Object field_76148_b;
            LongHashMap.Entry field_76149_c;
            final int field_76147_d;
            private static final String __OBFID = "CL_00001493";

            Entry(int p_i1553_1_, long p_i1553_2_, Object p_i1553_4_, LongHashMap.Entry p_i1553_5_)
            {
                this.field_76148_b = p_i1553_4_;
                this.field_76149_c = p_i1553_5_;
                this.field_76150_a = p_i1553_2_;
                this.field_76147_d = p_i1553_1_;
            }

            public final long func_76146_a()
            {
                return this.field_76150_a;
            }

            public final Object func_76145_b()
            {
                return this.field_76148_b;
            }

            public final boolean equals(Object p_equals_1_)
            {
                if (!(p_equals_1_ instanceof LongHashMap.Entry))
                {
                    return false;
                }
                else
                {
                    LongHashMap.Entry entry = (LongHashMap.Entry)p_equals_1_;
                    Long olong = Long.valueOf(this.func_76146_a());
                    Long olong1 = Long.valueOf(entry.func_76146_a());

                    if (olong == olong1 || olong != null && olong.equals(olong1))
                    {
                        Object object1 = this.func_76145_b();
                        Object object2 = entry.func_76145_b();

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
                return LongHashMap.func_76155_g(this.field_76150_a);
            }

            public final String toString()
            {
                return this.func_76146_a() + "=" + this.func_76145_b();
            }
        }
}