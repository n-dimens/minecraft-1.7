package net.minecraft.inventory;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.util.IIcon;

public class ContainerPlayer extends Container
{
    public InventoryCrafting field_75181_e = new InventoryCrafting(this, 2, 2);
    public IInventory field_75179_f = new InventoryCraftResult();
    public boolean field_75180_g;
    private final EntityPlayer field_82862_h;
    private static final String __OBFID = "CL_00001754";

    public ContainerPlayer(final InventoryPlayer p_i1819_1_, boolean p_i1819_2_, EntityPlayer p_i1819_3_)
    {
        this.field_75180_g = p_i1819_2_;
        this.field_82862_h = p_i1819_3_;
        this.func_75146_a(new SlotCrafting(p_i1819_1_.player, this.field_75181_e, this.field_75179_f, 0, 144, 36));
        int i;
        int j;

        for (i = 0; i < 2; ++i)
        {
            for (j = 0; j < 2; ++j)
            {
                this.func_75146_a(new Slot(this.field_75181_e, j + i * 2, 88 + j * 18, 26 + i * 18));
            }
        }

        for (i = 0; i < 4; ++i)
        {
            final int k = i;
            this.func_75146_a(new Slot(p_i1819_1_, p_i1819_1_.func_70302_i_() - 1 - i, 8, 8 + i * 18)
            {
                private static final String __OBFID = "CL_00001755";
                public int func_75219_a()
                {
                    return 1;
                }
                public boolean func_75214_a(ItemStack p_75214_1_)
                {
                    return p_75214_1_ == null ? false : (p_75214_1_.getBaseItem() instanceof ItemArmor ? ((ItemArmor)p_75214_1_.getBaseItem()).field_77881_a == k : (p_75214_1_.getBaseItem() != Item.func_150898_a(Blocks.PUMPKIN) && p_75214_1_.getBaseItem() != Items.SKULL ? false : k == 0));
                }
                @SideOnly(Side.CLIENT)
                public IIcon func_75212_b()
                {
                    return ItemArmor.func_94602_b(k);
                }
            });
        }

        for (i = 0; i < 3; ++i)
        {
            for (j = 0; j < 9; ++j)
            {
                this.func_75146_a(new Slot(p_i1819_1_, j + (i + 1) * 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (i = 0; i < 9; ++i)
        {
            this.func_75146_a(new Slot(p_i1819_1_, i, 8 + i * 18, 142));
        }

        this.func_75130_a(this.field_75181_e);
    }

    public void func_75130_a(IInventory p_75130_1_)
    {
        this.field_75179_f.putItem(0, CraftingManager.getInstance().func_82787_a(this.field_75181_e, this.field_82862_h.world));
    }

    public void func_75134_a(EntityPlayer p_75134_1_)
    {
        super.func_75134_a(p_75134_1_);

        for (int i = 0; i < 4; ++i)
        {
            ItemStack itemstack = this.field_75181_e.func_70304_b(i);

            if (itemstack != null)
            {
                p_75134_1_.func_71019_a(itemstack, false);
            }
        }

        this.field_75179_f.putItem(0, (ItemStack)null);
    }

    public boolean func_75145_c(EntityPlayer player)
    {
        return true;
    }

    public ItemStack func_82846_b(EntityPlayer p_82846_1_, int p_82846_2_)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.field_75151_b.get(p_82846_2_);

        if (slot != null && slot.func_75216_d())
        {
            ItemStack itemstack1 = slot.func_75211_c();
            itemstack = itemstack1.func_77946_l();

            if (p_82846_2_ == 0)
            {
                if (!this.func_75135_a(itemstack1, 9, 45, true))
                {
                    return null;
                }

                slot.func_75220_a(itemstack1, itemstack);
            }
            else if (p_82846_2_ >= 1 && p_82846_2_ < 5)
            {
                if (!this.func_75135_a(itemstack1, 9, 45, false))
                {
                    return null;
                }
            }
            else if (p_82846_2_ >= 5 && p_82846_2_ < 9)
            {
                if (!this.func_75135_a(itemstack1, 9, 45, false))
                {
                    return null;
                }
            }
            else if (itemstack.getBaseItem() instanceof ItemArmor && !((Slot)this.field_75151_b.get(5 + ((ItemArmor)itemstack.getBaseItem()).field_77881_a)).func_75216_d())
            {
                int j = 5 + ((ItemArmor)itemstack.getBaseItem()).field_77881_a;

                if (!this.func_75135_a(itemstack1, j, j + 1, false))
                {
                    return null;
                }
            }
            else if (p_82846_2_ >= 9 && p_82846_2_ < 36)
            {
                if (!this.func_75135_a(itemstack1, 36, 45, false))
                {
                    return null;
                }
            }
            else if (p_82846_2_ >= 36 && p_82846_2_ < 45)
            {
                if (!this.func_75135_a(itemstack1, 9, 36, false))
                {
                    return null;
                }
            }
            else if (!this.func_75135_a(itemstack1, 9, 45, false))
            {
                return null;
            }

            if (itemstack1.count == 0)
            {
                slot.func_75215_d((ItemStack)null);
            }
            else
            {
                slot.func_75218_e();
            }

            if (itemstack1.count == itemstack.count)
            {
                return null;
            }

            slot.func_82870_a(p_82846_1_, itemstack1);
        }

        return itemstack;
    }

    public boolean func_94530_a(ItemStack p_94530_1_, Slot p_94530_2_)
    {
        return p_94530_2_.field_75224_c != this.field_75179_f && super.func_94530_a(p_94530_1_, p_94530_2_);
    }
}