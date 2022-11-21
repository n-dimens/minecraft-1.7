package net.minecraft.network.login;

import net.minecraft.network.INetHandler;
import net.minecraft.network.login.server.S00PacketDisconnect;
import net.minecraft.network.login.server.S01PacketEncryptionRequest;
import net.minecraft.network.login.server.S02PacketLoginSuccess;

public interface INetHandlerLoginClient extends INetHandler
{
    void func_147389_a(S01PacketEncryptionRequest p_147389_1_);

    void func_147390_a(S02PacketLoginSuccess p_147390_1_);

    void func_147388_a(S00PacketDisconnect p_147388_1_);
}