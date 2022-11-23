package net.minecraft.item.crafting;

import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class RecipesMapCloning implements IRecipe
{
    private static final String __OBFID = "CL_00000087";

    public boolean func_77569_a(InventoryCrafting p_77569_1_, World p_77569_2_)
    {
        int i = 0;
        ItemStack itemstack = null;

        for (int j = 0; j < p_77569_1_.func_70302_i_(); ++j)
        {
            ItemStack itemstack1 = p_77569_1_.func_70301_a(j);

            if (itemstack1 != null)
            {
                if (itemstack1.getBaseItem() == Items.FILLED_MAP)
                {
                    if (itemstack != null)
                    {
                        return false;
                    }

                    itemstack = itemstack1;
                }
                else
                {
                    if (itemstack1.getBaseItem() != Items.MAP)
                    {
                        return false;
                    }

                    ++i;
                }
            }
        }

        return itemstack != null && i > 0;
    }

    public ItemStack func_77572_b(InventoryCrafting p_77572_1_)
    {
        int i = 0;
        ItemStack itemstack = null;

        for (int j = 0; j < p_77572_1_.func_70302_i_(); ++j)
        {
            ItemStack itemstack1 = p_77572_1_.func_70301_a(j);

            if (itemstack1 != null)
            {
                if (itemstack1.getBaseItem() == Items.FILLED_MAP)
                {
                    if (itemstack != null)
                    {
                        return null;
                    }

                    itemstack = itemstack1;
                }
                else
                {
                    if (itemstack1.getBaseItem() != Items.MAP)
                    {
                        return null;
                    }

                    ++i;
                }
            }
        }

        if (itemstack != null && i >= 1)
        {
            ItemStack itemstack2 = new ItemStack(Items.FILLED_MAP, i + 1, itemstack.func_77960_j());

            if (itemstack.func_82837_s())
            {
                itemstack2.func_151001_c(itemstack.func_82833_r());
            }

            return itemstack2;
        }
        else
        {
            return null;
        }
    }

    public int func_77570_a()
    {
        return 9;
    }

    public ItemStack getOutputItem()
    {
        return null;
    }
}