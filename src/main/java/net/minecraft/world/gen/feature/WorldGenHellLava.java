package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class WorldGenHellLava extends WorldGenerator
{
    private Block field_150553_a;
    private boolean field_94524_b;
    private static final String __OBFID = "CL_00000414";

    public WorldGenHellLava(Block p_i45453_1_, boolean p_i45453_2_)
    {
        this.field_150553_a = p_i45453_1_;
        this.field_94524_b = p_i45453_2_;
    }

    public boolean func_76484_a(World p_76484_1_, Random p_76484_2_, int p_76484_3_, int p_76484_4_, int p_76484_5_)
    {
        if (p_76484_1_.func_147439_a(p_76484_3_, p_76484_4_ + 1, p_76484_5_) != Blocks.NETHERRACK)
        {
            return false;
        }
        else if (p_76484_1_.func_147439_a(p_76484_3_, p_76484_4_, p_76484_5_).func_149688_o() != Material.field_151579_a && p_76484_1_.func_147439_a(p_76484_3_, p_76484_4_, p_76484_5_) != Blocks.NETHERRACK)
        {
            return false;
        }
        else
        {
            int l = 0;

            if (p_76484_1_.func_147439_a(p_76484_3_ - 1, p_76484_4_, p_76484_5_) == Blocks.NETHERRACK)
            {
                ++l;
            }

            if (p_76484_1_.func_147439_a(p_76484_3_ + 1, p_76484_4_, p_76484_5_) == Blocks.NETHERRACK)
            {
                ++l;
            }

            if (p_76484_1_.func_147439_a(p_76484_3_, p_76484_4_, p_76484_5_ - 1) == Blocks.NETHERRACK)
            {
                ++l;
            }

            if (p_76484_1_.func_147439_a(p_76484_3_, p_76484_4_, p_76484_5_ + 1) == Blocks.NETHERRACK)
            {
                ++l;
            }

            if (p_76484_1_.func_147439_a(p_76484_3_, p_76484_4_ - 1, p_76484_5_) == Blocks.NETHERRACK)
            {
                ++l;
            }

            int i1 = 0;

            if (p_76484_1_.func_147437_c(p_76484_3_ - 1, p_76484_4_, p_76484_5_))
            {
                ++i1;
            }

            if (p_76484_1_.func_147437_c(p_76484_3_ + 1, p_76484_4_, p_76484_5_))
            {
                ++i1;
            }

            if (p_76484_1_.func_147437_c(p_76484_3_, p_76484_4_, p_76484_5_ - 1))
            {
                ++i1;
            }

            if (p_76484_1_.func_147437_c(p_76484_3_, p_76484_4_, p_76484_5_ + 1))
            {
                ++i1;
            }

            if (p_76484_1_.func_147437_c(p_76484_3_, p_76484_4_ - 1, p_76484_5_))
            {
                ++i1;
            }

            if (!this.field_94524_b && l == 4 && i1 == 1 || l == 5)
            {
                p_76484_1_.func_147465_d(p_76484_3_, p_76484_4_, p_76484_5_, this.field_150553_a, 0, 2);
                p_76484_1_.field_72999_e = true;
                this.field_150553_a.func_149674_a(p_76484_1_, p_76484_3_, p_76484_4_, p_76484_5_, p_76484_2_);
                p_76484_1_.field_72999_e = false;
            }

            return true;
        }
    }
}