package org.goaler.trans;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientTransMain {

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("10.10.80.65", 2019);
        OutputStream os = socket.getOutputStream();

        File file = new File("d:/test.log");
        InputStream is = new FileInputStream(file);

        byte[] buf = new byte[1024];
        int len = 0;
        while ((len = is.read(buf)) != -1){
            os.write(buf, 0, len);
        }
        is.close();
        os.close();

    }


}
