package net.minecraft.item.crafting;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class RecipesArmor
{
    private String[][] field_77611_a = new String[][] {{"XXX", "X X"}, {"X X", "XXX", "XXX"}, {"XXX", "X X", "X X"}, {"X X", "X X"}};
    private Object[][] field_77610_b;
    private static final String __OBFID = "CL_00000080";

    public RecipesArmor()
    {
        this.field_77610_b = new Object[][] {{Items.LEATHER, Blocks.field_150480_ab, Items.IRON_INGOT, Items.DIAMOND, Items.GOLD_INGOT}, {Items.LEATHER_HELMET, Items.CHAINMAIL_HELMET, Items.IRON_HELMET, Items.DIAMOND_HELMET, Items.GOLDEN_HELMET}, {Items.LEATHER_CHESTPLATE, Items.CHAINMAIL_CHESTPLATE, Items.IRON_CHESTPLATE, Items.DIAMOND_CHESTPLATE, Items.GOLDEN_CHESTPLATE}, {Items.LEATHER_LEGGINGS, Items.CHAINMAIL_LEGGINGS, Items.IRON_LEGGINGS, Items.DIAMOND_LEGGINGS, Items.GOLDEN_LEGGINGS}, {Items.LEATHER_BOOTS, Items.CHAINMAIL_BOOTS, Items.IRON_BOOTS, Items.DIAMOND_BOOTS, Items.GOLDEN_BOOTS}};
    }

    public void func_77609_a(CraftingManager p_77609_1_)
    {
        for (int i = 0; i < this.field_77610_b[0].length; ++i)
        {
            Object object = this.field_77610_b[0][i];

            for (int j = 0; j < this.field_77610_b.length - 1; ++j)
            {
                Item item = (Item)this.field_77610_b[j + 1][i];
                p_77609_1_.func_92103_a(new ItemStack(item), new Object[] {this.field_77611_a[j], 'X', object});
            }
        }
    }
}