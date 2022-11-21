package net.minecraft.inventory;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Iterator;
import java.util.Map;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ContainerRepair extends Container
{
    private static final Logger field_148326_f = LogManager.getLogger();
    private IInventory field_82852_f = new InventoryCraftResult();
    private IInventory field_82853_g = new InventoryBasic("Repair", true, 2)
    {
        private static final String __OBFID = "CL_00001733";
        public void func_70296_d()
        {
            super.func_70296_d();
            ContainerRepair.this.func_75130_a(this);
        }
    };
    private World field_82860_h;
    private int field_82861_i;
    private int field_82858_j;
    private int field_82859_k;
    public int field_82854_e;
    public int field_82856_l;
    private String field_82857_m;
    private final EntityPlayer field_82855_n;
    private static final String __OBFID = "CL_00001732";

    public ContainerRepair(InventoryPlayer p_i1800_1_, final World p_i1800_2_, final int p_i1800_3_, final int p_i1800_4_, final int p_i1800_5_, EntityPlayer p_i1800_6_)
    {
        this.field_82860_h = p_i1800_2_;
        this.field_82861_i = p_i1800_3_;
        this.field_82858_j = p_i1800_4_;
        this.field_82859_k = p_i1800_5_;
        this.field_82855_n = p_i1800_6_;
        this.func_75146_a(new Slot(this.field_82853_g, 0, 27, 47));
        this.func_75146_a(new Slot(this.field_82853_g, 1, 76, 47));
        this.func_75146_a(new Slot(this.field_82852_f, 2, 134, 47)
        {
            private static final String __OBFID = "CL_00001734";
            public boolean func_75214_a(ItemStack p_75214_1_)
            {
                return false;
            }
            public boolean func_82869_a(EntityPlayer p_82869_1_)
            {
                return (p_82869_1_.field_71075_bZ.field_75098_d || p_82869_1_.field_71068_ca >= ContainerRepair.this.field_82854_e) && ContainerRepair.this.field_82854_e > 0 && this.func_75216_d();
            }
            public void func_82870_a(EntityPlayer p_82870_1_, ItemStack p_82870_2_)
            {
                if (!p_82870_1_.field_71075_bZ.field_75098_d)
                {
                    p_82870_1_.func_82242_a(-ContainerRepair.this.field_82854_e);
                }

                ContainerRepair.this.field_82853_g.func_70299_a(0, (ItemStack)null);

                if (ContainerRepair.this.field_82856_l > 0)
                {
                    ItemStack itemstack1 = ContainerRepair.this.field_82853_g.func_70301_a(1);

                    if (itemstack1 != null && itemstack1.field_77994_a > ContainerRepair.this.field_82856_l)
                    {
                        itemstack1.field_77994_a -= ContainerRepair.this.field_82856_l;
                        ContainerRepair.this.field_82853_g.func_70299_a(1, itemstack1);
                    }
                    else
                    {
                        ContainerRepair.this.field_82853_g.func_70299_a(1, (ItemStack)null);
                    }
                }
                else
                {
                    ContainerRepair.this.field_82853_g.func_70299_a(1, (ItemStack)null);
                }

                ContainerRepair.this.field_82854_e = 0;

                if (!p_82870_1_.field_71075_bZ.field_75098_d && !p_i1800_2_.field_72995_K && p_i1800_2_.func_147439_a(p_i1800_3_, p_i1800_4_, p_i1800_5_) == Blocks.ANVIL && p_82870_1_.func_70681_au().nextFloat() < 0.12F)
                {
                    int i1 = p_i1800_2_.func_72805_g(p_i1800_3_, p_i1800_4_, p_i1800_5_);
                    int k = i1 & 3;
                    int l = i1 >> 2;
                    ++l;

                    if (l > 2)
                    {
                        p_i1800_2_.func_147468_f(p_i1800_3_, p_i1800_4_, p_i1800_5_);
                        p_i1800_2_.func_72926_e(1020, p_i1800_3_, p_i1800_4_, p_i1800_5_, 0);
                    }
                    else
                    {
                        p_i1800_2_.func_72921_c(p_i1800_3_, p_i1800_4_, p_i1800_5_, k | l << 2, 2);
                        p_i1800_2_.func_72926_e(1021, p_i1800_3_, p_i1800_4_, p_i1800_5_, 0);
                    }
                }
                else if (!p_i1800_2_.field_72995_K)
                {
                    p_i1800_2_.func_72926_e(1021, p_i1800_3_, p_i1800_4_, p_i1800_5_, 0);
                }
            }
        });
        int i;

        for (i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.func_75146_a(new Slot(p_i1800_1_, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (i = 0; i < 9; ++i)
        {
            this.func_75146_a(new Slot(p_i1800_1_, i, 8 + i * 18, 142));
        }
    }

    public void func_75130_a(IInventory p_75130_1_)
    {
        super.func_75130_a(p_75130_1_);

        if (p_75130_1_ == this.field_82853_g)
        {
            this.func_82848_d();
        }
    }

    public void func_82848_d()
    {
        ItemStack itemstack = this.field_82853_g.func_70301_a(0);
        this.field_82854_e = 0;
        int i = 0;
        byte b0 = 0;
        int j = 0;

        if (itemstack == null)
        {
            this.field_82852_f.func_70299_a(0, (ItemStack)null);
            this.field_82854_e = 0;
        }
        else
        {
            ItemStack itemstack1 = itemstack.func_77946_l();
            ItemStack itemstack2 = this.field_82853_g.func_70301_a(1);
            Map map = EnchantmentHelper.func_82781_a(itemstack1);
            boolean flag = false;
            int k2 = b0 + itemstack.func_82838_A() + (itemstack2 == null ? 0 : itemstack2.func_82838_A());
            this.field_82856_l = 0;
            int k;
            int l;
            int i1;
            int k1;
            int l1;
            Iterator iterator1;
            Enchantment enchantment;

            if (itemstack2 != null)
            {
                flag = itemstack2.func_77973_b() == Items.ENCHANTED_BOOK && Items.ENCHANTED_BOOK.func_92110_g(itemstack2).func_74745_c() > 0;

                if (itemstack1.func_77984_f() && itemstack1.func_77973_b().func_82789_a(itemstack, itemstack2))
                {
                    k = Math.min(itemstack1.func_77952_i(), itemstack1.func_77958_k() / 4);

                    if (k <= 0)
                    {
                        this.field_82852_f.func_70299_a(0, (ItemStack)null);
                        this.field_82854_e = 0;
                        return;
                    }

                    for (l = 0; k > 0 && l < itemstack2.field_77994_a; ++l)
                    {
                        i1 = itemstack1.func_77952_i() - k;
                        itemstack1.func_77964_b(i1);
                        i += Math.max(1, k / 100) + map.size();
                        k = Math.min(itemstack1.func_77952_i(), itemstack1.func_77958_k() / 4);
                    }

                    this.field_82856_l = l;
                }
                else
                {
                    if (!flag && (itemstack1.func_77973_b() != itemstack2.func_77973_b() || !itemstack1.func_77984_f()))
                    {
                        this.field_82852_f.func_70299_a(0, (ItemStack)null);
                        this.field_82854_e = 0;
                        return;
                    }

                    if (itemstack1.func_77984_f() && !flag)
                    {
                        k = itemstack.func_77958_k() - itemstack.func_77952_i();
                        l = itemstack2.func_77958_k() - itemstack2.func_77952_i();
                        i1 = l + itemstack1.func_77958_k() * 12 / 100;
                        int j1 = k + i1;
                        k1 = itemstack1.func_77958_k() - j1;

                        if (k1 < 0)
                        {
                            k1 = 0;
                        }

                        if (k1 < itemstack1.func_77960_j())
                        {
                            itemstack1.func_77964_b(k1);
                            i += Math.max(1, i1 / 100);
                        }
                    }

                    Map map1 = EnchantmentHelper.func_82781_a(itemstack2);
                    iterator1 = map1.keySet().iterator();

                    while (iterator1.hasNext())
                    {
                        i1 = ((Integer)iterator1.next()).intValue();
                        enchantment = Enchantment.field_77331_b[i1];
                        k1 = map.containsKey(Integer.valueOf(i1)) ? ((Integer)map.get(Integer.valueOf(i1))).intValue() : 0;
                        l1 = ((Integer)map1.get(Integer.valueOf(i1))).intValue();
                        int i3;

                        if (k1 == l1)
                        {
                            ++l1;
                            i3 = l1;
                        }
                        else
                        {
                            i3 = Math.max(l1, k1);
                        }

                        l1 = i3;
                        int i2 = l1 - k1;
                        boolean flag1 = enchantment.func_92089_a(itemstack);

                        if (this.field_82855_n.field_71075_bZ.field_75098_d || itemstack.func_77973_b() == Items.ENCHANTED_BOOK)
                        {
                            flag1 = true;
                        }

                        Iterator iterator = map.keySet().iterator();

                        while (iterator.hasNext())
                        {
                            int j2 = ((Integer)iterator.next()).intValue();

                            if (j2 != i1 && !enchantment.func_77326_a(Enchantment.field_77331_b[j2]))
                            {
                                flag1 = false;
                                i += i2;
                            }
                        }

                        if (flag1)
                        {
                            if (l1 > enchantment.func_77325_b())
                            {
                                l1 = enchantment.func_77325_b();
                            }

                            map.put(Integer.valueOf(i1), Integer.valueOf(l1));
                            int l2 = 0;

                            switch (enchantment.func_77324_c())
                            {
                                case 1:
                                    l2 = 8;
                                    break;
                                case 2:
                                    l2 = 4;
                                case 3:
                                case 4:
                                case 6:
                                case 7:
                                case 8:
                                case 9:
                                default:
                                    break;
                                case 5:
                                    l2 = 2;
                                    break;
                                case 10:
                                    l2 = 1;
                            }

                            if (flag)
                            {
                                l2 = Math.max(1, l2 / 2);
                            }

                            i += l2 * i2;
                        }
                    }
                }
            }

            if (StringUtils.isBlank(this.field_82857_m))
            {
                if (itemstack.func_82837_s())
                {
                    j = itemstack.func_77984_f() ? 7 : itemstack.field_77994_a * 5;
                    i += j;
                    itemstack1.func_135074_t();
                }
            }
            else if (!this.field_82857_m.equals(itemstack.func_82833_r()))
            {
                j = itemstack.func_77984_f() ? 7 : itemstack.field_77994_a * 5;
                i += j;

                if (itemstack.func_82837_s())
                {
                    k2 += j / 2;
                }

                itemstack1.func_151001_c(this.field_82857_m);
            }

            k = 0;

            for (iterator1 = map.keySet().iterator(); iterator1.hasNext(); k2 += k + k1 * l1)
            {
                i1 = ((Integer)iterator1.next()).intValue();
                enchantment = Enchantment.field_77331_b[i1];
                k1 = ((Integer)map.get(Integer.valueOf(i1))).intValue();
                l1 = 0;
                ++k;

                switch (enchantment.func_77324_c())
                {
                    case 1:
                        l1 = 8;
                        break;
                    case 2:
                        l1 = 4;
                    case 3:
                    case 4:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                    default:
                        break;
                    case 5:
                        l1 = 2;
                        break;
                    case 10:
                        l1 = 1;
                }

                if (flag)
                {
                    l1 = Math.max(1, l1 / 2);
                }
            }

            if (flag)
            {
                k2 = Math.max(1, k2 / 2);
            }

            this.field_82854_e = k2 + i;

            if (i <= 0)
            {
                itemstack1 = null;
            }

            if (j == i && j > 0 && this.field_82854_e >= 40)
            {
                this.field_82854_e = 39;
            }

            if (this.field_82854_e >= 40 && !this.field_82855_n.field_71075_bZ.field_75098_d)
            {
                itemstack1 = null;
            }

            if (itemstack1 != null)
            {
                l = itemstack1.func_82838_A();

                if (itemstack2 != null && l < itemstack2.func_82838_A())
                {
                    l = itemstack2.func_82838_A();
                }

                if (itemstack1.func_82837_s())
                {
                    l -= 9;
                }

                if (l < 0)
                {
                    l = 0;
                }

                l += 2;
                itemstack1.func_82841_c(l);
                EnchantmentHelper.func_82782_a(map, itemstack1);
            }

            this.field_82852_f.func_70299_a(0, itemstack1);
            this.func_75142_b();
        }
    }

    public void func_75132_a(ICrafting p_75132_1_)
    {
        super.func_75132_a(p_75132_1_);
        p_75132_1_.func_71112_a(this, 0, this.field_82854_e);
    }

    @SideOnly(Side.CLIENT)
    public void func_75137_b(int p_75137_1_, int p_75137_2_)
    {
        if (p_75137_1_ == 0)
        {
            this.field_82854_e = p_75137_2_;
        }
    }

    public void func_75134_a(EntityPlayer p_75134_1_)
    {
        super.func_75134_a(p_75134_1_);

        if (!this.field_82860_h.field_72995_K)
        {
            for (int i = 0; i < this.field_82853_g.func_70302_i_(); ++i)
            {
                ItemStack itemstack = this.field_82853_g.func_70304_b(i);

                if (itemstack != null)
                {
                    p_75134_1_.func_71019_a(itemstack, false);
                }
            }
        }
    }

    public boolean func_75145_c(EntityPlayer p_75145_1_)
    {
        return this.field_82860_h.func_147439_a(this.field_82861_i, this.field_82858_j, this.field_82859_k) != Blocks.ANVIL ? false : p_75145_1_.func_70092_e((double)this.field_82861_i + 0.5D, (double)this.field_82858_j + 0.5D, (double)this.field_82859_k + 0.5D) <= 64.0D;
    }

    public ItemStack func_82846_b(EntityPlayer p_82846_1_, int p_82846_2_)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.field_75151_b.get(p_82846_2_);

        if (slot != null && slot.func_75216_d())
        {
            ItemStack itemstack1 = slot.func_75211_c();
            itemstack = itemstack1.func_77946_l();

            if (p_82846_2_ == 2)
            {
                if (!this.func_75135_a(itemstack1, 3, 39, true))
                {
                    return null;
                }

                slot.func_75220_a(itemstack1, itemstack);
            }
            else if (p_82846_2_ != 0 && p_82846_2_ != 1)
            {
                if (p_82846_2_ >= 3 && p_82846_2_ < 39 && !this.func_75135_a(itemstack1, 0, 2, false))
                {
                    return null;
                }
            }
            else if (!this.func_75135_a(itemstack1, 3, 39, false))
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

    public void func_82850_a(String p_82850_1_)
    {
        this.field_82857_m = p_82850_1_;

        if (this.func_75139_a(2).func_75216_d())
        {
            ItemStack itemstack = this.func_75139_a(2).func_75211_c();

            if (StringUtils.isBlank(p_82850_1_))
            {
                itemstack.func_135074_t();
            }
            else
            {
                itemstack.func_151001_c(this.field_82857_m);
            }
        }

        this.func_82848_d();
    }
}