package net.minecraft.creativetab;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Iterator;
import java.util.List;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public abstract class CreativeTabs
{
    public static CreativeTabs[] field_78032_a = new CreativeTabs[12];
    public static final CreativeTabs field_78030_b = new CreativeTabs(0, "buildingBlocks")
    {
        private static final String __OBFID = "CL_00000006";
        @SideOnly(Side.CLIENT)
        public Item func_78016_d()
        {
            return Item.func_150898_a(Blocks.field_150336_V);
        }
    };
    public static final CreativeTabs field_78031_c = new CreativeTabs(1, "decorations")
    {
        private static final String __OBFID = "CL_00000010";
        @SideOnly(Side.CLIENT)
        public Item func_78016_d()
        {
            return Item.func_150898_a(Blocks.field_150398_cm);
        }
        @SideOnly(Side.CLIENT)
        public int func_151243_f()
        {
            return 5;
        }
    };
    public static final CreativeTabs field_78028_d = new CreativeTabs(2, "redstone")
    {
        private static final String __OBFID = "CL_00000011";
        @SideOnly(Side.CLIENT)
        public Item func_78016_d()
        {
            return Items.REDSTONE;
        }
    };
    public static final CreativeTabs field_78029_e = new CreativeTabs(3, "transportation")
    {
        private static final String __OBFID = "CL_00000012";
        @SideOnly(Side.CLIENT)
        public Item func_78016_d()
        {
            return Item.func_150898_a(Blocks.field_150318_D);
        }
    };
    public static final CreativeTabs field_78026_f = (new CreativeTabs(4, "misc")
    {
        private static final String __OBFID = "CL_00000014";
        @SideOnly(Side.CLIENT)
        public Item func_78016_d()
        {
            return Items.LAVA_BUCKET;
        }
    }).func_111229_a(new EnumEnchantmentType[] {EnumEnchantmentType.all});
    public static final CreativeTabs field_78027_g = (new CreativeTabs(5, "search")
    {
        private static final String __OBFID = "CL_00000015";
        @SideOnly(Side.CLIENT)
        public Item func_78016_d()
        {
            return Items.COMPASS;
        }
    }).func_78025_a("item_search.png");
    public static final CreativeTabs field_78039_h = new CreativeTabs(6, "food")
    {
        private static final String __OBFID = "CL_00000016";
        @SideOnly(Side.CLIENT)
        public Item func_78016_d()
        {
            return Items.APPLE;
        }
    };
    public static final CreativeTabs field_78040_i = (new CreativeTabs(7, "tools")
    {
        private static final String __OBFID = "CL_00000017";
        @SideOnly(Side.CLIENT)
        public Item func_78016_d()
        {
            return Items.IRON_AXE;
        }
    }).func_111229_a(new EnumEnchantmentType[] {EnumEnchantmentType.digger, EnumEnchantmentType.fishing_rod, EnumEnchantmentType.breakable});
    public static final CreativeTabs field_78037_j = (new CreativeTabs(8, "combat")
    {
        private static final String __OBFID = "CL_00000018";
        @SideOnly(Side.CLIENT)
        public Item func_78016_d()
        {
            return Items.GOLDEN_SWORD;
        }
    }).func_111229_a(new EnumEnchantmentType[] {EnumEnchantmentType.armor, EnumEnchantmentType.armor_feet, EnumEnchantmentType.armor_head, EnumEnchantmentType.armor_legs, EnumEnchantmentType.armor_torso, EnumEnchantmentType.bow, EnumEnchantmentType.weapon});
    public static final CreativeTabs field_78038_k = new CreativeTabs(9, "brewing")
    {
        private static final String __OBFID = "CL_00000007";
        @SideOnly(Side.CLIENT)
        public Item func_78016_d()
        {
            return Items.POTION;
        }
    };
    public static final CreativeTabs field_78035_l = new CreativeTabs(10, "materials")
    {
        private static final String __OBFID = "CL_00000008";
        @SideOnly(Side.CLIENT)
        public Item func_78016_d()
        {
            return Items.STICK;
        }
    };
    public static final CreativeTabs field_78036_m = (new CreativeTabs(11, "inventory")
    {
        private static final String __OBFID = "CL_00000009";
        @SideOnly(Side.CLIENT)
        public Item func_78016_d()
        {
            return Item.func_150898_a(Blocks.field_150486_ae);
        }
    }).func_78025_a("inventory.png").func_78022_j().func_78014_h();
    private final int field_78033_n;
    private final String field_78034_o;
    private String field_78043_p = "items.png";
    private boolean field_78042_q = true;
    private boolean field_78041_r = true;
    private EnumEnchantmentType[] field_111230_s;
    @SideOnly(Side.CLIENT)
    private ItemStack field_151245_t;
    private static final String __OBFID = "CL_00000005";

    public CreativeTabs(int p_i1853_1_, String p_i1853_2_)
    {
        this.field_78033_n = p_i1853_1_;
        this.field_78034_o = p_i1853_2_;
        field_78032_a[p_i1853_1_] = this;
    }

    @SideOnly(Side.CLIENT)
    public int func_78021_a()
    {
        return this.field_78033_n;
    }

    public CreativeTabs func_78025_a(String p_78025_1_)
    {
        this.field_78043_p = p_78025_1_;
        return this;
    }

    @SideOnly(Side.CLIENT)
    public String func_78013_b()
    {
        return this.field_78034_o;
    }

    @SideOnly(Side.CLIENT)
    public String func_78024_c()
    {
        return "itemGroup." + this.func_78013_b();
    }

    @SideOnly(Side.CLIENT)
    public ItemStack func_151244_d()
    {
        if (this.field_151245_t == null)
        {
            this.field_151245_t = new ItemStack(this.func_78016_d(), 1, this.func_151243_f());
        }

        return this.field_151245_t;
    }

    @SideOnly(Side.CLIENT)
    public abstract Item func_78016_d();

    @SideOnly(Side.CLIENT)
    public int func_151243_f()
    {
        return 0;
    }

    @SideOnly(Side.CLIENT)
    public String func_78015_f()
    {
        return this.field_78043_p;
    }

    @SideOnly(Side.CLIENT)
    public boolean func_78019_g()
    {
        return this.field_78041_r;
    }

    public CreativeTabs func_78014_h()
    {
        this.field_78041_r = false;
        return this;
    }

    @SideOnly(Side.CLIENT)
    public boolean func_78017_i()
    {
        return this.field_78042_q;
    }

    public CreativeTabs func_78022_j()
    {
        this.field_78042_q = false;
        return this;
    }

    @SideOnly(Side.CLIENT)
    public int func_78020_k()
    {
        return this.field_78033_n % 6;
    }

    @SideOnly(Side.CLIENT)
    public boolean func_78023_l()
    {
        return this.field_78033_n < 6;
    }

    @SideOnly(Side.CLIENT)
    public EnumEnchantmentType[] func_111225_m()
    {
        return this.field_111230_s;
    }

    public CreativeTabs func_111229_a(EnumEnchantmentType ... p_111229_1_)
    {
        this.field_111230_s = p_111229_1_;
        return this;
    }

    @SideOnly(Side.CLIENT)
    public boolean func_111226_a(EnumEnchantmentType p_111226_1_)
    {
        if (this.field_111230_s == null)
        {
            return false;
        }
        else
        {
            EnumEnchantmentType[] aenumenchantmenttype = this.field_111230_s;
            int i = aenumenchantmenttype.length;

            for (int j = 0; j < i; ++j)
            {
                EnumEnchantmentType enumenchantmenttype1 = aenumenchantmenttype[j];

                if (enumenchantmenttype1 == p_111226_1_)
                {
                    return true;
                }
            }

            return false;
        }
    }

    @SideOnly(Side.CLIENT)
    public void func_78018_a(List p_78018_1_)
    {
        Iterator iterator = Item.REGISTRY.iterator();

        while (iterator.hasNext())
        {
            Item item = (Item)iterator.next();

            if (item != null && item.func_77640_w() == this)
            {
                item.func_150895_a(item, this, p_78018_1_);
            }
        }

        if (this.func_111225_m() != null)
        {
            this.func_92116_a(p_78018_1_, this.func_111225_m());
        }
    }

    @SideOnly(Side.CLIENT)
    public void func_92116_a(List p_92116_1_, EnumEnchantmentType ... p_92116_2_)
    {
        Enchantment[] aenchantment = Enchantment.field_77331_b;
        int i = aenchantment.length;

        for (int j = 0; j < i; ++j)
        {
            Enchantment enchantment = aenchantment[j];

            if (enchantment != null && enchantment.field_77351_y != null)
            {
                boolean flag = false;

                for (int k = 0; k < p_92116_2_.length && !flag; ++k)
                {
                    if (enchantment.field_77351_y == p_92116_2_[k])
                    {
                        flag = true;
                    }
                }

                if (flag)
                {
                    p_92116_1_.add(Items.ENCHANTED_BOOK.func_92111_a(new EnchantmentData(enchantment, enchantment.func_77325_b())));
                }
            }
        }
    }
}