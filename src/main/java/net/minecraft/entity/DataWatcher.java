package net.minecraft.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.ReportedException;
import org.apache.commons.lang3.ObjectUtils;

public class DataWatcher
{
    private final Entity field_151511_a;
    private boolean field_92086_a = true;
    private static final HashMap field_75697_a = new HashMap();
    private final Map field_75695_b = new HashMap();
    private boolean field_75696_c;
    private ReadWriteLock field_75694_d = new ReentrantReadWriteLock();
    private static final String __OBFID = "CL_00001559";

    public DataWatcher(Entity p_i45313_1_)
    {
        this.field_151511_a = p_i45313_1_;
    }

    public void func_75682_a(int p_75682_1_, Object p_75682_2_)
    {
        Integer integer = (Integer)field_75697_a.get(p_75682_2_.getClass());

        if (integer == null)
        {
            throw new IllegalArgumentException("Unknown data type: " + p_75682_2_.getClass());
        }
        else if (p_75682_1_ > 31)
        {
            throw new IllegalArgumentException("Data value id is too big with " + p_75682_1_ + "! (Max is " + 31 + ")");
        }
        else if (this.field_75695_b.containsKey(Integer.valueOf(p_75682_1_)))
        {
            throw new IllegalArgumentException("Duplicate id value for " + p_75682_1_ + "!");
        }
        else
        {
            DataWatcher.WatchableObject watchableobject = new DataWatcher.WatchableObject(integer.intValue(), p_75682_1_, p_75682_2_);
            this.field_75694_d.writeLock().lock();
            this.field_75695_b.put(Integer.valueOf(p_75682_1_), watchableobject);
            this.field_75694_d.writeLock().unlock();
            this.field_92086_a = false;
        }
    }

    public void func_82709_a(int p_82709_1_, int p_82709_2_)
    {
        DataWatcher.WatchableObject watchableobject = new DataWatcher.WatchableObject(p_82709_2_, p_82709_1_, (Object)null);
        this.field_75694_d.writeLock().lock();
        this.field_75695_b.put(Integer.valueOf(p_82709_1_), watchableobject);
        this.field_75694_d.writeLock().unlock();
        this.field_92086_a = false;
    }

    public byte func_75683_a(int p_75683_1_)
    {
        return ((Byte)this.func_75691_i(p_75683_1_).func_75669_b()).byteValue();
    }

    public short func_75693_b(int p_75693_1_)
    {
        return ((Short)this.func_75691_i(p_75693_1_).func_75669_b()).shortValue();
    }

    public int func_75679_c(int p_75679_1_)
    {
        return ((Integer)this.func_75691_i(p_75679_1_).func_75669_b()).intValue();
    }

    public float func_111145_d(int p_111145_1_)
    {
        return ((Float)this.func_75691_i(p_111145_1_).func_75669_b()).floatValue();
    }

    public String func_75681_e(int p_75681_1_)
    {
        return (String)this.func_75691_i(p_75681_1_).func_75669_b();
    }

    public ItemStack func_82710_f(int p_82710_1_)
    {
        return (ItemStack)this.func_75691_i(p_82710_1_).func_75669_b();
    }

    private DataWatcher.WatchableObject func_75691_i(int p_75691_1_)
    {
        this.field_75694_d.readLock().lock();
        DataWatcher.WatchableObject watchableobject;

        try
        {
            watchableobject = (DataWatcher.WatchableObject)this.field_75695_b.get(Integer.valueOf(p_75691_1_));
        }
        catch (Throwable throwable)
        {
            CrashReport crashreport = CrashReport.func_85055_a(throwable, "Getting synched entity data");
            CrashReportCategory crashreportcategory = crashreport.func_85058_a("Synched entity data");
            crashreportcategory.func_71507_a("Data ID", Integer.valueOf(p_75691_1_));
            throw new ReportedException(crashreport);
        }

        this.field_75694_d.readLock().unlock();
        return watchableobject;
    }

    public void func_75692_b(int p_75692_1_, Object p_75692_2_)
    {
        DataWatcher.WatchableObject watchableobject = this.func_75691_i(p_75692_1_);

        if (ObjectUtils.notEqual(p_75692_2_, watchableobject.func_75669_b()))
        {
            watchableobject.func_75673_a(p_75692_2_);
            this.field_151511_a.func_145781_i(p_75692_1_);
            watchableobject.func_75671_a(true);
            this.field_75696_c = true;
        }
    }

    public void func_82708_h(int p_82708_1_)
    {
        this.func_75691_i(p_82708_1_).field_75675_d = true;
        this.field_75696_c = true;
    }

    public boolean func_75684_a()
    {
        return this.field_75696_c;
    }

    public static void func_151507_a(List p_151507_0_, PacketBuffer p_151507_1_) throws IOException
    {
        if (p_151507_0_ != null)
        {
            Iterator iterator = p_151507_0_.iterator();

            while (iterator.hasNext())
            {
                DataWatcher.WatchableObject watchableobject = (DataWatcher.WatchableObject)iterator.next();
                func_151510_a(p_151507_1_, watchableobject);
            }
        }

        p_151507_1_.writeByte(127);
    }

    public List func_75688_b()
    {
        ArrayList arraylist = null;

        if (this.field_75696_c)
        {
            this.field_75694_d.readLock().lock();
            Iterator iterator = this.field_75695_b.values().iterator();

            while (iterator.hasNext())
            {
                DataWatcher.WatchableObject watchableobject = (DataWatcher.WatchableObject)iterator.next();

                if (watchableobject.func_75670_d())
                {
                    watchableobject.func_75671_a(false);

                    if (arraylist == null)
                    {
                        arraylist = new ArrayList();
                    }

                    arraylist.add(watchableobject);
                }
            }

            this.field_75694_d.readLock().unlock();
        }

        this.field_75696_c = false;
        return arraylist;
    }

    public void func_151509_a(PacketBuffer p_151509_1_) throws IOException
    {
        this.field_75694_d.readLock().lock();
        Iterator iterator = this.field_75695_b.values().iterator();

        while (iterator.hasNext())
        {
            DataWatcher.WatchableObject watchableobject = (DataWatcher.WatchableObject)iterator.next();
            func_151510_a(p_151509_1_, watchableobject);
        }

        this.field_75694_d.readLock().unlock();
        p_151509_1_.writeByte(127);
    }

    public List func_75685_c()
    {
        ArrayList arraylist = null;
        this.field_75694_d.readLock().lock();
        DataWatcher.WatchableObject watchableobject;

        for (Iterator iterator = this.field_75695_b.values().iterator(); iterator.hasNext(); arraylist.add(watchableobject))
        {
            watchableobject = (DataWatcher.WatchableObject)iterator.next();

            if (arraylist == null)
            {
                arraylist = new ArrayList();
            }
        }

        this.field_75694_d.readLock().unlock();
        return arraylist;
    }

    private static void func_151510_a(PacketBuffer p_151510_0_, DataWatcher.WatchableObject p_151510_1_) throws IOException
    {
        int i = (p_151510_1_.func_75674_c() << 5 | p_151510_1_.func_75672_a() & 31) & 255;
        p_151510_0_.writeByte(i);

        switch (p_151510_1_.func_75674_c())
        {
            case 0:
                p_151510_0_.writeByte(((Byte)p_151510_1_.func_75669_b()).byteValue());
                break;
            case 1:
                p_151510_0_.writeShort(((Short)p_151510_1_.func_75669_b()).shortValue());
                break;
            case 2:
                p_151510_0_.writeInt(((Integer)p_151510_1_.func_75669_b()).intValue());
                break;
            case 3:
                p_151510_0_.writeFloat(((Float)p_151510_1_.func_75669_b()).floatValue());
                break;
            case 4:
                p_151510_0_.func_150785_a((String)p_151510_1_.func_75669_b());
                break;
            case 5:
                ItemStack itemstack = (ItemStack)p_151510_1_.func_75669_b();
                p_151510_0_.func_150788_a(itemstack);
                break;
            case 6:
                ChunkCoordinates chunkcoordinates = (ChunkCoordinates)p_151510_1_.func_75669_b();
                p_151510_0_.writeInt(chunkcoordinates.field_71574_a);
                p_151510_0_.writeInt(chunkcoordinates.field_71572_b);
                p_151510_0_.writeInt(chunkcoordinates.field_71573_c);
        }
    }

    public static List func_151508_b(PacketBuffer p_151508_0_) throws IOException
    {
        ArrayList arraylist = null;

        for (byte b0 = p_151508_0_.readByte(); b0 != 127; b0 = p_151508_0_.readByte())
        {
            if (arraylist == null)
            {
                arraylist = new ArrayList();
            }

            int i = (b0 & 224) >> 5;
            int j = b0 & 31;
            DataWatcher.WatchableObject watchableobject = null;

            switch (i)
            {
                case 0:
                    watchableobject = new DataWatcher.WatchableObject(i, j, Byte.valueOf(p_151508_0_.readByte()));
                    break;
                case 1:
                    watchableobject = new DataWatcher.WatchableObject(i, j, Short.valueOf(p_151508_0_.readShort()));
                    break;
                case 2:
                    watchableobject = new DataWatcher.WatchableObject(i, j, Integer.valueOf(p_151508_0_.readInt()));
                    break;
                case 3:
                    watchableobject = new DataWatcher.WatchableObject(i, j, Float.valueOf(p_151508_0_.readFloat()));
                    break;
                case 4:
                    watchableobject = new DataWatcher.WatchableObject(i, j, p_151508_0_.func_150789_c(32767));
                    break;
                case 5:
                    watchableobject = new DataWatcher.WatchableObject(i, j, p_151508_0_.func_150791_c());
                    break;
                case 6:
                    int k = p_151508_0_.readInt();
                    int l = p_151508_0_.readInt();
                    int i1 = p_151508_0_.readInt();
                    watchableobject = new DataWatcher.WatchableObject(i, j, new ChunkCoordinates(k, l, i1));
            }

            arraylist.add(watchableobject);
        }

        return arraylist;
    }

    @SideOnly(Side.CLIENT)
    public void func_75687_a(List p_75687_1_)
    {
        this.field_75694_d.writeLock().lock();
        Iterator iterator = p_75687_1_.iterator();

        while (iterator.hasNext())
        {
            DataWatcher.WatchableObject watchableobject = (DataWatcher.WatchableObject)iterator.next();
            DataWatcher.WatchableObject watchableobject1 = (DataWatcher.WatchableObject)this.field_75695_b.get(Integer.valueOf(watchableobject.func_75672_a()));

            if (watchableobject1 != null)
            {
                watchableobject1.func_75673_a(watchableobject.func_75669_b());
                this.field_151511_a.func_145781_i(watchableobject.func_75672_a());
            }
        }

        this.field_75694_d.writeLock().unlock();
        this.field_75696_c = true;
    }

    public boolean func_92085_d()
    {
        return this.field_92086_a;
    }

    public void func_111144_e()
    {
        this.field_75696_c = false;
    }

    static
    {
        field_75697_a.put(Byte.class, Integer.valueOf(0));
        field_75697_a.put(Short.class, Integer.valueOf(1));
        field_75697_a.put(Integer.class, Integer.valueOf(2));
        field_75697_a.put(Float.class, Integer.valueOf(3));
        field_75697_a.put(String.class, Integer.valueOf(4));
        field_75697_a.put(ItemStack.class, Integer.valueOf(5));
        field_75697_a.put(ChunkCoordinates.class, Integer.valueOf(6));
    }

    public static class WatchableObject
        {
            private final int field_75678_a;
            private final int field_75676_b;
            private Object field_75677_c;
            private boolean field_75675_d;
            private static final String __OBFID = "CL_00001560";

            public WatchableObject(int p_i1603_1_, int p_i1603_2_, Object p_i1603_3_)
            {
                this.field_75676_b = p_i1603_2_;
                this.field_75677_c = p_i1603_3_;
                this.field_75678_a = p_i1603_1_;
                this.field_75675_d = true;
            }

            public int func_75672_a()
            {
                return this.field_75676_b;
            }

            public void func_75673_a(Object p_75673_1_)
            {
                this.field_75677_c = p_75673_1_;
            }

            public Object func_75669_b()
            {
                return this.field_75677_c;
            }

            public int func_75674_c()
            {
                return this.field_75678_a;
            }

            public boolean func_75670_d()
            {
                return this.field_75675_d;
            }

            public void func_75671_a(boolean p_75671_1_)
            {
                this.field_75675_d = p_75671_1_;
            }
        }
}