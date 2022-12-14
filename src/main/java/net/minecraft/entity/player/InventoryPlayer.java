package net.minecraft.entity.player;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.concurrent.Callable;
import net.minecraft.block.Block;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ReportedException;

public class InventoryPlayer implements IInventory
{
    public ItemStack[] cells = new ItemStack[36];
    public ItemStack[] field_70460_b = new ItemStack[4];
    public int activeItemPosition;
    @SideOnly(Side.CLIENT)
    private ItemStack field_70456_f;
    public final EntityPlayer player;
    private ItemStack field_70457_g;
    public boolean field_70459_e;
    private static final String __OBFID = "CL_00001709";

    public InventoryPlayer(EntityPlayer player)
    {
        this.player = player;
    }

    /**
     * Предмет в руке
     */
    public ItemStack getActiveItem()
    {
        return this.activeItemPosition < 9 && this.activeItemPosition >= 0 ? this.cells[this.activeItemPosition] : null;
    }

    public static int func_70451_h()
    {
        return 9;
    }

    private int getItemPosition(Item item)
    {
        for (int i = 0; i < this.cells.length; ++i)
        {
            if (this.cells[i] != null && this.cells[i].getBaseItem() == item)
            {
                return i;
            }
        }

        return -1;
    }

    @SideOnly(Side.CLIENT)
    private int func_146024_c(Item item, int p_146024_2_)
    {
        for (int j = 0; j < this.cells.length; ++j)
        {
            if (this.cells[j] != null && this.cells[j].getBaseItem() == item && this.cells[j].func_77960_j() == p_146024_2_)
            {
                return j;
            }
        }

        return -1;
    }

    private int func_70432_d(ItemStack p_70432_1_)
    {
        for (int i = 0; i < this.cells.length; ++i)
        {
            if (this.cells[i] != null && this.cells[i].getBaseItem() == p_70432_1_.getBaseItem() && this.cells[i].func_77985_e() && this.cells[i].count < this.cells[i].func_77976_d() && this.cells[i].count < this.func_70297_j_() && (!this.cells[i].func_77981_g() || this.cells[i].func_77960_j() == p_70432_1_.func_77960_j()) && ItemStack.func_77970_a(this.cells[i], p_70432_1_))
            {
                return i;
            }
        }

        return -1;
    }

    public int func_70447_i()
    {
        for (int i = 0; i < this.cells.length; ++i)
        {
            if (this.cells[i] == null)
            {
                return i;
            }
        }

        return -1;
    }

    @SideOnly(Side.CLIENT)
    public void func_146030_a(Item p_146030_1_, int p_146030_2_, boolean p_146030_3_, boolean p_146030_4_)
    {
        boolean flag2 = true;
        this.field_70456_f = this.getActiveItem();
        int k;

        if (p_146030_3_)
        {
            k = this.func_146024_c(p_146030_1_, p_146030_2_);
        }
        else
        {
            k = this.getItemPosition(p_146030_1_);
        }

        if (k >= 0 && k < 9)
        {
            this.activeItemPosition = k;
        }
        else
        {
            if (p_146030_4_ && p_146030_1_ != null)
            {
                int j = this.func_70447_i();

                if (j >= 0 && j < 9)
                {
                    this.activeItemPosition = j;
                }

                this.func_70439_a(p_146030_1_, p_146030_2_);
            }
        }
    }

    public int func_146027_a(Item p_146027_1_, int p_146027_2_)
    {
        int j = 0;
        int k;
        ItemStack itemstack;

        for (k = 0; k < this.cells.length; ++k)
        {
            itemstack = this.cells[k];

            if (itemstack != null && (p_146027_1_ == null || itemstack.getBaseItem() == p_146027_1_) && (p_146027_2_ <= -1 || itemstack.func_77960_j() == p_146027_2_))
            {
                j += itemstack.count;
                this.cells[k] = null;
            }
        }

        for (k = 0; k < this.field_70460_b.length; ++k)
        {
            itemstack = this.field_70460_b[k];

            if (itemstack != null && (p_146027_1_ == null || itemstack.getBaseItem() == p_146027_1_) && (p_146027_2_ <= -1 || itemstack.func_77960_j() == p_146027_2_))
            {
                j += itemstack.count;
                this.field_70460_b[k] = null;
            }
        }

        if (this.field_70457_g != null)
        {
            if (p_146027_1_ != null && this.field_70457_g.getBaseItem() != p_146027_1_)
            {
                return j;
            }

            if (p_146027_2_ > -1 && this.field_70457_g.func_77960_j() != p_146027_2_)
            {
                return j;
            }

            j += this.field_70457_g.count;
            this.func_70437_b((ItemStack)null);
        }

        return j;
    }

    @SideOnly(Side.CLIENT)
    public void func_70453_c(int p_70453_1_)
    {
        if (p_70453_1_ > 0)
        {
            p_70453_1_ = 1;
        }

        if (p_70453_1_ < 0)
        {
            p_70453_1_ = -1;
        }

        for (this.activeItemPosition -= p_70453_1_; this.activeItemPosition < 0; this.activeItemPosition += 9)
        {
            ;
        }

        while (this.activeItemPosition >= 9)
        {
            this.activeItemPosition -= 9;
        }
    }

    @SideOnly(Side.CLIENT)
    public void func_70439_a(Item p_70439_1_, int p_70439_2_)
    {
        if (p_70439_1_ != null)
        {
            if (this.field_70456_f != null && this.field_70456_f.func_77956_u() && this.func_146024_c(this.field_70456_f.getBaseItem(), this.field_70456_f.func_77952_i()) == this.activeItemPosition)
            {
                return;
            }

            int j = this.func_146024_c(p_70439_1_, p_70439_2_);

            if (j >= 0)
            {
                int k = this.cells[j].count;
                this.cells[j] = this.cells[this.activeItemPosition];
                this.cells[this.activeItemPosition] = new ItemStack(p_70439_1_, k, p_70439_2_);
            }
            else
            {
                this.cells[this.activeItemPosition] = new ItemStack(p_70439_1_, 1, p_70439_2_);
            }
        }
    }

    private int func_70452_e(ItemStack p_70452_1_)
    {
        Item item = p_70452_1_.getBaseItem();
        int i = p_70452_1_.count;
        int j;

        if (p_70452_1_.func_77976_d() == 1)
        {
            j = this.func_70447_i();

            if (j < 0)
            {
                return i;
            }
            else
            {
                if (this.cells[j] == null)
                {
                    this.cells[j] = ItemStack.func_77944_b(p_70452_1_);
                }

                return 0;
            }
        }
        else
        {
            j = this.func_70432_d(p_70452_1_);

            if (j < 0)
            {
                j = this.func_70447_i();
            }

            if (j < 0)
            {
                return i;
            }
            else
            {
                if (this.cells[j] == null)
                {
                    this.cells[j] = new ItemStack(item, 0, p_70452_1_.func_77960_j());

                    if (p_70452_1_.func_77942_o())
                    {
                        this.cells[j].func_77982_d((NBTTagCompound)p_70452_1_.func_77978_p().func_74737_b());
                    }
                }

                int k = i;

                if (i > this.cells[j].func_77976_d() - this.cells[j].count)
                {
                    k = this.cells[j].func_77976_d() - this.cells[j].count;
                }

                if (k > this.func_70297_j_() - this.cells[j].count)
                {
                    k = this.func_70297_j_() - this.cells[j].count;
                }

                if (k == 0)
                {
                    return i;
                }
                else
                {
                    i -= k;
                    this.cells[j].count += k;
                    this.cells[j].field_77992_b = 5;
                    return i;
                }
            }
        }
    }

    public void func_70429_k()
    {
        for (int i = 0; i < this.cells.length; ++i)
        {
            if (this.cells[i] != null)
            {
                this.cells[i].func_77945_a(this.player.world, this.player, i, this.activeItemPosition == i);
            }
        }
    }

    public boolean func_146026_a(Item p_146026_1_)
    {
        int i = this.getItemPosition(p_146026_1_);

        if (i < 0)
        {
            return false;
        }
        else
        {
            if (--this.cells[i].count <= 0)
            {
                this.cells[i] = null;
            }

            return true;
        }
    }

    public boolean func_146028_b(Item p_146028_1_)
    {
        int i = this.getItemPosition(p_146028_1_);
        return i >= 0;
    }

    public boolean func_70441_a(final ItemStack p_70441_1_)
    {
        if (p_70441_1_ != null && p_70441_1_.count != 0 && p_70441_1_.getBaseItem() != null)
        {
            try
            {
                int i;

                if (p_70441_1_.func_77951_h())
                {
                    i = this.func_70447_i();

                    if (i >= 0)
                    {
                        this.cells[i] = ItemStack.func_77944_b(p_70441_1_);
                        this.cells[i].field_77992_b = 5;
                        p_70441_1_.count = 0;
                        return true;
                    }
                    else if (this.player.capabilities.instabuild)
                    {
                        p_70441_1_.count = 0;
                        return true;
                    }
                    else
                    {
                        return false;
                    }
                }
                else
                {
                    do
                    {
                        i = p_70441_1_.count;
                        p_70441_1_.count = this.func_70452_e(p_70441_1_);
                    }
                    while (p_70441_1_.count > 0 && p_70441_1_.count < i);

                    if (p_70441_1_.count == i && this.player.capabilities.instabuild)
                    {
                        p_70441_1_.count = 0;
                        return true;
                    }
                    else
                    {
                        return p_70441_1_.count < i;
                    }
                }
            }
            catch (Throwable throwable)
            {
                CrashReport crashreport = CrashReport.func_85055_a(throwable, "Adding item to inventory");
                CrashReportCategory crashreportcategory = crashreport.func_85058_a("Item being added");
                crashreportcategory.func_71507_a("Item ID", Integer.valueOf(Item.func_150891_b(p_70441_1_.getBaseItem())));
                crashreportcategory.func_71507_a("Item data", Integer.valueOf(p_70441_1_.func_77960_j()));
                crashreportcategory.func_71500_a("Item name", new Callable()
                {
                    private static final String __OBFID = "CL_00001710";
                    public String call()
                    {
                        return p_70441_1_.func_82833_r();
                    }
                });
                throw new ReportedException(crashreport);
            }
        }
        else
        {
            return false;
        }
    }

    public ItemStack func_70298_a(int p_70298_1_, int p_70298_2_)
    {
        ItemStack[] aitemstack = this.cells;

        if (p_70298_1_ >= this.cells.length)
        {
            aitemstack = this.field_70460_b;
            p_70298_1_ -= this.cells.length;
        }

        if (aitemstack[p_70298_1_] != null)
        {
            ItemStack itemstack;

            if (aitemstack[p_70298_1_].count <= p_70298_2_)
            {
                itemstack = aitemstack[p_70298_1_];
                aitemstack[p_70298_1_] = null;
                return itemstack;
            }
            else
            {
                itemstack = aitemstack[p_70298_1_].func_77979_a(p_70298_2_);

                if (aitemstack[p_70298_1_].count == 0)
                {
                    aitemstack[p_70298_1_] = null;
                }

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
        ItemStack[] aitemstack = this.cells;

        if (p_70304_1_ >= this.cells.length)
        {
            aitemstack = this.field_70460_b;
            p_70304_1_ -= this.cells.length;
        }

        if (aitemstack[p_70304_1_] != null)
        {
            ItemStack itemstack = aitemstack[p_70304_1_];
            aitemstack[p_70304_1_] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }

    public void putItem(int position, ItemStack item)
    {
        ItemStack[] inventoryCells = this.cells;

        if (position >= inventoryCells.length)
        {
            position -= inventoryCells.length;
            inventoryCells = this.field_70460_b;
        }

        inventoryCells[position] = item;
    }

    public float func_146023_a(Block p_146023_1_)
    {
        float f = 1.0F;

        if (this.cells[this.activeItemPosition] != null)
        {
            f *= this.cells[this.activeItemPosition].func_150997_a(p_146023_1_);
        }

        return f;
    }

    public NBTTagList func_70442_a(NBTTagList p_70442_1_)
    {
        int i;
        NBTTagCompound nbttagcompound;

        for (i = 0; i < this.cells.length; ++i)
        {
            if (this.cells[i] != null)
            {
                nbttagcompound = new NBTTagCompound();
                nbttagcompound.func_74774_a("Slot", (byte)i);
                this.cells[i].func_77955_b(nbttagcompound);
                p_70442_1_.func_74742_a(nbttagcompound);
            }
        }

        for (i = 0; i < this.field_70460_b.length; ++i)
        {
            if (this.field_70460_b[i] != null)
            {
                nbttagcompound = new NBTTagCompound();
                nbttagcompound.func_74774_a("Slot", (byte)(i + 100));
                this.field_70460_b[i].func_77955_b(nbttagcompound);
                p_70442_1_.func_74742_a(nbttagcompound);
            }
        }

        return p_70442_1_;
    }

    public void func_70443_b(NBTTagList p_70443_1_)
    {
        this.cells = new ItemStack[36];
        this.field_70460_b = new ItemStack[4];

        for (int i = 0; i < p_70443_1_.func_74745_c(); ++i)
        {
            NBTTagCompound nbttagcompound = p_70443_1_.func_150305_b(i);
            int j = nbttagcompound.func_74771_c("Slot") & 255;
            ItemStack itemstack = ItemStack.func_77949_a(nbttagcompound);

            if (itemstack != null)
            {
                if (j >= 0 && j < this.cells.length)
                {
                    this.cells[j] = itemstack;
                }

                if (j >= 100 && j < this.field_70460_b.length + 100)
                {
                    this.field_70460_b[j - 100] = itemstack;
                }
            }
        }
    }

    public int func_70302_i_()
    {
        return this.cells.length + 4;
    }

    public ItemStack func_70301_a(int p_70301_1_)
    {
        ItemStack[] aitemstack = this.cells;

        if (p_70301_1_ >= aitemstack.length)
        {
            p_70301_1_ -= aitemstack.length;
            aitemstack = this.field_70460_b;
        }

        return aitemstack[p_70301_1_];
    }

    public String func_145825_b()
    {
        return "container.inventory";
    }

    public boolean func_145818_k_()
    {
        return false;
    }

    public int func_70297_j_()
    {
        return 64;
    }

    public boolean func_146025_b(Block p_146025_1_)
    {
        if (p_146025_1_.func_149688_o().func_76229_l())
        {
            return true;
        }
        else
        {
            ItemStack itemstack = this.func_70301_a(this.activeItemPosition);
            return itemstack != null ? itemstack.func_150998_b(p_146025_1_) : false;
        }
    }

    public ItemStack func_70440_f(int p_70440_1_)
    {
        return this.field_70460_b[p_70440_1_];
    }

    public int func_70430_l()
    {
        int i = 0;

        for (int j = 0; j < this.field_70460_b.length; ++j)
        {
            if (this.field_70460_b[j] != null && this.field_70460_b[j].getBaseItem() instanceof ItemArmor)
            {
                int k = ((ItemArmor)this.field_70460_b[j].getBaseItem()).field_77879_b;
                i += k;
            }
        }

        return i;
    }

    public void func_70449_g(float p_70449_1_)
    {
        p_70449_1_ /= 4.0F;

        if (p_70449_1_ < 1.0F)
        {
            p_70449_1_ = 1.0F;
        }

        for (int i = 0; i < this.field_70460_b.length; ++i)
        {
            if (this.field_70460_b[i] != null && this.field_70460_b[i].getBaseItem() instanceof ItemArmor)
            {
                this.field_70460_b[i].func_77972_a((int)p_70449_1_, this.player);

                if (this.field_70460_b[i].count == 0)
                {
                    this.field_70460_b[i] = null;
                }
            }
        }
    }

    public void func_70436_m()
    {
        int i;

        for (i = 0; i < this.cells.length; ++i)
        {
            if (this.cells[i] != null)
            {
                this.player.func_146097_a(this.cells[i], true, false);
                this.cells[i] = null;
            }
        }

        for (i = 0; i < this.field_70460_b.length; ++i)
        {
            if (this.field_70460_b[i] != null)
            {
                this.player.func_146097_a(this.field_70460_b[i], true, false);
                this.field_70460_b[i] = null;
            }
        }
    }

    public void func_70296_d()
    {
        this.field_70459_e = true;
    }

    public void func_70437_b(ItemStack p_70437_1_)
    {
        this.field_70457_g = p_70437_1_;
    }

    public ItemStack func_70445_o()
    {
        return this.field_70457_g;
    }

    public boolean func_70300_a(EntityPlayer p_70300_1_)
    {
        return this.player.field_70128_L ? false : p_70300_1_.func_70068_e(this.player) <= 64.0D;
    }

    public boolean func_70431_c(ItemStack p_70431_1_)
    {
        int i;

        for (i = 0; i < this.field_70460_b.length; ++i)
        {
            if (this.field_70460_b[i] != null && this.field_70460_b[i].func_77969_a(p_70431_1_))
            {
                return true;
            }
        }

        for (i = 0; i < this.cells.length; ++i)
        {
            if (this.cells[i] != null && this.cells[i].func_77969_a(p_70431_1_))
            {
                return true;
            }
        }

        return false;
    }

    public void func_70295_k_() {}

    public void func_70305_f() {}

    public boolean func_94041_b(int p_94041_1_, ItemStack p_94041_2_)
    {
        return true;
    }

    public void func_70455_b(InventoryPlayer p_70455_1_)
    {
        int i;

        for (i = 0; i < this.cells.length; ++i)
        {
            this.cells[i] = ItemStack.func_77944_b(p_70455_1_.cells[i]);
        }

        for (i = 0; i < this.field_70460_b.length; ++i)
        {
            this.field_70460_b[i] = ItemStack.func_77944_b(p_70455_1_.field_70460_b[i]);
        }

        this.activeItemPosition = p_70455_1_.activeItemPosition;
    }
}