package net.minecraft.world.gen.layer;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenJungle;
import net.minecraft.world.biome.BiomeGenMesa;

public class GenLayerShore extends GenLayer
{
    private static final String __OBFID = "CL_00000568";

    public GenLayerShore(long p_i2130_1_, GenLayer p_i2130_3_)
    {
        super(p_i2130_1_);
        this.field_75909_a = p_i2130_3_;
    }

    public int[] func_75904_a(int p_75904_1_, int p_75904_2_, int p_75904_3_, int p_75904_4_)
    {
        int[] aint = this.field_75909_a.func_75904_a(p_75904_1_ - 1, p_75904_2_ - 1, p_75904_3_ + 2, p_75904_4_ + 2);
        int[] aint1 = IntCache.func_76445_a(p_75904_3_ * p_75904_4_);

        for (int i1 = 0; i1 < p_75904_4_; ++i1)
        {
            for (int j1 = 0; j1 < p_75904_3_; ++j1)
            {
                this.func_75903_a((long)(j1 + p_75904_1_), (long)(i1 + p_75904_2_));
                int k1 = aint[j1 + 1 + (i1 + 1) * (p_75904_3_ + 2)];
                BiomeGenBase biomegenbase = BiomeGenBase.func_150568_d(k1);
                int l1;
                int i2;
                int j2;
                int k2;

                if (k1 == BiomeGenBase.field_76789_p.field_76756_M)
                {
                    l1 = aint[j1 + 1 + (i1 + 1 - 1) * (p_75904_3_ + 2)];
                    i2 = aint[j1 + 1 + 1 + (i1 + 1) * (p_75904_3_ + 2)];
                    j2 = aint[j1 + 1 - 1 + (i1 + 1) * (p_75904_3_ + 2)];
                    k2 = aint[j1 + 1 + (i1 + 1 + 1) * (p_75904_3_ + 2)];

                    if (l1 != BiomeGenBase.field_76771_b.field_76756_M && i2 != BiomeGenBase.field_76771_b.field_76756_M && j2 != BiomeGenBase.field_76771_b.field_76756_M && k2 != BiomeGenBase.field_76771_b.field_76756_M)
                    {
                        aint1[j1 + i1 * p_75904_3_] = k1;
                    }
                    else
                    {
                        aint1[j1 + i1 * p_75904_3_] = BiomeGenBase.field_76788_q.field_76756_M;
                    }
                }
                else if (biomegenbase != null && biomegenbase.func_150562_l() == BiomeGenJungle.class)
                {
                    l1 = aint[j1 + 1 + (i1 + 1 - 1) * (p_75904_3_ + 2)];
                    i2 = aint[j1 + 1 + 1 + (i1 + 1) * (p_75904_3_ + 2)];
                    j2 = aint[j1 + 1 - 1 + (i1 + 1) * (p_75904_3_ + 2)];
                    k2 = aint[j1 + 1 + (i1 + 1 + 1) * (p_75904_3_ + 2)];

                    if (this.func_151631_c(l1) && this.func_151631_c(i2) && this.func_151631_c(j2) && this.func_151631_c(k2))
                    {
                        if (!func_151618_b(l1) && !func_151618_b(i2) && !func_151618_b(j2) && !func_151618_b(k2))
                        {
                            aint1[j1 + i1 * p_75904_3_] = k1;
                        }
                        else
                        {
                            aint1[j1 + i1 * p_75904_3_] = BiomeGenBase.field_76787_r.field_76756_M;
                        }
                    }
                    else
                    {
                        aint1[j1 + i1 * p_75904_3_] = BiomeGenBase.field_150574_L.field_76756_M;
                    }
                }
                else if (k1 != BiomeGenBase.field_76770_e.field_76756_M && k1 != BiomeGenBase.field_150580_W.field_76756_M && k1 != BiomeGenBase.field_76783_v.field_76756_M)
                {
                    if (biomegenbase != null && biomegenbase.func_150559_j())
                    {
                        this.func_151632_a(aint, aint1, j1, i1, p_75904_3_, k1, BiomeGenBase.field_150577_O.field_76756_M);
                    }
                    else if (k1 != BiomeGenBase.field_150589_Z.field_76756_M && k1 != BiomeGenBase.field_150607_aa.field_76756_M)
                    {
                        if (k1 != BiomeGenBase.field_76771_b.field_76756_M && k1 != BiomeGenBase.field_150575_M.field_76756_M && k1 != BiomeGenBase.field_76781_i.field_76756_M && k1 != BiomeGenBase.field_76780_h.field_76756_M)
                        {
                            l1 = aint[j1 + 1 + (i1 + 1 - 1) * (p_75904_3_ + 2)];
                            i2 = aint[j1 + 1 + 1 + (i1 + 1) * (p_75904_3_ + 2)];
                            j2 = aint[j1 + 1 - 1 + (i1 + 1) * (p_75904_3_ + 2)];
                            k2 = aint[j1 + 1 + (i1 + 1 + 1) * (p_75904_3_ + 2)];

                            if (!func_151618_b(l1) && !func_151618_b(i2) && !func_151618_b(j2) && !func_151618_b(k2))
                            {
                                aint1[j1 + i1 * p_75904_3_] = k1;
                            }
                            else
                            {
                                aint1[j1 + i1 * p_75904_3_] = BiomeGenBase.field_76787_r.field_76756_M;
                            }
                        }
                        else
                        {
                            aint1[j1 + i1 * p_75904_3_] = k1;
                        }
                    }
                    else
                    {
                        l1 = aint[j1 + 1 + (i1 + 1 - 1) * (p_75904_3_ + 2)];
                        i2 = aint[j1 + 1 + 1 + (i1 + 1) * (p_75904_3_ + 2)];
                        j2 = aint[j1 + 1 - 1 + (i1 + 1) * (p_75904_3_ + 2)];
                        k2 = aint[j1 + 1 + (i1 + 1 + 1) * (p_75904_3_ + 2)];

                        if (!func_151618_b(l1) && !func_151618_b(i2) && !func_151618_b(j2) && !func_151618_b(k2))
                        {
                            if (this.func_151633_d(l1) && this.func_151633_d(i2) && this.func_151633_d(j2) && this.func_151633_d(k2))
                            {
                                aint1[j1 + i1 * p_75904_3_] = k1;
                            }
                            else
                            {
                                aint1[j1 + i1 * p_75904_3_] = BiomeGenBase.field_76769_d.field_76756_M;
                            }
                        }
                        else
                        {
                            aint1[j1 + i1 * p_75904_3_] = k1;
                        }
                    }
                }
                else
                {
                    this.func_151632_a(aint, aint1, j1, i1, p_75904_3_, k1, BiomeGenBase.field_150576_N.field_76756_M);
                }
            }
        }

        return aint1;
    }

    private void func_151632_a(int[] p_151632_1_, int[] p_151632_2_, int p_151632_3_, int p_151632_4_, int p_151632_5_, int p_151632_6_, int p_151632_7_)
    {
        if (func_151618_b(p_151632_6_))
        {
            p_151632_2_[p_151632_3_ + p_151632_4_ * p_151632_5_] = p_151632_6_;
        }
        else
        {
            int j1 = p_151632_1_[p_151632_3_ + 1 + (p_151632_4_ + 1 - 1) * (p_151632_5_ + 2)];
            int k1 = p_151632_1_[p_151632_3_ + 1 + 1 + (p_151632_4_ + 1) * (p_151632_5_ + 2)];
            int l1 = p_151632_1_[p_151632_3_ + 1 - 1 + (p_151632_4_ + 1) * (p_151632_5_ + 2)];
            int i2 = p_151632_1_[p_151632_3_ + 1 + (p_151632_4_ + 1 + 1) * (p_151632_5_ + 2)];

            if (!func_151618_b(j1) && !func_151618_b(k1) && !func_151618_b(l1) && !func_151618_b(i2))
            {
                p_151632_2_[p_151632_3_ + p_151632_4_ * p_151632_5_] = p_151632_6_;
            }
            else
            {
                p_151632_2_[p_151632_3_ + p_151632_4_ * p_151632_5_] = p_151632_7_;
            }
        }
    }

    private boolean func_151631_c(int p_151631_1_)
    {
        return BiomeGenBase.func_150568_d(p_151631_1_) != null && BiomeGenBase.func_150568_d(p_151631_1_).func_150562_l() == BiomeGenJungle.class ? true : p_151631_1_ == BiomeGenBase.field_150574_L.field_76756_M || p_151631_1_ == BiomeGenBase.field_76782_w.field_76756_M || p_151631_1_ == BiomeGenBase.field_76792_x.field_76756_M || p_151631_1_ == BiomeGenBase.field_76767_f.field_76756_M || p_151631_1_ == BiomeGenBase.field_76768_g.field_76756_M || func_151618_b(p_151631_1_);
    }

    private boolean func_151633_d(int p_151633_1_)
    {
        return BiomeGenBase.func_150568_d(p_151633_1_) != null && BiomeGenBase.func_150568_d(p_151633_1_) instanceof BiomeGenMesa;
    }
}