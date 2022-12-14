package net.minecraft.world.chunk.storage;

public class NibbleArrayReader
{
    public final byte[] field_76689_a;
    private final int field_76687_b;
    private final int field_76688_c;
    private static final String __OBFID = "CL_00000376";

    public NibbleArrayReader(byte[] p_i1998_1_, int p_i1998_2_)
    {
        this.field_76689_a = p_i1998_1_;
        this.field_76687_b = p_i1998_2_;
        this.field_76688_c = p_i1998_2_ + 4;
    }

    public int func_76686_a(int p_76686_1_, int p_76686_2_, int p_76686_3_)
    {
        int l = p_76686_1_ << this.field_76688_c | p_76686_3_ << this.field_76687_b | p_76686_2_;
        int i1 = l >> 1;
        int j1 = l & 1;
        return j1 == 0 ? this.field_76689_a[i1] & 15 : this.field_76689_a[i1] >> 4 & 15;
    }
}