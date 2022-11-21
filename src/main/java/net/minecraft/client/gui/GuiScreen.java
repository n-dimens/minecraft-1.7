package net.minecraft.client.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Toolkit;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

@SideOnly(Side.CLIENT)
public class GuiScreen extends Gui
{
    protected static RenderItem field_146296_j = new RenderItem();
    public Minecraft field_146297_k;
    public int field_146294_l;
    public int field_146295_m;
    protected List field_146292_n = new ArrayList();
    protected List field_146293_o = new ArrayList();
    public boolean field_146291_p;
    protected FontRenderer field_146289_q;
    private GuiButton field_146290_a;
    private int field_146287_f;
    private long field_146288_g;
    private int field_146298_h;
    private static final String __OBFID = "CL_00000710";

    public void func_73863_a(int p_73863_1_, int p_73863_2_, float p_73863_3_)
    {
        int k;

        for (k = 0; k < this.field_146292_n.size(); ++k)
        {
            ((GuiButton)this.field_146292_n.get(k)).func_146112_a(this.field_146297_k, p_73863_1_, p_73863_2_);
        }

        for (k = 0; k < this.field_146293_o.size(); ++k)
        {
            ((GuiLabel)this.field_146293_o.get(k)).func_146159_a(this.field_146297_k, p_73863_1_, p_73863_2_);
        }
    }

    protected void func_73869_a(char p_73869_1_, int p_73869_2_)
    {
        if (p_73869_2_ == 1)
        {
            this.field_146297_k.func_147108_a((GuiScreen)null);
            this.field_146297_k.func_71381_h();
        }
    }

    public static String func_146277_j()
    {
        try
        {
            Transferable transferable = Toolkit.getDefaultToolkit().getSystemClipboard().getContents((Object)null);

            if (transferable != null && transferable.isDataFlavorSupported(DataFlavor.stringFlavor))
            {
                return (String)transferable.getTransferData(DataFlavor.stringFlavor);
            }
        }
        catch (Exception exception)
        {
            ;
        }

        return "";
    }

    public static void func_146275_d(String p_146275_0_)
    {
        try
        {
            StringSelection stringselection = new StringSelection(p_146275_0_);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringselection, (ClipboardOwner)null);
        }
        catch (Exception exception)
        {
            ;
        }
    }

    protected void func_146285_a(ItemStack p_146285_1_, int p_146285_2_, int p_146285_3_)
    {
        List list = p_146285_1_.func_82840_a(this.field_146297_k.field_71439_g, this.field_146297_k.field_71474_y.field_82882_x);

        for (int k = 0; k < list.size(); ++k)
        {
            if (k == 0)
            {
                list.set(k, p_146285_1_.func_77953_t().field_77937_e + (String)list.get(k));
            }
            else
            {
                list.set(k, EnumChatFormatting.GRAY + (String)list.get(k));
            }
        }

        this.func_146283_a(list, p_146285_2_, p_146285_3_);
    }

    protected void func_146279_a(String p_146279_1_, int p_146279_2_, int p_146279_3_)
    {
        this.func_146283_a(Arrays.asList(new String[] {p_146279_1_}), p_146279_2_, p_146279_3_);
    }

    protected void func_146283_a(List p_146283_1_, int p_146283_2_, int p_146283_3_)
    {
        if (!p_146283_1_.isEmpty())
        {
            GL11.glDisable(GL12.GL_RESCALE_NORMAL);
            RenderHelper.func_74518_a();
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glDisable(GL11.GL_DEPTH_TEST);
            int k = 0;
            Iterator iterator = p_146283_1_.iterator();

            while (iterator.hasNext())
            {
                String s = (String)iterator.next();
                int l = this.field_146289_q.func_78256_a(s);

                if (l > k)
                {
                    k = l;
                }
            }

            int j2 = p_146283_2_ + 12;
            int k2 = p_146283_3_ - 12;
            int i1 = 8;

            if (p_146283_1_.size() > 1)
            {
                i1 += 2 + (p_146283_1_.size() - 1) * 10;
            }

            if (j2 + k > this.field_146294_l)
            {
                j2 -= 28 + k;
            }

            if (k2 + i1 + 6 > this.field_146295_m)
            {
                k2 = this.field_146295_m - i1 - 6;
            }

            this.field_73735_i = 300.0F;
            field_146296_j.field_77023_b = 300.0F;
            int j1 = -267386864;
            this.func_73733_a(j2 - 3, k2 - 4, j2 + k + 3, k2 - 3, j1, j1);
            this.func_73733_a(j2 - 3, k2 + i1 + 3, j2 + k + 3, k2 + i1 + 4, j1, j1);
            this.func_73733_a(j2 - 3, k2 - 3, j2 + k + 3, k2 + i1 + 3, j1, j1);
            this.func_73733_a(j2 - 4, k2 - 3, j2 - 3, k2 + i1 + 3, j1, j1);
            this.func_73733_a(j2 + k + 3, k2 - 3, j2 + k + 4, k2 + i1 + 3, j1, j1);
            int k1 = 1347420415;
            int l1 = (k1 & 16711422) >> 1 | k1 & -16777216;
            this.func_73733_a(j2 - 3, k2 - 3 + 1, j2 - 3 + 1, k2 + i1 + 3 - 1, k1, l1);
            this.func_73733_a(j2 + k + 2, k2 - 3 + 1, j2 + k + 3, k2 + i1 + 3 - 1, k1, l1);
            this.func_73733_a(j2 - 3, k2 - 3, j2 + k + 3, k2 - 3 + 1, k1, k1);
            this.func_73733_a(j2 - 3, k2 + i1 + 2, j2 + k + 3, k2 + i1 + 3, l1, l1);

            for (int i2 = 0; i2 < p_146283_1_.size(); ++i2)
            {
                String s1 = (String)p_146283_1_.get(i2);
                this.field_146289_q.func_78261_a(s1, j2, k2, -1);

                if (i2 == 0)
                {
                    k2 += 2;
                }

                k2 += 10;
            }

            this.field_73735_i = 0.0F;
            field_146296_j.field_77023_b = 0.0F;
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glEnable(GL11.GL_DEPTH_TEST);
            RenderHelper.func_74519_b();
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        }
    }

    protected void func_73864_a(int p_73864_1_, int p_73864_2_, int p_73864_3_)
    {
        if (p_73864_3_ == 0)
        {
            for (int l = 0; l < this.field_146292_n.size(); ++l)
            {
                GuiButton guibutton = (GuiButton)this.field_146292_n.get(l);

                if (guibutton.func_146116_c(this.field_146297_k, p_73864_1_, p_73864_2_))
                {
                    this.field_146290_a = guibutton;
                    guibutton.func_146113_a(this.field_146297_k.func_147118_V());
                    this.func_146284_a(guibutton);
                }
            }
        }
    }

    protected void func_146286_b(int p_146286_1_, int p_146286_2_, int p_146286_3_)
    {
        if (this.field_146290_a != null && p_146286_3_ == 0)
        {
            this.field_146290_a.func_146118_a(p_146286_1_, p_146286_2_);
            this.field_146290_a = null;
        }
    }

    protected void func_146273_a(int p_146273_1_, int p_146273_2_, int p_146273_3_, long p_146273_4_) {}

    protected void func_146284_a(GuiButton p_146284_1_) {}

    public void func_146280_a(Minecraft p_146280_1_, int p_146280_2_, int p_146280_3_)
    {
        this.field_146297_k = p_146280_1_;
        this.field_146289_q = p_146280_1_.field_71466_p;
        this.field_146294_l = p_146280_2_;
        this.field_146295_m = p_146280_3_;
        this.field_146292_n.clear();
        this.func_73866_w_();
    }

    public void func_73866_w_() {}

    public void func_146269_k()
    {
        if (Mouse.isCreated())
        {
            while (Mouse.next())
            {
                this.func_146274_d();
            }
        }

        if (Keyboard.isCreated())
        {
            while (Keyboard.next())
            {
                this.func_146282_l();
            }
        }
    }

    public void func_146274_d()
    {
        int i = Mouse.getEventX() * this.field_146294_l / this.field_146297_k.field_71443_c;
        int j = this.field_146295_m - Mouse.getEventY() * this.field_146295_m / this.field_146297_k.field_71440_d - 1;
        int k = Mouse.getEventButton();

        if (Mouse.getEventButtonState())
        {
            if (this.field_146297_k.field_71474_y.field_85185_A && this.field_146298_h++ > 0)
            {
                return;
            }

            this.field_146287_f = k;
            this.field_146288_g = Minecraft.func_71386_F();
            this.func_73864_a(i, j, this.field_146287_f);
        }
        else if (k != -1)
        {
            if (this.field_146297_k.field_71474_y.field_85185_A && --this.field_146298_h > 0)
            {
                return;
            }

            this.field_146287_f = -1;
            this.func_146286_b(i, j, k);
        }
        else if (this.field_146287_f != -1 && this.field_146288_g > 0L)
        {
            long l = Minecraft.func_71386_F() - this.field_146288_g;
            this.func_146273_a(i, j, this.field_146287_f, l);
        }
    }

    public void func_146282_l()
    {
        if (Keyboard.getEventKeyState())
        {
            this.func_73869_a(Keyboard.getEventCharacter(), Keyboard.getEventKey());
        }

        this.field_146297_k.func_152348_aa();
    }

    public void func_73876_c() {}

    public void func_146281_b() {}

    public void func_146276_q_()
    {
        this.func_146270_b(0);
    }

    public void func_146270_b(int p_146270_1_)
    {
        if (this.field_146297_k.field_71441_e != null)
        {
            this.func_73733_a(0, 0, this.field_146294_l, this.field_146295_m, -1072689136, -804253680);
        }
        else
        {
            this.func_146278_c(p_146270_1_);
        }
    }

    public void func_146278_c(int p_146278_1_)
    {
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_FOG);
        Tessellator tessellator = Tessellator.field_78398_a;
        this.field_146297_k.func_110434_K().func_110577_a(field_110325_k);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        float f = 32.0F;
        tessellator.func_78382_b();
        tessellator.func_78378_d(4210752);
        tessellator.func_78374_a(0.0D, (double)this.field_146295_m, 0.0D, 0.0D, (double)((float)this.field_146295_m / f + (float)p_146278_1_));
        tessellator.func_78374_a((double)this.field_146294_l, (double)this.field_146295_m, 0.0D, (double)((float)this.field_146294_l / f), (double)((float)this.field_146295_m / f + (float)p_146278_1_));
        tessellator.func_78374_a((double)this.field_146294_l, 0.0D, 0.0D, (double)((float)this.field_146294_l / f), (double)p_146278_1_);
        tessellator.func_78374_a(0.0D, 0.0D, 0.0D, 0.0D, (double)p_146278_1_);
        tessellator.func_78381_a();
    }

    public boolean func_73868_f()
    {
        return true;
    }

    public void func_73878_a(boolean p_73878_1_, int p_73878_2_) {}

    public static boolean func_146271_m()
    {
        return Minecraft.field_142025_a ? Keyboard.isKeyDown(219) || Keyboard.isKeyDown(220) : Keyboard.isKeyDown(29) || Keyboard.isKeyDown(157);
    }

    public static boolean func_146272_n()
    {
        return Keyboard.isKeyDown(42) || Keyboard.isKeyDown(54);
    }
}