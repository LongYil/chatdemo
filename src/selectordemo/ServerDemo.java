package selectordemo;

import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
            map.forEach((k,v)->{
                try{
                    System.out.println(k + ":" + v);
                    ByteBuffer buffer = ByteBuffer.allocate(64);
                    k.read(buffer);
                    System.out.println("实时信息查询：读：" + new String(buffer.array()));
                }catch (Exception e){
                    e.printStackTrace();
                }
            });

            SocketChannel socketChannel = server.accept();
            if(socketChannel != null){
                map.put(socketChannel,format.format(new Date()));
                socketChannel.configureBlocking(false);

                SelectionKey selectionKey = socketChannel.register(selector, SelectionKey.OP_WRITE);

                System.out.println("有新通道接入。。。");

                ByteBuffer buffer = ByteBuffer.allocate(64);
                int temp = socketChannel.read(buffer);
                Thread.sleep(5000);
                System.out.println("客户端读：" + selectionKey.isReadable());

            }else {
                System.out.println("无新通道接入");
            }

            if(map.size() > 0){
                System.out.println("选择感兴趣的通道。。。");
                System.out.println(selector.select());
            }
        }
    }
}