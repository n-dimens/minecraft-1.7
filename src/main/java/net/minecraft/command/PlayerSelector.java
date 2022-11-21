package net.minecraft.command;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldSettings;

public class PlayerSelector
{
    private static final Pattern field_82389_a = Pattern.compile("^@([parf])(?:\\[([\\w=,!-]*)\\])?$");
    private static final Pattern field_82387_b = Pattern.compile("\\G([-!]?[\\w-]*)(?:$|,)");
    private static final Pattern field_82388_c = Pattern.compile("\\G(\\w+)=([-!]?[\\w-]*)(?:$|,)");
    private static final String __OBFID = "CL_00000086";

    public static EntityPlayerMP func_82386_a(ICommandSender p_82386_0_, String p_82386_1_)
    {
        EntityPlayerMP[] aentityplayermp = func_82380_c(p_82386_0_, p_82386_1_);
        return aentityplayermp != null && aentityplayermp.length == 1 ? aentityplayermp[0] : null;
    }

    public static IChatComponent func_150869_b(ICommandSender p_150869_0_, String p_150869_1_)
    {
        EntityPlayerMP[] aentityplayermp = func_82380_c(p_150869_0_, p_150869_1_);

        if (aentityplayermp != null && aentityplayermp.length != 0)
        {
            IChatComponent[] aichatcomponent = new IChatComponent[aentityplayermp.length];

            for (int i = 0; i < aichatcomponent.length; ++i)
            {
                aichatcomponent[i] = aentityplayermp[i].func_145748_c_();
            }

            return CommandBase.func_147177_a(aichatcomponent);
        }
        else
        {
            return null;
        }
    }

    public static EntityPlayerMP[] func_82380_c(ICommandSender p_82380_0_, String p_82380_1_)
    {
        Matcher matcher = field_82389_a.matcher(p_82380_1_);

        if (matcher.matches())
        {
            Map map = func_82381_h(matcher.group(2));
            String s1 = matcher.group(1);
            int i = func_82384_c(s1);
            int j = func_82379_d(s1);
            int k = func_82375_f(s1);
            int l = func_82376_e(s1);
            int i1 = func_82382_g(s1);
            int j1 = WorldSettings.GameType.NOT_SET.func_77148_a();
            ChunkCoordinates chunkcoordinates = p_82380_0_.func_82114_b();
            Map map1 = func_96560_a(map);
            String s2 = null;
            String s3 = null;
            boolean flag = false;

            if (map.containsKey("rm"))
            {
                i = MathHelper.func_82715_a((String)map.get("rm"), i);
                flag = true;
            }

            if (map.containsKey("r"))
            {
                j = MathHelper.func_82715_a((String)map.get("r"), j);
                flag = true;
            }

            if (map.containsKey("lm"))
            {
                k = MathHelper.func_82715_a((String)map.get("lm"), k);
            }

            if (map.containsKey("l"))
            {
                l = MathHelper.func_82715_a((String)map.get("l"), l);
            }

            if (map.containsKey("x"))
            {
                chunkcoordinates.field_71574_a = MathHelper.func_82715_a((String)map.get("x"), chunkcoordinates.field_71574_a);
                flag = true;
            }

            if (map.containsKey("y"))
            {
                chunkcoordinates.field_71572_b = MathHelper.func_82715_a((String)map.get("y"), chunkcoordinates.field_71572_b);
                flag = true;
            }

            if (map.containsKey("z"))
            {
                chunkcoordinates.field_71573_c = MathHelper.func_82715_a((String)map.get("z"), chunkcoordinates.field_71573_c);
                flag = true;
            }

            if (map.containsKey("m"))
            {
                j1 = MathHelper.func_82715_a((String)map.get("m"), j1);
            }

            if (map.containsKey("c"))
            {
                i1 = MathHelper.func_82715_a((String)map.get("c"), i1);
            }

            if (map.containsKey("team"))
            {
                s3 = (String)map.get("team");
            }

            if (map.containsKey("name"))
            {
                s2 = (String)map.get("name");
            }

            World world = flag ? p_82380_0_.func_130014_f_() : null;
            List list;

            if (!s1.equals("p") && !s1.equals("a"))
            {
                if (s1.equals("r"))
                {
                    list = MinecraftServer.func_71276_C().func_71203_ab().func_82449_a(chunkcoordinates, i, j, 0, j1, k, l, map1, s2, s3, world);
                    Collections.shuffle(list);
                    list = list.subList(0, Math.min(i1, list.size()));
                    return list.isEmpty() ? new EntityPlayerMP[0] : (EntityPlayerMP[])list.toArray(new EntityPlayerMP[list.size()]);
                }
                else
                {
                    return null;
                }
            }
            else
            {
                list = MinecraftServer.func_71276_C().func_71203_ab().func_82449_a(chunkcoordinates, i, j, i1, j1, k, l, map1, s2, s3, world);
                return list.isEmpty() ? new EntityPlayerMP[0] : (EntityPlayerMP[])list.toArray(new EntityPlayerMP[list.size()]);
            }
        }
        else
        {
            return null;
        }
    }

    public static Map func_96560_a(Map p_96560_0_)
    {
        HashMap hashmap = new HashMap();
        Iterator iterator = p_96560_0_.keySet().iterator();

        while (iterator.hasNext())
        {
            String s = (String)iterator.next();

            if (s.startsWith("score_") && s.length() > "score_".length())
            {
                String s1 = s.substring("score_".length());
                hashmap.put(s1, Integer.valueOf(MathHelper.func_82715_a((String)p_96560_0_.get(s), 1)));
            }
        }

        return hashmap;
    }

    public static boolean func_82377_a(String p_82377_0_)
    {
        Matcher matcher = field_82389_a.matcher(p_82377_0_);

        if (matcher.matches())
        {
            Map map = func_82381_h(matcher.group(2));
            String s1 = matcher.group(1);
            int i = func_82382_g(s1);

            if (map.containsKey("c"))
            {
                i = MathHelper.func_82715_a((String)map.get("c"), i);
            }

            return i != 1;
        }
        else
        {
            return false;
        }
    }

    public static boolean func_82383_a(String p_82383_0_, String p_82383_1_)
    {
        Matcher matcher = field_82389_a.matcher(p_82383_0_);

        if (matcher.matches())
        {
            String s2 = matcher.group(1);
            return p_82383_1_ == null || p_82383_1_.equals(s2);
        }
        else
        {
            return false;
        }
    }

    public static boolean func_82378_b(String p_82378_0_)
    {
        return func_82383_a(p_82378_0_, (String)null);
    }

    private static final int func_82384_c(String p_82384_0_)
    {
        return 0;
    }

    private static final int func_82379_d(String p_82379_0_)
    {
        return 0;
    }

    private static final int func_82376_e(String p_82376_0_)
    {
        return Integer.MAX_VALUE;
    }

    private static final int func_82375_f(String p_82375_0_)
    {
        return 0;
    }

    private static final int func_82382_g(String p_82382_0_)
    {
        return p_82382_0_.equals("a") ? 0 : 1;
    }

    private static Map func_82381_h(String p_82381_0_)
    {
        HashMap hashmap = new HashMap();

        if (p_82381_0_ == null)
        {
            return hashmap;
        }
        else
        {
            Matcher matcher = field_82387_b.matcher(p_82381_0_);
            int i = 0;
            int j;

            for (j = -1; matcher.find(); j = matcher.end())
            {
                String s1 = null;

                switch (i++)
                {
                    case 0:
                        s1 = "x";
                        break;
                    case 1:
                        s1 = "y";
                        break;
                    case 2:
                        s1 = "z";
                        break;
                    case 3:
                        s1 = "r";
                }

                if (s1 != null && matcher.group(1).length() > 0)
                {
                    hashmap.put(s1, matcher.group(1));
                }
            }

            if (j < p_82381_0_.length())
            {
                matcher = field_82388_c.matcher(j == -1 ? p_82381_0_ : p_82381_0_.substring(j));

                while (matcher.find())
                {
                    hashmap.put(matcher.group(1), matcher.group(2));
                }
            }

            return hashmap;
        }
    }
}