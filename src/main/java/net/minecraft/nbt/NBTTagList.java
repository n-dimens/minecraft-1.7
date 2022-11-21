package net.minecraft.nbt;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NBTTagList extends NBTBase
{
    private List field_74747_a = new ArrayList();
    private byte field_74746_b = 0;
    private static final String __OBFID = "CL_00001224";

    void func_74734_a(DataOutput p_74734_1_) throws IOException
    {
        if (!this.field_74747_a.isEmpty())
        {
            this.field_74746_b = ((NBTBase)this.field_74747_a.get(0)).func_74732_a();
        }
        else
        {
            this.field_74746_b = 0;
        }

        p_74734_1_.writeByte(this.field_74746_b);
        p_74734_1_.writeInt(this.field_74747_a.size());

        for (int i = 0; i < this.field_74747_a.size(); ++i)
        {
            ((NBTBase)this.field_74747_a.get(i)).func_74734_a(p_74734_1_);
        }
    }

    void func_152446_a(DataInput p_152446_1_, int p_152446_2_, NBTSizeTracker p_152446_3_) throws IOException
    {
        if (p_152446_2_ > 512)
        {
            throw new RuntimeException("Tried to read NBT tag with too high complexity, depth > 512");
        }
        else
        {
            p_152446_3_.func_152450_a(8L);
            this.field_74746_b = p_152446_1_.readByte();
            int j = p_152446_1_.readInt();
            this.field_74747_a = new ArrayList();

            for (int k = 0; k < j; ++k)
            {
                NBTBase nbtbase = NBTBase.func_150284_a(this.field_74746_b);
                nbtbase.func_152446_a(p_152446_1_, p_152446_2_ + 1, p_152446_3_);
                this.field_74747_a.add(nbtbase);
            }
        }
    }

    public byte func_74732_a()
    {
        return (byte)9;
    }

    public String toString()
    {
        String s = "[";
        int i = 0;

        for (Iterator iterator = this.field_74747_a.iterator(); iterator.hasNext(); ++i)
        {
            NBTBase nbtbase = (NBTBase)iterator.next();
            s = s + "" + i + ':' + nbtbase + ',';
        }

        return s + "]";
    }

    public void func_74742_a(NBTBase p_74742_1_)
    {
        if (this.field_74746_b == 0)
        {
            this.field_74746_b = p_74742_1_.func_74732_a();
        }
        else if (this.field_74746_b != p_74742_1_.func_74732_a())
        {
            System.err.println("WARNING: Adding mismatching tag types to tag list");
            return;
        }

        this.field_74747_a.add(p_74742_1_);
    }

    @SideOnly(Side.CLIENT)
    public void func_150304_a(int p_150304_1_, NBTBase p_150304_2_)
    {
        if (p_150304_1_ >= 0 && p_150304_1_ < this.field_74747_a.size())
        {
            if (this.field_74746_b == 0)
            {
                this.field_74746_b = p_150304_2_.func_74732_a();
            }
            else if (this.field_74746_b != p_150304_2_.func_74732_a())
            {
                System.err.println("WARNING: Adding mismatching tag types to tag list");
                return;
            }

            this.field_74747_a.set(p_150304_1_, p_150304_2_);
        }
        else
        {
            System.err.println("WARNING: index out of bounds to set tag in tag list");
        }
    }

    @SideOnly(Side.CLIENT)
    public NBTBase func_74744_a(int p_74744_1_)
    {
        return (NBTBase)this.field_74747_a.remove(p_74744_1_);
    }

    public NBTTagCompound func_150305_b(int p_150305_1_)
    {
        if (p_150305_1_ >= 0 && p_150305_1_ < this.field_74747_a.size())
        {
            NBTBase nbtbase = (NBTBase)this.field_74747_a.get(p_150305_1_);
            return nbtbase.func_74732_a() == 10 ? (NBTTagCompound)nbtbase : new NBTTagCompound();
        }
        else
        {
            return new NBTTagCompound();
        }
    }

    public int[] func_150306_c(int p_150306_1_)
    {
        if (p_150306_1_ >= 0 && p_150306_1_ < this.field_74747_a.size())
        {
            NBTBase nbtbase = (NBTBase)this.field_74747_a.get(p_150306_1_);
            return nbtbase.func_74732_a() == 11 ? ((NBTTagIntArray)nbtbase).func_150302_c() : new int[0];
        }
        else
        {
            return new int[0];
        }
    }

    public double func_150309_d(int p_150309_1_)
    {
        if (p_150309_1_ >= 0 && p_150309_1_ < this.field_74747_a.size())
        {
            NBTBase nbtbase = (NBTBase)this.field_74747_a.get(p_150309_1_);
            return nbtbase.func_74732_a() == 6 ? ((NBTTagDouble)nbtbase).func_150286_g() : 0.0D;
        }
        else
        {
            return 0.0D;
        }
    }

    public float func_150308_e(int p_150308_1_)
    {
        if (p_150308_1_ >= 0 && p_150308_1_ < this.field_74747_a.size())
        {
            NBTBase nbtbase = (NBTBase)this.field_74747_a.get(p_150308_1_);
            return nbtbase.func_74732_a() == 5 ? ((NBTTagFloat)nbtbase).func_150288_h() : 0.0F;
        }
        else
        {
            return 0.0F;
        }
    }

    public String func_150307_f(int p_150307_1_)
    {
        if (p_150307_1_ >= 0 && p_150307_1_ < this.field_74747_a.size())
        {
            NBTBase nbtbase = (NBTBase)this.field_74747_a.get(p_150307_1_);
            return nbtbase.func_74732_a() == 8 ? nbtbase.func_150285_a_() : nbtbase.toString();
        }
        else
        {
            return "";
        }
    }

    public int func_74745_c()
    {
        return this.field_74747_a.size();
    }

    public NBTBase func_74737_b()
    {
        NBTTagList nbttaglist = new NBTTagList();
        nbttaglist.field_74746_b = this.field_74746_b;
        Iterator iterator = this.field_74747_a.iterator();

        while (iterator.hasNext())
        {
            NBTBase nbtbase = (NBTBase)iterator.next();
            NBTBase nbtbase1 = nbtbase.func_74737_b();
            nbttaglist.field_74747_a.add(nbtbase1);
        }

        return nbttaglist;
    }

    public boolean equals(Object p_equals_1_)
    {
        if (super.equals(p_equals_1_))
        {
            NBTTagList nbttaglist = (NBTTagList)p_equals_1_;

            if (this.field_74746_b == nbttaglist.field_74746_b)
            {
                return this.field_74747_a.equals(nbttaglist.field_74747_a);
            }
        }

        return false;
    }

    public int hashCode()
    {
        return super.hashCode() ^ this.field_74747_a.hashCode();
    }

    public int func_150303_d()
    {
        return this.field_74746_b;
    }
}