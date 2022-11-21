package net.minecraft.command;

import java.util.List;

public interface ICommand extends Comparable
{
    String func_71517_b();

    String func_71518_a(ICommandSender p_71518_1_);

    List func_71514_a();

    void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_);

    boolean func_71519_b(ICommandSender p_71519_1_);

    List func_71516_a(ICommandSender p_71516_1_, String[] p_71516_2_);

    boolean func_82358_a(String[] p_82358_1_, int p_82358_2_);
}