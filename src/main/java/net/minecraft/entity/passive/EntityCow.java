package net.minecraft.entity.passive;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityCow extends EntityAnimal
{
    private static final String __OBFID = "CL_00001640";

    public EntityCow(World p_i1683_1_)
    {
        super(p_i1683_1_);
        this.func_70105_a(0.9F, 1.3F);
        this.func_70661_as().func_75491_a(true);
        this.aiTasks.func_75776_a(0, new EntityAISwimming(this));
        this.aiTasks.func_75776_a(1, new EntityAIPanic(this, 2.0D));
        this.aiTasks.func_75776_a(2, new EntityAIMate(this, 1.0D));
        this.aiTasks.func_75776_a(3, new EntityAITempt(this, 1.25D, Items.WHEAT, false));
        this.aiTasks.func_75776_a(4, new EntityAIFollowParent(this, 1.25D));
        this.aiTasks.func_75776_a(5, new EntityAIWander(this, 1.0D));
        this.aiTasks.func_75776_a(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.aiTasks.func_75776_a(7, new EntityAILookIdle(this));
    }

    public boolean func_70650_aV()
    {
        return true;
    }

    protected void func_110147_ax()
    {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(10.0D);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.20000000298023224D);
    }

    protected String func_70639_aQ()
    {
        return "mob.cow.say";
    }

    protected String func_70621_aR()
    {
        return "mob.cow.hurt";
    }

    protected String func_70673_aS()
    {
        return "mob.cow.hurt";
    }

    protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_)
    {
        this.func_85030_a("mob.cow.step", 0.15F, 1.0F);
    }

    protected float func_70599_aP()
    {
        return 0.4F;
    }

    @Override
    protected Item droppingItem()
    {
        return Items.LEATHER;
    }

    @Override
    protected void func_70628_a(boolean p_70628_1_, int p_70628_2_)
    {
        int j = this.randomizer.nextInt(3) + this.randomizer.nextInt(1 + p_70628_2_);
        int k;

        for (k = 0; k < j; ++k)
        {
            this.func_145779_a(Items.LEATHER, 1);
        }

        j = this.randomizer.nextInt(3) + 1 + this.randomizer.nextInt(1 + p_70628_2_);

        for (k = 0; k < j; ++k)
        {
            if (this.func_70027_ad())
            {
                this.func_145779_a(Items.COOKED_BEEF, 1);
            }
            else
            {
                this.func_145779_a(Items.BEEF, 1);
            }
        }
    }

    @Override
    public boolean resultOfImpact(EntityPlayer player)
    {
        ItemStack itemstack = player.inventory.getActiveItem();

        if (itemstack != null && itemstack.getBaseItem() == Items.BUCKET && !player.capabilities.instabuild)
        {
            if (itemstack.count-- == 1)
            {
                player.inventory.putItem(player.inventory.activeItemPosition, new ItemStack(Items.MILK_BUCKET));
            }
            else if (!player.inventory.func_70441_a(new ItemStack(Items.MILK_BUCKET)))
            {
                player.func_71019_a(new ItemStack(Items.MILK_BUCKET, 1, 0), false);
            }

            return true;
        }
        else
        {
            return super.resultOfImpact(player);
        }
    }

    public EntityCow func_90011_a(EntityAgeable p_90011_1_)
    {
        return new EntityCow(this.world);
    }
}