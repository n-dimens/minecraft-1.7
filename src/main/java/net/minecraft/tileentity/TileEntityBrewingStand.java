package net.minecraft.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.PotionHelper;

public class TileEntityBrewingStand extends TileEntity implements ISidedInventory
{
    private static final int[] field_145941_a = new int[] {3};
    private static final int[] field_145947_i = new int[] {0, 1, 2};
    private ItemStack[] field_145945_j = new ItemStack[4];
    private int field_145946_k;
    private int field_145943_l;
    private Item field_145944_m;
    private String field_145942_n;
    private static final String __OBFID = "CL_00000345";

    public String func_145825_b()
    {
        return this.func_145818_k_() ? this.field_145942_n : "container.brewing";
    }

    public boolean func_145818_k_()
    {
        return this.field_145942_n != null && this.field_145942_n.length() > 0;
    }

    public void func_145937_a(String p_145937_1_)
    {
        this.field_145942_n = p_145937_1_;
    }

    public int func_70302_i_()
    {
        return this.field_145945_j.length;
    }

    public void func_145845_h()
    {
        if (this.field_145946_k > 0)
        {
            --this.field_145946_k;

            if (this.field_145946_k == 0)
            {
                this.func_145940_l();
                this.func_70296_d();
            }
            else if (!this.func_145934_k())
            {
                this.field_145946_k = 0;
                this.func_70296_d();
            }
            else if (this.field_145944_m != this.field_145945_j[3].func_77973_b())
            {
                this.field_145946_k = 0;
                this.func_70296_d();
            }
        }
        else if (this.func_145934_k())
        {
            this.field_145946_k = 400;
            this.field_145944_m = this.field_145945_j[3].func_77973_b();
        }

        int i = this.func_145939_j();

        if (i != this.field_145943_l)
        {
            this.field_145943_l = i;
            this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, i, 2);
        }

        super.func_145845_h();
    }

    public int func_145935_i()
    {
        return this.field_145946_k;
    }

    private boolean func_145934_k()
    {
        if (this.field_145945_j[3] != null && this.field_145945_j[3].field_77994_a > 0)
        {
            ItemStack itemstack = this.field_145945_j[3];

            if (!itemstack.func_77973_b().func_150892_m(itemstack))
            {
                return false;
            }
            else
            {
                boolean flag = false;

                for (int i = 0; i < 3; ++i)
                {
                    if (this.field_145945_j[i] != null && this.field_145945_j[i].func_77973_b() == Items.field_151068_bn)
                    {
                        int j = this.field_145945_j[i].func_77960_j();
                        int k = this.func_145936_c(j, itemstack);

                        if (!ItemPotion.func_77831_g(j) && ItemPotion.func_77831_g(k))
                        {
                            flag = true;
                            break;
                        }

                        List list = Items.field_151068_bn.func_77834_f(j);
                        List list1 = Items.field_151068_bn.func_77834_f(k);

                        if ((j <= 0 || list != list1) && (list == null || !list.equals(list1) && list1 != null) && j != k)
                        {
                            flag = true;
                            break;
                        }
                    }
                }

                return flag;
            }
        }
        else
        {
            return false;
        }
    }

    private void func_145940_l()
    {
        if (this.func_145934_k())
        {
            ItemStack itemstack = this.field_145945_j[3];

            for (int i = 0; i < 3; ++i)
            {
                if (this.field_145945_j[i] != null && this.field_145945_j[i].func_77973_b() == Items.field_151068_bn)
                {
                    int j = this.field_145945_j[i].func_77960_j();
                    int k = this.func_145936_c(j, itemstack);
                    List list = Items.field_151068_bn.func_77834_f(j);
                    List list1 = Items.field_151068_bn.func_77834_f(k);

                    if ((j <= 0 || list != list1) && (list == null || !list.equals(list1) && list1 != null))
                    {
                        if (j != k)
                        {
                            this.field_145945_j[i].func_77964_b(k);
                        }
                    }
                    else if (!ItemPotion.func_77831_g(j) && ItemPotion.func_77831_g(k))
                    {
                        this.field_145945_j[i].func_77964_b(k);
                    }
                }
            }

            if (itemstack.func_77973_b().func_77634_r())
            {
                this.field_145945_j[3] = new ItemStack(itemstack.func_77973_b().func_77668_q());
            }
            else
            {
                --this.field_145945_j[3].field_77994_a;

                if (this.field_145945_j[3].field_77994_a <= 0)
                {
                    this.field_145945_j[3] = null;
                }
            }
        }
    }

    private int func_145936_c(int p_145936_1_, ItemStack p_145936_2_)
    {
        return p_145936_2_ == null ? p_145936_1_ : (p_145936_2_.func_77973_b().func_150892_m(p_145936_2_) ? PotionHelper.func_77913_a(p_145936_1_, p_145936_2_.func_77973_b().func_150896_i(p_145936_2_)) : p_145936_1_);
    }

    public void func_145839_a(NBTTagCompound p_145839_1_)
    {
        super.func_145839_a(p_145839_1_);
        NBTTagList nbttaglist = p_145839_1_.func_150295_c("Items", 10);
        this.field_145945_j = new ItemStack[this.func_70302_i_()];

        for (int i = 0; i < nbttaglist.func_74745_c(); ++i)
        {
            NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
            byte b0 = nbttagcompound1.func_74771_c("Slot");

            if (b0 >= 0 && b0 < this.field_145945_j.length)
            {
                this.field_145945_j[b0] = ItemStack.func_77949_a(nbttagcompound1);
            }
        }

        this.field_145946_k = p_145839_1_.func_74765_d("BrewTime");

        if (p_145839_1_.func_150297_b("CustomName", 8))
        {
            this.field_145942_n = p_145839_1_.func_74779_i("CustomName");
        }
    }

    public void func_145841_b(NBTTagCompound p_145841_1_)
    {
        super.func_145841_b(p_145841_1_);
        p_145841_1_.func_74777_a("BrewTime", (short)this.field_145946_k);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.field_145945_j.length; ++i)
        {
            if (this.field_145945_j[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.func_74774_a("Slot", (byte)i);
                this.field_145945_j[i].func_77955_b(nbttagcompound1);
                nbttaglist.func_74742_a(nbttagcompound1);
            }
        }

        p_145841_1_.func_74782_a("Items", nbttaglist);

        if (this.func_145818_k_())
        {
            p_145841_1_.func_74778_a("CustomName", this.field_145942_n);
        }
    }

    public ItemStack func_70301_a(int p_70301_1_)
    {
        return p_70301_1_ >= 0 && p_70301_1_ < this.field_145945_j.length ? this.field_145945_j[p_70301_1_] : null;
    }

    public ItemStack func_70298_a(int p_70298_1_, int p_70298_2_)
    {
        if (p_70298_1_ >= 0 && p_70298_1_ < this.field_145945_j.length)
        {
            ItemStack itemstack = this.field_145945_j[p_70298_1_];
            this.field_145945_j[p_70298_1_] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }

    public ItemStack func_70304_b(int p_70304_1_)
    {
        if (p_70304_1_ >= 0 && p_70304_1_ < this.field_145945_j.length)
        {
            ItemStack itemstack = this.field_145945_j[p_70304_1_];
            this.field_145945_j[p_70304_1_] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }

    public void func_70299_a(int p_70299_1_, ItemStack p_70299_2_)
    {
        if (p_70299_1_ >= 0 && p_70299_1_ < this.field_145945_j.length)
        {
            this.field_145945_j[p_70299_1_] = p_70299_2_;
        }
    }

    public int func_70297_j_()
    {
        return 64;
    }

    public boolean func_70300_a(EntityPlayer p_70300_1_)
    {
        return this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) != this ? false : p_70300_1_.func_70092_e((double)this.field_145851_c + 0.5D, (double)this.field_145848_d + 0.5D, (double)this.field_145849_e + 0.5D) <= 64.0D;
    }

    public void func_70295_k_() {}

    public void func_70305_f() {}

    public boolean func_94041_b(int p_94041_1_, ItemStack p_94041_2_)
    {
        return p_94041_1_ == 3 ? p_94041_2_.func_77973_b().func_150892_m(p_94041_2_) : p_94041_2_.func_77973_b() == Items.field_151068_bn || p_94041_2_.func_77973_b() == Items.field_151069_bo;
    }

    @SideOnly(Side.CLIENT)
    public void func_145938_d(int p_145938_1_)
    {
        this.field_145946_k = p_145938_1_;
    }

    public int func_145939_j()
    {
        int i = 0;

        for (int j = 0; j < 3; ++j)
        {
            if (this.field_145945_j[j] != null)
            {
                i |= 1 << j;
            }
        }

        return i;
    }

    public int[] func_94128_d(int p_94128_1_)
    {
        return p_94128_1_ == 1 ? field_145941_a : field_145947_i;
    }

    public boolean func_102007_a(int p_102007_1_, ItemStack p_102007_2_, int p_102007_3_)
    {
        return this.func_94041_b(p_102007_1_, p_102007_2_);
    }

    public boolean func_102008_b(int p_102008_1_, ItemStack p_102008_2_, int p_102008_3_)
    {
        return true;
    }
}