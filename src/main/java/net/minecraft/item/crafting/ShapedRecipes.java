package net.minecraft.item.crafting;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ShapedRecipes implements IRecipe
{
    public final int field_77576_b;
    public final int field_77577_c;
    public final ItemStack[] field_77574_d;
    private ItemStack field_77575_e;
    private boolean field_92101_f;
    private static final String __OBFID = "CL_00000093";

    public ShapedRecipes(int p_i1917_1_, int p_i1917_2_, ItemStack[] p_i1917_3_, ItemStack p_i1917_4_)
    {
        this.field_77576_b = p_i1917_1_;
        this.field_77577_c = p_i1917_2_;
        this.field_77574_d = p_i1917_3_;
        this.field_77575_e = p_i1917_4_;
    }

    public ItemStack func_77571_b()
    {
        return this.field_77575_e;
    }

    public boolean func_77569_a(InventoryCrafting p_77569_1_, World p_77569_2_)
    {
        for (int i = 0; i <= 3 - this.field_77576_b; ++i)
        {
            for (int j = 0; j <= 3 - this.field_77577_c; ++j)
            {
                if (this.func_77573_a(p_77569_1_, i, j, true))
                {
                    return true;
                }

                if (this.func_77573_a(p_77569_1_, i, j, false))
                {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean func_77573_a(InventoryCrafting p_77573_1_, int p_77573_2_, int p_77573_3_, boolean p_77573_4_)
    {
        for (int k = 0; k < 3; ++k)
        {
            for (int l = 0; l < 3; ++l)
            {
                int i1 = k - p_77573_2_;
                int j1 = l - p_77573_3_;
                ItemStack itemstack = null;

                if (i1 >= 0 && j1 >= 0 && i1 < this.field_77576_b && j1 < this.field_77577_c)
                {
                    if (p_77573_4_)
                    {
                        itemstack = this.field_77574_d[this.field_77576_b - i1 - 1 + j1 * this.field_77576_b];
                    }
                    else
                    {
                        itemstack = this.field_77574_d[i1 + j1 * this.field_77576_b];
                    }
                }

                ItemStack itemstack1 = p_77573_1_.func_70463_b(k, l);

                if (itemstack1 != null || itemstack != null)
                {
                    if (itemstack1 == null && itemstack != null || itemstack1 != null && itemstack == null)
                    {
                        return false;
                    }

                    if (itemstack.getBaseItem() != itemstack1.getBaseItem())
                    {
                        return false;
                    }

                    if (itemstack.func_77960_j() != 32767 && itemstack.func_77960_j() != itemstack1.func_77960_j())
                    {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public ItemStack func_77572_b(InventoryCrafting p_77572_1_)
    {
        ItemStack itemstack = this.func_77571_b().func_77946_l();

        if (this.field_92101_f)
        {
            for (int i = 0; i < p_77572_1_.func_70302_i_(); ++i)
            {
                ItemStack itemstack1 = p_77572_1_.func_70301_a(i);

                if (itemstack1 != null && itemstack1.func_77942_o())
                {
                    itemstack.func_77982_d((NBTTagCompound)itemstack1.field_77990_d.func_74737_b());
                }
            }
        }

        return itemstack;
    }

    public int func_77570_a()
    {
        return this.field_77576_b * this.field_77577_c;
    }

    public ShapedRecipes func_92100_c()
    {
        this.field_92101_f = true;
        return this;
    }
}