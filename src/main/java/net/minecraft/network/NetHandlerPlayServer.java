package net.minecraft.network;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import io.netty.buffer.Unpooled;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.Callable;
import net.minecraft.block.material.Material;
import net.minecraft.command.server.CommandBlockLogic;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityMinecartCommandBlock;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerBeacon;
import net.minecraft.inventory.ContainerMerchant;
import net.minecraft.inventory.ContainerRepair;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemEditableBook;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemWritableBook;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.network.play.INetHandlerPlayServer;
import net.minecraft.network.play.client.C00PacketKeepAlive;
import net.minecraft.network.play.client.C01PacketChatMessage;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.client.C09PacketHeldItemChange;
import net.minecraft.network.play.client.C0APacketAnimation;
import net.minecraft.network.play.client.C0BPacketEntityAction;
import net.minecraft.network.play.client.C0CPacketInput;
import net.minecraft.network.play.client.C0DPacketCloseWindow;
import net.minecraft.network.play.client.C0EPacketClickWindow;
import net.minecraft.network.play.client.C0FPacketConfirmTransaction;
import net.minecraft.network.play.client.C10PacketCreativeInventoryAction;
import net.minecraft.network.play.client.C11PacketEnchantItem;
import net.minecraft.network.play.client.C12PacketUpdateSign;
import net.minecraft.network.play.client.C13PacketPlayerAbilities;
import net.minecraft.network.play.client.C14PacketTabComplete;
import net.minecraft.network.play.client.C15PacketClientSettings;
import net.minecraft.network.play.client.C16PacketClientStatus;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.network.play.server.S00PacketKeepAlive;
import net.minecraft.network.play.server.S02PacketChat;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.network.play.server.S23PacketBlockChange;
import net.minecraft.network.play.server.S2FPacketSetSlot;
import net.minecraft.network.play.server.S32PacketConfirmTransaction;
import net.minecraft.network.play.server.S3APacketTabComplete;
import net.minecraft.network.play.server.S40PacketDisconnect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.UserListBansEntry;
import net.minecraft.stats.AchievementList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.tileentity.TileEntityCommandBlock;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatAllowedCharacters;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IntHashMap;
import net.minecraft.util.ReportedException;
import net.minecraft.world.WorldServer;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NetHandlerPlayServer implements INetHandlerPlayServer
{
    private static final Logger field_147370_c = LogManager.getLogger();
    public final NetworkManager field_147371_a;
    private final MinecraftServer field_147367_d;
    public EntityPlayerMP field_147369_b;
    private int field_147368_e;
    private int field_147365_f;
    private boolean field_147366_g;
    private int field_147378_h;
    private long field_147379_i;
    private static Random field_147376_j = new Random();
    private long field_147377_k;
    private int field_147374_l;
    private int field_147375_m;
    private IntHashMap field_147372_n = new IntHashMap();
    private double field_147373_o;
    private double field_147382_p;
    private double field_147381_q;
    private boolean field_147380_r = true;
    private static final String __OBFID = "CL_00001452";

    public NetHandlerPlayServer(MinecraftServer p_i1530_1_, NetworkManager p_i1530_2_, EntityPlayerMP p_i1530_3_)
    {
        this.field_147367_d = p_i1530_1_;
        this.field_147371_a = p_i1530_2_;
        p_i1530_2_.func_150719_a(this);
        this.field_147369_b = p_i1530_3_;
        p_i1530_3_.field_71135_a = this;
    }

    public void func_147233_a()
    {
        this.field_147366_g = false;
        ++this.field_147368_e;
        this.field_147367_d.profiler.startMeasure("keepAlive");

        if ((long)this.field_147368_e - this.field_147377_k > 40L)
        {
            this.field_147377_k = (long)this.field_147368_e;
            this.field_147379_i = this.func_147363_d();
            this.field_147378_h = (int)this.field_147379_i;
            this.func_147359_a(new S00PacketKeepAlive(this.field_147378_h));
        }

        if (this.field_147374_l > 0)
        {
            --this.field_147374_l;
        }

        if (this.field_147375_m > 0)
        {
            --this.field_147375_m;
        }

        if (this.field_147369_b.func_154331_x() > 0L && this.field_147367_d.func_143007_ar() > 0 && MinecraftServer.func_130071_aq() - this.field_147369_b.func_154331_x() > (long)(this.field_147367_d.func_143007_ar() * 1000 * 60))
        {
            this.func_147360_c("You have been idle for too long!");
        }
    }

    public NetworkManager func_147362_b()
    {
        return this.field_147371_a;
    }

    public void func_147360_c(String p_147360_1_)
    {
        final ChatComponentText chatcomponenttext = new ChatComponentText(p_147360_1_);
        this.field_147371_a.func_150725_a(new S40PacketDisconnect(chatcomponenttext), new GenericFutureListener[] {new GenericFutureListener()
        {
            private static final String __OBFID = "CL_00001453";
            public void operationComplete(Future p_operationComplete_1_)
            {
                NetHandlerPlayServer.this.field_147371_a.func_150718_a(chatcomponenttext);
            }
        }
                                                                                                     });
        this.field_147371_a.func_150721_g();
    }

    public void func_147358_a(C0CPacketInput p_147358_1_)
    {
        this.field_147369_b.func_110430_a(p_147358_1_.func_149620_c(), p_147358_1_.func_149616_d(), p_147358_1_.func_149618_e(), p_147358_1_.func_149617_f());
    }

    public void func_147347_a(C03PacketPlayer p_147347_1_)
    {
        WorldServer worldserver = this.field_147367_d.func_71218_a(this.field_147369_b.field_71093_bK);
        this.field_147366_g = true;

        if (!this.field_147369_b.field_71136_j)
        {
            double d0;

            if (!this.field_147380_r)
            {
                d0 = p_147347_1_.func_149467_d() - this.field_147382_p;

                if (p_147347_1_.func_149464_c() == this.field_147373_o && d0 * d0 < 0.01D && p_147347_1_.func_149472_e() == this.field_147381_q)
                {
                    this.field_147380_r = true;
                }
            }

            if (this.field_147380_r)
            {
                double d1;
                double d2;
                double d3;

                if (this.field_147369_b.field_70154_o != null)
                {
                    float f4 = this.field_147369_b.field_70177_z;
                    float f = this.field_147369_b.field_70125_A;
                    this.field_147369_b.field_70154_o.func_70043_V();
                    d1 = this.field_147369_b.field_70165_t;
                    d2 = this.field_147369_b.field_70163_u;
                    d3 = this.field_147369_b.field_70161_v;

                    if (p_147347_1_.func_149463_k())
                    {
                        f4 = p_147347_1_.func_149462_g();
                        f = p_147347_1_.func_149470_h();
                    }

                    this.field_147369_b.field_70122_E = p_147347_1_.func_149465_i();
                    this.field_147369_b.func_71127_g();
                    this.field_147369_b.field_70139_V = 0.0F;
                    this.field_147369_b.func_70080_a(d1, d2, d3, f4, f);

                    if (this.field_147369_b.field_70154_o != null)
                    {
                        this.field_147369_b.field_70154_o.func_70043_V();
                    }

                    this.field_147367_d.func_71203_ab().func_72358_d(this.field_147369_b);

                    if (this.field_147380_r)
                    {
                        this.field_147373_o = this.field_147369_b.field_70165_t;
                        this.field_147382_p = this.field_147369_b.field_70163_u;
                        this.field_147381_q = this.field_147369_b.field_70161_v;
                    }

                    worldserver.func_72870_g(this.field_147369_b);
                    return;
                }

                if (this.field_147369_b.func_70608_bn())
                {
                    this.field_147369_b.func_71127_g();
                    this.field_147369_b.func_70080_a(this.field_147373_o, this.field_147382_p, this.field_147381_q, this.field_147369_b.field_70177_z, this.field_147369_b.field_70125_A);
                    worldserver.func_72870_g(this.field_147369_b);
                    return;
                }

                d0 = this.field_147369_b.field_70163_u;
                this.field_147373_o = this.field_147369_b.field_70165_t;
                this.field_147382_p = this.field_147369_b.field_70163_u;
                this.field_147381_q = this.field_147369_b.field_70161_v;
                d1 = this.field_147369_b.field_70165_t;
                d2 = this.field_147369_b.field_70163_u;
                d3 = this.field_147369_b.field_70161_v;
                float f1 = this.field_147369_b.field_70177_z;
                float f2 = this.field_147369_b.field_70125_A;

                if (p_147347_1_.func_149466_j() && p_147347_1_.func_149467_d() == -999.0D && p_147347_1_.func_149471_f() == -999.0D)
                {
                    p_147347_1_.func_149469_a(false);
                }

                double d4;

                if (p_147347_1_.func_149466_j())
                {
                    d1 = p_147347_1_.func_149464_c();
                    d2 = p_147347_1_.func_149467_d();
                    d3 = p_147347_1_.func_149472_e();
                    d4 = p_147347_1_.func_149471_f() - p_147347_1_.func_149467_d();

                    if (!this.field_147369_b.func_70608_bn() && (d4 > 1.65D || d4 < 0.1D))
                    {
                        this.func_147360_c("Illegal stance");
                        field_147370_c.warn(this.field_147369_b.func_70005_c_() + " had an illegal stance: " + d4);
                        return;
                    }

                    if (Math.abs(p_147347_1_.func_149464_c()) > 3.2E7D || Math.abs(p_147347_1_.func_149472_e()) > 3.2E7D)
                    {
                        this.func_147360_c("Illegal position");
                        return;
                    }
                }

                if (p_147347_1_.func_149463_k())
                {
                    f1 = p_147347_1_.func_149462_g();
                    f2 = p_147347_1_.func_149470_h();
                }

                this.field_147369_b.func_71127_g();
                this.field_147369_b.field_70139_V = 0.0F;
                this.field_147369_b.func_70080_a(this.field_147373_o, this.field_147382_p, this.field_147381_q, f1, f2);

                if (!this.field_147380_r)
                {
                    return;
                }

                d4 = d1 - this.field_147369_b.field_70165_t;
                double d5 = d2 - this.field_147369_b.field_70163_u;
                double d6 = d3 - this.field_147369_b.field_70161_v;
                double d7 = Math.min(Math.abs(d4), Math.abs(this.field_147369_b.field_70159_w));
                double d8 = Math.min(Math.abs(d5), Math.abs(this.field_147369_b.field_70181_x));
                double d9 = Math.min(Math.abs(d6), Math.abs(this.field_147369_b.field_70179_y));
                double d10 = d7 * d7 + d8 * d8 + d9 * d9;

                if (d10 > 100.0D && (!this.field_147367_d.func_71264_H() || !this.field_147367_d.func_71214_G().equals(this.field_147369_b.func_70005_c_())))
                {
                    field_147370_c.warn(this.field_147369_b.func_70005_c_() + " moved too quickly! " + d4 + "," + d5 + "," + d6 + " (" + d7 + ", " + d8 + ", " + d9 + ")");
                    this.func_147364_a(this.field_147373_o, this.field_147382_p, this.field_147381_q, this.field_147369_b.field_70177_z, this.field_147369_b.field_70125_A);
                    return;
                }

                float f3 = 0.0625F;
                boolean flag = worldserver.func_72945_a(this.field_147369_b, this.field_147369_b.field_70121_D.func_72329_c().func_72331_e((double)f3, (double)f3, (double)f3)).isEmpty();

                if (this.field_147369_b.field_70122_E && !p_147347_1_.func_149465_i() && d5 > 0.0D)
                {
                    this.field_147369_b.func_70664_aZ();
                }

                this.field_147369_b.func_70091_d(d4, d5, d6);
                this.field_147369_b.field_70122_E = p_147347_1_.func_149465_i();
                this.field_147369_b.func_71000_j(d4, d5, d6);
                double d11 = d5;
                d4 = d1 - this.field_147369_b.field_70165_t;
                d5 = d2 - this.field_147369_b.field_70163_u;

                if (d5 > -0.5D || d5 < 0.5D)
                {
                    d5 = 0.0D;
                }

                d6 = d3 - this.field_147369_b.field_70161_v;
                d10 = d4 * d4 + d5 * d5 + d6 * d6;
                boolean flag1 = false;

                if (d10 > 0.0625D && !this.field_147369_b.func_70608_bn() && !this.field_147369_b.field_71134_c.func_73083_d())
                {
                    flag1 = true;
                    field_147370_c.warn(this.field_147369_b.func_70005_c_() + " moved wrongly!");
                }

                this.field_147369_b.func_70080_a(d1, d2, d3, f1, f2);
                boolean flag2 = worldserver.func_72945_a(this.field_147369_b, this.field_147369_b.field_70121_D.func_72329_c().func_72331_e((double)f3, (double)f3, (double)f3)).isEmpty();

                if (flag && (flag1 || !flag2) && !this.field_147369_b.func_70608_bn())
                {
                    this.func_147364_a(this.field_147373_o, this.field_147382_p, this.field_147381_q, f1, f2);
                    return;
                }

                AxisAlignedBB axisalignedbb = this.field_147369_b.field_70121_D.func_72329_c().func_72314_b((double)f3, (double)f3, (double)f3).func_72321_a(0.0D, -0.55D, 0.0D);

                if (!this.field_147367_d.func_71231_X() && !this.field_147369_b.field_71134_c.func_73083_d() && !worldserver.func_72829_c(axisalignedbb))
                {
                    if (d11 >= -0.03125D)
                    {
                        ++this.field_147365_f;

                        if (this.field_147365_f > 80)
                        {
                            field_147370_c.warn(this.field_147369_b.func_70005_c_() + " was kicked for floating too long!");
                            this.func_147360_c("Flying is not enabled on this server");
                            return;
                        }
                    }
                }
                else
                {
                    this.field_147365_f = 0;
                }

                this.field_147369_b.field_70122_E = p_147347_1_.func_149465_i();
                this.field_147367_d.func_71203_ab().func_72358_d(this.field_147369_b);
                this.field_147369_b.func_71122_b(this.field_147369_b.field_70163_u - d0, p_147347_1_.func_149465_i());
            }
            else if (this.field_147368_e % 20 == 0)
            {
                this.func_147364_a(this.field_147373_o, this.field_147382_p, this.field_147381_q, this.field_147369_b.field_70177_z, this.field_147369_b.field_70125_A);
            }
        }
    }

    public void func_147364_a(double p_147364_1_, double p_147364_3_, double p_147364_5_, float p_147364_7_, float p_147364_8_)
    {
        this.field_147380_r = false;
        this.field_147373_o = p_147364_1_;
        this.field_147382_p = p_147364_3_;
        this.field_147381_q = p_147364_5_;
        this.field_147369_b.func_70080_a(p_147364_1_, p_147364_3_, p_147364_5_, p_147364_7_, p_147364_8_);
        this.field_147369_b.field_71135_a.func_147359_a(new S08PacketPlayerPosLook(p_147364_1_, p_147364_3_ + 1.6200000047683716D, p_147364_5_, p_147364_7_, p_147364_8_, false));
    }

    public void func_147345_a(C07PacketPlayerDigging p_147345_1_)
    {
        WorldServer worldserver = this.field_147367_d.func_71218_a(this.field_147369_b.field_71093_bK);
        this.field_147369_b.func_143004_u();

        if (p_147345_1_.func_149506_g() == 4)
        {
            this.field_147369_b.func_71040_bB(false);
        }
        else if (p_147345_1_.func_149506_g() == 3)
        {
            this.field_147369_b.func_71040_bB(true);
        }
        else if (p_147345_1_.func_149506_g() == 5)
        {
            this.field_147369_b.func_71034_by();
        }
        else
        {
            boolean flag = false;

            if (p_147345_1_.func_149506_g() == 0)
            {
                flag = true;
            }

            if (p_147345_1_.func_149506_g() == 1)
            {
                flag = true;
            }

            if (p_147345_1_.func_149506_g() == 2)
            {
                flag = true;
            }

            int i = p_147345_1_.func_149505_c();
            int j = p_147345_1_.func_149503_d();
            int k = p_147345_1_.func_149502_e();

            if (flag)
            {
                double d0 = this.field_147369_b.field_70165_t - ((double)i + 0.5D);
                double d1 = this.field_147369_b.field_70163_u - ((double)j + 0.5D) + 1.5D;
                double d2 = this.field_147369_b.field_70161_v - ((double)k + 0.5D);
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;

                if (d3 > 36.0D)
                {
                    return;
                }

                if (j >= this.field_147367_d.func_71207_Z())
                {
                    return;
                }
            }

            if (p_147345_1_.func_149506_g() == 0)
            {
                if (!this.field_147367_d.func_96290_a(worldserver, i, j, k, this.field_147369_b))
                {
                    this.field_147369_b.field_71134_c.func_73074_a(i, j, k, p_147345_1_.func_149501_f());
                }
                else
                {
                    this.field_147369_b.field_71135_a.func_147359_a(new S23PacketBlockChange(i, j, k, worldserver));
                }
            }
            else if (p_147345_1_.func_149506_g() == 2)
            {
                this.field_147369_b.field_71134_c.func_73082_a(i, j, k);

                if (worldserver.func_147439_a(i, j, k).func_149688_o() != Material.field_151579_a)
                {
                    this.field_147369_b.field_71135_a.func_147359_a(new S23PacketBlockChange(i, j, k, worldserver));
                }
            }
            else if (p_147345_1_.func_149506_g() == 1)
            {
                this.field_147369_b.field_71134_c.func_73073_c(i, j, k);

                if (worldserver.func_147439_a(i, j, k).func_149688_o() != Material.field_151579_a)
                {
                    this.field_147369_b.field_71135_a.func_147359_a(new S23PacketBlockChange(i, j, k, worldserver));
                }
            }
        }
    }

    public void func_147346_a(C08PacketPlayerBlockPlacement p_147346_1_)
    {
        WorldServer worldserver = this.field_147367_d.func_71218_a(this.field_147369_b.field_71093_bK);
        ItemStack itemstack = this.field_147369_b.field_71071_by.func_70448_g();
        boolean flag = false;
        int i = p_147346_1_.func_149576_c();
        int j = p_147346_1_.func_149571_d();
        int k = p_147346_1_.func_149570_e();
        int l = p_147346_1_.func_149568_f();
        this.field_147369_b.func_143004_u();

        if (p_147346_1_.func_149568_f() == 255)
        {
            if (itemstack == null)
            {
                return;
            }

            this.field_147369_b.field_71134_c.func_73085_a(this.field_147369_b, worldserver, itemstack);
        }
        else if (p_147346_1_.func_149571_d() >= this.field_147367_d.func_71207_Z() - 1 && (p_147346_1_.func_149568_f() == 1 || p_147346_1_.func_149571_d() >= this.field_147367_d.func_71207_Z()))
        {
            ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation("build.tooHigh", new Object[] {Integer.valueOf(this.field_147367_d.func_71207_Z())});
            chatcomponenttranslation.func_150256_b().func_150238_a(EnumChatFormatting.RED);
            this.field_147369_b.field_71135_a.func_147359_a(new S02PacketChat(chatcomponenttranslation));
            flag = true;
        }
        else
        {
            if (this.field_147380_r && this.field_147369_b.func_70092_e((double)i + 0.5D, (double)j + 0.5D, (double)k + 0.5D) < 64.0D && !this.field_147367_d.func_96290_a(worldserver, i, j, k, this.field_147369_b))
            {
                this.field_147369_b.field_71134_c.func_73078_a(this.field_147369_b, worldserver, itemstack, i, j, k, l, p_147346_1_.func_149573_h(), p_147346_1_.func_149569_i(), p_147346_1_.func_149575_j());
            }

            flag = true;
        }

        if (flag)
        {
            this.field_147369_b.field_71135_a.func_147359_a(new S23PacketBlockChange(i, j, k, worldserver));

            if (l == 0)
            {
                --j;
            }

            if (l == 1)
            {
                ++j;
            }

            if (l == 2)
            {
                --k;
            }

            if (l == 3)
            {
                ++k;
            }

            if (l == 4)
            {
                --i;
            }

            if (l == 5)
            {
                ++i;
            }

            this.field_147369_b.field_71135_a.func_147359_a(new S23PacketBlockChange(i, j, k, worldserver));
        }

        itemstack = this.field_147369_b.field_71071_by.func_70448_g();

        if (itemstack != null && itemstack.field_77994_a == 0)
        {
            this.field_147369_b.field_71071_by.field_70462_a[this.field_147369_b.field_71071_by.field_70461_c] = null;
            itemstack = null;
        }

        if (itemstack == null || itemstack.func_77988_m() == 0)
        {
            this.field_147369_b.field_71137_h = true;
            this.field_147369_b.field_71071_by.field_70462_a[this.field_147369_b.field_71071_by.field_70461_c] = ItemStack.func_77944_b(this.field_147369_b.field_71071_by.field_70462_a[this.field_147369_b.field_71071_by.field_70461_c]);
            Slot slot = this.field_147369_b.field_71070_bA.func_75147_a(this.field_147369_b.field_71071_by, this.field_147369_b.field_71071_by.field_70461_c);
            this.field_147369_b.field_71070_bA.func_75142_b();
            this.field_147369_b.field_71137_h = false;

            if (!ItemStack.func_77989_b(this.field_147369_b.field_71071_by.func_70448_g(), p_147346_1_.func_149574_g()))
            {
                this.func_147359_a(new S2FPacketSetSlot(this.field_147369_b.field_71070_bA.field_75152_c, slot.field_75222_d, this.field_147369_b.field_71071_by.func_70448_g()));
            }
        }
    }

    public void func_147231_a(IChatComponent p_147231_1_)
    {
        field_147370_c.info(this.field_147369_b.func_70005_c_() + " lost connection: " + p_147231_1_);
        this.field_147367_d.func_147132_au();
        ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation("multiplayer.player.left", new Object[] {this.field_147369_b.func_145748_c_()});
        chatcomponenttranslation.func_150256_b().func_150238_a(EnumChatFormatting.YELLOW);
        this.field_147367_d.func_71203_ab().func_148539_a(chatcomponenttranslation);
        this.field_147369_b.func_71123_m();
        this.field_147367_d.func_71203_ab().func_72367_e(this.field_147369_b);

        if (this.field_147367_d.func_71264_H() && this.field_147369_b.func_70005_c_().equals(this.field_147367_d.func_71214_G()))
        {
            field_147370_c.info("Stopping singleplayer server as player logged out");
            this.field_147367_d.func_71263_m();
        }
    }

    public void func_147359_a(final Packet p_147359_1_)
    {
        if (p_147359_1_ instanceof S02PacketChat)
        {
            S02PacketChat s02packetchat = (S02PacketChat)p_147359_1_;
            EntityPlayer.EnumChatVisibility enumchatvisibility = this.field_147369_b.func_147096_v();

            if (enumchatvisibility == EntityPlayer.EnumChatVisibility.HIDDEN)
            {
                return;
            }

            if (enumchatvisibility == EntityPlayer.EnumChatVisibility.SYSTEM && !s02packetchat.func_148916_d())
            {
                return;
            }
        }

        try
        {
            this.field_147371_a.func_150725_a(p_147359_1_, new GenericFutureListener[0]);
        }
        catch (Throwable throwable)
        {
            CrashReport crashreport = CrashReport.func_85055_a(throwable, "Sending packet");
            CrashReportCategory crashreportcategory = crashreport.func_85058_a("Packet being sent");
            crashreportcategory.func_71500_a("Packet class", new Callable()
            {
                private static final String __OBFID = "CL_00001454";
                public String call()
                {
                    return p_147359_1_.getClass().getCanonicalName();
                }
            });
            throw new ReportedException(crashreport);
        }
    }

    public void func_147355_a(C09PacketHeldItemChange p_147355_1_)
    {
        if (p_147355_1_.func_149614_c() >= 0 && p_147355_1_.func_149614_c() < InventoryPlayer.func_70451_h())
        {
            this.field_147369_b.field_71071_by.field_70461_c = p_147355_1_.func_149614_c();
            this.field_147369_b.func_143004_u();
        }
        else
        {
            field_147370_c.warn(this.field_147369_b.func_70005_c_() + " tried to set an invalid carried item");
        }
    }

    public void func_147354_a(C01PacketChatMessage p_147354_1_)
    {
        if (this.field_147369_b.func_147096_v() == EntityPlayer.EnumChatVisibility.HIDDEN)
        {
            ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation("chat.cannotSend", new Object[0]);
            chatcomponenttranslation.func_150256_b().func_150238_a(EnumChatFormatting.RED);
            this.func_147359_a(new S02PacketChat(chatcomponenttranslation));
        }
        else
        {
            this.field_147369_b.func_143004_u();
            String s = p_147354_1_.func_149439_c();
            s = StringUtils.normalizeSpace(s);

            for (int i = 0; i < s.length(); ++i)
            {
                if (!ChatAllowedCharacters.func_71566_a(s.charAt(i)))
                {
                    this.func_147360_c("Illegal characters in chat");
                    return;
                }
            }

            if (s.startsWith("/"))
            {
                this.func_147361_d(s);
            }
            else
            {
                ChatComponentTranslation chatcomponenttranslation1 = new ChatComponentTranslation("chat.type.text", new Object[] {this.field_147369_b.func_145748_c_(), s});
                this.field_147367_d.func_71203_ab().func_148544_a(chatcomponenttranslation1, false);
            }

            this.field_147374_l += 20;

            if (this.field_147374_l > 200 && !this.field_147367_d.func_71203_ab().func_152596_g(this.field_147369_b.func_146103_bH()))
            {
                this.func_147360_c("disconnect.spam");
            }
        }
    }

    private void func_147361_d(String p_147361_1_)
    {
        this.field_147367_d.func_71187_D().func_71556_a(this.field_147369_b, p_147361_1_);
    }

    public void func_147350_a(C0APacketAnimation p_147350_1_)
    {
        this.field_147369_b.func_143004_u();

        if (p_147350_1_.func_149421_d() == 1)
        {
            this.field_147369_b.func_71038_i();
        }
    }

    public void func_147357_a(C0BPacketEntityAction p_147357_1_)
    {
        this.field_147369_b.func_143004_u();

        if (p_147357_1_.func_149513_d() == 1)
        {
            this.field_147369_b.func_70095_a(true);
        }
        else if (p_147357_1_.func_149513_d() == 2)
        {
            this.field_147369_b.func_70095_a(false);
        }
        else if (p_147357_1_.func_149513_d() == 4)
        {
            this.field_147369_b.func_70031_b(true);
        }
        else if (p_147357_1_.func_149513_d() == 5)
        {
            this.field_147369_b.func_70031_b(false);
        }
        else if (p_147357_1_.func_149513_d() == 3)
        {
            this.field_147369_b.func_70999_a(false, true, true);
            this.field_147380_r = false;
        }
        else if (p_147357_1_.func_149513_d() == 6)
        {
            if (this.field_147369_b.field_70154_o != null && this.field_147369_b.field_70154_o instanceof EntityHorse)
            {
                ((EntityHorse)this.field_147369_b.field_70154_o).func_110206_u(p_147357_1_.func_149512_e());
            }
        }
        else if (p_147357_1_.func_149513_d() == 7 && this.field_147369_b.field_70154_o != null && this.field_147369_b.field_70154_o instanceof EntityHorse)
        {
            ((EntityHorse)this.field_147369_b.field_70154_o).func_110199_f(this.field_147369_b);
        }
    }

    public void func_147340_a(C02PacketUseEntity p_147340_1_)
    {
        WorldServer worldserver = this.field_147367_d.func_71218_a(this.field_147369_b.field_71093_bK);
        Entity entity = p_147340_1_.func_149564_a(worldserver);
        this.field_147369_b.func_143004_u();

        if (entity != null)
        {
            boolean flag = this.field_147369_b.func_70685_l(entity);
            double d0 = 36.0D;

            if (!flag)
            {
                d0 = 9.0D;
            }

            if (this.field_147369_b.func_70068_e(entity) < d0)
            {
                if (p_147340_1_.func_149565_c() == C02PacketUseEntity.Action.INTERACT)
                {
                    this.field_147369_b.func_70998_m(entity);
                }
                else if (p_147340_1_.func_149565_c() == C02PacketUseEntity.Action.ATTACK)
                {
                    if (entity instanceof EntityItem || entity instanceof EntityXPOrb || entity instanceof EntityArrow || entity == this.field_147369_b)
                    {
                        this.func_147360_c("Attempting to attack an invalid entity");
                        this.field_147367_d.func_71236_h("Player " + this.field_147369_b.func_70005_c_() + " tried to attack an invalid entity");
                        return;
                    }

                    this.field_147369_b.func_71059_n(entity);
                }
            }
        }
    }

    public void func_147342_a(C16PacketClientStatus p_147342_1_)
    {
        this.field_147369_b.func_143004_u();
        C16PacketClientStatus.EnumState enumstate = p_147342_1_.func_149435_c();

        switch (NetHandlerPlayServer.SwitchEnumState.field_151290_a[enumstate.ordinal()])
        {
            case 1:
                if (this.field_147369_b.field_71136_j)
                {
                    this.field_147369_b = this.field_147367_d.func_71203_ab().func_72368_a(this.field_147369_b, 0, true);
                }
                else if (this.field_147369_b.func_71121_q().func_72912_H().func_76093_s())
                {
                    if (this.field_147367_d.func_71264_H() && this.field_147369_b.func_70005_c_().equals(this.field_147367_d.func_71214_G()))
                    {
                        this.field_147369_b.field_71135_a.func_147360_c("You have died. Game over, man, it\'s game over!");
                        this.field_147367_d.func_71272_O();
                    }
                    else
                    {
                        UserListBansEntry userlistbansentry = new UserListBansEntry(this.field_147369_b.func_146103_bH(), (Date)null, "(You just lost the game)", (Date)null, "Death in Hardcore");
                        this.field_147367_d.func_71203_ab().func_152608_h().func_152687_a(userlistbansentry);
                        this.field_147369_b.field_71135_a.func_147360_c("You have died. Game over, man, it\'s game over!");
                    }
                }
                else
                {
                    if (this.field_147369_b.func_110143_aJ() > 0.0F)
                    {
                        return;
                    }

                    this.field_147369_b = this.field_147367_d.func_71203_ab().func_72368_a(this.field_147369_b, 0, false);
                }

                break;
            case 2:
                this.field_147369_b.func_147099_x().func_150876_a(this.field_147369_b);
                break;
            case 3:
                this.field_147369_b.func_71029_a(AchievementList.OPEN_INVENTORY);
        }
    }

    public void func_147356_a(C0DPacketCloseWindow p_147356_1_)
    {
        this.field_147369_b.func_71128_l();
    }

    public void func_147351_a(C0EPacketClickWindow p_147351_1_)
    {
        this.field_147369_b.func_143004_u();

        if (this.field_147369_b.field_71070_bA.field_75152_c == p_147351_1_.func_149548_c() && this.field_147369_b.field_71070_bA.func_75129_b(this.field_147369_b))
        {
            ItemStack itemstack = this.field_147369_b.field_71070_bA.func_75144_a(p_147351_1_.func_149544_d(), p_147351_1_.func_149543_e(), p_147351_1_.func_149542_h(), this.field_147369_b);

            if (ItemStack.func_77989_b(p_147351_1_.func_149546_g(), itemstack))
            {
                this.field_147369_b.field_71135_a.func_147359_a(new S32PacketConfirmTransaction(p_147351_1_.func_149548_c(), p_147351_1_.func_149547_f(), true));
                this.field_147369_b.field_71137_h = true;
                this.field_147369_b.field_71070_bA.func_75142_b();
                this.field_147369_b.func_71113_k();
                this.field_147369_b.field_71137_h = false;
            }
            else
            {
                this.field_147372_n.func_76038_a(this.field_147369_b.field_71070_bA.field_75152_c, Short.valueOf(p_147351_1_.func_149547_f()));
                this.field_147369_b.field_71135_a.func_147359_a(new S32PacketConfirmTransaction(p_147351_1_.func_149548_c(), p_147351_1_.func_149547_f(), false));
                this.field_147369_b.field_71070_bA.func_75128_a(this.field_147369_b, false);
                ArrayList arraylist = new ArrayList();

                for (int i = 0; i < this.field_147369_b.field_71070_bA.field_75151_b.size(); ++i)
                {
                    arraylist.add(((Slot)this.field_147369_b.field_71070_bA.field_75151_b.get(i)).func_75211_c());
                }

                this.field_147369_b.func_71110_a(this.field_147369_b.field_71070_bA, arraylist);
            }
        }
    }

    public void func_147338_a(C11PacketEnchantItem p_147338_1_)
    {
        this.field_147369_b.func_143004_u();

        if (this.field_147369_b.field_71070_bA.field_75152_c == p_147338_1_.func_149539_c() && this.field_147369_b.field_71070_bA.func_75129_b(this.field_147369_b))
        {
            this.field_147369_b.field_71070_bA.func_75140_a(this.field_147369_b, p_147338_1_.func_149537_d());
            this.field_147369_b.field_71070_bA.func_75142_b();
        }
    }

    public void func_147344_a(C10PacketCreativeInventoryAction p_147344_1_)
    {
        if (this.field_147369_b.field_71134_c.func_73083_d())
        {
            boolean flag = p_147344_1_.func_149627_c() < 0;
            ItemStack itemstack = p_147344_1_.func_149625_d();
            boolean flag1 = p_147344_1_.func_149627_c() >= 1 && p_147344_1_.func_149627_c() < 36 + InventoryPlayer.func_70451_h();
            boolean flag2 = itemstack == null || itemstack.func_77973_b() != null;
            boolean flag3 = itemstack == null || itemstack.func_77960_j() >= 0 && itemstack.field_77994_a <= 64 && itemstack.field_77994_a > 0;

            if (flag1 && flag2 && flag3)
            {
                if (itemstack == null)
                {
                    this.field_147369_b.field_71069_bz.func_75141_a(p_147344_1_.func_149627_c(), (ItemStack)null);
                }
                else
                {
                    this.field_147369_b.field_71069_bz.func_75141_a(p_147344_1_.func_149627_c(), itemstack);
                }

                this.field_147369_b.field_71069_bz.func_75128_a(this.field_147369_b, true);
            }
            else if (flag && flag2 && flag3 && this.field_147375_m < 200)
            {
                this.field_147375_m += 20;
                EntityItem entityitem = this.field_147369_b.func_71019_a(itemstack, true);

                if (entityitem != null)
                {
                    entityitem.func_70288_d();
                }
            }
        }
    }

    public void func_147339_a(C0FPacketConfirmTransaction p_147339_1_)
    {
        Short oshort = (Short)this.field_147372_n.func_76041_a(this.field_147369_b.field_71070_bA.field_75152_c);

        if (oshort != null && p_147339_1_.func_149533_d() == oshort.shortValue() && this.field_147369_b.field_71070_bA.field_75152_c == p_147339_1_.func_149532_c() && !this.field_147369_b.field_71070_bA.func_75129_b(this.field_147369_b))
        {
            this.field_147369_b.field_71070_bA.func_75128_a(this.field_147369_b, true);
        }
    }

    public void func_147343_a(C12PacketUpdateSign p_147343_1_)
    {
        this.field_147369_b.func_143004_u();
        WorldServer worldserver = this.field_147367_d.func_71218_a(this.field_147369_b.field_71093_bK);

        if (worldserver.func_72899_e(p_147343_1_.func_149588_c(), p_147343_1_.func_149586_d(), p_147343_1_.func_149585_e()))
        {
            TileEntity tileentity = worldserver.func_147438_o(p_147343_1_.func_149588_c(), p_147343_1_.func_149586_d(), p_147343_1_.func_149585_e());

            if (tileentity instanceof TileEntitySign)
            {
                TileEntitySign tileentitysign = (TileEntitySign)tileentity;

                if (!tileentitysign.func_145914_a() || tileentitysign.func_145911_b() != this.field_147369_b)
                {
                    this.field_147367_d.func_71236_h("Player " + this.field_147369_b.func_70005_c_() + " just tried to change non-editable sign");
                    return;
                }
            }

            int i;
            int j;

            for (j = 0; j < 4; ++j)
            {
                boolean flag = true;

                if (p_147343_1_.func_149589_f()[j].length() > 15)
                {
                    flag = false;
                }
                else
                {
                    for (i = 0; i < p_147343_1_.func_149589_f()[j].length(); ++i)
                    {
                        if (!ChatAllowedCharacters.func_71566_a(p_147343_1_.func_149589_f()[j].charAt(i)))
                        {
                            flag = false;
                        }
                    }
                }

                if (!flag)
                {
                    p_147343_1_.func_149589_f()[j] = "!?";
                }
            }

            if (tileentity instanceof TileEntitySign)
            {
                j = p_147343_1_.func_149588_c();
                int k = p_147343_1_.func_149586_d();
                i = p_147343_1_.func_149585_e();
                TileEntitySign tileentitysign1 = (TileEntitySign)tileentity;
                System.arraycopy(p_147343_1_.func_149589_f(), 0, tileentitysign1.field_145915_a, 0, 4);
                tileentitysign1.func_70296_d();
                worldserver.func_147471_g(j, k, i);
            }
        }
    }

    public void func_147353_a(C00PacketKeepAlive p_147353_1_)
    {
        if (p_147353_1_.func_149460_c() == this.field_147378_h)
        {
            int i = (int)(this.func_147363_d() - this.field_147379_i);
            this.field_147369_b.field_71138_i = (this.field_147369_b.field_71138_i * 3 + i) / 4;
        }
    }

    private long func_147363_d()
    {
        return System.nanoTime() / 1000000L;
    }

    public void func_147348_a(C13PacketPlayerAbilities p_147348_1_)
    {
        this.field_147369_b.field_71075_bZ.field_75100_b = p_147348_1_.func_149488_d() && this.field_147369_b.field_71075_bZ.field_75101_c;
    }

    public void func_147341_a(C14PacketTabComplete p_147341_1_)
    {
        ArrayList arraylist = Lists.newArrayList();
        Iterator iterator = this.field_147367_d.func_71248_a(this.field_147369_b, p_147341_1_.func_149419_c()).iterator();

        while (iterator.hasNext())
        {
            String s = (String)iterator.next();
            arraylist.add(s);
        }

        this.field_147369_b.field_71135_a.func_147359_a(new S3APacketTabComplete((String[])arraylist.toArray(new String[arraylist.size()])));
    }

    public void func_147352_a(C15PacketClientSettings p_147352_1_)
    {
        this.field_147369_b.func_147100_a(p_147352_1_);
    }

    public void func_147349_a(C17PacketCustomPayload p_147349_1_)
    {
        PacketBuffer packetbuffer;
        ItemStack itemstack;
        ItemStack itemstack1;

        if ("MC|BEdit".equals(p_147349_1_.func_149559_c()))
        {
            packetbuffer = new PacketBuffer(Unpooled.wrappedBuffer(p_147349_1_.func_149558_e()));

            try
            {
                itemstack = packetbuffer.func_150791_c();

                if (itemstack == null)
                {
                    return;
                }

                if (!ItemWritableBook.func_150930_a(itemstack.func_77978_p()))
                {
                    throw new IOException("Invalid book tag!");
                }

                itemstack1 = this.field_147369_b.field_71071_by.func_70448_g();

                if (itemstack1 != null)
                {
                    if (itemstack.func_77973_b() == Items.WRITABLE_BOOK && itemstack.func_77973_b() == itemstack1.func_77973_b())
                    {
                        itemstack1.func_77983_a("pages", itemstack.func_77978_p().func_150295_c("pages", 8));
                    }

                    return;
                }
            }
            catch (Exception exception4)
            {
                field_147370_c.error("Couldn\'t handle book info", exception4);
                return;
            }
            finally
            {
                packetbuffer.release();
            }

            return;
        }
        else if ("MC|BSign".equals(p_147349_1_.func_149559_c()))
        {
            packetbuffer = new PacketBuffer(Unpooled.wrappedBuffer(p_147349_1_.func_149558_e()));

            try
            {
                itemstack = packetbuffer.func_150791_c();

                if (itemstack == null)
                {
                    return;
                }

                if (!ItemEditableBook.func_77828_a(itemstack.func_77978_p()))
                {
                    throw new IOException("Invalid book tag!");
                }

                itemstack1 = this.field_147369_b.field_71071_by.func_70448_g();

                if (itemstack1 != null)
                {
                    if (itemstack.func_77973_b() == Items.WRITTEN_BOOK && itemstack1.func_77973_b() == Items.WRITABLE_BOOK)
                    {
                        itemstack1.func_77983_a("author", new NBTTagString(this.field_147369_b.func_70005_c_()));
                        itemstack1.func_77983_a("title", new NBTTagString(itemstack.func_77978_p().func_74779_i("title")));
                        itemstack1.func_77983_a("pages", itemstack.func_77978_p().func_150295_c("pages", 8));
                        itemstack1.func_150996_a(Items.WRITTEN_BOOK);
                    }

                    return;
                }
            }
            catch (Exception exception3)
            {
                field_147370_c.error("Couldn\'t sign book", exception3);
                return;
            }
            finally
            {
                packetbuffer.release();
            }

            return;
        }
        else
        {
            DataInputStream datainputstream;
            int i;

            if ("MC|TrSel".equals(p_147349_1_.func_149559_c()))
            {
                try
                {
                    datainputstream = new DataInputStream(new ByteArrayInputStream(p_147349_1_.func_149558_e()));
                    i = datainputstream.readInt();
                    Container container = this.field_147369_b.field_71070_bA;

                    if (container instanceof ContainerMerchant)
                    {
                        ((ContainerMerchant)container).func_75175_c(i);
                    }
                }
                catch (Exception exception2)
                {
                    field_147370_c.error("Couldn\'t select trade", exception2);
                }
            }
            else if ("MC|AdvCdm".equals(p_147349_1_.func_149559_c()))
            {
                if (!this.field_147367_d.func_82356_Z())
                {
                    this.field_147369_b.func_145747_a(new ChatComponentTranslation("advMode.notEnabled", new Object[0]));
                }
                else if (this.field_147369_b.func_70003_b(2, "") && this.field_147369_b.field_71075_bZ.field_75098_d)
                {
                    packetbuffer = new PacketBuffer(Unpooled.wrappedBuffer(p_147349_1_.func_149558_e()));

                    try
                    {
                        byte b0 = packetbuffer.readByte();
                        CommandBlockLogic commandblocklogic = null;

                        if (b0 == 0)
                        {
                            TileEntity tileentity = this.field_147369_b.world.func_147438_o(packetbuffer.readInt(), packetbuffer.readInt(), packetbuffer.readInt());

                            if (tileentity instanceof TileEntityCommandBlock)
                            {
                                commandblocklogic = ((TileEntityCommandBlock)tileentity).func_145993_a();
                            }
                        }
                        else if (b0 == 1)
                        {
                            Entity entity = this.field_147369_b.world.func_73045_a(packetbuffer.readInt());

                            if (entity instanceof EntityMinecartCommandBlock)
                            {
                                commandblocklogic = ((EntityMinecartCommandBlock)entity).func_145822_e();
                            }
                        }

                        String s1 = packetbuffer.func_150789_c(packetbuffer.readableBytes());

                        if (commandblocklogic != null)
                        {
                            commandblocklogic.func_145752_a(s1);
                            commandblocklogic.func_145756_e();
                            this.field_147369_b.func_145747_a(new ChatComponentTranslation("advMode.setCommand.success", new Object[] {s1}));
                        }
                    }
                    catch (Exception exception1)
                    {
                        field_147370_c.error("Couldn\'t set command block", exception1);
                    }
                    finally
                    {
                        packetbuffer.release();
                    }
                }
                else
                {
                    this.field_147369_b.func_145747_a(new ChatComponentTranslation("advMode.notAllowed", new Object[0]));
                }
            }
            else if ("MC|Beacon".equals(p_147349_1_.func_149559_c()))
            {
                if (this.field_147369_b.field_71070_bA instanceof ContainerBeacon)
                {
                    try
                    {
                        datainputstream = new DataInputStream(new ByteArrayInputStream(p_147349_1_.func_149558_e()));
                        i = datainputstream.readInt();
                        int j = datainputstream.readInt();
                        ContainerBeacon containerbeacon = (ContainerBeacon)this.field_147369_b.field_71070_bA;
                        Slot slot = containerbeacon.func_75139_a(0);

                        if (slot.func_75216_d())
                        {
                            slot.func_75209_a(1);
                            TileEntityBeacon tileentitybeacon = containerbeacon.func_148327_e();
                            tileentitybeacon.func_146001_d(i);
                            tileentitybeacon.func_146004_e(j);
                            tileentitybeacon.func_70296_d();
                        }
                    }
                    catch (Exception exception)
                    {
                        field_147370_c.error("Couldn\'t set beacon", exception);
                    }
                }
            }
            else if ("MC|ItemName".equals(p_147349_1_.func_149559_c()) && this.field_147369_b.field_71070_bA instanceof ContainerRepair)
            {
                ContainerRepair containerrepair = (ContainerRepair)this.field_147369_b.field_71070_bA;

                if (p_147349_1_.func_149558_e() != null && p_147349_1_.func_149558_e().length >= 1)
                {
                    String s = ChatAllowedCharacters.func_71565_a(new String(p_147349_1_.func_149558_e(), Charsets.UTF_8));

                    if (s.length() <= 30)
                    {
                        containerrepair.func_82850_a(s);
                    }
                }
                else
                {
                    containerrepair.func_82850_a("");
                }
            }
        }
    }

    public void func_147232_a(EnumConnectionState p_147232_1_, EnumConnectionState p_147232_2_)
    {
        if (p_147232_2_ != EnumConnectionState.PLAY)
        {
            throw new IllegalStateException("Unexpected change in protocol!");
        }
    }

    static final class SwitchEnumState
        {
            static final int[] field_151290_a = new int[C16PacketClientStatus.EnumState.values().length];
            private static final String __OBFID = "CL_00001455";

            static
            {
                try
                {
                    field_151290_a[C16PacketClientStatus.EnumState.PERFORM_RESPAWN.ordinal()] = 1;
                }
                catch (NoSuchFieldError var3)
                {
                    ;
                }

                try
                {
                    field_151290_a[C16PacketClientStatus.EnumState.REQUEST_STATS.ordinal()] = 2;
                }
                catch (NoSuchFieldError var2)
                {
                    ;
                }

                try
                {
                    field_151290_a[C16PacketClientStatus.EnumState.OPEN_INVENTORY_ACHIEVEMENT.ordinal()] = 3;
                }
                catch (NoSuchFieldError var1)
                {
                    ;
                }
            }
        }
}