package net.minecraft.item.crafting;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFishFood;
import net.minecraft.item.ItemStack;

public class FurnaceRecipes
{
    private static final FurnaceRecipes INSTANCE = new FurnaceRecipes();
    private final Map<ItemStack, ItemStack> recipes = new HashMap<>();
    private final Map<ItemStack, Float> field_77605_c = new HashMap<>();
    private static final String __OBFID = "CL_00000085";

    public static FurnaceRecipes getInstance()
    {
        return INSTANCE;
    }

    private FurnaceRecipes()
    {
        this.addRecipe(Blocks.IRON_ORE, new ItemStack(Items.IRON_INGOT), 0.7F);
        this.addRecipe(Blocks.GOLD_ORE, new ItemStack(Items.GOLD_INGOT), 1.0F);
        this.addRecipe(Blocks.DIAMOND_ORE, new ItemStack(Items.DIAMOND), 1.0F);
        this.addRecipe(Blocks.SAND, new ItemStack(Blocks.GLASS), 0.1F);
        this.addRecipe(Items.PORKCHOP, new ItemStack(Items.COOKED_PORKCHOP), 0.35F);
        this.addRecipe(Items.BEEF, new ItemStack(Items.COOKED_BEEF), 0.35F);
        this.addRecipe(Items.CHICKEN, new ItemStack(Items.COOKED_CHICKEN), 0.35F);
        this.addRecipe(Blocks.COBBLESTONE, new ItemStack(Blocks.STONE), 0.1F);
        this.addRecipe(Items.CLAY_BALL, new ItemStack(Items.BRICK), 0.3F);
        this.addRecipe(Blocks.CLAY, new ItemStack(Blocks.HARDENED_CLAY), 0.35F);
        this.addRecipe(Blocks.CACTUS, new ItemStack(Items.DYE, 1, 2), 0.2F);
        this.addRecipe(Blocks.LOG, new ItemStack(Items.COAL, 1, 1), 0.15F);
        this.addRecipe(Blocks.LOG_2, new ItemStack(Items.COAL, 1, 1), 0.15F);
        this.addRecipe(Blocks.EMERALD_ORE, new ItemStack(Items.EMERALD), 1.0F);
        this.addRecipe(Items.POTATO, new ItemStack(Items.BAKED_POTATO), 0.35F);
        this.addRecipe(Blocks.NETHERRACK, new ItemStack(Items.NETHERBRICK), 0.1F);
        this.addFishCookingRecipes();
        this.addRecipe(Blocks.COAL_ORE, new ItemStack(Items.COAL), 0.1F);
        this.addRecipe(Blocks.REDSTONE_ORE, new ItemStack(Items.REDSTONE), 0.7F);
        this.addRecipe(Blocks.LAPIS_ORE, new ItemStack(Items.DYE, 1, 4), 0.2F);
        this.addRecipe(Blocks.QUARTZ_ORE, new ItemStack(Items.QUARTZ), 0.2F);
    }

    public void addRecipe(Block inputBlock, ItemStack outputItem, float p_151393_3_)
    {
        this.addRecipe(Item.func_150898_a(inputBlock), outputItem, p_151393_3_);
    }

    public void addRecipe(Item inputItem, ItemStack outputItem, float p_151396_3_)
    {
        this.addRecipe(new ItemStack(inputItem, 1, 32767), outputItem, p_151396_3_);
    }

    public void addRecipe(ItemStack inputItem, ItemStack outputItem, float p_151394_3_)
    {
        this.recipes.put(inputItem, outputItem);
        this.field_77605_c.put(outputItem, p_151394_3_);
    }

    public ItemStack func_151395_a(ItemStack p_151395_1_)
    {
        Iterator iterator = this.recipes.entrySet().iterator();
        Entry entry;

        do
        {
            if (!iterator.hasNext())
            {
                return null;
            }

            entry = (Entry)iterator.next();
        }
        while (!this.func_151397_a(p_151395_1_, (ItemStack)entry.getKey()));

        return (ItemStack)entry.getValue();
    }

    private boolean func_151397_a(ItemStack p_151397_1_, ItemStack p_151397_2_)
    {
        return p_151397_2_.getBaseItem() == p_151397_1_.getBaseItem() && (p_151397_2_.func_77960_j() == 32767 || p_151397_2_.func_77960_j() == p_151397_1_.func_77960_j());
    }

    public Map<ItemStack, ItemStack> getRecipes()
    {
        return Collections.unmodifiableMap(this.recipes);
    }

    public float func_151398_b(ItemStack p_151398_1_)
    {
        Iterator iterator = this.field_77605_c.entrySet().iterator();
        Entry entry;

        do
        {
            if (!iterator.hasNext())
            {
                return 0.0F;
            }

            entry = (Entry)iterator.next();
        }
        while (!this.func_151397_a(p_151398_1_, (ItemStack)entry.getKey()));

        return ((Float)entry.getValue()).floatValue();
    }

    private void addFishCookingRecipes() {
        for (ItemFishFood.FishType fishType : ItemFishFood.FishType.values()) {
            if (fishType.func_150973_i()) {
                this.addRecipe(new ItemStack(Items.FISH, 1, fishType.func_150976_a()), new ItemStack(Items.COOKED_FISHED, 1, fishType.func_150976_a()), 0.35F);
            }
        }
    }
}