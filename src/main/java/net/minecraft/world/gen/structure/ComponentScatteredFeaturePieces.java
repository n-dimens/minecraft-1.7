package net.minecraft.world.gen.structure;

import java.util.Random;
import net.minecraft.block.BlockLever;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Direction;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;

public class ComponentScatteredFeaturePieces
{
    private static final String __OBFID = "CL_00000473";

    public static void func_143045_a()
    {
        MapGenStructureIO.func_143031_a(ComponentScatteredFeaturePieces.DesertPyramid.class, "TeDP");
        MapGenStructureIO.func_143031_a(ComponentScatteredFeaturePieces.JunglePyramid.class, "TeJP");
        MapGenStructureIO.func_143031_a(ComponentScatteredFeaturePieces.SwampHut.class, "TeSH");
    }

    public static class DesertPyramid extends ComponentScatteredFeaturePieces.Feature
        {
            private boolean[] field_74940_h = new boolean[4];
            public static final WeightedRandomChestContent[] field_74941_i = new WeightedRandomChestContent[] {new WeightedRandomChestContent(Items.field_151045_i, 0, 1, 3, 3), new WeightedRandomChestContent(Items.field_151042_j, 0, 1, 5, 10), new WeightedRandomChestContent(Items.field_151043_k, 0, 2, 7, 15), new WeightedRandomChestContent(Items.field_151166_bC, 0, 1, 3, 2), new WeightedRandomChestContent(Items.field_151103_aS, 0, 4, 6, 20), new WeightedRandomChestContent(Items.field_151078_bh, 0, 3, 7, 16), new WeightedRandomChestContent(Items.field_151141_av, 0, 1, 1, 3), new WeightedRandomChestContent(Items.field_151138_bX, 0, 1, 1, 1), new WeightedRandomChestContent(Items.field_151136_bY, 0, 1, 1, 1), new WeightedRandomChestContent(Items.field_151125_bZ, 0, 1, 1, 1)};
            private static final String __OBFID = "CL_00000476";

            public DesertPyramid() {}

            public DesertPyramid(Random p_i2062_1_, int p_i2062_2_, int p_i2062_3_)
            {
                super(p_i2062_1_, p_i2062_2_, 64, p_i2062_3_, 21, 15, 21);
            }

            protected void func_143012_a(NBTTagCompound p_143012_1_)
            {
                super.func_143012_a(p_143012_1_);
                p_143012_1_.func_74757_a("hasPlacedChest0", this.field_74940_h[0]);
                p_143012_1_.func_74757_a("hasPlacedChest1", this.field_74940_h[1]);
                p_143012_1_.func_74757_a("hasPlacedChest2", this.field_74940_h[2]);
                p_143012_1_.func_74757_a("hasPlacedChest3", this.field_74940_h[3]);
            }

            protected void func_143011_b(NBTTagCompound p_143011_1_)
            {
                super.func_143011_b(p_143011_1_);
                this.field_74940_h[0] = p_143011_1_.func_74767_n("hasPlacedChest0");
                this.field_74940_h[1] = p_143011_1_.func_74767_n("hasPlacedChest1");
                this.field_74940_h[2] = p_143011_1_.func_74767_n("hasPlacedChest2");
                this.field_74940_h[3] = p_143011_1_.func_74767_n("hasPlacedChest3");
            }

            public boolean func_74875_a(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_)
            {
                this.func_151549_a(p_74875_1_, p_74875_3_, 0, -4, 0, this.field_74939_a - 1, 0, this.field_74938_c - 1, Blocks.field_150322_A, Blocks.field_150322_A, false);
                int i;

                for (i = 1; i <= 9; ++i)
                {
                    this.func_151549_a(p_74875_1_, p_74875_3_, i, i, i, this.field_74939_a - 1 - i, i, this.field_74938_c - 1 - i, Blocks.field_150322_A, Blocks.field_150322_A, false);
                    this.func_151549_a(p_74875_1_, p_74875_3_, i + 1, i, i + 1, this.field_74939_a - 2 - i, i, this.field_74938_c - 2 - i, Blocks.field_150350_a, Blocks.field_150350_a, false);
                }

                int j;

                for (i = 0; i < this.field_74939_a; ++i)
                {
                    for (j = 0; j < this.field_74938_c; ++j)
                    {
                        byte b0 = -5;
                        this.func_151554_b(p_74875_1_, Blocks.field_150322_A, 0, i, b0, j, p_74875_3_);
                    }
                }

                i = this.func_151555_a(Blocks.field_150372_bz, 3);
                j = this.func_151555_a(Blocks.field_150372_bz, 2);
                int k1 = this.func_151555_a(Blocks.field_150372_bz, 0);
                int k = this.func_151555_a(Blocks.field_150372_bz, 1);
                byte b1 = 1;
                byte b2 = 11;
                this.func_151549_a(p_74875_1_, p_74875_3_, 0, 0, 0, 4, 9, 4, Blocks.field_150322_A, Blocks.field_150350_a, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 1, 10, 1, 3, 10, 3, Blocks.field_150322_A, Blocks.field_150322_A, false);
                this.func_151550_a(p_74875_1_, Blocks.field_150372_bz, i, 2, 10, 0, p_74875_3_);
                this.func_151550_a(p_74875_1_, Blocks.field_150372_bz, j, 2, 10, 4, p_74875_3_);
                this.func_151550_a(p_74875_1_, Blocks.field_150372_bz, k1, 0, 10, 2, p_74875_3_);
                this.func_151550_a(p_74875_1_, Blocks.field_150372_bz, k, 4, 10, 2, p_74875_3_);
                this.func_151549_a(p_74875_1_, p_74875_3_, this.field_74939_a - 5, 0, 0, this.field_74939_a - 1, 9, 4, Blocks.field_150322_A, Blocks.field_150350_a, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, this.field_74939_a - 4, 10, 1, this.field_74939_a - 2, 10, 3, Blocks.field_150322_A, Blocks.field_150322_A, false);
                this.func_151550_a(p_74875_1_, Blocks.field_150372_bz, i, this.field_74939_a - 3, 10, 0, p_74875_3_);
                this.func_151550_a(p_74875_1_, Blocks.field_150372_bz, j, this.field_74939_a - 3, 10, 4, p_74875_3_);
                this.func_151550_a(p_74875_1_, Blocks.field_150372_bz, k1, this.field_74939_a - 5, 10, 2, p_74875_3_);
                this.func_151550_a(p_74875_1_, Blocks.field_150372_bz, k, this.field_74939_a - 1, 10, 2, p_74875_3_);
                this.func_151549_a(p_74875_1_, p_74875_3_, 8, 0, 0, 12, 4, 4, Blocks.field_150322_A, Blocks.field_150350_a, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 9, 1, 0, 11, 3, 4, Blocks.field_150350_a, Blocks.field_150350_a, false);
                this.func_151550_a(p_74875_1_, Blocks.field_150322_A, 2, 9, 1, 1, p_74875_3_);
                this.func_151550_a(p_74875_1_, Blocks.field_150322_A, 2, 9, 2, 1, p_74875_3_);
                this.func_151550_a(p_74875_1_, Blocks.field_150322_A, 2, 9, 3, 1, p_74875_3_);
                this.func_151550_a(p_74875_1_, Blocks.field_150322_A, 2, 10, 3, 1, p_74875_3_);
                this.func_151550_a(p_74875_1_, Blocks.field_150322_A, 2, 11, 3, 1, p_74875_3_);
                this.func_151550_a(p_74875_1_, Blocks.field_150322_A, 2, 11, 2, 1, p_74875_3_);
                this.func_151550_a(p_74875_1_, Blocks.field_150322_A, 2, 11, 1, 1, p_74875_3_);
                this.func_151549_a(p_74875_1_, p_74875_3_, 4, 1, 1, 8, 3, 3, Blocks.field_150322_A, Blocks.field_150350_a, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 4, 1, 2, 8, 2, 2, Blocks.field_150350_a, Blocks.field_150350_a, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 12, 1, 1, 16, 3, 3, Blocks.field_150322_A, Blocks.field_150350_a, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 12, 1, 2, 16, 2, 2, Blocks.field_150350_a, Blocks.field_150350_a, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 5, 4, 5, this.field_74939_a - 6, 4, this.field_74938_c - 6, Blocks.field_150322_A, Blocks.field_150322_A, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 9, 4, 9, 11, 4, 11, Blocks.field_150350_a, Blocks.field_150350_a, false);
                this.func_151556_a(p_74875_1_, p_74875_3_, 8, 1, 8, 8, 3, 8, Blocks.field_150322_A, 2, Blocks.field_150322_A, 2, false);
                this.func_151556_a(p_74875_1_, p_74875_3_, 12, 1, 8, 12, 3, 8, Blocks.field_150322_A, 2, Blocks.field_150322_A, 2, false);
                this.func_151556_a(p_74875_1_, p_74875_3_, 8, 1, 12, 8, 3, 12, Blocks.field_150322_A, 2, Blocks.field_150322_A, 2, false);
                this.func_151556_a(p_74875_1_, p_74875_3_, 12, 1, 12, 12, 3, 12, Blocks.field_150322_A, 2, Blocks.field_150322_A, 2, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 1, 1, 5, 4, 4, 11, Blocks.field_150322_A, Blocks.field_150322_A, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, this.field_74939_a - 5, 1, 5, this.field_74939_a - 2, 4, 11, Blocks.field_150322_A, Blocks.field_150322_A, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 6, 7, 9, 6, 7, 11, Blocks.field_150322_A, Blocks.field_150322_A, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, this.field_74939_a - 7, 7, 9, this.field_74939_a - 7, 7, 11, Blocks.field_150322_A, Blocks.field_150322_A, false);
                this.func_151556_a(p_74875_1_, p_74875_3_, 5, 5, 9, 5, 7, 11, Blocks.field_150322_A, 2, Blocks.field_150322_A, 2, false);
                this.func_151556_a(p_74875_1_, p_74875_3_, this.field_74939_a - 6, 5, 9, this.field_74939_a - 6, 7, 11, Blocks.field_150322_A, 2, Blocks.field_150322_A, 2, false);
                this.func_151550_a(p_74875_1_, Blocks.field_150350_a, 0, 5, 5, 10, p_74875_3_);
                this.func_151550_a(p_74875_1_, Blocks.field_150350_a, 0, 5, 6, 10, p_74875_3_);
                this.func_151550_a(p_74875_1_, Blocks.field_150350_a, 0, 6, 6, 10, p_74875_3_);
                this.func_151550_a(p_74875_1_, Blocks.field_150350_a, 0, this.field_74939_a - 6, 5, 10, p_74875_3_);
                this.func_151550_a(p_74875_1_, Blocks.field_150350_a, 0, this.field_74939_a - 6, 6, 10, p_74875_3_);
                this.func_151550_a(p_74875_1_, Blocks.field_150350_a, 0, this.field_74939_a - 7, 6, 10, p_74875_3_);
                this.func_151549_a(p_74875_1_, p_74875_3_, 2, 4, 4, 2, 6, 4, Blocks.field_150350_a, Blocks.field_150350_a, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, this.field_74939_a - 3, 4, 4, this.field_74939_a - 3, 6, 4, Blocks.field_150350_a, Blocks.field_150350_a, false);
                this.func_151550_a(p_74875_1_, Blocks.field_150372_bz, i, 2, 4, 5, p_74875_3_);
                this.func_151550_a(p_74875_1_, Blocks.field_150372_bz, i, 2, 3, 4, p_74875_3_);
                this.func_151550_a(p_74875_1_, Blocks.field_150372_bz, i, this.field_74939_a - 3, 4, 5, p_74875_3_);
                this.func_151550_a(p_74875_1_, Blocks.field_150372_bz, i, this.field_74939_a - 3, 3, 4, p_74875_3_);
                this.func_151549_a(p_74875_1_, p_74875_3_, 1, 1, 3, 2, 2, 3, Blocks.field_150322_A, Blocks.field_150322_A, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, this.field_74939_a - 3, 1, 3, this.field_74939_a - 2, 2, 3, Blocks.field_150322_A, Blocks.field_150322_A, false);
                this.func_151550_a(p_74875_1_, Blocks.field_150372_bz, 0, 1, 1, 2, p_74875_3_);
                this.func_151550_a(p_74875_1_, Blocks.field_150372_bz, 0, this.field_74939_a - 2, 1, 2, p_74875_3_);
                this.func_151550_a(p_74875_1_, Blocks.field_150333_U, 1, 1, 2, 2, p_74875_3_);
                this.func_151550_a(p_74875_1_, Blocks.field_150333_U, 1, this.field_74939_a - 2, 2, 2, p_74875_3_);
                this.func_151550_a(p_74875_1_, Blocks.field_150372_bz, k, 2, 1, 2, p_74875_3_);
                this.func_151550_a(p_74875_1_, Blocks.field_150372_bz, k1, this.field_74939_a - 3, 1, 2, p_74875_3_);
                this.func_151549_a(p_74875_1_, p_74875_3_, 4, 3, 5, 4, 3, 18, Blocks.field_150322_A, Blocks.field_150322_A, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, this.field_74939_a - 5, 3, 5, this.field_74939_a - 5, 3, 17, Blocks.field_150322_A, Blocks.field_150322_A, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 3, 1, 5, 4, 2, 16, Blocks.field_150350_a, Blocks.field_150350_a, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, this.field_74939_a - 6, 1, 5, this.field_74939_a - 5, 2, 16, Blocks.field_150350_a, Blocks.field_150350_a, false);
                int l;

                for (l = 5; l <= 17; l += 2)
                {
                    this.func_151550_a(p_74875_1_, Blocks.field_150322_A, 2, 4, 1, l, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150322_A, 1, 4, 2, l, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150322_A, 2, this.field_74939_a - 5, 1, l, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150322_A, 1, this.field_74939_a - 5, 2, l, p_74875_3_);
                }

                this.func_151550_a(p_74875_1_, Blocks.field_150325_L, b1, 10, 0, 7, p_74875_3_);
                this.func_151550_a(p_74875_1_, Blocks.field_150325_L, b1, 10, 0, 8, p_74875_3_);
                this.func_151550_a(p_74875_1_, Blocks.field_150325_L, b1, 9, 0, 9, p_74875_3_);
                this.func_151550_a(p_74875_1_, Blocks.field_150325_L, b1, 11, 0, 9, p_74875_3_);
                this.func_151550_a(p_74875_1_, Blocks.field_150325_L, b1, 8, 0, 10, p_74875_3_);
                this.func_151550_a(p_74875_1_, Blocks.field_150325_L, b1, 12, 0, 10, p_74875_3_);
                this.func_151550_a(p_74875_1_, Blocks.field_150325_L, b1, 7, 0, 10, p_74875_3_);
                this.func_151550_a(p_74875_1_, Blocks.field_150325_L, b1, 13, 0, 10, p_74875_3_);
                this.func_151550_a(p_74875_1_, Blocks.field_150325_L, b1, 9, 0, 11, p_74875_3_);
                this.func_151550_a(p_74875_1_, Blocks.field_150325_L, b1, 11, 0, 11, p_74875_3_);
                this.func_151550_a(p_74875_1_, Blocks.field_150325_L, b1, 10, 0, 12, p_74875_3_);
                this.func_151550_a(p_74875_1_, Blocks.field_150325_L, b1, 10, 0, 13, p_74875_3_);
                this.func_151550_a(p_74875_1_, Blocks.field_150325_L, b2, 10, 0, 10, p_74875_3_);

                for (l = 0; l <= this.field_74939_a - 1; l += this.field_74939_a - 1)
                {
                    this.func_151550_a(p_74875_1_, Blocks.field_150322_A, 2, l, 2, 1, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150325_L, b1, l, 2, 2, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150322_A, 2, l, 2, 3, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150322_A, 2, l, 3, 1, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150325_L, b1, l, 3, 2, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150322_A, 2, l, 3, 3, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150325_L, b1, l, 4, 1, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150322_A, 1, l, 4, 2, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150325_L, b1, l, 4, 3, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150322_A, 2, l, 5, 1, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150325_L, b1, l, 5, 2, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150322_A, 2, l, 5, 3, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150325_L, b1, l, 6, 1, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150322_A, 1, l, 6, 2, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150325_L, b1, l, 6, 3, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150325_L, b1, l, 7, 1, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150325_L, b1, l, 7, 2, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150325_L, b1, l, 7, 3, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150322_A, 2, l, 8, 1, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150322_A, 2, l, 8, 2, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150322_A, 2, l, 8, 3, p_74875_3_);
                }

                for (l = 2; l <= this.field_74939_a - 3; l += this.field_74939_a - 3 - 2)
                {
                    this.func_151550_a(p_74875_1_, Blocks.field_150322_A, 2, l - 1, 2, 0, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150325_L, b1, l, 2, 0, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150322_A, 2, l + 1, 2, 0, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150322_A, 2, l - 1, 3, 0, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150325_L, b1, l, 3, 0, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150322_A, 2, l + 1, 3, 0, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150325_L, b1, l - 1, 4, 0, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150322_A, 1, l, 4, 0, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150325_L, b1, l + 1, 4, 0, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150322_A, 2, l - 1, 5, 0, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150325_L, b1, l, 5, 0, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150322_A, 2, l + 1, 5, 0, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150325_L, b1, l - 1, 6, 0, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150322_A, 1, l, 6, 0, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150325_L, b1, l + 1, 6, 0, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150325_L, b1, l - 1, 7, 0, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150325_L, b1, l, 7, 0, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150325_L, b1, l + 1, 7, 0, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150322_A, 2, l - 1, 8, 0, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150322_A, 2, l, 8, 0, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150322_A, 2, l + 1, 8, 0, p_74875_3_);
                }

                this.func_151556_a(p_74875_1_, p_74875_3_, 8, 4, 0, 12, 6, 0, Blocks.field_150322_A, 2, Blocks.field_150322_A, 2, false);
                this.func_151550_a(p_74875_1_, Blocks.field_150350_a, 0, 8, 6, 0, p_74875_3_);
                this.func_151550_a(p_74875_1_, Blocks.field_150350_a, 0, 12, 6, 0, p_74875_3_);
                this.func_151550_a(p_74875_1_, Blocks.field_150325_L, b1, 9, 5, 0, p_74875_3_);
                this.func_151550_a(p_74875_1_, Blocks.field_150322_A, 1, 10, 5, 0, p_74875_3_);
                this.func_151550_a(p_74875_1_, Blocks.field_150325_L, b1, 11, 5, 0, p_74875_3_);
                this.func_151556_a(p_74875_1_, p_74875_3_, 8, -14, 8, 12, -11, 12, Blocks.field_150322_A, 2, Blocks.field_150322_A, 2, false);
                this.func_151556_a(p_74875_1_, p_74875_3_, 8, -10, 8, 12, -10, 12, Blocks.field_150322_A, 1, Blocks.field_150322_A, 1, false);
                this.func_151556_a(p_74875_1_, p_74875_3_, 8, -9, 8, 12, -9, 12, Blocks.field_150322_A, 2, Blocks.field_150322_A, 2, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 8, -8, 8, 12, -1, 12, Blocks.field_150322_A, Blocks.field_150322_A, false);
                this.func_151549_a(p_74875_1_, p_74875_3_, 9, -11, 9, 11, -1, 11, Blocks.field_150350_a, Blocks.field_150350_a, false);
                this.func_151550_a(p_74875_1_, Blocks.field_150456_au, 0, 10, -11, 10, p_74875_3_);
                this.func_151549_a(p_74875_1_, p_74875_3_, 9, -13, 9, 11, -13, 11, Blocks.field_150335_W, Blocks.field_150350_a, false);
                this.func_151550_a(p_74875_1_, Blocks.field_150350_a, 0, 8, -11, 10, p_74875_3_);
                this.func_151550_a(p_74875_1_, Blocks.field_150350_a, 0, 8, -10, 10, p_74875_3_);
                this.func_151550_a(p_74875_1_, Blocks.field_150322_A, 1, 7, -10, 10, p_74875_3_);
                this.func_151550_a(p_74875_1_, Blocks.field_150322_A, 2, 7, -11, 10, p_74875_3_);
                this.func_151550_a(p_74875_1_, Blocks.field_150350_a, 0, 12, -11, 10, p_74875_3_);
                this.func_151550_a(p_74875_1_, Blocks.field_150350_a, 0, 12, -10, 10, p_74875_3_);
                this.func_151550_a(p_74875_1_, Blocks.field_150322_A, 1, 13, -10, 10, p_74875_3_);
                this.func_151550_a(p_74875_1_, Blocks.field_150322_A, 2, 13, -11, 10, p_74875_3_);
                this.func_151550_a(p_74875_1_, Blocks.field_150350_a, 0, 10, -11, 8, p_74875_3_);
                this.func_151550_a(p_74875_1_, Blocks.field_150350_a, 0, 10, -10, 8, p_74875_3_);
                this.func_151550_a(p_74875_1_, Blocks.field_150322_A, 1, 10, -10, 7, p_74875_3_);
                this.func_151550_a(p_74875_1_, Blocks.field_150322_A, 2, 10, -11, 7, p_74875_3_);
                this.func_151550_a(p_74875_1_, Blocks.field_150350_a, 0, 10, -11, 12, p_74875_3_);
                this.func_151550_a(p_74875_1_, Blocks.field_150350_a, 0, 10, -10, 12, p_74875_3_);
                this.func_151550_a(p_74875_1_, Blocks.field_150322_A, 1, 10, -10, 13, p_74875_3_);
                this.func_151550_a(p_74875_1_, Blocks.field_150322_A, 2, 10, -11, 13, p_74875_3_);

                for (l = 0; l < 4; ++l)
                {
                    if (!this.field_74940_h[l])
                    {
                        int i1 = Direction.field_71583_a[l] * 2;
                        int j1 = Direction.field_71581_b[l] * 2;
                        this.field_74940_h[l] = this.func_74879_a(p_74875_1_, p_74875_3_, p_74875_2_, 10 + i1, -11, 10 + j1, WeightedRandomChestContent.func_92080_a(field_74941_i, new WeightedRandomChestContent[] {Items.field_151134_bR.func_92114_b(p_74875_2_)}), 2 + p_74875_2_.nextInt(5));
                    }
                }

                return true;
            }
        }

    abstract static class Feature extends StructureComponent
        {
            protected int field_74939_a;
            protected int field_74937_b;
            protected int field_74938_c;
            protected int field_74936_d = -1;
            private static final String __OBFID = "CL_00000479";

            public Feature() {}

            protected Feature(Random p_i2065_1_, int p_i2065_2_, int p_i2065_3_, int p_i2065_4_, int p_i2065_5_, int p_i2065_6_, int p_i2065_7_)
            {
                super(0);
                this.field_74939_a = p_i2065_5_;
                this.field_74937_b = p_i2065_6_;
                this.field_74938_c = p_i2065_7_;
                this.field_74885_f = p_i2065_1_.nextInt(4);

                switch (this.field_74885_f)
                {
                    case 0:
                    case 2:
                        this.field_74887_e = new StructureBoundingBox(p_i2065_2_, p_i2065_3_, p_i2065_4_, p_i2065_2_ + p_i2065_5_ - 1, p_i2065_3_ + p_i2065_6_ - 1, p_i2065_4_ + p_i2065_7_ - 1);
                        break;
                    default:
                        this.field_74887_e = new StructureBoundingBox(p_i2065_2_, p_i2065_3_, p_i2065_4_, p_i2065_2_ + p_i2065_7_ - 1, p_i2065_3_ + p_i2065_6_ - 1, p_i2065_4_ + p_i2065_5_ - 1);
                }
            }

            protected void func_143012_a(NBTTagCompound p_143012_1_)
            {
                p_143012_1_.func_74768_a("Width", this.field_74939_a);
                p_143012_1_.func_74768_a("Height", this.field_74937_b);
                p_143012_1_.func_74768_a("Depth", this.field_74938_c);
                p_143012_1_.func_74768_a("HPos", this.field_74936_d);
            }

            protected void func_143011_b(NBTTagCompound p_143011_1_)
            {
                this.field_74939_a = p_143011_1_.func_74762_e("Width");
                this.field_74937_b = p_143011_1_.func_74762_e("Height");
                this.field_74938_c = p_143011_1_.func_74762_e("Depth");
                this.field_74936_d = p_143011_1_.func_74762_e("HPos");
            }

            protected boolean func_74935_a(World p_74935_1_, StructureBoundingBox p_74935_2_, int p_74935_3_)
            {
                if (this.field_74936_d >= 0)
                {
                    return true;
                }
                else
                {
                    int j = 0;
                    int k = 0;

                    for (int l = this.field_74887_e.field_78896_c; l <= this.field_74887_e.field_78892_f; ++l)
                    {
                        for (int i1 = this.field_74887_e.field_78897_a; i1 <= this.field_74887_e.field_78893_d; ++i1)
                        {
                            if (p_74935_2_.func_78890_b(i1, 64, l))
                            {
                                j += Math.max(p_74935_1_.func_72825_h(i1, l), p_74935_1_.field_73011_w.func_76557_i());
                                ++k;
                            }
                        }
                    }

                    if (k == 0)
                    {
                        return false;
                    }
                    else
                    {
                        this.field_74936_d = j / k;
                        this.field_74887_e.func_78886_a(0, this.field_74936_d - this.field_74887_e.field_78895_b + p_74935_3_, 0);
                        return true;
                    }
                }
            }
        }

    public static class JunglePyramid extends ComponentScatteredFeaturePieces.Feature
        {
            private boolean field_74947_h;
            private boolean field_74948_i;
            private boolean field_74945_j;
            private boolean field_74946_k;
            public static final WeightedRandomChestContent[] field_74943_l = new WeightedRandomChestContent[] {new WeightedRandomChestContent(Items.field_151045_i, 0, 1, 3, 3), new WeightedRandomChestContent(Items.field_151042_j, 0, 1, 5, 10), new WeightedRandomChestContent(Items.field_151043_k, 0, 2, 7, 15), new WeightedRandomChestContent(Items.field_151166_bC, 0, 1, 3, 2), new WeightedRandomChestContent(Items.field_151103_aS, 0, 4, 6, 20), new WeightedRandomChestContent(Items.field_151078_bh, 0, 3, 7, 16), new WeightedRandomChestContent(Items.field_151141_av, 0, 1, 1, 3), new WeightedRandomChestContent(Items.field_151138_bX, 0, 1, 1, 1), new WeightedRandomChestContent(Items.field_151136_bY, 0, 1, 1, 1), new WeightedRandomChestContent(Items.field_151125_bZ, 0, 1, 1, 1)};
            public static final WeightedRandomChestContent[] field_74944_m = new WeightedRandomChestContent[] {new WeightedRandomChestContent(Items.field_151032_g, 0, 2, 7, 30)};
            private static ComponentScatteredFeaturePieces.JunglePyramid.Stones field_74942_n = new ComponentScatteredFeaturePieces.JunglePyramid.Stones(null);
            private static final String __OBFID = "CL_00000477";

            public JunglePyramid() {}

            public JunglePyramid(Random p_i2064_1_, int p_i2064_2_, int p_i2064_3_)
            {
                super(p_i2064_1_, p_i2064_2_, 64, p_i2064_3_, 12, 10, 15);
            }

            protected void func_143012_a(NBTTagCompound p_143012_1_)
            {
                super.func_143012_a(p_143012_1_);
                p_143012_1_.func_74757_a("placedMainChest", this.field_74947_h);
                p_143012_1_.func_74757_a("placedHiddenChest", this.field_74948_i);
                p_143012_1_.func_74757_a("placedTrap1", this.field_74945_j);
                p_143012_1_.func_74757_a("placedTrap2", this.field_74946_k);
            }

            protected void func_143011_b(NBTTagCompound p_143011_1_)
            {
                super.func_143011_b(p_143011_1_);
                this.field_74947_h = p_143011_1_.func_74767_n("placedMainChest");
                this.field_74948_i = p_143011_1_.func_74767_n("placedHiddenChest");
                this.field_74945_j = p_143011_1_.func_74767_n("placedTrap1");
                this.field_74946_k = p_143011_1_.func_74767_n("placedTrap2");
            }

            public boolean func_74875_a(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_)
            {
                if (!this.func_74935_a(p_74875_1_, p_74875_3_, 0))
                {
                    return false;
                }
                else
                {
                    int i = this.func_151555_a(Blocks.field_150446_ar, 3);
                    int j = this.func_151555_a(Blocks.field_150446_ar, 2);
                    int k = this.func_151555_a(Blocks.field_150446_ar, 0);
                    int l = this.func_151555_a(Blocks.field_150446_ar, 1);
                    this.func_74882_a(p_74875_1_, p_74875_3_, 0, -4, 0, this.field_74939_a - 1, 0, this.field_74938_c - 1, false, p_74875_2_, field_74942_n);
                    this.func_74882_a(p_74875_1_, p_74875_3_, 2, 1, 2, 9, 2, 2, false, p_74875_2_, field_74942_n);
                    this.func_74882_a(p_74875_1_, p_74875_3_, 2, 1, 12, 9, 2, 12, false, p_74875_2_, field_74942_n);
                    this.func_74882_a(p_74875_1_, p_74875_3_, 2, 1, 3, 2, 2, 11, false, p_74875_2_, field_74942_n);
                    this.func_74882_a(p_74875_1_, p_74875_3_, 9, 1, 3, 9, 2, 11, false, p_74875_2_, field_74942_n);
                    this.func_74882_a(p_74875_1_, p_74875_3_, 1, 3, 1, 10, 6, 1, false, p_74875_2_, field_74942_n);
                    this.func_74882_a(p_74875_1_, p_74875_3_, 1, 3, 13, 10, 6, 13, false, p_74875_2_, field_74942_n);
                    this.func_74882_a(p_74875_1_, p_74875_3_, 1, 3, 2, 1, 6, 12, false, p_74875_2_, field_74942_n);
                    this.func_74882_a(p_74875_1_, p_74875_3_, 10, 3, 2, 10, 6, 12, false, p_74875_2_, field_74942_n);
                    this.func_74882_a(p_74875_1_, p_74875_3_, 2, 3, 2, 9, 3, 12, false, p_74875_2_, field_74942_n);
                    this.func_74882_a(p_74875_1_, p_74875_3_, 2, 6, 2, 9, 6, 12, false, p_74875_2_, field_74942_n);
                    this.func_74882_a(p_74875_1_, p_74875_3_, 3, 7, 3, 8, 7, 11, false, p_74875_2_, field_74942_n);
                    this.func_74882_a(p_74875_1_, p_74875_3_, 4, 8, 4, 7, 8, 10, false, p_74875_2_, field_74942_n);
                    this.func_74878_a(p_74875_1_, p_74875_3_, 3, 1, 3, 8, 2, 11);
                    this.func_74878_a(p_74875_1_, p_74875_3_, 4, 3, 6, 7, 3, 9);
                    this.func_74878_a(p_74875_1_, p_74875_3_, 2, 4, 2, 9, 5, 12);
                    this.func_74878_a(p_74875_1_, p_74875_3_, 4, 6, 5, 7, 6, 9);
                    this.func_74878_a(p_74875_1_, p_74875_3_, 5, 7, 6, 6, 7, 8);
                    this.func_74878_a(p_74875_1_, p_74875_3_, 5, 1, 2, 6, 2, 2);
                    this.func_74878_a(p_74875_1_, p_74875_3_, 5, 2, 12, 6, 2, 12);
                    this.func_74878_a(p_74875_1_, p_74875_3_, 5, 5, 1, 6, 5, 1);
                    this.func_74878_a(p_74875_1_, p_74875_3_, 5, 5, 13, 6, 5, 13);
                    this.func_151550_a(p_74875_1_, Blocks.field_150350_a, 0, 1, 5, 5, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150350_a, 0, 10, 5, 5, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150350_a, 0, 1, 5, 9, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150350_a, 0, 10, 5, 9, p_74875_3_);
                    int i1;

                    for (i1 = 0; i1 <= 14; i1 += 14)
                    {
                        this.func_74882_a(p_74875_1_, p_74875_3_, 2, 4, i1, 2, 5, i1, false, p_74875_2_, field_74942_n);
                        this.func_74882_a(p_74875_1_, p_74875_3_, 4, 4, i1, 4, 5, i1, false, p_74875_2_, field_74942_n);
                        this.func_74882_a(p_74875_1_, p_74875_3_, 7, 4, i1, 7, 5, i1, false, p_74875_2_, field_74942_n);
                        this.func_74882_a(p_74875_1_, p_74875_3_, 9, 4, i1, 9, 5, i1, false, p_74875_2_, field_74942_n);
                    }

                    this.func_74882_a(p_74875_1_, p_74875_3_, 5, 6, 0, 6, 6, 0, false, p_74875_2_, field_74942_n);

                    for (i1 = 0; i1 <= 11; i1 += 11)
                    {
                        for (int j1 = 2; j1 <= 12; j1 += 2)
                        {
                            this.func_74882_a(p_74875_1_, p_74875_3_, i1, 4, j1, i1, 5, j1, false, p_74875_2_, field_74942_n);
                        }

                        this.func_74882_a(p_74875_1_, p_74875_3_, i1, 6, 5, i1, 6, 5, false, p_74875_2_, field_74942_n);
                        this.func_74882_a(p_74875_1_, p_74875_3_, i1, 6, 9, i1, 6, 9, false, p_74875_2_, field_74942_n);
                    }

                    this.func_74882_a(p_74875_1_, p_74875_3_, 2, 7, 2, 2, 9, 2, false, p_74875_2_, field_74942_n);
                    this.func_74882_a(p_74875_1_, p_74875_3_, 9, 7, 2, 9, 9, 2, false, p_74875_2_, field_74942_n);
                    this.func_74882_a(p_74875_1_, p_74875_3_, 2, 7, 12, 2, 9, 12, false, p_74875_2_, field_74942_n);
                    this.func_74882_a(p_74875_1_, p_74875_3_, 9, 7, 12, 9, 9, 12, false, p_74875_2_, field_74942_n);
                    this.func_74882_a(p_74875_1_, p_74875_3_, 4, 9, 4, 4, 9, 4, false, p_74875_2_, field_74942_n);
                    this.func_74882_a(p_74875_1_, p_74875_3_, 7, 9, 4, 7, 9, 4, false, p_74875_2_, field_74942_n);
                    this.func_74882_a(p_74875_1_, p_74875_3_, 4, 9, 10, 4, 9, 10, false, p_74875_2_, field_74942_n);
                    this.func_74882_a(p_74875_1_, p_74875_3_, 7, 9, 10, 7, 9, 10, false, p_74875_2_, field_74942_n);
                    this.func_74882_a(p_74875_1_, p_74875_3_, 5, 9, 7, 6, 9, 7, false, p_74875_2_, field_74942_n);
                    this.func_151550_a(p_74875_1_, Blocks.field_150446_ar, i, 5, 9, 6, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150446_ar, i, 6, 9, 6, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150446_ar, j, 5, 9, 8, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150446_ar, j, 6, 9, 8, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150446_ar, i, 4, 0, 0, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150446_ar, i, 5, 0, 0, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150446_ar, i, 6, 0, 0, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150446_ar, i, 7, 0, 0, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150446_ar, i, 4, 1, 8, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150446_ar, i, 4, 2, 9, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150446_ar, i, 4, 3, 10, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150446_ar, i, 7, 1, 8, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150446_ar, i, 7, 2, 9, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150446_ar, i, 7, 3, 10, p_74875_3_);
                    this.func_74882_a(p_74875_1_, p_74875_3_, 4, 1, 9, 4, 1, 9, false, p_74875_2_, field_74942_n);
                    this.func_74882_a(p_74875_1_, p_74875_3_, 7, 1, 9, 7, 1, 9, false, p_74875_2_, field_74942_n);
                    this.func_74882_a(p_74875_1_, p_74875_3_, 4, 1, 10, 7, 2, 10, false, p_74875_2_, field_74942_n);
                    this.func_74882_a(p_74875_1_, p_74875_3_, 5, 4, 5, 6, 4, 5, false, p_74875_2_, field_74942_n);
                    this.func_151550_a(p_74875_1_, Blocks.field_150446_ar, k, 4, 4, 5, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150446_ar, l, 7, 4, 5, p_74875_3_);

                    for (i1 = 0; i1 < 4; ++i1)
                    {
                        this.func_151550_a(p_74875_1_, Blocks.field_150446_ar, j, 5, 0 - i1, 6 + i1, p_74875_3_);
                        this.func_151550_a(p_74875_1_, Blocks.field_150446_ar, j, 6, 0 - i1, 6 + i1, p_74875_3_);
                        this.func_74878_a(p_74875_1_, p_74875_3_, 5, 0 - i1, 7 + i1, 6, 0 - i1, 9 + i1);
                    }

                    this.func_74878_a(p_74875_1_, p_74875_3_, 1, -3, 12, 10, -1, 13);
                    this.func_74878_a(p_74875_1_, p_74875_3_, 1, -3, 1, 3, -1, 13);
                    this.func_74878_a(p_74875_1_, p_74875_3_, 1, -3, 1, 9, -1, 5);

                    for (i1 = 1; i1 <= 13; i1 += 2)
                    {
                        this.func_74882_a(p_74875_1_, p_74875_3_, 1, -3, i1, 1, -2, i1, false, p_74875_2_, field_74942_n);
                    }

                    for (i1 = 2; i1 <= 12; i1 += 2)
                    {
                        this.func_74882_a(p_74875_1_, p_74875_3_, 1, -1, i1, 3, -1, i1, false, p_74875_2_, field_74942_n);
                    }

                    this.func_74882_a(p_74875_1_, p_74875_3_, 2, -2, 1, 5, -2, 1, false, p_74875_2_, field_74942_n);
                    this.func_74882_a(p_74875_1_, p_74875_3_, 7, -2, 1, 9, -2, 1, false, p_74875_2_, field_74942_n);
                    this.func_74882_a(p_74875_1_, p_74875_3_, 6, -3, 1, 6, -3, 1, false, p_74875_2_, field_74942_n);
                    this.func_74882_a(p_74875_1_, p_74875_3_, 6, -1, 1, 6, -1, 1, false, p_74875_2_, field_74942_n);
                    this.func_151550_a(p_74875_1_, Blocks.field_150479_bC, this.func_151555_a(Blocks.field_150479_bC, 3) | 4, 1, -3, 8, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150479_bC, this.func_151555_a(Blocks.field_150479_bC, 1) | 4, 4, -3, 8, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150473_bD, 4, 2, -3, 8, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150473_bD, 4, 3, -3, 8, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150488_af, 0, 5, -3, 7, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150488_af, 0, 5, -3, 6, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150488_af, 0, 5, -3, 5, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150488_af, 0, 5, -3, 4, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150488_af, 0, 5, -3, 3, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150488_af, 0, 5, -3, 2, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150488_af, 0, 5, -3, 1, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150488_af, 0, 4, -3, 1, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150341_Y, 0, 3, -3, 1, p_74875_3_);

                    if (!this.field_74945_j)
                    {
                        this.field_74945_j = this.func_74869_a(p_74875_1_, p_74875_3_, p_74875_2_, 3, -2, 1, 2, field_74944_m, 2);
                    }

                    this.func_151550_a(p_74875_1_, Blocks.field_150395_bd, 15, 3, -2, 2, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150479_bC, this.func_151555_a(Blocks.field_150479_bC, 2) | 4, 7, -3, 1, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150479_bC, this.func_151555_a(Blocks.field_150479_bC, 0) | 4, 7, -3, 5, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150473_bD, 4, 7, -3, 2, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150473_bD, 4, 7, -3, 3, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150473_bD, 4, 7, -3, 4, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150488_af, 0, 8, -3, 6, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150488_af, 0, 9, -3, 6, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150488_af, 0, 9, -3, 5, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150341_Y, 0, 9, -3, 4, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150488_af, 0, 9, -2, 4, p_74875_3_);

                    if (!this.field_74946_k)
                    {
                        this.field_74946_k = this.func_74869_a(p_74875_1_, p_74875_3_, p_74875_2_, 9, -2, 3, 4, field_74944_m, 2);
                    }

                    this.func_151550_a(p_74875_1_, Blocks.field_150395_bd, 15, 8, -1, 3, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150395_bd, 15, 8, -2, 3, p_74875_3_);

                    if (!this.field_74947_h)
                    {
                        this.field_74947_h = this.func_74879_a(p_74875_1_, p_74875_3_, p_74875_2_, 8, -3, 3, WeightedRandomChestContent.func_92080_a(field_74943_l, new WeightedRandomChestContent[] {Items.field_151134_bR.func_92114_b(p_74875_2_)}), 2 + p_74875_2_.nextInt(5));
                    }

                    this.func_151550_a(p_74875_1_, Blocks.field_150341_Y, 0, 9, -3, 2, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150341_Y, 0, 8, -3, 1, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150341_Y, 0, 4, -3, 5, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150341_Y, 0, 5, -2, 5, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150341_Y, 0, 5, -1, 5, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150341_Y, 0, 6, -3, 5, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150341_Y, 0, 7, -2, 5, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150341_Y, 0, 7, -1, 5, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150341_Y, 0, 8, -3, 5, p_74875_3_);
                    this.func_74882_a(p_74875_1_, p_74875_3_, 9, -1, 1, 9, -1, 5, false, p_74875_2_, field_74942_n);
                    this.func_74878_a(p_74875_1_, p_74875_3_, 8, -3, 8, 10, -1, 10);
                    this.func_151550_a(p_74875_1_, Blocks.field_150417_aV, 3, 8, -2, 11, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150417_aV, 3, 9, -2, 11, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150417_aV, 3, 10, -2, 11, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150442_at, BlockLever.func_149819_b(this.func_151555_a(Blocks.field_150442_at, 2)), 8, -2, 12, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150442_at, BlockLever.func_149819_b(this.func_151555_a(Blocks.field_150442_at, 2)), 9, -2, 12, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150442_at, BlockLever.func_149819_b(this.func_151555_a(Blocks.field_150442_at, 2)), 10, -2, 12, p_74875_3_);
                    this.func_74882_a(p_74875_1_, p_74875_3_, 8, -3, 8, 8, -3, 10, false, p_74875_2_, field_74942_n);
                    this.func_74882_a(p_74875_1_, p_74875_3_, 10, -3, 8, 10, -3, 10, false, p_74875_2_, field_74942_n);
                    this.func_151550_a(p_74875_1_, Blocks.field_150341_Y, 0, 10, -2, 9, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150488_af, 0, 8, -2, 9, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150488_af, 0, 8, -2, 10, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150488_af, 0, 10, -1, 9, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150320_F, 1, 9, -2, 8, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150320_F, this.func_151555_a(Blocks.field_150320_F, 4), 10, -2, 8, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150320_F, this.func_151555_a(Blocks.field_150320_F, 4), 10, -1, 8, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150413_aR, this.func_151555_a(Blocks.field_150413_aR, 2), 10, -2, 10, p_74875_3_);

                    if (!this.field_74948_i)
                    {
                        this.field_74948_i = this.func_74879_a(p_74875_1_, p_74875_3_, p_74875_2_, 9, -3, 10, WeightedRandomChestContent.func_92080_a(field_74943_l, new WeightedRandomChestContent[] {Items.field_151134_bR.func_92114_b(p_74875_2_)}), 2 + p_74875_2_.nextInt(5));
                    }

                    return true;
                }
            }

            static class Stones extends StructureComponent.BlockSelector
                {
                    private static final String __OBFID = "CL_00000478";

                    private Stones() {}

                    public void func_75062_a(Random p_75062_1_, int p_75062_2_, int p_75062_3_, int p_75062_4_, boolean p_75062_5_)
                    {
                        if (p_75062_1_.nextFloat() < 0.4F)
                        {
                            this.field_151562_a = Blocks.field_150347_e;
                        }
                        else
                        {
                            this.field_151562_a = Blocks.field_150341_Y;
                        }
                    }

                    Stones(Object p_i2063_1_)
                    {
                        this();
                    }
                }
        }

    public static class SwampHut extends ComponentScatteredFeaturePieces.Feature
        {
            private boolean field_82682_h;
            private static final String __OBFID = "CL_00000480";

            public SwampHut() {}

            public SwampHut(Random p_i2066_1_, int p_i2066_2_, int p_i2066_3_)
            {
                super(p_i2066_1_, p_i2066_2_, 64, p_i2066_3_, 7, 5, 9);
            }

            protected void func_143012_a(NBTTagCompound p_143012_1_)
            {
                super.func_143012_a(p_143012_1_);
                p_143012_1_.func_74757_a("Witch", this.field_82682_h);
            }

            protected void func_143011_b(NBTTagCompound p_143011_1_)
            {
                super.func_143011_b(p_143011_1_);
                this.field_82682_h = p_143011_1_.func_74767_n("Witch");
            }

            public boolean func_74875_a(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_)
            {
                if (!this.func_74935_a(p_74875_1_, p_74875_3_, 0))
                {
                    return false;
                }
                else
                {
                    this.func_151556_a(p_74875_1_, p_74875_3_, 1, 1, 1, 5, 1, 7, Blocks.field_150344_f, 1, Blocks.field_150344_f, 1, false);
                    this.func_151556_a(p_74875_1_, p_74875_3_, 1, 4, 2, 5, 4, 7, Blocks.field_150344_f, 1, Blocks.field_150344_f, 1, false);
                    this.func_151556_a(p_74875_1_, p_74875_3_, 2, 1, 0, 4, 1, 0, Blocks.field_150344_f, 1, Blocks.field_150344_f, 1, false);
                    this.func_151556_a(p_74875_1_, p_74875_3_, 2, 2, 2, 3, 3, 2, Blocks.field_150344_f, 1, Blocks.field_150344_f, 1, false);
                    this.func_151556_a(p_74875_1_, p_74875_3_, 1, 2, 3, 1, 3, 6, Blocks.field_150344_f, 1, Blocks.field_150344_f, 1, false);
                    this.func_151556_a(p_74875_1_, p_74875_3_, 5, 2, 3, 5, 3, 6, Blocks.field_150344_f, 1, Blocks.field_150344_f, 1, false);
                    this.func_151556_a(p_74875_1_, p_74875_3_, 2, 2, 7, 4, 3, 7, Blocks.field_150344_f, 1, Blocks.field_150344_f, 1, false);
                    this.func_151549_a(p_74875_1_, p_74875_3_, 1, 0, 2, 1, 3, 2, Blocks.field_150364_r, Blocks.field_150364_r, false);
                    this.func_151549_a(p_74875_1_, p_74875_3_, 5, 0, 2, 5, 3, 2, Blocks.field_150364_r, Blocks.field_150364_r, false);
                    this.func_151549_a(p_74875_1_, p_74875_3_, 1, 0, 7, 1, 3, 7, Blocks.field_150364_r, Blocks.field_150364_r, false);
                    this.func_151549_a(p_74875_1_, p_74875_3_, 5, 0, 7, 5, 3, 7, Blocks.field_150364_r, Blocks.field_150364_r, false);
                    this.func_151550_a(p_74875_1_, Blocks.field_150422_aJ, 0, 2, 3, 2, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150422_aJ, 0, 3, 3, 7, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150350_a, 0, 1, 3, 4, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150350_a, 0, 5, 3, 4, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150350_a, 0, 5, 3, 5, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150457_bL, 7, 1, 3, 5, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150462_ai, 0, 3, 2, 6, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150383_bp, 0, 4, 2, 6, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150422_aJ, 0, 1, 2, 1, p_74875_3_);
                    this.func_151550_a(p_74875_1_, Blocks.field_150422_aJ, 0, 5, 2, 1, p_74875_3_);
                    int i = this.func_151555_a(Blocks.field_150476_ad, 3);
                    int j = this.func_151555_a(Blocks.field_150476_ad, 1);
                    int k = this.func_151555_a(Blocks.field_150476_ad, 0);
                    int l = this.func_151555_a(Blocks.field_150476_ad, 2);
                    this.func_151556_a(p_74875_1_, p_74875_3_, 0, 4, 1, 6, 4, 1, Blocks.field_150485_bF, i, Blocks.field_150485_bF, i, false);
                    this.func_151556_a(p_74875_1_, p_74875_3_, 0, 4, 2, 0, 4, 7, Blocks.field_150485_bF, k, Blocks.field_150485_bF, k, false);
                    this.func_151556_a(p_74875_1_, p_74875_3_, 6, 4, 2, 6, 4, 7, Blocks.field_150485_bF, j, Blocks.field_150485_bF, j, false);
                    this.func_151556_a(p_74875_1_, p_74875_3_, 0, 4, 8, 6, 4, 8, Blocks.field_150485_bF, l, Blocks.field_150485_bF, l, false);
                    int i1;
                    int j1;

                    for (i1 = 2; i1 <= 7; i1 += 5)
                    {
                        for (j1 = 1; j1 <= 5; j1 += 4)
                        {
                            this.func_151554_b(p_74875_1_, Blocks.field_150364_r, 0, j1, -1, i1, p_74875_3_);
                        }
                    }

                    if (!this.field_82682_h)
                    {
                        i1 = this.func_74865_a(2, 5);
                        j1 = this.func_74862_a(2);
                        int k1 = this.func_74873_b(2, 5);

                        if (p_74875_3_.func_78890_b(i1, j1, k1))
                        {
                            this.field_82682_h = true;
                            EntityWitch entitywitch = new EntityWitch(p_74875_1_);
                            entitywitch.func_70012_b((double)i1 + 0.5D, (double)j1, (double)k1 + 0.5D, 0.0F, 0.0F);
                            entitywitch.func_110161_a((IEntityLivingData)null);
                            p_74875_1_.func_72838_d(entitywitch);
                        }
                    }

                    return true;
                }
            }
        }
}