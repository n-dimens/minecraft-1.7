package net.minecraft.entity.passive;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIControlledByPlayer;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.AchievementList;
import net.minecraft.world.World;

public class EntityPig extends EntityAnimal
{
    private final EntityAIControlledByPlayer field_82184_d;
    private static final String __OBFID = "CL_00001647";

    public EntityPig(World p_i1689_1_)
    {
        super(p_i1689_1_);
        this.func_70105_a(0.9F, 0.9F);
        this.func_70661_as().func_75491_a(true);
        this.aiTasks.func_75776_a(0, new EntityAISwimming(this));
        this.aiTasks.func_75776_a(1, new EntityAIPanic(this, 1.25D));
        this.aiTasks.func_75776_a(2, this.field_82184_d = new EntityAIControlledByPlayer(this, 0.3F));
        this.aiTasks.func_75776_a(3, new EntityAIMate(this, 1.0D));
        this.aiTasks.func_75776_a(4, new EntityAITempt(this, 1.2D, Items.CARROT_ON_A_STICK, false));
        this.aiTasks.func_75776_a(4, new EntityAITempt(this, 1.2D, Items.CARROT, false));
        this.aiTasks.func_75776_a(5, new EntityAIFollowParent(this, 1.1D));
        this.aiTasks.func_75776_a(6, new EntityAIWander(this, 1.0D));
        this.aiTasks.func_75776_a(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.aiTasks.func_75776_a(8, new EntityAILookIdle(this));
    }

    public boolean func_70650_aV()
    {
        return true;
    }

    protected void func_110147_ax()
    {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(10.0D);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.25D);
    }

    protected void func_70619_bc()
    {
        super.func_70619_bc();
    }

    public boolean func_82171_bF()
    {
        ItemStack itemstack = ((EntityPlayer)this.field_70153_n).func_70694_bm();
        return itemstack != null && itemstack.getBaseItem() == Items.CARROT_ON_A_STICK;
    }

    protected void func_70088_a()
    {
        super.func_70088_a();
        this.field_70180_af.func_75682_a(16, Byte.valueOf((byte)0));
    }

    public void func_70014_b(NBTTagCompound p_70014_1_)
    {
        super.func_70014_b(p_70014_1_);
        p_70014_1_.func_74757_a("Saddle", this.func_70901_n());
    }

    public void func_70037_a(NBTTagCompound p_70037_1_)
    {
        super.func_70037_a(p_70037_1_);
        this.func_70900_e(p_70037_1_.func_74767_n("Saddle"));
    }

    protected String func_70639_aQ()
    {
        return "mob.pig.say";
    }

    protected String func_70621_aR()
    {
        return "mob.pig.say";
    }

    protected String func_70673_aS()
    {
        return "mob.pig.death";
    }

    protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_)
    {
        this.func_85030_a("mob.pig.step", 0.15F, 1.0F);
    }

    public boolean resultOfImpact(EntityPlayer player)
    {
        if (super.resultOfImpact(player))
        {
            return true;
        }
        else if (this.func_70901_n() && !this.world.field_72995_K && (this.field_70153_n == null || this.field_70153_n == player))
        {
            player.func_70078_a(this);
            return true;
        }
        else
        {
            return false;
        }
    }

    protected Item droppingItem()
    {
        return this.func_70027_ad() ? Items.COOKED_PORKCHOP : Items.PORKCHOP;
    }

    protected void func_70628_a(boolean p_70628_1_, int p_70628_2_)
    {
        int j = this.randomizer.nextInt(3) + 1 + this.randomizer.nextInt(1 + p_70628_2_);

        for (int k = 0; k < j; ++k)
        {
            if (this.func_70027_ad())
            {
                this.func_145779_a(Items.COOKED_PORKCHOP, 1);
            }
            else
            {
                this.func_145779_a(Items.PORKCHOP, 1);
            }
        }

        if (this.func_70901_n())
        {
            this.func_145779_a(Items.SADDLE, 1);
        }
    }

    public boolean func_70901_n()
    {
        return (this.field_70180_af.func_75683_a(16) & 1) != 0;
    }

    public void func_70900_e(boolean p_70900_1_)
    {
        if (p_70900_1_)
        {
            this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)1));
        }
        else
        {
            this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)0));
        }
    }

    public void func_70077_a(EntityLightningBolt p_70077_1_)
    {
        if (!this.world.field_72995_K)
        {
            EntityPigZombie entitypigzombie = new EntityPigZombie(this.world);
            entitypigzombie.func_70062_b(0, new ItemStack(Items.GOLDEN_SWORD));
            entitypigzombie.func_70012_b(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70177_z, this.field_70125_A);
            this.world.func_72838_d(entitypigzombie);
            this.func_70106_y();
        }
    }

    protected void func_70069_a(float p_70069_1_)
    {
        super.func_70069_a(p_70069_1_);

        if (p_70069_1_ > 5.0F && this.field_70153_n instanceof EntityPlayer)
        {
            ((EntityPlayer)this.field_70153_n).func_71029_a(AchievementList.FLY_PIG);
        }
    }

    public EntityPig func_90011_a(EntityAgeable p_90011_1_)
    {
        return new EntityPig(this.world);
    }

    public boolean func_70877_b(ItemStack p_70877_1_)
    {
        return p_70877_1_ != null && p_70877_1_.getBaseItem() == Items.CARROT;
    }

    public EntityAIControlledByPlayer func_82183_n()
    {
        return this.field_82184_d;
    }
}