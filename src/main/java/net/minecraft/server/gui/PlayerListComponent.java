package net.minecraft.server.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Vector;
import javax.swing.JList;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

@SideOnly(Side.SERVER)
public class PlayerListComponent extends JList implements IUpdatePlayerListBox
{
    private MinecraftServer field_120015_a;
    private int field_120014_b;
    private static final String __OBFID = "CL_00001795";

    public PlayerListComponent(MinecraftServer p_i2366_1_)
    {
        this.field_120015_a = p_i2366_1_;
        p_i2366_1_.func_82010_a(this);
    }

    public void func_73660_a()
    {
        if (this.field_120014_b++ % 20 == 0)
        {
            Vector vector = new Vector();

            for (int i = 0; i < this.field_120015_a.func_71203_ab().field_72404_b.size(); ++i)
            {
                vector.add(((EntityPlayerMP)this.field_120015_a.func_71203_ab().field_72404_b.get(i)).func_70005_c_());
            }

            this.setListData(vector);
        }
    }
}