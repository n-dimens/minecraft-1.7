package net.minecraft.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class InventoryCraftResult implements IInventory
{
    private ItemStack[] field_70467_a = new ItemStack[1];
    private static final String __OBFID = "CL_00001760";

    public int func_70302_i_()
    {
        return 1;
    }

    public ItemStack func_70301_a(int p_70301_1_)
    {
        return this.field_70467_a[0];
    }

    public String func_145825_b()
    {
        return "Result";
    }

    public boolean func_145818_k_()
    {
        return false;
    }

    public ItemStack func_70298_a(int p_70298_1_, int p_70298_2_)
    {
        if (this.field_70467_a[0] != null)
        {
            ItemStack itemstack = this.field_70467_a[0];
            this.field_70467_a[0] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }

    public ItemStack func_70304_b(int p_70304_1_)
    {
        if (this.field_70467_a[0] != null)
        {
            ItemStack itemstack = this.field_70467_a[0];
            this.field_70467_a[0] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }

    public void putItem(int p_70299_1_, ItemStack p_70299_2_)
    {
        this.field_70467_a[0] = p_70299_2_;
    }

    public int func_70297_j_()
    {
        return 64;
    }

    public void func_70296_d() {}

    public boolean func_70300_a(EntityPlayer p_70300_1_)
    {
        return true;
    }

    public void func_70295_k_() {}

    public void func_70305_f() {}

    public boolean func_94041_b(int p_94041_1_, ItemStack p_94041_2_)
    {
        return true;
    }
}