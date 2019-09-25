package tcpdemo;
 
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
 
public class SocketClient1 {
    public static void main(String args[])throws Exception{  
        Socket socket = new Socket("120.79.245.180", 2181);
        System.out.println("小一连接成功");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(socket.getOutputStream());
        InputStream inputStream = socket.getInputStream();
        while(true){
            pw.println("小一说："+br.readLine());
            pw.flush();
            byte[] bytes = new byte[50];
            inputStream.read(bytes);
            System.out.println("收到响应：" + new String(bytes));
        }
    }
}
