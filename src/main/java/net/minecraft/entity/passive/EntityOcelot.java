package net.minecraft.entity.passive;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIOcelotAttack;
import net.minecraft.entity.ai.EntityAIOcelotSit;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITargetNonTamed;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class EntityOcelot extends EntityTameable
{
    private EntityAITempt field_70914_e;
    private static final String __OBFID = "CL_00001646";

    public EntityOcelot(World p_i1688_1_)
    {
        super(p_i1688_1_);
        this.func_70105_a(0.6F, 0.8F);
        this.func_70661_as().func_75491_a(true);
        this.aiTasks.func_75776_a(1, new EntityAISwimming(this));
        this.aiTasks.func_75776_a(2, this.field_70911_d);
        this.aiTasks.func_75776_a(3, this.field_70914_e = new EntityAITempt(this, 0.6D, Items.FISH, true));
        this.aiTasks.func_75776_a(4, new EntityAIAvoidEntity(this, EntityPlayer.class, 16.0F, 0.8D, 1.33D));
        this.aiTasks.func_75776_a(5, new EntityAIFollowOwner(this, 1.0D, 10.0F, 5.0F));
        this.aiTasks.func_75776_a(6, new EntityAIOcelotSit(this, 1.33D));
        this.aiTasks.func_75776_a(7, new EntityAILeapAtTarget(this, 0.3F));
        this.aiTasks.func_75776_a(8, new EntityAIOcelotAttack(this));
        this.aiTasks.func_75776_a(9, new EntityAIMate(this, 0.8D));
        this.aiTasks.func_75776_a(10, new EntityAIWander(this, 0.8D));
        this.aiTasks.func_75776_a(11, new EntityAIWatchClosest(this, EntityPlayer.class, 10.0F));
        this.field_70715_bh.func_75776_a(1, new EntityAITargetNonTamed(this, EntityChicken.class, 750, false));
    }

    protected void func_70088_a()
    {
        super.func_70088_a();
        this.field_70180_af.func_75682_a(18, Byte.valueOf((byte)0));
    }

    public void func_70629_bd()
    {
        if (this.func_70605_aq().func_75640_a())
        {
            double d0 = this.func_70605_aq().func_75638_b();

            if (d0 == 0.6D)
            {
                this.func_70095_a(true);
                this.func_70031_b(false);
            }
            else if (d0 == 1.33D)
            {
                this.func_70095_a(false);
                this.func_70031_b(true);
            }
            else
            {
                this.func_70095_a(false);
                this.func_70031_b(false);
            }
        }
        else
        {
            this.func_70095_a(false);
            this.func_70031_b(false);
        }
    }

    protected boolean func_70692_ba()
    {
        return !this.func_70909_n() && this.field_70173_aa > 2400;
    }

    public boolean func_70650_aV()
    {
        return true;
    }

    protected void func_110147_ax()
    {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(10.0D);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.30000001192092896D);
    }

    protected void func_70069_a(float p_70069_1_) {}

    public void func_70014_b(NBTTagCompound p_70014_1_)
    {
        super.func_70014_b(p_70014_1_);
        p_70014_1_.func_74768_a("CatType", this.func_70913_u());
    }

    public void func_70037_a(NBTTagCompound p_70037_1_)
    {
        super.func_70037_a(p_70037_1_);
        this.func_70912_b(p_70037_1_.func_74762_e("CatType"));
    }

    protected String func_70639_aQ()
    {
        return this.func_70909_n() ? (this.func_70880_s() ? "mob.cat.purr" : (this.randomizer.nextInt(4) == 0 ? "mob.cat.purreow" : "mob.cat.meow")) : "";
    }

    protected String func_70621_aR()
    {
        return "mob.cat.hitt";
    }

    protected String func_70673_aS()
    {
        return "mob.cat.hitt";
    }

    protected float func_70599_aP()
    {
        return 0.4F;
    }

    protected Item droppingItem()
    {
        return Items.LEATHER;
    }

    public boolean func_70652_k(Entity p_70652_1_)
    {
        return p_70652_1_.func_70097_a(DamageSource.func_76358_a(this), 3.0F);
    }

    public boolean func_70097_a(DamageSource p_70097_1_, float p_70097_2_)
    {
        if (this.func_85032_ar())
        {
            return false;
        }
        else
        {
            this.field_70911_d.func_75270_a(false);
            return super.func_70097_a(p_70097_1_, p_70097_2_);
        }
    }

    protected void func_70628_a(boolean p_70628_1_, int p_70628_2_) {}

    public boolean resultOfImpact(EntityPlayer player)
    {
        ItemStack itemstack = player.inventory.getActiveItem();

        if (this.func_70909_n())
        {
            if (this.func_152114_e(player) && !this.world.field_72995_K && !this.func_70877_b(itemstack))
            {
                this.field_70911_d.func_75270_a(!this.func_70906_o());
            }
        }
        else if (this.field_70914_e.func_75277_f() && itemstack != null && itemstack.getBaseItem() == Items.FISH && player.func_70068_e(this) < 9.0D)
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
                    this.func_70912_b(1 + this.world.field_73012_v.nextInt(3));
                    this.func_152115_b(player.func_110124_au().toString());
                    this.func_70908_e(true);
                    this.field_70911_d.func_75270_a(true);
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

    public EntityOcelot func_90011_a(EntityAgeable p_90011_1_)
    {
        EntityOcelot entityocelot = new EntityOcelot(this.world);

        if (this.func_70909_n())
        {
            entityocelot.func_152115_b(this.func_152113_b());
            entityocelot.func_70903_f(true);
            entityocelot.func_70912_b(this.func_70913_u());
        }

        return entityocelot;
    }

    public boolean func_70877_b(ItemStack p_70877_1_)
    {
        return p_70877_1_ != null && p_70877_1_.getBaseItem() == Items.FISH;
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
        else if (!(p_70878_1_ instanceof EntityOcelot))
        {
            return false;
        }
        else
        {
            EntityOcelot entityocelot = (EntityOcelot)p_70878_1_;
            return !entityocelot.func_70909_n() ? false : this.func_70880_s() && entityocelot.func_70880_s();
        }
    }

    public int func_70913_u()
    {
        return this.field_70180_af.func_75683_a(18);
    }

    public void func_70912_b(int p_70912_1_)
    {
        this.field_70180_af.func_75692_b(18, Byte.valueOf((byte)p_70912_1_));
    }

    public boolean func_70601_bi()
    {
        if (this.world.field_73012_v.nextInt(3) == 0)
        {
            return false;
        }
        else
        {
            if (this.world.func_72855_b(this.field_70121_D) && this.world.func_72945_a(this, this.field_70121_D).isEmpty() && !this.world.func_72953_d(this.field_70121_D))
            {
                int i = MathHelper.func_76128_c(this.field_70165_t);
                int j = MathHelper.func_76128_c(this.field_70121_D.field_72338_b);
                int k = MathHelper.func_76128_c(this.field_70161_v);

                if (j < 63)
                {
                    return false;
                }

                Block block = this.world.func_147439_a(i, j - 1, k);

                if (block == Blocks.GRASS || block.func_149688_o() == Material.field_151584_j)
                {
                    return true;
                }
            }

            return false;
        }
    }

    public String func_70005_c_()
    {
        return this.func_94056_bM() ? this.func_94057_bL() : (this.func_70909_n() ? StatCollector.func_74838_a("entity.Cat.name") : super.func_70005_c_());
    }

    public IEntityLivingData func_110161_a(IEntityLivingData p_110161_1_)
    {
        p_110161_1_ = super.func_110161_a(p_110161_1_);

        if (this.world.field_73012_v.nextInt(7) == 0)
        {
            for (int i = 0; i < 2; ++i)
            {
                EntityOcelot entityocelot = new EntityOcelot(this.world);
                entityocelot.func_70012_b(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70177_z, 0.0F);
                entityocelot.func_70873_a(-24000);
                this.world.func_72838_d(entityocelot);
            }
        }

        return p_110161_1_;
    }
}