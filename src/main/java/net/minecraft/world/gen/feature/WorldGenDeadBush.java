package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

public class WorldGenDeadBush extends WorldGenerator
{
    private Block field_150547_a;
    private static final String __OBFID = "CL_00000406";

    public WorldGenDeadBush(Block p_i45451_1_)
    {
        this.field_150547_a = p_i45451_1_;
    }

    public boolean func_76484_a(World p_76484_1_, Random p_76484_2_, int p_76484_3_, int p_76484_4_, int p_76484_5_)
    {
        Block block;

        while (((block = p_76484_1_.func_147439_a(p_76484_3_, p_76484_4_, p_76484_5_)).func_149688_o() == Material.field_151579_a || block.func_149688_o() == Material.field_151584_j) && p_76484_4_ > 0)
        {
            --p_76484_4_;
        }

        for (int l = 0; l < 4; ++l)
        {
            int i1 = p_76484_3_ + p_76484_2_.nextInt(8) - p_76484_2_.nextInt(8);
            int j1 = p_76484_4_ + p_76484_2_.nextInt(4) - p_76484_2_.nextInt(4);
            int k1 = p_76484_5_ + p_76484_2_.nextInt(8) - p_76484_2_.nextInt(8);

            if (p_76484_1_.func_147437_c(i1, j1, k1) && this.field_150547_a.func_149718_j(p_76484_1_, i1, j1, k1))
            {
                p_76484_1_.func_147465_d(i1, j1, k1, this.field_150547_a, 0, 2);
            }
        }

        return true;
    }
}