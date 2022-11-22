package net.minecraft.item.crafting;

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
    private static final FurnaceRecipes field_77606_a = new FurnaceRecipes();
    private Map field_77604_b = new HashMap();
    private Map field_77605_c = new HashMap();
    private static final String __OBFID = "CL_00000085";

    public static FurnaceRecipes func_77602_a()
    {
        return field_77606_a;
    }

    private FurnaceRecipes()
    {
        this.func_151393_a(Blocks.IRON_ORE, new ItemStack(Items.IRON_INGOT), 0.7F);
        this.func_151393_a(Blocks.GOLD_ORE, new ItemStack(Items.GOLD_INGOT), 1.0F);
        this.func_151393_a(Blocks.DIAMOND_ORE, new ItemStack(Items.DIAMOND), 1.0F);
        this.func_151393_a(Blocks.SAND, new ItemStack(Blocks.GLASS), 0.1F);
        this.func_151396_a(Items.PORKCHOP, new ItemStack(Items.COOKED_PORKCHOP), 0.35F);
        this.func_151396_a(Items.BEEF, new ItemStack(Items.COOKED_BEEF), 0.35F);
        this.func_151396_a(Items.CHICKEN, new ItemStack(Items.COOKED_CHICKEN), 0.35F);
        this.func_151393_a(Blocks.COBBLESTONE, new ItemStack(Blocks.STONE), 0.1F);
        this.func_151396_a(Items.CLAY_BALL, new ItemStack(Items.BRICK), 0.3F);
        this.func_151393_a(Blocks.CLAY, new ItemStack(Blocks.HARDENED_CLAY), 0.35F);
        this.func_151393_a(Blocks.CACTUS, new ItemStack(Items.DYE, 1, 2), 0.2F);
        this.func_151393_a(Blocks.LOG, new ItemStack(Items.COAL, 1, 1), 0.15F);
        this.func_151393_a(Blocks.LOG_2, new ItemStack(Items.COAL, 1, 1), 0.15F);
        this.func_151393_a(Blocks.EMERALD_ORE, new ItemStack(Items.EMERALD), 1.0F);
        this.func_151396_a(Items.POTATO, new ItemStack(Items.BAKED_POTATO), 0.35F);
        this.func_151393_a(Blocks.NETHERRACK, new ItemStack(Items.NETHERBRICK), 0.1F);
        ItemFishFood.FishType[] afishtype = ItemFishFood.FishType.values();
        int i = afishtype.length;

        for (int j = 0; j < i; ++j)
        {
            ItemFishFood.FishType fishtype = afishtype[j];

            if (fishtype.func_150973_i())
            {
                this.func_151394_a(new ItemStack(Items.FISH, 1, fishtype.func_150976_a()), new ItemStack(Items.COOKED_FISHED, 1, fishtype.func_150976_a()), 0.35F);
            }
        }

        this.func_151393_a(Blocks.COAL_ORE, new ItemStack(Items.COAL), 0.1F);
        this.func_151393_a(Blocks.REDSTONE_ORE, new ItemStack(Items.REDSTONE), 0.7F);
        this.func_151393_a(Blocks.LAPIS_ORE, new ItemStack(Items.DYE, 1, 4), 0.2F);
        this.func_151393_a(Blocks.QUARTZ_ORE, new ItemStack(Items.QUARTZ), 0.2F);
    }

    public void func_151393_a(Block p_151393_1_, ItemStack p_151393_2_, float p_151393_3_)
    {
        this.func_151396_a(Item.func_150898_a(p_151393_1_), p_151393_2_, p_151393_3_);
    }

    public void func_151396_a(Item p_151396_1_, ItemStack p_151396_2_, float p_151396_3_)
    {
        this.func_151394_a(new ItemStack(p_151396_1_, 1, 32767), p_151396_2_, p_151396_3_);
    }

    public void func_151394_a(ItemStack p_151394_1_, ItemStack p_151394_2_, float p_151394_3_)
    {
        this.field_77604_b.put(p_151394_1_, p_151394_2_);
        this.field_77605_c.put(p_151394_2_, Float.valueOf(p_151394_3_));
    }

    public ItemStack func_151395_a(ItemStack p_151395_1_)
    {
        Iterator iterator = this.field_77604_b.entrySet().iterator();
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

    public Map func_77599_b()
    {
        return this.field_77604_b;
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
}