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
    public final static StatBase field_75947_j = (new StatBasic("stat.leaveGame", new ChatComponentTranslation("stat.leaveGame", new Object[0]))).awardOnlyLocal().add();
    public final static StatBase field_75948_k = (new StatBasic("stat.playOneMinute", new ChatComponentTranslation("stat.playOneMinute", new Object[0]), StatBase.DATETIME_FORMATTER)).awardOnlyLocal().add();
    public final static StatBase field_75945_l = (new StatBasic("stat.walkOneCm", new ChatComponentTranslation("stat.walkOneCm", new Object[0]), StatBase.DISTANCE_FORMATTER)).awardOnlyLocal().add();
    public final static StatBase field_75946_m = (new StatBasic("stat.swimOneCm", new ChatComponentTranslation("stat.swimOneCm", new Object[0]), StatBase.DISTANCE_FORMATTER)).awardOnlyLocal().add();
    public final static StatBase field_75943_n = (new StatBasic("stat.fallOneCm", new ChatComponentTranslation("stat.fallOneCm", new Object[0]), StatBase.DISTANCE_FORMATTER)).awardOnlyLocal().add();
    public final static StatBase field_75944_o = (new StatBasic("stat.climbOneCm", new ChatComponentTranslation("stat.climbOneCm", new Object[0]), StatBase.DISTANCE_FORMATTER)).awardOnlyLocal().add();
    public final static StatBase field_75958_p = (new StatBasic("stat.flyOneCm", new ChatComponentTranslation("stat.flyOneCm", new Object[0]), StatBase.DISTANCE_FORMATTER)).awardOnlyLocal().add();
    public final static StatBase field_75957_q = (new StatBasic("stat.diveOneCm", new ChatComponentTranslation("stat.diveOneCm", new Object[0]), StatBase.DISTANCE_FORMATTER)).awardOnlyLocal().add();
    public final static StatBase field_75956_r = (new StatBasic("stat.minecartOneCm", new ChatComponentTranslation("stat.minecartOneCm", new Object[0]), StatBase.DISTANCE_FORMATTER)).awardOnlyLocal().add();
    public final static StatBase field_75955_s = (new StatBasic("stat.boatOneCm", new ChatComponentTranslation("stat.boatOneCm", new Object[0]), StatBase.DISTANCE_FORMATTER)).awardOnlyLocal().add();
    public final static StatBase field_75954_t = (new StatBasic("stat.pigOneCm", new ChatComponentTranslation("stat.pigOneCm", new Object[0]), StatBase.DISTANCE_FORMATTER)).awardOnlyLocal().add();
    public final static StatBase field_151185_q = (new StatBasic("stat.horseOneCm", new ChatComponentTranslation("stat.horseOneCm", new Object[0]), StatBase.DISTANCE_FORMATTER)).awardOnlyLocal().add();
    public final static StatBase field_75953_u = (new StatBasic("stat.jump", new ChatComponentTranslation("stat.jump", new Object[0]))).awardOnlyLocal().add();
    public final static StatBase field_75952_v = (new StatBasic("stat.drop", new ChatComponentTranslation("stat.drop", new Object[0]))).awardOnlyLocal().add();
    public final static StatBase field_75951_w = (new StatBasic("stat.damageDealt", new ChatComponentTranslation("stat.damageDealt", new Object[0]), StatBase.DAMAGE_FORMATTER)).add();
    public final static StatBase field_75961_x = (new StatBasic("stat.damageTaken", new ChatComponentTranslation("stat.damageTaken", new Object[0]), StatBase.DAMAGE_FORMATTER)).add();
    public final static StatBase field_75960_y = (new StatBasic("stat.deaths", new ChatComponentTranslation("stat.deaths", new Object[0]))).add();
    public final static StatBase field_75959_z = (new StatBasic("stat.mobKills", new ChatComponentTranslation("stat.mobKills", new Object[0]))).add();
    public final static StatBase field_151186_x = (new StatBasic("stat.animalsBred", new ChatComponentTranslation("stat.animalsBred", new Object[0]))).add();
    public final static StatBase field_75932_A = (new StatBasic("stat.playerKills", new ChatComponentTranslation("stat.playerKills", new Object[0]))).add();
    public final static StatBase field_75933_B = (new StatBasic("stat.fishCaught", new ChatComponentTranslation("stat.fishCaught", new Object[0]))).add();
    public final static StatBase field_151183_A = (new StatBasic("stat.junkFished", new ChatComponentTranslation("stat.junkFished", new Object[0]))).add();
    public final static StatBase field_151184_B = (new StatBasic("stat.treasureFished", new ChatComponentTranslation("stat.treasureFished", new Object[0]))).add();
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
        HashSet hashset = new HashSet();
        Iterator iterator = CraftingManager.func_77594_a().func_77592_b().iterator();

        while (iterator.hasNext())
        {
            IRecipe irecipe = (IRecipe)iterator.next();

            if (irecipe.func_77571_b() != null)
            {
                hashset.add(irecipe.func_77571_b().func_77973_b());
            }
        }

        iterator = FurnaceRecipes.func_77602_a().func_77599_b().values().iterator();

        while (iterator.hasNext())
        {
            ItemStack itemstack = (ItemStack)iterator.next();
            hashset.add(itemstack.func_77973_b());
        }

        iterator = hashset.iterator();

        while (iterator.hasNext())
        {
            Item item = (Item)iterator.next();

            if (item != null)
            {
                int i = Item.func_150891_b(item);
                field_75928_D[i] = (new StatCrafting("stat.craftItem." + i, new ChatComponentTranslation("stat.craftItem", new Object[] {(new ItemStack(item)).func_151000_E()}), item)).add();
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