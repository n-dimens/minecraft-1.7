package net.minecraft.world.biome;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenHills extends BiomeGenBase
{
    private WorldGenerator field_82915_S;
    private WorldGenTaiga2 field_150634_aD;
    private int field_150635_aE;
    private int field_150636_aF;
    private int field_150637_aG;
    private int field_150638_aH;
    private static final String __OBFID = "CL_00000168";

    public BiomeGenHills(int p_i45373_1_, boolean p_i45373_2_)
    {
        super(p_i45373_1_);
        this.field_82915_S = new WorldGenMinable(Blocks.MONSTER_EGG, 8);
        this.field_150634_aD = new WorldGenTaiga2(false);
        this.field_150635_aE = 0;
        this.field_150636_aF = 1;
        this.field_150637_aG = 2;
        this.field_150638_aH = this.field_150635_aE;

        if (p_i45373_2_)
        {
            this.field_76760_I.field_76832_z = 3;
            this.field_150638_aH = this.field_150636_aF;
        }
    }

    public WorldGenAbstractTree func_150567_a(Random p_150567_1_)
    {
        return (WorldGenAbstractTree)(p_150567_1_.nextInt(3) > 0 ? this.field_150634_aD : super.func_150567_a(p_150567_1_));
    }

    public void func_76728_a(World p_76728_1_, Random p_76728_2_, int p_76728_3_, int p_76728_4_)
    {
        super.func_76728_a(p_76728_1_, p_76728_2_, p_76728_3_, p_76728_4_);
        int k = 3 + p_76728_2_.nextInt(6);
        int l;
        int i1;
        int j1;

        for (l = 0; l < k; ++l)
        {
            i1 = p_76728_3_ + p_76728_2_.nextInt(16);
            j1 = p_76728_2_.nextInt(28) + 4;
            int k1 = p_76728_4_ + p_76728_2_.nextInt(16);

            if (p_76728_1_.func_147439_a(i1, j1, k1) == Blocks.STONE)
            {
                p_76728_1_.func_147465_d(i1, j1, k1, Blocks.EMERALD_ORE, 0, 2);
            }
        }

        for (k = 0; k < 7; ++k)
        {
            l = p_76728_3_ + p_76728_2_.nextInt(16);
            i1 = p_76728_2_.nextInt(64);
            j1 = p_76728_4_ + p_76728_2_.nextInt(16);
            this.field_82915_S.func_76484_a(p_76728_1_, p_76728_2_, l, i1, j1);
        }
    }

    public void func_150573_a(World p_150573_1_, Random p_150573_2_, Block[] p_150573_3_, byte[] p_150573_4_, int p_150573_5_, int p_150573_6_, double p_150573_7_)
    {
        this.field_76752_A = Blocks.GRASS;
        this.field_150604_aj = 0;
        this.field_76753_B = Blocks.DIRT;

        if ((p_150573_7_ < -1.0D || p_150573_7_ > 2.0D) && this.field_150638_aH == this.field_150637_aG)
        {
            this.field_76752_A = Blocks.GRAVEL;
            this.field_76753_B = Blocks.GRAVEL;
        }
        else if (p_150573_7_ > 1.0D && this.field_150638_aH != this.field_150636_aF)
        {
            this.field_76752_A = Blocks.STONE;
            this.field_76753_B = Blocks.STONE;
        }

        this.func_150560_b(p_150573_1_, p_150573_2_, p_150573_3_, p_150573_4_, p_150573_5_, p_150573_6_, p_150573_7_);
    }

    public BiomeGenHills func_150633_b(BiomeGenBase p_150633_1_)
    {
        this.field_150638_aH = this.field_150637_aG;
        this.func_150557_a(p_150633_1_.field_76790_z, true);
        this.func_76735_a(p_150633_1_.field_76791_y + " M");
        this.func_150570_a(new BiomeGenBase.Height(p_150633_1_.field_76748_D, p_150633_1_.field_76749_E));
        this.func_76732_a(p_150633_1_.field_76750_F, p_150633_1_.field_76751_G);
        return this;
    }

    public BiomeGenBase func_150566_k()
    {
        return (new BiomeGenHills(this.field_76756_M + 128, false)).func_150633_b(this);
    }
}