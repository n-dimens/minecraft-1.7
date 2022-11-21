package net.minecraft.item;

import com.google.common.collect.Sets;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;

public class ItemPickaxe extends ItemTool
{
    private static final Set field_150915_c = Sets.newHashSet(new Block[] {Blocks.COBBLESTONE, Blocks.DOUBLE_STONE_SLAB, Blocks.STONE_SLAB, Blocks.STONE, Blocks.SANDSTONE, Blocks.MOSSY_COBBLESTONE, Blocks.IRON_ORE, Blocks.IRON_BLOCK, Blocks.COAL_ORE, Blocks.GOLD_BLOCK, Blocks.GOLD_ORE, Blocks.DIAMOND_ORE, Blocks.DIAMOND_BLOCK, Blocks.ICE, Blocks.NETHERRACK, Blocks.LAPIS_ORE, Blocks.LAPIS_BLOCK, Blocks.REDSTONE_ORE, Blocks.LIT_REDSTONE_ORE, Blocks.RAIL, Blocks.DETECTOR_RAIL, Blocks.GOLDEN_RAIL, Blocks.ACTIVATOR_RAIL});
    private static final String __OBFID = "CL_00000053";

    protected ItemPickaxe(Item.ToolMaterial p_i45347_1_)
    {
        super(2.0F, p_i45347_1_, field_150915_c);
    }

    public boolean func_150897_b(Block p_150897_1_)
    {
        return p_150897_1_ == Blocks.OBSIDIAN ? this.field_77862_b.func_77996_d() == 3 : (p_150897_1_ != Blocks.DIAMOND_BLOCK && p_150897_1_ != Blocks.DIAMOND_ORE ? (p_150897_1_ != Blocks.EMERALD_ORE && p_150897_1_ != Blocks.EMERALD_BLOCK ? (p_150897_1_ != Blocks.GOLD_BLOCK && p_150897_1_ != Blocks.GOLD_ORE ? (p_150897_1_ != Blocks.IRON_BLOCK && p_150897_1_ != Blocks.IRON_ORE ? (p_150897_1_ != Blocks.LAPIS_BLOCK && p_150897_1_ != Blocks.LAPIS_ORE ? (p_150897_1_ != Blocks.REDSTONE_ORE && p_150897_1_ != Blocks.LIT_REDSTONE_ORE ? (p_150897_1_.func_149688_o() == Material.field_151576_e ? true : (p_150897_1_.func_149688_o() == Material.field_151573_f ? true : p_150897_1_.func_149688_o() == Material.field_151574_g)) : this.field_77862_b.func_77996_d() >= 2) : this.field_77862_b.func_77996_d() >= 1) : this.field_77862_b.func_77996_d() >= 1) : this.field_77862_b.func_77996_d() >= 2) : this.field_77862_b.func_77996_d() >= 2) : this.field_77862_b.func_77996_d() >= 2);
    }

    public float func_150893_a(ItemStack p_150893_1_, Block p_150893_2_)
    {
        return p_150893_2_.func_149688_o() != Material.field_151573_f && p_150893_2_.func_149688_o() != Material.field_151574_g && p_150893_2_.func_149688_o() != Material.field_151576_e ? super.func_150893_a(p_150893_1_, p_150893_2_) : this.field_77864_a;
    }
}