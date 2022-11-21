package net.minecraft.item;

import com.google.common.collect.HashMultimap;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.init.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionHelper;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemPotion extends Item
{
    private HashMap field_77836_a = new HashMap();
    private static final Map field_77835_b = new LinkedHashMap();
    @SideOnly(Side.CLIENT)
    private IIcon field_94591_c;
    @SideOnly(Side.CLIENT)
    private IIcon field_94590_d;
    @SideOnly(Side.CLIENT)
    private IIcon field_94592_ct;
    private static final String __OBFID = "CL_00000055";

    public ItemPotion()
    {
        this.func_77625_d(1);
        this.func_77627_a(true);
        this.func_77656_e(0);
        this.func_77637_a(CreativeTabs.field_78038_k);
    }

    public List func_77832_l(ItemStack p_77832_1_)
    {
        if (p_77832_1_.func_77942_o() && p_77832_1_.func_77978_p().func_150297_b("CustomPotionEffects", 9))
        {
            ArrayList arraylist = new ArrayList();
            NBTTagList nbttaglist = p_77832_1_.func_77978_p().func_150295_c("CustomPotionEffects", 10);

            for (int i = 0; i < nbttaglist.func_74745_c(); ++i)
            {
                NBTTagCompound nbttagcompound = nbttaglist.func_150305_b(i);
                PotionEffect potioneffect = PotionEffect.func_82722_b(nbttagcompound);

                if (potioneffect != null)
                {
                    arraylist.add(potioneffect);
                }
            }

            return arraylist;
        }
        else
        {
            List list = (List)this.field_77836_a.get(Integer.valueOf(p_77832_1_.func_77960_j()));

            if (list == null)
            {
                list = PotionHelper.func_77917_b(p_77832_1_.func_77960_j(), false);
                this.field_77836_a.put(Integer.valueOf(p_77832_1_.func_77960_j()), list);
            }

            return list;
        }
    }

    public List func_77834_f(int p_77834_1_)
    {
        List list = (List)this.field_77836_a.get(Integer.valueOf(p_77834_1_));

        if (list == null)
        {
            list = PotionHelper.func_77917_b(p_77834_1_, false);
            this.field_77836_a.put(Integer.valueOf(p_77834_1_), list);
        }

        return list;
    }

    public ItemStack func_77654_b(ItemStack p_77654_1_, World p_77654_2_, EntityPlayer p_77654_3_)
    {
        if (!p_77654_3_.field_71075_bZ.field_75098_d)
        {
            --p_77654_1_.field_77994_a;
        }

        if (!p_77654_2_.field_72995_K)
        {
            List list = this.func_77832_l(p_77654_1_);

            if (list != null)
            {
                Iterator iterator = list.iterator();

                while (iterator.hasNext())
                {
                    PotionEffect potioneffect = (PotionEffect)iterator.next();
                    p_77654_3_.func_70690_d(new PotionEffect(potioneffect));
                }
            }
        }

        if (!p_77654_3_.field_71075_bZ.field_75098_d)
        {
            if (p_77654_1_.field_77994_a <= 0)
            {
                return new ItemStack(Items.GLASS_BOTTLE);
            }

            p_77654_3_.field_71071_by.func_70441_a(new ItemStack(Items.GLASS_BOTTLE));
        }

        return p_77654_1_;
    }

    public int func_77626_a(ItemStack p_77626_1_)
    {
        return 32;
    }

    public EnumAction func_77661_b(ItemStack p_77661_1_)
    {
        return EnumAction.drink;
    }

    public ItemStack func_77659_a(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_)
    {
        if (func_77831_g(p_77659_1_.func_77960_j()))
        {
            if (!p_77659_3_.field_71075_bZ.field_75098_d)
            {
                --p_77659_1_.field_77994_a;
            }

            p_77659_2_.func_72956_a(p_77659_3_, "random.bow", 0.5F, 0.4F / (field_77697_d.nextFloat() * 0.4F + 0.8F));

            if (!p_77659_2_.field_72995_K)
            {
                p_77659_2_.func_72838_d(new EntityPotion(p_77659_2_, p_77659_3_, p_77659_1_));
            }

            return p_77659_1_;
        }
        else
        {
            p_77659_3_.func_71008_a(p_77659_1_, this.func_77626_a(p_77659_1_));
            return p_77659_1_;
        }
    }

    public boolean func_77648_a(ItemStack p_77648_1_, EntityPlayer p_77648_2_, World p_77648_3_, int p_77648_4_, int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_)
    {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public IIcon func_77617_a(int p_77617_1_)
    {
        return func_77831_g(p_77617_1_) ? this.field_94591_c : this.field_94590_d;
    }

    @SideOnly(Side.CLIENT)
    public IIcon func_77618_c(int p_77618_1_, int p_77618_2_)
    {
        return p_77618_2_ == 0 ? this.field_94592_ct : super.func_77618_c(p_77618_1_, p_77618_2_);
    }

    public static boolean func_77831_g(int p_77831_0_)
    {
        return (p_77831_0_ & 16384) != 0;
    }

    @SideOnly(Side.CLIENT)
    public int func_77620_a(int p_77620_1_)
    {
        return PotionHelper.func_77915_a(p_77620_1_, false);
    }

    @SideOnly(Side.CLIENT)
    public int func_82790_a(ItemStack p_82790_1_, int p_82790_2_)
    {
        return p_82790_2_ > 0 ? 16777215 : this.func_77620_a(p_82790_1_.func_77960_j());
    }

    @SideOnly(Side.CLIENT)
    public boolean func_77623_v()
    {
        return true;
    }

    @SideOnly(Side.CLIENT)
    public boolean func_77833_h(int p_77833_1_)
    {
        List list = this.func_77834_f(p_77833_1_);

        if (list != null && !list.isEmpty())
        {
            Iterator iterator = list.iterator();
            PotionEffect potioneffect;

            do
            {
                if (!iterator.hasNext())
                {
                    return false;
                }

                potioneffect = (PotionEffect)iterator.next();
            }
            while (!Potion.field_76425_a[potioneffect.func_76456_a()].func_76403_b());

            return true;
        }
        else
        {
            return false;
        }
    }

    public String func_77653_i(ItemStack p_77653_1_)
    {
        if (p_77653_1_.func_77960_j() == 0)
        {
            return StatCollector.func_74838_a("item.emptyPotion.name").trim();
        }
        else
        {
            String s = "";

            if (func_77831_g(p_77653_1_.func_77960_j()))
            {
                s = StatCollector.func_74838_a("potion.prefix.grenade").trim() + " ";
            }

            List list = Items.POTION.func_77832_l(p_77653_1_);
            String s1;

            if (list != null && !list.isEmpty())
            {
                s1 = ((PotionEffect)list.get(0)).func_76453_d();
                s1 = s1 + ".postfix";
                return s + StatCollector.func_74838_a(s1).trim();
            }
            else
            {
                s1 = PotionHelper.func_77905_c(p_77653_1_.func_77960_j());
                return StatCollector.func_74838_a(s1).trim() + " " + super.func_77653_i(p_77653_1_);
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_)
    {
        if (p_77624_1_.func_77960_j() != 0)
        {
            List list1 = Items.POTION.func_77832_l(p_77624_1_);
            HashMultimap hashmultimap = HashMultimap.create();
            Iterator iterator1;

            if (list1 != null && !list1.isEmpty())
            {
                iterator1 = list1.iterator();

                while (iterator1.hasNext())
                {
                    PotionEffect potioneffect = (PotionEffect)iterator1.next();
                    String s1 = StatCollector.func_74838_a(potioneffect.func_76453_d()).trim();
                    Potion potion = Potion.field_76425_a[potioneffect.func_76456_a()];
                    Map map = potion.func_111186_k();

                    if (map != null && map.size() > 0)
                    {
                        Iterator iterator = map.entrySet().iterator();

                        while (iterator.hasNext())
                        {
                            Entry entry = (Entry)iterator.next();
                            AttributeModifier attributemodifier = (AttributeModifier)entry.getValue();
                            AttributeModifier attributemodifier1 = new AttributeModifier(attributemodifier.func_111166_b(), potion.func_111183_a(potioneffect.func_76458_c(), attributemodifier), attributemodifier.func_111169_c());
                            hashmultimap.put(((IAttribute)entry.getKey()).func_111108_a(), attributemodifier1);
                        }
                    }

                    if (potioneffect.func_76458_c() > 0)
                    {
                        s1 = s1 + " " + StatCollector.func_74838_a("potion.potency." + potioneffect.func_76458_c()).trim();
                    }

                    if (potioneffect.func_76459_b() > 20)
                    {
                        s1 = s1 + " (" + Potion.func_76389_a(potioneffect) + ")";
                    }

                    if (potion.func_76398_f())
                    {
                        p_77624_3_.add(EnumChatFormatting.RED + s1);
                    }
                    else
                    {
                        p_77624_3_.add(EnumChatFormatting.GRAY + s1);
                    }
                }
            }
            else
            {
                String s = StatCollector.func_74838_a("potion.empty").trim();
                p_77624_3_.add(EnumChatFormatting.GRAY + s);
            }

            if (!hashmultimap.isEmpty())
            {
                p_77624_3_.add("");
                p_77624_3_.add(EnumChatFormatting.DARK_PURPLE + StatCollector.func_74838_a("potion.effects.whenDrank"));
                iterator1 = hashmultimap.entries().iterator();

                while (iterator1.hasNext())
                {
                    Entry entry1 = (Entry)iterator1.next();
                    AttributeModifier attributemodifier2 = (AttributeModifier)entry1.getValue();
                    double d0 = attributemodifier2.func_111164_d();
                    double d1;

                    if (attributemodifier2.func_111169_c() != 1 && attributemodifier2.func_111169_c() != 2)
                    {
                        d1 = attributemodifier2.func_111164_d();
                    }
                    else
                    {
                        d1 = attributemodifier2.func_111164_d() * 100.0D;
                    }

                    if (d0 > 0.0D)
                    {
                        p_77624_3_.add(EnumChatFormatting.BLUE + StatCollector.func_74837_a("attribute.modifier.plus." + attributemodifier2.func_111169_c(), new Object[] {ItemStack.field_111284_a.format(d1), StatCollector.func_74838_a("attribute.name." + (String)entry1.getKey())}));
                    }
                    else if (d0 < 0.0D)
                    {
                        d1 *= -1.0D;
                        p_77624_3_.add(EnumChatFormatting.RED + StatCollector.func_74837_a("attribute.modifier.take." + attributemodifier2.func_111169_c(), new Object[] {ItemStack.field_111284_a.format(d1), StatCollector.func_74838_a("attribute.name." + (String)entry1.getKey())}));
                    }
                }
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public boolean func_77636_d(ItemStack p_77636_1_)
    {
        List list = this.func_77832_l(p_77636_1_);
        return list != null && !list.isEmpty();
    }

    @SideOnly(Side.CLIENT)
    public void func_150895_a(Item p_150895_1_, CreativeTabs p_150895_2_, List p_150895_3_)
    {
        super.func_150895_a(p_150895_1_, p_150895_2_, p_150895_3_);
        int j;

        if (field_77835_b.isEmpty())
        {
            for (int i = 0; i <= 15; ++i)
            {
                for (j = 0; j <= 1; ++j)
                {
                    int k;

                    if (j == 0)
                    {
                        k = i | 8192;
                    }
                    else
                    {
                        k = i | 16384;
                    }

                    for (int l = 0; l <= 2; ++l)
                    {
                        int i1 = k;

                        if (l != 0)
                        {
                            if (l == 1)
                            {
                                i1 = k | 32;
                            }
                            else if (l == 2)
                            {
                                i1 = k | 64;
                            }
                        }

                        List list1 = PotionHelper.func_77917_b(i1, false);

                        if (list1 != null && !list1.isEmpty())
                        {
                            field_77835_b.put(list1, Integer.valueOf(i1));
                        }
                    }
                }
            }
        }

        Iterator iterator = field_77835_b.values().iterator();

        while (iterator.hasNext())
        {
            j = ((Integer)iterator.next()).intValue();
            p_150895_3_.add(new ItemStack(p_150895_1_, 1, j));
        }
    }

    @SideOnly(Side.CLIENT)
    public void func_94581_a(IIconRegister p_94581_1_)
    {
        this.field_94590_d = p_94581_1_.func_94245_a(this.func_111208_A() + "_" + "bottle_drinkable");
        this.field_94591_c = p_94581_1_.func_94245_a(this.func_111208_A() + "_" + "bottle_splash");
        this.field_94592_ct = p_94581_1_.func_94245_a(this.func_111208_A() + "_" + "overlay");
    }

    @SideOnly(Side.CLIENT)
    public static IIcon func_94589_d(String p_94589_0_)
    {
        return p_94589_0_.equals("bottle_drinkable") ? Items.POTION.field_94590_d : (p_94589_0_.equals("bottle_splash") ? Items.POTION.field_94591_c : (p_94589_0_.equals("overlay") ? Items.POTION.field_94592_ct : null));
    }
}