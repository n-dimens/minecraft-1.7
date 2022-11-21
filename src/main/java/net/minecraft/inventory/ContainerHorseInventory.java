package net.minecraft.inventory;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ContainerHorseInventory extends Container
{
    private IInventory field_111243_a;
    private EntityHorse field_111242_f;
    private static final String __OBFID = "CL_00001751";

    public ContainerHorseInventory(IInventory p_i1817_1_, final IInventory p_i1817_2_, final EntityHorse p_i1817_3_)
    {
        this.field_111243_a = p_i1817_2_;
        this.field_111242_f = p_i1817_3_;
        byte b0 = 3;
        p_i1817_2_.func_70295_k_();
        int i = (b0 - 4) * 18;
        this.func_75146_a(new Slot(p_i1817_2_, 0, 8, 18)
        {
            private static final String __OBFID = "CL_00001752";
            public boolean func_75214_a(ItemStack p_75214_1_)
            {
                return super.func_75214_a(p_75214_1_) && p_75214_1_.func_77973_b() == Items.SADDLE && !this.func_75216_d();
            }
        });
        this.func_75146_a(new Slot(p_i1817_2_, 1, 8, 36)
        {
            private static final String __OBFID = "CL_00001753";
            public boolean func_75214_a(ItemStack p_75214_1_)
            {
                return super.func_75214_a(p_75214_1_) && p_i1817_3_.func_110259_cr() && EntityHorse.func_146085_a(p_75214_1_.func_77973_b());
            }
            @SideOnly(Side.CLIENT)
            public boolean func_111238_b()
            {
                return p_i1817_3_.func_110259_cr();
            }
        });
        int j;
        int k;

        if (p_i1817_3_.func_110261_ca())
        {
            for (j = 0; j < b0; ++j)
            {
                for (k = 0; k < 5; ++k)
                {
                    this.func_75146_a(new Slot(p_i1817_2_, 2 + k + j * 5, 80 + k * 18, 18 + j * 18));
                }
            }
        }

        for (j = 0; j < 3; ++j)
        {
            for (k = 0; k < 9; ++k)
            {
                this.func_75146_a(new Slot(p_i1817_1_, k + j * 9 + 9, 8 + k * 18, 102 + j * 18 + i));
            }
        }

        for (j = 0; j < 9; ++j)
        {
            this.func_75146_a(new Slot(p_i1817_1_, j, 8 + j * 18, 160 + i));
        }
    }

    public boolean func_75145_c(EntityPlayer p_75145_1_)
    {
        return this.field_111243_a.func_70300_a(p_75145_1_) && this.field_111242_f.func_70089_S() && this.field_111242_f.func_70032_d(p_75145_1_) < 8.0F;
    }

    public ItemStack func_82846_b(EntityPlayer p_82846_1_, int p_82846_2_)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.field_75151_b.get(p_82846_2_);

        if (slot != null && slot.func_75216_d())
        {
            ItemStack itemstack1 = slot.func_75211_c();
            itemstack = itemstack1.func_77946_l();

            if (p_82846_2_ < this.field_111243_a.func_70302_i_())
            {
                if (!this.func_75135_a(itemstack1, this.field_111243_a.func_70302_i_(), this.field_75151_b.size(), true))
                {
                    return null;
                }
            }
            else if (this.func_75139_a(1).func_75214_a(itemstack1) && !this.func_75139_a(1).func_75216_d())
            {
                if (!this.func_75135_a(itemstack1, 1, 2, false))
                {
                    return null;
                }
            }
            else if (this.func_75139_a(0).func_75214_a(itemstack1))
            {
                if (!this.func_75135_a(itemstack1, 0, 1, false))
                {
                    return null;
                }
            }
            else if (this.field_111243_a.func_70302_i_() <= 2 || !this.func_75135_a(itemstack1, 2, this.field_111243_a.func_70302_i_(), false))
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
        }

        return itemstack;
    }

    public void func_75134_a(EntityPlayer p_75134_1_)
    {
        super.func_75134_a(p_75134_1_);
        this.field_111243_a.func_70305_f();
    }
}