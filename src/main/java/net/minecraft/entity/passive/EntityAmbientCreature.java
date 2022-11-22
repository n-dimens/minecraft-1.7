package net.minecraft.entity.passive;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public abstract class EntityAmbientCreature extends EntityLiving implements IAnimals
{
    private static final String __OBFID = "CL_00001636";

    public EntityAmbientCreature(World p_i1679_1_)
    {
        super(p_i1679_1_);
    }

    public boolean func_110164_bC()
    {
        return false;
    }

    protected boolean resultOfImpact(EntityPlayer player)
    {
        return false;
    }
}