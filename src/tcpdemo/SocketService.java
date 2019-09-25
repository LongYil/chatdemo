package tcpdemo;

import java.net.ServerSocket;
import java.net.Socket;
 
public class SocketService {
    public static void main(String args[])throws Exception {
        ServerSocket serverSocket = new ServerSocket(2181);
        System.out.println("服务器启动成功");
        while (true) {  
            Socket socket= serverSocket.accept();
            System.out.println("上线通知： " + socket.getInetAddress() + ":" +socket.getPort());
            new Thread(new ServerThread(socket)).start();
        }
    }
}