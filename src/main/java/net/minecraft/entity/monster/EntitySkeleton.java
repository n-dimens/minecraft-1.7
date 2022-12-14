package net.minecraft.entity.monster;

import java.util.Calendar;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIFleeSun;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIRestrictSun;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldProviderHell;

public class EntitySkeleton extends EntityMob implements IRangedAttackMob
{
    private EntityAIArrowAttack field_85037_d = new EntityAIArrowAttack(this, 1.0D, 20, 60, 15.0F);
    private EntityAIAttackOnCollide field_85038_e = new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.2D, false);
    private static final String __OBFID = "CL_00001697";

    public EntitySkeleton(World p_i1741_1_)
    {
        super(p_i1741_1_);
        this.aiTasks.func_75776_a(1, new EntityAISwimming(this));
        this.aiTasks.func_75776_a(2, new EntityAIRestrictSun(this));
        this.aiTasks.func_75776_a(3, new EntityAIFleeSun(this, 1.0D));
        this.aiTasks.func_75776_a(5, new EntityAIWander(this, 1.0D));
        this.aiTasks.func_75776_a(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.aiTasks.func_75776_a(6, new EntityAILookIdle(this));
        this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, false));
        this.field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));

        if (p_i1741_1_ != null && !p_i1741_1_.field_72995_K)
        {
            this.func_85036_m();
        }
    }

    protected void func_110147_ax()
    {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.25D);
    }

    protected void func_70088_a()
    {
        super.func_70088_a();
        this.field_70180_af.func_75682_a(13, new Byte((byte)0));
    }

    public boolean func_70650_aV()
    {
        return true;
    }

    protected String func_70639_aQ()
    {
        return "mob.skeleton.say";
    }

    protected String func_70621_aR()
    {
        return "mob.skeleton.hurt";
    }

    protected String func_70673_aS()
    {
        return "mob.skeleton.death";
    }

    protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_)
    {
        this.func_85030_a("mob.skeleton.step", 0.15F, 1.0F);
    }

    public boolean func_70652_k(Entity p_70652_1_)
    {
        if (super.func_70652_k(p_70652_1_))
        {
            if (this.func_82202_m() == 1 && p_70652_1_ instanceof EntityLivingBase)
            {
                ((EntityLivingBase)p_70652_1_).func_70690_d(new PotionEffect(Potion.field_82731_v.field_76415_H, 200));
            }

            return true;
        }
        else
        {
            return false;
        }
    }

    public EnumCreatureAttribute func_70668_bt()
    {
        return EnumCreatureAttribute.UNDEAD;
    }

    public void func_70636_d()
    {
        if (this.world.func_72935_r() && !this.world.field_72995_K)
        {
            float f = this.func_70013_c(1.0F);

            if (f > 0.5F && this.randomizer.nextFloat() * 30.0F < (f - 0.4F) * 2.0F && this.world.func_72937_j(MathHelper.func_76128_c(this.field_70165_t), MathHelper.func_76128_c(this.field_70163_u), MathHelper.func_76128_c(this.field_70161_v)))
            {
                boolean flag = true;
                ItemStack itemstack = this.func_71124_b(4);

                if (itemstack != null)
                {
                    if (itemstack.func_77984_f())
                    {
                        itemstack.func_77964_b(itemstack.func_77952_i() + this.randomizer.nextInt(2));

                        if (itemstack.func_77952_i() >= itemstack.getDurability())
                        {
                            this.func_70669_a(itemstack);
                            this.func_70062_b(4, (ItemStack)null);
                        }
                    }

                    flag = false;
                }

                if (flag)
                {
                    this.func_70015_d(8);
                }
            }
        }

        if (this.world.field_72995_K && this.func_82202_m() == 1)
        {
            this.func_70105_a(0.72F, 2.34F);
        }

        super.func_70636_d();
    }

    public void func_70098_U()
    {
        super.func_70098_U();

        if (this.field_70154_o instanceof EntityCreature)
        {
            EntityCreature entitycreature = (EntityCreature)this.field_70154_o;
            this.field_70761_aq = entitycreature.field_70761_aq;
        }
    }

    public void func_70645_a(DamageSource p_70645_1_)
    {
        super.func_70645_a(p_70645_1_);

        if (p_70645_1_.func_76364_f() instanceof EntityArrow && p_70645_1_.func_76346_g() instanceof EntityPlayer)
        {
            EntityPlayer entityplayer = (EntityPlayer)p_70645_1_.func_76346_g();
            double d0 = entityplayer.field_70165_t - this.field_70165_t;
            double d1 = entityplayer.field_70161_v - this.field_70161_v;

            if (d0 * d0 + d1 * d1 >= 2500.0D)
            {
                entityplayer.func_71029_a(AchievementList.SNIPE_SKELETON);
            }
        }
    }

    protected Item droppingItem()
    {
        return Items.ARROW;
    }

    protected void func_70628_a(boolean p_70628_1_, int p_70628_2_)
    {
        int j;
        int k;

        if (this.func_82202_m() == 1)
        {
            j = this.randomizer.nextInt(3 + p_70628_2_) - 1;

            for (k = 0; k < j; ++k)
            {
                this.func_145779_a(Items.COAL, 1);
            }
        }
        else
        {
            j = this.randomizer.nextInt(3 + p_70628_2_);

            for (k = 0; k < j; ++k)
            {
                this.func_145779_a(Items.ARROW, 1);
            }
        }

        j = this.randomizer.nextInt(3 + p_70628_2_);

        for (k = 0; k < j; ++k)
        {
            this.func_145779_a(Items.BONE, 1);
        }
    }

    protected void func_70600_l(int p_70600_1_)
    {
        if (this.func_82202_m() == 1)
        {
            this.func_70099_a(new ItemStack(Items.SKULL, 1, 1), 0.0F);
        }
    }

    protected void func_82164_bB()
    {
        super.func_82164_bB();
        this.func_70062_b(0, new ItemStack(Items.BOW));
    }

    public IEntityLivingData func_110161_a(IEntityLivingData p_110161_1_)
    {
        p_110161_1_ = super.func_110161_a(p_110161_1_);

        if (this.world.field_73011_w instanceof WorldProviderHell && this.func_70681_au().nextInt(5) > 0)
        {
            this.aiTasks.func_75776_a(4, this.field_85038_e);
            this.func_82201_a(1);
            this.func_70062_b(0, new ItemStack(Items.STONE_SWORD));
            this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(4.0D);
        }
        else
        {
            this.aiTasks.func_75776_a(4, this.field_85037_d);
            this.func_82164_bB();
            this.func_82162_bC();
        }

        this.func_98053_h(this.randomizer.nextFloat() < 0.55F * this.world.func_147462_b(this.field_70165_t, this.field_70163_u, this.field_70161_v));

        if (this.func_71124_b(4) == null)
        {
            Calendar calendar = this.world.func_83015_S();

            if (calendar.get(2) + 1 == 10 && calendar.get(5) == 31 && this.randomizer.nextFloat() < 0.25F)
            {
                this.func_70062_b(4, new ItemStack(this.randomizer.nextFloat() < 0.1F ? Blocks.LIT_PUMPKIN : Blocks.PUMPKIN));
                this.field_82174_bp[4] = 0.0F;
            }
        }

        return p_110161_1_;
    }

    public void func_85036_m()
    {
        this.aiTasks.func_85156_a(this.field_85038_e);
        this.aiTasks.func_85156_a(this.field_85037_d);
        ItemStack itemstack = this.func_70694_bm();

        if (itemstack != null && itemstack.getBaseItem() == Items.BOW)
        {
            this.aiTasks.func_75776_a(4, this.field_85037_d);
        }
        else
        {
            this.aiTasks.func_75776_a(4, this.field_85038_e);
        }
    }

    public void func_82196_d(EntityLivingBase p_82196_1_, float p_82196_2_)
    {
        EntityArrow entityarrow = new EntityArrow(this.world, this, p_82196_1_, 1.6F, (float)(14 - this.world.field_73013_u.func_151525_a() * 4));
        int i = EnchantmentHelper.func_77506_a(Enchantment.field_77345_t.field_77352_x, this.func_70694_bm());
        int j = EnchantmentHelper.func_77506_a(Enchantment.field_77344_u.field_77352_x, this.func_70694_bm());
        entityarrow.func_70239_b((double)(p_82196_2_ * 2.0F) + this.randomizer.nextGaussian() * 0.25D + (double)((float)this.world.field_73013_u.func_151525_a() * 0.11F));

        if (i > 0)
        {
            entityarrow.func_70239_b(entityarrow.func_70242_d() + (double)i * 0.5D + 0.5D);
        }

        if (j > 0)
        {
            entityarrow.func_70240_a(j);
        }

        if (EnchantmentHelper.func_77506_a(Enchantment.field_77343_v.field_77352_x, this.func_70694_bm()) > 0 || this.func_82202_m() == 1)
        {
            entityarrow.func_70015_d(100);
        }

        this.func_85030_a("random.bow", 1.0F, 1.0F / (this.func_70681_au().nextFloat() * 0.4F + 0.8F));
        this.world.func_72838_d(entityarrow);
    }

    public int func_82202_m()
    {
        return this.field_70180_af.func_75683_a(13);
    }

    public void func_82201_a(int p_82201_1_)
    {
        this.field_70180_af.func_75692_b(13, Byte.valueOf((byte)p_82201_1_));
        this.field_70178_ae = p_82201_1_ == 1;

        if (p_82201_1_ == 1)
        {
            this.func_70105_a(0.72F, 2.34F);
        }
        else
        {
            this.func_70105_a(0.6F, 1.8F);
        }
    }

    public void func_70037_a(NBTTagCompound p_70037_1_)
    {
        super.func_70037_a(p_70037_1_);

        if (p_70037_1_.func_150297_b("SkeletonType", 99))
        {
            byte b0 = p_70037_1_.func_74771_c("SkeletonType");
            this.func_82201_a(b0);
        }

        this.func_85036_m();
    }

    public void func_70014_b(NBTTagCompound p_70014_1_)
    {
        super.func_70014_b(p_70014_1_);
        p_70014_1_.func_74774_a("SkeletonType", (byte)this.func_82202_m());
    }

    public void func_70062_b(int p_70062_1_, ItemStack p_70062_2_)
    {
        super.func_70062_b(p_70062_1_, p_70062_2_);

        if (!this.world.field_72995_K && p_70062_1_ == 0)
        {
            this.func_85036_m();
        }
    }

    public double func_70033_W()
    {
        return super.func_70033_W() - 0.5D;
    }
}