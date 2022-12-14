package net.minecraft.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockJukebox;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TileEntity
{
    private static final Logger field_145852_a = LogManager.getLogger();
    private static Map field_145855_i = new HashMap();
    private static Map field_145853_j = new HashMap();
    protected World field_145850_b;
    public int field_145851_c;
    public int field_145848_d;
    public int field_145849_e;
    protected boolean field_145846_f;
    public int field_145847_g = -1;
    public Block field_145854_h;
    private static final String __OBFID = "CL_00000340";

    public static void func_145826_a(Class p_145826_0_, String p_145826_1_)
    {
        if (field_145855_i.containsKey(p_145826_1_))
        {
            throw new IllegalArgumentException("Duplicate id: " + p_145826_1_);
        }
        else
        {
            field_145855_i.put(p_145826_1_, p_145826_0_);
            field_145853_j.put(p_145826_0_, p_145826_1_);
        }
    }

    public World func_145831_w()
    {
        return this.field_145850_b;
    }

    public void func_145834_a(World p_145834_1_)
    {
        this.field_145850_b = p_145834_1_;
    }

    public boolean func_145830_o()
    {
        return this.field_145850_b != null;
    }

    public void func_145839_a(NBTTagCompound p_145839_1_)
    {
        this.field_145851_c = p_145839_1_.func_74762_e("x");
        this.field_145848_d = p_145839_1_.func_74762_e("y");
        this.field_145849_e = p_145839_1_.func_74762_e("z");
    }

    public void func_145841_b(NBTTagCompound p_145841_1_)
    {
        String s = (String)field_145853_j.get(this.getClass());

        if (s == null)
        {
            throw new RuntimeException(this.getClass() + " is missing a mapping! This is a bug!");
        }
        else
        {
            p_145841_1_.func_74778_a("id", s);
            p_145841_1_.func_74768_a("x", this.field_145851_c);
            p_145841_1_.func_74768_a("y", this.field_145848_d);
            p_145841_1_.func_74768_a("z", this.field_145849_e);
        }
    }

    public void func_145845_h() {}

    public static TileEntity func_145827_c(NBTTagCompound p_145827_0_)
    {
        TileEntity tileentity = null;

        try
        {
            Class oclass = (Class)field_145855_i.get(p_145827_0_.func_74779_i("id"));

            if (oclass != null)
            {
                tileentity = (TileEntity)oclass.newInstance();
            }
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }

        if (tileentity != null)
        {
            tileentity.func_145839_a(p_145827_0_);
        }
        else
        {
            field_145852_a.warn("Skipping BlockEntity with id " + p_145827_0_.func_74779_i("id"));
        }

        return tileentity;
    }

    public int func_145832_p()
    {
        if (this.field_145847_g == -1)
        {
            this.field_145847_g = this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        }

        return this.field_145847_g;
    }

    public void func_70296_d()
    {
        if (this.field_145850_b != null)
        {
            this.field_145847_g = this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            this.field_145850_b.func_147476_b(this.field_145851_c, this.field_145848_d, this.field_145849_e, this);

            if (this.func_145838_q() != Blocks.AIR)
            {
                this.field_145850_b.func_147453_f(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.func_145838_q());
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public double func_145835_a(double p_145835_1_, double p_145835_3_, double p_145835_5_)
    {
        double d3 = (double)this.field_145851_c + 0.5D - p_145835_1_;
        double d4 = (double)this.field_145848_d + 0.5D - p_145835_3_;
        double d5 = (double)this.field_145849_e + 0.5D - p_145835_5_;
        return d3 * d3 + d4 * d4 + d5 * d5;
    }

    @SideOnly(Side.CLIENT)
    public double func_145833_n()
    {
        return 4096.0D;
    }

    public Block func_145838_q()
    {
        if (this.field_145854_h == null)
        {
            this.field_145854_h = this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        }

        return this.field_145854_h;
    }

    public Packet func_145844_m()
    {
        return null;
    }

    public boolean func_145837_r()
    {
        return this.field_145846_f;
    }

    public void func_145843_s()
    {
        this.field_145846_f = true;
    }

    public void func_145829_t()
    {
        this.field_145846_f = false;
    }

    public boolean func_145842_c(int p_145842_1_, int p_145842_2_)
    {
        return false;
    }

    public void func_145836_u()
    {
        this.field_145854_h = null;
        this.field_145847_g = -1;
    }

    public void func_145828_a(CrashReportCategory p_145828_1_)
    {
        p_145828_1_.func_71500_a("Name", new Callable()
        {
            private static final String __OBFID = "CL_00000341";
            public String call()
            {
                return (String)TileEntity.field_145853_j.get(TileEntity.this.getClass()) + " // " + TileEntity.this.getClass().getCanonicalName();
            }
        });
        CrashReportCategory.func_147153_a(p_145828_1_, this.field_145851_c, this.field_145848_d, this.field_145849_e, this.func_145838_q(), this.func_145832_p());
        p_145828_1_.func_71500_a("Actual block type", new Callable()
        {
            private static final String __OBFID = "CL_00000343";
            public String call()
            {
                int i = Block.func_149682_b(TileEntity.this.field_145850_b.func_147439_a(TileEntity.this.field_145851_c, TileEntity.this.field_145848_d, TileEntity.this.field_145849_e));

                try
                {
                    return String.format("ID #%d (%s // %s)", new Object[] {Integer.valueOf(i), Block.func_149729_e(i).func_149739_a(), Block.func_149729_e(i).getClass().getCanonicalName()});
                }
                catch (Throwable throwable)
                {
                    return "ID #" + i;
                }
            }
        });
        p_145828_1_.func_71500_a("Actual block data value", new Callable()
        {
            private static final String __OBFID = "CL_00000344";
            public String call()
            {
                int i = TileEntity.this.field_145850_b.func_72805_g(TileEntity.this.field_145851_c, TileEntity.this.field_145848_d, TileEntity.this.field_145849_e);

                if (i < 0)
                {
                    return "Unknown? (Got " + i + ")";
                }
                else
                {
                    String s = String.format("%4s", new Object[] {Integer.toBinaryString(i)}).replace(" ", "0");
                    return String.format("%1$d / 0x%1$X / 0b%2$s", new Object[] {Integer.valueOf(i), s});
                }
            }
        });
    }

    static
    {
        func_145826_a(TileEntityFurnace.class, "Furnace");
        func_145826_a(TileEntityChest.class, "Chest");
        func_145826_a(TileEntityEnderChest.class, "EnderChest");
        func_145826_a(BlockJukebox.TileEntityJukebox.class, "RecordPlayer");
        func_145826_a(TileEntityDispenser.class, "Trap");
        func_145826_a(TileEntityDropper.class, "Dropper");
        func_145826_a(TileEntitySign.class, "Sign");
        func_145826_a(TileEntityMobSpawner.class, "MobSpawner");
        func_145826_a(TileEntityNote.class, "Music");
        func_145826_a(TileEntityPiston.class, "Piston");
        func_145826_a(TileEntityBrewingStand.class, "Cauldron");
        func_145826_a(TileEntityEnchantmentTable.class, "EnchantTable");
        func_145826_a(TileEntityEndPortal.class, "Airportal");
        func_145826_a(TileEntityCommandBlock.class, "Control");
        func_145826_a(TileEntityBeacon.class, "Beacon");
        func_145826_a(TileEntitySkull.class, "Skull");
        func_145826_a(TileEntityDaylightDetector.class, "DLDetector");
        func_145826_a(TileEntityHopper.class, "Hopper");
        func_145826_a(TileEntityComparator.class, "Comparator");
        func_145826_a(TileEntityFlowerPot.class, "FlowerPot");
    }
}