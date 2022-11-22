package net.minecraft.world.storage;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;

public class MapData extends WorldSavedData
{
    public int field_76201_a;
    public int field_76199_b;
    public byte field_76200_c;
    public byte field_76197_d;
    public byte[] field_76198_e = new byte[16384];
    public List field_76196_g = new ArrayList();
    private Map field_76202_j = new HashMap();
    public Map field_76203_h = new LinkedHashMap();
    private static final String __OBFID = "CL_00000577";

    public MapData(String p_i2140_1_)
    {
        super(p_i2140_1_);
    }

    public void func_76184_a(NBTTagCompound p_76184_1_)
    {
        this.field_76200_c = p_76184_1_.func_74771_c("dimension");
        this.field_76201_a = p_76184_1_.func_74762_e("xCenter");
        this.field_76199_b = p_76184_1_.func_74762_e("zCenter");
        this.field_76197_d = p_76184_1_.func_74771_c("scale");

        if (this.field_76197_d < 0)
        {
            this.field_76197_d = 0;
        }

        if (this.field_76197_d > 4)
        {
            this.field_76197_d = 4;
        }

        short short1 = p_76184_1_.func_74765_d("width");
        short short2 = p_76184_1_.func_74765_d("height");

        if (short1 == 128 && short2 == 128)
        {
            this.field_76198_e = p_76184_1_.func_74770_j("colors");
        }
        else
        {
            byte[] abyte = p_76184_1_.func_74770_j("colors");
            this.field_76198_e = new byte[16384];
            int i = (128 - short1) / 2;
            int j = (128 - short2) / 2;

            for (int k = 0; k < short2; ++k)
            {
                int l = k + j;

                if (l >= 0 || l < 128)
                {
                    for (int i1 = 0; i1 < short1; ++i1)
                    {
                        int j1 = i1 + i;

                        if (j1 >= 0 || j1 < 128)
                        {
                            this.field_76198_e[j1 + l * 128] = abyte[i1 + k * short1];
                        }
                    }
                }
            }
        }
    }

    public void func_76187_b(NBTTagCompound p_76187_1_)
    {
        p_76187_1_.func_74774_a("dimension", this.field_76200_c);
        p_76187_1_.func_74768_a("xCenter", this.field_76201_a);
        p_76187_1_.func_74768_a("zCenter", this.field_76199_b);
        p_76187_1_.func_74774_a("scale", this.field_76197_d);
        p_76187_1_.func_74777_a("width", (short)128);
        p_76187_1_.func_74777_a("height", (short)128);
        p_76187_1_.func_74773_a("colors", this.field_76198_e);
    }

    public void func_76191_a(EntityPlayer p_76191_1_, ItemStack p_76191_2_)
    {
        if (!this.field_76202_j.containsKey(p_76191_1_))
        {
            MapData.MapInfo mapinfo = new MapData.MapInfo(p_76191_1_);
            this.field_76202_j.put(p_76191_1_, mapinfo);
            this.field_76196_g.add(mapinfo);
        }

        if (!p_76191_1_.field_71071_by.func_70431_c(p_76191_2_))
        {
            this.field_76203_h.remove(p_76191_1_.func_70005_c_());
        }

        for (int i = 0; i < this.field_76196_g.size(); ++i)
        {
            MapData.MapInfo mapinfo1 = (MapData.MapInfo)this.field_76196_g.get(i);

            if (!mapinfo1.field_76211_a.field_70128_L && (mapinfo1.field_76211_a.field_71071_by.func_70431_c(p_76191_2_) || p_76191_2_.func_82839_y()))
            {
                if (!p_76191_2_.func_82839_y() && mapinfo1.field_76211_a.field_71093_bK == this.field_76200_c)
                {
                    this.func_82567_a(0, mapinfo1.field_76211_a.world, mapinfo1.field_76211_a.func_70005_c_(), mapinfo1.field_76211_a.field_70165_t, mapinfo1.field_76211_a.field_70161_v, (double)mapinfo1.field_76211_a.field_70177_z);
                }
            }
            else
            {
                this.field_76202_j.remove(mapinfo1.field_76211_a);
                this.field_76196_g.remove(mapinfo1);
            }
        }

        if (p_76191_2_.func_82839_y())
        {
            this.func_82567_a(1, p_76191_1_.world, "frame-" + p_76191_2_.func_82836_z().func_145782_y(), (double)p_76191_2_.func_82836_z().field_146063_b, (double)p_76191_2_.func_82836_z().field_146062_d, (double)(p_76191_2_.func_82836_z().field_82332_a * 90));
        }
    }

    private void func_82567_a(int p_82567_1_, World p_82567_2_, String p_82567_3_, double p_82567_4_, double p_82567_6_, double p_82567_8_)
    {
        int j = 1 << this.field_76197_d;
        float f = (float)(p_82567_4_ - (double)this.field_76201_a) / (float)j;
        float f1 = (float)(p_82567_6_ - (double)this.field_76199_b) / (float)j;
        byte b0 = (byte)((int)((double)(f * 2.0F) + 0.5D));
        byte b1 = (byte)((int)((double)(f1 * 2.0F) + 0.5D));
        byte b3 = 63;
        byte b2;

        if (f >= (float)(-b3) && f1 >= (float)(-b3) && f <= (float)b3 && f1 <= (float)b3)
        {
            p_82567_8_ += p_82567_8_ < 0.0D ? -8.0D : 8.0D;
            b2 = (byte)((int)(p_82567_8_ * 16.0D / 360.0D));

            if (this.field_76200_c < 0)
            {
                int k = (int)(p_82567_2_.func_72912_H().func_76073_f() / 10L);
                b2 = (byte)(k * k * 34187121 + k * 121 >> 15 & 15);
            }
        }
        else
        {
            if (Math.abs(f) >= 320.0F || Math.abs(f1) >= 320.0F)
            {
                this.field_76203_h.remove(p_82567_3_);
                return;
            }

            p_82567_1_ = 6;
            b2 = 0;

            if (f <= (float)(-b3))
            {
                b0 = (byte)((int)((double)(b3 * 2) + 2.5D));
            }

            if (f1 <= (float)(-b3))
            {
                b1 = (byte)((int)((double)(b3 * 2) + 2.5D));
            }

            if (f >= (float)b3)
            {
                b0 = (byte)(b3 * 2 + 1);
            }

            if (f1 >= (float)b3)
            {
                b1 = (byte)(b3 * 2 + 1);
            }
        }

        this.field_76203_h.put(p_82567_3_, new MapData.MapCoord((byte)p_82567_1_, b0, b1, b2));
    }

    public byte[] func_76193_a(ItemStack p_76193_1_, World p_76193_2_, EntityPlayer p_76193_3_)
    {
        MapData.MapInfo mapinfo = (MapData.MapInfo)this.field_76202_j.get(p_76193_3_);
        return mapinfo == null ? null : mapinfo.func_76204_a(p_76193_1_);
    }

    public void func_76194_a(int p_76194_1_, int p_76194_2_, int p_76194_3_)
    {
        super.func_76185_a();

        for (int l = 0; l < this.field_76196_g.size(); ++l)
        {
            MapData.MapInfo mapinfo = (MapData.MapInfo)this.field_76196_g.get(l);

            if (mapinfo.field_76209_b[p_76194_1_] < 0 || mapinfo.field_76209_b[p_76194_1_] > p_76194_2_)
            {
                mapinfo.field_76209_b[p_76194_1_] = p_76194_2_;
            }

            if (mapinfo.field_76210_c[p_76194_1_] < 0 || mapinfo.field_76210_c[p_76194_1_] < p_76194_3_)
            {
                mapinfo.field_76210_c[p_76194_1_] = p_76194_3_;
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public void func_76192_a(byte[] p_76192_1_)
    {
        int i;

        if (p_76192_1_[0] == 0)
        {
            i = p_76192_1_[1] & 255;
            int j = p_76192_1_[2] & 255;

            for (int k = 0; k < p_76192_1_.length - 3; ++k)
            {
                this.field_76198_e[(k + j) * 128 + i] = p_76192_1_[k + 3];
            }

            this.func_76185_a();
        }
        else if (p_76192_1_[0] == 1)
        {
            this.field_76203_h.clear();

            for (i = 0; i < (p_76192_1_.length - 1) / 3; ++i)
            {
                byte b2 = (byte)(p_76192_1_[i * 3 + 1] >> 4);
                byte b3 = p_76192_1_[i * 3 + 2];
                byte b0 = p_76192_1_[i * 3 + 3];
                byte b1 = (byte)(p_76192_1_[i * 3 + 1] & 15);
                this.field_76203_h.put("icon-" + i, new MapData.MapCoord(b2, b3, b0, b1));
            }
        }
        else if (p_76192_1_[0] == 2)
        {
            this.field_76197_d = p_76192_1_[1];
        }
    }

    public MapData.MapInfo func_82568_a(EntityPlayer p_82568_1_)
    {
        MapData.MapInfo mapinfo = (MapData.MapInfo)this.field_76202_j.get(p_82568_1_);

        if (mapinfo == null)
        {
            mapinfo = new MapData.MapInfo(p_82568_1_);
            this.field_76202_j.put(p_82568_1_, mapinfo);
            this.field_76196_g.add(mapinfo);
        }

        return mapinfo;
    }

    public class MapCoord
    {
        public byte field_76216_a;
        public byte field_76214_b;
        public byte field_76215_c;
        public byte field_76212_d;
        private static final String __OBFID = "CL_00000579";

        public MapCoord(byte p_i2139_2_, byte p_i2139_3_, byte p_i2139_4_, byte p_i2139_5_)
        {
            this.field_76216_a = p_i2139_2_;
            this.field_76214_b = p_i2139_3_;
            this.field_76215_c = p_i2139_4_;
            this.field_76212_d = p_i2139_5_;
        }
    }

    public class MapInfo
    {
        public final EntityPlayer field_76211_a;
        public int[] field_76209_b = new int[128];
        public int[] field_76210_c = new int[128];
        private int field_76208_e;
        private int field_76205_f;
        private byte[] field_76206_g;
        public int field_82569_d;
        private boolean field_82570_i;
        private static final String __OBFID = "CL_00000578";

        public MapInfo(EntityPlayer p_i2138_2_)
        {
            this.field_76211_a = p_i2138_2_;

            for (int i = 0; i < this.field_76209_b.length; ++i)
            {
                this.field_76209_b[i] = 0;
                this.field_76210_c[i] = 127;
            }
        }

        public byte[] func_76204_a(ItemStack p_76204_1_)
        {
            byte[] abyte;

            if (!this.field_82570_i)
            {
                abyte = new byte[] {(byte)2, MapData.this.field_76197_d};
                this.field_82570_i = true;
                return abyte;
            }
            else
            {
                int i;
                int i1;

                if (--this.field_76205_f < 0)
                {
                    this.field_76205_f = 4;
                    abyte = new byte[MapData.this.field_76203_h.size() * 3 + 1];
                    abyte[0] = 1;
                    i = 0;

                    for (Iterator iterator = MapData.this.field_76203_h.values().iterator(); iterator.hasNext(); ++i)
                    {
                        MapData.MapCoord mapcoord = (MapData.MapCoord)iterator.next();
                        abyte[i * 3 + 1] = (byte)(mapcoord.field_76216_a << 4 | mapcoord.field_76212_d & 15);
                        abyte[i * 3 + 2] = mapcoord.field_76214_b;
                        abyte[i * 3 + 3] = mapcoord.field_76215_c;
                    }

                    boolean flag = !p_76204_1_.func_82839_y();

                    if (this.field_76206_g != null && this.field_76206_g.length == abyte.length)
                    {
                        for (i1 = 0; i1 < abyte.length; ++i1)
                        {
                            if (abyte[i1] != this.field_76206_g[i1])
                            {
                                flag = false;
                                break;
                            }
                        }
                    }
                    else
                    {
                        flag = false;
                    }

                    if (!flag)
                    {
                        this.field_76206_g = abyte;
                        return abyte;
                    }
                }

                for (int k = 0; k < 1; ++k)
                {
                    i = this.field_76208_e++ * 11 % 128;

                    if (this.field_76209_b[i] >= 0)
                    {
                        int l = this.field_76210_c[i] - this.field_76209_b[i] + 1;
                        i1 = this.field_76209_b[i];
                        byte[] abyte1 = new byte[l + 3];
                        abyte1[0] = 0;
                        abyte1[1] = (byte)i;
                        abyte1[2] = (byte)i1;

                        for (int j = 0; j < abyte1.length - 3; ++j)
                        {
                            abyte1[j + 3] = MapData.this.field_76198_e[(j + i1) * 128 + i];
                        }

                        this.field_76210_c[i] = -1;
                        this.field_76209_b[i] = -1;
                        return abyte1;
                    }
                }

                return null;
            }
        }
    }
}