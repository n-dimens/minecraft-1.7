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
    private static final Logger LOG = LogManager.getLogger();
    private final List<String> actionsStack = new ArrayList<>(); // стек измеряемых действий
    private final List<Long> startTimeStack = new ArrayList<>(); // время старта измеряемых действий
    public boolean enabled;
    private String callStack = "";
    private final Map<String, Long> callStackDurations = new HashMap<>();
    private static final String __OBFID = "CL_00001497";

    public void reset()
    {
        this.callStackDurations.clear();
        this.callStack = "";
        this.actionsStack.clear();
    }

    /**
     * Начать отслеживать действие
     */
    public void startMeasure(String action)
    {
        if (this.enabled)
        {
            if (this.callStack.length() > 0)
            {
                this.callStack = this.callStack + ".";
            }

            this.callStack = this.callStack + action;
            this.actionsStack.add(this.callStack);
            this.startTimeStack.add(System.nanoTime());
        }
    }

    /**
     * Измерить время выполнение последнего действия и удалить его из стека
     */
    public void endMeasure()
    {
        if (this.enabled)
        {
            long endTime = System.nanoTime();
            long startTime = this.startTimeStack.remove(this.startTimeStack.size() - 1);
            this.actionsStack.remove(this.actionsStack.size() - 1);
            long duration = endTime - startTime;

            if (this.callStackDurations.containsKey(this.callStack))
            {
                this.callStackDurations.put(this.callStack, this.callStackDurations.get(this.callStack) + duration);
            }
            else
            {
                this.callStackDurations.put(this.callStack, duration);
            }

            if (duration > 100000000L)
            {
                LOG.warn("Something's taking too long! '" + this.callStack + "' took aprox " + (double)duration / 1000000.0D + " ms");
            }

            this.callStack = !this.actionsStack.isEmpty() ? (String)this.actionsStack.get(this.actionsStack.size() - 1) : "";
        }
    }

    public List<Profiler.Result> func_76321_b(String p_76321_1_)
    {
        if (!this.enabled)
        {
            return null;
        }
        else
        {
            long i = this.callStackDurations.containsKey("root") ? this.callStackDurations.get("root") : 0L;
            long j = this.callStackDurations.containsKey(p_76321_1_) ? this.callStackDurations.get(p_76321_1_) : -1L;
            ArrayList<Profiler.Result> arraylist = new ArrayList<>();

            if (p_76321_1_.length() > 0)
            {
                p_76321_1_ = p_76321_1_ + ".";
            }

            long k = 0L;
            Iterator iterator = this.callStackDurations.keySet().iterator();

            while (iterator.hasNext())
            {
                String s1 = (String)iterator.next();

                if (s1.length() > p_76321_1_.length() && s1.startsWith(p_76321_1_) && s1.indexOf(".", p_76321_1_.length() + 1) < 0)
                {
                    k += this.callStackDurations.get(s1);
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

            Iterator iterator1 = this.callStackDurations.keySet().iterator();
            String s2;

            while (iterator1.hasNext())
            {
                s2 = (String)iterator1.next();

                if (s2.length() > p_76321_1_.length() && s2.startsWith(p_76321_1_) && s2.indexOf(".", p_76321_1_.length() + 1) < 0)
                {
                    long l = this.callStackDurations.get(s2);
                    double d0 = (double)l * 100.0D / (double)k;
                    double d1 = (double)l * 100.0D / (double)i;
                    String s3 = s2.substring(p_76321_1_.length());
                    arraylist.add(new Profiler.Result(s3, d0, d1));
                }
            }

            iterator1 = this.callStackDurations.keySet().iterator();

            while (iterator1.hasNext())
            {
                s2 = (String)iterator1.next();
                this.callStackDurations.put(s2, this.callStackDurations.get(s2) * 999L / 1000L);
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

    /**
     * Завершает измерение действия и начинает измерение нового
     */
    public void startNewMeasure(String nextAction)
    {
        this.endMeasure();
        this.startMeasure(nextAction);
    }

    public String getNextAction()
    {
        return this.actionsStack.size() == 0 ? "[UNKNOWN]" : this.actionsStack.get(this.actionsStack.size() - 1);
    }

    public static final class Result implements Comparable<Profiler.Result>
        {
            public final double field_76332_a;
            public final double field_76330_b;
            public final String field_76331_c;
            private static final String __OBFID = "CL_00001498";

            public Result(String p_i1554_1_, double p_i1554_2_, double p_i1554_4_)
            {
                this.field_76331_c = p_i1554_1_;
                this.field_76332_a = p_i1554_2_;
                this.field_76330_b = p_i1554_4_;
            }

            @Override
            public int compareTo(Profiler.Result profileResult)
            {
                return profileResult.field_76332_a < this.field_76332_a ? -1 : (profileResult.field_76332_a > this.field_76332_a ? 1 : profileResult.field_76331_c.compareTo(this.field_76331_c));
            }

            @SideOnly(Side.CLIENT)
            public int func_76329_a()
            {
                return (this.field_76331_c.hashCode() & 11184810) + 4473924;
            }
        }
}