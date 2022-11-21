package net.minecraft.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Arrays;

public class NBTTagIntArray extends NBTBase
{
    private int[] field_74749_a;
    private static final String __OBFID = "CL_00001221";

    NBTTagIntArray() {}

    public NBTTagIntArray(int[] p_i45132_1_)
    {
        this.field_74749_a = p_i45132_1_;
    }

    void func_74734_a(DataOutput p_74734_1_) throws IOException
    {
        p_74734_1_.writeInt(this.field_74749_a.length);

        for (int i = 0; i < this.field_74749_a.length; ++i)
        {
            p_74734_1_.writeInt(this.field_74749_a[i]);
        }
    }

    void func_152446_a(DataInput p_152446_1_, int p_152446_2_, NBTSizeTracker p_152446_3_) throws IOException
    {
        int j = p_152446_1_.readInt();
        p_152446_3_.func_152450_a((long)(32 * j));
        this.field_74749_a = new int[j];

        for (int k = 0; k < j; ++k)
        {
            this.field_74749_a[k] = p_152446_1_.readInt();
        }
    }

    public byte func_74732_a()
    {
        return (byte)11;
    }

    public String toString()
    {
        String s = "[";
        int[] aint = this.field_74749_a;
        int i = aint.length;

        for (int j = 0; j < i; ++j)
        {
            int k = aint[j];
            s = s + k + ",";
        }

        return s + "]";
    }

    public NBTBase func_74737_b()
    {
        int[] aint = new int[this.field_74749_a.length];
        System.arraycopy(this.field_74749_a, 0, aint, 0, this.field_74749_a.length);
        return new NBTTagIntArray(aint);
    }

    public boolean equals(Object p_equals_1_)
    {
        return super.equals(p_equals_1_) ? Arrays.equals(this.field_74749_a, ((NBTTagIntArray)p_equals_1_).field_74749_a) : false;
    }

    public int hashCode()
    {
        return super.hashCode() ^ Arrays.hashCode(this.field_74749_a);
    }

    public int[] func_150302_c()
    {
        return this.field_74749_a;
    }
}