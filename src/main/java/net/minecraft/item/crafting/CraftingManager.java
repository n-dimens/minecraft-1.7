package net.minecraft.item.crafting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class CraftingManager
{
    private static final CraftingManager field_77598_a = new CraftingManager();
    private List field_77597_b = new ArrayList();
    private static final String __OBFID = "CL_00000090";

    public static final CraftingManager func_77594_a()
    {
        return field_77598_a;
    }

    private CraftingManager()
    {
        (new RecipesTools()).func_77586_a(this);
        (new RecipesWeapons()).func_77583_a(this);
        (new RecipesIngots()).func_77590_a(this);
        (new RecipesFood()).func_77608_a(this);
        (new RecipesCrafting()).func_77589_a(this);
        (new RecipesArmor()).func_77609_a(this);
        (new RecipesDyes()).func_77607_a(this);
        this.field_77597_b.add(new RecipesArmorDyes());
        this.field_77597_b.add(new RecipeBookCloning());
        this.field_77597_b.add(new RecipesMapCloning());
        this.field_77597_b.add(new RecipesMapExtending());
        this.field_77597_b.add(new RecipeFireworks());
        this.func_92103_a(new ItemStack(Items.PAPER, 3), new Object[] {"###", '#', Items.REEDS});
        this.func_77596_b(new ItemStack(Items.BOOK, 1), new Object[] {Items.PAPER, Items.PAPER, Items.PAPER, Items.LEATHER});
        this.func_77596_b(new ItemStack(Items.WRITABLE_BOOK, 1), new Object[] {Items.BOOK, new ItemStack(Items.DYE, 1, 0), Items.FEATHER});
        this.func_92103_a(new ItemStack(Blocks.field_150422_aJ, 2), new Object[] {"###", "###", '#', Items.STICK});
        this.func_92103_a(new ItemStack(Blocks.field_150463_bK, 6, 0), new Object[] {"###", "###", '#', Blocks.field_150347_e});
        this.func_92103_a(new ItemStack(Blocks.field_150463_bK, 6, 1), new Object[] {"###", "###", '#', Blocks.field_150341_Y});
        this.func_92103_a(new ItemStack(Blocks.field_150386_bk, 6), new Object[] {"###", "###", '#', Blocks.field_150385_bj});
        this.func_92103_a(new ItemStack(Blocks.field_150396_be, 1), new Object[] {"#W#", "#W#", '#', Items.STICK, 'W', Blocks.field_150344_f});
        this.func_92103_a(new ItemStack(Blocks.field_150421_aI, 1), new Object[] {"###", "#X#", "###", '#', Blocks.field_150344_f, 'X', Items.DIAMOND});
        this.func_92103_a(new ItemStack(Items.LEAD, 2), new Object[] {"~~ ", "~O ", "  ~", '~', Items.STRING, 'O', Items.SLIME_BALL});
        this.func_92103_a(new ItemStack(Blocks.field_150323_B, 1), new Object[] {"###", "#X#", "###", '#', Blocks.field_150344_f, 'X', Items.REDSTONE});
        this.func_92103_a(new ItemStack(Blocks.field_150342_X, 1), new Object[] {"###", "XXX", "###", '#', Blocks.field_150344_f, 'X', Items.BOOK});
        this.func_92103_a(new ItemStack(Blocks.field_150433_aE, 1), new Object[] {"##", "##", '#', Items.SNOWBALL});
        this.func_92103_a(new ItemStack(Blocks.field_150431_aC, 6), new Object[] {"###", '#', Blocks.field_150433_aE});
        this.func_92103_a(new ItemStack(Blocks.field_150435_aG, 1), new Object[] {"##", "##", '#', Items.CLAY_BALL});
        this.func_92103_a(new ItemStack(Blocks.field_150336_V, 1), new Object[] {"##", "##", '#', Items.BRICK});
        this.func_92103_a(new ItemStack(Blocks.field_150426_aN, 1), new Object[] {"##", "##", '#', Items.GLOWSTONE_DUST});
        this.func_92103_a(new ItemStack(Blocks.field_150371_ca, 1), new Object[] {"##", "##", '#', Items.QUARTZ});
        this.func_92103_a(new ItemStack(Blocks.WOOL, 1), new Object[] {"##", "##", '#', Items.STRING});
        this.func_92103_a(new ItemStack(Blocks.field_150335_W, 1), new Object[] {"X#X", "#X#", "X#X", 'X', Items.GUNPOWDER, '#', Blocks.field_150354_m});
        this.func_92103_a(new ItemStack(Blocks.field_150333_U, 6, 3), new Object[] {"###", '#', Blocks.field_150347_e});
        this.func_92103_a(new ItemStack(Blocks.field_150333_U, 6, 0), new Object[] {"###", '#', Blocks.field_150348_b});
        this.func_92103_a(new ItemStack(Blocks.field_150333_U, 6, 1), new Object[] {"###", '#', Blocks.field_150322_A});
        this.func_92103_a(new ItemStack(Blocks.field_150333_U, 6, 4), new Object[] {"###", '#', Blocks.field_150336_V});
        this.func_92103_a(new ItemStack(Blocks.field_150333_U, 6, 5), new Object[] {"###", '#', Blocks.field_150417_aV});
        this.func_92103_a(new ItemStack(Blocks.field_150333_U, 6, 6), new Object[] {"###", '#', Blocks.field_150385_bj});
        this.func_92103_a(new ItemStack(Blocks.field_150333_U, 6, 7), new Object[] {"###", '#', Blocks.field_150371_ca});
        this.func_92103_a(new ItemStack(Blocks.field_150376_bx, 6, 0), new Object[] {"###", '#', new ItemStack(Blocks.field_150344_f, 1, 0)});
        this.func_92103_a(new ItemStack(Blocks.field_150376_bx, 6, 2), new Object[] {"###", '#', new ItemStack(Blocks.field_150344_f, 1, 2)});
        this.func_92103_a(new ItemStack(Blocks.field_150376_bx, 6, 1), new Object[] {"###", '#', new ItemStack(Blocks.field_150344_f, 1, 1)});
        this.func_92103_a(new ItemStack(Blocks.field_150376_bx, 6, 3), new Object[] {"###", '#', new ItemStack(Blocks.field_150344_f, 1, 3)});
        this.func_92103_a(new ItemStack(Blocks.field_150376_bx, 6, 4), new Object[] {"###", '#', new ItemStack(Blocks.field_150344_f, 1, 4)});
        this.func_92103_a(new ItemStack(Blocks.field_150376_bx, 6, 5), new Object[] {"###", '#', new ItemStack(Blocks.field_150344_f, 1, 5)});
        this.func_92103_a(new ItemStack(Blocks.field_150468_ap, 3), new Object[] {"# #", "###", "# #", '#', Items.STICK});
        this.func_92103_a(new ItemStack(Items.WOODEN_DOOR, 1), new Object[] {"##", "##", "##", '#', Blocks.field_150344_f});
        this.func_92103_a(new ItemStack(Blocks.field_150415_aT, 2), new Object[] {"###", "###", '#', Blocks.field_150344_f});
        this.func_92103_a(new ItemStack(Items.IRON_DOOR, 1), new Object[] {"##", "##", "##", '#', Items.IRON_INGOT});
        this.func_92103_a(new ItemStack(Items.SIGN, 3), new Object[] {"###", "###", " X ", '#', Blocks.field_150344_f, 'X', Items.STICK});
        this.func_92103_a(new ItemStack(Items.CAKE, 1), new Object[] {"AAA", "BEB", "CCC", 'A', Items.MILK_BUCKET, 'B', Items.SUGAR, 'C', Items.WHEAT, 'E', Items.EGG});
        this.func_92103_a(new ItemStack(Items.SUGAR, 1), new Object[] {"#", '#', Items.REEDS});
        this.func_92103_a(new ItemStack(Blocks.field_150344_f, 4, 0), new Object[] {"#", '#', new ItemStack(Blocks.field_150364_r, 1, 0)});
        this.func_92103_a(new ItemStack(Blocks.field_150344_f, 4, 1), new Object[] {"#", '#', new ItemStack(Blocks.field_150364_r, 1, 1)});
        this.func_92103_a(new ItemStack(Blocks.field_150344_f, 4, 2), new Object[] {"#", '#', new ItemStack(Blocks.field_150364_r, 1, 2)});
        this.func_92103_a(new ItemStack(Blocks.field_150344_f, 4, 3), new Object[] {"#", '#', new ItemStack(Blocks.field_150364_r, 1, 3)});
        this.func_92103_a(new ItemStack(Blocks.field_150344_f, 4, 4), new Object[] {"#", '#', new ItemStack(Blocks.field_150363_s, 1, 0)});
        this.func_92103_a(new ItemStack(Blocks.field_150344_f, 4, 5), new Object[] {"#", '#', new ItemStack(Blocks.field_150363_s, 1, 1)});
        this.func_92103_a(new ItemStack(Items.STICK, 4), new Object[] {"#", "#", '#', Blocks.field_150344_f});
        this.func_92103_a(new ItemStack(Blocks.field_150478_aa, 4), new Object[] {"X", "#", 'X', Items.COAL, '#', Items.STICK});
        this.func_92103_a(new ItemStack(Blocks.field_150478_aa, 4), new Object[] {"X", "#", 'X', new ItemStack(Items.COAL, 1, 1), '#', Items.STICK});
        this.func_92103_a(new ItemStack(Items.BOWL, 4), new Object[] {"# #", " # ", '#', Blocks.field_150344_f});
        this.func_92103_a(new ItemStack(Items.GLASS_BOTTLE, 3), new Object[] {"# #", " # ", '#', Blocks.field_150359_w});
        this.func_92103_a(new ItemStack(Blocks.field_150448_aq, 16), new Object[] {"X X", "X#X", "X X", 'X', Items.IRON_INGOT, '#', Items.STICK});
        this.func_92103_a(new ItemStack(Blocks.field_150318_D, 6), new Object[] {"X X", "X#X", "XRX", 'X', Items.GOLD_INGOT, 'R', Items.REDSTONE, '#', Items.STICK});
        this.func_92103_a(new ItemStack(Blocks.field_150408_cc, 6), new Object[] {"XSX", "X#X", "XSX", 'X', Items.IRON_INGOT, '#', Blocks.field_150429_aA, 'S', Items.STICK});
        this.func_92103_a(new ItemStack(Blocks.field_150319_E, 6), new Object[] {"X X", "X#X", "XRX", 'X', Items.IRON_INGOT, 'R', Items.REDSTONE, '#', Blocks.field_150456_au});
        this.func_92103_a(new ItemStack(Items.MINECART, 1), new Object[] {"# #", "###", '#', Items.IRON_INGOT});
        this.func_92103_a(new ItemStack(Items.CAULDRON, 1), new Object[] {"# #", "# #", "###", '#', Items.IRON_INGOT});
        this.func_92103_a(new ItemStack(Items.BREWING_STAND, 1), new Object[] {" B ", "###", '#', Blocks.field_150347_e, 'B', Items.BLAZE_ROD});
        this.func_92103_a(new ItemStack(Blocks.field_150428_aP, 1), new Object[] {"A", "B", 'A', Blocks.field_150423_aK, 'B', Blocks.field_150478_aa});
        this.func_92103_a(new ItemStack(Items.CHEST_MINECART, 1), new Object[] {"A", "B", 'A', Blocks.field_150486_ae, 'B', Items.MINECART});
        this.func_92103_a(new ItemStack(Items.FURNACE_MINECART, 1), new Object[] {"A", "B", 'A', Blocks.field_150460_al, 'B', Items.MINECART});
        this.func_92103_a(new ItemStack(Items.TNT_MINECART, 1), new Object[] {"A", "B", 'A', Blocks.field_150335_W, 'B', Items.MINECART});
        this.func_92103_a(new ItemStack(Items.HOPPER_MINECART, 1), new Object[] {"A", "B", 'A', Blocks.field_150438_bZ, 'B', Items.MINECART});
        this.func_92103_a(new ItemStack(Items.BOAT, 1), new Object[] {"# #", "###", '#', Blocks.field_150344_f});
        this.func_92103_a(new ItemStack(Items.BUCKET, 1), new Object[] {"# #", " # ", '#', Items.IRON_INGOT});
        this.func_92103_a(new ItemStack(Items.FLOWER_POT, 1), new Object[] {"# #", " # ", '#', Items.BRICK});
        this.func_77596_b(new ItemStack(Items.FLINT_AND_STEEL, 1), new Object[] {new ItemStack(Items.IRON_INGOT, 1), new ItemStack(Items.FLINT, 1)});
        this.func_92103_a(new ItemStack(Items.BREAD, 1), new Object[] {"###", '#', Items.WHEAT});
        this.func_92103_a(new ItemStack(Blocks.field_150476_ad, 4), new Object[] {"#  ", "## ", "###", '#', new ItemStack(Blocks.field_150344_f, 1, 0)});
        this.func_92103_a(new ItemStack(Blocks.field_150487_bG, 4), new Object[] {"#  ", "## ", "###", '#', new ItemStack(Blocks.field_150344_f, 1, 2)});
        this.func_92103_a(new ItemStack(Blocks.field_150485_bF, 4), new Object[] {"#  ", "## ", "###", '#', new ItemStack(Blocks.field_150344_f, 1, 1)});
        this.func_92103_a(new ItemStack(Blocks.field_150481_bH, 4), new Object[] {"#  ", "## ", "###", '#', new ItemStack(Blocks.field_150344_f, 1, 3)});
        this.func_92103_a(new ItemStack(Blocks.field_150400_ck, 4), new Object[] {"#  ", "## ", "###", '#', new ItemStack(Blocks.field_150344_f, 1, 4)});
        this.func_92103_a(new ItemStack(Blocks.field_150401_cl, 4), new Object[] {"#  ", "## ", "###", '#', new ItemStack(Blocks.field_150344_f, 1, 5)});
        this.func_92103_a(new ItemStack(Items.FISHING_ROD, 1), new Object[] {"  #", " #X", "# X", '#', Items.STICK, 'X', Items.STRING});
        this.func_92103_a(new ItemStack(Items.CARROT_ON_A_STICK, 1), new Object[] {"# ", " X", '#', Items.FISHING_ROD, 'X', Items.CARROT}).func_92100_c();
        this.func_92103_a(new ItemStack(Blocks.field_150446_ar, 4), new Object[] {"#  ", "## ", "###", '#', Blocks.field_150347_e});
        this.func_92103_a(new ItemStack(Blocks.field_150389_bf, 4), new Object[] {"#  ", "## ", "###", '#', Blocks.field_150336_V});
        this.func_92103_a(new ItemStack(Blocks.field_150390_bg, 4), new Object[] {"#  ", "## ", "###", '#', Blocks.field_150417_aV});
        this.func_92103_a(new ItemStack(Blocks.field_150387_bl, 4), new Object[] {"#  ", "## ", "###", '#', Blocks.field_150385_bj});
        this.func_92103_a(new ItemStack(Blocks.field_150372_bz, 4), new Object[] {"#  ", "## ", "###", '#', Blocks.field_150322_A});
        this.func_92103_a(new ItemStack(Blocks.field_150370_cb, 4), new Object[] {"#  ", "## ", "###", '#', Blocks.field_150371_ca});
        this.func_92103_a(new ItemStack(Items.PAINTING, 1), new Object[] {"###", "#X#", "###", '#', Items.STICK, 'X', Blocks.WOOL});
        this.func_92103_a(new ItemStack(Items.ITEM_FRAME, 1), new Object[] {"###", "#X#", "###", '#', Items.STICK, 'X', Items.LEATHER});
        this.func_92103_a(new ItemStack(Items.GOLDEN_APPLE, 1, 0), new Object[] {"###", "#X#", "###", '#', Items.GOLD_INGOT, 'X', Items.APPLE});
        this.func_92103_a(new ItemStack(Items.GOLDEN_APPLE, 1, 1), new Object[] {"###", "#X#", "###", '#', Blocks.field_150340_R, 'X', Items.APPLE});
        this.func_92103_a(new ItemStack(Items.GOLDEN_CARROT, 1, 0), new Object[] {"###", "#X#", "###", '#', Items.GOLD_NUGGET, 'X', Items.CARROT});
        this.func_92103_a(new ItemStack(Items.SPECKLED_MELON, 1), new Object[] {"###", "#X#", "###", '#', Items.GOLD_NUGGET, 'X', Items.MELON});
        this.func_92103_a(new ItemStack(Blocks.field_150442_at, 1), new Object[] {"X", "#", '#', Blocks.field_150347_e, 'X', Items.STICK});
        this.func_92103_a(new ItemStack(Blocks.field_150479_bC, 2), new Object[] {"I", "S", "#", '#', Blocks.field_150344_f, 'S', Items.STICK, 'I', Items.IRON_INGOT});
        this.func_92103_a(new ItemStack(Blocks.field_150429_aA, 1), new Object[] {"X", "#", '#', Items.STICK, 'X', Items.REDSTONE});
        this.func_92103_a(new ItemStack(Items.REPEATER, 1), new Object[] {"#X#", "III", '#', Blocks.field_150429_aA, 'X', Items.REDSTONE, 'I', Blocks.field_150348_b});
        this.func_92103_a(new ItemStack(Items.COMPARATOR, 1), new Object[] {" # ", "#X#", "III", '#', Blocks.field_150429_aA, 'X', Items.QUARTZ, 'I', Blocks.field_150348_b});
        this.func_92103_a(new ItemStack(Items.CLOCK, 1), new Object[] {" # ", "#X#", " # ", '#', Items.GOLD_INGOT, 'X', Items.REDSTONE});
        this.func_92103_a(new ItemStack(Items.COMPASS, 1), new Object[] {" # ", "#X#", " # ", '#', Items.IRON_INGOT, 'X', Items.REDSTONE});
        this.func_92103_a(new ItemStack(Items.MAP, 1), new Object[] {"###", "#X#", "###", '#', Items.PAPER, 'X', Items.COMPASS});
        this.func_92103_a(new ItemStack(Blocks.field_150430_aB, 1), new Object[] {"#", '#', Blocks.field_150348_b});
        this.func_92103_a(new ItemStack(Blocks.field_150471_bO, 1), new Object[] {"#", '#', Blocks.field_150344_f});
        this.func_92103_a(new ItemStack(Blocks.field_150456_au, 1), new Object[] {"##", '#', Blocks.field_150348_b});
        this.func_92103_a(new ItemStack(Blocks.field_150452_aw, 1), new Object[] {"##", '#', Blocks.field_150344_f});
        this.func_92103_a(new ItemStack(Blocks.field_150443_bT, 1), new Object[] {"##", '#', Items.IRON_INGOT});
        this.func_92103_a(new ItemStack(Blocks.field_150445_bS, 1), new Object[] {"##", '#', Items.GOLD_INGOT});
        this.func_92103_a(new ItemStack(Blocks.field_150367_z, 1), new Object[] {"###", "#X#", "#R#", '#', Blocks.field_150347_e, 'X', Items.BOW, 'R', Items.REDSTONE});
        this.func_92103_a(new ItemStack(Blocks.field_150409_cd, 1), new Object[] {"###", "# #", "#R#", '#', Blocks.field_150347_e, 'R', Items.REDSTONE});
        this.func_92103_a(new ItemStack(Blocks.field_150331_J, 1), new Object[] {"TTT", "#X#", "#R#", '#', Blocks.field_150347_e, 'X', Items.IRON_INGOT, 'R', Items.REDSTONE, 'T', Blocks.field_150344_f});
        this.func_92103_a(new ItemStack(Blocks.field_150320_F, 1), new Object[] {"S", "P", 'S', Items.SLIME_BALL, 'P', Blocks.field_150331_J});
        this.func_92103_a(new ItemStack(Items.BED, 1), new Object[] {"###", "XXX", '#', Blocks.WOOL, 'X', Blocks.field_150344_f});
        this.func_92103_a(new ItemStack(Blocks.field_150381_bn, 1), new Object[] {" B ", "D#D", "###", '#', Blocks.field_150343_Z, 'B', Items.BOOK, 'D', Items.DIAMOND});
        this.func_92103_a(new ItemStack(Blocks.field_150467_bQ, 1), new Object[] {"III", " i ", "iii", 'I', Blocks.field_150339_S, 'i', Items.IRON_INGOT});
        this.func_77596_b(new ItemStack(Items.ENDER_EYE, 1), new Object[] {Items.ENDER_PEARL, Items.BLAZE_POWDER});
        this.func_77596_b(new ItemStack(Items.FIRE_CHARGE, 3), new Object[] {Items.GUNPOWDER, Items.BLAZE_POWDER, Items.COAL});
        this.func_77596_b(new ItemStack(Items.FIRE_CHARGE, 3), new Object[] {Items.GUNPOWDER, Items.BLAZE_POWDER, new ItemStack(Items.COAL, 1, 1)});
        this.func_92103_a(new ItemStack(Blocks.field_150453_bW), new Object[] {"GGG", "QQQ", "WWW", 'G', Blocks.field_150359_w, 'Q', Items.QUARTZ, 'W', Blocks.field_150376_bx});
        this.func_92103_a(new ItemStack(Blocks.field_150438_bZ), new Object[] {"I I", "ICI", " I ", 'I', Items.IRON_INGOT, 'C', Blocks.field_150486_ae});
        Collections.sort(this.field_77597_b, new Comparator()
        {
            private static final String __OBFID = "CL_00000091";
            public int compare(IRecipe p_compare_1_, IRecipe p_compare_2_)
            {
                return p_compare_1_ instanceof ShapelessRecipes && p_compare_2_ instanceof ShapedRecipes ? 1 : (p_compare_2_ instanceof ShapelessRecipes && p_compare_1_ instanceof ShapedRecipes ? -1 : (p_compare_2_.func_77570_a() < p_compare_1_.func_77570_a() ? -1 : (p_compare_2_.func_77570_a() > p_compare_1_.func_77570_a() ? 1 : 0)));
            }
            public int compare(Object p_compare_1_, Object p_compare_2_)
            {
                return this.compare((IRecipe)p_compare_1_, (IRecipe)p_compare_2_);
            }
        });
    }

    public ShapedRecipes func_92103_a(ItemStack p_92103_1_, Object ... p_92103_2_)
    {
        String s = "";
        int i = 0;
        int j = 0;
        int k = 0;

        if (p_92103_2_[i] instanceof String[])
        {
            String[] astring = (String[])((String[])p_92103_2_[i++]);

            for (int l = 0; l < astring.length; ++l)
            {
                String s1 = astring[l];
                ++k;
                j = s1.length();
                s = s + s1;
            }
        }
        else
        {
            while (p_92103_2_[i] instanceof String)
            {
                String s2 = (String)p_92103_2_[i++];
                ++k;
                j = s2.length();
                s = s + s2;
            }
        }

        HashMap hashmap;

        for (hashmap = new HashMap(); i < p_92103_2_.length; i += 2)
        {
            Character character = (Character)p_92103_2_[i];
            ItemStack itemstack1 = null;

            if (p_92103_2_[i + 1] instanceof Item)
            {
                itemstack1 = new ItemStack((Item)p_92103_2_[i + 1]);
            }
            else if (p_92103_2_[i + 1] instanceof Block)
            {
                itemstack1 = new ItemStack((Block)p_92103_2_[i + 1], 1, 32767);
            }
            else if (p_92103_2_[i + 1] instanceof ItemStack)
            {
                itemstack1 = (ItemStack)p_92103_2_[i + 1];
            }

            hashmap.put(character, itemstack1);
        }

        ItemStack[] aitemstack = new ItemStack[j * k];

        for (int i1 = 0; i1 < j * k; ++i1)
        {
            char c0 = s.charAt(i1);

            if (hashmap.containsKey(Character.valueOf(c0)))
            {
                aitemstack[i1] = ((ItemStack)hashmap.get(Character.valueOf(c0))).func_77946_l();
            }
            else
            {
                aitemstack[i1] = null;
            }
        }

        ShapedRecipes shapedrecipes = new ShapedRecipes(j, k, aitemstack, p_92103_1_);
        this.field_77597_b.add(shapedrecipes);
        return shapedrecipes;
    }

    public void func_77596_b(ItemStack p_77596_1_, Object ... p_77596_2_)
    {
        ArrayList arraylist = new ArrayList();
        Object[] aobject = p_77596_2_;
        int i = p_77596_2_.length;

        for (int j = 0; j < i; ++j)
        {
            Object object1 = aobject[j];

            if (object1 instanceof ItemStack)
            {
                arraylist.add(((ItemStack)object1).func_77946_l());
            }
            else if (object1 instanceof Item)
            {
                arraylist.add(new ItemStack((Item)object1));
            }
            else
            {
                if (!(object1 instanceof Block))
                {
                    throw new RuntimeException("Invalid shapeless recipy!");
                }

                arraylist.add(new ItemStack((Block)object1));
            }
        }

        this.field_77597_b.add(new ShapelessRecipes(p_77596_1_, arraylist));
    }

    public ItemStack func_82787_a(InventoryCrafting p_82787_1_, World p_82787_2_)
    {
        int i = 0;
        ItemStack itemstack = null;
        ItemStack itemstack1 = null;
        int j;

        for (j = 0; j < p_82787_1_.func_70302_i_(); ++j)
        {
            ItemStack itemstack2 = p_82787_1_.func_70301_a(j);

            if (itemstack2 != null)
            {
                if (i == 0)
                {
                    itemstack = itemstack2;
                }

                if (i == 1)
                {
                    itemstack1 = itemstack2;
                }

                ++i;
            }
        }

        if (i == 2 && itemstack.func_77973_b() == itemstack1.func_77973_b() && itemstack.field_77994_a == 1 && itemstack1.field_77994_a == 1 && itemstack.func_77973_b().func_77645_m())
        {
            Item item = itemstack.func_77973_b();
            int j1 = item.func_77612_l() - itemstack.func_77952_i();
            int k = item.func_77612_l() - itemstack1.func_77952_i();
            int l = j1 + k + item.func_77612_l() * 5 / 100;
            int i1 = item.func_77612_l() - l;

            if (i1 < 0)
            {
                i1 = 0;
            }

            return new ItemStack(itemstack.func_77973_b(), 1, i1);
        }
        else
        {
            for (j = 0; j < this.field_77597_b.size(); ++j)
            {
                IRecipe irecipe = (IRecipe)this.field_77597_b.get(j);

                if (irecipe.func_77569_a(p_82787_1_, p_82787_2_))
                {
                    return irecipe.func_77572_b(p_82787_1_);
                }
            }

            return null;
        }
    }

    public List func_77592_b()
    {
        return this.field_77597_b;
    }
}