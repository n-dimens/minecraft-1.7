package net.minecraft.client.resources;

import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.io.IOException;
import java.io.InputStream;
import java.util.IllegalFormatException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;

@SideOnly(Side.CLIENT)
public class Locale
{
    private static final Splitter field_135030_b = Splitter.on('=').limit(2);
    private static final Pattern field_135031_c = Pattern.compile("%(\\d+\\$)?[\\d\\.]*[df]");
    Map field_135032_a = Maps.newHashMap();
    private boolean field_135029_d;
    private static final String __OBFID = "CL_00001097";

    public synchronized void func_135022_a(IResourceManager p_135022_1_, List p_135022_2_)
    {
        this.field_135032_a.clear();
        Iterator iterator = p_135022_2_.iterator();

        while (iterator.hasNext())
        {
            String s = (String)iterator.next();
            String s1 = String.format("lang/%s.lang", new Object[] {s});
            Iterator iterator1 = p_135022_1_.func_135055_a().iterator();

            while (iterator1.hasNext())
            {
                String s2 = (String)iterator1.next();

                try
                {
                    this.func_135028_a(p_135022_1_.func_135056_b(new ResourceLocation(s2, s1)));
                }
                catch (IOException ioexception)
                {
                    ;
                }
            }
        }

        this.func_135024_b();
    }

    public boolean func_135025_a()
    {
        return this.field_135029_d;
    }

    private void func_135024_b()
    {
        this.field_135029_d = false;
        int i = 0;
        int j = 0;
        Iterator iterator = this.field_135032_a.values().iterator();

        while (iterator.hasNext())
        {
            String s = (String)iterator.next();
            int k = s.length();
            j += k;

            for (int l = 0; l < k; ++l)
            {
                if (s.charAt(l) >= 256)
                {
                    ++i;
                }
            }
        }

        float f = (float)i / (float)j;
        this.field_135029_d = (double)f > 0.1D;
    }

    private void func_135028_a(List p_135028_1_) throws IOException
    {
        Iterator iterator = p_135028_1_.iterator();

        while (iterator.hasNext())
        {
            IResource iresource = (IResource)iterator.next();
            this.func_135021_a(iresource.func_110527_b());
        }
    }

    private void func_135021_a(InputStream p_135021_1_) throws IOException
    {
        Iterator iterator = IOUtils.readLines(p_135021_1_, Charsets.UTF_8).iterator();

        while (iterator.hasNext())
        {
            String s = (String)iterator.next();

            if (!s.isEmpty() && s.charAt(0) != 35)
            {
                String[] astring = (String[])Iterables.toArray(field_135030_b.split(s), String.class);

                if (astring != null && astring.length == 2)
                {
                    String s1 = astring[0];
                    String s2 = field_135031_c.matcher(astring[1]).replaceAll("%$1s");
                    this.field_135032_a.put(s1, s2);
                }
            }
        }
    }

    private String func_135026_c(String p_135026_1_)
    {
        String s1 = (String)this.field_135032_a.get(p_135026_1_);
        return s1 == null ? p_135026_1_ : s1;
    }

    public String func_135023_a(String p_135023_1_, Object[] p_135023_2_)
    {
        String s1 = this.func_135026_c(p_135023_1_);

        try
        {
            return String.format(s1, p_135023_2_);
        }
        catch (IllegalFormatException illegalformatexception)
        {
            return "Format error: " + s1;
        }
    }
}