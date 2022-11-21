package net.minecraft.client.renderer.texture;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.util.ReportedException;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SideOnly(Side.CLIENT)
public class TextureManager implements ITickable, IResourceManagerReloadListener
{
    private static final Logger field_147646_a = LogManager.getLogger();
    private final Map field_110585_a = Maps.newHashMap();
    private final Map field_130089_b = Maps.newHashMap();
    private final List field_110583_b = Lists.newArrayList();
    private final Map field_110584_c = Maps.newHashMap();
    private IResourceManager field_110582_d;
    private static final String __OBFID = "CL_00001064";

    public TextureManager(IResourceManager p_i1284_1_)
    {
        this.field_110582_d = p_i1284_1_;
    }

    public void func_110577_a(ResourceLocation p_110577_1_)
    {
        Object object = (ITextureObject)this.field_110585_a.get(p_110577_1_);

        if (object == null)
        {
            object = new SimpleTexture(p_110577_1_);
            this.func_110579_a(p_110577_1_, (ITextureObject)object);
        }

        TextureUtil.func_94277_a(((ITextureObject)object).func_110552_b());
    }

    public ResourceLocation func_130087_a(int p_130087_1_)
    {
        return (ResourceLocation)this.field_130089_b.get(Integer.valueOf(p_130087_1_));
    }

    public boolean func_130088_a(ResourceLocation p_130088_1_, TextureMap p_130088_2_)
    {
        if (this.func_110580_a(p_130088_1_, p_130088_2_))
        {
            this.field_130089_b.put(Integer.valueOf(p_130088_2_.func_130086_a()), p_130088_1_);
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean func_110580_a(ResourceLocation p_110580_1_, ITickableTextureObject p_110580_2_)
    {
        if (this.func_110579_a(p_110580_1_, p_110580_2_))
        {
            this.field_110583_b.add(p_110580_2_);
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean func_110579_a(ResourceLocation p_110579_1_, final ITextureObject p_110579_2_)
    {
        boolean flag = true;
        ITextureObject p_110579_2_2 = p_110579_2_;

        try
        {
            ((ITextureObject)p_110579_2_).func_110551_a(this.field_110582_d);
        }
        catch (IOException ioexception)
        {
            field_147646_a.warn("Failed to load texture: " + p_110579_1_, ioexception);
            p_110579_2_2 = TextureUtil.field_111001_a;
            this.field_110585_a.put(p_110579_1_, p_110579_2_2);
            flag = false;
        }
        catch (Throwable throwable)
        {
            CrashReport crashreport = CrashReport.func_85055_a(throwable, "Registering texture");
            CrashReportCategory crashreportcategory = crashreport.func_85058_a("Resource location being registered");
            crashreportcategory.func_71507_a("Resource location", p_110579_1_);
            crashreportcategory.func_71500_a("Texture object class", new Callable()
            {
                private static final String __OBFID = "CL_00001065";
                public String call()
                {
                    return p_110579_2_.getClass().getName();
                }
            });
            throw new ReportedException(crashreport);
        }

        this.field_110585_a.put(p_110579_1_, p_110579_2_2);
        return flag;
    }

    public ITextureObject func_110581_b(ResourceLocation p_110581_1_)
    {
        return (ITextureObject)this.field_110585_a.get(p_110581_1_);
    }

    public ResourceLocation func_110578_a(String p_110578_1_, DynamicTexture p_110578_2_)
    {
        Integer integer = (Integer)this.field_110584_c.get(p_110578_1_);

        if (integer == null)
        {
            integer = Integer.valueOf(1);
        }
        else
        {
            integer = Integer.valueOf(integer.intValue() + 1);
        }

        this.field_110584_c.put(p_110578_1_, integer);
        ResourceLocation resourcelocation = new ResourceLocation(String.format("dynamic/%s_%d", new Object[] {p_110578_1_, integer}));
        this.func_110579_a(resourcelocation, p_110578_2_);
        return resourcelocation;
    }

    public void func_110550_d()
    {
        Iterator iterator = this.field_110583_b.iterator();

        while (iterator.hasNext())
        {
            ITickable itickable = (ITickable)iterator.next();
            itickable.func_110550_d();
        }
    }

    public void func_147645_c(ResourceLocation p_147645_1_)
    {
        ITextureObject itextureobject = this.func_110581_b(p_147645_1_);

        if (itextureobject != null)
        {
            TextureUtil.func_147942_a(itextureobject.func_110552_b());
        }
    }

    public void func_110549_a(IResourceManager p_110549_1_)
    {
        Iterator iterator = this.field_110585_a.entrySet().iterator();

        while (iterator.hasNext())
        {
            Entry entry = (Entry)iterator.next();
            this.func_110579_a((ResourceLocation)entry.getKey(), (ITextureObject)entry.getValue());
        }
    }
}