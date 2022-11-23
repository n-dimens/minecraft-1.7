package net.minecraft.stats;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityList;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ChatComponentTranslation;

public final class StatList
{
    final static Map<String, StatBase> field_75942_a = new HashMap<>();
    public final static List<StatBase> field_75940_b = new ArrayList<>();
    public final static List<StatBase> field_75941_c = new ArrayList<>();
    public final static List<StatBase> field_75938_d = new ArrayList<>();
    public final static List<StatBase> field_75939_e = new ArrayList<>();
    public final static StatBase LEAVE_GAME = (new StatBasic("stat.leaveGame", new ChatComponentTranslation("stat.leaveGame", new Object[0]))).awardOnlyLocal().add();
    public final static StatBase PLAY_ONE_MINUTE = (new StatBasic("stat.playOneMinute", new ChatComponentTranslation("stat.playOneMinute", new Object[0]), StatBase.DATETIME_FORMATTER)).awardOnlyLocal().add();
    public final static StatBase WALK_ONE_CM = (new StatBasic("stat.walkOneCm", new ChatComponentTranslation("stat.walkOneCm", new Object[0]), StatBase.DISTANCE_FORMATTER)).awardOnlyLocal().add();
    public final static StatBase SWIM_ONE_CM = (new StatBasic("stat.swimOneCm", new ChatComponentTranslation("stat.swimOneCm", new Object[0]), StatBase.DISTANCE_FORMATTER)).awardOnlyLocal().add();
    public final static StatBase FALL_ON_CM = (new StatBasic("stat.fallOneCm", new ChatComponentTranslation("stat.fallOneCm", new Object[0]), StatBase.DISTANCE_FORMATTER)).awardOnlyLocal().add();
    public final static StatBase CLIMB_ONE_CM = (new StatBasic("stat.climbOneCm", new ChatComponentTranslation("stat.climbOneCm", new Object[0]), StatBase.DISTANCE_FORMATTER)).awardOnlyLocal().add();
    public final static StatBase FLY_ONE_CM = (new StatBasic("stat.flyOneCm", new ChatComponentTranslation("stat.flyOneCm", new Object[0]), StatBase.DISTANCE_FORMATTER)).awardOnlyLocal().add();
    public final static StatBase DIVE_ONE_CM = (new StatBasic("stat.diveOneCm", new ChatComponentTranslation("stat.diveOneCm", new Object[0]), StatBase.DISTANCE_FORMATTER)).awardOnlyLocal().add();
    public final static StatBase MINECART_ONE_CM = (new StatBasic("stat.minecartOneCm", new ChatComponentTranslation("stat.minecartOneCm", new Object[0]), StatBase.DISTANCE_FORMATTER)).awardOnlyLocal().add();
    public final static StatBase BOAT_ONE_CM = (new StatBasic("stat.boatOneCm", new ChatComponentTranslation("stat.boatOneCm", new Object[0]), StatBase.DISTANCE_FORMATTER)).awardOnlyLocal().add();
    public final static StatBase PIG_ONE_CM = (new StatBasic("stat.pigOneCm", new ChatComponentTranslation("stat.pigOneCm", new Object[0]), StatBase.DISTANCE_FORMATTER)).awardOnlyLocal().add();
    public final static StatBase HORSE_ONE_CM = (new StatBasic("stat.horseOneCm", new ChatComponentTranslation("stat.horseOneCm", new Object[0]), StatBase.DISTANCE_FORMATTER)).awardOnlyLocal().add();
    public final static StatBase JUMP = (new StatBasic("stat.jump", new ChatComponentTranslation("stat.jump", new Object[0]))).awardOnlyLocal().add();
    public final static StatBase DROP = (new StatBasic("stat.drop", new ChatComponentTranslation("stat.drop", new Object[0]))).awardOnlyLocal().add();
    public final static StatBase DAMAGE_DEALT = (new StatBasic("stat.damageDealt", new ChatComponentTranslation("stat.damageDealt", new Object[0]), StatBase.DAMAGE_FORMATTER)).add();
    public final static StatBase DAMAGE_TAKEN = (new StatBasic("stat.damageTaken", new ChatComponentTranslation("stat.damageTaken", new Object[0]), StatBase.DAMAGE_FORMATTER)).add();
    public final static StatBase DEATHS = (new StatBasic("stat.deaths", new ChatComponentTranslation("stat.deaths", new Object[0]))).add();
    public final static StatBase MOB_KILLS = (new StatBasic("stat.mobKills", new ChatComponentTranslation("stat.mobKills", new Object[0]))).add();
    public final static StatBase ANIMALS_BRED = (new StatBasic("stat.animalsBred", new ChatComponentTranslation("stat.animalsBred", new Object[0]))).add();
    public final static StatBase PLAYER_KILLS = (new StatBasic("stat.playerKills", new ChatComponentTranslation("stat.playerKills", new Object[0]))).add();
    public final static StatBase FISH_CAUGHT = (new StatBasic("stat.fishCaught", new ChatComponentTranslation("stat.fishCaught", new Object[0]))).add();
    public final static StatBase JUNK_FISHED = (new StatBasic("stat.junkFished", new ChatComponentTranslation("stat.junkFished", new Object[0]))).add();
    public final static StatBase TREASURE_FISHED = (new StatBasic("stat.treasureFished", new ChatComponentTranslation("stat.treasureFished", new Object[0]))).add();
    public static final StatBase[] field_75934_C = new StatBase[4096];
    public static final StatBase[] field_75928_D = new StatBase[32000];
    public static final StatBase[] field_75929_E = new StatBase[32000];
    public static final StatBase[] field_75930_F = new StatBase[32000];
    private static final String __OBFID = "CL_00001480";

    public static void func_151178_a()
    {
        func_151181_c();
        func_75925_c();
        func_151179_e();
        func_75918_d();
        AchievementList.func_75997_a();
        EntityList.func_151514_a();
    }

    private static void func_75918_d()
    {
        HashSet<Item> craftingItems = new HashSet<>();
        for (IRecipe recipe : CraftingManager.getInstance().getRecipes()) {
            if (recipe.getOutputItem() != null) {
                craftingItems.add(recipe.getOutputItem().getBaseItem());
            }
        }

        for (ItemStack itemStack : FurnaceRecipes.getInstance().getRecipes().values()) {
            craftingItems.add(itemStack.getBaseItem());
        }

        for (Item item : craftingItems) {
            if (item != null) {
                int i = Item.func_150891_b(item);
                field_75928_D[i] = (new StatCrafting("stat.craftItem." + i, new ChatComponentTranslation("stat.craftItem", (new ItemStack(item)).func_151000_E()), item)).add();
            }
        }

        func_75924_a(field_75928_D);
    }

    private static void func_151181_c()
    {
        Iterator iterator = Block.field_149771_c.iterator();

        while (iterator.hasNext())
        {
            Block block = (Block)iterator.next();

            if (Item.func_150898_a(block) != null)
            {
                int i = Block.func_149682_b(block);

                if (block.func_149652_G())
                {
                    field_75934_C[i] = (new StatCrafting("stat.mineBlock." + i, new ChatComponentTranslation("stat.mineBlock", new Object[] {(new ItemStack(block)).func_151000_E()}), Item.func_150898_a(block))).add();
                    field_75939_e.add((StatCrafting)field_75934_C[i]);
                }
            }
        }

        func_75924_a(field_75934_C);
    }

    private static void func_75925_c()
    {
        Iterator iterator = Item.REGISTRY.iterator();

        while (iterator.hasNext())
        {
            Item item = (Item)iterator.next();

            if (item != null)
            {
                int i = Item.func_150891_b(item);
                field_75929_E[i] = (new StatCrafting("stat.useItem." + i, new ChatComponentTranslation("stat.useItem", new Object[] {(new ItemStack(item)).func_151000_E()}), item)).add();

                if (!(item instanceof ItemBlock))
                {
                    field_75938_d.add((StatCrafting)field_75929_E[i]);
                }
            }
        }

        func_75924_a(field_75929_E);
    }

    private static void func_151179_e()
    {
        Iterator iterator = Item.REGISTRY.iterator();

        while (iterator.hasNext())
        {
            Item item = (Item)iterator.next();

            if (item != null)
            {
                int i = Item.func_150891_b(item);

                if (item.func_77645_m())
                {
                    field_75930_F[i] = (new StatCrafting("stat.breakItem." + i, new ChatComponentTranslation("stat.breakItem", new Object[] {(new ItemStack(item)).func_151000_E()}), item)).add();
                }
            }
        }

        func_75924_a(field_75930_F);
    }

    private static void func_75924_a(StatBase[] p_75924_0_)
    {
        func_151180_a(p_75924_0_, Blocks.WATER, Blocks.FLOWING_WATER);
        func_151180_a(p_75924_0_, Blocks.LAVA, Blocks.FLOWING_LAVA);
        func_151180_a(p_75924_0_, Blocks.LIT_PUMPKIN, Blocks.PUMPKIN);
        func_151180_a(p_75924_0_, Blocks.LIT_FURNACE, Blocks.FURNACE);
        func_151180_a(p_75924_0_, Blocks.LIT_REDSTONE_ORE, Blocks.REDSTONE_ORE);
        func_151180_a(p_75924_0_, Blocks.POWERED_REPEATER, Blocks.UNPOWERED_REPEATER);
        func_151180_a(p_75924_0_, Blocks.POWERED_COMPARATOR, Blocks.UNPOWERED_COMPARATOR);
        func_151180_a(p_75924_0_, Blocks.REDSTONE_TORCH, Blocks.UNLIT_REDSTONE_TORCH);
        func_151180_a(p_75924_0_, Blocks.LIT_REDSTONE_LAMP, Blocks.REDSTONE_LAMP);
        func_151180_a(p_75924_0_, Blocks.RED_MUSHROOM, Blocks.BROWN_MUSHROOM);
        func_151180_a(p_75924_0_, Blocks.DOUBLE_STONE_SLAB, Blocks.STONE_SLAB);
        func_151180_a(p_75924_0_, Blocks.DOUBLE_WOODEN_SLAB, Blocks.WOODEN_SLAB);
        func_151180_a(p_75924_0_, Blocks.GRASS, Blocks.DIRT);
        func_151180_a(p_75924_0_, Blocks.FARMLAND, Blocks.DIRT);
    }

    private static void func_151180_a(StatBase[] p_151180_0_, Block p_151180_1_, Block p_151180_2_)
    {
        int i = Block.func_149682_b(p_151180_1_);
        int j = Block.func_149682_b(p_151180_2_);

        if (p_151180_0_[i] != null && p_151180_0_[j] == null)
        {
            p_151180_0_[j] = p_151180_0_[i];
        }
        else
        {
            field_75940_b.remove(p_151180_0_[i]);
            field_75939_e.remove(p_151180_0_[i]);
            field_75941_c.remove(p_151180_0_[i]);
            p_151180_0_[i] = p_151180_0_[j];
        }
    }

    public static StatBase func_151182_a(EntityList.EntityEggInfo p_151182_0_)
    {
        String s = EntityList.func_75617_a(p_151182_0_.field_75613_a);
        return s == null ? null : (new StatBase("stat.killEntity." + s, new ChatComponentTranslation("stat.entityKill", new Object[] {new ChatComponentTranslation("entity." + s + ".name", new Object[0])}))).add();
    }

    public static StatBase func_151176_b(EntityList.EntityEggInfo p_151176_0_)
    {
        String s = EntityList.func_75617_a(p_151176_0_.field_75613_a);
        return s == null ? null : (new StatBase("stat.entityKilledBy." + s, new ChatComponentTranslation("stat.entityKilledBy", new Object[] {new ChatComponentTranslation("entity." + s + ".name", new Object[0])}))).add();
    }

    public static StatBase func_151177_a(String p_151177_0_)
    {
        return (StatBase)field_75942_a.get(p_151177_0_);
    }
}