package net.minecraft.server.dedicated;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.ServerCommand;
import net.minecraft.crash.CrashReport;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.rcon.IServer;
import net.minecraft.network.rcon.RConThreadMain;
import net.minecraft.network.rcon.RConThreadQuery;
import net.minecraft.profiler.PlayerUsageSnooper;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.gui.MinecraftServerGui;
import net.minecraft.server.management.PreYggdrasilConverter;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.CryptManager;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.WorldType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SideOnly(Side.SERVER)
public class DedicatedServer extends MinecraftServer implements IServer
{
    private static final Logger field_155771_h = LogManager.getLogger();
    public final List field_71341_l = Collections.synchronizedList(new ArrayList());
    private RConThreadQuery field_71342_m;
    private RConThreadMain field_71339_n;
    private PropertyManager field_71340_o;
    private boolean field_71338_p;
    private WorldSettings.GameType field_71337_q;
    private boolean withGui;
    private static final String __OBFID = "CL_00001784";

    public DedicatedServer(File p_i1508_1_)
    {
        super(p_i1508_1_, Proxy.NO_PROXY);
        Thread thread = new Thread("Server Infinisleeper")
        {
            private static final String __OBFID = "CL_00001787";
            {
                this.setDaemon(true);
                this.start();
            }
            public void run()
            {
                while (true)
                {
                    try
                    {
                        while (true)
                        {
                            Thread.sleep(2147483647L);
                        }
                    }
                    catch (InterruptedException interruptedexception)
                    {
                        ;
                    }
                }
            }
        };
    }

    protected boolean func_71197_b() throws IOException
    {
        Thread thread = new Thread("Server console handler")
        {
            private static final String __OBFID = "CL_00001786";
            public void run()
            {
                BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(System.in));
                String s4;

                try
                {
                    while (!DedicatedServer.this.func_71241_aa() && DedicatedServer.this.func_71278_l() && (s4 = bufferedreader.readLine()) != null)
                    {
                        DedicatedServer.this.func_71331_a(s4, DedicatedServer.this);
                    }
                }
                catch (IOException ioexception1)
                {
                    DedicatedServer.field_155771_h.error("Exception handling console input", ioexception1);
                }
            }
        };
        thread.setDaemon(true);
        thread.start();
        field_155771_h.info("Starting minecraft server version 1.7.10");

        if (Runtime.getRuntime().maxMemory() / 1024L / 1024L < 512L)
        {
            field_155771_h.warn("To start the server with more ram, launch it as \"java -Xmx1024M -Xms1024M -jar minecraft_server.jar\"");
        }

        field_155771_h.info("Loading properties");
        this.field_71340_o = new PropertyManager(new File("server.properties"));
        {
            if (this.func_71264_H())
            {
                this.func_71189_e("127.0.0.1");
            }
            else
            {
                this.func_71229_d(this.field_71340_o.func_73670_a("online-mode", false));
                this.func_71189_e(this.field_71340_o.func_73671_a("server-ip", ""));
            }

            this.func_71251_e(this.field_71340_o.func_73670_a("spawn-animals", true));
            this.func_71257_f(this.field_71340_o.func_73670_a("spawn-npcs", true));
            this.func_71188_g(this.field_71340_o.func_73670_a("pvp", false));
            this.func_71245_h(this.field_71340_o.func_73670_a("allow-flight", false));
            this.func_155759_m(this.field_71340_o.func_73671_a("resource-pack", ""));
            this.func_71205_p(this.field_71340_o.func_73671_a("motd", "A Minecraft Server"));
            this.func_104055_i(this.field_71340_o.func_73670_a("force-gamemode", false));
            this.func_143006_e(this.field_71340_o.func_73669_a("player-idle-timeout", 0));

            if (this.field_71340_o.func_73669_a("difficulty", 1) < 0)
            {
                this.field_71340_o.func_73667_a("difficulty", Integer.valueOf(0));
            }
            else if (this.field_71340_o.func_73669_a("difficulty", 1) > 3)
            {
                this.field_71340_o.func_73667_a("difficulty", Integer.valueOf(3));
            }

            this.field_71338_p = this.field_71340_o.func_73670_a("generate-structures", true);
            int i = this.field_71340_o.func_73669_a("gamemode", WorldSettings.GameType.SURVIVAL.func_77148_a());
            this.field_71337_q = WorldSettings.func_77161_a(i);
            field_155771_h.info("Default game type: " + this.field_71337_q);
            InetAddress inetaddress = null;

            if (this.func_71211_k().length() > 0)
            {
                inetaddress = InetAddress.getByName(this.func_71211_k());
            }

            if (this.func_71215_F() < 0)
            {
                this.func_71208_b(this.field_71340_o.func_73669_a("server-port", 25565));
            }

            field_155771_h.info("Generating keypair");
            this.func_71253_a(CryptManager.func_75891_b());
            field_155771_h.info("Starting Minecraft server on " + (this.func_71211_k().length() == 0 ? "*" : this.func_71211_k()) + ":" + this.func_71215_F());

            try
            {
                this.func_147137_ag().func_151265_a(inetaddress, this.func_71215_F());
            }
            catch (IOException ioexception)
            {
                field_155771_h.warn("**** FAILED TO BIND TO PORT!");
                field_155771_h.warn("The exception was: {}", new Object[] {ioexception.toString()});
                field_155771_h.warn("Perhaps a server is already running on that port?");
                return false;
            }

            if (!this.func_71266_T())
            {
                field_155771_h.warn("**** SERVER IS RUNNING IN OFFLINE/INSECURE MODE!");
                field_155771_h.warn("The server will make no attempt to authenticate usernames. Beware.");
                field_155771_h.warn("While this makes the game possible to play without internet access, it also opens up the ability for hackers to connect with any username they choose.");
                field_155771_h.warn("To change this, set \"online-mode\" to \"true\" in the server.properties file.");
            }

            if (this.func_152368_aE())
            {
                this.func_152358_ax().func_152658_c();
            }

            if (!PreYggdrasilConverter.func_152714_a(this.field_71340_o))
            {
                return false;
            }
            else
            {
                this.func_152361_a(new DedicatedPlayerList(this));
                long j = System.nanoTime();

                if (this.func_71270_I() == null)
                {
                    this.func_71261_m(this.field_71340_o.func_73671_a("level-name", "world"));
                }

                String s = this.field_71340_o.func_73671_a("level-seed", "");
                String s1 = this.field_71340_o.func_73671_a("level-type", "DEFAULT");
                String s2 = this.field_71340_o.func_73671_a("generator-settings", "");
                long k = (new Random()).nextLong();

                if (s.length() > 0)
                {
                    try
                    {
                        long l = Long.parseLong(s);

                        if (l != 0L)
                        {
                            k = l;
                        }
                    }
                    catch (NumberFormatException numberformatexception)
                    {
                        k = (long)s.hashCode();
                    }
                }

                WorldType worldtype = WorldType.func_77130_a(s1);

                if (worldtype == null)
                {
                    worldtype = WorldType.field_77137_b;
                }

                this.func_147136_ar();
                this.func_82356_Z();
                this.func_110455_j();
                this.func_70002_Q();
                this.func_71191_d(this.field_71340_o.func_73669_a("max-build-height", 256));
                this.func_71191_d((this.func_71207_Z() + 8) / 16 * 16);
                this.func_71191_d(MathHelper.func_76125_a(this.func_71207_Z(), 64, 256));
                this.field_71340_o.func_73667_a("max-build-height", Integer.valueOf(this.func_71207_Z()));
                field_155771_h.info("Preparing level \"" + this.func_71270_I() + "\"");
                this.func_71247_a(this.func_71270_I(), this.func_71270_I(), k, worldtype, s2);
                long i1 = System.nanoTime() - j;
                String s3 = String.format("%.3fs", new Object[] {Double.valueOf((double)i1 / 1.0E9D)});
                field_155771_h.info("Done (" + s3 + ")! For help, type \"help\" or \"?\"");

                if (this.field_71340_o.func_73670_a("enable-query", false))
                {
                    field_155771_h.info("Starting GS4 status listener");
                    this.field_71342_m = new RConThreadQuery(this);
                    this.field_71342_m.func_72602_a();
                }

                if (this.field_71340_o.func_73670_a("enable-rcon", false))
                {
                    field_155771_h.info("Starting remote control listener");
                    this.field_71339_n = new RConThreadMain(this);
                    this.field_71339_n.func_72602_a();
                }

                return true;
            }
        }
    }

    public boolean func_71225_e()
    {
        return this.field_71338_p;
    }

    public WorldSettings.GameType func_71265_f()
    {
        return this.field_71337_q;
    }

    public EnumDifficulty func_147135_j()
    {
        return EnumDifficulty.func_151523_a(this.field_71340_o.func_73669_a("difficulty", 1));
    }

    public boolean func_71199_h()
    {
        return this.field_71340_o.func_73670_a("hardcore", false);
    }

    protected void func_71228_a(CrashReport p_71228_1_) {}

    public CrashReport func_71230_b(CrashReport p_71230_1_)
    {
        p_71230_1_ = super.func_71230_b(p_71230_1_);
        p_71230_1_.func_85056_g().func_71500_a("Is Modded", new Callable()
        {
            private static final String __OBFID = "CL_00001785";
            public String call()
            {
                String s = DedicatedServer.this.getServerModName();
                return !s.equals("vanilla") ? "Definitely; Server brand changed to \'" + s + "\'" : "Unknown (can\'t tell)";
            }
        });
        p_71230_1_.func_85056_g().func_71500_a("Type", new Callable()
        {
            private static final String __OBFID = "CL_00001788";
            public String call()
            {
                return "Dedicated Server (map_server.txt)";
            }
        });
        return p_71230_1_;
    }

    protected void func_71240_o()
    {
        System.exit(0);
    }

    public void func_71190_q()
    {
        super.func_71190_q();
        this.func_71333_ah();
    }

    public boolean func_71255_r()
    {
        return this.field_71340_o.func_73670_a("allow-nether", true);
    }

    public boolean func_71193_K()
    {
        return this.field_71340_o.func_73670_a("spawn-monsters", true);
    }

    public void func_70000_a(PlayerUsageSnooper p_70000_1_)
    {
        p_70000_1_.func_152768_a("whitelist_enabled", Boolean.valueOf(this.func_71203_ab().func_72383_n()));
        p_70000_1_.func_152768_a("whitelist_count", Integer.valueOf(this.func_71203_ab().func_152598_l().length));
        super.func_70000_a(p_70000_1_);
    }

    public boolean func_70002_Q()
    {
        return this.field_71340_o.func_73670_a("snooper-enabled", true);
    }

    public void func_71331_a(String p_71331_1_, ICommandSender p_71331_2_)
    {
        this.field_71341_l.add(new ServerCommand(p_71331_1_, p_71331_2_));
    }

    public void func_71333_ah()
    {
        while (!this.field_71341_l.isEmpty())
        {
            ServerCommand servercommand = (ServerCommand)this.field_71341_l.remove(0);
            this.func_71187_D().func_71556_a(servercommand.field_73701_b, servercommand.field_73702_a);
        }
    }

    public boolean func_71262_S()
    {
        return true;
    }

    public DedicatedPlayerList func_71203_ab()
    {
        return (DedicatedPlayerList)super.func_71203_ab();
    }

    public int func_71327_a(String p_71327_1_, int p_71327_2_)
    {
        return this.field_71340_o.func_73669_a(p_71327_1_, p_71327_2_);
    }

    public String func_71330_a(String p_71330_1_, String p_71330_2_)
    {
        return this.field_71340_o.func_73671_a(p_71330_1_, p_71330_2_);
    }

    public boolean func_71332_a(String p_71332_1_, boolean p_71332_2_)
    {
        return this.field_71340_o.func_73670_a(p_71332_1_, p_71332_2_);
    }

    public void func_71328_a(String p_71328_1_, Object p_71328_2_)
    {
        this.field_71340_o.func_73667_a(p_71328_1_, p_71328_2_);
    }

    public void func_71326_a()
    {
        this.field_71340_o.func_73668_b();
    }

    public String func_71329_c()
    {
        File file1 = this.field_71340_o.func_73665_c();
        return file1 != null ? file1.getAbsolutePath() : "No settings file";
    }

    public void func_120011_ar()
    {
        MinecraftServerGui.func_120016_a(this);
        this.withGui = true;
    }

    public boolean func_71279_ae()
    {
        return this.withGui;
    }

    public String func_71206_a(WorldSettings.GameType p_71206_1_, boolean p_71206_2_)
    {
        return "";
    }

    public boolean func_82356_Z()
    {
        return this.field_71340_o.func_73670_a("enable-command-block", false);
    }

    public int func_82357_ak()
    {
        return this.field_71340_o.func_73669_a("spawn-protection", super.func_82357_ak());
    }

    public boolean func_96290_a(World p_96290_1_, int p_96290_2_, int p_96290_3_, int p_96290_4_, EntityPlayer p_96290_5_)
    {
        if (p_96290_1_.field_73011_w.field_76574_g != 0)
        {
            return false;
        }
        else if (this.func_71203_ab().func_152603_m().func_152690_d())
        {
            return false;
        }
        else if (this.func_71203_ab().func_152596_g(p_96290_5_.func_146103_bH()))
        {
            return false;
        }
        else if (this.func_82357_ak() <= 0)
        {
            return false;
        }
        else
        {
            ChunkCoordinates chunkcoordinates = p_96290_1_.func_72861_E();
            int l = MathHelper.func_76130_a(p_96290_2_ - chunkcoordinates.field_71574_a);
            int i1 = MathHelper.func_76130_a(p_96290_4_ - chunkcoordinates.field_71573_c);
            int j1 = Math.max(l, i1);
            return j1 <= this.func_82357_ak();
        }
    }

    public int func_110455_j()
    {
        return this.field_71340_o.func_73669_a("op-permission-level", 4);
    }

    public void func_143006_e(int p_143006_1_)
    {
        super.func_143006_e(p_143006_1_);
        this.field_71340_o.func_73667_a("player-idle-timeout", Integer.valueOf(p_143006_1_));
        this.func_71326_a();
    }

    public boolean func_152363_m()
    {
        return this.field_71340_o.func_73670_a("broadcast-rcon-to-ops", true);
    }

    public boolean func_147136_ar()
    {
        return this.field_71340_o.func_73670_a("announce-player-achievements", true);
    }

    protected boolean func_152368_aE() throws IOException
    {
        boolean flag = false;
        int i;

        for (i = 0; !flag && i <= 2; ++i)
        {
            if (i > 0)
            {
                field_155771_h.warn("Encountered a problem while converting the user banlist, retrying in a few seconds");
                this.func_152369_aG();
            }

            flag = PreYggdrasilConverter.func_152724_a(this);
        }

        boolean flag1 = false;

        for (i = 0; !flag1 && i <= 2; ++i)
        {
            if (i > 0)
            {
                field_155771_h.warn("Encountered a problem while converting the ip banlist, retrying in a few seconds");
                this.func_152369_aG();
            }

            flag1 = PreYggdrasilConverter.func_152722_b(this);
        }

        boolean flag2 = false;

        for (i = 0; !flag2 && i <= 2; ++i)
        {
            if (i > 0)
            {
                field_155771_h.warn("Encountered a problem while converting the op list, retrying in a few seconds");
                this.func_152369_aG();
            }

            flag2 = PreYggdrasilConverter.func_152718_c(this);
        }

        boolean flag3 = false;

        for (i = 0; !flag3 && i <= 2; ++i)
        {
            if (i > 0)
            {
                field_155771_h.warn("Encountered a problem while converting the whitelist, retrying in a few seconds");
                this.func_152369_aG();
            }

            flag3 = PreYggdrasilConverter.func_152710_d(this);
        }

        boolean flag4 = false;

        for (i = 0; !flag4 && i <= 2; ++i)
        {
            if (i > 0)
            {
                field_155771_h.warn("Encountered a problem while converting the player save files, retrying in a few seconds");
                this.func_152369_aG();
            }

            flag4 = PreYggdrasilConverter.func_152723_a(this, this.field_71340_o);
        }

        return flag || flag1 || flag2 || flag3 || flag4;
    }

    private void func_152369_aG()
    {
        try
        {
            Thread.sleep(5000L);
        }
        catch (InterruptedException interruptedexception)
        {
            ;
        }
    }
}