package net.minecraft.client.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.network.play.client.C16PacketClientStatus;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.Charsets;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiWinGame extends GuiScreen
{
    private static final Logger field_146580_a = LogManager.getLogger();
    private static final ResourceLocation field_146576_f = new ResourceLocation("textures/gui/title/minecraft.png");
    private static final ResourceLocation field_146577_g = new ResourceLocation("textures/misc/vignette.png");
    private int field_146581_h;
    private List field_146582_i;
    private int field_146579_r;
    private float field_146578_s = 0.5F;
    private static final String __OBFID = "CL_00000719";

    public void func_73876_c()
    {
        ++this.field_146581_h;
        float f = (float)(this.field_146579_r + this.field_146295_m + this.field_146295_m + 24) / this.field_146578_s;

        if ((float)this.field_146581_h > f)
        {
            this.func_146574_g();
        }
    }

    protected void func_73869_a(char p_73869_1_, int p_73869_2_)
    {
        if (p_73869_2_ == 1)
        {
            this.func_146574_g();
        }
    }

    private void func_146574_g()
    {
        this.field_146297_k.field_71439_g.field_71174_a.func_147297_a(new C16PacketClientStatus(C16PacketClientStatus.EnumState.PERFORM_RESPAWN));
        this.field_146297_k.func_147108_a((GuiScreen)null);
    }

    public boolean func_73868_f()
    {
        return true;
    }

    public void func_73866_w_()
    {
        if (this.field_146582_i == null)
        {
            this.field_146582_i = new ArrayList();

            try
            {
                String s = "";
                String s1 = "" + EnumChatFormatting.WHITE + EnumChatFormatting.OBFUSCATED + EnumChatFormatting.GREEN + EnumChatFormatting.AQUA;
                short short1 = 274;
                BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(this.field_146297_k.func_110442_L().func_110536_a(new ResourceLocation("texts/end.txt")).func_110527_b(), Charsets.UTF_8));
                Random random = new Random(8124371L);
                int i;

                while ((s = bufferedreader.readLine()) != null)
                {
                    String s2;
                    String s3;

                    for (s = s.replaceAll("PLAYERNAME", this.field_146297_k.func_110432_I().func_111285_a()); s.contains(s1); s = s2 + EnumChatFormatting.WHITE + EnumChatFormatting.OBFUSCATED + "XXXXXXXX".substring(0, random.nextInt(4) + 3) + s3)
                    {
                        i = s.indexOf(s1);
                        s2 = s.substring(0, i);
                        s3 = s.substring(i + s1.length());
                    }

                    this.field_146582_i.addAll(this.field_146297_k.field_71466_p.func_78271_c(s, short1));
                    this.field_146582_i.add("");
                }

                for (i = 0; i < 8; ++i)
                {
                    this.field_146582_i.add("");
                }

                bufferedreader = new BufferedReader(new InputStreamReader(this.field_146297_k.func_110442_L().func_110536_a(new ResourceLocation("texts/credits.txt")).func_110527_b(), Charsets.UTF_8));

                while ((s = bufferedreader.readLine()) != null)
                {
                    s = s.replaceAll("PLAYERNAME", this.field_146297_k.func_110432_I().func_111285_a());
                    s = s.replaceAll("\t", "    ");
                    this.field_146582_i.addAll(this.field_146297_k.field_71466_p.func_78271_c(s, short1));
                    this.field_146582_i.add("");
                }

                this.field_146579_r = this.field_146582_i.size() * 12;
            }
            catch (Exception exception)
            {
                field_146580_a.error("Couldn\'t load credits", exception);
            }
        }
    }

    private void func_146575_b(int p_146575_1_, int p_146575_2_, float p_146575_3_)
    {
        Tessellator tessellator = Tessellator.field_78398_a;
        this.field_146297_k.func_110434_K().func_110577_a(Gui.field_110325_k);
        tessellator.func_78382_b();
        tessellator.func_78369_a(1.0F, 1.0F, 1.0F, 1.0F);
        int k = this.field_146294_l;
        float f1 = 0.0F - ((float)this.field_146581_h + p_146575_3_) * 0.5F * this.field_146578_s;
        float f2 = (float)this.field_146295_m - ((float)this.field_146581_h + p_146575_3_) * 0.5F * this.field_146578_s;
        float f3 = 0.015625F;
        float f4 = ((float)this.field_146581_h + p_146575_3_ - 0.0F) * 0.02F;
        float f5 = (float)(this.field_146579_r + this.field_146295_m + this.field_146295_m + 24) / this.field_146578_s;
        float f6 = (f5 - 20.0F - ((float)this.field_146581_h + p_146575_3_)) * 0.005F;

        if (f6 < f4)
        {
            f4 = f6;
        }

        if (f4 > 1.0F)
        {
            f4 = 1.0F;
        }

        f4 *= f4;
        f4 = f4 * 96.0F / 255.0F;
        tessellator.func_78386_a(f4, f4, f4);
        tessellator.func_78374_a(0.0D, (double)this.field_146295_m, (double)this.field_73735_i, 0.0D, (double)(f1 * f3));
        tessellator.func_78374_a((double)k, (double)this.field_146295_m, (double)this.field_73735_i, (double)((float)k * f3), (double)(f1 * f3));
        tessellator.func_78374_a((double)k, 0.0D, (double)this.field_73735_i, (double)((float)k * f3), (double)(f2 * f3));
        tessellator.func_78374_a(0.0D, 0.0D, (double)this.field_73735_i, 0.0D, (double)(f2 * f3));
        tessellator.func_78381_a();
    }

    public void func_73863_a(int p_73863_1_, int p_73863_2_, float p_73863_3_)
    {
        this.func_146575_b(p_73863_1_, p_73863_2_, p_73863_3_);
        Tessellator tessellator = Tessellator.field_78398_a;
        short short1 = 274;
        int k = this.field_146294_l / 2 - short1 / 2;
        int l = this.field_146295_m + 50;
        float f1 = -((float)this.field_146581_h + p_73863_3_) * this.field_146578_s;
        GL11.glPushMatrix();
        GL11.glTranslatef(0.0F, f1, 0.0F);
        this.field_146297_k.func_110434_K().func_110577_a(field_146576_f);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.func_73729_b(k, l, 0, 0, 155, 44);
        this.func_73729_b(k + 155, l, 0, 45, 155, 44);
        tessellator.func_78378_d(16777215);
        int i1 = l + 200;
        int j1;

        for (j1 = 0; j1 < this.field_146582_i.size(); ++j1)
        {
            if (j1 == this.field_146582_i.size() - 1)
            {
                float f2 = (float)i1 + f1 - (float)(this.field_146295_m / 2 - 6);

                if (f2 < 0.0F)
                {
                    GL11.glTranslatef(0.0F, -f2, 0.0F);
                }
            }

            if ((float)i1 + f1 + 12.0F + 8.0F > 0.0F && (float)i1 + f1 < (float)this.field_146295_m)
            {
                String s = (String)this.field_146582_i.get(j1);

                if (s.startsWith("[C]"))
                {
                    this.field_146289_q.func_78261_a(s.substring(3), k + (short1 - this.field_146289_q.func_78256_a(s.substring(3))) / 2, i1, 16777215);
                }
                else
                {
                    this.field_146289_q.field_78289_c.setSeed((long)j1 * 4238972211L + (long)(this.field_146581_h / 4));
                    this.field_146289_q.func_78261_a(s, k, i1, 16777215);
                }
            }

            i1 += 12;
        }

        GL11.glPopMatrix();
        this.field_146297_k.func_110434_K().func_110577_a(field_146577_g);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_ZERO, GL11.GL_ONE_MINUS_SRC_COLOR);
        tessellator.func_78382_b();
        tessellator.func_78369_a(1.0F, 1.0F, 1.0F, 1.0F);
        j1 = this.field_146294_l;
        int k1 = this.field_146295_m;
        tessellator.func_78374_a(0.0D, (double)k1, (double)this.field_73735_i, 0.0D, 1.0D);
        tessellator.func_78374_a((double)j1, (double)k1, (double)this.field_73735_i, 1.0D, 1.0D);
        tessellator.func_78374_a((double)j1, 0.0D, (double)this.field_73735_i, 1.0D, 0.0D);
        tessellator.func_78374_a(0.0D, 0.0D, (double)this.field_73735_i, 0.0D, 0.0D);
        tessellator.func_78381_a();
        GL11.glDisable(GL11.GL_BLEND);
        super.func_73863_a(p_73863_1_, p_73863_2_, p_73863_3_);
    }
}