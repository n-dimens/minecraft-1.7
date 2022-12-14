package net.minecraft.client.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.I18n;

@SideOnly(Side.CLIENT)
public class ServerListEntryLanScan implements GuiListExtended.IGuiListEntry
{
    private final Minecraft field_148288_a = Minecraft.func_71410_x();
    private static final String __OBFID = "CL_00000815";

    public void func_148279_a(int p_148279_1_, int p_148279_2_, int p_148279_3_, int p_148279_4_, int p_148279_5_, Tessellator p_148279_6_, int p_148279_7_, int p_148279_8_, boolean p_148279_9_)
    {
        int l1 = p_148279_3_ + p_148279_5_ / 2 - this.field_148288_a.field_71466_p.field_78288_b / 2;
        this.field_148288_a.field_71466_p.func_78276_b(I18n.func_135052_a("lanServer.scanning", new Object[0]), this.field_148288_a.field_71462_r.field_146294_l / 2 - this.field_148288_a.field_71466_p.func_78256_a(I18n.func_135052_a("lanServer.scanning", new Object[0])) / 2, l1, 16777215);
        String s;

        switch ((int)(Minecraft.func_71386_F() / 300L % 4L))
        {
            case 0:
            default:
                s = "O o o";
                break;
            case 1:
            case 3:
                s = "o O o";
                break;
            case 2:
                s = "o o O";
        }

        this.field_148288_a.field_71466_p.func_78276_b(s, this.field_148288_a.field_71462_r.field_146294_l / 2 - this.field_148288_a.field_71466_p.func_78256_a(s) / 2, l1 + this.field_148288_a.field_71466_p.field_78288_b, 8421504);
    }

    public boolean func_148278_a(int p_148278_1_, int p_148278_2_, int p_148278_3_, int p_148278_4_, int p_148278_5_, int p_148278_6_)
    {
        return false;
    }

    public void func_148277_b(int p_148277_1_, int p_148277_2_, int p_148277_3_, int p_148277_4_, int p_148277_5_, int p_148277_6_) {}
}