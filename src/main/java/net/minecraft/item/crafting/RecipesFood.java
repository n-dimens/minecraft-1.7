package net.minecraft.item.crafting;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class RecipesFood
{
    private static final String __OBFID = "CL_00000084";

    public void func_77608_a(CraftingManager p_77608_1_)
    {
        p_77608_1_.func_77596_b(new ItemStack(Items.MUSHROOM_STEW), new Object[] {Blocks.field_150338_P, Blocks.field_150337_Q, Items.BOWL});
        p_77608_1_.func_92103_a(new ItemStack(Items.COOKIE, 8), new Object[] {"#X#", 'X', new ItemStack(Items.DYE, 1, 3), '#', Items.WHEAT});
        p_77608_1_.func_92103_a(new ItemStack(Blocks.field_150440_ba), new Object[] {"MMM", "MMM", "MMM", 'M', Items.MELON});
        p_77608_1_.func_92103_a(new ItemStack(Items.MELON_SEEDS), new Object[] {"M", 'M', Items.MELON});
        p_77608_1_.func_92103_a(new ItemStack(Items.PUMPKIN_SEEDS, 4), new Object[] {"M", 'M', Blocks.field_150423_aK});
        p_77608_1_.func_77596_b(new ItemStack(Items.PUMPKIN_PIE), new Object[] {Blocks.field_150423_aK, Items.SUGAR, Items.EGG});
        p_77608_1_.func_77596_b(new ItemStack(Items.FERMENTED_SPIDER_EYE), new Object[] {Items.SPIDER_EYE, Blocks.field_150338_P, Items.SUGAR});
        p_77608_1_.func_77596_b(new ItemStack(Items.BLAZE_POWDER, 2), new Object[] {Items.BLAZE_ROD});
        p_77608_1_.func_77596_b(new ItemStack(Items.MAGMA_CREAM), new Object[] {Items.BLAZE_POWDER, Items.SLIME_BALL});
    }
}