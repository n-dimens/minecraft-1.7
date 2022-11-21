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
        this.field_70714_bg.func_75776_a(0, new EntityAISwimming(this));
        this.field_70714_bg.func_75776_a(1, new EntityAIPanic(this, 2.0D));
        this.field_70714_bg.func_75776_a(2, new EntityAIMate(this, 1.0D));
        this.field_70714_bg.func_75776_a(3, new EntityAITempt(this, 1.25D, Items.WHEAT, false));
        this.field_70714_bg.func_75776_a(4, new EntityAIFollowParent(this, 1.25D));
        this.field_70714_bg.func_75776_a(5, new EntityAIWander(this, 1.0D));
        this.field_70714_bg.func_75776_a(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.field_70714_bg.func_75776_a(7, new EntityAILookIdle(this));
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

    protected Item droppingItem()
    {
        return Items.LEATHER;
    }

    protected void func_70628_a(boolean p_70628_1_, int p_70628_2_)
    {
        int j = this.field_70146_Z.nextInt(3) + this.field_70146_Z.nextInt(1 + p_70628_2_);
        int k;

        for (k = 0; k < j; ++k)
        {
            this.func_145779_a(Items.LEATHER, 1);
        }

        j = this.field_70146_Z.nextInt(3) + 1 + this.field_70146_Z.nextInt(1 + p_70628_2_);

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

    public boolean func_70085_c(EntityPlayer p_70085_1_)
    {
        ItemStack itemstack = p_70085_1_.field_71071_by.func_70448_g();

        if (itemstack != null && itemstack.func_77973_b() == Items.BUCKET && !p_70085_1_.field_71075_bZ.field_75098_d)
        {
            if (itemstack.field_77994_a-- == 1)
            {
                p_70085_1_.field_71071_by.func_70299_a(p_70085_1_.field_71071_by.field_70461_c, new ItemStack(Items.MILK_BUCKET));
            }
            else if (!p_70085_1_.field_71071_by.func_70441_a(new ItemStack(Items.MILK_BUCKET)))
            {
                p_70085_1_.func_71019_a(new ItemStack(Items.MILK_BUCKET, 1, 0), false);
            }

            return true;
        }
        else
        {
            return super.func_70085_c(p_70085_1_);
        }
    }

    public EntityCow func_90011_a(EntityAgeable p_90011_1_)
    {
        return new EntityCow(this.field_70170_p);
    }
}