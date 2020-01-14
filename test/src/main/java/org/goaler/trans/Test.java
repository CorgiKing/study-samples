package org.goaler.trans;

import java.io.*;

public class Test {
    public static void main(String[] args) throws Exception{
        final FileWriter raf = new FileWriter("d:/tmp/test");
        for (int i = 0; i < 10; i++)
        new Thread(new Runnable() {
            public void run() {
                for (int j = 0; j < 100; j++)
                try {
                    raf.write("测试多线程写入\r\n");
                    raf.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    ).start();
    }
}
