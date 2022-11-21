package net.minecraft.profiler;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Profiler
{
    private static final Logger field_151234_b = LogManager.getLogger();
    private final List field_76325_b = new ArrayList();
    private final List field_76326_c = new ArrayList();
    public boolean field_76327_a;
    private String field_76323_d = "";
    private final Map field_76324_e = new HashMap();
    private static final String __OBFID = "CL_00001497";

    public void func_76317_a()
    {
        this.field_76324_e.clear();
        this.field_76323_d = "";
        this.field_76325_b.clear();
    }

    public void func_76320_a(String p_76320_1_)
    {
        if (this.field_76327_a)
        {
            if (this.field_76323_d.length() > 0)
            {
                this.field_76323_d = this.field_76323_d + ".";
            }

            this.field_76323_d = this.field_76323_d + p_76320_1_;
            this.field_76325_b.add(this.field_76323_d);
            this.field_76326_c.add(Long.valueOf(System.nanoTime()));
        }
    }

    public void func_76319_b()
    {
        if (this.field_76327_a)
        {
            long i = System.nanoTime();
            long j = ((Long)this.field_76326_c.remove(this.field_76326_c.size() - 1)).longValue();
            this.field_76325_b.remove(this.field_76325_b.size() - 1);
            long k = i - j;

            if (this.field_76324_e.containsKey(this.field_76323_d))
            {
                this.field_76324_e.put(this.field_76323_d, Long.valueOf(((Long)this.field_76324_e.get(this.field_76323_d)).longValue() + k));
            }
            else
            {
                this.field_76324_e.put(this.field_76323_d, Long.valueOf(k));
            }

            if (k > 100000000L)
            {
                field_151234_b.warn("Something\'s taking too long! \'" + this.field_76323_d + "\' took aprox " + (double)k / 1000000.0D + " ms");
            }

            this.field_76323_d = !this.field_76325_b.isEmpty() ? (String)this.field_76325_b.get(this.field_76325_b.size() - 1) : "";
        }
    }

    public List func_76321_b(String p_76321_1_)
    {
        if (!this.field_76327_a)
        {
            return null;
        }
        else
        {
            long i = this.field_76324_e.containsKey("root") ? ((Long)this.field_76324_e.get("root")).longValue() : 0L;
            long j = this.field_76324_e.containsKey(p_76321_1_) ? ((Long)this.field_76324_e.get(p_76321_1_)).longValue() : -1L;
            ArrayList arraylist = new ArrayList();

            if (p_76321_1_.length() > 0)
            {
                p_76321_1_ = p_76321_1_ + ".";
            }

            long k = 0L;
            Iterator iterator = this.field_76324_e.keySet().iterator();

            while (iterator.hasNext())
            {
                String s1 = (String)iterator.next();

                if (s1.length() > p_76321_1_.length() && s1.startsWith(p_76321_1_) && s1.indexOf(".", p_76321_1_.length() + 1) < 0)
                {
                    k += ((Long)this.field_76324_e.get(s1)).longValue();
                }
            }

            float f = (float)k;

            if (k < j)
            {
                k = j;
            }

            if (i < k)
            {
                i = k;
            }

            Iterator iterator1 = this.field_76324_e.keySet().iterator();
            String s2;

            while (iterator1.hasNext())
            {
                s2 = (String)iterator1.next();

                if (s2.length() > p_76321_1_.length() && s2.startsWith(p_76321_1_) && s2.indexOf(".", p_76321_1_.length() + 1) < 0)
                {
                    long l = ((Long)this.field_76324_e.get(s2)).longValue();
                    double d0 = (double)l * 100.0D / (double)k;
                    double d1 = (double)l * 100.0D / (double)i;
                    String s3 = s2.substring(p_76321_1_.length());
                    arraylist.add(new Profiler.Result(s3, d0, d1));
                }
            }

            iterator1 = this.field_76324_e.keySet().iterator();

            while (iterator1.hasNext())
            {
                s2 = (String)iterator1.next();
                this.field_76324_e.put(s2, Long.valueOf(((Long)this.field_76324_e.get(s2)).longValue() * 999L / 1000L));
            }

            if ((float)k > f)
            {
                arraylist.add(new Profiler.Result("unspecified", (double)((float)k - f) * 100.0D / (double)k, (double)((float)k - f) * 100.0D / (double)i));
            }

            Collections.sort(arraylist);
            arraylist.add(0, new Profiler.Result(p_76321_1_, 100.0D, (double)k * 100.0D / (double)i));
            return arraylist;
        }
    }

    public void func_76318_c(String p_76318_1_)
    {
        this.func_76319_b();
        this.func_76320_a(p_76318_1_);
    }

    public String func_76322_c()
    {
        return this.field_76325_b.size() == 0 ? "[UNKNOWN]" : (String)this.field_76325_b.get(this.field_76325_b.size() - 1);
    }

    public static final class Result implements Comparable
        {
            public double field_76332_a;
            public double field_76330_b;
            public String field_76331_c;
            private static final String __OBFID = "CL_00001498";

            public Result(String p_i1554_1_, double p_i1554_2_, double p_i1554_4_)
            {
                this.field_76331_c = p_i1554_1_;
                this.field_76332_a = p_i1554_2_;
                this.field_76330_b = p_i1554_4_;
            }

            public int compareTo(Profiler.Result p_compareTo_1_)
            {
                return p_compareTo_1_.field_76332_a < this.field_76332_a ? -1 : (p_compareTo_1_.field_76332_a > this.field_76332_a ? 1 : p_compareTo_1_.field_76331_c.compareTo(this.field_76331_c));
            }

            @SideOnly(Side.CLIENT)
            public int func_76329_a()
            {
                return (this.field_76331_c.hashCode() & 11184810) + 4473924;
            }

            public int compareTo(Object p_compareTo_1_)
            {
                return this.compareTo((Profiler.Result)p_compareTo_1_);
            }
        }
}