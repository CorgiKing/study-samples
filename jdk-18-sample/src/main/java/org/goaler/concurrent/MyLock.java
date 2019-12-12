package org.goaler.concurrent;

import sun.misc.Unsafe;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 基于AQS实现自定义同步器
 */
public class MyLock implements Lock {

    private MyAQS myAQS;

    MyLock(){
        myAQS = new MyAQS();
    }

    public void lock() {
        myAQS.acquire(1);
    }

    public void lockInterruptibly() throws InterruptedException {
        myAQS.acquireInterruptibly(1);
    }

    public boolean tryLock() {
        return myAQS.tryAcquire(1);
    }

    public boolean tryLock(long l, TimeUnit timeUnit) throws InterruptedException {
        return myAQS.tryAcquireNanos(1, timeUnit.toNanos(l));
    }

    public void unlock() {
        myAQS.release(1);
    }

    public Condition newCondition() {
        return myAQS.newCondition();
    }




    private static int count;
    private static volatile int check;
    public static void main(String[] args) {
        MyLock lock = new MyLock();
        for(int i = 0; i < 3; ++i){
            new Thread(()->{
                for(;;){
                    lock.lock();
                    try{
                        int t = ++count;
                        System.out.println("当前值：  " + t);

                        //验证递增
                        if (t > check){
                            check = t;
                        }else {
                            System.out.println("产生并发问题： check=" + check + ",count=" + count);
                        }

                    }finally {
                        lock.unlock();
                    }

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
