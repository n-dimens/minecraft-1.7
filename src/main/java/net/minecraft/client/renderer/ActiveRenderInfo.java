package net.minecraft.client.renderer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

@SideOnly(Side.CLIENT)
public class ActiveRenderInfo
{
    public static float field_74592_a;
    public static float field_74590_b;
    public static float field_74591_c;
    private static IntBuffer field_74597_i = GLAllocation.func_74527_f(16);
    private static FloatBuffer field_74594_j = GLAllocation.func_74529_h(16);
    private static FloatBuffer field_74595_k = GLAllocation.func_74529_h(16);
    private static FloatBuffer field_74593_l = GLAllocation.func_74529_h(3);
    public static float field_74588_d;
    public static float field_74589_e;
    public static float field_74586_f;
    public static float field_74587_g;
    public static float field_74596_h;
    private static final String __OBFID = "CL_00000626";

    public static void func_74583_a(EntityPlayer p_74583_0_, boolean p_74583_1_)
    {
        GL11.glGetFloat(GL11.GL_MODELVIEW_MATRIX, field_74594_j);
        GL11.glGetFloat(GL11.GL_PROJECTION_MATRIX, field_74595_k);
        GL11.glGetInteger(GL11.GL_VIEWPORT, field_74597_i);
        float f = (float)((field_74597_i.get(0) + field_74597_i.get(2)) / 2);
        float f1 = (float)((field_74597_i.get(1) + field_74597_i.get(3)) / 2);
        GLU.gluUnProject(f, f1, 0.0F, field_74594_j, field_74595_k, field_74597_i, field_74593_l);
        field_74592_a = field_74593_l.get(0);
        field_74590_b = field_74593_l.get(1);
        field_74591_c = field_74593_l.get(2);
        int i = p_74583_1_ ? 1 : 0;
        float f2 = p_74583_0_.field_70125_A;
        float f3 = p_74583_0_.field_70177_z;
        field_74588_d = MathHelper.func_76134_b(f3 * (float)Math.PI / 180.0F) * (float)(1 - i * 2);
        field_74586_f = MathHelper.func_76126_a(f3 * (float)Math.PI / 180.0F) * (float)(1 - i * 2);
        field_74587_g = -field_74586_f * MathHelper.func_76126_a(f2 * (float)Math.PI / 180.0F) * (float)(1 - i * 2);
        field_74596_h = field_74588_d * MathHelper.func_76126_a(f2 * (float)Math.PI / 180.0F) * (float)(1 - i * 2);
        field_74589_e = MathHelper.func_76134_b(f2 * (float)Math.PI / 180.0F);
    }

    public static Vec3 func_74585_b(EntityLivingBase p_74585_0_, double p_74585_1_)
    {
        double d1 = p_74585_0_.field_70169_q + (p_74585_0_.field_70165_t - p_74585_0_.field_70169_q) * p_74585_1_;
        double d2 = p_74585_0_.field_70167_r + (p_74585_0_.field_70163_u - p_74585_0_.field_70167_r) * p_74585_1_ + (double)p_74585_0_.func_70047_e();
        double d3 = p_74585_0_.field_70166_s + (p_74585_0_.field_70161_v - p_74585_0_.field_70166_s) * p_74585_1_;
        double d4 = d1 + (double)(field_74592_a * 1.0F);
        double d5 = d2 + (double)(field_74590_b * 1.0F);
        double d6 = d3 + (double)(field_74591_c * 1.0F);
        return Vec3.func_72443_a(d4, d5, d6);
    }

    public static Block func_151460_a(World p_151460_0_, EntityLivingBase p_151460_1_, float p_151460_2_)
    {
        Vec3 vec3 = func_74585_b(p_151460_1_, (double)p_151460_2_);
        ChunkPosition chunkposition = new ChunkPosition(vec3);
        Block block = p_151460_0_.func_147439_a(chunkposition.field_151329_a, chunkposition.field_151327_b, chunkposition.field_151328_c);

        if (block.func_149688_o().func_76224_d())
        {
            float f1 = BlockLiquid.func_149801_b(p_151460_0_.func_72805_g(chunkposition.field_151329_a, chunkposition.field_151327_b, chunkposition.field_151328_c)) - 0.11111111F;
            float f2 = (float)(chunkposition.field_151327_b + 1) - f1;

            if (vec3.field_72448_b >= (double)f2)
            {
                block = p_151460_0_.func_147439_a(chunkposition.field_151329_a, chunkposition.field_151327_b + 1, chunkposition.field_151328_c);
            }
        }

        return block;
    }
}