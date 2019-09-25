package network;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SockerClientDemo {

    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("localhost",8080);

        OutputStream outputStream = socket.getOutputStream();

        outputStream.write("qq:2370775541".getBytes());
        outputStream.flush();
        InputStream inputStream = socket.getInputStream();
        while (true){
            System.out.println("收到服务端的数据");
            byte[] bytes = new byte[50];
            inputStream.read(bytes);
            System.out.println(new String(bytes));
        }


    }
}
