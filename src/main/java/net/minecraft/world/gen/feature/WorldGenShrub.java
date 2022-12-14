package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class WorldGenShrub extends WorldGenTrees
{
    private int field_150528_a;
    private int field_150527_b;
    private static final String __OBFID = "CL_00000411";

    public WorldGenShrub(int p_i2015_1_, int p_i2015_2_)
    {
        super(false);
        this.field_150527_b = p_i2015_1_;
        this.field_150528_a = p_i2015_2_;
    }

    public boolean func_76484_a(World p_76484_1_, Random p_76484_2_, int p_76484_3_, int p_76484_4_, int p_76484_5_)
    {
        Block block;

        while (((block = p_76484_1_.func_147439_a(p_76484_3_, p_76484_4_, p_76484_5_)).func_149688_o() == Material.field_151579_a || block.func_149688_o() == Material.field_151584_j) && p_76484_4_ > 0)
        {
            --p_76484_4_;
        }

        Block block1 = p_76484_1_.func_147439_a(p_76484_3_, p_76484_4_, p_76484_5_);

        if (block1 == Blocks.DIRT || block1 == Blocks.GRASS)
        {
            ++p_76484_4_;
            this.func_150516_a(p_76484_1_, p_76484_3_, p_76484_4_, p_76484_5_, Blocks.LOG, this.field_150527_b);

            for (int l = p_76484_4_; l <= p_76484_4_ + 2; ++l)
            {
                int i1 = l - p_76484_4_;
                int j1 = 2 - i1;

                for (int k1 = p_76484_3_ - j1; k1 <= p_76484_3_ + j1; ++k1)
                {
                    int l1 = k1 - p_76484_3_;

                    for (int i2 = p_76484_5_ - j1; i2 <= p_76484_5_ + j1; ++i2)
                    {
                        int j2 = i2 - p_76484_5_;

                        if ((Math.abs(l1) != j1 || Math.abs(j2) != j1 || p_76484_2_.nextInt(2) != 0) && !p_76484_1_.func_147439_a(k1, l, i2).func_149730_j())
                        {
                            this.func_150516_a(p_76484_1_, k1, l, i2, Blocks.LEAVES, this.field_150528_a);
                        }
                    }
                }
            }
        }

        return true;
    }
}