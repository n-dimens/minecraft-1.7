package net.minecraft.scoreboard;

import java.util.Comparator;
import java.util.List;

public class Score
{
    public static final Comparator field_96658_a = new Comparator()
    {
        private static final String __OBFID = "CL_00000618";
        public int compare(Score p_compare_1_, Score p_compare_2_)
        {
            return p_compare_1_.func_96652_c() > p_compare_2_.func_96652_c() ? 1 : (p_compare_1_.func_96652_c() < p_compare_2_.func_96652_c() ? -1 : 0);
        }
        public int compare(Object p_compare_1_, Object p_compare_2_)
        {
            return this.compare((Score)p_compare_1_, (Score)p_compare_2_);
        }
    };
    private final Scoreboard field_96656_b;
    private final ScoreObjective field_96657_c;
    private final String field_96654_d;
    private int field_96655_e;
    private static final String __OBFID = "CL_00000617";

    public Score(Scoreboard p_i2309_1_, ScoreObjective p_i2309_2_, String p_i2309_3_)
    {
        this.field_96656_b = p_i2309_1_;
        this.field_96657_c = p_i2309_2_;
        this.field_96654_d = p_i2309_3_;
    }

    public void func_96649_a(int p_96649_1_)
    {
        if (this.field_96657_c.func_96680_c().func_96637_b())
        {
            throw new IllegalStateException("Cannot modify read-only score");
        }
        else
        {
            this.func_96647_c(this.func_96652_c() + p_96649_1_);
        }
    }

    public void func_96646_b(int p_96646_1_)
    {
        if (this.field_96657_c.func_96680_c().func_96637_b())
        {
            throw new IllegalStateException("Cannot modify read-only score");
        }
        else
        {
            this.func_96647_c(this.func_96652_c() - p_96646_1_);
        }
    }

    public void func_96648_a()
    {
        if (this.field_96657_c.func_96680_c().func_96637_b())
        {
            throw new IllegalStateException("Cannot modify read-only score");
        }
        else
        {
            this.func_96649_a(1);
        }
    }

    public int func_96652_c()
    {
        return this.field_96655_e;
    }

    public void func_96647_c(int p_96647_1_)
    {
        int j = this.field_96655_e;
        this.field_96655_e = p_96647_1_;

        if (j != p_96647_1_)
        {
            this.func_96650_f().func_96536_a(this);
        }
    }

    public ScoreObjective func_96645_d()
    {
        return this.field_96657_c;
    }

    public String func_96653_e()
    {
        return this.field_96654_d;
    }

    public Scoreboard func_96650_f()
    {
        return this.field_96656_b;
    }

    public void func_96651_a(List p_96651_1_)
    {
        this.func_96647_c(this.field_96657_c.func_96680_c().func_96635_a(p_96651_1_));
    }
}