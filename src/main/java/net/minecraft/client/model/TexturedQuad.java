package net.minecraft.client.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Vec3;

@SideOnly(Side.CLIENT)
public class TexturedQuad
{
    public PositionTextureVertex[] field_78239_a;
    public int field_78237_b;
    private boolean field_78238_c;
    private static final String __OBFID = "CL_00000850";

    public TexturedQuad(PositionTextureVertex[] p_i1152_1_)
    {
        this.field_78239_a = p_i1152_1_;
        this.field_78237_b = p_i1152_1_.length;
    }

    public TexturedQuad(PositionTextureVertex[] p_i1153_1_, int p_i1153_2_, int p_i1153_3_, int p_i1153_4_, int p_i1153_5_, float p_i1153_6_, float p_i1153_7_)
    {
        this(p_i1153_1_);
        float f2 = 0.0F / p_i1153_6_;
        float f3 = 0.0F / p_i1153_7_;
        p_i1153_1_[0] = p_i1153_1_[0].func_78240_a((float)p_i1153_4_ / p_i1153_6_ - f2, (float)p_i1153_3_ / p_i1153_7_ + f3);
        p_i1153_1_[1] = p_i1153_1_[1].func_78240_a((float)p_i1153_2_ / p_i1153_6_ + f2, (float)p_i1153_3_ / p_i1153_7_ + f3);
        p_i1153_1_[2] = p_i1153_1_[2].func_78240_a((float)p_i1153_2_ / p_i1153_6_ + f2, (float)p_i1153_5_ / p_i1153_7_ - f3);
        p_i1153_1_[3] = p_i1153_1_[3].func_78240_a((float)p_i1153_4_ / p_i1153_6_ - f2, (float)p_i1153_5_ / p_i1153_7_ - f3);
    }

    public void func_78235_a()
    {
        PositionTextureVertex[] apositiontexturevertex = new PositionTextureVertex[this.field_78239_a.length];

        for (int i = 0; i < this.field_78239_a.length; ++i)
        {
            apositiontexturevertex[i] = this.field_78239_a[this.field_78239_a.length - i - 1];
        }

        this.field_78239_a = apositiontexturevertex;
    }

    public void func_78236_a(Tessellator p_78236_1_, float p_78236_2_)
    {
        Vec3 vec3 = this.field_78239_a[1].field_78243_a.func_72444_a(this.field_78239_a[0].field_78243_a);
        Vec3 vec31 = this.field_78239_a[1].field_78243_a.func_72444_a(this.field_78239_a[2].field_78243_a);
        Vec3 vec32 = vec31.func_72431_c(vec3).func_72432_b();
        p_78236_1_.func_78382_b();

        if (this.field_78238_c)
        {
            p_78236_1_.func_78375_b(-((float)vec32.field_72450_a), -((float)vec32.field_72448_b), -((float)vec32.field_72449_c));
        }
        else
        {
            p_78236_1_.func_78375_b((float)vec32.field_72450_a, (float)vec32.field_72448_b, (float)vec32.field_72449_c);
        }

        for (int i = 0; i < 4; ++i)
        {
            PositionTextureVertex positiontexturevertex = this.field_78239_a[i];
            p_78236_1_.func_78374_a((double)((float)positiontexturevertex.field_78243_a.field_72450_a * p_78236_2_), (double)((float)positiontexturevertex.field_78243_a.field_72448_b * p_78236_2_), (double)((float)positiontexturevertex.field_78243_a.field_72449_c * p_78236_2_), (double)positiontexturevertex.field_78241_b, (double)positiontexturevertex.field_78242_c);
        }

        p_78236_1_.func_78381_a();
    }
}