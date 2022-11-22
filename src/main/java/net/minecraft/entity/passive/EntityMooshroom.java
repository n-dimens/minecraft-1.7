package net.minecraft.entity.passive;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityMooshroom extends EntityCow
{
    private static final String __OBFID = "CL_00001645";

    public EntityMooshroom(World p_i1687_1_)
    {
        super(p_i1687_1_);
        this.func_70105_a(0.9F, 1.3F);
    }

    public boolean resultOfImpact(EntityPlayer player)
    {
        ItemStack itemstack = player.inventory.getActiveItem();

        if (itemstack != null && itemstack.getBaseItem() == Items.BOWL && this.func_70874_b() >= 0)
        {
            if (itemstack.count == 1)
            {
                player.inventory.putItem(player.inventory.activeItemPosition, new ItemStack(Items.MUSHROOM_STEW));
                return true;
            }

            if (player.inventory.func_70441_a(new ItemStack(Items.MUSHROOM_STEW)) && !player.capabilities.instabuild)
            {
                player.inventory.func_70298_a(player.inventory.activeItemPosition, 1);
                return true;
            }
        }

        if (itemstack != null && itemstack.getBaseItem() == Items.SHEARS && this.func_70874_b() >= 0)
        {
            this.func_70106_y();
            this.world.func_72869_a("largeexplode", this.field_70165_t, this.field_70163_u + (double)(this.field_70131_O / 2.0F), this.field_70161_v, 0.0D, 0.0D, 0.0D);

            if (!this.world.field_72995_K)
            {
                EntityCow entitycow = new EntityCow(this.world);
                entitycow.func_70012_b(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70177_z, this.field_70125_A);
                entitycow.func_70606_j(this.func_110143_aJ());
                entitycow.field_70761_aq = this.field_70761_aq;
                this.world.func_72838_d(entitycow);

                for (int i = 0; i < 5; ++i)
                {
                    this.world.func_72838_d(new EntityItem(this.world, this.field_70165_t, this.field_70163_u + (double)this.field_70131_O, this.field_70161_v, new ItemStack(Blocks.RED_MUSHROOM)));
                }

                itemstack.func_77972_a(1, player);
                this.func_85030_a("mob.sheep.shear", 1.0F, 1.0F);
            }

            return true;
        }
        else
        {
            return super.resultOfImpact(player);
        }
    }

    public EntityMooshroom func_90011_a(EntityAgeable p_90011_1_)
    {
        return new EntityMooshroom(this.world);
    }
}