package net.minecraft.entity.ai;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.MobSpawnerBaseLogic;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityMinecartMobSpawner extends EntityMinecart
{
    private final MobSpawnerBaseLogic field_98040_a = new MobSpawnerBaseLogic()
    {
        private static final String __OBFID = "CL_00001679";
        public void func_98267_a(int p_98267_1_)
        {
            EntityMinecartMobSpawner.this.world.func_72960_a(EntityMinecartMobSpawner.this, (byte)p_98267_1_);
        }
        public World func_98271_a()
        {
            return EntityMinecartMobSpawner.this.world;
        }
        public int func_98275_b()
        {
            return MathHelper.func_76128_c(EntityMinecartMobSpawner.this.field_70165_t);
        }
        public int func_98274_c()
        {
            return MathHelper.func_76128_c(EntityMinecartMobSpawner.this.field_70163_u);
        }
        public int func_98266_d()
        {
            return MathHelper.func_76128_c(EntityMinecartMobSpawner.this.field_70161_v);
        }
    };
    private static final String __OBFID = "CL_00001678";

    public EntityMinecartMobSpawner(World p_i1725_1_)
    {
        super(p_i1725_1_);
    }

    public EntityMinecartMobSpawner(World p_i1726_1_, double p_i1726_2_, double p_i1726_4_, double p_i1726_6_)
    {
        super(p_i1726_1_, p_i1726_2_, p_i1726_4_, p_i1726_6_);
    }

    public int func_94087_l()
    {
        return 4;
    }

    public Block func_145817_o()
    {
        return Blocks.MOB_SPAWNER;
    }

    protected void func_70037_a(NBTTagCompound p_70037_1_)
    {
        super.func_70037_a(p_70037_1_);
        this.field_98040_a.func_98270_a(p_70037_1_);
    }

    protected void func_70014_b(NBTTagCompound p_70014_1_)
    {
        super.func_70014_b(p_70014_1_);
        this.field_98040_a.func_98280_b(p_70014_1_);
    }

    @SideOnly(Side.CLIENT)
    public void func_70103_a(byte p_70103_1_)
    {
        this.field_98040_a.func_98268_b(p_70103_1_);
    }

    public void func_70071_h_()
    {
        super.func_70071_h_();
        this.field_98040_a.func_98278_g();
    }

    @SideOnly(Side.CLIENT)
    public MobSpawnerBaseLogic func_98039_d()
    {
        return this.field_98040_a;
    }
}