package net.minecraft.tileentity;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockHopper;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Facing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class TileEntityHopper extends TileEntity implements IHopper
{
    private ItemStack[] field_145900_a = new ItemStack[5];
    private String field_145902_i;
    private int field_145901_j = -1;
    private static final String __OBFID = "CL_00000359";

    public void func_145839_a(NBTTagCompound p_145839_1_)
    {
        super.func_145839_a(p_145839_1_);
        NBTTagList nbttaglist = p_145839_1_.func_150295_c("Items", 10);
        this.field_145900_a = new ItemStack[this.func_70302_i_()];

        if (p_145839_1_.func_150297_b("CustomName", 8))
        {
            this.field_145902_i = p_145839_1_.func_74779_i("CustomName");
        }

        this.field_145901_j = p_145839_1_.func_74762_e("TransferCooldown");

        for (int i = 0; i < nbttaglist.func_74745_c(); ++i)
        {
            NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
            byte b0 = nbttagcompound1.func_74771_c("Slot");

            if (b0 >= 0 && b0 < this.field_145900_a.length)
            {
                this.field_145900_a[b0] = ItemStack.func_77949_a(nbttagcompound1);
            }
        }
    }

    public void func_145841_b(NBTTagCompound p_145841_1_)
    {
        super.func_145841_b(p_145841_1_);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.field_145900_a.length; ++i)
        {
            if (this.field_145900_a[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.func_74774_a("Slot", (byte)i);
                this.field_145900_a[i].func_77955_b(nbttagcompound1);
                nbttaglist.func_74742_a(nbttagcompound1);
            }
        }

        p_145841_1_.func_74782_a("Items", nbttaglist);
        p_145841_1_.func_74768_a("TransferCooldown", this.field_145901_j);

        if (this.func_145818_k_())
        {
            p_145841_1_.func_74778_a("CustomName", this.field_145902_i);
        }
    }

    public void func_70296_d()
    {
        super.func_70296_d();
    }

    public int func_70302_i_()
    {
        return this.field_145900_a.length;
    }

    public ItemStack func_70301_a(int p_70301_1_)
    {
        return this.field_145900_a[p_70301_1_];
    }

    public ItemStack func_70298_a(int p_70298_1_, int p_70298_2_)
    {
        if (this.field_145900_a[p_70298_1_] != null)
        {
            ItemStack itemstack;

            if (this.field_145900_a[p_70298_1_].field_77994_a <= p_70298_2_)
            {
                itemstack = this.field_145900_a[p_70298_1_];
                this.field_145900_a[p_70298_1_] = null;
                return itemstack;
            }
            else
            {
                itemstack = this.field_145900_a[p_70298_1_].func_77979_a(p_70298_2_);

                if (this.field_145900_a[p_70298_1_].field_77994_a == 0)
                {
                    this.field_145900_a[p_70298_1_] = null;
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
        if (this.field_145900_a[p_70304_1_] != null)
        {
            ItemStack itemstack = this.field_145900_a[p_70304_1_];
            this.field_145900_a[p_70304_1_] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }

    public void func_70299_a(int p_70299_1_, ItemStack p_70299_2_)
    {
        this.field_145900_a[p_70299_1_] = p_70299_2_;

        if (p_70299_2_ != null && p_70299_2_.field_77994_a > this.func_70297_j_())
        {
            p_70299_2_.field_77994_a = this.func_70297_j_();
        }
    }

    public String func_145825_b()
    {
        return this.func_145818_k_() ? this.field_145902_i : "container.hopper";
    }

    public boolean func_145818_k_()
    {
        return this.field_145902_i != null && this.field_145902_i.length() > 0;
    }

    public void func_145886_a(String p_145886_1_)
    {
        this.field_145902_i = p_145886_1_;
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

    public void func_145845_h()
    {
        if (this.field_145850_b != null && !this.field_145850_b.field_72995_K)
        {
            --this.field_145901_j;

            if (!this.func_145888_j())
            {
                this.func_145896_c(0);
                this.func_145887_i();
            }
        }
    }

    public boolean func_145887_i()
    {
        if (this.field_145850_b != null && !this.field_145850_b.field_72995_K)
        {
            if (!this.func_145888_j() && BlockHopper.func_149917_c(this.func_145832_p()))
            {
                boolean flag = false;

                if (!this.func_152104_k())
                {
                    flag = this.func_145883_k();
                }

                if (!this.func_152105_l())
                {
                    flag = func_145891_a(this) || flag;
                }

                if (flag)
                {
                    this.func_145896_c(8);
                    this.func_70296_d();
                    return true;
                }
            }

            return false;
        }
        else
        {
            return false;
        }
    }

    private boolean func_152104_k()
    {
        ItemStack[] aitemstack = this.field_145900_a;
        int i = aitemstack.length;

        for (int j = 0; j < i; ++j)
        {
            ItemStack itemstack = aitemstack[j];

            if (itemstack != null)
            {
                return false;
            }
        }

        return true;
    }

    private boolean func_152105_l()
    {
        ItemStack[] aitemstack = this.field_145900_a;
        int i = aitemstack.length;

        for (int j = 0; j < i; ++j)
        {
            ItemStack itemstack = aitemstack[j];

            if (itemstack == null || itemstack.field_77994_a != itemstack.func_77976_d())
            {
                return false;
            }
        }

        return true;
    }

    private boolean func_145883_k()
    {
        IInventory iinventory = this.func_145895_l();

        if (iinventory == null)
        {
            return false;
        }
        else
        {
            int i = Facing.field_71588_a[BlockHopper.func_149918_b(this.func_145832_p())];

            if (this.func_152102_a(iinventory, i))
            {
                return false;
            }
            else
            {
                for (int j = 0; j < this.func_70302_i_(); ++j)
                {
                    if (this.func_70301_a(j) != null)
                    {
                        ItemStack itemstack = this.func_70301_a(j).func_77946_l();
                        ItemStack itemstack1 = func_145889_a(iinventory, this.func_70298_a(j, 1), i);

                        if (itemstack1 == null || itemstack1.field_77994_a == 0)
                        {
                            iinventory.func_70296_d();
                            return true;
                        }

                        this.func_70299_a(j, itemstack);
                    }
                }

                return false;
            }
        }
    }

    private boolean func_152102_a(IInventory p_152102_1_, int p_152102_2_)
    {
        if (p_152102_1_ instanceof ISidedInventory && p_152102_2_ > -1)
        {
            ISidedInventory isidedinventory = (ISidedInventory)p_152102_1_;
            int[] aint = isidedinventory.func_94128_d(p_152102_2_);

            for (int l = 0; l < aint.length; ++l)
            {
                ItemStack itemstack1 = isidedinventory.func_70301_a(aint[l]);

                if (itemstack1 == null || itemstack1.field_77994_a != itemstack1.func_77976_d())
                {
                    return false;
                }
            }
        }
        else
        {
            int j = p_152102_1_.func_70302_i_();

            for (int k = 0; k < j; ++k)
            {
                ItemStack itemstack = p_152102_1_.func_70301_a(k);

                if (itemstack == null || itemstack.field_77994_a != itemstack.func_77976_d())
                {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean func_152103_b(IInventory p_152103_0_, int p_152103_1_)
    {
        if (p_152103_0_ instanceof ISidedInventory && p_152103_1_ > -1)
        {
            ISidedInventory isidedinventory = (ISidedInventory)p_152103_0_;
            int[] aint = isidedinventory.func_94128_d(p_152103_1_);

            for (int l = 0; l < aint.length; ++l)
            {
                if (isidedinventory.func_70301_a(aint[l]) != null)
                {
                    return false;
                }
            }
        }
        else
        {
            int j = p_152103_0_.func_70302_i_();

            for (int k = 0; k < j; ++k)
            {
                if (p_152103_0_.func_70301_a(k) != null)
                {
                    return false;
                }
            }
        }

        return true;
    }

    public static boolean func_145891_a(IHopper p_145891_0_)
    {
        IInventory iinventory = func_145884_b(p_145891_0_);

        if (iinventory != null)
        {
            byte b0 = 0;

            if (func_152103_b(iinventory, b0))
            {
                return false;
            }

            if (iinventory instanceof ISidedInventory && b0 > -1)
            {
                ISidedInventory isidedinventory = (ISidedInventory)iinventory;
                int[] aint = isidedinventory.func_94128_d(b0);

                for (int k = 0; k < aint.length; ++k)
                {
                    if (func_145892_a(p_145891_0_, iinventory, aint[k], b0))
                    {
                        return true;
                    }
                }
            }
            else
            {
                int i = iinventory.func_70302_i_();

                for (int j = 0; j < i; ++j)
                {
                    if (func_145892_a(p_145891_0_, iinventory, j, b0))
                    {
                        return true;
                    }
                }
            }
        }
        else
        {
            EntityItem entityitem = func_145897_a(p_145891_0_.func_145831_w(), p_145891_0_.func_96107_aA(), p_145891_0_.func_96109_aB() + 1.0D, p_145891_0_.func_96108_aC());

            if (entityitem != null)
            {
                return func_145898_a(p_145891_0_, entityitem);
            }
        }

        return false;
    }

    private static boolean func_145892_a(IHopper p_145892_0_, IInventory p_145892_1_, int p_145892_2_, int p_145892_3_)
    {
        ItemStack itemstack = p_145892_1_.func_70301_a(p_145892_2_);

        if (itemstack != null && func_145890_b(p_145892_1_, itemstack, p_145892_2_, p_145892_3_))
        {
            ItemStack itemstack1 = itemstack.func_77946_l();
            ItemStack itemstack2 = func_145889_a(p_145892_0_, p_145892_1_.func_70298_a(p_145892_2_, 1), -1);

            if (itemstack2 == null || itemstack2.field_77994_a == 0)
            {
                p_145892_1_.func_70296_d();
                return true;
            }

            p_145892_1_.func_70299_a(p_145892_2_, itemstack1);
        }

        return false;
    }

    public static boolean func_145898_a(IInventory p_145898_0_, EntityItem p_145898_1_)
    {
        boolean flag = false;

        if (p_145898_1_ == null)
        {
            return false;
        }
        else
        {
            ItemStack itemstack = p_145898_1_.func_92059_d().func_77946_l();
            ItemStack itemstack1 = func_145889_a(p_145898_0_, itemstack, -1);

            if (itemstack1 != null && itemstack1.field_77994_a != 0)
            {
                p_145898_1_.func_92058_a(itemstack1);
            }
            else
            {
                flag = true;
                p_145898_1_.func_70106_y();
            }

            return flag;
        }
    }

    public static ItemStack func_145889_a(IInventory p_145889_0_, ItemStack p_145889_1_, int p_145889_2_)
    {
        if (p_145889_0_ instanceof ISidedInventory && p_145889_2_ > -1)
        {
            ISidedInventory isidedinventory = (ISidedInventory)p_145889_0_;
            int[] aint = isidedinventory.func_94128_d(p_145889_2_);

            for (int l = 0; l < aint.length && p_145889_1_ != null && p_145889_1_.field_77994_a > 0; ++l)
            {
                p_145889_1_ = func_145899_c(p_145889_0_, p_145889_1_, aint[l], p_145889_2_);
            }
        }
        else
        {
            int j = p_145889_0_.func_70302_i_();

            for (int k = 0; k < j && p_145889_1_ != null && p_145889_1_.field_77994_a > 0; ++k)
            {
                p_145889_1_ = func_145899_c(p_145889_0_, p_145889_1_, k, p_145889_2_);
            }
        }

        if (p_145889_1_ != null && p_145889_1_.field_77994_a == 0)
        {
            p_145889_1_ = null;
        }

        return p_145889_1_;
    }

    private static boolean func_145885_a(IInventory p_145885_0_, ItemStack p_145885_1_, int p_145885_2_, int p_145885_3_)
    {
        return !p_145885_0_.func_94041_b(p_145885_2_, p_145885_1_) ? false : !(p_145885_0_ instanceof ISidedInventory) || ((ISidedInventory)p_145885_0_).func_102007_a(p_145885_2_, p_145885_1_, p_145885_3_);
    }

    private static boolean func_145890_b(IInventory p_145890_0_, ItemStack p_145890_1_, int p_145890_2_, int p_145890_3_)
    {
        return !(p_145890_0_ instanceof ISidedInventory) || ((ISidedInventory)p_145890_0_).func_102008_b(p_145890_2_, p_145890_1_, p_145890_3_);
    }

    private static ItemStack func_145899_c(IInventory p_145899_0_, ItemStack p_145899_1_, int p_145899_2_, int p_145899_3_)
    {
        ItemStack itemstack1 = p_145899_0_.func_70301_a(p_145899_2_);

        if (func_145885_a(p_145899_0_, p_145899_1_, p_145899_2_, p_145899_3_))
        {
            boolean flag = false;

            if (itemstack1 == null)
            {
                p_145899_0_.func_70299_a(p_145899_2_, p_145899_1_);
                p_145899_1_ = null;
                flag = true;
            }
            else if (func_145894_a(itemstack1, p_145899_1_))
            {
                int k = p_145899_1_.func_77976_d() - itemstack1.field_77994_a;
                int l = Math.min(p_145899_1_.field_77994_a, k);
                p_145899_1_.field_77994_a -= l;
                itemstack1.field_77994_a += l;
                flag = l > 0;
            }

            if (flag)
            {
                if (p_145899_0_ instanceof TileEntityHopper)
                {
                    ((TileEntityHopper)p_145899_0_).func_145896_c(8);
                    p_145899_0_.func_70296_d();
                }

                p_145899_0_.func_70296_d();
            }
        }

        return p_145899_1_;
    }

    private IInventory func_145895_l()
    {
        int i = BlockHopper.func_149918_b(this.func_145832_p());
        return func_145893_b(this.func_145831_w(), (double)(this.field_145851_c + Facing.field_71586_b[i]), (double)(this.field_145848_d + Facing.field_71587_c[i]), (double)(this.field_145849_e + Facing.field_71585_d[i]));
    }

    public static IInventory func_145884_b(IHopper p_145884_0_)
    {
        return func_145893_b(p_145884_0_.func_145831_w(), p_145884_0_.func_96107_aA(), p_145884_0_.func_96109_aB() + 1.0D, p_145884_0_.func_96108_aC());
    }

    public static EntityItem func_145897_a(World p_145897_0_, double p_145897_1_, double p_145897_3_, double p_145897_5_)
    {
        List list = p_145897_0_.func_82733_a(EntityItem.class, AxisAlignedBB.func_72330_a(p_145897_1_, p_145897_3_, p_145897_5_, p_145897_1_ + 1.0D, p_145897_3_ + 1.0D, p_145897_5_ + 1.0D), IEntitySelector.field_94557_a);
        return list.size() > 0 ? (EntityItem)list.get(0) : null;
    }

    public static IInventory func_145893_b(World p_145893_0_, double p_145893_1_, double p_145893_3_, double p_145893_5_)
    {
        IInventory iinventory = null;
        int i = MathHelper.func_76128_c(p_145893_1_);
        int j = MathHelper.func_76128_c(p_145893_3_);
        int k = MathHelper.func_76128_c(p_145893_5_);
        TileEntity tileentity = p_145893_0_.func_147438_o(i, j, k);

        if (tileentity != null && tileentity instanceof IInventory)
        {
            iinventory = (IInventory)tileentity;

            if (iinventory instanceof TileEntityChest)
            {
                Block block = p_145893_0_.func_147439_a(i, j, k);

                if (block instanceof BlockChest)
                {
                    iinventory = ((BlockChest)block).func_149951_m(p_145893_0_, i, j, k);
                }
            }
        }

        if (iinventory == null)
        {
            List list = p_145893_0_.func_94576_a((Entity)null, AxisAlignedBB.func_72330_a(p_145893_1_, p_145893_3_, p_145893_5_, p_145893_1_ + 1.0D, p_145893_3_ + 1.0D, p_145893_5_ + 1.0D), IEntitySelector.field_96566_b);

            if (list != null && list.size() > 0)
            {
                iinventory = (IInventory)list.get(p_145893_0_.field_73012_v.nextInt(list.size()));
            }
        }

        return iinventory;
    }

    private static boolean func_145894_a(ItemStack p_145894_0_, ItemStack p_145894_1_)
    {
        return p_145894_0_.func_77973_b() != p_145894_1_.func_77973_b() ? false : (p_145894_0_.func_77960_j() != p_145894_1_.func_77960_j() ? false : (p_145894_0_.field_77994_a > p_145894_0_.func_77976_d() ? false : ItemStack.func_77970_a(p_145894_0_, p_145894_1_)));
    }

    public double func_96107_aA()
    {
        return (double)this.field_145851_c;
    }

    public double func_96109_aB()
    {
        return (double)this.field_145848_d;
    }

    public double func_96108_aC()
    {
        return (double)this.field_145849_e;
    }

    public void func_145896_c(int p_145896_1_)
    {
        this.field_145901_j = p_145896_1_;
    }

    public boolean func_145888_j()
    {
        return this.field_145901_j > 0;
    }
}