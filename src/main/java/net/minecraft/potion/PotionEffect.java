package net.minecraft.potion;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;

public class PotionEffect
{
    private int field_76462_a;
    private int field_76460_b;
    private int field_76461_c;
    private boolean field_82723_d;
    private boolean field_82724_e;
    @SideOnly(Side.CLIENT)
    private boolean field_100013_f;
    private static final String __OBFID = "CL_00001529";

    public PotionEffect(int p_i1574_1_, int p_i1574_2_)
    {
        this(p_i1574_1_, p_i1574_2_, 0);
    }

    public PotionEffect(int p_i1575_1_, int p_i1575_2_, int p_i1575_3_)
    {
        this(p_i1575_1_, p_i1575_2_, p_i1575_3_, false);
    }

    public PotionEffect(int p_i1576_1_, int p_i1576_2_, int p_i1576_3_, boolean p_i1576_4_)
    {
        this.field_76462_a = p_i1576_1_;
        this.field_76460_b = p_i1576_2_;
        this.field_76461_c = p_i1576_3_;
        this.field_82724_e = p_i1576_4_;
    }

    public PotionEffect(PotionEffect p_i1577_1_)
    {
        this.field_76462_a = p_i1577_1_.field_76462_a;
        this.field_76460_b = p_i1577_1_.field_76460_b;
        this.field_76461_c = p_i1577_1_.field_76461_c;
    }

    public void func_76452_a(PotionEffect p_76452_1_)
    {
        if (this.field_76462_a != p_76452_1_.field_76462_a)
        {
            System.err.println("This method should only be called for matching effects!");
        }

        if (p_76452_1_.field_76461_c > this.field_76461_c)
        {
            this.field_76461_c = p_76452_1_.field_76461_c;
            this.field_76460_b = p_76452_1_.field_76460_b;
        }
        else if (p_76452_1_.field_76461_c == this.field_76461_c && this.field_76460_b < p_76452_1_.field_76460_b)
        {
            this.field_76460_b = p_76452_1_.field_76460_b;
        }
        else if (!p_76452_1_.field_82724_e && this.field_82724_e)
        {
            this.field_82724_e = p_76452_1_.field_82724_e;
        }
    }

    public int func_76456_a()
    {
        return this.field_76462_a;
    }

    public int func_76459_b()
    {
        return this.field_76460_b;
    }

    public int func_76458_c()
    {
        return this.field_76461_c;
    }

    public void func_82721_a(boolean p_82721_1_)
    {
        this.field_82723_d = p_82721_1_;
    }

    public boolean func_82720_e()
    {
        return this.field_82724_e;
    }

    public boolean func_76455_a(EntityLivingBase p_76455_1_)
    {
        if (this.field_76460_b > 0)
        {
            if (Potion.field_76425_a[this.field_76462_a].func_76397_a(this.field_76460_b, this.field_76461_c))
            {
                this.func_76457_b(p_76455_1_);
            }

            this.func_76454_e();
        }

        return this.field_76460_b > 0;
    }

    private int func_76454_e()
    {
        return --this.field_76460_b;
    }

    public void func_76457_b(EntityLivingBase p_76457_1_)
    {
        if (this.field_76460_b > 0)
        {
            Potion.field_76425_a[this.field_76462_a].func_76394_a(p_76457_1_, this.field_76461_c);
        }
    }

    public String func_76453_d()
    {
        return Potion.field_76425_a[this.field_76462_a].func_76393_a();
    }

    public int hashCode()
    {
        return this.field_76462_a;
    }

    public String toString()
    {
        String s = "";

        if (this.func_76458_c() > 0)
        {
            s = this.func_76453_d() + " x " + (this.func_76458_c() + 1) + ", Duration: " + this.func_76459_b();
        }
        else
        {
            s = this.func_76453_d() + ", Duration: " + this.func_76459_b();
        }

        if (this.field_82723_d)
        {
            s = s + ", Splash: true";
        }

        return Potion.field_76425_a[this.field_76462_a].func_76395_i() ? "(" + s + ")" : s;
    }

    public boolean equals(Object p_equals_1_)
    {
        if (!(p_equals_1_ instanceof PotionEffect))
        {
            return false;
        }
        else
        {
            PotionEffect potioneffect = (PotionEffect)p_equals_1_;
            return this.field_76462_a == potioneffect.field_76462_a && this.field_76461_c == potioneffect.field_76461_c && this.field_76460_b == potioneffect.field_76460_b && this.field_82723_d == potioneffect.field_82723_d && this.field_82724_e == potioneffect.field_82724_e;
        }
    }

    public NBTTagCompound func_82719_a(NBTTagCompound p_82719_1_)
    {
        p_82719_1_.func_74774_a("Id", (byte)this.func_76456_a());
        p_82719_1_.func_74774_a("Amplifier", (byte)this.func_76458_c());
        p_82719_1_.func_74768_a("Duration", this.func_76459_b());
        p_82719_1_.func_74757_a("Ambient", this.func_82720_e());
        return p_82719_1_;
    }

    public static PotionEffect func_82722_b(NBTTagCompound p_82722_0_)
    {
        byte b0 = p_82722_0_.func_74771_c("Id");

        if (b0 >= 0 && b0 < Potion.field_76425_a.length && Potion.field_76425_a[b0] != null)
        {
            byte b1 = p_82722_0_.func_74771_c("Amplifier");
            int i = p_82722_0_.func_74762_e("Duration");
            boolean flag = p_82722_0_.func_74767_n("Ambient");
            return new PotionEffect(b0, i, b1, flag);
        }
        else
        {
            return null;
        }
    }

    @SideOnly(Side.CLIENT)
    public void func_100012_b(boolean p_100012_1_)
    {
        this.field_100013_f = p_100012_1_;
    }

    @SideOnly(Side.CLIENT)
    public boolean func_100011_g()
    {
        return this.field_100013_f;
    }
}