package net.minecraft.inventory;

import net.minecraft.item.ItemStack;

public interface ISidedInventory extends IInventory
{
    int[] func_94128_d(int p_94128_1_);

    boolean func_102007_a(int p_102007_1_, ItemStack p_102007_2_, int p_102007_3_);

    boolean func_102008_b(int p_102008_1_, ItemStack p_102008_2_, int p_102008_3_);
}