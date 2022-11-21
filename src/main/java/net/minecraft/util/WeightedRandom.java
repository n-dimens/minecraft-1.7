package net.minecraft.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

public class WeightedRandom
{
    private static final String __OBFID = "CL_00001503";

    public static int func_76272_a(Collection p_76272_0_)
    {
        int i = 0;
        WeightedRandom.Item item;

        for (Iterator iterator = p_76272_0_.iterator(); iterator.hasNext(); i += item.field_76292_a)
        {
            item = (WeightedRandom.Item)iterator.next();
        }

        return i;
    }

    public static WeightedRandom.Item func_76273_a(Random p_76273_0_, Collection p_76273_1_, int p_76273_2_)
    {
        if (p_76273_2_ <= 0)
        {
            throw new IllegalArgumentException();
        }
        else
        {
            int j = p_76273_0_.nextInt(p_76273_2_);
            Iterator iterator = p_76273_1_.iterator();
            WeightedRandom.Item item;

            do
            {
                if (!iterator.hasNext())
                {
                    return null;
                }

                item = (WeightedRandom.Item)iterator.next();
                j -= item.field_76292_a;
            }
            while (j >= 0);

            return item;
        }
    }

    public static WeightedRandom.Item func_76271_a(Random p_76271_0_, Collection p_76271_1_)
    {
        return func_76273_a(p_76271_0_, p_76271_1_, func_76272_a(p_76271_1_));
    }

    public static int func_76270_a(WeightedRandom.Item[] p_76270_0_)
    {
        int i = 0;
        WeightedRandom.Item[] aitem = p_76270_0_;
        int j = p_76270_0_.length;

        for (int k = 0; k < j; ++k)
        {
            WeightedRandom.Item item = aitem[k];
            i += item.field_76292_a;
        }

        return i;
    }

    public static WeightedRandom.Item func_76269_a(Random p_76269_0_, WeightedRandom.Item[] p_76269_1_, int p_76269_2_)
    {
        if (p_76269_2_ <= 0)
        {
            throw new IllegalArgumentException();
        }
        else
        {
            int j = p_76269_0_.nextInt(p_76269_2_);
            WeightedRandom.Item[] aitem = p_76269_1_;
            int k = p_76269_1_.length;

            for (int l = 0; l < k; ++l)
            {
                WeightedRandom.Item item = aitem[l];
                j -= item.field_76292_a;

                if (j < 0)
                {
                    return item;
                }
            }

            return null;
        }
    }

    public static WeightedRandom.Item func_76274_a(Random p_76274_0_, WeightedRandom.Item[] p_76274_1_)
    {
        return func_76269_a(p_76274_0_, p_76274_1_, func_76270_a(p_76274_1_));
    }

    public static class Item
        {
            public int field_76292_a;
            private static final String __OBFID = "CL_00001504";

            public Item(int p_i1556_1_)
            {
                this.field_76292_a = p_i1556_1_;
            }
        }
}