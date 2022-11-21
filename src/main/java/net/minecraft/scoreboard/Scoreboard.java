package net.minecraft.scoreboard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Scoreboard
{
    private final Map field_96545_a = new HashMap();
    private final Map field_96543_b = new HashMap();
    private final Map field_96544_c = new HashMap();
    private final ScoreObjective[] field_96541_d = new ScoreObjective[3];
    private final Map field_96542_e = new HashMap();
    private final Map field_96540_f = new HashMap();
    private static final String __OBFID = "CL_00000619";

    public ScoreObjective func_96518_b(String p_96518_1_)
    {
        return (ScoreObjective)this.field_96545_a.get(p_96518_1_);
    }

    public ScoreObjective func_96535_a(String p_96535_1_, IScoreObjectiveCriteria p_96535_2_)
    {
        ScoreObjective scoreobjective = this.func_96518_b(p_96535_1_);

        if (scoreobjective != null)
        {
            throw new IllegalArgumentException("An objective with the name \'" + p_96535_1_ + "\' already exists!");
        }
        else
        {
            scoreobjective = new ScoreObjective(this, p_96535_1_, p_96535_2_);
            Object object = (List)this.field_96543_b.get(p_96535_2_);

            if (object == null)
            {
                object = new ArrayList();
                this.field_96543_b.put(p_96535_2_, object);
            }

            ((List)object).add(scoreobjective);
            this.field_96545_a.put(p_96535_1_, scoreobjective);
            this.func_96522_a(scoreobjective);
            return scoreobjective;
        }
    }

    public Collection func_96520_a(IScoreObjectiveCriteria p_96520_1_)
    {
        Collection collection = (Collection)this.field_96543_b.get(p_96520_1_);
        return collection == null ? new ArrayList() : new ArrayList(collection);
    }

    public Score func_96529_a(String p_96529_1_, ScoreObjective p_96529_2_)
    {
        Object object = (Map)this.field_96544_c.get(p_96529_1_);

        if (object == null)
        {
            object = new HashMap();
            this.field_96544_c.put(p_96529_1_, object);
        }

        Score score = (Score)((Map)object).get(p_96529_2_);

        if (score == null)
        {
            score = new Score(this, p_96529_2_, p_96529_1_);
            ((Map)object).put(p_96529_2_, score);
        }

        return score;
    }

    public Collection func_96534_i(ScoreObjective p_96534_1_)
    {
        ArrayList arraylist = new ArrayList();
        Iterator iterator = this.field_96544_c.values().iterator();

        while (iterator.hasNext())
        {
            Map map = (Map)iterator.next();
            Score score = (Score)map.get(p_96534_1_);

            if (score != null)
            {
                arraylist.add(score);
            }
        }

        Collections.sort(arraylist, Score.field_96658_a);
        return arraylist;
    }

    public Collection func_96514_c()
    {
        return this.field_96545_a.values();
    }

    public Collection func_96526_d()
    {
        return this.field_96544_c.keySet();
    }

    public void func_96515_c(String p_96515_1_)
    {
        Map map = (Map)this.field_96544_c.remove(p_96515_1_);

        if (map != null)
        {
            this.func_96516_a(p_96515_1_);
        }
    }

    public Collection func_96528_e()
    {
        Collection collection = this.field_96544_c.values();
        ArrayList arraylist = new ArrayList();
        Iterator iterator = collection.iterator();

        while (iterator.hasNext())
        {
            Map map = (Map)iterator.next();
            arraylist.addAll(map.values());
        }

        return arraylist;
    }

    public Map func_96510_d(String p_96510_1_)
    {
        Object object = (Map)this.field_96544_c.get(p_96510_1_);

        if (object == null)
        {
            object = new HashMap();
        }

        return (Map)object;
    }

    public void func_96519_k(ScoreObjective p_96519_1_)
    {
        this.field_96545_a.remove(p_96519_1_.func_96679_b());

        for (int i = 0; i < 3; ++i)
        {
            if (this.func_96539_a(i) == p_96519_1_)
            {
                this.func_96530_a(i, (ScoreObjective)null);
            }
        }

        List list = (List)this.field_96543_b.get(p_96519_1_.func_96680_c());

        if (list != null)
        {
            list.remove(p_96519_1_);
        }

        Iterator iterator = this.field_96544_c.values().iterator();

        while (iterator.hasNext())
        {
            Map map = (Map)iterator.next();
            map.remove(p_96519_1_);
        }

        this.func_96533_c(p_96519_1_);
    }

    public void func_96530_a(int p_96530_1_, ScoreObjective p_96530_2_)
    {
        this.field_96541_d[p_96530_1_] = p_96530_2_;
    }

    public ScoreObjective func_96539_a(int p_96539_1_)
    {
        return this.field_96541_d[p_96539_1_];
    }

    public ScorePlayerTeam func_96508_e(String p_96508_1_)
    {
        return (ScorePlayerTeam)this.field_96542_e.get(p_96508_1_);
    }

    public ScorePlayerTeam func_96527_f(String p_96527_1_)
    {
        ScorePlayerTeam scoreplayerteam = this.func_96508_e(p_96527_1_);

        if (scoreplayerteam != null)
        {
            throw new IllegalArgumentException("A team with the name \'" + p_96527_1_ + "\' already exists!");
        }
        else
        {
            scoreplayerteam = new ScorePlayerTeam(this, p_96527_1_);
            this.field_96542_e.put(p_96527_1_, scoreplayerteam);
            this.func_96523_a(scoreplayerteam);
            return scoreplayerteam;
        }
    }

    public void func_96511_d(ScorePlayerTeam p_96511_1_)
    {
        this.field_96542_e.remove(p_96511_1_.func_96661_b());
        Iterator iterator = p_96511_1_.func_96670_d().iterator();

        while (iterator.hasNext())
        {
            String s = (String)iterator.next();
            this.field_96540_f.remove(s);
        }

        this.func_96513_c(p_96511_1_);
    }

    public boolean func_151392_a(String p_151392_1_, String p_151392_2_)
    {
        if (!this.field_96542_e.containsKey(p_151392_2_))
        {
            return false;
        }
        else
        {
            ScorePlayerTeam scoreplayerteam = this.func_96508_e(p_151392_2_);

            if (this.func_96509_i(p_151392_1_) != null)
            {
                this.func_96524_g(p_151392_1_);
            }

            this.field_96540_f.put(p_151392_1_, scoreplayerteam);
            scoreplayerteam.func_96670_d().add(p_151392_1_);
            return true;
        }
    }

    public boolean func_96524_g(String p_96524_1_)
    {
        ScorePlayerTeam scoreplayerteam = this.func_96509_i(p_96524_1_);

        if (scoreplayerteam != null)
        {
            this.func_96512_b(p_96524_1_, scoreplayerteam);
            return true;
        }
        else
        {
            return false;
        }
    }

    public void func_96512_b(String p_96512_1_, ScorePlayerTeam p_96512_2_)
    {
        if (this.func_96509_i(p_96512_1_) != p_96512_2_)
        {
            throw new IllegalStateException("Player is either on another team or not on any team. Cannot remove from team \'" + p_96512_2_.func_96661_b() + "\'.");
        }
        else
        {
            this.field_96540_f.remove(p_96512_1_);
            p_96512_2_.func_96670_d().remove(p_96512_1_);
        }
    }

    public Collection func_96531_f()
    {
        return this.field_96542_e.keySet();
    }

    public Collection func_96525_g()
    {
        return this.field_96542_e.values();
    }

    public ScorePlayerTeam func_96509_i(String p_96509_1_)
    {
        return (ScorePlayerTeam)this.field_96540_f.get(p_96509_1_);
    }

    public void func_96522_a(ScoreObjective p_96522_1_) {}

    public void func_96532_b(ScoreObjective p_96532_1_) {}

    public void func_96533_c(ScoreObjective p_96533_1_) {}

    public void func_96536_a(Score p_96536_1_) {}

    public void func_96516_a(String p_96516_1_) {}

    public void func_96523_a(ScorePlayerTeam p_96523_1_) {}

    public void func_96538_b(ScorePlayerTeam p_96538_1_) {}

    public void func_96513_c(ScorePlayerTeam p_96513_1_) {}

    public static String func_96517_b(int p_96517_0_)
    {
        switch (p_96517_0_)
        {
            case 0:
                return "list";
            case 1:
                return "sidebar";
            case 2:
                return "belowName";
            default:
                return null;
        }
    }

    public static int func_96537_j(String p_96537_0_)
    {
        return p_96537_0_.equalsIgnoreCase("list") ? 0 : (p_96537_0_.equalsIgnoreCase("sidebar") ? 1 : (p_96537_0_.equalsIgnoreCase("belowName") ? 2 : -1));
    }
}