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
import net.minecraft.world.World;

public class StructureNetherBridgePieces
{
    private static final StructureNetherBridgePieces.PieceWeight[] field_78742_a = new StructureNetherBridgePieces.PieceWeight[] {new StructureNetherBridgePieces.PieceWeight(StructureNetherBridgePieces.Straight.class, 30, 0, true), new StructureNetherBridgePieces.PieceWeight(StructureNetherBridgePieces.Crossing3.class, 10, 4), new StructureNetherBridgePieces.PieceWeight(StructureNetherBridgePieces.Crossing.class, 10, 4), new StructureNetherBridgePieces.PieceWeight(StructureNetherBridgePieces.Stairs.class, 10, 3), new StructureNetherBridgePieces.PieceWeight(StructureNetherBridgePieces.Throne.class, 5, 2), new StructureNetherBridgePieces.PieceWeight(StructureNetherBridgePieces.Entrance.class, 5, 1)};
    private static final StructureNetherBridgePieces.PieceWeight[] field_78741_b = new StructureNetherBridgePieces.PieceWeight[] {new StructureNetherBridgePieces.PieceWeight(StructureNetherBridgePieces.Corridor5.class, 25, 0, true), new StructureNetherBridgePieces.PieceWeight(StructureNetherBridgePieces.Crossing2.class, 15, 5), new StructureNetherBridgePieces.PieceWeight(StructureNetherBridgePieces.Corridor2.class, 5, 10), new StructureNetherBridgePieces.PieceWeight(StructureNetherBridgePieces.Corridor.class, 5, 10), new StructureNetherBridgePieces.PieceWeight(StructureNetherBridgePieces.Corridor3.class, 10, 3, true), new StructureNetherBridgePieces.PieceWeight(StructureNetherBridgePieces.Corridor4.class, 7, 2), new StructureNetherBridgePieces.PieceWeight(StructureNetherBridgePieces.NetherStalkRoom.class, 5, 2)};
    private static final String __OBFID = "CL_00000453";

    public static void func_143049_a()
    {
        MapGenStructureIO.func_143031_a(StructureNetherBridgePieces.Crossing3.class, "NeBCr");
        MapGenStructureIO.func_143031_a(StructureNetherBridgePieces.End.class, "NeBEF");
        MapGenStructureIO.func_143031_a(StructureNetherBridgePieces.Straight.class, "NeBS");
        MapGenStructureIO.func_143031_a(StructureNetherBridgePieces.Corridor3.class, "NeCCS");
        MapGenStructureIO.func_143031_a(StructureNetherBridgePieces.Corridor4.class, "NeCTB");
        MapGenStructureIO.func_143031_a(StructureNetherBridgePieces.Entrance.class, "NeCE");
        MapGenStructureIO.func_143031_a(StructureNetherBridgePieces.Crossing2.class, "NeSCSC");
        MapGenStructureIO.func_143031_a(StructureNetherBridgePieces.Corridor.class, "NeSCLT");
        MapGenStructureIO.func_143031_a(StructureNetherBridgePieces.Corridor5.class, "NeSC");
        MapGenStructureIO.func_143031_a(StructureNetherBridgePieces.Corridor2.class, "NeSCRT");
        MapGenStructureIO.func_143031_a(StructureNetherBridgePieces.NetherStalkRoom.class, "NeCSR");
        MapGenStructureIO.func_143031_a(StructureNetherBridgePieces.Throne.class, "NeMT");
        MapGenStructureIO.func_143031_a(StructureNetherBridgePieces.Crossing.class, "NeRC");
        MapGenStructureIO.func_143031_a(StructureNetherBridgePieces.Stairs.class, "NeSR");
        MapGenStructureIO.func_143031_a(StructureNetherBridgePieces.Start.class, "NeStart");
    }

    private static StructureNetherBridgePieces.Piece func_78738_b(StructureNetherBridgePieces.PieceWeight p_78738_0_, List p_78738_1_, Random p_78738_2_, int p_78738_3_, int p_78738_4_, int p_78738_5_, int p_78738_6_, int p_78738_7_)
    {
        Class oclass = p_78738_0_.field_78828_a;
        Object object = null;

        if (oclass == StructureNetherBridgePieces.Straight.class)
        {
            object = StructureNetherBridgePieces.Straight.func_74983_a(p_78738_1_, p_78738_2_, p_78738_3_, p_78738_4_, p_78738_5_, p_78738_6_, p_78738_7_);
        }
        else if (oclass == StructureNetherBridgePieces.Crossing3.class)
        {
            object = StructureNetherBridgePieces.Crossing3.func_74966_a(p_78738_1_, p_78738_2_, p_78738_3_, p_78738_4_, p_78738_5_, p_78738_6_, p_78738_7_);
        }
        else if (oclass == StructureNetherBridgePieces.Crossing.class)
        {
            object = StructureNetherBridgePieces.Crossing.func_74974_a(p_78738_1_, p_78738_2_, p_78738_3_, p_78738_4_, p_78738_5_, p_78738_6_, p_78738_7_);
        }
        else if (oclass == StructureNetherBridgePieces.Stairs.class)
        {
            object = StructureNetherBridgePieces.Stairs.func_74973_a(p_78738_1_, p_78738_2_, p_78738_3_, p_78738_4_, p_78738_5_, p_78738_6_, p_78738_7_);
        }
        else if (oclass == StructureNetherBridgePieces.Throne.class)
        {
            object = StructureNetherBridgePieces.Throne.func_74975_a(p_78738_1_, p_78738_2_, p_78738_3_, p_78738_4_, p_78738_5_, p_78738_6_, p_78738_7_);
        }
        else if (oclass == StructureNetherBridgePieces.Entrance.class)
        {
            object = StructureNetherBridgePieces.Entrance.func_74984_a(p_78738_1_, p_78738_2_, p_78738_3_, p_78738_4_, p_78738_5_, p_78738_6_, p_78738_7_);
        }
        else if (oclass == StructureNetherBridgePieces.Corridor5.class)
        {
            object = StructureNetherBridgePieces.Corridor5.func_74981_a(p_78738_1_, p_78738_2_, p_78738_3_, p_78738_4_, p_78738_5_, p_78738_6_, p_78738_7_);
        }
        else if (oclass == StructureNetherBridgePieces.Corridor2.class)
        {
            object = StructureNetherBridgePieces.Corridor2.func_74980_a(p_78738_1_, p_78738_2_, p_78738_3_, p_78738_4_, p_78738_5_, p_78738_6_, p_78738_7_);
        }
        else if (oclass == StructureNetherBridgePieces.Corridor.class)
        {
            object = StructureNetherBridgePieces.Corridor.func_74978_a(p_78738_1_, p_78738_2_, p_78738_3_, p_78738_4_, p_78738_5_, p_78738_6_, p_78738_7_);
        }
        else if (oclass == StructureNetherBridgePieces.Corridor3.class)
        {
            object = StructureNetherBridgePieces.Corridor3.func_74982_a(p_78738_1_, p_78738_2_, p_78738_3_, p_78738_4_, p_78738_5_, p_78738_6_, p_78738_7_);
        }
        else if (oclass == StructureNetherBridgePieces.Corridor4.class)
        {
            object = StructureNetherBridgePieces.Corridor4.func_74985_a(p_78738_1_, p_78738_2_, p_78738_3_, p_78738_4_, p_78738_5_, p_78738_6_, p_78738_7_);
        }
        else if (oclass == StructureNetherBridgePieces.Crossing2.class)
        {
            object = StructureNetherBridgePieces.Crossing2.func_74979_a(p_78738_1_, p_78738_2_, p_78738_3_, p_78738_4_, p_78738_5_, p_78738_6_, p_78738_7_);
        }
        else if (oclass == StructureNetherBridgePieces.NetherStalkRoom.class)
        {
            object = StructureNetherBridgePieces.NetherStalkRoom.func_74977_a(p_78738_1_, p_78738_2_, p_78738_3_, p_78738_4_, p_78738_5_, p_78738_6_, p_78738_7_);
        }

        return (StructureNetherBridgePieces.Piece)object;
    }

    public static class Corridor extends StructureNetherBridgePieces.Piece
        {
            private boolean field_111021_b;
            private static final String __OBFID = "CL_00000461";

            public Corridor() {}

            public Corridor(int p_i2049_1_, Random p_i2049_2_, StructureBoundingBox p_i2049_3_, int p_i2049_4_)
            {
                super(p_i2049_1_);
                this.field_74885_f = p_i2049_4_;
                this.field_74887_e = p_i2049_3_;
                this.field_111021_b = p_i2049_2_.nextInt(3) == 0;
            }

            protected void func_143011_b(NBTTagCompound p_143011_1_)
            {
                super.func_143011_b(p_143011_1_);
                this.field_111021_b = p_143011_1_.func_74767_n("Chest");
            }

            protected void func_143012_a(NBTTagCompound p_143012_1_)
            {
                super.func_143012_a(p_143012_1_);
                p_143012_1_.func_74757_a("Chest", this.field_111021_b);
            }

            public void func_74861_a(StructureComponent p_74861_1_, List p_74861_2_, Random p_74861_3_)
            {
                this.func_74961_b((StructureNetherBridgePieces.Start)p_74861_1_, p_74861_2_, p_74861_3_, 0, 1, true);
            }

            public static StructureNetherBridgePieces.Corridor func_74978_a(List p_74978_0_, Random p_74978_1_, int p_74978_2_, int p_74978_3_, int p_74978_4_, int p_74978_5_, int p_74978_6_)
            {
                StructureBoundingBox structureboundingbox = StructureBoundingBox.func_78889_a(p_74978_2_, p_74978_3_, p_74978_4_, -1, 0, 0, 5, 7, 5, p_74978_5_);
                return func_74964_a(structureboundingbox) && StructureComponent.func_74883_a(p_74978_0_, structureboundingbox) == null ? new StructureNetherBridgePieces.Corridor(p_74978_6_, p_74978_1_, structureboundingbox, p_74978_5_) : null;
            }

            public boolean func_74875_a(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_)
            {
                this.func_151549_a(p_74875_1_, p_74875_3_, 0, 0, 0, 4, 1, 4, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 0, 2, 0, 4, 5, 4, Blocks.AIR, Blocks.AIR, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 4, 2, 0, 4, 5, 4, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 4, 3, 1, 4, 4, 1, Blocks.NETHER_BRICK_FENCE, Blocks.NETHER_BRICK_FENCE, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 4, 3, 3, 4, 4, 3, Blocks.NETHER_BRICK_FENCE, Blocks.NETHER_BRICK_FENCE, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 0, 2, 0, 0, 5, 0, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 0, 2, 4, 3, 5, 4, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 1, 3, 4, 1, 4, 4, Blocks.NETHER_BRICK_FENCE, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 3, 3, 4, 3, 4, 4, Blocks.NETHER_BRICK_FENCE, Blocks.NETHER_BRICK, false);
                int i;
                int j;

                if (this.field_111021_b)
                {
                    i = this.func_74862_a(2);
                    j = this.func_74865_a(3, 3);
                    int k = this.func_74873_b(3, 3);

                    if (p_74875_3_.func_78890_b(j, i, k))
                    {
                        this.field_111021_b = false;
                        this.func_74879_a(p_74875_1_, p_74875_3_, p_74875_2_, 3, 2, 3, field_111019_a, 2 + p_74875_2_.nextInt(4));
                    }
                }

                this.func_151549_a(p_74875_1_, p_74875_3_, 0, 6, 0, 4, 6, 4, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);

                for (i = 0; i <= 4; ++i)
                {
                    for (j = 0; j <= 4; ++j)
                    {
                        this.func_151554_b(p_74875_1_, Blocks.NETHER_BRICK, 0, i, -1, j, p_74875_3_);
                    }
                }

                return true;
            }
        }

    public static class Corridor2 extends StructureNetherBridgePieces.Piece
        {
            private boolean field_111020_b;
            private static final String __OBFID = "CL_00000463";

            public Corridor2() {}

            public Corridor2(int p_i2051_1_, Random p_i2051_2_, StructureBoundingBox p_i2051_3_, int p_i2051_4_)
            {
                super(p_i2051_1_);
                this.field_74885_f = p_i2051_4_;
                this.field_74887_e = p_i2051_3_;
                this.field_111020_b = p_i2051_2_.nextInt(3) == 0;
            }

            protected void func_143011_b(NBTTagCompound p_143011_1_)
            {
                super.func_143011_b(p_143011_1_);
                this.field_111020_b = p_143011_1_.func_74767_n("Chest");
            }

            protected void func_143012_a(NBTTagCompound p_143012_1_)
            {
                super.func_143012_a(p_143012_1_);
                p_143012_1_.func_74757_a("Chest", this.field_111020_b);
            }

            public void func_74861_a(StructureComponent p_74861_1_, List p_74861_2_, Random p_74861_3_)
            {
                this.func_74965_c((StructureNetherBridgePieces.Start)p_74861_1_, p_74861_2_, p_74861_3_, 0, 1, true);
            }

            public static StructureNetherBridgePieces.Corridor2 func_74980_a(List p_74980_0_, Random p_74980_1_, int p_74980_2_, int p_74980_3_, int p_74980_4_, int p_74980_5_, int p_74980_6_)
            {
                StructureBoundingBox structureboundingbox = StructureBoundingBox.func_78889_a(p_74980_2_, p_74980_3_, p_74980_4_, -1, 0, 0, 5, 7, 5, p_74980_5_);
                return func_74964_a(structureboundingbox) && StructureComponent.func_74883_a(p_74980_0_, structureboundingbox) == null ? new StructureNetherBridgePieces.Corridor2(p_74980_6_, p_74980_1_, structureboundingbox, p_74980_5_) : null;
            }

            public boolean func_74875_a(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_)
            {
                this.func_151549_a(p_74875_1_, p_74875_3_, 0, 0, 0, 4, 1, 4, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 0, 2, 0, 4, 5, 4, Blocks.AIR, Blocks.AIR, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 0, 2, 0, 0, 5, 4, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 0, 3, 1, 0, 4, 1, Blocks.NETHER_BRICK_FENCE, Blocks.NETHER_BRICK_FENCE, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 0, 3, 3, 0, 4, 3, Blocks.NETHER_BRICK_FENCE, Blocks.NETHER_BRICK_FENCE, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 4, 2, 0, 4, 5, 0, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 1, 2, 4, 4, 5, 4, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 1, 3, 4, 1, 4, 4, Blocks.NETHER_BRICK_FENCE, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 3, 3, 4, 3, 4, 4, Blocks.NETHER_BRICK_FENCE, Blocks.NETHER_BRICK, false);
                int i;
                int j;

                if (this.field_111020_b)
                {
                    i = this.func_74862_a(2);
                    j = this.func_74865_a(1, 3);
                    int k = this.func_74873_b(1, 3);

                    if (p_74875_3_.func_78890_b(j, i, k))
                    {
                        this.field_111020_b = false;
                        this.func_74879_a(p_74875_1_, p_74875_3_, p_74875_2_, 1, 2, 3, field_111019_a, 2 + p_74875_2_.nextInt(4));
                    }
                }

                this.func_151549_a(p_74875_1_, p_74875_3_, 0, 6, 0, 4, 6, 4, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);

                for (i = 0; i <= 4; ++i)
                {
                    for (j = 0; j <= 4; ++j)
                    {
                        this.func_151554_b(p_74875_1_, Blocks.NETHER_BRICK, 0, i, -1, j, p_74875_3_);
                    }
                }

                return true;
            }
        }

    public static class Corridor3 extends StructureNetherBridgePieces.Piece
        {
            private static final String __OBFID = "CL_00000457";

            public Corridor3() {}

            public Corridor3(int p_i2045_1_, Random p_i2045_2_, StructureBoundingBox p_i2045_3_, int p_i2045_4_)
            {
                super(p_i2045_1_);
                this.field_74885_f = p_i2045_4_;
                this.field_74887_e = p_i2045_3_;
            }

            public void func_74861_a(StructureComponent p_74861_1_, List p_74861_2_, Random p_74861_3_)
            {
                this.func_74963_a((StructureNetherBridgePieces.Start)p_74861_1_, p_74861_2_, p_74861_3_, 1, 0, true);
            }

            public static StructureNetherBridgePieces.Corridor3 func_74982_a(List p_74982_0_, Random p_74982_1_, int p_74982_2_, int p_74982_3_, int p_74982_4_, int p_74982_5_, int p_74982_6_)
            {
                StructureBoundingBox structureboundingbox = StructureBoundingBox.func_78889_a(p_74982_2_, p_74982_3_, p_74982_4_, -1, -7, 0, 5, 14, 10, p_74982_5_);
                return func_74964_a(structureboundingbox) && StructureComponent.func_74883_a(p_74982_0_, structureboundingbox) == null ? new StructureNetherBridgePieces.Corridor3(p_74982_6_, p_74982_1_, structureboundingbox, p_74982_5_) : null;
            }

            public boolean func_74875_a(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_)
            {
                int i = this.func_151555_a(Blocks.NETHER_BRICK_STAIRS, 2);

                for (int j = 0; j <= 9; ++j)
                {
                    int k = Math.max(1, 7 - j);
                    int l = Math.min(Math.max(k + 5, 14 - j), 13);
                    int i1 = j;
                    this.func_151549_a(p_74875_1_, p_74875_3_, 0, 0, j, 4, k, j, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                    this.func_151549_a(p_74875_1_, p_74875_3_, 1, k + 1, j, 3, l - 1, j, Blocks.AIR, Blocks.AIR, false);

                    if (j <= 6)
                    {
                        this.func_151550_a(p_74875_1_, Blocks.NETHER_BRICK_STAIRS, i, 1, k + 1, j, p_74875_3_);
                        this.func_151550_a(p_74875_1_, Blocks.NETHER_BRICK_STAIRS, i, 2, k + 1, j, p_74875_3_);
                        this.func_151550_a(p_74875_1_, Blocks.NETHER_BRICK_STAIRS, i, 3, k + 1, j, p_74875_3_);
                    }

                    this.func_151549_a(p_74875_1_, p_74875_3_, 0, l, j, 4, l, j, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                    this.func_151549_a(p_74875_1_, p_74875_3_, 0, k + 1, j, 0, l - 1, j, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                    this.func_151549_a(p_74875_1_, p_74875_3_, 4, k + 1, j, 4, l - 1, j, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);

                    if ((j & 1) == 0)
                    {
                        this.func_151549_a(p_74875_1_, p_74875_3_, 0, k + 2, j, 0, k + 3, j, Blocks.NETHER_BRICK_FENCE, Blocks.NETHER_BRICK_FENCE, false);
                        this.func_151549_a(p_74875_1_, p_74875_3_, 4, k + 2, j, 4, k + 3, j, Blocks.NETHER_BRICK_FENCE, Blocks.NETHER_BRICK_FENCE, false);
                    }

                    for (int j1 = 0; j1 <= 4; ++j1)
                    {
                        this.func_151554_b(p_74875_1_, Blocks.NETHER_BRICK, 0, j1, -1, i1, p_74875_3_);
                    }
                }

                return true;
            }
        }

    public static class Corridor4 extends StructureNetherBridgePieces.Piece
        {
            private static final String __OBFID = "CL_00000458";

            public Corridor4() {}

            public Corridor4(int p_i2046_1_, Random p_i2046_2_, StructureBoundingBox p_i2046_3_, int p_i2046_4_)
            {
                super(p_i2046_1_);
                this.field_74885_f = p_i2046_4_;
                this.field_74887_e = p_i2046_3_;
            }

            public void func_74861_a(StructureComponent p_74861_1_, List p_74861_2_, Random p_74861_3_)
            {
                byte b0 = 1;

                if (this.field_74885_f == 1 || this.field_74885_f == 2)
                {
                    b0 = 5;
                }

                this.func_74961_b((StructureNetherBridgePieces.Start)p_74861_1_, p_74861_2_, p_74861_3_, 0, b0, p_74861_3_.nextInt(8) > 0);
                this.func_74965_c((StructureNetherBridgePieces.Start)p_74861_1_, p_74861_2_, p_74861_3_, 0, b0, p_74861_3_.nextInt(8) > 0);
            }

            public static StructureNetherBridgePieces.Corridor4 func_74985_a(List p_74985_0_, Random p_74985_1_, int p_74985_2_, int p_74985_3_, int p_74985_4_, int p_74985_5_, int p_74985_6_)
            {
                StructureBoundingBox structureboundingbox = StructureBoundingBox.func_78889_a(p_74985_2_, p_74985_3_, p_74985_4_, -3, 0, 0, 9, 7, 9, p_74985_5_);
                return func_74964_a(structureboundingbox) && StructureComponent.func_74883_a(p_74985_0_, structureboundingbox) == null ? new StructureNetherBridgePieces.Corridor4(p_74985_6_, p_74985_1_, structureboundingbox, p_74985_5_) : null;
            }

            public boolean func_74875_a(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_)
            {
                this.func_151549_a(p_74875_1_, p_74875_3_, 0, 0, 0, 8, 1, 8, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 0, 2, 0, 8, 5, 8, Blocks.AIR, Blocks.AIR, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 0, 6, 0, 8, 6, 5, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 0, 2, 0, 2, 5, 0, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 6, 2, 0, 8, 5, 0, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 1, 3, 0, 1, 4, 0, Blocks.NETHER_BRICK_FENCE, Blocks.NETHER_BRICK_FENCE, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 7, 3, 0, 7, 4, 0, Blocks.NETHER_BRICK_FENCE, Blocks.NETHER_BRICK_FENCE, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 0, 2, 4, 8, 2, 8, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 1, 1, 4, 2, 2, 4, Blocks.AIR, Blocks.AIR, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 6, 1, 4, 7, 2, 4, Blocks.AIR, Blocks.AIR, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 0, 3, 8, 8, 3, 8, Blocks.NETHER_BRICK_FENCE, Blocks.NETHER_BRICK_FENCE, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 0, 3, 6, 0, 3, 7, Blocks.NETHER_BRICK_FENCE, Blocks.NETHER_BRICK_FENCE, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 8, 3, 6, 8, 3, 7, Blocks.NETHER_BRICK_FENCE, Blocks.NETHER_BRICK_FENCE, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 0, 3, 4, 0, 5, 5, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 8, 3, 4, 8, 5, 5, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 1, 3, 5, 2, 5, 5, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 6, 3, 5, 7, 5, 5, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 1, 4, 5, 1, 5, 5, Blocks.NETHER_BRICK_FENCE, Blocks.NETHER_BRICK_FENCE, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 7, 4, 5, 7, 5, 5, Blocks.NETHER_BRICK_FENCE, Blocks.NETHER_BRICK_FENCE, false);

                for (int i = 0; i <= 5; ++i)
                {
                    for (int j = 0; j <= 8; ++j)
                    {
                        this.func_151554_b(p_74875_1_, Blocks.NETHER_BRICK, 0, j, -1, i, p_74875_3_);
                    }
                }

                return true;
            }
        }

    public static class Corridor5 extends StructureNetherBridgePieces.Piece
        {
            private static final String __OBFID = "CL_00000462";

            public Corridor5() {}

            public Corridor5(int p_i2050_1_, Random p_i2050_2_, StructureBoundingBox p_i2050_3_, int p_i2050_4_)
            {
                super(p_i2050_1_);
                this.field_74885_f = p_i2050_4_;
                this.field_74887_e = p_i2050_3_;
            }

            public void func_74861_a(StructureComponent p_74861_1_, List p_74861_2_, Random p_74861_3_)
            {
                this.func_74963_a((StructureNetherBridgePieces.Start)p_74861_1_, p_74861_2_, p_74861_3_, 1, 0, true);
            }

            public static StructureNetherBridgePieces.Corridor5 func_74981_a(List p_74981_0_, Random p_74981_1_, int p_74981_2_, int p_74981_3_, int p_74981_4_, int p_74981_5_, int p_74981_6_)
            {
                StructureBoundingBox structureboundingbox = StructureBoundingBox.func_78889_a(p_74981_2_, p_74981_3_, p_74981_4_, -1, 0, 0, 5, 7, 5, p_74981_5_);
                return func_74964_a(structureboundingbox) && StructureComponent.func_74883_a(p_74981_0_, structureboundingbox) == null ? new StructureNetherBridgePieces.Corridor5(p_74981_6_, p_74981_1_, structureboundingbox, p_74981_5_) : null;
            }

            public boolean func_74875_a(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_)
            {
                this.func_151549_a(p_74875_1_, p_74875_3_, 0, 0, 0, 4, 1, 4, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 0, 2, 0, 4, 5, 4, Blocks.AIR, Blocks.AIR, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 0, 2, 0, 0, 5, 4, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 4, 2, 0, 4, 5, 4, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 0, 3, 1, 0, 4, 1, Blocks.NETHER_BRICK_FENCE, Blocks.NETHER_BRICK_FENCE, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 0, 3, 3, 0, 4, 3, Blocks.NETHER_BRICK_FENCE, Blocks.NETHER_BRICK_FENCE, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 4, 3, 1, 4, 4, 1, Blocks.NETHER_BRICK_FENCE, Blocks.NETHER_BRICK_FENCE, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 4, 3, 3, 4, 4, 3, Blocks.NETHER_BRICK_FENCE, Blocks.NETHER_BRICK_FENCE, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 0, 6, 0, 4, 6, 4, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);

                for (int i = 0; i <= 4; ++i)
                {
                    for (int j = 0; j <= 4; ++j)
                    {
                        this.func_151554_b(p_74875_1_, Blocks.NETHER_BRICK, 0, i, -1, j, p_74875_3_);
                    }
                }

                return true;
            }
        }

    public static class Crossing extends StructureNetherBridgePieces.Piece
        {
            private static final String __OBFID = "CL_00000468";

            public Crossing() {}

            public Crossing(int p_i2057_1_, Random p_i2057_2_, StructureBoundingBox p_i2057_3_, int p_i2057_4_)
            {
                super(p_i2057_1_);
                this.field_74885_f = p_i2057_4_;
                this.field_74887_e = p_i2057_3_;
            }

            public void func_74861_a(StructureComponent p_74861_1_, List p_74861_2_, Random p_74861_3_)
            {
                this.func_74963_a((StructureNetherBridgePieces.Start)p_74861_1_, p_74861_2_, p_74861_3_, 2, 0, false);
                this.func_74961_b((StructureNetherBridgePieces.Start)p_74861_1_, p_74861_2_, p_74861_3_, 0, 2, false);
                this.func_74965_c((StructureNetherBridgePieces.Start)p_74861_1_, p_74861_2_, p_74861_3_, 0, 2, false);
            }

            public static StructureNetherBridgePieces.Crossing func_74974_a(List p_74974_0_, Random p_74974_1_, int p_74974_2_, int p_74974_3_, int p_74974_4_, int p_74974_5_, int p_74974_6_)
            {
                StructureBoundingBox structureboundingbox = StructureBoundingBox.func_78889_a(p_74974_2_, p_74974_3_, p_74974_4_, -2, 0, 0, 7, 9, 7, p_74974_5_);
                return func_74964_a(structureboundingbox) && StructureComponent.func_74883_a(p_74974_0_, structureboundingbox) == null ? new StructureNetherBridgePieces.Crossing(p_74974_6_, p_74974_1_, structureboundingbox, p_74974_5_) : null;
            }

            public boolean func_74875_a(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_)
            {
                this.func_151549_a(p_74875_1_, p_74875_3_, 0, 0, 0, 6, 1, 6, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 0, 2, 0, 6, 7, 6, Blocks.AIR, Blocks.AIR, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 0, 2, 0, 1, 6, 0, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 0, 2, 6, 1, 6, 6, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 5, 2, 0, 6, 6, 0, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 5, 2, 6, 6, 6, 6, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 0, 2, 0, 0, 6, 1, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 0, 2, 5, 0, 6, 6, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 6, 2, 0, 6, 6, 1, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 6, 2, 5, 6, 6, 6, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 2, 6, 0, 4, 6, 0, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 2, 5, 0, 4, 5, 0, Blocks.NETHER_BRICK_FENCE, Blocks.NETHER_BRICK_FENCE, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 2, 6, 6, 4, 6, 6, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 2, 5, 6, 4, 5, 6, Blocks.NETHER_BRICK_FENCE, Blocks.NETHER_BRICK_FENCE, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 0, 6, 2, 0, 6, 4, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 0, 5, 2, 0, 5, 4, Blocks.NETHER_BRICK_FENCE, Blocks.NETHER_BRICK_FENCE, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 6, 6, 2, 6, 6, 4, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 6, 5, 2, 6, 5, 4, Blocks.NETHER_BRICK_FENCE, Blocks.NETHER_BRICK_FENCE, false);

                for (int i = 0; i <= 6; ++i)
                {
                    for (int j = 0; j <= 6; ++j)
                    {
                        this.func_151554_b(p_74875_1_, Blocks.NETHER_BRICK, 0, i, -1, j, p_74875_3_);
                    }
                }

                return true;
            }
        }

    public static class Crossing2 extends StructureNetherBridgePieces.Piece
        {
            private static final String __OBFID = "CL_00000460";

            public Crossing2() {}

            public Crossing2(int p_i2048_1_, Random p_i2048_2_, StructureBoundingBox p_i2048_3_, int p_i2048_4_)
            {
                super(p_i2048_1_);
                this.field_74885_f = p_i2048_4_;
                this.field_74887_e = p_i2048_3_;
            }

            public void func_74861_a(StructureComponent p_74861_1_, List p_74861_2_, Random p_74861_3_)
            {
                this.func_74963_a((StructureNetherBridgePieces.Start)p_74861_1_, p_74861_2_, p_74861_3_, 1, 0, true);
                this.func_74961_b((StructureNetherBridgePieces.Start)p_74861_1_, p_74861_2_, p_74861_3_, 0, 1, true);
                this.func_74965_c((StructureNetherBridgePieces.Start)p_74861_1_, p_74861_2_, p_74861_3_, 0, 1, true);
            }

            public static StructureNetherBridgePieces.Crossing2 func_74979_a(List p_74979_0_, Random p_74979_1_, int p_74979_2_, int p_74979_3_, int p_74979_4_, int p_74979_5_, int p_74979_6_)
            {
                StructureBoundingBox structureboundingbox = StructureBoundingBox.func_78889_a(p_74979_2_, p_74979_3_, p_74979_4_, -1, 0, 0, 5, 7, 5, p_74979_5_);
                return func_74964_a(structureboundingbox) && StructureComponent.func_74883_a(p_74979_0_, structureboundingbox) == null ? new StructureNetherBridgePieces.Crossing2(p_74979_6_, p_74979_1_, structureboundingbox, p_74979_5_) : null;
            }

            public boolean func_74875_a(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_)
            {
                this.func_151549_a(p_74875_1_, p_74875_3_, 0, 0, 0, 4, 1, 4, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 0, 2, 0, 4, 5, 4, Blocks.AIR, Blocks.AIR, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 0, 2, 0, 0, 5, 0, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 4, 2, 0, 4, 5, 0, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 0, 2, 4, 0, 5, 4, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 4, 2, 4, 4, 5, 4, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 0, 6, 0, 4, 6, 4, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);

                for (int i = 0; i <= 4; ++i)
                {
                    for (int j = 0; j <= 4; ++j)
                    {
                        this.func_151554_b(p_74875_1_, Blocks.NETHER_BRICK, 0, i, -1, j, p_74875_3_);
                    }
                }

                return true;
            }
        }

    public static class Crossing3 extends StructureNetherBridgePieces.Piece
        {
            private static final String __OBFID = "CL_00000454";

            public Crossing3() {}

            public Crossing3(int p_i2041_1_, Random p_i2041_2_, StructureBoundingBox p_i2041_3_, int p_i2041_4_)
            {
                super(p_i2041_1_);
                this.field_74885_f = p_i2041_4_;
                this.field_74887_e = p_i2041_3_;
            }

            protected Crossing3(Random p_i2042_1_, int p_i2042_2_, int p_i2042_3_)
            {
                super(0);
                this.field_74885_f = p_i2042_1_.nextInt(4);

                switch (this.field_74885_f)
                {
                    case 0:
                    case 2:
                        this.field_74887_e = new StructureBoundingBox(p_i2042_2_, 64, p_i2042_3_, p_i2042_2_ + 19 - 1, 73, p_i2042_3_ + 19 - 1);
                        break;
                    default:
                        this.field_74887_e = new StructureBoundingBox(p_i2042_2_, 64, p_i2042_3_, p_i2042_2_ + 19 - 1, 73, p_i2042_3_ + 19 - 1);
                }
            }

            public void func_74861_a(StructureComponent p_74861_1_, List p_74861_2_, Random p_74861_3_)
            {
                this.func_74963_a((StructureNetherBridgePieces.Start)p_74861_1_, p_74861_2_, p_74861_3_, 8, 3, false);
                this.func_74961_b((StructureNetherBridgePieces.Start)p_74861_1_, p_74861_2_, p_74861_3_, 3, 8, false);
                this.func_74965_c((StructureNetherBridgePieces.Start)p_74861_1_, p_74861_2_, p_74861_3_, 3, 8, false);
            }

            public static StructureNetherBridgePieces.Crossing3 func_74966_a(List p_74966_0_, Random p_74966_1_, int p_74966_2_, int p_74966_3_, int p_74966_4_, int p_74966_5_, int p_74966_6_)
            {
                StructureBoundingBox structureboundingbox = StructureBoundingBox.func_78889_a(p_74966_2_, p_74966_3_, p_74966_4_, -8, -3, 0, 19, 10, 19, p_74966_5_);
                return func_74964_a(structureboundingbox) && StructureComponent.func_74883_a(p_74966_0_, structureboundingbox) == null ? new StructureNetherBridgePieces.Crossing3(p_74966_6_, p_74966_1_, structureboundingbox, p_74966_5_) : null;
            }

            public boolean func_74875_a(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_)
            {
                this.func_151549_a(p_74875_1_, p_74875_3_, 7, 3, 0, 11, 4, 18, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 0, 3, 7, 18, 4, 11, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 8, 5, 0, 10, 7, 18, Blocks.AIR, Blocks.AIR, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 0, 5, 8, 18, 7, 10, Blocks.AIR, Blocks.AIR, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 7, 5, 0, 7, 5, 7, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 7, 5, 11, 7, 5, 18, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 11, 5, 0, 11, 5, 7, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 11, 5, 11, 11, 5, 18, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 0, 5, 7, 7, 5, 7, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 11, 5, 7, 18, 5, 7, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 0, 5, 11, 7, 5, 11, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 11, 5, 11, 18, 5, 11, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 7, 2, 0, 11, 2, 5, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 7, 2, 13, 11, 2, 18, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 7, 0, 0, 11, 1, 3, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 7, 0, 15, 11, 1, 18, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                int i;
                int j;

                for (i = 7; i <= 11; ++i)
                {
                    for (j = 0; j <= 2; ++j)
                    {
                        this.func_151554_b(p_74875_1_, Blocks.NETHER_BRICK, 0, i, -1, j, p_74875_3_);
                        this.func_151554_b(p_74875_1_, Blocks.NETHER_BRICK, 0, i, -1, 18 - j, p_74875_3_);
                    }
                }

                this.func_151549_a(p_74875_1_, p_74875_3_, 0, 2, 7, 5, 2, 11, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 13, 2, 7, 18, 2, 11, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 0, 0, 7, 3, 1, 11, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 15, 0, 7, 18, 1, 11, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);

                for (i = 0; i <= 2; ++i)
                {
                    for (j = 7; j <= 11; ++j)
                    {
                        this.func_151554_b(p_74875_1_, Blocks.NETHER_BRICK, 0, i, -1, j, p_74875_3_);
                        this.func_151554_b(p_74875_1_, Blocks.NETHER_BRICK, 0, 18 - i, -1, j, p_74875_3_);
                    }
                }

                return true;
            }
        }

    public static class End extends StructureNetherBridgePieces.Piece
        {
            private int field_74972_a;
            private static final String __OBFID = "CL_00000455";

            public End() {}

            public End(int p_i2043_1_, Random p_i2043_2_, StructureBoundingBox p_i2043_3_, int p_i2043_4_)
            {
                super(p_i2043_1_);
                this.field_74885_f = p_i2043_4_;
                this.field_74887_e = p_i2043_3_;
                this.field_74972_a = p_i2043_2_.nextInt();
            }

            public static StructureNetherBridgePieces.End func_74971_a(List p_74971_0_, Random p_74971_1_, int p_74971_2_, int p_74971_3_, int p_74971_4_, int p_74971_5_, int p_74971_6_)
            {
                StructureBoundingBox structureboundingbox = StructureBoundingBox.func_78889_a(p_74971_2_, p_74971_3_, p_74971_4_, -1, -3, 0, 5, 10, 8, p_74971_5_);
                return func_74964_a(structureboundingbox) && StructureComponent.func_74883_a(p_74971_0_, structureboundingbox) == null ? new StructureNetherBridgePieces.End(p_74971_6_, p_74971_1_, structureboundingbox, p_74971_5_) : null;
            }

            protected void func_143011_b(NBTTagCompound p_143011_1_)
            {
                super.func_143011_b(p_143011_1_);
                this.field_74972_a = p_143011_1_.func_74762_e("Seed");
            }

            protected void func_143012_a(NBTTagCompound p_143012_1_)
            {
                super.func_143012_a(p_143012_1_);
                p_143012_1_.func_74768_a("Seed", this.field_74972_a);
            }

            public boolean func_74875_a(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_)
            {
                Random random1 = new Random((long)this.field_74972_a);
                int i;
                int j;
                int k;

                for (i = 0; i <= 4; ++i)
                {
                    for (j = 3; j <= 4; ++j)
                    {
                        k = random1.nextInt(8);
                        this.func_151549_a(p_74875_1_, p_74875_3_, i, j, 0, i, j, k, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                    }
                }

                i = random1.nextInt(8);
                this.func_151549_a(p_74875_1_, p_74875_3_, 0, 5, 0, 0, 5, i, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                i = random1.nextInt(8);
                this.func_151549_a(p_74875_1_, p_74875_3_, 4, 5, 0, 4, 5, i, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);

                for (i = 0; i <= 4; ++i)
                {
                    j = random1.nextInt(5);
                    this.func_151549_a(p_74875_1_, p_74875_3_, i, 2, 0, i, 2, j, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                }

                for (i = 0; i <= 4; ++i)
                {
                    for (j = 0; j <= 1; ++j)
                    {
                        k = random1.nextInt(3);
                        this.func_151549_a(p_74875_1_, p_74875_3_, i, j, 0, i, j, k, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                    }
                }

                return true;
            }
        }

    public static class Entrance extends StructureNetherBridgePieces.Piece
        {
            private static final String __OBFID = "CL_00000459";

            public Entrance() {}

            public Entrance(int p_i2047_1_, Random p_i2047_2_, StructureBoundingBox p_i2047_3_, int p_i2047_4_)
            {
                super(p_i2047_1_);
                this.field_74885_f = p_i2047_4_;
                this.field_74887_e = p_i2047_3_;
            }

            public void func_74861_a(StructureComponent p_74861_1_, List p_74861_2_, Random p_74861_3_)
            {
                this.func_74963_a((StructureNetherBridgePieces.Start)p_74861_1_, p_74861_2_, p_74861_3_, 5, 3, true);
            }

            public static StructureNetherBridgePieces.Entrance func_74984_a(List p_74984_0_, Random p_74984_1_, int p_74984_2_, int p_74984_3_, int p_74984_4_, int p_74984_5_, int p_74984_6_)
            {
                StructureBoundingBox structureboundingbox = StructureBoundingBox.func_78889_a(p_74984_2_, p_74984_3_, p_74984_4_, -5, -3, 0, 13, 14, 13, p_74984_5_);
                return func_74964_a(structureboundingbox) && StructureComponent.func_74883_a(p_74984_0_, structureboundingbox) == null ? new StructureNetherBridgePieces.Entrance(p_74984_6_, p_74984_1_, structureboundingbox, p_74984_5_) : null;
            }

            public boolean func_74875_a(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_)
            {
                this.func_151549_a(p_74875_1_, p_74875_3_, 0, 3, 0, 12, 4, 12, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 0, 5, 0, 12, 13, 12, Blocks.AIR, Blocks.AIR, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 0, 5, 0, 1, 12, 12, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 11, 5, 0, 12, 12, 12, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 2, 5, 11, 4, 12, 12, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 8, 5, 11, 10, 12, 12, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 5, 9, 11, 7, 12, 12, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 2, 5, 0, 4, 12, 1, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 8, 5, 0, 10, 12, 1, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 5, 9, 0, 7, 12, 1, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 2, 11, 2, 10, 12, 10, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 5, 8, 0, 7, 8, 0, Blocks.NETHER_BRICK_FENCE, Blocks.NETHER_BRICK_FENCE, false);
                int i;

                for (i = 1; i <= 11; i += 2)
                {
                    this.func_151549_a(p_74875_1_, p_74875_3_, i, 10, 0, i, 11, 0, Blocks.NETHER_BRICK_FENCE, Blocks.NETHER_BRICK_FENCE, false);
                    this.func_151549_a(p_74875_1_, p_74875_3_, i, 10, 12, i, 11, 12, Blocks.NETHER_BRICK_FENCE, Blocks.NETHER_BRICK_FENCE, false);
                    this.func_151549_a(p_74875_1_, p_74875_3_, 0, 10, i, 0, 11, i, Blocks.NETHER_BRICK_FENCE, Blocks.NETHER_BRICK_FENCE, false);
                    this.func_151549_a(p_74875_1_, p_74875_3_, 12, 10, i, 12, 11, i, Blocks.NETHER_BRICK_FENCE, Blocks.NETHER_BRICK_FENCE, false);
                    this.func_151550_a(p_74875_1_, Blocks.NETHER_BRICK, 0, i, 13, 0, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.NETHER_BRICK, 0, i, 13, 12, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.NETHER_BRICK, 0, 0, 13, i, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.NETHER_BRICK, 0, 12, 13, i, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.NETHER_BRICK_FENCE, 0, i + 1, 13, 0, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.NETHER_BRICK_FENCE, 0, i + 1, 13, 12, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.NETHER_BRICK_FENCE, 0, 0, 13, i + 1, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.NETHER_BRICK_FENCE, 0, 12, 13, i + 1, p_74875_3_);
                }

                this.func_151550_a(p_74875_1_, Blocks.NETHER_BRICK_FENCE, 0, 0, 13, 0, p_74875_3_);
                this.func_151550_a(p_74875_1_, Blocks.NETHER_BRICK_FENCE, 0, 0, 13, 12, p_74875_3_);
                this.func_151550_a(p_74875_1_, Blocks.NETHER_BRICK_FENCE, 0, 0, 13, 0, p_74875_3_);
                this.func_151550_a(p_74875_1_, Blocks.NETHER_BRICK_FENCE, 0, 12, 13, 0, p_74875_3_);

                for (i = 3; i <= 9; i += 2)
                {
                    this.func_151549_a(p_74875_1_, p_74875_3_, 1, 7, i, 1, 8, i, Blocks.NETHER_BRICK_FENCE, Blocks.NETHER_BRICK_FENCE, false);
                    this.func_151549_a(p_74875_1_, p_74875_3_, 11, 7, i, 11, 8, i, Blocks.NETHER_BRICK_FENCE, Blocks.NETHER_BRICK_FENCE, false);
                }

                this.func_151549_a(p_74875_1_, p_74875_3_, 4, 2, 0, 8, 2, 12, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 0, 2, 4, 12, 2, 8, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 4, 0, 0, 8, 1, 3, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 4, 0, 9, 8, 1, 12, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 0, 0, 4, 3, 1, 8, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 9, 0, 4, 12, 1, 8, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                int j;

                for (i = 4; i <= 8; ++i)
                {
                    for (j = 0; j <= 2; ++j)
                    {
                        this.func_151554_b(p_74875_1_, Blocks.NETHER_BRICK, 0, i, -1, j, p_74875_3_);
                        this.func_151554_b(p_74875_1_, Blocks.NETHER_BRICK, 0, i, -1, 12 - j, p_74875_3_);
                    }
                }

                for (i = 0; i <= 2; ++i)
                {
                    for (j = 4; j <= 8; ++j)
                    {
                        this.func_151554_b(p_74875_1_, Blocks.NETHER_BRICK, 0, i, -1, j, p_74875_3_);
                        this.func_151554_b(p_74875_1_, Blocks.NETHER_BRICK, 0, 12 - i, -1, j, p_74875_3_);
                    }
                }

                this.func_151549_a(p_74875_1_, p_74875_3_, 5, 5, 5, 7, 5, 7, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 6, 1, 6, 6, 4, 6, Blocks.AIR, Blocks.AIR, false);
                this.func_151550_a(p_74875_1_, Blocks.NETHER_BRICK, 0, 6, 0, 6, p_74875_3_);
                this.func_151550_a(p_74875_1_, Blocks.FLOWING_LAVA, 0, 6, 5, 6, p_74875_3_);
                i = this.func_74865_a(6, 6);
                j = this.func_74862_a(5);
                int k = this.func_74873_b(6, 6);

                if (p_74875_3_.func_78890_b(i, j, k))
                {
                    p_74875_1_.field_72999_e = true;
                    Blocks.FLOWING_LAVA.func_149674_a(p_74875_1_, i, j, k, p_74875_2_);
                    p_74875_1_.field_72999_e = false;
                }

                return true;
            }
        }

    public static class NetherStalkRoom extends StructureNetherBridgePieces.Piece
        {
            private static final String __OBFID = "CL_00000464";

            public NetherStalkRoom() {}

            public NetherStalkRoom(int p_i2052_1_, Random p_i2052_2_, StructureBoundingBox p_i2052_3_, int p_i2052_4_)
            {
                super(p_i2052_1_);
                this.field_74885_f = p_i2052_4_;
                this.field_74887_e = p_i2052_3_;
            }

            public void func_74861_a(StructureComponent p_74861_1_, List p_74861_2_, Random p_74861_3_)
            {
                this.func_74963_a((StructureNetherBridgePieces.Start)p_74861_1_, p_74861_2_, p_74861_3_, 5, 3, true);
                this.func_74963_a((StructureNetherBridgePieces.Start)p_74861_1_, p_74861_2_, p_74861_3_, 5, 11, true);
            }

            public static StructureNetherBridgePieces.NetherStalkRoom func_74977_a(List p_74977_0_, Random p_74977_1_, int p_74977_2_, int p_74977_3_, int p_74977_4_, int p_74977_5_, int p_74977_6_)
            {
                StructureBoundingBox structureboundingbox = StructureBoundingBox.func_78889_a(p_74977_2_, p_74977_3_, p_74977_4_, -5, -3, 0, 13, 14, 13, p_74977_5_);
                return func_74964_a(structureboundingbox) && StructureComponent.func_74883_a(p_74977_0_, structureboundingbox) == null ? new StructureNetherBridgePieces.NetherStalkRoom(p_74977_6_, p_74977_1_, structureboundingbox, p_74977_5_) : null;
            }

            public boolean func_74875_a(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_)
            {
                this.func_151549_a(p_74875_1_, p_74875_3_, 0, 3, 0, 12, 4, 12, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 0, 5, 0, 12, 13, 12, Blocks.AIR, Blocks.AIR, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 0, 5, 0, 1, 12, 12, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 11, 5, 0, 12, 12, 12, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 2, 5, 11, 4, 12, 12, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 8, 5, 11, 10, 12, 12, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 5, 9, 11, 7, 12, 12, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 2, 5, 0, 4, 12, 1, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 8, 5, 0, 10, 12, 1, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 5, 9, 0, 7, 12, 1, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 2, 11, 2, 10, 12, 10, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                int i;

                for (i = 1; i <= 11; i += 2)
                {
                    this.func_151549_a(p_74875_1_, p_74875_3_, i, 10, 0, i, 11, 0, Blocks.NETHER_BRICK_FENCE, Blocks.NETHER_BRICK_FENCE, false);
                    this.func_151549_a(p_74875_1_, p_74875_3_, i, 10, 12, i, 11, 12, Blocks.NETHER_BRICK_FENCE, Blocks.NETHER_BRICK_FENCE, false);
                    this.func_151549_a(p_74875_1_, p_74875_3_, 0, 10, i, 0, 11, i, Blocks.NETHER_BRICK_FENCE, Blocks.NETHER_BRICK_FENCE, false);
                    this.func_151549_a(p_74875_1_, p_74875_3_, 12, 10, i, 12, 11, i, Blocks.NETHER_BRICK_FENCE, Blocks.NETHER_BRICK_FENCE, false);
                    this.func_151550_a(p_74875_1_, Blocks.NETHER_BRICK, 0, i, 13, 0, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.NETHER_BRICK, 0, i, 13, 12, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.NETHER_BRICK, 0, 0, 13, i, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.NETHER_BRICK, 0, 12, 13, i, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.NETHER_BRICK_FENCE, 0, i + 1, 13, 0, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.NETHER_BRICK_FENCE, 0, i + 1, 13, 12, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.NETHER_BRICK_FENCE, 0, 0, 13, i + 1, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.NETHER_BRICK_FENCE, 0, 12, 13, i + 1, p_74875_3_);
                }

                this.func_151550_a(p_74875_1_, Blocks.NETHER_BRICK_FENCE, 0, 0, 13, 0, p_74875_3_);
                this.func_151550_a(p_74875_1_, Blocks.NETHER_BRICK_FENCE, 0, 0, 13, 12, p_74875_3_);
                this.func_151550_a(p_74875_1_, Blocks.NETHER_BRICK_FENCE, 0, 0, 13, 0, p_74875_3_);
                this.func_151550_a(p_74875_1_, Blocks.NETHER_BRICK_FENCE, 0, 12, 13, 0, p_74875_3_);

                for (i = 3; i <= 9; i += 2)
                {
                    this.func_151549_a(p_74875_1_, p_74875_3_, 1, 7, i, 1, 8, i, Blocks.NETHER_BRICK_FENCE, Blocks.NETHER_BRICK_FENCE, false);
                    this.func_151549_a(p_74875_1_, p_74875_3_, 11, 7, i, 11, 8, i, Blocks.NETHER_BRICK_FENCE, Blocks.NETHER_BRICK_FENCE, false);
                }

                i = this.func_151555_a(Blocks.NETHER_BRICK_STAIRS, 3);
                int j;
                int k;
                int l;

                for (j = 0; j <= 6; ++j)
                {
                    k = j + 4;

                    for (l = 5; l <= 7; ++l)
                    {
                        this.func_151550_a(p_74875_1_, Blocks.NETHER_BRICK_STAIRS, i, l, 5 + j, k, p_74875_3_);
                    }

                    if (k >= 5 && k <= 8)
                    {
                        this.func_151549_a(p_74875_1_, p_74875_3_, 5, 5, k, 7, j + 4, k, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                    }
                    else if (k >= 9 && k <= 10)
                    {
                        this.func_151549_a(p_74875_1_, p_74875_3_, 5, 8, k, 7, j + 4, k, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                    }

                    if (j >= 1)
                    {
                        this.func_151549_a(p_74875_1_, p_74875_3_, 5, 6 + j, k, 7, 9 + j, k, Blocks.AIR, Blocks.AIR, false);
                    }
                }

                for (j = 5; j <= 7; ++j)
                {
                    this.func_151550_a(p_74875_1_, Blocks.NETHER_BRICK_STAIRS, i, j, 12, 11, p_74875_3_);
                }

                this.func_151549_a(p_74875_1_, p_74875_3_, 5, 6, 7, 5, 7, 7, Blocks.NETHER_BRICK_FENCE, Blocks.NETHER_BRICK_FENCE, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 7, 6, 7, 7, 7, 7, Blocks.NETHER_BRICK_FENCE, Blocks.NETHER_BRICK_FENCE, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 5, 13, 12, 7, 13, 12, Blocks.AIR, Blocks.AIR, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 2, 5, 2, 3, 5, 3, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 2, 5, 9, 3, 5, 10, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 2, 5, 4, 2, 5, 8, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 9, 5, 2, 10, 5, 3, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 9, 5, 9, 10, 5, 10, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 10, 5, 4, 10, 5, 8, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                j = this.func_151555_a(Blocks.NETHER_BRICK_STAIRS, 0);
                k = this.func_151555_a(Blocks.NETHER_BRICK_STAIRS, 1);
                this.func_151550_a(p_74875_1_, Blocks.NETHER_BRICK_STAIRS, k, 4, 5, 2, p_74875_3_);
                this.func_151550_a(p_74875_1_, Blocks.NETHER_BRICK_STAIRS, k, 4, 5, 3, p_74875_3_);
                this.func_151550_a(p_74875_1_, Blocks.NETHER_BRICK_STAIRS, k, 4, 5, 9, p_74875_3_);
                this.func_151550_a(p_74875_1_, Blocks.NETHER_BRICK_STAIRS, k, 4, 5, 10, p_74875_3_);
                this.func_151550_a(p_74875_1_, Blocks.NETHER_BRICK_STAIRS, j, 8, 5, 2, p_74875_3_);
                this.func_151550_a(p_74875_1_, Blocks.NETHER_BRICK_STAIRS, j, 8, 5, 3, p_74875_3_);
                this.func_151550_a(p_74875_1_, Blocks.NETHER_BRICK_STAIRS, j, 8, 5, 9, p_74875_3_);
                this.func_151550_a(p_74875_1_, Blocks.NETHER_BRICK_STAIRS, j, 8, 5, 10, p_74875_3_);
                this.func_151549_a(p_74875_1_, p_74875_3_, 3, 4, 4, 4, 4, 8, Blocks.SOUL_SAND, Blocks.SOUL_SAND, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 8, 4, 4, 9, 4, 8, Blocks.SOUL_SAND, Blocks.SOUL_SAND, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 3, 5, 4, 4, 5, 8, Blocks.NETHER_WART, Blocks.NETHER_WART, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 8, 5, 4, 9, 5, 8, Blocks.NETHER_WART, Blocks.NETHER_WART, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 4, 2, 0, 8, 2, 12, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 0, 2, 4, 12, 2, 8, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 4, 0, 0, 8, 1, 3, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 4, 0, 9, 8, 1, 12, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 0, 0, 4, 3, 1, 8, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 9, 0, 4, 12, 1, 8, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                int i1;

                for (l = 4; l <= 8; ++l)
                {
                    for (i1 = 0; i1 <= 2; ++i1)
                    {
                        this.func_151554_b(p_74875_1_, Blocks.NETHER_BRICK, 0, l, -1, i1, p_74875_3_);
                        this.func_151554_b(p_74875_1_, Blocks.NETHER_BRICK, 0, l, -1, 12 - i1, p_74875_3_);
                    }
                }

                for (l = 0; l <= 2; ++l)
                {
                    for (i1 = 4; i1 <= 8; ++i1)
                    {
                        this.func_151554_b(p_74875_1_, Blocks.NETHER_BRICK, 0, l, -1, i1, p_74875_3_);
                        this.func_151554_b(p_74875_1_, Blocks.NETHER_BRICK, 0, 12 - l, -1, i1, p_74875_3_);
                    }
                }

                return true;
            }
        }

    abstract static class Piece extends StructureComponent
        {
            protected static final WeightedRandomChestContent[] field_111019_a = new WeightedRandomChestContent[] {new WeightedRandomChestContent(Items.DIAMOND, 0, 1, 3, 5), new WeightedRandomChestContent(Items.IRON_INGOT, 0, 1, 5, 5), new WeightedRandomChestContent(Items.GOLD_INGOT, 0, 1, 3, 15), new WeightedRandomChestContent(Items.GOLDEN_SWORD, 0, 1, 1, 5), new WeightedRandomChestContent(Items.GOLDEN_CHESTPLATE, 0, 1, 1, 5), new WeightedRandomChestContent(Items.FLINT_AND_STEEL, 0, 1, 1, 5), new WeightedRandomChestContent(Items.NETHER_WART, 0, 3, 7, 5), new WeightedRandomChestContent(Items.SADDLE, 0, 1, 1, 10), new WeightedRandomChestContent(Items.GOLDEN_HORSE_ARMOR, 0, 1, 1, 8), new WeightedRandomChestContent(Items.IRON_HORSE_ARMOR, 0, 1, 1, 5), new WeightedRandomChestContent(Items.DIAMOND_HORSE_ARMOR, 0, 1, 1, 3)};
            private static final String __OBFID = "CL_00000466";

            public Piece() {}

            protected Piece(int p_i2054_1_)
            {
                super(p_i2054_1_);
            }

            protected void func_143011_b(NBTTagCompound p_143011_1_) {}

            protected void func_143012_a(NBTTagCompound p_143012_1_) {}

            private int func_74960_a(List p_74960_1_)
            {
                boolean flag = false;
                int i = 0;
                StructureNetherBridgePieces.PieceWeight pieceweight;

                for (Iterator iterator = p_74960_1_.iterator(); iterator.hasNext(); i += pieceweight.field_78826_b)
                {
                    pieceweight = (StructureNetherBridgePieces.PieceWeight)iterator.next();

                    if (pieceweight.field_78824_d > 0 && pieceweight.field_78827_c < pieceweight.field_78824_d)
                    {
                        flag = true;
                    }
                }

                return flag ? i : -1;
            }

            private StructureNetherBridgePieces.Piece func_74959_a(StructureNetherBridgePieces.Start p_74959_1_, List p_74959_2_, List p_74959_3_, Random p_74959_4_, int p_74959_5_, int p_74959_6_, int p_74959_7_, int p_74959_8_, int p_74959_9_)
            {
                int j1 = this.func_74960_a(p_74959_2_);
                boolean flag = j1 > 0 && p_74959_9_ <= 30;
                int k1 = 0;

                while (k1 < 5 && flag)
                {
                    ++k1;
                    int l1 = p_74959_4_.nextInt(j1);
                    Iterator iterator = p_74959_2_.iterator();

                    while (iterator.hasNext())
                    {
                        StructureNetherBridgePieces.PieceWeight pieceweight = (StructureNetherBridgePieces.PieceWeight)iterator.next();
                        l1 -= pieceweight.field_78826_b;

                        if (l1 < 0)
                        {
                            if (!pieceweight.func_78822_a(p_74959_9_) || pieceweight == p_74959_1_.field_74970_a && !pieceweight.field_78825_e)
                            {
                                break;
                            }

                            StructureNetherBridgePieces.Piece piece = StructureNetherBridgePieces.func_78738_b(pieceweight, p_74959_3_, p_74959_4_, p_74959_5_, p_74959_6_, p_74959_7_, p_74959_8_, p_74959_9_);

                            if (piece != null)
                            {
                                ++pieceweight.field_78827_c;
                                p_74959_1_.field_74970_a = pieceweight;

                                if (!pieceweight.func_78823_a())
                                {
                                    p_74959_2_.remove(pieceweight);
                                }

                                return piece;
                            }
                        }
                    }
                }

                return StructureNetherBridgePieces.End.func_74971_a(p_74959_3_, p_74959_4_, p_74959_5_, p_74959_6_, p_74959_7_, p_74959_8_, p_74959_9_);
            }

            private StructureComponent func_74962_a(StructureNetherBridgePieces.Start p_74962_1_, List p_74962_2_, Random p_74962_3_, int p_74962_4_, int p_74962_5_, int p_74962_6_, int p_74962_7_, int p_74962_8_, boolean p_74962_9_)
            {
                if (Math.abs(p_74962_4_ - p_74962_1_.func_74874_b().field_78897_a) <= 112 && Math.abs(p_74962_6_ - p_74962_1_.func_74874_b().field_78896_c) <= 112)
                {
                    List list1 = p_74962_1_.field_74968_b;

                    if (p_74962_9_)
                    {
                        list1 = p_74962_1_.field_74969_c;
                    }

                    StructureNetherBridgePieces.Piece piece = this.func_74959_a(p_74962_1_, list1, p_74962_2_, p_74962_3_, p_74962_4_, p_74962_5_, p_74962_6_, p_74962_7_, p_74962_8_ + 1);

                    if (piece != null)
                    {
                        p_74962_2_.add(piece);
                        p_74962_1_.field_74967_d.add(piece);
                    }

                    return piece;
                }
                else
                {
                    return StructureNetherBridgePieces.End.func_74971_a(p_74962_2_, p_74962_3_, p_74962_4_, p_74962_5_, p_74962_6_, p_74962_7_, p_74962_8_);
                }
            }

            protected StructureComponent func_74963_a(StructureNetherBridgePieces.Start p_74963_1_, List p_74963_2_, Random p_74963_3_, int p_74963_4_, int p_74963_5_, boolean p_74963_6_)
            {
                switch (this.field_74885_f)
                {
                    case 0:
                        return this.func_74962_a(p_74963_1_, p_74963_2_, p_74963_3_, this.field_74887_e.field_78897_a + p_74963_4_, this.field_74887_e.field_78895_b + p_74963_5_, this.field_74887_e.field_78892_f + 1, this.field_74885_f, this.func_74877_c(), p_74963_6_);
                    case 1:
                        return this.func_74962_a(p_74963_1_, p_74963_2_, p_74963_3_, this.field_74887_e.field_78897_a - 1, this.field_74887_e.field_78895_b + p_74963_5_, this.field_74887_e.field_78896_c + p_74963_4_, this.field_74885_f, this.func_74877_c(), p_74963_6_);
                    case 2:
                        return this.func_74962_a(p_74963_1_, p_74963_2_, p_74963_3_, this.field_74887_e.field_78897_a + p_74963_4_, this.field_74887_e.field_78895_b + p_74963_5_, this.field_74887_e.field_78896_c - 1, this.field_74885_f, this.func_74877_c(), p_74963_6_);
                    case 3:
                        return this.func_74962_a(p_74963_1_, p_74963_2_, p_74963_3_, this.field_74887_e.field_78893_d + 1, this.field_74887_e.field_78895_b + p_74963_5_, this.field_74887_e.field_78896_c + p_74963_4_, this.field_74885_f, this.func_74877_c(), p_74963_6_);
                    default:
                        return null;
                }
            }

            protected StructureComponent func_74961_b(StructureNetherBridgePieces.Start p_74961_1_, List p_74961_2_, Random p_74961_3_, int p_74961_4_, int p_74961_5_, boolean p_74961_6_)
            {
                switch (this.field_74885_f)
                {
                    case 0:
                        return this.func_74962_a(p_74961_1_, p_74961_2_, p_74961_3_, this.field_74887_e.field_78897_a - 1, this.field_74887_e.field_78895_b + p_74961_4_, this.field_74887_e.field_78896_c + p_74961_5_, 1, this.func_74877_c(), p_74961_6_);
                    case 1:
                        return this.func_74962_a(p_74961_1_, p_74961_2_, p_74961_3_, this.field_74887_e.field_78897_a + p_74961_5_, this.field_74887_e.field_78895_b + p_74961_4_, this.field_74887_e.field_78896_c - 1, 2, this.func_74877_c(), p_74961_6_);
                    case 2:
                        return this.func_74962_a(p_74961_1_, p_74961_2_, p_74961_3_, this.field_74887_e.field_78897_a - 1, this.field_74887_e.field_78895_b + p_74961_4_, this.field_74887_e.field_78896_c + p_74961_5_, 1, this.func_74877_c(), p_74961_6_);
                    case 3:
                        return this.func_74962_a(p_74961_1_, p_74961_2_, p_74961_3_, this.field_74887_e.field_78897_a + p_74961_5_, this.field_74887_e.field_78895_b + p_74961_4_, this.field_74887_e.field_78896_c - 1, 2, this.func_74877_c(), p_74961_6_);
                    default:
                        return null;
                }
            }

            protected StructureComponent func_74965_c(StructureNetherBridgePieces.Start p_74965_1_, List p_74965_2_, Random p_74965_3_, int p_74965_4_, int p_74965_5_, boolean p_74965_6_)
            {
                switch (this.field_74885_f)
                {
                    case 0:
                        return this.func_74962_a(p_74965_1_, p_74965_2_, p_74965_3_, this.field_74887_e.field_78893_d + 1, this.field_74887_e.field_78895_b + p_74965_4_, this.field_74887_e.field_78896_c + p_74965_5_, 3, this.func_74877_c(), p_74965_6_);
                    case 1:
                        return this.func_74962_a(p_74965_1_, p_74965_2_, p_74965_3_, this.field_74887_e.field_78897_a + p_74965_5_, this.field_74887_e.field_78895_b + p_74965_4_, this.field_74887_e.field_78892_f + 1, 0, this.func_74877_c(), p_74965_6_);
                    case 2:
                        return this.func_74962_a(p_74965_1_, p_74965_2_, p_74965_3_, this.field_74887_e.field_78893_d + 1, this.field_74887_e.field_78895_b + p_74965_4_, this.field_74887_e.field_78896_c + p_74965_5_, 3, this.func_74877_c(), p_74965_6_);
                    case 3:
                        return this.func_74962_a(p_74965_1_, p_74965_2_, p_74965_3_, this.field_74887_e.field_78897_a + p_74965_5_, this.field_74887_e.field_78895_b + p_74965_4_, this.field_74887_e.field_78892_f + 1, 0, this.func_74877_c(), p_74965_6_);
                    default:
                        return null;
                }
            }

            protected static boolean func_74964_a(StructureBoundingBox p_74964_0_)
            {
                return p_74964_0_ != null && p_74964_0_.field_78895_b > 10;
            }
        }

    static class PieceWeight
        {
            public Class field_78828_a;
            public final int field_78826_b;
            public int field_78827_c;
            public int field_78824_d;
            public boolean field_78825_e;
            private static final String __OBFID = "CL_00000467";

            public PieceWeight(Class p_i2055_1_, int p_i2055_2_, int p_i2055_3_, boolean p_i2055_4_)
            {
                this.field_78828_a = p_i2055_1_;
                this.field_78826_b = p_i2055_2_;
                this.field_78824_d = p_i2055_3_;
                this.field_78825_e = p_i2055_4_;
            }

            public PieceWeight(Class p_i2056_1_, int p_i2056_2_, int p_i2056_3_)
            {
                this(p_i2056_1_, p_i2056_2_, p_i2056_3_, false);
            }

            public boolean func_78822_a(int p_78822_1_)
            {
                return this.field_78824_d == 0 || this.field_78827_c < this.field_78824_d;
            }

            public boolean func_78823_a()
            {
                return this.field_78824_d == 0 || this.field_78827_c < this.field_78824_d;
            }
        }

    public static class Stairs extends StructureNetherBridgePieces.Piece
        {
            private static final String __OBFID = "CL_00000469";

            public Stairs() {}

            public Stairs(int p_i2058_1_, Random p_i2058_2_, StructureBoundingBox p_i2058_3_, int p_i2058_4_)
            {
                super(p_i2058_1_);
                this.field_74885_f = p_i2058_4_;
                this.field_74887_e = p_i2058_3_;
            }

            public void func_74861_a(StructureComponent p_74861_1_, List p_74861_2_, Random p_74861_3_)
            {
                this.func_74965_c((StructureNetherBridgePieces.Start)p_74861_1_, p_74861_2_, p_74861_3_, 6, 2, false);
            }

            public static StructureNetherBridgePieces.Stairs func_74973_a(List p_74973_0_, Random p_74973_1_, int p_74973_2_, int p_74973_3_, int p_74973_4_, int p_74973_5_, int p_74973_6_)
            {
                StructureBoundingBox structureboundingbox = StructureBoundingBox.func_78889_a(p_74973_2_, p_74973_3_, p_74973_4_, -2, 0, 0, 7, 11, 7, p_74973_5_);
                return func_74964_a(structureboundingbox) && StructureComponent.func_74883_a(p_74973_0_, structureboundingbox) == null ? new StructureNetherBridgePieces.Stairs(p_74973_6_, p_74973_1_, structureboundingbox, p_74973_5_) : null;
            }

            public boolean func_74875_a(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_)
            {
                this.func_151549_a(p_74875_1_, p_74875_3_, 0, 0, 0, 6, 1, 6, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 0, 2, 0, 6, 10, 6, Blocks.AIR, Blocks.AIR, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 0, 2, 0, 1, 8, 0, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 5, 2, 0, 6, 8, 0, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 0, 2, 1, 0, 8, 6, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 6, 2, 1, 6, 8, 6, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 1, 2, 6, 5, 8, 6, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 0, 3, 2, 0, 5, 4, Blocks.NETHER_BRICK_FENCE, Blocks.NETHER_BRICK_FENCE, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 6, 3, 2, 6, 5, 2, Blocks.NETHER_BRICK_FENCE, Blocks.NETHER_BRICK_FENCE, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 6, 3, 4, 6, 5, 4, Blocks.NETHER_BRICK_FENCE, Blocks.NETHER_BRICK_FENCE, false);
                this.func_151550_a(p_74875_1_, Blocks.NETHER_BRICK, 0, 5, 2, 5, p_74875_3_);
                this.func_151549_a(p_74875_1_, p_74875_3_, 4, 2, 5, 4, 3, 5, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 3, 2, 5, 3, 4, 5, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 2, 2, 5, 2, 5, 5, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 1, 2, 5, 1, 6, 5, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 1, 7, 1, 5, 7, 4, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 6, 8, 2, 6, 8, 4, Blocks.AIR, Blocks.AIR, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 2, 6, 0, 4, 8, 0, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 2, 5, 0, 4, 5, 0, Blocks.NETHER_BRICK_FENCE, Blocks.NETHER_BRICK_FENCE, false);

                for (int i = 0; i <= 6; ++i)
                {
                    for (int j = 0; j <= 6; ++j)
                    {
                        this.func_151554_b(p_74875_1_, Blocks.NETHER_BRICK, 0, i, -1, j, p_74875_3_);
                    }
                }

                return true;
            }
        }

    public static class Start extends StructureNetherBridgePieces.Crossing3
        {
            public StructureNetherBridgePieces.PieceWeight field_74970_a;
            public List field_74968_b;
            public List field_74969_c;
            public ArrayList field_74967_d = new ArrayList();
            private static final String __OBFID = "CL_00000470";

            public Start() {}

            public Start(Random p_i2059_1_, int p_i2059_2_, int p_i2059_3_)
            {
                super(p_i2059_1_, p_i2059_2_, p_i2059_3_);
                this.field_74968_b = new ArrayList();
                StructureNetherBridgePieces.PieceWeight[] apieceweight = StructureNetherBridgePieces.field_78742_a;
                int k = apieceweight.length;
                int l;
                StructureNetherBridgePieces.PieceWeight pieceweight;

                for (l = 0; l < k; ++l)
                {
                    pieceweight = apieceweight[l];
                    pieceweight.field_78827_c = 0;
                    this.field_74968_b.add(pieceweight);
                }

                this.field_74969_c = new ArrayList();
                apieceweight = StructureNetherBridgePieces.field_78741_b;
                k = apieceweight.length;

                for (l = 0; l < k; ++l)
                {
                    pieceweight = apieceweight[l];
                    pieceweight.field_78827_c = 0;
                    this.field_74969_c.add(pieceweight);
                }
            }

            protected void func_143011_b(NBTTagCompound p_143011_1_)
            {
                super.func_143011_b(p_143011_1_);
            }

            protected void func_143012_a(NBTTagCompound p_143012_1_)
            {
                super.func_143012_a(p_143012_1_);
            }
        }

    public static class Straight extends StructureNetherBridgePieces.Piece
        {
            private static final String __OBFID = "CL_00000456";

            public Straight() {}

            public Straight(int p_i2044_1_, Random p_i2044_2_, StructureBoundingBox p_i2044_3_, int p_i2044_4_)
            {
                super(p_i2044_1_);
                this.field_74885_f = p_i2044_4_;
                this.field_74887_e = p_i2044_3_;
            }

            public void func_74861_a(StructureComponent p_74861_1_, List p_74861_2_, Random p_74861_3_)
            {
                this.func_74963_a((StructureNetherBridgePieces.Start)p_74861_1_, p_74861_2_, p_74861_3_, 1, 3, false);
            }

            public static StructureNetherBridgePieces.Straight func_74983_a(List p_74983_0_, Random p_74983_1_, int p_74983_2_, int p_74983_3_, int p_74983_4_, int p_74983_5_, int p_74983_6_)
            {
                StructureBoundingBox structureboundingbox = StructureBoundingBox.func_78889_a(p_74983_2_, p_74983_3_, p_74983_4_, -1, -3, 0, 5, 10, 19, p_74983_5_);
                return func_74964_a(structureboundingbox) && StructureComponent.func_74883_a(p_74983_0_, structureboundingbox) == null ? new StructureNetherBridgePieces.Straight(p_74983_6_, p_74983_1_, structureboundingbox, p_74983_5_) : null;
            }

            public boolean func_74875_a(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_)
            {
                this.func_151549_a(p_74875_1_, p_74875_3_, 0, 3, 0, 4, 4, 18, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 1, 5, 0, 3, 7, 18, Blocks.AIR, Blocks.AIR, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 0, 5, 0, 0, 5, 18, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 4, 5, 0, 4, 5, 18, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 0, 2, 0, 4, 2, 5, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 0, 2, 13, 4, 2, 18, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 0, 0, 0, 4, 1, 3, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 0, 0, 15, 4, 1, 18, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);

                for (int i = 0; i <= 4; ++i)
                {
                    for (int j = 0; j <= 2; ++j)
                    {
                        this.func_151554_b(p_74875_1_, Blocks.NETHER_BRICK, 0, i, -1, j, p_74875_3_);
                        this.func_151554_b(p_74875_1_, Blocks.NETHER_BRICK, 0, i, -1, 18 - j, p_74875_3_);
                    }
                }

                this.func_151549_a(p_74875_1_, p_74875_3_, 0, 1, 1, 0, 4, 1, Blocks.NETHER_BRICK_FENCE, Blocks.NETHER_BRICK_FENCE, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 0, 3, 4, 0, 4, 4, Blocks.NETHER_BRICK_FENCE, Blocks.NETHER_BRICK_FENCE, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 0, 3, 14, 0, 4, 14, Blocks.NETHER_BRICK_FENCE, Blocks.NETHER_BRICK_FENCE, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 0, 1, 17, 0, 4, 17, Blocks.NETHER_BRICK_FENCE, Blocks.NETHER_BRICK_FENCE, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 4, 1, 1, 4, 4, 1, Blocks.NETHER_BRICK_FENCE, Blocks.NETHER_BRICK_FENCE, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 4, 3, 4, 4, 4, 4, Blocks.NETHER_BRICK_FENCE, Blocks.NETHER_BRICK_FENCE, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 4, 3, 14, 4, 4, 14, Blocks.NETHER_BRICK_FENCE, Blocks.NETHER_BRICK_FENCE, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 4, 1, 17, 4, 4, 17, Blocks.NETHER_BRICK_FENCE, Blocks.NETHER_BRICK_FENCE, false);
                return true;
            }
        }

    public static class Throne extends StructureNetherBridgePieces.Piece
        {
            private boolean field_74976_a;
            private static final String __OBFID = "CL_00000465";

            public Throne() {}

            public Throne(int p_i2053_1_, Random p_i2053_2_, StructureBoundingBox p_i2053_3_, int p_i2053_4_)
            {
                super(p_i2053_1_);
                this.field_74885_f = p_i2053_4_;
                this.field_74887_e = p_i2053_3_;
            }

            protected void func_143011_b(NBTTagCompound p_143011_1_)
            {
                super.func_143011_b(p_143011_1_);
                this.field_74976_a = p_143011_1_.func_74767_n("Mob");
            }

            protected void func_143012_a(NBTTagCompound p_143012_1_)
            {
                super.func_143012_a(p_143012_1_);
                p_143012_1_.func_74757_a("Mob", this.field_74976_a);
            }

            public static StructureNetherBridgePieces.Throne func_74975_a(List p_74975_0_, Random p_74975_1_, int p_74975_2_, int p_74975_3_, int p_74975_4_, int p_74975_5_, int p_74975_6_)
            {
                StructureBoundingBox structureboundingbox = StructureBoundingBox.func_78889_a(p_74975_2_, p_74975_3_, p_74975_4_, -2, 0, 0, 7, 8, 9, p_74975_5_);
                return func_74964_a(structureboundingbox) && StructureComponent.func_74883_a(p_74975_0_, structureboundingbox) == null ? new StructureNetherBridgePieces.Throne(p_74975_6_, p_74975_1_, structureboundingbox, p_74975_5_) : null;
            }

            public boolean func_74875_a(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_)
            {
                this.func_151549_a(p_74875_1_, p_74875_3_, 0, 2, 0, 6, 7, 7, Blocks.AIR, Blocks.AIR, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 1, 0, 0, 5, 1, 7, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 1, 2, 1, 5, 2, 7, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 1, 3, 2, 5, 3, 7, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 1, 4, 3, 5, 4, 7, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 1, 2, 0, 1, 4, 2, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 5, 2, 0, 5, 4, 2, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 1, 5, 2, 1, 5, 3, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 5, 5, 2, 5, 5, 3, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 0, 5, 3, 0, 5, 8, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 6, 5, 3, 6, 5, 8, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 1, 5, 8, 5, 5, 8, Blocks.NETHER_BRICK, Blocks.NETHER_BRICK, false);
                this.func_151550_a(p_74875_1_, Blocks.NETHER_BRICK_FENCE, 0, 1, 6, 3, p_74875_3_);
                this.func_151550_a(p_74875_1_, Blocks.NETHER_BRICK_FENCE, 0, 5, 6, 3, p_74875_3_);
                this.func_151549_a(p_74875_1_, p_74875_3_, 0, 6, 3, 0, 6, 8, Blocks.NETHER_BRICK_FENCE, Blocks.NETHER_BRICK_FENCE, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 6, 6, 3, 6, 6, 8, Blocks.NETHER_BRICK_FENCE, Blocks.NETHER_BRICK_FENCE, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 1, 6, 8, 5, 7, 8, Blocks.NETHER_BRICK_FENCE, Blocks.NETHER_BRICK_FENCE, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 2, 8, 8, 4, 8, 8, Blocks.NETHER_BRICK_FENCE, Blocks.NETHER_BRICK_FENCE, false);
                int i;
                int j;

                if (!this.field_74976_a)
                {
                    i = this.func_74862_a(5);
                    j = this.func_74865_a(3, 5);
                    int k = this.func_74873_b(3, 5);

                    if (p_74875_3_.func_78890_b(j, i, k))
                    {
                        this.field_74976_a = true;
                        p_74875_1_.func_147465_d(j, i, k, Blocks.MOB_SPAWNER, 0, 2);
                        TileEntityMobSpawner tileentitymobspawner = (TileEntityMobSpawner)p_74875_1_.func_147438_o(j, i, k);

                        if (tileentitymobspawner != null)
                        {
                            tileentitymobspawner.func_145881_a().func_98272_a("Blaze");
                        }
                    }
                }

                for (i = 0; i <= 6; ++i)
                {
                    for (j = 0; j <= 6; ++j)
                    {
                        this.func_151554_b(p_74875_1_, Blocks.NETHER_BRICK, 0, i, -1, j, p_74875_3_);
                    }
                }

                return true;
            }
        }
}