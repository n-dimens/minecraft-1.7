package net.minecraft.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;

public class ItemWritableBook extends Item
{
    private static final String __OBFID = "CL_00000076";

    public ItemWritableBook()
    {
        this.func_77625_d(1);
    }

    public ItemStack func_77659_a(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_)
    {
        p_77659_3_.func_71048_c(p_77659_1_);
        return p_77659_1_;
    }

    public boolean func_77651_p()
    {
        return true;
    }

    public static boolean func_150930_a(NBTTagCompound p_150930_0_)
    {
        if (p_150930_0_ == null)
        {
            return false;
        }
        else if (!p_150930_0_.func_150297_b("pages", 9))
        {
            return false;
        }
        else
        {
            NBTTagList nbttaglist = p_150930_0_.func_150295_c("pages", 8);

            for (int i = 0; i < nbttaglist.func_74745_c(); ++i)
            {
                String s = nbttaglist.func_150307_f(i);

                if (s == null)
                {
                    return false;
                }

                if (s.length() > 256)
                {
                    return false;
                }
            }

            return true;
        }
    }
}