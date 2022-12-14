package net.minecraft.world.biome;

import java.util.Random;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenMegaJungle;
import net.minecraft.world.gen.feature.WorldGenMelon;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenVines;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenJungle extends BiomeGenBase
{
    private boolean field_150614_aC;
    private static final String __OBFID = "CL_00000175";

    public BiomeGenJungle(int p_i45379_1_, boolean p_i45379_2_)
    {
        super(p_i45379_1_);
        this.field_150614_aC = p_i45379_2_;

        if (p_i45379_2_)
        {
            this.field_76760_I.field_76832_z = 2;
        }
        else
        {
            this.field_76760_I.field_76832_z = 50;
        }

        this.field_76760_I.field_76803_B = 25;
        this.field_76760_I.field_76802_A = 4;

        if (!p_i45379_2_)
        {
            this.field_76761_J.add(new BiomeGenBase.SpawnListEntry(EntityOcelot.class, 2, 1, 1));
        }

        this.field_76762_K.add(new BiomeGenBase.SpawnListEntry(EntityChicken.class, 10, 4, 4));
    }

    public WorldGenAbstractTree func_150567_a(Random p_150567_1_)
    {
        return (WorldGenAbstractTree)(p_150567_1_.nextInt(10) == 0 ? this.field_76758_O : (p_150567_1_.nextInt(2) == 0 ? new WorldGenShrub(3, 0) : (!this.field_150614_aC && p_150567_1_.nextInt(3) == 0 ? new WorldGenMegaJungle(false, 10, 20, 3, 3) : new WorldGenTrees(false, 4 + p_150567_1_.nextInt(7), 3, 3, true))));
    }

    public WorldGenerator func_76730_b(Random p_76730_1_)
    {
        return p_76730_1_.nextInt(4) == 0 ? new WorldGenTallGrass(Blocks.TALLGRASS, 2) : new WorldGenTallGrass(Blocks.TALLGRASS, 1);
    }

    public void func_76728_a(World p_76728_1_, Random p_76728_2_, int p_76728_3_, int p_76728_4_)
    {
        super.func_76728_a(p_76728_1_, p_76728_2_, p_76728_3_, p_76728_4_);
        int k = p_76728_3_ + p_76728_2_.nextInt(16) + 8;
        int l = p_76728_4_ + p_76728_2_.nextInt(16) + 8;
        int i1 = p_76728_2_.nextInt(p_76728_1_.func_72976_f(k, l) * 2);
        (new WorldGenMelon()).func_76484_a(p_76728_1_, p_76728_2_, k, i1, l);
        WorldGenVines worldgenvines = new WorldGenVines();

        for (l = 0; l < 50; ++l)
        {
            i1 = p_76728_3_ + p_76728_2_.nextInt(16) + 8;
            short short1 = 128;
            int j1 = p_76728_4_ + p_76728_2_.nextInt(16) + 8;
            worldgenvines.func_76484_a(p_76728_1_, p_76728_2_, i1, short1, j1);
        }
    }
}