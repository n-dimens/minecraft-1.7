package net.minecraft.world.biome;

import java.util.Random;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenBigMushroom;
import net.minecraft.world.gen.feature.WorldGenCactus;
import net.minecraft.world.gen.feature.WorldGenClay;
import net.minecraft.world.gen.feature.WorldGenDeadBush;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenLiquids;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenPumpkin;
import net.minecraft.world.gen.feature.WorldGenReed;
import net.minecraft.world.gen.feature.WorldGenSand;
import net.minecraft.world.gen.feature.WorldGenWaterlily;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeDecorator
{
    public World field_76815_a;
    public Random field_76813_b;
    public int field_76814_c;
    public int field_76811_d;
    public WorldGenerator field_76809_f = new WorldGenClay(4);
    public WorldGenerator field_76810_g;
    public WorldGenerator field_76822_h;
    public WorldGenerator field_76823_i;
    public WorldGenerator field_76820_j;
    public WorldGenerator field_76821_k;
    public WorldGenerator field_76818_l;
    public WorldGenerator field_76819_m;
    public WorldGenerator field_76816_n;
    public WorldGenerator field_76817_o;
    public WorldGenerator field_76831_p;
    public WorldGenFlowers field_150514_p;
    public WorldGenerator field_76828_s;
    public WorldGenerator field_76827_t;
    public WorldGenerator field_76826_u;
    public WorldGenerator field_76825_v;
    public WorldGenerator field_76824_w;
    public WorldGenerator field_76834_x;
    public int field_76833_y;
    public int field_76832_z;
    public int field_76802_A;
    public int field_76803_B;
    public int field_76804_C;
    public int field_76798_D;
    public int field_76799_E;
    public int field_76800_F;
    public int field_76801_G;
    public int field_76805_H;
    public int field_76806_I;
    public int field_76807_J;
    public boolean field_76808_K;
    private static final String __OBFID = "CL_00000164";

    public BiomeDecorator()
    {
        this.field_76810_g = new WorldGenSand(Blocks.field_150354_m, 7);
        this.field_76822_h = new WorldGenSand(Blocks.field_150351_n, 6);
        this.field_76823_i = new WorldGenMinable(Blocks.field_150346_d, 32);
        this.field_76820_j = new WorldGenMinable(Blocks.field_150351_n, 32);
        this.field_76821_k = new WorldGenMinable(Blocks.field_150365_q, 16);
        this.field_76818_l = new WorldGenMinable(Blocks.field_150366_p, 8);
        this.field_76819_m = new WorldGenMinable(Blocks.field_150352_o, 8);
        this.field_76816_n = new WorldGenMinable(Blocks.field_150450_ax, 7);
        this.field_76817_o = new WorldGenMinable(Blocks.field_150482_ag, 7);
        this.field_76831_p = new WorldGenMinable(Blocks.field_150369_x, 6);
        this.field_150514_p = new WorldGenFlowers(Blocks.field_150327_N);
        this.field_76828_s = new WorldGenFlowers(Blocks.field_150338_P);
        this.field_76827_t = new WorldGenFlowers(Blocks.field_150337_Q);
        this.field_76826_u = new WorldGenBigMushroom();
        this.field_76825_v = new WorldGenReed();
        this.field_76824_w = new WorldGenCactus();
        this.field_76834_x = new WorldGenWaterlily();
        this.field_76802_A = 2;
        this.field_76803_B = 1;
        this.field_76801_G = 1;
        this.field_76805_H = 3;
        this.field_76806_I = 1;
        this.field_76808_K = true;
    }

    public void func_150512_a(World p_150512_1_, Random p_150512_2_, BiomeGenBase p_150512_3_, int p_150512_4_, int p_150512_5_)
    {
        if (this.field_76815_a != null)
        {
            throw new RuntimeException("Already decorating!!");
        }
        else
        {
            this.field_76815_a = p_150512_1_;
            this.field_76813_b = p_150512_2_;
            this.field_76814_c = p_150512_4_;
            this.field_76811_d = p_150512_5_;
            this.func_150513_a(p_150512_3_);
            this.field_76815_a = null;
            this.field_76813_b = null;
        }
    }

    protected void func_150513_a(BiomeGenBase p_150513_1_)
    {
        this.func_76797_b();
        int i;
        int j;
        int k;

        for (i = 0; i < this.field_76805_H; ++i)
        {
            j = this.field_76814_c + this.field_76813_b.nextInt(16) + 8;
            k = this.field_76811_d + this.field_76813_b.nextInt(16) + 8;
            this.field_76810_g.func_76484_a(this.field_76815_a, this.field_76813_b, j, this.field_76815_a.func_72825_h(j, k), k);
        }

        for (i = 0; i < this.field_76806_I; ++i)
        {
            j = this.field_76814_c + this.field_76813_b.nextInt(16) + 8;
            k = this.field_76811_d + this.field_76813_b.nextInt(16) + 8;
            this.field_76809_f.func_76484_a(this.field_76815_a, this.field_76813_b, j, this.field_76815_a.func_72825_h(j, k), k);
        }

        for (i = 0; i < this.field_76801_G; ++i)
        {
            j = this.field_76814_c + this.field_76813_b.nextInt(16) + 8;
            k = this.field_76811_d + this.field_76813_b.nextInt(16) + 8;
            this.field_76822_h.func_76484_a(this.field_76815_a, this.field_76813_b, j, this.field_76815_a.func_72825_h(j, k), k);
        }

        i = this.field_76832_z;

        if (this.field_76813_b.nextInt(10) == 0)
        {
            ++i;
        }

        int l;
        int i1;

        for (j = 0; j < i; ++j)
        {
            k = this.field_76814_c + this.field_76813_b.nextInt(16) + 8;
            l = this.field_76811_d + this.field_76813_b.nextInt(16) + 8;
            i1 = this.field_76815_a.func_72976_f(k, l);
            WorldGenAbstractTree worldgenabstracttree = p_150513_1_.func_150567_a(this.field_76813_b);
            worldgenabstracttree.func_76487_a(1.0D, 1.0D, 1.0D);

            if (worldgenabstracttree.func_76484_a(this.field_76815_a, this.field_76813_b, k, i1, l))
            {
                worldgenabstracttree.func_150524_b(this.field_76815_a, this.field_76813_b, k, i1, l);
            }
        }

        for (j = 0; j < this.field_76807_J; ++j)
        {
            k = this.field_76814_c + this.field_76813_b.nextInt(16) + 8;
            l = this.field_76811_d + this.field_76813_b.nextInt(16) + 8;
            this.field_76826_u.func_76484_a(this.field_76815_a, this.field_76813_b, k, this.field_76815_a.func_72976_f(k, l), l);
        }

        for (j = 0; j < this.field_76802_A; ++j)
        {
            k = this.field_76814_c + this.field_76813_b.nextInt(16) + 8;
            l = this.field_76811_d + this.field_76813_b.nextInt(16) + 8;
            i1 = this.field_76813_b.nextInt(this.field_76815_a.func_72976_f(k, l) + 32);
            String s = p_150513_1_.func_150572_a(this.field_76813_b, k, i1, l);
            BlockFlower blockflower = BlockFlower.func_149857_e(s);

            if (blockflower.func_149688_o() != Material.field_151579_a)
            {
                this.field_150514_p.func_150550_a(blockflower, BlockFlower.func_149856_f(s));
                this.field_150514_p.func_76484_a(this.field_76815_a, this.field_76813_b, k, i1, l);
            }
        }

        for (j = 0; j < this.field_76803_B; ++j)
        {
            k = this.field_76814_c + this.field_76813_b.nextInt(16) + 8;
            l = this.field_76811_d + this.field_76813_b.nextInt(16) + 8;
            i1 = this.field_76813_b.nextInt(this.field_76815_a.func_72976_f(k, l) * 2);
            WorldGenerator worldgenerator = p_150513_1_.func_76730_b(this.field_76813_b);
            worldgenerator.func_76484_a(this.field_76815_a, this.field_76813_b, k, i1, l);
        }

        for (j = 0; j < this.field_76804_C; ++j)
        {
            k = this.field_76814_c + this.field_76813_b.nextInt(16) + 8;
            l = this.field_76811_d + this.field_76813_b.nextInt(16) + 8;
            i1 = this.field_76813_b.nextInt(this.field_76815_a.func_72976_f(k, l) * 2);
            (new WorldGenDeadBush(Blocks.field_150330_I)).func_76484_a(this.field_76815_a, this.field_76813_b, k, i1, l);
        }

        for (j = 0; j < this.field_76833_y; ++j)
        {
            k = this.field_76814_c + this.field_76813_b.nextInt(16) + 8;
            l = this.field_76811_d + this.field_76813_b.nextInt(16) + 8;

            for (i1 = this.field_76813_b.nextInt(this.field_76815_a.func_72976_f(k, l) * 2); i1 > 0 && this.field_76815_a.func_147437_c(k, i1 - 1, l); --i1)
            {
                ;
            }

            this.field_76834_x.func_76484_a(this.field_76815_a, this.field_76813_b, k, i1, l);
        }

        for (j = 0; j < this.field_76798_D; ++j)
        {
            if (this.field_76813_b.nextInt(4) == 0)
            {
                k = this.field_76814_c + this.field_76813_b.nextInt(16) + 8;
                l = this.field_76811_d + this.field_76813_b.nextInt(16) + 8;
                i1 = this.field_76815_a.func_72976_f(k, l);
                this.field_76828_s.func_76484_a(this.field_76815_a, this.field_76813_b, k, i1, l);
            }

            if (this.field_76813_b.nextInt(8) == 0)
            {
                k = this.field_76814_c + this.field_76813_b.nextInt(16) + 8;
                l = this.field_76811_d + this.field_76813_b.nextInt(16) + 8;
                i1 = this.field_76813_b.nextInt(this.field_76815_a.func_72976_f(k, l) * 2);
                this.field_76827_t.func_76484_a(this.field_76815_a, this.field_76813_b, k, i1, l);
            }
        }

        if (this.field_76813_b.nextInt(4) == 0)
        {
            j = this.field_76814_c + this.field_76813_b.nextInt(16) + 8;
            k = this.field_76811_d + this.field_76813_b.nextInt(16) + 8;
            l = this.field_76813_b.nextInt(this.field_76815_a.func_72976_f(j, k) * 2);
            this.field_76828_s.func_76484_a(this.field_76815_a, this.field_76813_b, j, l, k);
        }

        if (this.field_76813_b.nextInt(8) == 0)
        {
            j = this.field_76814_c + this.field_76813_b.nextInt(16) + 8;
            k = this.field_76811_d + this.field_76813_b.nextInt(16) + 8;
            l = this.field_76813_b.nextInt(this.field_76815_a.func_72976_f(j, k) * 2);
            this.field_76827_t.func_76484_a(this.field_76815_a, this.field_76813_b, j, l, k);
        }

        for (j = 0; j < this.field_76799_E; ++j)
        {
            k = this.field_76814_c + this.field_76813_b.nextInt(16) + 8;
            l = this.field_76811_d + this.field_76813_b.nextInt(16) + 8;
            i1 = this.field_76813_b.nextInt(this.field_76815_a.func_72976_f(k, l) * 2);
            this.field_76825_v.func_76484_a(this.field_76815_a, this.field_76813_b, k, i1, l);
        }

        for (j = 0; j < 10; ++j)
        {
            k = this.field_76814_c + this.field_76813_b.nextInt(16) + 8;
            l = this.field_76811_d + this.field_76813_b.nextInt(16) + 8;
            i1 = this.field_76813_b.nextInt(this.field_76815_a.func_72976_f(k, l) * 2);
            this.field_76825_v.func_76484_a(this.field_76815_a, this.field_76813_b, k, i1, l);
        }

        if (this.field_76813_b.nextInt(32) == 0)
        {
            j = this.field_76814_c + this.field_76813_b.nextInt(16) + 8;
            k = this.field_76811_d + this.field_76813_b.nextInt(16) + 8;
            l = this.field_76813_b.nextInt(this.field_76815_a.func_72976_f(j, k) * 2);
            (new WorldGenPumpkin()).func_76484_a(this.field_76815_a, this.field_76813_b, j, l, k);
        }

        for (j = 0; j < this.field_76800_F; ++j)
        {
            k = this.field_76814_c + this.field_76813_b.nextInt(16) + 8;
            l = this.field_76811_d + this.field_76813_b.nextInt(16) + 8;
            i1 = this.field_76813_b.nextInt(this.field_76815_a.func_72976_f(k, l) * 2);
            this.field_76824_w.func_76484_a(this.field_76815_a, this.field_76813_b, k, i1, l);
        }

        if (this.field_76808_K)
        {
            for (j = 0; j < 50; ++j)
            {
                k = this.field_76814_c + this.field_76813_b.nextInt(16) + 8;
                l = this.field_76813_b.nextInt(this.field_76813_b.nextInt(248) + 8);
                i1 = this.field_76811_d + this.field_76813_b.nextInt(16) + 8;
                (new WorldGenLiquids(Blocks.field_150358_i)).func_76484_a(this.field_76815_a, this.field_76813_b, k, l, i1);
            }

            for (j = 0; j < 20; ++j)
            {
                k = this.field_76814_c + this.field_76813_b.nextInt(16) + 8;
                l = this.field_76813_b.nextInt(this.field_76813_b.nextInt(this.field_76813_b.nextInt(240) + 8) + 8);
                i1 = this.field_76811_d + this.field_76813_b.nextInt(16) + 8;
                (new WorldGenLiquids(Blocks.field_150356_k)).func_76484_a(this.field_76815_a, this.field_76813_b, k, l, i1);
            }
        }
    }

    protected void func_76795_a(int p_76795_1_, WorldGenerator p_76795_2_, int p_76795_3_, int p_76795_4_)
    {
        for (int l = 0; l < p_76795_1_; ++l)
        {
            int i1 = this.field_76814_c + this.field_76813_b.nextInt(16);
            int j1 = this.field_76813_b.nextInt(p_76795_4_ - p_76795_3_) + p_76795_3_;
            int k1 = this.field_76811_d + this.field_76813_b.nextInt(16);
            p_76795_2_.func_76484_a(this.field_76815_a, this.field_76813_b, i1, j1, k1);
        }
    }

    protected void func_76793_b(int p_76793_1_, WorldGenerator p_76793_2_, int p_76793_3_, int p_76793_4_)
    {
        for (int l = 0; l < p_76793_1_; ++l)
        {
            int i1 = this.field_76814_c + this.field_76813_b.nextInt(16);
            int j1 = this.field_76813_b.nextInt(p_76793_4_) + this.field_76813_b.nextInt(p_76793_4_) + (p_76793_3_ - p_76793_4_);
            int k1 = this.field_76811_d + this.field_76813_b.nextInt(16);
            p_76793_2_.func_76484_a(this.field_76815_a, this.field_76813_b, i1, j1, k1);
        }
    }

    protected void func_76797_b()
    {
        this.func_76795_a(20, this.field_76823_i, 0, 256);
        this.func_76795_a(10, this.field_76820_j, 0, 256);
        this.func_76795_a(20, this.field_76821_k, 0, 128);
        this.func_76795_a(20, this.field_76818_l, 0, 64);
        this.func_76795_a(2, this.field_76819_m, 0, 32);
        this.func_76795_a(8, this.field_76816_n, 0, 16);
        this.func_76795_a(1, this.field_76817_o, 0, 16);
        this.func_76793_b(1, this.field_76831_p, 16, 16);
    }
}