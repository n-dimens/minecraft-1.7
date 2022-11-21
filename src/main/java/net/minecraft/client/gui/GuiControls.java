package net.minecraft.client.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;

@SideOnly(Side.CLIENT)
public class GuiControls extends GuiScreen
{
    private static final GameSettings.Options[] field_146492_g = new GameSettings.Options[] {GameSettings.Options.INVERT_MOUSE, GameSettings.Options.SENSITIVITY, GameSettings.Options.TOUCHSCREEN};
    private GuiScreen field_146496_h;
    protected String field_146495_a = "Controls";
    private GameSettings field_146497_i;
    public KeyBinding field_146491_f = null;
    public long field_152177_g;
    private GuiKeyBindingList field_146494_r;
    private GuiButton field_146493_s;
    private static final String __OBFID = "CL_00000736";

    public GuiControls(GuiScreen p_i1027_1_, GameSettings p_i1027_2_)
    {
        this.field_146496_h = p_i1027_1_;
        this.field_146497_i = p_i1027_2_;
    }

    public void func_73866_w_()
    {
        this.field_146494_r = new GuiKeyBindingList(this, this.field_146297_k);
        this.field_146292_n.add(new GuiButton(200, this.field_146294_l / 2 - 155, this.field_146295_m - 29, 150, 20, I18n.func_135052_a("gui.done", new Object[0])));
        this.field_146292_n.add(this.field_146493_s = new GuiButton(201, this.field_146294_l / 2 - 155 + 160, this.field_146295_m - 29, 150, 20, I18n.func_135052_a("controls.resetAll", new Object[0])));
        this.field_146495_a = I18n.func_135052_a("controls.title", new Object[0]);
        int i = 0;
        GameSettings.Options[] aoptions = field_146492_g;
        int j = aoptions.length;

        for (int k = 0; k < j; ++k)
        {
            GameSettings.Options options = aoptions[k];

            if (options.func_74380_a())
            {
                this.field_146292_n.add(new GuiOptionSlider(options.func_74381_c(), this.field_146294_l / 2 - 155 + i % 2 * 160, 18 + 24 * (i >> 1), options));
            }
            else
            {
                this.field_146292_n.add(new GuiOptionButton(options.func_74381_c(), this.field_146294_l / 2 - 155 + i % 2 * 160, 18 + 24 * (i >> 1), options, this.field_146497_i.func_74297_c(options)));
            }

            ++i;
        }
    }

    protected void func_146284_a(GuiButton p_146284_1_)
    {
        if (p_146284_1_.field_146127_k == 200)
        {
            this.field_146297_k.func_147108_a(this.field_146496_h);
        }
        else if (p_146284_1_.field_146127_k == 201)
        {
            KeyBinding[] akeybinding = this.field_146297_k.gameSettings.field_74324_K;
            int i = akeybinding.length;

            for (int j = 0; j < i; ++j)
            {
                KeyBinding keybinding = akeybinding[j];
                keybinding.setEventKey(keybinding.getDefaultEventKey());
            }

            KeyBinding.func_74508_b();
        }
        else if (p_146284_1_.field_146127_k < 100 && p_146284_1_ instanceof GuiOptionButton)
        {
            this.field_146497_i.func_74306_a(((GuiOptionButton)p_146284_1_).func_146136_c(), 1);
            p_146284_1_.field_146126_j = this.field_146497_i.func_74297_c(GameSettings.Options.func_74379_a(p_146284_1_.field_146127_k));
        }
    }

    protected void func_73864_a(int p_73864_1_, int p_73864_2_, int p_73864_3_)
    {
        if (this.field_146491_f != null)
        {
            this.field_146497_i.func_151440_a(this.field_146491_f, -100 + p_73864_3_);
            this.field_146491_f = null;
            KeyBinding.func_74508_b();
        }
        else if (p_73864_3_ != 0 || !this.field_146494_r.func_148179_a(p_73864_1_, p_73864_2_, p_73864_3_))
        {
            super.func_73864_a(p_73864_1_, p_73864_2_, p_73864_3_);
        }
    }

    protected void func_146286_b(int p_146286_1_, int p_146286_2_, int p_146286_3_)
    {
        if (p_146286_3_ != 0 || !this.field_146494_r.func_148181_b(p_146286_1_, p_146286_2_, p_146286_3_))
        {
            super.func_146286_b(p_146286_1_, p_146286_2_, p_146286_3_);
        }
    }

    protected void func_73869_a(char p_73869_1_, int p_73869_2_)
    {
        if (this.field_146491_f != null)
        {
            if (p_73869_2_ == 1)
            {
                this.field_146497_i.func_151440_a(this.field_146491_f, 0);
            }
            else
            {
                this.field_146497_i.func_151440_a(this.field_146491_f, p_73869_2_);
            }

            this.field_146491_f = null;
            this.field_152177_g = Minecraft.func_71386_F();
            KeyBinding.func_74508_b();
        }
        else
        {
            super.func_73869_a(p_73869_1_, p_73869_2_);
        }
    }

    public void func_73863_a(int p_73863_1_, int p_73863_2_, float p_73863_3_)
    {
        this.func_146276_q_();
        this.field_146494_r.func_148128_a(p_73863_1_, p_73863_2_, p_73863_3_);
        this.func_73732_a(this.field_146289_q, this.field_146495_a, this.field_146294_l / 2, 8, 16777215);
        boolean flag = true;
        KeyBinding[] akeybinding = this.field_146497_i.field_74324_K;
        int k = akeybinding.length;

        for (int l = 0; l < k; ++l)
        {
            KeyBinding keybinding = akeybinding[l];

            if (keybinding.getEventKey() != keybinding.getDefaultEventKey())
            {
                flag = false;
                break;
            }
        }

        this.field_146493_s.field_146124_l = !flag;
        super.func_73863_a(p_73863_1_, p_73863_2_, p_73863_3_);
    }
}