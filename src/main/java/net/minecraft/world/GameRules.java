package net.minecraft.world;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;
import net.minecraft.nbt.NBTTagCompound;

public class GameRules
{
    private TreeMap field_82771_a = new TreeMap();
    private static final String __OBFID = "CL_00000136";

    public GameRules()
    {
        this.func_82769_a("doFireTick", "true");
        this.func_82769_a("mobGriefing", "true");
        this.func_82769_a("keepInventory", "false");
        this.func_82769_a("doMobSpawning", "true");
        this.func_82769_a("doMobLoot", "true");
        this.func_82769_a("doTileDrops", "true");
        this.func_82769_a("commandBlockOutput", "true");
        this.func_82769_a("naturalRegeneration", "true");
        this.func_82769_a("doDaylightCycle", "true");
    }

    public void func_82769_a(String p_82769_1_, String p_82769_2_)
    {
        this.field_82771_a.put(p_82769_1_, new GameRules.Value(p_82769_2_));
    }

    public void func_82764_b(String p_82764_1_, String p_82764_2_)
    {
        GameRules.Value value = (GameRules.Value)this.field_82771_a.get(p_82764_1_);

        if (value != null)
        {
            value.func_82757_a(p_82764_2_);
        }
        else
        {
            this.func_82769_a(p_82764_1_, p_82764_2_);
        }
    }

    public String func_82767_a(String p_82767_1_)
    {
        GameRules.Value value = (GameRules.Value)this.field_82771_a.get(p_82767_1_);
        return value != null ? value.func_82756_a() : "";
    }

    public boolean func_82766_b(String p_82766_1_)
    {
        GameRules.Value value = (GameRules.Value)this.field_82771_a.get(p_82766_1_);
        return value != null ? value.func_82758_b() : false;
    }

    public NBTTagCompound func_82770_a()
    {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        Iterator iterator = this.field_82771_a.keySet().iterator();

        while (iterator.hasNext())
        {
            String s = (String)iterator.next();
            GameRules.Value value = (GameRules.Value)this.field_82771_a.get(s);
            nbttagcompound.func_74778_a(s, value.func_82756_a());
        }

        return nbttagcompound;
    }

    public void func_82768_a(NBTTagCompound p_82768_1_)
    {
        Set set = p_82768_1_.func_150296_c();
        Iterator iterator = set.iterator();

        while (iterator.hasNext())
        {
            String s = (String)iterator.next();
            String s1 = p_82768_1_.func_74779_i(s);
            this.func_82764_b(s, s1);
        }
    }

    public String[] func_82763_b()
    {
        return (String[])this.field_82771_a.keySet().toArray(new String[0]);
    }

    public boolean func_82765_e(String p_82765_1_)
    {
        return this.field_82771_a.containsKey(p_82765_1_);
    }

    static class Value
        {
            private String field_82762_a;
            private boolean field_82760_b;
            private int field_82761_c;
            private double field_82759_d;
            private static final String __OBFID = "CL_00000137";

            public Value(String p_i1949_1_)
            {
                this.func_82757_a(p_i1949_1_);
            }

            public void func_82757_a(String p_82757_1_)
            {
                this.field_82762_a = p_82757_1_;
                this.field_82760_b = Boolean.parseBoolean(p_82757_1_);

                try
                {
                    this.field_82761_c = Integer.parseInt(p_82757_1_);
                }
                catch (NumberFormatException numberformatexception1)
                {
                    ;
                }

                try
                {
                    this.field_82759_d = Double.parseDouble(p_82757_1_);
                }
                catch (NumberFormatException numberformatexception)
                {
                    ;
                }
            }

            public String func_82756_a()
            {
                return this.field_82762_a;
            }

            public boolean func_82758_b()
            {
                return this.field_82760_b;
            }
        }
}