package net.minecraft.item.crafting;

import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class RecipeBookCloning implements IRecipe
{
    private static final String __OBFID = "CL_00000081";

    public boolean func_77569_a(InventoryCrafting p_77569_1_, World p_77569_2_)
    {
        int i = 0;
        ItemStack itemstack = null;

        for (int j = 0; j < p_77569_1_.func_70302_i_(); ++j)
        {
            ItemStack itemstack1 = p_77569_1_.func_70301_a(j);

            if (itemstack1 != null)
            {
                if (itemstack1.func_77973_b() == Items.WRITTEN_BOOK)
                {
                    if (itemstack != null)
                    {
                        return false;
                    }

                    itemstack = itemstack1;
                }
                else
                {
                    if (itemstack1.func_77973_b() != Items.WRITABLE_BOOK)
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
                if (itemstack1.func_77973_b() == Items.WRITTEN_BOOK)
                {
                    if (itemstack != null)
                    {
                        return null;
                    }

                    itemstack = itemstack1;
                }
                else
                {
                    if (itemstack1.func_77973_b() != Items.WRITABLE_BOOK)
                    {
                        return null;
                    }

                    ++i;
                }
            }
        }

        if (itemstack != null && i >= 1)
        {
            ItemStack itemstack2 = new ItemStack(Items.WRITTEN_BOOK, i + 1);
            itemstack2.func_77982_d((NBTTagCompound)itemstack.func_77978_p().func_74737_b());

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

    public ItemStack func_77571_b()
    {
        return null;
    }
}