package net.minecraft.item;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Map.Entry;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentDurability;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.event.HoverEvent;
import net.minecraft.init.Items;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.stats.StatList;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public final class ItemStack
{
    public static final DecimalFormat field_111284_a = new DecimalFormat("#.###");
    public int field_77994_a;
    public int field_77992_b;
    private Item field_151002_e;
    public NBTTagCompound field_77990_d;
    int field_77991_e;
    private EntityItemFrame field_82843_f;
    private static final String __OBFID = "CL_00000043";

    public ItemStack(Block p_i1876_1_)
    {
        this(p_i1876_1_, 1);
    }

    public ItemStack(Block p_i1877_1_, int p_i1877_2_)
    {
        this(p_i1877_1_, p_i1877_2_, 0);
    }

    public ItemStack(Block p_i1878_1_, int p_i1878_2_, int p_i1878_3_)
    {
        this(Item.func_150898_a(p_i1878_1_), p_i1878_2_, p_i1878_3_);
    }

    public ItemStack(Item p_i1879_1_)
    {
        this(p_i1879_1_, 1);
    }

    public ItemStack(Item p_i1880_1_, int p_i1880_2_)
    {
        this(p_i1880_1_, p_i1880_2_, 0);
    }

    public ItemStack(Item p_i1881_1_, int p_i1881_2_, int p_i1881_3_)
    {
        this.field_151002_e = p_i1881_1_;
        this.field_77994_a = p_i1881_2_;
        this.field_77991_e = p_i1881_3_;

        if (this.field_77991_e < 0)
        {
            this.field_77991_e = 0;
        }
    }

    public static ItemStack func_77949_a(NBTTagCompound p_77949_0_)
    {
        ItemStack itemstack = new ItemStack();
        itemstack.func_77963_c(p_77949_0_);
        return itemstack.func_77973_b() != null ? itemstack : null;
    }

    private ItemStack() {}

    public ItemStack func_77979_a(int p_77979_1_)
    {
        ItemStack itemstack = new ItemStack(this.field_151002_e, p_77979_1_, this.field_77991_e);

        if (this.field_77990_d != null)
        {
            itemstack.field_77990_d = (NBTTagCompound)this.field_77990_d.func_74737_b();
        }

        this.field_77994_a -= p_77979_1_;
        return itemstack;
    }

    public Item func_77973_b()
    {
        return this.field_151002_e;
    }

    @SideOnly(Side.CLIENT)
    public IIcon func_77954_c()
    {
        return this.func_77973_b().func_77650_f(this);
    }

    @SideOnly(Side.CLIENT)
    public int func_94608_d()
    {
        return this.func_77973_b().func_94901_k();
    }

    public boolean func_77943_a(EntityPlayer p_77943_1_, World p_77943_2_, int p_77943_3_, int p_77943_4_, int p_77943_5_, int p_77943_6_, float p_77943_7_, float p_77943_8_, float p_77943_9_)
    {
        boolean flag = this.func_77973_b().func_77648_a(this, p_77943_1_, p_77943_2_, p_77943_3_, p_77943_4_, p_77943_5_, p_77943_6_, p_77943_7_, p_77943_8_, p_77943_9_);

        if (flag)
        {
            p_77943_1_.func_71064_a(StatList.field_75929_E[Item.func_150891_b(this.field_151002_e)], 1);
        }

        return flag;
    }

    public float func_150997_a(Block p_150997_1_)
    {
        return this.func_77973_b().func_150893_a(this, p_150997_1_);
    }

    public ItemStack func_77957_a(World p_77957_1_, EntityPlayer p_77957_2_)
    {
        return this.func_77973_b().func_77659_a(this, p_77957_1_, p_77957_2_);
    }

    public ItemStack func_77950_b(World p_77950_1_, EntityPlayer p_77950_2_)
    {
        return this.func_77973_b().func_77654_b(this, p_77950_1_, p_77950_2_);
    }

    public NBTTagCompound func_77955_b(NBTTagCompound p_77955_1_)
    {
        p_77955_1_.func_74777_a("id", (short)Item.func_150891_b(this.field_151002_e));
        p_77955_1_.func_74774_a("Count", (byte)this.field_77994_a);
        p_77955_1_.func_74777_a("Damage", (short)this.field_77991_e);

        if (this.field_77990_d != null)
        {
            p_77955_1_.func_74782_a("tag", this.field_77990_d);
        }

        return p_77955_1_;
    }

    public void func_77963_c(NBTTagCompound p_77963_1_)
    {
        this.field_151002_e = Item.func_150899_d(p_77963_1_.func_74765_d("id"));
        this.field_77994_a = p_77963_1_.func_74771_c("Count");
        this.field_77991_e = p_77963_1_.func_74765_d("Damage");

        if (this.field_77991_e < 0)
        {
            this.field_77991_e = 0;
        }

        if (p_77963_1_.func_150297_b("tag", 10))
        {
            this.field_77990_d = p_77963_1_.func_74775_l("tag");
        }
    }

    public int func_77976_d()
    {
        return this.func_77973_b().func_77639_j();
    }

    public boolean func_77985_e()
    {
        return this.func_77976_d() > 1 && (!this.func_77984_f() || !this.func_77951_h());
    }

    public boolean func_77984_f()
    {
        return this.field_151002_e.func_77612_l() <= 0 ? false : !this.func_77942_o() || !this.func_77978_p().func_74767_n("Unbreakable");
    }

    public boolean func_77981_g()
    {
        return this.field_151002_e.func_77614_k();
    }

    public boolean func_77951_h()
    {
        return this.func_77984_f() && this.field_77991_e > 0;
    }

    public int func_77952_i()
    {
        return this.field_77991_e;
    }

    public int func_77960_j()
    {
        return this.field_77991_e;
    }

    public void func_77964_b(int p_77964_1_)
    {
        this.field_77991_e = p_77964_1_;

        if (this.field_77991_e < 0)
        {
            this.field_77991_e = 0;
        }
    }

    public int func_77958_k()
    {
        return this.field_151002_e.func_77612_l();
    }

    public boolean func_96631_a(int p_96631_1_, Random p_96631_2_)
    {
        if (!this.func_77984_f())
        {
            return false;
        }
        else
        {
            if (p_96631_1_ > 0)
            {
                int j = EnchantmentHelper.func_77506_a(Enchantment.field_77347_r.field_77352_x, this);
                int k = 0;

                for (int l = 0; j > 0 && l < p_96631_1_; ++l)
                {
                    if (EnchantmentDurability.func_92097_a(this, j, p_96631_2_))
                    {
                        ++k;
                    }
                }

                p_96631_1_ -= k;

                if (p_96631_1_ <= 0)
                {
                    return false;
                }
            }

            this.field_77991_e += p_96631_1_;
            return this.field_77991_e > this.func_77958_k();
        }
    }

    public void func_77972_a(int p_77972_1_, EntityLivingBase p_77972_2_)
    {
        if (!(p_77972_2_ instanceof EntityPlayer) || !((EntityPlayer)p_77972_2_).field_71075_bZ.field_75098_d)
        {
            if (this.func_77984_f())
            {
                if (this.func_96631_a(p_77972_1_, p_77972_2_.func_70681_au()))
                {
                    p_77972_2_.func_70669_a(this);
                    --this.field_77994_a;

                    if (p_77972_2_ instanceof EntityPlayer)
                    {
                        EntityPlayer entityplayer = (EntityPlayer)p_77972_2_;
                        entityplayer.func_71064_a(StatList.field_75930_F[Item.func_150891_b(this.field_151002_e)], 1);

                        if (this.field_77994_a == 0 && this.func_77973_b() instanceof ItemBow)
                        {
                            entityplayer.func_71028_bD();
                        }
                    }

                    if (this.field_77994_a < 0)
                    {
                        this.field_77994_a = 0;
                    }

                    this.field_77991_e = 0;
                }
            }
        }
    }

    public void func_77961_a(EntityLivingBase p_77961_1_, EntityPlayer p_77961_2_)
    {
        boolean flag = this.field_151002_e.func_77644_a(this, p_77961_1_, p_77961_2_);

        if (flag)
        {
            p_77961_2_.func_71064_a(StatList.field_75929_E[Item.func_150891_b(this.field_151002_e)], 1);
        }
    }

    public void func_150999_a(World p_150999_1_, Block p_150999_2_, int p_150999_3_, int p_150999_4_, int p_150999_5_, EntityPlayer p_150999_6_)
    {
        boolean flag = this.field_151002_e.func_150894_a(this, p_150999_1_, p_150999_2_, p_150999_3_, p_150999_4_, p_150999_5_, p_150999_6_);

        if (flag)
        {
            p_150999_6_.func_71064_a(StatList.field_75929_E[Item.func_150891_b(this.field_151002_e)], 1);
        }
    }

    public boolean func_150998_b(Block p_150998_1_)
    {
        return this.field_151002_e.func_150897_b(p_150998_1_);
    }

    public boolean func_111282_a(EntityPlayer p_111282_1_, EntityLivingBase p_111282_2_)
    {
        return this.field_151002_e.func_111207_a(this, p_111282_1_, p_111282_2_);
    }

    public ItemStack func_77946_l()
    {
        ItemStack itemstack = new ItemStack(this.field_151002_e, this.field_77994_a, this.field_77991_e);

        if (this.field_77990_d != null)
        {
            itemstack.field_77990_d = (NBTTagCompound)this.field_77990_d.func_74737_b();
        }

        return itemstack;
    }

    public static boolean func_77970_a(ItemStack p_77970_0_, ItemStack p_77970_1_)
    {
        return p_77970_0_ == null && p_77970_1_ == null ? true : (p_77970_0_ != null && p_77970_1_ != null ? (p_77970_0_.field_77990_d == null && p_77970_1_.field_77990_d != null ? false : p_77970_0_.field_77990_d == null || p_77970_0_.field_77990_d.equals(p_77970_1_.field_77990_d)) : false);
    }

    public static boolean func_77989_b(ItemStack p_77989_0_, ItemStack p_77989_1_)
    {
        return p_77989_0_ == null && p_77989_1_ == null ? true : (p_77989_0_ != null && p_77989_1_ != null ? p_77989_0_.func_77959_d(p_77989_1_) : false);
    }

    private boolean func_77959_d(ItemStack p_77959_1_)
    {
        return this.field_77994_a != p_77959_1_.field_77994_a ? false : (this.field_151002_e != p_77959_1_.field_151002_e ? false : (this.field_77991_e != p_77959_1_.field_77991_e ? false : (this.field_77990_d == null && p_77959_1_.field_77990_d != null ? false : this.field_77990_d == null || this.field_77990_d.equals(p_77959_1_.field_77990_d))));
    }

    public boolean func_77969_a(ItemStack p_77969_1_)
    {
        return this.field_151002_e == p_77969_1_.field_151002_e && this.field_77991_e == p_77969_1_.field_77991_e;
    }

    public String func_77977_a()
    {
        return this.field_151002_e.func_77667_c(this);
    }

    public static ItemStack func_77944_b(ItemStack p_77944_0_)
    {
        return p_77944_0_ == null ? null : p_77944_0_.func_77946_l();
    }

    public String toString()
    {
        return this.field_77994_a + "x" + this.field_151002_e.func_77658_a() + "@" + this.field_77991_e;
    }

    public void func_77945_a(World p_77945_1_, Entity p_77945_2_, int p_77945_3_, boolean p_77945_4_)
    {
        if (this.field_77992_b > 0)
        {
            --this.field_77992_b;
        }

        this.field_151002_e.func_77663_a(this, p_77945_1_, p_77945_2_, p_77945_3_, p_77945_4_);
    }

    public void func_77980_a(World p_77980_1_, EntityPlayer p_77980_2_, int p_77980_3_)
    {
        p_77980_2_.func_71064_a(StatList.field_75928_D[Item.func_150891_b(this.field_151002_e)], p_77980_3_);
        this.field_151002_e.func_77622_d(this, p_77980_1_, p_77980_2_);
    }

    public int func_77988_m()
    {
        return this.func_77973_b().func_77626_a(this);
    }

    public EnumAction func_77975_n()
    {
        return this.func_77973_b().func_77661_b(this);
    }

    public void func_77974_b(World p_77974_1_, EntityPlayer p_77974_2_, int p_77974_3_)
    {
        this.func_77973_b().func_77615_a(this, p_77974_1_, p_77974_2_, p_77974_3_);
    }

    public boolean func_77942_o()
    {
        return this.field_77990_d != null;
    }

    public NBTTagCompound func_77978_p()
    {
        return this.field_77990_d;
    }

    public NBTTagList func_77986_q()
    {
        return this.field_77990_d == null ? null : this.field_77990_d.func_150295_c("ench", 10);
    }

    public void func_77982_d(NBTTagCompound p_77982_1_)
    {
        this.field_77990_d = p_77982_1_;
    }

    public String func_82833_r()
    {
        String s = this.func_77973_b().func_77653_i(this);

        if (this.field_77990_d != null && this.field_77990_d.func_150297_b("display", 10))
        {
            NBTTagCompound nbttagcompound = this.field_77990_d.func_74775_l("display");

            if (nbttagcompound.func_150297_b("Name", 8))
            {
                s = nbttagcompound.func_74779_i("Name");
            }
        }

        return s;
    }

    public ItemStack func_151001_c(String p_151001_1_)
    {
        if (this.field_77990_d == null)
        {
            this.field_77990_d = new NBTTagCompound();
        }

        if (!this.field_77990_d.func_150297_b("display", 10))
        {
            this.field_77990_d.func_74782_a("display", new NBTTagCompound());
        }

        this.field_77990_d.func_74775_l("display").func_74778_a("Name", p_151001_1_);
        return this;
    }

    public void func_135074_t()
    {
        if (this.field_77990_d != null)
        {
            if (this.field_77990_d.func_150297_b("display", 10))
            {
                NBTTagCompound nbttagcompound = this.field_77990_d.func_74775_l("display");
                nbttagcompound.func_82580_o("Name");

                if (nbttagcompound.func_82582_d())
                {
                    this.field_77990_d.func_82580_o("display");

                    if (this.field_77990_d.func_82582_d())
                    {
                        this.func_77982_d((NBTTagCompound)null);
                    }
                }
            }
        }
    }

    public boolean func_82837_s()
    {
        return this.field_77990_d == null ? false : (!this.field_77990_d.func_150297_b("display", 10) ? false : this.field_77990_d.func_74775_l("display").func_150297_b("Name", 8));
    }

    @SideOnly(Side.CLIENT)
    public List func_82840_a(EntityPlayer p_82840_1_, boolean p_82840_2_)
    {
        ArrayList arraylist = new ArrayList();
        String s = this.func_82833_r();

        if (this.func_82837_s())
        {
            s = EnumChatFormatting.ITALIC + s + EnumChatFormatting.RESET;
        }

        int i;

        if (p_82840_2_)
        {
            String s1 = "";

            if (s.length() > 0)
            {
                s = s + " (";
                s1 = ")";
            }

            i = Item.func_150891_b(this.field_151002_e);

            if (this.func_77981_g())
            {
                s = s + String.format("#%04d/%d%s", new Object[] {Integer.valueOf(i), Integer.valueOf(this.field_77991_e), s1});
            }
            else
            {
                s = s + String.format("#%04d%s", new Object[] {Integer.valueOf(i), s1});
            }
        }
        else if (!this.func_82837_s() && this.field_151002_e == Items.field_151098_aY)
        {
            s = s + " #" + this.field_77991_e;
        }

        arraylist.add(s);
        this.field_151002_e.func_77624_a(this, p_82840_1_, arraylist, p_82840_2_);

        if (this.func_77942_o())
        {
            NBTTagList nbttaglist = this.func_77986_q();

            if (nbttaglist != null)
            {
                for (i = 0; i < nbttaglist.func_74745_c(); ++i)
                {
                    short short1 = nbttaglist.func_150305_b(i).func_74765_d("id");
                    short short2 = nbttaglist.func_150305_b(i).func_74765_d("lvl");

                    if (Enchantment.field_77331_b[short1] != null)
                    {
                        arraylist.add(Enchantment.field_77331_b[short1].func_77316_c(short2));
                    }
                }
            }

            if (this.field_77990_d.func_150297_b("display", 10))
            {
                NBTTagCompound nbttagcompound = this.field_77990_d.func_74775_l("display");

                if (nbttagcompound.func_150297_b("color", 3))
                {
                    if (p_82840_2_)
                    {
                        arraylist.add("Color: #" + Integer.toHexString(nbttagcompound.func_74762_e("color")).toUpperCase());
                    }
                    else
                    {
                        arraylist.add(EnumChatFormatting.ITALIC + StatCollector.func_74838_a("item.dyed"));
                    }
                }

                if (nbttagcompound.func_150299_b("Lore") == 9)
                {
                    NBTTagList nbttaglist1 = nbttagcompound.func_150295_c("Lore", 8);

                    if (nbttaglist1.func_74745_c() > 0)
                    {
                        for (int j = 0; j < nbttaglist1.func_74745_c(); ++j)
                        {
                            arraylist.add(EnumChatFormatting.DARK_PURPLE + "" + EnumChatFormatting.ITALIC + nbttaglist1.func_150307_f(j));
                        }
                    }
                }
            }
        }

        Multimap multimap = this.func_111283_C();

        if (!multimap.isEmpty())
        {
            arraylist.add("");
            Iterator iterator = multimap.entries().iterator();

            while (iterator.hasNext())
            {
                Entry entry = (Entry)iterator.next();
                AttributeModifier attributemodifier = (AttributeModifier)entry.getValue();
                double d0 = attributemodifier.func_111164_d();

                if (attributemodifier.func_111167_a() == Item.field_111210_e)
                {
                    d0 += (double)EnchantmentHelper.func_152377_a(this, EnumCreatureAttribute.UNDEFINED);
                }

                double d1;

                if (attributemodifier.func_111169_c() != 1 && attributemodifier.func_111169_c() != 2)
                {
                    d1 = d0;
                }
                else
                {
                    d1 = d0 * 100.0D;
                }

                if (d0 > 0.0D)
                {
                    arraylist.add(EnumChatFormatting.BLUE + StatCollector.func_74837_a("attribute.modifier.plus." + attributemodifier.func_111169_c(), new Object[] {field_111284_a.format(d1), StatCollector.func_74838_a("attribute.name." + (String)entry.getKey())}));
                }
                else if (d0 < 0.0D)
                {
                    d1 *= -1.0D;
                    arraylist.add(EnumChatFormatting.RED + StatCollector.func_74837_a("attribute.modifier.take." + attributemodifier.func_111169_c(), new Object[] {field_111284_a.format(d1), StatCollector.func_74838_a("attribute.name." + (String)entry.getKey())}));
                }
            }
        }

        if (this.func_77942_o() && this.func_77978_p().func_74767_n("Unbreakable"))
        {
            arraylist.add(EnumChatFormatting.BLUE + StatCollector.func_74838_a("item.unbreakable"));
        }

        if (p_82840_2_ && this.func_77951_h())
        {
            arraylist.add("Durability: " + (this.func_77958_k() - this.func_77952_i()) + " / " + this.func_77958_k());
        }

        return arraylist;
    }

    @SideOnly(Side.CLIENT)
    public boolean func_77962_s()
    {
        return this.func_77973_b().func_77636_d(this);
    }

    public EnumRarity func_77953_t()
    {
        return this.func_77973_b().func_77613_e(this);
    }

    public boolean func_77956_u()
    {
        return !this.func_77973_b().func_77616_k(this) ? false : !this.func_77948_v();
    }

    public void func_77966_a(Enchantment p_77966_1_, int p_77966_2_)
    {
        if (this.field_77990_d == null)
        {
            this.func_77982_d(new NBTTagCompound());
        }

        if (!this.field_77990_d.func_150297_b("ench", 9))
        {
            this.field_77990_d.func_74782_a("ench", new NBTTagList());
        }

        NBTTagList nbttaglist = this.field_77990_d.func_150295_c("ench", 10);
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        nbttagcompound.func_74777_a("id", (short)p_77966_1_.field_77352_x);
        nbttagcompound.func_74777_a("lvl", (short)((byte)p_77966_2_));
        nbttaglist.func_74742_a(nbttagcompound);
    }

    public boolean func_77948_v()
    {
        return this.field_77990_d != null && this.field_77990_d.func_150297_b("ench", 9);
    }

    public void func_77983_a(String p_77983_1_, NBTBase p_77983_2_)
    {
        if (this.field_77990_d == null)
        {
            this.func_77982_d(new NBTTagCompound());
        }

        this.field_77990_d.func_74782_a(p_77983_1_, p_77983_2_);
    }

    public boolean func_82835_x()
    {
        return this.func_77973_b().func_82788_x();
    }

    public boolean func_82839_y()
    {
        return this.field_82843_f != null;
    }

    public void func_82842_a(EntityItemFrame p_82842_1_)
    {
        this.field_82843_f = p_82842_1_;
    }

    public EntityItemFrame func_82836_z()
    {
        return this.field_82843_f;
    }

    public int func_82838_A()
    {
        return this.func_77942_o() && this.field_77990_d.func_150297_b("RepairCost", 3) ? this.field_77990_d.func_74762_e("RepairCost") : 0;
    }

    public void func_82841_c(int p_82841_1_)
    {
        if (!this.func_77942_o())
        {
            this.field_77990_d = new NBTTagCompound();
        }

        this.field_77990_d.func_74768_a("RepairCost", p_82841_1_);
    }

    public Multimap func_111283_C()
    {
        Object object;

        if (this.func_77942_o() && this.field_77990_d.func_150297_b("AttributeModifiers", 9))
        {
            object = HashMultimap.create();
            NBTTagList nbttaglist = this.field_77990_d.func_150295_c("AttributeModifiers", 10);

            for (int i = 0; i < nbttaglist.func_74745_c(); ++i)
            {
                NBTTagCompound nbttagcompound = nbttaglist.func_150305_b(i);
                AttributeModifier attributemodifier = SharedMonsterAttributes.func_111259_a(nbttagcompound);

                if (attributemodifier.func_111167_a().getLeastSignificantBits() != 0L && attributemodifier.func_111167_a().getMostSignificantBits() != 0L)
                {
                    ((Multimap)object).put(nbttagcompound.func_74779_i("AttributeName"), attributemodifier);
                }
            }
        }
        else
        {
            object = this.func_77973_b().func_111205_h();
        }

        return (Multimap)object;
    }

    public void func_150996_a(Item p_150996_1_)
    {
        this.field_151002_e = p_150996_1_;
    }

    public IChatComponent func_151000_E()
    {
        IChatComponent ichatcomponent = (new ChatComponentText("[")).func_150258_a(this.func_82833_r()).func_150258_a("]");

        if (this.field_151002_e != null)
        {
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            this.func_77955_b(nbttagcompound);
            ichatcomponent.func_150256_b().func_150209_a(new HoverEvent(HoverEvent.Action.SHOW_ITEM, new ChatComponentText(nbttagcompound.toString())));
            ichatcomponent.func_150256_b().func_150238_a(this.func_77953_t().field_77937_e);
        }

        return ichatcomponent;
    }
}