package net.minecraft.item.crafting;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class RecipesCrafting
{
    private static final String __OBFID = "CL_00000095";

    public void func_77589_a(CraftingManager p_77589_1_)
    {
        p_77589_1_.func_92103_a(new ItemStack(Blocks.CHEST), new Object[] {"###", "# #", "###", '#', Blocks.PLANKS});
        p_77589_1_.func_92103_a(new ItemStack(Blocks.TRAPPED_CHEST), new Object[] {"#-", '#', Blocks.CHEST, '-', Blocks.TRIPWIRE_HOOK});
        p_77589_1_.func_92103_a(new ItemStack(Blocks.ENDER_CHEST), new Object[] {"###", "#E#", "###", '#', Blocks.OBSIDIAN, 'E', Items.ENDER_EYE});
        p_77589_1_.func_92103_a(new ItemStack(Blocks.FURNACE), new Object[] {"###", "# #", "###", '#', Blocks.COBBLESTONE});
        p_77589_1_.func_92103_a(new ItemStack(Blocks.CRAFTING_TABLE), new Object[] {"##", "##", '#', Blocks.PLANKS});
        p_77589_1_.func_92103_a(new ItemStack(Blocks.SANDSTONE), new Object[] {"##", "##", '#', new ItemStack(Blocks.SAND, 1, 0)});
        p_77589_1_.func_92103_a(new ItemStack(Blocks.SANDSTONE, 4, 2), new Object[] {"##", "##", '#', Blocks.SANDSTONE});
        p_77589_1_.func_92103_a(new ItemStack(Blocks.SANDSTONE, 1, 1), new Object[] {"#", "#", '#', new ItemStack(Blocks.STONE_SLAB, 1, 1)});
        p_77589_1_.func_92103_a(new ItemStack(Blocks.QUARTZ_BLOCK, 1, 1), new Object[] {"#", "#", '#', new ItemStack(Blocks.STONE_SLAB, 1, 7)});
        p_77589_1_.func_92103_a(new ItemStack(Blocks.QUARTZ_BLOCK, 2, 2), new Object[] {"#", "#", '#', new ItemStack(Blocks.QUARTZ_BLOCK, 1, 0)});
        p_77589_1_.func_92103_a(new ItemStack(Blocks.STONEBRICK, 4), new Object[] {"##", "##", '#', Blocks.STONE});
        p_77589_1_.func_92103_a(new ItemStack(Blocks.IRON_BARS, 16), new Object[] {"###", "###", '#', Items.IRON_INGOT});
        p_77589_1_.func_92103_a(new ItemStack(Blocks.GLASS_PANE, 16), new Object[] {"###", "###", '#', Blocks.GLASS});
        p_77589_1_.func_92103_a(new ItemStack(Blocks.REDSTONE_LAMP, 1), new Object[] {" R ", "RGR", " R ", 'R', Items.REDSTONE, 'G', Blocks.GLOWSTONE});
        p_77589_1_.func_92103_a(new ItemStack(Blocks.BEACON, 1), new Object[] {"GGG", "GSG", "OOO", 'G', Blocks.GLASS, 'S', Items.NETHER_STAR, 'O', Blocks.OBSIDIAN});
        p_77589_1_.func_92103_a(new ItemStack(Blocks.NETHER_BRICK, 1), new Object[] {"NN", "NN", 'N', Items.NETHERBRICK});
    }
}