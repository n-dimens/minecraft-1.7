package net.minecraft.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface IInventory
{
    int func_70302_i_();

    ItemStack func_70301_a(int p_70301_1_);

    ItemStack func_70298_a(int p_70298_1_, int p_70298_2_);

    ItemStack func_70304_b(int p_70304_1_);

    void func_70299_a(int p_70299_1_, ItemStack p_70299_2_);

    String func_145825_b();

    boolean func_145818_k_();

    int func_70297_j_();

    void func_70296_d();

    boolean func_70300_a(EntityPlayer p_70300_1_);

    void func_70295_k_();

    void func_70305_f();

    boolean func_94041_b(int p_94041_1_, ItemStack p_94041_2_);
}