package net.minecraft.network.play;

import net.minecraft.network.INetHandler;
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

public interface INetHandlerPlayClient extends INetHandler
{
    void func_147235_a(S0EPacketSpawnObject p_147235_1_);

    void func_147286_a(S11PacketSpawnExperienceOrb p_147286_1_);

    void func_147292_a(S2CPacketSpawnGlobalEntity p_147292_1_);

    void func_147281_a(S0FPacketSpawnMob p_147281_1_);

    void func_147291_a(S3BPacketScoreboardObjective p_147291_1_);

    void func_147288_a(S10PacketSpawnPainting p_147288_1_);

    void func_147237_a(S0CPacketSpawnPlayer p_147237_1_);

    void func_147279_a(S0BPacketAnimation p_147279_1_);

    void func_147293_a(S37PacketStatistics p_147293_1_);

    void func_147294_a(S25PacketBlockBreakAnim p_147294_1_);

    void func_147268_a(S36PacketSignEditorOpen p_147268_1_);

    void func_147273_a(S35PacketUpdateTileEntity p_147273_1_);

    void func_147261_a(S24PacketBlockAction p_147261_1_);

    void func_147234_a(S23PacketBlockChange p_147234_1_);

    void func_147251_a(S02PacketChat p_147251_1_);

    void func_147274_a(S3APacketTabComplete p_147274_1_);

    void func_147287_a(S22PacketMultiBlockChange p_147287_1_);

    void func_147264_a(S34PacketMaps p_147264_1_);

    void func_147239_a(S32PacketConfirmTransaction p_147239_1_);

    void func_147276_a(S2EPacketCloseWindow p_147276_1_);

    void func_147241_a(S30PacketWindowItems p_147241_1_);

    void func_147265_a(S2DPacketOpenWindow p_147265_1_);

    void func_147245_a(S31PacketWindowProperty p_147245_1_);

    void func_147266_a(S2FPacketSetSlot p_147266_1_);

    void func_147240_a(S3FPacketCustomPayload p_147240_1_);

    void func_147253_a(S40PacketDisconnect p_147253_1_);

    void func_147278_a(S0APacketUseBed p_147278_1_);

    void func_147236_a(S19PacketEntityStatus p_147236_1_);

    void func_147243_a(S1BPacketEntityAttach p_147243_1_);

    void func_147283_a(S27PacketExplosion p_147283_1_);

    void func_147252_a(S2BPacketChangeGameState p_147252_1_);

    void func_147272_a(S00PacketKeepAlive p_147272_1_);

    void func_147263_a(S21PacketChunkData p_147263_1_);

    void func_147269_a(S26PacketMapChunkBulk p_147269_1_);

    void func_147277_a(S28PacketEffect p_147277_1_);

    void func_147282_a(S01PacketJoinGame p_147282_1_);

    void func_147259_a(S14PacketEntity p_147259_1_);

    void func_147258_a(S08PacketPlayerPosLook p_147258_1_);

    void func_147289_a(S2APacketParticles p_147289_1_);

    void func_147270_a(S39PacketPlayerAbilities p_147270_1_);

    void func_147256_a(S38PacketPlayerListItem p_147256_1_);

    void func_147238_a(S13PacketDestroyEntities p_147238_1_);

    void func_147262_a(S1EPacketRemoveEntityEffect p_147262_1_);

    void func_147280_a(S07PacketRespawn p_147280_1_);

    void func_147267_a(S19PacketEntityHeadLook p_147267_1_);

    void func_147257_a(S09PacketHeldItemChange p_147257_1_);

    void func_147254_a(S3DPacketDisplayScoreboard p_147254_1_);

    void func_147284_a(S1CPacketEntityMetadata p_147284_1_);

    void func_147244_a(S12PacketEntityVelocity p_147244_1_);

    void func_147242_a(S04PacketEntityEquipment p_147242_1_);

    void func_147295_a(S1FPacketSetExperience p_147295_1_);

    void func_147249_a(S06PacketUpdateHealth p_147249_1_);

    void func_147247_a(S3EPacketTeams p_147247_1_);

    void func_147250_a(S3CPacketUpdateScore p_147250_1_);

    void func_147271_a(S05PacketSpawnPosition p_147271_1_);

    void func_147285_a(S03PacketTimeUpdate p_147285_1_);

    void func_147248_a(S33PacketUpdateSign p_147248_1_);

    void func_147255_a(S29PacketSoundEffect p_147255_1_);

    void func_147246_a(S0DPacketCollectItem p_147246_1_);

    void func_147275_a(S18PacketEntityTeleport p_147275_1_);

    void func_147290_a(S20PacketEntityProperties p_147290_1_);

    void func_147260_a(S1DPacketEntityEffect p_147260_1_);
}