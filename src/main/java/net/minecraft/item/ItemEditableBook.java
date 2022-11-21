package net.minecraft.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.util.StringUtils;
import net.minecraft.world.World;

public class ItemEditableBook extends Item
{
    private static final String __OBFID = "CL_00000077";

    public ItemEditableBook()
    {
        this.func_77625_d(1);
    }

    public static boolean func_77828_a(NBTTagCompound p_77828_0_)
    {
        if (!ItemWritableBook.func_150930_a(p_77828_0_))
        {
            return false;
        }
        else if (!p_77828_0_.func_150297_b("title", 8))
        {
            return false;
        }
        else
        {
            String s = p_77828_0_.func_74779_i("title");
            return s != null && s.length() <= 16 ? p_77828_0_.func_150297_b("author", 8) : false;
        }
    }

    public String func_77653_i(ItemStack p_77653_1_)
    {
        if (p_77653_1_.func_77942_o())
        {
            NBTTagCompound nbttagcompound = p_77653_1_.func_77978_p();
            String s = nbttagcompound.func_74779_i("title");

            if (!StringUtils.func_151246_b(s))
            {
                return s;
            }
        }

        return super.func_77653_i(p_77653_1_);
    }

    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_)
    {
        if (p_77624_1_.func_77942_o())
        {
            NBTTagCompound nbttagcompound = p_77624_1_.func_77978_p();
            String s = nbttagcompound.func_74779_i("author");

            if (!StringUtils.func_151246_b(s))
            {
                p_77624_3_.add(EnumChatFormatting.GRAY + StatCollector.func_74837_a("book.byAuthor", new Object[] {s}));
            }
        }
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

    @SideOnly(Side.CLIENT)
    public boolean func_77636_d(ItemStack p_77636_1_)
    {
        return true;
    }
}