package net.minecraft.entity.player;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.nbt.NBTTagCompound;

public class PlayerCapabilities
{
    public boolean invulnerable;
    public boolean flying;
    public boolean mayfly;
    public boolean instabuild;
    public boolean mayBuild = true;
    private float flySpeed = 0.05F;
    private float walkSpeed = 0.1F;
    private static final String __OBFID = "CL_00001708";

    public void func_75091_a(NBTTagCompound p_75091_1_)
    {
        NBTTagCompound nbttagcompound1 = new NBTTagCompound();
        nbttagcompound1.func_74757_a("invulnerable", this.invulnerable);
        nbttagcompound1.func_74757_a("flying", this.flying);
        nbttagcompound1.func_74757_a("mayfly", this.mayfly);
        nbttagcompound1.func_74757_a("instabuild", this.instabuild);
        nbttagcompound1.func_74757_a("mayBuild", this.mayBuild);
        nbttagcompound1.func_74776_a("flySpeed", this.flySpeed);
        nbttagcompound1.func_74776_a("walkSpeed", this.walkSpeed);
        p_75091_1_.func_74782_a("abilities", nbttagcompound1);
    }

    public void func_75095_b(NBTTagCompound p_75095_1_)
    {
        if (p_75095_1_.func_150297_b("abilities", 10))
        {
            NBTTagCompound nbttagcompound1 = p_75095_1_.func_74775_l("abilities");
            this.invulnerable = nbttagcompound1.func_74767_n("invulnerable");
            this.flying = nbttagcompound1.func_74767_n("flying");
            this.mayfly = nbttagcompound1.func_74767_n("mayfly");
            this.instabuild = nbttagcompound1.func_74767_n("instabuild");

            if (nbttagcompound1.func_150297_b("flySpeed", 99))
            {
                this.flySpeed = nbttagcompound1.func_74760_g("flySpeed");
                this.walkSpeed = nbttagcompound1.func_74760_g("walkSpeed");
            }

            if (nbttagcompound1.func_150297_b("mayBuild", 1))
            {
                this.mayBuild = nbttagcompound1.func_74767_n("mayBuild");
            }
        }
    }

    public float func_75093_a()
    {
        return this.flySpeed;
    }

    @SideOnly(Side.CLIENT)
    public void func_75092_a(float p_75092_1_)
    {
        this.flySpeed = p_75092_1_;
    }

    public float func_75094_b()
    {
        return this.walkSpeed;
    }

    @SideOnly(Side.CLIENT)
    public void func_82877_b(float p_82877_1_)
    {
        this.walkSpeed = p_82877_1_;
    }
}