package channeldemo;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class SocketClientChannel {

    public static void main(String[] args) throws Exception{
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost",8080));

        ByteBuffer buffer = ByteBuffer.allocate(100);
        buffer.put("李银龙，id:101".getBytes());
        buffer.flip();
        socketChannel.write(buffer);
        socketChannel.close();
        Thread.sleep(5000);
    }
}
