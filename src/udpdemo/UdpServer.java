package udpdemo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;

public class UdpServer {

    public static void main(String[] args) throws IOException {
        // 1,创建服务端+端口
        DatagramSocket server = new DatagramSocket(80);

        // 2,准备接收容器
        byte[] container = new byte[1024];

        // 3,封装成包 new DatagramPacket(byte[] b,int length)
        DatagramPacket packet = new DatagramPacket(container, container.length);

        while (true){
            // 4,接收数据,使用 DatagramSocket的实例的 receive( DatagramPacket ) 方法进行接收
            System.out.println("开始接收数据。。。");
            server.receive(packet);

            // 5,分析数据
            byte[] data = packet.getData();
            int length = packet.getLength();
            String msg = new String(data, 0, length);
            System.out.println("数据来自：" + packet.getAddress().getHostAddress() + ":" + packet.getPort() + " 内容：" + msg);

            //6.返回数据
            InetAddress inetAddress = packet.getAddress();

            for(int i = 0; i < 10; i++){
                byte[] bytes = ("数据段" + i).getBytes();
                DatagramPacket back = new DatagramPacket(bytes, bytes.length, new InetSocketAddress(inetAddress.getHostAddress(), packet.getPort()));
                // 4,发送资源
                server.send(back);
            }

        }
//        server.close();
    }

}
