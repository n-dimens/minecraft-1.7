package net.minecraft.item;

import com.google.common.collect.Sets;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;

public class ItemAxe extends ItemTool
{
    private static final Set field_150917_c = Sets.newHashSet(new Block[] {Blocks.PLANKS, Blocks.BOOKSHELF, Blocks.LOG, Blocks.LOG_2, Blocks.CHEST, Blocks.PUMPKIN, Blocks.LIT_PUMPKIN});
    private static final String __OBFID = "CL_00001770";

    protected ItemAxe(Item.ToolMaterial p_i45327_1_)
    {
        super(3.0F, p_i45327_1_, field_150917_c);
    }

    public float func_150893_a(ItemStack p_150893_1_, Block p_150893_2_)
    {
        return p_150893_2_.func_149688_o() != Material.field_151575_d && p_150893_2_.func_149688_o() != Material.field_151585_k && p_150893_2_.func_149688_o() != Material.field_151582_l ? super.func_150893_a(p_150893_1_, p_150893_2_) : this.field_77864_a;
    }
}