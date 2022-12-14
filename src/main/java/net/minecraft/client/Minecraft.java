package net.minecraft.client;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.Queues;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.util.concurrent.GenericFutureListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.Proxy;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import javax.imageio.ImageIO;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.audio.MusicTicker;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiControls;
import net.minecraft.client.gui.GuiGameOver;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiMemoryErrorScreen;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSleepMP;
import net.minecraft.client.gui.GuiWinGame;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.achievement.GuiAchievement;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.gui.stream.GuiStreamUnavailable;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.network.NetHandlerLoginClient;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.DefaultResourcePack;
import net.minecraft.client.resources.FoliageColorReloadListener;
import net.minecraft.client.resources.GrassColorReloadListener;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.LanguageManager;
import net.minecraft.client.resources.ResourceIndex;
import net.minecraft.client.resources.ResourcePackRepository;
import net.minecraft.client.resources.SimpleReloadableResourceManager;
import net.minecraft.client.resources.SkinManager;
import net.minecraft.client.resources.data.AnimationMetadataSection;
import net.minecraft.client.resources.data.AnimationMetadataSectionSerializer;
import net.minecraft.client.resources.data.FontMetadataSection;
import net.minecraft.client.resources.data.FontMetadataSectionSerializer;
import net.minecraft.client.resources.data.IMetadataSerializer;
import net.minecraft.client.resources.data.LanguageMetadataSection;
import net.minecraft.client.resources.data.LanguageMetadataSectionSerializer;
import net.minecraft.client.resources.data.PackMetadataSection;
import net.minecraft.client.resources.data.PackMetadataSectionSerializer;
import net.minecraft.client.resources.data.TextureMetadataSection;
import net.minecraft.client.resources.data.TextureMetadataSectionSerializer;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.client.stream.IStream;
import net.minecraft.client.stream.NullStream;
import net.minecraft.client.stream.TwitchStream;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLeashKnot;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.item.EntityPainting;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Bootstrap;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.handshake.client.C00Handshake;
import net.minecraft.network.login.client.C00PacketLoginStart;
import net.minecraft.network.play.client.C16PacketClientStatus;
import net.minecraft.profiler.IPlayerUsage;
import net.minecraft.profiler.PlayerUsageSnooper;
import net.minecraft.profiler.Profiler;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.integrated.IntegratedServer;
import net.minecraft.stats.AchievementList;
import net.minecraft.stats.IStatStringFormat;
import net.minecraft.stats.StatFileWriter;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MinecraftError;
import net.minecraft.util.MouseHelper;
import net.minecraft.util.MovementInputFromOptions;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ReportedException;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.ScreenShotHelper;
import net.minecraft.util.Session;
import net.minecraft.util.Timer;
import net.minecraft.util.Util;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.WorldProviderEnd;
import net.minecraft.world.WorldProviderHell;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.chunk.storage.AnvilSaveConverter;
import net.minecraft.world.storage.ISaveFormat;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraft.world.storage.WorldInfo;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.ContextCapabilities;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GLContext;
import org.lwjgl.opengl.OpenGLException;
import org.lwjgl.opengl.PixelFormat;
import org.lwjgl.util.glu.GLU;

@SideOnly(Side.CLIENT)
public class Minecraft implements IPlayerUsage
{
    private static final Logger LOG = LogManager.getLogger();
    private static final ResourceLocation field_110444_H = new ResourceLocation("textures/gui/title/mojang.png");
    public static final boolean field_142025_a = Util.func_110647_a() == Util.EnumOS.OSX;
    public static byte[] field_71444_a = new byte[10485760];
    private static final List field_110445_I = Lists.newArrayList(new DisplayMode[] {new DisplayMode(2560, 1600), new DisplayMode(2880, 1800)});
    private final File field_130070_K;
    private final Multimap field_152356_J;
    private ServerData field_71422_O;
    public TextureManager field_71446_o;
    private static Minecraft field_71432_P;
    public PlayerControllerMP field_71442_b;
    private boolean field_71431_Q;
    private boolean field_71434_R;
    private CrashReport field_71433_S;
    public int field_71443_c;
    public int field_71440_d;
    private Timer field_71428_T = new Timer(20.0F);
    private PlayerUsageSnooper field_71427_U = new PlayerUsageSnooper("client", this, MinecraftServer.func_130071_aq());
    public WorldClient field_71441_e;
    public RenderGlobal field_71438_f;
    public EntityClientPlayerMP field_71439_g;
    public EntityLivingBase field_71451_h;
    public Entity field_147125_j;
    public EffectRenderer field_71452_i;
    private final Session field_71449_j;
    private boolean field_71445_n;
    public FontRenderer field_71466_p;
    public FontRenderer field_71464_q;
    public GuiScreen field_71462_r;
    public LoadingScreenRenderer field_71461_s;
    public EntityRenderer field_71460_t;
    private int field_71429_W;
    private int field_71436_X;
    private int field_71435_Y;
    private IntegratedServer field_71437_Z;
    public GuiAchievement field_71458_u;
    public GuiIngame field_71456_v;
    public boolean field_71454_w;
    public MovingObjectPosition field_71476_x;
    public GameSettings gameSettings;
    public MouseHelper field_71417_B;
    public final File field_71412_D;
    private final File field_110446_Y;
    private final String field_110447_Z;
    private final Proxy field_110453_aa;
    private ISaveFormat field_71469_aa;
    private static int field_71470_ab;
    private int field_71467_ac;
    private boolean field_71468_ad;
    private String field_71475_ae;
    private int field_71477_af;
    public boolean field_71415_G;
    long field_71423_H = func_71386_F();
    private int field_71457_ai;
    private final boolean field_147129_ai;
    private final boolean field_71459_aj;
    private NetworkManager field_71453_ak;
    private boolean field_71455_al;
    public final Profiler profiler = new Profiler();
    private long field_83002_am = -1L;
    private IReloadableResourceManager field_110451_am;
    private final IMetadataSerializer field_110452_an = new IMetadataSerializer();
    private List field_110449_ao = Lists.newArrayList();
    public DefaultResourcePack field_110450_ap;
    private ResourcePackRepository field_110448_aq;
    private LanguageManager field_135017_as;
    private IStream field_152353_at;
    private Framebuffer field_147124_at;
    private TextureMap field_147128_au;
    private SoundHandler field_147127_av;
    private MusicTicker field_147126_aw;
    private ResourceLocation field_152354_ay;
    private final MinecraftSessionService field_152355_az;
    private SkinManager field_152350_aA;
    private final Queue field_152351_aB = Queues.newArrayDeque();
    private final Thread field_152352_aC = Thread.currentThread();
    volatile boolean field_71425_J = true;
    public String field_71426_K = "";
    long field_71419_L = func_71386_F();
    int field_71420_M;
    long field_71421_N = -1L;
    private String field_71465_an = "root";
    private static final String __OBFID = "CL_00000631";

    public Minecraft(Session p_i1103_1_, int p_i1103_2_, int p_i1103_3_, boolean p_i1103_4_, boolean p_i1103_5_, File p_i1103_6_, File p_i1103_7_, File p_i1103_8_, Proxy p_i1103_9_, String p_i1103_10_, Multimap p_i1103_11_, String p_i1103_12_)
    {
        field_71432_P = this;
        this.field_71412_D = p_i1103_6_;
        this.field_110446_Y = p_i1103_7_;
        this.field_130070_K = p_i1103_8_;
        this.field_110447_Z = p_i1103_10_;
        this.field_152356_J = p_i1103_11_;
        this.field_110450_ap = new DefaultResourcePack((new ResourceIndex(p_i1103_7_, p_i1103_12_)).func_152782_a());
        this.func_110435_P();
        this.field_110453_aa = p_i1103_9_ == null ? Proxy.NO_PROXY : p_i1103_9_;
        this.field_152355_az = (new YggdrasilAuthenticationService(p_i1103_9_, UUID.randomUUID().toString())).createMinecraftSessionService();
        this.func_71389_H();
        this.field_71449_j = p_i1103_1_;
        LOG.info("Setting user: " + p_i1103_1_.func_111285_a());
        LOG.info("(Session ID is " + p_i1103_1_.func_111286_b() + ")");
        this.field_71459_aj = p_i1103_5_;
        this.field_71443_c = p_i1103_2_;
        this.field_71440_d = p_i1103_3_;
        this.field_71436_X = p_i1103_2_;
        this.field_71435_Y = p_i1103_3_;
        this.field_71431_Q = p_i1103_4_;
        this.field_147129_ai = func_147122_X();
        ImageIO.setUseCache(false);
        Bootstrap.initialize();
    }

    private static boolean func_147122_X()
    {
        String[] astring = new String[] {"sun.arch.data.model", "com.ibm.vm.bitmode", "os.arch"};
        String[] astring1 = astring;
        int i = astring.length;

        for (int j = 0; j < i; ++j)
        {
            String s = astring1[j];
            String s1 = System.getProperty(s);

            if (s1 != null && s1.contains("64"))
            {
                return true;
            }
        }

        return false;
    }

    public Framebuffer func_147110_a()
    {
        return this.field_147124_at;
    }

    private void func_71389_H()
    {
        Thread thread = new Thread("Timer hack thread")
        {
            private static final String __OBFID = "CL_00000632";
            public void run()
            {
                while (Minecraft.this.field_71425_J)
                {
                    try
                    {
                        Thread.sleep(2147483647L);
                    }
                    catch (InterruptedException interruptedexception)
                    {
                        ;
                    }
                }
            }
        };
        thread.setDaemon(true);
        thread.start();
    }

    public void func_71404_a(CrashReport p_71404_1_)
    {
        this.field_71434_R = true;
        this.field_71433_S = p_71404_1_;
    }

    public void func_71377_b(CrashReport p_71377_1_)
    {
        File file1 = new File(func_71410_x().field_71412_D, "crash-reports");
        File file2 = new File(file1, "crash-" + (new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss")).format(new Date()) + "-client.txt");
        System.out.println(p_71377_1_.func_71502_e());

        if (p_71377_1_.func_71497_f() != null)
        {
            System.out.println("#@!@# Game crashed! Crash report saved to: #@!@# " + p_71377_1_.func_71497_f());
            System.exit(-1);
        }
        else if (p_71377_1_.func_147149_a(file2))
        {
            System.out.println("#@!@# Game crashed! Crash report saved to: #@!@# " + file2.getAbsolutePath());
            System.exit(-1);
        }
        else
        {
            System.out.println("#@?@# Game crashed! Crash report could not be saved. #@?@#");
            System.exit(-2);
        }
    }

    public void func_71367_a(String p_71367_1_, int p_71367_2_)
    {
        this.field_71475_ae = p_71367_1_;
        this.field_71477_af = p_71367_2_;
    }

    private void func_71384_a() throws LWJGLException
    {
        this.gameSettings = new GameSettings(this, this.field_71412_D);

        if (this.gameSettings.field_92119_C > 0 && this.gameSettings.field_92118_B > 0)
        {
            this.field_71443_c = this.gameSettings.field_92118_B;
            this.field_71440_d = this.gameSettings.field_92119_C;
        }

        if (this.field_71431_Q)
        {
            Display.setFullscreen(true);
            this.field_71443_c = Display.getDisplayMode().getWidth();
            this.field_71440_d = Display.getDisplayMode().getHeight();

            if (this.field_71443_c <= 0)
            {
                this.field_71443_c = 1;
            }

            if (this.field_71440_d <= 0)
            {
                this.field_71440_d = 1;
            }
        }
        else
        {
            Display.setDisplayMode(new DisplayMode(this.field_71443_c, this.field_71440_d));
        }

        Display.setResizable(true);
        Display.setTitle("Minecraft 1.7.10");
        LOG.info("LWJGL Version: " + Sys.getVersion());
        Util.EnumOS enumos = Util.func_110647_a();

        if (enumos != Util.EnumOS.OSX)
        {
            try
            {
                InputStream inputstream = this.field_110450_ap.func_152780_c(new ResourceLocation("icons/icon_16x16.png"));
                InputStream inputstream1 = this.field_110450_ap.func_152780_c(new ResourceLocation("icons/icon_32x32.png"));

                if (inputstream != null && inputstream1 != null)
                {
                    Display.setIcon(new ByteBuffer[] {this.func_152340_a(inputstream), this.func_152340_a(inputstream1)});
                }
            }
            catch (IOException ioexception)
            {
                LOG.error("Couldn\'t set icon", ioexception);
            }
        }

        try
        {
            Display.create((new PixelFormat()).withDepthBits(24));
        }
        catch (LWJGLException lwjglexception)
        {
            LOG.error("Couldn\'t set pixel format", lwjglexception);

            try
            {
                Thread.sleep(1000L);
            }
            catch (InterruptedException interruptedexception)
            {
                ;
            }

            if (this.field_71431_Q)
            {
                this.func_110441_Q();
            }

            Display.create();
        }

        OpenGlHelper.func_77474_a();

        try
        {
            this.field_152353_at = new TwitchStream(this, (String)Iterables.getFirst(this.field_152356_J.get("twitch_access_token"), (Object)null));
        }
        catch (Throwable throwable)
        {
            this.field_152353_at = new NullStream(throwable);
            LOG.error("Couldn\'t initialize twitch stream");
        }

        this.field_147124_at = new Framebuffer(this.field_71443_c, this.field_71440_d, true);
        this.field_147124_at.func_147604_a(0.0F, 0.0F, 0.0F, 0.0F);
        this.field_71458_u = new GuiAchievement(this);
        this.field_110452_an.func_110504_a(new TextureMetadataSectionSerializer(), TextureMetadataSection.class);
        this.field_110452_an.func_110504_a(new FontMetadataSectionSerializer(), FontMetadataSection.class);
        this.field_110452_an.func_110504_a(new AnimationMetadataSectionSerializer(), AnimationMetadataSection.class);
        this.field_110452_an.func_110504_a(new PackMetadataSectionSerializer(), PackMetadataSection.class);
        this.field_110452_an.func_110504_a(new LanguageMetadataSectionSerializer(), LanguageMetadataSection.class);
        this.field_71469_aa = new AnvilSaveConverter(new File(this.field_71412_D, "saves"));
        this.field_110448_aq = new ResourcePackRepository(this.field_130070_K, new File(this.field_71412_D, "server-resource-packs"), this.field_110450_ap, this.field_110452_an, this.gameSettings);
        this.field_110451_am = new SimpleReloadableResourceManager(this.field_110452_an);
        this.field_135017_as = new LanguageManager(this.field_110452_an, this.gameSettings.field_74363_ab);
        this.field_110451_am.func_110542_a(this.field_135017_as);
        this.func_110436_a();
        this.field_71446_o = new TextureManager(this.field_110451_am);
        this.field_110451_am.func_110542_a(this.field_71446_o);
        this.field_152350_aA = new SkinManager(this.field_71446_o, new File(this.field_110446_Y, "skins"), this.field_152355_az);
        this.func_71357_I();
        this.field_147127_av = new SoundHandler(this.field_110451_am, this.gameSettings);
        this.field_110451_am.func_110542_a(this.field_147127_av);
        this.field_147126_aw = new MusicTicker(this);
        this.field_71466_p = new FontRenderer(this.gameSettings, new ResourceLocation("textures/font/ascii.png"), this.field_71446_o, false);

        if (this.gameSettings.field_74363_ab != null)
        {
            this.field_71466_p.func_78264_a(this.func_152349_b());
            this.field_71466_p.func_78275_b(this.field_135017_as.func_135044_b());
        }

        this.field_71464_q = new FontRenderer(this.gameSettings, new ResourceLocation("textures/font/ascii_sga.png"), this.field_71446_o, false);
        this.field_110451_am.func_110542_a(this.field_71466_p);
        this.field_110451_am.func_110542_a(this.field_71464_q);
        this.field_110451_am.func_110542_a(new GrassColorReloadListener());
        this.field_110451_am.func_110542_a(new FoliageColorReloadListener());
        RenderManager.field_78727_a.field_78721_f = new ItemRenderer(this);
        this.field_71460_t = new EntityRenderer(this, this.field_110451_am);
        this.field_110451_am.func_110542_a(this.field_71460_t);
        AchievementList.OPEN_INVENTORY.func_75988_a(new IStatStringFormat()
        {
            private static final String __OBFID = "CL_00000639";
            public String func_74535_a(String p_74535_1_)
            {
                try
                {
                    return String.format(p_74535_1_, new Object[] {GameSettings.func_74298_c(Minecraft.this.gameSettings.field_151445_Q.getEventKey())});
                }
                catch (Exception exception)
                {
                    return "Error: " + exception.getLocalizedMessage();
                }
            }
        });
        this.field_71417_B = new MouseHelper();
        this.func_71361_d("Pre startup");
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glShadeModel(GL11.GL_SMOOTH);
        GL11.glClearDepth(1.0D);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthFunc(GL11.GL_LEQUAL);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
        GL11.glCullFace(GL11.GL_BACK);
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        this.func_71361_d("Startup");
        this.field_71438_f = new RenderGlobal(this);
        this.field_147128_au = new TextureMap(0, "textures/blocks");
        this.field_147128_au.func_147632_b(this.gameSettings.field_151443_J);
        this.field_147128_au.func_147633_a(this.gameSettings.field_151442_I);
        this.field_71446_o.func_130088_a(TextureMap.field_110575_b, this.field_147128_au);
        this.field_71446_o.func_130088_a(TextureMap.field_110576_c, new TextureMap(1, "textures/items"));
        GL11.glViewport(0, 0, this.field_71443_c, this.field_71440_d);
        this.field_71452_i = new EffectRenderer(this.field_71441_e, this.field_71446_o);
        this.func_71361_d("Post startup");
        this.field_71456_v = new GuiIngame(this);

        if (this.field_71475_ae != null)
        {
            this.func_147108_a(new GuiConnecting(new GuiMainMenu(), this, this.field_71475_ae, this.field_71477_af));
        }
        else
        {
            this.func_147108_a(new GuiMainMenu());
        }

        this.field_71446_o.func_147645_c(this.field_152354_ay);
        this.field_152354_ay = null;
        this.field_71461_s = new LoadingScreenRenderer(this);

        if (this.gameSettings.field_74353_u && !this.field_71431_Q)
        {
            this.func_71352_k();
        }

        try
        {
            Display.setVSyncEnabled(this.gameSettings.field_74352_v);
        }
        catch (OpenGLException openglexception)
        {
            this.gameSettings.field_74352_v = false;
            this.gameSettings.func_74303_b();
        }
    }

    public boolean func_152349_b()
    {
        return this.field_135017_as.func_135042_a() || this.gameSettings.field_151455_aw;
    }

    public void func_110436_a()
    {
        ArrayList arraylist = Lists.newArrayList(this.field_110449_ao);
        Iterator iterator = this.field_110448_aq.func_110613_c().iterator();

        while (iterator.hasNext())
        {
            ResourcePackRepository.Entry entry = (ResourcePackRepository.Entry)iterator.next();
            arraylist.add(entry.func_110514_c());
        }

        if (this.field_110448_aq.func_148530_e() != null)
        {
            arraylist.add(this.field_110448_aq.func_148530_e());
        }

        try
        {
            this.field_110451_am.func_110541_a(arraylist);
        }
        catch (RuntimeException runtimeexception)
        {
            LOG.info("Caught error stitching, removing all assigned resourcepacks", runtimeexception);
            arraylist.clear();
            arraylist.addAll(this.field_110449_ao);
            this.field_110448_aq.func_148527_a(Collections.emptyList());
            this.field_110451_am.func_110541_a(arraylist);
            this.gameSettings.field_151453_l.clear();
            this.gameSettings.func_74303_b();
        }

        this.field_135017_as.func_135043_a(arraylist);

        if (this.field_71438_f != null)
        {
            this.field_71438_f.func_72712_a();
        }
    }

    private void func_110435_P()
    {
        this.field_110449_ao.add(this.field_110450_ap);
    }

    private ByteBuffer func_152340_a(InputStream p_152340_1_) throws IOException
    {
        BufferedImage bufferedimage = ImageIO.read(p_152340_1_);
        int[] aint = bufferedimage.getRGB(0, 0, bufferedimage.getWidth(), bufferedimage.getHeight(), (int[])null, 0, bufferedimage.getWidth());
        ByteBuffer bytebuffer = ByteBuffer.allocate(4 * aint.length);
        int[] aint1 = aint;
        int i = aint.length;

        for (int j = 0; j < i; ++j)
        {
            int k = aint1[j];
            bytebuffer.putInt(k << 8 | k >> 24 & 255);
        }

        bytebuffer.flip();
        return bytebuffer;
    }

    private void func_110441_Q() throws LWJGLException
    {
        HashSet hashset = new HashSet();
        Collections.addAll(hashset, Display.getAvailableDisplayModes());
        DisplayMode displaymode = Display.getDesktopDisplayMode();

        if (!hashset.contains(displaymode) && Util.func_110647_a() == Util.EnumOS.OSX)
        {
            Iterator iterator = field_110445_I.iterator();

            while (iterator.hasNext())
            {
                DisplayMode displaymode1 = (DisplayMode)iterator.next();
                boolean flag = true;
                Iterator iterator1 = hashset.iterator();
                DisplayMode displaymode2;

                while (iterator1.hasNext())
                {
                    displaymode2 = (DisplayMode)iterator1.next();

                    if (displaymode2.getBitsPerPixel() == 32 && displaymode2.getWidth() == displaymode1.getWidth() && displaymode2.getHeight() == displaymode1.getHeight())
                    {
                        flag = false;
                        break;
                    }
                }

                if (!flag)
                {
                    iterator1 = hashset.iterator();

                    while (iterator1.hasNext())
                    {
                        displaymode2 = (DisplayMode)iterator1.next();

                        if (displaymode2.getBitsPerPixel() == 32 && displaymode2.getWidth() == displaymode1.getWidth() / 2 && displaymode2.getHeight() == displaymode1.getHeight() / 2)
                        {
                            displaymode = displaymode2;
                            break;
                        }
                    }
                }
            }
        }

        Display.setDisplayMode(displaymode);
        this.field_71443_c = displaymode.getWidth();
        this.field_71440_d = displaymode.getHeight();
    }

    public void func_71357_I() throws LWJGLException
    {
        ScaledResolution scaledresolution = new ScaledResolution(this, this.field_71443_c, this.field_71440_d);
        int i = scaledresolution.func_78325_e();
        Framebuffer framebuffer = new Framebuffer(scaledresolution.func_78326_a() * i, scaledresolution.func_78328_b() * i, true);
        framebuffer.func_147610_a(false);
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0.0D, (double)scaledresolution.func_78326_a(), (double)scaledresolution.func_78328_b(), 0.0D, 1000.0D, 3000.0D);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glLoadIdentity();
        GL11.glTranslatef(0.0F, 0.0F, -2000.0F);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_FOG);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_TEXTURE_2D);

        try
        {
            this.field_152354_ay = this.field_71446_o.func_110578_a("logo", new DynamicTexture(ImageIO.read(this.field_110450_ap.func_110590_a(field_110444_H))));
            this.field_71446_o.func_110577_a(this.field_152354_ay);
        }
        catch (IOException ioexception)
        {
            LOG.error("Unable to load logo: " + field_110444_H, ioexception);
        }

        Tessellator tessellator = Tessellator.field_78398_a;
        tessellator.func_78382_b();
        tessellator.func_78378_d(16777215);
        tessellator.func_78374_a(0.0D, (double)this.field_71440_d, 0.0D, 0.0D, 0.0D);
        tessellator.func_78374_a((double)this.field_71443_c, (double)this.field_71440_d, 0.0D, 0.0D, 0.0D);
        tessellator.func_78374_a((double)this.field_71443_c, 0.0D, 0.0D, 0.0D, 0.0D);
        tessellator.func_78374_a(0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
        tessellator.func_78381_a();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        tessellator.func_78378_d(16777215);
        short short1 = 256;
        short short2 = 256;
        this.func_71392_a((scaledresolution.func_78326_a() - short1) / 2, (scaledresolution.func_78328_b() - short2) / 2, 0, 0, short1, short2);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_FOG);
        framebuffer.func_147609_e();
        framebuffer.func_147615_c(scaledresolution.func_78326_a() * i, scaledresolution.func_78328_b() * i);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
        GL11.glFlush();
        this.func_147120_f();
    }

    public void func_71392_a(int p_71392_1_, int p_71392_2_, int p_71392_3_, int p_71392_4_, int p_71392_5_, int p_71392_6_)
    {
        float f = 0.00390625F;
        float f1 = 0.00390625F;
        Tessellator tessellator = Tessellator.field_78398_a;
        tessellator.func_78382_b();
        tessellator.func_78374_a((double)(p_71392_1_ + 0), (double)(p_71392_2_ + p_71392_6_), 0.0D, (double)((float)(p_71392_3_ + 0) * f), (double)((float)(p_71392_4_ + p_71392_6_) * f1));
        tessellator.func_78374_a((double)(p_71392_1_ + p_71392_5_), (double)(p_71392_2_ + p_71392_6_), 0.0D, (double)((float)(p_71392_3_ + p_71392_5_) * f), (double)((float)(p_71392_4_ + p_71392_6_) * f1));
        tessellator.func_78374_a((double)(p_71392_1_ + p_71392_5_), (double)(p_71392_2_ + 0), 0.0D, (double)((float)(p_71392_3_ + p_71392_5_) * f), (double)((float)(p_71392_4_ + 0) * f1));
        tessellator.func_78374_a((double)(p_71392_1_ + 0), (double)(p_71392_2_ + 0), 0.0D, (double)((float)(p_71392_3_ + 0) * f), (double)((float)(p_71392_4_ + 0) * f1));
        tessellator.func_78381_a();
    }

    public ISaveFormat func_71359_d()
    {
        return this.field_71469_aa;
    }

    public void func_147108_a(GuiScreen p_147108_1_)
    {
        if (this.field_71462_r != null)
        {
            this.field_71462_r.func_146281_b();
        }

        if (p_147108_1_ == null && this.field_71441_e == null)
        {
            p_147108_1_ = new GuiMainMenu();
        }
        else if (p_147108_1_ == null && this.field_71439_g.func_110143_aJ() <= 0.0F)
        {
            p_147108_1_ = new GuiGameOver();
        }

        if (p_147108_1_ instanceof GuiMainMenu)
        {
            this.gameSettings.field_74330_P = false;
            this.field_71456_v.func_146158_b().func_146231_a();
        }

        this.field_71462_r = (GuiScreen)p_147108_1_;

        if (p_147108_1_ != null)
        {
            this.func_71364_i();
            ScaledResolution scaledresolution = new ScaledResolution(this, this.field_71443_c, this.field_71440_d);
            int i = scaledresolution.func_78326_a();
            int j = scaledresolution.func_78328_b();
            ((GuiScreen)p_147108_1_).func_146280_a(this, i, j);
            this.field_71454_w = false;
        }
        else
        {
            this.field_147127_av.func_147687_e();
            this.func_71381_h();
        }
    }

    private void func_71361_d(String p_71361_1_)
    {
        int i = GL11.glGetError();

        if (i != 0)
        {
            String s1 = GLU.gluErrorString(i);
            LOG.error("########## GL ERROR ##########");
            LOG.error("@ " + p_71361_1_);
            LOG.error(i + ": " + s1);
        }
    }

    public void func_71405_e()
    {
        try
        {
            this.field_152353_at.func_152923_i();
            LOG.info("Stopping!");

            try
            {
                this.func_71403_a((WorldClient)null);
            }
            catch (Throwable throwable1)
            {
                ;
            }

            try
            {
                GLAllocation.func_74525_a();
            }
            catch (Throwable throwable)
            {
                ;
            }

            this.field_147127_av.func_147685_d();
        }
        finally
        {
            Display.destroy();

            if (!this.field_71434_R)
            {
                System.exit(0);
            }
        }

        System.gc();
    }

    public void func_99999_d()
    {
        this.field_71425_J = true;
        CrashReport crashreport;

        try
        {
            this.func_71384_a();
        }
        catch (Throwable throwable)
        {
            crashreport = CrashReport.func_85055_a(throwable, "Initializing game");
            crashreport.func_85058_a("Initialization");
            this.func_71377_b(this.func_71396_d(crashreport));
            return;
        }

        while (true)
        {
            try
            {
                while (this.field_71425_J)
                {
                    if (!this.field_71434_R || this.field_71433_S == null)
                    {
                        try
                        {
                            this.func_71411_J();
                        }
                        catch (OutOfMemoryError outofmemoryerror)
                        {
                            this.func_71398_f();
                            this.func_147108_a(new GuiMemoryErrorScreen());
                            System.gc();
                        }

                        continue;
                    }

                    this.func_71377_b(this.field_71433_S);
                    return;
                }
            }
            catch (MinecraftError minecrafterror)
            {
                ;
            }
            catch (ReportedException reportedexception)
            {
                this.func_71396_d(reportedexception.func_71575_a());
                this.func_71398_f();
                LOG.fatal("Reported exception thrown!", reportedexception);
                this.func_71377_b(reportedexception.func_71575_a());
            }
            catch (Throwable throwable1)
            {
                crashreport = this.func_71396_d(new CrashReport("Unexpected error", throwable1));
                this.func_71398_f();
                LOG.fatal("Unreported exception thrown!", throwable1);
                this.func_71377_b(crashreport);
            }
            finally
            {
                this.func_71405_e();
            }

            return;
        }
    }

    private void func_71411_J()
    {
        this.profiler.startMeasure("root");

        if (Display.isCreated() && Display.isCloseRequested())
        {
            this.func_71400_g();
        }

        if (this.field_71445_n && this.field_71441_e != null)
        {
            float f = this.field_71428_T.field_74281_c;
            this.field_71428_T.func_74275_a();
            this.field_71428_T.field_74281_c = f;
        }
        else
        {
            this.field_71428_T.func_74275_a();
        }

        if ((this.field_71441_e == null || this.field_71462_r == null) && this.field_71468_ad)
        {
            this.field_71468_ad = false;
            this.func_110436_a();
        }

        long j = System.nanoTime();
        this.profiler.startMeasure("tick");

        for (int i = 0; i < this.field_71428_T.field_74280_b; ++i)
        {
            this.func_71407_l();
        }

        this.profiler.startNewMeasure("preRenderErrors");
        long k = System.nanoTime() - j;
        this.func_71361_d("Pre render");
        RenderBlocks.field_147843_b = this.gameSettings.field_74347_j;
        this.profiler.startNewMeasure("sound");
        this.field_147127_av.func_147691_a(this.field_71439_g, this.field_71428_T.field_74281_c);
        this.profiler.endMeasure();
        this.profiler.startMeasure("render");
        GL11.glPushMatrix();
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        this.field_147124_at.func_147610_a(true);
        this.profiler.startMeasure("display");
        GL11.glEnable(GL11.GL_TEXTURE_2D);

        if (this.field_71439_g != null && this.field_71439_g.func_70094_T())
        {
            this.gameSettings.field_74320_O = 0;
        }

        this.profiler.endMeasure();

        if (!this.field_71454_w)
        {
            this.profiler.startNewMeasure("gameRenderer");
            this.field_71460_t.func_78480_b(this.field_71428_T.field_74281_c);
            this.profiler.endMeasure();
        }

        GL11.glFlush();
        this.profiler.endMeasure();

        if (!Display.isActive() && this.field_71431_Q)
        {
            this.func_71352_k();
        }

        if (this.gameSettings.field_74330_P && this.gameSettings.field_74329_Q)
        {
            if (!this.profiler.enabled)
            {
                this.profiler.reset();
            }

            this.profiler.enabled = true;
            this.func_71366_a(k);
        }
        else
        {
            this.profiler.enabled = false;
            this.field_71421_N = System.nanoTime();
        }

        this.field_71458_u.func_146254_a();
        this.field_147124_at.func_147609_e();
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        this.field_147124_at.func_147615_c(this.field_71443_c, this.field_71440_d);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        this.field_71460_t.func_152430_c(this.field_71428_T.field_74281_c);
        GL11.glPopMatrix();
        this.profiler.startMeasure("root");
        this.func_147120_f();
        Thread.yield();
        this.profiler.startMeasure("stream");
        this.profiler.startMeasure("update");
        this.field_152353_at.func_152935_j();
        this.profiler.startNewMeasure("submit");
        this.field_152353_at.func_152922_k();
        this.profiler.endMeasure();
        this.profiler.endMeasure();
        this.func_71361_d("Post render");
        ++this.field_71420_M;
        this.field_71445_n = this.func_71356_B() && this.field_71462_r != null && this.field_71462_r.func_73868_f() && !this.field_71437_Z.func_71344_c();

        while (func_71386_F() >= this.field_71419_L + 1000L)
        {
            field_71470_ab = this.field_71420_M;
            this.field_71426_K = field_71470_ab + " fps, " + WorldRenderer.field_78922_b + " chunk updates";
            WorldRenderer.field_78922_b = 0;
            this.field_71419_L += 1000L;
            this.field_71420_M = 0;
            this.field_71427_U.func_76471_b();

            if (!this.field_71427_U.func_76468_d())
            {
                this.field_71427_U.func_76463_a();
            }
        }

        this.profiler.endMeasure();

        if (this.func_147107_h())
        {
            Display.sync(this.func_90020_K());
        }
    }

    public void func_147120_f()
    {
        Display.update();

        if (!this.field_71431_Q && Display.wasResized())
        {
            int i = this.field_71443_c;
            int j = this.field_71440_d;
            this.field_71443_c = Display.getWidth();
            this.field_71440_d = Display.getHeight();

            if (this.field_71443_c != i || this.field_71440_d != j)
            {
                if (this.field_71443_c <= 0)
                {
                    this.field_71443_c = 1;
                }

                if (this.field_71440_d <= 0)
                {
                    this.field_71440_d = 1;
                }

                this.func_71370_a(this.field_71443_c, this.field_71440_d);
            }
        }
    }

    public int func_90020_K()
    {
        return this.field_71441_e == null && this.field_71462_r != null ? 30 : this.gameSettings.field_74350_i;
    }

    public boolean func_147107_h()
    {
        return (float)this.func_90020_K() < GameSettings.Options.FRAMERATE_LIMIT.func_148267_f();
    }

    public void func_71398_f()
    {
        try
        {
            field_71444_a = new byte[0];
            this.field_71438_f.func_72728_f();
        }
        catch (Throwable throwable2)
        {
            ;
        }

        try
        {
            System.gc();
        }
        catch (Throwable throwable1)
        {
            ;
        }

        try
        {
            System.gc();
            this.func_71403_a((WorldClient)null);
        }
        catch (Throwable throwable)
        {
            ;
        }

        System.gc();
    }

    private void func_71383_b(int p_71383_1_)
    {
        List list = this.profiler.func_76321_b(this.field_71465_an);

        if (list != null && !list.isEmpty())
        {
            Profiler.Result result = (Profiler.Result)list.remove(0);

            if (p_71383_1_ == 0)
            {
                if (result.field_76331_c.length() > 0)
                {
                    int j = this.field_71465_an.lastIndexOf(".");

                    if (j >= 0)
                    {
                        this.field_71465_an = this.field_71465_an.substring(0, j);
                    }
                }
            }
            else
            {
                --p_71383_1_;

                if (p_71383_1_ < list.size() && !((Profiler.Result)list.get(p_71383_1_)).field_76331_c.equals("unspecified"))
                {
                    if (this.field_71465_an.length() > 0)
                    {
                        this.field_71465_an = this.field_71465_an + ".";
                    }

                    this.field_71465_an = this.field_71465_an + ((Profiler.Result)list.get(p_71383_1_)).field_76331_c;
                }
            }
        }
    }

    private void func_71366_a(long p_71366_1_)
    {
        if (this.profiler.enabled)
        {
            List<Profiler.Result> list = this.profiler.func_76321_b(this.field_71465_an);
            Profiler.Result result = list.remove(0);
            GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT);
            GL11.glMatrixMode(GL11.GL_PROJECTION);
            GL11.glEnable(GL11.GL_COLOR_MATERIAL);
            GL11.glLoadIdentity();
            GL11.glOrtho(0.0D, (double)this.field_71443_c, (double)this.field_71440_d, 0.0D, 1000.0D, 3000.0D);
            GL11.glMatrixMode(GL11.GL_MODELVIEW);
            GL11.glLoadIdentity();
            GL11.glTranslatef(0.0F, 0.0F, -2000.0F);
            GL11.glLineWidth(1.0F);
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            Tessellator tessellator = Tessellator.field_78398_a;
            short short1 = 160;
            int j = this.field_71443_c - short1 - 10;
            int k = this.field_71440_d - short1 * 2;
            GL11.glEnable(GL11.GL_BLEND);
            tessellator.func_78382_b();
            tessellator.func_78384_a(0, 200);
            tessellator.func_78377_a((double)((float)j - (float)short1 * 1.1F), (double)((float)k - (float)short1 * 0.6F - 16.0F), 0.0D);
            tessellator.func_78377_a((double)((float)j - (float)short1 * 1.1F), (double)(k + short1 * 2), 0.0D);
            tessellator.func_78377_a((double)((float)j + (float)short1 * 1.1F), (double)(k + short1 * 2), 0.0D);
            tessellator.func_78377_a((double)((float)j + (float)short1 * 1.1F), (double)((float)k - (float)short1 * 0.6F - 16.0F), 0.0D);
            tessellator.func_78381_a();
            GL11.glDisable(GL11.GL_BLEND);
            double d0 = 0.0D;
            int i1;

            for (int l = 0; l < list.size(); ++l)
            {
                Profiler.Result result1 = (Profiler.Result)list.get(l);
                i1 = MathHelper.func_76128_c(result1.field_76332_a / 4.0D) + 1;
                tessellator.func_78371_b(6);
                tessellator.func_78378_d(result1.func_76329_a());
                tessellator.func_78377_a((double)j, (double)k, 0.0D);
                int j1;
                float f;
                float f1;
                float f2;

                for (j1 = i1; j1 >= 0; --j1)
                {
                    f = (float)((d0 + result1.field_76332_a * (double)j1 / (double)i1) * Math.PI * 2.0D / 100.0D);
                    f1 = MathHelper.func_76126_a(f) * (float)short1;
                    f2 = MathHelper.func_76134_b(f) * (float)short1 * 0.5F;
                    tessellator.func_78377_a((double)((float)j + f1), (double)((float)k - f2), 0.0D);
                }

                tessellator.func_78381_a();
                tessellator.func_78371_b(5);
                tessellator.func_78378_d((result1.func_76329_a() & 16711422) >> 1);

                for (j1 = i1; j1 >= 0; --j1)
                {
                    f = (float)((d0 + result1.field_76332_a * (double)j1 / (double)i1) * Math.PI * 2.0D / 100.0D);
                    f1 = MathHelper.func_76126_a(f) * (float)short1;
                    f2 = MathHelper.func_76134_b(f) * (float)short1 * 0.5F;
                    tessellator.func_78377_a((double)((float)j + f1), (double)((float)k - f2), 0.0D);
                    tessellator.func_78377_a((double)((float)j + f1), (double)((float)k - f2 + 10.0F), 0.0D);
                }

                tessellator.func_78381_a();
                d0 += result1.field_76332_a;
            }

            DecimalFormat decimalformat = new DecimalFormat("##0.00");
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            String s = "";

            if (!result.field_76331_c.equals("unspecified"))
            {
                s = s + "[0] ";
            }

            if (result.field_76331_c.length() == 0)
            {
                s = s + "ROOT ";
            }
            else
            {
                s = s + result.field_76331_c + " ";
            }

            i1 = 16777215;
            this.field_71466_p.func_78261_a(s, j - short1, k - short1 / 2 - 16, i1);
            this.field_71466_p.func_78261_a(s = decimalformat.format(result.field_76330_b) + "%", j + short1 - this.field_71466_p.func_78256_a(s), k - short1 / 2 - 16, i1);

            for (int k1 = 0; k1 < list.size(); ++k1)
            {
                Profiler.Result result2 = (Profiler.Result)list.get(k1);
                String s1 = "";

                if (result2.field_76331_c.equals("unspecified"))
                {
                    s1 = s1 + "[?] ";
                }
                else
                {
                    s1 = s1 + "[" + (k1 + 1) + "] ";
                }

                s1 = s1 + result2.field_76331_c;
                this.field_71466_p.func_78261_a(s1, j - short1, k + short1 / 2 + k1 * 8 + 20, result2.func_76329_a());
                this.field_71466_p.func_78261_a(s1 = decimalformat.format(result2.field_76332_a) + "%", j + short1 - 50 - this.field_71466_p.func_78256_a(s1), k + short1 / 2 + k1 * 8 + 20, result2.func_76329_a());
                this.field_71466_p.func_78261_a(s1 = decimalformat.format(result2.field_76330_b) + "%", j + short1 - this.field_71466_p.func_78256_a(s1), k + short1 / 2 + k1 * 8 + 20, result2.func_76329_a());
            }
        }
    }

    public void func_71400_g()
    {
        this.field_71425_J = false;
    }

    public void func_71381_h()
    {
        if (Display.isActive())
        {
            if (!this.field_71415_G)
            {
                this.field_71415_G = true;
                this.field_71417_B.func_74372_a();
                this.func_147108_a((GuiScreen)null);
                this.field_71429_W = 10000;
            }
        }
    }

    public void func_71364_i()
    {
        if (this.field_71415_G)
        {
            KeyBinding.func_74506_a();
            this.field_71415_G = false;
            this.field_71417_B.func_74373_b();
        }
    }

    public void func_71385_j()
    {
        if (this.field_71462_r == null)
        {
            this.func_147108_a(new GuiIngameMenu());

            if (this.func_71356_B() && !this.field_71437_Z.func_71344_c())
            {
                this.field_147127_av.func_147689_b();
            }
        }
    }

    private void func_147115_a(boolean p_147115_1_)
    {
        if (!p_147115_1_)
        {
            this.field_71429_W = 0;
        }

        if (this.field_71429_W <= 0)
        {
            if (p_147115_1_ && this.field_71476_x != null && this.field_71476_x.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK)
            {
                int i = this.field_71476_x.field_72311_b;
                int j = this.field_71476_x.field_72312_c;
                int k = this.field_71476_x.field_72309_d;

                if (this.field_71441_e.func_147439_a(i, j, k).func_149688_o() != Material.field_151579_a)
                {
                    this.field_71442_b.func_78759_c(i, j, k, this.field_71476_x.field_72310_e);

                    if (this.field_71439_g.func_82246_f(i, j, k))
                    {
                        this.field_71452_i.func_78867_a(i, j, k, this.field_71476_x.field_72310_e);
                        this.field_71439_g.func_71038_i();
                    }
                }
            }
            else
            {
                this.field_71442_b.func_78767_c();
            }
        }
    }

    private void func_147116_af()
    {
        if (this.field_71429_W <= 0)
        {
            this.field_71439_g.func_71038_i();

            if (this.field_71476_x == null)
            {
                LOG.error("Null returned as \'hitResult\', this shouldn\'t happen!");

                if (this.field_71442_b.func_78762_g())
                {
                    this.field_71429_W = 10;
                }
            }
            else
            {
                switch (Minecraft.SwitchMovingObjectType.field_152390_a[this.field_71476_x.field_72313_a.ordinal()])
                {
                    case 1:
                        this.field_71442_b.func_78764_a(this.field_71439_g, this.field_71476_x.field_72308_g);
                        break;
                    case 2:
                        int i = this.field_71476_x.field_72311_b;
                        int j = this.field_71476_x.field_72312_c;
                        int k = this.field_71476_x.field_72309_d;

                        if (this.field_71441_e.func_147439_a(i, j, k).func_149688_o() == Material.field_151579_a)
                        {
                            if (this.field_71442_b.func_78762_g())
                            {
                                this.field_71429_W = 10;
                            }
                        }
                        else
                        {
                            this.field_71442_b.func_78743_b(i, j, k, this.field_71476_x.field_72310_e);
                        }
                }
            }
        }
    }

    private void func_147121_ag()
    {
        this.field_71467_ac = 4;
        boolean flag = true;
        ItemStack itemstack = this.field_71439_g.inventory.getActiveItem();

        if (this.field_71476_x == null)
        {
            LOG.warn("Null returned as \'hitResult\', this shouldn\'t happen!");
        }
        else
        {
            switch (Minecraft.SwitchMovingObjectType.field_152390_a[this.field_71476_x.field_72313_a.ordinal()])
            {
                case 1:
                    if (this.field_71442_b.func_78768_b(this.field_71439_g, this.field_71476_x.field_72308_g))
                    {
                        flag = false;
                    }

                    break;
                case 2:
                    int i = this.field_71476_x.field_72311_b;
                    int j = this.field_71476_x.field_72312_c;
                    int k = this.field_71476_x.field_72309_d;

                    if (this.field_71441_e.func_147439_a(i, j, k).func_149688_o() != Material.field_151579_a)
                    {
                        int l = itemstack != null ? itemstack.count : 0;

                        if (this.field_71442_b.func_78760_a(this.field_71439_g, this.field_71441_e, itemstack, i, j, k, this.field_71476_x.field_72310_e, this.field_71476_x.field_72307_f))
                        {
                            flag = false;
                            this.field_71439_g.func_71038_i();
                        }

                        if (itemstack == null)
                        {
                            return;
                        }

                        if (itemstack.count == 0)
                        {
                            this.field_71439_g.inventory.cells[this.field_71439_g.inventory.activeItemPosition] = null;
                        }
                        else if (itemstack.count != l || this.field_71442_b.func_78758_h())
                        {
                            this.field_71460_t.field_78516_c.func_78444_b();
                        }
                    }
            }
        }

        if (flag)
        {
            ItemStack itemstack1 = this.field_71439_g.inventory.getActiveItem();

            if (itemstack1 != null && this.field_71442_b.func_78769_a(this.field_71439_g, this.field_71441_e, itemstack1))
            {
                this.field_71460_t.field_78516_c.func_78445_c();
            }
        }
    }

    public void func_71352_k()
    {
        try
        {
            this.field_71431_Q = !this.field_71431_Q;

            if (this.field_71431_Q)
            {
                this.func_110441_Q();
                this.field_71443_c = Display.getDisplayMode().getWidth();
                this.field_71440_d = Display.getDisplayMode().getHeight();

                if (this.field_71443_c <= 0)
                {
                    this.field_71443_c = 1;
                }

                if (this.field_71440_d <= 0)
                {
                    this.field_71440_d = 1;
                }
            }
            else
            {
                Display.setDisplayMode(new DisplayMode(this.field_71436_X, this.field_71435_Y));
                this.field_71443_c = this.field_71436_X;
                this.field_71440_d = this.field_71435_Y;

                if (this.field_71443_c <= 0)
                {
                    this.field_71443_c = 1;
                }

                if (this.field_71440_d <= 0)
                {
                    this.field_71440_d = 1;
                }
            }

            if (this.field_71462_r != null)
            {
                this.func_71370_a(this.field_71443_c, this.field_71440_d);
            }
            else
            {
                this.func_147119_ah();
            }

            Display.setFullscreen(this.field_71431_Q);
            Display.setVSyncEnabled(this.gameSettings.field_74352_v);
            this.func_147120_f();
        }
        catch (Exception exception)
        {
            LOG.error("Couldn\'t toggle fullscreen", exception);
        }
    }

    public void func_71370_a(int p_71370_1_, int p_71370_2_)
    {
        this.field_71443_c = p_71370_1_ <= 0 ? 1 : p_71370_1_;
        this.field_71440_d = p_71370_2_ <= 0 ? 1 : p_71370_2_;

        if (this.field_71462_r != null)
        {
            ScaledResolution scaledresolution = new ScaledResolution(this, p_71370_1_, p_71370_2_);
            int k = scaledresolution.func_78326_a();
            int l = scaledresolution.func_78328_b();
            this.field_71462_r.func_146280_a(this, k, l);
        }

        this.field_71461_s = new LoadingScreenRenderer(this);
        this.func_147119_ah();
    }

    private void func_147119_ah()
    {
        this.field_147124_at.func_147613_a(this.field_71443_c, this.field_71440_d);

        if (this.field_71460_t != null)
        {
            this.field_71460_t.func_147704_a(this.field_71443_c, this.field_71440_d);
        }
    }

    public void func_71407_l()
    {
        this.profiler.startMeasure("scheduledExecutables");
        Queue queue = this.field_152351_aB;

        synchronized (this.field_152351_aB)
        {
            while (!this.field_152351_aB.isEmpty())
            {
                ((FutureTask)this.field_152351_aB.poll()).run();
            }
        }

        this.profiler.endMeasure();

        if (this.field_71467_ac > 0)
        {
            --this.field_71467_ac;
        }

        this.profiler.startMeasure("gui");

        if (!this.field_71445_n)
        {
            this.field_71456_v.func_73831_a();
        }

        this.profiler.startNewMeasure("pick");
        this.field_71460_t.func_78473_a(1.0F);
        this.profiler.startNewMeasure("gameMode");

        if (!this.field_71445_n && this.field_71441_e != null)
        {
            this.field_71442_b.func_78765_e();
        }

        this.profiler.startNewMeasure("textures");

        if (!this.field_71445_n)
        {
            this.field_71446_o.func_110550_d();
        }

        if (this.field_71462_r == null && this.field_71439_g != null)
        {
            if (this.field_71439_g.func_110143_aJ() <= 0.0F)
            {
                this.func_147108_a((GuiScreen)null);
            }
            else if (this.field_71439_g.func_70608_bn() && this.field_71441_e != null)
            {
                this.func_147108_a(new GuiSleepMP());
            }
        }
        else if (this.field_71462_r != null && this.field_71462_r instanceof GuiSleepMP && !this.field_71439_g.func_70608_bn())
        {
            this.func_147108_a((GuiScreen)null);
        }

        if (this.field_71462_r != null)
        {
            this.field_71429_W = 10000;
        }

        CrashReport crashreport;
        CrashReportCategory crashreportcategory;

        if (this.field_71462_r != null)
        {
            try
            {
                this.field_71462_r.func_146269_k();
            }
            catch (Throwable throwable1)
            {
                crashreport = CrashReport.func_85055_a(throwable1, "Updating screen events");
                crashreportcategory = crashreport.func_85058_a("Affected screen");
                crashreportcategory.func_71500_a("Screen name", new Callable()
                {
                    private static final String __OBFID = "CL_00000640";
                    public String call()
                    {
                        return Minecraft.this.field_71462_r.getClass().getCanonicalName();
                    }
                });
                throw new ReportedException(crashreport);
            }

            if (this.field_71462_r != null)
            {
                try
                {
                    this.field_71462_r.func_73876_c();
                }
                catch (Throwable throwable)
                {
                    crashreport = CrashReport.func_85055_a(throwable, "Ticking screen");
                    crashreportcategory = crashreport.func_85058_a("Affected screen");
                    crashreportcategory.func_71500_a("Screen name", new Callable()
                    {
                        private static final String __OBFID = "CL_00000642";
                        public String call()
                        {
                            return Minecraft.this.field_71462_r.getClass().getCanonicalName();
                        }
                    });
                    throw new ReportedException(crashreport);
                }
            }
        }

        if (this.field_71462_r == null || this.field_71462_r.field_146291_p)
        {
            this.profiler.startNewMeasure("mouse");
            int j;

            while (Mouse.next())
            {
                j = Mouse.getEventButton();
                KeyBinding.func_74510_a(j - 100, Mouse.getEventButtonState());

                if (Mouse.getEventButtonState())
                {
                    KeyBinding.func_74507_a(j - 100);
                }

                long k = func_71386_F() - this.field_71423_H;

                if (k <= 200L)
                {
                    int i = Mouse.getEventDWheel();

                    if (i != 0)
                    {
                        this.field_71439_g.inventory.func_70453_c(i);

                        if (this.gameSettings.field_74331_S)
                        {
                            if (i > 0)
                            {
                                i = 1;
                            }

                            if (i < 0)
                            {
                                i = -1;
                            }

                            this.gameSettings.field_74328_V += (float)i * 0.25F;
                        }
                    }

                    if (this.field_71462_r == null)
                    {
                        if (!this.field_71415_G && Mouse.getEventButtonState())
                        {
                            this.func_71381_h();
                        }
                    }
                    else if (this.field_71462_r != null)
                    {
                        this.field_71462_r.func_146274_d();
                    }
                }
            }

            if (this.field_71429_W > 0)
            {
                --this.field_71429_W;
            }

            this.profiler.startNewMeasure("keyboard");
            boolean flag;

            while (Keyboard.next())
            {
                KeyBinding.func_74510_a(Keyboard.getEventKey(), Keyboard.getEventKeyState());

                if (Keyboard.getEventKeyState())
                {
                    KeyBinding.func_74507_a(Keyboard.getEventKey());
                }

                if (this.field_83002_am > 0L)
                {
                    if (func_71386_F() - this.field_83002_am >= 6000L)
                    {
                        throw new ReportedException(new CrashReport("Manually triggered debug crash", new Throwable()));
                    }

                    if (!Keyboard.isKeyDown(46) || !Keyboard.isKeyDown(61))
                    {
                        this.field_83002_am = -1L;
                    }
                }
                else if (Keyboard.isKeyDown(46) && Keyboard.isKeyDown(61))
                {
                    this.field_83002_am = func_71386_F();
                }

                this.func_152348_aa();

                if (Keyboard.getEventKeyState())
                {
                    if (Keyboard.getEventKey() == 62 && this.field_71460_t != null)
                    {
                        this.field_71460_t.func_147703_b();
                    }

                    if (this.field_71462_r != null)
                    {
                        this.field_71462_r.func_146282_l();
                    }
                    else
                    {
                        if (Keyboard.getEventKey() == 1)
                        {
                            this.func_71385_j();
                        }

                        if (Keyboard.getEventKey() == 31 && Keyboard.isKeyDown(61))
                        {
                            this.func_110436_a();
                        }

                        if (Keyboard.getEventKey() == 20 && Keyboard.isKeyDown(61))
                        {
                            this.func_110436_a();
                        }

                        if (Keyboard.getEventKey() == 33 && Keyboard.isKeyDown(61))
                        {
                            flag = Keyboard.isKeyDown(42) | Keyboard.isKeyDown(54);
                            this.gameSettings.func_74306_a(GameSettings.Options.RENDER_DISTANCE, flag ? -1 : 1);
                        }

                        if (Keyboard.getEventKey() == 30 && Keyboard.isKeyDown(61))
                        {
                            this.field_71438_f.func_72712_a();
                        }

                        if (Keyboard.getEventKey() == 35 && Keyboard.isKeyDown(61))
                        {
                            this.gameSettings.field_82882_x = !this.gameSettings.field_82882_x;
                            this.gameSettings.func_74303_b();
                        }

                        if (Keyboard.getEventKey() == 48 && Keyboard.isKeyDown(61))
                        {
                            RenderManager.field_85095_o = !RenderManager.field_85095_o;
                        }

                        if (Keyboard.getEventKey() == 25 && Keyboard.isKeyDown(61))
                        {
                            this.gameSettings.field_82881_y = !this.gameSettings.field_82881_y;
                            this.gameSettings.func_74303_b();
                        }

                        if (Keyboard.getEventKey() == 59)
                        {
                            this.gameSettings.field_74319_N = !this.gameSettings.field_74319_N;
                        }

                        if (Keyboard.getEventKey() == 61)
                        {
                            this.gameSettings.field_74330_P = !this.gameSettings.field_74330_P;
                            this.gameSettings.field_74329_Q = GuiScreen.func_146272_n();
                        }

                        if (this.gameSettings.field_151457_aa.func_151468_f())
                        {
                            ++this.gameSettings.field_74320_O;

                            if (this.gameSettings.field_74320_O > 2)
                            {
                                this.gameSettings.field_74320_O = 0;
                            }
                        }

                        if (this.gameSettings.field_151458_ab.func_151468_f())
                        {
                            this.gameSettings.field_74326_T = !this.gameSettings.field_74326_T;
                        }
                    }

                    if (this.gameSettings.field_74330_P && this.gameSettings.field_74329_Q)
                    {
                        if (Keyboard.getEventKey() == 11)
                        {
                            this.func_71383_b(0);
                        }

                        for (j = 0; j < 9; ++j)
                        {
                            if (Keyboard.getEventKey() == 2 + j)
                            {
                                this.func_71383_b(j + 1);
                            }
                        }
                    }
                }
            }

            for (j = 0; j < 9; ++j)
            {
                if (this.gameSettings.field_151456_ac[j].func_151468_f())
                {
                    this.field_71439_g.inventory.activeItemPosition = j;
                }
            }

            flag = this.gameSettings.field_74343_n != EntityPlayer.EnumChatVisibility.HIDDEN;

            while (this.gameSettings.field_151445_Q.func_151468_f())
            {
                if (this.field_71442_b.func_110738_j())
                {
                    this.field_71439_g.func_110322_i();
                }
                else
                {
                    this.func_147114_u().func_147297_a(new C16PacketClientStatus(C16PacketClientStatus.EnumState.OPEN_INVENTORY_ACHIEVEMENT));
                    this.func_147108_a(new GuiInventory(this.field_71439_g));
                }
            }

            while (this.gameSettings.field_74316_C.func_151468_f())
            {
                this.field_71439_g.func_71040_bB(GuiScreen.func_146271_m());
            }

            while (this.gameSettings.field_74310_D.func_151468_f() && flag)
            {
                this.func_147108_a(new GuiChat());
            }

            if (this.field_71462_r == null && this.gameSettings.field_74323_J.func_151468_f() && flag)
            {
                this.func_147108_a(new GuiChat("/"));
            }

            if (this.field_71439_g.func_71039_bw())
            {
                if (!this.gameSettings.field_74313_G.func_151470_d())
                {
                    this.field_71442_b.func_78766_c(this.field_71439_g);
                }

                label391:

                while (true)
                {
                    if (!this.gameSettings.field_74312_F.func_151468_f())
                    {
                        while (this.gameSettings.field_74313_G.func_151468_f())
                        {
                            ;
                        }

                        while (true)
                        {
                            if (this.gameSettings.field_74322_I.func_151468_f())
                            {
                                continue;
                            }

                            break label391;
                        }
                    }
                }
            }
            else
            {
                while (this.gameSettings.field_74312_F.func_151468_f())
                {
                    this.func_147116_af();
                }

                while (this.gameSettings.field_74313_G.func_151468_f())
                {
                    this.func_147121_ag();
                }

                while (this.gameSettings.field_74322_I.func_151468_f())
                {
                    this.func_147112_ai();
                }
            }

            if (this.gameSettings.field_74313_G.func_151470_d() && this.field_71467_ac == 0 && !this.field_71439_g.func_71039_bw())
            {
                this.func_147121_ag();
            }

            this.func_147115_a(this.field_71462_r == null && this.gameSettings.field_74312_F.func_151470_d() && this.field_71415_G);
        }

        if (this.field_71441_e != null)
        {
            if (this.field_71439_g != null)
            {
                ++this.field_71457_ai;

                if (this.field_71457_ai == 30)
                {
                    this.field_71457_ai = 0;
                    this.field_71441_e.func_72897_h(this.field_71439_g);
                }
            }

            this.profiler.startNewMeasure("gameRenderer");

            if (!this.field_71445_n)
            {
                this.field_71460_t.func_78464_a();
            }

            this.profiler.startNewMeasure("levelRenderer");

            if (!this.field_71445_n)
            {
                this.field_71438_f.func_72734_e();
            }

            this.profiler.startNewMeasure("level");

            if (!this.field_71445_n)
            {
                if (this.field_71441_e.field_73016_r > 0)
                {
                    --this.field_71441_e.field_73016_r;
                }

                this.field_71441_e.func_72939_s();
            }
        }

        if (!this.field_71445_n)
        {
            this.field_147126_aw.func_73660_a();
            this.field_147127_av.func_73660_a();
        }

        if (this.field_71441_e != null)
        {
            if (!this.field_71445_n)
            {
                this.field_71441_e.func_72891_a(this.field_71441_e.field_73013_u != EnumDifficulty.PEACEFUL, true);

                try
                {
                    this.field_71441_e.func_72835_b();
                }
                catch (Throwable throwable2)
                {
                    crashreport = CrashReport.func_85055_a(throwable2, "Exception in world tick");

                    if (this.field_71441_e == null)
                    {
                        crashreportcategory = crashreport.func_85058_a("Affected level");
                        crashreportcategory.func_71507_a("Problem", "Level is null!");
                    }
                    else
                    {
                        this.field_71441_e.func_72914_a(crashreport);
                    }

                    throw new ReportedException(crashreport);
                }
            }

            this.profiler.startNewMeasure("animateTick");

            if (!this.field_71445_n && this.field_71441_e != null)
            {
                this.field_71441_e.func_73029_E(MathHelper.func_76128_c(this.field_71439_g.field_70165_t), MathHelper.func_76128_c(this.field_71439_g.field_70163_u), MathHelper.func_76128_c(this.field_71439_g.field_70161_v));
            }

            this.profiler.startNewMeasure("particles");

            if (!this.field_71445_n)
            {
                this.field_71452_i.func_78868_a();
            }
        }
        else if (this.field_71453_ak != null)
        {
            this.profiler.startNewMeasure("pendingConnection");
            this.field_71453_ak.func_74428_b();
        }

        this.profiler.endMeasure();
        this.field_71423_H = func_71386_F();
    }

    public void func_71371_a(String p_71371_1_, String p_71371_2_, WorldSettings p_71371_3_)
    {
        this.func_71403_a((WorldClient)null);
        System.gc();
        ISaveHandler isavehandler = this.field_71469_aa.func_75804_a(p_71371_1_, false);
        WorldInfo worldinfo = isavehandler.func_75757_d();

        if (worldinfo == null && p_71371_3_ != null)
        {
            worldinfo = new WorldInfo(p_71371_3_, p_71371_1_);
            isavehandler.func_75761_a(worldinfo);
        }

        if (p_71371_3_ == null)
        {
            p_71371_3_ = new WorldSettings(worldinfo);
        }

        try
        {
            this.field_71437_Z = new IntegratedServer(this, p_71371_1_, p_71371_2_, p_71371_3_);
            this.field_71437_Z.func_71256_s();
            this.field_71455_al = true;
        }
        catch (Throwable throwable)
        {
            CrashReport crashreport = CrashReport.func_85055_a(throwable, "Starting integrated server");
            CrashReportCategory crashreportcategory = crashreport.func_85058_a("Starting integrated server");
            crashreportcategory.func_71507_a("Level ID", p_71371_1_);
            crashreportcategory.func_71507_a("Level Name", p_71371_2_);
            throw new ReportedException(crashreport);
        }

        this.field_71461_s.func_73720_a(I18n.func_135052_a("menu.loadingLevel", new Object[0]));

        while (!this.field_71437_Z.func_71200_ad())
        {
            String s2 = this.field_71437_Z.func_71195_b_();

            if (s2 != null)
            {
                this.field_71461_s.func_73719_c(I18n.func_135052_a(s2, new Object[0]));
            }
            else
            {
                this.field_71461_s.func_73719_c("");
            }

            try
            {
                Thread.sleep(200L);
            }
            catch (InterruptedException interruptedexception)
            {
                ;
            }
        }

        this.func_147108_a((GuiScreen)null);
        SocketAddress socketaddress = this.field_71437_Z.func_147137_ag().func_151270_a();
        NetworkManager networkmanager = NetworkManager.func_150722_a(socketaddress);
        networkmanager.func_150719_a(new NetHandlerLoginClient(networkmanager, this, (GuiScreen)null));
        networkmanager.func_150725_a(new C00Handshake(5, socketaddress.toString(), 0, EnumConnectionState.LOGIN), new GenericFutureListener[0]);
        networkmanager.func_150725_a(new C00PacketLoginStart(this.func_110432_I().func_148256_e()), new GenericFutureListener[0]);
        this.field_71453_ak = networkmanager;
    }

    public void func_71403_a(WorldClient p_71403_1_)
    {
        this.func_71353_a(p_71403_1_, "");
    }

    public void func_71353_a(WorldClient p_71353_1_, String p_71353_2_)
    {
        if (p_71353_1_ == null)
        {
            NetHandlerPlayClient nethandlerplayclient = this.func_147114_u();

            if (nethandlerplayclient != null)
            {
                nethandlerplayclient.func_147296_c();
            }

            if (this.field_71437_Z != null)
            {
                this.field_71437_Z.func_71263_m();
            }

            this.field_71437_Z = null;
            this.field_71458_u.func_146257_b();
            this.field_71460_t.func_147701_i().func_148249_a();
        }

        this.field_71451_h = null;
        this.field_71453_ak = null;

        if (this.field_71461_s != null)
        {
            this.field_71461_s.func_73721_b(p_71353_2_);
            this.field_71461_s.func_73719_c("");
        }

        if (p_71353_1_ == null && this.field_71441_e != null)
        {
            if (this.field_110448_aq.func_148530_e() != null)
            {
                this.func_147106_B();
            }

            this.field_110448_aq.func_148529_f();
            this.func_71351_a((ServerData)null);
            this.field_71455_al = false;
        }

        this.field_147127_av.func_147690_c();
        this.field_71441_e = p_71353_1_;

        if (p_71353_1_ != null)
        {
            if (this.field_71438_f != null)
            {
                this.field_71438_f.func_72732_a(p_71353_1_);
            }

            if (this.field_71452_i != null)
            {
                this.field_71452_i.func_78870_a(p_71353_1_);
            }

            if (this.field_71439_g == null)
            {
                this.field_71439_g = this.field_71442_b.func_147493_a(p_71353_1_, new StatFileWriter());
                this.field_71442_b.func_78745_b(this.field_71439_g);
            }

            this.field_71439_g.func_70065_x();
            p_71353_1_.func_72838_d(this.field_71439_g);
            this.field_71439_g.field_71158_b = new MovementInputFromOptions(this.gameSettings);
            this.field_71442_b.func_78748_a(this.field_71439_g);
            this.field_71451_h = this.field_71439_g;
        }
        else
        {
            this.field_71469_aa.func_75800_d();
            this.field_71439_g = null;
        }

        System.gc();
        this.field_71423_H = 0L;
    }

    public String func_71393_m()
    {
        return this.field_71438_f.func_72735_c();
    }

    public String func_71408_n()
    {
        return this.field_71438_f.func_72723_d();
    }

    public String func_71388_o()
    {
        return this.field_71441_e.func_72827_u();
    }

    public String func_71374_p()
    {
        return "P: " + this.field_71452_i.func_78869_b() + ". T: " + this.field_71441_e.func_72981_t();
    }

    public void func_71354_a(int p_71354_1_)
    {
        this.field_71441_e.func_72974_f();
        this.field_71441_e.func_73022_a();
        int j = 0;
        String s = null;

        if (this.field_71439_g != null)
        {
            j = this.field_71439_g.func_145782_y();
            this.field_71441_e.func_72900_e(this.field_71439_g);
            s = this.field_71439_g.func_142021_k();
        }

        this.field_71451_h = null;
        this.field_71439_g = this.field_71442_b.func_147493_a(this.field_71441_e, this.field_71439_g == null ? new StatFileWriter() : this.field_71439_g.func_146107_m());
        this.field_71439_g.field_71093_bK = p_71354_1_;
        this.field_71451_h = this.field_71439_g;
        this.field_71439_g.func_70065_x();
        this.field_71439_g.func_142020_c(s);
        this.field_71441_e.func_72838_d(this.field_71439_g);
        this.field_71442_b.func_78745_b(this.field_71439_g);
        this.field_71439_g.field_71158_b = new MovementInputFromOptions(this.gameSettings);
        this.field_71439_g.func_145769_d(j);
        this.field_71442_b.func_78748_a(this.field_71439_g);

        if (this.field_71462_r instanceof GuiGameOver)
        {
            this.func_147108_a((GuiScreen)null);
        }
    }

    public final boolean func_71355_q()
    {
        return this.field_71459_aj;
    }

    public NetHandlerPlayClient func_147114_u()
    {
        return this.field_71439_g != null ? this.field_71439_g.field_71174_a : null;
    }

    public static boolean func_71382_s()
    {
        return field_71432_P == null || !field_71432_P.gameSettings.field_74319_N;
    }

    public static boolean func_71375_t()
    {
        return field_71432_P != null && field_71432_P.gameSettings.field_74347_j;
    }

    public static boolean func_71379_u()
    {
        return field_71432_P != null && field_71432_P.gameSettings.field_74348_k != 0;
    }

    private void func_147112_ai()
    {
        if (this.field_71476_x != null)
        {
            boolean flag = this.field_71439_g.capabilities.instabuild;
            int i = 0;
            boolean flag1 = false;
            Item item;
            int j;

            if (this.field_71476_x.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK)
            {
                j = this.field_71476_x.field_72311_b;
                int k = this.field_71476_x.field_72312_c;
                int l = this.field_71476_x.field_72309_d;
                Block block = this.field_71441_e.func_147439_a(j, k, l);

                if (block.func_149688_o() == Material.field_151579_a)
                {
                    return;
                }

                item = block.func_149694_d(this.field_71441_e, j, k, l);

                if (item == null)
                {
                    return;
                }

                flag1 = item.func_77614_k();
                Block block1 = item instanceof ItemBlock && !block.func_149648_K() ? Block.func_149634_a(item) : block;
                i = block1.func_149643_k(this.field_71441_e, j, k, l);
            }
            else
            {
                if (this.field_71476_x.field_72313_a != MovingObjectPosition.MovingObjectType.ENTITY || this.field_71476_x.field_72308_g == null || !flag)
                {
                    return;
                }

                if (this.field_71476_x.field_72308_g instanceof EntityPainting)
                {
                    item = Items.PAINTING;
                }
                else if (this.field_71476_x.field_72308_g instanceof EntityLeashKnot)
                {
                    item = Items.LEAD;
                }
                else if (this.field_71476_x.field_72308_g instanceof EntityItemFrame)
                {
                    EntityItemFrame entityitemframe = (EntityItemFrame)this.field_71476_x.field_72308_g;
                    ItemStack itemstack = entityitemframe.func_82335_i();

                    if (itemstack == null)
                    {
                        item = Items.ITEM_FRAME;
                    }
                    else
                    {
                        item = itemstack.getBaseItem();
                        i = itemstack.func_77960_j();
                        flag1 = true;
                    }
                }
                else if (this.field_71476_x.field_72308_g instanceof EntityMinecart)
                {
                    EntityMinecart entityminecart = (EntityMinecart)this.field_71476_x.field_72308_g;

                    if (entityminecart.func_94087_l() == 2)
                    {
                        item = Items.FURNACE_MINECART;
                    }
                    else if (entityminecart.func_94087_l() == 1)
                    {
                        item = Items.CHEST_MINECART;
                    }
                    else if (entityminecart.func_94087_l() == 3)
                    {
                        item = Items.TNT_MINECART;
                    }
                    else if (entityminecart.func_94087_l() == 5)
                    {
                        item = Items.HOPPER_MINECART;
                    }
                    else if (entityminecart.func_94087_l() == 6)
                    {
                        item = Items.COMMAND_BLOCK_MINECART;
                    }
                    else
                    {
                        item = Items.MINECART;
                    }
                }
                else if (this.field_71476_x.field_72308_g instanceof EntityBoat)
                {
                    item = Items.BOAT;
                }
                else
                {
                    item = Items.SPAWN_EGG;
                    i = EntityList.func_75619_a(this.field_71476_x.field_72308_g);
                    flag1 = true;

                    if (i <= 0 || !EntityList.field_75627_a.containsKey(Integer.valueOf(i)))
                    {
                        return;
                    }
                }
            }

            this.field_71439_g.inventory.func_146030_a(item, i, flag1, flag);

            if (flag)
            {
                j = this.field_71439_g.field_71069_bz.field_75151_b.size() - 9 + this.field_71439_g.inventory.activeItemPosition;
                this.field_71442_b.func_78761_a(this.field_71439_g.inventory.func_70301_a(this.field_71439_g.inventory.activeItemPosition), j);
            }
        }
    }

    public CrashReport func_71396_d(CrashReport p_71396_1_)
    {
        p_71396_1_.func_85056_g().func_71500_a("Launched Version", new Callable()
        {
            private static final String __OBFID = "CL_00000643";
            public String call()
            {
                return Minecraft.this.field_110447_Z;
            }
        });
        p_71396_1_.func_85056_g().func_71500_a("LWJGL", new Callable()
        {
            private static final String __OBFID = "CL_00000644";
            public String call()
            {
                return Sys.getVersion();
            }
        });
        p_71396_1_.func_85056_g().func_71500_a("OpenGL", new Callable()
        {
            private static final String __OBFID = "CL_00000645";
            public String call()
            {
                return GL11.glGetString(GL11.GL_RENDERER) + " GL version " + GL11.glGetString(GL11.GL_VERSION) + ", " + GL11.glGetString(GL11.GL_VENDOR);
            }
        });
        p_71396_1_.func_85056_g().func_71500_a("GL Caps", new Callable()
        {
            private static final String __OBFID = "CL_00000646";
            public String call()
            {
                return OpenGlHelper.func_153172_c();
            }
        });
        p_71396_1_.func_85056_g().func_71500_a("Is Modded", new Callable()
        {
            private static final String __OBFID = "CL_00000647";
            public String call()
            {
                String s = ClientBrandRetriever.getClientModName();
                return !s.equals("vanilla") ? "Definitely; Client brand changed to \'" + s + "\'" : (Minecraft.class.getSigners() == null ? "Very likely; Jar signature invalidated" : "Probably not. Jar signature remains and client brand is untouched.");
            }
        });
        p_71396_1_.func_85056_g().func_71500_a("Type", new Callable()
        {
            private static final String __OBFID = "CL_00000633";
            public String call()
            {
                return "Client (map_client.txt)";
            }
        });
        p_71396_1_.func_85056_g().func_71500_a("Resource Packs", new Callable()
        {
            private static final String __OBFID = "CL_00000634";
            public String call()
            {
                return Minecraft.this.gameSettings.field_151453_l.toString();
            }
        });
        p_71396_1_.func_85056_g().func_71500_a("Current Language", new Callable()
        {
            private static final String __OBFID = "CL_00000635";
            public String call()
            {
                return Minecraft.this.field_135017_as.func_135041_c().toString();
            }
        });
        p_71396_1_.func_85056_g().func_71500_a("Profiler Position", new Callable()
        {
            private static final String __OBFID = "CL_00000636";
            public String call()
            {
                return Minecraft.this.profiler.enabled ? Minecraft.this.profiler.getNextAction() : "N/A (disabled)";
            }
        });
        p_71396_1_.func_85056_g().func_71500_a("Vec3 Pool Size", new Callable()
        {
            private static final String __OBFID = "CL_00000637";
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
        p_71396_1_.func_85056_g().func_71500_a("Anisotropic Filtering", new Callable()
        {
            private static final String __OBFID = "CL_00001853";
            public String func_152388_a()
            {
                return Minecraft.this.gameSettings.field_151443_J == 1 ? "Off (1)" : "On (" + Minecraft.this.gameSettings.field_151443_J + ")";
            }
            public Object call()
            {
                return this.func_152388_a();
            }
        });

        if (this.field_71441_e != null)
        {
            this.field_71441_e.func_72914_a(p_71396_1_);
        }

        return p_71396_1_;
    }

    public static Minecraft func_71410_x()
    {
        return field_71432_P;
    }

    public void func_147106_B()
    {
        this.field_71468_ad = true;
    }

    public void func_70000_a(PlayerUsageSnooper p_70000_1_)
    {
        p_70000_1_.func_152768_a("fps", Integer.valueOf(field_71470_ab));
        p_70000_1_.func_152768_a("vsync_enabled", Boolean.valueOf(this.gameSettings.field_74352_v));
        p_70000_1_.func_152768_a("display_frequency", Integer.valueOf(Display.getDisplayMode().getFrequency()));
        p_70000_1_.func_152768_a("display_type", this.field_71431_Q ? "fullscreen" : "windowed");
        p_70000_1_.func_152768_a("run_time", Long.valueOf((MinecraftServer.func_130071_aq() - p_70000_1_.func_130105_g()) / 60L * 1000L));
        p_70000_1_.func_152768_a("resource_packs", Integer.valueOf(this.field_110448_aq.func_110613_c().size()));
        int i = 0;
        Iterator iterator = this.field_110448_aq.func_110613_c().iterator();

        while (iterator.hasNext())
        {
            ResourcePackRepository.Entry entry = (ResourcePackRepository.Entry)iterator.next();
            p_70000_1_.func_152768_a("resource_pack[" + i++ + "]", entry.func_110515_d());
        }

        if (this.field_71437_Z != null && this.field_71437_Z.func_80003_ah() != null)
        {
            p_70000_1_.func_152768_a("snooper_partner", this.field_71437_Z.func_80003_ah().func_80006_f());
        }
    }

    public void func_70001_b(PlayerUsageSnooper p_70001_1_)
    {
        p_70001_1_.func_152767_b("opengl_version", GL11.glGetString(GL11.GL_VERSION));
        p_70001_1_.func_152767_b("opengl_vendor", GL11.glGetString(GL11.GL_VENDOR));
        p_70001_1_.func_152767_b("client_brand", ClientBrandRetriever.getClientModName());
        p_70001_1_.func_152767_b("launched_version", this.field_110447_Z);
        ContextCapabilities contextcapabilities = GLContext.getCapabilities();
        p_70001_1_.func_152767_b("gl_caps[ARB_arrays_of_arrays]", Boolean.valueOf(contextcapabilities.GL_ARB_arrays_of_arrays));
        p_70001_1_.func_152767_b("gl_caps[ARB_base_instance]", Boolean.valueOf(contextcapabilities.GL_ARB_base_instance));
        p_70001_1_.func_152767_b("gl_caps[ARB_blend_func_extended]", Boolean.valueOf(contextcapabilities.GL_ARB_blend_func_extended));
        p_70001_1_.func_152767_b("gl_caps[ARB_clear_buffer_object]", Boolean.valueOf(contextcapabilities.GL_ARB_clear_buffer_object));
        p_70001_1_.func_152767_b("gl_caps[ARB_color_buffer_float]", Boolean.valueOf(contextcapabilities.GL_ARB_color_buffer_float));
        p_70001_1_.func_152767_b("gl_caps[ARB_compatibility]", Boolean.valueOf(contextcapabilities.GL_ARB_compatibility));
        p_70001_1_.func_152767_b("gl_caps[ARB_compressed_texture_pixel_storage]", Boolean.valueOf(contextcapabilities.GL_ARB_compressed_texture_pixel_storage));
        p_70001_1_.func_152767_b("gl_caps[ARB_compute_shader]", Boolean.valueOf(contextcapabilities.GL_ARB_compute_shader));
        p_70001_1_.func_152767_b("gl_caps[ARB_copy_buffer]", Boolean.valueOf(contextcapabilities.GL_ARB_copy_buffer));
        p_70001_1_.func_152767_b("gl_caps[ARB_copy_image]", Boolean.valueOf(contextcapabilities.GL_ARB_copy_image));
        p_70001_1_.func_152767_b("gl_caps[ARB_depth_buffer_float]", Boolean.valueOf(contextcapabilities.GL_ARB_depth_buffer_float));
        p_70001_1_.func_152767_b("gl_caps[ARB_compute_shader]", Boolean.valueOf(contextcapabilities.GL_ARB_compute_shader));
        p_70001_1_.func_152767_b("gl_caps[ARB_copy_buffer]", Boolean.valueOf(contextcapabilities.GL_ARB_copy_buffer));
        p_70001_1_.func_152767_b("gl_caps[ARB_copy_image]", Boolean.valueOf(contextcapabilities.GL_ARB_copy_image));
        p_70001_1_.func_152767_b("gl_caps[ARB_depth_buffer_float]", Boolean.valueOf(contextcapabilities.GL_ARB_depth_buffer_float));
        p_70001_1_.func_152767_b("gl_caps[ARB_depth_clamp]", Boolean.valueOf(contextcapabilities.GL_ARB_depth_clamp));
        p_70001_1_.func_152767_b("gl_caps[ARB_depth_texture]", Boolean.valueOf(contextcapabilities.GL_ARB_depth_texture));
        p_70001_1_.func_152767_b("gl_caps[ARB_draw_buffers]", Boolean.valueOf(contextcapabilities.GL_ARB_draw_buffers));
        p_70001_1_.func_152767_b("gl_caps[ARB_draw_buffers_blend]", Boolean.valueOf(contextcapabilities.GL_ARB_draw_buffers_blend));
        p_70001_1_.func_152767_b("gl_caps[ARB_draw_elements_base_vertex]", Boolean.valueOf(contextcapabilities.GL_ARB_draw_elements_base_vertex));
        p_70001_1_.func_152767_b("gl_caps[ARB_draw_indirect]", Boolean.valueOf(contextcapabilities.GL_ARB_draw_indirect));
        p_70001_1_.func_152767_b("gl_caps[ARB_draw_instanced]", Boolean.valueOf(contextcapabilities.GL_ARB_draw_instanced));
        p_70001_1_.func_152767_b("gl_caps[ARB_explicit_attrib_location]", Boolean.valueOf(contextcapabilities.GL_ARB_explicit_attrib_location));
        p_70001_1_.func_152767_b("gl_caps[ARB_explicit_uniform_location]", Boolean.valueOf(contextcapabilities.GL_ARB_explicit_uniform_location));
        p_70001_1_.func_152767_b("gl_caps[ARB_fragment_layer_viewport]", Boolean.valueOf(contextcapabilities.GL_ARB_fragment_layer_viewport));
        p_70001_1_.func_152767_b("gl_caps[ARB_fragment_program]", Boolean.valueOf(contextcapabilities.GL_ARB_fragment_program));
        p_70001_1_.func_152767_b("gl_caps[ARB_fragment_shader]", Boolean.valueOf(contextcapabilities.GL_ARB_fragment_shader));
        p_70001_1_.func_152767_b("gl_caps[ARB_fragment_program_shadow]", Boolean.valueOf(contextcapabilities.GL_ARB_fragment_program_shadow));
        p_70001_1_.func_152767_b("gl_caps[ARB_framebuffer_object]", Boolean.valueOf(contextcapabilities.GL_ARB_framebuffer_object));
        p_70001_1_.func_152767_b("gl_caps[ARB_framebuffer_sRGB]", Boolean.valueOf(contextcapabilities.GL_ARB_framebuffer_sRGB));
        p_70001_1_.func_152767_b("gl_caps[ARB_geometry_shader4]", Boolean.valueOf(contextcapabilities.GL_ARB_geometry_shader4));
        p_70001_1_.func_152767_b("gl_caps[ARB_gpu_shader5]", Boolean.valueOf(contextcapabilities.GL_ARB_gpu_shader5));
        p_70001_1_.func_152767_b("gl_caps[ARB_half_float_pixel]", Boolean.valueOf(contextcapabilities.GL_ARB_half_float_pixel));
        p_70001_1_.func_152767_b("gl_caps[ARB_half_float_vertex]", Boolean.valueOf(contextcapabilities.GL_ARB_half_float_vertex));
        p_70001_1_.func_152767_b("gl_caps[ARB_instanced_arrays]", Boolean.valueOf(contextcapabilities.GL_ARB_instanced_arrays));
        p_70001_1_.func_152767_b("gl_caps[ARB_map_buffer_alignment]", Boolean.valueOf(contextcapabilities.GL_ARB_map_buffer_alignment));
        p_70001_1_.func_152767_b("gl_caps[ARB_map_buffer_range]", Boolean.valueOf(contextcapabilities.GL_ARB_map_buffer_range));
        p_70001_1_.func_152767_b("gl_caps[ARB_multisample]", Boolean.valueOf(contextcapabilities.GL_ARB_multisample));
        p_70001_1_.func_152767_b("gl_caps[ARB_multitexture]", Boolean.valueOf(contextcapabilities.GL_ARB_multitexture));
        p_70001_1_.func_152767_b("gl_caps[ARB_occlusion_query2]", Boolean.valueOf(contextcapabilities.GL_ARB_occlusion_query2));
        p_70001_1_.func_152767_b("gl_caps[ARB_pixel_buffer_object]", Boolean.valueOf(contextcapabilities.GL_ARB_pixel_buffer_object));
        p_70001_1_.func_152767_b("gl_caps[ARB_seamless_cube_map]", Boolean.valueOf(contextcapabilities.GL_ARB_seamless_cube_map));
        p_70001_1_.func_152767_b("gl_caps[ARB_shader_objects]", Boolean.valueOf(contextcapabilities.GL_ARB_shader_objects));
        p_70001_1_.func_152767_b("gl_caps[ARB_shader_stencil_export]", Boolean.valueOf(contextcapabilities.GL_ARB_shader_stencil_export));
        p_70001_1_.func_152767_b("gl_caps[ARB_shader_texture_lod]", Boolean.valueOf(contextcapabilities.GL_ARB_shader_texture_lod));
        p_70001_1_.func_152767_b("gl_caps[ARB_shadow]", Boolean.valueOf(contextcapabilities.GL_ARB_shadow));
        p_70001_1_.func_152767_b("gl_caps[ARB_shadow_ambient]", Boolean.valueOf(contextcapabilities.GL_ARB_shadow_ambient));
        p_70001_1_.func_152767_b("gl_caps[ARB_stencil_texturing]", Boolean.valueOf(contextcapabilities.GL_ARB_stencil_texturing));
        p_70001_1_.func_152767_b("gl_caps[ARB_sync]", Boolean.valueOf(contextcapabilities.GL_ARB_sync));
        p_70001_1_.func_152767_b("gl_caps[ARB_tessellation_shader]", Boolean.valueOf(contextcapabilities.GL_ARB_tessellation_shader));
        p_70001_1_.func_152767_b("gl_caps[ARB_texture_border_clamp]", Boolean.valueOf(contextcapabilities.GL_ARB_texture_border_clamp));
        p_70001_1_.func_152767_b("gl_caps[ARB_texture_buffer_object]", Boolean.valueOf(contextcapabilities.GL_ARB_texture_buffer_object));
        p_70001_1_.func_152767_b("gl_caps[ARB_texture_cube_map]", Boolean.valueOf(contextcapabilities.GL_ARB_texture_cube_map));
        p_70001_1_.func_152767_b("gl_caps[ARB_texture_cube_map_array]", Boolean.valueOf(contextcapabilities.GL_ARB_texture_cube_map_array));
        p_70001_1_.func_152767_b("gl_caps[ARB_texture_non_power_of_two]", Boolean.valueOf(contextcapabilities.GL_ARB_texture_non_power_of_two));
        p_70001_1_.func_152767_b("gl_caps[ARB_uniform_buffer_object]", Boolean.valueOf(contextcapabilities.GL_ARB_uniform_buffer_object));
        p_70001_1_.func_152767_b("gl_caps[ARB_vertex_blend]", Boolean.valueOf(contextcapabilities.GL_ARB_vertex_blend));
        p_70001_1_.func_152767_b("gl_caps[ARB_vertex_buffer_object]", Boolean.valueOf(contextcapabilities.GL_ARB_vertex_buffer_object));
        p_70001_1_.func_152767_b("gl_caps[ARB_vertex_program]", Boolean.valueOf(contextcapabilities.GL_ARB_vertex_program));
        p_70001_1_.func_152767_b("gl_caps[ARB_vertex_shader]", Boolean.valueOf(contextcapabilities.GL_ARB_vertex_shader));
        p_70001_1_.func_152767_b("gl_caps[EXT_bindable_uniform]", Boolean.valueOf(contextcapabilities.GL_EXT_bindable_uniform));
        p_70001_1_.func_152767_b("gl_caps[EXT_blend_equation_separate]", Boolean.valueOf(contextcapabilities.GL_EXT_blend_equation_separate));
        p_70001_1_.func_152767_b("gl_caps[EXT_blend_func_separate]", Boolean.valueOf(contextcapabilities.GL_EXT_blend_func_separate));
        p_70001_1_.func_152767_b("gl_caps[EXT_blend_minmax]", Boolean.valueOf(contextcapabilities.GL_EXT_blend_minmax));
        p_70001_1_.func_152767_b("gl_caps[EXT_blend_subtract]", Boolean.valueOf(contextcapabilities.GL_EXT_blend_subtract));
        p_70001_1_.func_152767_b("gl_caps[EXT_draw_instanced]", Boolean.valueOf(contextcapabilities.GL_EXT_draw_instanced));
        p_70001_1_.func_152767_b("gl_caps[EXT_framebuffer_multisample]", Boolean.valueOf(contextcapabilities.GL_EXT_framebuffer_multisample));
        p_70001_1_.func_152767_b("gl_caps[EXT_framebuffer_object]", Boolean.valueOf(contextcapabilities.GL_EXT_framebuffer_object));
        p_70001_1_.func_152767_b("gl_caps[EXT_framebuffer_sRGB]", Boolean.valueOf(contextcapabilities.GL_EXT_framebuffer_sRGB));
        p_70001_1_.func_152767_b("gl_caps[EXT_geometry_shader4]", Boolean.valueOf(contextcapabilities.GL_EXT_geometry_shader4));
        p_70001_1_.func_152767_b("gl_caps[EXT_gpu_program_parameters]", Boolean.valueOf(contextcapabilities.GL_EXT_gpu_program_parameters));
        p_70001_1_.func_152767_b("gl_caps[EXT_gpu_shader4]", Boolean.valueOf(contextcapabilities.GL_EXT_gpu_shader4));
        p_70001_1_.func_152767_b("gl_caps[EXT_multi_draw_arrays]", Boolean.valueOf(contextcapabilities.GL_EXT_multi_draw_arrays));
        p_70001_1_.func_152767_b("gl_caps[EXT_packed_depth_stencil]", Boolean.valueOf(contextcapabilities.GL_EXT_packed_depth_stencil));
        p_70001_1_.func_152767_b("gl_caps[EXT_paletted_texture]", Boolean.valueOf(contextcapabilities.GL_EXT_paletted_texture));
        p_70001_1_.func_152767_b("gl_caps[EXT_rescale_normal]", Boolean.valueOf(contextcapabilities.GL_EXT_rescale_normal));
        p_70001_1_.func_152767_b("gl_caps[EXT_separate_shader_objects]", Boolean.valueOf(contextcapabilities.GL_EXT_separate_shader_objects));
        p_70001_1_.func_152767_b("gl_caps[EXT_shader_image_load_store]", Boolean.valueOf(contextcapabilities.GL_EXT_shader_image_load_store));
        p_70001_1_.func_152767_b("gl_caps[EXT_shadow_funcs]", Boolean.valueOf(contextcapabilities.GL_EXT_shadow_funcs));
        p_70001_1_.func_152767_b("gl_caps[EXT_shared_texture_palette]", Boolean.valueOf(contextcapabilities.GL_EXT_shared_texture_palette));
        p_70001_1_.func_152767_b("gl_caps[EXT_stencil_clear_tag]", Boolean.valueOf(contextcapabilities.GL_EXT_stencil_clear_tag));
        p_70001_1_.func_152767_b("gl_caps[EXT_stencil_two_side]", Boolean.valueOf(contextcapabilities.GL_EXT_stencil_two_side));
        p_70001_1_.func_152767_b("gl_caps[EXT_stencil_wrap]", Boolean.valueOf(contextcapabilities.GL_EXT_stencil_wrap));
        p_70001_1_.func_152767_b("gl_caps[EXT_texture_3d]", Boolean.valueOf(contextcapabilities.GL_EXT_texture_3d));
        p_70001_1_.func_152767_b("gl_caps[EXT_texture_array]", Boolean.valueOf(contextcapabilities.GL_EXT_texture_array));
        p_70001_1_.func_152767_b("gl_caps[EXT_texture_buffer_object]", Boolean.valueOf(contextcapabilities.GL_EXT_texture_buffer_object));
        p_70001_1_.func_152767_b("gl_caps[EXT_texture_filter_anisotropic]", Boolean.valueOf(contextcapabilities.GL_EXT_texture_filter_anisotropic));
        p_70001_1_.func_152767_b("gl_caps[EXT_texture_integer]", Boolean.valueOf(contextcapabilities.GL_EXT_texture_integer));
        p_70001_1_.func_152767_b("gl_caps[EXT_texture_lod_bias]", Boolean.valueOf(contextcapabilities.GL_EXT_texture_lod_bias));
        p_70001_1_.func_152767_b("gl_caps[EXT_texture_sRGB]", Boolean.valueOf(contextcapabilities.GL_EXT_texture_sRGB));
        p_70001_1_.func_152767_b("gl_caps[EXT_vertex_shader]", Boolean.valueOf(contextcapabilities.GL_EXT_vertex_shader));
        p_70001_1_.func_152767_b("gl_caps[EXT_vertex_weighting]", Boolean.valueOf(contextcapabilities.GL_EXT_vertex_weighting));
        p_70001_1_.func_152767_b("gl_caps[gl_max_vertex_uniforms]", Integer.valueOf(GL11.glGetInteger(GL20.GL_MAX_VERTEX_UNIFORM_COMPONENTS)));
        GL11.glGetError();
        p_70001_1_.func_152767_b("gl_caps[gl_max_fragment_uniforms]", Integer.valueOf(GL11.glGetInteger(GL20.GL_MAX_FRAGMENT_UNIFORM_COMPONENTS)));
        GL11.glGetError();
        p_70001_1_.func_152767_b("gl_caps[gl_max_vertex_attribs]", Integer.valueOf(GL11.glGetInteger(GL20.GL_MAX_VERTEX_ATTRIBS)));
        GL11.glGetError();
        p_70001_1_.func_152767_b("gl_caps[gl_max_vertex_texture_image_units]", Integer.valueOf(GL11.glGetInteger(GL20.GL_MAX_VERTEX_TEXTURE_IMAGE_UNITS)));
        GL11.glGetError();
        p_70001_1_.func_152767_b("gl_caps[gl_max_texture_image_units]", Integer.valueOf(GL11.glGetInteger(GL20.GL_MAX_TEXTURE_IMAGE_UNITS)));
        GL11.glGetError();
        p_70001_1_.func_152767_b("gl_caps[gl_max_texture_image_units]", Integer.valueOf(GL11.glGetInteger(35071)));
        GL11.glGetError();
        p_70001_1_.func_152767_b("gl_max_texture_size", Integer.valueOf(func_71369_N()));
    }

    public static int func_71369_N()
    {
        for (int i = 16384; i > 0; i >>= 1)
        {
            GL11.glTexImage2D(GL11.GL_PROXY_TEXTURE_2D, 0, GL11.GL_RGBA, i, i, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, (ByteBuffer)null);
            int j = GL11.glGetTexLevelParameteri(GL11.GL_PROXY_TEXTURE_2D, 0, GL11.GL_TEXTURE_WIDTH);

            if (j != 0)
            {
                return i;
            }
        }

        return -1;
    }

    public boolean func_70002_Q()
    {
        return this.gameSettings.field_74355_t;
    }

    public void func_71351_a(ServerData p_71351_1_)
    {
        this.field_71422_O = p_71351_1_;
    }

    public ServerData func_147104_D()
    {
        return this.field_71422_O;
    }

    public boolean func_71387_A()
    {
        return this.field_71455_al;
    }

    public boolean func_71356_B()
    {
        return this.field_71455_al && this.field_71437_Z != null;
    }

    public IntegratedServer func_71401_C()
    {
        return this.field_71437_Z;
    }

    public static void func_71363_D()
    {
        if (field_71432_P != null)
        {
            IntegratedServer integratedserver = field_71432_P.func_71401_C();

            if (integratedserver != null)
            {
                integratedserver.func_71260_j();
            }
        }
    }

    public PlayerUsageSnooper func_71378_E()
    {
        return this.field_71427_U;
    }

    public static long func_71386_F()
    {
        return Sys.getTime() * 1000L / Sys.getTimerResolution();
    }

    public boolean func_71372_G()
    {
        return this.field_71431_Q;
    }

    public Session func_110432_I()
    {
        return this.field_71449_j;
    }

    public Multimap func_152341_N()
    {
        return this.field_152356_J;
    }

    public Proxy func_110437_J()
    {
        return this.field_110453_aa;
    }

    public TextureManager func_110434_K()
    {
        return this.field_71446_o;
    }

    public IResourceManager func_110442_L()
    {
        return this.field_110451_am;
    }

    public ResourcePackRepository func_110438_M()
    {
        return this.field_110448_aq;
    }

    public LanguageManager func_135016_M()
    {
        return this.field_135017_as;
    }

    public TextureMap func_147117_R()
    {
        return this.field_147128_au;
    }

    public boolean func_147111_S()
    {
        return this.field_147129_ai;
    }

    public boolean func_147113_T()
    {
        return this.field_71445_n;
    }

    public SoundHandler func_147118_V()
    {
        return this.field_147127_av;
    }

    public MusicTicker.MusicType func_147109_W()
    {
        return this.field_71462_r instanceof GuiWinGame ? MusicTicker.MusicType.CREDITS : (this.field_71439_g != null ? (this.field_71439_g.world.field_73011_w instanceof WorldProviderHell ? MusicTicker.MusicType.NETHER : (this.field_71439_g.world.field_73011_w instanceof WorldProviderEnd ? (BossStatus.field_82827_c != null && BossStatus.field_82826_b > 0 ? MusicTicker.MusicType.END_BOSS : MusicTicker.MusicType.END) : (this.field_71439_g.capabilities.instabuild && this.field_71439_g.capabilities.mayfly ? MusicTicker.MusicType.CREATIVE : MusicTicker.MusicType.GAME))) : MusicTicker.MusicType.MENU);
    }

    public IStream func_152346_Z()
    {
        return this.field_152353_at;
    }

    public void func_152348_aa()
    {
        int i = Keyboard.getEventKey();

        if (i != 0 && !Keyboard.isRepeatEvent())
        {
            if (!(this.field_71462_r instanceof GuiControls) || ((GuiControls)this.field_71462_r).field_152177_g <= func_71386_F() - 20L)
            {
                if (Keyboard.getEventKeyState())
                {
                    if (i == this.gameSettings.field_152396_an.getEventKey())
                    {
                        if (this.func_152346_Z().func_152934_n())
                        {
                            this.func_152346_Z().func_152914_u();
                        }
                        else if (this.func_152346_Z().func_152924_m())
                        {
                            this.func_147108_a(new GuiYesNo(new GuiYesNoCallback()
                            {
                                private static final String __OBFID = "CL_00001852";
                                public void func_73878_a(boolean p_73878_1_, int p_73878_2_)
                                {
                                    if (p_73878_1_)
                                    {
                                        Minecraft.this.func_152346_Z().func_152930_t();
                                    }

                                    Minecraft.this.func_147108_a((GuiScreen)null);
                                }
                            }, I18n.func_135052_a("stream.confirm_start", new Object[0]), "", 0));
                        }
                        else if (this.func_152346_Z().func_152928_D() && this.func_152346_Z().func_152936_l())
                        {
                            if (this.field_71441_e != null)
                            {
                                this.field_71456_v.func_146158_b().func_146227_a(new ChatComponentText("Not ready to start streaming yet!"));
                            }
                        }
                        else
                        {
                            GuiStreamUnavailable.func_152321_a(this.field_71462_r);
                        }
                    }
                    else if (i == this.gameSettings.field_152397_ao.getEventKey())
                    {
                        if (this.func_152346_Z().func_152934_n())
                        {
                            if (this.func_152346_Z().func_152919_o())
                            {
                                this.func_152346_Z().func_152933_r();
                            }
                            else
                            {
                                this.func_152346_Z().func_152916_q();
                            }
                        }
                    }
                    else if (i == this.gameSettings.field_152398_ap.getEventKey())
                    {
                        if (this.func_152346_Z().func_152934_n())
                        {
                            this.func_152346_Z().func_152931_p();
                        }
                    }
                    else if (i == this.gameSettings.field_152399_aq.getEventKey())
                    {
                        this.field_152353_at.func_152910_a(true);
                    }
                    else if (i == this.gameSettings.field_152395_am.getEventKey())
                    {
                        this.func_71352_k();
                    }
                    else if (i == this.gameSettings.field_151447_Z.getEventKey())
                    {
                        this.field_71456_v.func_146158_b().func_146227_a(ScreenShotHelper.func_148260_a(this.field_71412_D, this.field_71443_c, this.field_71440_d, this.field_147124_at));
                    }
                }
                else if (i == this.gameSettings.field_152399_aq.getEventKey())
                {
                    this.field_152353_at.func_152910_a(false);
                }
            }
        }
    }

    public ListenableFuture func_152343_a(Callable p_152343_1_)
    {
        Validate.notNull(p_152343_1_);

        if (!this.func_152345_ab())
        {
            ListenableFutureTask listenablefuturetask = ListenableFutureTask.create(p_152343_1_);
            Queue queue = this.field_152351_aB;

            synchronized (this.field_152351_aB)
            {
                this.field_152351_aB.add(listenablefuturetask);
                return listenablefuturetask;
            }
        }
        else
        {
            try
            {
                return Futures.immediateFuture(p_152343_1_.call());
            }
            catch (Exception exception)
            {
                return Futures.immediateFailedCheckedFuture(exception);
            }
        }
    }

    public ListenableFuture func_152344_a(Runnable p_152344_1_)
    {
        Validate.notNull(p_152344_1_);
        return this.func_152343_a(Executors.callable(p_152344_1_));
    }

    public boolean func_152345_ab()
    {
        return Thread.currentThread() == this.field_152352_aC;
    }

    public MinecraftSessionService func_152347_ac()
    {
        return this.field_152355_az;
    }

    public SkinManager func_152342_ad()
    {
        return this.field_152350_aA;
    }

    @SideOnly(Side.CLIENT)

    static final class SwitchMovingObjectType
        {
            static final int[] field_152390_a = new int[MovingObjectPosition.MovingObjectType.values().length];
            private static final String __OBFID = "CL_00000638";

            static
            {
                try
                {
                    field_152390_a[MovingObjectPosition.MovingObjectType.ENTITY.ordinal()] = 1;
                }
                catch (NoSuchFieldError var2)
                {
                    ;
                }

                try
                {
                    field_152390_a[MovingObjectPosition.MovingObjectType.BLOCK.ordinal()] = 2;
                }
                catch (NoSuchFieldError var1)
                {
                    ;
                }
            }
        }
}