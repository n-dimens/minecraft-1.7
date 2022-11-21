package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;

public class WorldGenDungeons extends WorldGenerator
{
    public static final WeightedRandomChestContent[] field_111189_a = new WeightedRandomChestContent[] {new WeightedRandomChestContent(Items.SADDLE, 0, 1, 1, 10), new WeightedRandomChestContent(Items.IRON_INGOT, 0, 1, 4, 10), new WeightedRandomChestContent(Items.BREAD, 0, 1, 1, 10), new WeightedRandomChestContent(Items.WHEAT, 0, 1, 4, 10), new WeightedRandomChestContent(Items.GUNPOWDER, 0, 1, 4, 10), new WeightedRandomChestContent(Items.STRING, 0, 1, 4, 10), new WeightedRandomChestContent(Items.BUCKET, 0, 1, 1, 10), new WeightedRandomChestContent(Items.GOLDEN_APPLE, 0, 1, 1, 1), new WeightedRandomChestContent(Items.REDSTONE, 0, 1, 4, 10), new WeightedRandomChestContent(Items.RECORD_13, 0, 1, 1, 10), new WeightedRandomChestContent(Items.RECORD_CAT, 0, 1, 1, 10), new WeightedRandomChestContent(Items.NAME_TAG, 0, 1, 1, 10), new WeightedRandomChestContent(Items.GOLDEN_HORSE_ARMOR, 0, 1, 1, 2), new WeightedRandomChestContent(Items.IRON_HORSE_ARMOR, 0, 1, 1, 5), new WeightedRandomChestContent(Items.DIAMOND_HORSE_ARMOR, 0, 1, 1, 1)};
    private static final String __OBFID = "CL_00000425";

    public boolean func_76484_a(World p_76484_1_, Random p_76484_2_, int p_76484_3_, int p_76484_4_, int p_76484_5_)
    {
        byte b0 = 3;
        int l = p_76484_2_.nextInt(2) + 2;
        int i1 = p_76484_2_.nextInt(2) + 2;
        int j1 = 0;
        int k1;
        int l1;
        int i2;

        for (k1 = p_76484_3_ - l - 1; k1 <= p_76484_3_ + l + 1; ++k1)
        {
            for (l1 = p_76484_4_ - 1; l1 <= p_76484_4_ + b0 + 1; ++l1)
            {
                for (i2 = p_76484_5_ - i1 - 1; i2 <= p_76484_5_ + i1 + 1; ++i2)
                {
                    Material material = p_76484_1_.func_147439_a(k1, l1, i2).func_149688_o();

                    if (l1 == p_76484_4_ - 1 && !material.func_76220_a())
                    {
                        return false;
                    }

                    if (l1 == p_76484_4_ + b0 + 1 && !material.func_76220_a())
                    {
                        return false;
                    }

                    if ((k1 == p_76484_3_ - l - 1 || k1 == p_76484_3_ + l + 1 || i2 == p_76484_5_ - i1 - 1 || i2 == p_76484_5_ + i1 + 1) && l1 == p_76484_4_ && p_76484_1_.func_147437_c(k1, l1, i2) && p_76484_1_.func_147437_c(k1, l1 + 1, i2))
                    {
                        ++j1;
                    }
                }
            }
        }

        if (j1 >= 1 && j1 <= 5)
        {
            for (k1 = p_76484_3_ - l - 1; k1 <= p_76484_3_ + l + 1; ++k1)
            {
                for (l1 = p_76484_4_ + b0; l1 >= p_76484_4_ - 1; --l1)
                {
                    for (i2 = p_76484_5_ - i1 - 1; i2 <= p_76484_5_ + i1 + 1; ++i2)
                    {
                        if (k1 != p_76484_3_ - l - 1 && l1 != p_76484_4_ - 1 && i2 != p_76484_5_ - i1 - 1 && k1 != p_76484_3_ + l + 1 && l1 != p_76484_4_ + b0 + 1 && i2 != p_76484_5_ + i1 + 1)
                        {
                            p_76484_1_.func_147468_f(k1, l1, i2);
                        }
                        else if (l1 >= 0 && !p_76484_1_.func_147439_a(k1, l1 - 1, i2).func_149688_o().func_76220_a())
                        {
                            p_76484_1_.func_147468_f(k1, l1, i2);
                        }
                        else if (p_76484_1_.func_147439_a(k1, l1, i2).func_149688_o().func_76220_a())
                        {
                            if (l1 == p_76484_4_ - 1 && p_76484_2_.nextInt(4) != 0)
                            {
                                p_76484_1_.func_147465_d(k1, l1, i2, Blocks.field_150341_Y, 0, 2);
                            }
                            else
                            {
                                p_76484_1_.func_147465_d(k1, l1, i2, Blocks.field_150347_e, 0, 2);
                            }
                        }
                    }
                }
            }

            k1 = 0;

            while (k1 < 2)
            {
                l1 = 0;

                while (true)
                {
                    if (l1 < 3)
                    {
                        label197:
                        {
                            i2 = p_76484_3_ + p_76484_2_.nextInt(l * 2 + 1) - l;
                            int j2 = p_76484_5_ + p_76484_2_.nextInt(i1 * 2 + 1) - i1;

                            if (p_76484_1_.func_147437_c(i2, p_76484_4_, j2))
                            {
                                int k2 = 0;

                                if (p_76484_1_.func_147439_a(i2 - 1, p_76484_4_, j2).func_149688_o().func_76220_a())
                                {
                                    ++k2;
                                }

                                if (p_76484_1_.func_147439_a(i2 + 1, p_76484_4_, j2).func_149688_o().func_76220_a())
                                {
                                    ++k2;
                                }

                                if (p_76484_1_.func_147439_a(i2, p_76484_4_, j2 - 1).func_149688_o().func_76220_a())
                                {
                                    ++k2;
                                }

                                if (p_76484_1_.func_147439_a(i2, p_76484_4_, j2 + 1).func_149688_o().func_76220_a())
                                {
                                    ++k2;
                                }

                                if (k2 == 1)
                                {
                                    p_76484_1_.func_147465_d(i2, p_76484_4_, j2, Blocks.field_150486_ae, 0, 2);
                                    WeightedRandomChestContent[] aweightedrandomchestcontent = WeightedRandomChestContent.func_92080_a(field_111189_a, new WeightedRandomChestContent[] {Items.ENCHANTED_BOOK.func_92114_b(p_76484_2_)});
                                    TileEntityChest tileentitychest = (TileEntityChest)p_76484_1_.func_147438_o(i2, p_76484_4_, j2);

                                    if (tileentitychest != null)
                                    {
                                        WeightedRandomChestContent.func_76293_a(p_76484_2_, aweightedrandomchestcontent, tileentitychest, 8);
                                    }

                                    break label197;
                                }
                            }

                            ++l1;
                            continue;
                        }
                    }

                    ++k1;
                    break;
                }
            }

            p_76484_1_.func_147465_d(p_76484_3_, p_76484_4_, p_76484_5_, Blocks.field_150474_ac, 0, 2);
            TileEntityMobSpawner tileentitymobspawner = (TileEntityMobSpawner)p_76484_1_.func_147438_o(p_76484_3_, p_76484_4_, p_76484_5_);

            if (tileentitymobspawner != null)
            {
                tileentitymobspawner.func_145881_a().func_98272_a(this.func_76543_b(p_76484_2_));
            }
            else
            {
                System.err.println("Failed to fetch mob spawner entity at (" + p_76484_3_ + ", " + p_76484_4_ + ", " + p_76484_5_ + ")");
            }

            return true;
        }
        else
        {
            return false;
        }
    }

    private String func_76543_b(Random p_76543_1_)
    {
        int i = p_76543_1_.nextInt(4);
        return i == 0 ? "Skeleton" : (i == 1 ? "Zombie" : (i == 2 ? "Zombie" : (i == 3 ? "Spider" : "")));
    }
}