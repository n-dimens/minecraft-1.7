package net.minecraft.world.gen.structure;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityMinecartChest;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;

public class StructureMineshaftPieces
{
    public static final WeightedRandomChestContent[] field_78818_a = new WeightedRandomChestContent[] {new WeightedRandomChestContent(Items.IRON_INGOT, 0, 1, 5, 10), new WeightedRandomChestContent(Items.GOLD_INGOT, 0, 1, 3, 5), new WeightedRandomChestContent(Items.REDSTONE, 0, 4, 9, 5), new WeightedRandomChestContent(Items.DYE, 4, 4, 9, 5), new WeightedRandomChestContent(Items.DIAMOND, 0, 1, 2, 3), new WeightedRandomChestContent(Items.COAL, 0, 3, 8, 10), new WeightedRandomChestContent(Items.BREAD, 0, 1, 3, 15), new WeightedRandomChestContent(Items.IRON_PICKAXE, 0, 1, 1, 1), new WeightedRandomChestContent(Item.func_150898_a(Blocks.field_150448_aq), 0, 4, 8, 1), new WeightedRandomChestContent(Items.MELON_SEEDS, 0, 2, 4, 10), new WeightedRandomChestContent(Items.PUMPKIN_SEEDS, 0, 2, 4, 10), new WeightedRandomChestContent(Items.SADDLE, 0, 1, 1, 3), new WeightedRandomChestContent(Items.IRON_HORSE_ARMOR, 0, 1, 1, 1)};
    private static final String __OBFID = "CL_00000444";

    public static void func_143048_a()
    {
        MapGenStructureIO.func_143031_a(StructureMineshaftPieces.Corridor.class, "MSCorridor");
        MapGenStructureIO.func_143031_a(StructureMineshaftPieces.Cross.class, "MSCrossing");
        MapGenStructureIO.func_143031_a(StructureMineshaftPieces.Room.class, "MSRoom");
        MapGenStructureIO.func_143031_a(StructureMineshaftPieces.Stairs.class, "MSStairs");
    }

    private static StructureComponent func_78815_a(List p_78815_0_, Random p_78815_1_, int p_78815_2_, int p_78815_3_, int p_78815_4_, int p_78815_5_, int p_78815_6_)
    {
        int j1 = p_78815_1_.nextInt(100);
        StructureBoundingBox structureboundingbox;

        if (j1 >= 80)
        {
            structureboundingbox = StructureMineshaftPieces.Cross.func_74951_a(p_78815_0_, p_78815_1_, p_78815_2_, p_78815_3_, p_78815_4_, p_78815_5_);

            if (structureboundingbox != null)
            {
                return new StructureMineshaftPieces.Cross(p_78815_6_, p_78815_1_, structureboundingbox, p_78815_5_);
            }
        }
        else if (j1 >= 70)
        {
            structureboundingbox = StructureMineshaftPieces.Stairs.func_74950_a(p_78815_0_, p_78815_1_, p_78815_2_, p_78815_3_, p_78815_4_, p_78815_5_);

            if (structureboundingbox != null)
            {
                return new StructureMineshaftPieces.Stairs(p_78815_6_, p_78815_1_, structureboundingbox, p_78815_5_);
            }
        }
        else
        {
            structureboundingbox = StructureMineshaftPieces.Corridor.func_74954_a(p_78815_0_, p_78815_1_, p_78815_2_, p_78815_3_, p_78815_4_, p_78815_5_);

            if (structureboundingbox != null)
            {
                return new StructureMineshaftPieces.Corridor(p_78815_6_, p_78815_1_, structureboundingbox, p_78815_5_);
            }
        }

        return null;
    }

    private static StructureComponent func_78817_b(StructureComponent p_78817_0_, List p_78817_1_, Random p_78817_2_, int p_78817_3_, int p_78817_4_, int p_78817_5_, int p_78817_6_, int p_78817_7_)
    {
        if (p_78817_7_ > 8)
        {
            return null;
        }
        else if (Math.abs(p_78817_3_ - p_78817_0_.func_74874_b().field_78897_a) <= 80 && Math.abs(p_78817_5_ - p_78817_0_.func_74874_b().field_78896_c) <= 80)
        {
            StructureComponent structurecomponent1 = func_78815_a(p_78817_1_, p_78817_2_, p_78817_3_, p_78817_4_, p_78817_5_, p_78817_6_, p_78817_7_ + 1);

            if (structurecomponent1 != null)
            {
                p_78817_1_.add(structurecomponent1);
                structurecomponent1.func_74861_a(p_78817_0_, p_78817_1_, p_78817_2_);
            }

            return structurecomponent1;
        }
        else
        {
            return null;
        }
    }

    public static class Corridor extends StructureComponent
        {
            private boolean field_74958_a;
            private boolean field_74956_b;
            private boolean field_74957_c;
            private int field_74955_d;
            private static final String __OBFID = "CL_00000445";

            public Corridor() {}

            protected void func_143012_a(NBTTagCompound p_143012_1_)
            {
                p_143012_1_.func_74757_a("hr", this.field_74958_a);
                p_143012_1_.func_74757_a("sc", this.field_74956_b);
                p_143012_1_.func_74757_a("hps", this.field_74957_c);
                p_143012_1_.func_74768_a("Num", this.field_74955_d);
            }

            protected void func_143011_b(NBTTagCompound p_143011_1_)
            {
                this.field_74958_a = p_143011_1_.func_74767_n("hr");
                this.field_74956_b = p_143011_1_.func_74767_n("sc");
                this.field_74957_c = p_143011_1_.func_74767_n("hps");
                this.field_74955_d = p_143011_1_.func_74762_e("Num");
            }

            public Corridor(int p_i2035_1_, Random p_i2035_2_, StructureBoundingBox p_i2035_3_, int p_i2035_4_)
            {
                super(p_i2035_1_);
                this.field_74885_f = p_i2035_4_;
                this.field_74887_e = p_i2035_3_;
                this.field_74958_a = p_i2035_2_.nextInt(3) == 0;
                this.field_74956_b = !this.field_74958_a && p_i2035_2_.nextInt(23) == 0;

                if (this.field_74885_f != 2 && this.field_74885_f != 0)
                {
                    this.field_74955_d = p_i2035_3_.func_78883_b() / 5;
                }
                else
                {
                    this.field_74955_d = p_i2035_3_.func_78880_d() / 5;
                }
            }

            public static StructureBoundingBox func_74954_a(List p_74954_0_, Random p_74954_1_, int p_74954_2_, int p_74954_3_, int p_74954_4_, int p_74954_5_)
            {
                StructureBoundingBox structureboundingbox = new StructureBoundingBox(p_74954_2_, p_74954_3_, p_74954_4_, p_74954_2_, p_74954_3_ + 2, p_74954_4_);
                int i1;

                for (i1 = p_74954_1_.nextInt(3) + 2; i1 > 0; --i1)
                {
                    int j1 = i1 * 5;

                    switch (p_74954_5_)
                    {
                        case 0:
                            structureboundingbox.field_78893_d = p_74954_2_ + 2;
                            structureboundingbox.field_78892_f = p_74954_4_ + (j1 - 1);
                            break;
                        case 1:
                            structureboundingbox.field_78897_a = p_74954_2_ - (j1 - 1);
                            structureboundingbox.field_78892_f = p_74954_4_ + 2;
                            break;
                        case 2:
                            structureboundingbox.field_78893_d = p_74954_2_ + 2;
                            structureboundingbox.field_78896_c = p_74954_4_ - (j1 - 1);
                            break;
                        case 3:
                            structureboundingbox.field_78893_d = p_74954_2_ + (j1 - 1);
                            structureboundingbox.field_78892_f = p_74954_4_ + 2;
                    }

                    if (StructureComponent.func_74883_a(p_74954_0_, structureboundingbox) == null)
                    {
                        break;
                    }
                }

                return i1 > 0 ? structureboundingbox : null;
            }

            public void func_74861_a(StructureComponent p_74861_1_, List p_74861_2_, Random p_74861_3_)
            {
                int i = this.func_74877_c();
                int j = p_74861_3_.nextInt(4);

                switch (this.field_74885_f)
                {
                    case 0:
                        if (j <= 1)
                        {
                            StructureMineshaftPieces.func_78817_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78897_a, this.field_74887_e.field_78895_b - 1 + p_74861_3_.nextInt(3), this.field_74887_e.field_78892_f + 1, this.field_74885_f, i);
                        }
                        else if (j == 2)
                        {
                            StructureMineshaftPieces.func_78817_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78897_a - 1, this.field_74887_e.field_78895_b - 1 + p_74861_3_.nextInt(3), this.field_74887_e.field_78892_f - 3, 1, i);
                        }
                        else
                        {
                            StructureMineshaftPieces.func_78817_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78893_d + 1, this.field_74887_e.field_78895_b - 1 + p_74861_3_.nextInt(3), this.field_74887_e.field_78892_f - 3, 3, i);
                        }

                        break;
                    case 1:
                        if (j <= 1)
                        {
                            StructureMineshaftPieces.func_78817_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78897_a - 1, this.field_74887_e.field_78895_b - 1 + p_74861_3_.nextInt(3), this.field_74887_e.field_78896_c, this.field_74885_f, i);
                        }
                        else if (j == 2)
                        {
                            StructureMineshaftPieces.func_78817_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78897_a, this.field_74887_e.field_78895_b - 1 + p_74861_3_.nextInt(3), this.field_74887_e.field_78896_c - 1, 2, i);
                        }
                        else
                        {
                            StructureMineshaftPieces.func_78817_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78897_a, this.field_74887_e.field_78895_b - 1 + p_74861_3_.nextInt(3), this.field_74887_e.field_78892_f + 1, 0, i);
                        }

                        break;
                    case 2:
                        if (j <= 1)
                        {
                            StructureMineshaftPieces.func_78817_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78897_a, this.field_74887_e.field_78895_b - 1 + p_74861_3_.nextInt(3), this.field_74887_e.field_78896_c - 1, this.field_74885_f, i);
                        }
                        else if (j == 2)
                        {
                            StructureMineshaftPieces.func_78817_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78897_a - 1, this.field_74887_e.field_78895_b - 1 + p_74861_3_.nextInt(3), this.field_74887_e.field_78896_c, 1, i);
                        }
                        else
                        {
                            StructureMineshaftPieces.func_78817_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78893_d + 1, this.field_74887_e.field_78895_b - 1 + p_74861_3_.nextInt(3), this.field_74887_e.field_78896_c, 3, i);
                        }

                        break;
                    case 3:
                        if (j <= 1)
                        {
                            StructureMineshaftPieces.func_78817_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78893_d + 1, this.field_74887_e.field_78895_b - 1 + p_74861_3_.nextInt(3), this.field_74887_e.field_78896_c, this.field_74885_f, i);
                        }
                        else if (j == 2)
                        {
                            StructureMineshaftPieces.func_78817_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78893_d - 3, this.field_74887_e.field_78895_b - 1 + p_74861_3_.nextInt(3), this.field_74887_e.field_78896_c - 1, 2, i);
                        }
                        else
                        {
                            StructureMineshaftPieces.func_78817_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78893_d - 3, this.field_74887_e.field_78895_b - 1 + p_74861_3_.nextInt(3), this.field_74887_e.field_78892_f + 1, 0, i);
                        }
                }

                if (i < 8)
                {
                    int k;
                    int l;

                    if (this.field_74885_f != 2 && this.field_74885_f != 0)
                    {
                        for (k = this.field_74887_e.field_78897_a + 3; k + 3 <= this.field_74887_e.field_78893_d; k += 5)
                        {
                            l = p_74861_3_.nextInt(5);

                            if (l == 0)
                            {
                                StructureMineshaftPieces.func_78817_b(p_74861_1_, p_74861_2_, p_74861_3_, k, this.field_74887_e.field_78895_b, this.field_74887_e.field_78896_c - 1, 2, i + 1);
                            }
                            else if (l == 1)
                            {
                                StructureMineshaftPieces.func_78817_b(p_74861_1_, p_74861_2_, p_74861_3_, k, this.field_74887_e.field_78895_b, this.field_74887_e.field_78892_f + 1, 0, i + 1);
                            }
                        }
                    }
                    else
                    {
                        for (k = this.field_74887_e.field_78896_c + 3; k + 3 <= this.field_74887_e.field_78892_f; k += 5)
                        {
                            l = p_74861_3_.nextInt(5);

                            if (l == 0)
                            {
                                StructureMineshaftPieces.func_78817_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78897_a - 1, this.field_74887_e.field_78895_b, k, 1, i + 1);
                            }
                            else if (l == 1)
                            {
                                StructureMineshaftPieces.func_78817_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78893_d + 1, this.field_74887_e.field_78895_b, k, 3, i + 1);
                            }
                        }
                    }
                }
            }

            protected boolean func_74879_a(World p_74879_1_, StructureBoundingBox p_74879_2_, Random p_74879_3_, int p_74879_4_, int p_74879_5_, int p_74879_6_, WeightedRandomChestContent[] p_74879_7_, int p_74879_8_)
            {
                int i1 = this.func_74865_a(p_74879_4_, p_74879_6_);
                int j1 = this.func_74862_a(p_74879_5_);
                int k1 = this.func_74873_b(p_74879_4_, p_74879_6_);

                if (p_74879_2_.func_78890_b(i1, j1, k1) && p_74879_1_.func_147439_a(i1, j1, k1).func_149688_o() == Material.field_151579_a)
                {
                    int l1 = p_74879_3_.nextBoolean() ? 1 : 0;
                    p_74879_1_.func_147465_d(i1, j1, k1, Blocks.field_150448_aq, this.func_151555_a(Blocks.field_150448_aq, l1), 2);
                    EntityMinecartChest entityminecartchest = new EntityMinecartChest(p_74879_1_, (double)((float)i1 + 0.5F), (double)((float)j1 + 0.5F), (double)((float)k1 + 0.5F));
                    WeightedRandomChestContent.func_76293_a(p_74879_3_, p_74879_7_, entityminecartchest, p_74879_8_);
                    p_74879_1_.func_72838_d(entityminecartchest);
                    return true;
                }
                else
                {
                    return false;
                }
            }

            public boolean func_74875_a(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_)
            {
                if (this.func_74860_a(p_74875_1_, p_74875_3_))
                {
                    return false;
                }
                else
                {
                    boolean flag = false;
                    boolean flag1 = true;
                    boolean flag2 = false;
                    boolean flag3 = true;
                    int i = this.field_74955_d * 5 - 1;
                    this.func_151549_a(p_74875_1_, p_74875_3_, 0, 0, 0, 2, 1, i, Blocks.field_150350_a, Blocks.field_150350_a, false);
                    this.func_151551_a(p_74875_1_, p_74875_3_, p_74875_2_, 0.8F, 0, 2, 0, 2, 2, i, Blocks.field_150350_a, Blocks.field_150350_a, false);

                    if (this.field_74956_b)
                    {
                        this.func_151551_a(p_74875_1_, p_74875_3_, p_74875_2_, 0.6F, 0, 0, 0, 2, 1, i, Blocks.field_150321_G, Blocks.field_150350_a, false);
                    }

                    int j;
                    int k;

                    for (j = 0; j < this.field_74955_d; ++j)
                    {
                        k = 2 + j * 5;
                        this.func_151549_a(p_74875_1_, p_74875_3_, 0, 0, k, 0, 1, k, Blocks.field_150422_aJ, Blocks.field_150350_a, false);
                        this.func_151549_a(p_74875_1_, p_74875_3_, 2, 0, k, 2, 1, k, Blocks.field_150422_aJ, Blocks.field_150350_a, false);

                        if (p_74875_2_.nextInt(4) == 0)
                        {
                            this.func_151549_a(p_74875_1_, p_74875_3_, 0, 2, k, 0, 2, k, Blocks.field_150344_f, Blocks.field_150350_a, false);
                            this.func_151549_a(p_74875_1_, p_74875_3_, 2, 2, k, 2, 2, k, Blocks.field_150344_f, Blocks.field_150350_a, false);
                        }
                        else
                        {
                            this.func_151549_a(p_74875_1_, p_74875_3_, 0, 2, k, 2, 2, k, Blocks.field_150344_f, Blocks.field_150350_a, false);
                        }

                        this.func_151552_a(p_74875_1_, p_74875_3_, p_74875_2_, 0.1F, 0, 2, k - 1, Blocks.field_150321_G, 0);
                        this.func_151552_a(p_74875_1_, p_74875_3_, p_74875_2_, 0.1F, 2, 2, k - 1, Blocks.field_150321_G, 0);
                        this.func_151552_a(p_74875_1_, p_74875_3_, p_74875_2_, 0.1F, 0, 2, k + 1, Blocks.field_150321_G, 0);
                        this.func_151552_a(p_74875_1_, p_74875_3_, p_74875_2_, 0.1F, 2, 2, k + 1, Blocks.field_150321_G, 0);
                        this.func_151552_a(p_74875_1_, p_74875_3_, p_74875_2_, 0.05F, 0, 2, k - 2, Blocks.field_150321_G, 0);
                        this.func_151552_a(p_74875_1_, p_74875_3_, p_74875_2_, 0.05F, 2, 2, k - 2, Blocks.field_150321_G, 0);
                        this.func_151552_a(p_74875_1_, p_74875_3_, p_74875_2_, 0.05F, 0, 2, k + 2, Blocks.field_150321_G, 0);
                        this.func_151552_a(p_74875_1_, p_74875_3_, p_74875_2_, 0.05F, 2, 2, k + 2, Blocks.field_150321_G, 0);
                        this.func_151552_a(p_74875_1_, p_74875_3_, p_74875_2_, 0.05F, 1, 2, k - 1, Blocks.field_150478_aa, 0);
                        this.func_151552_a(p_74875_1_, p_74875_3_, p_74875_2_, 0.05F, 1, 2, k + 1, Blocks.field_150478_aa, 0);

                        if (p_74875_2_.nextInt(100) == 0)
                        {
                            this.func_74879_a(p_74875_1_, p_74875_3_, p_74875_2_, 2, 0, k - 1, WeightedRandomChestContent.func_92080_a(StructureMineshaftPieces.field_78818_a, new WeightedRandomChestContent[] {Items.ENCHANTED_BOOK.func_92114_b(p_74875_2_)}), 3 + p_74875_2_.nextInt(4));
                        }

                        if (p_74875_2_.nextInt(100) == 0)
                        {
                            this.func_74879_a(p_74875_1_, p_74875_3_, p_74875_2_, 0, 0, k + 1, WeightedRandomChestContent.func_92080_a(StructureMineshaftPieces.field_78818_a, new WeightedRandomChestContent[] {Items.ENCHANTED_BOOK.func_92114_b(p_74875_2_)}), 3 + p_74875_2_.nextInt(4));
                        }

                        if (this.field_74956_b && !this.field_74957_c)
                        {
                            int l = this.func_74862_a(0);
                            int i1 = k - 1 + p_74875_2_.nextInt(3);
                            int j1 = this.func_74865_a(1, i1);
                            i1 = this.func_74873_b(1, i1);

                            if (p_74875_3_.func_78890_b(j1, l, i1))
                            {
                                this.field_74957_c = true;
                                p_74875_1_.func_147465_d(j1, l, i1, Blocks.field_150474_ac, 0, 2);
                                TileEntityMobSpawner tileentitymobspawner = (TileEntityMobSpawner)p_74875_1_.func_147438_o(j1, l, i1);

                                if (tileentitymobspawner != null)
                                {
                                    tileentitymobspawner.func_145881_a().func_98272_a("CaveSpider");
                                }
                            }
                        }
                    }

                    for (j = 0; j <= 2; ++j)
                    {
                        for (k = 0; k <= i; ++k)
                        {
                            byte b0 = -1;
                            Block block1 = this.func_151548_a(p_74875_1_, j, b0, k, p_74875_3_);

                            if (block1.func_149688_o() == Material.field_151579_a)
                            {
                                byte b1 = -1;
                                this.func_151550_a(p_74875_1_, Blocks.field_150344_f, 0, j, b1, k, p_74875_3_);
                            }
                        }
                    }

                    if (this.field_74958_a)
                    {
                        for (j = 0; j <= i; ++j)
                        {
                            Block block = this.func_151548_a(p_74875_1_, 1, -1, j, p_74875_3_);

                            if (block.func_149688_o() != Material.field_151579_a && block.func_149730_j())
                            {
                                this.func_151552_a(p_74875_1_, p_74875_3_, p_74875_2_, 0.7F, 1, 0, j, Blocks.field_150448_aq, this.func_151555_a(Blocks.field_150448_aq, 0));
                            }
                        }
                    }

                    return true;
                }
            }
        }

    public static class Cross extends StructureComponent
        {
            private int field_74953_a;
            private boolean field_74952_b;
            private static final String __OBFID = "CL_00000446";

            public Cross() {}

            protected void func_143012_a(NBTTagCompound p_143012_1_)
            {
                p_143012_1_.func_74757_a("tf", this.field_74952_b);
                p_143012_1_.func_74768_a("D", this.field_74953_a);
            }

            protected void func_143011_b(NBTTagCompound p_143011_1_)
            {
                this.field_74952_b = p_143011_1_.func_74767_n("tf");
                this.field_74953_a = p_143011_1_.func_74762_e("D");
            }

            public Cross(int p_i2036_1_, Random p_i2036_2_, StructureBoundingBox p_i2036_3_, int p_i2036_4_)
            {
                super(p_i2036_1_);
                this.field_74953_a = p_i2036_4_;
                this.field_74887_e = p_i2036_3_;
                this.field_74952_b = p_i2036_3_.func_78882_c() > 3;
            }

            public static StructureBoundingBox func_74951_a(List p_74951_0_, Random p_74951_1_, int p_74951_2_, int p_74951_3_, int p_74951_4_, int p_74951_5_)
            {
                StructureBoundingBox structureboundingbox = new StructureBoundingBox(p_74951_2_, p_74951_3_, p_74951_4_, p_74951_2_, p_74951_3_ + 2, p_74951_4_);

                if (p_74951_1_.nextInt(4) == 0)
                {
                    structureboundingbox.field_78894_e += 4;
                }

                switch (p_74951_5_)
                {
                    case 0:
                        structureboundingbox.field_78897_a = p_74951_2_ - 1;
                        structureboundingbox.field_78893_d = p_74951_2_ + 3;
                        structureboundingbox.field_78892_f = p_74951_4_ + 4;
                        break;
                    case 1:
                        structureboundingbox.field_78897_a = p_74951_2_ - 4;
                        structureboundingbox.field_78896_c = p_74951_4_ - 1;
                        structureboundingbox.field_78892_f = p_74951_4_ + 3;
                        break;
                    case 2:
                        structureboundingbox.field_78897_a = p_74951_2_ - 1;
                        structureboundingbox.field_78893_d = p_74951_2_ + 3;
                        structureboundingbox.field_78896_c = p_74951_4_ - 4;
                        break;
                    case 3:
                        structureboundingbox.field_78893_d = p_74951_2_ + 4;
                        structureboundingbox.field_78896_c = p_74951_4_ - 1;
                        structureboundingbox.field_78892_f = p_74951_4_ + 3;
                }

                return StructureComponent.func_74883_a(p_74951_0_, structureboundingbox) != null ? null : structureboundingbox;
            }

            public void func_74861_a(StructureComponent p_74861_1_, List p_74861_2_, Random p_74861_3_)
            {
                int i = this.func_74877_c();

                switch (this.field_74953_a)
                {
                    case 0:
                        StructureMineshaftPieces.func_78817_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78897_a + 1, this.field_74887_e.field_78895_b, this.field_74887_e.field_78892_f + 1, 0, i);
                        StructureMineshaftPieces.func_78817_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78897_a - 1, this.field_74887_e.field_78895_b, this.field_74887_e.field_78896_c + 1, 1, i);
                        StructureMineshaftPieces.func_78817_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78893_d + 1, this.field_74887_e.field_78895_b, this.field_74887_e.field_78896_c + 1, 3, i);
                        break;
                    case 1:
                        StructureMineshaftPieces.func_78817_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78897_a + 1, this.field_74887_e.field_78895_b, this.field_74887_e.field_78896_c - 1, 2, i);
                        StructureMineshaftPieces.func_78817_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78897_a + 1, this.field_74887_e.field_78895_b, this.field_74887_e.field_78892_f + 1, 0, i);
                        StructureMineshaftPieces.func_78817_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78897_a - 1, this.field_74887_e.field_78895_b, this.field_74887_e.field_78896_c + 1, 1, i);
                        break;
                    case 2:
                        StructureMineshaftPieces.func_78817_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78897_a + 1, this.field_74887_e.field_78895_b, this.field_74887_e.field_78896_c - 1, 2, i);
                        StructureMineshaftPieces.func_78817_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78897_a - 1, this.field_74887_e.field_78895_b, this.field_74887_e.field_78896_c + 1, 1, i);
                        StructureMineshaftPieces.func_78817_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78893_d + 1, this.field_74887_e.field_78895_b, this.field_74887_e.field_78896_c + 1, 3, i);
                        break;
                    case 3:
                        StructureMineshaftPieces.func_78817_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78897_a + 1, this.field_74887_e.field_78895_b, this.field_74887_e.field_78896_c - 1, 2, i);
                        StructureMineshaftPieces.func_78817_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78897_a + 1, this.field_74887_e.field_78895_b, this.field_74887_e.field_78892_f + 1, 0, i);
                        StructureMineshaftPieces.func_78817_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78893_d + 1, this.field_74887_e.field_78895_b, this.field_74887_e.field_78896_c + 1, 3, i);
                }

                if (this.field_74952_b)
                {
                    if (p_74861_3_.nextBoolean())
                    {
                        StructureMineshaftPieces.func_78817_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78897_a + 1, this.field_74887_e.field_78895_b + 3 + 1, this.field_74887_e.field_78896_c - 1, 2, i);
                    }

                    if (p_74861_3_.nextBoolean())
                    {
                        StructureMineshaftPieces.func_78817_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78897_a - 1, this.field_74887_e.field_78895_b + 3 + 1, this.field_74887_e.field_78896_c + 1, 1, i);
                    }

                    if (p_74861_3_.nextBoolean())
                    {
                        StructureMineshaftPieces.func_78817_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78893_d + 1, this.field_74887_e.field_78895_b + 3 + 1, this.field_74887_e.field_78896_c + 1, 3, i);
                    }

                    if (p_74861_3_.nextBoolean())
                    {
                        StructureMineshaftPieces.func_78817_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78897_a + 1, this.field_74887_e.field_78895_b + 3 + 1, this.field_74887_e.field_78892_f + 1, 0, i);
                    }
                }
            }

            public boolean func_74875_a(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_)
            {
                if (this.func_74860_a(p_74875_1_, p_74875_3_))
                {
                    return false;
                }
                else
                {
                    if (this.field_74952_b)
                    {
                        this.func_151549_a(p_74875_1_, p_74875_3_, this.field_74887_e.field_78897_a + 1, this.field_74887_e.field_78895_b, this.field_74887_e.field_78896_c, this.field_74887_e.field_78893_d - 1, this.field_74887_e.field_78895_b + 3 - 1, this.field_74887_e.field_78892_f, Blocks.field_150350_a, Blocks.field_150350_a, false);
                        this.func_151549_a(p_74875_1_, p_74875_3_, this.field_74887_e.field_78897_a, this.field_74887_e.field_78895_b, this.field_74887_e.field_78896_c + 1, this.field_74887_e.field_78893_d, this.field_74887_e.field_78895_b + 3 - 1, this.field_74887_e.field_78892_f - 1, Blocks.field_150350_a, Blocks.field_150350_a, false);
                        this.func_151549_a(p_74875_1_, p_74875_3_, this.field_74887_e.field_78897_a + 1, this.field_74887_e.field_78894_e - 2, this.field_74887_e.field_78896_c, this.field_74887_e.field_78893_d - 1, this.field_74887_e.field_78894_e, this.field_74887_e.field_78892_f, Blocks.field_150350_a, Blocks.field_150350_a, false);
                        this.func_151549_a(p_74875_1_, p_74875_3_, this.field_74887_e.field_78897_a, this.field_74887_e.field_78894_e - 2, this.field_74887_e.field_78896_c + 1, this.field_74887_e.field_78893_d, this.field_74887_e.field_78894_e, this.field_74887_e.field_78892_f - 1, Blocks.field_150350_a, Blocks.field_150350_a, false);
                        this.func_151549_a(p_74875_1_, p_74875_3_, this.field_74887_e.field_78897_a + 1, this.field_74887_e.field_78895_b + 3, this.field_74887_e.field_78896_c + 1, this.field_74887_e.field_78893_d - 1, this.field_74887_e.field_78895_b + 3, this.field_74887_e.field_78892_f - 1, Blocks.field_150350_a, Blocks.field_150350_a, false);
                    }
                    else
                    {
                        this.func_151549_a(p_74875_1_, p_74875_3_, this.field_74887_e.field_78897_a + 1, this.field_74887_e.field_78895_b, this.field_74887_e.field_78896_c, this.field_74887_e.field_78893_d - 1, this.field_74887_e.field_78894_e, this.field_74887_e.field_78892_f, Blocks.field_150350_a, Blocks.field_150350_a, false);
                        this.func_151549_a(p_74875_1_, p_74875_3_, this.field_74887_e.field_78897_a, this.field_74887_e.field_78895_b, this.field_74887_e.field_78896_c + 1, this.field_74887_e.field_78893_d, this.field_74887_e.field_78894_e, this.field_74887_e.field_78892_f - 1, Blocks.field_150350_a, Blocks.field_150350_a, false);
                    }

                    this.func_151549_a(p_74875_1_, p_74875_3_, this.field_74887_e.field_78897_a + 1, this.field_74887_e.field_78895_b, this.field_74887_e.field_78896_c + 1, this.field_74887_e.field_78897_a + 1, this.field_74887_e.field_78894_e, this.field_74887_e.field_78896_c + 1, Blocks.field_150344_f, Blocks.field_150350_a, false);
                    this.func_151549_a(p_74875_1_, p_74875_3_, this.field_74887_e.field_78897_a + 1, this.field_74887_e.field_78895_b, this.field_74887_e.field_78892_f - 1, this.field_74887_e.field_78897_a + 1, this.field_74887_e.field_78894_e, this.field_74887_e.field_78892_f - 1, Blocks.field_150344_f, Blocks.field_150350_a, false);
                    this.func_151549_a(p_74875_1_, p_74875_3_, this.field_74887_e.field_78893_d - 1, this.field_74887_e.field_78895_b, this.field_74887_e.field_78896_c + 1, this.field_74887_e.field_78893_d - 1, this.field_74887_e.field_78894_e, this.field_74887_e.field_78896_c + 1, Blocks.field_150344_f, Blocks.field_150350_a, false);
                    this.func_151549_a(p_74875_1_, p_74875_3_, this.field_74887_e.field_78893_d - 1, this.field_74887_e.field_78895_b, this.field_74887_e.field_78892_f - 1, this.field_74887_e.field_78893_d - 1, this.field_74887_e.field_78894_e, this.field_74887_e.field_78892_f - 1, Blocks.field_150344_f, Blocks.field_150350_a, false);

                    for (int i = this.field_74887_e.field_78897_a; i <= this.field_74887_e.field_78893_d; ++i)
                    {
                        for (int j = this.field_74887_e.field_78896_c; j <= this.field_74887_e.field_78892_f; ++j)
                        {
                            if (this.func_151548_a(p_74875_1_, i, this.field_74887_e.field_78895_b - 1, j, p_74875_3_).func_149688_o() == Material.field_151579_a)
                            {
                                this.func_151550_a(p_74875_1_, Blocks.field_150344_f, 0, i, this.field_74887_e.field_78895_b - 1, j, p_74875_3_);
                            }
                        }
                    }

                    return true;
                }
            }
        }

    public static class Room extends StructureComponent
        {
            private List field_74949_a = new LinkedList();
            private static final String __OBFID = "CL_00000447";

            public Room() {}

            public Room(int p_i2037_1_, Random p_i2037_2_, int p_i2037_3_, int p_i2037_4_)
            {
                super(p_i2037_1_);
                this.field_74887_e = new StructureBoundingBox(p_i2037_3_, 50, p_i2037_4_, p_i2037_3_ + 7 + p_i2037_2_.nextInt(6), 54 + p_i2037_2_.nextInt(6), p_i2037_4_ + 7 + p_i2037_2_.nextInt(6));
            }

            public void func_74861_a(StructureComponent p_74861_1_, List p_74861_2_, Random p_74861_3_)
            {
                int i = this.func_74877_c();
                int k = this.field_74887_e.func_78882_c() - 3 - 1;

                if (k <= 0)
                {
                    k = 1;
                }

                int j;
                StructureComponent structurecomponent1;
                StructureBoundingBox structureboundingbox;

                for (j = 0; j < this.field_74887_e.func_78883_b(); j += 4)
                {
                    j += p_74861_3_.nextInt(this.field_74887_e.func_78883_b());

                    if (j + 3 > this.field_74887_e.func_78883_b())
                    {
                        break;
                    }

                    structurecomponent1 = StructureMineshaftPieces.func_78817_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78897_a + j, this.field_74887_e.field_78895_b + p_74861_3_.nextInt(k) + 1, this.field_74887_e.field_78896_c - 1, 2, i);

                    if (structurecomponent1 != null)
                    {
                        structureboundingbox = structurecomponent1.func_74874_b();
                        this.field_74949_a.add(new StructureBoundingBox(structureboundingbox.field_78897_a, structureboundingbox.field_78895_b, this.field_74887_e.field_78896_c, structureboundingbox.field_78893_d, structureboundingbox.field_78894_e, this.field_74887_e.field_78896_c + 1));
                    }
                }

                for (j = 0; j < this.field_74887_e.func_78883_b(); j += 4)
                {
                    j += p_74861_3_.nextInt(this.field_74887_e.func_78883_b());

                    if (j + 3 > this.field_74887_e.func_78883_b())
                    {
                        break;
                    }

                    structurecomponent1 = StructureMineshaftPieces.func_78817_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78897_a + j, this.field_74887_e.field_78895_b + p_74861_3_.nextInt(k) + 1, this.field_74887_e.field_78892_f + 1, 0, i);

                    if (structurecomponent1 != null)
                    {
                        structureboundingbox = structurecomponent1.func_74874_b();
                        this.field_74949_a.add(new StructureBoundingBox(structureboundingbox.field_78897_a, structureboundingbox.field_78895_b, this.field_74887_e.field_78892_f - 1, structureboundingbox.field_78893_d, structureboundingbox.field_78894_e, this.field_74887_e.field_78892_f));
                    }
                }

                for (j = 0; j < this.field_74887_e.func_78880_d(); j += 4)
                {
                    j += p_74861_3_.nextInt(this.field_74887_e.func_78880_d());

                    if (j + 3 > this.field_74887_e.func_78880_d())
                    {
                        break;
                    }

                    structurecomponent1 = StructureMineshaftPieces.func_78817_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78897_a - 1, this.field_74887_e.field_78895_b + p_74861_3_.nextInt(k) + 1, this.field_74887_e.field_78896_c + j, 1, i);

                    if (structurecomponent1 != null)
                    {
                        structureboundingbox = structurecomponent1.func_74874_b();
                        this.field_74949_a.add(new StructureBoundingBox(this.field_74887_e.field_78897_a, structureboundingbox.field_78895_b, structureboundingbox.field_78896_c, this.field_74887_e.field_78897_a + 1, structureboundingbox.field_78894_e, structureboundingbox.field_78892_f));
                    }
                }

                for (j = 0; j < this.field_74887_e.func_78880_d(); j += 4)
                {
                    j += p_74861_3_.nextInt(this.field_74887_e.func_78880_d());

                    if (j + 3 > this.field_74887_e.func_78880_d())
                    {
                        break;
                    }

                    structurecomponent1 = StructureMineshaftPieces.func_78817_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78893_d + 1, this.field_74887_e.field_78895_b + p_74861_3_.nextInt(k) + 1, this.field_74887_e.field_78896_c + j, 3, i);

                    if (structurecomponent1 != null)
                    {
                        structureboundingbox = structurecomponent1.func_74874_b();
                        this.field_74949_a.add(new StructureBoundingBox(this.field_74887_e.field_78893_d - 1, structureboundingbox.field_78895_b, structureboundingbox.field_78896_c, this.field_74887_e.field_78893_d, structureboundingbox.field_78894_e, structureboundingbox.field_78892_f));
                    }
                }
            }

            public boolean func_74875_a(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_)
            {
                if (this.func_74860_a(p_74875_1_, p_74875_3_))
                {
                    return false;
                }
                else
                {
                    this.func_151549_a(p_74875_1_, p_74875_3_, this.field_74887_e.field_78897_a, this.field_74887_e.field_78895_b, this.field_74887_e.field_78896_c, this.field_74887_e.field_78893_d, this.field_74887_e.field_78895_b, this.field_74887_e.field_78892_f, Blocks.field_150346_d, Blocks.field_150350_a, true);
                    this.func_151549_a(p_74875_1_, p_74875_3_, this.field_74887_e.field_78897_a, this.field_74887_e.field_78895_b + 1, this.field_74887_e.field_78896_c, this.field_74887_e.field_78893_d, Math.min(this.field_74887_e.field_78895_b + 3, this.field_74887_e.field_78894_e), this.field_74887_e.field_78892_f, Blocks.field_150350_a, Blocks.field_150350_a, false);
                    Iterator iterator = this.field_74949_a.iterator();

                    while (iterator.hasNext())
                    {
                        StructureBoundingBox structureboundingbox1 = (StructureBoundingBox)iterator.next();
                        this.func_151549_a(p_74875_1_, p_74875_3_, structureboundingbox1.field_78897_a, structureboundingbox1.field_78894_e - 2, structureboundingbox1.field_78896_c, structureboundingbox1.field_78893_d, structureboundingbox1.field_78894_e, structureboundingbox1.field_78892_f, Blocks.field_150350_a, Blocks.field_150350_a, false);
                    }

                    this.func_151547_a(p_74875_1_, p_74875_3_, this.field_74887_e.field_78897_a, this.field_74887_e.field_78895_b + 4, this.field_74887_e.field_78896_c, this.field_74887_e.field_78893_d, this.field_74887_e.field_78894_e, this.field_74887_e.field_78892_f, Blocks.field_150350_a, false);
                    return true;
                }
            }

            protected void func_143012_a(NBTTagCompound p_143012_1_)
            {
                NBTTagList nbttaglist = new NBTTagList();
                Iterator iterator = this.field_74949_a.iterator();

                while (iterator.hasNext())
                {
                    StructureBoundingBox structureboundingbox = (StructureBoundingBox)iterator.next();
                    nbttaglist.func_74742_a(structureboundingbox.func_151535_h());
                }

                p_143012_1_.func_74782_a("Entrances", nbttaglist);
            }

            protected void func_143011_b(NBTTagCompound p_143011_1_)
            {
                NBTTagList nbttaglist = p_143011_1_.func_150295_c("Entrances", 11);

                for (int i = 0; i < nbttaglist.func_74745_c(); ++i)
                {
                    this.field_74949_a.add(new StructureBoundingBox(nbttaglist.func_150306_c(i)));
                }
            }
        }

    public static class Stairs extends StructureComponent
        {
            private static final String __OBFID = "CL_00000449";

            public Stairs() {}

            public Stairs(int p_i2038_1_, Random p_i2038_2_, StructureBoundingBox p_i2038_3_, int p_i2038_4_)
            {
                super(p_i2038_1_);
                this.field_74885_f = p_i2038_4_;
                this.field_74887_e = p_i2038_3_;
            }

            protected void func_143012_a(NBTTagCompound p_143012_1_) {}

            protected void func_143011_b(NBTTagCompound p_143011_1_) {}

            public static StructureBoundingBox func_74950_a(List p_74950_0_, Random p_74950_1_, int p_74950_2_, int p_74950_3_, int p_74950_4_, int p_74950_5_)
            {
                StructureBoundingBox structureboundingbox = new StructureBoundingBox(p_74950_2_, p_74950_3_ - 5, p_74950_4_, p_74950_2_, p_74950_3_ + 2, p_74950_4_);

                switch (p_74950_5_)
                {
                    case 0:
                        structureboundingbox.field_78893_d = p_74950_2_ + 2;
                        structureboundingbox.field_78892_f = p_74950_4_ + 8;
                        break;
                    case 1:
                        structureboundingbox.field_78897_a = p_74950_2_ - 8;
                        structureboundingbox.field_78892_f = p_74950_4_ + 2;
                        break;
                    case 2:
                        structureboundingbox.field_78893_d = p_74950_2_ + 2;
                        structureboundingbox.field_78896_c = p_74950_4_ - 8;
                        break;
                    case 3:
                        structureboundingbox.field_78893_d = p_74950_2_ + 8;
                        structureboundingbox.field_78892_f = p_74950_4_ + 2;
                }

                return StructureComponent.func_74883_a(p_74950_0_, structureboundingbox) != null ? null : structureboundingbox;
            }

            public void func_74861_a(StructureComponent p_74861_1_, List p_74861_2_, Random p_74861_3_)
            {
                int i = this.func_74877_c();

                switch (this.field_74885_f)
                {
                    case 0:
                        StructureMineshaftPieces.func_78817_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78897_a, this.field_74887_e.field_78895_b, this.field_74887_e.field_78892_f + 1, 0, i);
                        break;
                    case 1:
                        StructureMineshaftPieces.func_78817_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78897_a - 1, this.field_74887_e.field_78895_b, this.field_74887_e.field_78896_c, 1, i);
                        break;
                    case 2:
                        StructureMineshaftPieces.func_78817_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78897_a, this.field_74887_e.field_78895_b, this.field_74887_e.field_78896_c - 1, 2, i);
                        break;
                    case 3:
                        StructureMineshaftPieces.func_78817_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78893_d + 1, this.field_74887_e.field_78895_b, this.field_74887_e.field_78896_c, 3, i);
                }
            }

            public boolean func_74875_a(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_)
            {
                if (this.func_74860_a(p_74875_1_, p_74875_3_))
                {
                    return false;
                }
                else
                {
                    this.func_151549_a(p_74875_1_, p_74875_3_, 0, 5, 0, 2, 7, 1, Blocks.field_150350_a, Blocks.field_150350_a, false);
                    this.func_151549_a(p_74875_1_, p_74875_3_, 0, 0, 7, 2, 2, 8, Blocks.field_150350_a, Blocks.field_150350_a, false);

                    for (int i = 0; i < 5; ++i)
                    {
                        this.func_151549_a(p_74875_1_, p_74875_3_, 0, 5 - i - (i < 4 ? 1 : 0), 2 + i, 2, 7 - i, 2 + i, Blocks.field_150350_a, Blocks.field_150350_a, false);
                    }

                    return true;
                }
            }
        }
}