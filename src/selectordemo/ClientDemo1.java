package selectordemo;

import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

public class ClientDemo1 {

    public static void main(String[] args) throws Exception{

        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("localhost",8888));

        while (true){
            boolean temp = socketChannel.isConnected();
            if(temp){
                System.out.println(temp);
            }
        }


    }
}
