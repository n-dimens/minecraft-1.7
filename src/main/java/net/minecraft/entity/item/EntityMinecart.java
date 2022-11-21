package net.minecraft.entity.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRailBase;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityMinecartCommandBlock;
import net.minecraft.entity.ai.EntityMinecartMobSpawner;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public abstract class EntityMinecart extends Entity
{
    private boolean field_70499_f;
    private String field_94102_c;
    private static final int[][][] field_70500_g = new int[][][] {{{0, 0, -1}, {0, 0, 1}}, {{ -1, 0, 0}, {1, 0, 0}}, {{ -1, -1, 0}, {1, 0, 0}}, {{ -1, 0, 0}, {1, -1, 0}}, {{0, 0, -1}, {0, -1, 1}}, {{0, -1, -1}, {0, 0, 1}}, {{0, 0, 1}, {1, 0, 0}}, {{0, 0, 1}, { -1, 0, 0}}, {{0, 0, -1}, { -1, 0, 0}}, {{0, 0, -1}, {1, 0, 0}}};
    private int field_70510_h;
    private double field_70511_i;
    private double field_70509_j;
    private double field_70514_an;
    private double field_70512_ao;
    private double field_70513_ap;
    @SideOnly(Side.CLIENT)
    private double field_70508_aq;
    @SideOnly(Side.CLIENT)
    private double field_70507_ar;
    @SideOnly(Side.CLIENT)
    private double field_70506_as;
    private static final String __OBFID = "CL_00001670";

    public EntityMinecart(World p_i1712_1_)
    {
        super(p_i1712_1_);
        this.field_70156_m = true;
        this.func_70105_a(0.98F, 0.7F);
        this.field_70129_M = this.field_70131_O / 2.0F;
    }

    public static EntityMinecart func_94090_a(World p_94090_0_, double p_94090_1_, double p_94090_3_, double p_94090_5_, int p_94090_7_)
    {
        switch (p_94090_7_)
        {
            case 1:
                return new EntityMinecartChest(p_94090_0_, p_94090_1_, p_94090_3_, p_94090_5_);
            case 2:
                return new EntityMinecartFurnace(p_94090_0_, p_94090_1_, p_94090_3_, p_94090_5_);
            case 3:
                return new EntityMinecartTNT(p_94090_0_, p_94090_1_, p_94090_3_, p_94090_5_);
            case 4:
                return new EntityMinecartMobSpawner(p_94090_0_, p_94090_1_, p_94090_3_, p_94090_5_);
            case 5:
                return new EntityMinecartHopper(p_94090_0_, p_94090_1_, p_94090_3_, p_94090_5_);
            case 6:
                return new EntityMinecartCommandBlock(p_94090_0_, p_94090_1_, p_94090_3_, p_94090_5_);
            default:
                return new EntityMinecartEmpty(p_94090_0_, p_94090_1_, p_94090_3_, p_94090_5_);
        }
    }

    protected boolean func_70041_e_()
    {
        return false;
    }

    protected void func_70088_a()
    {
        this.field_70180_af.func_75682_a(17, new Integer(0));
        this.field_70180_af.func_75682_a(18, new Integer(1));
        this.field_70180_af.func_75682_a(19, new Float(0.0F));
        this.field_70180_af.func_75682_a(20, new Integer(0));
        this.field_70180_af.func_75682_a(21, new Integer(6));
        this.field_70180_af.func_75682_a(22, Byte.valueOf((byte)0));
    }

    public AxisAlignedBB func_70114_g(Entity p_70114_1_)
    {
        return p_70114_1_.func_70104_M() ? p_70114_1_.field_70121_D : null;
    }

    public AxisAlignedBB func_70046_E()
    {
        return null;
    }

    public boolean func_70104_M()
    {
        return true;
    }

    public EntityMinecart(World p_i1713_1_, double p_i1713_2_, double p_i1713_4_, double p_i1713_6_)
    {
        this(p_i1713_1_);
        this.func_70107_b(p_i1713_2_, p_i1713_4_, p_i1713_6_);
        this.field_70159_w = 0.0D;
        this.field_70181_x = 0.0D;
        this.field_70179_y = 0.0D;
        this.field_70169_q = p_i1713_2_;
        this.field_70167_r = p_i1713_4_;
        this.field_70166_s = p_i1713_6_;
    }

    public double func_70042_X()
    {
        return (double)this.field_70131_O * 0.0D - 0.30000001192092896D;
    }

    public boolean func_70097_a(DamageSource p_70097_1_, float p_70097_2_)
    {
        if (!this.field_70170_p.field_72995_K && !this.field_70128_L)
        {
            if (this.func_85032_ar())
            {
                return false;
            }
            else
            {
                this.func_70494_i(-this.func_70493_k());
                this.func_70497_h(10);
                this.func_70018_K();
                this.func_70492_c(this.func_70491_i() + p_70097_2_ * 10.0F);
                boolean flag = p_70097_1_.func_76346_g() instanceof EntityPlayer && ((EntityPlayer)p_70097_1_.func_76346_g()).field_71075_bZ.field_75098_d;

                if (flag || this.func_70491_i() > 40.0F)
                {
                    if (this.field_70153_n != null)
                    {
                        this.field_70153_n.func_70078_a(this);
                    }

                    if (flag && !this.func_145818_k_())
                    {
                        this.func_70106_y();
                    }
                    else
                    {
                        this.func_94095_a(p_70097_1_);
                    }
                }

                return true;
            }
        }
        else
        {
            return true;
        }
    }

    public void func_94095_a(DamageSource p_94095_1_)
    {
        this.func_70106_y();
        ItemStack itemstack = new ItemStack(Items.MINECART, 1);

        if (this.field_94102_c != null)
        {
            itemstack.func_151001_c(this.field_94102_c);
        }

        this.func_70099_a(itemstack, 0.0F);
    }

    @SideOnly(Side.CLIENT)
    public void func_70057_ab()
    {
        this.func_70494_i(-this.func_70493_k());
        this.func_70497_h(10);
        this.func_70492_c(this.func_70491_i() + this.func_70491_i() * 10.0F);
    }

    public boolean func_70067_L()
    {
        return !this.field_70128_L;
    }

    public void func_70106_y()
    {
        super.func_70106_y();
    }

    public void func_70071_h_()
    {
        if (this.func_70496_j() > 0)
        {
            this.func_70497_h(this.func_70496_j() - 1);
        }

        if (this.func_70491_i() > 0.0F)
        {
            this.func_70492_c(this.func_70491_i() - 1.0F);
        }

        if (this.field_70163_u < -64.0D)
        {
            this.func_70076_C();
        }

        int i;

        if (!this.field_70170_p.field_72995_K && this.field_70170_p instanceof WorldServer)
        {
            this.field_70170_p.field_72984_F.func_76320_a("portal");
            MinecraftServer minecraftserver = ((WorldServer)this.field_70170_p).func_73046_m();
            i = this.func_82145_z();

            if (this.field_71087_bX)
            {
                if (minecraftserver.func_71255_r())
                {
                    if (this.field_70154_o == null && this.field_82153_h++ >= i)
                    {
                        this.field_82153_h = i;
                        this.field_71088_bW = this.func_82147_ab();
                        byte b0;

                        if (this.field_70170_p.field_73011_w.field_76574_g == -1)
                        {
                            b0 = 0;
                        }
                        else
                        {
                            b0 = -1;
                        }

                        this.func_71027_c(b0);
                    }

                    this.field_71087_bX = false;
                }
            }
            else
            {
                if (this.field_82153_h > 0)
                {
                    this.field_82153_h -= 4;
                }

                if (this.field_82153_h < 0)
                {
                    this.field_82153_h = 0;
                }
            }

            if (this.field_71088_bW > 0)
            {
                --this.field_71088_bW;
            }

            this.field_70170_p.field_72984_F.func_76319_b();
        }

        if (this.field_70170_p.field_72995_K)
        {
            if (this.field_70510_h > 0)
            {
                double d6 = this.field_70165_t + (this.field_70511_i - this.field_70165_t) / (double)this.field_70510_h;
                double d7 = this.field_70163_u + (this.field_70509_j - this.field_70163_u) / (double)this.field_70510_h;
                double d1 = this.field_70161_v + (this.field_70514_an - this.field_70161_v) / (double)this.field_70510_h;
                double d3 = MathHelper.func_76138_g(this.field_70512_ao - (double)this.field_70177_z);
                this.field_70177_z = (float)((double)this.field_70177_z + d3 / (double)this.field_70510_h);
                this.field_70125_A = (float)((double)this.field_70125_A + (this.field_70513_ap - (double)this.field_70125_A) / (double)this.field_70510_h);
                --this.field_70510_h;
                this.func_70107_b(d6, d7, d1);
                this.func_70101_b(this.field_70177_z, this.field_70125_A);
            }
            else
            {
                this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
                this.func_70101_b(this.field_70177_z, this.field_70125_A);
            }
        }
        else
        {
            this.field_70169_q = this.field_70165_t;
            this.field_70167_r = this.field_70163_u;
            this.field_70166_s = this.field_70161_v;
            this.field_70181_x -= 0.03999999910593033D;
            int l = MathHelper.func_76128_c(this.field_70165_t);
            i = MathHelper.func_76128_c(this.field_70163_u);
            int i1 = MathHelper.func_76128_c(this.field_70161_v);

            if (BlockRailBase.func_150049_b_(this.field_70170_p, l, i - 1, i1))
            {
                --i;
            }

            double d0 = 0.4D;
            double d2 = 0.0078125D;
            Block block = this.field_70170_p.func_147439_a(l, i, i1);

            if (BlockRailBase.func_150051_a(block))
            {
                int j = this.field_70170_p.func_72805_g(l, i, i1);
                this.func_145821_a(l, i, i1, d0, d2, block, j);

                if (block == Blocks.field_150408_cc)
                {
                    this.func_96095_a(l, i, i1, (j & 8) != 0);
                }
            }
            else
            {
                this.func_94088_b(d0);
            }

            this.func_145775_I();
            this.field_70125_A = 0.0F;
            double d8 = this.field_70169_q - this.field_70165_t;
            double d4 = this.field_70166_s - this.field_70161_v;

            if (d8 * d8 + d4 * d4 > 0.001D)
            {
                this.field_70177_z = (float)(Math.atan2(d4, d8) * 180.0D / Math.PI);

                if (this.field_70499_f)
                {
                    this.field_70177_z += 180.0F;
                }
            }

            double d5 = (double)MathHelper.func_76142_g(this.field_70177_z - this.field_70126_B);

            if (d5 < -170.0D || d5 >= 170.0D)
            {
                this.field_70177_z += 180.0F;
                this.field_70499_f = !this.field_70499_f;
            }

            this.func_70101_b(this.field_70177_z, this.field_70125_A);
            List list = this.field_70170_p.func_72839_b(this, this.field_70121_D.func_72314_b(0.20000000298023224D, 0.0D, 0.20000000298023224D));

            if (list != null && !list.isEmpty())
            {
                for (int k = 0; k < list.size(); ++k)
                {
                    Entity entity = (Entity)list.get(k);

                    if (entity != this.field_70153_n && entity.func_70104_M() && entity instanceof EntityMinecart)
                    {
                        entity.func_70108_f(this);
                    }
                }
            }

            if (this.field_70153_n != null && this.field_70153_n.field_70128_L)
            {
                if (this.field_70153_n.field_70154_o == this)
                {
                    this.field_70153_n.field_70154_o = null;
                }

                this.field_70153_n = null;
            }
        }
    }

    public void func_96095_a(int p_96095_1_, int p_96095_2_, int p_96095_3_, boolean p_96095_4_) {}

    protected void func_94088_b(double p_94088_1_)
    {
        if (this.field_70159_w < -p_94088_1_)
        {
            this.field_70159_w = -p_94088_1_;
        }

        if (this.field_70159_w > p_94088_1_)
        {
            this.field_70159_w = p_94088_1_;
        }

        if (this.field_70179_y < -p_94088_1_)
        {
            this.field_70179_y = -p_94088_1_;
        }

        if (this.field_70179_y > p_94088_1_)
        {
            this.field_70179_y = p_94088_1_;
        }

        if (this.field_70122_E)
        {
            this.field_70159_w *= 0.5D;
            this.field_70181_x *= 0.5D;
            this.field_70179_y *= 0.5D;
        }

        this.func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);

        if (!this.field_70122_E)
        {
            this.field_70159_w *= 0.949999988079071D;
            this.field_70181_x *= 0.949999988079071D;
            this.field_70179_y *= 0.949999988079071D;
        }
    }

    protected void func_145821_a(int p_145821_1_, int p_145821_2_, int p_145821_3_, double p_145821_4_, double p_145821_6_, Block p_145821_8_, int p_145821_9_)
    {
        this.field_70143_R = 0.0F;
        Vec3 vec3 = this.func_70489_a(this.field_70165_t, this.field_70163_u, this.field_70161_v);
        this.field_70163_u = (double)p_145821_2_;
        boolean flag = false;
        boolean flag1 = false;

        if (p_145821_8_ == Blocks.field_150318_D)
        {
            flag = (p_145821_9_ & 8) != 0;
            flag1 = !flag;
        }

        if (((BlockRailBase)p_145821_8_).func_150050_e())
        {
            p_145821_9_ &= 7;
        }

        if (p_145821_9_ >= 2 && p_145821_9_ <= 5)
        {
            this.field_70163_u = (double)(p_145821_2_ + 1);
        }

        if (p_145821_9_ == 2)
        {
            this.field_70159_w -= p_145821_6_;
        }

        if (p_145821_9_ == 3)
        {
            this.field_70159_w += p_145821_6_;
        }

        if (p_145821_9_ == 4)
        {
            this.field_70179_y += p_145821_6_;
        }

        if (p_145821_9_ == 5)
        {
            this.field_70179_y -= p_145821_6_;
        }

        int[][] aint = field_70500_g[p_145821_9_];
        double d2 = (double)(aint[1][0] - aint[0][0]);
        double d3 = (double)(aint[1][2] - aint[0][2]);
        double d4 = Math.sqrt(d2 * d2 + d3 * d3);
        double d5 = this.field_70159_w * d2 + this.field_70179_y * d3;

        if (d5 < 0.0D)
        {
            d2 = -d2;
            d3 = -d3;
        }

        double d6 = Math.sqrt(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);

        if (d6 > 2.0D)
        {
            d6 = 2.0D;
        }

        this.field_70159_w = d6 * d2 / d4;
        this.field_70179_y = d6 * d3 / d4;
        double d7;
        double d8;
        double d9;
        double d10;

        if (this.field_70153_n != null && this.field_70153_n instanceof EntityLivingBase)
        {
            d7 = (double)((EntityLivingBase)this.field_70153_n).field_70701_bs;

            if (d7 > 0.0D)
            {
                d8 = -Math.sin((double)(this.field_70153_n.field_70177_z * (float)Math.PI / 180.0F));
                d9 = Math.cos((double)(this.field_70153_n.field_70177_z * (float)Math.PI / 180.0F));
                d10 = this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y;

                if (d10 < 0.01D)
                {
                    this.field_70159_w += d8 * 0.1D;
                    this.field_70179_y += d9 * 0.1D;
                    flag1 = false;
                }
            }
        }

        if (flag1)
        {
            d7 = Math.sqrt(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);

            if (d7 < 0.03D)
            {
                this.field_70159_w *= 0.0D;
                this.field_70181_x *= 0.0D;
                this.field_70179_y *= 0.0D;
            }
            else
            {
                this.field_70159_w *= 0.5D;
                this.field_70181_x *= 0.0D;
                this.field_70179_y *= 0.5D;
            }
        }

        d7 = 0.0D;
        d8 = (double)p_145821_1_ + 0.5D + (double)aint[0][0] * 0.5D;
        d9 = (double)p_145821_3_ + 0.5D + (double)aint[0][2] * 0.5D;
        d10 = (double)p_145821_1_ + 0.5D + (double)aint[1][0] * 0.5D;
        double d11 = (double)p_145821_3_ + 0.5D + (double)aint[1][2] * 0.5D;
        d2 = d10 - d8;
        d3 = d11 - d9;
        double d12;
        double d13;

        if (d2 == 0.0D)
        {
            this.field_70165_t = (double)p_145821_1_ + 0.5D;
            d7 = this.field_70161_v - (double)p_145821_3_;
        }
        else if (d3 == 0.0D)
        {
            this.field_70161_v = (double)p_145821_3_ + 0.5D;
            d7 = this.field_70165_t - (double)p_145821_1_;
        }
        else
        {
            d12 = this.field_70165_t - d8;
            d13 = this.field_70161_v - d9;
            d7 = (d12 * d2 + d13 * d3) * 2.0D;
        }

        this.field_70165_t = d8 + d2 * d7;
        this.field_70161_v = d9 + d3 * d7;
        this.func_70107_b(this.field_70165_t, this.field_70163_u + (double)this.field_70129_M, this.field_70161_v);
        d12 = this.field_70159_w;
        d13 = this.field_70179_y;

        if (this.field_70153_n != null)
        {
            d12 *= 0.75D;
            d13 *= 0.75D;
        }

        if (d12 < -p_145821_4_)
        {
            d12 = -p_145821_4_;
        }

        if (d12 > p_145821_4_)
        {
            d12 = p_145821_4_;
        }

        if (d13 < -p_145821_4_)
        {
            d13 = -p_145821_4_;
        }

        if (d13 > p_145821_4_)
        {
            d13 = p_145821_4_;
        }

        this.func_70091_d(d12, 0.0D, d13);

        if (aint[0][1] != 0 && MathHelper.func_76128_c(this.field_70165_t) - p_145821_1_ == aint[0][0] && MathHelper.func_76128_c(this.field_70161_v) - p_145821_3_ == aint[0][2])
        {
            this.func_70107_b(this.field_70165_t, this.field_70163_u + (double)aint[0][1], this.field_70161_v);
        }
        else if (aint[1][1] != 0 && MathHelper.func_76128_c(this.field_70165_t) - p_145821_1_ == aint[1][0] && MathHelper.func_76128_c(this.field_70161_v) - p_145821_3_ == aint[1][2])
        {
            this.func_70107_b(this.field_70165_t, this.field_70163_u + (double)aint[1][1], this.field_70161_v);
        }

        this.func_94101_h();
        Vec3 vec31 = this.func_70489_a(this.field_70165_t, this.field_70163_u, this.field_70161_v);

        if (vec31 != null && vec3 != null)
        {
            double d14 = (vec3.field_72448_b - vec31.field_72448_b) * 0.05D;
            d6 = Math.sqrt(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);

            if (d6 > 0.0D)
            {
                this.field_70159_w = this.field_70159_w / d6 * (d6 + d14);
                this.field_70179_y = this.field_70179_y / d6 * (d6 + d14);
            }

            this.func_70107_b(this.field_70165_t, vec31.field_72448_b, this.field_70161_v);
        }

        int j1 = MathHelper.func_76128_c(this.field_70165_t);
        int i1 = MathHelper.func_76128_c(this.field_70161_v);

        if (j1 != p_145821_1_ || i1 != p_145821_3_)
        {
            d6 = Math.sqrt(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
            this.field_70159_w = d6 * (double)(j1 - p_145821_1_);
            this.field_70179_y = d6 * (double)(i1 - p_145821_3_);
        }

        if (flag)
        {
            double d15 = Math.sqrt(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);

            if (d15 > 0.01D)
            {
                double d16 = 0.06D;
                this.field_70159_w += this.field_70159_w / d15 * d16;
                this.field_70179_y += this.field_70179_y / d15 * d16;
            }
            else if (p_145821_9_ == 1)
            {
                if (this.field_70170_p.func_147439_a(p_145821_1_ - 1, p_145821_2_, p_145821_3_).func_149721_r())
                {
                    this.field_70159_w = 0.02D;
                }
                else if (this.field_70170_p.func_147439_a(p_145821_1_ + 1, p_145821_2_, p_145821_3_).func_149721_r())
                {
                    this.field_70159_w = -0.02D;
                }
            }
            else if (p_145821_9_ == 0)
            {
                if (this.field_70170_p.func_147439_a(p_145821_1_, p_145821_2_, p_145821_3_ - 1).func_149721_r())
                {
                    this.field_70179_y = 0.02D;
                }
                else if (this.field_70170_p.func_147439_a(p_145821_1_, p_145821_2_, p_145821_3_ + 1).func_149721_r())
                {
                    this.field_70179_y = -0.02D;
                }
            }
        }
    }

    protected void func_94101_h()
    {
        if (this.field_70153_n != null)
        {
            this.field_70159_w *= 0.996999979019165D;
            this.field_70181_x *= 0.0D;
            this.field_70179_y *= 0.996999979019165D;
        }
        else
        {
            this.field_70159_w *= 0.9599999785423279D;
            this.field_70181_x *= 0.0D;
            this.field_70179_y *= 0.9599999785423279D;
        }
    }

    @SideOnly(Side.CLIENT)
    public Vec3 func_70495_a(double p_70495_1_, double p_70495_3_, double p_70495_5_, double p_70495_7_)
    {
        int i = MathHelper.func_76128_c(p_70495_1_);
        int j = MathHelper.func_76128_c(p_70495_3_);
        int k = MathHelper.func_76128_c(p_70495_5_);

        if (BlockRailBase.func_150049_b_(this.field_70170_p, i, j - 1, k))
        {
            --j;
        }

        Block block = this.field_70170_p.func_147439_a(i, j, k);

        if (!BlockRailBase.func_150051_a(block))
        {
            return null;
        }
        else
        {
            int l = this.field_70170_p.func_72805_g(i, j, k);

            if (((BlockRailBase)block).func_150050_e())
            {
                l &= 7;
            }

            p_70495_3_ = (double)j;

            if (l >= 2 && l <= 5)
            {
                p_70495_3_ = (double)(j + 1);
            }

            int[][] aint = field_70500_g[l];
            double d4 = (double)(aint[1][0] - aint[0][0]);
            double d5 = (double)(aint[1][2] - aint[0][2]);
            double d6 = Math.sqrt(d4 * d4 + d5 * d5);
            d4 /= d6;
            d5 /= d6;
            p_70495_1_ += d4 * p_70495_7_;
            p_70495_5_ += d5 * p_70495_7_;

            if (aint[0][1] != 0 && MathHelper.func_76128_c(p_70495_1_) - i == aint[0][0] && MathHelper.func_76128_c(p_70495_5_) - k == aint[0][2])
            {
                p_70495_3_ += (double)aint[0][1];
            }
            else if (aint[1][1] != 0 && MathHelper.func_76128_c(p_70495_1_) - i == aint[1][0] && MathHelper.func_76128_c(p_70495_5_) - k == aint[1][2])
            {
                p_70495_3_ += (double)aint[1][1];
            }

            return this.func_70489_a(p_70495_1_, p_70495_3_, p_70495_5_);
        }
    }

    public Vec3 func_70489_a(double p_70489_1_, double p_70489_3_, double p_70489_5_)
    {
        int i = MathHelper.func_76128_c(p_70489_1_);
        int j = MathHelper.func_76128_c(p_70489_3_);
        int k = MathHelper.func_76128_c(p_70489_5_);

        if (BlockRailBase.func_150049_b_(this.field_70170_p, i, j - 1, k))
        {
            --j;
        }

        Block block = this.field_70170_p.func_147439_a(i, j, k);

        if (BlockRailBase.func_150051_a(block))
        {
            int l = this.field_70170_p.func_72805_g(i, j, k);
            p_70489_3_ = (double)j;

            if (((BlockRailBase)block).func_150050_e())
            {
                l &= 7;
            }

            if (l >= 2 && l <= 5)
            {
                p_70489_3_ = (double)(j + 1);
            }

            int[][] aint = field_70500_g[l];
            double d3 = 0.0D;
            double d4 = (double)i + 0.5D + (double)aint[0][0] * 0.5D;
            double d5 = (double)j + 0.5D + (double)aint[0][1] * 0.5D;
            double d6 = (double)k + 0.5D + (double)aint[0][2] * 0.5D;
            double d7 = (double)i + 0.5D + (double)aint[1][0] * 0.5D;
            double d8 = (double)j + 0.5D + (double)aint[1][1] * 0.5D;
            double d9 = (double)k + 0.5D + (double)aint[1][2] * 0.5D;
            double d10 = d7 - d4;
            double d11 = (d8 - d5) * 2.0D;
            double d12 = d9 - d6;

            if (d10 == 0.0D)
            {
                p_70489_1_ = (double)i + 0.5D;
                d3 = p_70489_5_ - (double)k;
            }
            else if (d12 == 0.0D)
            {
                p_70489_5_ = (double)k + 0.5D;
                d3 = p_70489_1_ - (double)i;
            }
            else
            {
                double d13 = p_70489_1_ - d4;
                double d14 = p_70489_5_ - d6;
                d3 = (d13 * d10 + d14 * d12) * 2.0D;
            }

            p_70489_1_ = d4 + d10 * d3;
            p_70489_3_ = d5 + d11 * d3;
            p_70489_5_ = d6 + d12 * d3;

            if (d11 < 0.0D)
            {
                ++p_70489_3_;
            }

            if (d11 > 0.0D)
            {
                p_70489_3_ += 0.5D;
            }

            return Vec3.func_72443_a(p_70489_1_, p_70489_3_, p_70489_5_);
        }
        else
        {
            return null;
        }
    }

    protected void func_70037_a(NBTTagCompound p_70037_1_)
    {
        if (p_70037_1_.func_74767_n("CustomDisplayTile"))
        {
            this.func_145819_k(p_70037_1_.func_74762_e("DisplayTile"));
            this.func_94092_k(p_70037_1_.func_74762_e("DisplayData"));
            this.func_94086_l(p_70037_1_.func_74762_e("DisplayOffset"));
        }

        if (p_70037_1_.func_150297_b("CustomName", 8) && p_70037_1_.func_74779_i("CustomName").length() > 0)
        {
            this.field_94102_c = p_70037_1_.func_74779_i("CustomName");
        }
    }

    protected void func_70014_b(NBTTagCompound p_70014_1_)
    {
        if (this.func_94100_s())
        {
            p_70014_1_.func_74757_a("CustomDisplayTile", true);
            p_70014_1_.func_74768_a("DisplayTile", this.func_145820_n().func_149688_o() == Material.field_151579_a ? 0 : Block.func_149682_b(this.func_145820_n()));
            p_70014_1_.func_74768_a("DisplayData", this.func_94098_o());
            p_70014_1_.func_74768_a("DisplayOffset", this.func_94099_q());
        }

        if (this.field_94102_c != null && this.field_94102_c.length() > 0)
        {
            p_70014_1_.func_74778_a("CustomName", this.field_94102_c);
        }
    }

    @SideOnly(Side.CLIENT)
    public float func_70053_R()
    {
        return 0.0F;
    }

    public void func_70108_f(Entity p_70108_1_)
    {
        if (!this.field_70170_p.field_72995_K)
        {
            if (p_70108_1_ != this.field_70153_n)
            {
                if (p_70108_1_ instanceof EntityLivingBase && !(p_70108_1_ instanceof EntityPlayer) && !(p_70108_1_ instanceof EntityIronGolem) && this.func_94087_l() == 0 && this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y > 0.01D && this.field_70153_n == null && p_70108_1_.field_70154_o == null)
                {
                    p_70108_1_.func_70078_a(this);
                }

                double d0 = p_70108_1_.field_70165_t - this.field_70165_t;
                double d1 = p_70108_1_.field_70161_v - this.field_70161_v;
                double d2 = d0 * d0 + d1 * d1;

                if (d2 >= 9.999999747378752E-5D)
                {
                    d2 = (double)MathHelper.func_76133_a(d2);
                    d0 /= d2;
                    d1 /= d2;
                    double d3 = 1.0D / d2;

                    if (d3 > 1.0D)
                    {
                        d3 = 1.0D;
                    }

                    d0 *= d3;
                    d1 *= d3;
                    d0 *= 0.10000000149011612D;
                    d1 *= 0.10000000149011612D;
                    d0 *= (double)(1.0F - this.field_70144_Y);
                    d1 *= (double)(1.0F - this.field_70144_Y);
                    d0 *= 0.5D;
                    d1 *= 0.5D;

                    if (p_70108_1_ instanceof EntityMinecart)
                    {
                        double d4 = p_70108_1_.field_70165_t - this.field_70165_t;
                        double d5 = p_70108_1_.field_70161_v - this.field_70161_v;
                        Vec3 vec3 = Vec3.func_72443_a(d4, 0.0D, d5).func_72432_b();
                        Vec3 vec31 = Vec3.func_72443_a((double)MathHelper.func_76134_b(this.field_70177_z * (float)Math.PI / 180.0F), 0.0D, (double)MathHelper.func_76126_a(this.field_70177_z * (float)Math.PI / 180.0F)).func_72432_b();
                        double d6 = Math.abs(vec3.func_72430_b(vec31));

                        if (d6 < 0.800000011920929D)
                        {
                            return;
                        }

                        double d7 = p_70108_1_.field_70159_w + this.field_70159_w;
                        double d8 = p_70108_1_.field_70179_y + this.field_70179_y;

                        if (((EntityMinecart)p_70108_1_).func_94087_l() == 2 && this.func_94087_l() != 2)
                        {
                            this.field_70159_w *= 0.20000000298023224D;
                            this.field_70179_y *= 0.20000000298023224D;
                            this.func_70024_g(p_70108_1_.field_70159_w - d0, 0.0D, p_70108_1_.field_70179_y - d1);
                            p_70108_1_.field_70159_w *= 0.949999988079071D;
                            p_70108_1_.field_70179_y *= 0.949999988079071D;
                        }
                        else if (((EntityMinecart)p_70108_1_).func_94087_l() != 2 && this.func_94087_l() == 2)
                        {
                            p_70108_1_.field_70159_w *= 0.20000000298023224D;
                            p_70108_1_.field_70179_y *= 0.20000000298023224D;
                            p_70108_1_.func_70024_g(this.field_70159_w + d0, 0.0D, this.field_70179_y + d1);
                            this.field_70159_w *= 0.949999988079071D;
                            this.field_70179_y *= 0.949999988079071D;
                        }
                        else
                        {
                            d7 /= 2.0D;
                            d8 /= 2.0D;
                            this.field_70159_w *= 0.20000000298023224D;
                            this.field_70179_y *= 0.20000000298023224D;
                            this.func_70024_g(d7 - d0, 0.0D, d8 - d1);
                            p_70108_1_.field_70159_w *= 0.20000000298023224D;
                            p_70108_1_.field_70179_y *= 0.20000000298023224D;
                            p_70108_1_.func_70024_g(d7 + d0, 0.0D, d8 + d1);
                        }
                    }
                    else
                    {
                        this.func_70024_g(-d0, 0.0D, -d1);
                        p_70108_1_.func_70024_g(d0 / 4.0D, 0.0D, d1 / 4.0D);
                    }
                }
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public void func_70056_a(double p_70056_1_, double p_70056_3_, double p_70056_5_, float p_70056_7_, float p_70056_8_, int p_70056_9_)
    {
        this.field_70511_i = p_70056_1_;
        this.field_70509_j = p_70056_3_;
        this.field_70514_an = p_70056_5_;
        this.field_70512_ao = (double)p_70056_7_;
        this.field_70513_ap = (double)p_70056_8_;
        this.field_70510_h = p_70056_9_ + 2;
        this.field_70159_w = this.field_70508_aq;
        this.field_70181_x = this.field_70507_ar;
        this.field_70179_y = this.field_70506_as;
    }

    public void func_70492_c(float p_70492_1_)
    {
        this.field_70180_af.func_75692_b(19, Float.valueOf(p_70492_1_));
    }

    @SideOnly(Side.CLIENT)
    public void func_70016_h(double p_70016_1_, double p_70016_3_, double p_70016_5_)
    {
        this.field_70508_aq = this.field_70159_w = p_70016_1_;
        this.field_70507_ar = this.field_70181_x = p_70016_3_;
        this.field_70506_as = this.field_70179_y = p_70016_5_;
    }

    public float func_70491_i()
    {
        return this.field_70180_af.func_111145_d(19);
    }

    public void func_70497_h(int p_70497_1_)
    {
        this.field_70180_af.func_75692_b(17, Integer.valueOf(p_70497_1_));
    }

    public int func_70496_j()
    {
        return this.field_70180_af.func_75679_c(17);
    }

    public void func_70494_i(int p_70494_1_)
    {
        this.field_70180_af.func_75692_b(18, Integer.valueOf(p_70494_1_));
    }

    public int func_70493_k()
    {
        return this.field_70180_af.func_75679_c(18);
    }

    public abstract int func_94087_l();

    public Block func_145820_n()
    {
        if (!this.func_94100_s())
        {
            return this.func_145817_o();
        }
        else
        {
            int i = this.func_70096_w().func_75679_c(20) & 65535;
            return Block.func_149729_e(i);
        }
    }

    public Block func_145817_o()
    {
        return Blocks.field_150350_a;
    }

    public int func_94098_o()
    {
        return !this.func_94100_s() ? this.func_94097_p() : this.func_70096_w().func_75679_c(20) >> 16;
    }

    public int func_94097_p()
    {
        return 0;
    }

    public int func_94099_q()
    {
        return !this.func_94100_s() ? this.func_94085_r() : this.func_70096_w().func_75679_c(21);
    }

    public int func_94085_r()
    {
        return 6;
    }

    public void func_145819_k(int p_145819_1_)
    {
        this.func_70096_w().func_75692_b(20, Integer.valueOf(p_145819_1_ & 65535 | this.func_94098_o() << 16));
        this.func_94096_e(true);
    }

    public void func_94092_k(int p_94092_1_)
    {
        this.func_70096_w().func_75692_b(20, Integer.valueOf(Block.func_149682_b(this.func_145820_n()) & 65535 | p_94092_1_ << 16));
        this.func_94096_e(true);
    }

    public void func_94086_l(int p_94086_1_)
    {
        this.func_70096_w().func_75692_b(21, Integer.valueOf(p_94086_1_));
        this.func_94096_e(true);
    }

    public boolean func_94100_s()
    {
        return this.func_70096_w().func_75683_a(22) == 1;
    }

    public void func_94096_e(boolean p_94096_1_)
    {
        this.func_70096_w().func_75692_b(22, Byte.valueOf((byte)(p_94096_1_ ? 1 : 0)));
    }

    public void func_96094_a(String p_96094_1_)
    {
        this.field_94102_c = p_96094_1_;
    }

    public String func_70005_c_()
    {
        return this.field_94102_c != null ? this.field_94102_c : super.func_70005_c_();
    }

    public boolean func_145818_k_()
    {
        return this.field_94102_c != null;
    }

    public String func_95999_t()
    {
        return this.field_94102_c;
    }
}