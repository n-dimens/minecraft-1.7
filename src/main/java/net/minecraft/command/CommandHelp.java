package net.minecraft.command;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.event.ClickEvent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;

public class CommandHelp extends CommandBase
{
    private static final String __OBFID = "CL_00000529";

    public String func_71517_b()
    {
        return "help";
    }

    public int func_82362_a()
    {
        return 0;
    }

    public String func_71518_a(ICommandSender p_71518_1_)
    {
        return "commands.help.usage";
    }

    public List func_71514_a()
    {
        return Arrays.asList(new String[] {"?"});
    }

    public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_)
    {
        List list = this.func_71534_d(p_71515_1_);
        byte b0 = 7;
        int i = (list.size() - 1) / b0;
        boolean flag = false;
        int k;

        try
        {
            k = p_71515_2_.length == 0 ? 0 : func_71532_a(p_71515_1_, p_71515_2_[0], 1, i + 1) - 1;
        }
        catch (NumberInvalidException numberinvalidexception)
        {
            Map map = this.func_71535_c();
            ICommand icommand = (ICommand)map.get(p_71515_2_[0]);

            if (icommand != null)
            {
                throw new WrongUsageException(icommand.func_71518_a(p_71515_1_), new Object[0]);
            }

            if (MathHelper.func_82715_a(p_71515_2_[0], -1) != -1)
            {
                throw numberinvalidexception;
            }

            throw new CommandNotFoundException();
        }

        int j = Math.min((k + 1) * b0, list.size());
        ChatComponentTranslation chatcomponenttranslation1 = new ChatComponentTranslation("commands.help.header", new Object[] {Integer.valueOf(k + 1), Integer.valueOf(i + 1)});
        chatcomponenttranslation1.func_150256_b().func_150238_a(EnumChatFormatting.DARK_GREEN);
        p_71515_1_.func_145747_a(chatcomponenttranslation1);

        for (int l = k * b0; l < j; ++l)
        {
            ICommand icommand1 = (ICommand)list.get(l);
            ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation(icommand1.func_71518_a(p_71515_1_), new Object[0]);
            chatcomponenttranslation.func_150256_b().func_150241_a(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/" + icommand1.func_71517_b() + " "));
            p_71515_1_.func_145747_a(chatcomponenttranslation);
        }

        if (k == 0 && p_71515_1_ instanceof EntityPlayer)
        {
            ChatComponentTranslation chatcomponenttranslation2 = new ChatComponentTranslation("commands.help.footer", new Object[0]);
            chatcomponenttranslation2.func_150256_b().func_150238_a(EnumChatFormatting.GREEN);
            p_71515_1_.func_145747_a(chatcomponenttranslation2);
        }
    }

    protected List func_71534_d(ICommandSender p_71534_1_)
    {
        List list = MinecraftServer.func_71276_C().func_71187_D().func_71557_a(p_71534_1_);
        Collections.sort(list);
        return list;
    }

    protected Map func_71535_c()
    {
        return MinecraftServer.func_71276_C().func_71187_D().func_71555_a();
    }
}