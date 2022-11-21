package net.minecraft.village;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.BlockDoor;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;

public class VillageCollection extends WorldSavedData
{
    private World field_75556_a;
    private final List field_75554_b = new ArrayList();
    private final List field_75555_c = new ArrayList();
    private final List field_75552_d = new ArrayList();
    private int field_75553_e;
    private static final String __OBFID = "CL_00001635";

    public VillageCollection(String p_i1677_1_)
    {
        super(p_i1677_1_);
    }

    public VillageCollection(World p_i1678_1_)
    {
        super("villages");
        this.field_75556_a = p_i1678_1_;
        this.func_76185_a();
    }

    public void func_82566_a(World p_82566_1_)
    {
        this.field_75556_a = p_82566_1_;
        Iterator iterator = this.field_75552_d.iterator();

        while (iterator.hasNext())
        {
            Village village = (Village)iterator.next();
            village.func_82691_a(p_82566_1_);
        }
    }

    public void func_75551_a(int p_75551_1_, int p_75551_2_, int p_75551_3_)
    {
        if (this.field_75554_b.size() <= 64)
        {
            if (!this.func_75548_d(p_75551_1_, p_75551_2_, p_75551_3_))
            {
                this.field_75554_b.add(new ChunkCoordinates(p_75551_1_, p_75551_2_, p_75551_3_));
            }
        }
    }

    public void func_75544_a()
    {
        ++this.field_75553_e;
        Iterator iterator = this.field_75552_d.iterator();

        while (iterator.hasNext())
        {
            Village village = (Village)iterator.next();
            village.func_75560_a(this.field_75553_e);
        }

        this.func_75549_c();
        this.func_75543_d();
        this.func_75545_e();

        if (this.field_75553_e % 400 == 0)
        {
            this.func_76185_a();
        }
    }

    private void func_75549_c()
    {
        Iterator iterator = this.field_75552_d.iterator();

        while (iterator.hasNext())
        {
            Village village = (Village)iterator.next();

            if (village.func_75566_g())
            {
                iterator.remove();
                this.func_76185_a();
            }
        }
    }

    public List func_75540_b()
    {
        return this.field_75552_d;
    }

    public Village func_75550_a(int p_75550_1_, int p_75550_2_, int p_75550_3_, int p_75550_4_)
    {
        Village village = null;
        float f = Float.MAX_VALUE;
        Iterator iterator = this.field_75552_d.iterator();

        while (iterator.hasNext())
        {
            Village village1 = (Village)iterator.next();
            float f1 = village1.func_75577_a().func_71569_e(p_75550_1_, p_75550_2_, p_75550_3_);

            if (f1 < f)
            {
                float f2 = (float)(p_75550_4_ + village1.func_75568_b());

                if (f1 <= f2 * f2)
                {
                    village = village1;
                    f = f1;
                }
            }
        }

        return village;
    }

    private void func_75543_d()
    {
        if (!this.field_75554_b.isEmpty())
        {
            this.func_75546_a((ChunkCoordinates)this.field_75554_b.remove(0));
        }
    }

    private void func_75545_e()
    {
        int i = 0;

        while (i < this.field_75555_c.size())
        {
            VillageDoorInfo villagedoorinfo = (VillageDoorInfo)this.field_75555_c.get(i);
            boolean flag = false;
            Iterator iterator = this.field_75552_d.iterator();

            while (true)
            {
                if (iterator.hasNext())
                {
                    Village village = (Village)iterator.next();
                    int j = (int)village.func_75577_a().func_71569_e(villagedoorinfo.field_75481_a, villagedoorinfo.field_75479_b, villagedoorinfo.field_75480_c);
                    int k = 32 + village.func_75568_b();

                    if (j > k * k)
                    {
                        continue;
                    }

                    village.func_75576_a(villagedoorinfo);
                    flag = true;
                }

                if (!flag)
                {
                    Village village1 = new Village(this.field_75556_a);
                    village1.func_75576_a(villagedoorinfo);
                    this.field_75552_d.add(village1);
                    this.func_76185_a();
                }

                ++i;
                break;
            }
        }

        this.field_75555_c.clear();
    }

    private void func_75546_a(ChunkCoordinates p_75546_1_)
    {
        byte b0 = 16;
        byte b1 = 4;
        byte b2 = 16;

        for (int i = p_75546_1_.field_71574_a - b0; i < p_75546_1_.field_71574_a + b0; ++i)
        {
            for (int j = p_75546_1_.field_71572_b - b1; j < p_75546_1_.field_71572_b + b1; ++j)
            {
                for (int k = p_75546_1_.field_71573_c - b2; k < p_75546_1_.field_71573_c + b2; ++k)
                {
                    if (this.func_75541_e(i, j, k))
                    {
                        VillageDoorInfo villagedoorinfo = this.func_75547_b(i, j, k);

                        if (villagedoorinfo == null)
                        {
                            this.func_75542_c(i, j, k);
                        }
                        else
                        {
                            villagedoorinfo.field_75475_f = this.field_75553_e;
                        }
                    }
                }
            }
        }
    }

    private VillageDoorInfo func_75547_b(int p_75547_1_, int p_75547_2_, int p_75547_3_)
    {
        Iterator iterator = this.field_75555_c.iterator();
        VillageDoorInfo villagedoorinfo;

        do
        {
            if (!iterator.hasNext())
            {
                iterator = this.field_75552_d.iterator();
                VillageDoorInfo villagedoorinfo1;

                do
                {
                    if (!iterator.hasNext())
                    {
                        return null;
                    }

                    Village village = (Village)iterator.next();
                    villagedoorinfo1 = village.func_75578_e(p_75547_1_, p_75547_2_, p_75547_3_);
                }
                while (villagedoorinfo1 == null);

                return villagedoorinfo1;
            }

            villagedoorinfo = (VillageDoorInfo)iterator.next();
        }
        while (villagedoorinfo.field_75481_a != p_75547_1_ || villagedoorinfo.field_75480_c != p_75547_3_ || Math.abs(villagedoorinfo.field_75479_b - p_75547_2_) > 1);

        return villagedoorinfo;
    }

    private void func_75542_c(int p_75542_1_, int p_75542_2_, int p_75542_3_)
    {
        int l = ((BlockDoor)Blocks.WOODEN_DOOR).func_150013_e(this.field_75556_a, p_75542_1_, p_75542_2_, p_75542_3_);
        int i1;
        int j1;

        if (l != 0 && l != 2)
        {
            i1 = 0;

            for (j1 = -5; j1 < 0; ++j1)
            {
                if (this.field_75556_a.func_72937_j(p_75542_1_, p_75542_2_, p_75542_3_ + j1))
                {
                    --i1;
                }
            }

            for (j1 = 1; j1 <= 5; ++j1)
            {
                if (this.field_75556_a.func_72937_j(p_75542_1_, p_75542_2_, p_75542_3_ + j1))
                {
                    ++i1;
                }
            }

            if (i1 != 0)
            {
                this.field_75555_c.add(new VillageDoorInfo(p_75542_1_, p_75542_2_, p_75542_3_, 0, i1 > 0 ? -2 : 2, this.field_75553_e));
            }
        }
        else
        {
            i1 = 0;

            for (j1 = -5; j1 < 0; ++j1)
            {
                if (this.field_75556_a.func_72937_j(p_75542_1_ + j1, p_75542_2_, p_75542_3_))
                {
                    --i1;
                }
            }

            for (j1 = 1; j1 <= 5; ++j1)
            {
                if (this.field_75556_a.func_72937_j(p_75542_1_ + j1, p_75542_2_, p_75542_3_))
                {
                    ++i1;
                }
            }

            if (i1 != 0)
            {
                this.field_75555_c.add(new VillageDoorInfo(p_75542_1_, p_75542_2_, p_75542_3_, i1 > 0 ? -2 : 2, 0, this.field_75553_e));
            }
        }
    }

    private boolean func_75548_d(int p_75548_1_, int p_75548_2_, int p_75548_3_)
    {
        Iterator iterator = this.field_75554_b.iterator();
        ChunkCoordinates chunkcoordinates;

        do
        {
            if (!iterator.hasNext())
            {
                return false;
            }

            chunkcoordinates = (ChunkCoordinates)iterator.next();
        }
        while (chunkcoordinates.field_71574_a != p_75548_1_ || chunkcoordinates.field_71572_b != p_75548_2_ || chunkcoordinates.field_71573_c != p_75548_3_);

        return true;
    }

    private boolean func_75541_e(int p_75541_1_, int p_75541_2_, int p_75541_3_)
    {
        return this.field_75556_a.func_147439_a(p_75541_1_, p_75541_2_, p_75541_3_) == Blocks.WOODEN_DOOR;
    }

    public void func_76184_a(NBTTagCompound p_76184_1_)
    {
        this.field_75553_e = p_76184_1_.func_74762_e("Tick");
        NBTTagList nbttaglist = p_76184_1_.func_150295_c("Villages", 10);

        for (int i = 0; i < nbttaglist.func_74745_c(); ++i)
        {
            NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
            Village village = new Village();
            village.func_82690_a(nbttagcompound1);
            this.field_75552_d.add(village);
        }
    }

    public void func_76187_b(NBTTagCompound p_76187_1_)
    {
        p_76187_1_.func_74768_a("Tick", this.field_75553_e);
        NBTTagList nbttaglist = new NBTTagList();
        Iterator iterator = this.field_75552_d.iterator();

        while (iterator.hasNext())
        {
            Village village = (Village)iterator.next();
            NBTTagCompound nbttagcompound1 = new NBTTagCompound();
            village.func_82689_b(nbttagcompound1);
            nbttaglist.func_74742_a(nbttagcompound1);
        }

        p_76187_1_.func_74782_a("Villages", nbttaglist);
    }
}