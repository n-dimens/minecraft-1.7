package net.minecraft.client.network;

import com.google.common.base.Charsets;
import com.mojang.authlib.GameProfile;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.concurrent.GenericFutureListener;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;
import net.minecraft.block.Block;
import net.minecraft.client.ClientBrandRetriever;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiDisconnected;
import net.minecraft.client.gui.GuiDownloadTerrain;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiMerchant;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiPlayerInfo;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiScreenDemo;
import net.minecraft.client.gui.GuiScreenRealmsProxy;
import net.minecraft.client.gui.GuiWinGame;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.client.gui.IProgressMeter;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.ServerList;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.particle.EntityCrit2FX;
import net.minecraft.client.particle.EntityPickupFX;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.stream.MetadataAchievement;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLeashKnot;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.NpcMerchant;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.BaseAttributeMap;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.entity.effect.EntityLightningBolt;
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
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.inventory.AnimalChest;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMap;
import net.minecraft.item.ItemStack;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.network.play.client.C00PacketKeepAlive;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C0FPacketConfirmTransaction;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.network.play.server.S00PacketKeepAlive;
import net.minecraft.network.play.server.S01PacketJoinGame;
import net.minecraft.network.play.server.S02PacketChat;
import net.minecraft.network.play.server.S03PacketTimeUpdate;
import net.minecraft.network.play.server.S04PacketEntityEquipment;
import net.minecraft.network.play.server.S05PacketSpawnPosition;
import net.minecraft.network.play.server.S06PacketUpdateHealth;
import net.minecraft.network.play.server.S07PacketRespawn;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.network.play.server.S09PacketHeldItemChange;
import net.minecraft.network.play.server.S0APacketUseBed;
import net.minecraft.network.play.server.S0BPacketAnimation;
import net.minecraft.network.play.server.S0CPacketSpawnPlayer;
import net.minecraft.network.play.server.S0DPacketCollectItem;
import net.minecraft.network.play.server.S0EPacketSpawnObject;
import net.minecraft.network.play.server.S0FPacketSpawnMob;
import net.minecraft.network.play.server.S10PacketSpawnPainting;
import net.minecraft.network.play.server.S11PacketSpawnExperienceOrb;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.network.play.server.S13PacketDestroyEntities;
import net.minecraft.network.play.server.S14PacketEntity;
import net.minecraft.network.play.server.S18PacketEntityTeleport;
import net.minecraft.network.play.server.S19PacketEntityHeadLook;
import net.minecraft.network.play.server.S19PacketEntityStatus;
import net.minecraft.network.play.server.S1BPacketEntityAttach;
import net.minecraft.network.play.server.S1CPacketEntityMetadata;
import net.minecraft.network.play.server.S1DPacketEntityEffect;
import net.minecraft.network.play.server.S1EPacketRemoveEntityEffect;
import net.minecraft.network.play.server.S1FPacketSetExperience;
import net.minecraft.network.play.server.S20PacketEntityProperties;
import net.minecraft.network.play.server.S21PacketChunkData;
import net.minecraft.network.play.server.S22PacketMultiBlockChange;
import net.minecraft.network.play.server.S23PacketBlockChange;
import net.minecraft.network.play.server.S24PacketBlockAction;
import net.minecraft.network.play.server.S25PacketBlockBreakAnim;
import net.minecraft.network.play.server.S26PacketMapChunkBulk;
import net.minecraft.network.play.server.S27PacketExplosion;
import net.minecraft.network.play.server.S28PacketEffect;
import net.minecraft.network.play.server.S29PacketSoundEffect;
import net.minecraft.network.play.server.S2APacketParticles;
import net.minecraft.network.play.server.S2BPacketChangeGameState;
import net.minecraft.network.play.server.S2CPacketSpawnGlobalEntity;
import net.minecraft.network.play.server.S2DPacketOpenWindow;
import net.minecraft.network.play.server.S2EPacketCloseWindow;
import net.minecraft.network.play.server.S2FPacketSetSlot;
import net.minecraft.network.play.server.S30PacketWindowItems;
import net.minecraft.network.play.server.S31PacketWindowProperty;
import net.minecraft.network.play.server.S32PacketConfirmTransaction;
import net.minecraft.network.play.server.S33PacketUpdateSign;
import net.minecraft.network.play.server.S34PacketMaps;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.network.play.server.S36PacketSignEditorOpen;
import net.minecraft.network.play.server.S37PacketStatistics;
import net.minecraft.network.play.server.S38PacketPlayerListItem;
import net.minecraft.network.play.server.S39PacketPlayerAbilities;
import net.minecraft.network.play.server.S3APacketTabComplete;
import net.minecraft.network.play.server.S3BPacketScoreboardObjective;
import net.minecraft.network.play.server.S3CPacketUpdateScore;
import net.minecraft.network.play.server.S3DPacketDisplayScoreboard;
import net.minecraft.network.play.server.S3EPacketTeams;
import net.minecraft.network.play.server.S3FPacketCustomPayload;
import net.minecraft.network.play.server.S40PacketDisconnect;
import net.minecraft.potion.PotionEffect;
import net.minecraft.realms.DisconnectedOnlineScreen;
import net.minecraft.scoreboard.IScoreObjectiveCriteria;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraft.stats.StatBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.tileentity.TileEntityBrewingStand;
import net.minecraft.tileentity.TileEntityCommandBlock;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.tileentity.TileEntityDropper;
import net.minecraft.tileentity.TileEntityFlowerPot;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.Explosion;
import net.minecraft.world.WorldProviderSurface;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraft.world.storage.MapData;
import net.minecraft.world.storage.MapStorage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SideOnly(Side.CLIENT)
public class NetHandlerPlayClient implements INetHandlerPlayClient
{
    private static final Logger field_147301_d = LogManager.getLogger();
    private final NetworkManager field_147302_e;
    private Minecraft field_147299_f;
    private WorldClient field_147300_g;
    private boolean field_147309_h;
    public MapStorage field_147305_a = new MapStorage((ISaveHandler)null);
    private Map field_147310_i = new HashMap();
    public List field_147303_b = new ArrayList();
    public int field_147304_c = 20;
    private GuiScreen field_147307_j;
    private boolean field_147308_k = false;
    private Random field_147306_l = new Random();
    private static final String __OBFID = "CL_00000878";

    public NetHandlerPlayClient(Minecraft p_i45061_1_, GuiScreen p_i45061_2_, NetworkManager p_i45061_3_)
    {
        this.field_147299_f = p_i45061_1_;
        this.field_147307_j = p_i45061_2_;
        this.field_147302_e = p_i45061_3_;
    }

    public void func_147296_c()
    {
        this.field_147300_g = null;
    }

    public void func_147233_a() {}

    public void func_147282_a(S01PacketJoinGame p_147282_1_)
    {
        this.field_147299_f.field_71442_b = new PlayerControllerMP(this.field_147299_f, this);
        this.field_147300_g = new WorldClient(this, new WorldSettings(0L, p_147282_1_.func_149198_e(), false, p_147282_1_.func_149195_d(), p_147282_1_.func_149196_i()), p_147282_1_.func_149194_f(), p_147282_1_.func_149192_g(), this.field_147299_f.field_71424_I);
        this.field_147300_g.field_72995_K = true;
        this.field_147299_f.func_71403_a(this.field_147300_g);
        this.field_147299_f.field_71439_g.field_71093_bK = p_147282_1_.func_149194_f();
        this.field_147299_f.func_147108_a(new GuiDownloadTerrain(this));
        this.field_147299_f.field_71439_g.func_145769_d(p_147282_1_.func_149197_c());
        this.field_147304_c = p_147282_1_.func_149193_h();
        this.field_147299_f.field_71442_b.func_78746_a(p_147282_1_.func_149198_e());
        this.field_147299_f.gameSettings.func_82879_c();
        this.field_147302_e.func_150725_a(new C17PacketCustomPayload("MC|Brand", ClientBrandRetriever.getClientModName().getBytes(Charsets.UTF_8)), new GenericFutureListener[0]);
    }

    public void func_147235_a(S0EPacketSpawnObject p_147235_1_)
    {
        double d0 = (double)p_147235_1_.func_148997_d() / 32.0D;
        double d1 = (double)p_147235_1_.func_148998_e() / 32.0D;
        double d2 = (double)p_147235_1_.func_148994_f() / 32.0D;
        Object object = null;

        if (p_147235_1_.func_148993_l() == 10)
        {
            object = EntityMinecart.func_94090_a(this.field_147300_g, d0, d1, d2, p_147235_1_.func_149009_m());
        }
        else if (p_147235_1_.func_148993_l() == 90)
        {
            Entity entity = this.field_147300_g.func_73045_a(p_147235_1_.func_149009_m());

            if (entity instanceof EntityPlayer)
            {
                object = new EntityFishHook(this.field_147300_g, d0, d1, d2, (EntityPlayer)entity);
            }

            p_147235_1_.func_149002_g(0);
        }
        else if (p_147235_1_.func_148993_l() == 60)
        {
            object = new EntityArrow(this.field_147300_g, d0, d1, d2);
        }
        else if (p_147235_1_.func_148993_l() == 61)
        {
            object = new EntitySnowball(this.field_147300_g, d0, d1, d2);
        }
        else if (p_147235_1_.func_148993_l() == 71)
        {
            object = new EntityItemFrame(this.field_147300_g, (int)d0, (int)d1, (int)d2, p_147235_1_.func_149009_m());
            p_147235_1_.func_149002_g(0);
        }
        else if (p_147235_1_.func_148993_l() == 77)
        {
            object = new EntityLeashKnot(this.field_147300_g, (int)d0, (int)d1, (int)d2);
            p_147235_1_.func_149002_g(0);
        }
        else if (p_147235_1_.func_148993_l() == 65)
        {
            object = new EntityEnderPearl(this.field_147300_g, d0, d1, d2);
        }
        else if (p_147235_1_.func_148993_l() == 72)
        {
            object = new EntityEnderEye(this.field_147300_g, d0, d1, d2);
        }
        else if (p_147235_1_.func_148993_l() == 76)
        {
            object = new EntityFireworkRocket(this.field_147300_g, d0, d1, d2, (ItemStack)null);
        }
        else if (p_147235_1_.func_148993_l() == 63)
        {
            object = new EntityLargeFireball(this.field_147300_g, d0, d1, d2, (double)p_147235_1_.func_149010_g() / 8000.0D, (double)p_147235_1_.func_149004_h() / 8000.0D, (double)p_147235_1_.func_148999_i() / 8000.0D);
            p_147235_1_.func_149002_g(0);
        }
        else if (p_147235_1_.func_148993_l() == 64)
        {
            object = new EntitySmallFireball(this.field_147300_g, d0, d1, d2, (double)p_147235_1_.func_149010_g() / 8000.0D, (double)p_147235_1_.func_149004_h() / 8000.0D, (double)p_147235_1_.func_148999_i() / 8000.0D);
            p_147235_1_.func_149002_g(0);
        }
        else if (p_147235_1_.func_148993_l() == 66)
        {
            object = new EntityWitherSkull(this.field_147300_g, d0, d1, d2, (double)p_147235_1_.func_149010_g() / 8000.0D, (double)p_147235_1_.func_149004_h() / 8000.0D, (double)p_147235_1_.func_148999_i() / 8000.0D);
            p_147235_1_.func_149002_g(0);
        }
        else if (p_147235_1_.func_148993_l() == 62)
        {
            object = new EntityEgg(this.field_147300_g, d0, d1, d2);
        }
        else if (p_147235_1_.func_148993_l() == 73)
        {
            object = new EntityPotion(this.field_147300_g, d0, d1, d2, p_147235_1_.func_149009_m());
            p_147235_1_.func_149002_g(0);
        }
        else if (p_147235_1_.func_148993_l() == 75)
        {
            object = new EntityExpBottle(this.field_147300_g, d0, d1, d2);
            p_147235_1_.func_149002_g(0);
        }
        else if (p_147235_1_.func_148993_l() == 1)
        {
            object = new EntityBoat(this.field_147300_g, d0, d1, d2);
        }
        else if (p_147235_1_.func_148993_l() == 50)
        {
            object = new EntityTNTPrimed(this.field_147300_g, d0, d1, d2, (EntityLivingBase)null);
        }
        else if (p_147235_1_.func_148993_l() == 51)
        {
            object = new EntityEnderCrystal(this.field_147300_g, d0, d1, d2);
        }
        else if (p_147235_1_.func_148993_l() == 2)
        {
            object = new EntityItem(this.field_147300_g, d0, d1, d2);
        }
        else if (p_147235_1_.func_148993_l() == 70)
        {
            object = new EntityFallingBlock(this.field_147300_g, d0, d1, d2, Block.func_149729_e(p_147235_1_.func_149009_m() & 65535), p_147235_1_.func_149009_m() >> 16);
            p_147235_1_.func_149002_g(0);
        }

        if (object != null)
        {
            ((Entity)object).field_70118_ct = p_147235_1_.func_148997_d();
            ((Entity)object).field_70117_cu = p_147235_1_.func_148998_e();
            ((Entity)object).field_70116_cv = p_147235_1_.func_148994_f();
            ((Entity)object).field_70125_A = (float)(p_147235_1_.func_149008_j() * 360) / 256.0F;
            ((Entity)object).field_70177_z = (float)(p_147235_1_.func_149006_k() * 360) / 256.0F;
            Entity[] aentity = ((Entity)object).func_70021_al();

            if (aentity != null)
            {
                int i = p_147235_1_.func_149001_c() - ((Entity)object).func_145782_y();

                for (int j = 0; j < aentity.length; ++j)
                {
                    aentity[j].func_145769_d(aentity[j].func_145782_y() + i);
                }
            }

            ((Entity)object).func_145769_d(p_147235_1_.func_149001_c());
            this.field_147300_g.func_73027_a(p_147235_1_.func_149001_c(), (Entity)object);

            if (p_147235_1_.func_149009_m() > 0)
            {
                if (p_147235_1_.func_148993_l() == 60)
                {
                    Entity entity1 = this.field_147300_g.func_73045_a(p_147235_1_.func_149009_m());

                    if (entity1 instanceof EntityLivingBase)
                    {
                        EntityArrow entityarrow = (EntityArrow)object;
                        entityarrow.field_70250_c = entity1;
                    }
                }

                ((Entity)object).func_70016_h((double)p_147235_1_.func_149010_g() / 8000.0D, (double)p_147235_1_.func_149004_h() / 8000.0D, (double)p_147235_1_.func_148999_i() / 8000.0D);
            }
        }
    }

    public void func_147286_a(S11PacketSpawnExperienceOrb p_147286_1_)
    {
        EntityXPOrb entityxporb = new EntityXPOrb(this.field_147300_g, (double)p_147286_1_.func_148984_d(), (double)p_147286_1_.func_148983_e(), (double)p_147286_1_.func_148982_f(), p_147286_1_.func_148986_g());
        entityxporb.field_70118_ct = p_147286_1_.func_148984_d();
        entityxporb.field_70117_cu = p_147286_1_.func_148983_e();
        entityxporb.field_70116_cv = p_147286_1_.func_148982_f();
        entityxporb.field_70177_z = 0.0F;
        entityxporb.field_70125_A = 0.0F;
        entityxporb.func_145769_d(p_147286_1_.func_148985_c());
        this.field_147300_g.func_73027_a(p_147286_1_.func_148985_c(), entityxporb);
    }

    public void func_147292_a(S2CPacketSpawnGlobalEntity p_147292_1_)
    {
        double d0 = (double)p_147292_1_.func_149051_d() / 32.0D;
        double d1 = (double)p_147292_1_.func_149050_e() / 32.0D;
        double d2 = (double)p_147292_1_.func_149049_f() / 32.0D;
        EntityLightningBolt entitylightningbolt = null;

        if (p_147292_1_.func_149053_g() == 1)
        {
            entitylightningbolt = new EntityLightningBolt(this.field_147300_g, d0, d1, d2);
        }

        if (entitylightningbolt != null)
        {
            entitylightningbolt.field_70118_ct = p_147292_1_.func_149051_d();
            entitylightningbolt.field_70117_cu = p_147292_1_.func_149050_e();
            entitylightningbolt.field_70116_cv = p_147292_1_.func_149049_f();
            entitylightningbolt.field_70177_z = 0.0F;
            entitylightningbolt.field_70125_A = 0.0F;
            entitylightningbolt.func_145769_d(p_147292_1_.func_149052_c());
            this.field_147300_g.func_72942_c(entitylightningbolt);
        }
    }

    public void func_147288_a(S10PacketSpawnPainting p_147288_1_)
    {
        EntityPainting entitypainting = new EntityPainting(this.field_147300_g, p_147288_1_.func_148964_d(), p_147288_1_.func_148963_e(), p_147288_1_.func_148962_f(), p_147288_1_.func_148966_g(), p_147288_1_.func_148961_h());
        this.field_147300_g.func_73027_a(p_147288_1_.func_148965_c(), entitypainting);
    }

    public void func_147244_a(S12PacketEntityVelocity p_147244_1_)
    {
        Entity entity = this.field_147300_g.func_73045_a(p_147244_1_.func_149412_c());

        if (entity != null)
        {
            entity.func_70016_h((double)p_147244_1_.func_149411_d() / 8000.0D, (double)p_147244_1_.func_149410_e() / 8000.0D, (double)p_147244_1_.func_149409_f() / 8000.0D);
        }
    }

    public void func_147284_a(S1CPacketEntityMetadata p_147284_1_)
    {
        Entity entity = this.field_147300_g.func_73045_a(p_147284_1_.func_149375_d());

        if (entity != null && p_147284_1_.func_149376_c() != null)
        {
            entity.func_70096_w().func_75687_a(p_147284_1_.func_149376_c());
        }
    }

    public void func_147237_a(S0CPacketSpawnPlayer p_147237_1_)
    {
        double d0 = (double)p_147237_1_.func_148942_f() / 32.0D;
        double d1 = (double)p_147237_1_.func_148949_g() / 32.0D;
        double d2 = (double)p_147237_1_.func_148946_h() / 32.0D;
        float f = (float)(p_147237_1_.func_148941_i() * 360) / 256.0F;
        float f1 = (float)(p_147237_1_.func_148945_j() * 360) / 256.0F;
        GameProfile gameprofile = p_147237_1_.func_148948_e();
        EntityOtherPlayerMP entityotherplayermp = new EntityOtherPlayerMP(this.field_147299_f.field_71441_e, p_147237_1_.func_148948_e());
        entityotherplayermp.field_70169_q = entityotherplayermp.field_70142_S = (double)(entityotherplayermp.field_70118_ct = p_147237_1_.func_148942_f());
        entityotherplayermp.field_70167_r = entityotherplayermp.field_70137_T = (double)(entityotherplayermp.field_70117_cu = p_147237_1_.func_148949_g());
        entityotherplayermp.field_70166_s = entityotherplayermp.field_70136_U = (double)(entityotherplayermp.field_70116_cv = p_147237_1_.func_148946_h());
        int i = p_147237_1_.func_148947_k();

        if (i == 0)
        {
            entityotherplayermp.field_71071_by.field_70462_a[entityotherplayermp.field_71071_by.field_70461_c] = null;
        }
        else
        {
            entityotherplayermp.field_71071_by.field_70462_a[entityotherplayermp.field_71071_by.field_70461_c] = new ItemStack(Item.func_150899_d(i), 1, 0);
        }

        entityotherplayermp.func_70080_a(d0, d1, d2, f, f1);
        this.field_147300_g.func_73027_a(p_147237_1_.func_148943_d(), entityotherplayermp);
        List list = p_147237_1_.func_148944_c();

        if (list != null)
        {
            entityotherplayermp.func_70096_w().func_75687_a(list);
        }
    }

    public void func_147275_a(S18PacketEntityTeleport p_147275_1_)
    {
        Entity entity = this.field_147300_g.func_73045_a(p_147275_1_.func_149451_c());

        if (entity != null)
        {
            entity.field_70118_ct = p_147275_1_.func_149449_d();
            entity.field_70117_cu = p_147275_1_.func_149448_e();
            entity.field_70116_cv = p_147275_1_.func_149446_f();
            double d0 = (double)entity.field_70118_ct / 32.0D;
            double d1 = (double)entity.field_70117_cu / 32.0D + 0.015625D;
            double d2 = (double)entity.field_70116_cv / 32.0D;
            float f = (float)(p_147275_1_.func_149450_g() * 360) / 256.0F;
            float f1 = (float)(p_147275_1_.func_149447_h() * 360) / 256.0F;
            entity.func_70056_a(d0, d1, d2, f, f1, 3);
        }
    }

    public void func_147257_a(S09PacketHeldItemChange p_147257_1_)
    {
        if (p_147257_1_.func_149385_c() >= 0 && p_147257_1_.func_149385_c() < InventoryPlayer.func_70451_h())
        {
            this.field_147299_f.field_71439_g.field_71071_by.field_70461_c = p_147257_1_.func_149385_c();
        }
    }

    public void func_147259_a(S14PacketEntity p_147259_1_)
    {
        Entity entity = p_147259_1_.func_149065_a(this.field_147300_g);

        if (entity != null)
        {
            entity.field_70118_ct += p_147259_1_.func_149062_c();
            entity.field_70117_cu += p_147259_1_.func_149061_d();
            entity.field_70116_cv += p_147259_1_.func_149064_e();
            double d0 = (double)entity.field_70118_ct / 32.0D;
            double d1 = (double)entity.field_70117_cu / 32.0D;
            double d2 = (double)entity.field_70116_cv / 32.0D;
            float f = p_147259_1_.func_149060_h() ? (float)(p_147259_1_.func_149066_f() * 360) / 256.0F : entity.field_70177_z;
            float f1 = p_147259_1_.func_149060_h() ? (float)(p_147259_1_.func_149063_g() * 360) / 256.0F : entity.field_70125_A;
            entity.func_70056_a(d0, d1, d2, f, f1, 3);
        }
    }

    public void func_147267_a(S19PacketEntityHeadLook p_147267_1_)
    {
        Entity entity = p_147267_1_.func_149381_a(this.field_147300_g);

        if (entity != null)
        {
            float f = (float)(p_147267_1_.func_149380_c() * 360) / 256.0F;
            entity.func_70034_d(f);
        }
    }

    public void func_147238_a(S13PacketDestroyEntities p_147238_1_)
    {
        for (int i = 0; i < p_147238_1_.func_149098_c().length; ++i)
        {
            this.field_147300_g.func_73028_b(p_147238_1_.func_149098_c()[i]);
        }
    }

    public void func_147258_a(S08PacketPlayerPosLook p_147258_1_)
    {
        EntityClientPlayerMP entityclientplayermp = this.field_147299_f.field_71439_g;
        double d0 = p_147258_1_.func_148932_c();
        double d1 = p_147258_1_.func_148928_d();
        double d2 = p_147258_1_.func_148933_e();
        float f = p_147258_1_.func_148931_f();
        float f1 = p_147258_1_.func_148930_g();
        entityclientplayermp.field_70139_V = 0.0F;
        entityclientplayermp.field_70159_w = entityclientplayermp.field_70181_x = entityclientplayermp.field_70179_y = 0.0D;
        entityclientplayermp.func_70080_a(d0, d1, d2, f, f1);
        this.field_147302_e.func_150725_a(new C03PacketPlayer.C06PacketPlayerPosLook(entityclientplayermp.field_70165_t, entityclientplayermp.field_70121_D.field_72338_b, entityclientplayermp.field_70163_u, entityclientplayermp.field_70161_v, p_147258_1_.func_148931_f(), p_147258_1_.func_148930_g(), p_147258_1_.func_148929_h()), new GenericFutureListener[0]);

        if (!this.field_147309_h)
        {
            this.field_147299_f.field_71439_g.field_70169_q = this.field_147299_f.field_71439_g.field_70165_t;
            this.field_147299_f.field_71439_g.field_70167_r = this.field_147299_f.field_71439_g.field_70163_u;
            this.field_147299_f.field_71439_g.field_70166_s = this.field_147299_f.field_71439_g.field_70161_v;
            this.field_147309_h = true;
            this.field_147299_f.func_147108_a((GuiScreen)null);
        }
    }

    public void func_147287_a(S22PacketMultiBlockChange p_147287_1_)
    {
        int i = p_147287_1_.func_148920_c().field_77276_a * 16;
        int j = p_147287_1_.func_148920_c().field_77275_b * 16;

        if (p_147287_1_.func_148921_d() != null)
        {
            DataInputStream datainputstream = new DataInputStream(new ByteArrayInputStream(p_147287_1_.func_148921_d()));

            try
            {
                for (int k = 0; k < p_147287_1_.func_148922_e(); ++k)
                {
                    short short1 = datainputstream.readShort();
                    short short2 = datainputstream.readShort();
                    int l = short2 >> 4 & 4095;
                    int i1 = short2 & 15;
                    int j1 = short1 >> 12 & 15;
                    int k1 = short1 >> 8 & 15;
                    int l1 = short1 & 255;
                    this.field_147300_g.func_147492_c(j1 + i, l1, k1 + j, Block.func_149729_e(l), i1);
                }
            }
            catch (IOException ioexception)
            {
                ;
            }
        }
    }

    public void func_147263_a(S21PacketChunkData p_147263_1_)
    {
        if (p_147263_1_.func_149274_i())
        {
            if (p_147263_1_.func_149276_g() == 0)
            {
                this.field_147300_g.func_73025_a(p_147263_1_.func_149273_e(), p_147263_1_.func_149271_f(), false);
                return;
            }

            this.field_147300_g.func_73025_a(p_147263_1_.func_149273_e(), p_147263_1_.func_149271_f(), true);
        }

        this.field_147300_g.func_73031_a(p_147263_1_.func_149273_e() << 4, 0, p_147263_1_.func_149271_f() << 4, (p_147263_1_.func_149273_e() << 4) + 15, 256, (p_147263_1_.func_149271_f() << 4) + 15);
        Chunk chunk = this.field_147300_g.func_72964_e(p_147263_1_.func_149273_e(), p_147263_1_.func_149271_f());
        chunk.func_76607_a(p_147263_1_.func_149272_d(), p_147263_1_.func_149276_g(), p_147263_1_.func_149270_h(), p_147263_1_.func_149274_i());
        this.field_147300_g.func_147458_c(p_147263_1_.func_149273_e() << 4, 0, p_147263_1_.func_149271_f() << 4, (p_147263_1_.func_149273_e() << 4) + 15, 256, (p_147263_1_.func_149271_f() << 4) + 15);

        if (!p_147263_1_.func_149274_i() || !(this.field_147300_g.field_73011_w instanceof WorldProviderSurface))
        {
            chunk.func_76613_n();
        }
    }

    public void func_147234_a(S23PacketBlockChange p_147234_1_)
    {
        this.field_147300_g.func_147492_c(p_147234_1_.func_148879_d(), p_147234_1_.func_148878_e(), p_147234_1_.func_148877_f(), p_147234_1_.func_148880_c(), p_147234_1_.func_148881_g());
    }

    public void func_147253_a(S40PacketDisconnect p_147253_1_)
    {
        this.field_147302_e.func_150718_a(p_147253_1_.func_149165_c());
    }

    public void func_147231_a(IChatComponent p_147231_1_)
    {
        this.field_147299_f.func_71403_a((WorldClient)null);

        if (this.field_147307_j != null)
        {
            if (this.field_147307_j instanceof GuiScreenRealmsProxy)
            {
                this.field_147299_f.func_147108_a((new DisconnectedOnlineScreen(((GuiScreenRealmsProxy)this.field_147307_j).func_154321_a(), "disconnect.lost", p_147231_1_)).getProxy());
            }
            else
            {
                this.field_147299_f.func_147108_a(new GuiDisconnected(this.field_147307_j, "disconnect.lost", p_147231_1_));
            }
        }
        else
        {
            this.field_147299_f.func_147108_a(new GuiDisconnected(new GuiMultiplayer(new GuiMainMenu()), "disconnect.lost", p_147231_1_));
        }
    }

    public void func_147297_a(Packet p_147297_1_)
    {
        this.field_147302_e.func_150725_a(p_147297_1_, new GenericFutureListener[0]);
    }

    public void func_147246_a(S0DPacketCollectItem p_147246_1_)
    {
        Entity entity = this.field_147300_g.func_73045_a(p_147246_1_.func_149354_c());
        Object object = (EntityLivingBase)this.field_147300_g.func_73045_a(p_147246_1_.func_149353_d());

        if (object == null)
        {
            object = this.field_147299_f.field_71439_g;
        }

        if (entity != null)
        {
            if (entity instanceof EntityXPOrb)
            {
                this.field_147300_g.func_72956_a(entity, "random.orb", 0.2F, ((this.field_147306_l.nextFloat() - this.field_147306_l.nextFloat()) * 0.7F + 1.0F) * 2.0F);
            }
            else
            {
                this.field_147300_g.func_72956_a(entity, "random.pop", 0.2F, ((this.field_147306_l.nextFloat() - this.field_147306_l.nextFloat()) * 0.7F + 1.0F) * 2.0F);
            }

            this.field_147299_f.field_71452_i.func_78873_a(new EntityPickupFX(this.field_147299_f.field_71441_e, entity, (Entity)object, -0.5F));
            this.field_147300_g.func_73028_b(p_147246_1_.func_149354_c());
        }
    }

    public void func_147251_a(S02PacketChat p_147251_1_)
    {
        this.field_147299_f.field_71456_v.func_146158_b().func_146227_a(p_147251_1_.func_148915_c());
    }

    public void func_147279_a(S0BPacketAnimation p_147279_1_)
    {
        Entity entity = this.field_147300_g.func_73045_a(p_147279_1_.func_148978_c());

        if (entity != null)
        {
            if (p_147279_1_.func_148977_d() == 0)
            {
                EntityLivingBase entitylivingbase = (EntityLivingBase)entity;
                entitylivingbase.func_71038_i();
            }
            else if (p_147279_1_.func_148977_d() == 1)
            {
                entity.func_70057_ab();
            }
            else if (p_147279_1_.func_148977_d() == 2)
            {
                EntityPlayer entityplayer = (EntityPlayer)entity;
                entityplayer.func_70999_a(false, false, false);
            }
            else if (p_147279_1_.func_148977_d() == 4)
            {
                this.field_147299_f.field_71452_i.func_78873_a(new EntityCrit2FX(this.field_147299_f.field_71441_e, entity));
            }
            else if (p_147279_1_.func_148977_d() == 5)
            {
                EntityCrit2FX entitycrit2fx = new EntityCrit2FX(this.field_147299_f.field_71441_e, entity, "magicCrit");
                this.field_147299_f.field_71452_i.func_78873_a(entitycrit2fx);
            }
        }
    }

    public void func_147278_a(S0APacketUseBed p_147278_1_)
    {
        p_147278_1_.func_149091_a(this.field_147300_g).func_71018_a(p_147278_1_.func_149092_c(), p_147278_1_.func_149090_d(), p_147278_1_.func_149089_e());
    }

    public void func_147281_a(S0FPacketSpawnMob p_147281_1_)
    {
        double d0 = (double)p_147281_1_.func_149023_f() / 32.0D;
        double d1 = (double)p_147281_1_.func_149034_g() / 32.0D;
        double d2 = (double)p_147281_1_.func_149029_h() / 32.0D;
        float f = (float)(p_147281_1_.func_149028_l() * 360) / 256.0F;
        float f1 = (float)(p_147281_1_.func_149030_m() * 360) / 256.0F;
        EntityLivingBase entitylivingbase = (EntityLivingBase)EntityList.func_75616_a(p_147281_1_.func_149025_e(), this.field_147299_f.field_71441_e);
        entitylivingbase.field_70118_ct = p_147281_1_.func_149023_f();
        entitylivingbase.field_70117_cu = p_147281_1_.func_149034_g();
        entitylivingbase.field_70116_cv = p_147281_1_.func_149029_h();
        entitylivingbase.field_70759_as = (float)(p_147281_1_.func_149032_n() * 360) / 256.0F;
        Entity[] aentity = entitylivingbase.func_70021_al();

        if (aentity != null)
        {
            int i = p_147281_1_.func_149024_d() - entitylivingbase.func_145782_y();

            for (int j = 0; j < aentity.length; ++j)
            {
                aentity[j].func_145769_d(aentity[j].func_145782_y() + i);
            }
        }

        entitylivingbase.func_145769_d(p_147281_1_.func_149024_d());
        entitylivingbase.func_70080_a(d0, d1, d2, f, f1);
        entitylivingbase.field_70159_w = (double)((float)p_147281_1_.func_149026_i() / 8000.0F);
        entitylivingbase.field_70181_x = (double)((float)p_147281_1_.func_149033_j() / 8000.0F);
        entitylivingbase.field_70179_y = (double)((float)p_147281_1_.func_149031_k() / 8000.0F);
        this.field_147300_g.func_73027_a(p_147281_1_.func_149024_d(), entitylivingbase);
        List list = p_147281_1_.func_149027_c();

        if (list != null)
        {
            entitylivingbase.func_70096_w().func_75687_a(list);
        }
    }

    public void func_147285_a(S03PacketTimeUpdate p_147285_1_)
    {
        this.field_147299_f.field_71441_e.func_82738_a(p_147285_1_.func_149366_c());
        this.field_147299_f.field_71441_e.func_72877_b(p_147285_1_.func_149365_d());
    }

    public void func_147271_a(S05PacketSpawnPosition p_147271_1_)
    {
        this.field_147299_f.field_71439_g.func_71063_a(new ChunkCoordinates(p_147271_1_.func_149360_c(), p_147271_1_.func_149359_d(), p_147271_1_.func_149358_e()), true);
        this.field_147299_f.field_71441_e.func_72912_H().func_76081_a(p_147271_1_.func_149360_c(), p_147271_1_.func_149359_d(), p_147271_1_.func_149358_e());
    }

    public void func_147243_a(S1BPacketEntityAttach p_147243_1_)
    {
        Object object = this.field_147300_g.func_73045_a(p_147243_1_.func_149403_d());
        Entity entity = this.field_147300_g.func_73045_a(p_147243_1_.func_149402_e());

        if (p_147243_1_.func_149404_c() == 0)
        {
            boolean flag = false;

            if (p_147243_1_.func_149403_d() == this.field_147299_f.field_71439_g.func_145782_y())
            {
                object = this.field_147299_f.field_71439_g;

                if (entity instanceof EntityBoat)
                {
                    ((EntityBoat)entity).func_70270_d(false);
                }

                flag = ((Entity)object).field_70154_o == null && entity != null;
            }
            else if (entity instanceof EntityBoat)
            {
                ((EntityBoat)entity).func_70270_d(true);
            }

            if (object == null)
            {
                return;
            }

            ((Entity)object).func_70078_a(entity);

            if (flag)
            {
                GameSettings gamesettings = this.field_147299_f.gameSettings;
                this.field_147299_f.field_71456_v.func_110326_a(I18n.func_135052_a("mount.onboard", new Object[] {GameSettings.func_74298_c(gamesettings.field_74311_E.getEventKey())}), false);
            }
        }
        else if (p_147243_1_.func_149404_c() == 1 && object != null && object instanceof EntityLiving)
        {
            if (entity != null)
            {
                ((EntityLiving)object).func_110162_b(entity, false);
            }
            else
            {
                ((EntityLiving)object).func_110160_i(false, false);
            }
        }
    }

    public void func_147236_a(S19PacketEntityStatus p_147236_1_)
    {
        Entity entity = p_147236_1_.func_149161_a(this.field_147300_g);

        if (entity != null)
        {
            entity.func_70103_a(p_147236_1_.func_149160_c());
        }
    }

    public void func_147249_a(S06PacketUpdateHealth p_147249_1_)
    {
        this.field_147299_f.field_71439_g.func_71150_b(p_147249_1_.func_149332_c());
        this.field_147299_f.field_71439_g.func_71024_bL().func_75114_a(p_147249_1_.func_149330_d());
        this.field_147299_f.field_71439_g.func_71024_bL().func_75119_b(p_147249_1_.func_149331_e());
    }

    public void func_147295_a(S1FPacketSetExperience p_147295_1_)
    {
        this.field_147299_f.field_71439_g.func_71152_a(p_147295_1_.func_149397_c(), p_147295_1_.func_149396_d(), p_147295_1_.func_149395_e());
    }

    public void func_147280_a(S07PacketRespawn p_147280_1_)
    {
        if (p_147280_1_.func_149082_c() != this.field_147299_f.field_71439_g.field_71093_bK)
        {
            this.field_147309_h = false;
            Scoreboard scoreboard = this.field_147300_g.func_96441_U();
            this.field_147300_g = new WorldClient(this, new WorldSettings(0L, p_147280_1_.func_149083_e(), false, this.field_147299_f.field_71441_e.func_72912_H().func_76093_s(), p_147280_1_.func_149080_f()), p_147280_1_.func_149082_c(), p_147280_1_.func_149081_d(), this.field_147299_f.field_71424_I);
            this.field_147300_g.func_96443_a(scoreboard);
            this.field_147300_g.field_72995_K = true;
            this.field_147299_f.func_71403_a(this.field_147300_g);
            this.field_147299_f.field_71439_g.field_71093_bK = p_147280_1_.func_149082_c();
            this.field_147299_f.func_147108_a(new GuiDownloadTerrain(this));
        }

        this.field_147299_f.func_71354_a(p_147280_1_.func_149082_c());
        this.field_147299_f.field_71442_b.func_78746_a(p_147280_1_.func_149083_e());
    }

    public void func_147283_a(S27PacketExplosion p_147283_1_)
    {
        Explosion explosion = new Explosion(this.field_147299_f.field_71441_e, (Entity)null, p_147283_1_.func_149148_f(), p_147283_1_.func_149143_g(), p_147283_1_.func_149145_h(), p_147283_1_.func_149146_i());
        explosion.field_77281_g = p_147283_1_.func_149150_j();
        explosion.func_77279_a(true);
        this.field_147299_f.field_71439_g.field_70159_w += (double)p_147283_1_.func_149149_c();
        this.field_147299_f.field_71439_g.field_70181_x += (double)p_147283_1_.func_149144_d();
        this.field_147299_f.field_71439_g.field_70179_y += (double)p_147283_1_.func_149147_e();
    }

    public void func_147265_a(S2DPacketOpenWindow p_147265_1_)
    {
        EntityClientPlayerMP entityclientplayermp = this.field_147299_f.field_71439_g;

        switch (p_147265_1_.func_148899_d())
        {
            case 0:
                entityclientplayermp.func_71007_a(new InventoryBasic(p_147265_1_.func_148902_e(), p_147265_1_.func_148900_g(), p_147265_1_.func_148898_f()));
                entityclientplayermp.field_71070_bA.field_75152_c = p_147265_1_.func_148901_c();
                break;
            case 1:
                entityclientplayermp.func_71058_b(MathHelper.func_76128_c(entityclientplayermp.field_70165_t), MathHelper.func_76128_c(entityclientplayermp.field_70163_u), MathHelper.func_76128_c(entityclientplayermp.field_70161_v));
                entityclientplayermp.field_71070_bA.field_75152_c = p_147265_1_.func_148901_c();
                break;
            case 2:
                TileEntityFurnace tileentityfurnace = new TileEntityFurnace();

                if (p_147265_1_.func_148900_g())
                {
                    tileentityfurnace.func_145951_a(p_147265_1_.func_148902_e());
                }

                entityclientplayermp.func_146101_a(tileentityfurnace);
                entityclientplayermp.field_71070_bA.field_75152_c = p_147265_1_.func_148901_c();
                break;
            case 3:
                TileEntityDispenser tileentitydispenser = new TileEntityDispenser();

                if (p_147265_1_.func_148900_g())
                {
                    tileentitydispenser.func_146018_a(p_147265_1_.func_148902_e());
                }

                entityclientplayermp.func_146102_a(tileentitydispenser);
                entityclientplayermp.field_71070_bA.field_75152_c = p_147265_1_.func_148901_c();
                break;
            case 4:
                entityclientplayermp.func_71002_c(MathHelper.func_76128_c(entityclientplayermp.field_70165_t), MathHelper.func_76128_c(entityclientplayermp.field_70163_u), MathHelper.func_76128_c(entityclientplayermp.field_70161_v), p_147265_1_.func_148900_g() ? p_147265_1_.func_148902_e() : null);
                entityclientplayermp.field_71070_bA.field_75152_c = p_147265_1_.func_148901_c();
                break;
            case 5:
                TileEntityBrewingStand tileentitybrewingstand = new TileEntityBrewingStand();

                if (p_147265_1_.func_148900_g())
                {
                    tileentitybrewingstand.func_145937_a(p_147265_1_.func_148902_e());
                }

                entityclientplayermp.func_146098_a(tileentitybrewingstand);
                entityclientplayermp.field_71070_bA.field_75152_c = p_147265_1_.func_148901_c();
                break;
            case 6:
                entityclientplayermp.func_71030_a(new NpcMerchant(entityclientplayermp), p_147265_1_.func_148900_g() ? p_147265_1_.func_148902_e() : null);
                entityclientplayermp.field_71070_bA.field_75152_c = p_147265_1_.func_148901_c();
                break;
            case 7:
                TileEntityBeacon tileentitybeacon = new TileEntityBeacon();
                entityclientplayermp.func_146104_a(tileentitybeacon);

                if (p_147265_1_.func_148900_g())
                {
                    tileentitybeacon.func_145999_a(p_147265_1_.func_148902_e());
                }

                entityclientplayermp.field_71070_bA.field_75152_c = p_147265_1_.func_148901_c();
                break;
            case 8:
                entityclientplayermp.func_82244_d(MathHelper.func_76128_c(entityclientplayermp.field_70165_t), MathHelper.func_76128_c(entityclientplayermp.field_70163_u), MathHelper.func_76128_c(entityclientplayermp.field_70161_v));
                entityclientplayermp.field_71070_bA.field_75152_c = p_147265_1_.func_148901_c();
                break;
            case 9:
                TileEntityHopper tileentityhopper = new TileEntityHopper();

                if (p_147265_1_.func_148900_g())
                {
                    tileentityhopper.func_145886_a(p_147265_1_.func_148902_e());
                }

                entityclientplayermp.func_146093_a(tileentityhopper);
                entityclientplayermp.field_71070_bA.field_75152_c = p_147265_1_.func_148901_c();
                break;
            case 10:
                TileEntityDropper tileentitydropper = new TileEntityDropper();

                if (p_147265_1_.func_148900_g())
                {
                    tileentitydropper.func_146018_a(p_147265_1_.func_148902_e());
                }

                entityclientplayermp.func_146102_a(tileentitydropper);
                entityclientplayermp.field_71070_bA.field_75152_c = p_147265_1_.func_148901_c();
                break;
            case 11:
                Entity entity = this.field_147300_g.func_73045_a(p_147265_1_.func_148897_h());

                if (entity != null && entity instanceof EntityHorse)
                {
                    entityclientplayermp.func_110298_a((EntityHorse)entity, new AnimalChest(p_147265_1_.func_148902_e(), p_147265_1_.func_148900_g(), p_147265_1_.func_148898_f()));
                    entityclientplayermp.field_71070_bA.field_75152_c = p_147265_1_.func_148901_c();
                }
        }
    }

    public void func_147266_a(S2FPacketSetSlot p_147266_1_)
    {
        EntityClientPlayerMP entityclientplayermp = this.field_147299_f.field_71439_g;

        if (p_147266_1_.func_149175_c() == -1)
        {
            entityclientplayermp.field_71071_by.func_70437_b(p_147266_1_.func_149174_e());
        }
        else
        {
            boolean flag = false;

            if (this.field_147299_f.field_71462_r instanceof GuiContainerCreative)
            {
                GuiContainerCreative guicontainercreative = (GuiContainerCreative)this.field_147299_f.field_71462_r;
                flag = guicontainercreative.func_147056_g() != CreativeTabs.field_78036_m.func_78021_a();
            }

            if (p_147266_1_.func_149175_c() == 0 && p_147266_1_.func_149173_d() >= 36 && p_147266_1_.func_149173_d() < 45)
            {
                ItemStack itemstack = entityclientplayermp.field_71069_bz.func_75139_a(p_147266_1_.func_149173_d()).func_75211_c();

                if (p_147266_1_.func_149174_e() != null && (itemstack == null || itemstack.field_77994_a < p_147266_1_.func_149174_e().field_77994_a))
                {
                    p_147266_1_.func_149174_e().field_77992_b = 5;
                }

                entityclientplayermp.field_71069_bz.func_75141_a(p_147266_1_.func_149173_d(), p_147266_1_.func_149174_e());
            }
            else if (p_147266_1_.func_149175_c() == entityclientplayermp.field_71070_bA.field_75152_c && (p_147266_1_.func_149175_c() != 0 || !flag))
            {
                entityclientplayermp.field_71070_bA.func_75141_a(p_147266_1_.func_149173_d(), p_147266_1_.func_149174_e());
            }
        }
    }

    public void func_147239_a(S32PacketConfirmTransaction p_147239_1_)
    {
        Container container = null;
        EntityClientPlayerMP entityclientplayermp = this.field_147299_f.field_71439_g;

        if (p_147239_1_.func_148889_c() == 0)
        {
            container = entityclientplayermp.field_71069_bz;
        }
        else if (p_147239_1_.func_148889_c() == entityclientplayermp.field_71070_bA.field_75152_c)
        {
            container = entityclientplayermp.field_71070_bA;
        }

        if (container != null && !p_147239_1_.func_148888_e())
        {
            this.func_147297_a(new C0FPacketConfirmTransaction(p_147239_1_.func_148889_c(), p_147239_1_.func_148890_d(), true));
        }
    }

    public void func_147241_a(S30PacketWindowItems p_147241_1_)
    {
        EntityClientPlayerMP entityclientplayermp = this.field_147299_f.field_71439_g;

        if (p_147241_1_.func_148911_c() == 0)
        {
            entityclientplayermp.field_71069_bz.func_75131_a(p_147241_1_.func_148910_d());
        }
        else if (p_147241_1_.func_148911_c() == entityclientplayermp.field_71070_bA.field_75152_c)
        {
            entityclientplayermp.field_71070_bA.func_75131_a(p_147241_1_.func_148910_d());
        }
    }

    public void func_147268_a(S36PacketSignEditorOpen p_147268_1_)
    {
        Object object = this.field_147300_g.func_147438_o(p_147268_1_.func_149129_c(), p_147268_1_.func_149128_d(), p_147268_1_.func_149127_e());

        if (object == null)
        {
            object = new TileEntitySign();
            ((TileEntity)object).func_145834_a(this.field_147300_g);
            ((TileEntity)object).field_145851_c = p_147268_1_.func_149129_c();
            ((TileEntity)object).field_145848_d = p_147268_1_.func_149128_d();
            ((TileEntity)object).field_145849_e = p_147268_1_.func_149127_e();
        }

        this.field_147299_f.field_71439_g.func_146100_a((TileEntity)object);
    }

    public void func_147248_a(S33PacketUpdateSign p_147248_1_)
    {
        boolean flag = false;

        if (this.field_147299_f.field_71441_e.func_72899_e(p_147248_1_.func_149346_c(), p_147248_1_.func_149345_d(), p_147248_1_.func_149344_e()))
        {
            TileEntity tileentity = this.field_147299_f.field_71441_e.func_147438_o(p_147248_1_.func_149346_c(), p_147248_1_.func_149345_d(), p_147248_1_.func_149344_e());

            if (tileentity instanceof TileEntitySign)
            {
                TileEntitySign tileentitysign = (TileEntitySign)tileentity;

                if (tileentitysign.func_145914_a())
                {
                    for (int i = 0; i < 4; ++i)
                    {
                        tileentitysign.field_145915_a[i] = p_147248_1_.func_149347_f()[i];
                    }

                    tileentitysign.func_70296_d();
                }

                flag = true;
            }
        }

        if (!flag && this.field_147299_f.field_71439_g != null)
        {
            this.field_147299_f.field_71439_g.func_145747_a(new ChatComponentText("Unable to locate sign at " + p_147248_1_.func_149346_c() + ", " + p_147248_1_.func_149345_d() + ", " + p_147248_1_.func_149344_e()));
        }
    }

    public void func_147273_a(S35PacketUpdateTileEntity p_147273_1_)
    {
        if (this.field_147299_f.field_71441_e.func_72899_e(p_147273_1_.func_148856_c(), p_147273_1_.func_148855_d(), p_147273_1_.func_148854_e()))
        {
            TileEntity tileentity = this.field_147299_f.field_71441_e.func_147438_o(p_147273_1_.func_148856_c(), p_147273_1_.func_148855_d(), p_147273_1_.func_148854_e());

            if (tileentity != null)
            {
                if (p_147273_1_.func_148853_f() == 1 && tileentity instanceof TileEntityMobSpawner)
                {
                    tileentity.func_145839_a(p_147273_1_.func_148857_g());
                }
                else if (p_147273_1_.func_148853_f() == 2 && tileentity instanceof TileEntityCommandBlock)
                {
                    tileentity.func_145839_a(p_147273_1_.func_148857_g());
                }
                else if (p_147273_1_.func_148853_f() == 3 && tileentity instanceof TileEntityBeacon)
                {
                    tileentity.func_145839_a(p_147273_1_.func_148857_g());
                }
                else if (p_147273_1_.func_148853_f() == 4 && tileentity instanceof TileEntitySkull)
                {
                    tileentity.func_145839_a(p_147273_1_.func_148857_g());
                }
                else if (p_147273_1_.func_148853_f() == 5 && tileentity instanceof TileEntityFlowerPot)
                {
                    tileentity.func_145839_a(p_147273_1_.func_148857_g());
                }
            }
        }
    }

    public void func_147245_a(S31PacketWindowProperty p_147245_1_)
    {
        EntityClientPlayerMP entityclientplayermp = this.field_147299_f.field_71439_g;

        if (entityclientplayermp.field_71070_bA != null && entityclientplayermp.field_71070_bA.field_75152_c == p_147245_1_.func_149182_c())
        {
            entityclientplayermp.field_71070_bA.func_75137_b(p_147245_1_.func_149181_d(), p_147245_1_.func_149180_e());
        }
    }

    public void func_147242_a(S04PacketEntityEquipment p_147242_1_)
    {
        Entity entity = this.field_147300_g.func_73045_a(p_147242_1_.func_149389_d());

        if (entity != null)
        {
            entity.func_70062_b(p_147242_1_.func_149388_e(), p_147242_1_.func_149390_c());
        }
    }

    public void func_147276_a(S2EPacketCloseWindow p_147276_1_)
    {
        this.field_147299_f.field_71439_g.func_92015_f();
    }

    public void func_147261_a(S24PacketBlockAction p_147261_1_)
    {
        this.field_147299_f.field_71441_e.func_147452_c(p_147261_1_.func_148867_d(), p_147261_1_.func_148866_e(), p_147261_1_.func_148865_f(), p_147261_1_.func_148868_c(), p_147261_1_.func_148869_g(), p_147261_1_.func_148864_h());
    }

    public void func_147294_a(S25PacketBlockBreakAnim p_147294_1_)
    {
        this.field_147299_f.field_71441_e.func_147443_d(p_147294_1_.func_148845_c(), p_147294_1_.func_148844_d(), p_147294_1_.func_148843_e(), p_147294_1_.func_148842_f(), p_147294_1_.func_148846_g());
    }

    public void func_147269_a(S26PacketMapChunkBulk p_147269_1_)
    {
        for (int i = 0; i < p_147269_1_.func_149254_d(); ++i)
        {
            int j = p_147269_1_.func_149255_a(i);
            int k = p_147269_1_.func_149253_b(i);
            this.field_147300_g.func_73025_a(j, k, true);
            this.field_147300_g.func_73031_a(j << 4, 0, k << 4, (j << 4) + 15, 256, (k << 4) + 15);
            Chunk chunk = this.field_147300_g.func_72964_e(j, k);
            chunk.func_76607_a(p_147269_1_.func_149256_c(i), p_147269_1_.func_149252_e()[i], p_147269_1_.func_149257_f()[i], true);
            this.field_147300_g.func_147458_c(j << 4, 0, k << 4, (j << 4) + 15, 256, (k << 4) + 15);

            if (!(this.field_147300_g.field_73011_w instanceof WorldProviderSurface))
            {
                chunk.func_76613_n();
            }
        }
    }

    public void func_147252_a(S2BPacketChangeGameState p_147252_1_)
    {
        EntityClientPlayerMP entityclientplayermp = this.field_147299_f.field_71439_g;
        int i = p_147252_1_.func_149138_c();
        float f = p_147252_1_.func_149137_d();
        int j = MathHelper.func_76141_d(f + 0.5F);

        if (i >= 0 && i < S2BPacketChangeGameState.field_149142_a.length && S2BPacketChangeGameState.field_149142_a[i] != null)
        {
            entityclientplayermp.func_146105_b(new ChatComponentTranslation(S2BPacketChangeGameState.field_149142_a[i], new Object[0]));
        }

        if (i == 1)
        {
            this.field_147300_g.func_72912_H().func_76084_b(true);
            this.field_147300_g.func_72894_k(0.0F);
        }
        else if (i == 2)
        {
            this.field_147300_g.func_72912_H().func_76084_b(false);
            this.field_147300_g.func_72894_k(1.0F);
        }
        else if (i == 3)
        {
            this.field_147299_f.field_71442_b.func_78746_a(WorldSettings.GameType.func_77146_a(j));
        }
        else if (i == 4)
        {
            this.field_147299_f.func_147108_a(new GuiWinGame());
        }
        else if (i == 5)
        {
            GameSettings gamesettings = this.field_147299_f.gameSettings;

            if (f == 0.0F)
            {
                this.field_147299_f.func_147108_a(new GuiScreenDemo());
            }
            else if (f == 101.0F)
            {
                this.field_147299_f.field_71456_v.func_146158_b().func_146227_a(new ChatComponentTranslation("demo.help.movement", new Object[] {GameSettings.func_74298_c(gamesettings.field_74351_w.getEventKey()), GameSettings.func_74298_c(gamesettings.field_74370_x.getEventKey()), GameSettings.func_74298_c(gamesettings.field_74368_y.getEventKey()), GameSettings.func_74298_c(gamesettings.field_74366_z.getEventKey())}));
            }
            else if (f == 102.0F)
            {
                this.field_147299_f.field_71456_v.func_146158_b().func_146227_a(new ChatComponentTranslation("demo.help.jump", new Object[] {GameSettings.func_74298_c(gamesettings.field_74314_A.getEventKey())}));
            }
            else if (f == 103.0F)
            {
                this.field_147299_f.field_71456_v.func_146158_b().func_146227_a(new ChatComponentTranslation("demo.help.inventory", new Object[] {GameSettings.func_74298_c(gamesettings.field_151445_Q.getEventKey())}));
            }
        }
        else if (i == 6)
        {
            this.field_147300_g.func_72980_b(entityclientplayermp.field_70165_t, entityclientplayermp.field_70163_u + (double)entityclientplayermp.func_70047_e(), entityclientplayermp.field_70161_v, "random.successful_hit", 0.18F, 0.45F, false);
        }
        else if (i == 7)
        {
            this.field_147300_g.func_72894_k(f);
        }
        else if (i == 8)
        {
            this.field_147300_g.func_147442_i(f);
        }
    }

    public void func_147264_a(S34PacketMaps p_147264_1_)
    {
        MapData mapdata = ItemMap.func_150912_a(p_147264_1_.func_149188_c(), this.field_147299_f.field_71441_e);
        mapdata.func_76192_a(p_147264_1_.func_149187_d());
        this.field_147299_f.field_71460_t.func_147701_i().func_148246_a(mapdata);
    }

    public void func_147277_a(S28PacketEffect p_147277_1_)
    {
        if (p_147277_1_.func_149244_c())
        {
            this.field_147299_f.field_71441_e.func_82739_e(p_147277_1_.func_149242_d(), p_147277_1_.func_149240_f(), p_147277_1_.func_149243_g(), p_147277_1_.func_149239_h(), p_147277_1_.func_149241_e());
        }
        else
        {
            this.field_147299_f.field_71441_e.func_72926_e(p_147277_1_.func_149242_d(), p_147277_1_.func_149240_f(), p_147277_1_.func_149243_g(), p_147277_1_.func_149239_h(), p_147277_1_.func_149241_e());
        }
    }

    public void func_147293_a(S37PacketStatistics p_147293_1_)
    {
        boolean flag = false;
        StatBase statbase;
        int i;

        for (Iterator iterator = p_147293_1_.func_148974_c().entrySet().iterator(); iterator.hasNext(); this.field_147299_f.field_71439_g.func_146107_m().func_150873_a(this.field_147299_f.field_71439_g, statbase, i))
        {
            Entry entry = (Entry)iterator.next();
            statbase = (StatBase)entry.getKey();
            i = ((Integer)entry.getValue()).intValue();

            if (statbase.func_75967_d() && i > 0)
            {
                if (this.field_147308_k && this.field_147299_f.field_71439_g.func_146107_m().func_77444_a(statbase) == 0)
                {
                    Achievement achievement = (Achievement)statbase;
                    this.field_147299_f.field_71458_u.func_146256_a(achievement);
                    this.field_147299_f.func_152346_Z().func_152911_a(new MetadataAchievement(achievement), 0L);

                    if (statbase == AchievementList.OPEN_INVENTORY)
                    {
                        this.field_147299_f.gameSettings.field_151441_H = false;
                        this.field_147299_f.gameSettings.func_74303_b();
                    }
                }

                flag = true;
            }
        }

        if (!this.field_147308_k && !flag && this.field_147299_f.gameSettings.field_151441_H)
        {
            this.field_147299_f.field_71458_u.func_146255_b(AchievementList.OPEN_INVENTORY);
        }

        this.field_147308_k = true;

        if (this.field_147299_f.field_71462_r instanceof IProgressMeter)
        {
            ((IProgressMeter)this.field_147299_f.field_71462_r).func_146509_g();
        }
    }

    public void func_147260_a(S1DPacketEntityEffect p_147260_1_)
    {
        Entity entity = this.field_147300_g.func_73045_a(p_147260_1_.func_149426_d());

        if (entity instanceof EntityLivingBase)
        {
            PotionEffect potioneffect = new PotionEffect(p_147260_1_.func_149427_e(), p_147260_1_.func_149425_g(), p_147260_1_.func_149428_f());
            potioneffect.func_100012_b(p_147260_1_.func_149429_c());
            ((EntityLivingBase)entity).func_70690_d(potioneffect);
        }
    }

    public void func_147262_a(S1EPacketRemoveEntityEffect p_147262_1_)
    {
        Entity entity = this.field_147300_g.func_73045_a(p_147262_1_.func_149076_c());

        if (entity instanceof EntityLivingBase)
        {
            ((EntityLivingBase)entity).func_70618_n(p_147262_1_.func_149075_d());
        }
    }

    public void func_147256_a(S38PacketPlayerListItem p_147256_1_)
    {
        GuiPlayerInfo guiplayerinfo = (GuiPlayerInfo)this.field_147310_i.get(p_147256_1_.func_149122_c());

        if (guiplayerinfo == null && p_147256_1_.func_149121_d())
        {
            guiplayerinfo = new GuiPlayerInfo(p_147256_1_.func_149122_c());
            this.field_147310_i.put(p_147256_1_.func_149122_c(), guiplayerinfo);
            this.field_147303_b.add(guiplayerinfo);
        }

        if (guiplayerinfo != null && !p_147256_1_.func_149121_d())
        {
            this.field_147310_i.remove(p_147256_1_.func_149122_c());
            this.field_147303_b.remove(guiplayerinfo);
        }

        if (guiplayerinfo != null && p_147256_1_.func_149121_d())
        {
            guiplayerinfo.field_78829_b = p_147256_1_.func_149120_e();
        }
    }

    public void func_147272_a(S00PacketKeepAlive p_147272_1_)
    {
        this.func_147297_a(new C00PacketKeepAlive(p_147272_1_.func_149134_c()));
    }

    public void func_147232_a(EnumConnectionState p_147232_1_, EnumConnectionState p_147232_2_)
    {
        throw new IllegalStateException("Unexpected protocol change!");
    }

    public void func_147270_a(S39PacketPlayerAbilities p_147270_1_)
    {
        EntityClientPlayerMP entityclientplayermp = this.field_147299_f.field_71439_g;
        entityclientplayermp.field_71075_bZ.field_75100_b = p_147270_1_.func_149106_d();
        entityclientplayermp.field_71075_bZ.field_75098_d = p_147270_1_.func_149103_f();
        entityclientplayermp.field_71075_bZ.field_75102_a = p_147270_1_.func_149112_c();
        entityclientplayermp.field_71075_bZ.field_75101_c = p_147270_1_.func_149105_e();
        entityclientplayermp.field_71075_bZ.func_75092_a(p_147270_1_.func_149101_g());
        entityclientplayermp.field_71075_bZ.func_82877_b(p_147270_1_.func_149107_h());
    }

    public void func_147274_a(S3APacketTabComplete p_147274_1_)
    {
        String[] astring = p_147274_1_.func_149630_c();

        if (this.field_147299_f.field_71462_r instanceof GuiChat)
        {
            GuiChat guichat = (GuiChat)this.field_147299_f.field_71462_r;
            guichat.func_146406_a(astring);
        }
    }

    public void func_147255_a(S29PacketSoundEffect p_147255_1_)
    {
        this.field_147299_f.field_71441_e.func_72980_b(p_147255_1_.func_149207_d(), p_147255_1_.func_149211_e(), p_147255_1_.func_149210_f(), p_147255_1_.func_149212_c(), p_147255_1_.func_149208_g(), p_147255_1_.func_149209_h(), false);
    }

    public void func_147240_a(S3FPacketCustomPayload p_147240_1_)
    {
        if ("MC|TrList".equals(p_147240_1_.func_149169_c()))
        {
            ByteBuf bytebuf = Unpooled.wrappedBuffer(p_147240_1_.func_149168_d());

            try
            {
                int i = bytebuf.readInt();
                GuiScreen guiscreen = this.field_147299_f.field_71462_r;

                if (guiscreen != null && guiscreen instanceof GuiMerchant && i == this.field_147299_f.field_71439_g.field_71070_bA.field_75152_c)
                {
                    IMerchant imerchant = ((GuiMerchant)guiscreen).func_147035_g();
                    MerchantRecipeList merchantrecipelist = MerchantRecipeList.func_151390_b(new PacketBuffer(bytebuf));
                    imerchant.func_70930_a(merchantrecipelist);
                }
            }
            catch (IOException ioexception)
            {
                field_147301_d.error("Couldn\'t load trade info", ioexception);
            }
            finally
            {
                bytebuf.release();
            }
        }
        else if ("MC|Brand".equals(p_147240_1_.func_149169_c()))
        {
            this.field_147299_f.field_71439_g.func_142020_c(new String(p_147240_1_.func_149168_d(), Charsets.UTF_8));
        }
        else if ("MC|RPack".equals(p_147240_1_.func_149169_c()))
        {
            final String s = new String(p_147240_1_.func_149168_d(), Charsets.UTF_8);

            if (this.field_147299_f.func_147104_D() != null && this.field_147299_f.func_147104_D().func_152586_b() == ServerData.ServerResourceMode.ENABLED)
            {
                this.field_147299_f.func_110438_M().func_148526_a(s);
            }
            else if (this.field_147299_f.func_147104_D() == null || this.field_147299_f.func_147104_D().func_152586_b() == ServerData.ServerResourceMode.PROMPT)
            {
                this.field_147299_f.func_147108_a(new GuiYesNo(new GuiYesNoCallback()
                {
                    private static final String __OBFID = "CL_00000879";
                    public void func_73878_a(boolean p_73878_1_, int p_73878_2_)
                    {
                        NetHandlerPlayClient.this.field_147299_f = Minecraft.func_71410_x();

                        if (NetHandlerPlayClient.this.field_147299_f.func_147104_D() != null)
                        {
                            NetHandlerPlayClient.this.field_147299_f.func_147104_D().func_152584_a(ServerData.ServerResourceMode.ENABLED);
                            ServerList.func_147414_b(NetHandlerPlayClient.this.field_147299_f.func_147104_D());
                        }

                        if (p_73878_1_)
                        {
                            NetHandlerPlayClient.this.field_147299_f.func_110438_M().func_148526_a(s);
                        }

                        NetHandlerPlayClient.this.field_147299_f.func_147108_a((GuiScreen)null);
                    }
                }, I18n.func_135052_a("multiplayer.texturePrompt.line1", new Object[0]), I18n.func_135052_a("multiplayer.texturePrompt.line2", new Object[0]), 0));
            }
        }
    }

    public void func_147291_a(S3BPacketScoreboardObjective p_147291_1_)
    {
        Scoreboard scoreboard = this.field_147300_g.func_96441_U();
        ScoreObjective scoreobjective;

        if (p_147291_1_.func_149338_e() == 0)
        {
            scoreobjective = scoreboard.func_96535_a(p_147291_1_.func_149339_c(), IScoreObjectiveCriteria.field_96641_b);
            scoreobjective.func_96681_a(p_147291_1_.func_149337_d());
        }
        else
        {
            scoreobjective = scoreboard.func_96518_b(p_147291_1_.func_149339_c());

            if (p_147291_1_.func_149338_e() == 1)
            {
                scoreboard.func_96519_k(scoreobjective);
            }
            else if (p_147291_1_.func_149338_e() == 2)
            {
                scoreobjective.func_96681_a(p_147291_1_.func_149337_d());
            }
        }
    }

    public void func_147250_a(S3CPacketUpdateScore p_147250_1_)
    {
        Scoreboard scoreboard = this.field_147300_g.func_96441_U();
        ScoreObjective scoreobjective = scoreboard.func_96518_b(p_147250_1_.func_149321_d());

        if (p_147250_1_.func_149322_f() == 0)
        {
            Score score = scoreboard.func_96529_a(p_147250_1_.func_149324_c(), scoreobjective);
            score.func_96647_c(p_147250_1_.func_149323_e());
        }
        else if (p_147250_1_.func_149322_f() == 1)
        {
            scoreboard.func_96515_c(p_147250_1_.func_149324_c());
        }
    }

    public void func_147254_a(S3DPacketDisplayScoreboard p_147254_1_)
    {
        Scoreboard scoreboard = this.field_147300_g.func_96441_U();

        if (p_147254_1_.func_149370_d().length() == 0)
        {
            scoreboard.func_96530_a(p_147254_1_.func_149371_c(), (ScoreObjective)null);
        }
        else
        {
            ScoreObjective scoreobjective = scoreboard.func_96518_b(p_147254_1_.func_149370_d());
            scoreboard.func_96530_a(p_147254_1_.func_149371_c(), scoreobjective);
        }
    }

    public void func_147247_a(S3EPacketTeams p_147247_1_)
    {
        Scoreboard scoreboard = this.field_147300_g.func_96441_U();
        ScorePlayerTeam scoreplayerteam;

        if (p_147247_1_.func_149307_h() == 0)
        {
            scoreplayerteam = scoreboard.func_96527_f(p_147247_1_.func_149312_c());
        }
        else
        {
            scoreplayerteam = scoreboard.func_96508_e(p_147247_1_.func_149312_c());
        }

        if (p_147247_1_.func_149307_h() == 0 || p_147247_1_.func_149307_h() == 2)
        {
            scoreplayerteam.func_96664_a(p_147247_1_.func_149306_d());
            scoreplayerteam.func_96666_b(p_147247_1_.func_149311_e());
            scoreplayerteam.func_96662_c(p_147247_1_.func_149309_f());
            scoreplayerteam.func_98298_a(p_147247_1_.func_149308_i());
        }

        Iterator iterator;
        String s;

        if (p_147247_1_.func_149307_h() == 0 || p_147247_1_.func_149307_h() == 3)
        {
            iterator = p_147247_1_.func_149310_g().iterator();

            while (iterator.hasNext())
            {
                s = (String)iterator.next();
                scoreboard.func_151392_a(s, p_147247_1_.func_149312_c());
            }
        }

        if (p_147247_1_.func_149307_h() == 4)
        {
            iterator = p_147247_1_.func_149310_g().iterator();

            while (iterator.hasNext())
            {
                s = (String)iterator.next();
                scoreboard.func_96512_b(s, scoreplayerteam);
            }
        }

        if (p_147247_1_.func_149307_h() == 1)
        {
            scoreboard.func_96511_d(scoreplayerteam);
        }
    }

    public void func_147289_a(S2APacketParticles p_147289_1_)
    {
        if (p_147289_1_.func_149222_k() == 0)
        {
            double d0 = (double)(p_147289_1_.func_149227_j() * p_147289_1_.func_149221_g());
            double d2 = (double)(p_147289_1_.func_149227_j() * p_147289_1_.func_149224_h());
            double d4 = (double)(p_147289_1_.func_149227_j() * p_147289_1_.func_149223_i());
            this.field_147300_g.func_72869_a(p_147289_1_.func_149228_c(), p_147289_1_.func_149220_d(), p_147289_1_.func_149226_e(), p_147289_1_.func_149225_f(), d0, d2, d4);
        }
        else
        {
            for (int i = 0; i < p_147289_1_.func_149222_k(); ++i)
            {
                double d1 = this.field_147306_l.nextGaussian() * (double)p_147289_1_.func_149221_g();
                double d3 = this.field_147306_l.nextGaussian() * (double)p_147289_1_.func_149224_h();
                double d5 = this.field_147306_l.nextGaussian() * (double)p_147289_1_.func_149223_i();
                double d6 = this.field_147306_l.nextGaussian() * (double)p_147289_1_.func_149227_j();
                double d7 = this.field_147306_l.nextGaussian() * (double)p_147289_1_.func_149227_j();
                double d8 = this.field_147306_l.nextGaussian() * (double)p_147289_1_.func_149227_j();
                this.field_147300_g.func_72869_a(p_147289_1_.func_149228_c(), p_147289_1_.func_149220_d() + d1, p_147289_1_.func_149226_e() + d3, p_147289_1_.func_149225_f() + d5, d6, d7, d8);
            }
        }
    }

    public void func_147290_a(S20PacketEntityProperties p_147290_1_)
    {
        Entity entity = this.field_147300_g.func_73045_a(p_147290_1_.func_149442_c());

        if (entity != null)
        {
            if (!(entity instanceof EntityLivingBase))
            {
                throw new IllegalStateException("Server tried to update attributes of a non-living entity (actually: " + entity + ")");
            }
            else
            {
                BaseAttributeMap baseattributemap = ((EntityLivingBase)entity).func_110140_aT();
                Iterator iterator = p_147290_1_.func_149441_d().iterator();

                while (iterator.hasNext())
                {
                    S20PacketEntityProperties.Snapshot snapshot = (S20PacketEntityProperties.Snapshot)iterator.next();
                    IAttributeInstance iattributeinstance = baseattributemap.func_111152_a(snapshot.func_151409_a());

                    if (iattributeinstance == null)
                    {
                        iattributeinstance = baseattributemap.func_111150_b(new RangedAttribute(snapshot.func_151409_a(), 0.0D, 2.2250738585072014E-308D, Double.MAX_VALUE));
                    }

                    iattributeinstance.func_111128_a(snapshot.func_151410_b());
                    iattributeinstance.func_142049_d();
                    Iterator iterator1 = snapshot.func_151408_c().iterator();

                    while (iterator1.hasNext())
                    {
                        AttributeModifier attributemodifier = (AttributeModifier)iterator1.next();
                        iattributeinstance.func_111121_a(attributemodifier);
                    }
                }
            }
        }
    }

    public NetworkManager func_147298_b()
    {
        return this.field_147302_e;
    }
}