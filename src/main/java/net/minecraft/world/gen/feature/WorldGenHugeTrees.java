package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public abstract class WorldGenHugeTrees extends WorldGenAbstractTree
{
    protected final int field_76522_a;
    protected final int field_76520_b;
    protected final int field_76521_c;
    protected int field_150538_d;
    private static final String __OBFID = "CL_00000423";

    public WorldGenHugeTrees(boolean p_i45458_1_, int p_i45458_2_, int p_i45458_3_, int p_i45458_4_, int p_i45458_5_)
    {
        super(p_i45458_1_);
        this.field_76522_a = p_i45458_2_;
        this.field_150538_d = p_i45458_3_;
        this.field_76520_b = p_i45458_4_;
        this.field_76521_c = p_i45458_5_;
    }

    protected int func_150533_a(Random p_150533_1_)
    {
        int i = p_150533_1_.nextInt(3) + this.field_76522_a;

        if (this.field_150538_d > 1)
        {
            i += p_150533_1_.nextInt(this.field_150538_d);
        }

        return i;
    }

    private boolean func_150536_b(World p_150536_1_, Random p_150536_2_, int p_150536_3_, int p_150536_4_, int p_150536_5_, int p_150536_6_)
    {
        boolean flag = true;

        if (p_150536_4_ >= 1 && p_150536_4_ + p_150536_6_ + 1 <= 256)
        {
            for (int i1 = p_150536_4_; i1 <= p_150536_4_ + 1 + p_150536_6_; ++i1)
            {
                byte b0 = 2;

                if (i1 == p_150536_4_)
                {
                    b0 = 1;
                }

                if (i1 >= p_150536_4_ + 1 + p_150536_6_ - 2)
                {
                    b0 = 2;
                }

                for (int j1 = p_150536_3_ - b0; j1 <= p_150536_3_ + b0 && flag; ++j1)
                {
                    for (int k1 = p_150536_5_ - b0; k1 <= p_150536_5_ + b0 && flag; ++k1)
                    {
                        if (i1 >= 0 && i1 < 256)
                        {
                            Block block = p_150536_1_.func_147439_a(j1, i1, k1);

                            if (!this.func_150523_a(block))
                            {
                                flag = false;
                            }
                        }
                        else
                        {
                            flag = false;
                        }
                    }
                }
            }

            return flag;
        }
        else
        {
            return false;
        }
    }

    private boolean func_150532_c(World p_150532_1_, Random p_150532_2_, int p_150532_3_, int p_150532_4_, int p_150532_5_)
    {
        Block block = p_150532_1_.func_147439_a(p_150532_3_, p_150532_4_ - 1, p_150532_5_);

        if ((block == Blocks.field_150349_c || block == Blocks.field_150346_d) && p_150532_4_ >= 2)
        {
            p_150532_1_.func_147465_d(p_150532_3_, p_150532_4_ - 1, p_150532_5_, Blocks.field_150346_d, 0, 2);
            p_150532_1_.func_147465_d(p_150532_3_ + 1, p_150532_4_ - 1, p_150532_5_, Blocks.field_150346_d, 0, 2);
            p_150532_1_.func_147465_d(p_150532_3_, p_150532_4_ - 1, p_150532_5_ + 1, Blocks.field_150346_d, 0, 2);
            p_150532_1_.func_147465_d(p_150532_3_ + 1, p_150532_4_ - 1, p_150532_5_ + 1, Blocks.field_150346_d, 0, 2);
            return true;
        }
        else
        {
            return false;
        }
    }

    protected boolean func_150537_a(World p_150537_1_, Random p_150537_2_, int p_150537_3_, int p_150537_4_, int p_150537_5_, int p_150537_6_)
    {
        return this.func_150536_b(p_150537_1_, p_150537_2_, p_150537_3_, p_150537_4_, p_150537_5_, p_150537_6_) && this.func_150532_c(p_150537_1_, p_150537_2_, p_150537_3_, p_150537_4_, p_150537_5_);
    }

    protected void func_150535_a(World p_150535_1_, int p_150535_2_, int p_150535_3_, int p_150535_4_, int p_150535_5_, Random p_150535_6_)
    {
        int i1 = p_150535_5_ * p_150535_5_;

        for (int j1 = p_150535_2_ - p_150535_5_; j1 <= p_150535_2_ + p_150535_5_ + 1; ++j1)
        {
            int k1 = j1 - p_150535_2_;

            for (int l1 = p_150535_4_ - p_150535_5_; l1 <= p_150535_4_ + p_150535_5_ + 1; ++l1)
            {
                int i2 = l1 - p_150535_4_;
                int j2 = k1 - 1;
                int k2 = i2 - 1;

                if (k1 * k1 + i2 * i2 <= i1 || j2 * j2 + k2 * k2 <= i1 || k1 * k1 + k2 * k2 <= i1 || j2 * j2 + i2 * i2 <= i1)
                {
                    Block block = p_150535_1_.func_147439_a(j1, p_150535_3_, l1);

                    if (block.func_149688_o() == Material.field_151579_a || block.func_149688_o() == Material.field_151584_j)
                    {
                        this.func_150516_a(p_150535_1_, j1, p_150535_3_, l1, Blocks.field_150362_t, this.field_76521_c);
                    }
                }
            }
        }
    }

    protected void func_150534_b(World p_150534_1_, int p_150534_2_, int p_150534_3_, int p_150534_4_, int p_150534_5_, Random p_150534_6_)
    {
        int i1 = p_150534_5_ * p_150534_5_;

        for (int j1 = p_150534_2_ - p_150534_5_; j1 <= p_150534_2_ + p_150534_5_; ++j1)
        {
            int k1 = j1 - p_150534_2_;

            for (int l1 = p_150534_4_ - p_150534_5_; l1 <= p_150534_4_ + p_150534_5_; ++l1)
            {
                int i2 = l1 - p_150534_4_;

                if (k1 * k1 + i2 * i2 <= i1)
                {
                    Block block = p_150534_1_.func_147439_a(j1, p_150534_3_, l1);

                    if (block.func_149688_o() == Material.field_151579_a || block.func_149688_o() == Material.field_151584_j)
                    {
                        this.func_150516_a(p_150534_1_, j1, p_150534_3_, l1, Blocks.field_150362_t, this.field_76521_c);
                    }
                }
            }
        }
    }
}