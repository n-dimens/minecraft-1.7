package net.minecraft.inventory;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ContainerEnchantment extends Container
{
    public IInventory field_75168_e = new InventoryBasic("Enchant", true, 1)
    {
        private static final String __OBFID = "CL_00001746";
        public int func_70297_j_()
        {
            return 1;
        }
        public void func_70296_d()
        {
            super.func_70296_d();
            ContainerEnchantment.this.func_75130_a(this);
        }
    };
    private World field_75172_h;
    private int field_75173_i;
    private int field_75170_j;
    private int field_75171_k;
    private Random field_75169_l = new Random();
    public long field_75166_f;
    public int[] field_75167_g = new int[3];
    private static final String __OBFID = "CL_00001745";

    public ContainerEnchantment(InventoryPlayer p_i1811_1_, World p_i1811_2_, int p_i1811_3_, int p_i1811_4_, int p_i1811_5_)
    {
        this.field_75172_h = p_i1811_2_;
        this.field_75173_i = p_i1811_3_;
        this.field_75170_j = p_i1811_4_;
        this.field_75171_k = p_i1811_5_;
        this.func_75146_a(new Slot(this.field_75168_e, 0, 25, 47)
        {
            private static final String __OBFID = "CL_00001747";
            public boolean func_75214_a(ItemStack p_75214_1_)
            {
                return true;
            }
        });
        int l;

        for (l = 0; l < 3; ++l)
        {
            for (int i1 = 0; i1 < 9; ++i1)
            {
                this.func_75146_a(new Slot(p_i1811_1_, i1 + l * 9 + 9, 8 + i1 * 18, 84 + l * 18));
            }
        }

        for (l = 0; l < 9; ++l)
        {
            this.func_75146_a(new Slot(p_i1811_1_, l, 8 + l * 18, 142));
        }
    }

    public void func_75132_a(ICrafting p_75132_1_)
    {
        super.func_75132_a(p_75132_1_);
        p_75132_1_.func_71112_a(this, 0, this.field_75167_g[0]);
        p_75132_1_.func_71112_a(this, 1, this.field_75167_g[1]);
        p_75132_1_.func_71112_a(this, 2, this.field_75167_g[2]);
    }

    public void func_75142_b()
    {
        super.func_75142_b();

        for (int i = 0; i < this.field_75149_d.size(); ++i)
        {
            ICrafting icrafting = (ICrafting)this.field_75149_d.get(i);
            icrafting.func_71112_a(this, 0, this.field_75167_g[0]);
            icrafting.func_71112_a(this, 1, this.field_75167_g[1]);
            icrafting.func_71112_a(this, 2, this.field_75167_g[2]);
        }
    }

    @SideOnly(Side.CLIENT)
    public void func_75137_b(int p_75137_1_, int p_75137_2_)
    {
        if (p_75137_1_ >= 0 && p_75137_1_ <= 2)
        {
            this.field_75167_g[p_75137_1_] = p_75137_2_;
        }
        else
        {
            super.func_75137_b(p_75137_1_, p_75137_2_);
        }
    }

    public void func_75130_a(IInventory p_75130_1_)
    {
        if (p_75130_1_ == this.field_75168_e)
        {
            ItemStack itemstack = p_75130_1_.func_70301_a(0);
            int i;

            if (itemstack != null && itemstack.func_77956_u())
            {
                this.field_75166_f = this.field_75169_l.nextLong();

                if (!this.field_75172_h.field_72995_K)
                {
                    i = 0;
                    int j;

                    for (j = -1; j <= 1; ++j)
                    {
                        for (int k = -1; k <= 1; ++k)
                        {
                            if ((j != 0 || k != 0) && this.field_75172_h.func_147437_c(this.field_75173_i + k, this.field_75170_j, this.field_75171_k + j) && this.field_75172_h.func_147437_c(this.field_75173_i + k, this.field_75170_j + 1, this.field_75171_k + j))
                            {
                                if (this.field_75172_h.func_147439_a(this.field_75173_i + k * 2, this.field_75170_j, this.field_75171_k + j * 2) == Blocks.BOOKSHELF)
                                {
                                    ++i;
                                }

                                if (this.field_75172_h.func_147439_a(this.field_75173_i + k * 2, this.field_75170_j + 1, this.field_75171_k + j * 2) == Blocks.BOOKSHELF)
                                {
                                    ++i;
                                }

                                if (k != 0 && j != 0)
                                {
                                    if (this.field_75172_h.func_147439_a(this.field_75173_i + k * 2, this.field_75170_j, this.field_75171_k + j) == Blocks.BOOKSHELF)
                                    {
                                        ++i;
                                    }

                                    if (this.field_75172_h.func_147439_a(this.field_75173_i + k * 2, this.field_75170_j + 1, this.field_75171_k + j) == Blocks.BOOKSHELF)
                                    {
                                        ++i;
                                    }

                                    if (this.field_75172_h.func_147439_a(this.field_75173_i + k, this.field_75170_j, this.field_75171_k + j * 2) == Blocks.BOOKSHELF)
                                    {
                                        ++i;
                                    }

                                    if (this.field_75172_h.func_147439_a(this.field_75173_i + k, this.field_75170_j + 1, this.field_75171_k + j * 2) == Blocks.BOOKSHELF)
                                    {
                                        ++i;
                                    }
                                }
                            }
                        }
                    }

                    for (j = 0; j < 3; ++j)
                    {
                        this.field_75167_g[j] = EnchantmentHelper.func_77514_a(this.field_75169_l, j, i, itemstack);
                    }

                    this.func_75142_b();
                }
            }
            else
            {
                for (i = 0; i < 3; ++i)
                {
                    this.field_75167_g[i] = 0;
                }
            }
        }
    }

    public boolean func_75140_a(EntityPlayer p_75140_1_, int p_75140_2_)
    {
        ItemStack itemstack = this.field_75168_e.func_70301_a(0);

        if (this.field_75167_g[p_75140_2_] > 0 && itemstack != null && (p_75140_1_.field_71068_ca >= this.field_75167_g[p_75140_2_] || p_75140_1_.capabilities.instabuild))
        {
            if (!this.field_75172_h.field_72995_K)
            {
                List list = EnchantmentHelper.func_77513_b(this.field_75169_l, itemstack, this.field_75167_g[p_75140_2_]);
                boolean flag = itemstack.getBaseItem() == Items.BOOK;

                if (list != null)
                {
                    p_75140_1_.func_82242_a(-this.field_75167_g[p_75140_2_]);

                    if (flag)
                    {
                        itemstack.func_150996_a(Items.ENCHANTED_BOOK);
                    }

                    int j = flag && list.size() > 1 ? this.field_75169_l.nextInt(list.size()) : -1;

                    for (int k = 0; k < list.size(); ++k)
                    {
                        EnchantmentData enchantmentdata = (EnchantmentData)list.get(k);

                        if (!flag || k != j)
                        {
                            if (flag)
                            {
                                Items.ENCHANTED_BOOK.func_92115_a(itemstack, enchantmentdata);
                            }
                            else
                            {
                                itemstack.func_77966_a(enchantmentdata.field_76302_b, enchantmentdata.field_76303_c);
                            }
                        }
                    }

                    this.func_75130_a(this.field_75168_e);
                }
            }

            return true;
        }
        else
        {
            return false;
        }
    }

    public void func_75134_a(EntityPlayer p_75134_1_)
    {
        super.func_75134_a(p_75134_1_);

        if (!this.field_75172_h.field_72995_K)
        {
            ItemStack itemstack = this.field_75168_e.func_70304_b(0);

            if (itemstack != null)
            {
                p_75134_1_.func_71019_a(itemstack, false);
            }
        }
    }

    public boolean func_75145_c(EntityPlayer player)
    {
        return this.field_75172_h.func_147439_a(this.field_75173_i, this.field_75170_j, this.field_75171_k) != Blocks.ENCHANTING_TABLE ? false : player.func_70092_e((double)this.field_75173_i + 0.5D, (double)this.field_75170_j + 0.5D, (double)this.field_75171_k + 0.5D) <= 64.0D;
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
                if (!this.func_75135_a(itemstack1, 1, 37, true))
                {
                    return null;
                }
            }
            else
            {
                if (((Slot)this.field_75151_b.get(0)).func_75216_d() || !((Slot)this.field_75151_b.get(0)).func_75214_a(itemstack1))
                {
                    return null;
                }

                if (itemstack1.func_77942_o() && itemstack1.count == 1)
                {
                    ((Slot)this.field_75151_b.get(0)).func_75215_d(itemstack1.func_77946_l());
                    itemstack1.count = 0;
                }
                else if (itemstack1.count >= 1)
                {
                    ((Slot)this.field_75151_b.get(0)).func_75215_d(new ItemStack(itemstack1.getBaseItem(), 1, itemstack1.func_77960_j()));
                    --itemstack1.count;
                }
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
}