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
        this.func_92103_a(new ItemStack(Blocks.FENCE, 2), new Object[] {"###", "###", '#', Items.STICK});
        this.func_92103_a(new ItemStack(Blocks.COBBLESTONE_WALL, 6, 0), new Object[] {"###", "###", '#', Blocks.COBBLESTONE});
        this.func_92103_a(new ItemStack(Blocks.COBBLESTONE_WALL, 6, 1), new Object[] {"###", "###", '#', Blocks.MOSSY_COBBLESTONE});
        this.func_92103_a(new ItemStack(Blocks.NETHER_BRICK_FENCE, 6), new Object[] {"###", "###", '#', Blocks.NETHER_BRICK});
        this.func_92103_a(new ItemStack(Blocks.FENCE_GATE, 1), new Object[] {"#W#", "#W#", '#', Items.STICK, 'W', Blocks.PLANKS});
        this.func_92103_a(new ItemStack(Blocks.JUKEBOX, 1), new Object[] {"###", "#X#", "###", '#', Blocks.PLANKS, 'X', Items.DIAMOND});
        this.func_92103_a(new ItemStack(Items.LEAD, 2), new Object[] {"~~ ", "~O ", "  ~", '~', Items.STRING, 'O', Items.SLIME_BALL});
        this.func_92103_a(new ItemStack(Blocks.NOTEBLOCK, 1), new Object[] {"###", "#X#", "###", '#', Blocks.PLANKS, 'X', Items.REDSTONE});
        this.func_92103_a(new ItemStack(Blocks.BOOKSHELF, 1), new Object[] {"###", "XXX", "###", '#', Blocks.PLANKS, 'X', Items.BOOK});
        this.func_92103_a(new ItemStack(Blocks.SNOW, 1), new Object[] {"##", "##", '#', Items.SNOWBALL});
        this.func_92103_a(new ItemStack(Blocks.SNOW_LAYER, 6), new Object[] {"###", '#', Blocks.SNOW});
        this.func_92103_a(new ItemStack(Blocks.CLAY, 1), new Object[] {"##", "##", '#', Items.CLAY_BALL});
        this.func_92103_a(new ItemStack(Blocks.BRICK_BLOCK, 1), new Object[] {"##", "##", '#', Items.BRICK});
        this.func_92103_a(new ItemStack(Blocks.GLOWSTONE, 1), new Object[] {"##", "##", '#', Items.GLOWSTONE_DUST});
        this.func_92103_a(new ItemStack(Blocks.QUARTZ_BLOCK, 1), new Object[] {"##", "##", '#', Items.QUARTZ});
        this.func_92103_a(new ItemStack(Blocks.WOOL, 1), new Object[] {"##", "##", '#', Items.STRING});
        this.func_92103_a(new ItemStack(Blocks.TNT, 1), new Object[] {"X#X", "#X#", "X#X", 'X', Items.GUNPOWDER, '#', Blocks.SAND});
        this.func_92103_a(new ItemStack(Blocks.STONE_SLAB, 6, 3), new Object[] {"###", '#', Blocks.COBBLESTONE});
        this.func_92103_a(new ItemStack(Blocks.STONE_SLAB, 6, 0), new Object[] {"###", '#', Blocks.STONE});
        this.func_92103_a(new ItemStack(Blocks.STONE_SLAB, 6, 1), new Object[] {"###", '#', Blocks.SANDSTONE});
        this.func_92103_a(new ItemStack(Blocks.STONE_SLAB, 6, 4), new Object[] {"###", '#', Blocks.BRICK_BLOCK});
        this.func_92103_a(new ItemStack(Blocks.STONE_SLAB, 6, 5), new Object[] {"###", '#', Blocks.STONEBRICK});
        this.func_92103_a(new ItemStack(Blocks.STONE_SLAB, 6, 6), new Object[] {"###", '#', Blocks.NETHER_BRICK});
        this.func_92103_a(new ItemStack(Blocks.STONE_SLAB, 6, 7), new Object[] {"###", '#', Blocks.QUARTZ_BLOCK});
        this.func_92103_a(new ItemStack(Blocks.WOODEN_SLAB, 6, 0), new Object[] {"###", '#', new ItemStack(Blocks.PLANKS, 1, 0)});
        this.func_92103_a(new ItemStack(Blocks.WOODEN_SLAB, 6, 2), new Object[] {"###", '#', new ItemStack(Blocks.PLANKS, 1, 2)});
        this.func_92103_a(new ItemStack(Blocks.WOODEN_SLAB, 6, 1), new Object[] {"###", '#', new ItemStack(Blocks.PLANKS, 1, 1)});
        this.func_92103_a(new ItemStack(Blocks.WOODEN_SLAB, 6, 3), new Object[] {"###", '#', new ItemStack(Blocks.PLANKS, 1, 3)});
        this.func_92103_a(new ItemStack(Blocks.WOODEN_SLAB, 6, 4), new Object[] {"###", '#', new ItemStack(Blocks.PLANKS, 1, 4)});
        this.func_92103_a(new ItemStack(Blocks.WOODEN_SLAB, 6, 5), new Object[] {"###", '#', new ItemStack(Blocks.PLANKS, 1, 5)});
        this.func_92103_a(new ItemStack(Blocks.LADDER, 3), new Object[] {"# #", "###", "# #", '#', Items.STICK});
        this.func_92103_a(new ItemStack(Items.WOODEN_DOOR, 1), new Object[] {"##", "##", "##", '#', Blocks.PLANKS});
        this.func_92103_a(new ItemStack(Blocks.TRAPDOOR, 2), new Object[] {"###", "###", '#', Blocks.PLANKS});
        this.func_92103_a(new ItemStack(Items.IRON_DOOR, 1), new Object[] {"##", "##", "##", '#', Items.IRON_INGOT});
        this.func_92103_a(new ItemStack(Items.SIGN, 3), new Object[] {"###", "###", " X ", '#', Blocks.PLANKS, 'X', Items.STICK});
        this.func_92103_a(new ItemStack(Items.CAKE, 1), new Object[] {"AAA", "BEB", "CCC", 'A', Items.MILK_BUCKET, 'B', Items.SUGAR, 'C', Items.WHEAT, 'E', Items.EGG});
        this.func_92103_a(new ItemStack(Items.SUGAR, 1), new Object[] {"#", '#', Items.REEDS});
        this.func_92103_a(new ItemStack(Blocks.PLANKS, 4, 0), new Object[] {"#", '#', new ItemStack(Blocks.LOG, 1, 0)});
        this.func_92103_a(new ItemStack(Blocks.PLANKS, 4, 1), new Object[] {"#", '#', new ItemStack(Blocks.LOG, 1, 1)});
        this.func_92103_a(new ItemStack(Blocks.PLANKS, 4, 2), new Object[] {"#", '#', new ItemStack(Blocks.LOG, 1, 2)});
        this.func_92103_a(new ItemStack(Blocks.PLANKS, 4, 3), new Object[] {"#", '#', new ItemStack(Blocks.LOG, 1, 3)});
        this.func_92103_a(new ItemStack(Blocks.PLANKS, 4, 4), new Object[] {"#", '#', new ItemStack(Blocks.LOG_2, 1, 0)});
        this.func_92103_a(new ItemStack(Blocks.PLANKS, 4, 5), new Object[] {"#", '#', new ItemStack(Blocks.LOG_2, 1, 1)});
        this.func_92103_a(new ItemStack(Items.STICK, 4), new Object[] {"#", "#", '#', Blocks.PLANKS});
        this.func_92103_a(new ItemStack(Blocks.TORCH, 4), new Object[] {"X", "#", 'X', Items.COAL, '#', Items.STICK});
        this.func_92103_a(new ItemStack(Blocks.TORCH, 4), new Object[] {"X", "#", 'X', new ItemStack(Items.COAL, 1, 1), '#', Items.STICK});
        this.func_92103_a(new ItemStack(Items.BOWL, 4), new Object[] {"# #", " # ", '#', Blocks.PLANKS});
        this.func_92103_a(new ItemStack(Items.GLASS_BOTTLE, 3), new Object[] {"# #", " # ", '#', Blocks.GLASS});
        this.func_92103_a(new ItemStack(Blocks.RAIL, 16), new Object[] {"X X", "X#X", "X X", 'X', Items.IRON_INGOT, '#', Items.STICK});
        this.func_92103_a(new ItemStack(Blocks.GOLDEN_RAIL, 6), new Object[] {"X X", "X#X", "XRX", 'X', Items.GOLD_INGOT, 'R', Items.REDSTONE, '#', Items.STICK});
        this.func_92103_a(new ItemStack(Blocks.ACTIVATOR_RAIL, 6), new Object[] {"XSX", "X#X", "XSX", 'X', Items.IRON_INGOT, '#', Blocks.REDSTONE_TORCH, 'S', Items.STICK});
        this.func_92103_a(new ItemStack(Blocks.DETECTOR_RAIL, 6), new Object[] {"X X", "X#X", "XRX", 'X', Items.IRON_INGOT, 'R', Items.REDSTONE, '#', Blocks.STONE_PRESSURE_PLATE});
        this.func_92103_a(new ItemStack(Items.MINECART, 1), new Object[] {"# #", "###", '#', Items.IRON_INGOT});
        this.func_92103_a(new ItemStack(Items.CAULDRON, 1), new Object[] {"# #", "# #", "###", '#', Items.IRON_INGOT});
        this.func_92103_a(new ItemStack(Items.BREWING_STAND, 1), new Object[] {" B ", "###", '#', Blocks.COBBLESTONE, 'B', Items.BLAZE_ROD});
        this.func_92103_a(new ItemStack(Blocks.LIT_PUMPKIN, 1), new Object[] {"A", "B", 'A', Blocks.PUMPKIN, 'B', Blocks.TORCH});
        this.func_92103_a(new ItemStack(Items.CHEST_MINECART, 1), new Object[] {"A", "B", 'A', Blocks.CHEST, 'B', Items.MINECART});
        this.func_92103_a(new ItemStack(Items.FURNACE_MINECART, 1), new Object[] {"A", "B", 'A', Blocks.FURNACE, 'B', Items.MINECART});
        this.func_92103_a(new ItemStack(Items.TNT_MINECART, 1), new Object[] {"A", "B", 'A', Blocks.TNT, 'B', Items.MINECART});
        this.func_92103_a(new ItemStack(Items.HOPPER_MINECART, 1), new Object[] {"A", "B", 'A', Blocks.HOPPER, 'B', Items.MINECART});
        this.func_92103_a(new ItemStack(Items.BOAT, 1), new Object[] {"# #", "###", '#', Blocks.PLANKS});
        this.func_92103_a(new ItemStack(Items.BUCKET, 1), new Object[] {"# #", " # ", '#', Items.IRON_INGOT});
        this.func_92103_a(new ItemStack(Items.FLOWER_POT, 1), new Object[] {"# #", " # ", '#', Items.BRICK});
        this.func_77596_b(new ItemStack(Items.FLINT_AND_STEEL, 1), new Object[] {new ItemStack(Items.IRON_INGOT, 1), new ItemStack(Items.FLINT, 1)});
        this.func_92103_a(new ItemStack(Items.BREAD, 1), new Object[] {"###", '#', Items.WHEAT});
        this.func_92103_a(new ItemStack(Blocks.OAK_STAIRS, 4), new Object[] {"#  ", "## ", "###", '#', new ItemStack(Blocks.PLANKS, 1, 0)});
        this.func_92103_a(new ItemStack(Blocks.BIRCH_STAIRS, 4), new Object[] {"#  ", "## ", "###", '#', new ItemStack(Blocks.PLANKS, 1, 2)});
        this.func_92103_a(new ItemStack(Blocks.SPRUCE_STAIRS, 4), new Object[] {"#  ", "## ", "###", '#', new ItemStack(Blocks.PLANKS, 1, 1)});
        this.func_92103_a(new ItemStack(Blocks.JUNGLE_STAIRS, 4), new Object[] {"#  ", "## ", "###", '#', new ItemStack(Blocks.PLANKS, 1, 3)});
        this.func_92103_a(new ItemStack(Blocks.ACACIA_STAIRS, 4), new Object[] {"#  ", "## ", "###", '#', new ItemStack(Blocks.PLANKS, 1, 4)});
        this.func_92103_a(new ItemStack(Blocks.DARK_OAK_STAIRS, 4), new Object[] {"#  ", "## ", "###", '#', new ItemStack(Blocks.PLANKS, 1, 5)});
        this.func_92103_a(new ItemStack(Items.FISHING_ROD, 1), new Object[] {"  #", " #X", "# X", '#', Items.STICK, 'X', Items.STRING});
        this.func_92103_a(new ItemStack(Items.CARROT_ON_A_STICK, 1), new Object[] {"# ", " X", '#', Items.FISHING_ROD, 'X', Items.CARROT}).func_92100_c();
        this.func_92103_a(new ItemStack(Blocks.STONE_STAIRS, 4), new Object[] {"#  ", "## ", "###", '#', Blocks.COBBLESTONE});
        this.func_92103_a(new ItemStack(Blocks.BRICK_STAIRS, 4), new Object[] {"#  ", "## ", "###", '#', Blocks.BRICK_BLOCK});
        this.func_92103_a(new ItemStack(Blocks.STONE_BRICK_STAIRS, 4), new Object[] {"#  ", "## ", "###", '#', Blocks.STONEBRICK});
        this.func_92103_a(new ItemStack(Blocks.NETHER_BRICK_STAIRS, 4), new Object[] {"#  ", "## ", "###", '#', Blocks.NETHER_BRICK});
        this.func_92103_a(new ItemStack(Blocks.SANDSTONE_STAIRS, 4), new Object[] {"#  ", "## ", "###", '#', Blocks.SANDSTONE});
        this.func_92103_a(new ItemStack(Blocks.QUARTZ_STAIRS, 4), new Object[] {"#  ", "## ", "###", '#', Blocks.QUARTZ_BLOCK});
        this.func_92103_a(new ItemStack(Items.PAINTING, 1), new Object[] {"###", "#X#", "###", '#', Items.STICK, 'X', Blocks.WOOL});
        this.func_92103_a(new ItemStack(Items.ITEM_FRAME, 1), new Object[] {"###", "#X#", "###", '#', Items.STICK, 'X', Items.LEATHER});
        this.func_92103_a(new ItemStack(Items.GOLDEN_APPLE, 1, 0), new Object[] {"###", "#X#", "###", '#', Items.GOLD_INGOT, 'X', Items.APPLE});
        this.func_92103_a(new ItemStack(Items.GOLDEN_APPLE, 1, 1), new Object[] {"###", "#X#", "###", '#', Blocks.GOLD_BLOCK, 'X', Items.APPLE});
        this.func_92103_a(new ItemStack(Items.GOLDEN_CARROT, 1, 0), new Object[] {"###", "#X#", "###", '#', Items.GOLD_NUGGET, 'X', Items.CARROT});
        this.func_92103_a(new ItemStack(Items.SPECKLED_MELON, 1), new Object[] {"###", "#X#", "###", '#', Items.GOLD_NUGGET, 'X', Items.MELON});
        this.func_92103_a(new ItemStack(Blocks.LEVER, 1), new Object[] {"X", "#", '#', Blocks.COBBLESTONE, 'X', Items.STICK});
        this.func_92103_a(new ItemStack(Blocks.TRIPWIRE_HOOK, 2), new Object[] {"I", "S", "#", '#', Blocks.PLANKS, 'S', Items.STICK, 'I', Items.IRON_INGOT});
        this.func_92103_a(new ItemStack(Blocks.REDSTONE_TORCH, 1), new Object[] {"X", "#", '#', Items.STICK, 'X', Items.REDSTONE});
        this.func_92103_a(new ItemStack(Items.REPEATER, 1), new Object[] {"#X#", "III", '#', Blocks.REDSTONE_TORCH, 'X', Items.REDSTONE, 'I', Blocks.STONE});
        this.func_92103_a(new ItemStack(Items.COMPARATOR, 1), new Object[] {" # ", "#X#", "III", '#', Blocks.REDSTONE_TORCH, 'X', Items.QUARTZ, 'I', Blocks.STONE});
        this.func_92103_a(new ItemStack(Items.CLOCK, 1), new Object[] {" # ", "#X#", " # ", '#', Items.GOLD_INGOT, 'X', Items.REDSTONE});
        this.func_92103_a(new ItemStack(Items.COMPASS, 1), new Object[] {" # ", "#X#", " # ", '#', Items.IRON_INGOT, 'X', Items.REDSTONE});
        this.func_92103_a(new ItemStack(Items.MAP, 1), new Object[] {"###", "#X#", "###", '#', Items.PAPER, 'X', Items.COMPASS});
        this.func_92103_a(new ItemStack(Blocks.STONE_BUTTON, 1), new Object[] {"#", '#', Blocks.STONE});
        this.func_92103_a(new ItemStack(Blocks.WOODEN_BUTTON, 1), new Object[] {"#", '#', Blocks.PLANKS});
        this.func_92103_a(new ItemStack(Blocks.STONE_PRESSURE_PLATE, 1), new Object[] {"##", '#', Blocks.STONE});
        this.func_92103_a(new ItemStack(Blocks.WOODEN_PRESSURE_PLATE, 1), new Object[] {"##", '#', Blocks.PLANKS});
        this.func_92103_a(new ItemStack(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE, 1), new Object[] {"##", '#', Items.IRON_INGOT});
        this.func_92103_a(new ItemStack(Blocks.LIGHT_WEIGHTED_PRESSURE_PLATE, 1), new Object[] {"##", '#', Items.GOLD_INGOT});
        this.func_92103_a(new ItemStack(Blocks.DISPENSER, 1), new Object[] {"###", "#X#", "#R#", '#', Blocks.COBBLESTONE, 'X', Items.BOW, 'R', Items.REDSTONE});
        this.func_92103_a(new ItemStack(Blocks.DROPPER, 1), new Object[] {"###", "# #", "#R#", '#', Blocks.COBBLESTONE, 'R', Items.REDSTONE});
        this.func_92103_a(new ItemStack(Blocks.PISTON, 1), new Object[] {"TTT", "#X#", "#R#", '#', Blocks.COBBLESTONE, 'X', Items.IRON_INGOT, 'R', Items.REDSTONE, 'T', Blocks.PLANKS});
        this.func_92103_a(new ItemStack(Blocks.STICKY_PISTON, 1), new Object[] {"S", "P", 'S', Items.SLIME_BALL, 'P', Blocks.PISTON});
        this.func_92103_a(new ItemStack(Items.BED, 1), new Object[] {"###", "XXX", '#', Blocks.WOOL, 'X', Blocks.PLANKS});
        this.func_92103_a(new ItemStack(Blocks.ENCHANTING_TABLE, 1), new Object[] {" B ", "D#D", "###", '#', Blocks.OBSIDIAN, 'B', Items.BOOK, 'D', Items.DIAMOND});
        this.func_92103_a(new ItemStack(Blocks.ANVIL, 1), new Object[] {"III", " i ", "iii", 'I', Blocks.IRON_BLOCK, 'i', Items.IRON_INGOT});
        this.func_77596_b(new ItemStack(Items.ENDER_EYE, 1), new Object[] {Items.ENDER_PEARL, Items.BLAZE_POWDER});
        this.func_77596_b(new ItemStack(Items.FIRE_CHARGE, 3), new Object[] {Items.GUNPOWDER, Items.BLAZE_POWDER, Items.COAL});
        this.func_77596_b(new ItemStack(Items.FIRE_CHARGE, 3), new Object[] {Items.GUNPOWDER, Items.BLAZE_POWDER, new ItemStack(Items.COAL, 1, 1)});
        this.func_92103_a(new ItemStack(Blocks.DAYLIGHT_DETECTOR), new Object[] {"GGG", "QQQ", "WWW", 'G', Blocks.GLASS, 'Q', Items.QUARTZ, 'W', Blocks.WOODEN_SLAB});
        this.func_92103_a(new ItemStack(Blocks.HOPPER), new Object[] {"I I", "ICI", " I ", 'I', Items.IRON_INGOT, 'C', Blocks.CHEST});
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