package uitcpdemo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClientFrame extends Frame {


    private TextField textFieldContent = new TextField();

    private TextArea textAreaContent = new TextArea();

    private Socket socket = null;

    private OutputStream out = null;

    private DataOutputStream dos = null;

    private InputStream in = null;

    private DataInputStream dis = null;

    private boolean flag = false;


    /**
     * 启动客户端程序
     *
     * @param args
     */

    public static void main(String[] args) {

        new ClientFrame().init();

    }


    /**
     * 功能：对窗口进行初始化
     */

    private void init() {

        this.setSize(300, 300);

        setLocation(250, 150);

        setVisible(true);

        setTitle("WeChatRoom");


        // 添加控件

        this.add(textAreaContent);

        this.add(textFieldContent, BorderLayout.SOUTH);

        textAreaContent.setFocusable(false);

        pack();


        // 关闭事件

        addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {

                System.out.println("用户试图关闭窗口");

                disconnect();

                System.exit(0);

            }


        });

        // textFieldContent添加回车事件

        textFieldContent.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                onClickEnter();

            }

        });


        // 建立连接

        connect();

        new Thread(new ReciveMessage()).start();

    }


    private class ReciveMessage implements Runnable {

        @Override

        public void run() {

            flag = true;

            try {

                while (flag) {

                    String message = dis.readUTF();

                    textAreaContent.append(message + "\n");

                }

            } catch (EOFException e) {

                flag = false;

                System.out.println("客户端已关闭");

                // e.printStackTrace();

            } catch (SocketException e) {

                flag = false;

                System.out.println("客户端已关闭");

                // e.printStackTrace();

            } catch (IOException e) {

                flag = false;

                System.out.println("接受消息失败");

                e.printStackTrace();

            }

        }


    }


    /**
     * 功能：当点击回车时触发事件
     */

    private void onClickEnter() {
        String message = textFieldContent.getText().trim();
        if (message != null && !message.equals("")) {
            String time = new SimpleDateFormat("h:m:s").format(new Date());
            textAreaContent.append(time + "\n" + message + "\n");
            textFieldContent.setText("");
            sendMessageToServer(message);
        }

    }


    /**
     * 功能：给服务器发送消息
     *
     * @param message
     */

    private void sendMessageToServer(String message) {
        try {
            dos.writeUTF(message);
            dos.flush();
        } catch (IOException e) {
            System.out.println("发送消息失败");
            e.printStackTrace();
        }

    }


    /**
     * 功能：申请socket链接
     */

    private void connect() {

        try {
            socket = new Socket("localhost", 8888);
            out = socket.getOutputStream();
            dos = new DataOutputStream(out);
            in = socket.getInputStream();
            dis = new DataInputStream(in);
        } catch (UnknownHostException e) {
            System.out.println("申请链接失败");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("申请链接失败");
            e.printStackTrace();

        }

    }


    /**
     * 功能：关闭流和链接
     */

    private void disconnect() {

        flag = false;

        if (dos != null) {
            try {
                dos.close();
            } catch (IOException e) {
                System.out.println("dos关闭失败");
                e.printStackTrace();
            }
        }

        if (out != null) {
            try {
                out.close();
            } catch (IOException e) {
                System.out.println("dos关闭失败");
                e.printStackTrace();
            }
        }

        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println("socket关闭失败");
                e.printStackTrace();
            }
        }

    }

}