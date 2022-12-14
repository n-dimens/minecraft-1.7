package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.world.World;

public class BlockDeadBush extends BlockBush
{
    private static final String __OBFID = "CL_00000224";

    protected BlockDeadBush()
    {
        super(Material.field_151582_l);
        float f = 0.4F;
        this.func_149676_a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.8F, 0.5F + f);
    }

    protected boolean func_149854_a(Block p_149854_1_)
    {
        return p_149854_1_ == Blocks.SAND || p_149854_1_ == Blocks.HARDENED_CLAY || p_149854_1_ == Blocks.STAINED_HARDENED_CLAY || p_149854_1_ == Blocks.DIRT;
    }

    public Item func_149650_a(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
        return null;
    }

    public void func_149636_a(World p_149636_1_, EntityPlayer p_149636_2_, int p_149636_3_, int p_149636_4_, int p_149636_5_, int p_149636_6_)
    {
        if (!p_149636_1_.field_72995_K && p_149636_2_.func_71045_bC() != null && p_149636_2_.func_71045_bC().getBaseItem() == Items.SHEARS)
        {
            p_149636_2_.func_71064_a(StatList.field_75934_C[Block.func_149682_b(this)], 1);
            this.func_149642_a(p_149636_1_, p_149636_3_, p_149636_4_, p_149636_5_, new ItemStack(Blocks.DEADBUSH, 1, p_149636_6_));
        }
        else
        {
            super.func_149636_a(p_149636_1_, p_149636_2_, p_149636_3_, p_149636_4_, p_149636_5_, p_149636_6_);
        }
    }
}