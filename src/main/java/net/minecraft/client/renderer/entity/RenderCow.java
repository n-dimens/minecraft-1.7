package net.minecraft.client.renderer.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderCow extends RenderLiving
{
    private static final ResourceLocation field_110833_a = new ResourceLocation("textures/entity/cow/cow.png");
    private static final String __OBFID = "CL_00000984";

    public RenderCow(ModelBase p_i1253_1_, float p_i1253_2_)
    {
        super(p_i1253_1_, p_i1253_2_);
    }

    protected ResourceLocation func_110775_a(EntityCow p_110775_1_)
    {
        return field_110833_a;
    }

    protected ResourceLocation func_110775_a(Entity p_110775_1_)
    {
        return this.func_110775_a((EntityCow)p_110775_1_);
    }
}