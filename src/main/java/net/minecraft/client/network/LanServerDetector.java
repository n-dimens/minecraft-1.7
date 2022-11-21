package net.minecraft.client.network;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ThreadLanServerPing;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SideOnly(Side.CLIENT)
public class LanServerDetector
{
    private static final AtomicInteger field_148551_a = new AtomicInteger(0);
    private static final Logger field_148550_b = LogManager.getLogger();
    private static final String __OBFID = "CL_00001133";

    @SideOnly(Side.CLIENT)
    public static class LanServer
        {
            private String field_77492_a;
            private String field_77490_b;
            private long field_77491_c;
            private static final String __OBFID = "CL_00001134";

            public LanServer(String p_i1319_1_, String p_i1319_2_)
            {
                this.field_77492_a = p_i1319_1_;
                this.field_77490_b = p_i1319_2_;
                this.field_77491_c = Minecraft.func_71386_F();
            }

            public String func_77487_a()
            {
                return this.field_77492_a;
            }

            public String func_77488_b()
            {
                return this.field_77490_b;
            }

            public void func_77489_c()
            {
                this.field_77491_c = Minecraft.func_71386_F();
            }
        }

    @SideOnly(Side.CLIENT)
    public static class LanServerList
        {
            private ArrayList field_77555_b = new ArrayList();
            boolean field_77556_a;
            private static final String __OBFID = "CL_00001136";

            public synchronized boolean func_77553_a()
            {
                return this.field_77556_a;
            }

            public synchronized void func_77552_b()
            {
                this.field_77556_a = false;
            }

            public synchronized List func_77554_c()
            {
                return Collections.unmodifiableList(this.field_77555_b);
            }

            public synchronized void func_77551_a(String p_77551_1_, InetAddress p_77551_2_)
            {
                String s1 = ThreadLanServerPing.func_77524_a(p_77551_1_);
                String s2 = ThreadLanServerPing.func_77523_b(p_77551_1_);

                if (s2 != null)
                {
                    s2 = p_77551_2_.getHostAddress() + ":" + s2;
                    boolean flag = false;
                    Iterator iterator = this.field_77555_b.iterator();

                    while (iterator.hasNext())
                    {
                        LanServerDetector.LanServer lanserver = (LanServerDetector.LanServer)iterator.next();

                        if (lanserver.func_77488_b().equals(s2))
                        {
                            lanserver.func_77489_c();
                            flag = true;
                            break;
                        }
                    }

                    if (!flag)
                    {
                        this.field_77555_b.add(new LanServerDetector.LanServer(s1, s2));
                        this.field_77556_a = true;
                    }
                }
            }
        }

    @SideOnly(Side.CLIENT)
    public static class ThreadLanServerFind extends Thread
        {
            private final LanServerDetector.LanServerList field_77500_a;
            private final InetAddress field_77498_b;
            private final MulticastSocket field_77499_c;
            private static final String __OBFID = "CL_00001135";

            public ThreadLanServerFind(LanServerDetector.LanServerList p_i1320_1_) throws IOException
            {
                super("LanServerDetector #" + LanServerDetector.field_148551_a.incrementAndGet());
                this.field_77500_a = p_i1320_1_;
                this.setDaemon(true);
                this.field_77499_c = new MulticastSocket(4445);
                this.field_77498_b = InetAddress.getByName("224.0.2.60");
                this.field_77499_c.setSoTimeout(5000);
                this.field_77499_c.joinGroup(this.field_77498_b);
            }

            public void run()
            {
                byte[] abyte = new byte[1024];

                while (!this.isInterrupted())
                {
                    DatagramPacket datagrampacket = new DatagramPacket(abyte, abyte.length);

                    try
                    {
                        this.field_77499_c.receive(datagrampacket);
                    }
                    catch (SocketTimeoutException sockettimeoutexception)
                    {
                        continue;
                    }
                    catch (IOException ioexception1)
                    {
                        LanServerDetector.field_148550_b.error("Couldn\'t ping server", ioexception1);
                        break;
                    }

                    String s = new String(datagrampacket.getData(), datagrampacket.getOffset(), datagrampacket.getLength());
                    LanServerDetector.field_148550_b.debug(datagrampacket.getAddress() + ": " + s);
                    this.field_77500_a.func_77551_a(s, datagrampacket.getAddress());
                }

                try
                {
                    this.field_77499_c.leaveGroup(this.field_77498_b);
                }
                catch (IOException ioexception)
                {
                    ;
                }

                this.field_77499_c.close();
            }
        }
}