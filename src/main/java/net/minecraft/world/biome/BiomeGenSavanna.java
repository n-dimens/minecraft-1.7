package net.minecraft.world.biome;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenSavannaTree;

public class BiomeGenSavanna extends BiomeGenBase
{
    private static final WorldGenSavannaTree field_150627_aC = new WorldGenSavannaTree(false);
    private static final String __OBFID = "CL_00000182";

    public BiomeGenSavanna(int p_i45383_1_)
    {
        super(p_i45383_1_);
        this.field_76762_K.add(new BiomeGenBase.SpawnListEntry(EntityHorse.class, 1, 2, 6));
        this.field_76760_I.field_76832_z = 1;
        this.field_76760_I.field_76802_A = 4;
        this.field_76760_I.field_76803_B = 20;
    }

    public WorldGenAbstractTree func_150567_a(Random p_150567_1_)
    {
        return (WorldGenAbstractTree)(p_150567_1_.nextInt(5) > 0 ? field_150627_aC : this.field_76757_N);
    }

    public BiomeGenBase func_150566_k()
    {
        BiomeGenSavanna.Mutated mutated = new BiomeGenSavanna.Mutated(this.field_76756_M + 128, this);
        mutated.field_76750_F = (this.field_76750_F + 1.0F) * 0.5F;
        mutated.field_76748_D = this.field_76748_D * 0.5F + 0.3F;
        mutated.field_76749_E = this.field_76749_E * 0.5F + 1.2F;
        return mutated;
    }

    public void func_76728_a(World p_76728_1_, Random p_76728_2_, int p_76728_3_, int p_76728_4_)
    {
        field_150610_ae.func_150548_a(2);

        for (int k = 0; k < 7; ++k)
        {
            int l = p_76728_3_ + p_76728_2_.nextInt(16) + 8;
            int i1 = p_76728_4_ + p_76728_2_.nextInt(16) + 8;
            int j1 = p_76728_2_.nextInt(p_76728_1_.func_72976_f(l, i1) + 32);
            field_150610_ae.func_76484_a(p_76728_1_, p_76728_2_, l, j1, i1);
        }

        super.func_76728_a(p_76728_1_, p_76728_2_, p_76728_3_, p_76728_4_);
    }

    public static class Mutated extends BiomeGenMutated
        {
            private static final String __OBFID = "CL_00000183";

            public Mutated(int p_i45382_1_, BiomeGenBase p_i45382_2_)
            {
                super(p_i45382_1_, p_i45382_2_);
                this.field_76760_I.field_76832_z = 2;
                this.field_76760_I.field_76802_A = 2;
                this.field_76760_I.field_76803_B = 5;
            }

            public void func_150573_a(World p_150573_1_, Random p_150573_2_, Block[] p_150573_3_, byte[] p_150573_4_, int p_150573_5_, int p_150573_6_, double p_150573_7_)
            {
                this.field_76752_A = Blocks.GRASS;
                this.field_150604_aj = 0;
                this.field_76753_B = Blocks.DIRT;

                if (p_150573_7_ > 1.75D)
                {
                    this.field_76752_A = Blocks.STONE;
                    this.field_76753_B = Blocks.STONE;
                }
                else if (p_150573_7_ > -0.5D)
                {
                    this.field_76752_A = Blocks.DIRT;
                    this.field_150604_aj = 1;
                }

                this.func_150560_b(p_150573_1_, p_150573_2_, p_150573_3_, p_150573_4_, p_150573_5_, p_150573_6_, p_150573_7_);
            }

            public void func_76728_a(World p_76728_1_, Random p_76728_2_, int p_76728_3_, int p_76728_4_)
            {
                this.field_76760_I.func_150512_a(p_76728_1_, p_76728_2_, this, p_76728_3_, p_76728_4_);
            }
        }
}