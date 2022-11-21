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
        this.field_77587_b = new Object[][] {{Blocks.PLANKS, Blocks.COBBLESTONE, Items.IRON_INGOT, Items.DIAMOND, Items.GOLD_INGOT}, {Items.WOODEN_PICKAXE, Items.STONE_PICKAXE, Items.IRON_PICKAXE, Items.DIAMOND_PICKAXE, Items.GOLDEN_PICKAXE}, {Items.WOODEN_SHOVEL, Items.STONE_SHOVEL, Items.IRON_SHOVEL, Items.DIAMOND_SHOVEL, Items.GOLDEN_SHOVEL}, {Items.WOODEN_AXE, Items.STONE_AXE, Items.IRON_AXE, Items.DIAMOND_AXE, Items.GOLDEN_AXE}, {Items.WOODEN_HOE, Items.STONE_HOE, Items.IRON_HOE, Items.DIAMOND_HOE, Items.GOLDEN_HOE}};
    }

    public void func_77586_a(CraftingManager p_77586_1_)
    {
        for (int i = 0; i < this.field_77587_b[0].length; ++i)
        {
            Object object = this.field_77587_b[0][i];

            for (int j = 0; j < this.field_77587_b.length - 1; ++j)
            {
                Item item = (Item)this.field_77587_b[j + 1][i];
                p_77586_1_.func_92103_a(new ItemStack(item), new Object[] {this.field_77588_a[j], '#', Items.STICK, 'X', object});
            }
        }

        p_77586_1_.func_92103_a(new ItemStack(Items.SHEARS), new Object[] {" #", "# ", '#', Items.IRON_INGOT});
    }
}