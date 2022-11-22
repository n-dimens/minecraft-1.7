package net.minecraft.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.world.World;

public class ItemCarrotOnAStick extends Item
{
    private static final String __OBFID = "CL_00000001";

    public ItemCarrotOnAStick()
    {
        this.func_77637_a(CreativeTabs.field_78029_e);
        this.setStackMaxSize(1);
        this.setDurability(25);
    }

    @SideOnly(Side.CLIENT)
    public boolean func_77662_d()
    {
        return true;
    }

    @SideOnly(Side.CLIENT)
    public boolean func_77629_n_()
    {
        return true;
    }

    public ItemStack func_77659_a(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_)
    {
        if (p_77659_3_.func_70115_ae() && p_77659_3_.field_70154_o instanceof EntityPig)
        {
            EntityPig entitypig = (EntityPig)p_77659_3_.field_70154_o;

            if (entitypig.func_82183_n().func_82633_h() && p_77659_1_.getDurability() - p_77659_1_.func_77960_j() >= 7)
            {
                entitypig.func_82183_n().func_82632_g();
                p_77659_1_.func_77972_a(7, p_77659_3_);

                if (p_77659_1_.count == 0)
                {
                    ItemStack itemstack1 = new ItemStack(Items.FISHING_ROD);
                    itemstack1.func_77982_d(p_77659_1_.field_77990_d);
                    return itemstack1;
                }
            }
        }

        return p_77659_1_;
    }
}