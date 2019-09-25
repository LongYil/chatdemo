package selectordemo;

import java.net.InetSocketAddress;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ServerDemo {

    public static void main(String[] args) throws Exception{
        Selector selector = Selector.open();

        ServerSocketChannel server = ServerSocketChannel.open();
        server.configureBlocking(false);
        server.bind(new InetSocketAddress("localhost",8888));

        Map<SocketChannel,String> map = new HashMap<>();

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        while (true){
            Thread.sleep(2000);
            System.out.println("当前通道数：" + map.size());
            SocketChannel socketChannel = server.accept();
            if(socketChannel != null){
                map.put(socketChannel,format.format(new Date()));
                System.out.println("有新通道接入。。。");
            }else {
                System.out.println("无新通道接入");
            }
        }
    }
}
