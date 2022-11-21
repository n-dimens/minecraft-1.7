package net.minecraft.world.gen.structure;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemDoor;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.util.Direction;
import net.minecraft.util.Facing;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;

public abstract class StructureComponent
{
    protected StructureBoundingBox field_74887_e;
    protected int field_74885_f;
    protected int field_74886_g;
    private static final String __OBFID = "CL_00000511";

    public StructureComponent() {}

    protected StructureComponent(int p_i2091_1_)
    {
        this.field_74886_g = p_i2091_1_;
        this.field_74885_f = -1;
    }

    public NBTTagCompound func_143010_b()
    {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        nbttagcompound.func_74778_a("id", MapGenStructureIO.func_143036_a(this));
        nbttagcompound.func_74782_a("BB", this.field_74887_e.func_151535_h());
        nbttagcompound.func_74768_a("O", this.field_74885_f);
        nbttagcompound.func_74768_a("GD", this.field_74886_g);
        this.func_143012_a(nbttagcompound);
        return nbttagcompound;
    }

    protected abstract void func_143012_a(NBTTagCompound p_143012_1_);

    public void func_143009_a(World p_143009_1_, NBTTagCompound p_143009_2_)
    {
        if (p_143009_2_.func_74764_b("BB"))
        {
            this.field_74887_e = new StructureBoundingBox(p_143009_2_.func_74759_k("BB"));
        }

        this.field_74885_f = p_143009_2_.func_74762_e("O");
        this.field_74886_g = p_143009_2_.func_74762_e("GD");
        this.func_143011_b(p_143009_2_);
    }

    protected abstract void func_143011_b(NBTTagCompound p_143011_1_);

    public void func_74861_a(StructureComponent p_74861_1_, List p_74861_2_, Random p_74861_3_) {}

    public abstract boolean func_74875_a(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_);

    public StructureBoundingBox func_74874_b()
    {
        return this.field_74887_e;
    }

    public int func_74877_c()
    {
        return this.field_74886_g;
    }

    public static StructureComponent func_74883_a(List p_74883_0_, StructureBoundingBox p_74883_1_)
    {
        Iterator iterator = p_74883_0_.iterator();
        StructureComponent structurecomponent;

        do
        {
            if (!iterator.hasNext())
            {
                return null;
            }

            structurecomponent = (StructureComponent)iterator.next();
        }
        while (structurecomponent.func_74874_b() == null || !structurecomponent.func_74874_b().func_78884_a(p_74883_1_));

        return structurecomponent;
    }

    public ChunkPosition func_151553_a()
    {
        return new ChunkPosition(this.field_74887_e.func_78881_e(), this.field_74887_e.func_78879_f(), this.field_74887_e.func_78891_g());
    }

    protected boolean func_74860_a(World p_74860_1_, StructureBoundingBox p_74860_2_)
    {
        int i = Math.max(this.field_74887_e.field_78897_a - 1, p_74860_2_.field_78897_a);
        int j = Math.max(this.field_74887_e.field_78895_b - 1, p_74860_2_.field_78895_b);
        int k = Math.max(this.field_74887_e.field_78896_c - 1, p_74860_2_.field_78896_c);
        int l = Math.min(this.field_74887_e.field_78893_d + 1, p_74860_2_.field_78893_d);
        int i1 = Math.min(this.field_74887_e.field_78894_e + 1, p_74860_2_.field_78894_e);
        int j1 = Math.min(this.field_74887_e.field_78892_f + 1, p_74860_2_.field_78892_f);
        int k1;
        int l1;

        for (k1 = i; k1 <= l; ++k1)
        {
            for (l1 = k; l1 <= j1; ++l1)
            {
                if (p_74860_1_.func_147439_a(k1, j, l1).func_149688_o().func_76224_d())
                {
                    return true;
                }

                if (p_74860_1_.func_147439_a(k1, i1, l1).func_149688_o().func_76224_d())
                {
                    return true;
                }
            }
        }

        for (k1 = i; k1 <= l; ++k1)
        {
            for (l1 = j; l1 <= i1; ++l1)
            {
                if (p_74860_1_.func_147439_a(k1, l1, k).func_149688_o().func_76224_d())
                {
                    return true;
                }

                if (p_74860_1_.func_147439_a(k1, l1, j1).func_149688_o().func_76224_d())
                {
                    return true;
                }
            }
        }

        for (k1 = k; k1 <= j1; ++k1)
        {
            for (l1 = j; l1 <= i1; ++l1)
            {
                if (p_74860_1_.func_147439_a(i, l1, k1).func_149688_o().func_76224_d())
                {
                    return true;
                }

                if (p_74860_1_.func_147439_a(l, l1, k1).func_149688_o().func_76224_d())
                {
                    return true;
                }
            }
        }

        return false;
    }

    protected int func_74865_a(int p_74865_1_, int p_74865_2_)
    {
        switch (this.field_74885_f)
        {
            case 0:
            case 2:
                return this.field_74887_e.field_78897_a + p_74865_1_;
            case 1:
                return this.field_74887_e.field_78893_d - p_74865_2_;
            case 3:
                return this.field_74887_e.field_78897_a + p_74865_2_;
            default:
                return p_74865_1_;
        }
    }

    protected int func_74862_a(int p_74862_1_)
    {
        return this.field_74885_f == -1 ? p_74862_1_ : p_74862_1_ + this.field_74887_e.field_78895_b;
    }

    protected int func_74873_b(int p_74873_1_, int p_74873_2_)
    {
        switch (this.field_74885_f)
        {
            case 0:
                return this.field_74887_e.field_78896_c + p_74873_2_;
            case 1:
            case 3:
                return this.field_74887_e.field_78896_c + p_74873_1_;
            case 2:
                return this.field_74887_e.field_78892_f - p_74873_2_;
            default:
                return p_74873_2_;
        }
    }

    protected int func_151555_a(Block p_151555_1_, int p_151555_2_)
    {
        if (p_151555_1_ == Blocks.field_150448_aq)
        {
            if (this.field_74885_f == 1 || this.field_74885_f == 3)
            {
                if (p_151555_2_ == 1)
                {
                    return 0;
                }

                return 1;
            }
        }
        else if (p_151555_1_ != Blocks.field_150466_ao && p_151555_1_ != Blocks.field_150454_av)
        {
            if (p_151555_1_ != Blocks.field_150446_ar && p_151555_1_ != Blocks.field_150476_ad && p_151555_1_ != Blocks.field_150387_bl && p_151555_1_ != Blocks.field_150390_bg && p_151555_1_ != Blocks.field_150372_bz)
            {
                if (p_151555_1_ == Blocks.field_150468_ap)
                {
                    if (this.field_74885_f == 0)
                    {
                        if (p_151555_2_ == 2)
                        {
                            return 3;
                        }

                        if (p_151555_2_ == 3)
                        {
                            return 2;
                        }
                    }
                    else if (this.field_74885_f == 1)
                    {
                        if (p_151555_2_ == 2)
                        {
                            return 4;
                        }

                        if (p_151555_2_ == 3)
                        {
                            return 5;
                        }

                        if (p_151555_2_ == 4)
                        {
                            return 2;
                        }

                        if (p_151555_2_ == 5)
                        {
                            return 3;
                        }
                    }
                    else if (this.field_74885_f == 3)
                    {
                        if (p_151555_2_ == 2)
                        {
                            return 5;
                        }

                        if (p_151555_2_ == 3)
                        {
                            return 4;
                        }

                        if (p_151555_2_ == 4)
                        {
                            return 2;
                        }

                        if (p_151555_2_ == 5)
                        {
                            return 3;
                        }
                    }
                }
                else if (p_151555_1_ == Blocks.field_150430_aB)
                {
                    if (this.field_74885_f == 0)
                    {
                        if (p_151555_2_ == 3)
                        {
                            return 4;
                        }

                        if (p_151555_2_ == 4)
                        {
                            return 3;
                        }
                    }
                    else if (this.field_74885_f == 1)
                    {
                        if (p_151555_2_ == 3)
                        {
                            return 1;
                        }

                        if (p_151555_2_ == 4)
                        {
                            return 2;
                        }

                        if (p_151555_2_ == 2)
                        {
                            return 3;
                        }

                        if (p_151555_2_ == 1)
                        {
                            return 4;
                        }
                    }
                    else if (this.field_74885_f == 3)
                    {
                        if (p_151555_2_ == 3)
                        {
                            return 2;
                        }

                        if (p_151555_2_ == 4)
                        {
                            return 1;
                        }

                        if (p_151555_2_ == 2)
                        {
                            return 3;
                        }

                        if (p_151555_2_ == 1)
                        {
                            return 4;
                        }
                    }
                }
                else if (p_151555_1_ != Blocks.field_150479_bC && !(p_151555_1_ instanceof BlockDirectional))
                {
                    if (p_151555_1_ == Blocks.field_150331_J || p_151555_1_ == Blocks.field_150320_F || p_151555_1_ == Blocks.field_150442_at || p_151555_1_ == Blocks.field_150367_z)
                    {
                        if (this.field_74885_f == 0)
                        {
                            if (p_151555_2_ == 2 || p_151555_2_ == 3)
                            {
                                return Facing.field_71588_a[p_151555_2_];
                            }
                        }
                        else if (this.field_74885_f == 1)
                        {
                            if (p_151555_2_ == 2)
                            {
                                return 4;
                            }

                            if (p_151555_2_ == 3)
                            {
                                return 5;
                            }

                            if (p_151555_2_ == 4)
                            {
                                return 2;
                            }

                            if (p_151555_2_ == 5)
                            {
                                return 3;
                            }
                        }
                        else if (this.field_74885_f == 3)
                        {
                            if (p_151555_2_ == 2)
                            {
                                return 5;
                            }

                            if (p_151555_2_ == 3)
                            {
                                return 4;
                            }

                            if (p_151555_2_ == 4)
                            {
                                return 2;
                            }

                            if (p_151555_2_ == 5)
                            {
                                return 3;
                            }
                        }
                    }
                }
                else if (this.field_74885_f == 0)
                {
                    if (p_151555_2_ == 0 || p_151555_2_ == 2)
                    {
                        return Direction.field_71580_e[p_151555_2_];
                    }
                }
                else if (this.field_74885_f == 1)
                {
                    if (p_151555_2_ == 2)
                    {
                        return 1;
                    }

                    if (p_151555_2_ == 0)
                    {
                        return 3;
                    }

                    if (p_151555_2_ == 1)
                    {
                        return 2;
                    }

                    if (p_151555_2_ == 3)
                    {
                        return 0;
                    }
                }
                else if (this.field_74885_f == 3)
                {
                    if (p_151555_2_ == 2)
                    {
                        return 3;
                    }

                    if (p_151555_2_ == 0)
                    {
                        return 1;
                    }

                    if (p_151555_2_ == 1)
                    {
                        return 2;
                    }

                    if (p_151555_2_ == 3)
                    {
                        return 0;
                    }
                }
            }
            else if (this.field_74885_f == 0)
            {
                if (p_151555_2_ == 2)
                {
                    return 3;
                }

                if (p_151555_2_ == 3)
                {
                    return 2;
                }
            }
            else if (this.field_74885_f == 1)
            {
                if (p_151555_2_ == 0)
                {
                    return 2;
                }

                if (p_151555_2_ == 1)
                {
                    return 3;
                }

                if (p_151555_2_ == 2)
                {
                    return 0;
                }

                if (p_151555_2_ == 3)
                {
                    return 1;
                }
            }
            else if (this.field_74885_f == 3)
            {
                if (p_151555_2_ == 0)
                {
                    return 2;
                }

                if (p_151555_2_ == 1)
                {
                    return 3;
                }

                if (p_151555_2_ == 2)
                {
                    return 1;
                }

                if (p_151555_2_ == 3)
                {
                    return 0;
                }
            }
        }
        else if (this.field_74885_f == 0)
        {
            if (p_151555_2_ == 0)
            {
                return 2;
            }

            if (p_151555_2_ == 2)
            {
                return 0;
            }
        }
        else
        {
            if (this.field_74885_f == 1)
            {
                return p_151555_2_ + 1 & 3;
            }

            if (this.field_74885_f == 3)
            {
                return p_151555_2_ + 3 & 3;
            }
        }

        return p_151555_2_;
    }

    protected void func_151550_a(World p_151550_1_, Block p_151550_2_, int p_151550_3_, int p_151550_4_, int p_151550_5_, int p_151550_6_, StructureBoundingBox p_151550_7_)
    {
        int i1 = this.func_74865_a(p_151550_4_, p_151550_6_);
        int j1 = this.func_74862_a(p_151550_5_);
        int k1 = this.func_74873_b(p_151550_4_, p_151550_6_);

        if (p_151550_7_.func_78890_b(i1, j1, k1))
        {
            p_151550_1_.func_147465_d(i1, j1, k1, p_151550_2_, p_151550_3_, 2);
        }
    }

    protected Block func_151548_a(World p_151548_1_, int p_151548_2_, int p_151548_3_, int p_151548_4_, StructureBoundingBox p_151548_5_)
    {
        int l = this.func_74865_a(p_151548_2_, p_151548_4_);
        int i1 = this.func_74862_a(p_151548_3_);
        int j1 = this.func_74873_b(p_151548_2_, p_151548_4_);
        return !p_151548_5_.func_78890_b(l, i1, j1) ? Blocks.field_150350_a : p_151548_1_.func_147439_a(l, i1, j1);
    }

    protected void func_74878_a(World p_74878_1_, StructureBoundingBox p_74878_2_, int p_74878_3_, int p_74878_4_, int p_74878_5_, int p_74878_6_, int p_74878_7_, int p_74878_8_)
    {
        for (int k1 = p_74878_4_; k1 <= p_74878_7_; ++k1)
        {
            for (int l1 = p_74878_3_; l1 <= p_74878_6_; ++l1)
            {
                for (int i2 = p_74878_5_; i2 <= p_74878_8_; ++i2)
                {
                    this.func_151550_a(p_74878_1_, Blocks.field_150350_a, 0, l1, k1, i2, p_74878_2_);
                }
            }
        }
    }

    protected void func_151549_a(World p_151549_1_, StructureBoundingBox p_151549_2_, int p_151549_3_, int p_151549_4_, int p_151549_5_, int p_151549_6_, int p_151549_7_, int p_151549_8_, Block p_151549_9_, Block p_151549_10_, boolean p_151549_11_)
    {
        for (int k1 = p_151549_4_; k1 <= p_151549_7_; ++k1)
        {
            for (int l1 = p_151549_3_; l1 <= p_151549_6_; ++l1)
            {
                for (int i2 = p_151549_5_; i2 <= p_151549_8_; ++i2)
                {
                    if (!p_151549_11_ || this.func_151548_a(p_151549_1_, l1, k1, i2, p_151549_2_).func_149688_o() != Material.field_151579_a)
                    {
                        if (k1 != p_151549_4_ && k1 != p_151549_7_ && l1 != p_151549_3_ && l1 != p_151549_6_ && i2 != p_151549_5_ && i2 != p_151549_8_)
                        {
                            this.func_151550_a(p_151549_1_, p_151549_10_, 0, l1, k1, i2, p_151549_2_);
                        }
                        else
                        {
                            this.func_151550_a(p_151549_1_, p_151549_9_, 0, l1, k1, i2, p_151549_2_);
                        }
                    }
                }
            }
        }
    }

    protected void func_151556_a(World p_151556_1_, StructureBoundingBox p_151556_2_, int p_151556_3_, int p_151556_4_, int p_151556_5_, int p_151556_6_, int p_151556_7_, int p_151556_8_, Block p_151556_9_, int p_151556_10_, Block p_151556_11_, int p_151556_12_, boolean p_151556_13_)
    {
        for (int i2 = p_151556_4_; i2 <= p_151556_7_; ++i2)
        {
            for (int j2 = p_151556_3_; j2 <= p_151556_6_; ++j2)
            {
                for (int k2 = p_151556_5_; k2 <= p_151556_8_; ++k2)
                {
                    if (!p_151556_13_ || this.func_151548_a(p_151556_1_, j2, i2, k2, p_151556_2_).func_149688_o() != Material.field_151579_a)
                    {
                        if (i2 != p_151556_4_ && i2 != p_151556_7_ && j2 != p_151556_3_ && j2 != p_151556_6_ && k2 != p_151556_5_ && k2 != p_151556_8_)
                        {
                            this.func_151550_a(p_151556_1_, p_151556_11_, p_151556_12_, j2, i2, k2, p_151556_2_);
                        }
                        else
                        {
                            this.func_151550_a(p_151556_1_, p_151556_9_, p_151556_10_, j2, i2, k2, p_151556_2_);
                        }
                    }
                }
            }
        }
    }

    protected void func_74882_a(World p_74882_1_, StructureBoundingBox p_74882_2_, int p_74882_3_, int p_74882_4_, int p_74882_5_, int p_74882_6_, int p_74882_7_, int p_74882_8_, boolean p_74882_9_, Random p_74882_10_, StructureComponent.BlockSelector p_74882_11_)
    {
        for (int k1 = p_74882_4_; k1 <= p_74882_7_; ++k1)
        {
            for (int l1 = p_74882_3_; l1 <= p_74882_6_; ++l1)
            {
                for (int i2 = p_74882_5_; i2 <= p_74882_8_; ++i2)
                {
                    if (!p_74882_9_ || this.func_151548_a(p_74882_1_, l1, k1, i2, p_74882_2_).func_149688_o() != Material.field_151579_a)
                    {
                        p_74882_11_.func_75062_a(p_74882_10_, l1, k1, i2, k1 == p_74882_4_ || k1 == p_74882_7_ || l1 == p_74882_3_ || l1 == p_74882_6_ || i2 == p_74882_5_ || i2 == p_74882_8_);
                        this.func_151550_a(p_74882_1_, p_74882_11_.func_151561_a(), p_74882_11_.func_75064_b(), l1, k1, i2, p_74882_2_);
                    }
                }
            }
        }
    }

    protected void func_151551_a(World p_151551_1_, StructureBoundingBox p_151551_2_, Random p_151551_3_, float p_151551_4_, int p_151551_5_, int p_151551_6_, int p_151551_7_, int p_151551_8_, int p_151551_9_, int p_151551_10_, Block p_151551_11_, Block p_151551_12_, boolean p_151551_13_)
    {
        for (int k1 = p_151551_6_; k1 <= p_151551_9_; ++k1)
        {
            for (int l1 = p_151551_5_; l1 <= p_151551_8_; ++l1)
            {
                for (int i2 = p_151551_7_; i2 <= p_151551_10_; ++i2)
                {
                    if (p_151551_3_.nextFloat() <= p_151551_4_ && (!p_151551_13_ || this.func_151548_a(p_151551_1_, l1, k1, i2, p_151551_2_).func_149688_o() != Material.field_151579_a))
                    {
                        if (k1 != p_151551_6_ && k1 != p_151551_9_ && l1 != p_151551_5_ && l1 != p_151551_8_ && i2 != p_151551_7_ && i2 != p_151551_10_)
                        {
                            this.func_151550_a(p_151551_1_, p_151551_12_, 0, l1, k1, i2, p_151551_2_);
                        }
                        else
                        {
                            this.func_151550_a(p_151551_1_, p_151551_11_, 0, l1, k1, i2, p_151551_2_);
                        }
                    }
                }
            }
        }
    }

    protected void func_151552_a(World p_151552_1_, StructureBoundingBox p_151552_2_, Random p_151552_3_, float p_151552_4_, int p_151552_5_, int p_151552_6_, int p_151552_7_, Block p_151552_8_, int p_151552_9_)
    {
        if (p_151552_3_.nextFloat() < p_151552_4_)
        {
            this.func_151550_a(p_151552_1_, p_151552_8_, p_151552_9_, p_151552_5_, p_151552_6_, p_151552_7_, p_151552_2_);
        }
    }

    protected void func_151547_a(World p_151547_1_, StructureBoundingBox p_151547_2_, int p_151547_3_, int p_151547_4_, int p_151547_5_, int p_151547_6_, int p_151547_7_, int p_151547_8_, Block p_151547_9_, boolean p_151547_10_)
    {
        float f = (float)(p_151547_6_ - p_151547_3_ + 1);
        float f1 = (float)(p_151547_7_ - p_151547_4_ + 1);
        float f2 = (float)(p_151547_8_ - p_151547_5_ + 1);
        float f3 = (float)p_151547_3_ + f / 2.0F;
        float f4 = (float)p_151547_5_ + f2 / 2.0F;

        for (int k1 = p_151547_4_; k1 <= p_151547_7_; ++k1)
        {
            float f5 = (float)(k1 - p_151547_4_) / f1;

            for (int l1 = p_151547_3_; l1 <= p_151547_6_; ++l1)
            {
                float f6 = ((float)l1 - f3) / (f * 0.5F);

                for (int i2 = p_151547_5_; i2 <= p_151547_8_; ++i2)
                {
                    float f7 = ((float)i2 - f4) / (f2 * 0.5F);

                    if (!p_151547_10_ || this.func_151548_a(p_151547_1_, l1, k1, i2, p_151547_2_).func_149688_o() != Material.field_151579_a)
                    {
                        float f8 = f6 * f6 + f5 * f5 + f7 * f7;

                        if (f8 <= 1.05F)
                        {
                            this.func_151550_a(p_151547_1_, p_151547_9_, 0, l1, k1, i2, p_151547_2_);
                        }
                    }
                }
            }
        }
    }

    protected void func_74871_b(World p_74871_1_, int p_74871_2_, int p_74871_3_, int p_74871_4_, StructureBoundingBox p_74871_5_)
    {
        int l = this.func_74865_a(p_74871_2_, p_74871_4_);
        int i1 = this.func_74862_a(p_74871_3_);
        int j1 = this.func_74873_b(p_74871_2_, p_74871_4_);

        if (p_74871_5_.func_78890_b(l, i1, j1))
        {
            while (!p_74871_1_.func_147437_c(l, i1, j1) && i1 < 255)
            {
                p_74871_1_.func_147465_d(l, i1, j1, Blocks.field_150350_a, 0, 2);
                ++i1;
            }
        }
    }

    protected void func_151554_b(World p_151554_1_, Block p_151554_2_, int p_151554_3_, int p_151554_4_, int p_151554_5_, int p_151554_6_, StructureBoundingBox p_151554_7_)
    {
        int i1 = this.func_74865_a(p_151554_4_, p_151554_6_);
        int j1 = this.func_74862_a(p_151554_5_);
        int k1 = this.func_74873_b(p_151554_4_, p_151554_6_);

        if (p_151554_7_.func_78890_b(i1, j1, k1))
        {
            while ((p_151554_1_.func_147437_c(i1, j1, k1) || p_151554_1_.func_147439_a(i1, j1, k1).func_149688_o().func_76224_d()) && j1 > 1)
            {
                p_151554_1_.func_147465_d(i1, j1, k1, p_151554_2_, p_151554_3_, 2);
                --j1;
            }
        }
    }

    protected boolean func_74879_a(World p_74879_1_, StructureBoundingBox p_74879_2_, Random p_74879_3_, int p_74879_4_, int p_74879_5_, int p_74879_6_, WeightedRandomChestContent[] p_74879_7_, int p_74879_8_)
    {
        int i1 = this.func_74865_a(p_74879_4_, p_74879_6_);
        int j1 = this.func_74862_a(p_74879_5_);
        int k1 = this.func_74873_b(p_74879_4_, p_74879_6_);

        if (p_74879_2_.func_78890_b(i1, j1, k1) && p_74879_1_.func_147439_a(i1, j1, k1) != Blocks.field_150486_ae)
        {
            p_74879_1_.func_147465_d(i1, j1, k1, Blocks.field_150486_ae, 0, 2);
            TileEntityChest tileentitychest = (TileEntityChest)p_74879_1_.func_147438_o(i1, j1, k1);

            if (tileentitychest != null)
            {
                WeightedRandomChestContent.func_76293_a(p_74879_3_, p_74879_7_, tileentitychest, p_74879_8_);
            }

            return true;
        }
        else
        {
            return false;
        }
    }

    protected boolean func_74869_a(World p_74869_1_, StructureBoundingBox p_74869_2_, Random p_74869_3_, int p_74869_4_, int p_74869_5_, int p_74869_6_, int p_74869_7_, WeightedRandomChestContent[] p_74869_8_, int p_74869_9_)
    {
        int j1 = this.func_74865_a(p_74869_4_, p_74869_6_);
        int k1 = this.func_74862_a(p_74869_5_);
        int l1 = this.func_74873_b(p_74869_4_, p_74869_6_);

        if (p_74869_2_.func_78890_b(j1, k1, l1) && p_74869_1_.func_147439_a(j1, k1, l1) != Blocks.field_150367_z)
        {
            p_74869_1_.func_147465_d(j1, k1, l1, Blocks.field_150367_z, this.func_151555_a(Blocks.field_150367_z, p_74869_7_), 2);
            TileEntityDispenser tileentitydispenser = (TileEntityDispenser)p_74869_1_.func_147438_o(j1, k1, l1);

            if (tileentitydispenser != null)
            {
                WeightedRandomChestContent.func_150706_a(p_74869_3_, p_74869_8_, tileentitydispenser, p_74869_9_);
            }

            return true;
        }
        else
        {
            return false;
        }
    }

    protected void func_74881_a(World p_74881_1_, StructureBoundingBox p_74881_2_, Random p_74881_3_, int p_74881_4_, int p_74881_5_, int p_74881_6_, int p_74881_7_)
    {
        int i1 = this.func_74865_a(p_74881_4_, p_74881_6_);
        int j1 = this.func_74862_a(p_74881_5_);
        int k1 = this.func_74873_b(p_74881_4_, p_74881_6_);

        if (p_74881_2_.func_78890_b(i1, j1, k1))
        {
            ItemDoor.func_150924_a(p_74881_1_, i1, j1, k1, p_74881_7_, Blocks.field_150466_ao);
        }
    }

    public abstract static class BlockSelector
        {
            protected Block field_151562_a;
            protected int field_75065_b;
            private static final String __OBFID = "CL_00000512";

            protected BlockSelector()
            {
                this.field_151562_a = Blocks.field_150350_a;
            }

            public abstract void func_75062_a(Random p_75062_1_, int p_75062_2_, int p_75062_3_, int p_75062_4_, boolean p_75062_5_);

            public Block func_151561_a()
            {
                return this.field_151562_a;
            }

            public int func_75064_b()
            {
                return this.field_75065_b;
            }
        }
}