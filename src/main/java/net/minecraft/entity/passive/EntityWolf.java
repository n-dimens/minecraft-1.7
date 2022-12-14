package net.minecraft.entity.passive;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockColored;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBeg;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITargetNonTamed;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityWolf extends EntityTameable
{
    private float field_70926_e;
    private float field_70924_f;
    private boolean field_70925_g;
    private boolean field_70928_h;
    private float field_70929_i;
    private float field_70927_j;
    private static final String __OBFID = "CL_00001654";

    public EntityWolf(World p_i1696_1_)
    {
        super(p_i1696_1_);
        this.func_70105_a(0.6F, 0.8F);
        this.func_70661_as().func_75491_a(true);
        this.aiTasks.func_75776_a(1, new EntityAISwimming(this));
        this.aiTasks.func_75776_a(2, this.field_70911_d);
        this.aiTasks.func_75776_a(3, new EntityAILeapAtTarget(this, 0.4F));
        this.aiTasks.func_75776_a(4, new EntityAIAttackOnCollide(this, 1.0D, true));
        this.aiTasks.func_75776_a(5, new EntityAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
        this.aiTasks.func_75776_a(6, new EntityAIMate(this, 1.0D));
        this.aiTasks.func_75776_a(7, new EntityAIWander(this, 1.0D));
        this.aiTasks.func_75776_a(8, new EntityAIBeg(this, 8.0F));
        this.aiTasks.func_75776_a(9, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.aiTasks.func_75776_a(9, new EntityAILookIdle(this));
        this.field_70715_bh.func_75776_a(1, new EntityAIOwnerHurtByTarget(this));
        this.field_70715_bh.func_75776_a(2, new EntityAIOwnerHurtTarget(this));
        this.field_70715_bh.func_75776_a(3, new EntityAIHurtByTarget(this, true));
        this.field_70715_bh.func_75776_a(4, new EntityAITargetNonTamed(this, EntitySheep.class, 200, false));
        this.func_70903_f(false);
    }

    protected void func_110147_ax()
    {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.30000001192092896D);

        if (this.func_70909_n())
        {
            this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(20.0D);
        }
        else
        {
            this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(8.0D);
        }
    }

    public boolean func_70650_aV()
    {
        return true;
    }

    public void func_70624_b(EntityLivingBase p_70624_1_)
    {
        super.func_70624_b(p_70624_1_);

        if (p_70624_1_ == null)
        {
            this.func_70916_h(false);
        }
        else if (!this.func_70909_n())
        {
            this.func_70916_h(true);
        }
    }

    protected void func_70629_bd()
    {
        this.field_70180_af.func_75692_b(18, Float.valueOf(this.func_110143_aJ()));
    }

    protected void func_70088_a()
    {
        super.func_70088_a();
        this.field_70180_af.func_75682_a(18, new Float(this.func_110143_aJ()));
        this.field_70180_af.func_75682_a(19, new Byte((byte)0));
        this.field_70180_af.func_75682_a(20, new Byte((byte)BlockColored.func_150032_b(1)));
    }

    protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_)
    {
        this.func_85030_a("mob.wolf.step", 0.15F, 1.0F);
    }

    public void func_70014_b(NBTTagCompound p_70014_1_)
    {
        super.func_70014_b(p_70014_1_);
        p_70014_1_.func_74757_a("Angry", this.func_70919_bu());
        p_70014_1_.func_74774_a("CollarColor", (byte)this.func_82186_bH());
    }

    public void func_70037_a(NBTTagCompound p_70037_1_)
    {
        super.func_70037_a(p_70037_1_);
        this.func_70916_h(p_70037_1_.func_74767_n("Angry"));

        if (p_70037_1_.func_150297_b("CollarColor", 99))
        {
            this.func_82185_r(p_70037_1_.func_74771_c("CollarColor"));
        }
    }

    protected String func_70639_aQ()
    {
        return this.func_70919_bu() ? "mob.wolf.growl" : (this.randomizer.nextInt(3) == 0 ? (this.func_70909_n() && this.field_70180_af.func_111145_d(18) < 10.0F ? "mob.wolf.whine" : "mob.wolf.panting") : "mob.wolf.bark");
    }

    protected String func_70621_aR()
    {
        return "mob.wolf.hurt";
    }

    protected String func_70673_aS()
    {
        return "mob.wolf.death";
    }

    protected float func_70599_aP()
    {
        return 0.4F;
    }

    protected Item droppingItem()
    {
        return Item.func_150899_d(-1);
    }

    public void func_70636_d()
    {
        super.func_70636_d();

        if (!this.world.field_72995_K && this.field_70925_g && !this.field_70928_h && !this.func_70781_l() && this.field_70122_E)
        {
            this.field_70928_h = true;
            this.field_70929_i = 0.0F;
            this.field_70927_j = 0.0F;
            this.world.func_72960_a(this, (byte)8);
        }
    }

    public void func_70071_h_()
    {
        super.func_70071_h_();
        this.field_70924_f = this.field_70926_e;

        if (this.func_70922_bv())
        {
            this.field_70926_e += (1.0F - this.field_70926_e) * 0.4F;
        }
        else
        {
            this.field_70926_e += (0.0F - this.field_70926_e) * 0.4F;
        }

        if (this.func_70922_bv())
        {
            this.field_70700_bx = 10;
        }

        if (this.func_70026_G())
        {
            this.field_70925_g = true;
            this.field_70928_h = false;
            this.field_70929_i = 0.0F;
            this.field_70927_j = 0.0F;
        }
        else if ((this.field_70925_g || this.field_70928_h) && this.field_70928_h)
        {
            if (this.field_70929_i == 0.0F)
            {
                this.func_85030_a("mob.wolf.shake", this.func_70599_aP(), (this.randomizer.nextFloat() - this.randomizer.nextFloat()) * 0.2F + 1.0F);
            }

            this.field_70927_j = this.field_70929_i;
            this.field_70929_i += 0.05F;

            if (this.field_70927_j >= 2.0F)
            {
                this.field_70925_g = false;
                this.field_70928_h = false;
                this.field_70927_j = 0.0F;
                this.field_70929_i = 0.0F;
            }

            if (this.field_70929_i > 0.4F)
            {
                float f = (float)this.field_70121_D.field_72338_b;
                int i = (int)(MathHelper.func_76126_a((this.field_70929_i - 0.4F) * (float)Math.PI) * 7.0F);

                for (int j = 0; j < i; ++j)
                {
                    float f1 = (this.randomizer.nextFloat() * 2.0F - 1.0F) * this.field_70130_N * 0.5F;
                    float f2 = (this.randomizer.nextFloat() * 2.0F - 1.0F) * this.field_70130_N * 0.5F;
                    this.world.func_72869_a("splash", this.field_70165_t + (double)f1, (double)(f + 0.8F), this.field_70161_v + (double)f2, this.field_70159_w, this.field_70181_x, this.field_70179_y);
                }
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public boolean func_70921_u()
    {
        return this.field_70925_g;
    }

    @SideOnly(Side.CLIENT)
    public float func_70915_j(float p_70915_1_)
    {
        return 0.75F + (this.field_70927_j + (this.field_70929_i - this.field_70927_j) * p_70915_1_) / 2.0F * 0.25F;
    }

    @SideOnly(Side.CLIENT)
    public float func_70923_f(float p_70923_1_, float p_70923_2_)
    {
        float f2 = (this.field_70927_j + (this.field_70929_i - this.field_70927_j) * p_70923_1_ + p_70923_2_) / 1.8F;

        if (f2 < 0.0F)
        {
            f2 = 0.0F;
        }
        else if (f2 > 1.0F)
        {
            f2 = 1.0F;
        }

        return MathHelper.func_76126_a(f2 * (float)Math.PI) * MathHelper.func_76126_a(f2 * (float)Math.PI * 11.0F) * 0.15F * (float)Math.PI;
    }

    public float func_70047_e()
    {
        return this.field_70131_O * 0.8F;
    }

    @SideOnly(Side.CLIENT)
    public float func_70917_k(float p_70917_1_)
    {
        return (this.field_70924_f + (this.field_70926_e - this.field_70924_f) * p_70917_1_) * 0.15F * (float)Math.PI;
    }

    public int func_70646_bf()
    {
        return this.func_70906_o() ? 20 : super.func_70646_bf();
    }

    public boolean func_70097_a(DamageSource p_70097_1_, float p_70097_2_)
    {
        if (this.func_85032_ar())
        {
            return false;
        }
        else
        {
            Entity entity = p_70097_1_.func_76346_g();
            this.field_70911_d.func_75270_a(false);

            if (entity != null && !(entity instanceof EntityPlayer) && !(entity instanceof EntityArrow))
            {
                p_70097_2_ = (p_70097_2_ + 1.0F) / 2.0F;
            }

            return super.func_70097_a(p_70097_1_, p_70097_2_);
        }
    }

    public boolean func_70652_k(Entity p_70652_1_)
    {
        int i = this.func_70909_n() ? 4 : 2;
        return p_70652_1_.func_70097_a(DamageSource.func_76358_a(this), (float)i);
    }

    public void func_70903_f(boolean p_70903_1_)
    {
        super.func_70903_f(p_70903_1_);

        if (p_70903_1_)
        {
            this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(20.0D);
        }
        else
        {
            this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(8.0D);
        }
    }

    public boolean resultOfImpact(EntityPlayer player)
    {
        ItemStack itemstack = player.inventory.getActiveItem();

        if (this.func_70909_n())
        {
            if (itemstack != null)
            {
                if (itemstack.getBaseItem() instanceof ItemFood)
                {
                    ItemFood itemfood = (ItemFood)itemstack.getBaseItem();

                    if (itemfood.func_77845_h() && this.field_70180_af.func_111145_d(18) < 20.0F)
                    {
                        if (!player.capabilities.instabuild)
                        {
                            --itemstack.count;
                        }

                        this.func_70691_i((float)itemfood.func_150905_g(itemstack));

                        if (itemstack.count <= 0)
                        {
                            player.inventory.putItem(player.inventory.activeItemPosition, (ItemStack)null);
                        }

                        return true;
                    }
                }
                else if (itemstack.getBaseItem() == Items.DYE)
                {
                    int i = BlockColored.func_150032_b(itemstack.func_77960_j());

                    if (i != this.func_82186_bH())
                    {
                        this.func_82185_r(i);

                        if (!player.capabilities.instabuild && --itemstack.count <= 0)
                        {
                            player.inventory.putItem(player.inventory.activeItemPosition, (ItemStack)null);
                        }

                        return true;
                    }
                }
            }

            if (this.func_152114_e(player) && !this.world.field_72995_K && !this.func_70877_b(itemstack))
            {
                this.field_70911_d.func_75270_a(!this.func_70906_o());
                this.field_70703_bu = false;
                this.func_70778_a((PathEntity)null);
                this.func_70784_b((Entity)null);
                this.func_70624_b((EntityLivingBase)null);
            }
        }
        else if (itemstack != null && itemstack.getBaseItem() == Items.BONE && !this.func_70919_bu())
        {
            if (!player.capabilities.instabuild)
            {
                --itemstack.count;
            }

            if (itemstack.count <= 0)
            {
                player.inventory.putItem(player.inventory.activeItemPosition, (ItemStack)null);
            }

            if (!this.world.field_72995_K)
            {
                if (this.randomizer.nextInt(3) == 0)
                {
                    this.func_70903_f(true);
                    this.func_70778_a((PathEntity)null);
                    this.func_70624_b((EntityLivingBase)null);
                    this.field_70911_d.func_75270_a(true);
                    this.func_70606_j(20.0F);
                    this.func_152115_b(player.func_110124_au().toString());
                    this.func_70908_e(true);
                    this.world.func_72960_a(this, (byte)7);
                }
                else
                {
                    this.func_70908_e(false);
                    this.world.func_72960_a(this, (byte)6);
                }
            }

            return true;
        }

        return super.resultOfImpact(player);
    }

    @SideOnly(Side.CLIENT)
    public void func_70103_a(byte p_70103_1_)
    {
        if (p_70103_1_ == 8)
        {
            this.field_70928_h = true;
            this.field_70929_i = 0.0F;
            this.field_70927_j = 0.0F;
        }
        else
        {
            super.func_70103_a(p_70103_1_);
        }
    }

    @SideOnly(Side.CLIENT)
    public float func_70920_v()
    {
        return this.func_70919_bu() ? 1.5393804F : (this.func_70909_n() ? (0.55F - (20.0F - this.field_70180_af.func_111145_d(18)) * 0.02F) * (float)Math.PI : ((float)Math.PI / 5F));
    }

    public boolean func_70877_b(ItemStack p_70877_1_)
    {
        return p_70877_1_ == null ? false : (!(p_70877_1_.getBaseItem() instanceof ItemFood) ? false : ((ItemFood)p_70877_1_.getBaseItem()).func_77845_h());
    }

    public int func_70641_bl()
    {
        return 8;
    }

    public boolean func_70919_bu()
    {
        return (this.field_70180_af.func_75683_a(16) & 2) != 0;
    }

    public void func_70916_h(boolean p_70916_1_)
    {
        byte b0 = this.field_70180_af.func_75683_a(16);

        if (p_70916_1_)
        {
            this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)(b0 | 2)));
        }
        else
        {
            this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)(b0 & -3)));
        }
    }

    public int func_82186_bH()
    {
        return this.field_70180_af.func_75683_a(20) & 15;
    }

    public void func_82185_r(int p_82185_1_)
    {
        this.field_70180_af.func_75692_b(20, Byte.valueOf((byte)(p_82185_1_ & 15)));
    }

    public EntityWolf func_90011_a(EntityAgeable p_90011_1_)
    {
        EntityWolf entitywolf = new EntityWolf(this.world);
        String s = this.func_152113_b();

        if (s != null && s.trim().length() > 0)
        {
            entitywolf.func_152115_b(s);
            entitywolf.func_70903_f(true);
        }

        return entitywolf;
    }

    public void func_70918_i(boolean p_70918_1_)
    {
        if (p_70918_1_)
        {
            this.field_70180_af.func_75692_b(19, Byte.valueOf((byte)1));
        }
        else
        {
            this.field_70180_af.func_75692_b(19, Byte.valueOf((byte)0));
        }
    }

    public boolean func_70878_b(EntityAnimal p_70878_1_)
    {
        if (p_70878_1_ == this)
        {
            return false;
        }
        else if (!this.func_70909_n())
        {
            return false;
        }
        else if (!(p_70878_1_ instanceof EntityWolf))
        {
            return false;
        }
        else
        {
            EntityWolf entitywolf = (EntityWolf)p_70878_1_;
            return !entitywolf.func_70909_n() ? false : (entitywolf.func_70906_o() ? false : this.func_70880_s() && entitywolf.func_70880_s());
        }
    }

    public boolean func_70922_bv()
    {
        return this.field_70180_af.func_75683_a(19) == 1;
    }

    protected boolean func_70692_ba()
    {
        return !this.func_70909_n() && this.field_70173_aa > 2400;
    }

    public boolean func_142018_a(EntityLivingBase p_142018_1_, EntityLivingBase p_142018_2_)
    {
        if (!(p_142018_1_ instanceof EntityCreeper) && !(p_142018_1_ instanceof EntityGhast))
        {
            if (p_142018_1_ instanceof EntityWolf)
            {
                EntityWolf entitywolf = (EntityWolf)p_142018_1_;

                if (entitywolf.func_70909_n() && entitywolf.func_70902_q() == p_142018_2_)
                {
                    return false;
                }
            }

            return p_142018_1_ instanceof EntityPlayer && p_142018_2_ instanceof EntityPlayer && !((EntityPlayer)p_142018_2_).func_96122_a((EntityPlayer)p_142018_1_) ? false : !(p_142018_1_ instanceof EntityHorse) || !((EntityHorse)p_142018_1_).func_110248_bS();
        }
        else
        {
            return false;
        }
    }
}