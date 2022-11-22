package net.minecraft.entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.entity.ai.attributes.ServersideAttributeMap;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.item.EntityEnderEye;
import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.item.EntityExpBottle;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.item.EntityPainting;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.init.Items;
import net.minecraft.item.ItemMap;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S04PacketEntityEquipment;
import net.minecraft.network.play.server.S0APacketUseBed;
import net.minecraft.network.play.server.S0CPacketSpawnPlayer;
import net.minecraft.network.play.server.S0EPacketSpawnObject;
import net.minecraft.network.play.server.S0FPacketSpawnMob;
import net.minecraft.network.play.server.S10PacketSpawnPainting;
import net.minecraft.network.play.server.S11PacketSpawnExperienceOrb;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.network.play.server.S14PacketEntity;
import net.minecraft.network.play.server.S18PacketEntityTeleport;
import net.minecraft.network.play.server.S19PacketEntityHeadLook;
import net.minecraft.network.play.server.S1BPacketEntityAttach;
import net.minecraft.network.play.server.S1CPacketEntityMetadata;
import net.minecraft.network.play.server.S1DPacketEntityEffect;
import net.minecraft.network.play.server.S20PacketEntityProperties;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.world.storage.MapData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EntityTrackerEntry
{
    private static final Logger field_151262_p = LogManager.getLogger();
    public Entity field_73132_a;
    public int field_73130_b;
    public int field_73131_c;
    public int field_73128_d;
    public int field_73129_e;
    public int field_73126_f;
    public int field_73127_g;
    public int field_73139_h;
    public int field_73140_i;
    public double field_73137_j;
    public double field_73138_k;
    public double field_73135_l;
    public int field_73136_m;
    private double field_73147_p;
    private double field_73146_q;
    private double field_73145_r;
    private boolean field_73144_s;
    private boolean field_73143_t;
    private int field_73142_u;
    private Entity field_85178_v;
    private boolean field_73141_v;
    public boolean field_73133_n;
    public Set field_73134_o = new HashSet();
    private static final String __OBFID = "CL_00001443";

    public EntityTrackerEntry(Entity p_i1525_1_, int p_i1525_2_, int p_i1525_3_, boolean p_i1525_4_)
    {
        this.field_73132_a = p_i1525_1_;
        this.field_73130_b = p_i1525_2_;
        this.field_73131_c = p_i1525_3_;
        this.field_73143_t = p_i1525_4_;
        this.field_73128_d = MathHelper.func_76128_c(p_i1525_1_.field_70165_t * 32.0D);
        this.field_73129_e = MathHelper.func_76128_c(p_i1525_1_.field_70163_u * 32.0D);
        this.field_73126_f = MathHelper.func_76128_c(p_i1525_1_.field_70161_v * 32.0D);
        this.field_73127_g = MathHelper.func_76141_d(p_i1525_1_.field_70177_z * 256.0F / 360.0F);
        this.field_73139_h = MathHelper.func_76141_d(p_i1525_1_.field_70125_A * 256.0F / 360.0F);
        this.field_73140_i = MathHelper.func_76141_d(p_i1525_1_.func_70079_am() * 256.0F / 360.0F);
    }

    public boolean equals(Object p_equals_1_)
    {
        return p_equals_1_ instanceof EntityTrackerEntry ? ((EntityTrackerEntry)p_equals_1_).field_73132_a.func_145782_y() == this.field_73132_a.func_145782_y() : false;
    }

    public int hashCode()
    {
        return this.field_73132_a.func_145782_y();
    }

    public void func_73122_a(List p_73122_1_)
    {
        this.field_73133_n = false;

        if (!this.field_73144_s || this.field_73132_a.func_70092_e(this.field_73147_p, this.field_73146_q, this.field_73145_r) > 16.0D)
        {
            this.field_73147_p = this.field_73132_a.field_70165_t;
            this.field_73146_q = this.field_73132_a.field_70163_u;
            this.field_73145_r = this.field_73132_a.field_70161_v;
            this.field_73144_s = true;
            this.field_73133_n = true;
            this.func_73125_b(p_73122_1_);
        }

        if (this.field_85178_v != this.field_73132_a.field_70154_o || this.field_73132_a.field_70154_o != null && this.field_73136_m % 60 == 0)
        {
            this.field_85178_v = this.field_73132_a.field_70154_o;
            this.func_151259_a(new S1BPacketEntityAttach(0, this.field_73132_a, this.field_73132_a.field_70154_o));
        }

        if (this.field_73132_a instanceof EntityItemFrame && this.field_73136_m % 10 == 0)
        {
            EntityItemFrame entityitemframe = (EntityItemFrame)this.field_73132_a;
            ItemStack itemstack = entityitemframe.func_82335_i();

            if (itemstack != null && itemstack.func_77973_b() instanceof ItemMap)
            {
                MapData mapdata = Items.FILLED_MAP.func_77873_a(itemstack, this.field_73132_a.world);
                Iterator iterator = p_73122_1_.iterator();

                while (iterator.hasNext())
                {
                    EntityPlayer entityplayer = (EntityPlayer)iterator.next();
                    EntityPlayerMP entityplayermp = (EntityPlayerMP)entityplayer;
                    mapdata.func_76191_a(entityplayermp, itemstack);
                    Packet packet = Items.FILLED_MAP.func_150911_c(itemstack, this.field_73132_a.world, entityplayermp);

                    if (packet != null)
                    {
                        entityplayermp.field_71135_a.func_147359_a(packet);
                    }
                }
            }

            this.func_111190_b();
        }
        else if (this.field_73136_m % this.field_73131_c == 0 || this.field_73132_a.field_70160_al || this.field_73132_a.func_70096_w().func_75684_a())
        {
            int i;
            int j;

            if (this.field_73132_a.field_70154_o == null)
            {
                ++this.field_73142_u;
                i = this.field_73132_a.field_70168_am.func_75630_a(this.field_73132_a.field_70165_t);
                j = MathHelper.func_76128_c(this.field_73132_a.field_70163_u * 32.0D);
                int k = this.field_73132_a.field_70168_am.func_75630_a(this.field_73132_a.field_70161_v);
                int l = MathHelper.func_76141_d(this.field_73132_a.field_70177_z * 256.0F / 360.0F);
                int i1 = MathHelper.func_76141_d(this.field_73132_a.field_70125_A * 256.0F / 360.0F);
                int j1 = i - this.field_73128_d;
                int k1 = j - this.field_73129_e;
                int l1 = k - this.field_73126_f;
                Object object = null;
                boolean flag = Math.abs(j1) >= 4 || Math.abs(k1) >= 4 || Math.abs(l1) >= 4 || this.field_73136_m % 60 == 0;
                boolean flag1 = Math.abs(l - this.field_73127_g) >= 4 || Math.abs(i1 - this.field_73139_h) >= 4;

                if (this.field_73136_m > 0 || this.field_73132_a instanceof EntityArrow)
                {
                    if (j1 >= -128 && j1 < 128 && k1 >= -128 && k1 < 128 && l1 >= -128 && l1 < 128 && this.field_73142_u <= 400 && !this.field_73141_v)
                    {
                        if (flag && flag1)
                        {
                            object = new S14PacketEntity.S17PacketEntityLookMove(this.field_73132_a.func_145782_y(), (byte)j1, (byte)k1, (byte)l1, (byte)l, (byte)i1);
                        }
                        else if (flag)
                        {
                            object = new S14PacketEntity.S15PacketEntityRelMove(this.field_73132_a.func_145782_y(), (byte)j1, (byte)k1, (byte)l1);
                        }
                        else if (flag1)
                        {
                            object = new S14PacketEntity.S16PacketEntityLook(this.field_73132_a.func_145782_y(), (byte)l, (byte)i1);
                        }
                    }
                    else
                    {
                        this.field_73142_u = 0;
                        object = new S18PacketEntityTeleport(this.field_73132_a.func_145782_y(), i, j, k, (byte)l, (byte)i1);
                    }
                }

                if (this.field_73143_t)
                {
                    double d0 = this.field_73132_a.field_70159_w - this.field_73137_j;
                    double d1 = this.field_73132_a.field_70181_x - this.field_73138_k;
                    double d2 = this.field_73132_a.field_70179_y - this.field_73135_l;
                    double d3 = 0.02D;
                    double d4 = d0 * d0 + d1 * d1 + d2 * d2;

                    if (d4 > d3 * d3 || d4 > 0.0D && this.field_73132_a.field_70159_w == 0.0D && this.field_73132_a.field_70181_x == 0.0D && this.field_73132_a.field_70179_y == 0.0D)
                    {
                        this.field_73137_j = this.field_73132_a.field_70159_w;
                        this.field_73138_k = this.field_73132_a.field_70181_x;
                        this.field_73135_l = this.field_73132_a.field_70179_y;
                        this.func_151259_a(new S12PacketEntityVelocity(this.field_73132_a.func_145782_y(), this.field_73137_j, this.field_73138_k, this.field_73135_l));
                    }
                }

                if (object != null)
                {
                    this.func_151259_a((Packet)object);
                }

                this.func_111190_b();

                if (flag)
                {
                    this.field_73128_d = i;
                    this.field_73129_e = j;
                    this.field_73126_f = k;
                }

                if (flag1)
                {
                    this.field_73127_g = l;
                    this.field_73139_h = i1;
                }

                this.field_73141_v = false;
            }
            else
            {
                i = MathHelper.func_76141_d(this.field_73132_a.field_70177_z * 256.0F / 360.0F);
                j = MathHelper.func_76141_d(this.field_73132_a.field_70125_A * 256.0F / 360.0F);
                boolean flag2 = Math.abs(i - this.field_73127_g) >= 4 || Math.abs(j - this.field_73139_h) >= 4;

                if (flag2)
                {
                    this.func_151259_a(new S14PacketEntity.S16PacketEntityLook(this.field_73132_a.func_145782_y(), (byte)i, (byte)j));
                    this.field_73127_g = i;
                    this.field_73139_h = j;
                }

                this.field_73128_d = this.field_73132_a.field_70168_am.func_75630_a(this.field_73132_a.field_70165_t);
                this.field_73129_e = MathHelper.func_76128_c(this.field_73132_a.field_70163_u * 32.0D);
                this.field_73126_f = this.field_73132_a.field_70168_am.func_75630_a(this.field_73132_a.field_70161_v);
                this.func_111190_b();
                this.field_73141_v = true;
            }

            i = MathHelper.func_76141_d(this.field_73132_a.func_70079_am() * 256.0F / 360.0F);

            if (Math.abs(i - this.field_73140_i) >= 4)
            {
                this.func_151259_a(new S19PacketEntityHeadLook(this.field_73132_a, (byte)i));
                this.field_73140_i = i;
            }

            this.field_73132_a.field_70160_al = false;
        }

        ++this.field_73136_m;

        if (this.field_73132_a.field_70133_I)
        {
            this.func_151261_b(new S12PacketEntityVelocity(this.field_73132_a));
            this.field_73132_a.field_70133_I = false;
        }
    }

    private void func_111190_b()
    {
        DataWatcher datawatcher = this.field_73132_a.func_70096_w();

        if (datawatcher.func_75684_a())
        {
            this.func_151261_b(new S1CPacketEntityMetadata(this.field_73132_a.func_145782_y(), datawatcher, false));
        }

        if (this.field_73132_a instanceof EntityLivingBase)
        {
            ServersideAttributeMap serversideattributemap = (ServersideAttributeMap)((EntityLivingBase)this.field_73132_a).func_110140_aT();
            Set set = serversideattributemap.func_111161_b();

            if (!set.isEmpty())
            {
                this.func_151261_b(new S20PacketEntityProperties(this.field_73132_a.func_145782_y(), set));
            }

            set.clear();
        }
    }

    public void func_151259_a(Packet p_151259_1_)
    {
        Iterator iterator = this.field_73134_o.iterator();

        while (iterator.hasNext())
        {
            EntityPlayerMP entityplayermp = (EntityPlayerMP)iterator.next();
            entityplayermp.field_71135_a.func_147359_a(p_151259_1_);
        }
    }

    public void func_151261_b(Packet p_151261_1_)
    {
        this.func_151259_a(p_151261_1_);

        if (this.field_73132_a instanceof EntityPlayerMP)
        {
            ((EntityPlayerMP)this.field_73132_a).field_71135_a.func_147359_a(p_151261_1_);
        }
    }

    public void func_73119_a()
    {
        Iterator iterator = this.field_73134_o.iterator();

        while (iterator.hasNext())
        {
            EntityPlayerMP entityplayermp = (EntityPlayerMP)iterator.next();
            entityplayermp.func_152339_d(this.field_73132_a);
        }
    }

    public void func_73118_a(EntityPlayerMP p_73118_1_)
    {
        if (this.field_73134_o.contains(p_73118_1_))
        {
            p_73118_1_.func_152339_d(this.field_73132_a);
            this.field_73134_o.remove(p_73118_1_);
        }
    }

    public void func_73117_b(EntityPlayerMP p_73117_1_)
    {
        if (p_73117_1_ != this.field_73132_a)
        {
            double d0 = p_73117_1_.field_70165_t - (double)(this.field_73128_d / 32);
            double d1 = p_73117_1_.field_70161_v - (double)(this.field_73126_f / 32);

            if (d0 >= (double)(-this.field_73130_b) && d0 <= (double)this.field_73130_b && d1 >= (double)(-this.field_73130_b) && d1 <= (double)this.field_73130_b)
            {
                if (!this.field_73134_o.contains(p_73117_1_) && (this.func_73121_d(p_73117_1_) || this.field_73132_a.field_98038_p))
                {
                    this.field_73134_o.add(p_73117_1_);
                    Packet packet = this.func_151260_c();
                    p_73117_1_.field_71135_a.func_147359_a(packet);

                    if (!this.field_73132_a.func_70096_w().func_92085_d())
                    {
                        p_73117_1_.field_71135_a.func_147359_a(new S1CPacketEntityMetadata(this.field_73132_a.func_145782_y(), this.field_73132_a.func_70096_w(), true));
                    }

                    if (this.field_73132_a instanceof EntityLivingBase)
                    {
                        ServersideAttributeMap serversideattributemap = (ServersideAttributeMap)((EntityLivingBase)this.field_73132_a).func_110140_aT();
                        Collection collection = serversideattributemap.func_111160_c();

                        if (!collection.isEmpty())
                        {
                            p_73117_1_.field_71135_a.func_147359_a(new S20PacketEntityProperties(this.field_73132_a.func_145782_y(), collection));
                        }
                    }

                    this.field_73137_j = this.field_73132_a.field_70159_w;
                    this.field_73138_k = this.field_73132_a.field_70181_x;
                    this.field_73135_l = this.field_73132_a.field_70179_y;

                    if (this.field_73143_t && !(packet instanceof S0FPacketSpawnMob))
                    {
                        p_73117_1_.field_71135_a.func_147359_a(new S12PacketEntityVelocity(this.field_73132_a.func_145782_y(), this.field_73132_a.field_70159_w, this.field_73132_a.field_70181_x, this.field_73132_a.field_70179_y));
                    }

                    if (this.field_73132_a.field_70154_o != null)
                    {
                        p_73117_1_.field_71135_a.func_147359_a(new S1BPacketEntityAttach(0, this.field_73132_a, this.field_73132_a.field_70154_o));
                    }

                    if (this.field_73132_a instanceof EntityLiving && ((EntityLiving)this.field_73132_a).func_110166_bE() != null)
                    {
                        p_73117_1_.field_71135_a.func_147359_a(new S1BPacketEntityAttach(1, this.field_73132_a, ((EntityLiving)this.field_73132_a).func_110166_bE()));
                    }

                    if (this.field_73132_a instanceof EntityLivingBase)
                    {
                        for (int i = 0; i < 5; ++i)
                        {
                            ItemStack itemstack = ((EntityLivingBase)this.field_73132_a).func_71124_b(i);

                            if (itemstack != null)
                            {
                                p_73117_1_.field_71135_a.func_147359_a(new S04PacketEntityEquipment(this.field_73132_a.func_145782_y(), i, itemstack));
                            }
                        }
                    }

                    if (this.field_73132_a instanceof EntityPlayer)
                    {
                        EntityPlayer entityplayer = (EntityPlayer)this.field_73132_a;

                        if (entityplayer.func_70608_bn())
                        {
                            p_73117_1_.field_71135_a.func_147359_a(new S0APacketUseBed(entityplayer, MathHelper.func_76128_c(this.field_73132_a.field_70165_t), MathHelper.func_76128_c(this.field_73132_a.field_70163_u), MathHelper.func_76128_c(this.field_73132_a.field_70161_v)));
                        }
                    }

                    if (this.field_73132_a instanceof EntityLivingBase)
                    {
                        EntityLivingBase entitylivingbase = (EntityLivingBase)this.field_73132_a;
                        Iterator iterator = entitylivingbase.func_70651_bq().iterator();

                        while (iterator.hasNext())
                        {
                            PotionEffect potioneffect = (PotionEffect)iterator.next();
                            p_73117_1_.field_71135_a.func_147359_a(new S1DPacketEntityEffect(this.field_73132_a.func_145782_y(), potioneffect));
                        }
                    }
                }
            }
            else if (this.field_73134_o.contains(p_73117_1_))
            {
                this.field_73134_o.remove(p_73117_1_);
                p_73117_1_.func_152339_d(this.field_73132_a);
            }
        }
    }

    private boolean func_73121_d(EntityPlayerMP p_73121_1_)
    {
        return p_73121_1_.func_71121_q().func_73040_p().func_72694_a(p_73121_1_, this.field_73132_a.field_70176_ah, this.field_73132_a.field_70164_aj);
    }

    public void func_73125_b(List p_73125_1_)
    {
        for (int i = 0; i < p_73125_1_.size(); ++i)
        {
            this.func_73117_b((EntityPlayerMP)p_73125_1_.get(i));
        }
    }

    private Packet func_151260_c()
    {
        if (this.field_73132_a.field_70128_L)
        {
            field_151262_p.warn("Fetching addPacket for removed entity");
        }

        if (this.field_73132_a instanceof EntityItem)
        {
            return new S0EPacketSpawnObject(this.field_73132_a, 2, 1);
        }
        else if (this.field_73132_a instanceof EntityPlayerMP)
        {
            return new S0CPacketSpawnPlayer((EntityPlayer)this.field_73132_a);
        }
        else if (this.field_73132_a instanceof EntityMinecart)
        {
            EntityMinecart entityminecart = (EntityMinecart)this.field_73132_a;
            return new S0EPacketSpawnObject(this.field_73132_a, 10, entityminecart.func_94087_l());
        }
        else if (this.field_73132_a instanceof EntityBoat)
        {
            return new S0EPacketSpawnObject(this.field_73132_a, 1);
        }
        else if (!(this.field_73132_a instanceof IAnimals) && !(this.field_73132_a instanceof EntityDragon))
        {
            if (this.field_73132_a instanceof EntityFishHook)
            {
                EntityPlayer entityplayer = ((EntityFishHook)this.field_73132_a).field_146042_b;
                return new S0EPacketSpawnObject(this.field_73132_a, 90, entityplayer != null ? entityplayer.func_145782_y() : this.field_73132_a.func_145782_y());
            }
            else if (this.field_73132_a instanceof EntityArrow)
            {
                Entity entity = ((EntityArrow)this.field_73132_a).field_70250_c;
                return new S0EPacketSpawnObject(this.field_73132_a, 60, entity != null ? entity.func_145782_y() : this.field_73132_a.func_145782_y());
            }
            else if (this.field_73132_a instanceof EntitySnowball)
            {
                return new S0EPacketSpawnObject(this.field_73132_a, 61);
            }
            else if (this.field_73132_a instanceof EntityPotion)
            {
                return new S0EPacketSpawnObject(this.field_73132_a, 73, ((EntityPotion)this.field_73132_a).func_70196_i());
            }
            else if (this.field_73132_a instanceof EntityExpBottle)
            {
                return new S0EPacketSpawnObject(this.field_73132_a, 75);
            }
            else if (this.field_73132_a instanceof EntityEnderPearl)
            {
                return new S0EPacketSpawnObject(this.field_73132_a, 65);
            }
            else if (this.field_73132_a instanceof EntityEnderEye)
            {
                return new S0EPacketSpawnObject(this.field_73132_a, 72);
            }
            else if (this.field_73132_a instanceof EntityFireworkRocket)
            {
                return new S0EPacketSpawnObject(this.field_73132_a, 76);
            }
            else
            {
                S0EPacketSpawnObject s0epacketspawnobject;

                if (this.field_73132_a instanceof EntityFireball)
                {
                    EntityFireball entityfireball = (EntityFireball)this.field_73132_a;
                    s0epacketspawnobject = null;
                    byte b0 = 63;

                    if (this.field_73132_a instanceof EntitySmallFireball)
                    {
                        b0 = 64;
                    }
                    else if (this.field_73132_a instanceof EntityWitherSkull)
                    {
                        b0 = 66;
                    }

                    if (entityfireball.field_70235_a != null)
                    {
                        s0epacketspawnobject = new S0EPacketSpawnObject(this.field_73132_a, b0, ((EntityFireball)this.field_73132_a).field_70235_a.func_145782_y());
                    }
                    else
                    {
                        s0epacketspawnobject = new S0EPacketSpawnObject(this.field_73132_a, b0, 0);
                    }

                    s0epacketspawnobject.func_149003_d((int)(entityfireball.field_70232_b * 8000.0D));
                    s0epacketspawnobject.func_149000_e((int)(entityfireball.field_70233_c * 8000.0D));
                    s0epacketspawnobject.func_149007_f((int)(entityfireball.field_70230_d * 8000.0D));
                    return s0epacketspawnobject;
                }
                else if (this.field_73132_a instanceof EntityEgg)
                {
                    return new S0EPacketSpawnObject(this.field_73132_a, 62);
                }
                else if (this.field_73132_a instanceof EntityTNTPrimed)
                {
                    return new S0EPacketSpawnObject(this.field_73132_a, 50);
                }
                else if (this.field_73132_a instanceof EntityEnderCrystal)
                {
                    return new S0EPacketSpawnObject(this.field_73132_a, 51);
                }
                else if (this.field_73132_a instanceof EntityFallingBlock)
                {
                    EntityFallingBlock entityfallingblock = (EntityFallingBlock)this.field_73132_a;
                    return new S0EPacketSpawnObject(this.field_73132_a, 70, Block.func_149682_b(entityfallingblock.func_145805_f()) | entityfallingblock.field_145814_a << 16);
                }
                else if (this.field_73132_a instanceof EntityPainting)
                {
                    return new S10PacketSpawnPainting((EntityPainting)this.field_73132_a);
                }
                else if (this.field_73132_a instanceof EntityItemFrame)
                {
                    EntityItemFrame entityitemframe = (EntityItemFrame)this.field_73132_a;
                    s0epacketspawnobject = new S0EPacketSpawnObject(this.field_73132_a, 71, entityitemframe.field_82332_a);
                    s0epacketspawnobject.func_148996_a(MathHelper.func_76141_d((float)(entityitemframe.field_146063_b * 32)));
                    s0epacketspawnobject.func_148995_b(MathHelper.func_76141_d((float)(entityitemframe.field_146064_c * 32)));
                    s0epacketspawnobject.func_149005_c(MathHelper.func_76141_d((float)(entityitemframe.field_146062_d * 32)));
                    return s0epacketspawnobject;
                }
                else if (this.field_73132_a instanceof EntityLeashKnot)
                {
                    EntityLeashKnot entityleashknot = (EntityLeashKnot)this.field_73132_a;
                    s0epacketspawnobject = new S0EPacketSpawnObject(this.field_73132_a, 77);
                    s0epacketspawnobject.func_148996_a(MathHelper.func_76141_d((float)(entityleashknot.field_146063_b * 32)));
                    s0epacketspawnobject.func_148995_b(MathHelper.func_76141_d((float)(entityleashknot.field_146064_c * 32)));
                    s0epacketspawnobject.func_149005_c(MathHelper.func_76141_d((float)(entityleashknot.field_146062_d * 32)));
                    return s0epacketspawnobject;
                }
                else if (this.field_73132_a instanceof EntityXPOrb)
                {
                    return new S11PacketSpawnExperienceOrb((EntityXPOrb)this.field_73132_a);
                }
                else
                {
                    throw new IllegalArgumentException("Don\'t know how to add " + this.field_73132_a.getClass() + "!");
                }
            }
        }
        else
        {
            this.field_73140_i = MathHelper.func_76141_d(this.field_73132_a.func_70079_am() * 256.0F / 360.0F);
            return new S0FPacketSpawnMob((EntityLivingBase)this.field_73132_a);
        }
    }

    public void func_73123_c(EntityPlayerMP p_73123_1_)
    {
        if (this.field_73134_o.contains(p_73123_1_))
        {
            this.field_73134_o.remove(p_73123_1_);
            p_73123_1_.func_152339_d(this.field_73132_a);
        }
    }
}