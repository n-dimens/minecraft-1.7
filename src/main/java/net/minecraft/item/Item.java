package net.minecraft.item;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockNewLog;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockQuartz;
import net.minecraft.block.BlockSand;
import net.minecraft.block.BlockSandStone;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.BlockSilverfish;
import net.minecraft.block.BlockStoneBrick;
import net.minecraft.block.BlockWall;
import net.minecraft.block.BlockWood;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.item.EntityPainting;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionHelper;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.RegistryNamespaced;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class Item
{
    public static final RegistryNamespaced REGISTRY = new RegistryNamespaced();
    protected static final UUID field_111210_e = UUID.fromString("CB3F55D3-645C-4F38-A497-9C13A33DB5CF");
    private CreativeTabs field_77701_a;
    protected static Random randomizer = new Random();
    protected int stackMaxSize = 64;
    private int durability;
    protected boolean field_77789_bW;
    protected boolean field_77787_bX;
    private Item field_77700_c;
    private String field_77785_bY;
    private String id;
    @SideOnly(Side.CLIENT)
    protected IIcon field_77791_bV;
    protected String iconId;
    private static final String __OBFID = "CL_00000041";

    public static int func_150891_b(Item item)
    {
        return item == null ? 0 : REGISTRY.func_148757_b(item);
    }

    public static Item func_150899_d(int p_150899_0_)
    {
        return (Item) REGISTRY.func_148754_a(p_150899_0_);
    }

    public static Item func_150898_a(Block block)
    {
        return func_150899_d(Block.func_149682_b(block));
    }

    public static void fillRegistry()
    {
        REGISTRY.func_148756_a(256, "iron_shovel", (new ItemSpade(Item.ToolMaterial.IRON)).setId("shovelIron").setIconId("iron_shovel"));
        REGISTRY.func_148756_a(257, "iron_pickaxe", (new ItemPickaxe(Item.ToolMaterial.IRON)).setId("pickaxeIron").setIconId("iron_pickaxe"));
        REGISTRY.func_148756_a(258, "iron_axe", (new ItemAxe(Item.ToolMaterial.IRON)).setId("hatchetIron").setIconId("iron_axe"));
        REGISTRY.func_148756_a(259, "flint_and_steel", (new ItemFlintAndSteel()).setId("flintAndSteel").setIconId("flint_and_steel"));
        REGISTRY.func_148756_a(260, "apple", (new ItemFood(4, 0.3F, false)).setId("apple").setIconId("apple"));
        REGISTRY.func_148756_a(261, "bow", (new ItemBow()).setId("bow").setIconId("bow"));
        REGISTRY.func_148756_a(262, "arrow", (new Item()).setId("arrow").func_77637_a(CreativeTabs.field_78037_j).setIconId("arrow"));
        REGISTRY.func_148756_a(263, "coal", (new ItemCoal()).setId("coal").setIconId("coal"));
        REGISTRY.func_148756_a(264, "diamond", (new Item()).setId("diamond").func_77637_a(CreativeTabs.field_78035_l).setIconId("diamond"));
        REGISTRY.func_148756_a(265, "iron_ingot", (new Item()).setId("ingotIron").func_77637_a(CreativeTabs.field_78035_l).setIconId("iron_ingot"));
        REGISTRY.func_148756_a(266, "gold_ingot", (new Item()).setId("ingotGold").func_77637_a(CreativeTabs.field_78035_l).setIconId("gold_ingot"));
        REGISTRY.func_148756_a(267, "iron_sword", (new ItemSword(Item.ToolMaterial.IRON)).setId("swordIron").setIconId("iron_sword"));
        REGISTRY.func_148756_a(268, "wooden_sword", (new ItemSword(Item.ToolMaterial.WOOD)).setId("swordWood").setIconId("wood_sword"));
        REGISTRY.func_148756_a(269, "wooden_shovel", (new ItemSpade(Item.ToolMaterial.WOOD)).setId("shovelWood").setIconId("wood_shovel"));
        REGISTRY.func_148756_a(270, "wooden_pickaxe", (new ItemPickaxe(Item.ToolMaterial.WOOD)).setId("pickaxeWood").setIconId("wood_pickaxe"));
        REGISTRY.func_148756_a(271, "wooden_axe", (new ItemAxe(Item.ToolMaterial.WOOD)).setId("hatchetWood").setIconId("wood_axe"));
        REGISTRY.func_148756_a(272, "stone_sword", (new ItemSword(Item.ToolMaterial.STONE)).setId("swordStone").setIconId("stone_sword"));
        REGISTRY.func_148756_a(273, "stone_shovel", (new ItemSpade(Item.ToolMaterial.STONE)).setId("shovelStone").setIconId("stone_shovel"));
        REGISTRY.func_148756_a(274, "stone_pickaxe", (new ItemPickaxe(Item.ToolMaterial.STONE)).setId("pickaxeStone").setIconId("stone_pickaxe"));
        REGISTRY.func_148756_a(275, "stone_axe", (new ItemAxe(Item.ToolMaterial.STONE)).setId("hatchetStone").setIconId("stone_axe"));
        REGISTRY.func_148756_a(276, "diamond_sword", (new ItemSword(Item.ToolMaterial.EMERALD)).setId("swordDiamond").setIconId("diamond_sword"));
        REGISTRY.func_148756_a(277, "diamond_shovel", (new ItemSpade(Item.ToolMaterial.EMERALD)).setId("shovelDiamond").setIconId("diamond_shovel"));
        REGISTRY.func_148756_a(278, "diamond_pickaxe", (new ItemPickaxe(Item.ToolMaterial.EMERALD)).setId("pickaxeDiamond").setIconId("diamond_pickaxe"));
        REGISTRY.func_148756_a(279, "diamond_axe", (new ItemAxe(Item.ToolMaterial.EMERALD)).setId("hatchetDiamond").setIconId("diamond_axe"));
        REGISTRY.func_148756_a(280, "stick", (new Item()).func_77664_n().setId("stick").func_77637_a(CreativeTabs.field_78035_l).setIconId("stick"));
        REGISTRY.func_148756_a(281, "bowl", (new Item()).setId("bowl").func_77637_a(CreativeTabs.field_78035_l).setIconId("bowl"));
        REGISTRY.func_148756_a(282, "mushroom_stew", (new ItemSoup(6)).setId("mushroomStew").setIconId("mushroom_stew"));
        REGISTRY.func_148756_a(283, "golden_sword", (new ItemSword(Item.ToolMaterial.GOLD)).setId("swordGold").setIconId("gold_sword"));
        REGISTRY.func_148756_a(284, "golden_shovel", (new ItemSpade(Item.ToolMaterial.GOLD)).setId("shovelGold").setIconId("gold_shovel"));
        REGISTRY.func_148756_a(285, "golden_pickaxe", (new ItemPickaxe(Item.ToolMaterial.GOLD)).setId("pickaxeGold").setIconId("gold_pickaxe"));
        REGISTRY.func_148756_a(286, "golden_axe", (new ItemAxe(Item.ToolMaterial.GOLD)).setId("hatchetGold").setIconId("gold_axe"));
        REGISTRY.func_148756_a(287, "string", (new ItemReed(Blocks.TRIPWIRE)).setId("string").func_77637_a(CreativeTabs.field_78035_l).setIconId("string"));
        REGISTRY.func_148756_a(288, "feather", (new Item()).setId("feather").func_77637_a(CreativeTabs.field_78035_l).setIconId("feather"));
        REGISTRY.func_148756_a(289, "gunpowder", (new Item()).setId("sulphur").func_77631_c(PotionHelper.field_77930_k).func_77637_a(CreativeTabs.field_78035_l).setIconId("gunpowder"));
        REGISTRY.func_148756_a(290, "wooden_hoe", (new ItemHoe(Item.ToolMaterial.WOOD)).setId("hoeWood").setIconId("wood_hoe"));
        REGISTRY.func_148756_a(291, "stone_hoe", (new ItemHoe(Item.ToolMaterial.STONE)).setId("hoeStone").setIconId("stone_hoe"));
        REGISTRY.func_148756_a(292, "iron_hoe", (new ItemHoe(Item.ToolMaterial.IRON)).setId("hoeIron").setIconId("iron_hoe"));
        REGISTRY.func_148756_a(293, "diamond_hoe", (new ItemHoe(Item.ToolMaterial.EMERALD)).setId("hoeDiamond").setIconId("diamond_hoe"));
        REGISTRY.func_148756_a(294, "golden_hoe", (new ItemHoe(Item.ToolMaterial.GOLD)).setId("hoeGold").setIconId("gold_hoe"));
        REGISTRY.func_148756_a(295, "wheat_seeds", (new ItemSeeds(Blocks.WHEAT, Blocks.FARMLAND)).setId("seeds").setIconId("seeds_wheat"));
        REGISTRY.func_148756_a(296, "wheat", (new Item()).setId("wheat").func_77637_a(CreativeTabs.field_78035_l).setIconId("wheat"));
        REGISTRY.func_148756_a(297, "bread", (new ItemFood(5, 0.6F, false)).setId("bread").setIconId("bread"));
        REGISTRY.func_148756_a(298, "leather_helmet", (new ItemArmor(ItemArmor.ArmorMaterial.CLOTH, 0, 0)).setId("helmetCloth").setIconId("leather_helmet"));
        REGISTRY.func_148756_a(299, "leather_chestplate", (new ItemArmor(ItemArmor.ArmorMaterial.CLOTH, 0, 1)).setId("chestplateCloth").setIconId("leather_chestplate"));
        REGISTRY.func_148756_a(300, "leather_leggings", (new ItemArmor(ItemArmor.ArmorMaterial.CLOTH, 0, 2)).setId("leggingsCloth").setIconId("leather_leggings"));
        REGISTRY.func_148756_a(301, "leather_boots", (new ItemArmor(ItemArmor.ArmorMaterial.CLOTH, 0, 3)).setId("bootsCloth").setIconId("leather_boots"));
        REGISTRY.func_148756_a(302, "chainmail_helmet", (new ItemArmor(ItemArmor.ArmorMaterial.CHAIN, 1, 0)).setId("helmetChain").setIconId("chainmail_helmet"));
        REGISTRY.func_148756_a(303, "chainmail_chestplate", (new ItemArmor(ItemArmor.ArmorMaterial.CHAIN, 1, 1)).setId("chestplateChain").setIconId("chainmail_chestplate"));
        REGISTRY.func_148756_a(304, "chainmail_leggings", (new ItemArmor(ItemArmor.ArmorMaterial.CHAIN, 1, 2)).setId("leggingsChain").setIconId("chainmail_leggings"));
        REGISTRY.func_148756_a(305, "chainmail_boots", (new ItemArmor(ItemArmor.ArmorMaterial.CHAIN, 1, 3)).setId("bootsChain").setIconId("chainmail_boots"));
        REGISTRY.func_148756_a(306, "iron_helmet", (new ItemArmor(ItemArmor.ArmorMaterial.IRON, 2, 0)).setId("helmetIron").setIconId("iron_helmet"));
        REGISTRY.func_148756_a(307, "iron_chestplate", (new ItemArmor(ItemArmor.ArmorMaterial.IRON, 2, 1)).setId("chestplateIron").setIconId("iron_chestplate"));
        REGISTRY.func_148756_a(308, "iron_leggings", (new ItemArmor(ItemArmor.ArmorMaterial.IRON, 2, 2)).setId("leggingsIron").setIconId("iron_leggings"));
        REGISTRY.func_148756_a(309, "iron_boots", (new ItemArmor(ItemArmor.ArmorMaterial.IRON, 2, 3)).setId("bootsIron").setIconId("iron_boots"));
        REGISTRY.func_148756_a(310, "diamond_helmet", (new ItemArmor(ItemArmor.ArmorMaterial.DIAMOND, 3, 0)).setId("helmetDiamond").setIconId("diamond_helmet"));
        REGISTRY.func_148756_a(311, "diamond_chestplate", (new ItemArmor(ItemArmor.ArmorMaterial.DIAMOND, 3, 1)).setId("chestplateDiamond").setIconId("diamond_chestplate"));
        REGISTRY.func_148756_a(312, "diamond_leggings", (new ItemArmor(ItemArmor.ArmorMaterial.DIAMOND, 3, 2)).setId("leggingsDiamond").setIconId("diamond_leggings"));
        REGISTRY.func_148756_a(313, "diamond_boots", (new ItemArmor(ItemArmor.ArmorMaterial.DIAMOND, 3, 3)).setId("bootsDiamond").setIconId("diamond_boots"));
        REGISTRY.func_148756_a(314, "golden_helmet", (new ItemArmor(ItemArmor.ArmorMaterial.GOLD, 4, 0)).setId("helmetGold").setIconId("gold_helmet"));
        REGISTRY.func_148756_a(315, "golden_chestplate", (new ItemArmor(ItemArmor.ArmorMaterial.GOLD, 4, 1)).setId("chestplateGold").setIconId("gold_chestplate"));
        REGISTRY.func_148756_a(316, "golden_leggings", (new ItemArmor(ItemArmor.ArmorMaterial.GOLD, 4, 2)).setId("leggingsGold").setIconId("gold_leggings"));
        REGISTRY.func_148756_a(317, "golden_boots", (new ItemArmor(ItemArmor.ArmorMaterial.GOLD, 4, 3)).setId("bootsGold").setIconId("gold_boots"));
        REGISTRY.func_148756_a(318, "flint", (new Item()).setId("flint").func_77637_a(CreativeTabs.field_78035_l).setIconId("flint"));
        REGISTRY.func_148756_a(319, "porkchop", (new ItemFood(3, 0.3F, true)).setId("porkchopRaw").setIconId("porkchop_raw"));
        REGISTRY.func_148756_a(320, "cooked_porkchop", (new ItemFood(8, 0.8F, true)).setId("porkchopCooked").setIconId("porkchop_cooked"));
        REGISTRY.func_148756_a(321, "painting", (new ItemHangingEntity(EntityPainting.class)).setId("painting").setIconId("painting"));
        REGISTRY.func_148756_a(322, "golden_apple", (new ItemAppleGold(4, 1.2F, false)).func_77848_i().func_77844_a(Potion.field_76428_l.field_76415_H, 5, 1, 1.0F).setId("appleGold").setIconId("apple_golden"));
        REGISTRY.func_148756_a(323, "sign", (new ItemSign()).setId("sign").setIconId("sign"));
        REGISTRY.func_148756_a(324, "wooden_door", (new ItemDoor(Material.field_151575_d)).setId("doorWood").setIconId("door_wood"));
        Item item = (new ItemBucket(Blocks.AIR)).setId("bucket").setStackMaxSize(16).setIconId("bucket_empty");
        REGISTRY.func_148756_a(325, "bucket", item);
        REGISTRY.func_148756_a(326, "water_bucket", (new ItemBucket(Blocks.FLOWING_WATER)).setId("bucketWater").func_77642_a(item).setIconId("bucket_water"));
        REGISTRY.func_148756_a(327, "lava_bucket", (new ItemBucket(Blocks.FLOWING_LAVA)).setId("bucketLava").func_77642_a(item).setIconId("bucket_lava"));
        REGISTRY.func_148756_a(328, "minecart", (new ItemMinecart(0)).setId("minecart").setIconId("minecart_normal"));
        REGISTRY.func_148756_a(329, "saddle", (new ItemSaddle()).setId("saddle").setIconId("saddle"));
        REGISTRY.func_148756_a(330, "iron_door", (new ItemDoor(Material.field_151573_f)).setId("doorIron").setIconId("door_iron"));
        REGISTRY.func_148756_a(331, "redstone", (new ItemRedstone()).setId("redstone").func_77631_c(PotionHelper.field_77932_i).setIconId("redstone_dust"));
        REGISTRY.func_148756_a(332, "snowball", (new ItemSnowball()).setId("snowball").setIconId("snowball"));
        REGISTRY.func_148756_a(333, "boat", (new ItemBoat()).setId("boat").setIconId("boat"));
        REGISTRY.func_148756_a(334, "leather", (new Item()).setId("leather").func_77637_a(CreativeTabs.field_78035_l).setIconId("leather"));
        REGISTRY.func_148756_a(335, "milk_bucket", (new ItemBucketMilk()).setId("milk").func_77642_a(item).setIconId("bucket_milk"));
        REGISTRY.func_148756_a(336, "brick", (new Item()).setId("brick").func_77637_a(CreativeTabs.field_78035_l).setIconId("brick"));
        REGISTRY.func_148756_a(337, "clay_ball", (new Item()).setId("clay").func_77637_a(CreativeTabs.field_78035_l).setIconId("clay_ball"));
        REGISTRY.func_148756_a(338, "reeds", (new ItemReed(Blocks.REEDS)).setId("reeds").func_77637_a(CreativeTabs.field_78035_l).setIconId("reeds"));
        REGISTRY.func_148756_a(339, "paper", (new Item()).setId("paper").func_77637_a(CreativeTabs.field_78026_f).setIconId("paper"));
        REGISTRY.func_148756_a(340, "book", (new ItemBook()).setId("book").func_77637_a(CreativeTabs.field_78026_f).setIconId("book_normal"));
        REGISTRY.func_148756_a(341, "slime_ball", (new Item()).setId("slimeball").func_77637_a(CreativeTabs.field_78026_f).setIconId("slimeball"));
        REGISTRY.func_148756_a(342, "chest_minecart", (new ItemMinecart(1)).setId("minecartChest").setIconId("minecart_chest"));
        REGISTRY.func_148756_a(343, "furnace_minecart", (new ItemMinecart(2)).setId("minecartFurnace").setIconId("minecart_furnace"));
        REGISTRY.func_148756_a(344, "egg", (new ItemEgg()).setId("egg").setIconId("egg"));
        REGISTRY.func_148756_a(345, "compass", (new Item()).setId("compass").func_77637_a(CreativeTabs.field_78040_i).setIconId("compass"));
        REGISTRY.func_148756_a(346, "fishing_rod", (new ItemFishingRod()).setId("fishingRod").setIconId("fishing_rod"));
        REGISTRY.func_148756_a(347, "clock", (new Item()).setId("clock").func_77637_a(CreativeTabs.field_78040_i).setIconId("clock"));
        REGISTRY.func_148756_a(348, "glowstone_dust", (new Item()).setId("yellowDust").func_77631_c(PotionHelper.field_77929_j).func_77637_a(CreativeTabs.field_78035_l).setIconId("glowstone_dust"));
        REGISTRY.func_148756_a(349, "fish", (new ItemFishFood(false)).setId("fish").setIconId("fish_raw").func_77627_a(true));
        REGISTRY.func_148756_a(350, "cooked_fished", (new ItemFishFood(true)).setId("fish").setIconId("fish_cooked").func_77627_a(true));
        REGISTRY.func_148756_a(351, "dye", (new ItemDye()).setId("dyePowder").setIconId("dye_powder"));
        REGISTRY.func_148756_a(352, "bone", (new Item()).setId("bone").func_77664_n().func_77637_a(CreativeTabs.field_78026_f).setIconId("bone"));
        REGISTRY.func_148756_a(353, "sugar", (new Item()).setId("sugar").func_77631_c(PotionHelper.field_77922_b).func_77637_a(CreativeTabs.field_78035_l).setIconId("sugar"));
        REGISTRY.func_148756_a(354, "cake", (new ItemReed(Blocks.CAKE)).setStackMaxSize(1).setId("cake").func_77637_a(CreativeTabs.field_78039_h).setIconId("cake"));
        REGISTRY.func_148756_a(355, "bed", (new ItemBed()).setStackMaxSize(1).setId("bed").setIconId("bed"));
        REGISTRY.func_148756_a(356, "repeater", (new ItemReed(Blocks.UNPOWERED_REPEATER)).setId("diode").func_77637_a(CreativeTabs.field_78028_d).setIconId("repeater"));
        REGISTRY.func_148756_a(357, "cookie", (new ItemFood(2, 0.1F, false)).setId("cookie").setIconId("cookie"));
        REGISTRY.func_148756_a(358, "filled_map", (new ItemMap()).setId("map").setIconId("map_filled"));
        REGISTRY.func_148756_a(359, "shears", (new ItemShears()).setId("shears").setIconId("shears"));
        REGISTRY.func_148756_a(360, "melon", (new ItemFood(2, 0.3F, false)).setId("melon").setIconId("melon"));
        REGISTRY.func_148756_a(361, "pumpkin_seeds", (new ItemSeeds(Blocks.PUMPKIN_STEM, Blocks.FARMLAND)).setId("seeds_pumpkin").setIconId("seeds_pumpkin"));
        REGISTRY.func_148756_a(362, "melon_seeds", (new ItemSeeds(Blocks.MELON_STEM, Blocks.FARMLAND)).setId("seeds_melon").setIconId("seeds_melon"));
        REGISTRY.func_148756_a(363, "beef", (new ItemFood(3, 0.3F, true)).setId("beefRaw").setIconId("beef_raw"));
        REGISTRY.func_148756_a(364, "cooked_beef", (new ItemFood(8, 0.8F, true)).setId("beefCooked").setIconId("beef_cooked"));
        REGISTRY.func_148756_a(365, "chicken", (new ItemFood(2, 0.3F, true)).func_77844_a(Potion.field_76438_s.field_76415_H, 30, 0, 0.3F).setId("chickenRaw").setIconId("chicken_raw"));
        REGISTRY.func_148756_a(366, "cooked_chicken", (new ItemFood(6, 0.6F, true)).setId("chickenCooked").setIconId("chicken_cooked"));
        REGISTRY.func_148756_a(367, "rotten_flesh", (new ItemFood(4, 0.1F, true)).func_77844_a(Potion.field_76438_s.field_76415_H, 30, 0, 0.8F).setId("rottenFlesh").setIconId("rotten_flesh"));
        REGISTRY.func_148756_a(368, "ender_pearl", (new ItemEnderPearl()).setId("enderPearl").setIconId("ender_pearl"));
        REGISTRY.func_148756_a(369, "blaze_rod", (new Item()).setId("blazeRod").func_77637_a(CreativeTabs.field_78035_l).setIconId("blaze_rod"));
        REGISTRY.func_148756_a(370, "ghast_tear", (new Item()).setId("ghastTear").func_77631_c(PotionHelper.field_77923_c).func_77637_a(CreativeTabs.field_78038_k).setIconId("ghast_tear"));
        REGISTRY.func_148756_a(371, "gold_nugget", (new Item()).setId("goldNugget").func_77637_a(CreativeTabs.field_78035_l).setIconId("gold_nugget"));
        REGISTRY.func_148756_a(372, "nether_wart", (new ItemSeeds(Blocks.NETHER_WART, Blocks.SOUL_SAND)).setId("netherStalkSeeds").func_77631_c("+4").setIconId("nether_wart"));
        REGISTRY.func_148756_a(373, "potion", (new ItemPotion()).setId("potion").setIconId("potion"));
        REGISTRY.func_148756_a(374, "glass_bottle", (new ItemGlassBottle()).setId("glassBottle").setIconId("potion_bottle_empty"));
        REGISTRY.func_148756_a(375, "spider_eye", (new ItemFood(2, 0.8F, false)).func_77844_a(Potion.field_76436_u.field_76415_H, 5, 0, 1.0F).setId("spiderEye").func_77631_c(PotionHelper.field_77920_d).setIconId("spider_eye"));
        REGISTRY.func_148756_a(376, "fermented_spider_eye", (new Item()).setId("fermentedSpiderEye").func_77631_c(PotionHelper.field_77921_e).func_77637_a(CreativeTabs.field_78038_k).setIconId("spider_eye_fermented"));
        REGISTRY.func_148756_a(377, "blaze_powder", (new Item()).setId("blazePowder").func_77631_c(PotionHelper.field_77919_g).func_77637_a(CreativeTabs.field_78038_k).setIconId("blaze_powder"));
        REGISTRY.func_148756_a(378, "magma_cream", (new Item()).setId("magmaCream").func_77631_c(PotionHelper.field_77931_h).func_77637_a(CreativeTabs.field_78038_k).setIconId("magma_cream"));
        REGISTRY.func_148756_a(379, "brewing_stand", (new ItemReed(Blocks.BREWING_STAND)).setId("brewingStand").func_77637_a(CreativeTabs.field_78038_k).setIconId("brewing_stand"));
        REGISTRY.func_148756_a(380, "cauldron", (new ItemReed(Blocks.CAULDRON)).setId("cauldron").func_77637_a(CreativeTabs.field_78038_k).setIconId("cauldron"));
        REGISTRY.func_148756_a(381, "ender_eye", (new ItemEnderEye()).setId("eyeOfEnder").setIconId("ender_eye"));
        REGISTRY.func_148756_a(382, "speckled_melon", (new Item()).setId("speckledMelon").func_77631_c(PotionHelper.field_77918_f).func_77637_a(CreativeTabs.field_78038_k).setIconId("melon_speckled"));
        REGISTRY.func_148756_a(383, "spawn_egg", (new ItemMonsterPlacer()).setId("monsterPlacer").setIconId("spawn_egg"));
        REGISTRY.func_148756_a(384, "experience_bottle", (new ItemExpBottle()).setId("expBottle").setIconId("experience_bottle"));
        REGISTRY.func_148756_a(385, "fire_charge", (new ItemFireball()).setId("fireball").setIconId("fireball"));
        REGISTRY.func_148756_a(386, "writable_book", (new ItemWritableBook()).setId("writingBook").func_77637_a(CreativeTabs.field_78026_f).setIconId("book_writable"));
        REGISTRY.func_148756_a(387, "written_book", (new ItemEditableBook()).setId("writtenBook").setIconId("book_written").setStackMaxSize(16));
        REGISTRY.func_148756_a(388, "emerald", (new Item()).setId("emerald").func_77637_a(CreativeTabs.field_78035_l).setIconId("emerald"));
        REGISTRY.func_148756_a(389, "item_frame", (new ItemHangingEntity(EntityItemFrame.class)).setId("frame").setIconId("item_frame"));
        REGISTRY.func_148756_a(390, "flower_pot", (new ItemReed(Blocks.FLOWER_POT)).setId("flowerPot").func_77637_a(CreativeTabs.field_78031_c).setIconId("flower_pot"));
        REGISTRY.func_148756_a(391, "carrot", (new ItemSeedFood(4, 0.6F, Blocks.CARROTS, Blocks.FARMLAND)).setId("carrots").setIconId("carrot"));
        REGISTRY.func_148756_a(392, "potato", (new ItemSeedFood(1, 0.3F, Blocks.POTATOES, Blocks.FARMLAND)).setId("potato").setIconId("potato"));
        REGISTRY.func_148756_a(393, "baked_potato", (new ItemFood(6, 0.6F, false)).setId("potatoBaked").setIconId("potato_baked"));
        REGISTRY.func_148756_a(394, "poisonous_potato", (new ItemFood(2, 0.3F, false)).func_77844_a(Potion.field_76436_u.field_76415_H, 5, 0, 0.6F).setId("potatoPoisonous").setIconId("potato_poisonous"));
        REGISTRY.func_148756_a(395, "map", (new ItemEmptyMap()).setId("emptyMap").setIconId("map_empty"));
        REGISTRY.func_148756_a(396, "golden_carrot", (new ItemFood(6, 1.2F, false)).setId("carrotGolden").func_77631_c(PotionHelper.field_82818_l).setIconId("carrot_golden"));
        REGISTRY.func_148756_a(397, "skull", (new ItemSkull()).setId("skull").setIconId("skull"));
        REGISTRY.func_148756_a(398, "carrot_on_a_stick", (new ItemCarrotOnAStick()).setId("carrotOnAStick").setIconId("carrot_on_a_stick"));
        REGISTRY.func_148756_a(399, "nether_star", (new ItemSimpleFoiled()).setId("netherStar").func_77637_a(CreativeTabs.field_78035_l).setIconId("nether_star"));
        REGISTRY.func_148756_a(400, "pumpkin_pie", (new ItemFood(8, 0.3F, false)).setId("pumpkinPie").func_77637_a(CreativeTabs.field_78039_h).setIconId("pumpkin_pie"));
        REGISTRY.func_148756_a(401, "fireworks", (new ItemFirework()).setId("fireworks").setIconId("fireworks"));
        REGISTRY.func_148756_a(402, "firework_charge", (new ItemFireworkCharge()).setId("fireworksCharge").func_77637_a(CreativeTabs.field_78026_f).setIconId("fireworks_charge"));
        REGISTRY.func_148756_a(403, "enchanted_book", (new ItemEnchantedBook()).setStackMaxSize(1).setId("enchantedBook").setIconId("book_enchanted"));
        REGISTRY.func_148756_a(404, "comparator", (new ItemReed(Blocks.UNPOWERED_COMPARATOR)).setId("comparator").func_77637_a(CreativeTabs.field_78028_d).setIconId("comparator"));
        REGISTRY.func_148756_a(405, "netherbrick", (new Item()).setId("netherbrick").func_77637_a(CreativeTabs.field_78035_l).setIconId("netherbrick"));
        REGISTRY.func_148756_a(406, "quartz", (new Item()).setId("netherquartz").func_77637_a(CreativeTabs.field_78035_l).setIconId("quartz"));
        REGISTRY.func_148756_a(407, "tnt_minecart", (new ItemMinecart(3)).setId("minecartTnt").setIconId("minecart_tnt"));
        REGISTRY.func_148756_a(408, "hopper_minecart", (new ItemMinecart(5)).setId("minecartHopper").setIconId("minecart_hopper"));
        REGISTRY.func_148756_a(417, "iron_horse_armor", (new Item()).setId("horsearmormetal").setStackMaxSize(1).func_77637_a(CreativeTabs.field_78026_f).setIconId("iron_horse_armor"));
        REGISTRY.func_148756_a(418, "golden_horse_armor", (new Item()).setId("horsearmorgold").setStackMaxSize(1).func_77637_a(CreativeTabs.field_78026_f).setIconId("gold_horse_armor"));
        REGISTRY.func_148756_a(419, "diamond_horse_armor", (new Item()).setId("horsearmordiamond").setStackMaxSize(1).func_77637_a(CreativeTabs.field_78026_f).setIconId("diamond_horse_armor"));
        REGISTRY.func_148756_a(420, "lead", (new ItemLead()).setId("leash").setIconId("lead"));
        REGISTRY.func_148756_a(421, "name_tag", (new ItemNameTag()).setId("nameTag").setIconId("name_tag"));
        REGISTRY.func_148756_a(422, "command_block_minecart", (new ItemMinecart(6)).setId("minecartCommandBlock").setIconId("minecart_command_block").func_77637_a((CreativeTabs)null));
        REGISTRY.func_148756_a(2256, "record_13", (new ItemRecord("13")).setId("record").setIconId("record_13"));
        REGISTRY.func_148756_a(2257, "record_cat", (new ItemRecord("cat")).setId("record").setIconId("record_cat"));
        REGISTRY.func_148756_a(2258, "record_blocks", (new ItemRecord("blocks")).setId("record").setIconId("record_blocks"));
        REGISTRY.func_148756_a(2259, "record_chirp", (new ItemRecord("chirp")).setId("record").setIconId("record_chirp"));
        REGISTRY.func_148756_a(2260, "record_far", (new ItemRecord("far")).setId("record").setIconId("record_far"));
        REGISTRY.func_148756_a(2261, "record_mall", (new ItemRecord("mall")).setId("record").setIconId("record_mall"));
        REGISTRY.func_148756_a(2262, "record_mellohi", (new ItemRecord("mellohi")).setId("record").setIconId("record_mellohi"));
        REGISTRY.func_148756_a(2263, "record_stal", (new ItemRecord("stal")).setId("record").setIconId("record_stal"));
        REGISTRY.func_148756_a(2264, "record_strad", (new ItemRecord("strad")).setId("record").setIconId("record_strad"));
        REGISTRY.func_148756_a(2265, "record_ward", (new ItemRecord("ward")).setId("record").setIconId("record_ward"));
        REGISTRY.func_148756_a(2266, "record_11", (new ItemRecord("11")).setId("record").setIconId("record_11"));
        REGISTRY.func_148756_a(2267, "record_wait", (new ItemRecord("wait")).setId("record").setIconId("record_wait"));
        HashSet hashset = Sets.newHashSet(new Block[] {Blocks.AIR, Blocks.BREWING_STAND, Blocks.BED, Blocks.NETHER_WART, Blocks.CAULDRON, Blocks.FLOWER_POT, Blocks.WHEAT, Blocks.REEDS, Blocks.CAKE, Blocks.SKULL, Blocks.PISTON_HEAD, Blocks.PISTON_EXTENSION, Blocks.LIT_REDSTONE_ORE, Blocks.POWERED_REPEATER, Blocks.PUMPKIN_STEM, Blocks.STANDING_SIGN, Blocks.POWERED_COMPARATOR, Blocks.TRIPWIRE, Blocks.LIT_REDSTONE_LAMP, Blocks.MELON_STEM, Blocks.UNLIT_REDSTONE_TORCH, Blocks.UNPOWERED_COMPARATOR, Blocks.REDSTONE_WIRE, Blocks.WALL_SIGN, Blocks.UNPOWERED_REPEATER, Blocks.IRON_DOOR, Blocks.WOODEN_DOOR});
        Iterator iterator = Block.field_149771_c.func_148742_b().iterator();

        while (iterator.hasNext())
        {
            String s = (String)iterator.next();
            Block block = (Block)Block.field_149771_c.getById(s);
            Object object;

            if (block == Blocks.WOOL)
            {
                object = (new ItemCloth(Blocks.WOOL)).setId("cloth");
            }
            else if (block == Blocks.STAINED_HARDENED_CLAY)
            {
                object = (new ItemCloth(Blocks.STAINED_HARDENED_CLAY)).setId("clayHardenedStained");
            }
            else if (block == Blocks.STAINED_GLASS)
            {
                object = (new ItemCloth(Blocks.STAINED_GLASS)).setId("stainedGlass");
            }
            else if (block == Blocks.STAINED_GLASS_PANE)
            {
                object = (new ItemCloth(Blocks.STAINED_GLASS_PANE)).setId("stainedGlassPane");
            }
            else if (block == Blocks.CARPET)
            {
                object = (new ItemCloth(Blocks.CARPET)).setId("woolCarpet");
            }
            else if (block == Blocks.DIRT)
            {
                object = (new ItemMultiTexture(Blocks.DIRT, Blocks.DIRT, BlockDirt.field_150009_a)).setId("dirt");
            }
            else if (block == Blocks.SAND)
            {
                object = (new ItemMultiTexture(Blocks.SAND, Blocks.SAND, BlockSand.field_149838_a)).setId("sand");
            }
            else if (block == Blocks.LOG)
            {
                object = (new ItemMultiTexture(Blocks.LOG, Blocks.LOG, BlockOldLog.field_150168_M)).setId("log");
            }
            else if (block == Blocks.LOG_2)
            {
                object = (new ItemMultiTexture(Blocks.LOG_2, Blocks.LOG_2, BlockNewLog.field_150169_M)).setId("log");
            }
            else if (block == Blocks.PLANKS)
            {
                object = (new ItemMultiTexture(Blocks.PLANKS, Blocks.PLANKS, BlockWood.field_150096_a)).setId("wood");
            }
            else if (block == Blocks.MONSTER_EGG)
            {
                object = (new ItemMultiTexture(Blocks.MONSTER_EGG, Blocks.MONSTER_EGG, BlockSilverfish.field_150198_a)).setId("monsterStoneEgg");
            }
            else if (block == Blocks.STONEBRICK)
            {
                object = (new ItemMultiTexture(Blocks.STONEBRICK, Blocks.STONEBRICK, BlockStoneBrick.field_150142_a)).setId("stonebricksmooth");
            }
            else if (block == Blocks.SANDSTONE)
            {
                object = (new ItemMultiTexture(Blocks.SANDSTONE, Blocks.SANDSTONE, BlockSandStone.field_150157_a)).setId("sandStone");
            }
            else if (block == Blocks.QUARTZ_BLOCK)
            {
                object = (new ItemMultiTexture(Blocks.QUARTZ_BLOCK, Blocks.QUARTZ_BLOCK, BlockQuartz.field_150191_a)).setId("quartzBlock");
            }
            else if (block == Blocks.STONE_SLAB)
            {
                object = (new ItemSlab(Blocks.STONE_SLAB, Blocks.STONE_SLAB, Blocks.DOUBLE_STONE_SLAB, false)).setId("stoneSlab");
            }
            else if (block == Blocks.DOUBLE_STONE_SLAB)
            {
                object = (new ItemSlab(Blocks.DOUBLE_STONE_SLAB, Blocks.STONE_SLAB, Blocks.DOUBLE_STONE_SLAB, true)).setId("stoneSlab");
            }
            else if (block == Blocks.WOODEN_SLAB)
            {
                object = (new ItemSlab(Blocks.WOODEN_SLAB, Blocks.WOODEN_SLAB, Blocks.DOUBLE_WOODEN_SLAB, false)).setId("woodSlab");
            }
            else if (block == Blocks.DOUBLE_WOODEN_SLAB)
            {
                object = (new ItemSlab(Blocks.DOUBLE_WOODEN_SLAB, Blocks.WOODEN_SLAB, Blocks.DOUBLE_WOODEN_SLAB, true)).setId("woodSlab");
            }
            else if (block == Blocks.SAPLING)
            {
                object = (new ItemMultiTexture(Blocks.SAPLING, Blocks.SAPLING, BlockSapling.field_149882_a)).setId("sapling");
            }
            else if (block == Blocks.LEAVES)
            {
                object = (new ItemLeaves(Blocks.LEAVES)).setId("leaves");
            }
            else if (block == Blocks.LEAVES_2)
            {
                object = (new ItemLeaves(Blocks.LEAVES_2)).setId("leaves");
            }
            else if (block == Blocks.VINE)
            {
                object = new ItemColored(Blocks.VINE, false);
            }
            else if (block == Blocks.TALLGRASS)
            {
                object = (new ItemColored(Blocks.TALLGRASS, true)).func_150943_a(new String[] {"shrub", "grass", "fern"});
            }
            else if (block == Blocks.YELLOW_FLOWER)
            {
                object = (new ItemMultiTexture(Blocks.YELLOW_FLOWER, Blocks.YELLOW_FLOWER, BlockFlower.field_149858_b)).setId("flower");
            }
            else if (block == Blocks.RED_FLOWER)
            {
                object = (new ItemMultiTexture(Blocks.RED_FLOWER, Blocks.RED_FLOWER, BlockFlower.field_149859_a)).setId("rose");
            }
            else if (block == Blocks.SNOW_LAYER)
            {
                object = new ItemSnow(Blocks.SNOW_LAYER, Blocks.SNOW_LAYER);
            }
            else if (block == Blocks.WATERLILY)
            {
                object = new ItemLilyPad(Blocks.WATERLILY);
            }
            else if (block == Blocks.PISTON)
            {
                object = new ItemPiston(Blocks.PISTON);
            }
            else if (block == Blocks.STICKY_PISTON)
            {
                object = new ItemPiston(Blocks.STICKY_PISTON);
            }
            else if (block == Blocks.COBBLESTONE_WALL)
            {
                object = (new ItemMultiTexture(Blocks.COBBLESTONE_WALL, Blocks.COBBLESTONE_WALL, BlockWall.field_150092_a)).setId("cobbleWall");
            }
            else if (block == Blocks.ANVIL)
            {
                object = (new ItemAnvilBlock(Blocks.ANVIL)).setId("anvil");
            }
            else if (block == Blocks.DOUBLE_PLANT)
            {
                object = (new ItemDoublePlant(Blocks.DOUBLE_PLANT, Blocks.DOUBLE_PLANT, BlockDoublePlant.field_149892_a)).setId("doublePlant");
            }
            else
            {
                if (hashset.contains(block))
                {
                    continue;
                }

                object = new ItemBlock(block);
            }

            REGISTRY.func_148756_a(Block.func_149682_b(block), s, object);
        }
    }

    public Item setStackMaxSize(int value)
    {
        this.stackMaxSize = value;
        return this;
    }

    @SideOnly(Side.CLIENT)
    public int func_94901_k()
    {
        return 1;
    }

    @SideOnly(Side.CLIENT)
    public IIcon func_77617_a(int p_77617_1_)
    {
        return this.field_77791_bV;
    }

    @SideOnly(Side.CLIENT)
    public IIcon func_77650_f(ItemStack p_77650_1_)
    {
        return this.func_77617_a(p_77650_1_.func_77960_j());
    }

    public boolean func_77648_a(ItemStack p_77648_1_, EntityPlayer p_77648_2_, World p_77648_3_, int p_77648_4_, int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_)
    {
        return false;
    }

    public float func_150893_a(ItemStack p_150893_1_, Block p_150893_2_)
    {
        return 1.0F;
    }

    public ItemStack func_77659_a(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_)
    {
        return p_77659_1_;
    }

    public ItemStack func_77654_b(ItemStack p_77654_1_, World p_77654_2_, EntityPlayer p_77654_3_)
    {
        return p_77654_1_;
    }

    public int getStackMaxSize()
    {
        return this.stackMaxSize;
    }

    public int func_77647_b(int p_77647_1_)
    {
        return 0;
    }

    public boolean func_77614_k()
    {
        return this.field_77787_bX;
    }

    public Item func_77627_a(boolean value)
    {
        this.field_77787_bX = value;
        return this;
    }

    public int getDurability()
    {
        return this.durability;
    }

    public Item setDurability(int value)
    {
        this.durability = value;
        return this;
    }

    public boolean func_77645_m()
    {
        return this.durability > 0 && !this.field_77787_bX;
    }

    public boolean func_77644_a(ItemStack p_77644_1_, EntityLivingBase p_77644_2_, EntityLivingBase p_77644_3_)
    {
        return false;
    }

    public boolean func_150894_a(ItemStack p_150894_1_, World p_150894_2_, Block p_150894_3_, int p_150894_4_, int p_150894_5_, int p_150894_6_, EntityLivingBase p_150894_7_)
    {
        return false;
    }

    public boolean func_150897_b(Block p_150897_1_)
    {
        return false;
    }

    public boolean func_111207_a(ItemStack p_111207_1_, EntityPlayer p_111207_2_, EntityLivingBase p_111207_3_)
    {
        return false;
    }

    public Item func_77664_n()
    {
        this.field_77789_bW = true;
        return this;
    }

    @SideOnly(Side.CLIENT)
    public boolean func_77662_d()
    {
        return this.field_77789_bW;
    }

    @SideOnly(Side.CLIENT)
    public boolean func_77629_n_()
    {
        return false;
    }

    public Item setId(String value)
    {
        this.id = value;
        return this;
    }

    public String func_77657_g(ItemStack p_77657_1_)
    {
        String s = this.func_77667_c(p_77657_1_);
        return s == null ? "" : StatCollector.func_74838_a(s);
    }

    public String func_77658_a()
    {
        return "item." + this.id;
    }

    public String func_77667_c(ItemStack p_77667_1_)
    {
        return "item." + this.id;
    }

    public Item func_77642_a(Item p_77642_1_)
    {
        this.field_77700_c = p_77642_1_;
        return this;
    }

    public boolean func_77630_h(ItemStack p_77630_1_)
    {
        return true;
    }

    public boolean func_77651_p()
    {
        return true;
    }

    public Item func_77668_q()
    {
        return this.field_77700_c;
    }

    public boolean func_77634_r()
    {
        return this.field_77700_c != null;
    }

    @SideOnly(Side.CLIENT)
    public int func_82790_a(ItemStack p_82790_1_, int p_82790_2_)
    {
        return 16777215;
    }

    public void func_77663_a(ItemStack p_77663_1_, World p_77663_2_, Entity p_77663_3_, int p_77663_4_, boolean p_77663_5_) {}

    public void func_77622_d(ItemStack p_77622_1_, World p_77622_2_, EntityPlayer p_77622_3_) {}

    public boolean func_77643_m_()
    {
        return false;
    }

    public EnumAction func_77661_b(ItemStack p_77661_1_)
    {
        return EnumAction.none;
    }

    public int func_77626_a(ItemStack p_77626_1_)
    {
        return 0;
    }

    public void func_77615_a(ItemStack p_77615_1_, World p_77615_2_, EntityPlayer p_77615_3_, int p_77615_4_) {}

    public Item func_77631_c(String p_77631_1_)
    {
        this.field_77785_bY = p_77631_1_;
        return this;
    }

    public String func_150896_i(ItemStack p_150896_1_)
    {
        return this.field_77785_bY;
    }

    public boolean func_150892_m(ItemStack p_150892_1_)
    {
        return this.func_150896_i(p_150892_1_) != null;
    }

    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {}

    public String func_77653_i(ItemStack p_77653_1_)
    {
        return ("" + StatCollector.func_74838_a(this.func_77657_g(p_77653_1_) + ".name")).trim();
    }

    @SideOnly(Side.CLIENT)
    public boolean func_77636_d(ItemStack p_77636_1_)
    {
        return p_77636_1_.func_77948_v();
    }

    public EnumRarity func_77613_e(ItemStack p_77613_1_)
    {
        return p_77613_1_.func_77948_v() ? EnumRarity.rare : EnumRarity.common;
    }

    public boolean func_77616_k(ItemStack p_77616_1_)
    {
        return this.getStackMaxSize() == 1 && this.func_77645_m();
    }

    protected MovingObjectPosition func_77621_a(World p_77621_1_, EntityPlayer p_77621_2_, boolean p_77621_3_)
    {
        float f = 1.0F;
        float f1 = p_77621_2_.field_70127_C + (p_77621_2_.field_70125_A - p_77621_2_.field_70127_C) * f;
        float f2 = p_77621_2_.field_70126_B + (p_77621_2_.field_70177_z - p_77621_2_.field_70126_B) * f;
        double d0 = p_77621_2_.field_70169_q + (p_77621_2_.field_70165_t - p_77621_2_.field_70169_q) * (double)f;
        double d1 = p_77621_2_.field_70167_r + (p_77621_2_.field_70163_u - p_77621_2_.field_70167_r) * (double)f + 1.62D - (double)p_77621_2_.field_70129_M;
        double d2 = p_77621_2_.field_70166_s + (p_77621_2_.field_70161_v - p_77621_2_.field_70166_s) * (double)f;
        Vec3 vec3 = Vec3.func_72443_a(d0, d1, d2);
        float f3 = MathHelper.func_76134_b(-f2 * 0.017453292F - (float)Math.PI);
        float f4 = MathHelper.func_76126_a(-f2 * 0.017453292F - (float)Math.PI);
        float f5 = -MathHelper.func_76134_b(-f1 * 0.017453292F);
        float f6 = MathHelper.func_76126_a(-f1 * 0.017453292F);
        float f7 = f4 * f5;
        float f8 = f3 * f5;
        double d3 = 5.0D;
        Vec3 vec31 = vec3.func_72441_c((double)f7 * d3, (double)f6 * d3, (double)f8 * d3);
        return p_77621_1_.func_147447_a(vec3, vec31, p_77621_3_, !p_77621_3_, false);
    }

    public int func_77619_b()
    {
        return 0;
    }

    @SideOnly(Side.CLIENT)
    public boolean func_77623_v()
    {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public IIcon func_77618_c(int p_77618_1_, int p_77618_2_)
    {
        return this.func_77617_a(p_77618_1_);
    }

    @SideOnly(Side.CLIENT)
    public void func_150895_a(Item p_150895_1_, CreativeTabs p_150895_2_, List p_150895_3_)
    {
        p_150895_3_.add(new ItemStack(p_150895_1_, 1, 0));
    }

    public Item func_77637_a(CreativeTabs p_77637_1_)
    {
        this.field_77701_a = p_77637_1_;
        return this;
    }

    @SideOnly(Side.CLIENT)
    public CreativeTabs func_77640_w()
    {
        return this.field_77701_a;
    }

    public boolean func_82788_x()
    {
        return true;
    }

    public boolean func_82789_a(ItemStack p_82789_1_, ItemStack p_82789_2_)
    {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public void func_94581_a(IIconRegister p_94581_1_)
    {
        this.field_77791_bV = p_94581_1_.func_94245_a(this.func_111208_A());
    }

    public Multimap func_111205_h()
    {
        return HashMultimap.create();
    }

    public Item setIconId(String value)
    {
        this.iconId = value;
        return this;
    }

    @SideOnly(Side.CLIENT)
    protected String func_111208_A()
    {
        return this.iconId == null ? "MISSING_ICON_ITEM_" + REGISTRY.func_148757_b(this) + "_" + this.id : this.iconId;
    }

    public static enum ToolMaterial
    {
        WOOD(0, 59, 2.0F, 0.0F, 15),
        STONE(1, 131, 4.0F, 1.0F, 5),
        IRON(2, 250, 6.0F, 2.0F, 14),
        EMERALD(3, 1561, 8.0F, 3.0F, 10),
        GOLD(0, 32, 12.0F, 0.0F, 22);
        private final int field_78001_f;
        private final int field_78002_g;
        private final float field_78010_h;
        private final float field_78011_i;
        private final int field_78008_j;

        private static final String __OBFID = "CL_00000042";

        private ToolMaterial(int p_i1874_3_, int p_i1874_4_, float p_i1874_5_, float p_i1874_6_, int p_i1874_7_)
        {
            this.field_78001_f = p_i1874_3_;
            this.field_78002_g = p_i1874_4_;
            this.field_78010_h = p_i1874_5_;
            this.field_78011_i = p_i1874_6_;
            this.field_78008_j = p_i1874_7_;
        }

        public int func_77997_a()
        {
            return this.field_78002_g;
        }

        public float func_77998_b()
        {
            return this.field_78010_h;
        }

        public float func_78000_c()
        {
            return this.field_78011_i;
        }

        public int func_77996_d()
        {
            return this.field_78001_f;
        }

        public int func_77995_e()
        {
            return this.field_78008_j;
        }

        public Item func_150995_f()
        {
            return this == WOOD ? Item.func_150898_a(Blocks.PLANKS) : (this == STONE ? Item.func_150898_a(Blocks.COBBLESTONE) : (this == GOLD ? Items.GOLD_INGOT : (this == IRON ? Items.IRON_INGOT : (this == EMERALD ? Items.DIAMOND : null))));
        }
    }
}