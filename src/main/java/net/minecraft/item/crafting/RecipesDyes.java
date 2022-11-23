package net.minecraft.item.crafting;

import net.minecraft.block.BlockColored;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class RecipesDyes
{
    private static final String __OBFID = "CL_00000082";

    public void addRecipes(CraftingManager craftingManager)
    {
        int i;

        for (i = 0; i < 16; ++i)
        {
            craftingManager.addShapelessRecipe(new ItemStack(Blocks.WOOL, 1, BlockColored.func_150031_c(i)), new ItemStack(Items.DYE, 1, i), new ItemStack(Item.func_150898_a(Blocks.WOOL), 1, 0));
            craftingManager.addRecipe(new ItemStack(Blocks.STAINED_HARDENED_CLAY, 8, BlockColored.func_150031_c(i)), "###", "#X#", "###", '#', new ItemStack(Blocks.HARDENED_CLAY), 'X', new ItemStack(Items.DYE, 1, i));
            craftingManager.addRecipe(new ItemStack(Blocks.STAINED_GLASS, 8, BlockColored.func_150031_c(i)), "###", "#X#", "###", '#', new ItemStack(Blocks.GLASS), 'X', new ItemStack(Items.DYE, 1, i));
            craftingManager.addRecipe(new ItemStack(Blocks.STAINED_GLASS_PANE, 16, i), "###", "###", '#', new ItemStack(Blocks.STAINED_GLASS, 1, i));
        }

        craftingManager.addShapelessRecipe(new ItemStack(Items.DYE, 1, 11), new ItemStack(Blocks.YELLOW_FLOWER, 1, 0));
        craftingManager.addShapelessRecipe(new ItemStack(Items.DYE, 1, 1), new ItemStack(Blocks.RED_FLOWER, 1, 0));
        craftingManager.addShapelessRecipe(new ItemStack(Items.DYE, 3, 15), Items.BONE);
        craftingManager.addShapelessRecipe(new ItemStack(Items.DYE, 2, 9), new ItemStack(Items.DYE, 1, 1), new ItemStack(Items.DYE, 1, 15));
        craftingManager.addShapelessRecipe(new ItemStack(Items.DYE, 2, 14), new ItemStack(Items.DYE, 1, 1), new ItemStack(Items.DYE, 1, 11));
        craftingManager.addShapelessRecipe(new ItemStack(Items.DYE, 2, 10), new ItemStack(Items.DYE, 1, 2), new ItemStack(Items.DYE, 1, 15));
        craftingManager.addShapelessRecipe(new ItemStack(Items.DYE, 2, 8), new ItemStack(Items.DYE, 1, 0), new ItemStack(Items.DYE, 1, 15));
        craftingManager.addShapelessRecipe(new ItemStack(Items.DYE, 2, 7), new ItemStack(Items.DYE, 1, 8), new ItemStack(Items.DYE, 1, 15));
        craftingManager.addShapelessRecipe(new ItemStack(Items.DYE, 3, 7), new ItemStack(Items.DYE, 1, 0), new ItemStack(Items.DYE, 1, 15), new ItemStack(Items.DYE, 1, 15));
        craftingManager.addShapelessRecipe(new ItemStack(Items.DYE, 2, 12), new ItemStack(Items.DYE, 1, 4), new ItemStack(Items.DYE, 1, 15));
        craftingManager.addShapelessRecipe(new ItemStack(Items.DYE, 2, 6), new ItemStack(Items.DYE, 1, 4), new ItemStack(Items.DYE, 1, 2));
        craftingManager.addShapelessRecipe(new ItemStack(Items.DYE, 2, 5), new ItemStack(Items.DYE, 1, 4), new ItemStack(Items.DYE, 1, 1));
        craftingManager.addShapelessRecipe(new ItemStack(Items.DYE, 2, 13), new ItemStack(Items.DYE, 1, 5), new ItemStack(Items.DYE, 1, 9));
        craftingManager.addShapelessRecipe(new ItemStack(Items.DYE, 3, 13), new ItemStack(Items.DYE, 1, 4), new ItemStack(Items.DYE, 1, 1), new ItemStack(Items.DYE, 1, 9));
        craftingManager.addShapelessRecipe(new ItemStack(Items.DYE, 4, 13), new ItemStack(Items.DYE, 1, 4), new ItemStack(Items.DYE, 1, 1), new ItemStack(Items.DYE, 1, 1), new ItemStack(Items.DYE, 1, 15));
        craftingManager.addShapelessRecipe(new ItemStack(Items.DYE, 1, 12), new ItemStack(Blocks.RED_FLOWER, 1, 1));
        craftingManager.addShapelessRecipe(new ItemStack(Items.DYE, 1, 13), new ItemStack(Blocks.RED_FLOWER, 1, 2));
        craftingManager.addShapelessRecipe(new ItemStack(Items.DYE, 1, 7), new ItemStack(Blocks.RED_FLOWER, 1, 3));
        craftingManager.addShapelessRecipe(new ItemStack(Items.DYE, 1, 1), new ItemStack(Blocks.RED_FLOWER, 1, 4));
        craftingManager.addShapelessRecipe(new ItemStack(Items.DYE, 1, 14), new ItemStack(Blocks.RED_FLOWER, 1, 5));
        craftingManager.addShapelessRecipe(new ItemStack(Items.DYE, 1, 7), new ItemStack(Blocks.RED_FLOWER, 1, 6));
        craftingManager.addShapelessRecipe(new ItemStack(Items.DYE, 1, 9), new ItemStack(Blocks.RED_FLOWER, 1, 7));
        craftingManager.addShapelessRecipe(new ItemStack(Items.DYE, 1, 7), new ItemStack(Blocks.RED_FLOWER, 1, 8));
        craftingManager.addShapelessRecipe(new ItemStack(Items.DYE, 2, 11), new ItemStack(Blocks.DOUBLE_PLANT, 1, 0));
        craftingManager.addShapelessRecipe(new ItemStack(Items.DYE, 2, 13), new ItemStack(Blocks.DOUBLE_PLANT, 1, 1));
        craftingManager.addShapelessRecipe(new ItemStack(Items.DYE, 2, 1), new ItemStack(Blocks.DOUBLE_PLANT, 1, 4));
        craftingManager.addShapelessRecipe(new ItemStack(Items.DYE, 2, 9), new ItemStack(Blocks.DOUBLE_PLANT, 1, 5));

        for (i = 0; i < 16; ++i)
        {
            craftingManager.addRecipe(new ItemStack(Blocks.CARPET, 3, i), "##", '#', new ItemStack(Blocks.WOOL, 1, i));
        }
    }
}