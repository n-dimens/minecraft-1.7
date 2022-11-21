package net.minecraft.server;

import com.google.common.base.Charsets;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.GameProfileRepository;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.base64.Base64;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.Proxy;
import java.security.KeyPair;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.Callable;
import javax.imageio.ImageIO;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandManager;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.crash.CrashReport;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Bootstrap;
import net.minecraft.network.NetworkSystem;
import net.minecraft.network.ServerStatusResponse;
import net.minecraft.network.play.server.S03PacketTimeUpdate;
import net.minecraft.network.rcon.RConConsoleSource;
import net.minecraft.profiler.IPlayerUsage;
import net.minecraft.profiler.PlayerUsageSnooper;
import net.minecraft.profiler.Profiler;
import net.minecraft.server.dedicated.DedicatedServer;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.server.management.PlayerProfileCache;
import net.minecraft.server.management.ServerConfigurationManager;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ReportedException;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.MinecraftException;
import net.minecraft.world.World;
import net.minecraft.world.WorldManager;
import net.minecraft.world.WorldServer;
import net.minecraft.world.WorldServerMulti;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.WorldType;
import net.minecraft.world.chunk.storage.AnvilSaveConverter;
import net.minecraft.world.demo.DemoWorldServer;
import net.minecraft.world.storage.ISaveFormat;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraft.world.storage.WorldInfo;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class MinecraftServer implements ICommandSender, Runnable, IPlayerUsage
{
    private static final Logger field_147145_h = LogManager.getLogger();
    public static final File field_152367_a = new File("usercache.json");
    private static MinecraftServer field_71309_l;
    private final ISaveFormat field_71310_m;
    private final PlayerUsageSnooper field_71307_n = new PlayerUsageSnooper("server", this, func_130071_aq());
    private final File field_71308_o;
    private final List field_71322_p = new ArrayList();
    private final ICommandManager field_71321_q;
    public final Profiler field_71304_b = new Profiler();
    private final NetworkSystem field_147144_o;
    private final ServerStatusResponse field_147147_p = new ServerStatusResponse();
    private final Random field_147146_q = new Random();
    @SideOnly(Side.SERVER)
    private String field_71320_r;
    private int field_71319_s = -1;
    public WorldServer[] field_71305_c;
    private ServerConfigurationManager field_71318_t;
    private boolean field_71317_u = true;
    private boolean field_71316_v;
    private int field_71315_w;
    protected final Proxy field_110456_c;
    public String field_71302_d;
    public int field_71303_e;
    private boolean field_71325_x;
    private boolean field_71324_y;
    private boolean field_71323_z;
    private boolean field_71284_A;
    private boolean field_71285_B;
    private String field_71286_C;
    private int field_71280_D;
    private int field_143008_E = 0;
    public final long[] field_71311_j = new long[100];
    public long[][] field_71312_k;
    private KeyPair field_71292_I;
    private String field_71293_J;
    private String field_71294_K;
    @SideOnly(Side.CLIENT)
    private String field_71287_L;
    private boolean field_71288_M;
    private boolean field_71289_N;
    private boolean field_71290_O;
    private String field_147141_M = "";
    private boolean field_71296_Q;
    private long field_71299_R;
    private String field_71298_S;
    private boolean field_71295_T;
    private boolean field_104057_T;
    private final YggdrasilAuthenticationService field_152364_T;
    private final MinecraftSessionService field_147143_S;
    private long field_147142_T = 0L;
    private final GameProfileRepository field_152365_W;
    private final PlayerProfileCache field_152366_X;
    private static final String __OBFID = "CL_00001462";

    public MinecraftServer(File p_i45281_1_, Proxy p_i45281_2_)
    {
        this.field_152366_X = new PlayerProfileCache(this, field_152367_a);
        field_71309_l = this;
        this.field_110456_c = p_i45281_2_;
        this.field_71308_o = p_i45281_1_;
        this.field_147144_o = new NetworkSystem(this);
        this.field_71321_q = new ServerCommandManager();
        this.field_71310_m = new AnvilSaveConverter(p_i45281_1_);
        this.field_152364_T = new YggdrasilAuthenticationService(p_i45281_2_, UUID.randomUUID().toString());
        this.field_147143_S = this.field_152364_T.createMinecraftSessionService();
        this.field_152365_W = this.field_152364_T.createProfileRepository();
    }

    protected abstract boolean func_71197_b() throws IOException;

    protected void func_71237_c(String p_71237_1_)
    {
        if (this.func_71254_M().func_75801_b(p_71237_1_))
        {
            field_147145_h.info("Converting map!");
            this.func_71192_d("menu.convertingLevel");
            this.func_71254_M().func_75805_a(p_71237_1_, new IProgressUpdate()
            {
                private long field_96245_b = System.currentTimeMillis();
                private static final String __OBFID = "CL_00001417";
                public void func_73720_a(String p_73720_1_) {}
                public void func_73718_a(int p_73718_1_)
                {
                    if (System.currentTimeMillis() - this.field_96245_b >= 1000L)
                    {
                        this.field_96245_b = System.currentTimeMillis();
                        MinecraftServer.field_147145_h.info("Converting... " + p_73718_1_ + "%");
                    }
                }
                @SideOnly(Side.CLIENT)
                public void func_73721_b(String p_73721_1_) {}
                @SideOnly(Side.CLIENT)
                public void func_146586_a() {}
                public void func_73719_c(String p_73719_1_) {}
            });
        }
    }

    protected synchronized void func_71192_d(String p_71192_1_)
    {
        this.field_71298_S = p_71192_1_;
    }

    @SideOnly(Side.CLIENT)

    public synchronized String func_71195_b_()
    {
        return this.field_71298_S;
    }

    protected void func_71247_a(String p_71247_1_, String p_71247_2_, long p_71247_3_, WorldType p_71247_5_, String p_71247_6_)
    {
        this.func_71237_c(p_71247_1_);
        this.func_71192_d("menu.loadingLevel");
        this.field_71305_c = new WorldServer[3];
        this.field_71312_k = new long[this.field_71305_c.length][100];
        ISaveHandler isavehandler = this.field_71310_m.func_75804_a(p_71247_1_, true);
        WorldInfo worldinfo = isavehandler.func_75757_d();
        WorldSettings worldsettings;

        if (worldinfo == null)
        {
            worldsettings = new WorldSettings(p_71247_3_, this.func_71265_f(), this.func_71225_e(), this.func_71199_h(), p_71247_5_);
            worldsettings.func_82750_a(p_71247_6_);
        }
        else
        {
            worldsettings = new WorldSettings(worldinfo);
        }

        if (this.field_71289_N)
        {
            worldsettings.func_77159_a();
        }

        for (int j = 0; j < this.field_71305_c.length; ++j)
        {
            byte b0 = 0;

            if (j == 1)
            {
                b0 = -1;
            }

            if (j == 2)
            {
                b0 = 1;
            }

            if (j == 0)
            {
                if (this.func_71242_L())
                {
                    this.field_71305_c[j] = new DemoWorldServer(this, isavehandler, p_71247_2_, b0, this.field_71304_b);
                }
                else
                {
                    this.field_71305_c[j] = new WorldServer(this, isavehandler, p_71247_2_, b0, worldsettings, this.field_71304_b);
                }
            }
            else
            {
                this.field_71305_c[j] = new WorldServerMulti(this, isavehandler, p_71247_2_, b0, worldsettings, this.field_71305_c[0], this.field_71304_b);
            }

            this.field_71305_c[j].func_72954_a(new WorldManager(this, this.field_71305_c[j]));

            if (!this.func_71264_H())
            {
                this.field_71305_c[j].func_72912_H().func_76060_a(this.func_71265_f());
            }

            this.field_71318_t.func_72364_a(this.field_71305_c);
        }

        this.func_147139_a(this.func_147135_j());
        this.func_71222_d();
    }

    protected void func_71222_d()
    {
        boolean flag = true;
        boolean flag1 = true;
        boolean flag2 = true;
        boolean flag3 = true;
        int i = 0;
        this.func_71192_d("menu.generatingTerrain");
        byte b0 = 0;
        field_147145_h.info("Preparing start region for level " + b0);
        WorldServer worldserver = this.field_71305_c[b0];
        ChunkCoordinates chunkcoordinates = worldserver.func_72861_E();
        long j = func_130071_aq();

        for (int k = -192; k <= 192 && this.func_71278_l(); k += 16)
        {
            for (int l = -192; l <= 192 && this.func_71278_l(); l += 16)
            {
                long i1 = func_130071_aq();

                if (i1 - j > 1000L)
                {
                    this.func_71216_a_("Preparing spawn area", i * 100 / 625);
                    j = i1;
                }

                ++i;
                worldserver.field_73059_b.func_73158_c(chunkcoordinates.field_71574_a + k >> 4, chunkcoordinates.field_71573_c + l >> 4);
            }
        }

        this.func_71243_i();
    }

    public abstract boolean func_71225_e();

    public abstract WorldSettings.GameType func_71265_f();

    public abstract EnumDifficulty func_147135_j();

    public abstract boolean func_71199_h();

    public abstract int func_110455_j();

    public abstract boolean func_152363_m();

    protected void func_71216_a_(String p_71216_1_, int p_71216_2_)
    {
        this.field_71302_d = p_71216_1_;
        this.field_71303_e = p_71216_2_;
        field_147145_h.info(p_71216_1_ + ": " + p_71216_2_ + "%");
    }

    protected void func_71243_i()
    {
        this.field_71302_d = null;
        this.field_71303_e = 0;
    }

    protected void func_71267_a(boolean p_71267_1_)
    {
        if (!this.field_71290_O)
        {
            WorldServer[] aworldserver = this.field_71305_c;
            int i = aworldserver.length;

            for (int j = 0; j < i; ++j)
            {
                WorldServer worldserver = aworldserver[j];

                if (worldserver != null)
                {
                    if (!p_71267_1_)
                    {
                        field_147145_h.info("Saving chunks for level \'" + worldserver.func_72912_H().func_76065_j() + "\'/" + worldserver.field_73011_w.func_80007_l());
                    }

                    try
                    {
                        worldserver.func_73044_a(true, (IProgressUpdate)null);
                    }
                    catch (MinecraftException minecraftexception)
                    {
                        field_147145_h.warn(minecraftexception.getMessage());
                    }
                }
            }
        }
    }

    public void func_71260_j()
    {
        if (!this.field_71290_O)
        {
            field_147145_h.info("Stopping server");

            if (this.func_147137_ag() != null)
            {
                this.func_147137_ag().func_151268_b();
            }

            if (this.field_71318_t != null)
            {
                field_147145_h.info("Saving players");
                this.field_71318_t.func_72389_g();
                this.field_71318_t.func_72392_r();
            }

            if (this.field_71305_c != null)
            {
                field_147145_h.info("Saving worlds");
                this.func_71267_a(false);

                for (int i = 0; i < this.field_71305_c.length; ++i)
                {
                    WorldServer worldserver = this.field_71305_c[i];
                    worldserver.func_73041_k();
                }
            }

            if (this.field_71307_n.func_76468_d())
            {
                this.field_71307_n.func_76470_e();
            }
        }
    }

    public boolean func_71278_l()
    {
        return this.field_71317_u;
    }

    public void func_71263_m()
    {
        this.field_71317_u = false;
    }

    public void run()
    {
        try
        {
            if (this.func_71197_b())
            {
                long i = func_130071_aq();
                long l = 0L;
                this.field_147147_p.func_151315_a(new ChatComponentText(this.field_71286_C));
                this.field_147147_p.func_151321_a(new ServerStatusResponse.MinecraftProtocolVersionIdentifier("1.7.10", 5));
                this.func_147138_a(this.field_147147_p);

                while (this.field_71317_u)
                {
                    long j = func_130071_aq();
                    long k = j - i;

                    if (k > 2000L && i - this.field_71299_R >= 15000L)
                    {
                        field_147145_h.warn("Can\'t keep up! Did the system time change, or is the server overloaded? Running {}ms behind, skipping {} tick(s)", new Object[] {Long.valueOf(k), Long.valueOf(k / 50L)});
                        k = 2000L;
                        this.field_71299_R = i;
                    }

                    if (k < 0L)
                    {
                        field_147145_h.warn("Time ran backwards! Did the system time change?");
                        k = 0L;
                    }

                    l += k;
                    i = j;

                    if (this.field_71305_c[0].func_73056_e())
                    {
                        this.func_71217_p();
                        l = 0L;
                    }
                    else
                    {
                        while (l > 50L)
                        {
                            l -= 50L;
                            this.func_71217_p();
                        }
                    }

                    Thread.sleep(Math.max(1L, 50L - l));
                    this.field_71296_Q = true;
                }
            }
            else
            {
                this.func_71228_a((CrashReport)null);
            }
        }
        catch (Throwable throwable1)
        {
            field_147145_h.error("Encountered an unexpected exception", throwable1);
            CrashReport crashreport = null;

            if (throwable1 instanceof ReportedException)
            {
                crashreport = this.func_71230_b(((ReportedException)throwable1).func_71575_a());
            }
            else
            {
                crashreport = this.func_71230_b(new CrashReport("Exception in server tick loop", throwable1));
            }

            File file1 = new File(new File(this.func_71238_n(), "crash-reports"), "crash-" + (new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss")).format(new Date()) + "-server.txt");

            if (crashreport.func_147149_a(file1))
            {
                field_147145_h.error("This crash report has been saved to: " + file1.getAbsolutePath());
            }
            else
            {
                field_147145_h.error("We were unable to save this crash report to disk.");
            }

            this.func_71228_a(crashreport);
        }
        finally
        {
            try
            {
                this.func_71260_j();
                this.field_71316_v = true;
            }
            catch (Throwable throwable)
            {
                field_147145_h.error("Exception stopping the server", throwable);
            }
            finally
            {
                this.func_71240_o();
            }
        }
    }

    private void func_147138_a(ServerStatusResponse p_147138_1_)
    {
        File file1 = this.func_71209_f("server-icon.png");

        if (file1.isFile())
        {
            ByteBuf bytebuf = Unpooled.buffer();

            try
            {
                BufferedImage bufferedimage = ImageIO.read(file1);
                Validate.validState(bufferedimage.getWidth() == 64, "Must be 64 pixels wide", new Object[0]);
                Validate.validState(bufferedimage.getHeight() == 64, "Must be 64 pixels high", new Object[0]);
                ImageIO.write(bufferedimage, "PNG", new ByteBufOutputStream(bytebuf));
                ByteBuf bytebuf1 = Base64.encode(bytebuf);
                p_147138_1_.func_151320_a("data:image/png;base64," + bytebuf1.toString(Charsets.UTF_8));
            }
            catch (Exception exception)
            {
                field_147145_h.error("Couldn\'t load server icon", exception);
            }
            finally
            {
                bytebuf.release();
            }
        }
    }

    protected File func_71238_n()
    {
        return new File(".");
    }

    protected void func_71228_a(CrashReport p_71228_1_) {}

    protected void func_71240_o() {}

    public void func_71217_p()
    {
        long i = System.nanoTime();
        ++this.field_71315_w;

        if (this.field_71295_T)
        {
            this.field_71295_T = false;
            this.field_71304_b.field_76327_a = true;
            this.field_71304_b.func_76317_a();
        }

        this.field_71304_b.func_76320_a("root");
        this.func_71190_q();

        if (i - this.field_147142_T >= 5000000000L)
        {
            this.field_147142_T = i;
            this.field_147147_p.func_151319_a(new ServerStatusResponse.PlayerCountData(this.func_71275_y(), this.func_71233_x()));
            GameProfile[] agameprofile = new GameProfile[Math.min(this.func_71233_x(), 12)];
            int j = MathHelper.func_76136_a(this.field_147146_q, 0, this.func_71233_x() - agameprofile.length);

            for (int k = 0; k < agameprofile.length; ++k)
            {
                agameprofile[k] = ((EntityPlayerMP)this.field_71318_t.field_72404_b.get(j + k)).func_146103_bH();
            }

            Collections.shuffle(Arrays.asList(agameprofile));
            this.field_147147_p.func_151318_b().func_151330_a(agameprofile);
        }

        if (this.field_71315_w % 900 == 0)
        {
            this.field_71304_b.func_76320_a("save");
            this.field_71318_t.func_72389_g();
            this.func_71267_a(true);
            this.field_71304_b.func_76319_b();
        }

        this.field_71304_b.func_76320_a("tallying");
        this.field_71311_j[this.field_71315_w % 100] = System.nanoTime() - i;
        this.field_71304_b.func_76319_b();
        this.field_71304_b.func_76320_a("snooper");

        if (!this.field_71307_n.func_76468_d() && this.field_71315_w > 100)
        {
            this.field_71307_n.func_76463_a();
        }

        if (this.field_71315_w % 6000 == 0)
        {
            this.field_71307_n.func_76471_b();
        }

        this.field_71304_b.func_76319_b();
        this.field_71304_b.func_76319_b();
    }

    public void func_71190_q()
    {
        this.field_71304_b.func_76320_a("levels");
        int i;

        for (i = 0; i < this.field_71305_c.length; ++i)
        {
            long j = System.nanoTime();

            if (i == 0 || this.func_71255_r())
            {
                WorldServer worldserver = this.field_71305_c[i];
                this.field_71304_b.func_76320_a(worldserver.func_72912_H().func_76065_j());
                this.field_71304_b.func_76320_a("pools");
                this.field_71304_b.func_76319_b();

                if (this.field_71315_w % 20 == 0)
                {
                    this.field_71304_b.func_76320_a("timeSync");
                    this.field_71318_t.func_148537_a(new S03PacketTimeUpdate(worldserver.func_82737_E(), worldserver.func_72820_D(), worldserver.func_82736_K().getBooleanValue("doDaylightCycle")), worldserver.field_73011_w.field_76574_g);
                    this.field_71304_b.func_76319_b();
                }

                this.field_71304_b.func_76320_a("tick");
                CrashReport crashreport;

                try
                {
                    worldserver.func_72835_b();
                }
                catch (Throwable throwable1)
                {
                    crashreport = CrashReport.func_85055_a(throwable1, "Exception ticking world");
                    worldserver.func_72914_a(crashreport);
                    throw new ReportedException(crashreport);
                }

                try
                {
                    worldserver.func_72939_s();
                }
                catch (Throwable throwable)
                {
                    crashreport = CrashReport.func_85055_a(throwable, "Exception ticking world entities");
                    worldserver.func_72914_a(crashreport);
                    throw new ReportedException(crashreport);
                }

                this.field_71304_b.func_76319_b();
                this.field_71304_b.func_76320_a("tracker");
                worldserver.func_73039_n().func_72788_a();
                this.field_71304_b.func_76319_b();
                this.field_71304_b.func_76319_b();
            }

            this.field_71312_k[i][this.field_71315_w % 100] = System.nanoTime() - j;
        }

        this.field_71304_b.func_76318_c("connection");
        this.func_147137_ag().func_151269_c();
        this.field_71304_b.func_76318_c("players");
        this.field_71318_t.func_72374_b();
        this.field_71304_b.func_76318_c("tickables");

        for (i = 0; i < this.field_71322_p.size(); ++i)
        {
            ((IUpdatePlayerListBox)this.field_71322_p.get(i)).func_73660_a();
        }

        this.field_71304_b.func_76319_b();
    }

    public boolean func_71255_r()
    {
        return true;
    }

    public void func_71256_s()
    {
        (new Thread("Server thread")
        {
            private static final String __OBFID = "CL_00001418";
            public void run()
            {
                MinecraftServer.this.run();
            }
        }).start();
    }

    public File func_71209_f(String p_71209_1_)
    {
        return new File(this.func_71238_n(), p_71209_1_);
    }

    public void func_71236_h(String p_71236_1_)
    {
        field_147145_h.warn(p_71236_1_);
    }

    public WorldServer func_71218_a(int p_71218_1_)
    {
        return p_71218_1_ == -1 ? this.field_71305_c[1] : (p_71218_1_ == 1 ? this.field_71305_c[2] : this.field_71305_c[0]);
    }

    public String func_71249_w()
    {
        return "1.7.10";
    }

    public int func_71233_x()
    {
        return this.field_71318_t.func_72394_k();
    }

    public int func_71275_y()
    {
        return this.field_71318_t.func_72352_l();
    }

    public String[] func_71213_z()
    {
        return this.field_71318_t.func_72369_d();
    }

    public GameProfile[] func_152357_F()
    {
        return this.field_71318_t.func_152600_g();
    }

    public String getServerModName()
    {
        return "vanilla";
    }

    public CrashReport func_71230_b(CrashReport p_71230_1_)
    {
        p_71230_1_.func_85056_g().func_71500_a("Profiler Position", new Callable()
        {
            private static final String __OBFID = "CL_00001419";
            public String call()
            {
                return MinecraftServer.this.field_71304_b.field_76327_a ? MinecraftServer.this.field_71304_b.func_76322_c() : "N/A (disabled)";
            }
        });

        if (this.field_71305_c != null && this.field_71305_c.length > 0 && this.field_71305_c[0] != null)
        {
            p_71230_1_.func_85056_g().func_71500_a("Vec3 Pool Size", new Callable()
            {
                private static final String __OBFID = "CL_00001420";
                public String call()
                {
                    byte b0 = 0;
                    int i = 56 * b0;
                    int j = i / 1024 / 1024;
                    byte b1 = 0;
                    int k = 56 * b1;
                    int l = k / 1024 / 1024;
                    return b0 + " (" + i + " bytes; " + j + " MB) allocated, " + b1 + " (" + k + " bytes; " + l + " MB) used";
                }
            });
        }

        if (this.field_71318_t != null)
        {
            p_71230_1_.func_85056_g().func_71500_a("Player Count", new Callable()
            {
                private static final String __OBFID = "CL_00001780";
                public String call()
                {
                    return MinecraftServer.this.field_71318_t.func_72394_k() + " / " + MinecraftServer.this.field_71318_t.func_72352_l() + "; " + MinecraftServer.this.field_71318_t.field_72404_b;
                }
            });
        }

        return p_71230_1_;
    }

    public List func_71248_a(ICommandSender p_71248_1_, String p_71248_2_)
    {
        ArrayList arraylist = new ArrayList();

        if (p_71248_2_.startsWith("/"))
        {
            p_71248_2_ = p_71248_2_.substring(1);
            boolean flag = !p_71248_2_.contains(" ");
            List list = this.field_71321_q.func_71558_b(p_71248_1_, p_71248_2_);

            if (list != null)
            {
                Iterator iterator = list.iterator();

                while (iterator.hasNext())
                {
                    String s3 = (String)iterator.next();

                    if (flag)
                    {
                        arraylist.add("/" + s3);
                    }
                    else
                    {
                        arraylist.add(s3);
                    }
                }
            }

            return arraylist;
        }
        else
        {
            String[] astring = p_71248_2_.split(" ", -1);
            String s1 = astring[astring.length - 1];
            String[] astring1 = this.field_71318_t.func_72369_d();
            int i = astring1.length;

            for (int j = 0; j < i; ++j)
            {
                String s2 = astring1[j];

                if (CommandBase.func_71523_a(s1, s2))
                {
                    arraylist.add(s2);
                }
            }

            return arraylist;
        }
    }

    public static MinecraftServer func_71276_C()
    {
        return field_71309_l;
    }

    public String func_70005_c_()
    {
        return "Server";
    }

    public void func_145747_a(IChatComponent p_145747_1_)
    {
        field_147145_h.info(p_145747_1_.func_150260_c());
    }

    public boolean func_70003_b(int p_70003_1_, String p_70003_2_)
    {
        return true;
    }

    public ICommandManager func_71187_D()
    {
        return this.field_71321_q;
    }

    public KeyPair func_71250_E()
    {
        return this.field_71292_I;
    }

    public String func_71214_G()
    {
        return this.field_71293_J;
    }

    public void func_71224_l(String p_71224_1_)
    {
        this.field_71293_J = p_71224_1_;
    }

    public boolean func_71264_H()
    {
        return this.field_71293_J != null;
    }

    public String func_71270_I()
    {
        return this.field_71294_K;
    }

    public void func_71261_m(String p_71261_1_)
    {
        this.field_71294_K = p_71261_1_;
    }

    @SideOnly(Side.CLIENT)
    public void func_71246_n(String p_71246_1_)
    {
        this.field_71287_L = p_71246_1_;
    }

    @SideOnly(Side.CLIENT)
    public String func_71221_J()
    {
        return this.field_71287_L;
    }

    public void func_71253_a(KeyPair p_71253_1_)
    {
        this.field_71292_I = p_71253_1_;
    }

    public void func_147139_a(EnumDifficulty p_147139_1_)
    {
        for (int i = 0; i < this.field_71305_c.length; ++i)
        {
            WorldServer worldserver = this.field_71305_c[i];

            if (worldserver != null)
            {
                if (worldserver.func_72912_H().func_76093_s())
                {
                    worldserver.field_73013_u = EnumDifficulty.HARD;
                    worldserver.func_72891_a(true, true);
                }
                else if (this.func_71264_H())
                {
                    worldserver.field_73013_u = p_147139_1_;
                    worldserver.func_72891_a(worldserver.field_73013_u != EnumDifficulty.PEACEFUL, true);
                }
                else
                {
                    worldserver.field_73013_u = p_147139_1_;
                    worldserver.func_72891_a(this.func_71193_K(), this.field_71324_y);
                }
            }
        }
    }

    protected boolean func_71193_K()
    {
        return true;
    }

    public boolean func_71242_L()
    {
        return this.field_71288_M;
    }

    public void func_71204_b(boolean p_71204_1_)
    {
        this.field_71288_M = p_71204_1_;
    }

    public void func_71194_c(boolean p_71194_1_)
    {
        this.field_71289_N = p_71194_1_;
    }

    public ISaveFormat func_71254_M()
    {
        return this.field_71310_m;
    }

    public void func_71272_O()
    {
        this.field_71290_O = true;
        this.func_71254_M().func_75800_d();

        for (int i = 0; i < this.field_71305_c.length; ++i)
        {
            WorldServer worldserver = this.field_71305_c[i];

            if (worldserver != null)
            {
                worldserver.func_73041_k();
            }
        }

        this.func_71254_M().func_75802_e(this.field_71305_c[0].func_72860_G().func_75760_g());
        this.func_71263_m();
    }

    public String func_147133_T()
    {
        return this.field_147141_M;
    }

    public void func_70000_a(PlayerUsageSnooper p_70000_1_)
    {
        p_70000_1_.func_152768_a("whitelist_enabled", Boolean.valueOf(false));
        p_70000_1_.func_152768_a("whitelist_count", Integer.valueOf(0));
        p_70000_1_.func_152768_a("players_current", Integer.valueOf(this.func_71233_x()));
        p_70000_1_.func_152768_a("players_max", Integer.valueOf(this.func_71275_y()));
        p_70000_1_.func_152768_a("players_seen", Integer.valueOf(this.field_71318_t.func_72373_m().length));
        p_70000_1_.func_152768_a("uses_auth", Boolean.valueOf(this.field_71325_x));
        p_70000_1_.func_152768_a("gui_state", this.func_71279_ae() ? "enabled" : "disabled");
        p_70000_1_.func_152768_a("run_time", Long.valueOf((func_130071_aq() - p_70000_1_.func_130105_g()) / 60L * 1000L));
        p_70000_1_.func_152768_a("avg_tick_ms", Integer.valueOf((int)(MathHelper.func_76127_a(this.field_71311_j) * 1.0E-6D)));
        int i = 0;

        for (int j = 0; j < this.field_71305_c.length; ++j)
        {
            if (this.field_71305_c[j] != null)
            {
                WorldServer worldserver = this.field_71305_c[j];
                WorldInfo worldinfo = worldserver.func_72912_H();
                p_70000_1_.func_152768_a("world[" + i + "][dimension]", Integer.valueOf(worldserver.field_73011_w.field_76574_g));
                p_70000_1_.func_152768_a("world[" + i + "][mode]", worldinfo.func_76077_q());
                p_70000_1_.func_152768_a("world[" + i + "][difficulty]", worldserver.field_73013_u);
                p_70000_1_.func_152768_a("world[" + i + "][hardcore]", Boolean.valueOf(worldinfo.func_76093_s()));
                p_70000_1_.func_152768_a("world[" + i + "][generator_name]", worldinfo.func_76067_t().func_77127_a());
                p_70000_1_.func_152768_a("world[" + i + "][generator_version]", Integer.valueOf(worldinfo.func_76067_t().func_77131_c()));
                p_70000_1_.func_152768_a("world[" + i + "][height]", Integer.valueOf(this.field_71280_D));
                p_70000_1_.func_152768_a("world[" + i + "][chunks_loaded]", Integer.valueOf(worldserver.func_72863_F().func_73152_e()));
                ++i;
            }
        }

        p_70000_1_.func_152768_a("worlds", Integer.valueOf(i));
    }

    public void func_70001_b(PlayerUsageSnooper p_70001_1_)
    {
        p_70001_1_.func_152767_b("singleplayer", Boolean.valueOf(this.func_71264_H()));
        p_70001_1_.func_152767_b("server_brand", this.getServerModName());
        p_70001_1_.func_152767_b("gui_supported", GraphicsEnvironment.isHeadless() ? "headless" : "supported");
        p_70001_1_.func_152767_b("dedicated", Boolean.valueOf(this.func_71262_S()));
    }

    public boolean func_70002_Q()
    {
        return true;
    }

    public abstract boolean func_71262_S();

    public boolean func_71266_T()
    {
        return this.field_71325_x;
    }

    public void func_71229_d(boolean p_71229_1_)
    {
        this.field_71325_x = p_71229_1_;
    }

    public boolean func_71268_U()
    {
        return this.field_71324_y;
    }

    public void func_71251_e(boolean p_71251_1_)
    {
        this.field_71324_y = p_71251_1_;
    }

    public boolean func_71220_V()
    {
        return this.field_71323_z;
    }

    public void func_71257_f(boolean p_71257_1_)
    {
        this.field_71323_z = p_71257_1_;
    }

    public boolean func_71219_W()
    {
        return this.field_71284_A;
    }

    public void func_71188_g(boolean p_71188_1_)
    {
        this.field_71284_A = p_71188_1_;
    }

    public boolean func_71231_X()
    {
        return this.field_71285_B;
    }

    public void func_71245_h(boolean p_71245_1_)
    {
        this.field_71285_B = p_71245_1_;
    }

    public abstract boolean func_82356_Z();

    public String func_71273_Y()
    {
        return this.field_71286_C;
    }

    public void func_71205_p(String p_71205_1_)
    {
        this.field_71286_C = p_71205_1_;
    }

    public int func_71207_Z()
    {
        return this.field_71280_D;
    }

    public void func_71191_d(int p_71191_1_)
    {
        this.field_71280_D = p_71191_1_;
    }

    public ServerConfigurationManager func_71203_ab()
    {
        return this.field_71318_t;
    }

    public void func_152361_a(ServerConfigurationManager p_152361_1_)
    {
        this.field_71318_t = p_152361_1_;
    }

    public void func_71235_a(WorldSettings.GameType p_71235_1_)
    {
        for (int i = 0; i < this.field_71305_c.length; ++i)
        {
            func_71276_C().field_71305_c[i].func_72912_H().func_76060_a(p_71235_1_);
        }
    }

    public NetworkSystem func_147137_ag()
    {
        return this.field_147144_o;
    }

    @SideOnly(Side.CLIENT)
    public boolean func_71200_ad()
    {
        return this.field_71296_Q;
    }

    public boolean func_71279_ae()
    {
        return false;
    }

    public abstract String func_71206_a(WorldSettings.GameType p_71206_1_, boolean p_71206_2_);

    public int func_71259_af()
    {
        return this.field_71315_w;
    }

    public void func_71223_ag()
    {
        this.field_71295_T = true;
    }

    @SideOnly(Side.CLIENT)
    public PlayerUsageSnooper func_80003_ah()
    {
        return this.field_71307_n;
    }

    public ChunkCoordinates func_82114_b()
    {
        return new ChunkCoordinates(0, 0, 0);
    }

    public World func_130014_f_()
    {
        return this.field_71305_c[0];
    }

    public int func_82357_ak()
    {
        return 16;
    }

    public boolean func_96290_a(World p_96290_1_, int p_96290_2_, int p_96290_3_, int p_96290_4_, EntityPlayer p_96290_5_)
    {
        return false;
    }

    public boolean func_104056_am()
    {
        return this.field_104057_T;
    }

    public Proxy func_110454_ao()
    {
        return this.field_110456_c;
    }

    public static long func_130071_aq()
    {
        return System.currentTimeMillis();
    }

    public int func_143007_ar()
    {
        return this.field_143008_E;
    }

    public void func_143006_e(int p_143006_1_)
    {
        this.field_143008_E = p_143006_1_;
    }

    public IChatComponent func_145748_c_()
    {
        return new ChatComponentText(this.func_70005_c_());
    }

    public boolean func_147136_ar()
    {
        return true;
    }

    public MinecraftSessionService func_147130_as()
    {
        return this.field_147143_S;
    }

    public GameProfileRepository func_152359_aw()
    {
        return this.field_152365_W;
    }

    public PlayerProfileCache func_152358_ax()
    {
        return this.field_152366_X;
    }

    public ServerStatusResponse func_147134_at()
    {
        return this.field_147147_p;
    }

    public void func_147132_au()
    {
        this.field_147142_T = 0L;
    }

    @SideOnly(Side.SERVER)
    public String func_71211_k()
    {
        return this.field_71320_r;
    }

    @SideOnly(Side.SERVER)
    public void func_71189_e(String p_71189_1_)
    {
        this.field_71320_r = p_71189_1_;
    }

    @SideOnly(Side.SERVER)
    public void func_82010_a(IUpdatePlayerListBox p_82010_1_)
    {
        this.field_71322_p.add(p_82010_1_);
    }

    @SideOnly(Side.SERVER)
    public static void main(String[] p_main_0_)
    {
        Bootstrap.func_151354_b();

        try
        {
            boolean flag = true;
            String s = null;
            String s1 = ".";
            String s2 = null;
            boolean flag1 = false;
            boolean flag2 = false;
            int i = -1;

            for (int j = 0; j < p_main_0_.length; ++j)
            {
                String s3 = p_main_0_[j];
                String s4 = j == p_main_0_.length - 1 ? null : p_main_0_[j + 1];
                boolean flag3 = false;

                if (!s3.equals("nogui") && !s3.equals("--nogui"))
                {
                    if (s3.equals("--port") && s4 != null)
                    {
                        flag3 = true;

                        try
                        {
                            i = Integer.parseInt(s4);
                        }
                        catch (NumberFormatException numberformatexception)
                        {
                            ;
                        }
                    }
                    else if (s3.equals("--singleplayer") && s4 != null)
                    {
                        flag3 = true;
                        s = s4;
                    }
                    else if (s3.equals("--universe") && s4 != null)
                    {
                        flag3 = true;
                        s1 = s4;
                    }
                    else if (s3.equals("--world") && s4 != null)
                    {
                        flag3 = true;
                        s2 = s4;
                    }
                    else if (s3.equals("--demo"))
                    {
                        flag1 = true;
                    }
                    else if (s3.equals("--bonusChest"))
                    {
                        flag2 = true;
                    }
                }
                else
                {
                    flag = false;
                }

                if (flag3)
                {
                    ++j;
                }
            }

            final DedicatedServer dedicatedserver = new DedicatedServer(new File(s1));

            if (s != null)
            {
                dedicatedserver.func_71224_l(s);
            }

            if (s2 != null)
            {
                dedicatedserver.func_71261_m(s2);
            }

            if (i >= 0)
            {
                dedicatedserver.func_71208_b(i);
            }

            if (flag1)
            {
                dedicatedserver.func_71204_b(true);
            }

            if (flag2)
            {
                dedicatedserver.func_71194_c(true);
            }

            if (flag && !GraphicsEnvironment.isHeadless())
            {
                dedicatedserver.func_120011_ar();
            }

            dedicatedserver.func_71256_s();
            Runtime.getRuntime().addShutdownHook(new Thread("Server Shutdown Thread")
            {
                private static final String __OBFID = "CL_00001806";
                public void run()
                {
                    dedicatedserver.func_71260_j();
                }
            });
        }
        catch (Exception exception)
        {
            field_147145_h.fatal("Failed to start the minecraft server", exception);
        }
    }

    @SideOnly(Side.SERVER)
    public void func_71244_g(String p_71244_1_)
    {
        field_147145_h.info(p_71244_1_);
    }

    @SideOnly(Side.SERVER)
    public String func_71277_t()
    {
        return this.field_71320_r;
    }

    @SideOnly(Side.SERVER)
    public int func_71234_u()
    {
        return this.field_71319_s;
    }

    @SideOnly(Side.SERVER)
    public String func_71274_v()
    {
        return this.field_71286_C;
    }

    @SideOnly(Side.SERVER)
    public String func_71258_A()
    {
        return "";
    }

    @SideOnly(Side.SERVER)
    public String func_71252_i(String p_71252_1_)
    {
        RConConsoleSource.field_70010_a.func_70007_b();
        this.field_71321_q.func_71556_a(RConConsoleSource.field_70010_a, p_71252_1_);
        return RConConsoleSource.field_70010_a.func_70008_c();
    }

    @SideOnly(Side.SERVER)
    public boolean func_71239_B()
    {
        return false;
    }

    @SideOnly(Side.SERVER)
    public void func_71201_j(String p_71201_1_)
    {
        field_147145_h.error(p_71201_1_);
    }

    @SideOnly(Side.SERVER)
    public void func_71198_k(String p_71198_1_)
    {
        if (this.func_71239_B())
        {
            field_147145_h.info(p_71198_1_);
        }
    }

    @SideOnly(Side.SERVER)
    public int func_71215_F()
    {
        return this.field_71319_s;
    }

    @SideOnly(Side.SERVER)
    public void func_71208_b(int p_71208_1_)
    {
        this.field_71319_s = p_71208_1_;
    }

    @SideOnly(Side.SERVER)
    public void func_155759_m(String p_155759_1_)
    {
        this.field_147141_M = p_155759_1_;
    }

    @SideOnly(Side.SERVER)
    public boolean func_71241_aa()
    {
        return this.field_71316_v;
    }

    @SideOnly(Side.SERVER)
    public void func_104055_i(boolean p_104055_1_)
    {
        this.field_104057_T = p_104055_1_;
    }
}