package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;

public class WorldGeneratorBonusChest extends WorldGenerator
{
    private final WeightedRandomChestContent[] field_76546_a;
    private final int field_76545_b;
    private static final String __OBFID = "CL_00000403";

    public WorldGeneratorBonusChest(WeightedRandomChestContent[] p_i2010_1_, int p_i2010_2_)
    {
        this.field_76546_a = p_i2010_1_;
        this.field_76545_b = p_i2010_2_;
    }

    public boolean func_76484_a(World p_76484_1_, Random p_76484_2_, int p_76484_3_, int p_76484_4_, int p_76484_5_)
    {
        Block block;

        while (((block = p_76484_1_.func_147439_a(p_76484_3_, p_76484_4_, p_76484_5_)).func_149688_o() == Material.field_151579_a || block.func_149688_o() == Material.field_151584_j) && p_76484_4_ > 1)
        {
            --p_76484_4_;
        }

        if (p_76484_4_ < 1)
        {
            return false;
        }
        else
        {
            ++p_76484_4_;

            for (int l = 0; l < 4; ++l)
            {
                int i1 = p_76484_3_ + p_76484_2_.nextInt(4) - p_76484_2_.nextInt(4);
                int j1 = p_76484_4_ + p_76484_2_.nextInt(3) - p_76484_2_.nextInt(3);
                int k1 = p_76484_5_ + p_76484_2_.nextInt(4) - p_76484_2_.nextInt(4);

                if (p_76484_1_.func_147437_c(i1, j1, k1) && World.func_147466_a(p_76484_1_, i1, j1 - 1, k1))
                {
                    p_76484_1_.func_147465_d(i1, j1, k1, Blocks.CHEST, 0, 2);
                    TileEntityChest tileentitychest = (TileEntityChest)p_76484_1_.func_147438_o(i1, j1, k1);

                    if (tileentitychest != null && tileentitychest != null)
                    {
                        WeightedRandomChestContent.func_76293_a(p_76484_2_, this.field_76546_a, tileentitychest, this.field_76545_b);
                    }

                    if (p_76484_1_.func_147437_c(i1 - 1, j1, k1) && World.func_147466_a(p_76484_1_, i1 - 1, j1 - 1, k1))
                    {
                        p_76484_1_.func_147465_d(i1 - 1, j1, k1, Blocks.TORCH, 0, 2);
                    }

                    if (p_76484_1_.func_147437_c(i1 + 1, j1, k1) && World.func_147466_a(p_76484_1_, i1 - 1, j1 - 1, k1))
                    {
                        p_76484_1_.func_147465_d(i1 + 1, j1, k1, Blocks.TORCH, 0, 2);
                    }

                    if (p_76484_1_.func_147437_c(i1, j1, k1 - 1) && World.func_147466_a(p_76484_1_, i1 - 1, j1 - 1, k1))
                    {
                        p_76484_1_.func_147465_d(i1, j1, k1 - 1, Blocks.TORCH, 0, 2);
                    }

                    if (p_76484_1_.func_147437_c(i1, j1, k1 + 1) && World.func_147466_a(p_76484_1_, i1 - 1, j1 - 1, k1))
                    {
                        p_76484_1_.func_147465_d(i1, j1, k1 + 1, Blocks.TORCH, 0, 2);
                    }

                    return true;
                }
            }

            return false;
        }
    }
}