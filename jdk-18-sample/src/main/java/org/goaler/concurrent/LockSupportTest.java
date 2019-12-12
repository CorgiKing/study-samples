package org.goaler.concurrent;

import sun.misc.Unsafe;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

public class LockSupportTest {
    public static void main(String[] args) {

        //阻塞当前线程
        LockSupport.park();

        //阻塞当前线程,超时返回,阻塞时间最长不超过nanos纳秒
        long nanos = TimeUnit.SECONDS.toNanos(2);
        LockSupport.parkNanos(nanos);

        //阻塞当前线程,直到deadline时间点(毫秒时间戳)
        long deadline = System.currentTimeMillis() + 1000;
        LockSupport.parkUntil(deadline);

        //唤醒线程
        LockSupport.unpark(Thread.currentThread());

        //其中参数blocker是用来标识当前线程在等待的对象,该对象主要用于问题排查和系统监控
        LockSupport.park(LockSupportTest.class);
        LockSupport.parkNanos(LockSupportTest.class, nanos);
        LockSupport.parkUntil(LockSupportTest.class, deadline);

    }
}
