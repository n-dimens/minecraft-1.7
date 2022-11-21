package net.minecraft.entity.boss;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityWither extends EntityMob implements IBossDisplayData, IRangedAttackMob
{
    private float[] field_82220_d = new float[2];
    private float[] field_82221_e = new float[2];
    private float[] field_82217_f = new float[2];
    private float[] field_82218_g = new float[2];
    private int[] field_82223_h = new int[2];
    private int[] field_82224_i = new int[2];
    private int field_82222_j;
    private static final IEntitySelector field_82219_bJ = new IEntitySelector()
    {
        private static final String __OBFID = "CL_00001662";
        public boolean func_82704_a(Entity p_82704_1_)
        {
            return p_82704_1_ instanceof EntityLivingBase && ((EntityLivingBase)p_82704_1_).func_70668_bt() != EnumCreatureAttribute.UNDEAD;
        }
    };
    private static final String __OBFID = "CL_00001661";

    public EntityWither(World p_i1701_1_)
    {
        super(p_i1701_1_);
        this.func_70606_j(this.func_110138_aP());
        this.func_70105_a(0.9F, 4.0F);
        this.field_70178_ae = true;
        this.func_70661_as().func_75495_e(true);
        this.field_70714_bg.func_75776_a(0, new EntityAISwimming(this));
        this.field_70714_bg.func_75776_a(2, new EntityAIArrowAttack(this, 1.0D, 40, 20.0F));
        this.field_70714_bg.func_75776_a(5, new EntityAIWander(this, 1.0D));
        this.field_70714_bg.func_75776_a(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.field_70714_bg.func_75776_a(7, new EntityAILookIdle(this));
        this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, false));
        this.field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, EntityLiving.class, 0, false, false, field_82219_bJ));
        this.field_70728_aV = 50;
    }

    protected void func_70088_a()
    {
        super.func_70088_a();
        this.field_70180_af.func_75682_a(17, new Integer(0));
        this.field_70180_af.func_75682_a(18, new Integer(0));
        this.field_70180_af.func_75682_a(19, new Integer(0));
        this.field_70180_af.func_75682_a(20, new Integer(0));
    }

    public void func_70014_b(NBTTagCompound p_70014_1_)
    {
        super.func_70014_b(p_70014_1_);
        p_70014_1_.func_74768_a("Invul", this.func_82212_n());
    }

    public void func_70037_a(NBTTagCompound p_70037_1_)
    {
        super.func_70037_a(p_70037_1_);
        this.func_82215_s(p_70037_1_.func_74762_e("Invul"));
    }

    @SideOnly(Side.CLIENT)
    public float func_70053_R()
    {
        return this.field_70131_O / 8.0F;
    }

    protected String func_70639_aQ()
    {
        return "mob.wither.idle";
    }

    protected String func_70621_aR()
    {
        return "mob.wither.hurt";
    }

    protected String func_70673_aS()
    {
        return "mob.wither.death";
    }

    public void func_70636_d()
    {
        this.field_70181_x *= 0.6000000238418579D;
        double d1;
        double d3;
        double d5;

        if (!this.field_70170_p.field_72995_K && this.func_82203_t(0) > 0)
        {
            Entity entity = this.field_70170_p.func_73045_a(this.func_82203_t(0));

            if (entity != null)
            {
                if (this.field_70163_u < entity.field_70163_u || !this.func_82205_o() && this.field_70163_u < entity.field_70163_u + 5.0D)
                {
                    if (this.field_70181_x < 0.0D)
                    {
                        this.field_70181_x = 0.0D;
                    }

                    this.field_70181_x += (0.5D - this.field_70181_x) * 0.6000000238418579D;
                }

                double d0 = entity.field_70165_t - this.field_70165_t;
                d1 = entity.field_70161_v - this.field_70161_v;
                d3 = d0 * d0 + d1 * d1;

                if (d3 > 9.0D)
                {
                    d5 = (double)MathHelper.func_76133_a(d3);
                    this.field_70159_w += (d0 / d5 * 0.5D - this.field_70159_w) * 0.6000000238418579D;
                    this.field_70179_y += (d1 / d5 * 0.5D - this.field_70179_y) * 0.6000000238418579D;
                }
            }
        }

        if (this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y > 0.05000000074505806D)
        {
            this.field_70177_z = (float)Math.atan2(this.field_70179_y, this.field_70159_w) * (180F / (float)Math.PI) - 90.0F;
        }

        super.func_70636_d();
        int i;

        for (i = 0; i < 2; ++i)
        {
            this.field_82218_g[i] = this.field_82221_e[i];
            this.field_82217_f[i] = this.field_82220_d[i];
        }

        int j;

        for (i = 0; i < 2; ++i)
        {
            j = this.func_82203_t(i + 1);
            Entity entity1 = null;

            if (j > 0)
            {
                entity1 = this.field_70170_p.func_73045_a(j);
            }

            if (entity1 != null)
            {
                d1 = this.func_82214_u(i + 1);
                d3 = this.func_82208_v(i + 1);
                d5 = this.func_82213_w(i + 1);
                double d6 = entity1.field_70165_t - d1;
                double d7 = entity1.field_70163_u + (double)entity1.func_70047_e() - d3;
                double d8 = entity1.field_70161_v - d5;
                double d9 = (double)MathHelper.func_76133_a(d6 * d6 + d8 * d8);
                float f = (float)(Math.atan2(d8, d6) * 180.0D / Math.PI) - 90.0F;
                float f1 = (float)(-(Math.atan2(d7, d9) * 180.0D / Math.PI));
                this.field_82220_d[i] = this.func_82204_b(this.field_82220_d[i], f1, 40.0F);
                this.field_82221_e[i] = this.func_82204_b(this.field_82221_e[i], f, 10.0F);
            }
            else
            {
                this.field_82221_e[i] = this.func_82204_b(this.field_82221_e[i], this.field_70761_aq, 10.0F);
            }
        }

        boolean flag = this.func_82205_o();

        for (j = 0; j < 3; ++j)
        {
            double d10 = this.func_82214_u(j);
            double d2 = this.func_82208_v(j);
            double d4 = this.func_82213_w(j);
            this.field_70170_p.func_72869_a("smoke", d10 + this.field_70146_Z.nextGaussian() * 0.30000001192092896D, d2 + this.field_70146_Z.nextGaussian() * 0.30000001192092896D, d4 + this.field_70146_Z.nextGaussian() * 0.30000001192092896D, 0.0D, 0.0D, 0.0D);

            if (flag && this.field_70170_p.field_73012_v.nextInt(4) == 0)
            {
                this.field_70170_p.func_72869_a("mobSpell", d10 + this.field_70146_Z.nextGaussian() * 0.30000001192092896D, d2 + this.field_70146_Z.nextGaussian() * 0.30000001192092896D, d4 + this.field_70146_Z.nextGaussian() * 0.30000001192092896D, 0.699999988079071D, 0.699999988079071D, 0.5D);
            }
        }

        if (this.func_82212_n() > 0)
        {
            for (j = 0; j < 3; ++j)
            {
                this.field_70170_p.func_72869_a("mobSpell", this.field_70165_t + this.field_70146_Z.nextGaussian() * 1.0D, this.field_70163_u + (double)(this.field_70146_Z.nextFloat() * 3.3F), this.field_70161_v + this.field_70146_Z.nextGaussian() * 1.0D, 0.699999988079071D, 0.699999988079071D, 0.8999999761581421D);
            }
        }
    }

    protected void func_70619_bc()
    {
        int i;

        if (this.func_82212_n() > 0)
        {
            i = this.func_82212_n() - 1;

            if (i <= 0)
            {
                this.field_70170_p.func_72885_a(this, this.field_70165_t, this.field_70163_u + (double)this.func_70047_e(), this.field_70161_v, 7.0F, false, this.field_70170_p.func_82736_K().getBooleanValue("mobGriefing"));
                this.field_70170_p.func_82739_e(1013, (int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v, 0);
            }

            this.func_82215_s(i);

            if (this.field_70173_aa % 10 == 0)
            {
                this.func_70691_i(10.0F);
            }
        }
        else
        {
            super.func_70619_bc();
            int i1;

            for (i = 1; i < 3; ++i)
            {
                if (this.field_70173_aa >= this.field_82223_h[i - 1])
                {
                    this.field_82223_h[i - 1] = this.field_70173_aa + 10 + this.field_70146_Z.nextInt(10);

                    if (this.field_70170_p.field_73013_u == EnumDifficulty.NORMAL || this.field_70170_p.field_73013_u == EnumDifficulty.HARD)
                    {
                        int k2 = i - 1;
                        int l2 = this.field_82224_i[i - 1];
                        this.field_82224_i[k2] = this.field_82224_i[i - 1] + 1;

                        if (l2 > 15)
                        {
                            float f = 10.0F;
                            float f1 = 5.0F;
                            double d0 = MathHelper.func_82716_a(this.field_70146_Z, this.field_70165_t - (double)f, this.field_70165_t + (double)f);
                            double d1 = MathHelper.func_82716_a(this.field_70146_Z, this.field_70163_u - (double)f1, this.field_70163_u + (double)f1);
                            double d2 = MathHelper.func_82716_a(this.field_70146_Z, this.field_70161_v - (double)f, this.field_70161_v + (double)f);
                            this.func_82209_a(i + 1, d0, d1, d2, true);
                            this.field_82224_i[i - 1] = 0;
                        }
                    }

                    i1 = this.func_82203_t(i);

                    if (i1 > 0)
                    {
                        Entity entity = this.field_70170_p.func_73045_a(i1);

                        if (entity != null && entity.func_70089_S() && this.func_70068_e(entity) <= 900.0D && this.func_70685_l(entity))
                        {
                            this.func_82216_a(i + 1, (EntityLivingBase)entity);
                            this.field_82223_h[i - 1] = this.field_70173_aa + 40 + this.field_70146_Z.nextInt(20);
                            this.field_82224_i[i - 1] = 0;
                        }
                        else
                        {
                            this.func_82211_c(i, 0);
                        }
                    }
                    else
                    {
                        List list = this.field_70170_p.func_82733_a(EntityLivingBase.class, this.field_70121_D.func_72314_b(20.0D, 8.0D, 20.0D), field_82219_bJ);

                        for (int k1 = 0; k1 < 10 && !list.isEmpty(); ++k1)
                        {
                            EntityLivingBase entitylivingbase = (EntityLivingBase)list.get(this.field_70146_Z.nextInt(list.size()));

                            if (entitylivingbase != this && entitylivingbase.func_70089_S() && this.func_70685_l(entitylivingbase))
                            {
                                if (entitylivingbase instanceof EntityPlayer)
                                {
                                    if (!((EntityPlayer)entitylivingbase).field_71075_bZ.field_75102_a)
                                    {
                                        this.func_82211_c(i, entitylivingbase.func_145782_y());
                                    }
                                }
                                else
                                {
                                    this.func_82211_c(i, entitylivingbase.func_145782_y());
                                }

                                break;
                            }

                            list.remove(entitylivingbase);
                        }
                    }
                }
            }

            if (this.func_70638_az() != null)
            {
                this.func_82211_c(0, this.func_70638_az().func_145782_y());
            }
            else
            {
                this.func_82211_c(0, 0);
            }

            if (this.field_82222_j > 0)
            {
                --this.field_82222_j;

                if (this.field_82222_j == 0 && this.field_70170_p.func_82736_K().getBooleanValue("mobGriefing"))
                {
                    i = MathHelper.func_76128_c(this.field_70163_u);
                    i1 = MathHelper.func_76128_c(this.field_70165_t);
                    int j1 = MathHelper.func_76128_c(this.field_70161_v);
                    boolean flag = false;

                    for (int l1 = -1; l1 <= 1; ++l1)
                    {
                        for (int i2 = -1; i2 <= 1; ++i2)
                        {
                            for (int j = 0; j <= 3; ++j)
                            {
                                int j2 = i1 + l1;
                                int k = i + j;
                                int l = j1 + i2;
                                Block block = this.field_70170_p.func_147439_a(j2, k, l);

                                if (block.func_149688_o() != Material.field_151579_a && block != Blocks.BEDROCK && block != Blocks.END_PORTAL && block != Blocks.END_PORTAL_FRAME && block != Blocks.COMMAND_BLOCK)
                                {
                                    flag = this.field_70170_p.func_147480_a(j2, k, l, true) || flag;
                                }
                            }
                        }
                    }

                    if (flag)
                    {
                        this.field_70170_p.func_72889_a((EntityPlayer)null, 1012, (int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v, 0);
                    }
                }
            }

            if (this.field_70173_aa % 20 == 0)
            {
                this.func_70691_i(1.0F);
            }
        }
    }

    public void func_82206_m()
    {
        this.func_82215_s(220);
        this.func_70606_j(this.func_110138_aP() / 3.0F);
    }

    public void func_70110_aj() {}

    public int func_70658_aO()
    {
        return 4;
    }

    private double func_82214_u(int p_82214_1_)
    {
        if (p_82214_1_ <= 0)
        {
            return this.field_70165_t;
        }
        else
        {
            float f = (this.field_70761_aq + (float)(180 * (p_82214_1_ - 1))) / 180.0F * (float)Math.PI;
            float f1 = MathHelper.func_76134_b(f);
            return this.field_70165_t + (double)f1 * 1.3D;
        }
    }

    private double func_82208_v(int p_82208_1_)
    {
        return p_82208_1_ <= 0 ? this.field_70163_u + 3.0D : this.field_70163_u + 2.2D;
    }

    private double func_82213_w(int p_82213_1_)
    {
        if (p_82213_1_ <= 0)
        {
            return this.field_70161_v;
        }
        else
        {
            float f = (this.field_70761_aq + (float)(180 * (p_82213_1_ - 1))) / 180.0F * (float)Math.PI;
            float f1 = MathHelper.func_76126_a(f);
            return this.field_70161_v + (double)f1 * 1.3D;
        }
    }

    private float func_82204_b(float p_82204_1_, float p_82204_2_, float p_82204_3_)
    {
        float f3 = MathHelper.func_76142_g(p_82204_2_ - p_82204_1_);

        if (f3 > p_82204_3_)
        {
            f3 = p_82204_3_;
        }

        if (f3 < -p_82204_3_)
        {
            f3 = -p_82204_3_;
        }

        return p_82204_1_ + f3;
    }

    private void func_82216_a(int p_82216_1_, EntityLivingBase p_82216_2_)
    {
        this.func_82209_a(p_82216_1_, p_82216_2_.field_70165_t, p_82216_2_.field_70163_u + (double)p_82216_2_.func_70047_e() * 0.5D, p_82216_2_.field_70161_v, p_82216_1_ == 0 && this.field_70146_Z.nextFloat() < 0.001F);
    }

    private void func_82209_a(int p_82209_1_, double p_82209_2_, double p_82209_4_, double p_82209_6_, boolean p_82209_8_)
    {
        this.field_70170_p.func_72889_a((EntityPlayer)null, 1014, (int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v, 0);
        double d3 = this.func_82214_u(p_82209_1_);
        double d4 = this.func_82208_v(p_82209_1_);
        double d5 = this.func_82213_w(p_82209_1_);
        double d6 = p_82209_2_ - d3;
        double d7 = p_82209_4_ - d4;
        double d8 = p_82209_6_ - d5;
        EntityWitherSkull entitywitherskull = new EntityWitherSkull(this.field_70170_p, this, d6, d7, d8);

        if (p_82209_8_)
        {
            entitywitherskull.func_82343_e(true);
        }

        entitywitherskull.field_70163_u = d4;
        entitywitherskull.field_70165_t = d3;
        entitywitherskull.field_70161_v = d5;
        this.field_70170_p.func_72838_d(entitywitherskull);
    }

    public void func_82196_d(EntityLivingBase p_82196_1_, float p_82196_2_)
    {
        this.func_82216_a(0, p_82196_1_);
    }

    public boolean func_70097_a(DamageSource p_70097_1_, float p_70097_2_)
    {
        if (this.func_85032_ar())
        {
            return false;
        }
        else if (p_70097_1_ == DamageSource.field_76369_e)
        {
            return false;
        }
        else if (this.func_82212_n() > 0)
        {
            return false;
        }
        else
        {
            Entity entity;

            if (this.func_82205_o())
            {
                entity = p_70097_1_.func_76364_f();

                if (entity instanceof EntityArrow)
                {
                    return false;
                }
            }

            entity = p_70097_1_.func_76346_g();

            if (entity != null && !(entity instanceof EntityPlayer) && entity instanceof EntityLivingBase && ((EntityLivingBase)entity).func_70668_bt() == this.func_70668_bt())
            {
                return false;
            }
            else
            {
                if (this.field_82222_j <= 0)
                {
                    this.field_82222_j = 20;
                }

                for (int i = 0; i < this.field_82224_i.length; ++i)
                {
                    this.field_82224_i[i] += 3;
                }

                return super.func_70097_a(p_70097_1_, p_70097_2_);
            }
        }
    }

    protected void func_70628_a(boolean p_70628_1_, int p_70628_2_)
    {
        this.func_145779_a(Items.NETHER_STAR, 1);

        if (!this.field_70170_p.field_72995_K)
        {
            Iterator iterator = this.field_70170_p.func_72872_a(EntityPlayer.class, this.field_70121_D.func_72314_b(50.0D, 100.0D, 50.0D)).iterator();

            while (iterator.hasNext())
            {
                EntityPlayer entityplayer = (EntityPlayer)iterator.next();
                entityplayer.func_71029_a(AchievementList.KILL_WITHER);
            }
        }
    }

    protected void func_70623_bb()
    {
        this.field_70708_bq = 0;
    }

    @SideOnly(Side.CLIENT)
    public int func_70070_b(float p_70070_1_)
    {
        return 15728880;
    }

    protected void func_70069_a(float p_70069_1_) {}

    public void func_70690_d(PotionEffect p_70690_1_) {}

    protected boolean func_70650_aV()
    {
        return true;
    }

    protected void func_110147_ax()
    {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(300.0D);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.6000000238418579D);
        this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(40.0D);
    }

    @SideOnly(Side.CLIENT)
    public float func_82207_a(int p_82207_1_)
    {
        return this.field_82221_e[p_82207_1_];
    }

    @SideOnly(Side.CLIENT)
    public float func_82210_r(int p_82210_1_)
    {
        return this.field_82220_d[p_82210_1_];
    }

    public int func_82212_n()
    {
        return this.field_70180_af.func_75679_c(20);
    }

    public void func_82215_s(int p_82215_1_)
    {
        this.field_70180_af.func_75692_b(20, Integer.valueOf(p_82215_1_));
    }

    public int func_82203_t(int p_82203_1_)
    {
        return this.field_70180_af.func_75679_c(17 + p_82203_1_);
    }

    public void func_82211_c(int p_82211_1_, int p_82211_2_)
    {
        this.field_70180_af.func_75692_b(17 + p_82211_1_, Integer.valueOf(p_82211_2_));
    }

    public boolean func_82205_o()
    {
        return this.func_110143_aJ() <= this.func_110138_aP() / 2.0F;
    }

    public EnumCreatureAttribute func_70668_bt()
    {
        return EnumCreatureAttribute.UNDEAD;
    }

    public void func_70078_a(Entity p_70078_1_)
    {
        this.field_70154_o = null;
    }
}