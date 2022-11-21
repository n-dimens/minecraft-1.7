package net.minecraft.client.resources;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.image.BufferedImage;
import java.io.Closeable;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreenWorking;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.data.IMetadataSerializer;
import net.minecraft.client.resources.data.PackMetadataSection;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.HttpUtil;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.IOUtils;

@SideOnly(Side.CLIENT)
public class ResourcePackRepository
{
    protected static final FileFilter field_110622_a = new FileFilter()
    {
        private static final String __OBFID = "CL_00001088";
        public boolean accept(File p_accept_1_)
        {
            boolean flag = p_accept_1_.isFile() && p_accept_1_.getName().endsWith(".zip");
            boolean flag1 = p_accept_1_.isDirectory() && (new File(p_accept_1_, "pack.mcmeta")).isFile();
            return flag || flag1;
        }
    };
    private final File field_110618_d;
    public final IResourcePack field_110620_b;
    private final File field_148534_e;
    public final IMetadataSerializer field_110621_c;
    private IResourcePack field_148532_f;
    private boolean field_148533_g;
    private List field_110619_e = Lists.newArrayList();
    private List field_110617_f = Lists.newArrayList();
    private static final String __OBFID = "CL_00001087";

    public ResourcePackRepository(File p_i45101_1_, File p_i45101_2_, IResourcePack p_i45101_3_, IMetadataSerializer p_i45101_4_, GameSettings p_i45101_5_)
    {
        this.field_110618_d = p_i45101_1_;
        this.field_148534_e = p_i45101_2_;
        this.field_110620_b = p_i45101_3_;
        this.field_110621_c = p_i45101_4_;
        this.func_110616_f();
        this.func_110611_a();
        Iterator iterator = p_i45101_5_.field_151453_l.iterator();

        while (iterator.hasNext())
        {
            String s = (String)iterator.next();
            Iterator iterator1 = this.field_110619_e.iterator();

            while (iterator1.hasNext())
            {
                ResourcePackRepository.Entry entry = (ResourcePackRepository.Entry)iterator1.next();

                if (entry.func_110515_d().equals(s))
                {
                    this.field_110617_f.add(entry);
                    break;
                }
            }
        }
    }

    private void func_110616_f()
    {
        if (!this.field_110618_d.isDirectory())
        {
            this.field_110618_d.delete();
            this.field_110618_d.mkdirs();
        }
    }

    private List func_110614_g()
    {
        return this.field_110618_d.isDirectory() ? Arrays.asList(this.field_110618_d.listFiles(field_110622_a)) : Collections.emptyList();
    }

    public void func_110611_a()
    {
        ArrayList arraylist = Lists.newArrayList();
        Iterator iterator = this.func_110614_g().iterator();

        while (iterator.hasNext())
        {
            File file1 = (File)iterator.next();
            ResourcePackRepository.Entry entry = new ResourcePackRepository.Entry(file1, null);

            if (!this.field_110619_e.contains(entry))
            {
                try
                {
                    entry.func_110516_a();
                    arraylist.add(entry);
                }
                catch (Exception exception)
                {
                    arraylist.remove(entry);
                }
            }
            else
            {
                int i = this.field_110619_e.indexOf(entry);

                if (i > -1 && i < this.field_110619_e.size())
                {
                    arraylist.add(this.field_110619_e.get(i));
                }
            }
        }

        this.field_110619_e.removeAll(arraylist);
        iterator = this.field_110619_e.iterator();

        while (iterator.hasNext())
        {
            ResourcePackRepository.Entry entry1 = (ResourcePackRepository.Entry)iterator.next();
            entry1.func_110517_b();
        }

        this.field_110619_e = arraylist;
    }

    public List func_110609_b()
    {
        return ImmutableList.copyOf(this.field_110619_e);
    }

    public List func_110613_c()
    {
        return ImmutableList.copyOf(this.field_110617_f);
    }

    public void func_148527_a(List p_148527_1_)
    {
        this.field_110617_f.clear();
        this.field_110617_f.addAll(p_148527_1_);
    }

    public File func_110612_e()
    {
        return this.field_110618_d;
    }

    public void func_148526_a(String p_148526_1_)
    {
        String s1 = p_148526_1_.substring(p_148526_1_.lastIndexOf("/") + 1);

        if (s1.contains("?"))
        {
            s1 = s1.substring(0, s1.indexOf("?"));
        }

        if (s1.endsWith(".zip"))
        {
            File file1 = new File(this.field_148534_e, s1.replaceAll("\\W", ""));
            this.func_148529_f();
            this.func_148528_a(p_148526_1_, file1);
        }
    }

    private void func_148528_a(String p_148528_1_, File p_148528_2_)
    {
        HashMap hashmap = Maps.newHashMap();
        GuiScreenWorking guiscreenworking = new GuiScreenWorking();
        hashmap.put("X-Minecraft-Username", Minecraft.func_71410_x().func_110432_I().func_111285_a());
        hashmap.put("X-Minecraft-UUID", Minecraft.func_71410_x().func_110432_I().func_148255_b());
        hashmap.put("X-Minecraft-Version", "1.7.10");
        this.field_148533_g = true;
        Minecraft.func_71410_x().func_147108_a(guiscreenworking);
        HttpUtil.func_151223_a(p_148528_2_, p_148528_1_, new HttpUtil.DownloadListener()
        {
            private static final String __OBFID = "CL_00001089";
            public void func_148522_a(File p_148522_1_)
            {
                if (ResourcePackRepository.this.field_148533_g)
                {
                    ResourcePackRepository.this.field_148533_g = false;
                    ResourcePackRepository.this.field_148532_f = new FileResourcePack(p_148522_1_);
                    Minecraft.func_71410_x().func_147106_B();
                }
            }
        }, hashmap, 52428800, guiscreenworking, Minecraft.func_71410_x().func_110437_J());
    }

    public IResourcePack func_148530_e()
    {
        return this.field_148532_f;
    }

    public void func_148529_f()
    {
        this.field_148532_f = null;
        this.field_148533_g = false;
    }

    @SideOnly(Side.CLIENT)
    public class Entry
    {
        private final File field_110523_b;
        private IResourcePack field_110524_c;
        private PackMetadataSection field_110521_d;
        private BufferedImage field_110522_e;
        private ResourceLocation field_110520_f;
        private static final String __OBFID = "CL_00001090";

        private Entry(File p_i1295_2_)
        {
            this.field_110523_b = p_i1295_2_;
        }

        public void func_110516_a() throws IOException
        {
            this.field_110524_c = (IResourcePack)(this.field_110523_b.isDirectory() ? new FolderResourcePack(this.field_110523_b) : new FileResourcePack(this.field_110523_b));
            this.field_110521_d = (PackMetadataSection)this.field_110524_c.func_135058_a(ResourcePackRepository.this.field_110621_c, "pack");

            try
            {
                this.field_110522_e = this.field_110524_c.func_110586_a();
            }
            catch (IOException ioexception)
            {
                ;
            }

            if (this.field_110522_e == null)
            {
                this.field_110522_e = ResourcePackRepository.this.field_110620_b.func_110586_a();
            }

            this.func_110517_b();
        }

        public void func_110518_a(TextureManager p_110518_1_)
        {
            if (this.field_110520_f == null)
            {
                this.field_110520_f = p_110518_1_.func_110578_a("texturepackicon", new DynamicTexture(this.field_110522_e));
            }

            p_110518_1_.func_110577_a(this.field_110520_f);
        }

        public void func_110517_b()
        {
            if (this.field_110524_c instanceof Closeable)
            {
                IOUtils.closeQuietly((Closeable)this.field_110524_c);
            }
        }

        public IResourcePack func_110514_c()
        {
            return this.field_110524_c;
        }

        public String func_110515_d()
        {
            return this.field_110524_c.func_130077_b();
        }

        public String func_110519_e()
        {
            return this.field_110521_d == null ? EnumChatFormatting.RED + "Invalid pack.mcmeta (or missing \'pack\' section)" : this.field_110521_d.func_152805_a().func_150254_d();
        }

        public boolean equals(Object p_equals_1_)
        {
            return this == p_equals_1_ ? true : (p_equals_1_ instanceof ResourcePackRepository.Entry ? this.toString().equals(p_equals_1_.toString()) : false);
        }

        public int hashCode()
        {
            return this.toString().hashCode();
        }

        public String toString()
        {
            return String.format("%s:%s:%d", new Object[] {this.field_110523_b.getName(), this.field_110523_b.isDirectory() ? "folder" : "zip", Long.valueOf(this.field_110523_b.lastModified())});
        }

        Entry(File p_i1296_2_, Object p_i1296_3_)
        {
            this(p_i1296_2_);
        }
    }
}