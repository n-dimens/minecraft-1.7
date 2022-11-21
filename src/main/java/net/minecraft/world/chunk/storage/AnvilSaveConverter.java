package net.minecraft.world.chunk.storage;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.AnvilConverterException;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.MathHelper;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraft.world.storage.SaveFormatComparator;
import net.minecraft.world.storage.SaveFormatOld;
import net.minecraft.world.storage.WorldInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AnvilSaveConverter extends SaveFormatOld
{
    private static final Logger field_151480_b = LogManager.getLogger();
    private static final String __OBFID = "CL_00000582";

    public AnvilSaveConverter(File p_i2144_1_)
    {
        super(p_i2144_1_);
    }

    @SideOnly(Side.CLIENT)
    public String func_154333_a()
    {
        return "Anvil";
    }

    @SideOnly(Side.CLIENT)
    public List func_75799_b() throws AnvilConverterException
    {
        if (this.field_75808_a != null && this.field_75808_a.exists() && this.field_75808_a.isDirectory())
        {
            ArrayList arraylist = new ArrayList();
            File[] afile = this.field_75808_a.listFiles();
            File[] afile1 = afile;
            int i = afile.length;

            for (int j = 0; j < i; ++j)
            {
                File file1 = afile1[j];

                if (file1.isDirectory())
                {
                    String s = file1.getName();
                    WorldInfo worldinfo = this.func_75803_c(s);

                    if (worldinfo != null && (worldinfo.func_76088_k() == 19132 || worldinfo.func_76088_k() == 19133))
                    {
                        boolean flag = worldinfo.func_76088_k() != this.func_75812_c();
                        String s1 = worldinfo.func_76065_j();

                        if (s1 == null || MathHelper.func_76139_a(s1))
                        {
                            s1 = s;
                        }

                        long k = 0L;
                        arraylist.add(new SaveFormatComparator(s, s1, worldinfo.func_76057_l(), k, worldinfo.func_76077_q(), flag, worldinfo.func_76093_s(), worldinfo.func_76086_u()));
                    }
                }
            }

            return arraylist;
        }
        else
        {
            throw new AnvilConverterException("Unable to read or access folder where game worlds are saved!");
        }
    }

    protected int func_75812_c()
    {
        return 19133;
    }

    public void func_75800_d()
    {
        RegionFileCache.func_76551_a();
    }

    public ISaveHandler func_75804_a(String p_75804_1_, boolean p_75804_2_)
    {
        return new AnvilSaveHandler(this.field_75808_a, p_75804_1_, p_75804_2_);
    }

    @SideOnly(Side.CLIENT)
    public boolean func_154334_a(String p_154334_1_)
    {
        WorldInfo worldinfo = this.func_75803_c(p_154334_1_);
        return worldinfo != null && worldinfo.func_76088_k() == 19132;
    }

    public boolean func_75801_b(String p_75801_1_)
    {
        WorldInfo worldinfo = this.func_75803_c(p_75801_1_);
        return worldinfo != null && worldinfo.func_76088_k() != this.func_75812_c();
    }

    public boolean func_75805_a(String p_75805_1_, IProgressUpdate p_75805_2_)
    {
        p_75805_2_.func_73718_a(0);
        ArrayList arraylist = new ArrayList();
        ArrayList arraylist1 = new ArrayList();
        ArrayList arraylist2 = new ArrayList();
        File file1 = new File(this.field_75808_a, p_75805_1_);
        File file2 = new File(file1, "DIM-1");
        File file3 = new File(file1, "DIM1");
        field_151480_b.info("Scanning folders...");
        this.func_75810_a(file1, arraylist);

        if (file2.exists())
        {
            this.func_75810_a(file2, arraylist1);
        }

        if (file3.exists())
        {
            this.func_75810_a(file3, arraylist2);
        }

        int i = arraylist.size() + arraylist1.size() + arraylist2.size();
        field_151480_b.info("Total conversion count is " + i);
        WorldInfo worldinfo = this.func_75803_c(p_75805_1_);
        Object object = null;

        if (worldinfo.func_76067_t() == WorldType.field_77138_c)
        {
            object = new WorldChunkManagerHell(BiomeGenBase.field_76772_c, 0.5F);
        }
        else
        {
            object = new WorldChunkManager(worldinfo.func_76063_b(), worldinfo.func_76067_t());
        }

        this.func_75813_a(new File(file1, "region"), arraylist, (WorldChunkManager)object, 0, i, p_75805_2_);
        this.func_75813_a(new File(file2, "region"), arraylist1, new WorldChunkManagerHell(BiomeGenBase.field_76778_j, 0.0F), arraylist.size(), i, p_75805_2_);
        this.func_75813_a(new File(file3, "region"), arraylist2, new WorldChunkManagerHell(BiomeGenBase.field_76779_k, 0.0F), arraylist.size() + arraylist1.size(), i, p_75805_2_);
        worldinfo.func_76078_e(19133);

        if (worldinfo.func_76067_t() == WorldType.field_77136_e)
        {
            worldinfo.func_76085_a(WorldType.field_77137_b);
        }

        this.func_75809_f(p_75805_1_);
        ISaveHandler isavehandler = this.func_75804_a(p_75805_1_, false);
        isavehandler.func_75761_a(worldinfo);
        return true;
    }

    private void func_75809_f(String p_75809_1_)
    {
        File file1 = new File(this.field_75808_a, p_75809_1_);

        if (!file1.exists())
        {
            field_151480_b.warn("Unable to create level.dat_mcr backup");
        }
        else
        {
            File file2 = new File(file1, "level.dat");

            if (!file2.exists())
            {
                field_151480_b.warn("Unable to create level.dat_mcr backup");
            }
            else
            {
                File file3 = new File(file1, "level.dat_mcr");

                if (!file2.renameTo(file3))
                {
                    field_151480_b.warn("Unable to create level.dat_mcr backup");
                }
            }
        }
    }

    private void func_75813_a(File p_75813_1_, Iterable p_75813_2_, WorldChunkManager p_75813_3_, int p_75813_4_, int p_75813_5_, IProgressUpdate p_75813_6_)
    {
        Iterator iterator = p_75813_2_.iterator();

        while (iterator.hasNext())
        {
            File file2 = (File)iterator.next();
            this.func_75811_a(p_75813_1_, file2, p_75813_3_, p_75813_4_, p_75813_5_, p_75813_6_);
            ++p_75813_4_;
            int k = (int)Math.round(100.0D * (double)p_75813_4_ / (double)p_75813_5_);
            p_75813_6_.func_73718_a(k);
        }
    }

    private void func_75811_a(File p_75811_1_, File p_75811_2_, WorldChunkManager p_75811_3_, int p_75811_4_, int p_75811_5_, IProgressUpdate p_75811_6_)
    {
        try
        {
            String s = p_75811_2_.getName();
            RegionFile regionfile = new RegionFile(p_75811_2_);
            RegionFile regionfile1 = new RegionFile(new File(p_75811_1_, s.substring(0, s.length() - ".mcr".length()) + ".mca"));

            for (int k = 0; k < 32; ++k)
            {
                int l;

                for (l = 0; l < 32; ++l)
                {
                    if (regionfile.func_76709_c(k, l) && !regionfile1.func_76709_c(k, l))
                    {
                        DataInputStream datainputstream = regionfile.func_76704_a(k, l);

                        if (datainputstream == null)
                        {
                            field_151480_b.warn("Failed to fetch input stream");
                        }
                        else
                        {
                            NBTTagCompound nbttagcompound = CompressedStreamTools.func_74794_a(datainputstream);
                            datainputstream.close();
                            NBTTagCompound nbttagcompound1 = nbttagcompound.func_74775_l("Level");
                            ChunkLoader.AnvilConverterData anvilconverterdata = ChunkLoader.func_76691_a(nbttagcompound1);
                            NBTTagCompound nbttagcompound2 = new NBTTagCompound();
                            NBTTagCompound nbttagcompound3 = new NBTTagCompound();
                            nbttagcompound2.func_74782_a("Level", nbttagcompound3);
                            ChunkLoader.func_76690_a(anvilconverterdata, nbttagcompound3, p_75811_3_);
                            DataOutputStream dataoutputstream = regionfile1.func_76710_b(k, l);
                            CompressedStreamTools.func_74800_a(nbttagcompound2, dataoutputstream);
                            dataoutputstream.close();
                        }
                    }
                }

                l = (int)Math.round(100.0D * (double)(p_75811_4_ * 1024) / (double)(p_75811_5_ * 1024));
                int i1 = (int)Math.round(100.0D * (double)((k + 1) * 32 + p_75811_4_ * 1024) / (double)(p_75811_5_ * 1024));

                if (i1 > l)
                {
                    p_75811_6_.func_73718_a(i1);
                }
            }

            regionfile.func_76708_c();
            regionfile1.func_76708_c();
        }
        catch (IOException ioexception)
        {
            ioexception.printStackTrace();
        }
    }

    private void func_75810_a(File p_75810_1_, Collection p_75810_2_)
    {
        File file2 = new File(p_75810_1_, "region");
        File[] afile = file2.listFiles(new FilenameFilter()
        {
            private static final String __OBFID = "CL_00000583";
            public boolean accept(File p_accept_1_, String p_accept_2_)
            {
                return p_accept_2_.endsWith(".mcr");
            }
        });

        if (afile != null)
        {
            Collections.addAll(p_75810_2_, afile);
        }
    }
}