package org.goaler.zerocopy;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MappedByteBufferTest {

    public static void main(String[] args) throws IOException {
        File tmpFile = new File("jdk-18-sample/src/main/resources/tmp.txt");

        long size = tmpFile.length();
        FileChannel channel = new FileInputStream(tmpFile)
                .getChannel();
        MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, size);

        byte[] buf = new byte[1024];
        System.out.println(mappedByteBuffer.remaining());

        mappedByteBuffer.get(buf, 0, (int)size);
        System.out.println(new String(buf));
    }
}
