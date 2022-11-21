package net.minecraft.world.biome;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Arrays;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class BiomeGenMesa extends BiomeGenBase
{
    private byte[] field_150621_aC;
    private long field_150622_aD;
    private NoiseGeneratorPerlin field_150623_aE;
    private NoiseGeneratorPerlin field_150624_aF;
    private NoiseGeneratorPerlin field_150625_aG;
    private boolean field_150626_aH;
    private boolean field_150620_aI;
    private static final String __OBFID = "CL_00000176";

    public BiomeGenMesa(int p_i45380_1_, boolean p_i45380_2_, boolean p_i45380_3_)
    {
        super(p_i45380_1_);
        this.field_150626_aH = p_i45380_2_;
        this.field_150620_aI = p_i45380_3_;
        this.func_76745_m();
        this.func_76732_a(2.0F, 0.0F);
        this.field_76762_K.clear();
        this.field_76752_A = Blocks.field_150354_m;
        this.field_150604_aj = 1;
        this.field_76753_B = Blocks.field_150406_ce;
        this.field_76760_I.field_76832_z = -999;
        this.field_76760_I.field_76804_C = 20;
        this.field_76760_I.field_76799_E = 3;
        this.field_76760_I.field_76800_F = 5;
        this.field_76760_I.field_76802_A = 0;
        this.field_76762_K.clear();

        if (p_i45380_3_)
        {
            this.field_76760_I.field_76832_z = 5;
        }
    }

    public WorldGenAbstractTree func_150567_a(Random p_150567_1_)
    {
        return this.field_76757_N;
    }

    public void func_76728_a(World p_76728_1_, Random p_76728_2_, int p_76728_3_, int p_76728_4_)
    {
        super.func_76728_a(p_76728_1_, p_76728_2_, p_76728_3_, p_76728_4_);
    }

    public void func_150573_a(World p_150573_1_, Random p_150573_2_, Block[] p_150573_3_, byte[] p_150573_4_, int p_150573_5_, int p_150573_6_, double p_150573_7_)
    {
        if (this.field_150621_aC == null || this.field_150622_aD != p_150573_1_.func_72905_C())
        {
            this.func_150619_a(p_150573_1_.func_72905_C());
        }

        if (this.field_150623_aE == null || this.field_150624_aF == null || this.field_150622_aD != p_150573_1_.func_72905_C())
        {
            Random random1 = new Random(this.field_150622_aD);
            this.field_150623_aE = new NoiseGeneratorPerlin(random1, 4);
            this.field_150624_aF = new NoiseGeneratorPerlin(random1, 1);
        }

        this.field_150622_aD = p_150573_1_.func_72905_C();
        double d5 = 0.0D;
        int k;
        int l;

        if (this.field_150626_aH)
        {
            k = (p_150573_5_ & -16) + (p_150573_6_ & 15);
            l = (p_150573_6_ & -16) + (p_150573_5_ & 15);
            double d1 = Math.min(Math.abs(p_150573_7_), this.field_150623_aE.func_151601_a((double)k * 0.25D, (double)l * 0.25D));

            if (d1 > 0.0D)
            {
                double d2 = 0.001953125D;
                double d3 = Math.abs(this.field_150624_aF.func_151601_a((double)k * d2, (double)l * d2));
                d5 = d1 * d1 * 2.5D;
                double d4 = Math.ceil(d3 * 50.0D) + 14.0D;

                if (d5 > d4)
                {
                    d5 = d4;
                }

                d5 += 64.0D;
            }
        }

        k = p_150573_5_ & 15;
        l = p_150573_6_ & 15;
        boolean flag = true;
        Block block = Blocks.field_150406_ce;
        Block block2 = this.field_76753_B;
        int i1 = (int)(p_150573_7_ / 3.0D + 3.0D + p_150573_2_.nextDouble() * 0.25D);
        boolean flag1 = Math.cos(p_150573_7_ / 3.0D * Math.PI) > 0.0D;
        int j1 = -1;
        boolean flag2 = false;
        int k1 = p_150573_3_.length / 256;

        for (int l1 = 255; l1 >= 0; --l1)
        {
            int i2 = (l * 16 + k) * k1 + l1;

            if ((p_150573_3_[i2] == null || p_150573_3_[i2].func_149688_o() == Material.field_151579_a) && l1 < (int)d5)
            {
                p_150573_3_[i2] = Blocks.field_150348_b;
            }

            if (l1 <= 0 + p_150573_2_.nextInt(5))
            {
                p_150573_3_[i2] = Blocks.field_150357_h;
            }
            else
            {
                Block block1 = p_150573_3_[i2];

                if (block1 != null && block1.func_149688_o() != Material.field_151579_a)
                {
                    if (block1 == Blocks.field_150348_b)
                    {
                        byte b0;

                        if (j1 == -1)
                        {
                            flag2 = false;

                            if (i1 <= 0)
                            {
                                block = null;
                                block2 = Blocks.field_150348_b;
                            }
                            else if (l1 >= 59 && l1 <= 64)
                            {
                                block = Blocks.field_150406_ce;
                                block2 = this.field_76753_B;
                            }

                            if (l1 < 63 && (block == null || block.func_149688_o() == Material.field_151579_a))
                            {
                                block = Blocks.field_150355_j;
                            }

                            j1 = i1 + Math.max(0, l1 - 63);

                            if (l1 >= 62)
                            {
                                if (this.field_150620_aI && l1 > 86 + i1 * 2)
                                {
                                    if (flag1)
                                    {
                                        p_150573_3_[i2] = Blocks.field_150346_d;
                                        p_150573_4_[i2] = 1;
                                    }
                                    else
                                    {
                                        p_150573_3_[i2] = Blocks.field_150349_c;
                                    }
                                }
                                else if (l1 > 66 + i1)
                                {
                                    b0 = 16;

                                    if (l1 >= 64 && l1 <= 127)
                                    {
                                        if (!flag1)
                                        {
                                            b0 = this.func_150618_d(p_150573_5_, l1, p_150573_6_);
                                        }
                                    }
                                    else
                                    {
                                        b0 = 1;
                                    }

                                    if (b0 < 16)
                                    {
                                        p_150573_3_[i2] = Blocks.field_150406_ce;
                                        p_150573_4_[i2] = (byte)b0;
                                    }
                                    else
                                    {
                                        p_150573_3_[i2] = Blocks.field_150405_ch;
                                    }
                                }
                                else
                                {
                                    p_150573_3_[i2] = this.field_76752_A;
                                    p_150573_4_[i2] = (byte)this.field_150604_aj;
                                    flag2 = true;
                                }
                            }
                            else
                            {
                                p_150573_3_[i2] = block2;

                                if (block2 == Blocks.field_150406_ce)
                                {
                                    p_150573_4_[i2] = 1;
                                }
                            }
                        }
                        else if (j1 > 0)
                        {
                            --j1;

                            if (flag2)
                            {
                                p_150573_3_[i2] = Blocks.field_150406_ce;
                                p_150573_4_[i2] = 1;
                            }
                            else
                            {
                                b0 = this.func_150618_d(p_150573_5_, l1, p_150573_6_);

                                if (b0 < 16)
                                {
                                    p_150573_3_[i2] = Blocks.field_150406_ce;
                                    p_150573_4_[i2] = b0;
                                }
                                else
                                {
                                    p_150573_3_[i2] = Blocks.field_150405_ch;
                                }
                            }
                        }
                    }
                }
                else
                {
                    j1 = -1;
                }
            }
        }
    }

    public void func_150619_a(long p_150619_1_)
    {
        this.field_150621_aC = new byte[64];
        Arrays.fill(this.field_150621_aC, (byte)16);
        Random random = new Random(p_150619_1_);
        this.field_150625_aG = new NoiseGeneratorPerlin(random, 1);
        int j;

        for (j = 0; j < 64; ++j)
        {
            j += random.nextInt(5) + 1;

            if (j < 64)
            {
                this.field_150621_aC[j] = 1;
            }
        }

        j = random.nextInt(4) + 2;
        int k;
        int l;
        int i1;
        int j1;

        for (k = 0; k < j; ++k)
        {
            l = random.nextInt(3) + 1;
            i1 = random.nextInt(64);

            for (j1 = 0; i1 + j1 < 64 && j1 < l; ++j1)
            {
                this.field_150621_aC[i1 + j1] = 4;
            }
        }

        k = random.nextInt(4) + 2;
        int k1;

        for (l = 0; l < k; ++l)
        {
            i1 = random.nextInt(3) + 2;
            j1 = random.nextInt(64);

            for (k1 = 0; j1 + k1 < 64 && k1 < i1; ++k1)
            {
                this.field_150621_aC[j1 + k1] = 12;
            }
        }

        l = random.nextInt(4) + 2;

        for (i1 = 0; i1 < l; ++i1)
        {
            j1 = random.nextInt(3) + 1;
            k1 = random.nextInt(64);

            for (int l1 = 0; k1 + l1 < 64 && l1 < j1; ++l1)
            {
                this.field_150621_aC[k1 + l1] = 14;
            }
        }

        i1 = random.nextInt(3) + 3;
        j1 = 0;

        for (k1 = 0; k1 < i1; ++k1)
        {
            byte b0 = 1;
            j1 += random.nextInt(16) + 4;

            for (int i2 = 0; j1 + i2 < 64 && i2 < b0; ++i2)
            {
                this.field_150621_aC[j1 + i2] = 0;

                if (j1 + i2 > 1 && random.nextBoolean())
                {
                    this.field_150621_aC[j1 + i2 - 1] = 8;
                }

                if (j1 + i2 < 63 && random.nextBoolean())
                {
                    this.field_150621_aC[j1 + i2 + 1] = 8;
                }
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public int func_150571_c(int p_150571_1_, int p_150571_2_, int p_150571_3_)
    {
        return 10387789;
    }

    @SideOnly(Side.CLIENT)
    public int func_150558_b(int p_150558_1_, int p_150558_2_, int p_150558_3_)
    {
        return 9470285;
    }

    public byte func_150618_d(int p_150618_1_, int p_150618_2_, int p_150618_3_)
    {
        int l = (int)Math.round(this.field_150625_aG.func_151601_a((double)p_150618_1_ * 1.0D / 512.0D, (double)p_150618_1_ * 1.0D / 512.0D) * 2.0D);
        return this.field_150621_aC[(p_150618_2_ + l + 64) % 64];
    }

    public BiomeGenBase func_150566_k()
    {
        boolean flag = this.field_76756_M == BiomeGenBase.field_150589_Z.field_76756_M;
        BiomeGenMesa biomegenmesa = new BiomeGenMesa(this.field_76756_M + 128, flag, this.field_150620_aI);

        if (!flag)
        {
            biomegenmesa.func_150570_a(field_150591_g);
            biomegenmesa.func_76735_a(this.field_76791_y + " M");
        }
        else
        {
            biomegenmesa.func_76735_a(this.field_76791_y + " (Bryce)");
        }

        biomegenmesa.func_150557_a(this.field_76790_z, true);
        return biomegenmesa;
    }
}