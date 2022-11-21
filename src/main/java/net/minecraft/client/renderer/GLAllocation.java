package net.minecraft.client.renderer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GLAllocation
{
    private static final Map field_74531_a = new HashMap();
    private static final List field_74530_b = new ArrayList();
    private static final String __OBFID = "CL_00000630";

    public static synchronized int func_74526_a(int p_74526_0_)
    {
        int j = GL11.glGenLists(p_74526_0_);
        field_74531_a.put(Integer.valueOf(j), Integer.valueOf(p_74526_0_));
        return j;
    }

    public static synchronized void func_74523_b(int p_74523_0_)
    {
        GL11.glDeleteLists(p_74523_0_, ((Integer)field_74531_a.remove(Integer.valueOf(p_74523_0_))).intValue());
    }

    public static synchronized void func_74525_a()
    {
        Iterator iterator = field_74531_a.entrySet().iterator();

        while (iterator.hasNext())
        {
            Entry entry = (Entry)iterator.next();
            GL11.glDeleteLists(((Integer)entry.getKey()).intValue(), ((Integer)entry.getValue()).intValue());
        }

        field_74531_a.clear();
    }

    public static synchronized ByteBuffer func_74524_c(int p_74524_0_)
    {
        return ByteBuffer.allocateDirect(p_74524_0_).order(ByteOrder.nativeOrder());
    }

    public static IntBuffer func_74527_f(int p_74527_0_)
    {
        return func_74524_c(p_74527_0_ << 2).asIntBuffer();
    }

    public static FloatBuffer func_74529_h(int p_74529_0_)
    {
        return func_74524_c(p_74529_0_ << 2).asFloatBuffer();
    }
}