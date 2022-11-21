package net.minecraft.client.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiStreamIndicator
{
    private static final ResourceLocation field_152441_a = new ResourceLocation("textures/gui/stream_indicator.png");
    private final Minecraft field_152442_b;
    private float field_152443_c = 1.0F;
    private int field_152444_d = 1;
    private static final String __OBFID = "CL_00001849";

    public GuiStreamIndicator(Minecraft p_i1092_1_)
    {
        this.field_152442_b = p_i1092_1_;
    }

    public void func_152437_a(int p_152437_1_, int p_152437_2_)
    {
        if (this.field_152442_b.func_152346_Z().func_152934_n())
        {
            GL11.glEnable(GL11.GL_BLEND);
            int k = this.field_152442_b.func_152346_Z().func_152920_A();

            if (k > 0)
            {
                String s = "" + k;
                int l = this.field_152442_b.field_71466_p.func_78256_a(s);
                boolean flag = true;
                int i1 = p_152437_1_ - l - 1;
                int j1 = p_152437_2_ + 20 - 1;
                int k1 = p_152437_2_ + 20 + this.field_152442_b.field_71466_p.field_78288_b - 1;
                GL11.glDisable(GL11.GL_TEXTURE_2D);
                Tessellator tessellator = Tessellator.field_78398_a;
                GL11.glColor4f(0.0F, 0.0F, 0.0F, (0.65F + 0.35000002F * this.field_152443_c) / 2.0F);
                tessellator.func_78382_b();
                tessellator.func_78377_a((double)i1, (double)k1, 0.0D);
                tessellator.func_78377_a((double)p_152437_1_, (double)k1, 0.0D);
                tessellator.func_78377_a((double)p_152437_1_, (double)j1, 0.0D);
                tessellator.func_78377_a((double)i1, (double)j1, 0.0D);
                tessellator.func_78381_a();
                GL11.glEnable(GL11.GL_TEXTURE_2D);
                this.field_152442_b.field_71466_p.func_78276_b(s, p_152437_1_ - l, p_152437_2_ + 20, 16777215);
            }

            this.func_152436_a(p_152437_1_, p_152437_2_, this.func_152440_b(), 0);
            this.func_152436_a(p_152437_1_, p_152437_2_, this.func_152438_c(), 17);
        }
    }

    private void func_152436_a(int p_152436_1_, int p_152436_2_, int p_152436_3_, int p_152436_4_)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.65F + 0.35000002F * this.field_152443_c);
        this.field_152442_b.func_110434_K().func_110577_a(field_152441_a);
        float f = 150.0F;
        float f1 = 0.0F;
        float f2 = (float)p_152436_3_ * 0.015625F;
        float f3 = 1.0F;
        float f4 = (float)(p_152436_3_ + 16) * 0.015625F;
        Tessellator tessellator = Tessellator.field_78398_a;
        tessellator.func_78382_b();
        tessellator.func_78374_a((double)(p_152436_1_ - 16 - p_152436_4_), (double)(p_152436_2_ + 16), (double)f, (double)f1, (double)f4);
        tessellator.func_78374_a((double)(p_152436_1_ - p_152436_4_), (double)(p_152436_2_ + 16), (double)f, (double)f3, (double)f4);
        tessellator.func_78374_a((double)(p_152436_1_ - p_152436_4_), (double)(p_152436_2_ + 0), (double)f, (double)f3, (double)f2);
        tessellator.func_78374_a((double)(p_152436_1_ - 16 - p_152436_4_), (double)(p_152436_2_ + 0), (double)f, (double)f1, (double)f2);
        tessellator.func_78381_a();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

    private int func_152440_b()
    {
        return this.field_152442_b.func_152346_Z().func_152919_o() ? 16 : 0;
    }

    private int func_152438_c()
    {
        return this.field_152442_b.func_152346_Z().func_152929_G() ? 48 : 32;
    }

    public void func_152439_a()
    {
        if (this.field_152442_b.func_152346_Z().func_152934_n())
        {
            this.field_152443_c += 0.025F * (float)this.field_152444_d;

            if (this.field_152443_c < 0.0F)
            {
                this.field_152444_d *= -1;
                this.field_152443_c = 0.0F;
            }
            else if (this.field_152443_c > 1.0F)
            {
                this.field_152444_d *= -1;
                this.field_152443_c = 1.0F;
            }
        }
        else
        {
            this.field_152443_c = 1.0F;
            this.field_152444_d = 1;
        }
    }
}