package tcpdemo;
 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
 
public class ServerThread implements Runnable {
    
    public Socket socket;
    
    public ServerThread (Socket socket) {
        System.out.println("新建了一个线程");
        this.socket = socket;
    }
 
    @Override
    public void run() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (true) {
                String str = br.readLine();
                System.out.println(str);
                socket.getOutputStream().write("收到！".getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
}
