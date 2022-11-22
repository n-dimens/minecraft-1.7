package net.minecraft.tileentity;

import java.util.Random;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class TileEntityDispenser extends TileEntity implements IInventory
{
    private ItemStack[] field_146022_i = new ItemStack[9];
    private Random field_146021_j = new Random();
    protected String field_146020_a;
    private static final String __OBFID = "CL_00000352";

    public int func_70302_i_()
    {
        return 9;
    }

    public ItemStack func_70301_a(int p_70301_1_)
    {
        return this.field_146022_i[p_70301_1_];
    }

    public ItemStack func_70298_a(int p_70298_1_, int p_70298_2_)
    {
        if (this.field_146022_i[p_70298_1_] != null)
        {
            ItemStack itemstack;

            if (this.field_146022_i[p_70298_1_].count <= p_70298_2_)
            {
                itemstack = this.field_146022_i[p_70298_1_];
                this.field_146022_i[p_70298_1_] = null;
                this.func_70296_d();
                return itemstack;
            }
            else
            {
                itemstack = this.field_146022_i[p_70298_1_].func_77979_a(p_70298_2_);

                if (this.field_146022_i[p_70298_1_].count == 0)
                {
                    this.field_146022_i[p_70298_1_] = null;
                }

                this.func_70296_d();
                return itemstack;
            }
        }
        else
        {
            return null;
        }
    }

    public ItemStack func_70304_b(int p_70304_1_)
    {
        if (this.field_146022_i[p_70304_1_] != null)
        {
            ItemStack itemstack = this.field_146022_i[p_70304_1_];
            this.field_146022_i[p_70304_1_] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }

    public int func_146017_i()
    {
        int i = -1;
        int j = 1;

        for (int k = 0; k < this.field_146022_i.length; ++k)
        {
            if (this.field_146022_i[k] != null && this.field_146021_j.nextInt(j++) == 0)
            {
                i = k;
            }
        }

        return i;
    }

    public void putItem(int p_70299_1_, ItemStack p_70299_2_)
    {
        this.field_146022_i[p_70299_1_] = p_70299_2_;

        if (p_70299_2_ != null && p_70299_2_.count > this.func_70297_j_())
        {
            p_70299_2_.count = this.func_70297_j_();
        }

        this.func_70296_d();
    }

    public int func_146019_a(ItemStack p_146019_1_)
    {
        for (int i = 0; i < this.field_146022_i.length; ++i)
        {
            if (this.field_146022_i[i] == null || this.field_146022_i[i].getBaseItem() == null)
            {
                this.putItem(i, p_146019_1_);
                return i;
            }
        }

        return -1;
    }

    public String func_145825_b()
    {
        return this.func_145818_k_() ? this.field_146020_a : "container.dispenser";
    }

    public void func_146018_a(String p_146018_1_)
    {
        this.field_146020_a = p_146018_1_;
    }

    public boolean func_145818_k_()
    {
        return this.field_146020_a != null;
    }

    public void func_145839_a(NBTTagCompound p_145839_1_)
    {
        super.func_145839_a(p_145839_1_);
        NBTTagList nbttaglist = p_145839_1_.func_150295_c("Items", 10);
        this.field_146022_i = new ItemStack[this.func_70302_i_()];

        for (int i = 0; i < nbttaglist.func_74745_c(); ++i)
        {
            NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
            int j = nbttagcompound1.func_74771_c("Slot") & 255;

            if (j >= 0 && j < this.field_146022_i.length)
            {
                this.field_146022_i[j] = ItemStack.func_77949_a(nbttagcompound1);
            }
        }

        if (p_145839_1_.func_150297_b("CustomName", 8))
        {
            this.field_146020_a = p_145839_1_.func_74779_i("CustomName");
        }
    }

    public void func_145841_b(NBTTagCompound p_145841_1_)
    {
        super.func_145841_b(p_145841_1_);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.field_146022_i.length; ++i)
        {
            if (this.field_146022_i[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.func_74774_a("Slot", (byte)i);
                this.field_146022_i[i].func_77955_b(nbttagcompound1);
                nbttaglist.func_74742_a(nbttagcompound1);
            }
        }

        p_145841_1_.func_74782_a("Items", nbttaglist);

        if (this.func_145818_k_())
        {
            p_145841_1_.func_74778_a("CustomName", this.field_146020_a);
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
        return true;
    }
}