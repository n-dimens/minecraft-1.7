package net.minecraft.stats;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.JsonSerializableSet;

public class AchievementList
{
    // Rectangle
    public static int field_76010_a;
    public static int field_76008_b;
    public static int field_76009_c;
    public static int field_76006_d;
    public final static List<Achievement> ACHIEVEMENTS = new ArrayList<>();
    public final static Achievement OPEN_INVENTORY = (new Achievement("achievement.openInventory", "openInventory", 0, 0, Items.BOOK, null)).awardOnlyLocal().add();
    public final static Achievement MINE_WOOD = (new Achievement("achievement.mineWood", "mineWood", 2, 1, Blocks.field_150364_r, OPEN_INVENTORY)).add();
    public final static Achievement BUILD_WORK_BENCH = (new Achievement("achievement.buildWorkBench", "buildWorkBench", 4, -1, Blocks.field_150462_ai, MINE_WOOD)).add();
    public final static Achievement BUILD_PICKAXE = (new Achievement("achievement.buildPickaxe", "buildPickaxe", 4, 2, Items.WOODEN_PICKAXE, BUILD_WORK_BENCH)).add();
    public final static Achievement BUILD_FURNACE = (new Achievement("achievement.buildFurnace", "buildFurnace", 3, 4, Blocks.field_150460_al, BUILD_PICKAXE)).add();
    public final static Achievement ACQUIRE_IRON = (new Achievement("achievement.acquireIron", "acquireIron", 1, 4, Items.IRON_INGOT, BUILD_FURNACE)).add();
    public final static Achievement BUILD_HOE = (new Achievement("achievement.buildHoe", "buildHoe", 2, -3, Items.WOODEN_HOE, BUILD_WORK_BENCH)).add();
    public final static Achievement MAKE_BREAD = (new Achievement("achievement.makeBread", "makeBread", -1, -3, Items.BREAD, BUILD_HOE)).add();
    public final static Achievement BAKE_CAKE = (new Achievement("achievement.bakeCake", "bakeCake", 0, -5, Items.CAKE, BUILD_HOE)).add();
    public final static Achievement BUILD_BETTER_PICKAXE = (new Achievement("achievement.buildBetterPickaxe", "buildBetterPickaxe", 6, 2, Items.STONE_PICKAXE, BUILD_PICKAXE)).add();
    public final static Achievement COOK_FISH = (new Achievement("achievement.cookFish", "cookFish", 2, 6, Items.COOKED_FISHED, BUILD_FURNACE)).add();
    public final static Achievement ON_A_RAIL = (new Achievement("achievement.onARail", "onARail", 2, 3, Blocks.field_150448_aq, ACQUIRE_IRON)).func_75987_b().add();
    public final static Achievement BUILD_SWORD = (new Achievement("achievement.buildSword", "buildSword", 6, -1, Items.WOODEN_SWORD, BUILD_WORK_BENCH)).add();
    public final static Achievement KILL_ENEMY = (new Achievement("achievement.killEnemy", "killEnemy", 8, -1, Items.BONE, BUILD_SWORD)).add();
    public final static Achievement KILL_COW = (new Achievement("achievement.killCow", "killCow", 7, -3, Items.LEATHER, BUILD_SWORD)).add();
    public final static Achievement FLY_PIG = (new Achievement("achievement.flyPig", "flyPig", 9, -3, Items.SADDLE, KILL_COW)).func_75987_b().add();
    public final static Achievement SNIPE_SKELETON = (new Achievement("achievement.snipeSkeleton", "snipeSkeleton", 7, 0, Items.BOW, KILL_ENEMY)).func_75987_b().add();
    public final static Achievement DIAMONDS = (new Achievement("achievement.diamonds", "diamonds", -1, 5, Blocks.field_150482_ag, ACQUIRE_IRON)).add();
    public final static Achievement DIAMONDS_TO_YOU = (new Achievement("achievement.diamondsToYou", "diamondsToYou", -1, 2, Items.DIAMOND, DIAMONDS)).add();
    public final static Achievement PORTAL = (new Achievement("achievement.portal", "portal", -1, 7, Blocks.field_150343_Z, DIAMONDS)).add();
    public final static Achievement GHAST = (new Achievement("achievement.ghast", "ghast", -4, 8, Items.GHAST_TEAR, PORTAL)).func_75987_b().add();
    public final static Achievement BLAZE_ROD = (new Achievement("achievement.blazeRod", "blazeRod", 0, 9, Items.BLAZE_ROD, PORTAL)).add();
    public final static Achievement POTION = (new Achievement("achievement.potion", "potion", 2, 8, Items.POTION, BLAZE_ROD)).add();
    public final static Achievement THE_END = (new Achievement("achievement.theEnd", "theEnd", 3, 10, Items.ENDER_EYE, BLAZE_ROD)).func_75987_b().add();
    public final static Achievement THE_END_2 = (new Achievement("achievement.theEnd2", "theEnd2", 4, 13, Blocks.field_150380_bt, THE_END)).func_75987_b().add();
    public final static Achievement ENCHANTMENTS = (new Achievement("achievement.enchantments", "enchantments", -4, 4, Blocks.field_150381_bn, DIAMONDS)).add();
    public final static Achievement OVERKILL = (new Achievement("achievement.overkill", "overkill", -4, 1, Items.DIAMOND_SWORD, ENCHANTMENTS)).func_75987_b().add();
    public final static Achievement BOOKCASE = (new Achievement("achievement.bookcase", "bookcase", -3, 6, Blocks.field_150342_X, ENCHANTMENTS)).add();
    public final static Achievement BREED_COW = (new Achievement("achievement.breedCow", "breedCow", 7, -5, Items.WHEAT, KILL_COW)).add();
    public final static Achievement SPAWN_WITHER = (new Achievement("achievement.spawnWither", "spawnWither", 7, 12, new ItemStack(Items.SKULL, 1, 1), THE_END_2)).add();
    public final static Achievement KILL_WITHER = (new Achievement("achievement.killWither", "killWither", 7, 10, Items.NETHER_STAR, SPAWN_WITHER)).add();
    public final static Achievement FULL_BEACON = (new Achievement("achievement.fullBeacon", "fullBeacon", 7, 8, Blocks.field_150461_bJ, KILL_WITHER)).func_75987_b().add();
    public final static Achievement EXPLORE_ALL_BIOMES = (new Achievement("achievement.exploreAllBiomes", "exploreAllBiomes", 4, 8, Items.DIAMOND_BOOTS, THE_END)).func_150953_b(JsonSerializableSet.class).func_75987_b().add();
    private static final String __OBFID = "CL_00001467";

    public static void func_75997_a() {}
}