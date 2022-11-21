package net.minecraft.world.gen.structure;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;

public abstract class StructureStart
{
    protected LinkedList field_75075_a = new LinkedList();
    protected StructureBoundingBox field_75074_b;
    private int field_143024_c;
    private int field_143023_d;
    private static final String __OBFID = "CL_00000513";

    public StructureStart() {}

    public StructureStart(int p_i43002_1_, int p_i43002_2_)
    {
        this.field_143024_c = p_i43002_1_;
        this.field_143023_d = p_i43002_2_;
    }

    public StructureBoundingBox func_75071_a()
    {
        return this.field_75074_b;
    }

    public LinkedList func_75073_b()
    {
        return this.field_75075_a;
    }

    public void func_75068_a(World p_75068_1_, Random p_75068_2_, StructureBoundingBox p_75068_3_)
    {
        Iterator iterator = this.field_75075_a.iterator();

        while (iterator.hasNext())
        {
            StructureComponent structurecomponent = (StructureComponent)iterator.next();

            if (structurecomponent.func_74874_b().func_78884_a(p_75068_3_) && !structurecomponent.func_74875_a(p_75068_1_, p_75068_2_, p_75068_3_))
            {
                iterator.remove();
            }
        }
    }

    protected void func_75072_c()
    {
        this.field_75074_b = StructureBoundingBox.func_78887_a();
        Iterator iterator = this.field_75075_a.iterator();

        while (iterator.hasNext())
        {
            StructureComponent structurecomponent = (StructureComponent)iterator.next();
            this.field_75074_b.func_78888_b(structurecomponent.func_74874_b());
        }
    }

    public NBTTagCompound func_143021_a(int p_143021_1_, int p_143021_2_)
    {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        nbttagcompound.func_74778_a("id", MapGenStructureIO.func_143033_a(this));
        nbttagcompound.func_74768_a("ChunkX", p_143021_1_);
        nbttagcompound.func_74768_a("ChunkZ", p_143021_2_);
        nbttagcompound.func_74782_a("BB", this.field_75074_b.func_151535_h());
        NBTTagList nbttaglist = new NBTTagList();
        Iterator iterator = this.field_75075_a.iterator();

        while (iterator.hasNext())
        {
            StructureComponent structurecomponent = (StructureComponent)iterator.next();
            nbttaglist.func_74742_a(structurecomponent.func_143010_b());
        }

        nbttagcompound.func_74782_a("Children", nbttaglist);
        this.func_143022_a(nbttagcompound);
        return nbttagcompound;
    }

    public void func_143022_a(NBTTagCompound p_143022_1_) {}

    public void func_143020_a(World p_143020_1_, NBTTagCompound p_143020_2_)
    {
        this.field_143024_c = p_143020_2_.func_74762_e("ChunkX");
        this.field_143023_d = p_143020_2_.func_74762_e("ChunkZ");

        if (p_143020_2_.func_74764_b("BB"))
        {
            this.field_75074_b = new StructureBoundingBox(p_143020_2_.func_74759_k("BB"));
        }

        NBTTagList nbttaglist = p_143020_2_.func_150295_c("Children", 10);

        for (int i = 0; i < nbttaglist.func_74745_c(); ++i)
        {
            this.field_75075_a.add(MapGenStructureIO.func_143032_b(nbttaglist.func_150305_b(i), p_143020_1_));
        }

        this.func_143017_b(p_143020_2_);
    }

    public void func_143017_b(NBTTagCompound p_143017_1_) {}

    protected void func_75067_a(World p_75067_1_, Random p_75067_2_, int p_75067_3_)
    {
        int j = 63 - p_75067_3_;
        int k = this.field_75074_b.func_78882_c() + 1;

        if (k < j)
        {
            k += p_75067_2_.nextInt(j - k);
        }

        int l = k - this.field_75074_b.field_78894_e;
        this.field_75074_b.func_78886_a(0, l, 0);
        Iterator iterator = this.field_75075_a.iterator();

        while (iterator.hasNext())
        {
            StructureComponent structurecomponent = (StructureComponent)iterator.next();
            structurecomponent.func_74874_b().func_78886_a(0, l, 0);
        }
    }

    protected void func_75070_a(World p_75070_1_, Random p_75070_2_, int p_75070_3_, int p_75070_4_)
    {
        int k = p_75070_4_ - p_75070_3_ + 1 - this.field_75074_b.func_78882_c();
        boolean flag = true;
        int i1;

        if (k > 1)
        {
            i1 = p_75070_3_ + p_75070_2_.nextInt(k);
        }
        else
        {
            i1 = p_75070_3_;
        }

        int l = i1 - this.field_75074_b.field_78895_b;
        this.field_75074_b.func_78886_a(0, l, 0);
        Iterator iterator = this.field_75075_a.iterator();

        while (iterator.hasNext())
        {
            StructureComponent structurecomponent = (StructureComponent)iterator.next();
            structurecomponent.func_74874_b().func_78886_a(0, l, 0);
        }
    }

    public boolean func_75069_d()
    {
        return true;
    }

    public int func_143019_e()
    {
        return this.field_143024_c;
    }

    public int func_143018_f()
    {
        return this.field_143023_d;
    }
}