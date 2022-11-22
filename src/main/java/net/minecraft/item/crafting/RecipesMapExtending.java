package net.minecraft.item.crafting;

import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapData;

public class RecipesMapExtending extends ShapedRecipes
{
    private static final String __OBFID = "CL_00000088";

    public RecipesMapExtending()
    {
        super(3, 3, new ItemStack[] {new ItemStack(Items.PAPER), new ItemStack(Items.PAPER), new ItemStack(Items.PAPER), new ItemStack(Items.PAPER), new ItemStack(Items.FILLED_MAP, 0, 32767), new ItemStack(Items.PAPER), new ItemStack(Items.PAPER), new ItemStack(Items.PAPER), new ItemStack(Items.PAPER)}, new ItemStack(Items.MAP, 0, 0));
    }

    public boolean func_77569_a(InventoryCrafting p_77569_1_, World p_77569_2_)
    {
        if (!super.func_77569_a(p_77569_1_, p_77569_2_))
        {
            return false;
        }
        else
        {
            ItemStack itemstack = null;

            for (int i = 0; i < p_77569_1_.func_70302_i_() && itemstack == null; ++i)
            {
                ItemStack itemstack1 = p_77569_1_.func_70301_a(i);

                if (itemstack1 != null && itemstack1.getBaseItem() == Items.FILLED_MAP)
                {
                    itemstack = itemstack1;
                }
            }

            if (itemstack == null)
            {
                return false;
            }
            else
            {
                MapData mapdata = Items.FILLED_MAP.func_77873_a(itemstack, p_77569_2_);
                return mapdata == null ? false : mapdata.field_76197_d < 4;
            }
        }
    }

    public ItemStack func_77572_b(InventoryCrafting p_77572_1_)
    {
        ItemStack itemstack = null;

        for (int i = 0; i < p_77572_1_.func_70302_i_() && itemstack == null; ++i)
        {
            ItemStack itemstack1 = p_77572_1_.func_70301_a(i);

            if (itemstack1 != null && itemstack1.getBaseItem() == Items.FILLED_MAP)
            {
                itemstack = itemstack1;
            }
        }

        itemstack = itemstack.func_77946_l();
        itemstack.count = 1;

        if (itemstack.func_77978_p() == null)
        {
            itemstack.func_77982_d(new NBTTagCompound());
        }

        itemstack.func_77978_p().func_74757_a("map_is_scaling", true);
        return itemstack;
    }
}