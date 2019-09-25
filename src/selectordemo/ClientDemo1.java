package selectordemo;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class ClientDemo1 {

    public static void main(String[] args) throws Exception{

        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("localhost",8888));

//        socketChannel.configureBlocking(false);
        while (true){
            Thread.sleep(2000);
            boolean temp = socketChannel.isConnected();
            if(temp){
                System.out.println("客户端写入数据");
                System.out.println(temp);
                ByteBuffer buffer = ByteBuffer.allocate(64);
                buffer.put("哈哈哈".getBytes());
                buffer.flip();
                socketChannel.write(buffer);
                socketChannel.shutdownOutput();
            }
        }


    }
}
