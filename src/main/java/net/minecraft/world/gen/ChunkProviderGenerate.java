package net.minecraft.world.gen;

import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.SpawnerAnimals;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenDungeons;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.structure.MapGenMineshaft;
import net.minecraft.world.gen.structure.MapGenScatteredFeature;
import net.minecraft.world.gen.structure.MapGenStronghold;
import net.minecraft.world.gen.structure.MapGenVillage;

public class ChunkProviderGenerate implements IChunkProvider
{
    private Random field_73220_k;
    private NoiseGeneratorOctaves field_147431_j;
    private NoiseGeneratorOctaves field_147432_k;
    private NoiseGeneratorOctaves field_147429_l;
    private NoiseGeneratorPerlin field_147430_m;
    public NoiseGeneratorOctaves field_73214_a;
    public NoiseGeneratorOctaves field_73212_b;
    public NoiseGeneratorOctaves field_73213_c;
    private World field_73230_p;
    private final boolean field_73229_q;
    private WorldType field_147435_p;
    private final double[] field_147434_q;
    private final float[] field_147433_r;
    private double[] field_73227_s = new double[256];
    private MapGenBase field_73226_t = new MapGenCaves();
    private MapGenStronghold field_73225_u = new MapGenStronghold();
    private MapGenVillage field_73224_v = new MapGenVillage();
    private MapGenMineshaft field_73223_w = new MapGenMineshaft();
    private MapGenScatteredFeature field_73233_x = new MapGenScatteredFeature();
    private MapGenBase field_73232_y = new MapGenRavine();
    private BiomeGenBase[] field_73231_z;
    double[] field_147427_d;
    double[] field_147428_e;
    double[] field_147425_f;
    double[] field_147426_g;
    int[][] field_73219_j = new int[32][32];
    private static final String __OBFID = "CL_00000396";

    public ChunkProviderGenerate(World p_i2006_1_, long p_i2006_2_, boolean p_i2006_4_)
    {
        this.field_73230_p = p_i2006_1_;
        this.field_73229_q = p_i2006_4_;
        this.field_147435_p = p_i2006_1_.func_72912_H().func_76067_t();
        this.field_73220_k = new Random(p_i2006_2_);
        this.field_147431_j = new NoiseGeneratorOctaves(this.field_73220_k, 16);
        this.field_147432_k = new NoiseGeneratorOctaves(this.field_73220_k, 16);
        this.field_147429_l = new NoiseGeneratorOctaves(this.field_73220_k, 8);
        this.field_147430_m = new NoiseGeneratorPerlin(this.field_73220_k, 4);
        this.field_73214_a = new NoiseGeneratorOctaves(this.field_73220_k, 10);
        this.field_73212_b = new NoiseGeneratorOctaves(this.field_73220_k, 16);
        this.field_73213_c = new NoiseGeneratorOctaves(this.field_73220_k, 8);
        this.field_147434_q = new double[825];
        this.field_147433_r = new float[25];

        for (int j = -2; j <= 2; ++j)
        {
            for (int k = -2; k <= 2; ++k)
            {
                float f = 10.0F / MathHelper.func_76129_c((float)(j * j + k * k) + 0.2F);
                this.field_147433_r[j + 2 + (k + 2) * 5] = f;
            }
        }
    }

    public void func_147424_a(int p_147424_1_, int p_147424_2_, Block[] p_147424_3_)
    {
        byte b0 = 63;
        this.field_73231_z = this.field_73230_p.func_72959_q().func_76937_a(this.field_73231_z, p_147424_1_ * 4 - 2, p_147424_2_ * 4 - 2, 10, 10);
        this.func_147423_a(p_147424_1_ * 4, 0, p_147424_2_ * 4);

        for (int k = 0; k < 4; ++k)
        {
            int l = k * 5;
            int i1 = (k + 1) * 5;

            for (int j1 = 0; j1 < 4; ++j1)
            {
                int k1 = (l + j1) * 33;
                int l1 = (l + j1 + 1) * 33;
                int i2 = (i1 + j1) * 33;
                int j2 = (i1 + j1 + 1) * 33;

                for (int k2 = 0; k2 < 32; ++k2)
                {
                    double d0 = 0.125D;
                    double d1 = this.field_147434_q[k1 + k2];
                    double d2 = this.field_147434_q[l1 + k2];
                    double d3 = this.field_147434_q[i2 + k2];
                    double d4 = this.field_147434_q[j2 + k2];
                    double d5 = (this.field_147434_q[k1 + k2 + 1] - d1) * d0;
                    double d6 = (this.field_147434_q[l1 + k2 + 1] - d2) * d0;
                    double d7 = (this.field_147434_q[i2 + k2 + 1] - d3) * d0;
                    double d8 = (this.field_147434_q[j2 + k2 + 1] - d4) * d0;

                    for (int l2 = 0; l2 < 8; ++l2)
                    {
                        double d9 = 0.25D;
                        double d10 = d1;
                        double d11 = d2;
                        double d12 = (d3 - d1) * d9;
                        double d13 = (d4 - d2) * d9;

                        for (int i3 = 0; i3 < 4; ++i3)
                        {
                            int j3 = i3 + k * 4 << 12 | 0 + j1 * 4 << 8 | k2 * 8 + l2;
                            short short1 = 256;
                            j3 -= short1;
                            double d14 = 0.25D;
                            double d16 = (d11 - d10) * d14;
                            double d15 = d10 - d16;

                            for (int k3 = 0; k3 < 4; ++k3)
                            {
                                if ((d15 += d16) > 0.0D)
                                {
                                    p_147424_3_[j3 += short1] = Blocks.field_150348_b;
                                }
                                else if (k2 * 8 + l2 < b0)
                                {
                                    p_147424_3_[j3 += short1] = Blocks.field_150355_j;
                                }
                                else
                                {
                                    p_147424_3_[j3 += short1] = null;
                                }
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

    public void func_147422_a(int p_147422_1_, int p_147422_2_, Block[] p_147422_3_, byte[] p_147422_4_, BiomeGenBase[] p_147422_5_)
    {
        double d0 = 0.03125D;
        this.field_73227_s = this.field_147430_m.func_151599_a(this.field_73227_s, (double)(p_147422_1_ * 16), (double)(p_147422_2_ * 16), 16, 16, d0 * 2.0D, d0 * 2.0D, 1.0D);

        for (int k = 0; k < 16; ++k)
        {
            for (int l = 0; l < 16; ++l)
            {
                BiomeGenBase biomegenbase = p_147422_5_[l + k * 16];
                biomegenbase.func_150573_a(this.field_73230_p, this.field_73220_k, p_147422_3_, p_147422_4_, p_147422_1_ * 16 + k, p_147422_2_ * 16 + l, this.field_73227_s[l + k * 16]);
            }
        }
    }

    public Chunk func_73158_c(int p_73158_1_, int p_73158_2_)
    {
        return this.func_73154_d(p_73158_1_, p_73158_2_);
    }

    public Chunk func_73154_d(int p_73154_1_, int p_73154_2_)
    {
        this.field_73220_k.setSeed((long)p_73154_1_ * 341873128712L + (long)p_73154_2_ * 132897987541L);
        Block[] ablock = new Block[65536];
        byte[] abyte = new byte[65536];
        this.func_147424_a(p_73154_1_, p_73154_2_, ablock);
        this.field_73231_z = this.field_73230_p.func_72959_q().func_76933_b(this.field_73231_z, p_73154_1_ * 16, p_73154_2_ * 16, 16, 16);
        this.func_147422_a(p_73154_1_, p_73154_2_, ablock, abyte, this.field_73231_z);
        this.field_73226_t.func_151539_a(this, this.field_73230_p, p_73154_1_, p_73154_2_, ablock);
        this.field_73232_y.func_151539_a(this, this.field_73230_p, p_73154_1_, p_73154_2_, ablock);

        if (this.field_73229_q)
        {
            this.field_73223_w.func_151539_a(this, this.field_73230_p, p_73154_1_, p_73154_2_, ablock);
            this.field_73224_v.func_151539_a(this, this.field_73230_p, p_73154_1_, p_73154_2_, ablock);
            this.field_73225_u.func_151539_a(this, this.field_73230_p, p_73154_1_, p_73154_2_, ablock);
            this.field_73233_x.func_151539_a(this, this.field_73230_p, p_73154_1_, p_73154_2_, ablock);
        }

        Chunk chunk = new Chunk(this.field_73230_p, ablock, abyte, p_73154_1_, p_73154_2_);
        byte[] abyte1 = chunk.func_76605_m();

        for (int k = 0; k < abyte1.length; ++k)
        {
            abyte1[k] = (byte)this.field_73231_z[k].field_76756_M;
        }

        chunk.func_76603_b();
        return chunk;
    }

    private void func_147423_a(int p_147423_1_, int p_147423_2_, int p_147423_3_)
    {
        double d0 = 684.412D;
        double d1 = 684.412D;
        double d2 = 512.0D;
        double d3 = 512.0D;
        this.field_147426_g = this.field_73212_b.func_76305_a(this.field_147426_g, p_147423_1_, p_147423_3_, 5, 5, 200.0D, 200.0D, 0.5D);
        this.field_147427_d = this.field_147429_l.func_76304_a(this.field_147427_d, p_147423_1_, p_147423_2_, p_147423_3_, 5, 33, 5, 8.555150000000001D, 4.277575000000001D, 8.555150000000001D);
        this.field_147428_e = this.field_147431_j.func_76304_a(this.field_147428_e, p_147423_1_, p_147423_2_, p_147423_3_, 5, 33, 5, 684.412D, 684.412D, 684.412D);
        this.field_147425_f = this.field_147432_k.func_76304_a(this.field_147425_f, p_147423_1_, p_147423_2_, p_147423_3_, 5, 33, 5, 684.412D, 684.412D, 684.412D);
        boolean flag1 = false;
        boolean flag = false;
        int l = 0;
        int i1 = 0;
        double d4 = 8.5D;

        for (int j1 = 0; j1 < 5; ++j1)
        {
            for (int k1 = 0; k1 < 5; ++k1)
            {
                float f = 0.0F;
                float f1 = 0.0F;
                float f2 = 0.0F;
                byte b0 = 2;
                BiomeGenBase biomegenbase = this.field_73231_z[j1 + 2 + (k1 + 2) * 10];

                for (int l1 = -b0; l1 <= b0; ++l1)
                {
                    for (int i2 = -b0; i2 <= b0; ++i2)
                    {
                        BiomeGenBase biomegenbase1 = this.field_73231_z[j1 + l1 + 2 + (k1 + i2 + 2) * 10];
                        float f3 = biomegenbase1.field_76748_D;
                        float f4 = biomegenbase1.field_76749_E;

                        if (this.field_147435_p == WorldType.field_151360_e && f3 > 0.0F)
                        {
                            f3 = 1.0F + f3 * 2.0F;
                            f4 = 1.0F + f4 * 4.0F;
                        }

                        float f5 = this.field_147433_r[l1 + 2 + (i2 + 2) * 5] / (f3 + 2.0F);

                        if (biomegenbase1.field_76748_D > biomegenbase.field_76748_D)
                        {
                            f5 /= 2.0F;
                        }

                        f += f4 * f5;
                        f1 += f3 * f5;
                        f2 += f5;
                    }
                }

                f /= f2;
                f1 /= f2;
                f = f * 0.9F + 0.1F;
                f1 = (f1 * 4.0F - 1.0F) / 8.0F;
                double d12 = this.field_147426_g[i1] / 8000.0D;

                if (d12 < 0.0D)
                {
                    d12 = -d12 * 0.3D;
                }

                d12 = d12 * 3.0D - 2.0D;

                if (d12 < 0.0D)
                {
                    d12 /= 2.0D;

                    if (d12 < -1.0D)
                    {
                        d12 = -1.0D;
                    }

                    d12 /= 1.4D;
                    d12 /= 2.0D;
                }
                else
                {
                    if (d12 > 1.0D)
                    {
                        d12 = 1.0D;
                    }

                    d12 /= 8.0D;
                }

                ++i1;
                double d13 = (double)f1;
                double d14 = (double)f;
                d13 += d12 * 0.2D;
                d13 = d13 * 8.5D / 8.0D;
                double d5 = 8.5D + d13 * 4.0D;

                for (int j2 = 0; j2 < 33; ++j2)
                {
                    double d6 = ((double)j2 - d5) * 12.0D * 128.0D / 256.0D / d14;

                    if (d6 < 0.0D)
                    {
                        d6 *= 4.0D;
                    }

                    double d7 = this.field_147428_e[l] / 512.0D;
                    double d8 = this.field_147425_f[l] / 512.0D;
                    double d9 = (this.field_147427_d[l] / 10.0D + 1.0D) / 2.0D;
                    double d10 = MathHelper.func_151238_b(d7, d8, d9) - d6;

                    if (j2 > 29)
                    {
                        double d11 = (double)((float)(j2 - 29) / 3.0F);
                        d10 = d10 * (1.0D - d11) + -10.0D * d11;
                    }

                    this.field_147434_q[l] = d10;
                    ++l;
                }
            }
        }
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
        BiomeGenBase biomegenbase = this.field_73230_p.func_72807_a(k + 16, l + 16);
        this.field_73220_k.setSeed(this.field_73230_p.func_72905_C());
        long i1 = this.field_73220_k.nextLong() / 2L * 2L + 1L;
        long j1 = this.field_73220_k.nextLong() / 2L * 2L + 1L;
        this.field_73220_k.setSeed((long)p_73153_2_ * i1 + (long)p_73153_3_ * j1 ^ this.field_73230_p.func_72905_C());
        boolean flag = false;

        if (this.field_73229_q)
        {
            this.field_73223_w.func_75051_a(this.field_73230_p, this.field_73220_k, p_73153_2_, p_73153_3_);
            flag = this.field_73224_v.func_75051_a(this.field_73230_p, this.field_73220_k, p_73153_2_, p_73153_3_);
            this.field_73225_u.func_75051_a(this.field_73230_p, this.field_73220_k, p_73153_2_, p_73153_3_);
            this.field_73233_x.func_75051_a(this.field_73230_p, this.field_73220_k, p_73153_2_, p_73153_3_);
        }

        int k1;
        int l1;
        int i2;

        if (biomegenbase != BiomeGenBase.field_76769_d && biomegenbase != BiomeGenBase.field_76786_s && !flag && this.field_73220_k.nextInt(4) == 0)
        {
            k1 = k + this.field_73220_k.nextInt(16) + 8;
            l1 = this.field_73220_k.nextInt(256);
            i2 = l + this.field_73220_k.nextInt(16) + 8;
            (new WorldGenLakes(Blocks.field_150355_j)).func_76484_a(this.field_73230_p, this.field_73220_k, k1, l1, i2);
        }

        if (!flag && this.field_73220_k.nextInt(8) == 0)
        {
            k1 = k + this.field_73220_k.nextInt(16) + 8;
            l1 = this.field_73220_k.nextInt(this.field_73220_k.nextInt(248) + 8);
            i2 = l + this.field_73220_k.nextInt(16) + 8;

            if (l1 < 63 || this.field_73220_k.nextInt(10) == 0)
            {
                (new WorldGenLakes(Blocks.field_150353_l)).func_76484_a(this.field_73230_p, this.field_73220_k, k1, l1, i2);
            }
        }

        for (k1 = 0; k1 < 8; ++k1)
        {
            l1 = k + this.field_73220_k.nextInt(16) + 8;
            i2 = this.field_73220_k.nextInt(256);
            int j2 = l + this.field_73220_k.nextInt(16) + 8;
            (new WorldGenDungeons()).func_76484_a(this.field_73230_p, this.field_73220_k, l1, i2, j2);
        }

        biomegenbase.func_76728_a(this.field_73230_p, this.field_73220_k, k, l);
        SpawnerAnimals.func_77191_a(this.field_73230_p, biomegenbase, k + 8, l + 8, 16, 16, this.field_73220_k);
        k += 8;
        l += 8;

        for (k1 = 0; k1 < 16; ++k1)
        {
            for (l1 = 0; l1 < 16; ++l1)
            {
                i2 = this.field_73230_p.func_72874_g(k + k1, l + l1);

                if (this.field_73230_p.func_72884_u(k1 + k, i2 - 1, l1 + l))
                {
                    this.field_73230_p.func_147465_d(k1 + k, i2 - 1, l1 + l, Blocks.field_150432_aD, 0, 2);
                }

                if (this.field_73230_p.func_147478_e(k1 + k, i2, l1 + l, true))
                {
                    this.field_73230_p.func_147465_d(k1 + k, i2, l1 + l, Blocks.field_150431_aC, 0, 2);
                }
            }
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
        return "RandomLevelSource";
    }

    public List func_73155_a(EnumCreatureType p_73155_1_, int p_73155_2_, int p_73155_3_, int p_73155_4_)
    {
        BiomeGenBase biomegenbase = this.field_73230_p.func_72807_a(p_73155_2_, p_73155_4_);
        return p_73155_1_ == EnumCreatureType.monster && this.field_73233_x.func_143030_a(p_73155_2_, p_73155_3_, p_73155_4_) ? this.field_73233_x.func_82667_a() : biomegenbase.func_76747_a(p_73155_1_);
    }

    public ChunkPosition func_147416_a(World p_147416_1_, String p_147416_2_, int p_147416_3_, int p_147416_4_, int p_147416_5_)
    {
        return "Stronghold".equals(p_147416_2_) && this.field_73225_u != null ? this.field_73225_u.func_151545_a(p_147416_1_, p_147416_3_, p_147416_4_, p_147416_5_) : null;
    }

    public int func_73152_e()
    {
        return 0;
    }

    public void func_82695_e(int p_82695_1_, int p_82695_2_)
    {
        if (this.field_73229_q)
        {
            this.field_73223_w.func_151539_a(this, this.field_73230_p, p_82695_1_, p_82695_2_, (Block[])null);
            this.field_73224_v.func_151539_a(this, this.field_73230_p, p_82695_1_, p_82695_2_, (Block[])null);
            this.field_73225_u.func_151539_a(this, this.field_73230_p, p_82695_1_, p_82695_2_, (Block[])null);
            this.field_73233_x.func_151539_a(this, this.field_73230_p, p_82695_1_, p_82695_2_, (Block[])null);
        }
    }
}