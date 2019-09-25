package udpchannel;

import java.net.DatagramPacket;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class DatagramChannelClient {

    public static void main(String[] args) throws Exception{
        DatagramChannel datagramChannel = DatagramChannel.open();

        datagramChannel.connect(new InetSocketAddress("localhost",8888));

        byte[] bytes = new byte[64];

        DatagramPacket packet = new DatagramPacket(bytes,bytes.length);

        System.out.println("客户端已打开");
        for (int i = 0; i < 10 ;i++){
            ByteBuffer buffer = ByteBuffer.allocate(64);
            buffer.put("hello,udpChannel".getBytes());
            buffer.flip();

            datagramChannel.write(buffer);

            System.out.println(new String(packet.getData()));
        }

    }
}
