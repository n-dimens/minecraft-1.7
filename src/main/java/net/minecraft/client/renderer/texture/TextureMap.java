package net.minecraft.client.renderer.texture;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import javax.imageio.ImageIO;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.StitcherException;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.data.AnimationMetadataSection;
import net.minecraft.client.resources.data.TextureMetadataSection;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ReportedException;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SideOnly(Side.CLIENT)
public class TextureMap extends AbstractTexture implements ITickableTextureObject, IIconRegister
{
    private static final Logger field_147635_d = LogManager.getLogger();
    public static final ResourceLocation field_110575_b = new ResourceLocation("textures/atlas/blocks.png");
    public static final ResourceLocation field_110576_c = new ResourceLocation("textures/atlas/items.png");
    private final List field_94258_i = Lists.newArrayList();
    private final Map field_110574_e = Maps.newHashMap();
    private final Map field_94252_e = Maps.newHashMap();
    private final int field_94255_a;
    private final String field_94254_c;
    private int field_147636_j;
    private int field_147637_k = 1;
    private final TextureAtlasSprite field_94249_f = new TextureAtlasSprite("missingno");
    private static final String __OBFID = "CL_00001058";

    public TextureMap(int p_i1281_1_, String p_i1281_2_)
    {
        this.field_94255_a = p_i1281_1_;
        this.field_94254_c = p_i1281_2_;
        this.func_110573_f();
    }

    private void func_110569_e()
    {
        int[] aint;

        if ((float)this.field_147637_k > 1.0F)
        {
            boolean flag = true;
            boolean flag1 = true;
            boolean flag2 = true;
            this.field_94249_f.func_110966_b(32);
            this.field_94249_f.func_110969_c(32);
            aint = new int[1024];
            System.arraycopy(TextureUtil.field_110999_b, 0, aint, 0, TextureUtil.field_110999_b.length);
            TextureUtil.func_147948_a(aint, 16, 16, 8);
        }
        else
        {
            aint = TextureUtil.field_110999_b;
            this.field_94249_f.func_110966_b(16);
            this.field_94249_f.func_110969_c(16);
        }

        int[][] aint1 = new int[this.field_147636_j + 1][];
        aint1[0] = aint;
        this.field_94249_f.func_110968_a(Lists.newArrayList(new int[][][] {aint1}));
    }

    public void func_110551_a(IResourceManager p_110551_1_) throws IOException
    {
        this.func_110569_e();
        this.func_147631_c();
        this.func_110571_b(p_110551_1_);
    }

    public void func_110571_b(IResourceManager p_110571_1_)
    {
        int i = Minecraft.func_71369_N();
        Stitcher stitcher = new Stitcher(i, i, true, 0, this.field_147636_j);
        this.field_94252_e.clear();
        this.field_94258_i.clear();
        int j = Integer.MAX_VALUE;
        Iterator iterator = this.field_110574_e.entrySet().iterator();
        TextureAtlasSprite textureatlassprite;

        while (iterator.hasNext())
        {
            Entry entry = (Entry)iterator.next();
            ResourceLocation resourcelocation = new ResourceLocation((String)entry.getKey());
            textureatlassprite = (TextureAtlasSprite)entry.getValue();
            ResourceLocation resourcelocation1 = this.func_147634_a(resourcelocation, 0);

            try
            {
                IResource iresource = p_110571_1_.func_110536_a(resourcelocation1);
                BufferedImage[] abufferedimage = new BufferedImage[1 + this.field_147636_j];
                abufferedimage[0] = ImageIO.read(iresource.func_110527_b());
                TextureMetadataSection texturemetadatasection = (TextureMetadataSection)iresource.func_110526_a("texture");

                if (texturemetadatasection != null)
                {
                    List list = texturemetadatasection.func_148535_c();
                    int l;

                    if (!list.isEmpty())
                    {
                        int k = abufferedimage[0].getWidth();
                        l = abufferedimage[0].getHeight();

                        if (MathHelper.func_151236_b(k) != k || MathHelper.func_151236_b(l) != l)
                        {
                            throw new RuntimeException("Unable to load extra miplevels, source-texture is not power of two");
                        }
                    }

                    Iterator iterator3 = list.iterator();

                    while (iterator3.hasNext())
                    {
                        l = ((Integer)iterator3.next()).intValue();

                        if (l > 0 && l < abufferedimage.length - 1 && abufferedimage[l] == null)
                        {
                            ResourceLocation resourcelocation2 = this.func_147634_a(resourcelocation, l);

                            try
                            {
                                abufferedimage[l] = ImageIO.read(p_110571_1_.func_110536_a(resourcelocation2).func_110527_b());
                            }
                            catch (IOException ioexception)
                            {
                                field_147635_d.error("Unable to load miplevel {} from: {}", new Object[] {Integer.valueOf(l), resourcelocation2, ioexception});
                            }
                        }
                    }
                }

                AnimationMetadataSection animationmetadatasection = (AnimationMetadataSection)iresource.func_110526_a("animation");
                textureatlassprite.func_147964_a(abufferedimage, animationmetadatasection, (float)this.field_147637_k > 1.0F);
            }
            catch (RuntimeException runtimeexception)
            {
                field_147635_d.error("Unable to parse metadata from " + resourcelocation1, runtimeexception);
                continue;
            }
            catch (IOException ioexception1)
            {
                field_147635_d.error("Using missing texture, unable to load " + resourcelocation1, ioexception1);
                continue;
            }

            j = Math.min(j, Math.min(textureatlassprite.func_94211_a(), textureatlassprite.func_94216_b()));
            stitcher.func_110934_a(textureatlassprite);
        }

        int i1 = MathHelper.func_151239_c(j);

        if (i1 < this.field_147636_j)
        {
            field_147635_d.debug("{}: dropping miplevel from {} to {}, because of minTexel: {}", new Object[] {this.field_94254_c, Integer.valueOf(this.field_147636_j), Integer.valueOf(i1), Integer.valueOf(j)});
            this.field_147636_j = i1;
        }

        Iterator iterator1 = this.field_110574_e.values().iterator();

        while (iterator1.hasNext())
        {
            final TextureAtlasSprite textureatlassprite1 = (TextureAtlasSprite)iterator1.next();

            try
            {
                textureatlassprite1.func_147963_d(this.field_147636_j);
            }
            catch (Throwable throwable1)
            {
                CrashReport crashreport = CrashReport.func_85055_a(throwable1, "Applying mipmap");
                CrashReportCategory crashreportcategory = crashreport.func_85058_a("Sprite being mipmapped");
                crashreportcategory.func_71500_a("Sprite name", new Callable()
                {
                    private static final String __OBFID = "CL_00001059";
                    public String call()
                    {
                        return textureatlassprite1.func_94215_i();
                    }
                });
                crashreportcategory.func_71500_a("Sprite size", new Callable()
                {
                    private static final String __OBFID = "CL_00001060";
                    public String call()
                    {
                        return textureatlassprite1.func_94211_a() + " x " + textureatlassprite1.func_94216_b();
                    }
                });
                crashreportcategory.func_71500_a("Sprite frames", new Callable()
                {
                    private static final String __OBFID = "CL_00001061";
                    public String call()
                    {
                        return textureatlassprite1.func_110970_k() + " frames";
                    }
                });
                crashreportcategory.func_71507_a("Mipmap levels", Integer.valueOf(this.field_147636_j));
                throw new ReportedException(crashreport);
            }
        }

        this.field_94249_f.func_147963_d(this.field_147636_j);
        stitcher.func_110934_a(this.field_94249_f);

        try
        {
            stitcher.func_94305_f();
        }
        catch (StitcherException stitcherexception)
        {
            throw stitcherexception;
        }

        field_147635_d.info("Created: {}x{} {}-atlas", new Object[] {Integer.valueOf(stitcher.func_110935_a()), Integer.valueOf(stitcher.func_110936_b()), this.field_94254_c});
        TextureUtil.func_147946_a(this.func_110552_b(), this.field_147636_j, stitcher.func_110935_a(), stitcher.func_110936_b(), (float)this.field_147637_k);
        HashMap hashmap = Maps.newHashMap(this.field_110574_e);
        Iterator iterator2 = stitcher.func_94309_g().iterator();

        while (iterator2.hasNext())
        {
            textureatlassprite = (TextureAtlasSprite)iterator2.next();
            String s = textureatlassprite.func_94215_i();
            hashmap.remove(s);
            this.field_94252_e.put(s, textureatlassprite);

            try
            {
                TextureUtil.func_147955_a(textureatlassprite.func_147965_a(0), textureatlassprite.func_94211_a(), textureatlassprite.func_94216_b(), textureatlassprite.func_130010_a(), textureatlassprite.func_110967_i(), false, false);
            }
            catch (Throwable throwable)
            {
                CrashReport crashreport1 = CrashReport.func_85055_a(throwable, "Stitching texture atlas");
                CrashReportCategory crashreportcategory1 = crashreport1.func_85058_a("Texture being stitched together");
                crashreportcategory1.func_71507_a("Atlas path", this.field_94254_c);
                crashreportcategory1.func_71507_a("Sprite", textureatlassprite);
                throw new ReportedException(crashreport1);
            }

            if (textureatlassprite.func_130098_m())
            {
                this.field_94258_i.add(textureatlassprite);
            }
            else
            {
                textureatlassprite.func_130103_l();
            }
        }

        iterator2 = hashmap.values().iterator();

        while (iterator2.hasNext())
        {
            textureatlassprite = (TextureAtlasSprite)iterator2.next();
            textureatlassprite.func_94217_a(this.field_94249_f);
        }
    }

    private ResourceLocation func_147634_a(ResourceLocation p_147634_1_, int p_147634_2_)
    {
        return p_147634_2_ == 0 ? new ResourceLocation(p_147634_1_.func_110624_b(), String.format("%s/%s%s", new Object[] {this.field_94254_c, p_147634_1_.func_110623_a(), ".png"})): new ResourceLocation(p_147634_1_.func_110624_b(), String.format("%s/mipmaps/%s.%d%s", new Object[] {this.field_94254_c, p_147634_1_.func_110623_a(), Integer.valueOf(p_147634_2_), ".png"}));
    }

    private void func_110573_f()
    {
        this.field_110574_e.clear();
        Iterator iterator;

        if (this.field_94255_a == 0)
        {
            iterator = Block.field_149771_c.iterator();

            while (iterator.hasNext())
            {
                Block block = (Block)iterator.next();

                if (block.func_149688_o() != Material.field_151579_a)
                {
                    block.func_149651_a(this);
                }
            }

            Minecraft.func_71410_x().field_71438_f.func_94140_a(this);
            RenderManager.field_78727_a.func_94178_a(this);
        }

        iterator = Item.REGISTRY.iterator();

        while (iterator.hasNext())
        {
            Item item = (Item)iterator.next();

            if (item != null && item.func_94901_k() == this.field_94255_a)
            {
                item.func_94581_a(this);
            }
        }
    }

    public TextureAtlasSprite func_110572_b(String p_110572_1_)
    {
        TextureAtlasSprite textureatlassprite = (TextureAtlasSprite)this.field_94252_e.get(p_110572_1_);

        if (textureatlassprite == null)
        {
            textureatlassprite = this.field_94249_f;
        }

        return textureatlassprite;
    }

    public void func_94248_c()
    {
        TextureUtil.func_94277_a(this.func_110552_b());
        Iterator iterator = this.field_94258_i.iterator();

        while (iterator.hasNext())
        {
            TextureAtlasSprite textureatlassprite = (TextureAtlasSprite)iterator.next();
            textureatlassprite.func_94219_l();
        }
    }

    public IIcon func_94245_a(String p_94245_1_)
    {
        if (p_94245_1_ == null)
        {
            throw new IllegalArgumentException("Name cannot be null!");
        }
        else if (p_94245_1_.indexOf(47) == -1 && p_94245_1_.indexOf(92) == -1)
        {
            Object object = (TextureAtlasSprite)this.field_110574_e.get(p_94245_1_);

            if (object == null)
            {
                if (this.field_94255_a == 1)
                {
                    if ("clock".equals(p_94245_1_))
                    {
                        object = new TextureClock(p_94245_1_);
                    }
                    else if ("compass".equals(p_94245_1_))
                    {
                        object = new TextureCompass(p_94245_1_);
                    }
                    else
                    {
                        object = new TextureAtlasSprite(p_94245_1_);
                    }
                }
                else
                {
                    object = new TextureAtlasSprite(p_94245_1_);
                }

                this.field_110574_e.put(p_94245_1_, object);
            }

            return (IIcon)object;
        }
        else
        {
            throw new IllegalArgumentException("Name cannot contain slashes!");
        }
    }

    public int func_130086_a()
    {
        return this.field_94255_a;
    }

    public void func_110550_d()
    {
        this.func_94248_c();
    }

    public void func_147633_a(int p_147633_1_)
    {
        this.field_147636_j = p_147633_1_;
    }

    public void func_147632_b(int p_147632_1_)
    {
        this.field_147637_k = p_147632_1_;
    }
}