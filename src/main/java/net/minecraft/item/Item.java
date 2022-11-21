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
    protected static Random field_77697_d = new Random();
    protected int field_77777_bU = 64;
    private int field_77699_b;
    protected boolean field_77789_bW;
    protected boolean field_77787_bX;
    private Item field_77700_c;
    private String field_77785_bY;
    private String field_77774_bZ;
    @SideOnly(Side.CLIENT)
    protected IIcon field_77791_bV;
    protected String field_111218_cA;
    private static final String __OBFID = "CL_00000041";

    public static int func_150891_b(Item p_150891_0_)
    {
        return p_150891_0_ == null ? 0 : REGISTRY.func_148757_b(p_150891_0_);
    }

    public static Item func_150899_d(int p_150899_0_)
    {
        return (Item) REGISTRY.func_148754_a(p_150899_0_);
    }

    public static Item func_150898_a(Block p_150898_0_)
    {
        return func_150899_d(Block.func_149682_b(p_150898_0_));
    }

    public static void func_150900_l()
    {
        REGISTRY.func_148756_a(256, "iron_shovel", (new ItemSpade(Item.ToolMaterial.IRON)).func_77655_b("shovelIron").func_111206_d("iron_shovel"));
        REGISTRY.func_148756_a(257, "iron_pickaxe", (new ItemPickaxe(Item.ToolMaterial.IRON)).func_77655_b("pickaxeIron").func_111206_d("iron_pickaxe"));
        REGISTRY.func_148756_a(258, "iron_axe", (new ItemAxe(Item.ToolMaterial.IRON)).func_77655_b("hatchetIron").func_111206_d("iron_axe"));
        REGISTRY.func_148756_a(259, "flint_and_steel", (new ItemFlintAndSteel()).func_77655_b("flintAndSteel").func_111206_d("flint_and_steel"));
        REGISTRY.func_148756_a(260, "apple", (new ItemFood(4, 0.3F, false)).func_77655_b("apple").func_111206_d("apple"));
        REGISTRY.func_148756_a(261, "bow", (new ItemBow()).func_77655_b("bow").func_111206_d("bow"));
        REGISTRY.func_148756_a(262, "arrow", (new Item()).func_77655_b("arrow").func_77637_a(CreativeTabs.field_78037_j).func_111206_d("arrow"));
        REGISTRY.func_148756_a(263, "coal", (new ItemCoal()).func_77655_b("coal").func_111206_d("coal"));
        REGISTRY.func_148756_a(264, "diamond", (new Item()).func_77655_b("diamond").func_77637_a(CreativeTabs.field_78035_l).func_111206_d("diamond"));
        REGISTRY.func_148756_a(265, "iron_ingot", (new Item()).func_77655_b("ingotIron").func_77637_a(CreativeTabs.field_78035_l).func_111206_d("iron_ingot"));
        REGISTRY.func_148756_a(266, "gold_ingot", (new Item()).func_77655_b("ingotGold").func_77637_a(CreativeTabs.field_78035_l).func_111206_d("gold_ingot"));
        REGISTRY.func_148756_a(267, "iron_sword", (new ItemSword(Item.ToolMaterial.IRON)).func_77655_b("swordIron").func_111206_d("iron_sword"));
        REGISTRY.func_148756_a(268, "wooden_sword", (new ItemSword(Item.ToolMaterial.WOOD)).func_77655_b("swordWood").func_111206_d("wood_sword"));
        REGISTRY.func_148756_a(269, "wooden_shovel", (new ItemSpade(Item.ToolMaterial.WOOD)).func_77655_b("shovelWood").func_111206_d("wood_shovel"));
        REGISTRY.func_148756_a(270, "wooden_pickaxe", (new ItemPickaxe(Item.ToolMaterial.WOOD)).func_77655_b("pickaxeWood").func_111206_d("wood_pickaxe"));
        REGISTRY.func_148756_a(271, "wooden_axe", (new ItemAxe(Item.ToolMaterial.WOOD)).func_77655_b("hatchetWood").func_111206_d("wood_axe"));
        REGISTRY.func_148756_a(272, "stone_sword", (new ItemSword(Item.ToolMaterial.STONE)).func_77655_b("swordStone").func_111206_d("stone_sword"));
        REGISTRY.func_148756_a(273, "stone_shovel", (new ItemSpade(Item.ToolMaterial.STONE)).func_77655_b("shovelStone").func_111206_d("stone_shovel"));
        REGISTRY.func_148756_a(274, "stone_pickaxe", (new ItemPickaxe(Item.ToolMaterial.STONE)).func_77655_b("pickaxeStone").func_111206_d("stone_pickaxe"));
        REGISTRY.func_148756_a(275, "stone_axe", (new ItemAxe(Item.ToolMaterial.STONE)).func_77655_b("hatchetStone").func_111206_d("stone_axe"));
        REGISTRY.func_148756_a(276, "diamond_sword", (new ItemSword(Item.ToolMaterial.EMERALD)).func_77655_b("swordDiamond").func_111206_d("diamond_sword"));
        REGISTRY.func_148756_a(277, "diamond_shovel", (new ItemSpade(Item.ToolMaterial.EMERALD)).func_77655_b("shovelDiamond").func_111206_d("diamond_shovel"));
        REGISTRY.func_148756_a(278, "diamond_pickaxe", (new ItemPickaxe(Item.ToolMaterial.EMERALD)).func_77655_b("pickaxeDiamond").func_111206_d("diamond_pickaxe"));
        REGISTRY.func_148756_a(279, "diamond_axe", (new ItemAxe(Item.ToolMaterial.EMERALD)).func_77655_b("hatchetDiamond").func_111206_d("diamond_axe"));
        REGISTRY.func_148756_a(280, "stick", (new Item()).func_77664_n().func_77655_b("stick").func_77637_a(CreativeTabs.field_78035_l).func_111206_d("stick"));
        REGISTRY.func_148756_a(281, "bowl", (new Item()).func_77655_b("bowl").func_77637_a(CreativeTabs.field_78035_l).func_111206_d("bowl"));
        REGISTRY.func_148756_a(282, "mushroom_stew", (new ItemSoup(6)).func_77655_b("mushroomStew").func_111206_d("mushroom_stew"));
        REGISTRY.func_148756_a(283, "golden_sword", (new ItemSword(Item.ToolMaterial.GOLD)).func_77655_b("swordGold").func_111206_d("gold_sword"));
        REGISTRY.func_148756_a(284, "golden_shovel", (new ItemSpade(Item.ToolMaterial.GOLD)).func_77655_b("shovelGold").func_111206_d("gold_shovel"));
        REGISTRY.func_148756_a(285, "golden_pickaxe", (new ItemPickaxe(Item.ToolMaterial.GOLD)).func_77655_b("pickaxeGold").func_111206_d("gold_pickaxe"));
        REGISTRY.func_148756_a(286, "golden_axe", (new ItemAxe(Item.ToolMaterial.GOLD)).func_77655_b("hatchetGold").func_111206_d("gold_axe"));
        REGISTRY.func_148756_a(287, "string", (new ItemReed(Blocks.TRIPWIRE)).func_77655_b("string").func_77637_a(CreativeTabs.field_78035_l).func_111206_d("string"));
        REGISTRY.func_148756_a(288, "feather", (new Item()).func_77655_b("feather").func_77637_a(CreativeTabs.field_78035_l).func_111206_d("feather"));
        REGISTRY.func_148756_a(289, "gunpowder", (new Item()).func_77655_b("sulphur").func_77631_c(PotionHelper.field_77930_k).func_77637_a(CreativeTabs.field_78035_l).func_111206_d("gunpowder"));
        REGISTRY.func_148756_a(290, "wooden_hoe", (new ItemHoe(Item.ToolMaterial.WOOD)).func_77655_b("hoeWood").func_111206_d("wood_hoe"));
        REGISTRY.func_148756_a(291, "stone_hoe", (new ItemHoe(Item.ToolMaterial.STONE)).func_77655_b("hoeStone").func_111206_d("stone_hoe"));
        REGISTRY.func_148756_a(292, "iron_hoe", (new ItemHoe(Item.ToolMaterial.IRON)).func_77655_b("hoeIron").func_111206_d("iron_hoe"));
        REGISTRY.func_148756_a(293, "diamond_hoe", (new ItemHoe(Item.ToolMaterial.EMERALD)).func_77655_b("hoeDiamond").func_111206_d("diamond_hoe"));
        REGISTRY.func_148756_a(294, "golden_hoe", (new ItemHoe(Item.ToolMaterial.GOLD)).func_77655_b("hoeGold").func_111206_d("gold_hoe"));
        REGISTRY.func_148756_a(295, "wheat_seeds", (new ItemSeeds(Blocks.WHEAT, Blocks.FARMLAND)).func_77655_b("seeds").func_111206_d("seeds_wheat"));
        REGISTRY.func_148756_a(296, "wheat", (new Item()).func_77655_b("wheat").func_77637_a(CreativeTabs.field_78035_l).func_111206_d("wheat"));
        REGISTRY.func_148756_a(297, "bread", (new ItemFood(5, 0.6F, false)).func_77655_b("bread").func_111206_d("bread"));
        REGISTRY.func_148756_a(298, "leather_helmet", (new ItemArmor(ItemArmor.ArmorMaterial.CLOTH, 0, 0)).func_77655_b("helmetCloth").func_111206_d("leather_helmet"));
        REGISTRY.func_148756_a(299, "leather_chestplate", (new ItemArmor(ItemArmor.ArmorMaterial.CLOTH, 0, 1)).func_77655_b("chestplateCloth").func_111206_d("leather_chestplate"));
        REGISTRY.func_148756_a(300, "leather_leggings", (new ItemArmor(ItemArmor.ArmorMaterial.CLOTH, 0, 2)).func_77655_b("leggingsCloth").func_111206_d("leather_leggings"));
        REGISTRY.func_148756_a(301, "leather_boots", (new ItemArmor(ItemArmor.ArmorMaterial.CLOTH, 0, 3)).func_77655_b("bootsCloth").func_111206_d("leather_boots"));
        REGISTRY.func_148756_a(302, "chainmail_helmet", (new ItemArmor(ItemArmor.ArmorMaterial.CHAIN, 1, 0)).func_77655_b("helmetChain").func_111206_d("chainmail_helmet"));
        REGISTRY.func_148756_a(303, "chainmail_chestplate", (new ItemArmor(ItemArmor.ArmorMaterial.CHAIN, 1, 1)).func_77655_b("chestplateChain").func_111206_d("chainmail_chestplate"));
        REGISTRY.func_148756_a(304, "chainmail_leggings", (new ItemArmor(ItemArmor.ArmorMaterial.CHAIN, 1, 2)).func_77655_b("leggingsChain").func_111206_d("chainmail_leggings"));
        REGISTRY.func_148756_a(305, "chainmail_boots", (new ItemArmor(ItemArmor.ArmorMaterial.CHAIN, 1, 3)).func_77655_b("bootsChain").func_111206_d("chainmail_boots"));
        REGISTRY.func_148756_a(306, "iron_helmet", (new ItemArmor(ItemArmor.ArmorMaterial.IRON, 2, 0)).func_77655_b("helmetIron").func_111206_d("iron_helmet"));
        REGISTRY.func_148756_a(307, "iron_chestplate", (new ItemArmor(ItemArmor.ArmorMaterial.IRON, 2, 1)).func_77655_b("chestplateIron").func_111206_d("iron_chestplate"));
        REGISTRY.func_148756_a(308, "iron_leggings", (new ItemArmor(ItemArmor.ArmorMaterial.IRON, 2, 2)).func_77655_b("leggingsIron").func_111206_d("iron_leggings"));
        REGISTRY.func_148756_a(309, "iron_boots", (new ItemArmor(ItemArmor.ArmorMaterial.IRON, 2, 3)).func_77655_b("bootsIron").func_111206_d("iron_boots"));
        REGISTRY.func_148756_a(310, "diamond_helmet", (new ItemArmor(ItemArmor.ArmorMaterial.DIAMOND, 3, 0)).func_77655_b("helmetDiamond").func_111206_d("diamond_helmet"));
        REGISTRY.func_148756_a(311, "diamond_chestplate", (new ItemArmor(ItemArmor.ArmorMaterial.DIAMOND, 3, 1)).func_77655_b("chestplateDiamond").func_111206_d("diamond_chestplate"));
        REGISTRY.func_148756_a(312, "diamond_leggings", (new ItemArmor(ItemArmor.ArmorMaterial.DIAMOND, 3, 2)).func_77655_b("leggingsDiamond").func_111206_d("diamond_leggings"));
        REGISTRY.func_148756_a(313, "diamond_boots", (new ItemArmor(ItemArmor.ArmorMaterial.DIAMOND, 3, 3)).func_77655_b("bootsDiamond").func_111206_d("diamond_boots"));
        REGISTRY.func_148756_a(314, "golden_helmet", (new ItemArmor(ItemArmor.ArmorMaterial.GOLD, 4, 0)).func_77655_b("helmetGold").func_111206_d("gold_helmet"));
        REGISTRY.func_148756_a(315, "golden_chestplate", (new ItemArmor(ItemArmor.ArmorMaterial.GOLD, 4, 1)).func_77655_b("chestplateGold").func_111206_d("gold_chestplate"));
        REGISTRY.func_148756_a(316, "golden_leggings", (new ItemArmor(ItemArmor.ArmorMaterial.GOLD, 4, 2)).func_77655_b("leggingsGold").func_111206_d("gold_leggings"));
        REGISTRY.func_148756_a(317, "golden_boots", (new ItemArmor(ItemArmor.ArmorMaterial.GOLD, 4, 3)).func_77655_b("bootsGold").func_111206_d("gold_boots"));
        REGISTRY.func_148756_a(318, "flint", (new Item()).func_77655_b("flint").func_77637_a(CreativeTabs.field_78035_l).func_111206_d("flint"));
        REGISTRY.func_148756_a(319, "porkchop", (new ItemFood(3, 0.3F, true)).func_77655_b("porkchopRaw").func_111206_d("porkchop_raw"));
        REGISTRY.func_148756_a(320, "cooked_porkchop", (new ItemFood(8, 0.8F, true)).func_77655_b("porkchopCooked").func_111206_d("porkchop_cooked"));
        REGISTRY.func_148756_a(321, "painting", (new ItemHangingEntity(EntityPainting.class)).func_77655_b("painting").func_111206_d("painting"));
        REGISTRY.func_148756_a(322, "golden_apple", (new ItemAppleGold(4, 1.2F, false)).func_77848_i().func_77844_a(Potion.field_76428_l.field_76415_H, 5, 1, 1.0F).func_77655_b("appleGold").func_111206_d("apple_golden"));
        REGISTRY.func_148756_a(323, "sign", (new ItemSign()).func_77655_b("sign").func_111206_d("sign"));
        REGISTRY.func_148756_a(324, "wooden_door", (new ItemDoor(Material.field_151575_d)).func_77655_b("doorWood").func_111206_d("door_wood"));
        Item item = (new ItemBucket(Blocks.AIR)).func_77655_b("bucket").func_77625_d(16).func_111206_d("bucket_empty");
        REGISTRY.func_148756_a(325, "bucket", item);
        REGISTRY.func_148756_a(326, "water_bucket", (new ItemBucket(Blocks.FLOWING_WATER)).func_77655_b("bucketWater").func_77642_a(item).func_111206_d("bucket_water"));
        REGISTRY.func_148756_a(327, "lava_bucket", (new ItemBucket(Blocks.FLOWING_LAVA)).func_77655_b("bucketLava").func_77642_a(item).func_111206_d("bucket_lava"));
        REGISTRY.func_148756_a(328, "minecart", (new ItemMinecart(0)).func_77655_b("minecart").func_111206_d("minecart_normal"));
        REGISTRY.func_148756_a(329, "saddle", (new ItemSaddle()).func_77655_b("saddle").func_111206_d("saddle"));
        REGISTRY.func_148756_a(330, "iron_door", (new ItemDoor(Material.field_151573_f)).func_77655_b("doorIron").func_111206_d("door_iron"));
        REGISTRY.func_148756_a(331, "redstone", (new ItemRedstone()).func_77655_b("redstone").func_77631_c(PotionHelper.field_77932_i).func_111206_d("redstone_dust"));
        REGISTRY.func_148756_a(332, "snowball", (new ItemSnowball()).func_77655_b("snowball").func_111206_d("snowball"));
        REGISTRY.func_148756_a(333, "boat", (new ItemBoat()).func_77655_b("boat").func_111206_d("boat"));
        REGISTRY.func_148756_a(334, "leather", (new Item()).func_77655_b("leather").func_77637_a(CreativeTabs.field_78035_l).func_111206_d("leather"));
        REGISTRY.func_148756_a(335, "milk_bucket", (new ItemBucketMilk()).func_77655_b("milk").func_77642_a(item).func_111206_d("bucket_milk"));
        REGISTRY.func_148756_a(336, "brick", (new Item()).func_77655_b("brick").func_77637_a(CreativeTabs.field_78035_l).func_111206_d("brick"));
        REGISTRY.func_148756_a(337, "clay_ball", (new Item()).func_77655_b("clay").func_77637_a(CreativeTabs.field_78035_l).func_111206_d("clay_ball"));
        REGISTRY.func_148756_a(338, "reeds", (new ItemReed(Blocks.REEDS)).func_77655_b("reeds").func_77637_a(CreativeTabs.field_78035_l).func_111206_d("reeds"));
        REGISTRY.func_148756_a(339, "paper", (new Item()).func_77655_b("paper").func_77637_a(CreativeTabs.field_78026_f).func_111206_d("paper"));
        REGISTRY.func_148756_a(340, "book", (new ItemBook()).func_77655_b("book").func_77637_a(CreativeTabs.field_78026_f).func_111206_d("book_normal"));
        REGISTRY.func_148756_a(341, "slime_ball", (new Item()).func_77655_b("slimeball").func_77637_a(CreativeTabs.field_78026_f).func_111206_d("slimeball"));
        REGISTRY.func_148756_a(342, "chest_minecart", (new ItemMinecart(1)).func_77655_b("minecartChest").func_111206_d("minecart_chest"));
        REGISTRY.func_148756_a(343, "furnace_minecart", (new ItemMinecart(2)).func_77655_b("minecartFurnace").func_111206_d("minecart_furnace"));
        REGISTRY.func_148756_a(344, "egg", (new ItemEgg()).func_77655_b("egg").func_111206_d("egg"));
        REGISTRY.func_148756_a(345, "compass", (new Item()).func_77655_b("compass").func_77637_a(CreativeTabs.field_78040_i).func_111206_d("compass"));
        REGISTRY.func_148756_a(346, "fishing_rod", (new ItemFishingRod()).func_77655_b("fishingRod").func_111206_d("fishing_rod"));
        REGISTRY.func_148756_a(347, "clock", (new Item()).func_77655_b("clock").func_77637_a(CreativeTabs.field_78040_i).func_111206_d("clock"));
        REGISTRY.func_148756_a(348, "glowstone_dust", (new Item()).func_77655_b("yellowDust").func_77631_c(PotionHelper.field_77929_j).func_77637_a(CreativeTabs.field_78035_l).func_111206_d("glowstone_dust"));
        REGISTRY.func_148756_a(349, "fish", (new ItemFishFood(false)).func_77655_b("fish").func_111206_d("fish_raw").func_77627_a(true));
        REGISTRY.func_148756_a(350, "cooked_fished", (new ItemFishFood(true)).func_77655_b("fish").func_111206_d("fish_cooked").func_77627_a(true));
        REGISTRY.func_148756_a(351, "dye", (new ItemDye()).func_77655_b("dyePowder").func_111206_d("dye_powder"));
        REGISTRY.func_148756_a(352, "bone", (new Item()).func_77655_b("bone").func_77664_n().func_77637_a(CreativeTabs.field_78026_f).func_111206_d("bone"));
        REGISTRY.func_148756_a(353, "sugar", (new Item()).func_77655_b("sugar").func_77631_c(PotionHelper.field_77922_b).func_77637_a(CreativeTabs.field_78035_l).func_111206_d("sugar"));
        REGISTRY.func_148756_a(354, "cake", (new ItemReed(Blocks.CAKE)).func_77625_d(1).func_77655_b("cake").func_77637_a(CreativeTabs.field_78039_h).func_111206_d("cake"));
        REGISTRY.func_148756_a(355, "bed", (new ItemBed()).func_77625_d(1).func_77655_b("bed").func_111206_d("bed"));
        REGISTRY.func_148756_a(356, "repeater", (new ItemReed(Blocks.UNPOWERED_REPEATER)).func_77655_b("diode").func_77637_a(CreativeTabs.field_78028_d).func_111206_d("repeater"));
        REGISTRY.func_148756_a(357, "cookie", (new ItemFood(2, 0.1F, false)).func_77655_b("cookie").func_111206_d("cookie"));
        REGISTRY.func_148756_a(358, "filled_map", (new ItemMap()).func_77655_b("map").func_111206_d("map_filled"));
        REGISTRY.func_148756_a(359, "shears", (new ItemShears()).func_77655_b("shears").func_111206_d("shears"));
        REGISTRY.func_148756_a(360, "melon", (new ItemFood(2, 0.3F, false)).func_77655_b("melon").func_111206_d("melon"));
        REGISTRY.func_148756_a(361, "pumpkin_seeds", (new ItemSeeds(Blocks.PUMPKIN_STEM, Blocks.FARMLAND)).func_77655_b("seeds_pumpkin").func_111206_d("seeds_pumpkin"));
        REGISTRY.func_148756_a(362, "melon_seeds", (new ItemSeeds(Blocks.MELON_STEM, Blocks.FARMLAND)).func_77655_b("seeds_melon").func_111206_d("seeds_melon"));
        REGISTRY.func_148756_a(363, "beef", (new ItemFood(3, 0.3F, true)).func_77655_b("beefRaw").func_111206_d("beef_raw"));
        REGISTRY.func_148756_a(364, "cooked_beef", (new ItemFood(8, 0.8F, true)).func_77655_b("beefCooked").func_111206_d("beef_cooked"));
        REGISTRY.func_148756_a(365, "chicken", (new ItemFood(2, 0.3F, true)).func_77844_a(Potion.field_76438_s.field_76415_H, 30, 0, 0.3F).func_77655_b("chickenRaw").func_111206_d("chicken_raw"));
        REGISTRY.func_148756_a(366, "cooked_chicken", (new ItemFood(6, 0.6F, true)).func_77655_b("chickenCooked").func_111206_d("chicken_cooked"));
        REGISTRY.func_148756_a(367, "rotten_flesh", (new ItemFood(4, 0.1F, true)).func_77844_a(Potion.field_76438_s.field_76415_H, 30, 0, 0.8F).func_77655_b("rottenFlesh").func_111206_d("rotten_flesh"));
        REGISTRY.func_148756_a(368, "ender_pearl", (new ItemEnderPearl()).func_77655_b("enderPearl").func_111206_d("ender_pearl"));
        REGISTRY.func_148756_a(369, "blaze_rod", (new Item()).func_77655_b("blazeRod").func_77637_a(CreativeTabs.field_78035_l).func_111206_d("blaze_rod"));
        REGISTRY.func_148756_a(370, "ghast_tear", (new Item()).func_77655_b("ghastTear").func_77631_c(PotionHelper.field_77923_c).func_77637_a(CreativeTabs.field_78038_k).func_111206_d("ghast_tear"));
        REGISTRY.func_148756_a(371, "gold_nugget", (new Item()).func_77655_b("goldNugget").func_77637_a(CreativeTabs.field_78035_l).func_111206_d("gold_nugget"));
        REGISTRY.func_148756_a(372, "nether_wart", (new ItemSeeds(Blocks.NETHER_WART, Blocks.SOUL_SAND)).func_77655_b("netherStalkSeeds").func_77631_c("+4").func_111206_d("nether_wart"));
        REGISTRY.func_148756_a(373, "potion", (new ItemPotion()).func_77655_b("potion").func_111206_d("potion"));
        REGISTRY.func_148756_a(374, "glass_bottle", (new ItemGlassBottle()).func_77655_b("glassBottle").func_111206_d("potion_bottle_empty"));
        REGISTRY.func_148756_a(375, "spider_eye", (new ItemFood(2, 0.8F, false)).func_77844_a(Potion.field_76436_u.field_76415_H, 5, 0, 1.0F).func_77655_b("spiderEye").func_77631_c(PotionHelper.field_77920_d).func_111206_d("spider_eye"));
        REGISTRY.func_148756_a(376, "fermented_spider_eye", (new Item()).func_77655_b("fermentedSpiderEye").func_77631_c(PotionHelper.field_77921_e).func_77637_a(CreativeTabs.field_78038_k).func_111206_d("spider_eye_fermented"));
        REGISTRY.func_148756_a(377, "blaze_powder", (new Item()).func_77655_b("blazePowder").func_77631_c(PotionHelper.field_77919_g).func_77637_a(CreativeTabs.field_78038_k).func_111206_d("blaze_powder"));
        REGISTRY.func_148756_a(378, "magma_cream", (new Item()).func_77655_b("magmaCream").func_77631_c(PotionHelper.field_77931_h).func_77637_a(CreativeTabs.field_78038_k).func_111206_d("magma_cream"));
        REGISTRY.func_148756_a(379, "brewing_stand", (new ItemReed(Blocks.BREWING_STAND)).func_77655_b("brewingStand").func_77637_a(CreativeTabs.field_78038_k).func_111206_d("brewing_stand"));
        REGISTRY.func_148756_a(380, "cauldron", (new ItemReed(Blocks.CAULDRON)).func_77655_b("cauldron").func_77637_a(CreativeTabs.field_78038_k).func_111206_d("cauldron"));
        REGISTRY.func_148756_a(381, "ender_eye", (new ItemEnderEye()).func_77655_b("eyeOfEnder").func_111206_d("ender_eye"));
        REGISTRY.func_148756_a(382, "speckled_melon", (new Item()).func_77655_b("speckledMelon").func_77631_c(PotionHelper.field_77918_f).func_77637_a(CreativeTabs.field_78038_k).func_111206_d("melon_speckled"));
        REGISTRY.func_148756_a(383, "spawn_egg", (new ItemMonsterPlacer()).func_77655_b("monsterPlacer").func_111206_d("spawn_egg"));
        REGISTRY.func_148756_a(384, "experience_bottle", (new ItemExpBottle()).func_77655_b("expBottle").func_111206_d("experience_bottle"));
        REGISTRY.func_148756_a(385, "fire_charge", (new ItemFireball()).func_77655_b("fireball").func_111206_d("fireball"));
        REGISTRY.func_148756_a(386, "writable_book", (new ItemWritableBook()).func_77655_b("writingBook").func_77637_a(CreativeTabs.field_78026_f).func_111206_d("book_writable"));
        REGISTRY.func_148756_a(387, "written_book", (new ItemEditableBook()).func_77655_b("writtenBook").func_111206_d("book_written").func_77625_d(16));
        REGISTRY.func_148756_a(388, "emerald", (new Item()).func_77655_b("emerald").func_77637_a(CreativeTabs.field_78035_l).func_111206_d("emerald"));
        REGISTRY.func_148756_a(389, "item_frame", (new ItemHangingEntity(EntityItemFrame.class)).func_77655_b("frame").func_111206_d("item_frame"));
        REGISTRY.func_148756_a(390, "flower_pot", (new ItemReed(Blocks.FLOWER_POT)).func_77655_b("flowerPot").func_77637_a(CreativeTabs.field_78031_c).func_111206_d("flower_pot"));
        REGISTRY.func_148756_a(391, "carrot", (new ItemSeedFood(4, 0.6F, Blocks.CARROTS, Blocks.FARMLAND)).func_77655_b("carrots").func_111206_d("carrot"));
        REGISTRY.func_148756_a(392, "potato", (new ItemSeedFood(1, 0.3F, Blocks.POTATOES, Blocks.FARMLAND)).func_77655_b("potato").func_111206_d("potato"));
        REGISTRY.func_148756_a(393, "baked_potato", (new ItemFood(6, 0.6F, false)).func_77655_b("potatoBaked").func_111206_d("potato_baked"));
        REGISTRY.func_148756_a(394, "poisonous_potato", (new ItemFood(2, 0.3F, false)).func_77844_a(Potion.field_76436_u.field_76415_H, 5, 0, 0.6F).func_77655_b("potatoPoisonous").func_111206_d("potato_poisonous"));
        REGISTRY.func_148756_a(395, "map", (new ItemEmptyMap()).func_77655_b("emptyMap").func_111206_d("map_empty"));
        REGISTRY.func_148756_a(396, "golden_carrot", (new ItemFood(6, 1.2F, false)).func_77655_b("carrotGolden").func_77631_c(PotionHelper.field_82818_l).func_111206_d("carrot_golden"));
        REGISTRY.func_148756_a(397, "skull", (new ItemSkull()).func_77655_b("skull").func_111206_d("skull"));
        REGISTRY.func_148756_a(398, "carrot_on_a_stick", (new ItemCarrotOnAStick()).func_77655_b("carrotOnAStick").func_111206_d("carrot_on_a_stick"));
        REGISTRY.func_148756_a(399, "nether_star", (new ItemSimpleFoiled()).func_77655_b("netherStar").func_77637_a(CreativeTabs.field_78035_l).func_111206_d("nether_star"));
        REGISTRY.func_148756_a(400, "pumpkin_pie", (new ItemFood(8, 0.3F, false)).func_77655_b("pumpkinPie").func_77637_a(CreativeTabs.field_78039_h).func_111206_d("pumpkin_pie"));
        REGISTRY.func_148756_a(401, "fireworks", (new ItemFirework()).func_77655_b("fireworks").func_111206_d("fireworks"));
        REGISTRY.func_148756_a(402, "firework_charge", (new ItemFireworkCharge()).func_77655_b("fireworksCharge").func_77637_a(CreativeTabs.field_78026_f).func_111206_d("fireworks_charge"));
        REGISTRY.func_148756_a(403, "enchanted_book", (new ItemEnchantedBook()).func_77625_d(1).func_77655_b("enchantedBook").func_111206_d("book_enchanted"));
        REGISTRY.func_148756_a(404, "comparator", (new ItemReed(Blocks.UNPOWERED_COMPARATOR)).func_77655_b("comparator").func_77637_a(CreativeTabs.field_78028_d).func_111206_d("comparator"));
        REGISTRY.func_148756_a(405, "netherbrick", (new Item()).func_77655_b("netherbrick").func_77637_a(CreativeTabs.field_78035_l).func_111206_d("netherbrick"));
        REGISTRY.func_148756_a(406, "quartz", (new Item()).func_77655_b("netherquartz").func_77637_a(CreativeTabs.field_78035_l).func_111206_d("quartz"));
        REGISTRY.func_148756_a(407, "tnt_minecart", (new ItemMinecart(3)).func_77655_b("minecartTnt").func_111206_d("minecart_tnt"));
        REGISTRY.func_148756_a(408, "hopper_minecart", (new ItemMinecart(5)).func_77655_b("minecartHopper").func_111206_d("minecart_hopper"));
        REGISTRY.func_148756_a(417, "iron_horse_armor", (new Item()).func_77655_b("horsearmormetal").func_77625_d(1).func_77637_a(CreativeTabs.field_78026_f).func_111206_d("iron_horse_armor"));
        REGISTRY.func_148756_a(418, "golden_horse_armor", (new Item()).func_77655_b("horsearmorgold").func_77625_d(1).func_77637_a(CreativeTabs.field_78026_f).func_111206_d("gold_horse_armor"));
        REGISTRY.func_148756_a(419, "diamond_horse_armor", (new Item()).func_77655_b("horsearmordiamond").func_77625_d(1).func_77637_a(CreativeTabs.field_78026_f).func_111206_d("diamond_horse_armor"));
        REGISTRY.func_148756_a(420, "lead", (new ItemLead()).func_77655_b("leash").func_111206_d("lead"));
        REGISTRY.func_148756_a(421, "name_tag", (new ItemNameTag()).func_77655_b("nameTag").func_111206_d("name_tag"));
        REGISTRY.func_148756_a(422, "command_block_minecart", (new ItemMinecart(6)).func_77655_b("minecartCommandBlock").func_111206_d("minecart_command_block").func_77637_a((CreativeTabs)null));
        REGISTRY.func_148756_a(2256, "record_13", (new ItemRecord("13")).func_77655_b("record").func_111206_d("record_13"));
        REGISTRY.func_148756_a(2257, "record_cat", (new ItemRecord("cat")).func_77655_b("record").func_111206_d("record_cat"));
        REGISTRY.func_148756_a(2258, "record_blocks", (new ItemRecord("blocks")).func_77655_b("record").func_111206_d("record_blocks"));
        REGISTRY.func_148756_a(2259, "record_chirp", (new ItemRecord("chirp")).func_77655_b("record").func_111206_d("record_chirp"));
        REGISTRY.func_148756_a(2260, "record_far", (new ItemRecord("far")).func_77655_b("record").func_111206_d("record_far"));
        REGISTRY.func_148756_a(2261, "record_mall", (new ItemRecord("mall")).func_77655_b("record").func_111206_d("record_mall"));
        REGISTRY.func_148756_a(2262, "record_mellohi", (new ItemRecord("mellohi")).func_77655_b("record").func_111206_d("record_mellohi"));
        REGISTRY.func_148756_a(2263, "record_stal", (new ItemRecord("stal")).func_77655_b("record").func_111206_d("record_stal"));
        REGISTRY.func_148756_a(2264, "record_strad", (new ItemRecord("strad")).func_77655_b("record").func_111206_d("record_strad"));
        REGISTRY.func_148756_a(2265, "record_ward", (new ItemRecord("ward")).func_77655_b("record").func_111206_d("record_ward"));
        REGISTRY.func_148756_a(2266, "record_11", (new ItemRecord("11")).func_77655_b("record").func_111206_d("record_11"));
        REGISTRY.func_148756_a(2267, "record_wait", (new ItemRecord("wait")).func_77655_b("record").func_111206_d("record_wait"));
        HashSet hashset = Sets.newHashSet(new Block[] {Blocks.AIR, Blocks.BREWING_STAND, Blocks.BED, Blocks.NETHER_WART, Blocks.CAULDRON, Blocks.FLOWER_POT, Blocks.WHEAT, Blocks.REEDS, Blocks.CAKE, Blocks.SKULL, Blocks.PISTON_HEAD, Blocks.PISTON_EXTENSION, Blocks.LIT_REDSTONE_ORE, Blocks.POWERED_REPEATER, Blocks.PUMPKIN_STEM, Blocks.STANDING_SIGN, Blocks.POWERED_COMPARATOR, Blocks.TRIPWIRE, Blocks.LIT_REDSTONE_LAMP, Blocks.MELON_STEM, Blocks.UNLIT_REDSTONE_TORCH, Blocks.UNPOWERED_COMPARATOR, Blocks.REDSTONE_WIRE, Blocks.WALL_SIGN, Blocks.UNPOWERED_REPEATER, Blocks.IRON_DOOR, Blocks.WOODEN_DOOR});
        Iterator iterator = Block.field_149771_c.func_148742_b().iterator();

        while (iterator.hasNext())
        {
            String s = (String)iterator.next();
            Block block = (Block)Block.field_149771_c.getById(s);
            Object object;

            if (block == Blocks.WOOL)
            {
                object = (new ItemCloth(Blocks.WOOL)).func_77655_b("cloth");
            }
            else if (block == Blocks.STAINED_HARDENED_CLAY)
            {
                object = (new ItemCloth(Blocks.STAINED_HARDENED_CLAY)).func_77655_b("clayHardenedStained");
            }
            else if (block == Blocks.STAINED_GLASS)
            {
                object = (new ItemCloth(Blocks.STAINED_GLASS)).func_77655_b("stainedGlass");
            }
            else if (block == Blocks.STAINED_GLASS_PANE)
            {
                object = (new ItemCloth(Blocks.STAINED_GLASS_PANE)).func_77655_b("stainedGlassPane");
            }
            else if (block == Blocks.CARPET)
            {
                object = (new ItemCloth(Blocks.CARPET)).func_77655_b("woolCarpet");
            }
            else if (block == Blocks.DIRT)
            {
                object = (new ItemMultiTexture(Blocks.DIRT, Blocks.DIRT, BlockDirt.field_150009_a)).func_77655_b("dirt");
            }
            else if (block == Blocks.SAND)
            {
                object = (new ItemMultiTexture(Blocks.SAND, Blocks.SAND, BlockSand.field_149838_a)).func_77655_b("sand");
            }
            else if (block == Blocks.LOG)
            {
                object = (new ItemMultiTexture(Blocks.LOG, Blocks.LOG, BlockOldLog.field_150168_M)).func_77655_b("log");
            }
            else if (block == Blocks.LOG_2)
            {
                object = (new ItemMultiTexture(Blocks.LOG_2, Blocks.LOG_2, BlockNewLog.field_150169_M)).func_77655_b("log");
            }
            else if (block == Blocks.PLANKS)
            {
                object = (new ItemMultiTexture(Blocks.PLANKS, Blocks.PLANKS, BlockWood.field_150096_a)).func_77655_b("wood");
            }
            else if (block == Blocks.MONSTER_EGG)
            {
                object = (new ItemMultiTexture(Blocks.MONSTER_EGG, Blocks.MONSTER_EGG, BlockSilverfish.field_150198_a)).func_77655_b("monsterStoneEgg");
            }
            else if (block == Blocks.STONEBRICK)
            {
                object = (new ItemMultiTexture(Blocks.STONEBRICK, Blocks.STONEBRICK, BlockStoneBrick.field_150142_a)).func_77655_b("stonebricksmooth");
            }
            else if (block == Blocks.SANDSTONE)
            {
                object = (new ItemMultiTexture(Blocks.SANDSTONE, Blocks.SANDSTONE, BlockSandStone.field_150157_a)).func_77655_b("sandStone");
            }
            else if (block == Blocks.QUARTZ_BLOCK)
            {
                object = (new ItemMultiTexture(Blocks.QUARTZ_BLOCK, Blocks.QUARTZ_BLOCK, BlockQuartz.field_150191_a)).func_77655_b("quartzBlock");
            }
            else if (block == Blocks.STONE_SLAB)
            {
                object = (new ItemSlab(Blocks.STONE_SLAB, Blocks.STONE_SLAB, Blocks.DOUBLE_STONE_SLAB, false)).func_77655_b("stoneSlab");
            }
            else if (block == Blocks.DOUBLE_STONE_SLAB)
            {
                object = (new ItemSlab(Blocks.DOUBLE_STONE_SLAB, Blocks.STONE_SLAB, Blocks.DOUBLE_STONE_SLAB, true)).func_77655_b("stoneSlab");
            }
            else if (block == Blocks.WOODEN_SLAB)
            {
                object = (new ItemSlab(Blocks.WOODEN_SLAB, Blocks.WOODEN_SLAB, Blocks.DOUBLE_WOODEN_SLAB, false)).func_77655_b("woodSlab");
            }
            else if (block == Blocks.DOUBLE_WOODEN_SLAB)
            {
                object = (new ItemSlab(Blocks.DOUBLE_WOODEN_SLAB, Blocks.WOODEN_SLAB, Blocks.DOUBLE_WOODEN_SLAB, true)).func_77655_b("woodSlab");
            }
            else if (block == Blocks.SAPLING)
            {
                object = (new ItemMultiTexture(Blocks.SAPLING, Blocks.SAPLING, BlockSapling.field_149882_a)).func_77655_b("sapling");
            }
            else if (block == Blocks.LEAVES)
            {
                object = (new ItemLeaves(Blocks.LEAVES)).func_77655_b("leaves");
            }
            else if (block == Blocks.LEAVES_2)
            {
                object = (new ItemLeaves(Blocks.LEAVES_2)).func_77655_b("leaves");
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
                object = (new ItemMultiTexture(Blocks.YELLOW_FLOWER, Blocks.YELLOW_FLOWER, BlockFlower.field_149858_b)).func_77655_b("flower");
            }
            else if (block == Blocks.RED_FLOWER)
            {
                object = (new ItemMultiTexture(Blocks.RED_FLOWER, Blocks.RED_FLOWER, BlockFlower.field_149859_a)).func_77655_b("rose");
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
                object = (new ItemMultiTexture(Blocks.COBBLESTONE_WALL, Blocks.COBBLESTONE_WALL, BlockWall.field_150092_a)).func_77655_b("cobbleWall");
            }
            else if (block == Blocks.ANVIL)
            {
                object = (new ItemAnvilBlock(Blocks.ANVIL)).func_77655_b("anvil");
            }
            else if (block == Blocks.DOUBLE_PLANT)
            {
                object = (new ItemDoublePlant(Blocks.DOUBLE_PLANT, Blocks.DOUBLE_PLANT, BlockDoublePlant.field_149892_a)).func_77655_b("doublePlant");
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

    public Item func_77625_d(int p_77625_1_)
    {
        this.field_77777_bU = p_77625_1_;
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

    public int func_77639_j()
    {
        return this.field_77777_bU;
    }

    public int func_77647_b(int p_77647_1_)
    {
        return 0;
    }

    public boolean func_77614_k()
    {
        return this.field_77787_bX;
    }

    public Item func_77627_a(boolean p_77627_1_)
    {
        this.field_77787_bX = p_77627_1_;
        return this;
    }

    public int func_77612_l()
    {
        return this.field_77699_b;
    }

    public Item func_77656_e(int p_77656_1_)
    {
        this.field_77699_b = p_77656_1_;
        return this;
    }

    public boolean func_77645_m()
    {
        return this.field_77699_b > 0 && !this.field_77787_bX;
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

    public Item func_77655_b(String p_77655_1_)
    {
        this.field_77774_bZ = p_77655_1_;
        return this;
    }

    public String func_77657_g(ItemStack p_77657_1_)
    {
        String s = this.func_77667_c(p_77657_1_);
        return s == null ? "" : StatCollector.func_74838_a(s);
    }

    public String func_77658_a()
    {
        return "item." + this.field_77774_bZ;
    }

    public String func_77667_c(ItemStack p_77667_1_)
    {
        return "item." + this.field_77774_bZ;
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
        return this.func_77639_j() == 1 && this.func_77645_m();
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

    public Item func_111206_d(String p_111206_1_)
    {
        this.field_111218_cA = p_111206_1_;
        return this;
    }

    @SideOnly(Side.CLIENT)
    protected String func_111208_A()
    {
        return this.field_111218_cA == null ? "MISSING_ICON_ITEM_" + REGISTRY.func_148757_b(this) + "_" + this.field_77774_bZ : this.field_111218_cA;
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