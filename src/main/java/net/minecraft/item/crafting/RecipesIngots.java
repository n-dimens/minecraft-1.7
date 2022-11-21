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
        this.field_77591_a = new Object[][] {{Blocks.field_150340_R, new ItemStack(Items.field_151043_k, 9)}, {Blocks.field_150339_S, new ItemStack(Items.field_151042_j, 9)}, {Blocks.field_150484_ah, new ItemStack(Items.field_151045_i, 9)}, {Blocks.field_150475_bE, new ItemStack(Items.field_151166_bC, 9)}, {Blocks.field_150368_y, new ItemStack(Items.field_151100_aR, 9, 4)}, {Blocks.field_150451_bX, new ItemStack(Items.field_151137_ax, 9)}, {Blocks.field_150402_ci, new ItemStack(Items.field_151044_h, 9, 0)}, {Blocks.field_150407_cf, new ItemStack(Items.field_151015_O, 9)}};
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

        p_77590_1_.func_92103_a(new ItemStack(Items.field_151043_k), new Object[] {"###", "###", "###", '#', Items.field_151074_bl});
        p_77590_1_.func_92103_a(new ItemStack(Items.field_151074_bl, 9), new Object[] {"#", '#', Items.field_151043_k});
    }
}