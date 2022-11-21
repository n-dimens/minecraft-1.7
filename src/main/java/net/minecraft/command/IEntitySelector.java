package net.minecraft.command;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public interface IEntitySelector
{
    IEntitySelector field_94557_a = new IEntitySelector()
    {
        private static final String __OBFID = "CL_00001541";
        public boolean func_82704_a(Entity p_82704_1_)
        {
            return p_82704_1_.func_70089_S();
        }
    };
    IEntitySelector field_152785_b = new IEntitySelector()
    {
        private static final String __OBFID = "CL_00001542";
        public boolean func_82704_a(Entity p_82704_1_)
        {
            return p_82704_1_.func_70089_S() && p_82704_1_.field_70153_n == null && p_82704_1_.field_70154_o == null;
        }
    };
    IEntitySelector field_96566_b = new IEntitySelector()
    {
        private static final String __OBFID = "CL_00001867";
        public boolean func_82704_a(Entity p_82704_1_)
        {
            return p_82704_1_ instanceof IInventory && p_82704_1_.func_70089_S();
        }
    };

    boolean func_82704_a(Entity p_82704_1_);

    public static class ArmoredMob implements IEntitySelector
        {
            private final ItemStack field_96567_c;
            private static final String __OBFID = "CL_00001543";

            public ArmoredMob(ItemStack p_i1584_1_)
            {
                this.field_96567_c = p_i1584_1_;
            }

            public boolean func_82704_a(Entity p_82704_1_)
            {
                if (!p_82704_1_.func_70089_S())
                {
                    return false;
                }
                else if (!(p_82704_1_ instanceof EntityLivingBase))
                {
                    return false;
                }
                else
                {
                    EntityLivingBase entitylivingbase = (EntityLivingBase)p_82704_1_;
                    return entitylivingbase.func_71124_b(EntityLiving.func_82159_b(this.field_96567_c)) != null ? false : (entitylivingbase instanceof EntityLiving ? ((EntityLiving)entitylivingbase).func_98052_bS() : entitylivingbase instanceof EntityPlayer);
                }
            }
        }
}