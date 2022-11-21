package net.minecraft.world.gen.layer;

import java.util.ArrayList;
import java.util.List;

public class IntCache
{
    private static int field_76451_a = 256;
    private static List field_76449_b = new ArrayList();
    private static List field_76450_c = new ArrayList();
    private static List field_76447_d = new ArrayList();
    private static List field_76448_e = new ArrayList();
    private static final String __OBFID = "CL_00000557";

    public static synchronized int[] func_76445_a(int p_76445_0_)
    {
        int[] aint;

        if (p_76445_0_ <= 256)
        {
            if (field_76449_b.isEmpty())
            {
                aint = new int[256];
                field_76450_c.add(aint);
                return aint;
            }
            else
            {
                aint = (int[])field_76449_b.remove(field_76449_b.size() - 1);
                field_76450_c.add(aint);
                return aint;
            }
        }
        else if (p_76445_0_ > field_76451_a)
        {
            field_76451_a = p_76445_0_;
            field_76447_d.clear();
            field_76448_e.clear();
            aint = new int[field_76451_a];
            field_76448_e.add(aint);
            return aint;
        }
        else if (field_76447_d.isEmpty())
        {
            aint = new int[field_76451_a];
            field_76448_e.add(aint);
            return aint;
        }
        else
        {
            aint = (int[])field_76447_d.remove(field_76447_d.size() - 1);
            field_76448_e.add(aint);
            return aint;
        }
    }

    public static synchronized void func_76446_a()
    {
        if (!field_76447_d.isEmpty())
        {
            field_76447_d.remove(field_76447_d.size() - 1);
        }

        if (!field_76449_b.isEmpty())
        {
            field_76449_b.remove(field_76449_b.size() - 1);
        }

        field_76447_d.addAll(field_76448_e);
        field_76449_b.addAll(field_76450_c);
        field_76448_e.clear();
        field_76450_c.clear();
    }

    public static synchronized String func_85144_b()
    {
        return "cache: " + field_76447_d.size() + ", tcache: " + field_76449_b.size() + ", allocated: " + field_76448_e.size() + ", tallocated: " + field_76450_c.size();
    }
}