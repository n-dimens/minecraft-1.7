package net.minecraft.command;

import java.util.List;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.server.MinecraftServer;

public class CommandClearInventory extends CommandBase
{
    private static final String __OBFID = "CL_00000218";

    public String func_71517_b()
    {
        return "clear";
    }

    public String func_71518_a(ICommandSender p_71518_1_)
    {
        return "commands.clear.usage";
    }

    public int func_82362_a()
    {
        return 2;
    }

    public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_)
    {
        EntityPlayerMP entityplayermp = p_71515_2_.length == 0 ? func_71521_c(p_71515_1_) : func_82359_c(p_71515_1_, p_71515_2_[0]);
        Item item = p_71515_2_.length >= 2 ? func_147179_f(p_71515_1_, p_71515_2_[1]) : null;
        int i = p_71515_2_.length >= 3 ? func_71528_a(p_71515_1_, p_71515_2_[2], 0) : -1;

        if (p_71515_2_.length >= 2 && item == null)
        {
            throw new CommandException("commands.clear.failure", new Object[] {entityplayermp.func_70005_c_()});
        }
        else
        {
            int j = entityplayermp.inventory.func_146027_a(item, i);
            entityplayermp.field_71069_bz.func_75142_b();

            if (!entityplayermp.capabilities.instabuild)
            {
                entityplayermp.func_71113_k();
            }

            if (j == 0)
            {
                throw new CommandException("commands.clear.failure", new Object[] {entityplayermp.func_70005_c_()});
            }
            else
            {
                func_152373_a(p_71515_1_, this, "commands.clear.success", new Object[] {entityplayermp.func_70005_c_(), Integer.valueOf(j)});
            }
        }
    }

    public List func_71516_a(ICommandSender p_71516_1_, String[] p_71516_2_)
    {
        return p_71516_2_.length == 1 ? func_71530_a(p_71516_2_, this.func_147209_d()) : (p_71516_2_.length == 2 ? func_71531_a(p_71516_2_, Item.REGISTRY.func_148742_b()) : null);
    }

    protected String[] func_147209_d()
    {
        return MinecraftServer.func_71276_C().func_71213_z();
    }

    public boolean func_82358_a(String[] p_82358_1_, int p_82358_2_)
    {
        return p_82358_2_ == 0;
    }
}