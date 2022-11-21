package net.minecraft.command.server;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class CommandSetBlock extends CommandBase
{
    private static final String __OBFID = "CL_00000949";

    public String func_71517_b()
    {
        return "setblock";
    }

    public int func_82362_a()
    {
        return 2;
    }

    public String func_71518_a(ICommandSender p_71518_1_)
    {
        return "commands.setblock.usage";
    }

    public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_)
    {
        if (p_71515_2_.length >= 4)
        {
            int i = p_71515_1_.func_82114_b().field_71574_a;
            int j = p_71515_1_.func_82114_b().field_71572_b;
            int k = p_71515_1_.func_82114_b().field_71573_c;
            i = MathHelper.func_76128_c(func_110666_a(p_71515_1_, (double)i, p_71515_2_[0]));
            j = MathHelper.func_76128_c(func_110666_a(p_71515_1_, (double)j, p_71515_2_[1]));
            k = MathHelper.func_76128_c(func_110666_a(p_71515_1_, (double)k, p_71515_2_[2]));
            Block block = CommandBase.func_147180_g(p_71515_1_, p_71515_2_[3]);
            int l = 0;

            if (p_71515_2_.length >= 5)
            {
                l = func_71532_a(p_71515_1_, p_71515_2_[4], 0, 15);
            }

            World world = p_71515_1_.func_130014_f_();

            if (!world.func_72899_e(i, j, k))
            {
                throw new CommandException("commands.setblock.outOfWorld", new Object[0]);
            }
            else
            {
                NBTTagCompound nbttagcompound = new NBTTagCompound();
                boolean flag = false;

                if (p_71515_2_.length >= 7 && block.func_149716_u())
                {
                    String s = func_147178_a(p_71515_1_, p_71515_2_, 6).func_150260_c();

                    try
                    {
                        NBTBase nbtbase = JsonToNBT.func_150315_a(s);

                        if (!(nbtbase instanceof NBTTagCompound))
                        {
                            throw new CommandException("commands.setblock.tagError", new Object[] {"Not a valid tag"});
                        }

                        nbttagcompound = (NBTTagCompound)nbtbase;
                        flag = true;
                    }
                    catch (NBTException nbtexception)
                    {
                        throw new CommandException("commands.setblock.tagError", new Object[] {nbtexception.getMessage()});
                    }
                }

                if (p_71515_2_.length >= 6)
                {
                    if (p_71515_2_[5].equals("destroy"))
                    {
                        world.func_147480_a(i, j, k, true);
                    }
                    else if (p_71515_2_[5].equals("keep") && !world.func_147437_c(i, j, k))
                    {
                        throw new CommandException("commands.setblock.noChange", new Object[0]);
                    }
                }

                if (!world.func_147465_d(i, j, k, block, l, 3))
                {
                    throw new CommandException("commands.setblock.noChange", new Object[0]);
                }
                else
                {
                    if (flag)
                    {
                        TileEntity tileentity = world.func_147438_o(i, j, k);

                        if (tileentity != null)
                        {
                            nbttagcompound.func_74768_a("x", i);
                            nbttagcompound.func_74768_a("y", j);
                            nbttagcompound.func_74768_a("z", k);
                            tileentity.func_145839_a(nbttagcompound);
                        }
                    }

                    func_152373_a(p_71515_1_, this, "commands.setblock.success", new Object[0]);
                }
            }
        }
        else
        {
            throw new WrongUsageException("commands.setblock.usage", new Object[0]);
        }
    }

    public List func_71516_a(ICommandSender p_71516_1_, String[] p_71516_2_)
    {
        return p_71516_2_.length == 4 ? func_71531_a(p_71516_2_, Block.field_149771_c.func_148742_b()) : (p_71516_2_.length == 6 ? func_71530_a(p_71516_2_, new String[] {"replace", "destroy", "keep"}): null);
    }
}