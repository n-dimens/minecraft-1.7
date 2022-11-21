package net.minecraft.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemFirework extends Item
{
    private static final String __OBFID = "CL_00000031";

    public boolean func_77648_a(ItemStack p_77648_1_, EntityPlayer p_77648_2_, World p_77648_3_, int p_77648_4_, int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_)
    {
        if (!p_77648_3_.field_72995_K)
        {
            EntityFireworkRocket entityfireworkrocket = new EntityFireworkRocket(p_77648_3_, (double)((float)p_77648_4_ + p_77648_8_), (double)((float)p_77648_5_ + p_77648_9_), (double)((float)p_77648_6_ + p_77648_10_), p_77648_1_);
            p_77648_3_.func_72838_d(entityfireworkrocket);

            if (!p_77648_2_.field_71075_bZ.field_75098_d)
            {
                --p_77648_1_.field_77994_a;
            }

            return true;
        }
        else
        {
            return false;
        }
    }

    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_)
    {
        if (p_77624_1_.func_77942_o())
        {
            NBTTagCompound nbttagcompound = p_77624_1_.func_77978_p().func_74775_l("Fireworks");

            if (nbttagcompound != null)
            {
                if (nbttagcompound.func_150297_b("Flight", 99))
                {
                    p_77624_3_.add(StatCollector.func_74838_a("item.fireworks.flight") + " " + nbttagcompound.func_74771_c("Flight"));
                }

                NBTTagList nbttaglist = nbttagcompound.func_150295_c("Explosions", 10);

                if (nbttaglist != null && nbttaglist.func_74745_c() > 0)
                {
                    for (int i = 0; i < nbttaglist.func_74745_c(); ++i)
                    {
                        NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
                        ArrayList arraylist = new ArrayList();
                        ItemFireworkCharge.func_150902_a(nbttagcompound1, arraylist);

                        if (arraylist.size() > 0)
                        {
                            for (int j = 1; j < arraylist.size(); ++j)
                            {
                                arraylist.set(j, "  " + (String)arraylist.get(j));
                            }

                            p_77624_3_.addAll(arraylist);
                        }
                    }
                }
            }
        }
    }
}