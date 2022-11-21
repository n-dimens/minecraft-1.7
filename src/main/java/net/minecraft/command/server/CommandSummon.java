package net.minecraft.command.server;

import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class CommandSummon extends CommandBase
{
    private static final String __OBFID = "CL_00001158";

    public String func_71517_b()
    {
        return "summon";
    }

    public int func_82362_a()
    {
        return 2;
    }

    public String func_71518_a(ICommandSender p_71518_1_)
    {
        return "commands.summon.usage";
    }

    public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_)
    {
        if (p_71515_2_.length < 1)
        {
            throw new WrongUsageException("commands.summon.usage", new Object[0]);
        }
        else
        {
            String s = p_71515_2_[0];
            double d0 = (double)p_71515_1_.func_82114_b().field_71574_a + 0.5D;
            double d1 = (double)p_71515_1_.func_82114_b().field_71572_b;
            double d2 = (double)p_71515_1_.func_82114_b().field_71573_c + 0.5D;

            if (p_71515_2_.length >= 4)
            {
                d0 = func_110666_a(p_71515_1_, d0, p_71515_2_[1]);
                d1 = func_110666_a(p_71515_1_, d1, p_71515_2_[2]);
                d2 = func_110666_a(p_71515_1_, d2, p_71515_2_[3]);
            }

            World world = p_71515_1_.func_130014_f_();

            if (!world.func_72899_e((int)d0, (int)d1, (int)d2))
            {
                func_152373_a(p_71515_1_, this, "commands.summon.outOfWorld", new Object[0]);
            }
            else
            {
                NBTTagCompound nbttagcompound = new NBTTagCompound();
                boolean flag = false;

                if (p_71515_2_.length >= 5)
                {
                    IChatComponent ichatcomponent = func_147178_a(p_71515_1_, p_71515_2_, 4);

                    try
                    {
                        NBTBase nbtbase = JsonToNBT.func_150315_a(ichatcomponent.func_150260_c());

                        if (!(nbtbase instanceof NBTTagCompound))
                        {
                            func_152373_a(p_71515_1_, this, "commands.summon.tagError", new Object[] {"Not a valid tag"});
                            return;
                        }

                        nbttagcompound = (NBTTagCompound)nbtbase;
                        flag = true;
                    }
                    catch (NBTException nbtexception)
                    {
                        func_152373_a(p_71515_1_, this, "commands.summon.tagError", new Object[] {nbtexception.getMessage()});
                        return;
                    }
                }

                nbttagcompound.func_74778_a("id", s);
                Entity entity1 = EntityList.func_75615_a(nbttagcompound, world);

                if (entity1 == null)
                {
                    func_152373_a(p_71515_1_, this, "commands.summon.failed", new Object[0]);
                }
                else
                {
                    entity1.func_70012_b(d0, d1, d2, entity1.field_70177_z, entity1.field_70125_A);

                    if (!flag && entity1 instanceof EntityLiving)
                    {
                        ((EntityLiving)entity1).func_110161_a((IEntityLivingData)null);
                    }

                    world.func_72838_d(entity1);
                    Entity entity2 = entity1;

                    for (NBTTagCompound nbttagcompound1 = nbttagcompound; entity2 != null && nbttagcompound1.func_150297_b("Riding", 10); nbttagcompound1 = nbttagcompound1.func_74775_l("Riding"))
                    {
                        Entity entity = EntityList.func_75615_a(nbttagcompound1.func_74775_l("Riding"), world);

                        if (entity != null)
                        {
                            entity.func_70012_b(d0, d1, d2, entity.field_70177_z, entity.field_70125_A);
                            world.func_72838_d(entity);
                            entity2.func_70078_a(entity);
                        }

                        entity2 = entity;
                    }

                    func_152373_a(p_71515_1_, this, "commands.summon.success", new Object[0]);
                }
            }
        }
    }

    public List func_71516_a(ICommandSender p_71516_1_, String[] p_71516_2_)
    {
        return p_71516_2_.length == 1 ? func_71530_a(p_71516_2_, this.func_147182_d()) : null;
    }

    protected String[] func_147182_d()
    {
        return (String[])EntityList.func_151515_b().toArray(new String[0]);
    }
}