package net.minecraft.world.gen;

import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenFire;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenGlowStone1;
import net.minecraft.world.gen.feature.WorldGenGlowStone2;
import net.minecraft.world.gen.feature.WorldGenHellLava;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.structure.MapGenNetherBridge;

public class ChunkProviderHell implements IChunkProvider
{
    private Random field_73181_i;
    private NoiseGeneratorOctaves field_73178_j;
    private NoiseGeneratorOctaves field_73179_k;
    private NoiseGeneratorOctaves field_73176_l;
    private NoiseGeneratorOctaves field_73177_m;
    private NoiseGeneratorOctaves field_73174_n;
    public NoiseGeneratorOctaves field_73173_a;
    public NoiseGeneratorOctaves field_73171_b;
    private World field_73175_o;
    private double[] field_73186_p;
    public MapGenNetherBridge field_73172_c = new MapGenNetherBridge();
    private double[] field_73185_q = new double[256];
    private double[] field_73184_r = new double[256];
    private double[] field_73183_s = new double[256];
    private MapGenBase field_73182_t = new MapGenCavesHell();
    double[] field_73169_d;
    double[] field_73170_e;
    double[] field_73167_f;
    double[] field_73168_g;
    double[] field_73180_h;
    private static final String __OBFID = "CL_00000392";

    public ChunkProviderHell(World p_i2005_1_, long p_i2005_2_)
    {
        this.field_73175_o = p_i2005_1_;
        this.field_73181_i = new Random(p_i2005_2_);
        this.field_73178_j = new NoiseGeneratorOctaves(this.field_73181_i, 16);
        this.field_73179_k = new NoiseGeneratorOctaves(this.field_73181_i, 16);
        this.field_73176_l = new NoiseGeneratorOctaves(this.field_73181_i, 8);
        this.field_73177_m = new NoiseGeneratorOctaves(this.field_73181_i, 4);
        this.field_73174_n = new NoiseGeneratorOctaves(this.field_73181_i, 4);
        this.field_73173_a = new NoiseGeneratorOctaves(this.field_73181_i, 10);
        this.field_73171_b = new NoiseGeneratorOctaves(this.field_73181_i, 16);
    }

    public void func_147419_a(int p_147419_1_, int p_147419_2_, Block[] p_147419_3_)
    {
        byte b0 = 4;
        byte b1 = 32;
        int k = b0 + 1;
        byte b2 = 17;
        int l = b0 + 1;
        this.field_73186_p = this.func_73164_a(this.field_73186_p, p_147419_1_ * b0, 0, p_147419_2_ * b0, k, b2, l);

        for (int i1 = 0; i1 < b0; ++i1)
        {
            for (int j1 = 0; j1 < b0; ++j1)
            {
                for (int k1 = 0; k1 < 16; ++k1)
                {
                    double d0 = 0.125D;
                    double d1 = this.field_73186_p[((i1 + 0) * l + j1 + 0) * b2 + k1 + 0];
                    double d2 = this.field_73186_p[((i1 + 0) * l + j1 + 1) * b2 + k1 + 0];
                    double d3 = this.field_73186_p[((i1 + 1) * l + j1 + 0) * b2 + k1 + 0];
                    double d4 = this.field_73186_p[((i1 + 1) * l + j1 + 1) * b2 + k1 + 0];
                    double d5 = (this.field_73186_p[((i1 + 0) * l + j1 + 0) * b2 + k1 + 1] - d1) * d0;
                    double d6 = (this.field_73186_p[((i1 + 0) * l + j1 + 1) * b2 + k1 + 1] - d2) * d0;
                    double d7 = (this.field_73186_p[((i1 + 1) * l + j1 + 0) * b2 + k1 + 1] - d3) * d0;
                    double d8 = (this.field_73186_p[((i1 + 1) * l + j1 + 1) * b2 + k1 + 1] - d4) * d0;

                    for (int l1 = 0; l1 < 8; ++l1)
                    {
                        double d9 = 0.25D;
                        double d10 = d1;
                        double d11 = d2;
                        double d12 = (d3 - d1) * d9;
                        double d13 = (d4 - d2) * d9;

                        for (int i2 = 0; i2 < 4; ++i2)
                        {
                            int j2 = i2 + i1 * 4 << 11 | 0 + j1 * 4 << 7 | k1 * 8 + l1;
                            short short1 = 128;
                            double d14 = 0.25D;
                            double d15 = d10;
                            double d16 = (d11 - d10) * d14;

                            for (int k2 = 0; k2 < 4; ++k2)
                            {
                                Block block = null;

                                if (k1 * 8 + l1 < b1)
                                {
                                    block = Blocks.field_150353_l;
                                }

                                if (d15 > 0.0D)
                                {
                                    block = Blocks.field_150424_aL;
                                }

                                p_147419_3_[j2] = block;
                                j2 += short1;
                                d15 += d16;
                            }

                            d10 += d12;
                            d11 += d13;
                        }

                        d1 += d5;
                        d2 += d6;
                        d3 += d7;
                        d4 += d8;
                    }
                }
            }
        }
    }

    public void func_147418_b(int p_147418_1_, int p_147418_2_, Block[] p_147418_3_)
    {
        byte b0 = 64;
        double d0 = 0.03125D;
        this.field_73185_q = this.field_73177_m.func_76304_a(this.field_73185_q, p_147418_1_ * 16, p_147418_2_ * 16, 0, 16, 16, 1, d0, d0, 1.0D);
        this.field_73184_r = this.field_73177_m.func_76304_a(this.field_73184_r, p_147418_1_ * 16, 109, p_147418_2_ * 16, 16, 1, 16, d0, 1.0D, d0);
        this.field_73183_s = this.field_73174_n.func_76304_a(this.field_73183_s, p_147418_1_ * 16, p_147418_2_ * 16, 0, 16, 16, 1, d0 * 2.0D, d0 * 2.0D, d0 * 2.0D);

        for (int k = 0; k < 16; ++k)
        {
            for (int l = 0; l < 16; ++l)
            {
                boolean flag = this.field_73185_q[k + l * 16] + this.field_73181_i.nextDouble() * 0.2D > 0.0D;
                boolean flag1 = this.field_73184_r[k + l * 16] + this.field_73181_i.nextDouble() * 0.2D > 0.0D;
                int i1 = (int)(this.field_73183_s[k + l * 16] / 3.0D + 3.0D + this.field_73181_i.nextDouble() * 0.25D);
                int j1 = -1;
                Block block = Blocks.field_150424_aL;
                Block block1 = Blocks.field_150424_aL;

                for (int k1 = 127; k1 >= 0; --k1)
                {
                    int l1 = (l * 16 + k) * 128 + k1;

                    if (k1 < 127 - this.field_73181_i.nextInt(5) && k1 > 0 + this.field_73181_i.nextInt(5))
                    {
                        Block block2 = p_147418_3_[l1];

                        if (block2 != null && block2.func_149688_o() != Material.field_151579_a)
                        {
                            if (block2 == Blocks.field_150424_aL)
                            {
                                if (j1 == -1)
                                {
                                    if (i1 <= 0)
                                    {
                                        block = null;
                                        block1 = Blocks.field_150424_aL;
                                    }
                                    else if (k1 >= b0 - 4 && k1 <= b0 + 1)
                                    {
                                        block = Blocks.field_150424_aL;
                                        block1 = Blocks.field_150424_aL;

                                        if (flag1)
                                        {
                                            block = Blocks.field_150351_n;
                                            block1 = Blocks.field_150424_aL;
                                        }

                                        if (flag)
                                        {
                                            block = Blocks.field_150425_aM;
                                            block1 = Blocks.field_150425_aM;
                                        }
                                    }

                                    if (k1 < b0 && (block == null || block.func_149688_o() == Material.field_151579_a))
                                    {
                                        block = Blocks.field_150353_l;
                                    }

                                    j1 = i1;

                                    if (k1 >= b0 - 1)
                                    {
                                        p_147418_3_[l1] = block;
                                    }
                                    else
                                    {
                                        p_147418_3_[l1] = block1;
                                    }
                                }
                                else if (j1 > 0)
                                {
                                    --j1;
                                    p_147418_3_[l1] = block1;
                                }
                            }
                        }
                        else
                        {
                            j1 = -1;
                        }
                    }
                    else
                    {
                        p_147418_3_[l1] = Blocks.field_150357_h;
                    }
                }
            }
        }
    }

    public Chunk func_73158_c(int p_73158_1_, int p_73158_2_)
    {
        return this.func_73154_d(p_73158_1_, p_73158_2_);
    }

    public Chunk func_73154_d(int p_73154_1_, int p_73154_2_)
    {
        this.field_73181_i.setSeed((long)p_73154_1_ * 341873128712L + (long)p_73154_2_ * 132897987541L);
        Block[] ablock = new Block[32768];
        this.func_147419_a(p_73154_1_, p_73154_2_, ablock);
        this.func_147418_b(p_73154_1_, p_73154_2_, ablock);
        this.field_73182_t.func_151539_a(this, this.field_73175_o, p_73154_1_, p_73154_2_, ablock);
        this.field_73172_c.func_151539_a(this, this.field_73175_o, p_73154_1_, p_73154_2_, ablock);
        Chunk chunk = new Chunk(this.field_73175_o, ablock, p_73154_1_, p_73154_2_);
        BiomeGenBase[] abiomegenbase = this.field_73175_o.func_72959_q().func_76933_b((BiomeGenBase[])null, p_73154_1_ * 16, p_73154_2_ * 16, 16, 16);
        byte[] abyte = chunk.func_76605_m();

        for (int k = 0; k < abyte.length; ++k)
        {
            abyte[k] = (byte)abiomegenbase[k].field_76756_M;
        }

        chunk.func_76613_n();
        return chunk;
    }

    private double[] func_73164_a(double[] p_73164_1_, int p_73164_2_, int p_73164_3_, int p_73164_4_, int p_73164_5_, int p_73164_6_, int p_73164_7_)
    {
        if (p_73164_1_ == null)
        {
            p_73164_1_ = new double[p_73164_5_ * p_73164_6_ * p_73164_7_];
        }

        double d0 = 684.412D;
        double d1 = 2053.236D;
        this.field_73168_g = this.field_73173_a.func_76304_a(this.field_73168_g, p_73164_2_, p_73164_3_, p_73164_4_, p_73164_5_, 1, p_73164_7_, 1.0D, 0.0D, 1.0D);
        this.field_73180_h = this.field_73171_b.func_76304_a(this.field_73180_h, p_73164_2_, p_73164_3_, p_73164_4_, p_73164_5_, 1, p_73164_7_, 100.0D, 0.0D, 100.0D);
        this.field_73169_d = this.field_73176_l.func_76304_a(this.field_73169_d, p_73164_2_, p_73164_3_, p_73164_4_, p_73164_5_, p_73164_6_, p_73164_7_, d0 / 80.0D, d1 / 60.0D, d0 / 80.0D);
        this.field_73170_e = this.field_73178_j.func_76304_a(this.field_73170_e, p_73164_2_, p_73164_3_, p_73164_4_, p_73164_5_, p_73164_6_, p_73164_7_, d0, d1, d0);
        this.field_73167_f = this.field_73179_k.func_76304_a(this.field_73167_f, p_73164_2_, p_73164_3_, p_73164_4_, p_73164_5_, p_73164_6_, p_73164_7_, d0, d1, d0);
        int k1 = 0;
        int l1 = 0;
        double[] adouble1 = new double[p_73164_6_];
        int i2;

        for (i2 = 0; i2 < p_73164_6_; ++i2)
        {
            adouble1[i2] = Math.cos((double)i2 * Math.PI * 6.0D / (double)p_73164_6_) * 2.0D;
            double d2 = (double)i2;

            if (i2 > p_73164_6_ / 2)
            {
                d2 = (double)(p_73164_6_ - 1 - i2);
            }

            if (d2 < 4.0D)
            {
                d2 = 4.0D - d2;
                adouble1[i2] -= d2 * d2 * d2 * 10.0D;
            }
        }

        for (i2 = 0; i2 < p_73164_5_; ++i2)
        {
            for (int k2 = 0; k2 < p_73164_7_; ++k2)
            {
                double d3 = (this.field_73168_g[l1] + 256.0D) / 512.0D;

                if (d3 > 1.0D)
                {
                    d3 = 1.0D;
                }

                double d4 = 0.0D;
                double d5 = this.field_73180_h[l1] / 8000.0D;

                if (d5 < 0.0D)
                {
                    d5 = -d5;
                }

                d5 = d5 * 3.0D - 3.0D;

                if (d5 < 0.0D)
                {
                    d5 /= 2.0D;

                    if (d5 < -1.0D)
                    {
                        d5 = -1.0D;
                    }

                    d5 /= 1.4D;
                    d5 /= 2.0D;
                    d3 = 0.0D;
                }
                else
                {
                    if (d5 > 1.0D)
                    {
                        d5 = 1.0D;
                    }

                    d5 /= 6.0D;
                }

                d3 += 0.5D;
                d5 = d5 * (double)p_73164_6_ / 16.0D;
                ++l1;

                for (int j2 = 0; j2 < p_73164_6_; ++j2)
                {
                    double d6 = 0.0D;
                    double d7 = adouble1[j2];
                    double d8 = this.field_73170_e[k1] / 512.0D;
                    double d9 = this.field_73167_f[k1] / 512.0D;
                    double d10 = (this.field_73169_d[k1] / 10.0D + 1.0D) / 2.0D;

                    if (d10 < 0.0D)
                    {
                        d6 = d8;
                    }
                    else if (d10 > 1.0D)
                    {
                        d6 = d9;
                    }
                    else
                    {
                        d6 = d8 + (d9 - d8) * d10;
                    }

                    d6 -= d7;
                    double d11;

                    if (j2 > p_73164_6_ - 4)
                    {
                        d11 = (double)((float)(j2 - (p_73164_6_ - 4)) / 3.0F);
                        d6 = d6 * (1.0D - d11) + -10.0D * d11;
                    }

                    if ((double)j2 < d4)
                    {
                        d11 = (d4 - (double)j2) / 4.0D;

                        if (d11 < 0.0D)
                        {
                            d11 = 0.0D;
                        }

                        if (d11 > 1.0D)
                        {
                            d11 = 1.0D;
                        }

                        d6 = d6 * (1.0D - d11) + -10.0D * d11;
                    }

                    p_73164_1_[k1] = d6;
                    ++k1;
                }
            }
        }

        return p_73164_1_;
    }

    public boolean func_73149_a(int p_73149_1_, int p_73149_2_)
    {
        return true;
    }

    public void func_73153_a(IChunkProvider p_73153_1_, int p_73153_2_, int p_73153_3_)
    {
        BlockFalling.field_149832_M = true;
        int k = p_73153_2_ * 16;
        int l = p_73153_3_ * 16;
        this.field_73172_c.func_75051_a(this.field_73175_o, this.field_73181_i, p_73153_2_, p_73153_3_);
        int i1;
        int j1;
        int k1;
        int l1;

        for (i1 = 0; i1 < 8; ++i1)
        {
            j1 = k + this.field_73181_i.nextInt(16) + 8;
            k1 = this.field_73181_i.nextInt(120) + 4;
            l1 = l + this.field_73181_i.nextInt(16) + 8;
            (new WorldGenHellLava(Blocks.field_150356_k, false)).func_76484_a(this.field_73175_o, this.field_73181_i, j1, k1, l1);
        }

        i1 = this.field_73181_i.nextInt(this.field_73181_i.nextInt(10) + 1) + 1;
        int i2;

        for (j1 = 0; j1 < i1; ++j1)
        {
            k1 = k + this.field_73181_i.nextInt(16) + 8;
            l1 = this.field_73181_i.nextInt(120) + 4;
            i2 = l + this.field_73181_i.nextInt(16) + 8;
            (new WorldGenFire()).func_76484_a(this.field_73175_o, this.field_73181_i, k1, l1, i2);
        }

        i1 = this.field_73181_i.nextInt(this.field_73181_i.nextInt(10) + 1);

        for (j1 = 0; j1 < i1; ++j1)
        {
            k1 = k + this.field_73181_i.nextInt(16) + 8;
            l1 = this.field_73181_i.nextInt(120) + 4;
            i2 = l + this.field_73181_i.nextInt(16) + 8;
            (new WorldGenGlowStone1()).func_76484_a(this.field_73175_o, this.field_73181_i, k1, l1, i2);
        }

        for (j1 = 0; j1 < 10; ++j1)
        {
            k1 = k + this.field_73181_i.nextInt(16) + 8;
            l1 = this.field_73181_i.nextInt(128);
            i2 = l + this.field_73181_i.nextInt(16) + 8;
            (new WorldGenGlowStone2()).func_76484_a(this.field_73175_o, this.field_73181_i, k1, l1, i2);
        }

        if (this.field_73181_i.nextInt(1) == 0)
        {
            j1 = k + this.field_73181_i.nextInt(16) + 8;
            k1 = this.field_73181_i.nextInt(128);
            l1 = l + this.field_73181_i.nextInt(16) + 8;
            (new WorldGenFlowers(Blocks.field_150338_P)).func_76484_a(this.field_73175_o, this.field_73181_i, j1, k1, l1);
        }

        if (this.field_73181_i.nextInt(1) == 0)
        {
            j1 = k + this.field_73181_i.nextInt(16) + 8;
            k1 = this.field_73181_i.nextInt(128);
            l1 = l + this.field_73181_i.nextInt(16) + 8;
            (new WorldGenFlowers(Blocks.field_150337_Q)).func_76484_a(this.field_73175_o, this.field_73181_i, j1, k1, l1);
        }

        WorldGenMinable worldgenminable = new WorldGenMinable(Blocks.field_150449_bY, 13, Blocks.field_150424_aL);
        int j2;

        for (k1 = 0; k1 < 16; ++k1)
        {
            l1 = k + this.field_73181_i.nextInt(16);
            i2 = this.field_73181_i.nextInt(108) + 10;
            j2 = l + this.field_73181_i.nextInt(16);
            worldgenminable.func_76484_a(this.field_73175_o, this.field_73181_i, l1, i2, j2);
        }

        for (k1 = 0; k1 < 16; ++k1)
        {
            l1 = k + this.field_73181_i.nextInt(16);
            i2 = this.field_73181_i.nextInt(108) + 10;
            j2 = l + this.field_73181_i.nextInt(16);
            (new WorldGenHellLava(Blocks.field_150356_k, true)).func_76484_a(this.field_73175_o, this.field_73181_i, l1, i2, j2);
        }

        BlockFalling.field_149832_M = false;
    }

    public boolean func_73151_a(boolean p_73151_1_, IProgressUpdate p_73151_2_)
    {
        return true;
    }

    public void func_104112_b() {}

    public boolean func_73156_b()
    {
        return false;
    }

    public boolean func_73157_c()
    {
        return true;
    }

    public String func_73148_d()
    {
        return "HellRandomLevelSource";
    }

    public List func_73155_a(EnumCreatureType p_73155_1_, int p_73155_2_, int p_73155_3_, int p_73155_4_)
    {
        if (p_73155_1_ == EnumCreatureType.monster)
        {
            if (this.field_73172_c.func_75048_a(p_73155_2_, p_73155_3_, p_73155_4_))
            {
                return this.field_73172_c.func_75059_a();
            }

            if (this.field_73172_c.func_142038_b(p_73155_2_, p_73155_3_, p_73155_4_) && this.field_73175_o.func_147439_a(p_73155_2_, p_73155_3_ - 1, p_73155_4_) == Blocks.field_150385_bj)
            {
                return this.field_73172_c.func_75059_a();
            }
        }

        BiomeGenBase biomegenbase = this.field_73175_o.func_72807_a(p_73155_2_, p_73155_4_);
        return biomegenbase.func_76747_a(p_73155_1_);
    }

    public ChunkPosition func_147416_a(World p_147416_1_, String p_147416_2_, int p_147416_3_, int p_147416_4_, int p_147416_5_)
    {
        return null;
    }

    public int func_73152_e()
    {
        return 0;
    }

    public void func_82695_e(int p_82695_1_, int p_82695_2_)
    {
        this.field_73172_c.func_151539_a(this, this.field_73175_o, p_82695_1_, p_82695_2_, (Block[])null);
    }
}