package net.minecraft.world.chunk;

public class NibbleArray
{
    public final byte[] field_76585_a;
    private final int field_76583_b;
    private final int field_76584_c;
    private static final String __OBFID = "CL_00000371";

    public NibbleArray(int p_i1992_1_, int p_i1992_2_)
    {
        this.field_76585_a = new byte[p_i1992_1_ >> 1];
        this.field_76583_b = p_i1992_2_;
        this.field_76584_c = p_i1992_2_ + 4;
    }

    public NibbleArray(byte[] p_i1993_1_, int p_i1993_2_)
    {
        this.field_76585_a = p_i1993_1_;
        this.field_76583_b = p_i1993_2_;
        this.field_76584_c = p_i1993_2_ + 4;
    }

    public int func_76582_a(int p_76582_1_, int p_76582_2_, int p_76582_3_)
    {
        int l = p_76582_2_ << this.field_76584_c | p_76582_3_ << this.field_76583_b | p_76582_1_;
        int i1 = l >> 1;
        int j1 = l & 1;
        return j1 == 0 ? this.field_76585_a[i1] & 15 : this.field_76585_a[i1] >> 4 & 15;
    }

    public void func_76581_a(int p_76581_1_, int p_76581_2_, int p_76581_3_, int p_76581_4_)
    {
        int i1 = p_76581_2_ << this.field_76584_c | p_76581_3_ << this.field_76583_b | p_76581_1_;
        int j1 = i1 >> 1;
        int k1 = i1 & 1;

        if (k1 == 0)
        {
            this.field_76585_a[j1] = (byte)(this.field_76585_a[j1] & 240 | p_76581_4_ & 15);
        }
        else
        {
            this.field_76585_a[j1] = (byte)(this.field_76585_a[j1] & 15 | (p_76581_4_ & 15) << 4);
        }
    }
}