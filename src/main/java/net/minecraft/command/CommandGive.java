package net.minecraft.command;

import java.util.List;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;

public class CommandGive extends CommandBase
{
    private static final String __OBFID = "CL_00000502";

    public String func_71517_b()
    {
        return "give";
    }

    public int func_82362_a()
    {
        return 2;
    }

    public String func_71518_a(ICommandSender p_71518_1_)
    {
        return "commands.give.usage";
    }

    public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_)
    {
        if (p_71515_2_.length < 2)
        {
            throw new WrongUsageException("commands.give.usage", new Object[0]);
        }
        else
        {
            EntityPlayerMP entityplayermp = func_82359_c(p_71515_1_, p_71515_2_[0]);
            Item item = func_147179_f(p_71515_1_, p_71515_2_[1]);
            int i = 1;
            int j = 0;

            if (p_71515_2_.length >= 3)
            {
                i = func_71532_a(p_71515_1_, p_71515_2_[2], 1, 64);
            }

            if (p_71515_2_.length >= 4)
            {
                j = func_71526_a(p_71515_1_, p_71515_2_[3]);
            }

            ItemStack itemstack = new ItemStack(item, i, j);

            if (p_71515_2_.length >= 5)
            {
                String s = func_147178_a(p_71515_1_, p_71515_2_, 4).func_150260_c();

                try
                {
                    NBTBase nbtbase = JsonToNBT.func_150315_a(s);

                    if (!(nbtbase instanceof NBTTagCompound))
                    {
                        func_152373_a(p_71515_1_, this, "commands.give.tagError", new Object[] {"Not a valid tag"});
                        return;
                    }

                    itemstack.func_77982_d((NBTTagCompound)nbtbase);
                }
                catch (NBTException nbtexception)
                {
                    func_152373_a(p_71515_1_, this, "commands.give.tagError", new Object[] {nbtexception.getMessage()});
                    return;
                }
            }

            EntityItem entityitem = entityplayermp.func_71019_a(itemstack, false);
            entityitem.field_145804_b = 0;
            entityitem.func_145797_a(entityplayermp.func_70005_c_());
            func_152373_a(p_71515_1_, this, "commands.give.success", new Object[] {itemstack.func_151000_E(), Integer.valueOf(i), entityplayermp.func_70005_c_()});
        }
    }

    public List func_71516_a(ICommandSender p_71516_1_, String[] p_71516_2_)
    {
        return p_71516_2_.length == 1 ? func_71530_a(p_71516_2_, this.func_71536_c()) : (p_71516_2_.length == 2 ? func_71531_a(p_71516_2_, Item.field_150901_e.func_148742_b()) : null);
    }

    protected String[] func_71536_c()
    {
        return MinecraftServer.func_71276_C().func_71213_z();
    }

    public boolean func_82358_a(String[] p_82358_1_, int p_82358_2_)
    {
        return p_82358_2_ == 0;
    }
}