package net.minecraft.client.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundCategory;
import net.minecraft.client.audio.SoundEventAccessorComposite;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.stream.GuiStreamOptions;
import net.minecraft.client.gui.stream.GuiStreamUnavailable;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.stream.IStream;

@SideOnly(Side.CLIENT)
public class GuiOptions extends GuiScreen implements GuiYesNoCallback
{
    private static final GameSettings.Options[] field_146440_f = new GameSettings.Options[] {GameSettings.Options.FOV, GameSettings.Options.DIFFICULTY};
    private final GuiScreen field_146441_g;
    private final GameSettings field_146443_h;
    protected String field_146442_a = "Options";
    private static final String __OBFID = "CL_00000700";

    public GuiOptions(GuiScreen p_i1046_1_, GameSettings p_i1046_2_)
    {
        this.field_146441_g = p_i1046_1_;
        this.field_146443_h = p_i1046_2_;
    }

    public void func_73866_w_()
    {
        int i = 0;
        this.field_146442_a = I18n.func_135052_a("options.title", new Object[0]);
        GameSettings.Options[] aoptions = field_146440_f;
        int j = aoptions.length;

        for (int k = 0; k < j; ++k)
        {
            GameSettings.Options options = aoptions[k];

            if (options.func_74380_a())
            {
                this.field_146292_n.add(new GuiOptionSlider(options.func_74381_c(), this.field_146294_l / 2 - 155 + i % 2 * 160, this.field_146295_m / 6 - 12 + 24 * (i >> 1), options));
            }
            else
            {
                GuiOptionButton guioptionbutton = new GuiOptionButton(options.func_74381_c(), this.field_146294_l / 2 - 155 + i % 2 * 160, this.field_146295_m / 6 - 12 + 24 * (i >> 1), options, this.field_146443_h.func_74297_c(options));

                if (options == GameSettings.Options.DIFFICULTY && this.field_146297_k.field_71441_e != null && this.field_146297_k.field_71441_e.func_72912_H().func_76093_s())
                {
                    guioptionbutton.field_146124_l = false;
                    guioptionbutton.field_146126_j = I18n.func_135052_a("options.difficulty", new Object[0]) + ": " + I18n.func_135052_a("options.difficulty.hardcore", new Object[0]);
                }

                this.field_146292_n.add(guioptionbutton);
            }

            ++i;
        }

        this.field_146292_n.add(new GuiButton(8675309, this.field_146294_l / 2 + 5, this.field_146295_m / 6 + 48 - 6, 150, 20, "Super Secret Settings...")
        {
            private static final String __OBFID = "CL_00000701";
            public void func_146113_a(SoundHandler p_146113_1_)
            {
                SoundEventAccessorComposite soundeventaccessorcomposite = p_146113_1_.func_147686_a(new SoundCategory[] {SoundCategory.ANIMALS, SoundCategory.BLOCKS, SoundCategory.MOBS, SoundCategory.PLAYERS, SoundCategory.WEATHER});

                if (soundeventaccessorcomposite != null)
                {
                    p_146113_1_.func_147682_a(PositionedSoundRecord.func_147674_a(soundeventaccessorcomposite.func_148729_c(), 0.5F));
                }
            }
        });
        this.field_146292_n.add(new GuiButton(106, this.field_146294_l / 2 - 155, this.field_146295_m / 6 + 72 - 6, 150, 20, I18n.func_135052_a("options.sounds", new Object[0])));
        this.field_146292_n.add(new GuiButton(107, this.field_146294_l / 2 + 5, this.field_146295_m / 6 + 72 - 6, 150, 20, I18n.func_135052_a("options.stream", new Object[0])));
        this.field_146292_n.add(new GuiButton(101, this.field_146294_l / 2 - 155, this.field_146295_m / 6 + 96 - 6, 150, 20, I18n.func_135052_a("options.video", new Object[0])));
        this.field_146292_n.add(new GuiButton(100, this.field_146294_l / 2 + 5, this.field_146295_m / 6 + 96 - 6, 150, 20, I18n.func_135052_a("options.controls", new Object[0])));
        this.field_146292_n.add(new GuiButton(102, this.field_146294_l / 2 - 155, this.field_146295_m / 6 + 120 - 6, 150, 20, I18n.func_135052_a("options.language", new Object[0])));
        this.field_146292_n.add(new GuiButton(103, this.field_146294_l / 2 + 5, this.field_146295_m / 6 + 120 - 6, 150, 20, I18n.func_135052_a("options.multiplayer.title", new Object[0])));
        this.field_146292_n.add(new GuiButton(105, this.field_146294_l / 2 - 155, this.field_146295_m / 6 + 144 - 6, 150, 20, I18n.func_135052_a("options.resourcepack", new Object[0])));
        this.field_146292_n.add(new GuiButton(104, this.field_146294_l / 2 + 5, this.field_146295_m / 6 + 144 - 6, 150, 20, I18n.func_135052_a("options.snooper.view", new Object[0])));
        this.field_146292_n.add(new GuiButton(200, this.field_146294_l / 2 - 100, this.field_146295_m / 6 + 168, I18n.func_135052_a("gui.done", new Object[0])));
    }

    protected void func_146284_a(GuiButton p_146284_1_)
    {
        if (p_146284_1_.field_146124_l)
        {
            if (p_146284_1_.field_146127_k < 100 && p_146284_1_ instanceof GuiOptionButton)
            {
                this.field_146443_h.func_74306_a(((GuiOptionButton)p_146284_1_).func_146136_c(), 1);
                p_146284_1_.field_146126_j = this.field_146443_h.func_74297_c(GameSettings.Options.func_74379_a(p_146284_1_.field_146127_k));
            }

            if (p_146284_1_.field_146127_k == 8675309)
            {
                this.field_146297_k.field_71460_t.func_147705_c();
            }

            if (p_146284_1_.field_146127_k == 101)
            {
                this.field_146297_k.gameSettings.func_74303_b();
                this.field_146297_k.func_147108_a(new GuiVideoSettings(this, this.field_146443_h));
            }

            if (p_146284_1_.field_146127_k == 100)
            {
                this.field_146297_k.gameSettings.func_74303_b();
                this.field_146297_k.func_147108_a(new GuiControls(this, this.field_146443_h));
            }

            if (p_146284_1_.field_146127_k == 102)
            {
                this.field_146297_k.gameSettings.func_74303_b();
                this.field_146297_k.func_147108_a(new GuiLanguage(this, this.field_146443_h, this.field_146297_k.func_135016_M()));
            }

            if (p_146284_1_.field_146127_k == 103)
            {
                this.field_146297_k.gameSettings.func_74303_b();
                this.field_146297_k.func_147108_a(new ScreenChatOptions(this, this.field_146443_h));
            }

            if (p_146284_1_.field_146127_k == 104)
            {
                this.field_146297_k.gameSettings.func_74303_b();
                this.field_146297_k.func_147108_a(new GuiSnooper(this, this.field_146443_h));
            }

            if (p_146284_1_.field_146127_k == 200)
            {
                this.field_146297_k.gameSettings.func_74303_b();
                this.field_146297_k.func_147108_a(this.field_146441_g);
            }

            if (p_146284_1_.field_146127_k == 105)
            {
                this.field_146297_k.gameSettings.func_74303_b();
                this.field_146297_k.func_147108_a(new GuiScreenResourcePacks(this));
            }

            if (p_146284_1_.field_146127_k == 106)
            {
                this.field_146297_k.gameSettings.func_74303_b();
                this.field_146297_k.func_147108_a(new GuiScreenOptionsSounds(this, this.field_146443_h));
            }

            if (p_146284_1_.field_146127_k == 107)
            {
                this.field_146297_k.gameSettings.func_74303_b();
                IStream istream = this.field_146297_k.func_152346_Z();

                if (istream.func_152936_l() && istream.func_152928_D())
                {
                    this.field_146297_k.func_147108_a(new GuiStreamOptions(this, this.field_146443_h));
                }
                else
                {
                    GuiStreamUnavailable.func_152321_a(this);
                }
            }
        }
    }

    public void func_73863_a(int p_73863_1_, int p_73863_2_, float p_73863_3_)
    {
        this.func_146276_q_();
        this.func_73732_a(this.field_146289_q, this.field_146442_a, this.field_146294_l / 2, 15, 16777215);
        super.func_73863_a(p_73863_1_, p_73863_2_, p_73863_3_);
    }
}