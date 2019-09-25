package udpchannel;

import java.net.DatagramPacket;
import java.net.InetSocketAddress;
import java.nio.channels.DatagramChannel;

public class DatagramChannelTest {

    public static void main(String[] args) throws Exception{
        DatagramChannel datagramChannel = DatagramChannel.open();

        datagramChannel.bind(new InetSocketAddress(8888));
//        DatagramPacket datagramPacket = new DatagramPacket();
//        datagramChannel.socket().receive();
    }
}
