package urldemo;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;

public class UrlClient {

    public static void main(String[] args) throws Exception{

        URL url = new URL("http://localhost:8090/user/test");
        URLConnection connection = url.openConnection();
        connection.setDoOutput(true);
        OutputStream outputStream = connection.getOutputStream();
        InputStream inputStream = new FileInputStream("E:\\in.txt");
        byte[] bytes = new byte[2048];
        inputStream.read(bytes);
        outputStream.write(bytes);
//        outputStream.close();
//        InputStream inputStream = connection.getInputStream();
//        byte[] bytes = new byte[64];
//        inputStream.read(bytes);
//        System.out.println(new String(bytes));
    }
}
