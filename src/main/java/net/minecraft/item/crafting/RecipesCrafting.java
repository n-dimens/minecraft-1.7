package net.minecraft.item.crafting;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class RecipesCrafting
{
    private static final String __OBFID = "CL_00000095";

    public void addRecipes(CraftingManager craftingManager)
    {
        craftingManager.addRecipe(new ItemStack(Blocks.CHEST), "###", "# #", "###", '#', Blocks.PLANKS);
        craftingManager.addRecipe(new ItemStack(Blocks.TRAPPED_CHEST), "#-", '#', Blocks.CHEST, '-', Blocks.TRIPWIRE_HOOK);
        craftingManager.addRecipe(new ItemStack(Blocks.ENDER_CHEST), "###", "#E#", "###", '#', Blocks.OBSIDIAN, 'E', Items.ENDER_EYE);
        craftingManager.addRecipe(new ItemStack(Blocks.FURNACE), "###", "# #", "###", '#', Blocks.COBBLESTONE);
        craftingManager.addRecipe(new ItemStack(Blocks.CRAFTING_TABLE), "##", "##", '#', Blocks.PLANKS);
        craftingManager.addRecipe(new ItemStack(Blocks.SANDSTONE), "##", "##", '#', new ItemStack(Blocks.SAND, 1, 0));
        craftingManager.addRecipe(new ItemStack(Blocks.SANDSTONE, 4, 2), "##", "##", '#', Blocks.SANDSTONE);
        craftingManager.addRecipe(new ItemStack(Blocks.SANDSTONE, 1, 1), "#", "#", '#', new ItemStack(Blocks.STONE_SLAB, 1, 1));
        craftingManager.addRecipe(new ItemStack(Blocks.QUARTZ_BLOCK, 1, 1), "#", "#", '#', new ItemStack(Blocks.STONE_SLAB, 1, 7));
        craftingManager.addRecipe(new ItemStack(Blocks.QUARTZ_BLOCK, 2, 2), "#", "#", '#', new ItemStack(Blocks.QUARTZ_BLOCK, 1, 0));
        craftingManager.addRecipe(new ItemStack(Blocks.STONEBRICK, 4), "##", "##", '#', Blocks.STONE);
        craftingManager.addRecipe(new ItemStack(Blocks.IRON_BARS, 16), "###", "###", '#', Items.IRON_INGOT);
        craftingManager.addRecipe(new ItemStack(Blocks.GLASS_PANE, 16), "###", "###", '#', Blocks.GLASS);
        craftingManager.addRecipe(new ItemStack(Blocks.REDSTONE_LAMP, 1), " R ", "RGR", " R ", 'R', Items.REDSTONE, 'G', Blocks.GLOWSTONE);
        craftingManager.addRecipe(new ItemStack(Blocks.BEACON, 1), "GGG", "GSG", "OOO", 'G', Blocks.GLASS, 'S', Items.NETHER_STAR, 'O', Blocks.OBSIDIAN);
        craftingManager.addRecipe(new ItemStack(Blocks.NETHER_BRICK, 1), "NN", "NN", 'N', Items.NETHERBRICK);
    }
}