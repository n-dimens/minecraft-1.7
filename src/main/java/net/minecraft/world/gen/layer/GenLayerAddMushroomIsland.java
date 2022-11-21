package net.minecraft.world.gen.layer;

import net.minecraft.world.biome.BiomeGenBase;

public class GenLayerAddMushroomIsland extends GenLayer
{
    private static final String __OBFID = "CL_00000552";

    public GenLayerAddMushroomIsland(long p_i2120_1_, GenLayer p_i2120_3_)
    {
        super(p_i2120_1_);
        this.field_75909_a = p_i2120_3_;
    }

    public int[] func_75904_a(int p_75904_1_, int p_75904_2_, int p_75904_3_, int p_75904_4_)
    {
        int i1 = p_75904_1_ - 1;
        int j1 = p_75904_2_ - 1;
        int k1 = p_75904_3_ + 2;
        int l1 = p_75904_4_ + 2;
        int[] aint = this.field_75909_a.func_75904_a(i1, j1, k1, l1);
        int[] aint1 = IntCache.func_76445_a(p_75904_3_ * p_75904_4_);

        for (int i2 = 0; i2 < p_75904_4_; ++i2)
        {
            for (int j2 = 0; j2 < p_75904_3_; ++j2)
            {
                int k2 = aint[j2 + 0 + (i2 + 0) * k1];
                int l2 = aint[j2 + 2 + (i2 + 0) * k1];
                int i3 = aint[j2 + 0 + (i2 + 2) * k1];
                int j3 = aint[j2 + 2 + (i2 + 2) * k1];
                int k3 = aint[j2 + 1 + (i2 + 1) * k1];
                this.func_75903_a((long)(j2 + p_75904_1_), (long)(i2 + p_75904_2_));

                if (k3 == 0 && k2 == 0 && l2 == 0 && i3 == 0 && j3 == 0 && this.func_75902_a(100) == 0)
                {
                    aint1[j2 + i2 * p_75904_3_] = BiomeGenBase.field_76789_p.field_76756_M;
                }
                else
                {
                    aint1[j2 + i2 * p_75904_3_] = k3;
                }
            }
        }

        return aint1;
    }
}