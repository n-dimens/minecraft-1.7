package net.minecraft.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityEnderChest;

public class InventoryEnderChest extends InventoryBasic
{
    private TileEntityEnderChest field_70488_a;
    private static final String __OBFID = "CL_00001759";

    public InventoryEnderChest()
    {
        super("container.enderchest", false, 27);
    }

    public void func_146031_a(TileEntityEnderChest p_146031_1_)
    {
        this.field_70488_a = p_146031_1_;
    }

    public void func_70486_a(NBTTagList p_70486_1_)
    {
        int i;

        for (i = 0; i < this.func_70302_i_(); ++i)
        {
            this.putItem(i, (ItemStack)null);
        }

        for (i = 0; i < p_70486_1_.func_74745_c(); ++i)
        {
            NBTTagCompound nbttagcompound = p_70486_1_.func_150305_b(i);
            int j = nbttagcompound.func_74771_c("Slot") & 255;

            if (j >= 0 && j < this.func_70302_i_())
            {
                this.putItem(j, ItemStack.func_77949_a(nbttagcompound));
            }
        }
    }

    public NBTTagList func_70487_g()
    {
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.func_70302_i_(); ++i)
        {
            ItemStack itemstack = this.func_70301_a(i);

            if (itemstack != null)
            {
                NBTTagCompound nbttagcompound = new NBTTagCompound();
                nbttagcompound.func_74774_a("Slot", (byte)i);
                itemstack.func_77955_b(nbttagcompound);
                nbttaglist.func_74742_a(nbttagcompound);
            }
        }

        return nbttaglist;
    }

    public boolean func_70300_a(EntityPlayer p_70300_1_)
    {
        return this.field_70488_a != null && !this.field_70488_a.func_145971_a(p_70300_1_) ? false : super.func_70300_a(p_70300_1_);
    }

    public void func_70295_k_()
    {
        if (this.field_70488_a != null)
        {
            this.field_70488_a.func_145969_a();
        }

        super.func_70295_k_();
    }

    public void func_70305_f()
    {
        if (this.field_70488_a != null)
        {
            this.field_70488_a.func_145970_b();
        }

        super.func_70305_f();
        this.field_70488_a = null;
    }
}