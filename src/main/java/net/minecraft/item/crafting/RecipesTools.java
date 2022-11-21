package net.minecraft.item.crafting;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class RecipesTools
{
    private String[][] field_77588_a = new String[][] {{"XXX", " # ", " # "}, {"X", "#", "#"}, {"XX", "X#", " #"}, {"XX", " #", " #"}};
    private Object[][] field_77587_b;
    private static final String __OBFID = "CL_00000096";

    public RecipesTools()
    {
        this.field_77587_b = new Object[][] {{Blocks.field_150344_f, Blocks.field_150347_e, Items.field_151042_j, Items.field_151045_i, Items.field_151043_k}, {Items.WOODEN_PICKAXE, Items.STONE_PICKAXE, Items.IRON_PICKAXE, Items.DIAMOND_PICKAXE, Items.GOLDEN_PICKAXE}, {Items.WOODEN_SHOVEL, Items.STONE_SHOVEL, Items.IRON_SHOVEL, Items.DIAMOND_SHOVEL, Items.GOLDEN_SHOVEL}, {Items.WOODEN_AXE, Items.STONE_AXE, Items.IRON_AXE, Items.DIAMOND_AXE, Items.GOLDEN_AXE}, {Items.field_151017_I, Items.field_151018_J, Items.IRON_HOE, Items.field_151012_L, Items.field_151013_M}};
    }

    public void func_77586_a(CraftingManager p_77586_1_)
    {
        for (int i = 0; i < this.field_77587_b[0].length; ++i)
        {
            Object object = this.field_77587_b[0][i];

            for (int j = 0; j < this.field_77587_b.length - 1; ++j)
            {
                Item item = (Item)this.field_77587_b[j + 1][i];
                p_77586_1_.func_92103_a(new ItemStack(item), new Object[] {this.field_77588_a[j], '#', Items.field_151055_y, 'X', object});
            }
        }

        p_77586_1_.func_92103_a(new ItemStack(Items.field_151097_aZ), new Object[] {" #", "# ", '#', Items.field_151042_j});
    }
}