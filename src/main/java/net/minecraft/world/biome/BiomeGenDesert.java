package net.minecraft.world.biome;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenDesertWells;

public class BiomeGenDesert extends BiomeGenBase
{
    private static final String __OBFID = "CL_00000167";

    public BiomeGenDesert(int p_i1977_1_)
    {
        super(p_i1977_1_);
        this.field_76762_K.clear();
        this.field_76752_A = Blocks.SAND;
        this.field_76753_B = Blocks.SAND;
        this.field_76760_I.field_76832_z = -999;
        this.field_76760_I.field_76804_C = 2;
        this.field_76760_I.field_76799_E = 50;
        this.field_76760_I.field_76800_F = 10;
        this.field_76762_K.clear();
    }

    public void func_76728_a(World p_76728_1_, Random p_76728_2_, int p_76728_3_, int p_76728_4_)
    {
        super.func_76728_a(p_76728_1_, p_76728_2_, p_76728_3_, p_76728_4_);

        if (p_76728_2_.nextInt(1000) == 0)
        {
            int k = p_76728_3_ + p_76728_2_.nextInt(16) + 8;
            int l = p_76728_4_ + p_76728_2_.nextInt(16) + 8;
            WorldGenDesertWells worldgendesertwells = new WorldGenDesertWells();
            worldgendesertwells.func_76484_a(p_76728_1_, p_76728_2_, k, p_76728_1_.func_72976_f(k, l) + 1, l);
        }
    }
}