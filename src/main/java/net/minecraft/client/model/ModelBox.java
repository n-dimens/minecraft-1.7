package net.minecraft.client.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.Tessellator;

@SideOnly(Side.CLIENT)
public class ModelBox
{
    private PositionTextureVertex[] field_78253_h;
    private TexturedQuad[] field_78254_i;
    public final float field_78252_a;
    public final float field_78250_b;
    public final float field_78251_c;
    public final float field_78248_d;
    public final float field_78249_e;
    public final float field_78246_f;
    public String field_78247_g;
    private static final String __OBFID = "CL_00000872";

    public ModelBox(ModelRenderer p_i1171_1_, int p_i1171_2_, int p_i1171_3_, float p_i1171_4_, float p_i1171_5_, float p_i1171_6_, int p_i1171_7_, int p_i1171_8_, int p_i1171_9_, float p_i1171_10_)
    {
        this.field_78252_a = p_i1171_4_;
        this.field_78250_b = p_i1171_5_;
        this.field_78251_c = p_i1171_6_;
        this.field_78248_d = p_i1171_4_ + (float)p_i1171_7_;
        this.field_78249_e = p_i1171_5_ + (float)p_i1171_8_;
        this.field_78246_f = p_i1171_6_ + (float)p_i1171_9_;
        this.field_78253_h = new PositionTextureVertex[8];
        this.field_78254_i = new TexturedQuad[6];
        float f4 = p_i1171_4_ + (float)p_i1171_7_;
        float f5 = p_i1171_5_ + (float)p_i1171_8_;
        float f6 = p_i1171_6_ + (float)p_i1171_9_;
        p_i1171_4_ -= p_i1171_10_;
        p_i1171_5_ -= p_i1171_10_;
        p_i1171_6_ -= p_i1171_10_;
        f4 += p_i1171_10_;
        f5 += p_i1171_10_;
        f6 += p_i1171_10_;

        if (p_i1171_1_.field_78809_i)
        {
            float f7 = f4;
            f4 = p_i1171_4_;
            p_i1171_4_ = f7;
        }

        PositionTextureVertex positiontexturevertex7 = new PositionTextureVertex(p_i1171_4_, p_i1171_5_, p_i1171_6_, 0.0F, 0.0F);
        PositionTextureVertex positiontexturevertex = new PositionTextureVertex(f4, p_i1171_5_, p_i1171_6_, 0.0F, 8.0F);
        PositionTextureVertex positiontexturevertex1 = new PositionTextureVertex(f4, f5, p_i1171_6_, 8.0F, 8.0F);
        PositionTextureVertex positiontexturevertex2 = new PositionTextureVertex(p_i1171_4_, f5, p_i1171_6_, 8.0F, 0.0F);
        PositionTextureVertex positiontexturevertex3 = new PositionTextureVertex(p_i1171_4_, p_i1171_5_, f6, 0.0F, 0.0F);
        PositionTextureVertex positiontexturevertex4 = new PositionTextureVertex(f4, p_i1171_5_, f6, 0.0F, 8.0F);
        PositionTextureVertex positiontexturevertex5 = new PositionTextureVertex(f4, f5, f6, 8.0F, 8.0F);
        PositionTextureVertex positiontexturevertex6 = new PositionTextureVertex(p_i1171_4_, f5, f6, 8.0F, 0.0F);
        this.field_78253_h[0] = positiontexturevertex7;
        this.field_78253_h[1] = positiontexturevertex;
        this.field_78253_h[2] = positiontexturevertex1;
        this.field_78253_h[3] = positiontexturevertex2;
        this.field_78253_h[4] = positiontexturevertex3;
        this.field_78253_h[5] = positiontexturevertex4;
        this.field_78253_h[6] = positiontexturevertex5;
        this.field_78253_h[7] = positiontexturevertex6;
        this.field_78254_i[0] = new TexturedQuad(new PositionTextureVertex[] {positiontexturevertex4, positiontexturevertex, positiontexturevertex1, positiontexturevertex5}, p_i1171_2_ + p_i1171_9_ + p_i1171_7_, p_i1171_3_ + p_i1171_9_, p_i1171_2_ + p_i1171_9_ + p_i1171_7_ + p_i1171_9_, p_i1171_3_ + p_i1171_9_ + p_i1171_8_, p_i1171_1_.field_78801_a, p_i1171_1_.field_78799_b);
        this.field_78254_i[1] = new TexturedQuad(new PositionTextureVertex[] {positiontexturevertex7, positiontexturevertex3, positiontexturevertex6, positiontexturevertex2}, p_i1171_2_, p_i1171_3_ + p_i1171_9_, p_i1171_2_ + p_i1171_9_, p_i1171_3_ + p_i1171_9_ + p_i1171_8_, p_i1171_1_.field_78801_a, p_i1171_1_.field_78799_b);
        this.field_78254_i[2] = new TexturedQuad(new PositionTextureVertex[] {positiontexturevertex4, positiontexturevertex3, positiontexturevertex7, positiontexturevertex}, p_i1171_2_ + p_i1171_9_, p_i1171_3_, p_i1171_2_ + p_i1171_9_ + p_i1171_7_, p_i1171_3_ + p_i1171_9_, p_i1171_1_.field_78801_a, p_i1171_1_.field_78799_b);
        this.field_78254_i[3] = new TexturedQuad(new PositionTextureVertex[] {positiontexturevertex1, positiontexturevertex2, positiontexturevertex6, positiontexturevertex5}, p_i1171_2_ + p_i1171_9_ + p_i1171_7_, p_i1171_3_ + p_i1171_9_, p_i1171_2_ + p_i1171_9_ + p_i1171_7_ + p_i1171_7_, p_i1171_3_, p_i1171_1_.field_78801_a, p_i1171_1_.field_78799_b);
        this.field_78254_i[4] = new TexturedQuad(new PositionTextureVertex[] {positiontexturevertex, positiontexturevertex7, positiontexturevertex2, positiontexturevertex1}, p_i1171_2_ + p_i1171_9_, p_i1171_3_ + p_i1171_9_, p_i1171_2_ + p_i1171_9_ + p_i1171_7_, p_i1171_3_ + p_i1171_9_ + p_i1171_8_, p_i1171_1_.field_78801_a, p_i1171_1_.field_78799_b);
        this.field_78254_i[5] = new TexturedQuad(new PositionTextureVertex[] {positiontexturevertex3, positiontexturevertex4, positiontexturevertex5, positiontexturevertex6}, p_i1171_2_ + p_i1171_9_ + p_i1171_7_ + p_i1171_9_, p_i1171_3_ + p_i1171_9_, p_i1171_2_ + p_i1171_9_ + p_i1171_7_ + p_i1171_9_ + p_i1171_7_, p_i1171_3_ + p_i1171_9_ + p_i1171_8_, p_i1171_1_.field_78801_a, p_i1171_1_.field_78799_b);

        if (p_i1171_1_.field_78809_i)
        {
            for (int j1 = 0; j1 < this.field_78254_i.length; ++j1)
            {
                this.field_78254_i[j1].func_78235_a();
            }
        }
    }

    public void func_78245_a(Tessellator p_78245_1_, float p_78245_2_)
    {
        for (int i = 0; i < this.field_78254_i.length; ++i)
        {
            this.field_78254_i[i].func_78236_a(p_78245_1_, p_78245_2_);
        }
    }

    public ModelBox func_78244_a(String p_78244_1_)
    {
        this.field_78247_g = p_78244_1_;
        return this;
    }
}