package net.minecraft.item.crafting;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class RecipesWeapons
{
    private String[][] field_77585_a = new String[][] {{"X", "X", "#"}};
    private Object[][] field_77584_b;
    private static final String __OBFID = "CL_00000097";

    public RecipesWeapons()
    {
        this.field_77584_b = new Object[][] {{Blocks.PLANKS, Blocks.COBBLESTONE, Items.IRON_INGOT, Items.DIAMOND, Items.GOLD_INGOT}, {Items.WOODEN_SWORD, Items.STONE_SWORD, Items.IRON_SWORD, Items.DIAMOND_SWORD, Items.GOLDEN_SWORD}};
    }

    public void func_77583_a(CraftingManager p_77583_1_)
    {
        for (int i = 0; i < this.field_77584_b[0].length; ++i)
        {
            Object object = this.field_77584_b[0][i];

            for (int j = 0; j < this.field_77584_b.length - 1; ++j)
            {
                Item item = (Item)this.field_77584_b[j + 1][i];
                p_77583_1_.func_92103_a(new ItemStack(item), new Object[] {this.field_77585_a[j], '#', Items.STICK, 'X', object});
            }
        }

        p_77583_1_.func_92103_a(new ItemStack(Items.BOW, 1), new Object[] {" #X", "# X", " #X", 'X', Items.STRING, '#', Items.STICK});
        p_77583_1_.func_92103_a(new ItemStack(Items.ARROW, 4), new Object[] {"X", "#", "Y", 'Y', Items.FEATHER, 'X', Items.FLINT, '#', Items.STICK});
    }
}