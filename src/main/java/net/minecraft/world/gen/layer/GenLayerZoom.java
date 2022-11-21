package net.minecraft.world.gen.layer;

public class GenLayerZoom extends GenLayer
{
    private static final String __OBFID = "CL_00000572";

    public GenLayerZoom(long p_i2134_1_, GenLayer p_i2134_3_)
    {
        super(p_i2134_1_);
        super.field_75909_a = p_i2134_3_;
    }

    public int[] func_75904_a(int p_75904_1_, int p_75904_2_, int p_75904_3_, int p_75904_4_)
    {
        int i1 = p_75904_1_ >> 1;
        int j1 = p_75904_2_ >> 1;
        int k1 = (p_75904_3_ >> 1) + 2;
        int l1 = (p_75904_4_ >> 1) + 2;
        int[] aint = this.field_75909_a.func_75904_a(i1, j1, k1, l1);
        int i2 = k1 - 1 << 1;
        int j2 = l1 - 1 << 1;
        int[] aint1 = IntCache.func_76445_a(i2 * j2);
        int l2;

        for (int k2 = 0; k2 < l1 - 1; ++k2)
        {
            l2 = (k2 << 1) * i2;
            int i3 = 0;
            int j3 = aint[i3 + 0 + (k2 + 0) * k1];

            for (int k3 = aint[i3 + 0 + (k2 + 1) * k1]; i3 < k1 - 1; ++i3)
            {
                this.func_75903_a((long)(i3 + i1 << 1), (long)(k2 + j1 << 1));
                int l3 = aint[i3 + 1 + (k2 + 0) * k1];
                int i4 = aint[i3 + 1 + (k2 + 1) * k1];
                aint1[l2] = j3;
                aint1[l2++ + i2] = this.func_151619_a(new int[] {j3, k3});
                aint1[l2] = this.func_151619_a(new int[] {j3, l3});
                aint1[l2++ + i2] = this.func_151617_b(j3, l3, k3, i4);
                j3 = l3;
                k3 = i4;
            }
        }

        int[] aint2 = IntCache.func_76445_a(p_75904_3_ * p_75904_4_);

        for (l2 = 0; l2 < p_75904_4_; ++l2)
        {
            System.arraycopy(aint1, (l2 + (p_75904_2_ & 1)) * i2 + (p_75904_1_ & 1), aint2, l2 * p_75904_3_, p_75904_3_);
        }

        return aint2;
    }

    public static GenLayer func_75915_a(long p_75915_0_, GenLayer p_75915_2_, int p_75915_3_)
    {
        Object object = p_75915_2_;

        for (int k = 0; k < p_75915_3_; ++k)
        {
            object = new GenLayerZoom(p_75915_0_ + (long)k, (GenLayer)object);
        }

        return (GenLayer)object;
    }
}