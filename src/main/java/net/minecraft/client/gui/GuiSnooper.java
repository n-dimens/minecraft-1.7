package net.minecraft.client.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.Map.Entry;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;

@SideOnly(Side.CLIENT)
public class GuiSnooper extends GuiScreen
{
    private final GuiScreen field_146608_a;
    private final GameSettings field_146603_f;
    private final java.util.List field_146604_g = new ArrayList();
    private final java.util.List field_146609_h = new ArrayList();
    private String field_146610_i;
    private String[] field_146607_r;
    private GuiSnooper.List field_146606_s;
    private GuiButton field_146605_t;
    private static final String __OBFID = "CL_00000714";

    public GuiSnooper(GuiScreen p_i1061_1_, GameSettings p_i1061_2_)
    {
        this.field_146608_a = p_i1061_1_;
        this.field_146603_f = p_i1061_2_;
    }

    public void func_73866_w_()
    {
        this.field_146610_i = I18n.func_135052_a("options.snooper.title", new Object[0]);
        String s = I18n.func_135052_a("options.snooper.desc", new Object[0]);
        ArrayList arraylist = new ArrayList();
        Iterator iterator = this.field_146289_q.func_78271_c(s, this.field_146294_l - 30).iterator();

        while (iterator.hasNext())
        {
            String s1 = (String)iterator.next();
            arraylist.add(s1);
        }

        this.field_146607_r = (String[])arraylist.toArray(new String[0]);
        this.field_146604_g.clear();
        this.field_146609_h.clear();
        this.field_146292_n.add(this.field_146605_t = new GuiButton(1, this.field_146294_l / 2 - 152, this.field_146295_m - 30, 150, 20, this.field_146603_f.func_74297_c(GameSettings.Options.SNOOPER_ENABLED)));
        this.field_146292_n.add(new GuiButton(2, this.field_146294_l / 2 + 2, this.field_146295_m - 30, 150, 20, I18n.func_135052_a("gui.done", new Object[0])));
        boolean flag = this.field_146297_k.func_71401_C() != null && this.field_146297_k.func_71401_C().func_80003_ah() != null;
        Iterator iterator1 = (new TreeMap(this.field_146297_k.func_71378_E().func_76465_c())).entrySet().iterator();
        Entry entry;

        while (iterator1.hasNext())
        {
            entry = (Entry)iterator1.next();
            this.field_146604_g.add((flag ? "C " : "") + (String)entry.getKey());
            this.field_146609_h.add(this.field_146289_q.func_78269_a((String)entry.getValue(), this.field_146294_l - 220));
        }

        if (flag)
        {
            iterator1 = (new TreeMap(this.field_146297_k.func_71401_C().func_80003_ah().func_76465_c())).entrySet().iterator();

            while (iterator1.hasNext())
            {
                entry = (Entry)iterator1.next();
                this.field_146604_g.add("S " + (String)entry.getKey());
                this.field_146609_h.add(this.field_146289_q.func_78269_a((String)entry.getValue(), this.field_146294_l - 220));
            }
        }

        this.field_146606_s = new GuiSnooper.List();
    }

    protected void func_146284_a(GuiButton p_146284_1_)
    {
        if (p_146284_1_.field_146124_l)
        {
            if (p_146284_1_.field_146127_k == 2)
            {
                this.field_146603_f.func_74303_b();
                this.field_146603_f.func_74303_b();
                this.field_146297_k.func_147108_a(this.field_146608_a);
            }

            if (p_146284_1_.field_146127_k == 1)
            {
                this.field_146603_f.func_74306_a(GameSettings.Options.SNOOPER_ENABLED, 1);
                this.field_146605_t.field_146126_j = this.field_146603_f.func_74297_c(GameSettings.Options.SNOOPER_ENABLED);
            }
        }
    }

    public void func_73863_a(int p_73863_1_, int p_73863_2_, float p_73863_3_)
    {
        this.func_146276_q_();
        this.field_146606_s.func_148128_a(p_73863_1_, p_73863_2_, p_73863_3_);
        this.func_73732_a(this.field_146289_q, this.field_146610_i, this.field_146294_l / 2, 8, 16777215);
        int k = 22;
        String[] astring = this.field_146607_r;
        int l = astring.length;

        for (int i1 = 0; i1 < l; ++i1)
        {
            String s = astring[i1];
            this.func_73732_a(this.field_146289_q, s, this.field_146294_l / 2, k, 8421504);
            k += this.field_146289_q.field_78288_b;
        }

        super.func_73863_a(p_73863_1_, p_73863_2_, p_73863_3_);
    }

    @SideOnly(Side.CLIENT)
    class List extends GuiSlot
    {
        private static final String __OBFID = "CL_00000715";

        public List()
        {
            super(GuiSnooper.this.field_146297_k, GuiSnooper.this.field_146294_l, GuiSnooper.this.field_146295_m, 80, GuiSnooper.this.field_146295_m - 40, GuiSnooper.this.field_146289_q.field_78288_b + 1);
        }

        protected int func_148127_b()
        {
            return GuiSnooper.this.field_146604_g.size();
        }

        protected void func_148144_a(int p_148144_1_, boolean p_148144_2_, int p_148144_3_, int p_148144_4_) {}

        protected boolean func_148131_a(int p_148131_1_)
        {
            return false;
        }

        protected void func_148123_a() {}

        protected void func_148126_a(int p_148126_1_, int p_148126_2_, int p_148126_3_, int p_148126_4_, Tessellator p_148126_5_, int p_148126_6_, int p_148126_7_)
        {
            GuiSnooper.this.field_146289_q.func_78276_b((String)GuiSnooper.this.field_146604_g.get(p_148126_1_), 10, p_148126_3_, 16777215);
            GuiSnooper.this.field_146289_q.func_78276_b((String)GuiSnooper.this.field_146609_h.get(p_148126_1_), 230, p_148126_3_, 16777215);
        }

        protected int func_148137_d()
        {
            return this.field_148155_a - 10;
        }
    }
}