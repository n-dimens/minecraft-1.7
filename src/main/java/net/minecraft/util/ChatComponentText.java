package net.minecraft.util;

import java.util.Iterator;

public class ChatComponentText extends ChatComponentStyle
{
    private final String message;
    private static final String __OBFID = "CL_00001269";

    public ChatComponentText(String message)
    {
        this.message = message;
    }

    public String getMessage()
    {
        return this.message;
    }

    public String func_150261_e()
    {
        return this.message;
    }

    public ChatComponentText func_150259_f()
    {
        ChatComponentText chatcomponenttext = new ChatComponentText(this.message);
        chatcomponenttext.func_150255_a(this.func_150256_b().func_150232_l());
        Iterator iterator = this.func_150253_a().iterator();

        while (iterator.hasNext())
        {
            IChatComponent ichatcomponent = (IChatComponent)iterator.next();
            chatcomponenttext.func_150257_a(ichatcomponent.func_150259_f());
        }

        return chatcomponenttext;
    }

    public boolean equals(Object chatComponent)
    {
        if (this == chatComponent)
        {
            return true;
        }
        else if (!(chatComponent instanceof ChatComponentText))
        {
            return false;
        }
        else
        {
            ChatComponentText chatcomponenttext = (ChatComponentText)chatComponent;
            return this.message.equals(chatcomponenttext.getMessage()) && super.equals(chatComponent);
        }
    }

    public String toString()
    {
        return "TextComponent{text=\'" + this.message + '\'' + ", siblings=" + this.field_150264_a + ", style=" + this.func_150256_b() + '}';
    }
}