package net.minecraft.client.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;

@SideOnly(Side.CLIENT)
public class ScreenChatOptions extends GuiScreen
{
    private static final GameSettings.Options[] field_146399_a = new GameSettings.Options[] {GameSettings.Options.CHAT_VISIBILITY, GameSettings.Options.CHAT_COLOR, GameSettings.Options.CHAT_LINKS, GameSettings.Options.CHAT_OPACITY, GameSettings.Options.CHAT_LINKS_PROMPT, GameSettings.Options.CHAT_SCALE, GameSettings.Options.CHAT_HEIGHT_FOCUSED, GameSettings.Options.CHAT_HEIGHT_UNFOCUSED, GameSettings.Options.CHAT_WIDTH};
    private static final GameSettings.Options[] field_146395_f = new GameSettings.Options[] {GameSettings.Options.SHOW_CAPE};
    private final GuiScreen field_146396_g;
    private final GameSettings field_146400_h;
    private String field_146401_i;
    private String field_146398_r;
    private int field_146397_s;
    private static final String __OBFID = "CL_00000681";

    public ScreenChatOptions(GuiScreen p_i1023_1_, GameSettings p_i1023_2_)
    {
        this.field_146396_g = p_i1023_1_;
        this.field_146400_h = p_i1023_2_;
    }

    public void func_73866_w_()
    {
        int i = 0;
        this.field_146401_i = I18n.func_135052_a("options.chat.title", new Object[0]);
        this.field_146398_r = I18n.func_135052_a("options.multiplayer.title", new Object[0]);
        GameSettings.Options[] aoptions = field_146399_a;
        int j = aoptions.length;
        int k;
        GameSettings.Options options;

        for (k = 0; k < j; ++k)
        {
            options = aoptions[k];

            if (options.func_74380_a())
            {
                this.field_146292_n.add(new GuiOptionSlider(options.func_74381_c(), this.field_146294_l / 2 - 155 + i % 2 * 160, this.field_146295_m / 6 + 24 * (i >> 1), options));
            }
            else
            {
                this.field_146292_n.add(new GuiOptionButton(options.func_74381_c(), this.field_146294_l / 2 - 155 + i % 2 * 160, this.field_146295_m / 6 + 24 * (i >> 1), options, this.field_146400_h.func_74297_c(options)));
            }

            ++i;
        }

        if (i % 2 == 1)
        {
            ++i;
        }

        this.field_146397_s = this.field_146295_m / 6 + 24 * (i >> 1);
        i += 2;
        aoptions = field_146395_f;
        j = aoptions.length;

        for (k = 0; k < j; ++k)
        {
            options = aoptions[k];

            if (options.func_74380_a())
            {
                this.field_146292_n.add(new GuiOptionSlider(options.func_74381_c(), this.field_146294_l / 2 - 155 + i % 2 * 160, this.field_146295_m / 6 + 24 * (i >> 1), options));
            }
            else
            {
                this.field_146292_n.add(new GuiOptionButton(options.func_74381_c(), this.field_146294_l / 2 - 155 + i % 2 * 160, this.field_146295_m / 6 + 24 * (i >> 1), options, this.field_146400_h.func_74297_c(options)));
            }

            ++i;
        }

        this.field_146292_n.add(new GuiButton(200, this.field_146294_l / 2 - 100, this.field_146295_m / 6 + 168, I18n.func_135052_a("gui.done", new Object[0])));
    }

    protected void func_146284_a(GuiButton p_146284_1_)
    {
        if (p_146284_1_.field_146124_l)
        {
            if (p_146284_1_.field_146127_k < 100 && p_146284_1_ instanceof GuiOptionButton)
            {
                this.field_146400_h.func_74306_a(((GuiOptionButton)p_146284_1_).func_146136_c(), 1);
                p_146284_1_.field_146126_j = this.field_146400_h.func_74297_c(GameSettings.Options.func_74379_a(p_146284_1_.field_146127_k));
            }

            if (p_146284_1_.field_146127_k == 200)
            {
                this.field_146297_k.field_71474_y.func_74303_b();
                this.field_146297_k.func_147108_a(this.field_146396_g);
            }
        }
    }

    public void func_73863_a(int p_73863_1_, int p_73863_2_, float p_73863_3_)
    {
        this.func_146276_q_();
        this.func_73732_a(this.field_146289_q, this.field_146401_i, this.field_146294_l / 2, 20, 16777215);
        this.func_73732_a(this.field_146289_q, this.field_146398_r, this.field_146294_l / 2, this.field_146397_s + 7, 16777215);
        super.func_73863_a(p_73863_1_, p_73863_2_, p_73863_3_);
    }
}