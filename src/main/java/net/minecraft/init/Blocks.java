package net.minecraft.init;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBeacon;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockCauldron;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockDaylightDetector;
import net.minecraft.block.BlockDeadBush;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockFire;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockHopper;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockMycelium;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.BlockPistonExtension;
import net.minecraft.block.BlockPistonMoving;
import net.minecraft.block.BlockPortal;
import net.minecraft.block.BlockRedstoneComparator;
import net.minecraft.block.BlockRedstoneRepeater;
import net.minecraft.block.BlockRedstoneWire;
import net.minecraft.block.BlockSand;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockStainedGlass;
import net.minecraft.block.BlockStainedGlassPane;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.BlockTripWireHook;

public class Blocks
{
    public static final Block field_150350_a = (Block)Block.field_149771_c.getById("air");
    public static final Block field_150348_b = (Block)Block.field_149771_c.getById("stone");
    public static final BlockGrass field_150349_c = (BlockGrass)Block.field_149771_c.getById("grass");
    public static final Block field_150346_d = (Block)Block.field_149771_c.getById("dirt");
    public static final Block field_150347_e = (Block)Block.field_149771_c.getById("cobblestone");
    public static final Block field_150344_f = (Block)Block.field_149771_c.getById("planks");
    public static final Block field_150345_g = (Block)Block.field_149771_c.getById("sapling");
    public static final Block field_150357_h = (Block)Block.field_149771_c.getById("bedrock");
    public static final BlockLiquid field_150358_i = (BlockLiquid)Block.field_149771_c.getById("flowing_water");
    public static final Block field_150355_j = (Block)Block.field_149771_c.getById("water");
    public static final BlockLiquid field_150356_k = (BlockLiquid)Block.field_149771_c.getById("flowing_lava");
    public static final Block field_150353_l = (Block)Block.field_149771_c.getById("lava");
    public static final BlockSand field_150354_m = (BlockSand)Block.field_149771_c.getById("sand");
    public static final Block field_150351_n = (Block)Block.field_149771_c.getById("gravel");
    public static final Block field_150352_o = (Block)Block.field_149771_c.getById("gold_ore");
    public static final Block field_150366_p = (Block)Block.field_149771_c.getById("iron_ore");
    public static final Block field_150365_q = (Block)Block.field_149771_c.getById("coal_ore");
    public static final Block field_150364_r = (Block)Block.field_149771_c.getById("log");
    public static final Block field_150363_s = (Block)Block.field_149771_c.getById("log2");
    public static final BlockLeaves field_150362_t = (BlockLeaves)Block.field_149771_c.getById("leaves");
    public static final BlockLeaves field_150361_u = (BlockLeaves)Block.field_149771_c.getById("leaves2");
    public static final Block field_150360_v = (Block)Block.field_149771_c.getById("sponge");
    public static final Block field_150359_w = (Block)Block.field_149771_c.getById("glass");
    public static final Block field_150369_x = (Block)Block.field_149771_c.getById("lapis_ore");
    public static final Block field_150368_y = (Block)Block.field_149771_c.getById("lapis_block");
    public static final Block field_150367_z = (Block)Block.field_149771_c.getById("dispenser");
    public static final Block field_150322_A = (Block)Block.field_149771_c.getById("sandstone");
    public static final Block field_150323_B = (Block)Block.field_149771_c.getById("noteblock");
    public static final Block field_150324_C = (Block)Block.field_149771_c.getById("bed");
    public static final Block field_150318_D = (Block)Block.field_149771_c.getById("golden_rail");
    public static final Block field_150319_E = (Block)Block.field_149771_c.getById("detector_rail");
    public static final BlockPistonBase field_150320_F = (BlockPistonBase)Block.field_149771_c.getById("sticky_piston");
    public static final Block field_150321_G = (Block)Block.field_149771_c.getById("web");
    public static final BlockTallGrass field_150329_H = (BlockTallGrass)Block.field_149771_c.getById("tallgrass");
    public static final BlockDeadBush field_150330_I = (BlockDeadBush)Block.field_149771_c.getById("deadbush");
    public static final BlockPistonBase field_150331_J = (BlockPistonBase)Block.field_149771_c.getById("piston");
    public static final BlockPistonExtension field_150332_K = (BlockPistonExtension)Block.field_149771_c.getById("piston_head");
    public static final Block field_150325_L = (Block)Block.field_149771_c.getById("wool");
    public static final BlockPistonMoving field_150326_M = (BlockPistonMoving)Block.field_149771_c.getById("piston_extension");
    public static final BlockFlower field_150327_N = (BlockFlower)Block.field_149771_c.getById("yellow_flower");
    public static final BlockFlower field_150328_O = (BlockFlower)Block.field_149771_c.getById("red_flower");
    public static final BlockBush field_150338_P = (BlockBush)Block.field_149771_c.getById("brown_mushroom");
    public static final BlockBush field_150337_Q = (BlockBush)Block.field_149771_c.getById("red_mushroom");
    public static final Block field_150340_R = (Block)Block.field_149771_c.getById("gold_block");
    public static final Block field_150339_S = (Block)Block.field_149771_c.getById("iron_block");
    public static final BlockSlab field_150334_T = (BlockSlab)Block.field_149771_c.getById("double_stone_slab");
    public static final BlockSlab field_150333_U = (BlockSlab)Block.field_149771_c.getById("stone_slab");
    public static final Block field_150336_V = (Block)Block.field_149771_c.getById("brick_block");
    public static final Block field_150335_W = (Block)Block.field_149771_c.getById("tnt");
    public static final Block field_150342_X = (Block)Block.field_149771_c.getById("bookshelf");
    public static final Block field_150341_Y = (Block)Block.field_149771_c.getById("mossy_cobblestone");
    public static final Block field_150343_Z = (Block)Block.field_149771_c.getById("obsidian");
    public static final Block field_150478_aa = (Block)Block.field_149771_c.getById("torch");
    public static final BlockFire field_150480_ab = (BlockFire)Block.field_149771_c.getById("fire");
    public static final Block field_150474_ac = (Block)Block.field_149771_c.getById("mob_spawner");
    public static final Block field_150476_ad = (Block)Block.field_149771_c.getById("oak_stairs");
    public static final BlockChest field_150486_ae = (BlockChest)Block.field_149771_c.getById("chest");
    public static final BlockRedstoneWire field_150488_af = (BlockRedstoneWire)Block.field_149771_c.getById("redstone_wire");
    public static final Block field_150482_ag = (Block)Block.field_149771_c.getById("diamond_ore");
    public static final Block field_150484_ah = (Block)Block.field_149771_c.getById("diamond_block");
    public static final Block field_150462_ai = (Block)Block.field_149771_c.getById("crafting_table");
    public static final Block field_150464_aj = (Block)Block.field_149771_c.getById("wheat");
    public static final Block field_150458_ak = (Block)Block.field_149771_c.getById("farmland");
    public static final Block field_150460_al = (Block)Block.field_149771_c.getById("furnace");
    public static final Block field_150470_am = (Block)Block.field_149771_c.getById("lit_furnace");
    public static final Block field_150472_an = (Block)Block.field_149771_c.getById("standing_sign");
    public static final Block field_150466_ao = (Block)Block.field_149771_c.getById("wooden_door");
    public static final Block field_150468_ap = (Block)Block.field_149771_c.getById("ladder");
    public static final Block field_150448_aq = (Block)Block.field_149771_c.getById("rail");
    public static final Block field_150446_ar = (Block)Block.field_149771_c.getById("stone_stairs");
    public static final Block field_150444_as = (Block)Block.field_149771_c.getById("wall_sign");
    public static final Block field_150442_at = (Block)Block.field_149771_c.getById("lever");
    public static final Block field_150456_au = (Block)Block.field_149771_c.getById("stone_pressure_plate");
    public static final Block field_150454_av = (Block)Block.field_149771_c.getById("iron_door");
    public static final Block field_150452_aw = (Block)Block.field_149771_c.getById("wooden_pressure_plate");
    public static final Block field_150450_ax = (Block)Block.field_149771_c.getById("redstone_ore");
    public static final Block field_150439_ay = (Block)Block.field_149771_c.getById("lit_redstone_ore");
    public static final Block field_150437_az = (Block)Block.field_149771_c.getById("unlit_redstone_torch");
    public static final Block field_150429_aA = (Block)Block.field_149771_c.getById("redstone_torch");
    public static final Block field_150430_aB = (Block)Block.field_149771_c.getById("stone_button");
    public static final Block field_150431_aC = (Block)Block.field_149771_c.getById("snow_layer");
    public static final Block field_150432_aD = (Block)Block.field_149771_c.getById("ice");
    public static final Block field_150433_aE = (Block)Block.field_149771_c.getById("snow");
    public static final Block field_150434_aF = (Block)Block.field_149771_c.getById("cactus");
    public static final Block field_150435_aG = (Block)Block.field_149771_c.getById("clay");
    public static final Block field_150436_aH = (Block)Block.field_149771_c.getById("reeds");
    public static final Block field_150421_aI = (Block)Block.field_149771_c.getById("jukebox");
    public static final Block field_150422_aJ = (Block)Block.field_149771_c.getById("fence");
    public static final Block field_150423_aK = (Block)Block.field_149771_c.getById("pumpkin");
    public static final Block field_150424_aL = (Block)Block.field_149771_c.getById("netherrack");
    public static final Block field_150425_aM = (Block)Block.field_149771_c.getById("soul_sand");
    public static final Block field_150426_aN = (Block)Block.field_149771_c.getById("glowstone");
    public static final BlockPortal field_150427_aO = (BlockPortal)Block.field_149771_c.getById("portal");
    public static final Block field_150428_aP = (Block)Block.field_149771_c.getById("lit_pumpkin");
    public static final Block field_150414_aQ = (Block)Block.field_149771_c.getById("cake");
    public static final BlockRedstoneRepeater field_150413_aR = (BlockRedstoneRepeater)Block.field_149771_c.getById("unpowered_repeater");
    public static final BlockRedstoneRepeater field_150416_aS = (BlockRedstoneRepeater)Block.field_149771_c.getById("powered_repeater");
    public static final Block field_150415_aT = (Block)Block.field_149771_c.getById("trapdoor");
    public static final Block field_150418_aU = (Block)Block.field_149771_c.getById("monster_egg");
    public static final Block field_150417_aV = (Block)Block.field_149771_c.getById("stonebrick");
    public static final Block field_150420_aW = (Block)Block.field_149771_c.getById("brown_mushroom_block");
    public static final Block field_150419_aX = (Block)Block.field_149771_c.getById("red_mushroom_block");
    public static final Block field_150411_aY = (Block)Block.field_149771_c.getById("iron_bars");
    public static final Block field_150410_aZ = (Block)Block.field_149771_c.getById("glass_pane");
    public static final Block field_150440_ba = (Block)Block.field_149771_c.getById("melon_block");
    public static final Block field_150393_bb = (Block)Block.field_149771_c.getById("pumpkin_stem");
    public static final Block field_150394_bc = (Block)Block.field_149771_c.getById("melon_stem");
    public static final Block field_150395_bd = (Block)Block.field_149771_c.getById("vine");
    public static final Block field_150396_be = (Block)Block.field_149771_c.getById("fence_gate");
    public static final Block field_150389_bf = (Block)Block.field_149771_c.getById("brick_stairs");
    public static final Block field_150390_bg = (Block)Block.field_149771_c.getById("stone_brick_stairs");
    public static final BlockMycelium field_150391_bh = (BlockMycelium)Block.field_149771_c.getById("mycelium");
    public static final Block field_150392_bi = (Block)Block.field_149771_c.getById("waterlily");
    public static final Block field_150385_bj = (Block)Block.field_149771_c.getById("nether_brick");
    public static final Block field_150386_bk = (Block)Block.field_149771_c.getById("nether_brick_fence");
    public static final Block field_150387_bl = (Block)Block.field_149771_c.getById("nether_brick_stairs");
    public static final Block field_150388_bm = (Block)Block.field_149771_c.getById("nether_wart");
    public static final Block field_150381_bn = (Block)Block.field_149771_c.getById("enchanting_table");
    public static final Block field_150382_bo = (Block)Block.field_149771_c.getById("brewing_stand");
    public static final BlockCauldron field_150383_bp = (BlockCauldron)Block.field_149771_c.getById("cauldron");
    public static final Block field_150384_bq = (Block)Block.field_149771_c.getById("end_portal");
    public static final Block field_150378_br = (Block)Block.field_149771_c.getById("end_portal_frame");
    public static final Block field_150377_bs = (Block)Block.field_149771_c.getById("end_stone");
    public static final Block field_150380_bt = (Block)Block.field_149771_c.getById("dragon_egg");
    public static final Block field_150379_bu = (Block)Block.field_149771_c.getById("redstone_lamp");
    public static final Block field_150374_bv = (Block)Block.field_149771_c.getById("lit_redstone_lamp");
    public static final BlockSlab field_150373_bw = (BlockSlab)Block.field_149771_c.getById("double_wooden_slab");
    public static final BlockSlab field_150376_bx = (BlockSlab)Block.field_149771_c.getById("wooden_slab");
    public static final Block field_150375_by = (Block)Block.field_149771_c.getById("cocoa");
    public static final Block field_150372_bz = (Block)Block.field_149771_c.getById("sandstone_stairs");
    public static final Block field_150412_bA = (Block)Block.field_149771_c.getById("emerald_ore");
    public static final Block field_150477_bB = (Block)Block.field_149771_c.getById("ender_chest");
    public static final BlockTripWireHook field_150479_bC = (BlockTripWireHook)Block.field_149771_c.getById("tripwire_hook");
    public static final Block field_150473_bD = (Block)Block.field_149771_c.getById("tripwire");
    public static final Block field_150475_bE = (Block)Block.field_149771_c.getById("emerald_block");
    public static final Block field_150485_bF = (Block)Block.field_149771_c.getById("spruce_stairs");
    public static final Block field_150487_bG = (Block)Block.field_149771_c.getById("birch_stairs");
    public static final Block field_150481_bH = (Block)Block.field_149771_c.getById("jungle_stairs");
    public static final Block field_150483_bI = (Block)Block.field_149771_c.getById("command_block");
    public static final BlockBeacon field_150461_bJ = (BlockBeacon)Block.field_149771_c.getById("beacon");
    public static final Block field_150463_bK = (Block)Block.field_149771_c.getById("cobblestone_wall");
    public static final Block field_150457_bL = (Block)Block.field_149771_c.getById("flower_pot");
    public static final Block field_150459_bM = (Block)Block.field_149771_c.getById("carrots");
    public static final Block field_150469_bN = (Block)Block.field_149771_c.getById("potatoes");
    public static final Block field_150471_bO = (Block)Block.field_149771_c.getById("wooden_button");
    public static final Block field_150465_bP = (Block)Block.field_149771_c.getById("skull");
    public static final Block field_150467_bQ = (Block)Block.field_149771_c.getById("anvil");
    public static final Block field_150447_bR = (Block)Block.field_149771_c.getById("trapped_chest");
    public static final Block field_150445_bS = (Block)Block.field_149771_c.getById("light_weighted_pressure_plate");
    public static final Block field_150443_bT = (Block)Block.field_149771_c.getById("heavy_weighted_pressure_plate");
    public static final BlockRedstoneComparator field_150441_bU = (BlockRedstoneComparator)Block.field_149771_c.getById("unpowered_comparator");
    public static final BlockRedstoneComparator field_150455_bV = (BlockRedstoneComparator)Block.field_149771_c.getById("powered_comparator");
    public static final BlockDaylightDetector field_150453_bW = (BlockDaylightDetector)Block.field_149771_c.getById("daylight_detector");
    public static final Block field_150451_bX = (Block)Block.field_149771_c.getById("redstone_block");
    public static final Block field_150449_bY = (Block)Block.field_149771_c.getById("quartz_ore");
    public static final BlockHopper field_150438_bZ = (BlockHopper)Block.field_149771_c.getById("hopper");
    public static final Block field_150371_ca = (Block)Block.field_149771_c.getById("quartz_block");
    public static final Block field_150370_cb = (Block)Block.field_149771_c.getById("quartz_stairs");
    public static final Block field_150408_cc = (Block)Block.field_149771_c.getById("activator_rail");
    public static final Block field_150409_cd = (Block)Block.field_149771_c.getById("dropper");
    public static final Block field_150406_ce = (Block)Block.field_149771_c.getById("stained_hardened_clay");
    public static final Block field_150407_cf = (Block)Block.field_149771_c.getById("hay_block");
    public static final Block field_150404_cg = (Block)Block.field_149771_c.getById("carpet");
    public static final Block field_150405_ch = (Block)Block.field_149771_c.getById("hardened_clay");
    public static final Block field_150402_ci = (Block)Block.field_149771_c.getById("coal_block");
    public static final Block field_150403_cj = (Block)Block.field_149771_c.getById("packed_ice");
    public static final Block field_150400_ck = (Block)Block.field_149771_c.getById("acacia_stairs");
    public static final Block field_150401_cl = (Block)Block.field_149771_c.getById("dark_oak_stairs");
    public static final BlockDoublePlant field_150398_cm = (BlockDoublePlant)Block.field_149771_c.getById("double_plant");
    public static final BlockStainedGlass field_150399_cn = (BlockStainedGlass)Block.field_149771_c.getById("stained_glass");
    public static final BlockStainedGlassPane field_150397_co = (BlockStainedGlassPane)Block.field_149771_c.getById("stained_glass_pane");
    private static final String __OBFID = "CL_00000204";
}