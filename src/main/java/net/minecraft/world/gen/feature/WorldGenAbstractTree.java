package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public abstract class WorldGenAbstractTree extends WorldGenerator
{
    private static final String __OBFID = "CL_00000399";

    public WorldGenAbstractTree(boolean p_i45448_1_)
    {
        super(p_i45448_1_);
    }

    protected boolean func_150523_a(Block p_150523_1_)
    {
        return p_150523_1_.func_149688_o() == Material.field_151579_a || p_150523_1_.func_149688_o() == Material.field_151584_j || p_150523_1_ == Blocks.GRASS || p_150523_1_ == Blocks.DIRT || p_150523_1_ == Blocks.LOG || p_150523_1_ == Blocks.LOG_2 || p_150523_1_ == Blocks.SAPLING || p_150523_1_ == Blocks.VINE;
    }

    public void func_150524_b(World p_150524_1_, Random p_150524_2_, int p_150524_3_, int p_150524_4_, int p_150524_5_) {}
}