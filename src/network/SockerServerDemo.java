package network;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class SockerServerDemo {

    public static void main(String[] args) throws Exception{
        ServerSocket socket = new ServerSocket(8080);
        List<Socket> list = new ArrayList<>();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try{
                        System.out.println("准备推送消息");
                        Thread.sleep(2000);
                        list.forEach(item->{
                            try{
                                System.out.println("推送消息");
                                OutputStream outputStream = item.getOutputStream();
                                outputStream.write("你收到一条消息...".getBytes());
                                outputStream.flush();
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        });
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
        while (true){
            System.out.println("开始接收服务");
            Socket client = socket.accept();
            if(client != null){
                list.add(client);
                System.out.println("新用户上线");
                InputStream inputStream = client.getInputStream();
                byte[] bytes = new byte[50];
                inputStream.read(bytes);
                System.out.println("用户信息：" + new String(bytes));
                OutputStream outputStream = client.getOutputStream();
                outputStream.write("服务器正常".getBytes());
                outputStream.flush();
            }
        }

    }
}
