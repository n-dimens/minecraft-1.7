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
    private static final CraftingManager INSTANCE = new CraftingManager();
    private final List<IRecipe> recipes = new ArrayList<>();
    private static final String __OBFID = "CL_00000090";

    public static CraftingManager getInstance()
    {
        return INSTANCE;
    }

    private CraftingManager()
    {
        (new RecipesTools()).addRecipes(this);
        (new RecipesWeapons()).addRecipes(this);
        (new RecipesIngots()).addRecipes(this);
        (new RecipesFood()).addRecipes(this);
        (new RecipesCrafting()).addRecipes(this);
        (new RecipesArmor()).addRecipes(this);
        (new RecipesDyes()).addRecipes(this);
        this.recipes.add(new RecipesArmorDyes());
        this.recipes.add(new RecipeBookCloning());
        this.recipes.add(new RecipesMapCloning());
        this.recipes.add(new RecipesMapExtending());
        this.recipes.add(new RecipeFireworks());
        this.addRecipe(new ItemStack(Items.PAPER, 3), "###", '#', Items.REEDS);
        this.addShapelessRecipe(new ItemStack(Items.BOOK, 1), Items.PAPER, Items.PAPER, Items.PAPER, Items.LEATHER);
        this.addShapelessRecipe(new ItemStack(Items.WRITABLE_BOOK, 1), Items.BOOK, new ItemStack(Items.DYE, 1, 0), Items.FEATHER);
        this.addRecipe(new ItemStack(Blocks.FENCE, 2), "###", "###", '#', Items.STICK);
        this.addRecipe(new ItemStack(Blocks.COBBLESTONE_WALL, 6, 0), "###", "###", '#', Blocks.COBBLESTONE);
        this.addRecipe(new ItemStack(Blocks.COBBLESTONE_WALL, 6, 1), "###", "###", '#', Blocks.MOSSY_COBBLESTONE);
        this.addRecipe(new ItemStack(Blocks.NETHER_BRICK_FENCE, 6), "###", "###", '#', Blocks.NETHER_BRICK);
        this.addRecipe(new ItemStack(Blocks.FENCE_GATE, 1), "#W#", "#W#", '#', Items.STICK, 'W', Blocks.PLANKS);
        this.addRecipe(new ItemStack(Blocks.JUKEBOX, 1), "###", "#X#", "###", '#', Blocks.PLANKS, 'X', Items.DIAMOND);
        this.addRecipe(new ItemStack(Items.LEAD, 2), "~~ ", "~O ", "  ~", '~', Items.STRING, 'O', Items.SLIME_BALL);
        this.addRecipe(new ItemStack(Blocks.NOTEBLOCK, 1), "###", "#X#", "###", '#', Blocks.PLANKS, 'X', Items.REDSTONE);
        this.addRecipe(new ItemStack(Blocks.BOOKSHELF, 1), "###", "XXX", "###", '#', Blocks.PLANKS, 'X', Items.BOOK);
        this.addRecipe(new ItemStack(Blocks.SNOW, 1), "##", "##", '#', Items.SNOWBALL);
        this.addRecipe(new ItemStack(Blocks.SNOW_LAYER, 6), "###", '#', Blocks.SNOW);
        this.addRecipe(new ItemStack(Blocks.CLAY, 1), "##", "##", '#', Items.CLAY_BALL);
        this.addRecipe(new ItemStack(Blocks.BRICK_BLOCK, 1), "##", "##", '#', Items.BRICK);
        this.addRecipe(new ItemStack(Blocks.GLOWSTONE, 1), "##", "##", '#', Items.GLOWSTONE_DUST);
        this.addRecipe(new ItemStack(Blocks.QUARTZ_BLOCK, 1), "##", "##", '#', Items.QUARTZ);
        this.addRecipe(new ItemStack(Blocks.WOOL, 1), "##", "##", '#', Items.STRING);
        this.addRecipe(new ItemStack(Blocks.TNT, 1), "X#X", "#X#", "X#X", 'X', Items.GUNPOWDER, '#', Blocks.SAND);
        this.addRecipe(new ItemStack(Blocks.STONE_SLAB, 6, 3), "###", '#', Blocks.COBBLESTONE);
        this.addRecipe(new ItemStack(Blocks.STONE_SLAB, 6, 0), "###", '#', Blocks.STONE);
        this.addRecipe(new ItemStack(Blocks.STONE_SLAB, 6, 1), "###", '#', Blocks.SANDSTONE);
        this.addRecipe(new ItemStack(Blocks.STONE_SLAB, 6, 4), "###", '#', Blocks.BRICK_BLOCK);
        this.addRecipe(new ItemStack(Blocks.STONE_SLAB, 6, 5), "###", '#', Blocks.STONEBRICK);
        this.addRecipe(new ItemStack(Blocks.STONE_SLAB, 6, 6), "###", '#', Blocks.NETHER_BRICK);
        this.addRecipe(new ItemStack(Blocks.STONE_SLAB, 6, 7), "###", '#', Blocks.QUARTZ_BLOCK);
        this.addRecipe(new ItemStack(Blocks.WOODEN_SLAB, 6, 0), "###", '#', new ItemStack(Blocks.PLANKS, 1, 0));
        this.addRecipe(new ItemStack(Blocks.WOODEN_SLAB, 6, 2), "###", '#', new ItemStack(Blocks.PLANKS, 1, 2));
        this.addRecipe(new ItemStack(Blocks.WOODEN_SLAB, 6, 1), "###", '#', new ItemStack(Blocks.PLANKS, 1, 1));
        this.addRecipe(new ItemStack(Blocks.WOODEN_SLAB, 6, 3), "###", '#', new ItemStack(Blocks.PLANKS, 1, 3));
        this.addRecipe(new ItemStack(Blocks.WOODEN_SLAB, 6, 4), "###", '#', new ItemStack(Blocks.PLANKS, 1, 4));
        this.addRecipe(new ItemStack(Blocks.WOODEN_SLAB, 6, 5), "###", '#', new ItemStack(Blocks.PLANKS, 1, 5));
        this.addRecipe(new ItemStack(Blocks.LADDER, 3), "# #", "###", "# #", '#', Items.STICK);
        this.addRecipe(new ItemStack(Items.WOODEN_DOOR, 1), "##", "##", "##", '#', Blocks.PLANKS);
        this.addRecipe(new ItemStack(Blocks.TRAPDOOR, 2), "###", "###", '#', Blocks.PLANKS);
        this.addRecipe(new ItemStack(Items.IRON_DOOR, 1), "##", "##", "##", '#', Items.IRON_INGOT);
        this.addRecipe(new ItemStack(Items.SIGN, 3), "###", "###", " X ", '#', Blocks.PLANKS, 'X', Items.STICK);
        this.addRecipe(new ItemStack(Items.CAKE, 1), "AAA", "BEB", "CCC", 'A', Items.MILK_BUCKET, 'B', Items.SUGAR, 'C', Items.WHEAT, 'E', Items.EGG);
        this.addRecipe(new ItemStack(Items.SUGAR, 1), "#", '#', Items.REEDS);
        this.addRecipe(new ItemStack(Blocks.PLANKS, 4, 0), "#", '#', new ItemStack(Blocks.LOG, 1, 0));
        this.addRecipe(new ItemStack(Blocks.PLANKS, 4, 1), "#", '#', new ItemStack(Blocks.LOG, 1, 1));
        this.addRecipe(new ItemStack(Blocks.PLANKS, 4, 2), "#", '#', new ItemStack(Blocks.LOG, 1, 2));
        this.addRecipe(new ItemStack(Blocks.PLANKS, 4, 3), "#", '#', new ItemStack(Blocks.LOG, 1, 3));
        this.addRecipe(new ItemStack(Blocks.PLANKS, 4, 4), "#", '#', new ItemStack(Blocks.LOG_2, 1, 0));
        this.addRecipe(new ItemStack(Blocks.PLANKS, 4, 5), "#", '#', new ItemStack(Blocks.LOG_2, 1, 1));
        this.addRecipe(new ItemStack(Items.STICK, 4), "#", "#", '#', Blocks.PLANKS);
        this.addRecipe(new ItemStack(Blocks.TORCH, 4), "X", "#", 'X', Items.COAL, '#', Items.STICK);
        this.addRecipe(new ItemStack(Blocks.TORCH, 4), "X", "#", 'X', new ItemStack(Items.COAL, 1, 1), '#', Items.STICK);
        this.addRecipe(new ItemStack(Items.BOWL, 4), "# #", " # ", '#', Blocks.PLANKS);
        this.addRecipe(new ItemStack(Items.GLASS_BOTTLE, 3), "# #", " # ", '#', Blocks.GLASS);
        this.addRecipe(new ItemStack(Blocks.RAIL, 16), "X X", "X#X", "X X", 'X', Items.IRON_INGOT, '#', Items.STICK);
        this.addRecipe(new ItemStack(Blocks.GOLDEN_RAIL, 6), "X X", "X#X", "XRX", 'X', Items.GOLD_INGOT, 'R', Items.REDSTONE, '#', Items.STICK);
        this.addRecipe(new ItemStack(Blocks.ACTIVATOR_RAIL, 6), "XSX", "X#X", "XSX", 'X', Items.IRON_INGOT, '#', Blocks.REDSTONE_TORCH, 'S', Items.STICK);
        this.addRecipe(new ItemStack(Blocks.DETECTOR_RAIL, 6), "X X", "X#X", "XRX", 'X', Items.IRON_INGOT, 'R', Items.REDSTONE, '#', Blocks.STONE_PRESSURE_PLATE);
        this.addRecipe(new ItemStack(Items.MINECART, 1), "# #", "###", '#', Items.IRON_INGOT);
        this.addRecipe(new ItemStack(Items.CAULDRON, 1), "# #", "# #", "###", '#', Items.IRON_INGOT);
        this.addRecipe(new ItemStack(Items.BREWING_STAND, 1), " B ", "###", '#', Blocks.COBBLESTONE, 'B', Items.BLAZE_ROD);
        this.addRecipe(new ItemStack(Blocks.LIT_PUMPKIN, 1), "A", "B", 'A', Blocks.PUMPKIN, 'B', Blocks.TORCH);
        this.addRecipe(new ItemStack(Items.CHEST_MINECART, 1), "A", "B", 'A', Blocks.CHEST, 'B', Items.MINECART);
        this.addRecipe(new ItemStack(Items.FURNACE_MINECART, 1), "A", "B", 'A', Blocks.FURNACE, 'B', Items.MINECART);
        this.addRecipe(new ItemStack(Items.TNT_MINECART, 1), "A", "B", 'A', Blocks.TNT, 'B', Items.MINECART);
        this.addRecipe(new ItemStack(Items.HOPPER_MINECART, 1), "A", "B", 'A', Blocks.HOPPER, 'B', Items.MINECART);
        this.addRecipe(new ItemStack(Items.BOAT, 1), "# #", "###", '#', Blocks.PLANKS);
        this.addRecipe(new ItemStack(Items.BUCKET, 1), "# #", " # ", '#', Items.IRON_INGOT);
        this.addRecipe(new ItemStack(Items.FLOWER_POT, 1), "# #", " # ", '#', Items.BRICK);
        this.addShapelessRecipe(new ItemStack(Items.FLINT_AND_STEEL, 1), new ItemStack(Items.IRON_INGOT, 1), new ItemStack(Items.FLINT, 1));
        this.addRecipe(new ItemStack(Items.BREAD, 1), "###", '#', Items.WHEAT);
        this.addRecipe(new ItemStack(Blocks.OAK_STAIRS, 4), "#  ", "## ", "###", '#', new ItemStack(Blocks.PLANKS, 1, 0));
        this.addRecipe(new ItemStack(Blocks.BIRCH_STAIRS, 4), "#  ", "## ", "###", '#', new ItemStack(Blocks.PLANKS, 1, 2));
        this.addRecipe(new ItemStack(Blocks.SPRUCE_STAIRS, 4), "#  ", "## ", "###", '#', new ItemStack(Blocks.PLANKS, 1, 1));
        this.addRecipe(new ItemStack(Blocks.JUNGLE_STAIRS, 4), "#  ", "## ", "###", '#', new ItemStack(Blocks.PLANKS, 1, 3));
        this.addRecipe(new ItemStack(Blocks.ACACIA_STAIRS, 4), "#  ", "## ", "###", '#', new ItemStack(Blocks.PLANKS, 1, 4));
        this.addRecipe(new ItemStack(Blocks.DARK_OAK_STAIRS, 4), "#  ", "## ", "###", '#', new ItemStack(Blocks.PLANKS, 1, 5));
        this.addRecipe(new ItemStack(Items.FISHING_ROD, 1), "  #", " #X", "# X", '#', Items.STICK, 'X', Items.STRING);
        this.addRecipe(new ItemStack(Items.CARROT_ON_A_STICK, 1), "# ", " X", '#', Items.FISHING_ROD, 'X', Items.CARROT).func_92100_c();
        this.addRecipe(new ItemStack(Blocks.STONE_STAIRS, 4), "#  ", "## ", "###", '#', Blocks.COBBLESTONE);
        this.addRecipe(new ItemStack(Blocks.BRICK_STAIRS, 4), "#  ", "## ", "###", '#', Blocks.BRICK_BLOCK);
        this.addRecipe(new ItemStack(Blocks.STONE_BRICK_STAIRS, 4), "#  ", "## ", "###", '#', Blocks.STONEBRICK);
        this.addRecipe(new ItemStack(Blocks.NETHER_BRICK_STAIRS, 4), "#  ", "## ", "###", '#', Blocks.NETHER_BRICK);
        this.addRecipe(new ItemStack(Blocks.SANDSTONE_STAIRS, 4), "#  ", "## ", "###", '#', Blocks.SANDSTONE);
        this.addRecipe(new ItemStack(Blocks.QUARTZ_STAIRS, 4), "#  ", "## ", "###", '#', Blocks.QUARTZ_BLOCK);
        this.addRecipe(new ItemStack(Items.PAINTING, 1), "###", "#X#", "###", '#', Items.STICK, 'X', Blocks.WOOL);
        this.addRecipe(new ItemStack(Items.ITEM_FRAME, 1), "###", "#X#", "###", '#', Items.STICK, 'X', Items.LEATHER);
        this.addRecipe(new ItemStack(Items.GOLDEN_APPLE, 1, 0), "###", "#X#", "###", '#', Items.GOLD_INGOT, 'X', Items.APPLE);
        this.addRecipe(new ItemStack(Items.GOLDEN_APPLE, 1, 1), "###", "#X#", "###", '#', Blocks.GOLD_BLOCK, 'X', Items.APPLE);
        this.addRecipe(new ItemStack(Items.GOLDEN_CARROT, 1, 0), "###", "#X#", "###", '#', Items.GOLD_NUGGET, 'X', Items.CARROT);
        this.addRecipe(new ItemStack(Items.SPECKLED_MELON, 1), "###", "#X#", "###", '#', Items.GOLD_NUGGET, 'X', Items.MELON);
        this.addRecipe(new ItemStack(Blocks.LEVER, 1), "X", "#", '#', Blocks.COBBLESTONE, 'X', Items.STICK);
        this.addRecipe(new ItemStack(Blocks.TRIPWIRE_HOOK, 2), "I", "S", "#", '#', Blocks.PLANKS, 'S', Items.STICK, 'I', Items.IRON_INGOT);
        this.addRecipe(new ItemStack(Blocks.REDSTONE_TORCH, 1), "X", "#", '#', Items.STICK, 'X', Items.REDSTONE);
        this.addRecipe(new ItemStack(Items.REPEATER, 1), "#X#", "III", '#', Blocks.REDSTONE_TORCH, 'X', Items.REDSTONE, 'I', Blocks.STONE);
        this.addRecipe(new ItemStack(Items.COMPARATOR, 1), " # ", "#X#", "III", '#', Blocks.REDSTONE_TORCH, 'X', Items.QUARTZ, 'I', Blocks.STONE);
        this.addRecipe(new ItemStack(Items.CLOCK, 1), " # ", "#X#", " # ", '#', Items.GOLD_INGOT, 'X', Items.REDSTONE);
        this.addRecipe(new ItemStack(Items.COMPASS, 1), " # ", "#X#", " # ", '#', Items.IRON_INGOT, 'X', Items.REDSTONE);
        this.addRecipe(new ItemStack(Items.MAP, 1), "###", "#X#", "###", '#', Items.PAPER, 'X', Items.COMPASS);
        this.addRecipe(new ItemStack(Blocks.STONE_BUTTON, 1), "#", '#', Blocks.STONE);
        this.addRecipe(new ItemStack(Blocks.WOODEN_BUTTON, 1), "#", '#', Blocks.PLANKS);
        this.addRecipe(new ItemStack(Blocks.STONE_PRESSURE_PLATE, 1), "##", '#', Blocks.STONE);
        this.addRecipe(new ItemStack(Blocks.WOODEN_PRESSURE_PLATE, 1), "##", '#', Blocks.PLANKS);
        this.addRecipe(new ItemStack(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE, 1), "##", '#', Items.IRON_INGOT);
        this.addRecipe(new ItemStack(Blocks.LIGHT_WEIGHTED_PRESSURE_PLATE, 1), "##", '#', Items.GOLD_INGOT);
        this.addRecipe(new ItemStack(Blocks.DISPENSER, 1), "###", "#X#", "#R#", '#', Blocks.COBBLESTONE, 'X', Items.BOW, 'R', Items.REDSTONE);
        this.addRecipe(new ItemStack(Blocks.DROPPER, 1), "###", "# #", "#R#", '#', Blocks.COBBLESTONE, 'R', Items.REDSTONE);
        this.addRecipe(new ItemStack(Blocks.PISTON, 1), "TTT", "#X#", "#R#", '#', Blocks.COBBLESTONE, 'X', Items.IRON_INGOT, 'R', Items.REDSTONE, 'T', Blocks.PLANKS);
        this.addRecipe(new ItemStack(Blocks.STICKY_PISTON, 1), "S", "P", 'S', Items.SLIME_BALL, 'P', Blocks.PISTON);
        this.addRecipe(new ItemStack(Items.BED, 1), "###", "XXX", '#', Blocks.WOOL, 'X', Blocks.PLANKS);
        this.addRecipe(new ItemStack(Blocks.ENCHANTING_TABLE, 1), " B ", "D#D", "###", '#', Blocks.OBSIDIAN, 'B', Items.BOOK, 'D', Items.DIAMOND);
        this.addRecipe(new ItemStack(Blocks.ANVIL, 1), "III", " i ", "iii", 'I', Blocks.IRON_BLOCK, 'i', Items.IRON_INGOT);
        this.addShapelessRecipe(new ItemStack(Items.ENDER_EYE, 1), Items.ENDER_PEARL, Items.BLAZE_POWDER);
        this.addShapelessRecipe(new ItemStack(Items.FIRE_CHARGE, 3), Items.GUNPOWDER, Items.BLAZE_POWDER, Items.COAL);
        this.addShapelessRecipe(new ItemStack(Items.FIRE_CHARGE, 3), Items.GUNPOWDER, Items.BLAZE_POWDER, new ItemStack(Items.COAL, 1, 1));
        this.addRecipe(new ItemStack(Blocks.DAYLIGHT_DETECTOR), "GGG", "QQQ", "WWW", 'G', Blocks.GLASS, 'Q', Items.QUARTZ, 'W', Blocks.WOODEN_SLAB);
        this.addRecipe(new ItemStack(Blocks.HOPPER), "I I", "ICI", " I ", 'I', Items.IRON_INGOT, 'C', Blocks.CHEST);
        this.recipes.sort(new RecipeComparator());
    }

    public ShapedRecipes addRecipe(ItemStack p_92103_1_, Object ... p_92103_2_)
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
        this.recipes.add(shapedrecipes);
        return shapedrecipes;
    }

    public void addShapelessRecipe(ItemStack p_77596_1_, Object ... p_77596_2_)
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

        this.recipes.add(new ShapelessRecipes(p_77596_1_, arraylist));
    }

    public ItemStack func_82787_a(InventoryCrafting inventory, World world)
    {
        int i = 0;
        ItemStack itemstack = null;
        ItemStack itemstack1 = null;
        int j;

        for (j = 0; j < inventory.func_70302_i_(); ++j)
        {
            ItemStack itemstack2 = inventory.func_70301_a(j);

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

        if (i == 2 && itemstack.getBaseItem() == itemstack1.getBaseItem() && itemstack.count == 1 && itemstack1.count == 1 && itemstack.getBaseItem().func_77645_m())
        {
            Item item = itemstack.getBaseItem();
            int j1 = item.getDurability() - itemstack.func_77952_i();
            int k = item.getDurability() - itemstack1.func_77952_i();
            int l = j1 + k + item.getDurability() * 5 / 100;
            int i1 = item.getDurability() - l;

            if (i1 < 0)
            {
                i1 = 0;
            }

            return new ItemStack(itemstack.getBaseItem(), 1, i1);
        }
        else
        {
            for (j = 0; j < this.recipes.size(); ++j)
            {
                IRecipe irecipe = (IRecipe)this.recipes.get(j);

                if (irecipe.func_77569_a(inventory, world))
                {
                    return irecipe.func_77572_b(inventory);
                }
            }

            return null;
        }
    }

    public List<IRecipe> getRecipes()
    {
        return Collections.unmodifiableList(this.recipes);
    }

    private static class RecipeComparator implements Comparator<IRecipe> {
        private static final String __OBFID = "CL_00000091";

        @Override
        public int compare(IRecipe recipe1, IRecipe recipe2) {
            if (recipe1 instanceof ShapelessRecipes && recipe2 instanceof ShapedRecipes) {
                return 1;
            }

            if (recipe2 instanceof ShapelessRecipes && recipe1 instanceof ShapedRecipes) {
                return -1;
            }

            return Integer.compare(recipe2.func_77570_a(), recipe1.func_77570_a());
        }
    }
}