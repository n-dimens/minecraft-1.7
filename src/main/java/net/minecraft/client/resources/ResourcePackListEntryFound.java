package net.minecraft.client.resources;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiScreenResourcePacks;

@SideOnly(Side.CLIENT)
public class ResourcePackListEntryFound extends ResourcePackListEntry
{
    private final ResourcePackRepository.Entry field_148319_c;
    private static final String __OBFID = "CL_00000823";

    public ResourcePackListEntryFound(GuiScreenResourcePacks p_i45053_1_, ResourcePackRepository.Entry p_i45053_2_)
    {
        super(p_i45053_1_);
        this.field_148319_c = p_i45053_2_;
    }

    protected void func_148313_c()
    {
        this.field_148319_c.func_110518_a(this.field_148317_a.func_110434_K());
    }

    protected String func_148311_a()
    {
        return this.field_148319_c.func_110519_e();
    }

    protected String func_148312_b()
    {
        return this.field_148319_c.func_110515_d();
    }

    public ResourcePackRepository.Entry func_148318_i()
    {
        return this.field_148319_c;
    }
}