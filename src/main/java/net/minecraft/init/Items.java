package net.minecraft.init;

import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemEmptyMap;
import net.minecraft.item.ItemEnchantedBook;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemMap;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemShears;

public class Items
{
    public static final Item IRON_SHOVEL = (Item)Item.REGISTRY.getById("iron_shovel");
    public static final Item IRON_PICKAXE = (Item)Item.REGISTRY.getById("iron_pickaxe");
    public static final Item IRON_AXE = (Item)Item.REGISTRY.getById("iron_axe");
    public static final Item field_151033_d = (Item)Item.REGISTRY.getById("flint_and_steel");
    public static final Item field_151034_e = (Item)Item.REGISTRY.getById("apple");
    public static final ItemBow field_151031_f = (ItemBow)Item.REGISTRY.getById("bow");
    public static final Item field_151032_g = (Item)Item.REGISTRY.getById("arrow");
    public static final Item field_151044_h = (Item)Item.REGISTRY.getById("coal");
    public static final Item field_151045_i = (Item)Item.REGISTRY.getById("diamond");
    public static final Item field_151042_j = (Item)Item.REGISTRY.getById("iron_ingot");
    public static final Item field_151043_k = (Item)Item.REGISTRY.getById("gold_ingot");
    public static final Item IRON_SWORD = (Item)Item.REGISTRY.getById("iron_sword");
    public static final Item WOODEN_SWORD = (Item)Item.REGISTRY.getById("wooden_sword");
    public static final Item WOODEN_SHOVEL = (Item)Item.REGISTRY.getById("wooden_shovel");
    public static final Item WOODEN_PICKAXE = (Item)Item.REGISTRY.getById("wooden_pickaxe");
    public static final Item WOODEN_AXE = (Item)Item.REGISTRY.getById("wooden_axe");
    public static final Item STONE_SWORD = (Item)Item.REGISTRY.getById("stone_sword");
    public static final Item STONE_SHOVEL = (Item)Item.REGISTRY.getById("stone_shovel");
    public static final Item STONE_PICKAXE = (Item)Item.REGISTRY.getById("stone_pickaxe");
    public static final Item STONE_AXE = (Item)Item.REGISTRY.getById("stone_axe");
    public static final Item DIAMOND_SWORD = (Item)Item.REGISTRY.getById("diamond_sword");
    public static final Item DIAMOND_SHOVEL = (Item)Item.REGISTRY.getById("diamond_shovel");
    public static final Item DIAMOND_PICKAXE = (Item)Item.REGISTRY.getById("diamond_pickaxe");
    public static final Item DIAMOND_AXE = (Item)Item.REGISTRY.getById("diamond_axe");
    public static final Item field_151055_y = (Item)Item.REGISTRY.getById("stick");
    public static final Item field_151054_z = (Item)Item.REGISTRY.getById("bowl");
    public static final Item field_151009_A = (Item)Item.REGISTRY.getById("mushroom_stew");
    public static final Item GOLDEN_SWORD = (Item)Item.REGISTRY.getById("golden_sword");
    public static final Item GOLDEN_SHOVEL = (Item)Item.REGISTRY.getById("golden_shovel");
    public static final Item GOLDEN_PICKAXE = (Item)Item.REGISTRY.getById("golden_pickaxe");
    public static final Item GOLDEN_AXE = (Item)Item.REGISTRY.getById("golden_axe");
    public static final Item field_151007_F = (Item)Item.REGISTRY.getById("string");
    public static final Item field_151008_G = (Item)Item.REGISTRY.getById("feather");
    public static final Item field_151016_H = (Item)Item.REGISTRY.getById("gunpowder");
    public static final Item field_151017_I = (Item)Item.REGISTRY.getById("wooden_hoe");
    public static final Item field_151018_J = (Item)Item.REGISTRY.getById("stone_hoe");
    public static final Item IRON_HOE = (Item)Item.REGISTRY.getById("iron_hoe");
    public static final Item field_151012_L = (Item)Item.REGISTRY.getById("diamond_hoe");
    public static final Item field_151013_M = (Item)Item.REGISTRY.getById("golden_hoe");
    public static final Item field_151014_N = (Item)Item.REGISTRY.getById("wheat_seeds");
    public static final Item field_151015_O = (Item)Item.REGISTRY.getById("wheat");
    public static final Item field_151025_P = (Item)Item.REGISTRY.getById("bread");
    public static final ItemArmor field_151024_Q = (ItemArmor)Item.REGISTRY.getById("leather_helmet");
    public static final ItemArmor field_151027_R = (ItemArmor)Item.REGISTRY.getById("leather_chestplate");
    public static final ItemArmor field_151026_S = (ItemArmor)Item.REGISTRY.getById("leather_leggings");
    public static final ItemArmor field_151021_T = (ItemArmor)Item.REGISTRY.getById("leather_boots");
    public static final ItemArmor field_151020_U = (ItemArmor)Item.REGISTRY.getById("chainmail_helmet");
    public static final ItemArmor field_151023_V = (ItemArmor)Item.REGISTRY.getById("chainmail_chestplate");
    public static final ItemArmor field_151022_W = (ItemArmor)Item.REGISTRY.getById("chainmail_leggings");
    public static final ItemArmor field_151029_X = (ItemArmor)Item.REGISTRY.getById("chainmail_boots");
    public static final ItemArmor field_151028_Y = (ItemArmor)Item.REGISTRY.getById("iron_helmet");
    public static final ItemArmor field_151030_Z = (ItemArmor)Item.REGISTRY.getById("iron_chestplate");
    public static final ItemArmor field_151165_aa = (ItemArmor)Item.REGISTRY.getById("iron_leggings");
    public static final ItemArmor field_151167_ab = (ItemArmor)Item.REGISTRY.getById("iron_boots");
    public static final ItemArmor field_151161_ac = (ItemArmor)Item.REGISTRY.getById("diamond_helmet");
    public static final ItemArmor field_151163_ad = (ItemArmor)Item.REGISTRY.getById("diamond_chestplate");
    public static final ItemArmor field_151173_ae = (ItemArmor)Item.REGISTRY.getById("diamond_leggings");
    public static final ItemArmor field_151175_af = (ItemArmor)Item.REGISTRY.getById("diamond_boots");
    public static final ItemArmor field_151169_ag = (ItemArmor)Item.REGISTRY.getById("golden_helmet");
    public static final ItemArmor field_151171_ah = (ItemArmor)Item.REGISTRY.getById("golden_chestplate");
    public static final ItemArmor field_151149_ai = (ItemArmor)Item.REGISTRY.getById("golden_leggings");
    public static final ItemArmor field_151151_aj = (ItemArmor)Item.REGISTRY.getById("golden_boots");
    public static final Item field_151145_ak = (Item)Item.REGISTRY.getById("flint");
    public static final Item field_151147_al = (Item)Item.REGISTRY.getById("porkchop");
    public static final Item field_151157_am = (Item)Item.REGISTRY.getById("cooked_porkchop");
    public static final Item field_151159_an = (Item)Item.REGISTRY.getById("painting");
    public static final Item field_151153_ao = (Item)Item.REGISTRY.getById("golden_apple");
    public static final Item field_151155_ap = (Item)Item.REGISTRY.getById("sign");
    public static final Item field_151135_aq = (Item)Item.REGISTRY.getById("wooden_door");
    public static final Item field_151133_ar = (Item)Item.REGISTRY.getById("bucket");
    public static final Item field_151131_as = (Item)Item.REGISTRY.getById("water_bucket");
    public static final Item field_151129_at = (Item)Item.REGISTRY.getById("lava_bucket");
    public static final Item field_151143_au = (Item)Item.REGISTRY.getById("minecart");
    public static final Item field_151141_av = (Item)Item.REGISTRY.getById("saddle");
    public static final Item field_151139_aw = (Item)Item.REGISTRY.getById("iron_door");
    public static final Item field_151137_ax = (Item)Item.REGISTRY.getById("redstone");
    public static final Item field_151126_ay = (Item)Item.REGISTRY.getById("snowball");
    public static final Item field_151124_az = (Item)Item.REGISTRY.getById("boat");
    public static final Item field_151116_aA = (Item)Item.REGISTRY.getById("leather");
    public static final Item field_151117_aB = (Item)Item.REGISTRY.getById("milk_bucket");
    public static final Item field_151118_aC = (Item)Item.REGISTRY.getById("brick");
    public static final Item field_151119_aD = (Item)Item.REGISTRY.getById("clay_ball");
    public static final Item field_151120_aE = (Item)Item.REGISTRY.getById("reeds");
    public static final Item field_151121_aF = (Item)Item.REGISTRY.getById("paper");
    public static final Item field_151122_aG = (Item)Item.REGISTRY.getById("book");
    public static final Item field_151123_aH = (Item)Item.REGISTRY.getById("slime_ball");
    public static final Item field_151108_aI = (Item)Item.REGISTRY.getById("chest_minecart");
    public static final Item field_151109_aJ = (Item)Item.REGISTRY.getById("furnace_minecart");
    public static final Item field_151110_aK = (Item)Item.REGISTRY.getById("egg");
    public static final Item field_151111_aL = (Item)Item.REGISTRY.getById("compass");
    public static final ItemFishingRod field_151112_aM = (ItemFishingRod)Item.REGISTRY.getById("fishing_rod");
    public static final Item field_151113_aN = (Item)Item.REGISTRY.getById("clock");
    public static final Item field_151114_aO = (Item)Item.REGISTRY.getById("glowstone_dust");
    public static final Item field_151115_aP = (Item)Item.REGISTRY.getById("fish");
    public static final Item field_151101_aQ = (Item)Item.REGISTRY.getById("cooked_fished");
    public static final Item field_151100_aR = (Item)Item.REGISTRY.getById("dye");
    public static final Item field_151103_aS = (Item)Item.REGISTRY.getById("bone");
    public static final Item field_151102_aT = (Item)Item.REGISTRY.getById("sugar");
    public static final Item field_151105_aU = (Item)Item.REGISTRY.getById("cake");
    public static final Item field_151104_aV = (Item)Item.REGISTRY.getById("bed");
    public static final Item field_151107_aW = (Item)Item.REGISTRY.getById("repeater");
    public static final Item field_151106_aX = (Item)Item.REGISTRY.getById("cookie");
    public static final ItemMap field_151098_aY = (ItemMap)Item.REGISTRY.getById("filled_map");
    public static final ItemShears field_151097_aZ = (ItemShears)Item.REGISTRY.getById("shears");
    public static final Item field_151127_ba = (Item)Item.REGISTRY.getById("melon");
    public static final Item field_151080_bb = (Item)Item.REGISTRY.getById("pumpkin_seeds");
    public static final Item field_151081_bc = (Item)Item.REGISTRY.getById("melon_seeds");
    public static final Item field_151082_bd = (Item)Item.REGISTRY.getById("beef");
    public static final Item field_151083_be = (Item)Item.REGISTRY.getById("cooked_beef");
    public static final Item field_151076_bf = (Item)Item.REGISTRY.getById("chicken");
    public static final Item field_151077_bg = (Item)Item.REGISTRY.getById("cooked_chicken");
    public static final Item field_151078_bh = (Item)Item.REGISTRY.getById("rotten_flesh");
    public static final Item field_151079_bi = (Item)Item.REGISTRY.getById("ender_pearl");
    public static final Item field_151072_bj = (Item)Item.REGISTRY.getById("blaze_rod");
    public static final Item field_151073_bk = (Item)Item.REGISTRY.getById("ghast_tear");
    public static final Item field_151074_bl = (Item)Item.REGISTRY.getById("gold_nugget");
    public static final Item field_151075_bm = (Item)Item.REGISTRY.getById("nether_wart");
    public static final ItemPotion field_151068_bn = (ItemPotion)Item.REGISTRY.getById("potion");
    public static final Item field_151069_bo = (Item)Item.REGISTRY.getById("glass_bottle");
    public static final Item field_151070_bp = (Item)Item.REGISTRY.getById("spider_eye");
    public static final Item field_151071_bq = (Item)Item.REGISTRY.getById("fermented_spider_eye");
    public static final Item field_151065_br = (Item)Item.REGISTRY.getById("blaze_powder");
    public static final Item field_151064_bs = (Item)Item.REGISTRY.getById("magma_cream");
    public static final Item field_151067_bt = (Item)Item.REGISTRY.getById("brewing_stand");
    public static final Item field_151066_bu = (Item)Item.REGISTRY.getById("cauldron");
    public static final Item field_151061_bv = (Item)Item.REGISTRY.getById("ender_eye");
    public static final Item field_151060_bw = (Item)Item.REGISTRY.getById("speckled_melon");
    public static final Item field_151063_bx = (Item)Item.REGISTRY.getById("spawn_egg");
    public static final Item field_151062_by = (Item)Item.REGISTRY.getById("experience_bottle");
    public static final Item field_151059_bz = (Item)Item.REGISTRY.getById("fire_charge");
    public static final Item field_151099_bA = (Item)Item.REGISTRY.getById("writable_book");
    public static final Item field_151164_bB = (Item)Item.REGISTRY.getById("written_book");
    public static final Item field_151166_bC = (Item)Item.REGISTRY.getById("emerald");
    public static final Item field_151160_bD = (Item)Item.REGISTRY.getById("item_frame");
    public static final Item field_151162_bE = (Item)Item.REGISTRY.getById("flower_pot");
    public static final Item field_151172_bF = (Item)Item.REGISTRY.getById("carrot");
    public static final Item field_151174_bG = (Item)Item.REGISTRY.getById("potato");
    public static final Item field_151168_bH = (Item)Item.REGISTRY.getById("baked_potato");
    public static final Item field_151170_bI = (Item)Item.REGISTRY.getById("poisonous_potato");
    public static final ItemEmptyMap field_151148_bJ = (ItemEmptyMap)Item.REGISTRY.getById("map");
    public static final Item field_151150_bK = (Item)Item.REGISTRY.getById("golden_carrot");
    public static final Item field_151144_bL = (Item)Item.REGISTRY.getById("skull");
    public static final Item field_151146_bM = (Item)Item.REGISTRY.getById("carrot_on_a_stick");
    public static final Item field_151156_bN = (Item)Item.REGISTRY.getById("nether_star");
    public static final Item field_151158_bO = (Item)Item.REGISTRY.getById("pumpkin_pie");
    public static final Item field_151152_bP = (Item)Item.REGISTRY.getById("fireworks");
    public static final Item field_151154_bQ = (Item)Item.REGISTRY.getById("firework_charge");
    public static final ItemEnchantedBook field_151134_bR = (ItemEnchantedBook)Item.REGISTRY.getById("enchanted_book");
    public static final Item field_151132_bS = (Item)Item.REGISTRY.getById("comparator");
    public static final Item field_151130_bT = (Item)Item.REGISTRY.getById("netherbrick");
    public static final Item field_151128_bU = (Item)Item.REGISTRY.getById("quartz");
    public static final Item field_151142_bV = (Item)Item.REGISTRY.getById("tnt_minecart");
    public static final Item field_151140_bW = (Item)Item.REGISTRY.getById("hopper_minecart");
    public static final Item field_151138_bX = (Item)Item.REGISTRY.getById("iron_horse_armor");
    public static final Item field_151136_bY = (Item)Item.REGISTRY.getById("golden_horse_armor");
    public static final Item field_151125_bZ = (Item)Item.REGISTRY.getById("diamond_horse_armor");
    public static final Item field_151058_ca = (Item)Item.REGISTRY.getById("lead");
    public static final Item field_151057_cb = (Item)Item.REGISTRY.getById("name_tag");
    public static final Item field_151095_cc = (Item)Item.REGISTRY.getById("command_block_minecart");
    public static final Item field_151096_cd = (Item)Item.REGISTRY.getById("record_13");
    public static final Item field_151093_ce = (Item)Item.REGISTRY.getById("record_cat");
    public static final Item field_151094_cf = (Item)Item.REGISTRY.getById("record_blocks");
    public static final Item field_151091_cg = (Item)Item.REGISTRY.getById("record_chirp");
    public static final Item field_151092_ch = (Item)Item.REGISTRY.getById("record_far");
    public static final Item field_151089_ci = (Item)Item.REGISTRY.getById("record_mall");
    public static final Item field_151090_cj = (Item)Item.REGISTRY.getById("record_mellohi");
    public static final Item field_151087_ck = (Item)Item.REGISTRY.getById("record_stal");
    public static final Item field_151088_cl = (Item)Item.REGISTRY.getById("record_strad");
    public static final Item field_151085_cm = (Item)Item.REGISTRY.getById("record_ward");
    public static final Item field_151086_cn = (Item)Item.REGISTRY.getById("record_11");
    public static final Item field_151084_co = (Item)Item.REGISTRY.getById("record_wait");
    private static final String __OBFID = "CL_00000044";
}