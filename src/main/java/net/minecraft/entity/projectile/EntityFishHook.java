package net.minecraft.entity.projectile;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Arrays;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemFishFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.util.WeightedRandom;
import net.minecraft.util.WeightedRandomFishable;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class EntityFishHook extends Entity
{
    public static final List field_146039_d = Arrays.asList(new WeightedRandomFishable[] {(new WeightedRandomFishable(new ItemStack(Items.LEATHER_BOOTS), 10)).func_150709_a(0.9F), new WeightedRandomFishable(new ItemStack(Items.LEATHER), 10), new WeightedRandomFishable(new ItemStack(Items.BONE), 10), new WeightedRandomFishable(new ItemStack(Items.POTION), 10), new WeightedRandomFishable(new ItemStack(Items.STRING), 5), (new WeightedRandomFishable(new ItemStack(Items.FISHING_ROD), 2)).func_150709_a(0.9F), new WeightedRandomFishable(new ItemStack(Items.BOWL), 10), new WeightedRandomFishable(new ItemStack(Items.STICK), 5), new WeightedRandomFishable(new ItemStack(Items.DYE, 10, 0), 1), new WeightedRandomFishable(new ItemStack(Blocks.TRIPWIRE_HOOK), 10), new WeightedRandomFishable(new ItemStack(Items.ROTTEN_FLESH), 10)});
    public static final List field_146041_e = Arrays.asList(new WeightedRandomFishable[] {new WeightedRandomFishable(new ItemStack(Blocks.WATERLILY), 1), new WeightedRandomFishable(new ItemStack(Items.NAME_TAG), 1), new WeightedRandomFishable(new ItemStack(Items.SADDLE), 1), (new WeightedRandomFishable(new ItemStack(Items.BOW), 1)).func_150709_a(0.25F).func_150707_a(), (new WeightedRandomFishable(new ItemStack(Items.FISHING_ROD), 1)).func_150709_a(0.25F).func_150707_a(), (new WeightedRandomFishable(new ItemStack(Items.BOOK), 1)).func_150707_a()});
    public static final List field_146036_f = Arrays.asList(new WeightedRandomFishable[] {new WeightedRandomFishable(new ItemStack(Items.FISH, 1, ItemFishFood.FishType.COD.func_150976_a()), 60), new WeightedRandomFishable(new ItemStack(Items.FISH, 1, ItemFishFood.FishType.SALMON.func_150976_a()), 25), new WeightedRandomFishable(new ItemStack(Items.FISH, 1, ItemFishFood.FishType.CLOWNFISH.func_150976_a()), 2), new WeightedRandomFishable(new ItemStack(Items.FISH, 1, ItemFishFood.FishType.PUFFERFISH.func_150976_a()), 13)});
    private int field_146037_g;
    private int field_146048_h;
    private int field_146050_i;
    private Block field_146046_j;
    private boolean field_146051_au;
    public int field_146044_a;
    public EntityPlayer field_146042_b;
    private int field_146049_av;
    private int field_146047_aw;
    private int field_146045_ax;
    private int field_146040_ay;
    private int field_146038_az;
    private float field_146054_aA;
    public Entity field_146043_c;
    private int field_146055_aB;
    private double field_146056_aC;
    private double field_146057_aD;
    private double field_146058_aE;
    private double field_146059_aF;
    private double field_146060_aG;
    @SideOnly(Side.CLIENT)
    private double field_146061_aH;
    @SideOnly(Side.CLIENT)
    private double field_146052_aI;
    @SideOnly(Side.CLIENT)
    private double field_146053_aJ;
    private static final String __OBFID = "CL_00001663";

    public EntityFishHook(World p_i1764_1_)
    {
        super(p_i1764_1_);
        this.field_146037_g = -1;
        this.field_146048_h = -1;
        this.field_146050_i = -1;
        this.func_70105_a(0.25F, 0.25F);
        this.field_70158_ak = true;
    }

    @SideOnly(Side.CLIENT)
    public EntityFishHook(World p_i1765_1_, double p_i1765_2_, double p_i1765_4_, double p_i1765_6_, EntityPlayer p_i1765_8_)
    {
        this(p_i1765_1_);
        this.func_70107_b(p_i1765_2_, p_i1765_4_, p_i1765_6_);
        this.field_70158_ak = true;
        this.field_146042_b = p_i1765_8_;
        p_i1765_8_.field_71104_cf = this;
    }

    public EntityFishHook(World p_i1766_1_, EntityPlayer p_i1766_2_)
    {
        super(p_i1766_1_);
        this.field_146037_g = -1;
        this.field_146048_h = -1;
        this.field_146050_i = -1;
        this.field_70158_ak = true;
        this.field_146042_b = p_i1766_2_;
        this.field_146042_b.field_71104_cf = this;
        this.func_70105_a(0.25F, 0.25F);
        this.func_70012_b(p_i1766_2_.field_70165_t, p_i1766_2_.field_70163_u + 1.62D - (double)p_i1766_2_.field_70129_M, p_i1766_2_.field_70161_v, p_i1766_2_.field_70177_z, p_i1766_2_.field_70125_A);
        this.field_70165_t -= (double)(MathHelper.func_76134_b(this.field_70177_z / 180.0F * (float)Math.PI) * 0.16F);
        this.field_70163_u -= 0.10000000149011612D;
        this.field_70161_v -= (double)(MathHelper.func_76126_a(this.field_70177_z / 180.0F * (float)Math.PI) * 0.16F);
        this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
        this.field_70129_M = 0.0F;
        float f = 0.4F;
        this.field_70159_w = (double)(-MathHelper.func_76126_a(this.field_70177_z / 180.0F * (float)Math.PI) * MathHelper.func_76134_b(this.field_70125_A / 180.0F * (float)Math.PI) * f);
        this.field_70179_y = (double)(MathHelper.func_76134_b(this.field_70177_z / 180.0F * (float)Math.PI) * MathHelper.func_76134_b(this.field_70125_A / 180.0F * (float)Math.PI) * f);
        this.field_70181_x = (double)(-MathHelper.func_76126_a(this.field_70125_A / 180.0F * (float)Math.PI) * f);
        this.func_146035_c(this.field_70159_w, this.field_70181_x, this.field_70179_y, 1.5F, 1.0F);
    }

    protected void func_70088_a() {}

    public void func_146035_c(double p_146035_1_, double p_146035_3_, double p_146035_5_, float p_146035_7_, float p_146035_8_)
    {
        float f2 = MathHelper.func_76133_a(p_146035_1_ * p_146035_1_ + p_146035_3_ * p_146035_3_ + p_146035_5_ * p_146035_5_);
        p_146035_1_ /= (double)f2;
        p_146035_3_ /= (double)f2;
        p_146035_5_ /= (double)f2;
        p_146035_1_ += this.field_70146_Z.nextGaussian() * 0.007499999832361937D * (double)p_146035_8_;
        p_146035_3_ += this.field_70146_Z.nextGaussian() * 0.007499999832361937D * (double)p_146035_8_;
        p_146035_5_ += this.field_70146_Z.nextGaussian() * 0.007499999832361937D * (double)p_146035_8_;
        p_146035_1_ *= (double)p_146035_7_;
        p_146035_3_ *= (double)p_146035_7_;
        p_146035_5_ *= (double)p_146035_7_;
        this.field_70159_w = p_146035_1_;
        this.field_70181_x = p_146035_3_;
        this.field_70179_y = p_146035_5_;
        float f3 = MathHelper.func_76133_a(p_146035_1_ * p_146035_1_ + p_146035_5_ * p_146035_5_);
        this.field_70126_B = this.field_70177_z = (float)(Math.atan2(p_146035_1_, p_146035_5_) * 180.0D / Math.PI);
        this.field_70127_C = this.field_70125_A = (float)(Math.atan2(p_146035_3_, (double)f3) * 180.0D / Math.PI);
        this.field_146049_av = 0;
    }

    @SideOnly(Side.CLIENT)
    public boolean func_70112_a(double p_70112_1_)
    {
        double d1 = this.field_70121_D.func_72320_b() * 4.0D;
        d1 *= 64.0D;
        return p_70112_1_ < d1 * d1;
    }

    @SideOnly(Side.CLIENT)
    public void func_70056_a(double p_70056_1_, double p_70056_3_, double p_70056_5_, float p_70056_7_, float p_70056_8_, int p_70056_9_)
    {
        this.field_146056_aC = p_70056_1_;
        this.field_146057_aD = p_70056_3_;
        this.field_146058_aE = p_70056_5_;
        this.field_146059_aF = (double)p_70056_7_;
        this.field_146060_aG = (double)p_70056_8_;
        this.field_146055_aB = p_70056_9_;
        this.field_70159_w = this.field_146061_aH;
        this.field_70181_x = this.field_146052_aI;
        this.field_70179_y = this.field_146053_aJ;
    }

    @SideOnly(Side.CLIENT)
    public void func_70016_h(double p_70016_1_, double p_70016_3_, double p_70016_5_)
    {
        this.field_146061_aH = this.field_70159_w = p_70016_1_;
        this.field_146052_aI = this.field_70181_x = p_70016_3_;
        this.field_146053_aJ = this.field_70179_y = p_70016_5_;
    }

    public void func_70071_h_()
    {
        super.func_70071_h_();

        if (this.field_146055_aB > 0)
        {
            double d7 = this.field_70165_t + (this.field_146056_aC - this.field_70165_t) / (double)this.field_146055_aB;
            double d8 = this.field_70163_u + (this.field_146057_aD - this.field_70163_u) / (double)this.field_146055_aB;
            double d9 = this.field_70161_v + (this.field_146058_aE - this.field_70161_v) / (double)this.field_146055_aB;
            double d1 = MathHelper.func_76138_g(this.field_146059_aF - (double)this.field_70177_z);
            this.field_70177_z = (float)((double)this.field_70177_z + d1 / (double)this.field_146055_aB);
            this.field_70125_A = (float)((double)this.field_70125_A + (this.field_146060_aG - (double)this.field_70125_A) / (double)this.field_146055_aB);
            --this.field_146055_aB;
            this.func_70107_b(d7, d8, d9);
            this.func_70101_b(this.field_70177_z, this.field_70125_A);
        }
        else
        {
            if (!this.field_70170_p.field_72995_K)
            {
                ItemStack itemstack = this.field_146042_b.func_71045_bC();

                if (this.field_146042_b.field_70128_L || !this.field_146042_b.func_70089_S() || itemstack == null || itemstack.func_77973_b() != Items.FISHING_ROD || this.func_70068_e(this.field_146042_b) > 1024.0D)
                {
                    this.func_70106_y();
                    this.field_146042_b.field_71104_cf = null;
                    return;
                }

                if (this.field_146043_c != null)
                {
                    if (!this.field_146043_c.field_70128_L)
                    {
                        this.field_70165_t = this.field_146043_c.field_70165_t;
                        this.field_70163_u = this.field_146043_c.field_70121_D.field_72338_b + (double)this.field_146043_c.field_70131_O * 0.8D;
                        this.field_70161_v = this.field_146043_c.field_70161_v;
                        return;
                    }

                    this.field_146043_c = null;
                }
            }

            if (this.field_146044_a > 0)
            {
                --this.field_146044_a;
            }

            if (this.field_146051_au)
            {
                if (this.field_70170_p.func_147439_a(this.field_146037_g, this.field_146048_h, this.field_146050_i) == this.field_146046_j)
                {
                    ++this.field_146049_av;

                    if (this.field_146049_av == 1200)
                    {
                        this.func_70106_y();
                    }

                    return;
                }

                this.field_146051_au = false;
                this.field_70159_w *= (double)(this.field_70146_Z.nextFloat() * 0.2F);
                this.field_70181_x *= (double)(this.field_70146_Z.nextFloat() * 0.2F);
                this.field_70179_y *= (double)(this.field_70146_Z.nextFloat() * 0.2F);
                this.field_146049_av = 0;
                this.field_146047_aw = 0;
            }
            else
            {
                ++this.field_146047_aw;
            }

            Vec3 vec31 = Vec3.func_72443_a(this.field_70165_t, this.field_70163_u, this.field_70161_v);
            Vec3 vec3 = Vec3.func_72443_a(this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y);
            MovingObjectPosition movingobjectposition = this.field_70170_p.func_72933_a(vec31, vec3);
            vec31 = Vec3.func_72443_a(this.field_70165_t, this.field_70163_u, this.field_70161_v);
            vec3 = Vec3.func_72443_a(this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y);

            if (movingobjectposition != null)
            {
                vec3 = Vec3.func_72443_a(movingobjectposition.field_72307_f.field_72450_a, movingobjectposition.field_72307_f.field_72448_b, movingobjectposition.field_72307_f.field_72449_c);
            }

            Entity entity = null;
            List list = this.field_70170_p.func_72839_b(this, this.field_70121_D.func_72321_a(this.field_70159_w, this.field_70181_x, this.field_70179_y).func_72314_b(1.0D, 1.0D, 1.0D));
            double d0 = 0.0D;
            double d2;

            for (int i = 0; i < list.size(); ++i)
            {
                Entity entity1 = (Entity)list.get(i);

                if (entity1.func_70067_L() && (entity1 != this.field_146042_b || this.field_146047_aw >= 5))
                {
                    float f = 0.3F;
                    AxisAlignedBB axisalignedbb = entity1.field_70121_D.func_72314_b((double)f, (double)f, (double)f);
                    MovingObjectPosition movingobjectposition1 = axisalignedbb.func_72327_a(vec31, vec3);

                    if (movingobjectposition1 != null)
                    {
                        d2 = vec31.func_72438_d(movingobjectposition1.field_72307_f);

                        if (d2 < d0 || d0 == 0.0D)
                        {
                            entity = entity1;
                            d0 = d2;
                        }
                    }
                }
            }

            if (entity != null)
            {
                movingobjectposition = new MovingObjectPosition(entity);
            }

            if (movingobjectposition != null)
            {
                if (movingobjectposition.field_72308_g != null)
                {
                    if (movingobjectposition.field_72308_g.func_70097_a(DamageSource.func_76356_a(this, this.field_146042_b), 0.0F))
                    {
                        this.field_146043_c = movingobjectposition.field_72308_g;
                    }
                }
                else
                {
                    this.field_146051_au = true;
                }
            }

            if (!this.field_146051_au)
            {
                this.func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
                float f5 = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
                this.field_70177_z = (float)(Math.atan2(this.field_70159_w, this.field_70179_y) * 180.0D / Math.PI);

                for (this.field_70125_A = (float)(Math.atan2(this.field_70181_x, (double)f5) * 180.0D / Math.PI); this.field_70125_A - this.field_70127_C < -180.0F; this.field_70127_C -= 360.0F)
                {
                    ;
                }

                while (this.field_70125_A - this.field_70127_C >= 180.0F)
                {
                    this.field_70127_C += 360.0F;
                }

                while (this.field_70177_z - this.field_70126_B < -180.0F)
                {
                    this.field_70126_B -= 360.0F;
                }

                while (this.field_70177_z - this.field_70126_B >= 180.0F)
                {
                    this.field_70126_B += 360.0F;
                }

                this.field_70125_A = this.field_70127_C + (this.field_70125_A - this.field_70127_C) * 0.2F;
                this.field_70177_z = this.field_70126_B + (this.field_70177_z - this.field_70126_B) * 0.2F;
                float f6 = 0.92F;

                if (this.field_70122_E || this.field_70123_F)
                {
                    f6 = 0.5F;
                }

                byte b0 = 5;
                double d10 = 0.0D;

                for (int j = 0; j < b0; ++j)
                {
                    double d3 = this.field_70121_D.field_72338_b + (this.field_70121_D.field_72337_e - this.field_70121_D.field_72338_b) * (double)(j + 0) / (double)b0 - 0.125D + 0.125D;
                    double d4 = this.field_70121_D.field_72338_b + (this.field_70121_D.field_72337_e - this.field_70121_D.field_72338_b) * (double)(j + 1) / (double)b0 - 0.125D + 0.125D;
                    AxisAlignedBB axisalignedbb1 = AxisAlignedBB.func_72330_a(this.field_70121_D.field_72340_a, d3, this.field_70121_D.field_72339_c, this.field_70121_D.field_72336_d, d4, this.field_70121_D.field_72334_f);

                    if (this.field_70170_p.func_72830_b(axisalignedbb1, Material.field_151586_h))
                    {
                        d10 += 1.0D / (double)b0;
                    }
                }

                if (!this.field_70170_p.field_72995_K && d10 > 0.0D)
                {
                    WorldServer worldserver = (WorldServer)this.field_70170_p;
                    int k = 1;

                    if (this.field_70146_Z.nextFloat() < 0.25F && this.field_70170_p.func_72951_B(MathHelper.func_76128_c(this.field_70165_t), MathHelper.func_76128_c(this.field_70163_u) + 1, MathHelper.func_76128_c(this.field_70161_v)))
                    {
                        k = 2;
                    }

                    if (this.field_70146_Z.nextFloat() < 0.5F && !this.field_70170_p.func_72937_j(MathHelper.func_76128_c(this.field_70165_t), MathHelper.func_76128_c(this.field_70163_u) + 1, MathHelper.func_76128_c(this.field_70161_v)))
                    {
                        --k;
                    }

                    if (this.field_146045_ax > 0)
                    {
                        --this.field_146045_ax;

                        if (this.field_146045_ax <= 0)
                        {
                            this.field_146040_ay = 0;
                            this.field_146038_az = 0;
                        }
                    }
                    else
                    {
                        float f1;
                        float f2;
                        double d5;
                        double d6;
                        float f7;
                        double d11;

                        if (this.field_146038_az > 0)
                        {
                            this.field_146038_az -= k;

                            if (this.field_146038_az <= 0)
                            {
                                this.field_70181_x -= 0.20000000298023224D;
                                this.func_85030_a("random.splash", 0.25F, 1.0F + (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.4F);
                                f1 = (float)MathHelper.func_76128_c(this.field_70121_D.field_72338_b);
                                worldserver.func_147487_a("bubble", this.field_70165_t, (double)(f1 + 1.0F), this.field_70161_v, (int)(1.0F + this.field_70130_N * 20.0F), (double)this.field_70130_N, 0.0D, (double)this.field_70130_N, 0.20000000298023224D);
                                worldserver.func_147487_a("wake", this.field_70165_t, (double)(f1 + 1.0F), this.field_70161_v, (int)(1.0F + this.field_70130_N * 20.0F), (double)this.field_70130_N, 0.0D, (double)this.field_70130_N, 0.20000000298023224D);
                                this.field_146045_ax = MathHelper.func_76136_a(this.field_70146_Z, 10, 30);
                            }
                            else
                            {
                                this.field_146054_aA = (float)((double)this.field_146054_aA + this.field_70146_Z.nextGaussian() * 4.0D);
                                f1 = this.field_146054_aA * 0.017453292F;
                                f7 = MathHelper.func_76126_a(f1);
                                f2 = MathHelper.func_76134_b(f1);
                                d11 = this.field_70165_t + (double)(f7 * (float)this.field_146038_az * 0.1F);
                                d5 = (double)((float)MathHelper.func_76128_c(this.field_70121_D.field_72338_b) + 1.0F);
                                d6 = this.field_70161_v + (double)(f2 * (float)this.field_146038_az * 0.1F);

                                if (this.field_70146_Z.nextFloat() < 0.15F)
                                {
                                    worldserver.func_147487_a("bubble", d11, d5 - 0.10000000149011612D, d6, 1, (double)f7, 0.1D, (double)f2, 0.0D);
                                }

                                float f3 = f7 * 0.04F;
                                float f4 = f2 * 0.04F;
                                worldserver.func_147487_a("wake", d11, d5, d6, 0, (double)f4, 0.01D, (double)(-f3), 1.0D);
                                worldserver.func_147487_a("wake", d11, d5, d6, 0, (double)(-f4), 0.01D, (double)f3, 1.0D);
                            }
                        }
                        else if (this.field_146040_ay > 0)
                        {
                            this.field_146040_ay -= k;
                            f1 = 0.15F;

                            if (this.field_146040_ay < 20)
                            {
                                f1 = (float)((double)f1 + (double)(20 - this.field_146040_ay) * 0.05D);
                            }
                            else if (this.field_146040_ay < 40)
                            {
                                f1 = (float)((double)f1 + (double)(40 - this.field_146040_ay) * 0.02D);
                            }
                            else if (this.field_146040_ay < 60)
                            {
                                f1 = (float)((double)f1 + (double)(60 - this.field_146040_ay) * 0.01D);
                            }

                            if (this.field_70146_Z.nextFloat() < f1)
                            {
                                f7 = MathHelper.func_151240_a(this.field_70146_Z, 0.0F, 360.0F) * 0.017453292F;
                                f2 = MathHelper.func_151240_a(this.field_70146_Z, 25.0F, 60.0F);
                                d11 = this.field_70165_t + (double)(MathHelper.func_76126_a(f7) * f2 * 0.1F);
                                d5 = (double)((float)MathHelper.func_76128_c(this.field_70121_D.field_72338_b) + 1.0F);
                                d6 = this.field_70161_v + (double)(MathHelper.func_76134_b(f7) * f2 * 0.1F);
                                worldserver.func_147487_a("splash", d11, d5, d6, 2 + this.field_70146_Z.nextInt(2), 0.10000000149011612D, 0.0D, 0.10000000149011612D, 0.0D);
                            }

                            if (this.field_146040_ay <= 0)
                            {
                                this.field_146054_aA = MathHelper.func_151240_a(this.field_70146_Z, 0.0F, 360.0F);
                                this.field_146038_az = MathHelper.func_76136_a(this.field_70146_Z, 20, 80);
                            }
                        }
                        else
                        {
                            this.field_146040_ay = MathHelper.func_76136_a(this.field_70146_Z, 100, 900);
                            this.field_146040_ay -= EnchantmentHelper.func_151387_h(this.field_146042_b) * 20 * 5;
                        }
                    }

                    if (this.field_146045_ax > 0)
                    {
                        this.field_70181_x -= (double)(this.field_70146_Z.nextFloat() * this.field_70146_Z.nextFloat() * this.field_70146_Z.nextFloat()) * 0.2D;
                    }
                }

                d2 = d10 * 2.0D - 1.0D;
                this.field_70181_x += 0.03999999910593033D * d2;

                if (d10 > 0.0D)
                {
                    f6 = (float)((double)f6 * 0.9D);
                    this.field_70181_x *= 0.8D;
                }

                this.field_70159_w *= (double)f6;
                this.field_70181_x *= (double)f6;
                this.field_70179_y *= (double)f6;
                this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
            }
        }
    }

    public void func_70014_b(NBTTagCompound p_70014_1_)
    {
        p_70014_1_.func_74777_a("xTile", (short)this.field_146037_g);
        p_70014_1_.func_74777_a("yTile", (short)this.field_146048_h);
        p_70014_1_.func_74777_a("zTile", (short)this.field_146050_i);
        p_70014_1_.func_74774_a("inTile", (byte)Block.func_149682_b(this.field_146046_j));
        p_70014_1_.func_74774_a("shake", (byte)this.field_146044_a);
        p_70014_1_.func_74774_a("inGround", (byte)(this.field_146051_au ? 1 : 0));
    }

    public void func_70037_a(NBTTagCompound p_70037_1_)
    {
        this.field_146037_g = p_70037_1_.func_74765_d("xTile");
        this.field_146048_h = p_70037_1_.func_74765_d("yTile");
        this.field_146050_i = p_70037_1_.func_74765_d("zTile");
        this.field_146046_j = Block.func_149729_e(p_70037_1_.func_74771_c("inTile") & 255);
        this.field_146044_a = p_70037_1_.func_74771_c("shake") & 255;
        this.field_146051_au = p_70037_1_.func_74771_c("inGround") == 1;
    }

    @SideOnly(Side.CLIENT)
    public float func_70053_R()
    {
        return 0.0F;
    }

    public int func_146034_e()
    {
        if (this.field_70170_p.field_72995_K)
        {
            return 0;
        }
        else
        {
            byte b0 = 0;

            if (this.field_146043_c != null)
            {
                double d0 = this.field_146042_b.field_70165_t - this.field_70165_t;
                double d2 = this.field_146042_b.field_70163_u - this.field_70163_u;
                double d4 = this.field_146042_b.field_70161_v - this.field_70161_v;
                double d6 = (double)MathHelper.func_76133_a(d0 * d0 + d2 * d2 + d4 * d4);
                double d8 = 0.1D;
                this.field_146043_c.field_70159_w += d0 * d8;
                this.field_146043_c.field_70181_x += d2 * d8 + (double)MathHelper.func_76133_a(d6) * 0.08D;
                this.field_146043_c.field_70179_y += d4 * d8;
                b0 = 3;
            }
            else if (this.field_146045_ax > 0)
            {
                EntityItem entityitem = new EntityItem(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, this.func_146033_f());
                double d1 = this.field_146042_b.field_70165_t - this.field_70165_t;
                double d3 = this.field_146042_b.field_70163_u - this.field_70163_u;
                double d5 = this.field_146042_b.field_70161_v - this.field_70161_v;
                double d7 = (double)MathHelper.func_76133_a(d1 * d1 + d3 * d3 + d5 * d5);
                double d9 = 0.1D;
                entityitem.field_70159_w = d1 * d9;
                entityitem.field_70181_x = d3 * d9 + (double)MathHelper.func_76133_a(d7) * 0.08D;
                entityitem.field_70179_y = d5 * d9;
                this.field_70170_p.func_72838_d(entityitem);
                this.field_146042_b.field_70170_p.func_72838_d(new EntityXPOrb(this.field_146042_b.field_70170_p, this.field_146042_b.field_70165_t, this.field_146042_b.field_70163_u + 0.5D, this.field_146042_b.field_70161_v + 0.5D, this.field_70146_Z.nextInt(6) + 1));
                b0 = 1;
            }

            if (this.field_146051_au)
            {
                b0 = 2;
            }

            this.func_70106_y();
            this.field_146042_b.field_71104_cf = null;
            return b0;
        }
    }

    private ItemStack func_146033_f()
    {
        float f = this.field_70170_p.field_73012_v.nextFloat();
        int i = EnchantmentHelper.func_151386_g(this.field_146042_b);
        int j = EnchantmentHelper.func_151387_h(this.field_146042_b);
        float f1 = 0.1F - (float)i * 0.025F - (float)j * 0.01F;
        float f2 = 0.05F + (float)i * 0.01F - (float)j * 0.01F;
        f1 = MathHelper.func_76131_a(f1, 0.0F, 1.0F);
        f2 = MathHelper.func_76131_a(f2, 0.0F, 1.0F);

        if (f < f1)
        {
            this.field_146042_b.func_71064_a(StatList.field_151183_A, 1);
            return ((WeightedRandomFishable)WeightedRandom.func_76271_a(this.field_70146_Z, field_146039_d)).func_150708_a(this.field_70146_Z);
        }
        else
        {
            f -= f1;

            if (f < f2)
            {
                this.field_146042_b.func_71064_a(StatList.field_151184_B, 1);
                return ((WeightedRandomFishable)WeightedRandom.func_76271_a(this.field_70146_Z, field_146041_e)).func_150708_a(this.field_70146_Z);
            }
            else
            {
                float f3 = f - f2;
                this.field_146042_b.func_71064_a(StatList.field_75933_B, 1);
                return ((WeightedRandomFishable)WeightedRandom.func_76271_a(this.field_70146_Z, field_146036_f)).func_150708_a(this.field_70146_Z);
            }
        }
    }

    public void func_70106_y()
    {
        super.func_70106_y();

        if (this.field_146042_b != null)
        {
            this.field_146042_b.field_71104_cf = null;
        }
    }
}