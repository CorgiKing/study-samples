package org.goaler.trans;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TransMain {

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(2019);

        while (true){
            System.out.println("监听端口号：" + serverSocket.getLocalPort());
            final Socket client = serverSocket.accept();
            System.out.println("接收到：" + client.getInetAddress().getHostAddress());
            new Thread(new Runnable() {
                public void run() {
                    try {
                        long st = System.currentTimeMillis();
                        InputStream is = client.getInputStream();

                        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss");
                        String fileName = sdf.format(new Date());
                        FileOutputStream fos = new FileOutputStream("/tmp/file/" + fileName);

                        byte[] buf = new byte[1024];
                        int len = 0;
                        while ((len = is.read(buf)) != -1){

                            fos.write(buf, 0, len);
                        }
                        client.close();
                        fos.close();
                        System.out.println("文件传输完成。耗时：" + (System.currentTimeMillis() - st));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }


}
