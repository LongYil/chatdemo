package channeldemo;

import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class SocketServerChannel {

    public static void main(String[] args) throws Exception{
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
//        socketChannel.connect(new InetSocketAddress("localhost",8080));
        serverSocketChannel.bind(new InetSocketAddress(8080));
        serverSocketChannel.configureBlocking(true);
        while (true){
            System.out.println("检测新连接。。。");
            SocketChannel socketChannel = serverSocketChannel.accept();
            if(socketChannel != null){
                System.out.println("新用户上线了");

                ByteBuffer buffer = ByteBuffer.allocate(100);
                socketChannel.read(buffer);

                System.out.println("用户信息：" + new String(buffer.array()));
            }
        }
    }




}
