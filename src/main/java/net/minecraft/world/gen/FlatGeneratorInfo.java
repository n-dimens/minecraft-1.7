package net.minecraft.world.gen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.biome.BiomeGenBase;

public class FlatGeneratorInfo
{
    private final List field_82655_a = new ArrayList();
    private final Map field_82653_b = new HashMap();
    private int field_82654_c;
    private static final String __OBFID = "CL_00000440";

    public int func_82648_a()
    {
        return this.field_82654_c;
    }

    public void func_82647_a(int p_82647_1_)
    {
        this.field_82654_c = p_82647_1_;
    }

    public Map func_82644_b()
    {
        return this.field_82653_b;
    }

    public List func_82650_c()
    {
        return this.field_82655_a;
    }

    public void func_82645_d()
    {
        int i = 0;
        FlatLayerInfo flatlayerinfo;

        for (Iterator iterator = this.field_82655_a.iterator(); iterator.hasNext(); i += flatlayerinfo.func_82657_a())
        {
            flatlayerinfo = (FlatLayerInfo)iterator.next();
            flatlayerinfo.func_82660_d(i);
        }
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(2);
        stringbuilder.append(";");
        int i;

        for (i = 0; i < this.field_82655_a.size(); ++i)
        {
            if (i > 0)
            {
                stringbuilder.append(",");
            }

            stringbuilder.append(((FlatLayerInfo)this.field_82655_a.get(i)).toString());
        }

        stringbuilder.append(";");
        stringbuilder.append(this.field_82654_c);

        if (!this.field_82653_b.isEmpty())
        {
            stringbuilder.append(";");
            i = 0;
            Iterator iterator = this.field_82653_b.entrySet().iterator();

            while (iterator.hasNext())
            {
                Entry entry = (Entry)iterator.next();

                if (i++ > 0)
                {
                    stringbuilder.append(",");
                }

                stringbuilder.append(((String)entry.getKey()).toLowerCase());
                Map map = (Map)entry.getValue();

                if (!map.isEmpty())
                {
                    stringbuilder.append("(");
                    int j = 0;
                    Iterator iterator1 = map.entrySet().iterator();

                    while (iterator1.hasNext())
                    {
                        Entry entry1 = (Entry)iterator1.next();

                        if (j++ > 0)
                        {
                            stringbuilder.append(" ");
                        }

                        stringbuilder.append((String)entry1.getKey());
                        stringbuilder.append("=");
                        stringbuilder.append((String)entry1.getValue());
                    }

                    stringbuilder.append(")");
                }
            }
        }
        else
        {
            stringbuilder.append(";");
        }

        return stringbuilder.toString();
    }

    private static FlatLayerInfo func_82646_a(String p_82646_0_, int p_82646_1_)
    {
        String[] astring = p_82646_0_.split("x", 2);
        int j = 1;
        int l = 0;

        if (astring.length == 2)
        {
            try
            {
                j = Integer.parseInt(astring[0]);

                if (p_82646_1_ + j >= 256)
                {
                    j = 256 - p_82646_1_;
                }

                if (j < 0)
                {
                    j = 0;
                }
            }
            catch (Throwable throwable)
            {
                return null;
            }
        }

        int k;

        try
        {
            String s1 = astring[astring.length - 1];
            astring = s1.split(":", 2);
            k = Integer.parseInt(astring[0]);

            if (astring.length > 1)
            {
                l = Integer.parseInt(astring[1]);
            }

            if (Block.func_149729_e(k) == Blocks.AIR)
            {
                k = 0;
                l = 0;
            }

            if (l < 0 || l > 15)
            {
                l = 0;
            }
        }
        catch (Throwable throwable1)
        {
            return null;
        }

        FlatLayerInfo flatlayerinfo = new FlatLayerInfo(j, Block.func_149729_e(k), l);
        flatlayerinfo.func_82660_d(p_82646_1_);
        return flatlayerinfo;
    }

    private static List func_82652_b(String p_82652_0_)
    {
        if (p_82652_0_ != null && p_82652_0_.length() >= 1)
        {
            ArrayList arraylist = new ArrayList();
            String[] astring = p_82652_0_.split(",");
            int i = 0;
            String[] astring1 = astring;
            int j = astring.length;

            for (int k = 0; k < j; ++k)
            {
                String s1 = astring1[k];
                FlatLayerInfo flatlayerinfo = func_82646_a(s1, i);

                if (flatlayerinfo == null)
                {
                    return null;
                }

                arraylist.add(flatlayerinfo);
                i += flatlayerinfo.func_82657_a();
            }

            return arraylist;
        }
        else
        {
            return null;
        }
    }

    public static FlatGeneratorInfo func_82651_a(String p_82651_0_)
    {
        if (p_82651_0_ == null)
        {
            return func_82649_e();
        }
        else
        {
            String[] astring = p_82651_0_.split(";", -1);
            int i = astring.length == 1 ? 0 : MathHelper.func_82715_a(astring[0], 0);

            if (i >= 0 && i <= 2)
            {
                FlatGeneratorInfo flatgeneratorinfo = new FlatGeneratorInfo();
                int j = astring.length == 1 ? 0 : 1;
                List list = func_82652_b(astring[j++]);

                if (list != null && !list.isEmpty())
                {
                    flatgeneratorinfo.func_82650_c().addAll(list);
                    flatgeneratorinfo.func_82645_d();
                    int k = BiomeGenBase.field_76772_c.field_76756_M;

                    if (i > 0 && astring.length > j)
                    {
                        k = MathHelper.func_82715_a(astring[j++], k);
                    }

                    flatgeneratorinfo.func_82647_a(k);

                    if (i > 0 && astring.length > j)
                    {
                        String[] astring1 = astring[j++].toLowerCase().split(",");
                        String[] astring2 = astring1;
                        int l = astring1.length;

                        for (int i1 = 0; i1 < l; ++i1)
                        {
                            String s1 = astring2[i1];
                            String[] astring3 = s1.split("\\(", 2);
                            HashMap hashmap = new HashMap();

                            if (astring3[0].length() > 0)
                            {
                                flatgeneratorinfo.func_82644_b().put(astring3[0], hashmap);

                                if (astring3.length > 1 && astring3[1].endsWith(")") && astring3[1].length() > 1)
                                {
                                    String[] astring4 = astring3[1].substring(0, astring3[1].length() - 1).split(" ");

                                    for (int j1 = 0; j1 < astring4.length; ++j1)
                                    {
                                        String[] astring5 = astring4[j1].split("=", 2);

                                        if (astring5.length == 2)
                                        {
                                            hashmap.put(astring5[0], astring5[1]);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    else
                    {
                        flatgeneratorinfo.func_82644_b().put("village", new HashMap());
                    }

                    return flatgeneratorinfo;
                }
                else
                {
                    return func_82649_e();
                }
            }
            else
            {
                return func_82649_e();
            }
        }
    }

    public static FlatGeneratorInfo func_82649_e()
    {
        FlatGeneratorInfo flatgeneratorinfo = new FlatGeneratorInfo();
        flatgeneratorinfo.func_82647_a(BiomeGenBase.field_76772_c.field_76756_M);
        flatgeneratorinfo.func_82650_c().add(new FlatLayerInfo(1, Blocks.BEDROCK));
        flatgeneratorinfo.func_82650_c().add(new FlatLayerInfo(2, Blocks.DIRT));
        flatgeneratorinfo.func_82650_c().add(new FlatLayerInfo(1, Blocks.GRASS));
        flatgeneratorinfo.func_82645_d();
        flatgeneratorinfo.func_82644_b().put("village", new HashMap());
        return flatgeneratorinfo;
    }
}