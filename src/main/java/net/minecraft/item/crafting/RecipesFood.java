package net.minecraft.item.crafting;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class RecipesFood
{
    private static final String __OBFID = "CL_00000084";

    public void func_77608_a(CraftingManager p_77608_1_)
    {
        p_77608_1_.func_77596_b(new ItemStack(Items.MUSHROOM_STEW), new Object[] {Blocks.BROWN_MUSHROOM, Blocks.RED_MUSHROOM, Items.BOWL});
        p_77608_1_.func_92103_a(new ItemStack(Items.COOKIE, 8), new Object[] {"#X#", 'X', new ItemStack(Items.DYE, 1, 3), '#', Items.WHEAT});
        p_77608_1_.func_92103_a(new ItemStack(Blocks.MELON_BLOCK), new Object[] {"MMM", "MMM", "MMM", 'M', Items.MELON});
        p_77608_1_.func_92103_a(new ItemStack(Items.MELON_SEEDS), new Object[] {"M", 'M', Items.MELON});
        p_77608_1_.func_92103_a(new ItemStack(Items.PUMPKIN_SEEDS, 4), new Object[] {"M", 'M', Blocks.PUMPKIN});
        p_77608_1_.func_77596_b(new ItemStack(Items.PUMPKIN_PIE), new Object[] {Blocks.PUMPKIN, Items.SUGAR, Items.EGG});
        p_77608_1_.func_77596_b(new ItemStack(Items.FERMENTED_SPIDER_EYE), new Object[] {Items.SPIDER_EYE, Blocks.BROWN_MUSHROOM, Items.SUGAR});
        p_77608_1_.func_77596_b(new ItemStack(Items.BLAZE_POWDER, 2), new Object[] {Items.BLAZE_ROD});
        p_77608_1_.func_77596_b(new ItemStack(Items.MAGMA_CREAM), new Object[] {Items.BLAZE_POWDER, Items.SLIME_BALL});
    }
}