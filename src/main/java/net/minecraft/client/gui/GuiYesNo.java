package net.minecraft.client.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Iterator;
import net.minecraft.client.resources.I18n;

@SideOnly(Side.CLIENT)
public class GuiYesNo extends GuiScreen
{
    protected GuiYesNoCallback field_146355_a;
    protected String field_146351_f;
    private String field_146354_r;
    protected String field_146352_g;
    protected String field_146356_h;
    protected int field_146357_i;
    private int field_146353_s;
    private static final String __OBFID = "CL_00000684";

    public GuiYesNo(GuiYesNoCallback p_i1082_1_, String p_i1082_2_, String p_i1082_3_, int p_i1082_4_)
    {
        this.field_146355_a = p_i1082_1_;
        this.field_146351_f = p_i1082_2_;
        this.field_146354_r = p_i1082_3_;
        this.field_146357_i = p_i1082_4_;
        this.field_146352_g = I18n.func_135052_a("gui.yes", new Object[0]);
        this.field_146356_h = I18n.func_135052_a("gui.no", new Object[0]);
    }

    public GuiYesNo(GuiYesNoCallback p_i1083_1_, String p_i1083_2_, String p_i1083_3_, String p_i1083_4_, String p_i1083_5_, int p_i1083_6_)
    {
        this.field_146355_a = p_i1083_1_;
        this.field_146351_f = p_i1083_2_;
        this.field_146354_r = p_i1083_3_;
        this.field_146352_g = p_i1083_4_;
        this.field_146356_h = p_i1083_5_;
        this.field_146357_i = p_i1083_6_;
    }

    public void func_73866_w_()
    {
        this.field_146292_n.add(new GuiOptionButton(0, this.field_146294_l / 2 - 155, this.field_146295_m / 6 + 96, this.field_146352_g));
        this.field_146292_n.add(new GuiOptionButton(1, this.field_146294_l / 2 - 155 + 160, this.field_146295_m / 6 + 96, this.field_146356_h));
    }

    protected void func_146284_a(GuiButton p_146284_1_)
    {
        this.field_146355_a.func_73878_a(p_146284_1_.field_146127_k == 0, this.field_146357_i);
    }

    public void func_73863_a(int p_73863_1_, int p_73863_2_, float p_73863_3_)
    {
        this.func_146276_q_();
        this.func_73732_a(this.field_146289_q, this.field_146351_f, this.field_146294_l / 2, 70, 16777215);
        this.func_73732_a(this.field_146289_q, this.field_146354_r, this.field_146294_l / 2, 90, 16777215);
        super.func_73863_a(p_73863_1_, p_73863_2_, p_73863_3_);
    }

    public void func_146350_a(int p_146350_1_)
    {
        this.field_146353_s = p_146350_1_;
        GuiButton guibutton;

        for (Iterator iterator = this.field_146292_n.iterator(); iterator.hasNext(); guibutton.field_146124_l = false)
        {
            guibutton = (GuiButton)iterator.next();
        }
    }

    public void func_73876_c()
    {
        super.func_73876_c();
        GuiButton guibutton;

        if (--this.field_146353_s == 0)
        {
            for (Iterator iterator = this.field_146292_n.iterator(); iterator.hasNext(); guibutton.field_146124_l = true)
            {
                guibutton = (GuiButton)iterator.next();
            }
        }
    }
}