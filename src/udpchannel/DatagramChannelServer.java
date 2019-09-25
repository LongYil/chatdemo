package udpchannel;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class DatagramChannelServer {

    public static void main(String[] args) throws Exception{
        DatagramChannel datagramChannel = DatagramChannel.open();

        datagramChannel.bind(new InetSocketAddress(8888));

        ByteBuffer buffer = ByteBuffer.allocate(64);
        byte[] bytes = new byte[64];
        System.out.println("服务已打开");

        DatagramPacket packet = new DatagramPacket(bytes,bytes.length);

        while (true){
            DatagramSocket datagramSocket = datagramChannel.socket();

            datagramSocket.receive(packet);

            System.out.println(new String(packet.getData()));
        }

    }
}
