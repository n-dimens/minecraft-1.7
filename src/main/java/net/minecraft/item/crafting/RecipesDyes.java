package net.minecraft.item.crafting;

import net.minecraft.block.BlockColored;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class RecipesDyes
{
    private static final String __OBFID = "CL_00000082";

    public void func_77607_a(CraftingManager p_77607_1_)
    {
        int i;

        for (i = 0; i < 16; ++i)
        {
            p_77607_1_.func_77596_b(new ItemStack(Blocks.WOOL, 1, BlockColored.func_150031_c(i)), new Object[] {new ItemStack(Items.DYE, 1, i), new ItemStack(Item.func_150898_a(Blocks.WOOL), 1, 0)});
            p_77607_1_.func_92103_a(new ItemStack(Blocks.STAINED_HARDENED_CLAY, 8, BlockColored.func_150031_c(i)), new Object[] {"###", "#X#", "###", '#', new ItemStack(Blocks.HARDENED_CLAY), 'X', new ItemStack(Items.DYE, 1, i)});
            p_77607_1_.func_92103_a(new ItemStack(Blocks.STAINED_GLASS, 8, BlockColored.func_150031_c(i)), new Object[] {"###", "#X#", "###", '#', new ItemStack(Blocks.GLASS), 'X', new ItemStack(Items.DYE, 1, i)});
            p_77607_1_.func_92103_a(new ItemStack(Blocks.STAINED_GLASS_PANE, 16, i), new Object[] {"###", "###", '#', new ItemStack(Blocks.STAINED_GLASS, 1, i)});
        }

        p_77607_1_.func_77596_b(new ItemStack(Items.DYE, 1, 11), new Object[] {new ItemStack(Blocks.YELLOW_FLOWER, 1, 0)});
        p_77607_1_.func_77596_b(new ItemStack(Items.DYE, 1, 1), new Object[] {new ItemStack(Blocks.RED_FLOWER, 1, 0)});
        p_77607_1_.func_77596_b(new ItemStack(Items.DYE, 3, 15), new Object[] {Items.BONE});
        p_77607_1_.func_77596_b(new ItemStack(Items.DYE, 2, 9), new Object[] {new ItemStack(Items.DYE, 1, 1), new ItemStack(Items.DYE, 1, 15)});
        p_77607_1_.func_77596_b(new ItemStack(Items.DYE, 2, 14), new Object[] {new ItemStack(Items.DYE, 1, 1), new ItemStack(Items.DYE, 1, 11)});
        p_77607_1_.func_77596_b(new ItemStack(Items.DYE, 2, 10), new Object[] {new ItemStack(Items.DYE, 1, 2), new ItemStack(Items.DYE, 1, 15)});
        p_77607_1_.func_77596_b(new ItemStack(Items.DYE, 2, 8), new Object[] {new ItemStack(Items.DYE, 1, 0), new ItemStack(Items.DYE, 1, 15)});
        p_77607_1_.func_77596_b(new ItemStack(Items.DYE, 2, 7), new Object[] {new ItemStack(Items.DYE, 1, 8), new ItemStack(Items.DYE, 1, 15)});
        p_77607_1_.func_77596_b(new ItemStack(Items.DYE, 3, 7), new Object[] {new ItemStack(Items.DYE, 1, 0), new ItemStack(Items.DYE, 1, 15), new ItemStack(Items.DYE, 1, 15)});
        p_77607_1_.func_77596_b(new ItemStack(Items.DYE, 2, 12), new Object[] {new ItemStack(Items.DYE, 1, 4), new ItemStack(Items.DYE, 1, 15)});
        p_77607_1_.func_77596_b(new ItemStack(Items.DYE, 2, 6), new Object[] {new ItemStack(Items.DYE, 1, 4), new ItemStack(Items.DYE, 1, 2)});
        p_77607_1_.func_77596_b(new ItemStack(Items.DYE, 2, 5), new Object[] {new ItemStack(Items.DYE, 1, 4), new ItemStack(Items.DYE, 1, 1)});
        p_77607_1_.func_77596_b(new ItemStack(Items.DYE, 2, 13), new Object[] {new ItemStack(Items.DYE, 1, 5), new ItemStack(Items.DYE, 1, 9)});
        p_77607_1_.func_77596_b(new ItemStack(Items.DYE, 3, 13), new Object[] {new ItemStack(Items.DYE, 1, 4), new ItemStack(Items.DYE, 1, 1), new ItemStack(Items.DYE, 1, 9)});
        p_77607_1_.func_77596_b(new ItemStack(Items.DYE, 4, 13), new Object[] {new ItemStack(Items.DYE, 1, 4), new ItemStack(Items.DYE, 1, 1), new ItemStack(Items.DYE, 1, 1), new ItemStack(Items.DYE, 1, 15)});
        p_77607_1_.func_77596_b(new ItemStack(Items.DYE, 1, 12), new Object[] {new ItemStack(Blocks.RED_FLOWER, 1, 1)});
        p_77607_1_.func_77596_b(new ItemStack(Items.DYE, 1, 13), new Object[] {new ItemStack(Blocks.RED_FLOWER, 1, 2)});
        p_77607_1_.func_77596_b(new ItemStack(Items.DYE, 1, 7), new Object[] {new ItemStack(Blocks.RED_FLOWER, 1, 3)});
        p_77607_1_.func_77596_b(new ItemStack(Items.DYE, 1, 1), new Object[] {new ItemStack(Blocks.RED_FLOWER, 1, 4)});
        p_77607_1_.func_77596_b(new ItemStack(Items.DYE, 1, 14), new Object[] {new ItemStack(Blocks.RED_FLOWER, 1, 5)});
        p_77607_1_.func_77596_b(new ItemStack(Items.DYE, 1, 7), new Object[] {new ItemStack(Blocks.RED_FLOWER, 1, 6)});
        p_77607_1_.func_77596_b(new ItemStack(Items.DYE, 1, 9), new Object[] {new ItemStack(Blocks.RED_FLOWER, 1, 7)});
        p_77607_1_.func_77596_b(new ItemStack(Items.DYE, 1, 7), new Object[] {new ItemStack(Blocks.RED_FLOWER, 1, 8)});
        p_77607_1_.func_77596_b(new ItemStack(Items.DYE, 2, 11), new Object[] {new ItemStack(Blocks.DOUBLE_PLANT, 1, 0)});
        p_77607_1_.func_77596_b(new ItemStack(Items.DYE, 2, 13), new Object[] {new ItemStack(Blocks.DOUBLE_PLANT, 1, 1)});
        p_77607_1_.func_77596_b(new ItemStack(Items.DYE, 2, 1), new Object[] {new ItemStack(Blocks.DOUBLE_PLANT, 1, 4)});
        p_77607_1_.func_77596_b(new ItemStack(Items.DYE, 2, 9), new Object[] {new ItemStack(Blocks.DOUBLE_PLANT, 1, 5)});

        for (i = 0; i < 16; ++i)
        {
            p_77607_1_.func_92103_a(new ItemStack(Blocks.CARPET, 3, i), new Object[] {"##", '#', new ItemStack(Blocks.WOOL, 1, i)});
        }
    }
}