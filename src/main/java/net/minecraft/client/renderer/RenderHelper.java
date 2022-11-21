package net.minecraft.client.renderer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.nio.FloatBuffer;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderHelper
{
    private static FloatBuffer field_74522_a = GLAllocation.func_74529_h(16);
    private static final Vec3 field_82884_b = Vec3.func_72443_a(0.20000000298023224D, 1.0D, -0.699999988079071D).func_72432_b();
    private static final Vec3 field_82885_c = Vec3.func_72443_a(-0.20000000298023224D, 1.0D, 0.699999988079071D).func_72432_b();
    private static final String __OBFID = "CL_00000629";

    public static void func_74518_a()
    {
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_LIGHT0);
        GL11.glDisable(GL11.GL_LIGHT1);
        GL11.glDisable(GL11.GL_COLOR_MATERIAL);
    }

    public static void func_74519_b()
    {
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_LIGHT0);
        GL11.glEnable(GL11.GL_LIGHT1);
        GL11.glEnable(GL11.GL_COLOR_MATERIAL);
        GL11.glColorMaterial(GL11.GL_FRONT_AND_BACK, GL11.GL_AMBIENT_AND_DIFFUSE);
        float f = 0.4F;
        float f1 = 0.6F;
        float f2 = 0.0F;
        GL11.glLight(GL11.GL_LIGHT0, GL11.GL_POSITION, func_74517_a(field_82884_b.field_72450_a, field_82884_b.field_72448_b, field_82884_b.field_72449_c, 0.0D));
        GL11.glLight(GL11.GL_LIGHT0, GL11.GL_DIFFUSE, func_74521_a(f1, f1, f1, 1.0F));
        GL11.glLight(GL11.GL_LIGHT0, GL11.GL_AMBIENT, func_74521_a(0.0F, 0.0F, 0.0F, 1.0F));
        GL11.glLight(GL11.GL_LIGHT0, GL11.GL_SPECULAR, func_74521_a(f2, f2, f2, 1.0F));
        GL11.glLight(GL11.GL_LIGHT1, GL11.GL_POSITION, func_74517_a(field_82885_c.field_72450_a, field_82885_c.field_72448_b, field_82885_c.field_72449_c, 0.0D));
        GL11.glLight(GL11.GL_LIGHT1, GL11.GL_DIFFUSE, func_74521_a(f1, f1, f1, 1.0F));
        GL11.glLight(GL11.GL_LIGHT1, GL11.GL_AMBIENT, func_74521_a(0.0F, 0.0F, 0.0F, 1.0F));
        GL11.glLight(GL11.GL_LIGHT1, GL11.GL_SPECULAR, func_74521_a(f2, f2, f2, 1.0F));
        GL11.glShadeModel(GL11.GL_FLAT);
        GL11.glLightModel(GL11.GL_LIGHT_MODEL_AMBIENT, func_74521_a(f, f, f, 1.0F));
    }

    private static FloatBuffer func_74517_a(double p_74517_0_, double p_74517_2_, double p_74517_4_, double p_74517_6_)
    {
        return func_74521_a((float)p_74517_0_, (float)p_74517_2_, (float)p_74517_4_, (float)p_74517_6_);
    }

    private static FloatBuffer func_74521_a(float p_74521_0_, float p_74521_1_, float p_74521_2_, float p_74521_3_)
    {
        field_74522_a.clear();
        field_74522_a.put(p_74521_0_).put(p_74521_1_).put(p_74521_2_).put(p_74521_3_);
        field_74522_a.flip();
        return field_74522_a;
    }

    public static void func_74520_c()
    {
        GL11.glPushMatrix();
        GL11.glRotatef(-30.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(165.0F, 1.0F, 0.0F, 0.0F);
        func_74519_b();
        GL11.glPopMatrix();
    }
}