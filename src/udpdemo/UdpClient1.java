package udpdemo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class UdpClient1 {

    public static void main(String[] args) throws IOException {
        // 1,创建服务端+ 端口
        DatagramSocket client = new DatagramSocket(614);

        // 2,准备数据
        String msg = "Hello,Udp!";

        byte[] data = msg.getBytes();

        // 3,打包（发送的地点及端口）
        DatagramPacket packet = new DatagramPacket(data, data.length, new InetSocketAddress("localhost", 2181));

        // 4,发送资源
        client.send(packet);

        while (true){
            System.out.println("开始接收数据");
            // 5,准备接收容器
            byte[] container = new byte[1024];

            // 6,封装成包 new DatagramPacket(byte[] b,int length)
            DatagramPacket datagramPacket = new DatagramPacket(container, container.length);

            client.receive(datagramPacket);

            byte[] receive = datagramPacket.getData();
            int length = datagramPacket.getLength();
            String msgt = new String(receive, 0, length);
            System.out.println("收到来自服务端的数据报：" + datagramPacket.getAddress().getHostAddress() + ":" + packet.getPort() + " 内容：" + msgt);
        }
        // 5,关闭资源
//        client.close();
    }

}