package net.minecraft.client.renderer;

import com.google.gson.JsonSyntaxException;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.MapItemRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.particle.EntityRainFX;
import net.minecraft.client.particle.EntitySmokeFX;
import net.minecraft.client.renderer.culling.ClippingHelperImpl;
import net.minecraft.client.renderer.culling.Frustrum;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.client.shader.ShaderGroup;
import net.minecraft.client.shader.ShaderLinkHelper;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MouseFilter;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ReportedException;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.biome.BiomeGenBase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;
import org.lwjgl.util.glu.Project;

@SideOnly(Side.CLIENT)
public class EntityRenderer implements IResourceManagerReloadListener
{
    private static final Logger field_147710_q = LogManager.getLogger();
    private static final ResourceLocation field_110924_q = new ResourceLocation("textures/environment/rain.png");
    private static final ResourceLocation field_110923_r = new ResourceLocation("textures/environment/snow.png");
    public static boolean field_78517_a;
    public static int field_78515_b;
    private Minecraft field_78531_r;
    private float field_78530_s;
    public final ItemRenderer field_78516_c;
    private final MapItemRenderer field_147709_v;
    private int field_78529_t;
    private Entity field_78528_u;
    private MouseFilter field_78527_v = new MouseFilter();
    private MouseFilter field_78526_w = new MouseFilter();
    private MouseFilter field_78541_x = new MouseFilter();
    private MouseFilter field_78540_y = new MouseFilter();
    private MouseFilter field_78538_z = new MouseFilter();
    private MouseFilter field_78489_A = new MouseFilter();
    private float field_78490_B = 4.0F;
    private float field_78491_C = 4.0F;
    private float field_78485_D;
    private float field_78486_E;
    private float field_78487_F;
    private float field_78488_G;
    private float field_78496_H;
    private float field_78497_I;
    private float field_78498_J;
    private float field_78499_K;
    private float field_78492_L;
    private float field_78493_M;
    private float field_78494_N;
    private float field_78495_O;
    private float field_78505_P;
    private final DynamicTexture field_78513_d;
    private final int[] field_78504_Q;
    private final ResourceLocation field_110922_T;
    private float field_78507_R;
    private float field_78506_S;
    private float field_78501_T;
    private float field_82831_U;
    private float field_82832_V;
    private boolean field_78500_U;
    private final IResourceManager field_147711_ac;
    public ShaderGroup field_147707_d;
    private static final ResourceLocation[] field_147712_ad = new ResourceLocation[] {new ResourceLocation("shaders/post/notch.json"), new ResourceLocation("shaders/post/fxaa.json"), new ResourceLocation("shaders/post/art.json"), new ResourceLocation("shaders/post/bumpy.json"), new ResourceLocation("shaders/post/blobs2.json"), new ResourceLocation("shaders/post/pencil.json"), new ResourceLocation("shaders/post/color_convolve.json"), new ResourceLocation("shaders/post/deconverge.json"), new ResourceLocation("shaders/post/flip.json"), new ResourceLocation("shaders/post/invert.json"), new ResourceLocation("shaders/post/ntsc.json"), new ResourceLocation("shaders/post/outline.json"), new ResourceLocation("shaders/post/phosphor.json"), new ResourceLocation("shaders/post/scan_pincushion.json"), new ResourceLocation("shaders/post/sobel.json"), new ResourceLocation("shaders/post/bits.json"), new ResourceLocation("shaders/post/desaturate.json"), new ResourceLocation("shaders/post/green.json"), new ResourceLocation("shaders/post/blur.json"), new ResourceLocation("shaders/post/wobble.json"), new ResourceLocation("shaders/post/blobs.json"), new ResourceLocation("shaders/post/antialias.json")};
    public static final int field_147708_e = field_147712_ad.length;
    private int field_147713_ae;
    private double field_78503_V;
    private double field_78502_W;
    private double field_78509_X;
    private long field_78508_Y;
    private long field_78510_Z;
    private boolean field_78536_aa;
    float field_78514_e;
    float field_78511_f;
    float field_78512_g;
    float field_78524_h;
    private Random field_78537_ab;
    private int field_78534_ac;
    float[] field_78525_i;
    float[] field_78522_j;
    FloatBuffer field_78521_m;
    float field_78518_n;
    float field_78519_o;
    float field_78533_p;
    private float field_78535_ad;
    private float field_78539_ae;
    public int field_78532_q;
    private static final String __OBFID = "CL_00000947";

    public EntityRenderer(Minecraft p_i45076_1_, IResourceManager p_i45076_2_)
    {
        this.field_147713_ae = field_147708_e;
        this.field_78503_V = 1.0D;
        this.field_78508_Y = Minecraft.func_71386_F();
        this.field_78537_ab = new Random();
        this.field_78521_m = GLAllocation.func_74529_h(16);
        this.field_78531_r = p_i45076_1_;
        this.field_147711_ac = p_i45076_2_;
        this.field_147709_v = new MapItemRenderer(p_i45076_1_.func_110434_K());
        this.field_78516_c = new ItemRenderer(p_i45076_1_);
        this.field_78513_d = new DynamicTexture(16, 16);
        this.field_110922_T = p_i45076_1_.func_110434_K().func_110578_a("lightMap", this.field_78513_d);
        this.field_78504_Q = this.field_78513_d.func_110565_c();
        this.field_147707_d = null;
    }

    public boolean func_147702_a()
    {
        return OpenGlHelper.field_148824_g && this.field_147707_d != null;
    }

    public void func_147703_b()
    {
        if (this.field_147707_d != null)
        {
            this.field_147707_d.func_148021_a();
        }

        this.field_147707_d = null;
        this.field_147713_ae = field_147708_e;
    }

    public void func_147705_c()
    {
        if (OpenGlHelper.field_148824_g)
        {
            if (this.field_147707_d != null)
            {
                this.field_147707_d.func_148021_a();
            }

            this.field_147713_ae = (this.field_147713_ae + 1) % (field_147712_ad.length + 1);

            if (this.field_147713_ae != field_147708_e)
            {
                try
                {
                    field_147710_q.info("Selecting effect " + field_147712_ad[this.field_147713_ae]);
                    this.field_147707_d = new ShaderGroup(this.field_78531_r.func_110434_K(), this.field_147711_ac, this.field_78531_r.func_147110_a(), field_147712_ad[this.field_147713_ae]);
                    this.field_147707_d.func_148026_a(this.field_78531_r.field_71443_c, this.field_78531_r.field_71440_d);
                }
                catch (IOException ioexception)
                {
                    field_147710_q.warn("Failed to load shader: " + field_147712_ad[this.field_147713_ae], ioexception);
                    this.field_147713_ae = field_147708_e;
                }
                catch (JsonSyntaxException jsonsyntaxexception)
                {
                    field_147710_q.warn("Failed to load shader: " + field_147712_ad[this.field_147713_ae], jsonsyntaxexception);
                    this.field_147713_ae = field_147708_e;
                }
            }
            else
            {
                this.field_147707_d = null;
                field_147710_q.info("No effect selected");
            }
        }
    }

    public void func_110549_a(IResourceManager p_110549_1_)
    {
        if (this.field_147707_d != null)
        {
            this.field_147707_d.func_148021_a();
        }

        if (this.field_147713_ae != field_147708_e)
        {
            try
            {
                this.field_147707_d = new ShaderGroup(this.field_78531_r.func_110434_K(), p_110549_1_, this.field_78531_r.func_147110_a(), field_147712_ad[this.field_147713_ae]);
                this.field_147707_d.func_148026_a(this.field_78531_r.field_71443_c, this.field_78531_r.field_71440_d);
            }
            catch (IOException ioexception)
            {
                field_147710_q.warn("Failed to load shader: " + field_147712_ad[this.field_147713_ae], ioexception);
                this.field_147713_ae = field_147708_e;
            }
        }
    }

    public void func_78464_a()
    {
        if (OpenGlHelper.field_148824_g && ShaderLinkHelper.func_148074_b() == null)
        {
            ShaderLinkHelper.func_148076_a();
        }

        this.func_78477_e();
        this.func_78470_f();
        this.field_78535_ad = this.field_78539_ae;
        this.field_78491_C = this.field_78490_B;
        this.field_78486_E = this.field_78485_D;
        this.field_78488_G = this.field_78487_F;
        this.field_78494_N = this.field_78493_M;
        this.field_78505_P = this.field_78495_O;
        float f;
        float f1;

        if (this.field_78531_r.gameSettings.field_74326_T)
        {
            f = this.field_78531_r.gameSettings.field_74341_c * 0.6F + 0.2F;
            f1 = f * f * f * 8.0F;
            this.field_78498_J = this.field_78527_v.func_76333_a(this.field_78496_H, 0.05F * f1);
            this.field_78499_K = this.field_78526_w.func_76333_a(this.field_78497_I, 0.05F * f1);
            this.field_78492_L = 0.0F;
            this.field_78496_H = 0.0F;
            this.field_78497_I = 0.0F;
        }

        if (this.field_78531_r.field_71451_h == null)
        {
            this.field_78531_r.field_71451_h = this.field_78531_r.field_71439_g;
        }

        f = this.field_78531_r.field_71441_e.func_72801_o(MathHelper.func_76128_c(this.field_78531_r.field_71451_h.field_70165_t), MathHelper.func_76128_c(this.field_78531_r.field_71451_h.field_70163_u), MathHelper.func_76128_c(this.field_78531_r.field_71451_h.field_70161_v));
        f1 = (float)this.field_78531_r.gameSettings.field_151451_c / 16.0F;
        float f2 = f * (1.0F - f1) + f1;
        this.field_78539_ae += (f2 - this.field_78539_ae) * 0.1F;
        ++this.field_78529_t;
        this.field_78516_c.func_78441_a();
        this.func_78484_h();
        this.field_82832_V = this.field_82831_U;

        if (BossStatus.field_82825_d)
        {
            this.field_82831_U += 0.05F;

            if (this.field_82831_U > 1.0F)
            {
                this.field_82831_U = 1.0F;
            }

            BossStatus.field_82825_d = false;
        }
        else if (this.field_82831_U > 0.0F)
        {
            this.field_82831_U -= 0.0125F;
        }
    }

    public ShaderGroup func_147706_e()
    {
        return this.field_147707_d;
    }

    public void func_147704_a(int p_147704_1_, int p_147704_2_)
    {
        if (OpenGlHelper.field_148824_g)
        {
            if (this.field_147707_d != null)
            {
                this.field_147707_d.func_148026_a(p_147704_1_, p_147704_2_);
            }
        }
    }

    public void func_78473_a(float p_78473_1_)
    {
        if (this.field_78531_r.field_71451_h != null)
        {
            if (this.field_78531_r.field_71441_e != null)
            {
                this.field_78531_r.field_147125_j = null;
                double d0 = (double)this.field_78531_r.field_71442_b.func_78757_d();
                this.field_78531_r.field_71476_x = this.field_78531_r.field_71451_h.func_70614_a(d0, p_78473_1_);
                double d1 = d0;
                Vec3 vec3 = this.field_78531_r.field_71451_h.func_70666_h(p_78473_1_);

                if (this.field_78531_r.field_71442_b.func_78749_i())
                {
                    d0 = 6.0D;
                    d1 = 6.0D;
                }
                else
                {
                    if (d0 > 3.0D)
                    {
                        d1 = 3.0D;
                    }

                    d0 = d1;
                }

                if (this.field_78531_r.field_71476_x != null)
                {
                    d1 = this.field_78531_r.field_71476_x.field_72307_f.func_72438_d(vec3);
                }

                Vec3 vec31 = this.field_78531_r.field_71451_h.func_70676_i(p_78473_1_);
                Vec3 vec32 = vec3.func_72441_c(vec31.field_72450_a * d0, vec31.field_72448_b * d0, vec31.field_72449_c * d0);
                this.field_78528_u = null;
                Vec3 vec33 = null;
                float f1 = 1.0F;
                List list = this.field_78531_r.field_71441_e.func_72839_b(this.field_78531_r.field_71451_h, this.field_78531_r.field_71451_h.field_70121_D.func_72321_a(vec31.field_72450_a * d0, vec31.field_72448_b * d0, vec31.field_72449_c * d0).func_72314_b((double)f1, (double)f1, (double)f1));
                double d2 = d1;

                for (int i = 0; i < list.size(); ++i)
                {
                    Entity entity = (Entity)list.get(i);

                    if (entity.func_70067_L())
                    {
                        float f2 = entity.func_70111_Y();
                        AxisAlignedBB axisalignedbb = entity.field_70121_D.func_72314_b((double)f2, (double)f2, (double)f2);
                        MovingObjectPosition movingobjectposition = axisalignedbb.func_72327_a(vec3, vec32);

                        if (axisalignedbb.func_72318_a(vec3))
                        {
                            if (0.0D < d2 || d2 == 0.0D)
                            {
                                this.field_78528_u = entity;
                                vec33 = movingobjectposition == null ? vec3 : movingobjectposition.field_72307_f;
                                d2 = 0.0D;
                            }
                        }
                        else if (movingobjectposition != null)
                        {
                            double d3 = vec3.func_72438_d(movingobjectposition.field_72307_f);

                            if (d3 < d2 || d2 == 0.0D)
                            {
                                if (entity == this.field_78531_r.field_71451_h.field_70154_o)
                                {
                                    if (d2 == 0.0D)
                                    {
                                        this.field_78528_u = entity;
                                        vec33 = movingobjectposition.field_72307_f;
                                    }
                                }
                                else
                                {
                                    this.field_78528_u = entity;
                                    vec33 = movingobjectposition.field_72307_f;
                                    d2 = d3;
                                }
                            }
                        }
                    }
                }

                if (this.field_78528_u != null && (d2 < d1 || this.field_78531_r.field_71476_x == null))
                {
                    this.field_78531_r.field_71476_x = new MovingObjectPosition(this.field_78528_u, vec33);

                    if (this.field_78528_u instanceof EntityLivingBase || this.field_78528_u instanceof EntityItemFrame)
                    {
                        this.field_78531_r.field_147125_j = this.field_78528_u;
                    }
                }
            }
        }
    }

    private void func_78477_e()
    {
        EntityPlayerSP entityplayersp = (EntityPlayerSP)this.field_78531_r.field_71451_h;
        this.field_78501_T = entityplayersp.func_71151_f();
        this.field_78506_S = this.field_78507_R;
        this.field_78507_R += (this.field_78501_T - this.field_78507_R) * 0.5F;

        if (this.field_78507_R > 1.5F)
        {
            this.field_78507_R = 1.5F;
        }

        if (this.field_78507_R < 0.1F)
        {
            this.field_78507_R = 0.1F;
        }
    }

    private float func_78481_a(float p_78481_1_, boolean p_78481_2_)
    {
        if (this.field_78532_q > 0)
        {
            return 90.0F;
        }
        else
        {
            EntityPlayer entityplayer = (EntityPlayer)this.field_78531_r.field_71451_h;
            float f1 = 70.0F;

            if (p_78481_2_)
            {
                f1 = this.field_78531_r.gameSettings.field_74334_X;
                f1 *= this.field_78506_S + (this.field_78507_R - this.field_78506_S) * p_78481_1_;
            }

            if (entityplayer.func_110143_aJ() <= 0.0F)
            {
                float f2 = (float)entityplayer.field_70725_aQ + p_78481_1_;
                f1 /= (1.0F - 500.0F / (f2 + 500.0F)) * 2.0F + 1.0F;
            }

            Block block = ActiveRenderInfo.func_151460_a(this.field_78531_r.field_71441_e, entityplayer, p_78481_1_);

            if (block.func_149688_o() == Material.field_151586_h)
            {
                f1 = f1 * 60.0F / 70.0F;
            }

            return f1 + this.field_78494_N + (this.field_78493_M - this.field_78494_N) * p_78481_1_;
        }
    }

    private void func_78482_e(float p_78482_1_)
    {
        EntityLivingBase entitylivingbase = this.field_78531_r.field_71451_h;
        float f1 = (float)entitylivingbase.field_70737_aN - p_78482_1_;
        float f2;

        if (entitylivingbase.func_110143_aJ() <= 0.0F)
        {
            f2 = (float)entitylivingbase.field_70725_aQ + p_78482_1_;
            GL11.glRotatef(40.0F - 8000.0F / (f2 + 200.0F), 0.0F, 0.0F, 1.0F);
        }

        if (f1 >= 0.0F)
        {
            f1 /= (float)entitylivingbase.field_70738_aO;
            f1 = MathHelper.func_76126_a(f1 * f1 * f1 * f1 * (float)Math.PI);
            f2 = entitylivingbase.field_70739_aP;
            GL11.glRotatef(-f2, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(-f1 * 14.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(f2, 0.0F, 1.0F, 0.0F);
        }
    }

    private void func_78475_f(float p_78475_1_)
    {
        if (this.field_78531_r.field_71451_h instanceof EntityPlayer)
        {
            EntityPlayer entityplayer = (EntityPlayer)this.field_78531_r.field_71451_h;
            float f1 = entityplayer.field_70140_Q - entityplayer.field_70141_P;
            float f2 = -(entityplayer.field_70140_Q + f1 * p_78475_1_);
            float f3 = entityplayer.field_71107_bF + (entityplayer.field_71109_bG - entityplayer.field_71107_bF) * p_78475_1_;
            float f4 = entityplayer.field_70727_aS + (entityplayer.field_70726_aT - entityplayer.field_70727_aS) * p_78475_1_;
            GL11.glTranslatef(MathHelper.func_76126_a(f2 * (float)Math.PI) * f3 * 0.5F, -Math.abs(MathHelper.func_76134_b(f2 * (float)Math.PI) * f3), 0.0F);
            GL11.glRotatef(MathHelper.func_76126_a(f2 * (float)Math.PI) * f3 * 3.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(Math.abs(MathHelper.func_76134_b(f2 * (float)Math.PI - 0.2F) * f3) * 5.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(f4, 1.0F, 0.0F, 0.0F);
        }
    }

    private void func_78467_g(float p_78467_1_)
    {
        EntityLivingBase entitylivingbase = this.field_78531_r.field_71451_h;
        float f1 = entitylivingbase.field_70129_M - 1.62F;
        double d0 = entitylivingbase.field_70169_q + (entitylivingbase.field_70165_t - entitylivingbase.field_70169_q) * (double)p_78467_1_;
        double d1 = entitylivingbase.field_70167_r + (entitylivingbase.field_70163_u - entitylivingbase.field_70167_r) * (double)p_78467_1_ - (double)f1;
        double d2 = entitylivingbase.field_70166_s + (entitylivingbase.field_70161_v - entitylivingbase.field_70166_s) * (double)p_78467_1_;
        GL11.glRotatef(this.field_78505_P + (this.field_78495_O - this.field_78505_P) * p_78467_1_, 0.0F, 0.0F, 1.0F);

        if (entitylivingbase.func_70608_bn())
        {
            f1 = (float)((double)f1 + 1.0D);
            GL11.glTranslatef(0.0F, 0.3F, 0.0F);

            if (!this.field_78531_r.gameSettings.field_74325_U)
            {
                Block block = this.field_78531_r.field_71441_e.func_147439_a(MathHelper.func_76128_c(entitylivingbase.field_70165_t), MathHelper.func_76128_c(entitylivingbase.field_70163_u), MathHelper.func_76128_c(entitylivingbase.field_70161_v));

                if (block == Blocks.BED)
                {
                    int i = this.field_78531_r.field_71441_e.func_72805_g(MathHelper.func_76128_c(entitylivingbase.field_70165_t), MathHelper.func_76128_c(entitylivingbase.field_70163_u), MathHelper.func_76128_c(entitylivingbase.field_70161_v));
                    int j = i & 3;
                    GL11.glRotatef((float)(j * 90), 0.0F, 1.0F, 0.0F);
                }

                GL11.glRotatef(entitylivingbase.field_70126_B + (entitylivingbase.field_70177_z - entitylivingbase.field_70126_B) * p_78467_1_ + 180.0F, 0.0F, -1.0F, 0.0F);
                GL11.glRotatef(entitylivingbase.field_70127_C + (entitylivingbase.field_70125_A - entitylivingbase.field_70127_C) * p_78467_1_, -1.0F, 0.0F, 0.0F);
            }
        }
        else if (this.field_78531_r.gameSettings.field_74320_O > 0)
        {
            double d7 = (double)(this.field_78491_C + (this.field_78490_B - this.field_78491_C) * p_78467_1_);
            float f2;
            float f6;

            if (this.field_78531_r.gameSettings.field_74325_U)
            {
                f6 = this.field_78486_E + (this.field_78485_D - this.field_78486_E) * p_78467_1_;
                f2 = this.field_78488_G + (this.field_78487_F - this.field_78488_G) * p_78467_1_;
                GL11.glTranslatef(0.0F, 0.0F, (float)(-d7));
                GL11.glRotatef(f2, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(f6, 0.0F, 1.0F, 0.0F);
            }
            else
            {
                f6 = entitylivingbase.field_70177_z;
                f2 = entitylivingbase.field_70125_A;

                if (this.field_78531_r.gameSettings.field_74320_O == 2)
                {
                    f2 += 180.0F;
                }

                double d3 = (double)(-MathHelper.func_76126_a(f6 / 180.0F * (float)Math.PI) * MathHelper.func_76134_b(f2 / 180.0F * (float)Math.PI)) * d7;
                double d4 = (double)(MathHelper.func_76134_b(f6 / 180.0F * (float)Math.PI) * MathHelper.func_76134_b(f2 / 180.0F * (float)Math.PI)) * d7;
                double d5 = (double)(-MathHelper.func_76126_a(f2 / 180.0F * (float)Math.PI)) * d7;

                for (int k = 0; k < 8; ++k)
                {
                    float f3 = (float)((k & 1) * 2 - 1);
                    float f4 = (float)((k >> 1 & 1) * 2 - 1);
                    float f5 = (float)((k >> 2 & 1) * 2 - 1);
                    f3 *= 0.1F;
                    f4 *= 0.1F;
                    f5 *= 0.1F;
                    MovingObjectPosition movingobjectposition = this.field_78531_r.field_71441_e.func_72933_a(Vec3.func_72443_a(d0 + (double)f3, d1 + (double)f4, d2 + (double)f5), Vec3.func_72443_a(d0 - d3 + (double)f3 + (double)f5, d1 - d5 + (double)f4, d2 - d4 + (double)f5));

                    if (movingobjectposition != null)
                    {
                        double d6 = movingobjectposition.field_72307_f.func_72438_d(Vec3.func_72443_a(d0, d1, d2));

                        if (d6 < d7)
                        {
                            d7 = d6;
                        }
                    }
                }

                if (this.field_78531_r.gameSettings.field_74320_O == 2)
                {
                    GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
                }

                GL11.glRotatef(entitylivingbase.field_70125_A - f2, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(entitylivingbase.field_70177_z - f6, 0.0F, 1.0F, 0.0F);
                GL11.glTranslatef(0.0F, 0.0F, (float)(-d7));
                GL11.glRotatef(f6 - entitylivingbase.field_70177_z, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(f2 - entitylivingbase.field_70125_A, 1.0F, 0.0F, 0.0F);
            }
        }
        else
        {
            GL11.glTranslatef(0.0F, 0.0F, -0.1F);
        }

        if (!this.field_78531_r.gameSettings.field_74325_U)
        {
            GL11.glRotatef(entitylivingbase.field_70127_C + (entitylivingbase.field_70125_A - entitylivingbase.field_70127_C) * p_78467_1_, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(entitylivingbase.field_70126_B + (entitylivingbase.field_70177_z - entitylivingbase.field_70126_B) * p_78467_1_ + 180.0F, 0.0F, 1.0F, 0.0F);
        }

        GL11.glTranslatef(0.0F, f1, 0.0F);
        d0 = entitylivingbase.field_70169_q + (entitylivingbase.field_70165_t - entitylivingbase.field_70169_q) * (double)p_78467_1_;
        d1 = entitylivingbase.field_70167_r + (entitylivingbase.field_70163_u - entitylivingbase.field_70167_r) * (double)p_78467_1_ - (double)f1;
        d2 = entitylivingbase.field_70166_s + (entitylivingbase.field_70161_v - entitylivingbase.field_70166_s) * (double)p_78467_1_;
        this.field_78500_U = this.field_78531_r.field_71438_f.func_72721_a(d0, d1, d2, p_78467_1_);
    }

    private void func_78479_a(float p_78479_1_, int p_78479_2_)
    {
        this.field_78530_s = (float)(this.field_78531_r.gameSettings.field_151451_c * 16);
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        float f1 = 0.07F;

        if (this.field_78531_r.gameSettings.field_74337_g)
        {
            GL11.glTranslatef((float)(-(p_78479_2_ * 2 - 1)) * f1, 0.0F, 0.0F);
        }

        if (this.field_78503_V != 1.0D)
        {
            GL11.glTranslatef((float)this.field_78502_W, (float)(-this.field_78509_X), 0.0F);
            GL11.glScaled(this.field_78503_V, this.field_78503_V, 1.0D);
        }

        Project.gluPerspective(this.func_78481_a(p_78479_1_, true), (float)this.field_78531_r.field_71443_c / (float)this.field_78531_r.field_71440_d, 0.05F, this.field_78530_s * 2.0F);
        float f2;

        if (this.field_78531_r.field_71442_b.func_78747_a())
        {
            f2 = 0.6666667F;
            GL11.glScalef(1.0F, f2, 1.0F);
        }

        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glLoadIdentity();

        if (this.field_78531_r.gameSettings.field_74337_g)
        {
            GL11.glTranslatef((float)(p_78479_2_ * 2 - 1) * 0.1F, 0.0F, 0.0F);
        }

        this.func_78482_e(p_78479_1_);

        if (this.field_78531_r.gameSettings.field_74336_f)
        {
            this.func_78475_f(p_78479_1_);
        }

        f2 = this.field_78531_r.field_71439_g.field_71080_cy + (this.field_78531_r.field_71439_g.field_71086_bY - this.field_78531_r.field_71439_g.field_71080_cy) * p_78479_1_;

        if (f2 > 0.0F)
        {
            byte b0 = 20;

            if (this.field_78531_r.field_71439_g.func_70644_a(Potion.field_76431_k))
            {
                b0 = 7;
            }

            float f3 = 5.0F / (f2 * f2 + 5.0F) - f2 * 0.04F;
            f3 *= f3;
            GL11.glRotatef(((float)this.field_78529_t + p_78479_1_) * (float)b0, 0.0F, 1.0F, 1.0F);
            GL11.glScalef(1.0F / f3, 1.0F, 1.0F);
            GL11.glRotatef(-((float)this.field_78529_t + p_78479_1_) * (float)b0, 0.0F, 1.0F, 1.0F);
        }

        this.func_78467_g(p_78479_1_);

        if (this.field_78532_q > 0)
        {
            int j = this.field_78532_q - 1;

            if (j == 1)
            {
                GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
            }

            if (j == 2)
            {
                GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
            }

            if (j == 3)
            {
                GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
            }

            if (j == 4)
            {
                GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
            }

            if (j == 5)
            {
                GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
            }
        }
    }

    private void func_78476_b(float p_78476_1_, int p_78476_2_)
    {
        if (this.field_78532_q <= 0)
        {
            GL11.glMatrixMode(GL11.GL_PROJECTION);
            GL11.glLoadIdentity();
            float f1 = 0.07F;

            if (this.field_78531_r.gameSettings.field_74337_g)
            {
                GL11.glTranslatef((float)(-(p_78476_2_ * 2 - 1)) * f1, 0.0F, 0.0F);
            }

            if (this.field_78503_V != 1.0D)
            {
                GL11.glTranslatef((float)this.field_78502_W, (float)(-this.field_78509_X), 0.0F);
                GL11.glScaled(this.field_78503_V, this.field_78503_V, 1.0D);
            }

            Project.gluPerspective(this.func_78481_a(p_78476_1_, false), (float)this.field_78531_r.field_71443_c / (float)this.field_78531_r.field_71440_d, 0.05F, this.field_78530_s * 2.0F);

            if (this.field_78531_r.field_71442_b.func_78747_a())
            {
                float f2 = 0.6666667F;
                GL11.glScalef(1.0F, f2, 1.0F);
            }

            GL11.glMatrixMode(GL11.GL_MODELVIEW);
            GL11.glLoadIdentity();

            if (this.field_78531_r.gameSettings.field_74337_g)
            {
                GL11.glTranslatef((float)(p_78476_2_ * 2 - 1) * 0.1F, 0.0F, 0.0F);
            }

            GL11.glPushMatrix();
            this.func_78482_e(p_78476_1_);

            if (this.field_78531_r.gameSettings.field_74336_f)
            {
                this.func_78475_f(p_78476_1_);
            }

            if (this.field_78531_r.gameSettings.field_74320_O == 0 && !this.field_78531_r.field_71451_h.func_70608_bn() && !this.field_78531_r.gameSettings.field_74319_N && !this.field_78531_r.field_71442_b.func_78747_a())
            {
                this.func_78463_b((double)p_78476_1_);
                this.field_78516_c.func_78440_a(p_78476_1_);
                this.func_78483_a((double)p_78476_1_);
            }

            GL11.glPopMatrix();

            if (this.field_78531_r.gameSettings.field_74320_O == 0 && !this.field_78531_r.field_71451_h.func_70608_bn())
            {
                this.field_78516_c.func_78447_b(p_78476_1_);
                this.func_78482_e(p_78476_1_);
            }

            if (this.field_78531_r.gameSettings.field_74336_f)
            {
                this.func_78475_f(p_78476_1_);
            }
        }
    }

    public void func_78483_a(double p_78483_1_)
    {
        OpenGlHelper.func_77473_a(OpenGlHelper.field_77476_b);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        OpenGlHelper.func_77473_a(OpenGlHelper.field_77478_a);
    }

    public void func_78463_b(double p_78463_1_)
    {
        OpenGlHelper.func_77473_a(OpenGlHelper.field_77476_b);
        GL11.glMatrixMode(GL11.GL_TEXTURE);
        GL11.glLoadIdentity();
        float f = 0.00390625F;
        GL11.glScalef(f, f, f);
        GL11.glTranslatef(8.0F, 8.0F, 8.0F);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        this.field_78531_r.func_110434_K().func_110577_a(this.field_110922_T);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_CLAMP);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_CLAMP);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        OpenGlHelper.func_77473_a(OpenGlHelper.field_77478_a);
    }

    private void func_78470_f()
    {
        this.field_78511_f = (float)((double)this.field_78511_f + (Math.random() - Math.random()) * Math.random() * Math.random());
        this.field_78524_h = (float)((double)this.field_78524_h + (Math.random() - Math.random()) * Math.random() * Math.random());
        this.field_78511_f = (float)((double)this.field_78511_f * 0.9D);
        this.field_78524_h = (float)((double)this.field_78524_h * 0.9D);
        this.field_78514_e += (this.field_78511_f - this.field_78514_e) * 1.0F;
        this.field_78512_g += (this.field_78524_h - this.field_78512_g) * 1.0F;
        this.field_78536_aa = true;
    }

    private void func_78472_g(float p_78472_1_)
    {
        WorldClient worldclient = this.field_78531_r.field_71441_e;

        if (worldclient != null)
        {
            for (int i = 0; i < 256; ++i)
            {
                float f1 = worldclient.func_72971_b(1.0F) * 0.95F + 0.05F;
                float f2 = worldclient.field_73011_w.field_76573_f[i / 16] * f1;
                float f3 = worldclient.field_73011_w.field_76573_f[i % 16] * (this.field_78514_e * 0.1F + 1.5F);

                if (worldclient.field_73016_r > 0)
                {
                    f2 = worldclient.field_73011_w.field_76573_f[i / 16];
                }

                float f4 = f2 * (worldclient.func_72971_b(1.0F) * 0.65F + 0.35F);
                float f5 = f2 * (worldclient.func_72971_b(1.0F) * 0.65F + 0.35F);
                float f6 = f3 * ((f3 * 0.6F + 0.4F) * 0.6F + 0.4F);
                float f7 = f3 * (f3 * f3 * 0.6F + 0.4F);
                float f8 = f4 + f3;
                float f9 = f5 + f6;
                float f10 = f2 + f7;
                f8 = f8 * 0.96F + 0.03F;
                f9 = f9 * 0.96F + 0.03F;
                f10 = f10 * 0.96F + 0.03F;
                float f11;

                if (this.field_82831_U > 0.0F)
                {
                    f11 = this.field_82832_V + (this.field_82831_U - this.field_82832_V) * p_78472_1_;
                    f8 = f8 * (1.0F - f11) + f8 * 0.7F * f11;
                    f9 = f9 * (1.0F - f11) + f9 * 0.6F * f11;
                    f10 = f10 * (1.0F - f11) + f10 * 0.6F * f11;
                }

                if (worldclient.field_73011_w.field_76574_g == 1)
                {
                    f8 = 0.22F + f3 * 0.75F;
                    f9 = 0.28F + f6 * 0.75F;
                    f10 = 0.25F + f7 * 0.75F;
                }

                float f12;

                if (this.field_78531_r.field_71439_g.func_70644_a(Potion.field_76439_r))
                {
                    f11 = this.func_82830_a(this.field_78531_r.field_71439_g, p_78472_1_);
                    f12 = 1.0F / f8;

                    if (f12 > 1.0F / f9)
                    {
                        f12 = 1.0F / f9;
                    }

                    if (f12 > 1.0F / f10)
                    {
                        f12 = 1.0F / f10;
                    }

                    f8 = f8 * (1.0F - f11) + f8 * f12 * f11;
                    f9 = f9 * (1.0F - f11) + f9 * f12 * f11;
                    f10 = f10 * (1.0F - f11) + f10 * f12 * f11;
                }

                if (f8 > 1.0F)
                {
                    f8 = 1.0F;
                }

                if (f9 > 1.0F)
                {
                    f9 = 1.0F;
                }

                if (f10 > 1.0F)
                {
                    f10 = 1.0F;
                }

                f11 = this.field_78531_r.gameSettings.field_74333_Y;
                f12 = 1.0F - f8;
                float f13 = 1.0F - f9;
                float f14 = 1.0F - f10;
                f12 = 1.0F - f12 * f12 * f12 * f12;
                f13 = 1.0F - f13 * f13 * f13 * f13;
                f14 = 1.0F - f14 * f14 * f14 * f14;
                f8 = f8 * (1.0F - f11) + f12 * f11;
                f9 = f9 * (1.0F - f11) + f13 * f11;
                f10 = f10 * (1.0F - f11) + f14 * f11;
                f8 = f8 * 0.96F + 0.03F;
                f9 = f9 * 0.96F + 0.03F;
                f10 = f10 * 0.96F + 0.03F;

                if (f8 > 1.0F)
                {
                    f8 = 1.0F;
                }

                if (f9 > 1.0F)
                {
                    f9 = 1.0F;
                }

                if (f10 > 1.0F)
                {
                    f10 = 1.0F;
                }

                if (f8 < 0.0F)
                {
                    f8 = 0.0F;
                }

                if (f9 < 0.0F)
                {
                    f9 = 0.0F;
                }

                if (f10 < 0.0F)
                {
                    f10 = 0.0F;
                }

                short short1 = 255;
                int j = (int)(f8 * 255.0F);
                int k = (int)(f9 * 255.0F);
                int l = (int)(f10 * 255.0F);
                this.field_78504_Q[i] = short1 << 24 | j << 16 | k << 8 | l;
            }

            this.field_78513_d.func_110564_a();
            this.field_78536_aa = false;
        }
    }

    private float func_82830_a(EntityPlayer p_82830_1_, float p_82830_2_)
    {
        int i = p_82830_1_.func_70660_b(Potion.field_76439_r).func_76459_b();
        return i > 200 ? 1.0F : 0.7F + MathHelper.func_76126_a(((float)i - p_82830_2_) * (float)Math.PI * 0.2F) * 0.3F;
    }

    public void func_78480_b(float p_78480_1_)
    {
        this.field_78531_r.profiler.startMeasure("lightTex");

        if (this.field_78536_aa)
        {
            this.func_78472_g(p_78480_1_);
        }

        this.field_78531_r.profiler.endMeasure();
        boolean flag = Display.isActive();

        if (!flag && this.field_78531_r.gameSettings.field_82881_y && (!this.field_78531_r.gameSettings.field_85185_A || !Mouse.isButtonDown(1)))
        {
            if (Minecraft.func_71386_F() - this.field_78508_Y > 500L)
            {
                this.field_78531_r.func_71385_j();
            }
        }
        else
        {
            this.field_78508_Y = Minecraft.func_71386_F();
        }

        this.field_78531_r.profiler.startMeasure("mouse");

        if (this.field_78531_r.field_71415_G && flag)
        {
            this.field_78531_r.field_71417_B.func_74374_c();
            float f1 = this.field_78531_r.gameSettings.field_74341_c * 0.6F + 0.2F;
            float f2 = f1 * f1 * f1 * 8.0F;
            float f3 = (float)this.field_78531_r.field_71417_B.field_74377_a * f2;
            float f4 = (float)this.field_78531_r.field_71417_B.field_74375_b * f2;
            byte b0 = 1;

            if (this.field_78531_r.gameSettings.field_74338_d)
            {
                b0 = -1;
            }

            if (this.field_78531_r.gameSettings.field_74326_T)
            {
                this.field_78496_H += f3;
                this.field_78497_I += f4;
                float f5 = p_78480_1_ - this.field_78492_L;
                this.field_78492_L = p_78480_1_;
                f3 = this.field_78498_J * f5;
                f4 = this.field_78499_K * f5;
                this.field_78531_r.field_71439_g.func_70082_c(f3, f4 * (float)b0);
            }
            else
            {
                this.field_78531_r.field_71439_g.func_70082_c(f3, f4 * (float)b0);
            }
        }

        this.field_78531_r.profiler.endMeasure();

        if (!this.field_78531_r.field_71454_w)
        {
            field_78517_a = this.field_78531_r.gameSettings.field_74337_g;
            final ScaledResolution scaledresolution = new ScaledResolution(this.field_78531_r, this.field_78531_r.field_71443_c, this.field_78531_r.field_71440_d);
            int i = scaledresolution.func_78326_a();
            int j = scaledresolution.func_78328_b();
            final int k = Mouse.getX() * i / this.field_78531_r.field_71443_c;
            final int l = j - Mouse.getY() * j / this.field_78531_r.field_71440_d - 1;
            int i1 = this.field_78531_r.gameSettings.field_74350_i;

            if (this.field_78531_r.field_71441_e != null)
            {
                this.field_78531_r.profiler.startMeasure("level");

                if (this.field_78531_r.func_147107_h())
                {
                    this.func_78471_a(p_78480_1_, this.field_78510_Z + (long)(1000000000 / i1));
                }
                else
                {
                    this.func_78471_a(p_78480_1_, 0L);
                }

                if (OpenGlHelper.field_148824_g)
                {
                    if (this.field_147707_d != null)
                    {
                        GL11.glMatrixMode(GL11.GL_TEXTURE);
                        GL11.glPushMatrix();
                        GL11.glLoadIdentity();
                        this.field_147707_d.func_148018_a(p_78480_1_);
                        GL11.glPopMatrix();
                    }

                    this.field_78531_r.func_147110_a().func_147610_a(true);
                }

                this.field_78510_Z = System.nanoTime();
                this.field_78531_r.profiler.startNewMeasure("gui");

                if (!this.field_78531_r.gameSettings.field_74319_N || this.field_78531_r.field_71462_r != null)
                {
                    GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
                    this.field_78531_r.field_71456_v.func_73830_a(p_78480_1_, this.field_78531_r.field_71462_r != null, k, l);
                }

                this.field_78531_r.profiler.endMeasure();
            }
            else
            {
                GL11.glViewport(0, 0, this.field_78531_r.field_71443_c, this.field_78531_r.field_71440_d);
                GL11.glMatrixMode(GL11.GL_PROJECTION);
                GL11.glLoadIdentity();
                GL11.glMatrixMode(GL11.GL_MODELVIEW);
                GL11.glLoadIdentity();
                this.func_78478_c();
                this.field_78510_Z = System.nanoTime();
            }

            if (this.field_78531_r.field_71462_r != null)
            {
                GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT);

                try
                {
                    this.field_78531_r.field_71462_r.func_73863_a(k, l, p_78480_1_);
                }
                catch (Throwable throwable)
                {
                    CrashReport crashreport = CrashReport.func_85055_a(throwable, "Rendering screen");
                    CrashReportCategory crashreportcategory = crashreport.func_85058_a("Screen render details");
                    crashreportcategory.func_71500_a("Screen name", new Callable()
                    {
                        private static final String __OBFID = "CL_00000948";
                        public String call()
                        {
                            return EntityRenderer.this.field_78531_r.field_71462_r.getClass().getCanonicalName();
                        }
                    });
                    crashreportcategory.func_71500_a("Mouse location", new Callable()
                    {
                        private static final String __OBFID = "CL_00000950";
                        public String call()
                        {
                            return String.format("Scaled: (%d, %d). Absolute: (%d, %d)", new Object[] {Integer.valueOf(k), Integer.valueOf(l), Integer.valueOf(Mouse.getX()), Integer.valueOf(Mouse.getY())});
                        }
                    });
                    crashreportcategory.func_71500_a("Screen size", new Callable()
                    {
                        private static final String __OBFID = "CL_00000951";
                        public String call()
                        {
                            return String.format("Scaled: (%d, %d). Absolute: (%d, %d). Scale factor of %d", new Object[] {Integer.valueOf(scaledresolution.func_78326_a()), Integer.valueOf(scaledresolution.func_78328_b()), Integer.valueOf(EntityRenderer.this.field_78531_r.field_71443_c), Integer.valueOf(EntityRenderer.this.field_78531_r.field_71440_d), Integer.valueOf(scaledresolution.func_78325_e())});
                        }
                    });
                    throw new ReportedException(crashreport);
                }
            }
        }
    }

    public void func_152430_c(float p_152430_1_)
    {
        this.func_78478_c();
        ScaledResolution scaledresolution = new ScaledResolution(this.field_78531_r, this.field_78531_r.field_71443_c, this.field_78531_r.field_71440_d);
        int i = scaledresolution.func_78326_a();
        int j = scaledresolution.func_78328_b();
        this.field_78531_r.field_71456_v.func_152126_a((float)i, (float)j);
    }

    public void func_78471_a(float p_78471_1_, long p_78471_2_)
    {
        this.field_78531_r.profiler.startMeasure("lightTex");

        if (this.field_78536_aa)
        {
            this.func_78472_g(p_78471_1_);
        }

        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glAlphaFunc(GL11.GL_GREATER, 0.5F);

        if (this.field_78531_r.field_71451_h == null)
        {
            this.field_78531_r.field_71451_h = this.field_78531_r.field_71439_g;
        }

        this.field_78531_r.profiler.startNewMeasure("pick");
        this.func_78473_a(p_78471_1_);
        EntityLivingBase entitylivingbase = this.field_78531_r.field_71451_h;
        RenderGlobal renderglobal = this.field_78531_r.field_71438_f;
        EffectRenderer effectrenderer = this.field_78531_r.field_71452_i;
        double d0 = entitylivingbase.field_70142_S + (entitylivingbase.field_70165_t - entitylivingbase.field_70142_S) * (double)p_78471_1_;
        double d1 = entitylivingbase.field_70137_T + (entitylivingbase.field_70163_u - entitylivingbase.field_70137_T) * (double)p_78471_1_;
        double d2 = entitylivingbase.field_70136_U + (entitylivingbase.field_70161_v - entitylivingbase.field_70136_U) * (double)p_78471_1_;
        this.field_78531_r.profiler.startNewMeasure("center");

        for (int j = 0; j < 2; ++j)
        {
            if (this.field_78531_r.gameSettings.field_74337_g)
            {
                field_78515_b = j;

                if (field_78515_b == 0)
                {
                    GL11.glColorMask(false, true, true, false);
                }
                else
                {
                    GL11.glColorMask(true, false, false, false);
                }
            }

            this.field_78531_r.profiler.startNewMeasure("clear");
            GL11.glViewport(0, 0, this.field_78531_r.field_71443_c, this.field_78531_r.field_71440_d);
            this.func_78466_h(p_78471_1_);
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
            GL11.glEnable(GL11.GL_CULL_FACE);
            this.field_78531_r.profiler.startNewMeasure("camera");
            this.func_78479_a(p_78471_1_, j);
            ActiveRenderInfo.func_74583_a(this.field_78531_r.field_71439_g, this.field_78531_r.gameSettings.field_74320_O == 2);
            this.field_78531_r.profiler.startNewMeasure("frustrum");
            ClippingHelperImpl.func_78558_a();

            if (this.field_78531_r.gameSettings.field_151451_c >= 4)
            {
                this.func_78468_a(-1, p_78471_1_);
                this.field_78531_r.profiler.startNewMeasure("sky");
                renderglobal.func_72714_a(p_78471_1_);
            }

            GL11.glEnable(GL11.GL_FOG);
            this.func_78468_a(1, p_78471_1_);

            if (this.field_78531_r.gameSettings.field_74348_k != 0)
            {
                GL11.glShadeModel(GL11.GL_SMOOTH);
            }

            this.field_78531_r.profiler.startNewMeasure("culling");
            Frustrum frustrum = new Frustrum();
            frustrum.func_78547_a(d0, d1, d2);
            this.field_78531_r.field_71438_f.func_72729_a(frustrum, p_78471_1_);

            if (j == 0)
            {
                this.field_78531_r.profiler.startNewMeasure("updatechunks");

                while (!this.field_78531_r.field_71438_f.func_72716_a(entitylivingbase, false) && p_78471_2_ != 0L)
                {
                    long k = p_78471_2_ - System.nanoTime();

                    if (k < 0L || k > 1000000000L)
                    {
                        break;
                    }
                }
            }

            if (entitylivingbase.field_70163_u < 128.0D)
            {
                this.func_82829_a(renderglobal, p_78471_1_);
            }

            this.field_78531_r.profiler.startNewMeasure("prepareterrain");
            this.func_78468_a(0, p_78471_1_);
            GL11.glEnable(GL11.GL_FOG);
            this.field_78531_r.func_110434_K().func_110577_a(TextureMap.field_110575_b);
            RenderHelper.func_74518_a();
            this.field_78531_r.profiler.startNewMeasure("terrain");
            GL11.glMatrixMode(GL11.GL_MODELVIEW);
            GL11.glPushMatrix();
            renderglobal.func_72719_a(entitylivingbase, 0, (double)p_78471_1_);
            GL11.glShadeModel(GL11.GL_FLAT);
            GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
            EntityPlayer entityplayer;

            if (this.field_78532_q == 0)
            {
                GL11.glMatrixMode(GL11.GL_MODELVIEW);
                GL11.glPopMatrix();
                GL11.glPushMatrix();
                RenderHelper.func_74519_b();
                this.field_78531_r.profiler.startNewMeasure("entities");
                renderglobal.func_147589_a(entitylivingbase, frustrum, p_78471_1_);
                RenderHelper.func_74518_a();
                this.func_78483_a((double)p_78471_1_);
                GL11.glMatrixMode(GL11.GL_MODELVIEW);
                GL11.glPopMatrix();
                GL11.glPushMatrix();

                if (this.field_78531_r.field_71476_x != null && entitylivingbase.func_70055_a(Material.field_151586_h) && entitylivingbase instanceof EntityPlayer && !this.field_78531_r.gameSettings.field_74319_N)
                {
                    entityplayer = (EntityPlayer)entitylivingbase;
                    GL11.glDisable(GL11.GL_ALPHA_TEST);
                    this.field_78531_r.profiler.startNewMeasure("outline");
                    renderglobal.func_72731_b(entityplayer, this.field_78531_r.field_71476_x, 0, p_78471_1_);
                    GL11.glEnable(GL11.GL_ALPHA_TEST);
                }
            }

            GL11.glMatrixMode(GL11.GL_MODELVIEW);
            GL11.glPopMatrix();

            if (this.field_78503_V == 1.0D && entitylivingbase instanceof EntityPlayer && !this.field_78531_r.gameSettings.field_74319_N && this.field_78531_r.field_71476_x != null && !entitylivingbase.func_70055_a(Material.field_151586_h))
            {
                entityplayer = (EntityPlayer)entitylivingbase;
                GL11.glDisable(GL11.GL_ALPHA_TEST);
                this.field_78531_r.profiler.startNewMeasure("outline");
                renderglobal.func_72731_b(entityplayer, this.field_78531_r.field_71476_x, 0, p_78471_1_);
                GL11.glEnable(GL11.GL_ALPHA_TEST);
            }

            this.field_78531_r.profiler.startNewMeasure("destroyProgress");
            GL11.glEnable(GL11.GL_BLEND);
            OpenGlHelper.func_148821_a(770, 1, 1, 0);
            renderglobal.func_72717_a(Tessellator.field_78398_a, (EntityPlayer)entitylivingbase, p_78471_1_);
            GL11.glDisable(GL11.GL_BLEND);

            if (this.field_78532_q == 0)
            {
                this.func_78463_b((double)p_78471_1_);
                this.field_78531_r.profiler.startNewMeasure("litParticles");
                effectrenderer.func_78872_b(entitylivingbase, p_78471_1_);
                RenderHelper.func_74518_a();
                this.func_78468_a(0, p_78471_1_);
                this.field_78531_r.profiler.startNewMeasure("particles");
                effectrenderer.func_78874_a(entitylivingbase, p_78471_1_);
                this.func_78483_a((double)p_78471_1_);
            }

            GL11.glDepthMask(false);
            GL11.glEnable(GL11.GL_CULL_FACE);
            this.field_78531_r.profiler.startNewMeasure("weather");
            this.func_78474_d(p_78471_1_);
            GL11.glDepthMask(true);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glEnable(GL11.GL_CULL_FACE);
            OpenGlHelper.func_148821_a(770, 771, 1, 0);
            GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
            this.func_78468_a(0, p_78471_1_);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glDepthMask(false);
            this.field_78531_r.func_110434_K().func_110577_a(TextureMap.field_110575_b);

            if (this.field_78531_r.gameSettings.field_74347_j)
            {
                this.field_78531_r.profiler.startNewMeasure("water");

                if (this.field_78531_r.gameSettings.field_74348_k != 0)
                {
                    GL11.glShadeModel(GL11.GL_SMOOTH);
                }

                GL11.glEnable(GL11.GL_BLEND);
                OpenGlHelper.func_148821_a(770, 771, 1, 0);

                if (this.field_78531_r.gameSettings.field_74337_g)
                {
                    if (field_78515_b == 0)
                    {
                        GL11.glColorMask(false, true, true, true);
                    }
                    else
                    {
                        GL11.glColorMask(true, false, false, true);
                    }

                    renderglobal.func_72719_a(entitylivingbase, 1, (double)p_78471_1_);
                }
                else
                {
                    renderglobal.func_72719_a(entitylivingbase, 1, (double)p_78471_1_);
                }

                GL11.glDisable(GL11.GL_BLEND);
                GL11.glShadeModel(GL11.GL_FLAT);
            }
            else
            {
                this.field_78531_r.profiler.startNewMeasure("water");
                renderglobal.func_72719_a(entitylivingbase, 1, (double)p_78471_1_);
            }

            GL11.glDepthMask(true);
            GL11.glEnable(GL11.GL_CULL_FACE);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glDisable(GL11.GL_FOG);

            if (entitylivingbase.field_70163_u >= 128.0D)
            {
                this.field_78531_r.profiler.startNewMeasure("aboveClouds");
                this.func_82829_a(renderglobal, p_78471_1_);
            }

            this.field_78531_r.profiler.startNewMeasure("hand");

            if (this.field_78503_V == 1.0D)
            {
                GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT);
                this.func_78476_b(p_78471_1_, j);
            }

            if (!this.field_78531_r.gameSettings.field_74337_g)
            {
                this.field_78531_r.profiler.endMeasure();
                return;
            }
        }

        GL11.glColorMask(true, true, true, false);
        this.field_78531_r.profiler.endMeasure();
    }

    private void func_82829_a(RenderGlobal p_82829_1_, float p_82829_2_)
    {
        if (this.field_78531_r.gameSettings.func_74309_c())
        {
            this.field_78531_r.profiler.startNewMeasure("clouds");
            GL11.glPushMatrix();
            this.func_78468_a(0, p_82829_2_);
            GL11.glEnable(GL11.GL_FOG);
            p_82829_1_.func_72718_b(p_82829_2_);
            GL11.glDisable(GL11.GL_FOG);
            this.func_78468_a(1, p_82829_2_);
            GL11.glPopMatrix();
        }
    }

    private void func_78484_h()
    {
        float f = this.field_78531_r.field_71441_e.func_72867_j(1.0F);

        if (!this.field_78531_r.gameSettings.field_74347_j)
        {
            f /= 2.0F;
        }

        if (f != 0.0F)
        {
            this.field_78537_ab.setSeed((long)this.field_78529_t * 312987231L);
            EntityLivingBase entitylivingbase = this.field_78531_r.field_71451_h;
            WorldClient worldclient = this.field_78531_r.field_71441_e;
            int i = MathHelper.func_76128_c(entitylivingbase.field_70165_t);
            int j = MathHelper.func_76128_c(entitylivingbase.field_70163_u);
            int k = MathHelper.func_76128_c(entitylivingbase.field_70161_v);
            byte b0 = 10;
            double d0 = 0.0D;
            double d1 = 0.0D;
            double d2 = 0.0D;
            int l = 0;
            int i1 = (int)(100.0F * f * f);

            if (this.field_78531_r.gameSettings.field_74362_aa == 1)
            {
                i1 >>= 1;
            }
            else if (this.field_78531_r.gameSettings.field_74362_aa == 2)
            {
                i1 = 0;
            }

            for (int j1 = 0; j1 < i1; ++j1)
            {
                int k1 = i + this.field_78537_ab.nextInt(b0) - this.field_78537_ab.nextInt(b0);
                int l1 = k + this.field_78537_ab.nextInt(b0) - this.field_78537_ab.nextInt(b0);
                int i2 = worldclient.func_72874_g(k1, l1);
                Block block = worldclient.func_147439_a(k1, i2 - 1, l1);
                BiomeGenBase biomegenbase = worldclient.func_72807_a(k1, l1);

                if (i2 <= j + b0 && i2 >= j - b0 && biomegenbase.func_76738_d() && biomegenbase.func_150564_a(k1, i2, l1) >= 0.15F)
                {
                    float f1 = this.field_78537_ab.nextFloat();
                    float f2 = this.field_78537_ab.nextFloat();

                    if (block.func_149688_o() == Material.field_151587_i)
                    {
                        this.field_78531_r.field_71452_i.func_78873_a(new EntitySmokeFX(worldclient, (double)((float)k1 + f1), (double)((float)i2 + 0.1F) - block.func_149665_z(), (double)((float)l1 + f2), 0.0D, 0.0D, 0.0D));
                    }
                    else if (block.func_149688_o() != Material.field_151579_a)
                    {
                        ++l;

                        if (this.field_78537_ab.nextInt(l) == 0)
                        {
                            d0 = (double)((float)k1 + f1);
                            d1 = (double)((float)i2 + 0.1F) - block.func_149665_z();
                            d2 = (double)((float)l1 + f2);
                        }

                        this.field_78531_r.field_71452_i.func_78873_a(new EntityRainFX(worldclient, (double)((float)k1 + f1), (double)((float)i2 + 0.1F) - block.func_149665_z(), (double)((float)l1 + f2)));
                    }
                }
            }

            if (l > 0 && this.field_78537_ab.nextInt(3) < this.field_78534_ac++)
            {
                this.field_78534_ac = 0;

                if (d1 > entitylivingbase.field_70163_u + 1.0D && worldclient.func_72874_g(MathHelper.func_76128_c(entitylivingbase.field_70165_t), MathHelper.func_76128_c(entitylivingbase.field_70161_v)) > MathHelper.func_76128_c(entitylivingbase.field_70163_u))
                {
                    this.field_78531_r.field_71441_e.func_72980_b(d0, d1, d2, "ambient.weather.rain", 0.1F, 0.5F, false);
                }
                else
                {
                    this.field_78531_r.field_71441_e.func_72980_b(d0, d1, d2, "ambient.weather.rain", 0.2F, 1.0F, false);
                }
            }
        }
    }

    protected void func_78474_d(float p_78474_1_)
    {
        float f1 = this.field_78531_r.field_71441_e.func_72867_j(p_78474_1_);

        if (f1 > 0.0F)
        {
            this.func_78463_b((double)p_78474_1_);

            if (this.field_78525_i == null)
            {
                this.field_78525_i = new float[1024];
                this.field_78522_j = new float[1024];

                for (int i = 0; i < 32; ++i)
                {
                    for (int j = 0; j < 32; ++j)
                    {
                        float f2 = (float)(j - 16);
                        float f3 = (float)(i - 16);
                        float f4 = MathHelper.func_76129_c(f2 * f2 + f3 * f3);
                        this.field_78525_i[i << 5 | j] = -f3 / f4;
                        this.field_78522_j[i << 5 | j] = f2 / f4;
                    }
                }
            }

            EntityLivingBase entitylivingbase = this.field_78531_r.field_71451_h;
            WorldClient worldclient = this.field_78531_r.field_71441_e;
            int k2 = MathHelper.func_76128_c(entitylivingbase.field_70165_t);
            int l2 = MathHelper.func_76128_c(entitylivingbase.field_70163_u);
            int i3 = MathHelper.func_76128_c(entitylivingbase.field_70161_v);
            Tessellator tessellator = Tessellator.field_78398_a;
            GL11.glDisable(GL11.GL_CULL_FACE);
            GL11.glNormal3f(0.0F, 1.0F, 0.0F);
            GL11.glEnable(GL11.GL_BLEND);
            OpenGlHelper.func_148821_a(770, 771, 1, 0);
            GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
            double d0 = entitylivingbase.field_70142_S + (entitylivingbase.field_70165_t - entitylivingbase.field_70142_S) * (double)p_78474_1_;
            double d1 = entitylivingbase.field_70137_T + (entitylivingbase.field_70163_u - entitylivingbase.field_70137_T) * (double)p_78474_1_;
            double d2 = entitylivingbase.field_70136_U + (entitylivingbase.field_70161_v - entitylivingbase.field_70136_U) * (double)p_78474_1_;
            int k = MathHelper.func_76128_c(d1);
            byte b0 = 5;

            if (this.field_78531_r.gameSettings.field_74347_j)
            {
                b0 = 10;
            }

            boolean flag = false;
            byte b1 = -1;
            float f5 = (float)this.field_78529_t + p_78474_1_;

            if (this.field_78531_r.gameSettings.field_74347_j)
            {
                b0 = 10;
            }

            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            flag = false;

            for (int l = i3 - b0; l <= i3 + b0; ++l)
            {
                for (int i1 = k2 - b0; i1 <= k2 + b0; ++i1)
                {
                    int j1 = (l - i3 + 16) * 32 + i1 - k2 + 16;
                    float f6 = this.field_78525_i[j1] * 0.5F;
                    float f7 = this.field_78522_j[j1] * 0.5F;
                    BiomeGenBase biomegenbase = worldclient.func_72807_a(i1, l);

                    if (biomegenbase.func_76738_d() || biomegenbase.func_76746_c())
                    {
                        int k1 = worldclient.func_72874_g(i1, l);
                        int l1 = l2 - b0;
                        int i2 = l2 + b0;

                        if (l1 < k1)
                        {
                            l1 = k1;
                        }

                        if (i2 < k1)
                        {
                            i2 = k1;
                        }

                        float f8 = 1.0F;
                        int j2 = k1;

                        if (k1 < k)
                        {
                            j2 = k;
                        }

                        if (l1 != i2)
                        {
                            this.field_78537_ab.setSeed((long)(i1 * i1 * 3121 + i1 * 45238971 ^ l * l * 418711 + l * 13761));
                            float f9 = biomegenbase.func_150564_a(i1, l1, l);
                            float f10;
                            double d4;

                            if (worldclient.func_72959_q().func_76939_a(f9, k1) >= 0.15F)
                            {
                                if (b1 != 0)
                                {
                                    if (b1 >= 0)
                                    {
                                        tessellator.func_78381_a();
                                    }

                                    b1 = 0;
                                    this.field_78531_r.func_110434_K().func_110577_a(field_110924_q);
                                    tessellator.func_78382_b();
                                }

                                f10 = ((float)(this.field_78529_t + i1 * i1 * 3121 + i1 * 45238971 + l * l * 418711 + l * 13761 & 31) + p_78474_1_) / 32.0F * (3.0F + this.field_78537_ab.nextFloat());
                                double d3 = (double)((float)i1 + 0.5F) - entitylivingbase.field_70165_t;
                                d4 = (double)((float)l + 0.5F) - entitylivingbase.field_70161_v;
                                float f12 = MathHelper.func_76133_a(d3 * d3 + d4 * d4) / (float)b0;
                                float f13 = 1.0F;
                                tessellator.func_78380_c(worldclient.func_72802_i(i1, j2, l, 0));
                                tessellator.func_78369_a(f13, f13, f13, ((1.0F - f12 * f12) * 0.5F + 0.5F) * f1);
                                tessellator.func_78373_b(-d0 * 1.0D, -d1 * 1.0D, -d2 * 1.0D);
                                tessellator.func_78374_a((double)((float)i1 - f6) + 0.5D, (double)l1, (double)((float)l - f7) + 0.5D, (double)(0.0F * f8), (double)((float)l1 * f8 / 4.0F + f10 * f8));
                                tessellator.func_78374_a((double)((float)i1 + f6) + 0.5D, (double)l1, (double)((float)l + f7) + 0.5D, (double)(1.0F * f8), (double)((float)l1 * f8 / 4.0F + f10 * f8));
                                tessellator.func_78374_a((double)((float)i1 + f6) + 0.5D, (double)i2, (double)((float)l + f7) + 0.5D, (double)(1.0F * f8), (double)((float)i2 * f8 / 4.0F + f10 * f8));
                                tessellator.func_78374_a((double)((float)i1 - f6) + 0.5D, (double)i2, (double)((float)l - f7) + 0.5D, (double)(0.0F * f8), (double)((float)i2 * f8 / 4.0F + f10 * f8));
                                tessellator.func_78373_b(0.0D, 0.0D, 0.0D);
                            }
                            else
                            {
                                if (b1 != 1)
                                {
                                    if (b1 >= 0)
                                    {
                                        tessellator.func_78381_a();
                                    }

                                    b1 = 1;
                                    this.field_78531_r.func_110434_K().func_110577_a(field_110923_r);
                                    tessellator.func_78382_b();
                                }

                                f10 = ((float)(this.field_78529_t & 511) + p_78474_1_) / 512.0F;
                                float f16 = this.field_78537_ab.nextFloat() + f5 * 0.01F * (float)this.field_78537_ab.nextGaussian();
                                float f11 = this.field_78537_ab.nextFloat() + f5 * (float)this.field_78537_ab.nextGaussian() * 0.001F;
                                d4 = (double)((float)i1 + 0.5F) - entitylivingbase.field_70165_t;
                                double d5 = (double)((float)l + 0.5F) - entitylivingbase.field_70161_v;
                                float f14 = MathHelper.func_76133_a(d4 * d4 + d5 * d5) / (float)b0;
                                float f15 = 1.0F;
                                tessellator.func_78380_c((worldclient.func_72802_i(i1, j2, l, 0) * 3 + 15728880) / 4);
                                tessellator.func_78369_a(f15, f15, f15, ((1.0F - f14 * f14) * 0.3F + 0.5F) * f1);
                                tessellator.func_78373_b(-d0 * 1.0D, -d1 * 1.0D, -d2 * 1.0D);
                                tessellator.func_78374_a((double)((float)i1 - f6) + 0.5D, (double)l1, (double)((float)l - f7) + 0.5D, (double)(0.0F * f8 + f16), (double)((float)l1 * f8 / 4.0F + f10 * f8 + f11));
                                tessellator.func_78374_a((double)((float)i1 + f6) + 0.5D, (double)l1, (double)((float)l + f7) + 0.5D, (double)(1.0F * f8 + f16), (double)((float)l1 * f8 / 4.0F + f10 * f8 + f11));
                                tessellator.func_78374_a((double)((float)i1 + f6) + 0.5D, (double)i2, (double)((float)l + f7) + 0.5D, (double)(1.0F * f8 + f16), (double)((float)i2 * f8 / 4.0F + f10 * f8 + f11));
                                tessellator.func_78374_a((double)((float)i1 - f6) + 0.5D, (double)i2, (double)((float)l - f7) + 0.5D, (double)(0.0F * f8 + f16), (double)((float)i2 * f8 / 4.0F + f10 * f8 + f11));
                                tessellator.func_78373_b(0.0D, 0.0D, 0.0D);
                            }
                        }
                    }
                }
            }

            if (b1 >= 0)
            {
                tessellator.func_78381_a();
            }

            GL11.glEnable(GL11.GL_CULL_FACE);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
            this.func_78483_a((double)p_78474_1_);
        }
    }

    public void func_78478_c()
    {
        ScaledResolution scaledresolution = new ScaledResolution(this.field_78531_r, this.field_78531_r.field_71443_c, this.field_78531_r.field_71440_d);
        GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0.0D, scaledresolution.func_78327_c(), scaledresolution.func_78324_d(), 0.0D, 1000.0D, 3000.0D);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glLoadIdentity();
        GL11.glTranslatef(0.0F, 0.0F, -2000.0F);
    }

    private void func_78466_h(float p_78466_1_)
    {
        WorldClient worldclient = this.field_78531_r.field_71441_e;
        EntityLivingBase entitylivingbase = this.field_78531_r.field_71451_h;
        float f1 = 0.25F + 0.75F * (float)this.field_78531_r.gameSettings.field_151451_c / 16.0F;
        f1 = 1.0F - (float)Math.pow((double)f1, 0.25D);
        Vec3 vec3 = worldclient.func_72833_a(this.field_78531_r.field_71451_h, p_78466_1_);
        float f2 = (float)vec3.field_72450_a;
        float f3 = (float)vec3.field_72448_b;
        float f4 = (float)vec3.field_72449_c;
        Vec3 vec31 = worldclient.func_72948_g(p_78466_1_);
        this.field_78518_n = (float)vec31.field_72450_a;
        this.field_78519_o = (float)vec31.field_72448_b;
        this.field_78533_p = (float)vec31.field_72449_c;
        float f5;

        if (this.field_78531_r.gameSettings.field_151451_c >= 4)
        {
            Vec3 vec32 = MathHelper.func_76126_a(worldclient.func_72929_e(p_78466_1_)) > 0.0F ? Vec3.func_72443_a(-1.0D, 0.0D, 0.0D) : Vec3.func_72443_a(1.0D, 0.0D, 0.0D);
            f5 = (float)entitylivingbase.func_70676_i(p_78466_1_).func_72430_b(vec32);

            if (f5 < 0.0F)
            {
                f5 = 0.0F;
            }

            if (f5 > 0.0F)
            {
                float[] afloat = worldclient.field_73011_w.func_76560_a(worldclient.func_72826_c(p_78466_1_), p_78466_1_);

                if (afloat != null)
                {
                    f5 *= afloat[3];
                    this.field_78518_n = this.field_78518_n * (1.0F - f5) + afloat[0] * f5;
                    this.field_78519_o = this.field_78519_o * (1.0F - f5) + afloat[1] * f5;
                    this.field_78533_p = this.field_78533_p * (1.0F - f5) + afloat[2] * f5;
                }
            }
        }

        this.field_78518_n += (f2 - this.field_78518_n) * f1;
        this.field_78519_o += (f3 - this.field_78519_o) * f1;
        this.field_78533_p += (f4 - this.field_78533_p) * f1;
        float f8 = worldclient.func_72867_j(p_78466_1_);
        float f9;

        if (f8 > 0.0F)
        {
            f5 = 1.0F - f8 * 0.5F;
            f9 = 1.0F - f8 * 0.4F;
            this.field_78518_n *= f5;
            this.field_78519_o *= f5;
            this.field_78533_p *= f9;
        }

        f5 = worldclient.func_72819_i(p_78466_1_);

        if (f5 > 0.0F)
        {
            f9 = 1.0F - f5 * 0.5F;
            this.field_78518_n *= f9;
            this.field_78519_o *= f9;
            this.field_78533_p *= f9;
        }

        Block block = ActiveRenderInfo.func_151460_a(this.field_78531_r.field_71441_e, entitylivingbase, p_78466_1_);
        float f10;

        if (this.field_78500_U)
        {
            Vec3 vec33 = worldclient.func_72824_f(p_78466_1_);
            this.field_78518_n = (float)vec33.field_72450_a;
            this.field_78519_o = (float)vec33.field_72448_b;
            this.field_78533_p = (float)vec33.field_72449_c;
        }
        else if (block.func_149688_o() == Material.field_151586_h)
        {
            f10 = (float)EnchantmentHelper.func_77501_a(entitylivingbase) * 0.2F;
            this.field_78518_n = 0.02F + f10;
            this.field_78519_o = 0.02F + f10;
            this.field_78533_p = 0.2F + f10;
        }
        else if (block.func_149688_o() == Material.field_151587_i)
        {
            this.field_78518_n = 0.6F;
            this.field_78519_o = 0.1F;
            this.field_78533_p = 0.0F;
        }

        f10 = this.field_78535_ad + (this.field_78539_ae - this.field_78535_ad) * p_78466_1_;
        this.field_78518_n *= f10;
        this.field_78519_o *= f10;
        this.field_78533_p *= f10;
        double d0 = (entitylivingbase.field_70137_T + (entitylivingbase.field_70163_u - entitylivingbase.field_70137_T) * (double)p_78466_1_) * worldclient.field_73011_w.func_76565_k();

        if (entitylivingbase.func_70644_a(Potion.field_76440_q))
        {
            int i = entitylivingbase.func_70660_b(Potion.field_76440_q).func_76459_b();

            if (i < 20)
            {
                d0 *= (double)(1.0F - (float)i / 20.0F);
            }
            else
            {
                d0 = 0.0D;
            }
        }

        if (d0 < 1.0D)
        {
            if (d0 < 0.0D)
            {
                d0 = 0.0D;
            }

            d0 *= d0;
            this.field_78518_n = (float)((double)this.field_78518_n * d0);
            this.field_78519_o = (float)((double)this.field_78519_o * d0);
            this.field_78533_p = (float)((double)this.field_78533_p * d0);
        }

        float f11;

        if (this.field_82831_U > 0.0F)
        {
            f11 = this.field_82832_V + (this.field_82831_U - this.field_82832_V) * p_78466_1_;
            this.field_78518_n = this.field_78518_n * (1.0F - f11) + this.field_78518_n * 0.7F * f11;
            this.field_78519_o = this.field_78519_o * (1.0F - f11) + this.field_78519_o * 0.6F * f11;
            this.field_78533_p = this.field_78533_p * (1.0F - f11) + this.field_78533_p * 0.6F * f11;
        }

        float f6;

        if (entitylivingbase.func_70644_a(Potion.field_76439_r))
        {
            f11 = this.func_82830_a(this.field_78531_r.field_71439_g, p_78466_1_);
            f6 = 1.0F / this.field_78518_n;

            if (f6 > 1.0F / this.field_78519_o)
            {
                f6 = 1.0F / this.field_78519_o;
            }

            if (f6 > 1.0F / this.field_78533_p)
            {
                f6 = 1.0F / this.field_78533_p;
            }

            this.field_78518_n = this.field_78518_n * (1.0F - f11) + this.field_78518_n * f6 * f11;
            this.field_78519_o = this.field_78519_o * (1.0F - f11) + this.field_78519_o * f6 * f11;
            this.field_78533_p = this.field_78533_p * (1.0F - f11) + this.field_78533_p * f6 * f11;
        }

        if (this.field_78531_r.gameSettings.field_74337_g)
        {
            f11 = (this.field_78518_n * 30.0F + this.field_78519_o * 59.0F + this.field_78533_p * 11.0F) / 100.0F;
            f6 = (this.field_78518_n * 30.0F + this.field_78519_o * 70.0F) / 100.0F;
            float f7 = (this.field_78518_n * 30.0F + this.field_78533_p * 70.0F) / 100.0F;
            this.field_78518_n = f11;
            this.field_78519_o = f6;
            this.field_78533_p = f7;
        }

        GL11.glClearColor(this.field_78518_n, this.field_78519_o, this.field_78533_p, 0.0F);
    }

    private void func_78468_a(int p_78468_1_, float p_78468_2_)
    {
        EntityLivingBase entitylivingbase = this.field_78531_r.field_71451_h;
        boolean flag = false;

        if (entitylivingbase instanceof EntityPlayer)
        {
            flag = ((EntityPlayer)entitylivingbase).field_71075_bZ.field_75098_d;
        }

        if (p_78468_1_ == 999)
        {
            GL11.glFog(GL11.GL_FOG_COLOR, this.func_78469_a(0.0F, 0.0F, 0.0F, 1.0F));
            GL11.glFogi(GL11.GL_FOG_MODE, GL11.GL_LINEAR);
            GL11.glFogf(GL11.GL_FOG_START, 0.0F);
            GL11.glFogf(GL11.GL_FOG_END, 8.0F);

            if (GLContext.getCapabilities().GL_NV_fog_distance)
            {
                GL11.glFogi(34138, 34139);
            }

            GL11.glFogf(GL11.GL_FOG_START, 0.0F);
        }
        else
        {
            GL11.glFog(GL11.GL_FOG_COLOR, this.func_78469_a(this.field_78518_n, this.field_78519_o, this.field_78533_p, 1.0F));
            GL11.glNormal3f(0.0F, -1.0F, 0.0F);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            Block block = ActiveRenderInfo.func_151460_a(this.field_78531_r.field_71441_e, entitylivingbase, p_78468_2_);
            float f1;

            if (entitylivingbase.func_70644_a(Potion.field_76440_q))
            {
                f1 = 5.0F;
                int j = entitylivingbase.func_70660_b(Potion.field_76440_q).func_76459_b();

                if (j < 20)
                {
                    f1 = 5.0F + (this.field_78530_s - 5.0F) * (1.0F - (float)j / 20.0F);
                }

                GL11.glFogi(GL11.GL_FOG_MODE, GL11.GL_LINEAR);

                if (p_78468_1_ < 0)
                {
                    GL11.glFogf(GL11.GL_FOG_START, 0.0F);
                    GL11.glFogf(GL11.GL_FOG_END, f1 * 0.8F);
                }
                else
                {
                    GL11.glFogf(GL11.GL_FOG_START, f1 * 0.25F);
                    GL11.glFogf(GL11.GL_FOG_END, f1);
                }

                if (GLContext.getCapabilities().GL_NV_fog_distance)
                {
                    GL11.glFogi(34138, 34139);
                }
            }
            else if (this.field_78500_U)
            {
                GL11.glFogi(GL11.GL_FOG_MODE, GL11.GL_EXP);
                GL11.glFogf(GL11.GL_FOG_DENSITY, 0.1F);
            }
            else if (block.func_149688_o() == Material.field_151586_h)
            {
                GL11.glFogi(GL11.GL_FOG_MODE, GL11.GL_EXP);

                if (entitylivingbase.func_70644_a(Potion.field_76427_o))
                {
                    GL11.glFogf(GL11.GL_FOG_DENSITY, 0.05F);
                }
                else
                {
                    GL11.glFogf(GL11.GL_FOG_DENSITY, 0.1F - (float)EnchantmentHelper.func_77501_a(entitylivingbase) * 0.03F);
                }
            }
            else if (block.func_149688_o() == Material.field_151587_i)
            {
                GL11.glFogi(GL11.GL_FOG_MODE, GL11.GL_EXP);
                GL11.glFogf(GL11.GL_FOG_DENSITY, 2.0F);
            }
            else
            {
                f1 = this.field_78530_s;

                if (this.field_78531_r.field_71441_e.field_73011_w.func_76564_j() && !flag)
                {
                    double d0 = (double)((entitylivingbase.func_70070_b(p_78468_2_) & 15728640) >> 20) / 16.0D + (entitylivingbase.field_70137_T + (entitylivingbase.field_70163_u - entitylivingbase.field_70137_T) * (double)p_78468_2_ + 4.0D) / 32.0D;

                    if (d0 < 1.0D)
                    {
                        if (d0 < 0.0D)
                        {
                            d0 = 0.0D;
                        }

                        d0 *= d0;
                        float f2 = 100.0F * (float)d0;

                        if (f2 < 5.0F)
                        {
                            f2 = 5.0F;
                        }

                        if (f1 > f2)
                        {
                            f1 = f2;
                        }
                    }
                }

                GL11.glFogi(GL11.GL_FOG_MODE, GL11.GL_LINEAR);

                if (p_78468_1_ < 0)
                {
                    GL11.glFogf(GL11.GL_FOG_START, 0.0F);
                    GL11.glFogf(GL11.GL_FOG_END, f1);
                }
                else
                {
                    GL11.glFogf(GL11.GL_FOG_START, f1 * 0.75F);
                    GL11.glFogf(GL11.GL_FOG_END, f1);
                }

                if (GLContext.getCapabilities().GL_NV_fog_distance)
                {
                    GL11.glFogi(34138, 34139);
                }

                if (this.field_78531_r.field_71441_e.field_73011_w.func_76568_b((int)entitylivingbase.field_70165_t, (int)entitylivingbase.field_70161_v))
                {
                    GL11.glFogf(GL11.GL_FOG_START, f1 * 0.05F);
                    GL11.glFogf(GL11.GL_FOG_END, Math.min(f1, 192.0F) * 0.5F);
                }
            }

            GL11.glEnable(GL11.GL_COLOR_MATERIAL);
            GL11.glColorMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT);
        }
    }

    private FloatBuffer func_78469_a(float p_78469_1_, float p_78469_2_, float p_78469_3_, float p_78469_4_)
    {
        this.field_78521_m.clear();
        this.field_78521_m.put(p_78469_1_).put(p_78469_2_).put(p_78469_3_).put(p_78469_4_);
        this.field_78521_m.flip();
        return this.field_78521_m;
    }

    public MapItemRenderer func_147701_i()
    {
        return this.field_147709_v;
    }
}