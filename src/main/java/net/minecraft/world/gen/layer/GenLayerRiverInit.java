package net.minecraft.world.gen.layer;

public class GenLayerRiverInit extends GenLayer
{
    private static final String __OBFID = "CL_00000565";

    public GenLayerRiverInit(long p_i2127_1_, GenLayer p_i2127_3_)
    {
        super(p_i2127_1_);
        this.field_75909_a = p_i2127_3_;
    }

    public int[] func_75904_a(int p_75904_1_, int p_75904_2_, int p_75904_3_, int p_75904_4_)
    {
        int[] aint = this.field_75909_a.func_75904_a(p_75904_1_, p_75904_2_, p_75904_3_, p_75904_4_);
        int[] aint1 = IntCache.func_76445_a(p_75904_3_ * p_75904_4_);

        for (int i1 = 0; i1 < p_75904_4_; ++i1)
        {
            for (int j1 = 0; j1 < p_75904_3_; ++j1)
            {
                this.func_75903_a((long)(j1 + p_75904_1_), (long)(i1 + p_75904_2_));
                aint1[j1 + i1 * p_75904_3_] = aint[j1 + i1 * p_75904_3_] > 0 ? this.func_75902_a(299999) + 2 : 0;
            }
        }

        return aint1;
    }
}