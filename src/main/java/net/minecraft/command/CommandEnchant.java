package net.minecraft.command;

import java.util.List;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.MinecraftServer;

public class CommandEnchant extends CommandBase
{
    private static final String __OBFID = "CL_00000377";

    public String func_71517_b()
    {
        return "enchant";
    }

    public int func_82362_a()
    {
        return 2;
    }

    public String func_71518_a(ICommandSender p_71518_1_)
    {
        return "commands.enchant.usage";
    }

    public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_)
    {
        if (p_71515_2_.length < 2)
        {
            throw new WrongUsageException("commands.enchant.usage", new Object[0]);
        }
        else
        {
            EntityPlayerMP entityplayermp = func_82359_c(p_71515_1_, p_71515_2_[0]);
            int i = func_71532_a(p_71515_1_, p_71515_2_[1], 0, Enchantment.field_77331_b.length - 1);
            int j = 1;
            ItemStack itemstack = entityplayermp.func_71045_bC();

            if (itemstack == null)
            {
                throw new CommandException("commands.enchant.noItem", new Object[0]);
            }
            else
            {
                Enchantment enchantment = Enchantment.field_77331_b[i];

                if (enchantment == null)
                {
                    throw new NumberInvalidException("commands.enchant.notFound", new Object[] {Integer.valueOf(i)});
                }
                else if (!enchantment.func_92089_a(itemstack))
                {
                    throw new CommandException("commands.enchant.cantEnchant", new Object[0]);
                }
                else
                {
                    if (p_71515_2_.length >= 3)
                    {
                        j = func_71532_a(p_71515_1_, p_71515_2_[2], enchantment.func_77319_d(), enchantment.func_77325_b());
                    }

                    if (itemstack.func_77942_o())
                    {
                        NBTTagList nbttaglist = itemstack.func_77986_q();

                        if (nbttaglist != null)
                        {
                            for (int k = 0; k < nbttaglist.func_74745_c(); ++k)
                            {
                                short short1 = nbttaglist.func_150305_b(k).func_74765_d("id");

                                if (Enchantment.field_77331_b[short1] != null)
                                {
                                    Enchantment enchantment1 = Enchantment.field_77331_b[short1];

                                    if (!enchantment1.func_77326_a(enchantment))
                                    {
                                        throw new CommandException("commands.enchant.cantCombine", new Object[] {enchantment.func_77316_c(j), enchantment1.func_77316_c(nbttaglist.func_150305_b(k).func_74765_d("lvl"))});
                                    }
                                }
                            }
                        }
                    }

                    itemstack.func_77966_a(enchantment, j);
                    func_152373_a(p_71515_1_, this, "commands.enchant.success", new Object[0]);
                }
            }
        }
    }

    public List func_71516_a(ICommandSender p_71516_1_, String[] p_71516_2_)
    {
        return p_71516_2_.length == 1 ? func_71530_a(p_71516_2_, this.func_90022_d()) : null;
    }

    protected String[] func_90022_d()
    {
        return MinecraftServer.func_71276_C().func_71213_z();
    }

    public boolean func_82358_a(String[] p_82358_1_, int p_82358_2_)
    {
        return p_82358_2_ == 0;
    }
}