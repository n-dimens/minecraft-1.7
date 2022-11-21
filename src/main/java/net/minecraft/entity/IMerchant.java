package net.minecraft.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;

public interface IMerchant
{
    void func_70932_a_(EntityPlayer p_70932_1_);

    EntityPlayer func_70931_l_();

    MerchantRecipeList func_70934_b(EntityPlayer p_70934_1_);

    @SideOnly(Side.CLIENT)
    void func_70930_a(MerchantRecipeList p_70930_1_);

    void func_70933_a(MerchantRecipe p_70933_1_);

    void func_110297_a_(ItemStack p_110297_1_);
}