package net.minecraft.util;

public class AxisAlignedBB
{
    public double field_72340_a;
    public double field_72338_b;
    public double field_72339_c;
    public double field_72336_d;
    public double field_72337_e;
    public double field_72334_f;
    private static final String __OBFID = "CL_00000607";

    public static AxisAlignedBB func_72330_a(double p_72330_0_, double p_72330_2_, double p_72330_4_, double p_72330_6_, double p_72330_8_, double p_72330_10_)
    {
        return new AxisAlignedBB(p_72330_0_, p_72330_2_, p_72330_4_, p_72330_6_, p_72330_8_, p_72330_10_);
    }

    protected AxisAlignedBB(double p_i2300_1_, double p_i2300_3_, double p_i2300_5_, double p_i2300_7_, double p_i2300_9_, double p_i2300_11_)
    {
        this.field_72340_a = p_i2300_1_;
        this.field_72338_b = p_i2300_3_;
        this.field_72339_c = p_i2300_5_;
        this.field_72336_d = p_i2300_7_;
        this.field_72337_e = p_i2300_9_;
        this.field_72334_f = p_i2300_11_;
    }

    public AxisAlignedBB func_72324_b(double p_72324_1_, double p_72324_3_, double p_72324_5_, double p_72324_7_, double p_72324_9_, double p_72324_11_)
    {
        this.field_72340_a = p_72324_1_;
        this.field_72338_b = p_72324_3_;
        this.field_72339_c = p_72324_5_;
        this.field_72336_d = p_72324_7_;
        this.field_72337_e = p_72324_9_;
        this.field_72334_f = p_72324_11_;
        return this;
    }

    public AxisAlignedBB func_72321_a(double p_72321_1_, double p_72321_3_, double p_72321_5_)
    {
        double d3 = this.field_72340_a;
        double d4 = this.field_72338_b;
        double d5 = this.field_72339_c;
        double d6 = this.field_72336_d;
        double d7 = this.field_72337_e;
        double d8 = this.field_72334_f;

        if (p_72321_1_ < 0.0D)
        {
            d3 += p_72321_1_;
        }

        if (p_72321_1_ > 0.0D)
        {
            d6 += p_72321_1_;
        }

        if (p_72321_3_ < 0.0D)
        {
            d4 += p_72321_3_;
        }

        if (p_72321_3_ > 0.0D)
        {
            d7 += p_72321_3_;
        }

        if (p_72321_5_ < 0.0D)
        {
            d5 += p_72321_5_;
        }

        if (p_72321_5_ > 0.0D)
        {
            d8 += p_72321_5_;
        }

        return func_72330_a(d3, d4, d5, d6, d7, d8);
    }

    public AxisAlignedBB func_72314_b(double p_72314_1_, double p_72314_3_, double p_72314_5_)
    {
        double d3 = this.field_72340_a - p_72314_1_;
        double d4 = this.field_72338_b - p_72314_3_;
        double d5 = this.field_72339_c - p_72314_5_;
        double d6 = this.field_72336_d + p_72314_1_;
        double d7 = this.field_72337_e + p_72314_3_;
        double d8 = this.field_72334_f + p_72314_5_;
        return func_72330_a(d3, d4, d5, d6, d7, d8);
    }

    public AxisAlignedBB func_111270_a(AxisAlignedBB p_111270_1_)
    {
        double d0 = Math.min(this.field_72340_a, p_111270_1_.field_72340_a);
        double d1 = Math.min(this.field_72338_b, p_111270_1_.field_72338_b);
        double d2 = Math.min(this.field_72339_c, p_111270_1_.field_72339_c);
        double d3 = Math.max(this.field_72336_d, p_111270_1_.field_72336_d);
        double d4 = Math.max(this.field_72337_e, p_111270_1_.field_72337_e);
        double d5 = Math.max(this.field_72334_f, p_111270_1_.field_72334_f);
        return func_72330_a(d0, d1, d2, d3, d4, d5);
    }

    public AxisAlignedBB func_72325_c(double p_72325_1_, double p_72325_3_, double p_72325_5_)
    {
        return func_72330_a(this.field_72340_a + p_72325_1_, this.field_72338_b + p_72325_3_, this.field_72339_c + p_72325_5_, this.field_72336_d + p_72325_1_, this.field_72337_e + p_72325_3_, this.field_72334_f + p_72325_5_);
    }

    public double func_72316_a(AxisAlignedBB p_72316_1_, double p_72316_2_)
    {
        if (p_72316_1_.field_72337_e > this.field_72338_b && p_72316_1_.field_72338_b < this.field_72337_e)
        {
            if (p_72316_1_.field_72334_f > this.field_72339_c && p_72316_1_.field_72339_c < this.field_72334_f)
            {
                double d1;

                if (p_72316_2_ > 0.0D && p_72316_1_.field_72336_d <= this.field_72340_a)
                {
                    d1 = this.field_72340_a - p_72316_1_.field_72336_d;

                    if (d1 < p_72316_2_)
                    {
                        p_72316_2_ = d1;
                    }
                }

                if (p_72316_2_ < 0.0D && p_72316_1_.field_72340_a >= this.field_72336_d)
                {
                    d1 = this.field_72336_d - p_72316_1_.field_72340_a;

                    if (d1 > p_72316_2_)
                    {
                        p_72316_2_ = d1;
                    }
                }

                return p_72316_2_;
            }
            else
            {
                return p_72316_2_;
            }
        }
        else
        {
            return p_72316_2_;
        }
    }

    public double func_72323_b(AxisAlignedBB p_72323_1_, double p_72323_2_)
    {
        if (p_72323_1_.field_72336_d > this.field_72340_a && p_72323_1_.field_72340_a < this.field_72336_d)
        {
            if (p_72323_1_.field_72334_f > this.field_72339_c && p_72323_1_.field_72339_c < this.field_72334_f)
            {
                double d1;

                if (p_72323_2_ > 0.0D && p_72323_1_.field_72337_e <= this.field_72338_b)
                {
                    d1 = this.field_72338_b - p_72323_1_.field_72337_e;

                    if (d1 < p_72323_2_)
                    {
                        p_72323_2_ = d1;
                    }
                }

                if (p_72323_2_ < 0.0D && p_72323_1_.field_72338_b >= this.field_72337_e)
                {
                    d1 = this.field_72337_e - p_72323_1_.field_72338_b;

                    if (d1 > p_72323_2_)
                    {
                        p_72323_2_ = d1;
                    }
                }

                return p_72323_2_;
            }
            else
            {
                return p_72323_2_;
            }
        }
        else
        {
            return p_72323_2_;
        }
    }

    public double func_72322_c(AxisAlignedBB p_72322_1_, double p_72322_2_)
    {
        if (p_72322_1_.field_72336_d > this.field_72340_a && p_72322_1_.field_72340_a < this.field_72336_d)
        {
            if (p_72322_1_.field_72337_e > this.field_72338_b && p_72322_1_.field_72338_b < this.field_72337_e)
            {
                double d1;

                if (p_72322_2_ > 0.0D && p_72322_1_.field_72334_f <= this.field_72339_c)
                {
                    d1 = this.field_72339_c - p_72322_1_.field_72334_f;

                    if (d1 < p_72322_2_)
                    {
                        p_72322_2_ = d1;
                    }
                }

                if (p_72322_2_ < 0.0D && p_72322_1_.field_72339_c >= this.field_72334_f)
                {
                    d1 = this.field_72334_f - p_72322_1_.field_72339_c;

                    if (d1 > p_72322_2_)
                    {
                        p_72322_2_ = d1;
                    }
                }

                return p_72322_2_;
            }
            else
            {
                return p_72322_2_;
            }
        }
        else
        {
            return p_72322_2_;
        }
    }

    public boolean func_72326_a(AxisAlignedBB p_72326_1_)
    {
        return p_72326_1_.field_72336_d > this.field_72340_a && p_72326_1_.field_72340_a < this.field_72336_d ? (p_72326_1_.field_72337_e > this.field_72338_b && p_72326_1_.field_72338_b < this.field_72337_e ? p_72326_1_.field_72334_f > this.field_72339_c && p_72326_1_.field_72339_c < this.field_72334_f : false) : false;
    }

    public AxisAlignedBB func_72317_d(double p_72317_1_, double p_72317_3_, double p_72317_5_)
    {
        this.field_72340_a += p_72317_1_;
        this.field_72338_b += p_72317_3_;
        this.field_72339_c += p_72317_5_;
        this.field_72336_d += p_72317_1_;
        this.field_72337_e += p_72317_3_;
        this.field_72334_f += p_72317_5_;
        return this;
    }

    public boolean func_72318_a(Vec3 p_72318_1_)
    {
        return p_72318_1_.field_72450_a > this.field_72340_a && p_72318_1_.field_72450_a < this.field_72336_d ? (p_72318_1_.field_72448_b > this.field_72338_b && p_72318_1_.field_72448_b < this.field_72337_e ? p_72318_1_.field_72449_c > this.field_72339_c && p_72318_1_.field_72449_c < this.field_72334_f : false) : false;
    }

    public double func_72320_b()
    {
        double d0 = this.field_72336_d - this.field_72340_a;
        double d1 = this.field_72337_e - this.field_72338_b;
        double d2 = this.field_72334_f - this.field_72339_c;
        return (d0 + d1 + d2) / 3.0D;
    }

    public AxisAlignedBB func_72331_e(double p_72331_1_, double p_72331_3_, double p_72331_5_)
    {
        double d3 = this.field_72340_a + p_72331_1_;
        double d4 = this.field_72338_b + p_72331_3_;
        double d5 = this.field_72339_c + p_72331_5_;
        double d6 = this.field_72336_d - p_72331_1_;
        double d7 = this.field_72337_e - p_72331_3_;
        double d8 = this.field_72334_f - p_72331_5_;
        return func_72330_a(d3, d4, d5, d6, d7, d8);
    }

    public AxisAlignedBB func_72329_c()
    {
        return func_72330_a(this.field_72340_a, this.field_72338_b, this.field_72339_c, this.field_72336_d, this.field_72337_e, this.field_72334_f);
    }

    public MovingObjectPosition func_72327_a(Vec3 p_72327_1_, Vec3 p_72327_2_)
    {
        Vec3 vec32 = p_72327_1_.func_72429_b(p_72327_2_, this.field_72340_a);
        Vec3 vec33 = p_72327_1_.func_72429_b(p_72327_2_, this.field_72336_d);
        Vec3 vec34 = p_72327_1_.func_72435_c(p_72327_2_, this.field_72338_b);
        Vec3 vec35 = p_72327_1_.func_72435_c(p_72327_2_, this.field_72337_e);
        Vec3 vec36 = p_72327_1_.func_72434_d(p_72327_2_, this.field_72339_c);
        Vec3 vec37 = p_72327_1_.func_72434_d(p_72327_2_, this.field_72334_f);

        if (!this.func_72333_b(vec32))
        {
            vec32 = null;
        }

        if (!this.func_72333_b(vec33))
        {
            vec33 = null;
        }

        if (!this.func_72315_c(vec34))
        {
            vec34 = null;
        }

        if (!this.func_72315_c(vec35))
        {
            vec35 = null;
        }

        if (!this.func_72319_d(vec36))
        {
            vec36 = null;
        }

        if (!this.func_72319_d(vec37))
        {
            vec37 = null;
        }

        Vec3 vec38 = null;

        if (vec32 != null && (vec38 == null || p_72327_1_.func_72436_e(vec32) < p_72327_1_.func_72436_e(vec38)))
        {
            vec38 = vec32;
        }

        if (vec33 != null && (vec38 == null || p_72327_1_.func_72436_e(vec33) < p_72327_1_.func_72436_e(vec38)))
        {
            vec38 = vec33;
        }

        if (vec34 != null && (vec38 == null || p_72327_1_.func_72436_e(vec34) < p_72327_1_.func_72436_e(vec38)))
        {
            vec38 = vec34;
        }

        if (vec35 != null && (vec38 == null || p_72327_1_.func_72436_e(vec35) < p_72327_1_.func_72436_e(vec38)))
        {
            vec38 = vec35;
        }

        if (vec36 != null && (vec38 == null || p_72327_1_.func_72436_e(vec36) < p_72327_1_.func_72436_e(vec38)))
        {
            vec38 = vec36;
        }

        if (vec37 != null && (vec38 == null || p_72327_1_.func_72436_e(vec37) < p_72327_1_.func_72436_e(vec38)))
        {
            vec38 = vec37;
        }

        if (vec38 == null)
        {
            return null;
        }
        else
        {
            byte b0 = -1;

            if (vec38 == vec32)
            {
                b0 = 4;
            }

            if (vec38 == vec33)
            {
                b0 = 5;
            }

            if (vec38 == vec34)
            {
                b0 = 0;
            }

            if (vec38 == vec35)
            {
                b0 = 1;
            }

            if (vec38 == vec36)
            {
                b0 = 2;
            }

            if (vec38 == vec37)
            {
                b0 = 3;
            }

            return new MovingObjectPosition(0, 0, 0, b0, vec38);
        }
    }

    private boolean func_72333_b(Vec3 p_72333_1_)
    {
        return p_72333_1_ == null ? false : p_72333_1_.field_72448_b >= this.field_72338_b && p_72333_1_.field_72448_b <= this.field_72337_e && p_72333_1_.field_72449_c >= this.field_72339_c && p_72333_1_.field_72449_c <= this.field_72334_f;
    }

    private boolean func_72315_c(Vec3 p_72315_1_)
    {
        return p_72315_1_ == null ? false : p_72315_1_.field_72450_a >= this.field_72340_a && p_72315_1_.field_72450_a <= this.field_72336_d && p_72315_1_.field_72449_c >= this.field_72339_c && p_72315_1_.field_72449_c <= this.field_72334_f;
    }

    private boolean func_72319_d(Vec3 p_72319_1_)
    {
        return p_72319_1_ == null ? false : p_72319_1_.field_72450_a >= this.field_72340_a && p_72319_1_.field_72450_a <= this.field_72336_d && p_72319_1_.field_72448_b >= this.field_72338_b && p_72319_1_.field_72448_b <= this.field_72337_e;
    }

    public void func_72328_c(AxisAlignedBB p_72328_1_)
    {
        this.field_72340_a = p_72328_1_.field_72340_a;
        this.field_72338_b = p_72328_1_.field_72338_b;
        this.field_72339_c = p_72328_1_.field_72339_c;
        this.field_72336_d = p_72328_1_.field_72336_d;
        this.field_72337_e = p_72328_1_.field_72337_e;
        this.field_72334_f = p_72328_1_.field_72334_f;
    }

    public String toString()
    {
        return "box[" + this.field_72340_a + ", " + this.field_72338_b + ", " + this.field_72339_c + " -> " + this.field_72336_d + ", " + this.field_72337_e + ", " + this.field_72334_f + "]";
    }
}