package net.minecraft.world;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;
import net.minecraft.nbt.NBTTagCompound;

public class GameRules
{
    private final TreeMap<String, Object> rules = new TreeMap<>();
    private static final String __OBFID = "CL_00000136";

    public GameRules()
    {
        this.addRule("doFireTick", "true");
        this.addRule("mobGriefing", "false");
        this.addRule("keepInventory", "true");
        this.addRule("doMobSpawning", "true");
        this.addRule("doMobLoot", "true");
        this.addRule("doTileDrops", "true");
        this.addRule("commandBlockOutput", "true");
        this.addRule("naturalRegeneration", "true");
        this.addRule("doDaylightCycle", "true");
    }

    public void addRule(String name, String strValue)
    {
        this.rules.put(name, new GameRules.Value(strValue));
    }

    public void setRuleValue(String name, String strValue)
    {
        GameRules.Value value = (GameRules.Value)this.rules.get(name);

        if (value != null)
        {
            value.parse(strValue);
        }
        else
        {
            this.addRule(name, strValue);
        }
    }

    public String getStringValue(String ruleName)
    {
        GameRules.Value value = (GameRules.Value)this.rules.get(ruleName);
        return value != null ? value.getString() : "";
    }

    public boolean getBooleanValue(String ruleName)
    {
        GameRules.Value value = (GameRules.Value)this.rules.get(ruleName);
        return value != null ? value.getBoolean() : false;
    }

    public NBTTagCompound func_82770_a()
    {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        Iterator iterator = this.rules.keySet().iterator();

        while (iterator.hasNext())
        {
            String s = (String)iterator.next();
            GameRules.Value value = (GameRules.Value)this.rules.get(s);
            nbttagcompound.func_74778_a(s, value.getString());
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
            this.setRuleValue(s, s1);
        }
    }

    public String[] getRuleNames()
    {
        return this.rules.keySet().toArray(new String[0]);
    }

    public boolean contains(String ruleName)
    {
        return this.rules.containsKey(ruleName);
    }

    static class Value
        {
            private String stringValue;
            private boolean booleanValue;
            private int intValue;
            private double doubleValue;
            private static final String __OBFID = "CL_00000137";

            public Value(String p_i1949_1_)
            {
                this.parse(p_i1949_1_);
            }

            public void parse(String strValue)
            {
                this.stringValue = strValue;
                this.booleanValue = Boolean.parseBoolean(strValue);

                try
                {
                    this.intValue = Integer.parseInt(strValue);
                }
                catch (NumberFormatException numberformatexception1)
                {
                    ;
                }

                try
                {
                    this.doubleValue = Double.parseDouble(strValue);
                }
                catch (NumberFormatException numberformatexception)
                {
                    ;
                }
            }

            public String getString()
            {
                return this.stringValue;
            }

            public boolean getBoolean()
            {
                return this.booleanValue;
            }
        }
}