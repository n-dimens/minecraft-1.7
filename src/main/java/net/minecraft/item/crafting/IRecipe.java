package net.minecraft.item.crafting;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface IRecipe
{
    boolean func_77569_a(InventoryCrafting p_77569_1_, World p_77569_2_);

    ItemStack func_77572_b(InventoryCrafting p_77572_1_);

    int func_77570_a();

    ItemStack func_77571_b();
}