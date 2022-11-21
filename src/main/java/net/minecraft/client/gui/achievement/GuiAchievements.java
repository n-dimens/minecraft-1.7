package net.minecraft.client.gui.achievement;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiOptionButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.IProgressMeter;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.network.play.client.C16PacketClientStatus;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraft.stats.StatFileWriter;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

@SideOnly(Side.CLIENT)
public class GuiAchievements extends GuiScreen implements IProgressMeter
{
    private static final int field_146572_y = AchievementList.field_76010_a * 24 - 112;
    private static final int field_146571_z = AchievementList.field_76008_b * 24 - 112;
    private static final int field_146559_A = AchievementList.field_76009_c * 24 - 77;
    private static final int field_146560_B = AchievementList.field_76006_d * 24 - 77;
    private static final ResourceLocation field_146561_C = new ResourceLocation("textures/gui/achievement/achievement_background.png");
    protected GuiScreen field_146562_a;
    protected int field_146555_f = 256;
    protected int field_146557_g = 202;
    protected int field_146563_h;
    protected int field_146564_i;
    protected float field_146570_r = 1.0F;
    protected double field_146569_s;
    protected double field_146568_t;
    protected double field_146567_u;
    protected double field_146566_v;
    protected double field_146565_w;
    protected double field_146573_x;
    private int field_146554_D;
    private StatFileWriter field_146556_E;
    private boolean field_146558_F = true;
    private static final String __OBFID = "CL_00000722";

    public GuiAchievements(GuiScreen p_i45026_1_, StatFileWriter p_i45026_2_)
    {
        this.field_146562_a = p_i45026_1_;
        this.field_146556_E = p_i45026_2_;
        short short1 = 141;
        short short2 = 141;
        this.field_146569_s = this.field_146567_u = this.field_146565_w = (double)(AchievementList.OPEN_INVENTORY.field_75993_a * 24 - short1 / 2 - 12);
        this.field_146568_t = this.field_146566_v = this.field_146573_x = (double)(AchievementList.OPEN_INVENTORY.field_75991_b * 24 - short2 / 2);
    }

    public void func_73866_w_()
    {
        this.field_146297_k.func_147114_u().func_147297_a(new C16PacketClientStatus(C16PacketClientStatus.EnumState.REQUEST_STATS));
        this.field_146292_n.clear();
        this.field_146292_n.add(new GuiOptionButton(1, this.field_146294_l / 2 + 24, this.field_146295_m / 2 + 74, 80, 20, I18n.func_135052_a("gui.done", new Object[0])));
    }

    protected void func_146284_a(GuiButton p_146284_1_)
    {
        if (!this.field_146558_F)
        {
            if (p_146284_1_.field_146127_k == 1)
            {
                this.field_146297_k.func_147108_a(this.field_146562_a);
            }
        }
    }

    protected void func_73869_a(char p_73869_1_, int p_73869_2_)
    {
        if (p_73869_2_ == this.field_146297_k.gameSettings.field_151445_Q.getEventKey())
        {
            this.field_146297_k.func_147108_a((GuiScreen)null);
            this.field_146297_k.func_71381_h();
        }
        else
        {
            super.func_73869_a(p_73869_1_, p_73869_2_);
        }
    }

    public void func_73863_a(int p_73863_1_, int p_73863_2_, float p_73863_3_)
    {
        if (this.field_146558_F)
        {
            this.func_146276_q_();
            this.func_73732_a(this.field_146289_q, I18n.func_135052_a("multiplayer.downloadingStats", new Object[0]), this.field_146294_l / 2, this.field_146295_m / 2, 16777215);
            this.func_73732_a(this.field_146289_q, field_146510_b_[(int)(Minecraft.func_71386_F() / 150L % (long)field_146510_b_.length)], this.field_146294_l / 2, this.field_146295_m / 2 + this.field_146289_q.field_78288_b * 2, 16777215);
        }
        else
        {
            int k;

            if (Mouse.isButtonDown(0))
            {
                k = (this.field_146294_l - this.field_146555_f) / 2;
                int l = (this.field_146295_m - this.field_146557_g) / 2;
                int i1 = k + 8;
                int j1 = l + 17;

                if ((this.field_146554_D == 0 || this.field_146554_D == 1) && p_73863_1_ >= i1 && p_73863_1_ < i1 + 224 && p_73863_2_ >= j1 && p_73863_2_ < j1 + 155)
                {
                    if (this.field_146554_D == 0)
                    {
                        this.field_146554_D = 1;
                    }
                    else
                    {
                        this.field_146567_u -= (double)((float)(p_73863_1_ - this.field_146563_h) * this.field_146570_r);
                        this.field_146566_v -= (double)((float)(p_73863_2_ - this.field_146564_i) * this.field_146570_r);
                        this.field_146565_w = this.field_146569_s = this.field_146567_u;
                        this.field_146573_x = this.field_146568_t = this.field_146566_v;
                    }

                    this.field_146563_h = p_73863_1_;
                    this.field_146564_i = p_73863_2_;
                }
            }
            else
            {
                this.field_146554_D = 0;
            }

            k = Mouse.getDWheel();
            float f4 = this.field_146570_r;

            if (k < 0)
            {
                this.field_146570_r += 0.25F;
            }
            else if (k > 0)
            {
                this.field_146570_r -= 0.25F;
            }

            this.field_146570_r = MathHelper.func_76131_a(this.field_146570_r, 1.0F, 2.0F);

            if (this.field_146570_r != f4)
            {
                float f6 = f4 - this.field_146570_r;
                float f5 = f4 * (float)this.field_146555_f;
                float f1 = f4 * (float)this.field_146557_g;
                float f2 = this.field_146570_r * (float)this.field_146555_f;
                float f3 = this.field_146570_r * (float)this.field_146557_g;
                this.field_146567_u -= (double)((f2 - f5) * 0.5F);
                this.field_146566_v -= (double)((f3 - f1) * 0.5F);
                this.field_146565_w = this.field_146569_s = this.field_146567_u;
                this.field_146573_x = this.field_146568_t = this.field_146566_v;
            }

            if (this.field_146565_w < (double)field_146572_y)
            {
                this.field_146565_w = (double)field_146572_y;
            }

            if (this.field_146573_x < (double)field_146571_z)
            {
                this.field_146573_x = (double)field_146571_z;
            }

            if (this.field_146565_w >= (double)field_146559_A)
            {
                this.field_146565_w = (double)(field_146559_A - 1);
            }

            if (this.field_146573_x >= (double)field_146560_B)
            {
                this.field_146573_x = (double)(field_146560_B - 1);
            }

            this.func_146276_q_();
            this.func_146552_b(p_73863_1_, p_73863_2_, p_73863_3_);
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glDisable(GL11.GL_DEPTH_TEST);
            this.func_146553_h();
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glEnable(GL11.GL_DEPTH_TEST);
        }
    }

    public void func_146509_g()
    {
        if (this.field_146558_F)
        {
            this.field_146558_F = false;
        }
    }

    public void func_73876_c()
    {
        if (!this.field_146558_F)
        {
            this.field_146569_s = this.field_146567_u;
            this.field_146568_t = this.field_146566_v;
            double d0 = this.field_146565_w - this.field_146567_u;
            double d1 = this.field_146573_x - this.field_146566_v;

            if (d0 * d0 + d1 * d1 < 4.0D)
            {
                this.field_146567_u += d0;
                this.field_146566_v += d1;
            }
            else
            {
                this.field_146567_u += d0 * 0.85D;
                this.field_146566_v += d1 * 0.85D;
            }
        }
    }

    protected void func_146553_h()
    {
        int i = (this.field_146294_l - this.field_146555_f) / 2;
        int j = (this.field_146295_m - this.field_146557_g) / 2;
        this.field_146289_q.func_78276_b(I18n.func_135052_a("gui.achievements", new Object[0]), i + 15, j + 5, 4210752);
    }

    protected void func_146552_b(int p_146552_1_, int p_146552_2_, float p_146552_3_)
    {
        int k = MathHelper.func_76128_c(this.field_146569_s + (this.field_146567_u - this.field_146569_s) * (double)p_146552_3_);
        int l = MathHelper.func_76128_c(this.field_146568_t + (this.field_146566_v - this.field_146568_t) * (double)p_146552_3_);

        if (k < field_146572_y)
        {
            k = field_146572_y;
        }

        if (l < field_146571_z)
        {
            l = field_146571_z;
        }

        if (k >= field_146559_A)
        {
            k = field_146559_A - 1;
        }

        if (l >= field_146560_B)
        {
            l = field_146560_B - 1;
        }

        int i1 = (this.field_146294_l - this.field_146555_f) / 2;
        int j1 = (this.field_146295_m - this.field_146557_g) / 2;
        int k1 = i1 + 16;
        int l1 = j1 + 17;
        this.field_73735_i = 0.0F;
        GL11.glDepthFunc(GL11.GL_GEQUAL);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)k1, (float)l1, -200.0F);
        GL11.glScalef(1.0F / this.field_146570_r, 1.0F / this.field_146570_r, 0.0F);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glEnable(GL11.GL_COLOR_MATERIAL);
        int i2 = k + 288 >> 4;
        int j2 = l + 288 >> 4;
        int k2 = (k + 288) % 16;
        int l2 = (l + 288) % 16;
        boolean flag = true;
        boolean flag1 = true;
        boolean flag2 = true;
        boolean flag3 = true;
        boolean flag4 = true;
        Random random = new Random();
        float f1 = 16.0F / this.field_146570_r;
        float f2 = 16.0F / this.field_146570_r;
        int i3;
        int j3;
        int k3;

        for (i3 = 0; (float)i3 * f1 - (float)l2 < 155.0F; ++i3)
        {
            float f3 = 0.6F - (float)(j2 + i3) / 25.0F * 0.3F;
            GL11.glColor4f(f3, f3, f3, 1.0F);

            for (j3 = 0; (float)j3 * f2 - (float)k2 < 224.0F; ++j3)
            {
                random.setSeed((long)(this.field_146297_k.func_110432_I().func_148255_b().hashCode() + i2 + j3 + (j2 + i3) * 16));
                k3 = random.nextInt(1 + j2 + i3) + (j2 + i3) / 2;
                IIcon iicon = Blocks.SAND.func_149691_a(0, 0);

                if (k3 <= 37 && j2 + i3 != 35)
                {
                    if (k3 == 22)
                    {
                        if (random.nextInt(2) == 0)
                        {
                            iicon = Blocks.DIAMOND_ORE.func_149691_a(0, 0);
                        }
                        else
                        {
                            iicon = Blocks.REDSTONE_ORE.func_149691_a(0, 0);
                        }
                    }
                    else if (k3 == 10)
                    {
                        iicon = Blocks.IRON_ORE.func_149691_a(0, 0);
                    }
                    else if (k3 == 8)
                    {
                        iicon = Blocks.COAL_ORE.func_149691_a(0, 0);
                    }
                    else if (k3 > 4)
                    {
                        iicon = Blocks.STONE.func_149691_a(0, 0);
                    }
                    else if (k3 > 0)
                    {
                        iicon = Blocks.DIRT.func_149691_a(0, 0);
                    }
                }
                else
                {
                    iicon = Blocks.BEDROCK.func_149691_a(0, 0);
                }

                this.field_146297_k.func_110434_K().func_110577_a(TextureMap.field_110575_b);
                this.func_94065_a(j3 * 16 - k2, i3 * 16 - l2, iicon, 16, 16);
            }
        }

        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthFunc(GL11.GL_LEQUAL);
        this.field_146297_k.func_110434_K().func_110577_a(field_146561_C);
        int i4;
        int j4;
        int l4;

        for (i3 = 0; i3 < AchievementList.ACHIEVEMENTS.size(); ++i3)
        {
            Achievement achievement1 = (Achievement)AchievementList.ACHIEVEMENTS.get(i3);

            if (achievement1.requiredAchievement != null)
            {
                j3 = achievement1.field_75993_a * 24 - k + 11;
                k3 = achievement1.field_75991_b * 24 - l + 11;
                l4 = achievement1.requiredAchievement.field_75993_a * 24 - k + 11;
                int l3 = achievement1.requiredAchievement.field_75991_b * 24 - l + 11;
                boolean flag5 = this.field_146556_E.func_77443_a(achievement1);
                boolean flag6 = this.field_146556_E.func_77442_b(achievement1);
                i4 = this.field_146556_E.func_150874_c(achievement1);

                if (i4 <= 4)
                {
                    j4 = -16777216;

                    if (flag5)
                    {
                        j4 = -6250336;
                    }
                    else if (flag6)
                    {
                        j4 = -16711936;
                    }

                    this.func_73730_a(j3, l4, k3, j4);
                    this.func_73728_b(l4, k3, l3, j4);

                    if (j3 > l4)
                    {
                        this.func_73729_b(j3 - 11 - 7, k3 - 5, 114, 234, 7, 11);
                    }
                    else if (j3 < l4)
                    {
                        this.func_73729_b(j3 + 11, k3 - 5, 107, 234, 7, 11);
                    }
                    else if (k3 > l3)
                    {
                        this.func_73729_b(j3 - 5, k3 - 11 - 7, 96, 234, 11, 7);
                    }
                    else if (k3 < l3)
                    {
                        this.func_73729_b(j3 - 5, k3 + 11, 96, 241, 11, 7);
                    }
                }
            }
        }

        Achievement achievement = null;
        RenderItem renderitem = new RenderItem();
        float f4 = (float)(p_146552_1_ - k1) * this.field_146570_r;
        float f5 = (float)(p_146552_2_ - l1) * this.field_146570_r;
        RenderHelper.func_74520_c();
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glEnable(GL11.GL_COLOR_MATERIAL);
        int i5;
        int j5;

        for (l4 = 0; l4 < AchievementList.ACHIEVEMENTS.size(); ++l4)
        {
            Achievement achievement2 = (Achievement)AchievementList.ACHIEVEMENTS.get(l4);
            i5 = achievement2.field_75993_a * 24 - k;
            j5 = achievement2.field_75991_b * 24 - l;

            if (i5 >= -24 && j5 >= -24 && (float)i5 <= 224.0F * this.field_146570_r && (float)j5 <= 155.0F * this.field_146570_r)
            {
                i4 = this.field_146556_E.func_150874_c(achievement2);
                float f6;

                if (this.field_146556_E.func_77443_a(achievement2))
                {
                    f6 = 0.75F;
                    GL11.glColor4f(f6, f6, f6, 1.0F);
                }
                else if (this.field_146556_E.func_77442_b(achievement2))
                {
                    f6 = 1.0F;
                    GL11.glColor4f(f6, f6, f6, 1.0F);
                }
                else if (i4 < 3)
                {
                    f6 = 0.3F;
                    GL11.glColor4f(f6, f6, f6, 1.0F);
                }
                else if (i4 == 3)
                {
                    f6 = 0.2F;
                    GL11.glColor4f(f6, f6, f6, 1.0F);
                }
                else
                {
                    if (i4 != 4)
                    {
                        continue;
                    }

                    f6 = 0.1F;
                    GL11.glColor4f(f6, f6, f6, 1.0F);
                }

                this.field_146297_k.func_110434_K().func_110577_a(field_146561_C);

                if (achievement2.func_75984_f())
                {
                    this.func_73729_b(i5 - 2, j5 - 2, 26, 202, 26, 26);
                }
                else
                {
                    this.func_73729_b(i5 - 2, j5 - 2, 0, 202, 26, 26);
                }

                if (!this.field_146556_E.func_77442_b(achievement2))
                {
                    f6 = 0.1F;
                    GL11.glColor4f(f6, f6, f6, 1.0F);
                    renderitem.field_77024_a = false;
                }

                GL11.glEnable(GL11.GL_LIGHTING);
                GL11.glEnable(GL11.GL_CULL_FACE);
                renderitem.func_82406_b(this.field_146297_k.field_71466_p, this.field_146297_k.func_110434_K(), achievement2.renderItem, i5 + 3, j5 + 3);
                GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                GL11.glDisable(GL11.GL_LIGHTING);

                if (!this.field_146556_E.func_77442_b(achievement2))
                {
                    renderitem.field_77024_a = true;
                }

                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

                if (f4 >= (float)i5 && f4 <= (float)(i5 + 22) && f5 >= (float)j5 && f5 <= (float)(j5 + 22))
                {
                    achievement = achievement2;
                }
            }
        }

        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glPopMatrix();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.field_146297_k.func_110434_K().func_110577_a(field_146561_C);
        this.func_73729_b(i1, j1, 0, 0, this.field_146555_f, this.field_146557_g);
        this.field_73735_i = 0.0F;
        GL11.glDepthFunc(GL11.GL_LEQUAL);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        super.func_73863_a(p_146552_1_, p_146552_2_, p_146552_3_);

        if (achievement != null)
        {
            String s1 = achievement.func_150951_e().func_150260_c();
            String s2 = achievement.func_75989_e();
            i5 = p_146552_1_ + 12;
            j5 = p_146552_2_ - 4;
            i4 = this.field_146556_E.func_150874_c(achievement);

            if (!this.field_146556_E.func_77442_b(achievement))
            {
                String s;
                int k4;

                if (i4 == 3)
                {
                    s1 = I18n.func_135052_a("achievement.unknown", new Object[0]);
                    j4 = Math.max(this.field_146289_q.func_78256_a(s1), 120);
                    s = (new ChatComponentTranslation("achievement.requires", new Object[] {achievement.requiredAchievement.func_150951_e()})).func_150260_c();
                    k4 = this.field_146289_q.func_78267_b(s, j4);
                    this.func_73733_a(i5 - 3, j5 - 3, i5 + j4 + 3, j5 + k4 + 12 + 3, -1073741824, -1073741824);
                    this.field_146289_q.func_78279_b(s, i5, j5 + 12, j4, -9416624);
                }
                else if (i4 < 3)
                {
                    j4 = Math.max(this.field_146289_q.func_78256_a(s1), 120);
                    s = (new ChatComponentTranslation("achievement.requires", new Object[] {achievement.requiredAchievement.func_150951_e()})).func_150260_c();
                    k4 = this.field_146289_q.func_78267_b(s, j4);
                    this.func_73733_a(i5 - 3, j5 - 3, i5 + j4 + 3, j5 + k4 + 12 + 3, -1073741824, -1073741824);
                    this.field_146289_q.func_78279_b(s, i5, j5 + 12, j4, -9416624);
                }
                else
                {
                    s1 = null;
                }
            }
            else
            {
                j4 = Math.max(this.field_146289_q.func_78256_a(s1), 120);
                int k5 = this.field_146289_q.func_78267_b(s2, j4);

                if (this.field_146556_E.func_77443_a(achievement))
                {
                    k5 += 12;
                }

                this.func_73733_a(i5 - 3, j5 - 3, i5 + j4 + 3, j5 + k5 + 3 + 12, -1073741824, -1073741824);
                this.field_146289_q.func_78279_b(s2, i5, j5 + 12, j4, -6250336);

                if (this.field_146556_E.func_77443_a(achievement))
                {
                    this.field_146289_q.func_78261_a(I18n.func_135052_a("achievement.taken", new Object[0]), i5, j5 + k5 + 4, -7302913);
                }
            }

            if (s1 != null)
            {
                this.field_146289_q.func_78261_a(s1, i5, j5, this.field_146556_E.func_77442_b(achievement) ? (achievement.func_75984_f() ? -128 : -1) : (achievement.func_75984_f() ? -8355776 : -8355712));
            }
        }

        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_LIGHTING);
        RenderHelper.func_74518_a();
    }

    public boolean func_73868_f()
    {
        return !this.field_146558_F;
    }
}