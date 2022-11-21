package net.minecraft.client.renderer;

import com.google.common.collect.Maps;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.particle.EntityAuraFX;
import net.minecraft.client.particle.EntityBlockDustFX;
import net.minecraft.client.particle.EntityBreakingFX;
import net.minecraft.client.particle.EntityBubbleFX;
import net.minecraft.client.particle.EntityCloudFX;
import net.minecraft.client.particle.EntityCritFX;
import net.minecraft.client.particle.EntityDiggingFX;
import net.minecraft.client.particle.EntityDropParticleFX;
import net.minecraft.client.particle.EntityEnchantmentTableParticleFX;
import net.minecraft.client.particle.EntityExplodeFX;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.EntityFireworkSparkFX;
import net.minecraft.client.particle.EntityFishWakeFX;
import net.minecraft.client.particle.EntityFlameFX;
import net.minecraft.client.particle.EntityFootStepFX;
import net.minecraft.client.particle.EntityHeartFX;
import net.minecraft.client.particle.EntityHugeExplodeFX;
import net.minecraft.client.particle.EntityLargeExplodeFX;
import net.minecraft.client.particle.EntityLavaFX;
import net.minecraft.client.particle.EntityNoteFX;
import net.minecraft.client.particle.EntityPortalFX;
import net.minecraft.client.particle.EntityReddustFX;
import net.minecraft.client.particle.EntitySmokeFX;
import net.minecraft.client.particle.EntitySnowShovelFX;
import net.minecraft.client.particle.EntitySpellParticleFX;
import net.minecraft.client.particle.EntitySplashFX;
import net.minecraft.client.particle.EntitySuspendFX;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.util.RenderDistanceSorter;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemRecord;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ReportedException;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.IWorldAccess;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.ARBOcclusionQuery;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderGlobal implements IWorldAccess
{
    private static final Logger field_147599_m = LogManager.getLogger();
    private static final ResourceLocation field_110927_h = new ResourceLocation("textures/environment/moon_phases.png");
    private static final ResourceLocation field_110928_i = new ResourceLocation("textures/environment/sun.png");
    private static final ResourceLocation field_110925_j = new ResourceLocation("textures/environment/clouds.png");
    private static final ResourceLocation field_110926_k = new ResourceLocation("textures/environment/end_sky.png");
    public List field_147598_a = new ArrayList();
    private WorldClient field_72769_h;
    private final TextureManager field_72770_i;
    private List field_72767_j = new ArrayList();
    private WorldRenderer[] field_72768_k;
    private WorldRenderer[] field_72765_l;
    private int field_72766_m;
    private int field_72763_n;
    private int field_72764_o;
    private int field_72778_p;
    private Minecraft field_72777_q;
    private RenderBlocks field_147592_B;
    private IntBuffer field_72775_s;
    private boolean field_72774_t;
    private int field_72773_u;
    private int field_72772_v;
    private int field_72771_w;
    private int field_72781_x;
    private int field_72780_y;
    private int field_72779_z;
    private int field_72741_A;
    private int field_72742_B;
    private int field_72743_C;
    private int field_72737_D;
    private final Map field_72738_E = new HashMap();
    private final Map field_147593_P = Maps.newHashMap();
    private IIcon[] field_94141_F;
    private boolean field_147595_R;
    private int field_147594_S;
    private int field_72739_F = -1;
    private int field_72740_G = 2;
    private int field_72748_H;
    private int field_72749_I;
    private int field_72750_J;
    IntBuffer field_72761_c = GLAllocation.func_74527_f(64);
    private int field_72751_K;
    private int field_72744_L;
    private int field_72745_M;
    private int field_72746_N;
    private int field_72747_O;
    private int field_72753_P;
    private int field_72752_Q;
    private List field_72755_R = new ArrayList();
    private RenderList[] field_72754_S = new RenderList[] {new RenderList(), new RenderList(), new RenderList(), new RenderList()};
    double field_72758_d = -9999.0D;
    double field_72759_e = -9999.0D;
    double field_72756_f = -9999.0D;
    double field_147596_f = -9999.0D;
    double field_147597_g = -9999.0D;
    double field_147602_h = -9999.0D;
    int field_147603_i = -999;
    int field_147600_j = -999;
    int field_147601_k = -999;
    int field_72757_g;
    private static final String __OBFID = "CL_00000954";

    public RenderGlobal(Minecraft p_i1249_1_)
    {
        this.field_72777_q = p_i1249_1_;
        this.field_72770_i = p_i1249_1_.func_110434_K();
        byte b0 = 34;
        byte b1 = 16;
        this.field_72778_p = GLAllocation.func_74526_a(b0 * b0 * b1 * 3);
        this.field_147595_R = false;
        this.field_147594_S = GLAllocation.func_74526_a(1);
        this.field_72774_t = OpenGlCapsChecker.func_74371_a();

        if (this.field_72774_t)
        {
            this.field_72761_c.clear();
            this.field_72775_s = GLAllocation.func_74527_f(b0 * b0 * b1);
            this.field_72775_s.clear();
            this.field_72775_s.position(0);
            this.field_72775_s.limit(b0 * b0 * b1);
            ARBOcclusionQuery.glGenQueriesARB(this.field_72775_s);
        }

        this.field_72772_v = GLAllocation.func_74526_a(3);
        GL11.glPushMatrix();
        GL11.glNewList(this.field_72772_v, GL11.GL_COMPILE);
        this.func_72730_g();
        GL11.glEndList();
        GL11.glPopMatrix();
        Tessellator tessellator = Tessellator.field_78398_a;
        this.field_72771_w = this.field_72772_v + 1;
        GL11.glNewList(this.field_72771_w, GL11.GL_COMPILE);
        byte b2 = 64;
        int i = 256 / b2 + 2;
        float f = 16.0F;
        int j;
        int k;

        for (j = -b2 * i; j <= b2 * i; j += b2)
        {
            for (k = -b2 * i; k <= b2 * i; k += b2)
            {
                tessellator.func_78382_b();
                tessellator.func_78377_a((double)(j + 0), (double)f, (double)(k + 0));
                tessellator.func_78377_a((double)(j + b2), (double)f, (double)(k + 0));
                tessellator.func_78377_a((double)(j + b2), (double)f, (double)(k + b2));
                tessellator.func_78377_a((double)(j + 0), (double)f, (double)(k + b2));
                tessellator.func_78381_a();
            }
        }

        GL11.glEndList();
        this.field_72781_x = this.field_72772_v + 2;
        GL11.glNewList(this.field_72781_x, GL11.GL_COMPILE);
        f = -16.0F;
        tessellator.func_78382_b();

        for (j = -b2 * i; j <= b2 * i; j += b2)
        {
            for (k = -b2 * i; k <= b2 * i; k += b2)
            {
                tessellator.func_78377_a((double)(j + b2), (double)f, (double)(k + 0));
                tessellator.func_78377_a((double)(j + 0), (double)f, (double)(k + 0));
                tessellator.func_78377_a((double)(j + 0), (double)f, (double)(k + b2));
                tessellator.func_78377_a((double)(j + b2), (double)f, (double)(k + b2));
            }
        }

        tessellator.func_78381_a();
        GL11.glEndList();
    }

    private void func_72730_g()
    {
        Random random = new Random(10842L);
        Tessellator tessellator = Tessellator.field_78398_a;
        tessellator.func_78382_b();

        for (int i = 0; i < 1500; ++i)
        {
            double d0 = (double)(random.nextFloat() * 2.0F - 1.0F);
            double d1 = (double)(random.nextFloat() * 2.0F - 1.0F);
            double d2 = (double)(random.nextFloat() * 2.0F - 1.0F);
            double d3 = (double)(0.15F + random.nextFloat() * 0.1F);
            double d4 = d0 * d0 + d1 * d1 + d2 * d2;

            if (d4 < 1.0D && d4 > 0.01D)
            {
                d4 = 1.0D / Math.sqrt(d4);
                d0 *= d4;
                d1 *= d4;
                d2 *= d4;
                double d5 = d0 * 100.0D;
                double d6 = d1 * 100.0D;
                double d7 = d2 * 100.0D;
                double d8 = Math.atan2(d0, d2);
                double d9 = Math.sin(d8);
                double d10 = Math.cos(d8);
                double d11 = Math.atan2(Math.sqrt(d0 * d0 + d2 * d2), d1);
                double d12 = Math.sin(d11);
                double d13 = Math.cos(d11);
                double d14 = random.nextDouble() * Math.PI * 2.0D;
                double d15 = Math.sin(d14);
                double d16 = Math.cos(d14);

                for (int j = 0; j < 4; ++j)
                {
                    double d17 = 0.0D;
                    double d18 = (double)((j & 2) - 1) * d3;
                    double d19 = (double)((j + 1 & 2) - 1) * d3;
                    double d20 = d18 * d16 - d19 * d15;
                    double d21 = d19 * d16 + d18 * d15;
                    double d22 = d20 * d12 + d17 * d13;
                    double d23 = d17 * d12 - d20 * d13;
                    double d24 = d23 * d9 - d21 * d10;
                    double d25 = d21 * d9 + d23 * d10;
                    tessellator.func_78377_a(d5 + d24, d6 + d22, d7 + d25);
                }
            }
        }

        tessellator.func_78381_a();
    }

    public void func_72732_a(WorldClient p_72732_1_)
    {
        if (this.field_72769_h != null)
        {
            this.field_72769_h.func_72848_b(this);
        }

        this.field_72758_d = -9999.0D;
        this.field_72759_e = -9999.0D;
        this.field_72756_f = -9999.0D;
        this.field_147596_f = -9999.0D;
        this.field_147597_g = -9999.0D;
        this.field_147602_h = -9999.0D;
        this.field_147603_i = -9999;
        this.field_147600_j = -9999;
        this.field_147601_k = -9999;
        RenderManager.field_78727_a.func_78717_a(p_72732_1_);
        this.field_72769_h = p_72732_1_;
        this.field_147592_B = new RenderBlocks(p_72732_1_);

        if (p_72732_1_ != null)
        {
            p_72732_1_.func_72954_a(this);
            this.func_72712_a();
        }
    }

    public void func_72712_a()
    {
        if (this.field_72769_h != null)
        {
            Blocks.field_150362_t.func_150122_b(this.field_72777_q.gameSettings.field_74347_j);
            Blocks.field_150361_u.func_150122_b(this.field_72777_q.gameSettings.field_74347_j);
            this.field_72739_F = this.field_72777_q.gameSettings.field_151451_c;
            int i;

            if (this.field_72765_l != null)
            {
                for (i = 0; i < this.field_72765_l.length; ++i)
                {
                    this.field_72765_l[i].func_78911_c();
                }
            }

            i = this.field_72739_F * 2 + 1;
            this.field_72766_m = i;
            this.field_72763_n = 16;
            this.field_72764_o = i;
            this.field_72765_l = new WorldRenderer[this.field_72766_m * this.field_72763_n * this.field_72764_o];
            this.field_72768_k = new WorldRenderer[this.field_72766_m * this.field_72763_n * this.field_72764_o];
            int j = 0;
            int k = 0;
            this.field_72780_y = 0;
            this.field_72779_z = 0;
            this.field_72741_A = 0;
            this.field_72742_B = this.field_72766_m;
            this.field_72743_C = this.field_72763_n;
            this.field_72737_D = this.field_72764_o;
            int l;

            for (l = 0; l < this.field_72767_j.size(); ++l)
            {
                ((WorldRenderer)this.field_72767_j.get(l)).field_78939_q = false;
            }

            this.field_72767_j.clear();
            this.field_147598_a.clear();
            this.func_147584_b();

            for (l = 0; l < this.field_72766_m; ++l)
            {
                for (int i1 = 0; i1 < this.field_72763_n; ++i1)
                {
                    for (int j1 = 0; j1 < this.field_72764_o; ++j1)
                    {
                        this.field_72765_l[(j1 * this.field_72763_n + i1) * this.field_72766_m + l] = new WorldRenderer(this.field_72769_h, this.field_147598_a, l * 16, i1 * 16, j1 * 16, this.field_72778_p + j);

                        if (this.field_72774_t)
                        {
                            this.field_72765_l[(j1 * this.field_72763_n + i1) * this.field_72766_m + l].field_78934_v = this.field_72775_s.get(k);
                        }

                        this.field_72765_l[(j1 * this.field_72763_n + i1) * this.field_72766_m + l].field_78935_u = false;
                        this.field_72765_l[(j1 * this.field_72763_n + i1) * this.field_72766_m + l].field_78936_t = true;
                        this.field_72765_l[(j1 * this.field_72763_n + i1) * this.field_72766_m + l].field_78927_l = true;
                        this.field_72765_l[(j1 * this.field_72763_n + i1) * this.field_72766_m + l].field_78937_s = k++;
                        this.field_72765_l[(j1 * this.field_72763_n + i1) * this.field_72766_m + l].func_78914_f();
                        this.field_72768_k[(j1 * this.field_72763_n + i1) * this.field_72766_m + l] = this.field_72765_l[(j1 * this.field_72763_n + i1) * this.field_72766_m + l];
                        this.field_72767_j.add(this.field_72765_l[(j1 * this.field_72763_n + i1) * this.field_72766_m + l]);
                        j += 3;
                    }
                }
            }

            if (this.field_72769_h != null)
            {
                EntityLivingBase entitylivingbase = this.field_72777_q.field_71451_h;

                if (entitylivingbase != null)
                {
                    this.func_72722_c(MathHelper.func_76128_c(entitylivingbase.field_70165_t), MathHelper.func_76128_c(entitylivingbase.field_70163_u), MathHelper.func_76128_c(entitylivingbase.field_70161_v));
                    Arrays.sort(this.field_72768_k, new EntitySorter(entitylivingbase));
                }
            }

            this.field_72740_G = 2;
        }
    }

    public void func_147589_a(EntityLivingBase p_147589_1_, ICamera p_147589_2_, float p_147589_3_)
    {
        if (this.field_72740_G > 0)
        {
            --this.field_72740_G;
        }
        else
        {
            double d0 = p_147589_1_.field_70169_q + (p_147589_1_.field_70165_t - p_147589_1_.field_70169_q) * (double)p_147589_3_;
            double d1 = p_147589_1_.field_70167_r + (p_147589_1_.field_70163_u - p_147589_1_.field_70167_r) * (double)p_147589_3_;
            double d2 = p_147589_1_.field_70166_s + (p_147589_1_.field_70161_v - p_147589_1_.field_70166_s) * (double)p_147589_3_;
            this.field_72769_h.field_72984_F.func_76320_a("prepare");
            TileEntityRendererDispatcher.field_147556_a.func_147542_a(this.field_72769_h, this.field_72777_q.func_110434_K(), this.field_72777_q.field_71466_p, this.field_72777_q.field_71451_h, p_147589_3_);
            RenderManager.field_78727_a.func_147938_a(this.field_72769_h, this.field_72777_q.func_110434_K(), this.field_72777_q.field_71466_p, this.field_72777_q.field_71451_h, this.field_72777_q.field_147125_j, this.field_72777_q.gameSettings, p_147589_3_);
            this.field_72748_H = 0;
            this.field_72749_I = 0;
            this.field_72750_J = 0;
            EntityLivingBase entitylivingbase1 = this.field_72777_q.field_71451_h;
            double d3 = entitylivingbase1.field_70142_S + (entitylivingbase1.field_70165_t - entitylivingbase1.field_70142_S) * (double)p_147589_3_;
            double d4 = entitylivingbase1.field_70137_T + (entitylivingbase1.field_70163_u - entitylivingbase1.field_70137_T) * (double)p_147589_3_;
            double d5 = entitylivingbase1.field_70136_U + (entitylivingbase1.field_70161_v - entitylivingbase1.field_70136_U) * (double)p_147589_3_;
            TileEntityRendererDispatcher.field_147554_b = d3;
            TileEntityRendererDispatcher.field_147555_c = d4;
            TileEntityRendererDispatcher.field_147552_d = d5;
            this.field_72769_h.field_72984_F.func_76318_c("staticentities");

            if (this.field_147595_R)
            {
                RenderManager.field_78725_b = 0.0D;
                RenderManager.field_78726_c = 0.0D;
                RenderManager.field_78723_d = 0.0D;
                this.func_147591_f();
            }

            GL11.glMatrixMode(GL11.GL_MODELVIEW);
            GL11.glPushMatrix();
            GL11.glTranslated(-d3, -d4, -d5);
            GL11.glCallList(this.field_147594_S);
            GL11.glPopMatrix();
            RenderManager.field_78725_b = d3;
            RenderManager.field_78726_c = d4;
            RenderManager.field_78723_d = d5;
            this.field_72777_q.field_71460_t.func_78463_b((double)p_147589_3_);
            this.field_72769_h.field_72984_F.func_76318_c("global");
            List list = this.field_72769_h.func_72910_y();
            this.field_72748_H = list.size();
            int i;
            Entity entity;

            for (i = 0; i < this.field_72769_h.field_73007_j.size(); ++i)
            {
                entity = (Entity)this.field_72769_h.field_73007_j.get(i);
                ++this.field_72749_I;

                if (entity.func_145770_h(d0, d1, d2))
                {
                    RenderManager.field_78727_a.func_147937_a(entity, p_147589_3_);
                }
            }

            this.field_72769_h.field_72984_F.func_76318_c("entities");

            for (i = 0; i < list.size(); ++i)
            {
                entity = (Entity)list.get(i);
                boolean flag = entity.func_145770_h(d0, d1, d2) && (entity.field_70158_ak || p_147589_2_.func_78546_a(entity.field_70121_D) || entity.field_70153_n == this.field_72777_q.field_71439_g);

                if (!flag && entity instanceof EntityLiving)
                {
                    EntityLiving entityliving = (EntityLiving)entity;

                    if (entityliving.func_110167_bD() && entityliving.func_110166_bE() != null)
                    {
                        Entity entity1 = entityliving.func_110166_bE();
                        flag = p_147589_2_.func_78546_a(entity1.field_70121_D);
                    }
                }

                if (flag && (entity != this.field_72777_q.field_71451_h || this.field_72777_q.gameSettings.field_74320_O != 0 || this.field_72777_q.field_71451_h.func_70608_bn()) && this.field_72769_h.func_72899_e(MathHelper.func_76128_c(entity.field_70165_t), 0, MathHelper.func_76128_c(entity.field_70161_v)))
                {
                    ++this.field_72749_I;
                    RenderManager.field_78727_a.func_147937_a(entity, p_147589_3_);
                }
            }

            this.field_72769_h.field_72984_F.func_76318_c("blockentities");
            RenderHelper.func_74519_b();

            for (i = 0; i < this.field_147598_a.size(); ++i)
            {
                TileEntityRendererDispatcher.field_147556_a.func_147544_a((TileEntity)this.field_147598_a.get(i), p_147589_3_);
            }

            this.field_72777_q.field_71460_t.func_78483_a((double)p_147589_3_);
            this.field_72769_h.field_72984_F.func_76319_b();
        }
    }

    public String func_72735_c()
    {
        return "C: " + this.field_72746_N + "/" + this.field_72751_K + ". F: " + this.field_72744_L + ", O: " + this.field_72745_M + ", E: " + this.field_72747_O;
    }

    public String func_72723_d()
    {
        return "E: " + this.field_72749_I + "/" + this.field_72748_H + ". B: " + this.field_72750_J + ", I: " + (this.field_72748_H - this.field_72750_J - this.field_72749_I);
    }

    public void func_147584_b()
    {
        this.field_147595_R = true;
    }

    public void func_147591_f()
    {
        this.field_72769_h.field_72984_F.func_76320_a("staticentityrebuild");
        GL11.glPushMatrix();
        GL11.glNewList(this.field_147594_S, GL11.GL_COMPILE);
        List list = this.field_72769_h.func_72910_y();
        this.field_147595_R = false;

        for (int i = 0; i < list.size(); ++i)
        {
            Entity entity = (Entity)list.get(i);

            if (RenderManager.field_78727_a.func_78713_a(entity).func_147905_a())
            {
                this.field_147595_R = this.field_147595_R || !RenderManager.field_78727_a.func_147936_a(entity, 0.0F, true);
            }
        }

        GL11.glEndList();
        GL11.glPopMatrix();
        this.field_72769_h.field_72984_F.func_76319_b();
    }

    private void func_72722_c(int p_72722_1_, int p_72722_2_, int p_72722_3_)
    {
        p_72722_1_ -= 8;
        p_72722_2_ -= 8;
        p_72722_3_ -= 8;
        this.field_72780_y = Integer.MAX_VALUE;
        this.field_72779_z = Integer.MAX_VALUE;
        this.field_72741_A = Integer.MAX_VALUE;
        this.field_72742_B = Integer.MIN_VALUE;
        this.field_72743_C = Integer.MIN_VALUE;
        this.field_72737_D = Integer.MIN_VALUE;
        int l = this.field_72766_m * 16;
        int i1 = l / 2;

        for (int j1 = 0; j1 < this.field_72766_m; ++j1)
        {
            int k1 = j1 * 16;
            int l1 = k1 + i1 - p_72722_1_;

            if (l1 < 0)
            {
                l1 -= l - 1;
            }

            l1 /= l;
            k1 -= l1 * l;

            if (k1 < this.field_72780_y)
            {
                this.field_72780_y = k1;
            }

            if (k1 > this.field_72742_B)
            {
                this.field_72742_B = k1;
            }

            for (int i2 = 0; i2 < this.field_72764_o; ++i2)
            {
                int j2 = i2 * 16;
                int k2 = j2 + i1 - p_72722_3_;

                if (k2 < 0)
                {
                    k2 -= l - 1;
                }

                k2 /= l;
                j2 -= k2 * l;

                if (j2 < this.field_72741_A)
                {
                    this.field_72741_A = j2;
                }

                if (j2 > this.field_72737_D)
                {
                    this.field_72737_D = j2;
                }

                for (int l2 = 0; l2 < this.field_72763_n; ++l2)
                {
                    int i3 = l2 * 16;

                    if (i3 < this.field_72779_z)
                    {
                        this.field_72779_z = i3;
                    }

                    if (i3 > this.field_72743_C)
                    {
                        this.field_72743_C = i3;
                    }

                    WorldRenderer worldrenderer = this.field_72765_l[(i2 * this.field_72763_n + l2) * this.field_72766_m + j1];
                    boolean flag = worldrenderer.field_78939_q;
                    worldrenderer.func_78913_a(k1, i3, j2);

                    if (!flag && worldrenderer.field_78939_q)
                    {
                        this.field_72767_j.add(worldrenderer);
                    }
                }
            }
        }
    }

    public int func_72719_a(EntityLivingBase p_72719_1_, int p_72719_2_, double p_72719_3_)
    {
        this.field_72769_h.field_72984_F.func_76320_a("sortchunks");

        for (int j = 0; j < 10; ++j)
        {
            this.field_72752_Q = (this.field_72752_Q + 1) % this.field_72765_l.length;
            WorldRenderer worldrenderer = this.field_72765_l[this.field_72752_Q];

            if (worldrenderer.field_78939_q && !this.field_72767_j.contains(worldrenderer))
            {
                this.field_72767_j.add(worldrenderer);
            }
        }

        if (this.field_72777_q.gameSettings.field_151451_c != this.field_72739_F)
        {
            this.func_72712_a();
        }

        if (p_72719_2_ == 0)
        {
            this.field_72751_K = 0;
            this.field_72753_P = 0;
            this.field_72744_L = 0;
            this.field_72745_M = 0;
            this.field_72746_N = 0;
            this.field_72747_O = 0;
        }

        double d9 = p_72719_1_.field_70142_S + (p_72719_1_.field_70165_t - p_72719_1_.field_70142_S) * p_72719_3_;
        double d1 = p_72719_1_.field_70137_T + (p_72719_1_.field_70163_u - p_72719_1_.field_70137_T) * p_72719_3_;
        double d2 = p_72719_1_.field_70136_U + (p_72719_1_.field_70161_v - p_72719_1_.field_70136_U) * p_72719_3_;
        double d3 = p_72719_1_.field_70165_t - this.field_72758_d;
        double d4 = p_72719_1_.field_70163_u - this.field_72759_e;
        double d5 = p_72719_1_.field_70161_v - this.field_72756_f;

        if (this.field_147603_i != p_72719_1_.field_70176_ah || this.field_147600_j != p_72719_1_.field_70162_ai || this.field_147601_k != p_72719_1_.field_70164_aj || d3 * d3 + d4 * d4 + d5 * d5 > 16.0D)
        {
            this.field_72758_d = p_72719_1_.field_70165_t;
            this.field_72759_e = p_72719_1_.field_70163_u;
            this.field_72756_f = p_72719_1_.field_70161_v;
            this.field_147603_i = p_72719_1_.field_70176_ah;
            this.field_147600_j = p_72719_1_.field_70162_ai;
            this.field_147601_k = p_72719_1_.field_70164_aj;
            this.func_72722_c(MathHelper.func_76128_c(p_72719_1_.field_70165_t), MathHelper.func_76128_c(p_72719_1_.field_70163_u), MathHelper.func_76128_c(p_72719_1_.field_70161_v));
            Arrays.sort(this.field_72768_k, new EntitySorter(p_72719_1_));
        }

        double d6 = p_72719_1_.field_70165_t - this.field_147596_f;
        double d7 = p_72719_1_.field_70163_u - this.field_147597_g;
        double d8 = p_72719_1_.field_70161_v - this.field_147602_h;
        int k;

        if (d6 * d6 + d7 * d7 + d8 * d8 > 1.0D)
        {
            this.field_147596_f = p_72719_1_.field_70165_t;
            this.field_147597_g = p_72719_1_.field_70163_u;
            this.field_147602_h = p_72719_1_.field_70161_v;

            for (k = 0; k < 27; ++k)
            {
                this.field_72768_k[k].func_147889_b(p_72719_1_);
            }
        }

        RenderHelper.func_74518_a();
        byte b1 = 0;

        if (this.field_72774_t && this.field_72777_q.gameSettings.field_74349_h && !this.field_72777_q.gameSettings.field_74337_g && p_72719_2_ == 0)
        {
            byte b0 = 0;
            int l = 16;
            this.func_72720_a(b0, l);

            for (int i1 = b0; i1 < l; ++i1)
            {
                this.field_72768_k[i1].field_78936_t = true;
            }

            this.field_72769_h.field_72984_F.func_76318_c("render");
            k = b1 + this.func_72724_a(b0, l, p_72719_2_, p_72719_3_);

            do
            {
                this.field_72769_h.field_72984_F.func_76318_c("occ");
                int l1 = l;
                l *= 2;

                if (l > this.field_72768_k.length)
                {
                    l = this.field_72768_k.length;
                }

                GL11.glDisable(GL11.GL_TEXTURE_2D);
                GL11.glDisable(GL11.GL_LIGHTING);
                GL11.glDisable(GL11.GL_ALPHA_TEST);
                GL11.glDisable(GL11.GL_FOG);
                GL11.glColorMask(false, false, false, false);
                GL11.glDepthMask(false);
                this.field_72769_h.field_72984_F.func_76320_a("check");
                this.func_72720_a(l1, l);
                this.field_72769_h.field_72984_F.func_76319_b();
                GL11.glPushMatrix();
                float f9 = 0.0F;
                float f = 0.0F;
                float f1 = 0.0F;

                for (int j1 = l1; j1 < l; ++j1)
                {
                    if (this.field_72768_k[j1].func_78906_e())
                    {
                        this.field_72768_k[j1].field_78927_l = false;
                    }
                    else
                    {
                        if (!this.field_72768_k[j1].field_78927_l)
                        {
                            this.field_72768_k[j1].field_78936_t = true;
                        }

                        if (this.field_72768_k[j1].field_78927_l && !this.field_72768_k[j1].field_78935_u)
                        {
                            float f2 = MathHelper.func_76129_c(this.field_72768_k[j1].func_78912_a(p_72719_1_));
                            int k1 = (int)(1.0F + f2 / 128.0F);

                            if (this.field_72773_u % k1 == j1 % k1)
                            {
                                WorldRenderer worldrenderer1 = this.field_72768_k[j1];
                                float f3 = (float)((double)worldrenderer1.field_78918_f - d9);
                                float f4 = (float)((double)worldrenderer1.field_78919_g - d1);
                                float f5 = (float)((double)worldrenderer1.field_78931_h - d2);
                                float f6 = f3 - f9;
                                float f7 = f4 - f;
                                float f8 = f5 - f1;

                                if (f6 != 0.0F || f7 != 0.0F || f8 != 0.0F)
                                {
                                    GL11.glTranslatef(f6, f7, f8);
                                    f9 += f6;
                                    f += f7;
                                    f1 += f8;
                                }

                                this.field_72769_h.field_72984_F.func_76320_a("bb");
                                ARBOcclusionQuery.glBeginQueryARB(ARBOcclusionQuery.GL_SAMPLES_PASSED_ARB, this.field_72768_k[j1].field_78934_v);
                                this.field_72768_k[j1].func_78904_d();
                                ARBOcclusionQuery.glEndQueryARB(ARBOcclusionQuery.GL_SAMPLES_PASSED_ARB);
                                this.field_72769_h.field_72984_F.func_76319_b();
                                this.field_72768_k[j1].field_78935_u = true;
                            }
                        }
                    }
                }

                GL11.glPopMatrix();

                if (this.field_72777_q.gameSettings.field_74337_g)
                {
                    if (EntityRenderer.field_78515_b == 0)
                    {
                        GL11.glColorMask(false, true, true, true);
                    }
                    else
                    {
                        GL11.glColorMask(true, false, false, true);
                    }
                }
                else
                {
                    GL11.glColorMask(true, true, true, true);
                }

                GL11.glDepthMask(true);
                GL11.glEnable(GL11.GL_TEXTURE_2D);
                GL11.glEnable(GL11.GL_ALPHA_TEST);
                GL11.glEnable(GL11.GL_FOG);
                this.field_72769_h.field_72984_F.func_76318_c("render");
                k += this.func_72724_a(l1, l, p_72719_2_, p_72719_3_);
            }
            while (l < this.field_72768_k.length);
        }
        else
        {
            this.field_72769_h.field_72984_F.func_76318_c("render");
            k = b1 + this.func_72724_a(0, this.field_72768_k.length, p_72719_2_, p_72719_3_);
        }

        this.field_72769_h.field_72984_F.func_76319_b();
        return k;
    }

    private void func_72720_a(int p_72720_1_, int p_72720_2_)
    {
        for (int k = p_72720_1_; k < p_72720_2_; ++k)
        {
            if (this.field_72768_k[k].field_78935_u)
            {
                this.field_72761_c.clear();
                ARBOcclusionQuery.glGetQueryObjectuARB(this.field_72768_k[k].field_78934_v, ARBOcclusionQuery.GL_QUERY_RESULT_AVAILABLE_ARB, this.field_72761_c);

                if (this.field_72761_c.get(0) != 0)
                {
                    this.field_72768_k[k].field_78935_u = false;
                    this.field_72761_c.clear();
                    ARBOcclusionQuery.glGetQueryObjectuARB(this.field_72768_k[k].field_78934_v, ARBOcclusionQuery.GL_QUERY_RESULT_ARB, this.field_72761_c);
                    this.field_72768_k[k].field_78936_t = this.field_72761_c.get(0) != 0;
                }
            }
        }
    }

    private int func_72724_a(int p_72724_1_, int p_72724_2_, int p_72724_3_, double p_72724_4_)
    {
        this.field_72755_R.clear();
        int l = 0;
        int i1 = p_72724_1_;
        int j1 = p_72724_2_;
        byte b0 = 1;

        if (p_72724_3_ == 1)
        {
            i1 = this.field_72768_k.length - 1 - p_72724_1_;
            j1 = this.field_72768_k.length - 1 - p_72724_2_;
            b0 = -1;
        }

        for (int k1 = i1; k1 != j1; k1 += b0)
        {
            if (p_72724_3_ == 0)
            {
                ++this.field_72751_K;

                if (this.field_72768_k[k1].field_78928_m[p_72724_3_])
                {
                    ++this.field_72747_O;
                }
                else if (!this.field_72768_k[k1].field_78927_l)
                {
                    ++this.field_72744_L;
                }
                else if (this.field_72774_t && !this.field_72768_k[k1].field_78936_t)
                {
                    ++this.field_72745_M;
                }
                else
                {
                    ++this.field_72746_N;
                }
            }

            if (!this.field_72768_k[k1].field_78928_m[p_72724_3_] && this.field_72768_k[k1].field_78927_l && (!this.field_72774_t || this.field_72768_k[k1].field_78936_t))
            {
                int l1 = this.field_72768_k[k1].func_78909_a(p_72724_3_);

                if (l1 >= 0)
                {
                    this.field_72755_R.add(this.field_72768_k[k1]);
                    ++l;
                }
            }
        }

        EntityLivingBase entitylivingbase = this.field_72777_q.field_71451_h;
        double d3 = entitylivingbase.field_70142_S + (entitylivingbase.field_70165_t - entitylivingbase.field_70142_S) * p_72724_4_;
        double d1 = entitylivingbase.field_70137_T + (entitylivingbase.field_70163_u - entitylivingbase.field_70137_T) * p_72724_4_;
        double d2 = entitylivingbase.field_70136_U + (entitylivingbase.field_70161_v - entitylivingbase.field_70136_U) * p_72724_4_;
        int i2 = 0;
        int j2;

        for (j2 = 0; j2 < this.field_72754_S.length; ++j2)
        {
            this.field_72754_S[j2].func_78421_b();
        }

        int k2;
        int l2;

        for (j2 = 0; j2 < this.field_72755_R.size(); ++j2)
        {
            WorldRenderer worldrenderer = (WorldRenderer)this.field_72755_R.get(j2);
            k2 = -1;

            for (l2 = 0; l2 < i2; ++l2)
            {
                if (this.field_72754_S[l2].func_78418_a(worldrenderer.field_78918_f, worldrenderer.field_78919_g, worldrenderer.field_78931_h))
                {
                    k2 = l2;
                }
            }

            if (k2 < 0)
            {
                k2 = i2++;
                this.field_72754_S[k2].func_78422_a(worldrenderer.field_78918_f, worldrenderer.field_78919_g, worldrenderer.field_78931_h, d3, d1, d2);
            }

            this.field_72754_S[k2].func_78420_a(worldrenderer.func_78909_a(p_72724_3_));
        }

        j2 = MathHelper.func_76128_c(d3);
        int i3 = MathHelper.func_76128_c(d2);
        k2 = j2 - (j2 & 1023);
        l2 = i3 - (i3 & 1023);
        Arrays.sort(this.field_72754_S, new RenderDistanceSorter(k2, l2));
        this.func_72733_a(p_72724_3_, p_72724_4_);
        return l;
    }

    public void func_72733_a(int p_72733_1_, double p_72733_2_)
    {
        this.field_72777_q.field_71460_t.func_78463_b(p_72733_2_);

        for (int j = 0; j < this.field_72754_S.length; ++j)
        {
            this.field_72754_S[j].func_78419_a();
        }

        this.field_72777_q.field_71460_t.func_78483_a(p_72733_2_);
    }

    public void func_72734_e()
    {
        ++this.field_72773_u;

        if (this.field_72773_u % 20 == 0)
        {
            Iterator iterator = this.field_72738_E.values().iterator();

            while (iterator.hasNext())
            {
                DestroyBlockProgress destroyblockprogress = (DestroyBlockProgress)iterator.next();
                int i = destroyblockprogress.func_82743_f();

                if (this.field_72773_u - i > 400)
                {
                    iterator.remove();
                }
            }
        }
    }

    public void func_72714_a(float p_72714_1_)
    {
        if (this.field_72777_q.field_71441_e.field_73011_w.field_76574_g == 1)
        {
            GL11.glDisable(GL11.GL_FOG);
            GL11.glDisable(GL11.GL_ALPHA_TEST);
            GL11.glEnable(GL11.GL_BLEND);
            OpenGlHelper.func_148821_a(770, 771, 1, 0);
            RenderHelper.func_74518_a();
            GL11.glDepthMask(false);
            this.field_72770_i.func_110577_a(field_110926_k);
            Tessellator tessellator = Tessellator.field_78398_a;

            for (int i = 0; i < 6; ++i)
            {
                GL11.glPushMatrix();

                if (i == 1)
                {
                    GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
                }

                if (i == 2)
                {
                    GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                }

                if (i == 3)
                {
                    GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
                }

                if (i == 4)
                {
                    GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                }

                if (i == 5)
                {
                    GL11.glRotatef(-90.0F, 0.0F, 0.0F, 1.0F);
                }

                tessellator.func_78382_b();
                tessellator.func_78378_d(2631720);
                tessellator.func_78374_a(-100.0D, -100.0D, -100.0D, 0.0D, 0.0D);
                tessellator.func_78374_a(-100.0D, -100.0D, 100.0D, 0.0D, 16.0D);
                tessellator.func_78374_a(100.0D, -100.0D, 100.0D, 16.0D, 16.0D);
                tessellator.func_78374_a(100.0D, -100.0D, -100.0D, 16.0D, 0.0D);
                tessellator.func_78381_a();
                GL11.glPopMatrix();
            }

            GL11.glDepthMask(true);
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glEnable(GL11.GL_ALPHA_TEST);
        }
        else if (this.field_72777_q.field_71441_e.field_73011_w.func_76569_d())
        {
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            Vec3 vec3 = this.field_72769_h.func_72833_a(this.field_72777_q.field_71451_h, p_72714_1_);
            float f1 = (float)vec3.field_72450_a;
            float f2 = (float)vec3.field_72448_b;
            float f3 = (float)vec3.field_72449_c;
            float f6;

            if (this.field_72777_q.gameSettings.field_74337_g)
            {
                float f4 = (f1 * 30.0F + f2 * 59.0F + f3 * 11.0F) / 100.0F;
                float f5 = (f1 * 30.0F + f2 * 70.0F) / 100.0F;
                f6 = (f1 * 30.0F + f3 * 70.0F) / 100.0F;
                f1 = f4;
                f2 = f5;
                f3 = f6;
            }

            GL11.glColor3f(f1, f2, f3);
            Tessellator tessellator1 = Tessellator.field_78398_a;
            GL11.glDepthMask(false);
            GL11.glEnable(GL11.GL_FOG);
            GL11.glColor3f(f1, f2, f3);
            GL11.glCallList(this.field_72771_w);
            GL11.glDisable(GL11.GL_FOG);
            GL11.glDisable(GL11.GL_ALPHA_TEST);
            GL11.glEnable(GL11.GL_BLEND);
            OpenGlHelper.func_148821_a(770, 771, 1, 0);
            RenderHelper.func_74518_a();
            float[] afloat = this.field_72769_h.field_73011_w.func_76560_a(this.field_72769_h.func_72826_c(p_72714_1_), p_72714_1_);
            float f7;
            float f8;
            float f9;
            float f10;

            if (afloat != null)
            {
                GL11.glDisable(GL11.GL_TEXTURE_2D);
                GL11.glShadeModel(GL11.GL_SMOOTH);
                GL11.glPushMatrix();
                GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(MathHelper.func_76126_a(this.field_72769_h.func_72929_e(p_72714_1_)) < 0.0F ? 180.0F : 0.0F, 0.0F, 0.0F, 1.0F);
                GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                f6 = afloat[0];
                f7 = afloat[1];
                f8 = afloat[2];
                float f11;

                if (this.field_72777_q.gameSettings.field_74337_g)
                {
                    f9 = (f6 * 30.0F + f7 * 59.0F + f8 * 11.0F) / 100.0F;
                    f10 = (f6 * 30.0F + f7 * 70.0F) / 100.0F;
                    f11 = (f6 * 30.0F + f8 * 70.0F) / 100.0F;
                    f6 = f9;
                    f7 = f10;
                    f8 = f11;
                }

                tessellator1.func_78371_b(6);
                tessellator1.func_78369_a(f6, f7, f8, afloat[3]);
                tessellator1.func_78377_a(0.0D, 100.0D, 0.0D);
                byte b0 = 16;
                tessellator1.func_78369_a(afloat[0], afloat[1], afloat[2], 0.0F);

                for (int j = 0; j <= b0; ++j)
                {
                    f11 = (float)j * (float)Math.PI * 2.0F / (float)b0;
                    float f12 = MathHelper.func_76126_a(f11);
                    float f13 = MathHelper.func_76134_b(f11);
                    tessellator1.func_78377_a((double)(f12 * 120.0F), (double)(f13 * 120.0F), (double)(-f13 * 40.0F * afloat[3]));
                }

                tessellator1.func_78381_a();
                GL11.glPopMatrix();
                GL11.glShadeModel(GL11.GL_FLAT);
            }

            GL11.glEnable(GL11.GL_TEXTURE_2D);
            OpenGlHelper.func_148821_a(770, 1, 1, 0);
            GL11.glPushMatrix();
            f6 = 1.0F - this.field_72769_h.func_72867_j(p_72714_1_);
            f7 = 0.0F;
            f8 = 0.0F;
            f9 = 0.0F;
            GL11.glColor4f(1.0F, 1.0F, 1.0F, f6);
            GL11.glTranslatef(f7, f8, f9);
            GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(this.field_72769_h.func_72826_c(p_72714_1_) * 360.0F, 1.0F, 0.0F, 0.0F);
            f10 = 30.0F;
            this.field_72770_i.func_110577_a(field_110928_i);
            tessellator1.func_78382_b();
            tessellator1.func_78374_a((double)(-f10), 100.0D, (double)(-f10), 0.0D, 0.0D);
            tessellator1.func_78374_a((double)f10, 100.0D, (double)(-f10), 1.0D, 0.0D);
            tessellator1.func_78374_a((double)f10, 100.0D, (double)f10, 1.0D, 1.0D);
            tessellator1.func_78374_a((double)(-f10), 100.0D, (double)f10, 0.0D, 1.0D);
            tessellator1.func_78381_a();
            f10 = 20.0F;
            this.field_72770_i.func_110577_a(field_110927_h);
            int k = this.field_72769_h.func_72853_d();
            int l = k % 4;
            int i1 = k / 4 % 2;
            float f14 = (float)(l + 0) / 4.0F;
            float f15 = (float)(i1 + 0) / 2.0F;
            float f16 = (float)(l + 1) / 4.0F;
            float f17 = (float)(i1 + 1) / 2.0F;
            tessellator1.func_78382_b();
            tessellator1.func_78374_a((double)(-f10), -100.0D, (double)f10, (double)f16, (double)f17);
            tessellator1.func_78374_a((double)f10, -100.0D, (double)f10, (double)f14, (double)f17);
            tessellator1.func_78374_a((double)f10, -100.0D, (double)(-f10), (double)f14, (double)f15);
            tessellator1.func_78374_a((double)(-f10), -100.0D, (double)(-f10), (double)f16, (double)f15);
            tessellator1.func_78381_a();
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            float f18 = this.field_72769_h.func_72880_h(p_72714_1_) * f6;

            if (f18 > 0.0F)
            {
                GL11.glColor4f(f18, f18, f18, f18);
                GL11.glCallList(this.field_72772_v);
            }

            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glEnable(GL11.GL_ALPHA_TEST);
            GL11.glEnable(GL11.GL_FOG);
            GL11.glPopMatrix();
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glColor3f(0.0F, 0.0F, 0.0F);
            double d0 = this.field_72777_q.field_71439_g.func_70666_h(p_72714_1_).field_72448_b - this.field_72769_h.func_72919_O();

            if (d0 < 0.0D)
            {
                GL11.glPushMatrix();
                GL11.glTranslatef(0.0F, 12.0F, 0.0F);
                GL11.glCallList(this.field_72781_x);
                GL11.glPopMatrix();
                f8 = 1.0F;
                f9 = -((float)(d0 + 65.0D));
                f10 = -f8;
                tessellator1.func_78382_b();
                tessellator1.func_78384_a(0, 255);
                tessellator1.func_78377_a((double)(-f8), (double)f9, (double)f8);
                tessellator1.func_78377_a((double)f8, (double)f9, (double)f8);
                tessellator1.func_78377_a((double)f8, (double)f10, (double)f8);
                tessellator1.func_78377_a((double)(-f8), (double)f10, (double)f8);
                tessellator1.func_78377_a((double)(-f8), (double)f10, (double)(-f8));
                tessellator1.func_78377_a((double)f8, (double)f10, (double)(-f8));
                tessellator1.func_78377_a((double)f8, (double)f9, (double)(-f8));
                tessellator1.func_78377_a((double)(-f8), (double)f9, (double)(-f8));
                tessellator1.func_78377_a((double)f8, (double)f10, (double)(-f8));
                tessellator1.func_78377_a((double)f8, (double)f10, (double)f8);
                tessellator1.func_78377_a((double)f8, (double)f9, (double)f8);
                tessellator1.func_78377_a((double)f8, (double)f9, (double)(-f8));
                tessellator1.func_78377_a((double)(-f8), (double)f9, (double)(-f8));
                tessellator1.func_78377_a((double)(-f8), (double)f9, (double)f8);
                tessellator1.func_78377_a((double)(-f8), (double)f10, (double)f8);
                tessellator1.func_78377_a((double)(-f8), (double)f10, (double)(-f8));
                tessellator1.func_78377_a((double)(-f8), (double)f10, (double)(-f8));
                tessellator1.func_78377_a((double)(-f8), (double)f10, (double)f8);
                tessellator1.func_78377_a((double)f8, (double)f10, (double)f8);
                tessellator1.func_78377_a((double)f8, (double)f10, (double)(-f8));
                tessellator1.func_78381_a();
            }

            if (this.field_72769_h.field_73011_w.func_76561_g())
            {
                GL11.glColor3f(f1 * 0.2F + 0.04F, f2 * 0.2F + 0.04F, f3 * 0.6F + 0.1F);
            }
            else
            {
                GL11.glColor3f(f1, f2, f3);
            }

            GL11.glPushMatrix();
            GL11.glTranslatef(0.0F, -((float)(d0 - 16.0D)), 0.0F);
            GL11.glCallList(this.field_72781_x);
            GL11.glPopMatrix();
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glDepthMask(true);
        }
    }

    public void func_72718_b(float p_72718_1_)
    {
        if (this.field_72777_q.field_71441_e.field_73011_w.func_76569_d())
        {
            if (this.field_72777_q.gameSettings.field_74347_j)
            {
                this.func_72736_c(p_72718_1_);
            }
            else
            {
                GL11.glDisable(GL11.GL_CULL_FACE);
                float f1 = (float)(this.field_72777_q.field_71451_h.field_70137_T + (this.field_72777_q.field_71451_h.field_70163_u - this.field_72777_q.field_71451_h.field_70137_T) * (double)p_72718_1_);
                byte b0 = 32;
                int i = 256 / b0;
                Tessellator tessellator = Tessellator.field_78398_a;
                this.field_72770_i.func_110577_a(field_110925_j);
                GL11.glEnable(GL11.GL_BLEND);
                OpenGlHelper.func_148821_a(770, 771, 1, 0);
                Vec3 vec3 = this.field_72769_h.func_72824_f(p_72718_1_);
                float f2 = (float)vec3.field_72450_a;
                float f3 = (float)vec3.field_72448_b;
                float f4 = (float)vec3.field_72449_c;
                float f5;

                if (this.field_72777_q.gameSettings.field_74337_g)
                {
                    f5 = (f2 * 30.0F + f3 * 59.0F + f4 * 11.0F) / 100.0F;
                    float f6 = (f2 * 30.0F + f3 * 70.0F) / 100.0F;
                    float f7 = (f2 * 30.0F + f4 * 70.0F) / 100.0F;
                    f2 = f5;
                    f3 = f6;
                    f4 = f7;
                }

                f5 = 4.8828125E-4F;
                double d2 = (double)((float)this.field_72773_u + p_72718_1_);
                double d0 = this.field_72777_q.field_71451_h.field_70169_q + (this.field_72777_q.field_71451_h.field_70165_t - this.field_72777_q.field_71451_h.field_70169_q) * (double)p_72718_1_ + d2 * 0.029999999329447746D;
                double d1 = this.field_72777_q.field_71451_h.field_70166_s + (this.field_72777_q.field_71451_h.field_70161_v - this.field_72777_q.field_71451_h.field_70166_s) * (double)p_72718_1_;
                int j = MathHelper.func_76128_c(d0 / 2048.0D);
                int k = MathHelper.func_76128_c(d1 / 2048.0D);
                d0 -= (double)(j * 2048);
                d1 -= (double)(k * 2048);
                float f8 = this.field_72769_h.field_73011_w.func_76571_f() - f1 + 0.33F;
                float f9 = (float)(d0 * (double)f5);
                float f10 = (float)(d1 * (double)f5);
                tessellator.func_78382_b();
                tessellator.func_78369_a(f2, f3, f4, 0.8F);

                for (int l = -b0 * i; l < b0 * i; l += b0)
                {
                    for (int i1 = -b0 * i; i1 < b0 * i; i1 += b0)
                    {
                        tessellator.func_78374_a((double)(l + 0), (double)f8, (double)(i1 + b0), (double)((float)(l + 0) * f5 + f9), (double)((float)(i1 + b0) * f5 + f10));
                        tessellator.func_78374_a((double)(l + b0), (double)f8, (double)(i1 + b0), (double)((float)(l + b0) * f5 + f9), (double)((float)(i1 + b0) * f5 + f10));
                        tessellator.func_78374_a((double)(l + b0), (double)f8, (double)(i1 + 0), (double)((float)(l + b0) * f5 + f9), (double)((float)(i1 + 0) * f5 + f10));
                        tessellator.func_78374_a((double)(l + 0), (double)f8, (double)(i1 + 0), (double)((float)(l + 0) * f5 + f9), (double)((float)(i1 + 0) * f5 + f10));
                    }
                }

                tessellator.func_78381_a();
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                GL11.glDisable(GL11.GL_BLEND);
                GL11.glEnable(GL11.GL_CULL_FACE);
            }
        }
    }

    public boolean func_72721_a(double p_72721_1_, double p_72721_3_, double p_72721_5_, float p_72721_7_)
    {
        return false;
    }

    public void func_72736_c(float p_72736_1_)
    {
        GL11.glDisable(GL11.GL_CULL_FACE);
        float f1 = (float)(this.field_72777_q.field_71451_h.field_70137_T + (this.field_72777_q.field_71451_h.field_70163_u - this.field_72777_q.field_71451_h.field_70137_T) * (double)p_72736_1_);
        Tessellator tessellator = Tessellator.field_78398_a;
        float f2 = 12.0F;
        float f3 = 4.0F;
        double d0 = (double)((float)this.field_72773_u + p_72736_1_);
        double d1 = (this.field_72777_q.field_71451_h.field_70169_q + (this.field_72777_q.field_71451_h.field_70165_t - this.field_72777_q.field_71451_h.field_70169_q) * (double)p_72736_1_ + d0 * 0.029999999329447746D) / (double)f2;
        double d2 = (this.field_72777_q.field_71451_h.field_70166_s + (this.field_72777_q.field_71451_h.field_70161_v - this.field_72777_q.field_71451_h.field_70166_s) * (double)p_72736_1_) / (double)f2 + 0.33000001311302185D;
        float f4 = this.field_72769_h.field_73011_w.func_76571_f() - f1 + 0.33F;
        int i = MathHelper.func_76128_c(d1 / 2048.0D);
        int j = MathHelper.func_76128_c(d2 / 2048.0D);
        d1 -= (double)(i * 2048);
        d2 -= (double)(j * 2048);
        this.field_72770_i.func_110577_a(field_110925_j);
        GL11.glEnable(GL11.GL_BLEND);
        OpenGlHelper.func_148821_a(770, 771, 1, 0);
        Vec3 vec3 = this.field_72769_h.func_72824_f(p_72736_1_);
        float f5 = (float)vec3.field_72450_a;
        float f6 = (float)vec3.field_72448_b;
        float f7 = (float)vec3.field_72449_c;
        float f8;
        float f9;
        float f10;

        if (this.field_72777_q.gameSettings.field_74337_g)
        {
            f8 = (f5 * 30.0F + f6 * 59.0F + f7 * 11.0F) / 100.0F;
            f9 = (f5 * 30.0F + f6 * 70.0F) / 100.0F;
            f10 = (f5 * 30.0F + f7 * 70.0F) / 100.0F;
            f5 = f8;
            f6 = f9;
            f7 = f10;
        }

        f8 = (float)(d1 * 0.0D);
        f9 = (float)(d2 * 0.0D);
        f10 = 0.00390625F;
        f8 = (float)MathHelper.func_76128_c(d1) * f10;
        f9 = (float)MathHelper.func_76128_c(d2) * f10;
        float f11 = (float)(d1 - (double)MathHelper.func_76128_c(d1));
        float f12 = (float)(d2 - (double)MathHelper.func_76128_c(d2));
        byte b0 = 8;
        byte b1 = 4;
        float f13 = 9.765625E-4F;
        GL11.glScalef(f2, 1.0F, f2);

        for (int k = 0; k < 2; ++k)
        {
            if (k == 0)
            {
                GL11.glColorMask(false, false, false, false);
            }
            else if (this.field_72777_q.gameSettings.field_74337_g)
            {
                if (EntityRenderer.field_78515_b == 0)
                {
                    GL11.glColorMask(false, true, true, true);
                }
                else
                {
                    GL11.glColorMask(true, false, false, true);
                }
            }
            else
            {
                GL11.glColorMask(true, true, true, true);
            }

            for (int l = -b1 + 1; l <= b1; ++l)
            {
                for (int i1 = -b1 + 1; i1 <= b1; ++i1)
                {
                    tessellator.func_78382_b();
                    float f14 = (float)(l * b0);
                    float f15 = (float)(i1 * b0);
                    float f16 = f14 - f11;
                    float f17 = f15 - f12;

                    if (f4 > -f3 - 1.0F)
                    {
                        tessellator.func_78369_a(f5 * 0.7F, f6 * 0.7F, f7 * 0.7F, 0.8F);
                        tessellator.func_78375_b(0.0F, -1.0F, 0.0F);
                        tessellator.func_78374_a((double)(f16 + 0.0F), (double)(f4 + 0.0F), (double)(f17 + (float)b0), (double)((f14 + 0.0F) * f10 + f8), (double)((f15 + (float)b0) * f10 + f9));
                        tessellator.func_78374_a((double)(f16 + (float)b0), (double)(f4 + 0.0F), (double)(f17 + (float)b0), (double)((f14 + (float)b0) * f10 + f8), (double)((f15 + (float)b0) * f10 + f9));
                        tessellator.func_78374_a((double)(f16 + (float)b0), (double)(f4 + 0.0F), (double)(f17 + 0.0F), (double)((f14 + (float)b0) * f10 + f8), (double)((f15 + 0.0F) * f10 + f9));
                        tessellator.func_78374_a((double)(f16 + 0.0F), (double)(f4 + 0.0F), (double)(f17 + 0.0F), (double)((f14 + 0.0F) * f10 + f8), (double)((f15 + 0.0F) * f10 + f9));
                    }

                    if (f4 <= f3 + 1.0F)
                    {
                        tessellator.func_78369_a(f5, f6, f7, 0.8F);
                        tessellator.func_78375_b(0.0F, 1.0F, 0.0F);
                        tessellator.func_78374_a((double)(f16 + 0.0F), (double)(f4 + f3 - f13), (double)(f17 + (float)b0), (double)((f14 + 0.0F) * f10 + f8), (double)((f15 + (float)b0) * f10 + f9));
                        tessellator.func_78374_a((double)(f16 + (float)b0), (double)(f4 + f3 - f13), (double)(f17 + (float)b0), (double)((f14 + (float)b0) * f10 + f8), (double)((f15 + (float)b0) * f10 + f9));
                        tessellator.func_78374_a((double)(f16 + (float)b0), (double)(f4 + f3 - f13), (double)(f17 + 0.0F), (double)((f14 + (float)b0) * f10 + f8), (double)((f15 + 0.0F) * f10 + f9));
                        tessellator.func_78374_a((double)(f16 + 0.0F), (double)(f4 + f3 - f13), (double)(f17 + 0.0F), (double)((f14 + 0.0F) * f10 + f8), (double)((f15 + 0.0F) * f10 + f9));
                    }

                    tessellator.func_78369_a(f5 * 0.9F, f6 * 0.9F, f7 * 0.9F, 0.8F);
                    int j1;

                    if (l > -1)
                    {
                        tessellator.func_78375_b(-1.0F, 0.0F, 0.0F);

                        for (j1 = 0; j1 < b0; ++j1)
                        {
                            tessellator.func_78374_a((double)(f16 + (float)j1 + 0.0F), (double)(f4 + 0.0F), (double)(f17 + (float)b0), (double)((f14 + (float)j1 + 0.5F) * f10 + f8), (double)((f15 + (float)b0) * f10 + f9));
                            tessellator.func_78374_a((double)(f16 + (float)j1 + 0.0F), (double)(f4 + f3), (double)(f17 + (float)b0), (double)((f14 + (float)j1 + 0.5F) * f10 + f8), (double)((f15 + (float)b0) * f10 + f9));
                            tessellator.func_78374_a((double)(f16 + (float)j1 + 0.0F), (double)(f4 + f3), (double)(f17 + 0.0F), (double)((f14 + (float)j1 + 0.5F) * f10 + f8), (double)((f15 + 0.0F) * f10 + f9));
                            tessellator.func_78374_a((double)(f16 + (float)j1 + 0.0F), (double)(f4 + 0.0F), (double)(f17 + 0.0F), (double)((f14 + (float)j1 + 0.5F) * f10 + f8), (double)((f15 + 0.0F) * f10 + f9));
                        }
                    }

                    if (l <= 1)
                    {
                        tessellator.func_78375_b(1.0F, 0.0F, 0.0F);

                        for (j1 = 0; j1 < b0; ++j1)
                        {
                            tessellator.func_78374_a((double)(f16 + (float)j1 + 1.0F - f13), (double)(f4 + 0.0F), (double)(f17 + (float)b0), (double)((f14 + (float)j1 + 0.5F) * f10 + f8), (double)((f15 + (float)b0) * f10 + f9));
                            tessellator.func_78374_a((double)(f16 + (float)j1 + 1.0F - f13), (double)(f4 + f3), (double)(f17 + (float)b0), (double)((f14 + (float)j1 + 0.5F) * f10 + f8), (double)((f15 + (float)b0) * f10 + f9));
                            tessellator.func_78374_a((double)(f16 + (float)j1 + 1.0F - f13), (double)(f4 + f3), (double)(f17 + 0.0F), (double)((f14 + (float)j1 + 0.5F) * f10 + f8), (double)((f15 + 0.0F) * f10 + f9));
                            tessellator.func_78374_a((double)(f16 + (float)j1 + 1.0F - f13), (double)(f4 + 0.0F), (double)(f17 + 0.0F), (double)((f14 + (float)j1 + 0.5F) * f10 + f8), (double)((f15 + 0.0F) * f10 + f9));
                        }
                    }

                    tessellator.func_78369_a(f5 * 0.8F, f6 * 0.8F, f7 * 0.8F, 0.8F);

                    if (i1 > -1)
                    {
                        tessellator.func_78375_b(0.0F, 0.0F, -1.0F);

                        for (j1 = 0; j1 < b0; ++j1)
                        {
                            tessellator.func_78374_a((double)(f16 + 0.0F), (double)(f4 + f3), (double)(f17 + (float)j1 + 0.0F), (double)((f14 + 0.0F) * f10 + f8), (double)((f15 + (float)j1 + 0.5F) * f10 + f9));
                            tessellator.func_78374_a((double)(f16 + (float)b0), (double)(f4 + f3), (double)(f17 + (float)j1 + 0.0F), (double)((f14 + (float)b0) * f10 + f8), (double)((f15 + (float)j1 + 0.5F) * f10 + f9));
                            tessellator.func_78374_a((double)(f16 + (float)b0), (double)(f4 + 0.0F), (double)(f17 + (float)j1 + 0.0F), (double)((f14 + (float)b0) * f10 + f8), (double)((f15 + (float)j1 + 0.5F) * f10 + f9));
                            tessellator.func_78374_a((double)(f16 + 0.0F), (double)(f4 + 0.0F), (double)(f17 + (float)j1 + 0.0F), (double)((f14 + 0.0F) * f10 + f8), (double)((f15 + (float)j1 + 0.5F) * f10 + f9));
                        }
                    }

                    if (i1 <= 1)
                    {
                        tessellator.func_78375_b(0.0F, 0.0F, 1.0F);

                        for (j1 = 0; j1 < b0; ++j1)
                        {
                            tessellator.func_78374_a((double)(f16 + 0.0F), (double)(f4 + f3), (double)(f17 + (float)j1 + 1.0F - f13), (double)((f14 + 0.0F) * f10 + f8), (double)((f15 + (float)j1 + 0.5F) * f10 + f9));
                            tessellator.func_78374_a((double)(f16 + (float)b0), (double)(f4 + f3), (double)(f17 + (float)j1 + 1.0F - f13), (double)((f14 + (float)b0) * f10 + f8), (double)((f15 + (float)j1 + 0.5F) * f10 + f9));
                            tessellator.func_78374_a((double)(f16 + (float)b0), (double)(f4 + 0.0F), (double)(f17 + (float)j1 + 1.0F - f13), (double)((f14 + (float)b0) * f10 + f8), (double)((f15 + (float)j1 + 0.5F) * f10 + f9));
                            tessellator.func_78374_a((double)(f16 + 0.0F), (double)(f4 + 0.0F), (double)(f17 + (float)j1 + 1.0F - f13), (double)((f14 + 0.0F) * f10 + f8), (double)((f15 + (float)j1 + 0.5F) * f10 + f9));
                        }
                    }

                    tessellator.func_78381_a();
                }
            }
        }

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_CULL_FACE);
    }

    public boolean func_72716_a(EntityLivingBase p_72716_1_, boolean p_72716_2_)
    {
        byte b0 = 2;
        RenderSorter rendersorter = new RenderSorter(p_72716_1_);
        WorldRenderer[] aworldrenderer = new WorldRenderer[b0];
        ArrayList arraylist = null;
        int i = this.field_72767_j.size();
        int j = 0;
        this.field_72769_h.field_72984_F.func_76320_a("nearChunksSearch");
        int k;
        WorldRenderer worldrenderer;
        int l;
        int i1;
        label136:

        for (k = 0; k < i; ++k)
        {
            worldrenderer = (WorldRenderer)this.field_72767_j.get(k);

            if (worldrenderer != null)
            {
                if (!p_72716_2_)
                {
                    if (worldrenderer.func_78912_a(p_72716_1_) > 272.0F)
                    {
                        for (l = 0; l < b0 && (aworldrenderer[l] == null || rendersorter.compare(aworldrenderer[l], worldrenderer) <= 0); ++l)
                        {
                            ;
                        }

                        --l;

                        if (l > 0)
                        {
                            i1 = l;

                            while (true)
                            {
                                --i1;

                                if (i1 == 0)
                                {
                                    aworldrenderer[l] = worldrenderer;
                                    continue label136;
                                }

                                aworldrenderer[i1 - 1] = aworldrenderer[i1];
                            }
                        }

                        continue;
                    }
                }
                else if (!worldrenderer.field_78927_l)
                {
                    continue;
                }

                if (arraylist == null)
                {
                    arraylist = new ArrayList();
                }

                ++j;
                arraylist.add(worldrenderer);
                this.field_72767_j.set(k, (Object)null);
            }
        }

        this.field_72769_h.field_72984_F.func_76319_b();
        this.field_72769_h.field_72984_F.func_76320_a("sort");

        if (arraylist != null)
        {
            if (arraylist.size() > 1)
            {
                Collections.sort(arraylist, rendersorter);
            }

            for (k = arraylist.size() - 1; k >= 0; --k)
            {
                worldrenderer = (WorldRenderer)arraylist.get(k);
                worldrenderer.func_147892_a(p_72716_1_);
                worldrenderer.field_78939_q = false;
            }
        }

        this.field_72769_h.field_72984_F.func_76319_b();
        k = 0;
        this.field_72769_h.field_72984_F.func_76320_a("rebuild");
        int k1;

        for (k1 = b0 - 1; k1 >= 0; --k1)
        {
            WorldRenderer worldrenderer2 = aworldrenderer[k1];

            if (worldrenderer2 != null)
            {
                if (!worldrenderer2.field_78927_l && k1 != b0 - 1)
                {
                    aworldrenderer[k1] = null;
                    aworldrenderer[0] = null;
                    break;
                }

                aworldrenderer[k1].func_147892_a(p_72716_1_);
                aworldrenderer[k1].field_78939_q = false;
                ++k;
            }
        }

        this.field_72769_h.field_72984_F.func_76319_b();
        this.field_72769_h.field_72984_F.func_76320_a("cleanup");
        k1 = 0;
        l = 0;

        for (i1 = this.field_72767_j.size(); k1 != i1; ++k1)
        {
            WorldRenderer worldrenderer1 = (WorldRenderer)this.field_72767_j.get(k1);

            if (worldrenderer1 != null)
            {
                boolean flag1 = false;

                for (int j1 = 0; j1 < b0 && !flag1; ++j1)
                {
                    if (worldrenderer1 == aworldrenderer[j1])
                    {
                        flag1 = true;
                    }
                }

                if (!flag1)
                {
                    if (l != k1)
                    {
                        this.field_72767_j.set(l, worldrenderer1);
                    }

                    ++l;
                }
            }
        }

        this.field_72769_h.field_72984_F.func_76319_b();
        this.field_72769_h.field_72984_F.func_76320_a("trim");

        while (true)
        {
            --k1;

            if (k1 < l)
            {
                this.field_72769_h.field_72984_F.func_76319_b();
                return i == j + k;
            }

            this.field_72767_j.remove(k1);
        }
    }

    public void func_72717_a(Tessellator p_72717_1_, EntityPlayer p_72717_2_, float p_72717_3_)
    {
        double d0 = p_72717_2_.field_70142_S + (p_72717_2_.field_70165_t - p_72717_2_.field_70142_S) * (double)p_72717_3_;
        double d1 = p_72717_2_.field_70137_T + (p_72717_2_.field_70163_u - p_72717_2_.field_70137_T) * (double)p_72717_3_;
        double d2 = p_72717_2_.field_70136_U + (p_72717_2_.field_70161_v - p_72717_2_.field_70136_U) * (double)p_72717_3_;

        if (!this.field_72738_E.isEmpty())
        {
            OpenGlHelper.func_148821_a(774, 768, 1, 0);
            this.field_72770_i.func_110577_a(TextureMap.field_110575_b);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.5F);
            GL11.glPushMatrix();
            GL11.glPolygonOffset(-3.0F, -3.0F);
            GL11.glEnable(GL11.GL_POLYGON_OFFSET_FILL);
            GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
            GL11.glEnable(GL11.GL_ALPHA_TEST);
            p_72717_1_.func_78382_b();
            p_72717_1_.func_78373_b(-d0, -d1, -d2);
            p_72717_1_.func_78383_c();
            Iterator iterator = this.field_72738_E.values().iterator();

            while (iterator.hasNext())
            {
                DestroyBlockProgress destroyblockprogress = (DestroyBlockProgress)iterator.next();
                double d3 = (double)destroyblockprogress.func_73110_b() - d0;
                double d4 = (double)destroyblockprogress.func_73109_c() - d1;
                double d5 = (double)destroyblockprogress.func_73108_d() - d2;

                if (d3 * d3 + d4 * d4 + d5 * d5 > 1024.0D)
                {
                    iterator.remove();
                }
                else
                {
                    Block block = this.field_72769_h.func_147439_a(destroyblockprogress.func_73110_b(), destroyblockprogress.func_73109_c(), destroyblockprogress.func_73108_d());

                    if (block.func_149688_o() != Material.field_151579_a)
                    {
                        this.field_147592_B.func_147792_a(block, destroyblockprogress.func_73110_b(), destroyblockprogress.func_73109_c(), destroyblockprogress.func_73108_d(), this.field_94141_F[destroyblockprogress.func_73106_e()]);
                    }
                }
            }

            p_72717_1_.func_78381_a();
            p_72717_1_.func_78373_b(0.0D, 0.0D, 0.0D);
            GL11.glDisable(GL11.GL_ALPHA_TEST);
            GL11.glPolygonOffset(0.0F, 0.0F);
            GL11.glDisable(GL11.GL_POLYGON_OFFSET_FILL);
            GL11.glEnable(GL11.GL_ALPHA_TEST);
            GL11.glDepthMask(true);
            GL11.glPopMatrix();
        }
    }

    public void func_72731_b(EntityPlayer p_72731_1_, MovingObjectPosition p_72731_2_, int p_72731_3_, float p_72731_4_)
    {
        if (p_72731_3_ == 0 && p_72731_2_.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK)
        {
            GL11.glEnable(GL11.GL_BLEND);
            OpenGlHelper.func_148821_a(770, 771, 1, 0);
            GL11.glColor4f(0.0F, 0.0F, 0.0F, 0.4F);
            GL11.glLineWidth(2.0F);
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glDepthMask(false);
            float f1 = 0.002F;
            Block block = this.field_72769_h.func_147439_a(p_72731_2_.field_72311_b, p_72731_2_.field_72312_c, p_72731_2_.field_72309_d);

            if (block.func_149688_o() != Material.field_151579_a)
            {
                block.func_149719_a(this.field_72769_h, p_72731_2_.field_72311_b, p_72731_2_.field_72312_c, p_72731_2_.field_72309_d);
                double d0 = p_72731_1_.field_70142_S + (p_72731_1_.field_70165_t - p_72731_1_.field_70142_S) * (double)p_72731_4_;
                double d1 = p_72731_1_.field_70137_T + (p_72731_1_.field_70163_u - p_72731_1_.field_70137_T) * (double)p_72731_4_;
                double d2 = p_72731_1_.field_70136_U + (p_72731_1_.field_70161_v - p_72731_1_.field_70136_U) * (double)p_72731_4_;
                func_147590_a(block.func_149633_g(this.field_72769_h, p_72731_2_.field_72311_b, p_72731_2_.field_72312_c, p_72731_2_.field_72309_d).func_72314_b((double)f1, (double)f1, (double)f1).func_72325_c(-d0, -d1, -d2), -1);
            }

            GL11.glDepthMask(true);
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glDisable(GL11.GL_BLEND);
        }
    }

    public static void func_147590_a(AxisAlignedBB p_147590_0_, int p_147590_1_)
    {
        Tessellator tessellator = Tessellator.field_78398_a;
        tessellator.func_78371_b(3);

        if (p_147590_1_ != -1)
        {
            tessellator.func_78378_d(p_147590_1_);
        }

        tessellator.func_78377_a(p_147590_0_.field_72340_a, p_147590_0_.field_72338_b, p_147590_0_.field_72339_c);
        tessellator.func_78377_a(p_147590_0_.field_72336_d, p_147590_0_.field_72338_b, p_147590_0_.field_72339_c);
        tessellator.func_78377_a(p_147590_0_.field_72336_d, p_147590_0_.field_72338_b, p_147590_0_.field_72334_f);
        tessellator.func_78377_a(p_147590_0_.field_72340_a, p_147590_0_.field_72338_b, p_147590_0_.field_72334_f);
        tessellator.func_78377_a(p_147590_0_.field_72340_a, p_147590_0_.field_72338_b, p_147590_0_.field_72339_c);
        tessellator.func_78381_a();
        tessellator.func_78371_b(3);

        if (p_147590_1_ != -1)
        {
            tessellator.func_78378_d(p_147590_1_);
        }

        tessellator.func_78377_a(p_147590_0_.field_72340_a, p_147590_0_.field_72337_e, p_147590_0_.field_72339_c);
        tessellator.func_78377_a(p_147590_0_.field_72336_d, p_147590_0_.field_72337_e, p_147590_0_.field_72339_c);
        tessellator.func_78377_a(p_147590_0_.field_72336_d, p_147590_0_.field_72337_e, p_147590_0_.field_72334_f);
        tessellator.func_78377_a(p_147590_0_.field_72340_a, p_147590_0_.field_72337_e, p_147590_0_.field_72334_f);
        tessellator.func_78377_a(p_147590_0_.field_72340_a, p_147590_0_.field_72337_e, p_147590_0_.field_72339_c);
        tessellator.func_78381_a();
        tessellator.func_78371_b(1);

        if (p_147590_1_ != -1)
        {
            tessellator.func_78378_d(p_147590_1_);
        }

        tessellator.func_78377_a(p_147590_0_.field_72340_a, p_147590_0_.field_72338_b, p_147590_0_.field_72339_c);
        tessellator.func_78377_a(p_147590_0_.field_72340_a, p_147590_0_.field_72337_e, p_147590_0_.field_72339_c);
        tessellator.func_78377_a(p_147590_0_.field_72336_d, p_147590_0_.field_72338_b, p_147590_0_.field_72339_c);
        tessellator.func_78377_a(p_147590_0_.field_72336_d, p_147590_0_.field_72337_e, p_147590_0_.field_72339_c);
        tessellator.func_78377_a(p_147590_0_.field_72336_d, p_147590_0_.field_72338_b, p_147590_0_.field_72334_f);
        tessellator.func_78377_a(p_147590_0_.field_72336_d, p_147590_0_.field_72337_e, p_147590_0_.field_72334_f);
        tessellator.func_78377_a(p_147590_0_.field_72340_a, p_147590_0_.field_72338_b, p_147590_0_.field_72334_f);
        tessellator.func_78377_a(p_147590_0_.field_72340_a, p_147590_0_.field_72337_e, p_147590_0_.field_72334_f);
        tessellator.func_78381_a();
    }

    public void func_72725_b(int p_72725_1_, int p_72725_2_, int p_72725_3_, int p_72725_4_, int p_72725_5_, int p_72725_6_)
    {
        int k1 = MathHelper.func_76137_a(p_72725_1_, 16);
        int l1 = MathHelper.func_76137_a(p_72725_2_, 16);
        int i2 = MathHelper.func_76137_a(p_72725_3_, 16);
        int j2 = MathHelper.func_76137_a(p_72725_4_, 16);
        int k2 = MathHelper.func_76137_a(p_72725_5_, 16);
        int l2 = MathHelper.func_76137_a(p_72725_6_, 16);

        for (int i3 = k1; i3 <= j2; ++i3)
        {
            int j3 = i3 % this.field_72766_m;

            if (j3 < 0)
            {
                j3 += this.field_72766_m;
            }

            for (int k3 = l1; k3 <= k2; ++k3)
            {
                int l3 = k3 % this.field_72763_n;

                if (l3 < 0)
                {
                    l3 += this.field_72763_n;
                }

                for (int i4 = i2; i4 <= l2; ++i4)
                {
                    int j4 = i4 % this.field_72764_o;

                    if (j4 < 0)
                    {
                        j4 += this.field_72764_o;
                    }

                    int k4 = (j4 * this.field_72763_n + l3) * this.field_72766_m + j3;
                    WorldRenderer worldrenderer = this.field_72765_l[k4];

                    if (worldrenderer != null && !worldrenderer.field_78939_q)
                    {
                        this.field_72767_j.add(worldrenderer);
                        worldrenderer.func_78914_f();
                    }
                }
            }
        }
    }

    public void func_147586_a(int p_147586_1_, int p_147586_2_, int p_147586_3_)
    {
        this.func_72725_b(p_147586_1_ - 1, p_147586_2_ - 1, p_147586_3_ - 1, p_147586_1_ + 1, p_147586_2_ + 1, p_147586_3_ + 1);
    }

    public void func_147588_b(int p_147588_1_, int p_147588_2_, int p_147588_3_)
    {
        this.func_72725_b(p_147588_1_ - 1, p_147588_2_ - 1, p_147588_3_ - 1, p_147588_1_ + 1, p_147588_2_ + 1, p_147588_3_ + 1);
    }

    public void func_147585_a(int p_147585_1_, int p_147585_2_, int p_147585_3_, int p_147585_4_, int p_147585_5_, int p_147585_6_)
    {
        this.func_72725_b(p_147585_1_ - 1, p_147585_2_ - 1, p_147585_3_ - 1, p_147585_4_ + 1, p_147585_5_ + 1, p_147585_6_ + 1);
    }

    public void func_72729_a(ICamera p_72729_1_, float p_72729_2_)
    {
        for (int i = 0; i < this.field_72765_l.length; ++i)
        {
            if (!this.field_72765_l[i].func_78906_e() && (!this.field_72765_l[i].field_78927_l || (i + this.field_72757_g & 15) == 0))
            {
                this.field_72765_l[i].func_78908_a(p_72729_1_);
            }
        }

        ++this.field_72757_g;
    }

    public void func_72702_a(String p_72702_1_, int p_72702_2_, int p_72702_3_, int p_72702_4_)
    {
        ChunkCoordinates chunkcoordinates = new ChunkCoordinates(p_72702_2_, p_72702_3_, p_72702_4_);
        ISound isound = (ISound)this.field_147593_P.get(chunkcoordinates);

        if (isound != null)
        {
            this.field_72777_q.func_147118_V().func_147683_b(isound);
            this.field_147593_P.remove(chunkcoordinates);
        }

        if (p_72702_1_ != null)
        {
            ItemRecord itemrecord = ItemRecord.func_150926_b(p_72702_1_);

            if (itemrecord != null)
            {
                this.field_72777_q.field_71456_v.func_73833_a(itemrecord.func_150927_i());
            }

            PositionedSoundRecord positionedsoundrecord = PositionedSoundRecord.func_147675_a(new ResourceLocation(p_72702_1_), (float)p_72702_2_, (float)p_72702_3_, (float)p_72702_4_);
            this.field_147593_P.put(chunkcoordinates, positionedsoundrecord);
            this.field_72777_q.func_147118_V().func_147682_a(positionedsoundrecord);
        }
    }

    public void func_72704_a(String p_72704_1_, double p_72704_2_, double p_72704_4_, double p_72704_6_, float p_72704_8_, float p_72704_9_) {}

    public void func_85102_a(EntityPlayer p_85102_1_, String p_85102_2_, double p_85102_3_, double p_85102_5_, double p_85102_7_, float p_85102_9_, float p_85102_10_) {}

    public void func_72708_a(String p_72708_1_, final double p_72708_2_, final double p_72708_4_, final double p_72708_6_, double p_72708_8_, double p_72708_10_, double p_72708_12_)
    {
        try
        {
            this.func_72726_b(p_72708_1_, p_72708_2_, p_72708_4_, p_72708_6_, p_72708_8_, p_72708_10_, p_72708_12_);
        }
        catch (Throwable throwable)
        {
            CrashReport crashreport = CrashReport.func_85055_a(throwable, "Exception while adding particle");
            CrashReportCategory crashreportcategory = crashreport.func_85058_a("Particle being added");
            crashreportcategory.func_71507_a("Name", p_72708_1_);
            crashreportcategory.func_71500_a("Position", new Callable()
            {
                private static final String __OBFID = "CL_00000955";
                public String call()
                {
                    return CrashReportCategory.func_85074_a(p_72708_2_, p_72708_4_, p_72708_6_);
                }
            });
            throw new ReportedException(crashreport);
        }
    }

    public EntityFX func_72726_b(String p_72726_1_, double p_72726_2_, double p_72726_4_, double p_72726_6_, double p_72726_8_, double p_72726_10_, double p_72726_12_)
    {
        if (this.field_72777_q != null && this.field_72777_q.field_71451_h != null && this.field_72777_q.field_71452_i != null)
        {
            int i = this.field_72777_q.gameSettings.field_74362_aa;

            if (i == 1 && this.field_72769_h.field_73012_v.nextInt(3) == 0)
            {
                i = 2;
            }

            double d6 = this.field_72777_q.field_71451_h.field_70165_t - p_72726_2_;
            double d7 = this.field_72777_q.field_71451_h.field_70163_u - p_72726_4_;
            double d8 = this.field_72777_q.field_71451_h.field_70161_v - p_72726_6_;
            EntityFX entityfx = null;

            if (p_72726_1_.equals("hugeexplosion"))
            {
                this.field_72777_q.field_71452_i.func_78873_a(entityfx = new EntityHugeExplodeFX(this.field_72769_h, p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_));
            }
            else if (p_72726_1_.equals("largeexplode"))
            {
                this.field_72777_q.field_71452_i.func_78873_a(entityfx = new EntityLargeExplodeFX(this.field_72770_i, this.field_72769_h, p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_));
            }
            else if (p_72726_1_.equals("fireworksSpark"))
            {
                this.field_72777_q.field_71452_i.func_78873_a(entityfx = new EntityFireworkSparkFX(this.field_72769_h, p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_, this.field_72777_q.field_71452_i));
            }

            if (entityfx != null)
            {
                return (EntityFX)entityfx;
            }
            else
            {
                double d9 = 16.0D;

                if (d6 * d6 + d7 * d7 + d8 * d8 > d9 * d9)
                {
                    return null;
                }
                else if (i > 1)
                {
                    return null;
                }
                else
                {
                    if (p_72726_1_.equals("bubble"))
                    {
                        entityfx = new EntityBubbleFX(this.field_72769_h, p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_);
                    }
                    else if (p_72726_1_.equals("suspended"))
                    {
                        entityfx = new EntitySuspendFX(this.field_72769_h, p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_);
                    }
                    else if (p_72726_1_.equals("depthsuspend"))
                    {
                        entityfx = new EntityAuraFX(this.field_72769_h, p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_);
                    }
                    else if (p_72726_1_.equals("townaura"))
                    {
                        entityfx = new EntityAuraFX(this.field_72769_h, p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_);
                    }
                    else if (p_72726_1_.equals("crit"))
                    {
                        entityfx = new EntityCritFX(this.field_72769_h, p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_);
                    }
                    else if (p_72726_1_.equals("magicCrit"))
                    {
                        entityfx = new EntityCritFX(this.field_72769_h, p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_);
                        ((EntityFX)entityfx).func_70538_b(((EntityFX)entityfx).func_70534_d() * 0.3F, ((EntityFX)entityfx).func_70542_f() * 0.8F, ((EntityFX)entityfx).func_70535_g());
                        ((EntityFX)entityfx).func_94053_h();
                    }
                    else if (p_72726_1_.equals("smoke"))
                    {
                        entityfx = new EntitySmokeFX(this.field_72769_h, p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_);
                    }
                    else if (p_72726_1_.equals("mobSpell"))
                    {
                        entityfx = new EntitySpellParticleFX(this.field_72769_h, p_72726_2_, p_72726_4_, p_72726_6_, 0.0D, 0.0D, 0.0D);
                        ((EntityFX)entityfx).func_70538_b((float)p_72726_8_, (float)p_72726_10_, (float)p_72726_12_);
                    }
                    else if (p_72726_1_.equals("mobSpellAmbient"))
                    {
                        entityfx = new EntitySpellParticleFX(this.field_72769_h, p_72726_2_, p_72726_4_, p_72726_6_, 0.0D, 0.0D, 0.0D);
                        ((EntityFX)entityfx).func_82338_g(0.15F);
                        ((EntityFX)entityfx).func_70538_b((float)p_72726_8_, (float)p_72726_10_, (float)p_72726_12_);
                    }
                    else if (p_72726_1_.equals("spell"))
                    {
                        entityfx = new EntitySpellParticleFX(this.field_72769_h, p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_);
                    }
                    else if (p_72726_1_.equals("instantSpell"))
                    {
                        entityfx = new EntitySpellParticleFX(this.field_72769_h, p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_);
                        ((EntitySpellParticleFX)entityfx).func_70589_b(144);
                    }
                    else if (p_72726_1_.equals("witchMagic"))
                    {
                        entityfx = new EntitySpellParticleFX(this.field_72769_h, p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_);
                        ((EntitySpellParticleFX)entityfx).func_70589_b(144);
                        float f = this.field_72769_h.field_73012_v.nextFloat() * 0.5F + 0.35F;
                        ((EntityFX)entityfx).func_70538_b(1.0F * f, 0.0F * f, 1.0F * f);
                    }
                    else if (p_72726_1_.equals("note"))
                    {
                        entityfx = new EntityNoteFX(this.field_72769_h, p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_);
                    }
                    else if (p_72726_1_.equals("portal"))
                    {
                        entityfx = new EntityPortalFX(this.field_72769_h, p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_);
                    }
                    else if (p_72726_1_.equals("enchantmenttable"))
                    {
                        entityfx = new EntityEnchantmentTableParticleFX(this.field_72769_h, p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_);
                    }
                    else if (p_72726_1_.equals("explode"))
                    {
                        entityfx = new EntityExplodeFX(this.field_72769_h, p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_);
                    }
                    else if (p_72726_1_.equals("flame"))
                    {
                        entityfx = new EntityFlameFX(this.field_72769_h, p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_);
                    }
                    else if (p_72726_1_.equals("lava"))
                    {
                        entityfx = new EntityLavaFX(this.field_72769_h, p_72726_2_, p_72726_4_, p_72726_6_);
                    }
                    else if (p_72726_1_.equals("footstep"))
                    {
                        entityfx = new EntityFootStepFX(this.field_72770_i, this.field_72769_h, p_72726_2_, p_72726_4_, p_72726_6_);
                    }
                    else if (p_72726_1_.equals("splash"))
                    {
                        entityfx = new EntitySplashFX(this.field_72769_h, p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_);
                    }
                    else if (p_72726_1_.equals("wake"))
                    {
                        entityfx = new EntityFishWakeFX(this.field_72769_h, p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_);
                    }
                    else if (p_72726_1_.equals("largesmoke"))
                    {
                        entityfx = new EntitySmokeFX(this.field_72769_h, p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_, 2.5F);
                    }
                    else if (p_72726_1_.equals("cloud"))
                    {
                        entityfx = new EntityCloudFX(this.field_72769_h, p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_);
                    }
                    else if (p_72726_1_.equals("reddust"))
                    {
                        entityfx = new EntityReddustFX(this.field_72769_h, p_72726_2_, p_72726_4_, p_72726_6_, (float)p_72726_8_, (float)p_72726_10_, (float)p_72726_12_);
                    }
                    else if (p_72726_1_.equals("snowballpoof"))
                    {
                        entityfx = new EntityBreakingFX(this.field_72769_h, p_72726_2_, p_72726_4_, p_72726_6_, Items.field_151126_ay);
                    }
                    else if (p_72726_1_.equals("dripWater"))
                    {
                        entityfx = new EntityDropParticleFX(this.field_72769_h, p_72726_2_, p_72726_4_, p_72726_6_, Material.field_151586_h);
                    }
                    else if (p_72726_1_.equals("dripLava"))
                    {
                        entityfx = new EntityDropParticleFX(this.field_72769_h, p_72726_2_, p_72726_4_, p_72726_6_, Material.field_151587_i);
                    }
                    else if (p_72726_1_.equals("snowshovel"))
                    {
                        entityfx = new EntitySnowShovelFX(this.field_72769_h, p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_);
                    }
                    else if (p_72726_1_.equals("slime"))
                    {
                        entityfx = new EntityBreakingFX(this.field_72769_h, p_72726_2_, p_72726_4_, p_72726_6_, Items.field_151123_aH);
                    }
                    else if (p_72726_1_.equals("heart"))
                    {
                        entityfx = new EntityHeartFX(this.field_72769_h, p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_);
                    }
                    else if (p_72726_1_.equals("angryVillager"))
                    {
                        entityfx = new EntityHeartFX(this.field_72769_h, p_72726_2_, p_72726_4_ + 0.5D, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_);
                        ((EntityFX)entityfx).func_70536_a(81);
                        ((EntityFX)entityfx).func_70538_b(1.0F, 1.0F, 1.0F);
                    }
                    else if (p_72726_1_.equals("happyVillager"))
                    {
                        entityfx = new EntityAuraFX(this.field_72769_h, p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_);
                        ((EntityFX)entityfx).func_70536_a(82);
                        ((EntityFX)entityfx).func_70538_b(1.0F, 1.0F, 1.0F);
                    }
                    else
                    {
                        int k;
                        String[] astring;

                        if (p_72726_1_.startsWith("iconcrack_"))
                        {
                            astring = p_72726_1_.split("_", 3);
                            int j = Integer.parseInt(astring[1]);

                            if (astring.length > 2)
                            {
                                k = Integer.parseInt(astring[2]);
                                entityfx = new EntityBreakingFX(this.field_72769_h, p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_, Item.func_150899_d(j), k);
                            }
                            else
                            {
                                entityfx = new EntityBreakingFX(this.field_72769_h, p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_, Item.func_150899_d(j), 0);
                            }
                        }
                        else
                        {
                            Block block;

                            if (p_72726_1_.startsWith("blockcrack_"))
                            {
                                astring = p_72726_1_.split("_", 3);
                                block = Block.func_149729_e(Integer.parseInt(astring[1]));
                                k = Integer.parseInt(astring[2]);
                                entityfx = (new EntityDiggingFX(this.field_72769_h, p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_, block, k)).func_90019_g(k);
                            }
                            else if (p_72726_1_.startsWith("blockdust_"))
                            {
                                astring = p_72726_1_.split("_", 3);
                                block = Block.func_149729_e(Integer.parseInt(astring[1]));
                                k = Integer.parseInt(astring[2]);
                                entityfx = (new EntityBlockDustFX(this.field_72769_h, p_72726_2_, p_72726_4_, p_72726_6_, p_72726_8_, p_72726_10_, p_72726_12_, block, k)).func_90019_g(k);
                            }
                        }
                    }

                    if (entityfx != null)
                    {
                        this.field_72777_q.field_71452_i.func_78873_a((EntityFX)entityfx);
                    }

                    return (EntityFX)entityfx;
                }
            }
        }
        else
        {
            return null;
        }
    }

    public void func_72703_a(Entity p_72703_1_) {}

    public void func_72709_b(Entity p_72709_1_) {}

    public void func_72728_f()
    {
        GLAllocation.func_74523_b(this.field_72778_p);
    }

    public void func_82746_a(int p_82746_1_, int p_82746_2_, int p_82746_3_, int p_82746_4_, int p_82746_5_)
    {
        Random random = this.field_72769_h.field_73012_v;

        switch (p_82746_1_)
        {
            case 1013:
            case 1018:
                if (this.field_72777_q.field_71451_h != null)
                {
                    double d0 = (double)p_82746_2_ - this.field_72777_q.field_71451_h.field_70165_t;
                    double d1 = (double)p_82746_3_ - this.field_72777_q.field_71451_h.field_70163_u;
                    double d2 = (double)p_82746_4_ - this.field_72777_q.field_71451_h.field_70161_v;
                    double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                    double d4 = this.field_72777_q.field_71451_h.field_70165_t;
                    double d5 = this.field_72777_q.field_71451_h.field_70163_u;
                    double d6 = this.field_72777_q.field_71451_h.field_70161_v;

                    if (d3 > 0.0D)
                    {
                        d4 += d0 / d3 * 2.0D;
                        d5 += d1 / d3 * 2.0D;
                        d6 += d2 / d3 * 2.0D;
                    }

                    if (p_82746_1_ == 1013)
                    {
                        this.field_72769_h.func_72980_b(d4, d5, d6, "mob.wither.spawn", 1.0F, 1.0F, false);
                    }
                    else if (p_82746_1_ == 1018)
                    {
                        this.field_72769_h.func_72980_b(d4, d5, d6, "mob.enderdragon.end", 5.0F, 1.0F, false);
                    }
                }
            default:
        }
    }

    public void func_72706_a(EntityPlayer p_72706_1_, int p_72706_2_, int p_72706_3_, int p_72706_4_, int p_72706_5_, int p_72706_6_)
    {
        Random random = this.field_72769_h.field_73012_v;
        Block block = null;
        double d0;
        double d1;
        double d2;
        String s;
        int k1;
        double d4;
        double d5;
        double d6;
        double d7;
        int l2;
        double d13;

        switch (p_72706_2_)
        {
            case 1000:
                this.field_72769_h.func_72980_b((double)p_72706_3_, (double)p_72706_4_, (double)p_72706_5_, "random.click", 1.0F, 1.0F, false);
                break;
            case 1001:
                this.field_72769_h.func_72980_b((double)p_72706_3_, (double)p_72706_4_, (double)p_72706_5_, "random.click", 1.0F, 1.2F, false);
                break;
            case 1002:
                this.field_72769_h.func_72980_b((double)p_72706_3_, (double)p_72706_4_, (double)p_72706_5_, "random.bow", 1.0F, 1.2F, false);
                break;
            case 1003:
                if (Math.random() < 0.5D)
                {
                    this.field_72769_h.func_72980_b((double)p_72706_3_ + 0.5D, (double)p_72706_4_ + 0.5D, (double)p_72706_5_ + 0.5D, "random.door_open", 1.0F, this.field_72769_h.field_73012_v.nextFloat() * 0.1F + 0.9F, false);
                }
                else
                {
                    this.field_72769_h.func_72980_b((double)p_72706_3_ + 0.5D, (double)p_72706_4_ + 0.5D, (double)p_72706_5_ + 0.5D, "random.door_close", 1.0F, this.field_72769_h.field_73012_v.nextFloat() * 0.1F + 0.9F, false);
                }

                break;
            case 1004:
                this.field_72769_h.func_72980_b((double)((float)p_72706_3_ + 0.5F), (double)((float)p_72706_4_ + 0.5F), (double)((float)p_72706_5_ + 0.5F), "random.fizz", 0.5F, 2.6F + (random.nextFloat() - random.nextFloat()) * 0.8F, false);
                break;
            case 1005:
                if (Item.func_150899_d(p_72706_6_) instanceof ItemRecord)
                {
                    this.field_72769_h.func_72934_a("records." + ((ItemRecord)Item.func_150899_d(p_72706_6_)).field_150929_a, p_72706_3_, p_72706_4_, p_72706_5_);
                }
                else
                {
                    this.field_72769_h.func_72934_a((String)null, p_72706_3_, p_72706_4_, p_72706_5_);
                }

                break;
            case 1007:
                this.field_72769_h.func_72980_b((double)p_72706_3_ + 0.5D, (double)p_72706_4_ + 0.5D, (double)p_72706_5_ + 0.5D, "mob.ghast.charge", 10.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
                break;
            case 1008:
                this.field_72769_h.func_72980_b((double)p_72706_3_ + 0.5D, (double)p_72706_4_ + 0.5D, (double)p_72706_5_ + 0.5D, "mob.ghast.fireball", 10.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
                break;
            case 1009:
                this.field_72769_h.func_72980_b((double)p_72706_3_ + 0.5D, (double)p_72706_4_ + 0.5D, (double)p_72706_5_ + 0.5D, "mob.ghast.fireball", 2.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
                break;
            case 1010:
                this.field_72769_h.func_72980_b((double)p_72706_3_ + 0.5D, (double)p_72706_4_ + 0.5D, (double)p_72706_5_ + 0.5D, "mob.zombie.wood", 2.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
                break;
            case 1011:
                this.field_72769_h.func_72980_b((double)p_72706_3_ + 0.5D, (double)p_72706_4_ + 0.5D, (double)p_72706_5_ + 0.5D, "mob.zombie.metal", 2.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
                break;
            case 1012:
                this.field_72769_h.func_72980_b((double)p_72706_3_ + 0.5D, (double)p_72706_4_ + 0.5D, (double)p_72706_5_ + 0.5D, "mob.zombie.woodbreak", 2.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
                break;
            case 1014:
                this.field_72769_h.func_72980_b((double)p_72706_3_ + 0.5D, (double)p_72706_4_ + 0.5D, (double)p_72706_5_ + 0.5D, "mob.wither.shoot", 2.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
                break;
            case 1015:
                this.field_72769_h.func_72980_b((double)p_72706_3_ + 0.5D, (double)p_72706_4_ + 0.5D, (double)p_72706_5_ + 0.5D, "mob.bat.takeoff", 0.05F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
                break;
            case 1016:
                this.field_72769_h.func_72980_b((double)p_72706_3_ + 0.5D, (double)p_72706_4_ + 0.5D, (double)p_72706_5_ + 0.5D, "mob.zombie.infect", 2.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
                break;
            case 1017:
                this.field_72769_h.func_72980_b((double)p_72706_3_ + 0.5D, (double)p_72706_4_ + 0.5D, (double)p_72706_5_ + 0.5D, "mob.zombie.unfect", 2.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
                break;
            case 1020:
                this.field_72769_h.func_72980_b((double)((float)p_72706_3_ + 0.5F), (double)((float)p_72706_4_ + 0.5F), (double)((float)p_72706_5_ + 0.5F), "random.anvil_break", 1.0F, this.field_72769_h.field_73012_v.nextFloat() * 0.1F + 0.9F, false);
                break;
            case 1021:
                this.field_72769_h.func_72980_b((double)((float)p_72706_3_ + 0.5F), (double)((float)p_72706_4_ + 0.5F), (double)((float)p_72706_5_ + 0.5F), "random.anvil_use", 1.0F, this.field_72769_h.field_73012_v.nextFloat() * 0.1F + 0.9F, false);
                break;
            case 1022:
                this.field_72769_h.func_72980_b((double)((float)p_72706_3_ + 0.5F), (double)((float)p_72706_4_ + 0.5F), (double)((float)p_72706_5_ + 0.5F), "random.anvil_land", 0.3F, this.field_72769_h.field_73012_v.nextFloat() * 0.1F + 0.9F, false);
                break;
            case 2000:
                int j2 = p_72706_6_ % 3 - 1;
                int j1 = p_72706_6_ / 3 % 3 - 1;
                d1 = (double)p_72706_3_ + (double)j2 * 0.6D + 0.5D;
                d2 = (double)p_72706_4_ + 0.5D;
                double d9 = (double)p_72706_5_ + (double)j1 * 0.6D + 0.5D;

                for (int k2 = 0; k2 < 10; ++k2)
                {
                    double d11 = random.nextDouble() * 0.2D + 0.01D;
                    double d12 = d1 + (double)j2 * 0.01D + (random.nextDouble() - 0.5D) * (double)j1 * 0.5D;
                    d4 = d2 + (random.nextDouble() - 0.5D) * 0.5D;
                    d13 = d9 + (double)j1 * 0.01D + (random.nextDouble() - 0.5D) * (double)j2 * 0.5D;
                    d5 = (double)j2 * d11 + random.nextGaussian() * 0.01D;
                    d6 = -0.03D + random.nextGaussian() * 0.01D;
                    d7 = (double)j1 * d11 + random.nextGaussian() * 0.01D;
                    this.func_72708_a("smoke", d12, d4, d13, d5, d6, d7);
                }

                return;
            case 2001:
                block = Block.func_149729_e(p_72706_6_ & 4095);

                if (block.func_149688_o() != Material.field_151579_a)
                {
                    this.field_72777_q.func_147118_V().func_147682_a(new PositionedSoundRecord(new ResourceLocation(block.field_149762_H.func_150495_a()), (block.field_149762_H.func_150497_c() + 1.0F) / 2.0F, block.field_149762_H.func_150494_d() * 0.8F, (float)p_72706_3_ + 0.5F, (float)p_72706_4_ + 0.5F, (float)p_72706_5_ + 0.5F));
                }

                this.field_72777_q.field_71452_i.func_147215_a(p_72706_3_, p_72706_4_, p_72706_5_, block, p_72706_6_ >> 12 & 255);
                break;
            case 2002:
                d0 = (double)p_72706_3_;
                d1 = (double)p_72706_4_;
                d2 = (double)p_72706_5_;
                s = "iconcrack_" + Item.func_150891_b(Items.field_151068_bn) + "_" + p_72706_6_;

                for (k1 = 0; k1 < 8; ++k1)
                {
                    this.func_72708_a(s, d0, d1, d2, random.nextGaussian() * 0.15D, random.nextDouble() * 0.2D, random.nextGaussian() * 0.15D);
                }

                k1 = Items.field_151068_bn.func_77620_a(p_72706_6_);
                float f = (float)(k1 >> 16 & 255) / 255.0F;
                float f1 = (float)(k1 >> 8 & 255) / 255.0F;
                float f2 = (float)(k1 >> 0 & 255) / 255.0F;
                String s1 = "spell";

                if (Items.field_151068_bn.func_77833_h(p_72706_6_))
                {
                    s1 = "instantSpell";
                }

                for (l2 = 0; l2 < 100; ++l2)
                {
                    d4 = random.nextDouble() * 4.0D;
                    d13 = random.nextDouble() * Math.PI * 2.0D;
                    d5 = Math.cos(d13) * d4;
                    d6 = 0.01D + random.nextDouble() * 0.5D;
                    d7 = Math.sin(d13) * d4;
                    EntityFX entityfx = this.func_72726_b(s1, d0 + d5 * 0.1D, d1 + 0.3D, d2 + d7 * 0.1D, d5, d6, d7);

                    if (entityfx != null)
                    {
                        float f4 = 0.75F + random.nextFloat() * 0.25F;
                        entityfx.func_70538_b(f * f4, f1 * f4, f2 * f4);
                        entityfx.func_70543_e((float)d4);
                    }
                }

                this.field_72769_h.func_72980_b((double)p_72706_3_ + 0.5D, (double)p_72706_4_ + 0.5D, (double)p_72706_5_ + 0.5D, "game.potion.smash", 1.0F, this.field_72769_h.field_73012_v.nextFloat() * 0.1F + 0.9F, false);
                break;
            case 2003:
                d0 = (double)p_72706_3_ + 0.5D;
                d1 = (double)p_72706_4_;
                d2 = (double)p_72706_5_ + 0.5D;
                s = "iconcrack_" + Item.func_150891_b(Items.field_151061_bv);

                for (k1 = 0; k1 < 8; ++k1)
                {
                    this.func_72708_a(s, d0, d1, d2, random.nextGaussian() * 0.15D, random.nextDouble() * 0.2D, random.nextGaussian() * 0.15D);
                }

                for (double d10 = 0.0D; d10 < (Math.PI * 2D); d10 += 0.15707963267948966D)
                {
                    this.func_72708_a("portal", d0 + Math.cos(d10) * 5.0D, d1 - 0.4D, d2 + Math.sin(d10) * 5.0D, Math.cos(d10) * -5.0D, 0.0D, Math.sin(d10) * -5.0D);
                    this.func_72708_a("portal", d0 + Math.cos(d10) * 5.0D, d1 - 0.4D, d2 + Math.sin(d10) * 5.0D, Math.cos(d10) * -7.0D, 0.0D, Math.sin(d10) * -7.0D);
                }

                return;
            case 2004:
                for (l2 = 0; l2 < 20; ++l2)
                {
                    d4 = (double)p_72706_3_ + 0.5D + ((double)this.field_72769_h.field_73012_v.nextFloat() - 0.5D) * 2.0D;
                    d13 = (double)p_72706_4_ + 0.5D + ((double)this.field_72769_h.field_73012_v.nextFloat() - 0.5D) * 2.0D;
                    d5 = (double)p_72706_5_ + 0.5D + ((double)this.field_72769_h.field_73012_v.nextFloat() - 0.5D) * 2.0D;
                    this.field_72769_h.func_72869_a("smoke", d4, d13, d5, 0.0D, 0.0D, 0.0D);
                    this.field_72769_h.func_72869_a("flame", d4, d13, d5, 0.0D, 0.0D, 0.0D);
                }

                return;
            case 2005:
                ItemDye.func_150918_a(this.field_72769_h, p_72706_3_, p_72706_4_, p_72706_5_, p_72706_6_);
                break;
            case 2006:
                block = this.field_72769_h.func_147439_a(p_72706_3_, p_72706_4_, p_72706_5_);

                if (block.func_149688_o() != Material.field_151579_a)
                {
                    double d3 = (double)Math.min(0.2F + (float)p_72706_6_ / 15.0F, 10.0F);

                    if (d3 > 2.5D)
                    {
                        d3 = 2.5D;
                    }

                    int l1 = (int)(150.0D * d3);

                    for (int i2 = 0; i2 < l1; ++i2)
                    {
                        float f3 = MathHelper.func_151240_a(random, 0.0F, ((float)Math.PI * 2F));
                        d5 = (double)MathHelper.func_151240_a(random, 0.75F, 1.0F);
                        d6 = 0.20000000298023224D + d3 / 100.0D;
                        d7 = (double)(MathHelper.func_76134_b(f3) * 0.2F) * d5 * d5 * (d3 + 0.2D);
                        double d8 = (double)(MathHelper.func_76126_a(f3) * 0.2F) * d5 * d5 * (d3 + 0.2D);
                        this.field_72769_h.func_72869_a("blockdust_" + Block.func_149682_b(block) + "_" + this.field_72769_h.func_72805_g(p_72706_3_, p_72706_4_, p_72706_5_), (double)((float)p_72706_3_ + 0.5F), (double)((float)p_72706_4_ + 1.0F), (double)((float)p_72706_5_ + 0.5F), d7, d6, d8);
                    }
                }
        }
    }

    public void func_147587_b(int p_147587_1_, int p_147587_2_, int p_147587_3_, int p_147587_4_, int p_147587_5_)
    {
        if (p_147587_5_ >= 0 && p_147587_5_ < 10)
        {
            DestroyBlockProgress destroyblockprogress = (DestroyBlockProgress)this.field_72738_E.get(Integer.valueOf(p_147587_1_));

            if (destroyblockprogress == null || destroyblockprogress.func_73110_b() != p_147587_2_ || destroyblockprogress.func_73109_c() != p_147587_3_ || destroyblockprogress.func_73108_d() != p_147587_4_)
            {
                destroyblockprogress = new DestroyBlockProgress(p_147587_1_, p_147587_2_, p_147587_3_, p_147587_4_);
                this.field_72738_E.put(Integer.valueOf(p_147587_1_), destroyblockprogress);
            }

            destroyblockprogress.func_73107_a(p_147587_5_);
            destroyblockprogress.func_82744_b(this.field_72773_u);
        }
        else
        {
            this.field_72738_E.remove(Integer.valueOf(p_147587_1_));
        }
    }

    public void func_94140_a(IIconRegister p_94140_1_)
    {
        this.field_94141_F = new IIcon[10];

        for (int i = 0; i < this.field_94141_F.length; ++i)
        {
            this.field_94141_F[i] = p_94140_1_.func_94245_a("destroy_stage_" + i);
        }
    }
}