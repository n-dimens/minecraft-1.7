package net.minecraft.world.gen.layer;

import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;

public class GenLayerBiome extends GenLayer
{
    private BiomeGenBase[] field_151623_c;
    private BiomeGenBase[] field_151621_d;
    private BiomeGenBase[] field_151622_e;
    private BiomeGenBase[] field_151620_f;
    private static final String __OBFID = "CL_00000555";

    public GenLayerBiome(long p_i2122_1_, GenLayer p_i2122_3_, WorldType p_i2122_4_)
    {
        super(p_i2122_1_);
        this.field_151623_c = new BiomeGenBase[] {BiomeGenBase.field_76769_d, BiomeGenBase.field_76769_d, BiomeGenBase.field_76769_d, BiomeGenBase.field_150588_X, BiomeGenBase.field_150588_X, BiomeGenBase.field_76772_c};
        this.field_151621_d = new BiomeGenBase[] {BiomeGenBase.field_76767_f, BiomeGenBase.field_150585_R, BiomeGenBase.field_76770_e, BiomeGenBase.field_76772_c, BiomeGenBase.field_150583_P, BiomeGenBase.field_76780_h};
        this.field_151622_e = new BiomeGenBase[] {BiomeGenBase.field_76767_f, BiomeGenBase.field_76770_e, BiomeGenBase.field_76768_g, BiomeGenBase.field_76772_c};
        this.field_151620_f = new BiomeGenBase[] {BiomeGenBase.field_76774_n, BiomeGenBase.field_76774_n, BiomeGenBase.field_76774_n, BiomeGenBase.field_150584_S};
        this.field_75909_a = p_i2122_3_;

        if (p_i2122_4_ == WorldType.field_77136_e)
        {
            this.field_151623_c = new BiomeGenBase[] {BiomeGenBase.field_76769_d, BiomeGenBase.field_76767_f, BiomeGenBase.field_76770_e, BiomeGenBase.field_76780_h, BiomeGenBase.field_76772_c, BiomeGenBase.field_76768_g};
        }
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
                int k1 = aint[j1 + i1 * p_75904_3_];
                int l1 = (k1 & 3840) >> 8;
                k1 &= -3841;

                if (func_151618_b(k1))
                {
                    aint1[j1 + i1 * p_75904_3_] = k1;
                }
                else if (k1 == BiomeGenBase.field_76789_p.field_76756_M)
                {
                    aint1[j1 + i1 * p_75904_3_] = k1;
                }
                else if (k1 == 1)
                {
                    if (l1 > 0)
                    {
                        if (this.func_75902_a(3) == 0)
                        {
                            aint1[j1 + i1 * p_75904_3_] = BiomeGenBase.field_150608_ab.field_76756_M;
                        }
                        else
                        {
                            aint1[j1 + i1 * p_75904_3_] = BiomeGenBase.field_150607_aa.field_76756_M;
                        }
                    }
                    else
                    {
                        aint1[j1 + i1 * p_75904_3_] = this.field_151623_c[this.func_75902_a(this.field_151623_c.length)].field_76756_M;
                    }
                }
                else if (k1 == 2)
                {
                    if (l1 > 0)
                    {
                        aint1[j1 + i1 * p_75904_3_] = BiomeGenBase.field_76782_w.field_76756_M;
                    }
                    else
                    {
                        aint1[j1 + i1 * p_75904_3_] = this.field_151621_d[this.func_75902_a(this.field_151621_d.length)].field_76756_M;
                    }
                }
                else if (k1 == 3)
                {
                    if (l1 > 0)
                    {
                        aint1[j1 + i1 * p_75904_3_] = BiomeGenBase.field_150578_U.field_76756_M;
                    }
                    else
                    {
                        aint1[j1 + i1 * p_75904_3_] = this.field_151622_e[this.func_75902_a(this.field_151622_e.length)].field_76756_M;
                    }
                }
                else if (k1 == 4)
                {
                    aint1[j1 + i1 * p_75904_3_] = this.field_151620_f[this.func_75902_a(this.field_151620_f.length)].field_76756_M;
                }
                else
                {
                    aint1[j1 + i1 * p_75904_3_] = BiomeGenBase.field_76789_p.field_76756_M;
                }
            }
        }

        return aint1;
    }
}