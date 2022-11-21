package net.minecraft.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityDispenser;

public class ContainerDispenser extends Container
{
    private TileEntityDispenser field_75182_e;
    private static final String __OBFID = "CL_00001763";

    public ContainerDispenser(IInventory p_i1825_1_, TileEntityDispenser p_i1825_2_)
    {
        this.field_75182_e = p_i1825_2_;
        int i;
        int j;

        for (i = 0; i < 3; ++i)
        {
            for (j = 0; j < 3; ++j)
            {
                this.func_75146_a(new Slot(p_i1825_2_, j + i * 3, 62 + j * 18, 17 + i * 18));
            }
        }

        for (i = 0; i < 3; ++i)
        {
            for (j = 0; j < 9; ++j)
            {
                this.func_75146_a(new Slot(p_i1825_1_, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (i = 0; i < 9; ++i)
        {
            this.func_75146_a(new Slot(p_i1825_1_, i, 8 + i * 18, 142));
        }
    }

    public boolean func_75145_c(EntityPlayer p_75145_1_)
    {
        return this.field_75182_e.func_70300_a(p_75145_1_);
    }

    public ItemStack func_82846_b(EntityPlayer p_82846_1_, int p_82846_2_)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.field_75151_b.get(p_82846_2_);

        if (slot != null && slot.func_75216_d())
        {
            ItemStack itemstack1 = slot.func_75211_c();
            itemstack = itemstack1.func_77946_l();

            if (p_82846_2_ < 9)
            {
                if (!this.func_75135_a(itemstack1, 9, 45, true))
                {
                    return null;
                }
            }
            else if (!this.func_75135_a(itemstack1, 0, 9, false))
            {
                return null;
            }

            if (itemstack1.field_77994_a == 0)
            {
                slot.func_75215_d((ItemStack)null);
            }
            else
            {
                slot.func_75218_e();
            }

            if (itemstack1.field_77994_a == itemstack.field_77994_a)
            {
                return null;
            }

            slot.func_82870_a(p_82846_1_, itemstack1);
        }

        return itemstack;
    }
}