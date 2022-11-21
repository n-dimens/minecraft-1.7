package net.minecraft.client.gui.inventory;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.inventory.ContainerHorseInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiScreenHorseInventory extends GuiContainer
{
    private static final ResourceLocation field_147031_u = new ResourceLocation("textures/gui/container/horse.png");
    private IInventory field_147030_v;
    private IInventory field_147029_w;
    private EntityHorse field_147034_x;
    private float field_147033_y;
    private float field_147032_z;
    private static final String __OBFID = "CL_00000760";

    public GuiScreenHorseInventory(IInventory p_i1093_1_, IInventory p_i1093_2_, EntityHorse p_i1093_3_)
    {
        super(new ContainerHorseInventory(p_i1093_1_, p_i1093_2_, p_i1093_3_));
        this.field_147030_v = p_i1093_1_;
        this.field_147029_w = p_i1093_2_;
        this.field_147034_x = p_i1093_3_;
        this.field_146291_p = false;
    }

    protected void func_146979_b(int p_146979_1_, int p_146979_2_)
    {
        this.field_146289_q.func_78276_b(this.field_147029_w.func_145818_k_() ? this.field_147029_w.func_145825_b() : I18n.func_135052_a(this.field_147029_w.func_145825_b(), new Object[0]), 8, 6, 4210752);
        this.field_146289_q.func_78276_b(this.field_147030_v.func_145818_k_() ? this.field_147030_v.func_145825_b() : I18n.func_135052_a(this.field_147030_v.func_145825_b(), new Object[0]), 8, this.field_147000_g - 96 + 2, 4210752);
    }

    protected void func_146976_a(float p_146976_1_, int p_146976_2_, int p_146976_3_)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.field_146297_k.func_110434_K().func_110577_a(field_147031_u);
        int k = (this.field_146294_l - this.field_146999_f) / 2;
        int l = (this.field_146295_m - this.field_147000_g) / 2;
        this.func_73729_b(k, l, 0, 0, this.field_146999_f, this.field_147000_g);

        if (this.field_147034_x.func_110261_ca())
        {
            this.func_73729_b(k + 79, l + 17, 0, this.field_147000_g, 90, 54);
        }

        if (this.field_147034_x.func_110259_cr())
        {
            this.func_73729_b(k + 7, l + 35, 0, this.field_147000_g + 54, 18, 18);
        }

        GuiInventory.func_147046_a(k + 51, l + 60, 17, (float)(k + 51) - this.field_147033_y, (float)(l + 75 - 50) - this.field_147032_z, this.field_147034_x);
    }

    public void func_73863_a(int p_73863_1_, int p_73863_2_, float p_73863_3_)
    {
        this.field_147033_y = (float)p_73863_1_;
        this.field_147032_z = (float)p_73863_2_;
        super.func_73863_a(p_73863_1_, p_73863_2_, p_73863_3_);
    }
}