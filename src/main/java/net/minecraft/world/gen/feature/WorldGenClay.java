package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class WorldGenClay extends WorldGenerator
{
    private Block field_150546_a;
    private int field_76517_b;
    private static final String __OBFID = "CL_00000405";

    public WorldGenClay(int p_i2011_1_)
    {
        this.field_150546_a = Blocks.field_150435_aG;
        this.field_76517_b = p_i2011_1_;
    }

    public boolean func_76484_a(World p_76484_1_, Random p_76484_2_, int p_76484_3_, int p_76484_4_, int p_76484_5_)
    {
        if (p_76484_1_.func_147439_a(p_76484_3_, p_76484_4_, p_76484_5_).func_149688_o() != Material.field_151586_h)
        {
            return false;
        }
        else
        {
            int l = p_76484_2_.nextInt(this.field_76517_b - 2) + 2;
            byte b0 = 1;

            for (int i1 = p_76484_3_ - l; i1 <= p_76484_3_ + l; ++i1)
            {
                for (int j1 = p_76484_5_ - l; j1 <= p_76484_5_ + l; ++j1)
                {
                    int k1 = i1 - p_76484_3_;
                    int l1 = j1 - p_76484_5_;

                    if (k1 * k1 + l1 * l1 <= l * l)
                    {
                        for (int i2 = p_76484_4_ - b0; i2 <= p_76484_4_ + b0; ++i2)
                        {
                            Block block = p_76484_1_.func_147439_a(i1, i2, j1);

                            if (block == Blocks.field_150346_d || block == Blocks.field_150435_aG)
                            {
                                p_76484_1_.func_147465_d(i1, i2, j1, this.field_150546_a, 0, 2);
                            }
                        }
                    }
                }
            }

            return true;
        }
    }
}