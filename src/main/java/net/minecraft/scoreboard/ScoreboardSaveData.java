package net.minecraft.scoreboard;

import java.util.Collection;
import java.util.Iterator;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.world.WorldSavedData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ScoreboardSaveData extends WorldSavedData
{
    private static final Logger field_151481_a = LogManager.getLogger();
    private Scoreboard field_96507_a;
    private NBTTagCompound field_96506_b;
    private static final String __OBFID = "CL_00000620";

    public ScoreboardSaveData()
    {
        this("scoreboard");
    }

    public ScoreboardSaveData(String p_i2310_1_)
    {
        super(p_i2310_1_);
    }

    public void func_96499_a(Scoreboard p_96499_1_)
    {
        this.field_96507_a = p_96499_1_;

        if (this.field_96506_b != null)
        {
            this.func_76184_a(this.field_96506_b);
        }
    }

    public void func_76184_a(NBTTagCompound p_76184_1_)
    {
        if (this.field_96507_a == null)
        {
            this.field_96506_b = p_76184_1_;
        }
        else
        {
            this.func_96501_b(p_76184_1_.func_150295_c("Objectives", 10));
            this.func_96500_c(p_76184_1_.func_150295_c("PlayerScores", 10));

            if (p_76184_1_.func_150297_b("DisplaySlots", 10))
            {
                this.func_96504_c(p_76184_1_.func_74775_l("DisplaySlots"));
            }

            if (p_76184_1_.func_150297_b("Teams", 9))
            {
                this.func_96498_a(p_76184_1_.func_150295_c("Teams", 10));
            }
        }
    }

    protected void func_96498_a(NBTTagList p_96498_1_)
    {
        for (int i = 0; i < p_96498_1_.func_74745_c(); ++i)
        {
            NBTTagCompound nbttagcompound = p_96498_1_.func_150305_b(i);
            ScorePlayerTeam scoreplayerteam = this.field_96507_a.func_96527_f(nbttagcompound.func_74779_i("Name"));
            scoreplayerteam.func_96664_a(nbttagcompound.func_74779_i("DisplayName"));
            scoreplayerteam.func_96666_b(nbttagcompound.func_74779_i("Prefix"));
            scoreplayerteam.func_96662_c(nbttagcompound.func_74779_i("Suffix"));

            if (nbttagcompound.func_150297_b("AllowFriendlyFire", 99))
            {
                scoreplayerteam.func_96660_a(nbttagcompound.func_74767_n("AllowFriendlyFire"));
            }

            if (nbttagcompound.func_150297_b("SeeFriendlyInvisibles", 99))
            {
                scoreplayerteam.func_98300_b(nbttagcompound.func_74767_n("SeeFriendlyInvisibles"));
            }

            this.func_96502_a(scoreplayerteam, nbttagcompound.func_150295_c("Players", 8));
        }
    }

    protected void func_96502_a(ScorePlayerTeam p_96502_1_, NBTTagList p_96502_2_)
    {
        for (int i = 0; i < p_96502_2_.func_74745_c(); ++i)
        {
            this.field_96507_a.func_151392_a(p_96502_2_.func_150307_f(i), p_96502_1_.func_96661_b());
        }
    }

    protected void func_96504_c(NBTTagCompound p_96504_1_)
    {
        for (int i = 0; i < 3; ++i)
        {
            if (p_96504_1_.func_150297_b("slot_" + i, 8))
            {
                String s = p_96504_1_.func_74779_i("slot_" + i);
                ScoreObjective scoreobjective = this.field_96507_a.func_96518_b(s);
                this.field_96507_a.func_96530_a(i, scoreobjective);
            }
        }
    }

    protected void func_96501_b(NBTTagList p_96501_1_)
    {
        for (int i = 0; i < p_96501_1_.func_74745_c(); ++i)
        {
            NBTTagCompound nbttagcompound = p_96501_1_.func_150305_b(i);
            IScoreObjectiveCriteria iscoreobjectivecriteria = (IScoreObjectiveCriteria)IScoreObjectiveCriteria.field_96643_a.get(nbttagcompound.func_74779_i("CriteriaName"));
            ScoreObjective scoreobjective = this.field_96507_a.func_96535_a(nbttagcompound.func_74779_i("Name"), iscoreobjectivecriteria);
            scoreobjective.func_96681_a(nbttagcompound.func_74779_i("DisplayName"));
        }
    }

    protected void func_96500_c(NBTTagList p_96500_1_)
    {
        for (int i = 0; i < p_96500_1_.func_74745_c(); ++i)
        {
            NBTTagCompound nbttagcompound = p_96500_1_.func_150305_b(i);
            ScoreObjective scoreobjective = this.field_96507_a.func_96518_b(nbttagcompound.func_74779_i("Objective"));
            Score score = this.field_96507_a.func_96529_a(nbttagcompound.func_74779_i("Name"), scoreobjective);
            score.func_96647_c(nbttagcompound.func_74762_e("Score"));
        }
    }

    public void func_76187_b(NBTTagCompound p_76187_1_)
    {
        if (this.field_96507_a == null)
        {
            field_151481_a.warn("Tried to save scoreboard without having a scoreboard...");
        }
        else
        {
            p_76187_1_.func_74782_a("Objectives", this.func_96505_b());
            p_76187_1_.func_74782_a("PlayerScores", this.func_96503_e());
            p_76187_1_.func_74782_a("Teams", this.func_96496_a());
            this.func_96497_d(p_76187_1_);
        }
    }

    protected NBTTagList func_96496_a()
    {
        NBTTagList nbttaglist = new NBTTagList();
        Collection collection = this.field_96507_a.func_96525_g();
        Iterator iterator = collection.iterator();

        while (iterator.hasNext())
        {
            ScorePlayerTeam scoreplayerteam = (ScorePlayerTeam)iterator.next();
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            nbttagcompound.func_74778_a("Name", scoreplayerteam.func_96661_b());
            nbttagcompound.func_74778_a("DisplayName", scoreplayerteam.func_96669_c());
            nbttagcompound.func_74778_a("Prefix", scoreplayerteam.func_96668_e());
            nbttagcompound.func_74778_a("Suffix", scoreplayerteam.func_96663_f());
            nbttagcompound.func_74757_a("AllowFriendlyFire", scoreplayerteam.func_96665_g());
            nbttagcompound.func_74757_a("SeeFriendlyInvisibles", scoreplayerteam.func_98297_h());
            NBTTagList nbttaglist1 = new NBTTagList();
            Iterator iterator1 = scoreplayerteam.func_96670_d().iterator();

            while (iterator1.hasNext())
            {
                String s = (String)iterator1.next();
                nbttaglist1.func_74742_a(new NBTTagString(s));
            }

            nbttagcompound.func_74782_a("Players", nbttaglist1);
            nbttaglist.func_74742_a(nbttagcompound);
        }

        return nbttaglist;
    }

    protected void func_96497_d(NBTTagCompound p_96497_1_)
    {
        NBTTagCompound nbttagcompound1 = new NBTTagCompound();
        boolean flag = false;

        for (int i = 0; i < 3; ++i)
        {
            ScoreObjective scoreobjective = this.field_96507_a.func_96539_a(i);

            if (scoreobjective != null)
            {
                nbttagcompound1.func_74778_a("slot_" + i, scoreobjective.func_96679_b());
                flag = true;
            }
        }

        if (flag)
        {
            p_96497_1_.func_74782_a("DisplaySlots", nbttagcompound1);
        }
    }

    protected NBTTagList func_96505_b()
    {
        NBTTagList nbttaglist = new NBTTagList();
        Collection collection = this.field_96507_a.func_96514_c();
        Iterator iterator = collection.iterator();

        while (iterator.hasNext())
        {
            ScoreObjective scoreobjective = (ScoreObjective)iterator.next();
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            nbttagcompound.func_74778_a("Name", scoreobjective.func_96679_b());
            nbttagcompound.func_74778_a("CriteriaName", scoreobjective.func_96680_c().func_96636_a());
            nbttagcompound.func_74778_a("DisplayName", scoreobjective.func_96678_d());
            nbttaglist.func_74742_a(nbttagcompound);
        }

        return nbttaglist;
    }

    protected NBTTagList func_96503_e()
    {
        NBTTagList nbttaglist = new NBTTagList();
        Collection collection = this.field_96507_a.func_96528_e();
        Iterator iterator = collection.iterator();

        while (iterator.hasNext())
        {
            Score score = (Score)iterator.next();
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            nbttagcompound.func_74778_a("Name", score.func_96653_e());
            nbttagcompound.func_74778_a("Objective", score.func_96645_d().func_96679_b());
            nbttagcompound.func_74768_a("Score", score.func_96652_c());
            nbttaglist.func_74742_a(nbttagcompound);
        }

        return nbttaglist;
    }
}