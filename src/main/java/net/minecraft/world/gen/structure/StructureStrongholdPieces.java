package net.minecraft.world.gen.structure;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;

public class StructureStrongholdPieces
{
    private static final StructureStrongholdPieces.PieceWeight[] field_75205_b = new StructureStrongholdPieces.PieceWeight[] {new StructureStrongholdPieces.PieceWeight(StructureStrongholdPieces.Straight.class, 40, 0), new StructureStrongholdPieces.PieceWeight(StructureStrongholdPieces.Prison.class, 5, 5), new StructureStrongholdPieces.PieceWeight(StructureStrongholdPieces.LeftTurn.class, 20, 0), new StructureStrongholdPieces.PieceWeight(StructureStrongholdPieces.RightTurn.class, 20, 0), new StructureStrongholdPieces.PieceWeight(StructureStrongholdPieces.RoomCrossing.class, 10, 6), new StructureStrongholdPieces.PieceWeight(StructureStrongholdPieces.StairsStraight.class, 5, 5), new StructureStrongholdPieces.PieceWeight(StructureStrongholdPieces.Stairs.class, 5, 5), new StructureStrongholdPieces.PieceWeight(StructureStrongholdPieces.Crossing.class, 5, 4), new StructureStrongholdPieces.PieceWeight(StructureStrongholdPieces.ChestCorridor.class, 5, 4), new StructureStrongholdPieces.PieceWeight(StructureStrongholdPieces.Library.class, 10, 2)
    {
        private static final String __OBFID = "CL_00000484";
        public boolean func_75189_a(int p_75189_1_)
        {
            return super.func_75189_a(p_75189_1_) && p_75189_1_ > 4;
        }
    }, new StructureStrongholdPieces.PieceWeight(StructureStrongholdPieces.PortalRoom.class, 20, 1)
    {
        private static final String __OBFID = "CL_00000485";
        public boolean func_75189_a(int p_75189_1_)
        {
            return super.func_75189_a(p_75189_1_) && p_75189_1_ > 5;
        }
    }
                                                                                                                             };
    private static List field_75206_c;
    private static Class field_75203_d;
    static int field_75207_a;
    private static final StructureStrongholdPieces.Stones field_75204_e = new StructureStrongholdPieces.Stones(null);
    private static final String __OBFID = "CL_00000483";

    public static void func_143046_a()
    {
        MapGenStructureIO.func_143031_a(StructureStrongholdPieces.ChestCorridor.class, "SHCC");
        MapGenStructureIO.func_143031_a(StructureStrongholdPieces.Corridor.class, "SHFC");
        MapGenStructureIO.func_143031_a(StructureStrongholdPieces.Crossing.class, "SH5C");
        MapGenStructureIO.func_143031_a(StructureStrongholdPieces.LeftTurn.class, "SHLT");
        MapGenStructureIO.func_143031_a(StructureStrongholdPieces.Library.class, "SHLi");
        MapGenStructureIO.func_143031_a(StructureStrongholdPieces.PortalRoom.class, "SHPR");
        MapGenStructureIO.func_143031_a(StructureStrongholdPieces.Prison.class, "SHPH");
        MapGenStructureIO.func_143031_a(StructureStrongholdPieces.RightTurn.class, "SHRT");
        MapGenStructureIO.func_143031_a(StructureStrongholdPieces.RoomCrossing.class, "SHRC");
        MapGenStructureIO.func_143031_a(StructureStrongholdPieces.Stairs.class, "SHSD");
        MapGenStructureIO.func_143031_a(StructureStrongholdPieces.Stairs2.class, "SHStart");
        MapGenStructureIO.func_143031_a(StructureStrongholdPieces.Straight.class, "SHS");
        MapGenStructureIO.func_143031_a(StructureStrongholdPieces.StairsStraight.class, "SHSSD");
    }

    public static void func_75198_a()
    {
        field_75206_c = new ArrayList();
        StructureStrongholdPieces.PieceWeight[] apieceweight = field_75205_b;
        int i = apieceweight.length;

        for (int j = 0; j < i; ++j)
        {
            StructureStrongholdPieces.PieceWeight pieceweight = apieceweight[j];
            pieceweight.field_75193_c = 0;
            field_75206_c.add(pieceweight);
        }

        field_75203_d = null;
    }

    private static boolean func_75202_c()
    {
        boolean flag = false;
        field_75207_a = 0;
        StructureStrongholdPieces.PieceWeight pieceweight;

        for (Iterator iterator = field_75206_c.iterator(); iterator.hasNext(); field_75207_a += pieceweight.field_75192_b)
        {
            pieceweight = (StructureStrongholdPieces.PieceWeight)iterator.next();

            if (pieceweight.field_75191_d > 0 && pieceweight.field_75193_c < pieceweight.field_75191_d)
            {
                flag = true;
            }
        }

        return flag;
    }

    private static StructureStrongholdPieces.Stronghold func_75200_a(Class p_75200_0_, List p_75200_1_, Random p_75200_2_, int p_75200_3_, int p_75200_4_, int p_75200_5_, int p_75200_6_, int p_75200_7_)
    {
        Object object = null;

        if (p_75200_0_ == StructureStrongholdPieces.Straight.class)
        {
            object = StructureStrongholdPieces.Straight.func_75018_a(p_75200_1_, p_75200_2_, p_75200_3_, p_75200_4_, p_75200_5_, p_75200_6_, p_75200_7_);
        }
        else if (p_75200_0_ == StructureStrongholdPieces.Prison.class)
        {
            object = StructureStrongholdPieces.Prison.func_75016_a(p_75200_1_, p_75200_2_, p_75200_3_, p_75200_4_, p_75200_5_, p_75200_6_, p_75200_7_);
        }
        else if (p_75200_0_ == StructureStrongholdPieces.LeftTurn.class)
        {
            object = StructureStrongholdPieces.LeftTurn.func_75010_a(p_75200_1_, p_75200_2_, p_75200_3_, p_75200_4_, p_75200_5_, p_75200_6_, p_75200_7_);
        }
        else if (p_75200_0_ == StructureStrongholdPieces.RightTurn.class)
        {
            object = StructureStrongholdPieces.RightTurn.func_75010_a(p_75200_1_, p_75200_2_, p_75200_3_, p_75200_4_, p_75200_5_, p_75200_6_, p_75200_7_);
        }
        else if (p_75200_0_ == StructureStrongholdPieces.RoomCrossing.class)
        {
            object = StructureStrongholdPieces.RoomCrossing.func_75012_a(p_75200_1_, p_75200_2_, p_75200_3_, p_75200_4_, p_75200_5_, p_75200_6_, p_75200_7_);
        }
        else if (p_75200_0_ == StructureStrongholdPieces.StairsStraight.class)
        {
            object = StructureStrongholdPieces.StairsStraight.func_75028_a(p_75200_1_, p_75200_2_, p_75200_3_, p_75200_4_, p_75200_5_, p_75200_6_, p_75200_7_);
        }
        else if (p_75200_0_ == StructureStrongholdPieces.Stairs.class)
        {
            object = StructureStrongholdPieces.Stairs.func_75022_a(p_75200_1_, p_75200_2_, p_75200_3_, p_75200_4_, p_75200_5_, p_75200_6_, p_75200_7_);
        }
        else if (p_75200_0_ == StructureStrongholdPieces.Crossing.class)
        {
            object = StructureStrongholdPieces.Crossing.func_74994_a(p_75200_1_, p_75200_2_, p_75200_3_, p_75200_4_, p_75200_5_, p_75200_6_, p_75200_7_);
        }
        else if (p_75200_0_ == StructureStrongholdPieces.ChestCorridor.class)
        {
            object = StructureStrongholdPieces.ChestCorridor.func_75000_a(p_75200_1_, p_75200_2_, p_75200_3_, p_75200_4_, p_75200_5_, p_75200_6_, p_75200_7_);
        }
        else if (p_75200_0_ == StructureStrongholdPieces.Library.class)
        {
            object = StructureStrongholdPieces.Library.func_75006_a(p_75200_1_, p_75200_2_, p_75200_3_, p_75200_4_, p_75200_5_, p_75200_6_, p_75200_7_);
        }
        else if (p_75200_0_ == StructureStrongholdPieces.PortalRoom.class)
        {
            object = StructureStrongholdPieces.PortalRoom.func_75004_a(p_75200_1_, p_75200_2_, p_75200_3_, p_75200_4_, p_75200_5_, p_75200_6_, p_75200_7_);
        }

        return (StructureStrongholdPieces.Stronghold)object;
    }

    private static StructureStrongholdPieces.Stronghold func_75201_b(StructureStrongholdPieces.Stairs2 p_75201_0_, List p_75201_1_, Random p_75201_2_, int p_75201_3_, int p_75201_4_, int p_75201_5_, int p_75201_6_, int p_75201_7_)
    {
        if (!func_75202_c())
        {
            return null;
        }
        else
        {
            if (field_75203_d != null)
            {
                StructureStrongholdPieces.Stronghold stronghold = func_75200_a(field_75203_d, p_75201_1_, p_75201_2_, p_75201_3_, p_75201_4_, p_75201_5_, p_75201_6_, p_75201_7_);
                field_75203_d = null;

                if (stronghold != null)
                {
                    return stronghold;
                }
            }

            int k1 = 0;

            while (k1 < 5)
            {
                ++k1;
                int j1 = p_75201_2_.nextInt(field_75207_a);
                Iterator iterator = field_75206_c.iterator();

                while (iterator.hasNext())
                {
                    StructureStrongholdPieces.PieceWeight pieceweight = (StructureStrongholdPieces.PieceWeight)iterator.next();
                    j1 -= pieceweight.field_75192_b;

                    if (j1 < 0)
                    {
                        if (!pieceweight.func_75189_a(p_75201_7_) || pieceweight == p_75201_0_.field_75027_a)
                        {
                            break;
                        }

                        StructureStrongholdPieces.Stronghold stronghold1 = func_75200_a(pieceweight.field_75194_a, p_75201_1_, p_75201_2_, p_75201_3_, p_75201_4_, p_75201_5_, p_75201_6_, p_75201_7_);

                        if (stronghold1 != null)
                        {
                            ++pieceweight.field_75193_c;
                            p_75201_0_.field_75027_a = pieceweight;

                            if (!pieceweight.func_75190_a())
                            {
                                field_75206_c.remove(pieceweight);
                            }

                            return stronghold1;
                        }
                    }
                }
            }

            StructureBoundingBox structureboundingbox = StructureStrongholdPieces.Corridor.func_74992_a(p_75201_1_, p_75201_2_, p_75201_3_, p_75201_4_, p_75201_5_, p_75201_6_);

            if (structureboundingbox != null && structureboundingbox.field_78895_b > 1)
            {
                return new StructureStrongholdPieces.Corridor(p_75201_7_, p_75201_2_, structureboundingbox, p_75201_6_);
            }
            else
            {
                return null;
            }
        }
    }

    private static StructureComponent func_75196_c(StructureStrongholdPieces.Stairs2 p_75196_0_, List p_75196_1_, Random p_75196_2_, int p_75196_3_, int p_75196_4_, int p_75196_5_, int p_75196_6_, int p_75196_7_)
    {
        if (p_75196_7_ > 50)
        {
            return null;
        }
        else if (Math.abs(p_75196_3_ - p_75196_0_.func_74874_b().field_78897_a) <= 112 && Math.abs(p_75196_5_ - p_75196_0_.func_74874_b().field_78896_c) <= 112)
        {
            StructureStrongholdPieces.Stronghold stronghold = func_75201_b(p_75196_0_, p_75196_1_, p_75196_2_, p_75196_3_, p_75196_4_, p_75196_5_, p_75196_6_, p_75196_7_ + 1);

            if (stronghold != null)
            {
                p_75196_1_.add(stronghold);
                p_75196_0_.field_75026_c.add(stronghold);
            }

            return stronghold;
        }
        else
        {
            return null;
        }
    }

    public static class ChestCorridor extends StructureStrongholdPieces.Stronghold
        {
            public static final WeightedRandomChestContent[] field_75003_a = new WeightedRandomChestContent[] {new WeightedRandomChestContent(Items.ENDER_PEARL, 0, 1, 1, 10), new WeightedRandomChestContent(Items.DIAMOND, 0, 1, 3, 3), new WeightedRandomChestContent(Items.IRON_INGOT, 0, 1, 5, 10), new WeightedRandomChestContent(Items.GOLD_INGOT, 0, 1, 3, 5), new WeightedRandomChestContent(Items.REDSTONE, 0, 4, 9, 5), new WeightedRandomChestContent(Items.BREAD, 0, 1, 3, 15), new WeightedRandomChestContent(Items.APPLE, 0, 1, 3, 15), new WeightedRandomChestContent(Items.IRON_PICKAXE, 0, 1, 1, 5), new WeightedRandomChestContent(Items.IRON_SWORD, 0, 1, 1, 5), new WeightedRandomChestContent(Items.IRON_CHESTPLATE, 0, 1, 1, 5), new WeightedRandomChestContent(Items.IRON_HELMET, 0, 1, 1, 5), new WeightedRandomChestContent(Items.IRON_LEGGINGS, 0, 1, 1, 5), new WeightedRandomChestContent(Items.IRON_BOOTS, 0, 1, 1, 5), new WeightedRandomChestContent(Items.GOLDEN_APPLE, 0, 1, 1, 1), new WeightedRandomChestContent(Items.SADDLE, 0, 1, 1, 1), new WeightedRandomChestContent(Items.IRON_HORSE_ARMOR, 0, 1, 1, 1), new WeightedRandomChestContent(Items.GOLDEN_HORSE_ARMOR, 0, 1, 1, 1), new WeightedRandomChestContent(Items.DIAMOND_HORSE_ARMOR, 0, 1, 1, 1)};
            private boolean field_75002_c;
            private static final String __OBFID = "CL_00000487";

            public ChestCorridor() {}

            public ChestCorridor(int p_i2071_1_, Random p_i2071_2_, StructureBoundingBox p_i2071_3_, int p_i2071_4_)
            {
                super(p_i2071_1_);
                this.field_74885_f = p_i2071_4_;
                this.field_143013_d = this.func_74988_a(p_i2071_2_);
                this.field_74887_e = p_i2071_3_;
            }

            protected void func_143012_a(NBTTagCompound p_143012_1_)
            {
                super.func_143012_a(p_143012_1_);
                p_143012_1_.func_74757_a("Chest", this.field_75002_c);
            }

            protected void func_143011_b(NBTTagCompound p_143011_1_)
            {
                super.func_143011_b(p_143011_1_);
                this.field_75002_c = p_143011_1_.func_74767_n("Chest");
            }

            public void func_74861_a(StructureComponent p_74861_1_, List p_74861_2_, Random p_74861_3_)
            {
                this.func_74986_a((StructureStrongholdPieces.Stairs2)p_74861_1_, p_74861_2_, p_74861_3_, 1, 1);
            }

            public static StructureStrongholdPieces.ChestCorridor func_75000_a(List p_75000_0_, Random p_75000_1_, int p_75000_2_, int p_75000_3_, int p_75000_4_, int p_75000_5_, int p_75000_6_)
            {
                StructureBoundingBox structureboundingbox = StructureBoundingBox.func_78889_a(p_75000_2_, p_75000_3_, p_75000_4_, -1, -1, 0, 5, 5, 7, p_75000_5_);
                return func_74991_a(structureboundingbox) && StructureComponent.func_74883_a(p_75000_0_, structureboundingbox) == null ? new StructureStrongholdPieces.ChestCorridor(p_75000_6_, p_75000_1_, structureboundingbox, p_75000_5_) : null;
            }

            public boolean func_74875_a(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_)
            {
                if (this.func_74860_a(p_74875_1_, p_74875_3_))
                {
                    return false;
                }
                else
                {
                    this.func_74882_a(p_74875_1_, p_74875_3_, 0, 0, 0, 4, 4, 6, true, p_74875_2_, StructureStrongholdPieces.field_75204_e);
                    this.func_74990_a(p_74875_1_, p_74875_2_, p_74875_3_, this.field_143013_d, 1, 1, 0);
                    this.func_74990_a(p_74875_1_, p_74875_2_, p_74875_3_, StructureStrongholdPieces.Stronghold.Door.OPENING, 1, 1, 6);
                    this.func_151549_a(p_74875_1_, p_74875_3_, 3, 1, 2, 3, 1, 4, Blocks.STONEBRICK, Blocks.STONEBRICK, false);
                    this.func_151550_a(p_74875_1_, Blocks.STONE_SLAB, 5, 3, 1, 1, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.STONE_SLAB, 5, 3, 1, 5, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.STONE_SLAB, 5, 3, 2, 2, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.STONE_SLAB, 5, 3, 2, 4, p_74875_3_);
                    int i;

                    for (i = 2; i <= 4; ++i)
                    {
                        this.func_151550_a(p_74875_1_, Blocks.STONE_SLAB, 5, 2, 1, i, p_74875_3_);
                    }

                    if (!this.field_75002_c)
                    {
                        i = this.func_74862_a(2);
                        int j = this.func_74865_a(3, 3);
                        int k = this.func_74873_b(3, 3);

                        if (p_74875_3_.func_78890_b(j, i, k))
                        {
                            this.field_75002_c = true;
                            this.func_74879_a(p_74875_1_, p_74875_3_, p_74875_2_, 3, 2, 3, WeightedRandomChestContent.func_92080_a(field_75003_a, new WeightedRandomChestContent[] {Items.ENCHANTED_BOOK.func_92114_b(p_74875_2_)}), 2 + p_74875_2_.nextInt(2));
                        }
                    }

                    return true;
                }
            }
        }

    public static class Corridor extends StructureStrongholdPieces.Stronghold
        {
            private int field_74993_a;
            private static final String __OBFID = "CL_00000488";

            public Corridor() {}

            public Corridor(int p_i2072_1_, Random p_i2072_2_, StructureBoundingBox p_i2072_3_, int p_i2072_4_)
            {
                super(p_i2072_1_);
                this.field_74885_f = p_i2072_4_;
                this.field_74887_e = p_i2072_3_;
                this.field_74993_a = p_i2072_4_ != 2 && p_i2072_4_ != 0 ? p_i2072_3_.func_78883_b() : p_i2072_3_.func_78880_d();
            }

            protected void func_143012_a(NBTTagCompound p_143012_1_)
            {
                super.func_143012_a(p_143012_1_);
                p_143012_1_.func_74768_a("Steps", this.field_74993_a);
            }

            protected void func_143011_b(NBTTagCompound p_143011_1_)
            {
                super.func_143011_b(p_143011_1_);
                this.field_74993_a = p_143011_1_.func_74762_e("Steps");
            }

            public static StructureBoundingBox func_74992_a(List p_74992_0_, Random p_74992_1_, int p_74992_2_, int p_74992_3_, int p_74992_4_, int p_74992_5_)
            {
                boolean flag = true;
                StructureBoundingBox structureboundingbox = StructureBoundingBox.func_78889_a(p_74992_2_, p_74992_3_, p_74992_4_, -1, -1, 0, 5, 5, 4, p_74992_5_);
                StructureComponent structurecomponent = StructureComponent.func_74883_a(p_74992_0_, structureboundingbox);

                if (structurecomponent == null)
                {
                    return null;
                }
                else
                {
                    if (structurecomponent.func_74874_b().field_78895_b == structureboundingbox.field_78895_b)
                    {
                        for (int i1 = 3; i1 >= 1; --i1)
                        {
                            structureboundingbox = StructureBoundingBox.func_78889_a(p_74992_2_, p_74992_3_, p_74992_4_, -1, -1, 0, 5, 5, i1 - 1, p_74992_5_);

                            if (!structurecomponent.func_74874_b().func_78884_a(structureboundingbox))
                            {
                                return StructureBoundingBox.func_78889_a(p_74992_2_, p_74992_3_, p_74992_4_, -1, -1, 0, 5, 5, i1, p_74992_5_);
                            }
                        }
                    }

                    return null;
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
                    for (int i = 0; i < this.field_74993_a; ++i)
                    {
                        this.func_151550_a(p_74875_1_, Blocks.STONEBRICK, 0, 0, 0, i, p_74875_3_);
                        this.func_151550_a(p_74875_1_, Blocks.STONEBRICK, 0, 1, 0, i, p_74875_3_);
                        this.func_151550_a(p_74875_1_, Blocks.STONEBRICK, 0, 2, 0, i, p_74875_3_);
                        this.func_151550_a(p_74875_1_, Blocks.STONEBRICK, 0, 3, 0, i, p_74875_3_);
                        this.func_151550_a(p_74875_1_, Blocks.STONEBRICK, 0, 4, 0, i, p_74875_3_);

                        for (int j = 1; j <= 3; ++j)
                        {
                            this.func_151550_a(p_74875_1_, Blocks.STONEBRICK, 0, 0, j, i, p_74875_3_);
                            this.func_151550_a(p_74875_1_, Blocks.AIR, 0, 1, j, i, p_74875_3_);
                            this.func_151550_a(p_74875_1_, Blocks.AIR, 0, 2, j, i, p_74875_3_);
                            this.func_151550_a(p_74875_1_, Blocks.AIR, 0, 3, j, i, p_74875_3_);
                            this.func_151550_a(p_74875_1_, Blocks.STONEBRICK, 0, 4, j, i, p_74875_3_);
                        }

                        this.func_151550_a(p_74875_1_, Blocks.STONEBRICK, 0, 0, 4, i, p_74875_3_);
                        this.func_151550_a(p_74875_1_, Blocks.STONEBRICK, 0, 1, 4, i, p_74875_3_);
                        this.func_151550_a(p_74875_1_, Blocks.STONEBRICK, 0, 2, 4, i, p_74875_3_);
                        this.func_151550_a(p_74875_1_, Blocks.STONEBRICK, 0, 3, 4, i, p_74875_3_);
                        this.func_151550_a(p_74875_1_, Blocks.STONEBRICK, 0, 4, 4, i, p_74875_3_);
                    }

                    return true;
                }
            }
        }

    public static class Crossing extends StructureStrongholdPieces.Stronghold
        {
            private boolean field_74996_b;
            private boolean field_74997_c;
            private boolean field_74995_d;
            private boolean field_74999_h;
            private static final String __OBFID = "CL_00000489";

            public Crossing() {}

            public Crossing(int p_i2073_1_, Random p_i2073_2_, StructureBoundingBox p_i2073_3_, int p_i2073_4_)
            {
                super(p_i2073_1_);
                this.field_74885_f = p_i2073_4_;
                this.field_143013_d = this.func_74988_a(p_i2073_2_);
                this.field_74887_e = p_i2073_3_;
                this.field_74996_b = p_i2073_2_.nextBoolean();
                this.field_74997_c = p_i2073_2_.nextBoolean();
                this.field_74995_d = p_i2073_2_.nextBoolean();
                this.field_74999_h = p_i2073_2_.nextInt(3) > 0;
            }

            protected void func_143012_a(NBTTagCompound p_143012_1_)
            {
                super.func_143012_a(p_143012_1_);
                p_143012_1_.func_74757_a("leftLow", this.field_74996_b);
                p_143012_1_.func_74757_a("leftHigh", this.field_74997_c);
                p_143012_1_.func_74757_a("rightLow", this.field_74995_d);
                p_143012_1_.func_74757_a("rightHigh", this.field_74999_h);
            }

            protected void func_143011_b(NBTTagCompound p_143011_1_)
            {
                super.func_143011_b(p_143011_1_);
                this.field_74996_b = p_143011_1_.func_74767_n("leftLow");
                this.field_74997_c = p_143011_1_.func_74767_n("leftHigh");
                this.field_74995_d = p_143011_1_.func_74767_n("rightLow");
                this.field_74999_h = p_143011_1_.func_74767_n("rightHigh");
            }

            public void func_74861_a(StructureComponent p_74861_1_, List p_74861_2_, Random p_74861_3_)
            {
                int i = 3;
                int j = 5;

                if (this.field_74885_f == 1 || this.field_74885_f == 2)
                {
                    i = 8 - i;
                    j = 8 - j;
                }

                this.func_74986_a((StructureStrongholdPieces.Stairs2)p_74861_1_, p_74861_2_, p_74861_3_, 5, 1);

                if (this.field_74996_b)
                {
                    this.func_74989_b((StructureStrongholdPieces.Stairs2)p_74861_1_, p_74861_2_, p_74861_3_, i, 1);
                }

                if (this.field_74997_c)
                {
                    this.func_74989_b((StructureStrongholdPieces.Stairs2)p_74861_1_, p_74861_2_, p_74861_3_, j, 7);
                }

                if (this.field_74995_d)
                {
                    this.func_74987_c((StructureStrongholdPieces.Stairs2)p_74861_1_, p_74861_2_, p_74861_3_, i, 1);
                }

                if (this.field_74999_h)
                {
                    this.func_74987_c((StructureStrongholdPieces.Stairs2)p_74861_1_, p_74861_2_, p_74861_3_, j, 7);
                }
            }

            public static StructureStrongholdPieces.Crossing func_74994_a(List p_74994_0_, Random p_74994_1_, int p_74994_2_, int p_74994_3_, int p_74994_4_, int p_74994_5_, int p_74994_6_)
            {
                StructureBoundingBox structureboundingbox = StructureBoundingBox.func_78889_a(p_74994_2_, p_74994_3_, p_74994_4_, -4, -3, 0, 10, 9, 11, p_74994_5_);
                return func_74991_a(structureboundingbox) && StructureComponent.func_74883_a(p_74994_0_, structureboundingbox) == null ? new StructureStrongholdPieces.Crossing(p_74994_6_, p_74994_1_, structureboundingbox, p_74994_5_) : null;
            }

            public boolean func_74875_a(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_)
            {
                if (this.func_74860_a(p_74875_1_, p_74875_3_))
                {
                    return false;
                }
                else
                {
                    this.func_74882_a(p_74875_1_, p_74875_3_, 0, 0, 0, 9, 8, 10, true, p_74875_2_, StructureStrongholdPieces.field_75204_e);
                    this.func_74990_a(p_74875_1_, p_74875_2_, p_74875_3_, this.field_143013_d, 4, 3, 0);

                    if (this.field_74996_b)
                    {
                        this.func_151549_a(p_74875_1_, p_74875_3_, 0, 3, 1, 0, 5, 3, Blocks.AIR, Blocks.AIR, false);
                    }

                    if (this.field_74995_d)
                    {
                        this.func_151549_a(p_74875_1_, p_74875_3_, 9, 3, 1, 9, 5, 3, Blocks.AIR, Blocks.AIR, false);
                    }

                    if (this.field_74997_c)
                    {
                        this.func_151549_a(p_74875_1_, p_74875_3_, 0, 5, 7, 0, 7, 9, Blocks.AIR, Blocks.AIR, false);
                    }

                    if (this.field_74999_h)
                    {
                        this.func_151549_a(p_74875_1_, p_74875_3_, 9, 5, 7, 9, 7, 9, Blocks.AIR, Blocks.AIR, false);
                    }

                    this.func_151549_a(p_74875_1_, p_74875_3_, 5, 1, 10, 7, 3, 10, Blocks.AIR, Blocks.AIR, false);
                    this.func_74882_a(p_74875_1_, p_74875_3_, 1, 2, 1, 8, 2, 6, false, p_74875_2_, StructureStrongholdPieces.field_75204_e);
                    this.func_74882_a(p_74875_1_, p_74875_3_, 4, 1, 5, 4, 4, 9, false, p_74875_2_, StructureStrongholdPieces.field_75204_e);
                    this.func_74882_a(p_74875_1_, p_74875_3_, 8, 1, 5, 8, 4, 9, false, p_74875_2_, StructureStrongholdPieces.field_75204_e);
                    this.func_74882_a(p_74875_1_, p_74875_3_, 1, 4, 7, 3, 4, 9, false, p_74875_2_, StructureStrongholdPieces.field_75204_e);
                    this.func_74882_a(p_74875_1_, p_74875_3_, 1, 3, 5, 3, 3, 6, false, p_74875_2_, StructureStrongholdPieces.field_75204_e);
                    this.func_151549_a(p_74875_1_, p_74875_3_, 1, 3, 4, 3, 3, 4, Blocks.STONE_SLAB, Blocks.STONE_SLAB, false);
                    this.func_151549_a(p_74875_1_, p_74875_3_, 1, 4, 6, 3, 4, 6, Blocks.STONE_SLAB, Blocks.STONE_SLAB, false);
                    this.func_74882_a(p_74875_1_, p_74875_3_, 5, 1, 7, 7, 1, 8, false, p_74875_2_, StructureStrongholdPieces.field_75204_e);
                    this.func_151549_a(p_74875_1_, p_74875_3_, 5, 1, 9, 7, 1, 9, Blocks.STONE_SLAB, Blocks.STONE_SLAB, false);
                    this.func_151549_a(p_74875_1_, p_74875_3_, 5, 2, 7, 7, 2, 7, Blocks.STONE_SLAB, Blocks.STONE_SLAB, false);
                    this.func_151549_a(p_74875_1_, p_74875_3_, 4, 5, 7, 4, 5, 9, Blocks.STONE_SLAB, Blocks.STONE_SLAB, false);
                    this.func_151549_a(p_74875_1_, p_74875_3_, 8, 5, 7, 8, 5, 9, Blocks.STONE_SLAB, Blocks.STONE_SLAB, false);
                    this.func_151549_a(p_74875_1_, p_74875_3_, 5, 5, 7, 7, 5, 9, Blocks.DOUBLE_STONE_SLAB, Blocks.DOUBLE_STONE_SLAB, false);
                    this.func_151550_a(p_74875_1_, Blocks.TORCH, 0, 6, 5, 6, p_74875_3_);
                    return true;
                }
            }
        }

    public static class LeftTurn extends StructureStrongholdPieces.Stronghold
        {
            private static final String __OBFID = "CL_00000490";

            public LeftTurn() {}

            public LeftTurn(int p_i2074_1_, Random p_i2074_2_, StructureBoundingBox p_i2074_3_, int p_i2074_4_)
            {
                super(p_i2074_1_);
                this.field_74885_f = p_i2074_4_;
                this.field_143013_d = this.func_74988_a(p_i2074_2_);
                this.field_74887_e = p_i2074_3_;
            }

            public void func_74861_a(StructureComponent p_74861_1_, List p_74861_2_, Random p_74861_3_)
            {
                if (this.field_74885_f != 2 && this.field_74885_f != 3)
                {
                    this.func_74987_c((StructureStrongholdPieces.Stairs2)p_74861_1_, p_74861_2_, p_74861_3_, 1, 1);
                }
                else
                {
                    this.func_74989_b((StructureStrongholdPieces.Stairs2)p_74861_1_, p_74861_2_, p_74861_3_, 1, 1);
                }
            }

            public static StructureStrongholdPieces.LeftTurn func_75010_a(List p_75010_0_, Random p_75010_1_, int p_75010_2_, int p_75010_3_, int p_75010_4_, int p_75010_5_, int p_75010_6_)
            {
                StructureBoundingBox structureboundingbox = StructureBoundingBox.func_78889_a(p_75010_2_, p_75010_3_, p_75010_4_, -1, -1, 0, 5, 5, 5, p_75010_5_);
                return func_74991_a(structureboundingbox) && StructureComponent.func_74883_a(p_75010_0_, structureboundingbox) == null ? new StructureStrongholdPieces.LeftTurn(p_75010_6_, p_75010_1_, structureboundingbox, p_75010_5_) : null;
            }

            public boolean func_74875_a(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_)
            {
                if (this.func_74860_a(p_74875_1_, p_74875_3_))
                {
                    return false;
                }
                else
                {
                    this.func_74882_a(p_74875_1_, p_74875_3_, 0, 0, 0, 4, 4, 4, true, p_74875_2_, StructureStrongholdPieces.field_75204_e);
                    this.func_74990_a(p_74875_1_, p_74875_2_, p_74875_3_, this.field_143013_d, 1, 1, 0);

                    if (this.field_74885_f != 2 && this.field_74885_f != 3)
                    {
                        this.func_151549_a(p_74875_1_, p_74875_3_, 4, 1, 1, 4, 3, 3, Blocks.AIR, Blocks.AIR, false);
                    }
                    else
                    {
                        this.func_151549_a(p_74875_1_, p_74875_3_, 0, 1, 1, 0, 3, 3, Blocks.AIR, Blocks.AIR, false);
                    }

                    return true;
                }
            }
        }

    public static class Library extends StructureStrongholdPieces.Stronghold
        {
            public static final WeightedRandomChestContent[] field_75007_b = new WeightedRandomChestContent[] {new WeightedRandomChestContent(Items.BOOK, 0, 1, 3, 20), new WeightedRandomChestContent(Items.PAPER, 0, 2, 7, 20), new WeightedRandomChestContent(Items.MAP, 0, 1, 1, 1), new WeightedRandomChestContent(Items.COMPASS, 0, 1, 1, 1)};
            private boolean field_75008_c;
            private static final String __OBFID = "CL_00000491";

            public Library() {}

            public Library(int p_i2075_1_, Random p_i2075_2_, StructureBoundingBox p_i2075_3_, int p_i2075_4_)
            {
                super(p_i2075_1_);
                this.field_74885_f = p_i2075_4_;
                this.field_143013_d = this.func_74988_a(p_i2075_2_);
                this.field_74887_e = p_i2075_3_;
                this.field_75008_c = p_i2075_3_.func_78882_c() > 6;
            }

            protected void func_143012_a(NBTTagCompound p_143012_1_)
            {
                super.func_143012_a(p_143012_1_);
                p_143012_1_.func_74757_a("Tall", this.field_75008_c);
            }

            protected void func_143011_b(NBTTagCompound p_143011_1_)
            {
                super.func_143011_b(p_143011_1_);
                this.field_75008_c = p_143011_1_.func_74767_n("Tall");
            }

            public static StructureStrongholdPieces.Library func_75006_a(List p_75006_0_, Random p_75006_1_, int p_75006_2_, int p_75006_3_, int p_75006_4_, int p_75006_5_, int p_75006_6_)
            {
                StructureBoundingBox structureboundingbox = StructureBoundingBox.func_78889_a(p_75006_2_, p_75006_3_, p_75006_4_, -4, -1, 0, 14, 11, 15, p_75006_5_);

                if (!func_74991_a(structureboundingbox) || StructureComponent.func_74883_a(p_75006_0_, structureboundingbox) != null)
                {
                    structureboundingbox = StructureBoundingBox.func_78889_a(p_75006_2_, p_75006_3_, p_75006_4_, -4, -1, 0, 14, 6, 15, p_75006_5_);

                    if (!func_74991_a(structureboundingbox) || StructureComponent.func_74883_a(p_75006_0_, structureboundingbox) != null)
                    {
                        return null;
                    }
                }

                return new StructureStrongholdPieces.Library(p_75006_6_, p_75006_1_, structureboundingbox, p_75006_5_);
            }

            public boolean func_74875_a(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_)
            {
                if (this.func_74860_a(p_74875_1_, p_74875_3_))
                {
                    return false;
                }
                else
                {
                    byte b0 = 11;

                    if (!this.field_75008_c)
                    {
                        b0 = 6;
                    }

                    this.func_74882_a(p_74875_1_, p_74875_3_, 0, 0, 0, 13, b0 - 1, 14, true, p_74875_2_, StructureStrongholdPieces.field_75204_e);
                    this.func_74990_a(p_74875_1_, p_74875_2_, p_74875_3_, this.field_143013_d, 4, 1, 0);
                    this.func_151551_a(p_74875_1_, p_74875_3_, p_74875_2_, 0.07F, 2, 1, 1, 11, 4, 13, Blocks.WEB, Blocks.WEB, false);
                    boolean flag = true;
                    boolean flag1 = true;
                    int i;

                    for (i = 1; i <= 13; ++i)
                    {
                        if ((i - 1) % 4 == 0)
                        {
                            this.func_151549_a(p_74875_1_, p_74875_3_, 1, 1, i, 1, 4, i, Blocks.PLANKS, Blocks.PLANKS, false);
                            this.func_151549_a(p_74875_1_, p_74875_3_, 12, 1, i, 12, 4, i, Blocks.PLANKS, Blocks.PLANKS, false);
                            this.func_151550_a(p_74875_1_, Blocks.TORCH, 0, 2, 3, i, p_74875_3_);
                            this.func_151550_a(p_74875_1_, Blocks.TORCH, 0, 11, 3, i, p_74875_3_);

                            if (this.field_75008_c)
                            {
                                this.func_151549_a(p_74875_1_, p_74875_3_, 1, 6, i, 1, 9, i, Blocks.PLANKS, Blocks.PLANKS, false);
                                this.func_151549_a(p_74875_1_, p_74875_3_, 12, 6, i, 12, 9, i, Blocks.PLANKS, Blocks.PLANKS, false);
                            }
                        }
                        else
                        {
                            this.func_151549_a(p_74875_1_, p_74875_3_, 1, 1, i, 1, 4, i, Blocks.BOOKSHELF, Blocks.BOOKSHELF, false);
                            this.func_151549_a(p_74875_1_, p_74875_3_, 12, 1, i, 12, 4, i, Blocks.BOOKSHELF, Blocks.BOOKSHELF, false);

                            if (this.field_75008_c)
                            {
                                this.func_151549_a(p_74875_1_, p_74875_3_, 1, 6, i, 1, 9, i, Blocks.BOOKSHELF, Blocks.BOOKSHELF, false);
                                this.func_151549_a(p_74875_1_, p_74875_3_, 12, 6, i, 12, 9, i, Blocks.BOOKSHELF, Blocks.BOOKSHELF, false);
                            }
                        }
                    }

                    for (i = 3; i < 12; i += 2)
                    {
                        this.func_151549_a(p_74875_1_, p_74875_3_, 3, 1, i, 4, 3, i, Blocks.BOOKSHELF, Blocks.BOOKSHELF, false);
                        this.func_151549_a(p_74875_1_, p_74875_3_, 6, 1, i, 7, 3, i, Blocks.BOOKSHELF, Blocks.BOOKSHELF, false);
                        this.func_151549_a(p_74875_1_, p_74875_3_, 9, 1, i, 10, 3, i, Blocks.BOOKSHELF, Blocks.BOOKSHELF, false);
                    }

                    if (this.field_75008_c)
                    {
                        this.func_151549_a(p_74875_1_, p_74875_3_, 1, 5, 1, 3, 5, 13, Blocks.PLANKS, Blocks.PLANKS, false);
                        this.func_151549_a(p_74875_1_, p_74875_3_, 10, 5, 1, 12, 5, 13, Blocks.PLANKS, Blocks.PLANKS, false);
                        this.func_151549_a(p_74875_1_, p_74875_3_, 4, 5, 1, 9, 5, 2, Blocks.PLANKS, Blocks.PLANKS, false);
                        this.func_151549_a(p_74875_1_, p_74875_3_, 4, 5, 12, 9, 5, 13, Blocks.PLANKS, Blocks.PLANKS, false);
                        this.func_151550_a(p_74875_1_, Blocks.PLANKS, 0, 9, 5, 11, p_74875_3_);
                        this.func_151550_a(p_74875_1_, Blocks.PLANKS, 0, 8, 5, 11, p_74875_3_);
                        this.func_151550_a(p_74875_1_, Blocks.PLANKS, 0, 9, 5, 10, p_74875_3_);
                        this.func_151549_a(p_74875_1_, p_74875_3_, 3, 6, 2, 3, 6, 12, Blocks.FENCE, Blocks.FENCE, false);
                        this.func_151549_a(p_74875_1_, p_74875_3_, 10, 6, 2, 10, 6, 10, Blocks.FENCE, Blocks.FENCE, false);
                        this.func_151549_a(p_74875_1_, p_74875_3_, 4, 6, 2, 9, 6, 2, Blocks.FENCE, Blocks.FENCE, false);
                        this.func_151549_a(p_74875_1_, p_74875_3_, 4, 6, 12, 8, 6, 12, Blocks.FENCE, Blocks.FENCE, false);
                        this.func_151550_a(p_74875_1_, Blocks.FENCE, 0, 9, 6, 11, p_74875_3_);
                        this.func_151550_a(p_74875_1_, Blocks.FENCE, 0, 8, 6, 11, p_74875_3_);
                        this.func_151550_a(p_74875_1_, Blocks.FENCE, 0, 9, 6, 10, p_74875_3_);
                        i = this.func_151555_a(Blocks.LADDER, 3);
                        this.func_151550_a(p_74875_1_, Blocks.LADDER, i, 10, 1, 13, p_74875_3_);
                        this.func_151550_a(p_74875_1_, Blocks.LADDER, i, 10, 2, 13, p_74875_3_);
                        this.func_151550_a(p_74875_1_, Blocks.LADDER, i, 10, 3, 13, p_74875_3_);
                        this.func_151550_a(p_74875_1_, Blocks.LADDER, i, 10, 4, 13, p_74875_3_);
                        this.func_151550_a(p_74875_1_, Blocks.LADDER, i, 10, 5, 13, p_74875_3_);
                        this.func_151550_a(p_74875_1_, Blocks.LADDER, i, 10, 6, 13, p_74875_3_);
                        this.func_151550_a(p_74875_1_, Blocks.LADDER, i, 10, 7, 13, p_74875_3_);
                        byte b1 = 7;
                        byte b2 = 7;
                        this.func_151550_a(p_74875_1_, Blocks.FENCE, 0, b1 - 1, 9, b2, p_74875_3_);
                        this.func_151550_a(p_74875_1_, Blocks.FENCE, 0, b1, 9, b2, p_74875_3_);
                        this.func_151550_a(p_74875_1_, Blocks.FENCE, 0, b1 - 1, 8, b2, p_74875_3_);
                        this.func_151550_a(p_74875_1_, Blocks.FENCE, 0, b1, 8, b2, p_74875_3_);
                        this.func_151550_a(p_74875_1_, Blocks.FENCE, 0, b1 - 1, 7, b2, p_74875_3_);
                        this.func_151550_a(p_74875_1_, Blocks.FENCE, 0, b1, 7, b2, p_74875_3_);
                        this.func_151550_a(p_74875_1_, Blocks.FENCE, 0, b1 - 2, 7, b2, p_74875_3_);
                        this.func_151550_a(p_74875_1_, Blocks.FENCE, 0, b1 + 1, 7, b2, p_74875_3_);
                        this.func_151550_a(p_74875_1_, Blocks.FENCE, 0, b1 - 1, 7, b2 - 1, p_74875_3_);
                        this.func_151550_a(p_74875_1_, Blocks.FENCE, 0, b1 - 1, 7, b2 + 1, p_74875_3_);
                        this.func_151550_a(p_74875_1_, Blocks.FENCE, 0, b1, 7, b2 - 1, p_74875_3_);
                        this.func_151550_a(p_74875_1_, Blocks.FENCE, 0, b1, 7, b2 + 1, p_74875_3_);
                        this.func_151550_a(p_74875_1_, Blocks.TORCH, 0, b1 - 2, 8, b2, p_74875_3_);
                        this.func_151550_a(p_74875_1_, Blocks.TORCH, 0, b1 + 1, 8, b2, p_74875_3_);
                        this.func_151550_a(p_74875_1_, Blocks.TORCH, 0, b1 - 1, 8, b2 - 1, p_74875_3_);
                        this.func_151550_a(p_74875_1_, Blocks.TORCH, 0, b1 - 1, 8, b2 + 1, p_74875_3_);
                        this.func_151550_a(p_74875_1_, Blocks.TORCH, 0, b1, 8, b2 - 1, p_74875_3_);
                        this.func_151550_a(p_74875_1_, Blocks.TORCH, 0, b1, 8, b2 + 1, p_74875_3_);
                    }

                    this.func_74879_a(p_74875_1_, p_74875_3_, p_74875_2_, 3, 3, 5, WeightedRandomChestContent.func_92080_a(field_75007_b, new WeightedRandomChestContent[] {Items.ENCHANTED_BOOK.func_92112_a(p_74875_2_, 1, 5, 2)}), 1 + p_74875_2_.nextInt(4));

                    if (this.field_75008_c)
                    {
                        this.func_151550_a(p_74875_1_, Blocks.AIR, 0, 12, 9, 1, p_74875_3_);
                        this.func_74879_a(p_74875_1_, p_74875_3_, p_74875_2_, 12, 8, 1, WeightedRandomChestContent.func_92080_a(field_75007_b, new WeightedRandomChestContent[] {Items.ENCHANTED_BOOK.func_92112_a(p_74875_2_, 1, 5, 2)}), 1 + p_74875_2_.nextInt(4));
                    }

                    return true;
                }
            }
        }

    static class PieceWeight
        {
            public Class field_75194_a;
            public final int field_75192_b;
            public int field_75193_c;
            public int field_75191_d;
            private static final String __OBFID = "CL_00000492";

            public PieceWeight(Class p_i2076_1_, int p_i2076_2_, int p_i2076_3_)
            {
                this.field_75194_a = p_i2076_1_;
                this.field_75192_b = p_i2076_2_;
                this.field_75191_d = p_i2076_3_;
            }

            public boolean func_75189_a(int p_75189_1_)
            {
                return this.field_75191_d == 0 || this.field_75193_c < this.field_75191_d;
            }

            public boolean func_75190_a()
            {
                return this.field_75191_d == 0 || this.field_75193_c < this.field_75191_d;
            }
        }

    public static class PortalRoom extends StructureStrongholdPieces.Stronghold
        {
            private boolean field_75005_a;
            private static final String __OBFID = "CL_00000493";

            public PortalRoom() {}

            public PortalRoom(int p_i2077_1_, Random p_i2077_2_, StructureBoundingBox p_i2077_3_, int p_i2077_4_)
            {
                super(p_i2077_1_);
                this.field_74885_f = p_i2077_4_;
                this.field_74887_e = p_i2077_3_;
            }

            protected void func_143012_a(NBTTagCompound p_143012_1_)
            {
                super.func_143012_a(p_143012_1_);
                p_143012_1_.func_74757_a("Mob", this.field_75005_a);
            }

            protected void func_143011_b(NBTTagCompound p_143011_1_)
            {
                super.func_143011_b(p_143011_1_);
                this.field_75005_a = p_143011_1_.func_74767_n("Mob");
            }

            public void func_74861_a(StructureComponent p_74861_1_, List p_74861_2_, Random p_74861_3_)
            {
                if (p_74861_1_ != null)
                {
                    ((StructureStrongholdPieces.Stairs2)p_74861_1_).field_75025_b = this;
                }
            }

            public static StructureStrongholdPieces.PortalRoom func_75004_a(List p_75004_0_, Random p_75004_1_, int p_75004_2_, int p_75004_3_, int p_75004_4_, int p_75004_5_, int p_75004_6_)
            {
                StructureBoundingBox structureboundingbox = StructureBoundingBox.func_78889_a(p_75004_2_, p_75004_3_, p_75004_4_, -4, -1, 0, 11, 8, 16, p_75004_5_);
                return func_74991_a(structureboundingbox) && StructureComponent.func_74883_a(p_75004_0_, structureboundingbox) == null ? new StructureStrongholdPieces.PortalRoom(p_75004_6_, p_75004_1_, structureboundingbox, p_75004_5_) : null;
            }

            public boolean func_74875_a(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_)
            {
                this.func_74882_a(p_74875_1_, p_74875_3_, 0, 0, 0, 10, 7, 15, false, p_74875_2_, StructureStrongholdPieces.field_75204_e);
                this.func_74990_a(p_74875_1_, p_74875_2_, p_74875_3_, StructureStrongholdPieces.Stronghold.Door.GRATES, 4, 1, 0);
                byte b0 = 6;
                this.func_74882_a(p_74875_1_, p_74875_3_, 1, b0, 1, 1, b0, 14, false, p_74875_2_, StructureStrongholdPieces.field_75204_e);
                this.func_74882_a(p_74875_1_, p_74875_3_, 9, b0, 1, 9, b0, 14, false, p_74875_2_, StructureStrongholdPieces.field_75204_e);
                this.func_74882_a(p_74875_1_, p_74875_3_, 2, b0, 1, 8, b0, 2, false, p_74875_2_, StructureStrongholdPieces.field_75204_e);
                this.func_74882_a(p_74875_1_, p_74875_3_, 2, b0, 14, 8, b0, 14, false, p_74875_2_, StructureStrongholdPieces.field_75204_e);
                this.func_74882_a(p_74875_1_, p_74875_3_, 1, 1, 1, 2, 1, 4, false, p_74875_2_, StructureStrongholdPieces.field_75204_e);
                this.func_74882_a(p_74875_1_, p_74875_3_, 8, 1, 1, 9, 1, 4, false, p_74875_2_, StructureStrongholdPieces.field_75204_e);
                this.func_151549_a(p_74875_1_, p_74875_3_, 1, 1, 1, 1, 1, 3, Blocks.FLOWING_LAVA, Blocks.FLOWING_LAVA, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 9, 1, 1, 9, 1, 3, Blocks.FLOWING_LAVA, Blocks.FLOWING_LAVA, false);
                this.func_74882_a(p_74875_1_, p_74875_3_, 3, 1, 8, 7, 1, 12, false, p_74875_2_, StructureStrongholdPieces.field_75204_e);
                this.func_151549_a(p_74875_1_, p_74875_3_, 4, 1, 9, 6, 1, 11, Blocks.FLOWING_LAVA, Blocks.FLOWING_LAVA, false);
                int i;

                for (i = 3; i < 14; i += 2)
                {
                    this.func_151549_a(p_74875_1_, p_74875_3_, 0, 3, i, 0, 4, i, Blocks.IRON_BARS, Blocks.IRON_BARS, false);
                    this.func_151549_a(p_74875_1_, p_74875_3_, 10, 3, i, 10, 4, i, Blocks.IRON_BARS, Blocks.IRON_BARS, false);
                }

                for (i = 2; i < 9; i += 2)
                {
                    this.func_151549_a(p_74875_1_, p_74875_3_, i, 3, 15, i, 4, 15, Blocks.IRON_BARS, Blocks.IRON_BARS, false);
                }

                i = this.func_151555_a(Blocks.STONE_BRICK_STAIRS, 3);
                this.func_74882_a(p_74875_1_, p_74875_3_, 4, 1, 5, 6, 1, 7, false, p_74875_2_, StructureStrongholdPieces.field_75204_e);
                this.func_74882_a(p_74875_1_, p_74875_3_, 4, 2, 6, 6, 2, 7, false, p_74875_2_, StructureStrongholdPieces.field_75204_e);
                this.func_74882_a(p_74875_1_, p_74875_3_, 4, 3, 7, 6, 3, 7, false, p_74875_2_, StructureStrongholdPieces.field_75204_e);

                for (int j = 4; j <= 6; ++j)
                {
                    this.func_151550_a(p_74875_1_, Blocks.STONE_BRICK_STAIRS, i, j, 1, 4, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.STONE_BRICK_STAIRS, i, j, 2, 5, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.STONE_BRICK_STAIRS, i, j, 3, 6, p_74875_3_);
                }

                byte b4 = 2;
                byte b1 = 0;
                byte b2 = 3;
                byte b3 = 1;

                switch (this.field_74885_f)
                {
                    case 0:
                        b4 = 0;
                        b1 = 2;
                        break;
                    case 1:
                        b4 = 1;
                        b1 = 3;
                        b2 = 0;
                        b3 = 2;
                    case 2:
                    default:
                        break;
                    case 3:
                        b4 = 3;
                        b1 = 1;
                        b2 = 0;
                        b3 = 2;
                }

                this.func_151550_a(p_74875_1_, Blocks.END_PORTAL_FRAME, b4 + (p_74875_2_.nextFloat() > 0.9F ? 4 : 0), 4, 3, 8, p_74875_3_);
                this.func_151550_a(p_74875_1_, Blocks.END_PORTAL_FRAME, b4 + (p_74875_2_.nextFloat() > 0.9F ? 4 : 0), 5, 3, 8, p_74875_3_);
                this.func_151550_a(p_74875_1_, Blocks.END_PORTAL_FRAME, b4 + (p_74875_2_.nextFloat() > 0.9F ? 4 : 0), 6, 3, 8, p_74875_3_);
                this.func_151550_a(p_74875_1_, Blocks.END_PORTAL_FRAME, b1 + (p_74875_2_.nextFloat() > 0.9F ? 4 : 0), 4, 3, 12, p_74875_3_);
                this.func_151550_a(p_74875_1_, Blocks.END_PORTAL_FRAME, b1 + (p_74875_2_.nextFloat() > 0.9F ? 4 : 0), 5, 3, 12, p_74875_3_);
                this.func_151550_a(p_74875_1_, Blocks.END_PORTAL_FRAME, b1 + (p_74875_2_.nextFloat() > 0.9F ? 4 : 0), 6, 3, 12, p_74875_3_);
                this.func_151550_a(p_74875_1_, Blocks.END_PORTAL_FRAME, b2 + (p_74875_2_.nextFloat() > 0.9F ? 4 : 0), 3, 3, 9, p_74875_3_);
                this.func_151550_a(p_74875_1_, Blocks.END_PORTAL_FRAME, b2 + (p_74875_2_.nextFloat() > 0.9F ? 4 : 0), 3, 3, 10, p_74875_3_);
                this.func_151550_a(p_74875_1_, Blocks.END_PORTAL_FRAME, b2 + (p_74875_2_.nextFloat() > 0.9F ? 4 : 0), 3, 3, 11, p_74875_3_);
                this.func_151550_a(p_74875_1_, Blocks.END_PORTAL_FRAME, b3 + (p_74875_2_.nextFloat() > 0.9F ? 4 : 0), 7, 3, 9, p_74875_3_);
                this.func_151550_a(p_74875_1_, Blocks.END_PORTAL_FRAME, b3 + (p_74875_2_.nextFloat() > 0.9F ? 4 : 0), 7, 3, 10, p_74875_3_);
                this.func_151550_a(p_74875_1_, Blocks.END_PORTAL_FRAME, b3 + (p_74875_2_.nextFloat() > 0.9F ? 4 : 0), 7, 3, 11, p_74875_3_);

                if (!this.field_75005_a)
                {
                    int i1 = this.func_74862_a(3);
                    int k = this.func_74865_a(5, 6);
                    int l = this.func_74873_b(5, 6);

                    if (p_74875_3_.func_78890_b(k, i1, l))
                    {
                        this.field_75005_a = true;
                        p_74875_1_.func_147465_d(k, i1, l, Blocks.MOB_SPAWNER, 0, 2);
                        TileEntityMobSpawner tileentitymobspawner = (TileEntityMobSpawner)p_74875_1_.func_147438_o(k, i1, l);

                        if (tileentitymobspawner != null)
                        {
                            tileentitymobspawner.func_145881_a().func_98272_a("Silverfish");
                        }
                    }
                }

                return true;
            }
        }

    public static class Prison extends StructureStrongholdPieces.Stronghold
        {
            private static final String __OBFID = "CL_00000494";

            public Prison() {}

            public Prison(int p_i2078_1_, Random p_i2078_2_, StructureBoundingBox p_i2078_3_, int p_i2078_4_)
            {
                super(p_i2078_1_);
                this.field_74885_f = p_i2078_4_;
                this.field_143013_d = this.func_74988_a(p_i2078_2_);
                this.field_74887_e = p_i2078_3_;
            }

            public void func_74861_a(StructureComponent p_74861_1_, List p_74861_2_, Random p_74861_3_)
            {
                this.func_74986_a((StructureStrongholdPieces.Stairs2)p_74861_1_, p_74861_2_, p_74861_3_, 1, 1);
            }

            public static StructureStrongholdPieces.Prison func_75016_a(List p_75016_0_, Random p_75016_1_, int p_75016_2_, int p_75016_3_, int p_75016_4_, int p_75016_5_, int p_75016_6_)
            {
                StructureBoundingBox structureboundingbox = StructureBoundingBox.func_78889_a(p_75016_2_, p_75016_3_, p_75016_4_, -1, -1, 0, 9, 5, 11, p_75016_5_);
                return func_74991_a(structureboundingbox) && StructureComponent.func_74883_a(p_75016_0_, structureboundingbox) == null ? new StructureStrongholdPieces.Prison(p_75016_6_, p_75016_1_, structureboundingbox, p_75016_5_) : null;
            }

            public boolean func_74875_a(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_)
            {
                if (this.func_74860_a(p_74875_1_, p_74875_3_))
                {
                    return false;
                }
                else
                {
                    this.func_74882_a(p_74875_1_, p_74875_3_, 0, 0, 0, 8, 4, 10, true, p_74875_2_, StructureStrongholdPieces.field_75204_e);
                    this.func_74990_a(p_74875_1_, p_74875_2_, p_74875_3_, this.field_143013_d, 1, 1, 0);
                    this.func_151549_a(p_74875_1_, p_74875_3_, 1, 1, 10, 3, 3, 10, Blocks.AIR, Blocks.AIR, false);
                    this.func_74882_a(p_74875_1_, p_74875_3_, 4, 1, 1, 4, 3, 1, false, p_74875_2_, StructureStrongholdPieces.field_75204_e);
                    this.func_74882_a(p_74875_1_, p_74875_3_, 4, 1, 3, 4, 3, 3, false, p_74875_2_, StructureStrongholdPieces.field_75204_e);
                    this.func_74882_a(p_74875_1_, p_74875_3_, 4, 1, 7, 4, 3, 7, false, p_74875_2_, StructureStrongholdPieces.field_75204_e);
                    this.func_74882_a(p_74875_1_, p_74875_3_, 4, 1, 9, 4, 3, 9, false, p_74875_2_, StructureStrongholdPieces.field_75204_e);
                    this.func_151549_a(p_74875_1_, p_74875_3_, 4, 1, 4, 4, 3, 6, Blocks.IRON_BARS, Blocks.IRON_BARS, false);
                    this.func_151549_a(p_74875_1_, p_74875_3_, 5, 1, 5, 7, 3, 5, Blocks.IRON_BARS, Blocks.IRON_BARS, false);
                    this.func_151550_a(p_74875_1_, Blocks.IRON_BARS, 0, 4, 3, 2, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.IRON_BARS, 0, 4, 3, 8, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.IRON_DOOR, this.func_151555_a(Blocks.IRON_DOOR, 3), 4, 1, 2, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.IRON_DOOR, this.func_151555_a(Blocks.IRON_DOOR, 3) + 8, 4, 2, 2, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.IRON_DOOR, this.func_151555_a(Blocks.IRON_DOOR, 3), 4, 1, 8, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.IRON_DOOR, this.func_151555_a(Blocks.IRON_DOOR, 3) + 8, 4, 2, 8, p_74875_3_);
                    return true;
                }
            }
        }

    public static class RightTurn extends StructureStrongholdPieces.LeftTurn
        {
            private static final String __OBFID = "CL_00000495";

            public void func_74861_a(StructureComponent p_74861_1_, List p_74861_2_, Random p_74861_3_)
            {
                if (this.field_74885_f != 2 && this.field_74885_f != 3)
                {
                    this.func_74989_b((StructureStrongholdPieces.Stairs2)p_74861_1_, p_74861_2_, p_74861_3_, 1, 1);
                }
                else
                {
                    this.func_74987_c((StructureStrongholdPieces.Stairs2)p_74861_1_, p_74861_2_, p_74861_3_, 1, 1);
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
                    this.func_74882_a(p_74875_1_, p_74875_3_, 0, 0, 0, 4, 4, 4, true, p_74875_2_, StructureStrongholdPieces.field_75204_e);
                    this.func_74990_a(p_74875_1_, p_74875_2_, p_74875_3_, this.field_143013_d, 1, 1, 0);

                    if (this.field_74885_f != 2 && this.field_74885_f != 3)
                    {
                        this.func_151549_a(p_74875_1_, p_74875_3_, 0, 1, 1, 0, 3, 3, Blocks.AIR, Blocks.AIR, false);
                    }
                    else
                    {
                        this.func_151549_a(p_74875_1_, p_74875_3_, 4, 1, 1, 4, 3, 3, Blocks.AIR, Blocks.AIR, false);
                    }

                    return true;
                }
            }
        }

    public static class RoomCrossing extends StructureStrongholdPieces.Stronghold
        {
            public static final WeightedRandomChestContent[] field_75014_c = new WeightedRandomChestContent[] {new WeightedRandomChestContent(Items.IRON_INGOT, 0, 1, 5, 10), new WeightedRandomChestContent(Items.GOLD_INGOT, 0, 1, 3, 5), new WeightedRandomChestContent(Items.REDSTONE, 0, 4, 9, 5), new WeightedRandomChestContent(Items.COAL, 0, 3, 8, 10), new WeightedRandomChestContent(Items.BREAD, 0, 1, 3, 15), new WeightedRandomChestContent(Items.APPLE, 0, 1, 3, 15), new WeightedRandomChestContent(Items.IRON_PICKAXE, 0, 1, 1, 1)};
            protected int field_75013_b;
            private static final String __OBFID = "CL_00000496";

            public RoomCrossing() {}

            public RoomCrossing(int p_i2079_1_, Random p_i2079_2_, StructureBoundingBox p_i2079_3_, int p_i2079_4_)
            {
                super(p_i2079_1_);
                this.field_74885_f = p_i2079_4_;
                this.field_143013_d = this.func_74988_a(p_i2079_2_);
                this.field_74887_e = p_i2079_3_;
                this.field_75013_b = p_i2079_2_.nextInt(5);
            }

            protected void func_143012_a(NBTTagCompound p_143012_1_)
            {
                super.func_143012_a(p_143012_1_);
                p_143012_1_.func_74768_a("Type", this.field_75013_b);
            }

            protected void func_143011_b(NBTTagCompound p_143011_1_)
            {
                super.func_143011_b(p_143011_1_);
                this.field_75013_b = p_143011_1_.func_74762_e("Type");
            }

            public void func_74861_a(StructureComponent p_74861_1_, List p_74861_2_, Random p_74861_3_)
            {
                this.func_74986_a((StructureStrongholdPieces.Stairs2)p_74861_1_, p_74861_2_, p_74861_3_, 4, 1);
                this.func_74989_b((StructureStrongholdPieces.Stairs2)p_74861_1_, p_74861_2_, p_74861_3_, 1, 4);
                this.func_74987_c((StructureStrongholdPieces.Stairs2)p_74861_1_, p_74861_2_, p_74861_3_, 1, 4);
            }

            public static StructureStrongholdPieces.RoomCrossing func_75012_a(List p_75012_0_, Random p_75012_1_, int p_75012_2_, int p_75012_3_, int p_75012_4_, int p_75012_5_, int p_75012_6_)
            {
                StructureBoundingBox structureboundingbox = StructureBoundingBox.func_78889_a(p_75012_2_, p_75012_3_, p_75012_4_, -4, -1, 0, 11, 7, 11, p_75012_5_);
                return func_74991_a(structureboundingbox) && StructureComponent.func_74883_a(p_75012_0_, structureboundingbox) == null ? new StructureStrongholdPieces.RoomCrossing(p_75012_6_, p_75012_1_, structureboundingbox, p_75012_5_) : null;
            }

            public boolean func_74875_a(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_)
            {
                if (this.func_74860_a(p_74875_1_, p_74875_3_))
                {
                    return false;
                }
                else
                {
                    this.func_74882_a(p_74875_1_, p_74875_3_, 0, 0, 0, 10, 6, 10, true, p_74875_2_, StructureStrongholdPieces.field_75204_e);
                    this.func_74990_a(p_74875_1_, p_74875_2_, p_74875_3_, this.field_143013_d, 4, 1, 0);
                    this.func_151549_a(p_74875_1_, p_74875_3_, 4, 1, 10, 6, 3, 10, Blocks.AIR, Blocks.AIR, false);
                    this.func_151549_a(p_74875_1_, p_74875_3_, 0, 1, 4, 0, 3, 6, Blocks.AIR, Blocks.AIR, false);
                    this.func_151549_a(p_74875_1_, p_74875_3_, 10, 1, 4, 10, 3, 6, Blocks.AIR, Blocks.AIR, false);
                    int i;

                    switch (this.field_75013_b)
                    {
                        case 0:
                            this.func_151550_a(p_74875_1_, Blocks.STONEBRICK, 0, 5, 1, 5, p_74875_3_);
                            this.func_151550_a(p_74875_1_, Blocks.STONEBRICK, 0, 5, 2, 5, p_74875_3_);
                            this.func_151550_a(p_74875_1_, Blocks.STONEBRICK, 0, 5, 3, 5, p_74875_3_);
                            this.func_151550_a(p_74875_1_, Blocks.TORCH, 0, 4, 3, 5, p_74875_3_);
                            this.func_151550_a(p_74875_1_, Blocks.TORCH, 0, 6, 3, 5, p_74875_3_);
                            this.func_151550_a(p_74875_1_, Blocks.TORCH, 0, 5, 3, 4, p_74875_3_);
                            this.func_151550_a(p_74875_1_, Blocks.TORCH, 0, 5, 3, 6, p_74875_3_);
                            this.func_151550_a(p_74875_1_, Blocks.STONE_SLAB, 0, 4, 1, 4, p_74875_3_);
                            this.func_151550_a(p_74875_1_, Blocks.STONE_SLAB, 0, 4, 1, 5, p_74875_3_);
                            this.func_151550_a(p_74875_1_, Blocks.STONE_SLAB, 0, 4, 1, 6, p_74875_3_);
                            this.func_151550_a(p_74875_1_, Blocks.STONE_SLAB, 0, 6, 1, 4, p_74875_3_);
                            this.func_151550_a(p_74875_1_, Blocks.STONE_SLAB, 0, 6, 1, 5, p_74875_3_);
                            this.func_151550_a(p_74875_1_, Blocks.STONE_SLAB, 0, 6, 1, 6, p_74875_3_);
                            this.func_151550_a(p_74875_1_, Blocks.STONE_SLAB, 0, 5, 1, 4, p_74875_3_);
                            this.func_151550_a(p_74875_1_, Blocks.STONE_SLAB, 0, 5, 1, 6, p_74875_3_);
                            break;
                        case 1:
                            for (i = 0; i < 5; ++i)
                            {
                                this.func_151550_a(p_74875_1_, Blocks.STONEBRICK, 0, 3, 1, 3 + i, p_74875_3_);
                                this.func_151550_a(p_74875_1_, Blocks.STONEBRICK, 0, 7, 1, 3 + i, p_74875_3_);
                                this.func_151550_a(p_74875_1_, Blocks.STONEBRICK, 0, 3 + i, 1, 3, p_74875_3_);
                                this.func_151550_a(p_74875_1_, Blocks.STONEBRICK, 0, 3 + i, 1, 7, p_74875_3_);
                            }

                            this.func_151550_a(p_74875_1_, Blocks.STONEBRICK, 0, 5, 1, 5, p_74875_3_);
                            this.func_151550_a(p_74875_1_, Blocks.STONEBRICK, 0, 5, 2, 5, p_74875_3_);
                            this.func_151550_a(p_74875_1_, Blocks.STONEBRICK, 0, 5, 3, 5, p_74875_3_);
                            this.func_151550_a(p_74875_1_, Blocks.FLOWING_WATER, 0, 5, 4, 5, p_74875_3_);
                            break;
                        case 2:
                            for (i = 1; i <= 9; ++i)
                            {
                                this.func_151550_a(p_74875_1_, Blocks.COBBLESTONE, 0, 1, 3, i, p_74875_3_);
                                this.func_151550_a(p_74875_1_, Blocks.COBBLESTONE, 0, 9, 3, i, p_74875_3_);
                            }

                            for (i = 1; i <= 9; ++i)
                            {
                                this.func_151550_a(p_74875_1_, Blocks.COBBLESTONE, 0, i, 3, 1, p_74875_3_);
                                this.func_151550_a(p_74875_1_, Blocks.COBBLESTONE, 0, i, 3, 9, p_74875_3_);
                            }

                            this.func_151550_a(p_74875_1_, Blocks.COBBLESTONE, 0, 5, 1, 4, p_74875_3_);
                            this.func_151550_a(p_74875_1_, Blocks.COBBLESTONE, 0, 5, 1, 6, p_74875_3_);
                            this.func_151550_a(p_74875_1_, Blocks.COBBLESTONE, 0, 5, 3, 4, p_74875_3_);
                            this.func_151550_a(p_74875_1_, Blocks.COBBLESTONE, 0, 5, 3, 6, p_74875_3_);
                            this.func_151550_a(p_74875_1_, Blocks.COBBLESTONE, 0, 4, 1, 5, p_74875_3_);
                            this.func_151550_a(p_74875_1_, Blocks.COBBLESTONE, 0, 6, 1, 5, p_74875_3_);
                            this.func_151550_a(p_74875_1_, Blocks.COBBLESTONE, 0, 4, 3, 5, p_74875_3_);
                            this.func_151550_a(p_74875_1_, Blocks.COBBLESTONE, 0, 6, 3, 5, p_74875_3_);

                            for (i = 1; i <= 3; ++i)
                            {
                                this.func_151550_a(p_74875_1_, Blocks.COBBLESTONE, 0, 4, i, 4, p_74875_3_);
                                this.func_151550_a(p_74875_1_, Blocks.COBBLESTONE, 0, 6, i, 4, p_74875_3_);
                                this.func_151550_a(p_74875_1_, Blocks.COBBLESTONE, 0, 4, i, 6, p_74875_3_);
                                this.func_151550_a(p_74875_1_, Blocks.COBBLESTONE, 0, 6, i, 6, p_74875_3_);
                            }

                            this.func_151550_a(p_74875_1_, Blocks.TORCH, 0, 5, 3, 5, p_74875_3_);

                            for (i = 2; i <= 8; ++i)
                            {
                                this.func_151550_a(p_74875_1_, Blocks.PLANKS, 0, 2, 3, i, p_74875_3_);
                                this.func_151550_a(p_74875_1_, Blocks.PLANKS, 0, 3, 3, i, p_74875_3_);

                                if (i <= 3 || i >= 7)
                                {
                                    this.func_151550_a(p_74875_1_, Blocks.PLANKS, 0, 4, 3, i, p_74875_3_);
                                    this.func_151550_a(p_74875_1_, Blocks.PLANKS, 0, 5, 3, i, p_74875_3_);
                                    this.func_151550_a(p_74875_1_, Blocks.PLANKS, 0, 6, 3, i, p_74875_3_);
                                }

                                this.func_151550_a(p_74875_1_, Blocks.PLANKS, 0, 7, 3, i, p_74875_3_);
                                this.func_151550_a(p_74875_1_, Blocks.PLANKS, 0, 8, 3, i, p_74875_3_);
                            }

                            this.func_151550_a(p_74875_1_, Blocks.LADDER, this.func_151555_a(Blocks.LADDER, 4), 9, 1, 3, p_74875_3_);
                            this.func_151550_a(p_74875_1_, Blocks.LADDER, this.func_151555_a(Blocks.LADDER, 4), 9, 2, 3, p_74875_3_);
                            this.func_151550_a(p_74875_1_, Blocks.LADDER, this.func_151555_a(Blocks.LADDER, 4), 9, 3, 3, p_74875_3_);
                            this.func_74879_a(p_74875_1_, p_74875_3_, p_74875_2_, 3, 4, 8, WeightedRandomChestContent.func_92080_a(field_75014_c, new WeightedRandomChestContent[] {Items.ENCHANTED_BOOK.func_92114_b(p_74875_2_)}), 1 + p_74875_2_.nextInt(4));
                    }

                    return true;
                }
            }
        }

    public static class Stairs extends StructureStrongholdPieces.Stronghold
        {
            private boolean field_75024_a;
            private static final String __OBFID = "CL_00000498";

            public Stairs() {}

            public Stairs(int p_i2081_1_, Random p_i2081_2_, int p_i2081_3_, int p_i2081_4_)
            {
                super(p_i2081_1_);
                this.field_75024_a = true;
                this.field_74885_f = p_i2081_2_.nextInt(4);
                this.field_143013_d = StructureStrongholdPieces.Stronghold.Door.OPENING;

                switch (this.field_74885_f)
                {
                    case 0:
                    case 2:
                        this.field_74887_e = new StructureBoundingBox(p_i2081_3_, 64, p_i2081_4_, p_i2081_3_ + 5 - 1, 74, p_i2081_4_ + 5 - 1);
                        break;
                    default:
                        this.field_74887_e = new StructureBoundingBox(p_i2081_3_, 64, p_i2081_4_, p_i2081_3_ + 5 - 1, 74, p_i2081_4_ + 5 - 1);
                }
            }

            public Stairs(int p_i2082_1_, Random p_i2082_2_, StructureBoundingBox p_i2082_3_, int p_i2082_4_)
            {
                super(p_i2082_1_);
                this.field_75024_a = false;
                this.field_74885_f = p_i2082_4_;
                this.field_143013_d = this.func_74988_a(p_i2082_2_);
                this.field_74887_e = p_i2082_3_;
            }

            protected void func_143012_a(NBTTagCompound p_143012_1_)
            {
                super.func_143012_a(p_143012_1_);
                p_143012_1_.func_74757_a("Source", this.field_75024_a);
            }

            protected void func_143011_b(NBTTagCompound p_143011_1_)
            {
                super.func_143011_b(p_143011_1_);
                this.field_75024_a = p_143011_1_.func_74767_n("Source");
            }

            public void func_74861_a(StructureComponent p_74861_1_, List p_74861_2_, Random p_74861_3_)
            {
                if (this.field_75024_a)
                {
                    StructureStrongholdPieces.field_75203_d = StructureStrongholdPieces.Crossing.class;
                }

                this.func_74986_a((StructureStrongholdPieces.Stairs2)p_74861_1_, p_74861_2_, p_74861_3_, 1, 1);
            }

            public static StructureStrongholdPieces.Stairs func_75022_a(List p_75022_0_, Random p_75022_1_, int p_75022_2_, int p_75022_3_, int p_75022_4_, int p_75022_5_, int p_75022_6_)
            {
                StructureBoundingBox structureboundingbox = StructureBoundingBox.func_78889_a(p_75022_2_, p_75022_3_, p_75022_4_, -1, -7, 0, 5, 11, 5, p_75022_5_);
                return func_74991_a(structureboundingbox) && StructureComponent.func_74883_a(p_75022_0_, structureboundingbox) == null ? new StructureStrongholdPieces.Stairs(p_75022_6_, p_75022_1_, structureboundingbox, p_75022_5_) : null;
            }

            public boolean func_74875_a(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_)
            {
                if (this.func_74860_a(p_74875_1_, p_74875_3_))
                {
                    return false;
                }
                else
                {
                    this.func_74882_a(p_74875_1_, p_74875_3_, 0, 0, 0, 4, 10, 4, true, p_74875_2_, StructureStrongholdPieces.field_75204_e);
                    this.func_74990_a(p_74875_1_, p_74875_2_, p_74875_3_, this.field_143013_d, 1, 7, 0);
                    this.func_74990_a(p_74875_1_, p_74875_2_, p_74875_3_, StructureStrongholdPieces.Stronghold.Door.OPENING, 1, 1, 4);
                    this.func_151550_a(p_74875_1_, Blocks.STONEBRICK, 0, 2, 6, 1, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.STONEBRICK, 0, 1, 5, 1, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.STONE_SLAB, 0, 1, 6, 1, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.STONEBRICK, 0, 1, 5, 2, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.STONEBRICK, 0, 1, 4, 3, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.STONE_SLAB, 0, 1, 5, 3, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.STONEBRICK, 0, 2, 4, 3, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.STONEBRICK, 0, 3, 3, 3, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.STONE_SLAB, 0, 3, 4, 3, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.STONEBRICK, 0, 3, 3, 2, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.STONEBRICK, 0, 3, 2, 1, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.STONE_SLAB, 0, 3, 3, 1, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.STONEBRICK, 0, 2, 2, 1, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.STONEBRICK, 0, 1, 1, 1, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.STONE_SLAB, 0, 1, 2, 1, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.STONEBRICK, 0, 1, 1, 2, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.STONE_SLAB, 0, 1, 1, 3, p_74875_3_);
                    return true;
                }
            }
        }

    public static class Stairs2 extends StructureStrongholdPieces.Stairs
        {
            public StructureStrongholdPieces.PieceWeight field_75027_a;
            public StructureStrongholdPieces.PortalRoom field_75025_b;
            public List field_75026_c = new ArrayList();
            private static final String __OBFID = "CL_00000499";

            public Stairs2() {}

            public Stairs2(int p_i2083_1_, Random p_i2083_2_, int p_i2083_3_, int p_i2083_4_)
            {
                super(0, p_i2083_2_, p_i2083_3_, p_i2083_4_);
            }

            public ChunkPosition func_151553_a()
            {
                return this.field_75025_b != null ? this.field_75025_b.func_151553_a() : super.func_151553_a();
            }
        }

    public static class StairsStraight extends StructureStrongholdPieces.Stronghold
        {
            private static final String __OBFID = "CL_00000501";

            public StairsStraight() {}

            public StairsStraight(int p_i2085_1_, Random p_i2085_2_, StructureBoundingBox p_i2085_3_, int p_i2085_4_)
            {
                super(p_i2085_1_);
                this.field_74885_f = p_i2085_4_;
                this.field_143013_d = this.func_74988_a(p_i2085_2_);
                this.field_74887_e = p_i2085_3_;
            }

            public void func_74861_a(StructureComponent p_74861_1_, List p_74861_2_, Random p_74861_3_)
            {
                this.func_74986_a((StructureStrongholdPieces.Stairs2)p_74861_1_, p_74861_2_, p_74861_3_, 1, 1);
            }

            public static StructureStrongholdPieces.StairsStraight func_75028_a(List p_75028_0_, Random p_75028_1_, int p_75028_2_, int p_75028_3_, int p_75028_4_, int p_75028_5_, int p_75028_6_)
            {
                StructureBoundingBox structureboundingbox = StructureBoundingBox.func_78889_a(p_75028_2_, p_75028_3_, p_75028_4_, -1, -7, 0, 5, 11, 8, p_75028_5_);
                return func_74991_a(structureboundingbox) && StructureComponent.func_74883_a(p_75028_0_, structureboundingbox) == null ? new StructureStrongholdPieces.StairsStraight(p_75028_6_, p_75028_1_, structureboundingbox, p_75028_5_) : null;
            }

            public boolean func_74875_a(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_)
            {
                if (this.func_74860_a(p_74875_1_, p_74875_3_))
                {
                    return false;
                }
                else
                {
                    this.func_74882_a(p_74875_1_, p_74875_3_, 0, 0, 0, 4, 10, 7, true, p_74875_2_, StructureStrongholdPieces.field_75204_e);
                    this.func_74990_a(p_74875_1_, p_74875_2_, p_74875_3_, this.field_143013_d, 1, 7, 0);
                    this.func_74990_a(p_74875_1_, p_74875_2_, p_74875_3_, StructureStrongholdPieces.Stronghold.Door.OPENING, 1, 1, 7);
                    int i = this.func_151555_a(Blocks.STONE_STAIRS, 2);

                    for (int j = 0; j < 6; ++j)
                    {
                        this.func_151550_a(p_74875_1_, Blocks.STONE_STAIRS, i, 1, 6 - j, 1 + j, p_74875_3_);
                        this.func_151550_a(p_74875_1_, Blocks.STONE_STAIRS, i, 2, 6 - j, 1 + j, p_74875_3_);
                        this.func_151550_a(p_74875_1_, Blocks.STONE_STAIRS, i, 3, 6 - j, 1 + j, p_74875_3_);

                        if (j < 5)
                        {
                            this.func_151550_a(p_74875_1_, Blocks.STONEBRICK, 0, 1, 5 - j, 1 + j, p_74875_3_);
                            this.func_151550_a(p_74875_1_, Blocks.STONEBRICK, 0, 2, 5 - j, 1 + j, p_74875_3_);
                            this.func_151550_a(p_74875_1_, Blocks.STONEBRICK, 0, 3, 5 - j, 1 + j, p_74875_3_);
                        }
                    }

                    return true;
                }
            }
        }

    static class Stones extends StructureComponent.BlockSelector
        {
            private static final String __OBFID = "CL_00000497";

            private Stones() {}

            public void func_75062_a(Random p_75062_1_, int p_75062_2_, int p_75062_3_, int p_75062_4_, boolean p_75062_5_)
            {
                if (p_75062_5_)
                {
                    this.field_151562_a = Blocks.STONEBRICK;
                    float f = p_75062_1_.nextFloat();

                    if (f < 0.2F)
                    {
                        this.field_75065_b = 2;
                    }
                    else if (f < 0.5F)
                    {
                        this.field_75065_b = 1;
                    }
                    else if (f < 0.55F)
                    {
                        this.field_151562_a = Blocks.MONSTER_EGG;
                        this.field_75065_b = 2;
                    }
                    else
                    {
                        this.field_75065_b = 0;
                    }
                }
                else
                {
                    this.field_151562_a = Blocks.AIR;
                    this.field_75065_b = 0;
                }
            }

            Stones(Object p_i2080_1_)
            {
                this();
            }
        }

    public static class Straight extends StructureStrongholdPieces.Stronghold
        {
            private boolean field_75019_b;
            private boolean field_75020_c;
            private static final String __OBFID = "CL_00000500";

            public Straight() {}

            public Straight(int p_i2084_1_, Random p_i2084_2_, StructureBoundingBox p_i2084_3_, int p_i2084_4_)
            {
                super(p_i2084_1_);
                this.field_74885_f = p_i2084_4_;
                this.field_143013_d = this.func_74988_a(p_i2084_2_);
                this.field_74887_e = p_i2084_3_;
                this.field_75019_b = p_i2084_2_.nextInt(2) == 0;
                this.field_75020_c = p_i2084_2_.nextInt(2) == 0;
            }

            protected void func_143012_a(NBTTagCompound p_143012_1_)
            {
                super.func_143012_a(p_143012_1_);
                p_143012_1_.func_74757_a("Left", this.field_75019_b);
                p_143012_1_.func_74757_a("Right", this.field_75020_c);
            }

            protected void func_143011_b(NBTTagCompound p_143011_1_)
            {
                super.func_143011_b(p_143011_1_);
                this.field_75019_b = p_143011_1_.func_74767_n("Left");
                this.field_75020_c = p_143011_1_.func_74767_n("Right");
            }

            public void func_74861_a(StructureComponent p_74861_1_, List p_74861_2_, Random p_74861_3_)
            {
                this.func_74986_a((StructureStrongholdPieces.Stairs2)p_74861_1_, p_74861_2_, p_74861_3_, 1, 1);

                if (this.field_75019_b)
                {
                    this.func_74989_b((StructureStrongholdPieces.Stairs2)p_74861_1_, p_74861_2_, p_74861_3_, 1, 2);
                }

                if (this.field_75020_c)
                {
                    this.func_74987_c((StructureStrongholdPieces.Stairs2)p_74861_1_, p_74861_2_, p_74861_3_, 1, 2);
                }
            }

            public static StructureStrongholdPieces.Straight func_75018_a(List p_75018_0_, Random p_75018_1_, int p_75018_2_, int p_75018_3_, int p_75018_4_, int p_75018_5_, int p_75018_6_)
            {
                StructureBoundingBox structureboundingbox = StructureBoundingBox.func_78889_a(p_75018_2_, p_75018_3_, p_75018_4_, -1, -1, 0, 5, 5, 7, p_75018_5_);
                return func_74991_a(structureboundingbox) && StructureComponent.func_74883_a(p_75018_0_, structureboundingbox) == null ? new StructureStrongholdPieces.Straight(p_75018_6_, p_75018_1_, structureboundingbox, p_75018_5_) : null;
            }

            public boolean func_74875_a(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_)
            {
                if (this.func_74860_a(p_74875_1_, p_74875_3_))
                {
                    return false;
                }
                else
                {
                    this.func_74882_a(p_74875_1_, p_74875_3_, 0, 0, 0, 4, 4, 6, true, p_74875_2_, StructureStrongholdPieces.field_75204_e);
                    this.func_74990_a(p_74875_1_, p_74875_2_, p_74875_3_, this.field_143013_d, 1, 1, 0);
                    this.func_74990_a(p_74875_1_, p_74875_2_, p_74875_3_, StructureStrongholdPieces.Stronghold.Door.OPENING, 1, 1, 6);
                    this.func_151552_a(p_74875_1_, p_74875_3_, p_74875_2_, 0.1F, 1, 2, 1, Blocks.TORCH, 0);
                    this.func_151552_a(p_74875_1_, p_74875_3_, p_74875_2_, 0.1F, 3, 2, 1, Blocks.TORCH, 0);
                    this.func_151552_a(p_74875_1_, p_74875_3_, p_74875_2_, 0.1F, 1, 2, 5, Blocks.TORCH, 0);
                    this.func_151552_a(p_74875_1_, p_74875_3_, p_74875_2_, 0.1F, 3, 2, 5, Blocks.TORCH, 0);

                    if (this.field_75019_b)
                    {
                        this.func_151549_a(p_74875_1_, p_74875_3_, 0, 1, 2, 0, 3, 4, Blocks.AIR, Blocks.AIR, false);
                    }

                    if (this.field_75020_c)
                    {
                        this.func_151549_a(p_74875_1_, p_74875_3_, 4, 1, 2, 4, 3, 4, Blocks.AIR, Blocks.AIR, false);
                    }

                    return true;
                }
            }
        }

    public abstract static class Stronghold extends StructureComponent
        {
            protected StructureStrongholdPieces.Stronghold.Door field_143013_d;
            private static final String __OBFID = "CL_00000503";

            public Stronghold()
            {
                this.field_143013_d = StructureStrongholdPieces.Stronghold.Door.OPENING;
            }

            protected Stronghold(int p_i2087_1_)
            {
                super(p_i2087_1_);
                this.field_143013_d = StructureStrongholdPieces.Stronghold.Door.OPENING;
            }

            protected void func_143012_a(NBTTagCompound p_143012_1_)
            {
                p_143012_1_.func_74778_a("EntryDoor", this.field_143013_d.name());
            }

            protected void func_143011_b(NBTTagCompound p_143011_1_)
            {
                this.field_143013_d = StructureStrongholdPieces.Stronghold.Door.valueOf(p_143011_1_.func_74779_i("EntryDoor"));
            }

            protected void func_74990_a(World p_74990_1_, Random p_74990_2_, StructureBoundingBox p_74990_3_, StructureStrongholdPieces.Stronghold.Door p_74990_4_, int p_74990_5_, int p_74990_6_, int p_74990_7_)
            {
                switch (StructureStrongholdPieces.SwitchDoor.field_75245_a[p_74990_4_.ordinal()])
                {
                    case 1:
                    default:
                        this.func_151549_a(p_74990_1_, p_74990_3_, p_74990_5_, p_74990_6_, p_74990_7_, p_74990_5_ + 3 - 1, p_74990_6_ + 3 - 1, p_74990_7_, Blocks.AIR, Blocks.AIR, false);
                        break;
                    case 2:
                        this.func_151550_a(p_74990_1_, Blocks.STONEBRICK, 0, p_74990_5_, p_74990_6_, p_74990_7_, p_74990_3_);
                        this.func_151550_a(p_74990_1_, Blocks.STONEBRICK, 0, p_74990_5_, p_74990_6_ + 1, p_74990_7_, p_74990_3_);
                        this.func_151550_a(p_74990_1_, Blocks.STONEBRICK, 0, p_74990_5_, p_74990_6_ + 2, p_74990_7_, p_74990_3_);
                        this.func_151550_a(p_74990_1_, Blocks.STONEBRICK, 0, p_74990_5_ + 1, p_74990_6_ + 2, p_74990_7_, p_74990_3_);
                        this.func_151550_a(p_74990_1_, Blocks.STONEBRICK, 0, p_74990_5_ + 2, p_74990_6_ + 2, p_74990_7_, p_74990_3_);
                        this.func_151550_a(p_74990_1_, Blocks.STONEBRICK, 0, p_74990_5_ + 2, p_74990_6_ + 1, p_74990_7_, p_74990_3_);
                        this.func_151550_a(p_74990_1_, Blocks.STONEBRICK, 0, p_74990_5_ + 2, p_74990_6_, p_74990_7_, p_74990_3_);
                        this.func_151550_a(p_74990_1_, Blocks.WOODEN_DOOR, 0, p_74990_5_ + 1, p_74990_6_, p_74990_7_, p_74990_3_);
                        this.func_151550_a(p_74990_1_, Blocks.WOODEN_DOOR, 8, p_74990_5_ + 1, p_74990_6_ + 1, p_74990_7_, p_74990_3_);
                        break;
                    case 3:
                        this.func_151550_a(p_74990_1_, Blocks.AIR, 0, p_74990_5_ + 1, p_74990_6_, p_74990_7_, p_74990_3_);
                        this.func_151550_a(p_74990_1_, Blocks.AIR, 0, p_74990_5_ + 1, p_74990_6_ + 1, p_74990_7_, p_74990_3_);
                        this.func_151550_a(p_74990_1_, Blocks.IRON_BARS, 0, p_74990_5_, p_74990_6_, p_74990_7_, p_74990_3_);
                        this.func_151550_a(p_74990_1_, Blocks.IRON_BARS, 0, p_74990_5_, p_74990_6_ + 1, p_74990_7_, p_74990_3_);
                        this.func_151550_a(p_74990_1_, Blocks.IRON_BARS, 0, p_74990_5_, p_74990_6_ + 2, p_74990_7_, p_74990_3_);
                        this.func_151550_a(p_74990_1_, Blocks.IRON_BARS, 0, p_74990_5_ + 1, p_74990_6_ + 2, p_74990_7_, p_74990_3_);
                        this.func_151550_a(p_74990_1_, Blocks.IRON_BARS, 0, p_74990_5_ + 2, p_74990_6_ + 2, p_74990_7_, p_74990_3_);
                        this.func_151550_a(p_74990_1_, Blocks.IRON_BARS, 0, p_74990_5_ + 2, p_74990_6_ + 1, p_74990_7_, p_74990_3_);
                        this.func_151550_a(p_74990_1_, Blocks.IRON_BARS, 0, p_74990_5_ + 2, p_74990_6_, p_74990_7_, p_74990_3_);
                        break;
                    case 4:
                        this.func_151550_a(p_74990_1_, Blocks.STONEBRICK, 0, p_74990_5_, p_74990_6_, p_74990_7_, p_74990_3_);
                        this.func_151550_a(p_74990_1_, Blocks.STONEBRICK, 0, p_74990_5_, p_74990_6_ + 1, p_74990_7_, p_74990_3_);
                        this.func_151550_a(p_74990_1_, Blocks.STONEBRICK, 0, p_74990_5_, p_74990_6_ + 2, p_74990_7_, p_74990_3_);
                        this.func_151550_a(p_74990_1_, Blocks.STONEBRICK, 0, p_74990_5_ + 1, p_74990_6_ + 2, p_74990_7_, p_74990_3_);
                        this.func_151550_a(p_74990_1_, Blocks.STONEBRICK, 0, p_74990_5_ + 2, p_74990_6_ + 2, p_74990_7_, p_74990_3_);
                        this.func_151550_a(p_74990_1_, Blocks.STONEBRICK, 0, p_74990_5_ + 2, p_74990_6_ + 1, p_74990_7_, p_74990_3_);
                        this.func_151550_a(p_74990_1_, Blocks.STONEBRICK, 0, p_74990_5_ + 2, p_74990_6_, p_74990_7_, p_74990_3_);
                        this.func_151550_a(p_74990_1_, Blocks.IRON_DOOR, 0, p_74990_5_ + 1, p_74990_6_, p_74990_7_, p_74990_3_);
                        this.func_151550_a(p_74990_1_, Blocks.IRON_DOOR, 8, p_74990_5_ + 1, p_74990_6_ + 1, p_74990_7_, p_74990_3_);
                        this.func_151550_a(p_74990_1_, Blocks.STONE_BUTTON, this.func_151555_a(Blocks.STONE_BUTTON, 4), p_74990_5_ + 2, p_74990_6_ + 1, p_74990_7_ + 1, p_74990_3_);
                        this.func_151550_a(p_74990_1_, Blocks.STONE_BUTTON, this.func_151555_a(Blocks.STONE_BUTTON, 3), p_74990_5_ + 2, p_74990_6_ + 1, p_74990_7_ - 1, p_74990_3_);
                }
            }

            protected StructureStrongholdPieces.Stronghold.Door func_74988_a(Random p_74988_1_)
            {
                int i = p_74988_1_.nextInt(5);

                switch (i)
                {
                    case 0:
                    case 1:
                    default:
                        return StructureStrongholdPieces.Stronghold.Door.OPENING;
                    case 2:
                        return StructureStrongholdPieces.Stronghold.Door.WOOD_DOOR;
                    case 3:
                        return StructureStrongholdPieces.Stronghold.Door.GRATES;
                    case 4:
                        return StructureStrongholdPieces.Stronghold.Door.IRON_DOOR;
                }
            }

            protected StructureComponent func_74986_a(StructureStrongholdPieces.Stairs2 p_74986_1_, List p_74986_2_, Random p_74986_3_, int p_74986_4_, int p_74986_5_)
            {
                switch (this.field_74885_f)
                {
                    case 0:
                        return StructureStrongholdPieces.func_75196_c(p_74986_1_, p_74986_2_, p_74986_3_, this.field_74887_e.field_78897_a + p_74986_4_, this.field_74887_e.field_78895_b + p_74986_5_, this.field_74887_e.field_78892_f + 1, this.field_74885_f, this.func_74877_c());
                    case 1:
                        return StructureStrongholdPieces.func_75196_c(p_74986_1_, p_74986_2_, p_74986_3_, this.field_74887_e.field_78897_a - 1, this.field_74887_e.field_78895_b + p_74986_5_, this.field_74887_e.field_78896_c + p_74986_4_, this.field_74885_f, this.func_74877_c());
                    case 2:
                        return StructureStrongholdPieces.func_75196_c(p_74986_1_, p_74986_2_, p_74986_3_, this.field_74887_e.field_78897_a + p_74986_4_, this.field_74887_e.field_78895_b + p_74986_5_, this.field_74887_e.field_78896_c - 1, this.field_74885_f, this.func_74877_c());
                    case 3:
                        return StructureStrongholdPieces.func_75196_c(p_74986_1_, p_74986_2_, p_74986_3_, this.field_74887_e.field_78893_d + 1, this.field_74887_e.field_78895_b + p_74986_5_, this.field_74887_e.field_78896_c + p_74986_4_, this.field_74885_f, this.func_74877_c());
                    default:
                        return null;
                }
            }

            protected StructureComponent func_74989_b(StructureStrongholdPieces.Stairs2 p_74989_1_, List p_74989_2_, Random p_74989_3_, int p_74989_4_, int p_74989_5_)
            {
                switch (this.field_74885_f)
                {
                    case 0:
                        return StructureStrongholdPieces.func_75196_c(p_74989_1_, p_74989_2_, p_74989_3_, this.field_74887_e.field_78897_a - 1, this.field_74887_e.field_78895_b + p_74989_4_, this.field_74887_e.field_78896_c + p_74989_5_, 1, this.func_74877_c());
                    case 1:
                        return StructureStrongholdPieces.func_75196_c(p_74989_1_, p_74989_2_, p_74989_3_, this.field_74887_e.field_78897_a + p_74989_5_, this.field_74887_e.field_78895_b + p_74989_4_, this.field_74887_e.field_78896_c - 1, 2, this.func_74877_c());
                    case 2:
                        return StructureStrongholdPieces.func_75196_c(p_74989_1_, p_74989_2_, p_74989_3_, this.field_74887_e.field_78897_a - 1, this.field_74887_e.field_78895_b + p_74989_4_, this.field_74887_e.field_78896_c + p_74989_5_, 1, this.func_74877_c());
                    case 3:
                        return StructureStrongholdPieces.func_75196_c(p_74989_1_, p_74989_2_, p_74989_3_, this.field_74887_e.field_78897_a + p_74989_5_, this.field_74887_e.field_78895_b + p_74989_4_, this.field_74887_e.field_78896_c - 1, 2, this.func_74877_c());
                    default:
                        return null;
                }
            }

            protected StructureComponent func_74987_c(StructureStrongholdPieces.Stairs2 p_74987_1_, List p_74987_2_, Random p_74987_3_, int p_74987_4_, int p_74987_5_)
            {
                switch (this.field_74885_f)
                {
                    case 0:
                        return StructureStrongholdPieces.func_75196_c(p_74987_1_, p_74987_2_, p_74987_3_, this.field_74887_e.field_78893_d + 1, this.field_74887_e.field_78895_b + p_74987_4_, this.field_74887_e.field_78896_c + p_74987_5_, 3, this.func_74877_c());
                    case 1:
                        return StructureStrongholdPieces.func_75196_c(p_74987_1_, p_74987_2_, p_74987_3_, this.field_74887_e.field_78897_a + p_74987_5_, this.field_74887_e.field_78895_b + p_74987_4_, this.field_74887_e.field_78892_f + 1, 0, this.func_74877_c());
                    case 2:
                        return StructureStrongholdPieces.func_75196_c(p_74987_1_, p_74987_2_, p_74987_3_, this.field_74887_e.field_78893_d + 1, this.field_74887_e.field_78895_b + p_74987_4_, this.field_74887_e.field_78896_c + p_74987_5_, 3, this.func_74877_c());
                    case 3:
                        return StructureStrongholdPieces.func_75196_c(p_74987_1_, p_74987_2_, p_74987_3_, this.field_74887_e.field_78897_a + p_74987_5_, this.field_74887_e.field_78895_b + p_74987_4_, this.field_74887_e.field_78892_f + 1, 0, this.func_74877_c());
                    default:
                        return null;
                }
            }

            protected static boolean func_74991_a(StructureBoundingBox p_74991_0_)
            {
                return p_74991_0_ != null && p_74991_0_.field_78895_b > 10;
            }

            public static enum Door
            {
                OPENING,
                WOOD_DOOR,
                GRATES,
                IRON_DOOR;

                private static final String __OBFID = "CL_00000504";
            }
        }

    static final class SwitchDoor
        {
            static final int[] field_75245_a = new int[StructureStrongholdPieces.Stronghold.Door.values().length];
            private static final String __OBFID = "CL_00000486";

            static
            {
                try
                {
                    field_75245_a[StructureStrongholdPieces.Stronghold.Door.OPENING.ordinal()] = 1;
                }
                catch (NoSuchFieldError var4)
                {
                    ;
                }

                try
                {
                    field_75245_a[StructureStrongholdPieces.Stronghold.Door.WOOD_DOOR.ordinal()] = 2;
                }
                catch (NoSuchFieldError var3)
                {
                    ;
                }

                try
                {
                    field_75245_a[StructureStrongholdPieces.Stronghold.Door.GRATES.ordinal()] = 3;
                }
                catch (NoSuchFieldError var2)
                {
                    ;
                }

                try
                {
                    field_75245_a[StructureStrongholdPieces.Stronghold.Door.IRON_DOOR.ordinal()] = 4;
                }
                catch (NoSuchFieldError var1)
                {
                    ;
                }
            }
        }
}