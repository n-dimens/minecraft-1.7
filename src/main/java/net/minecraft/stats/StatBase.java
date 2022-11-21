package net.minecraft.stats;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import net.minecraft.event.HoverEvent;
import net.minecraft.scoreboard.IScoreObjectiveCriteria;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;

public class StatBase
{
    public final String id;
    private final IChatComponent nameId;
    public boolean awardLocallyOnly;
    private final IStatType formatter;
    private final IScoreObjectiveCriteria objectiveCriteria;
    private Class field_150956_d;
    private static final NumberFormat INTEGER_FORMAT = NumberFormat.getIntegerInstance(Locale.US);
    public static final IStatType integerFormatter = new IStatType()
    {
        private static final String __OBFID = "CL_00001473";
        @SideOnly(Side.CLIENT)
        public String format(int value)
        {
            return StatBase.INTEGER_FORMAT.format((long) value);
        }
    };
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("########0.00");
    public final static IStatType DATETIME_FORMATTER = new IStatType()
    {
        private static final String __OBFID = "CL_00001474";
        @SideOnly(Side.CLIENT)
        public String format(int value)
        {
            double d0 = (double) value / 20.0D;
            double d1 = d0 / 60.0D;
            double d2 = d1 / 60.0D;
            double d3 = d2 / 24.0D;
            double d4 = d3 / 365.0D;
            return d4 > 0.5D ? StatBase.DECIMAL_FORMAT.format(d4) + " y" : (d3 > 0.5D ? StatBase.DECIMAL_FORMAT.format(d3) + " d" : (d2 > 0.5D ? StatBase.DECIMAL_FORMAT.format(d2) + " h" : (d1 > 0.5D ? StatBase.DECIMAL_FORMAT.format(d1) + " m" : d0 + " s")));
        }
    };
    public static IStatType DISTANCE_FORMATTER = new IStatType()
    {
        private static final String __OBFID = "CL_00001475";
        @SideOnly(Side.CLIENT)
        public String format(int value)
        {
            double d0 = (double) value / 100.0D;
            double d1 = d0 / 1000.0D;
            return d1 > 0.5D ? StatBase.DECIMAL_FORMAT.format(d1) + " km" : (d0 > 0.5D ? StatBase.DECIMAL_FORMAT.format(d0) + " m" : value + " cm");
        }
    };
    public final static IStatType DAMAGE_FORMATTER = new IStatType()
    {
        private static final String __OBFID = "CL_00001476";
        @SideOnly(Side.CLIENT)
        public String format(int value)
        {
            return StatBase.DECIMAL_FORMAT.format((double) value * 0.1D);
        }
    };
    private static final String __OBFID = "CL_00001472";

    public StatBase(String id, IChatComponent nameId, IStatType formatter)
    {
        this.id = id;
        this.nameId = nameId;
        this.formatter = formatter;
        this.objectiveCriteria = new ObjectiveStat(this);
        IScoreObjectiveCriteria.field_96643_a.put(this.objectiveCriteria.func_96636_a(), this.objectiveCriteria);
    }

    public StatBase(String id, IChatComponent nameId)
    {
        this(id, nameId, integerFormatter);
    }

    public StatBase awardOnlyLocal()
    {
        this.awardLocallyOnly = true;
        return this;
    }

    public StatBase add()
    {
        if (StatList.field_75942_a.containsKey(this.id))
        {
            throw new RuntimeException("Duplicate stat id: \"" + ((StatBase)StatList.field_75942_a.get(this.id)).nameId + "\" and \"" + this.nameId + "\" at id " + this.id);
        }
        else
        {
            StatList.field_75940_b.add(this);
            StatList.field_75942_a.put(this.id, this);
            return this;
        }
    }

    public boolean func_75967_d()
    {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public String func_75968_a(int p_75968_1_)
    {
        return this.formatter.format(p_75968_1_);
    }

    public IChatComponent func_150951_e()
    {
        IChatComponent ichatcomponent = this.nameId.func_150259_f();
        ichatcomponent.func_150256_b().func_150238_a(EnumChatFormatting.GRAY);
        ichatcomponent.func_150256_b().func_150209_a(new HoverEvent(HoverEvent.Action.SHOW_ACHIEVEMENT, new ChatComponentText(this.id)));
        return ichatcomponent;
    }

    public IChatComponent func_150955_j()
    {
        IChatComponent ichatcomponent = this.func_150951_e();
        IChatComponent ichatcomponent1 = (new ChatComponentText("[")).func_150257_a(ichatcomponent).func_150258_a("]");
        ichatcomponent1.func_150255_a(ichatcomponent.func_150256_b());
        return ichatcomponent1;
    }

    @Override
    public boolean equals(Object stat)
    {
        if (this == stat)
        {
            return true;
        }
        else if (stat != null && this.getClass() == stat.getClass())
        {
            StatBase statbase = (StatBase)stat;
            return this.id.equals(statbase.id);
        }
        else
        {
            return false;
        }
    }

    @Override
    public int hashCode()
    {
        return this.id.hashCode();
    }

    @Override
    public String toString()
    {
        return "Stat{id=" + this.id + ", nameId=" + this.nameId + ", awardLocallyOnly=" + this.awardLocallyOnly + ", formatter=" + this.formatter + ", objectiveCriteria=" + this.objectiveCriteria + '}';
    }

    public IScoreObjectiveCriteria func_150952_k()
    {
        return this.objectiveCriteria;
    }

    public Class func_150954_l()
    {
        return this.field_150956_d;
    }

    public StatBase func_150953_b(Class p_150953_1_)
    {
        this.field_150956_d = p_150953_1_;
        return this;
    }
}