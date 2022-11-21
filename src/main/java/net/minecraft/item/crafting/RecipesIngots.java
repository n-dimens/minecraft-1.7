package net.minecraft.item.crafting;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class RecipesIngots
{
    private Object[][] field_77591_a;
    private static final String __OBFID = "CL_00000089";

    public RecipesIngots()
    {
        this.field_77591_a = new Object[][] {{Blocks.field_150340_R, new ItemStack(Items.GOLD_INGOT, 9)}, {Blocks.field_150339_S, new ItemStack(Items.IRON_INGOT, 9)}, {Blocks.field_150484_ah, new ItemStack(Items.DIAMOND, 9)}, {Blocks.field_150475_bE, new ItemStack(Items.EMERALD, 9)}, {Blocks.field_150368_y, new ItemStack(Items.DYE, 9, 4)}, {Blocks.field_150451_bX, new ItemStack(Items.REDSTONE, 9)}, {Blocks.field_150402_ci, new ItemStack(Items.COAL, 9, 0)}, {Blocks.field_150407_cf, new ItemStack(Items.WHEAT, 9)}};
    }

    public void func_77590_a(CraftingManager p_77590_1_)
    {
        for (int i = 0; i < this.field_77591_a.length; ++i)
        {
            Block block = (Block)this.field_77591_a[i][0];
            ItemStack itemstack = (ItemStack)this.field_77591_a[i][1];
            p_77590_1_.func_92103_a(new ItemStack(block), new Object[] {"###", "###", "###", '#', itemstack});
            p_77590_1_.func_92103_a(itemstack, new Object[] {"#", '#', block});
        }

        p_77590_1_.func_92103_a(new ItemStack(Items.GOLD_INGOT), new Object[] {"###", "###", "###", '#', Items.GOLD_NUGGET});
        p_77590_1_.func_92103_a(new ItemStack(Items.GOLD_NUGGET, 9), new Object[] {"#", '#', Items.GOLD_INGOT});
    }
}