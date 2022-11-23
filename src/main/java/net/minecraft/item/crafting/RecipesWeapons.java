package net.minecraft.item.crafting;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class RecipesWeapons
{
    private final String[][] field_77585_a = new String[][] {{"X", "X", "#"}};
    private final Object[][] field_77584_b;
    private static final String __OBFID = "CL_00000097";

    public RecipesWeapons()
    {
        this.field_77584_b = new Object[][] {{Blocks.PLANKS, Blocks.COBBLESTONE, Items.IRON_INGOT, Items.DIAMOND, Items.GOLD_INGOT}, {Items.WOODEN_SWORD, Items.STONE_SWORD, Items.IRON_SWORD, Items.DIAMOND_SWORD, Items.GOLDEN_SWORD}};
    }

    public void addRecipes(CraftingManager craftingManager)
    {
        for (int i = 0; i < this.field_77584_b[0].length; ++i)
        {
            Object object = this.field_77584_b[0][i];

            for (int j = 0; j < this.field_77584_b.length - 1; ++j)
            {
                Item item = (Item)this.field_77584_b[j + 1][i];
                craftingManager.addRecipe(new ItemStack(item), this.field_77585_a[j], '#', Items.STICK, 'X', object);
            }
        }

        craftingManager.addRecipe(new ItemStack(Items.BOW, 1), " #X", "# X", " #X", 'X', Items.STRING, '#', Items.STICK);
        craftingManager.addRecipe(new ItemStack(Items.ARROW, 4), "X", "#", "Y", 'Y', Items.FEATHER, 'X', Items.FLINT, '#', Items.STICK);
    }
}