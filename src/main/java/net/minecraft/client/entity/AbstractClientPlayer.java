package net.minecraft.client.entity;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.io.File;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ImageBufferDownload;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.SkinManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import net.minecraft.world.World;

@SideOnly(Side.CLIENT)
public abstract class AbstractClientPlayer extends EntityPlayer implements SkinManager.SkinAvailableCallback
{
    public static final ResourceLocation field_110314_b = new ResourceLocation("textures/entity/steve.png");
    private ResourceLocation field_110312_d;
    private ResourceLocation field_110313_e;
    private static final String __OBFID = "CL_00000935";

    public AbstractClientPlayer(World p_i45074_1_, GameProfile p_i45074_2_)
    {
        super(p_i45074_1_, p_i45074_2_);
        String s = this.func_70005_c_();

        if (!s.isEmpty())
        {
            SkinManager skinmanager = Minecraft.func_71410_x().func_152342_ad();
            skinmanager.func_152790_a(p_i45074_2_, this, true);
        }
    }

    public boolean func_152122_n()
    {
        return this.field_110313_e != null;
    }

    public boolean func_152123_o()
    {
        return this.field_110312_d != null;
    }

    public ResourceLocation func_110306_p()
    {
        return this.field_110312_d == null ? field_110314_b : this.field_110312_d;
    }

    public ResourceLocation func_110303_q()
    {
        return this.field_110313_e;
    }

    public static ThreadDownloadImageData func_110304_a(ResourceLocation p_110304_0_, String p_110304_1_)
    {
        TextureManager texturemanager = Minecraft.func_71410_x().func_110434_K();
        Object object = texturemanager.func_110581_b(p_110304_0_);

        if (object == null)
        {
            object = new ThreadDownloadImageData((File)null, String.format("http://skins.minecraft.net/MinecraftSkins/%s.png", new Object[] {StringUtils.func_76338_a(p_110304_1_)}), field_110314_b, new ImageBufferDownload());
            texturemanager.func_110579_a(p_110304_0_, (ITextureObject)object);
        }

        return (ThreadDownloadImageData)object;
    }

    public static ResourceLocation func_110311_f(String p_110311_0_)
    {
        return new ResourceLocation("skins/" + StringUtils.func_76338_a(p_110311_0_));
    }

    public void func_152121_a(Type p_152121_1_, ResourceLocation p_152121_2_)
    {
        switch (AbstractClientPlayer.SwitchType.field_152630_a[p_152121_1_.ordinal()])
        {
            case 1:
                this.field_110312_d = p_152121_2_;
                break;
            case 2:
                this.field_110313_e = p_152121_2_;
        }
    }

    @SideOnly(Side.CLIENT)

    static final class SwitchType
        {
            static final int[] field_152630_a = new int[Type.values().length];
            private static final String __OBFID = "CL_00001832";

            static
            {
                try
                {
                    field_152630_a[Type.SKIN.ordinal()] = 1;
                }
                catch (NoSuchFieldError var2)
                {
                    ;
                }

                try
                {
                    field_152630_a[Type.CAPE.ordinal()] = 2;
                }
                catch (NoSuchFieldError var1)
                {
                    ;
                }
            }
        }
}