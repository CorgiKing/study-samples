package org.goaler.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;

/**
 * 基于自定义同步器解决消费-生产者问题
 */
public class MyBlockQueue<E> {
    private Object[] queue;
    private int count;
    private int sIndex;
    private int eIndex;
    private MyLock lock;
    /**
     * 不空条件，取值时等待
     */
    private Condition notEmpty;
    /**
     * 不满条件，添加时等待
     */
    private Condition notFull;

    public MyBlockQueue(int size){
        queue = new Object[size];
        lock = new MyLock();
        notEmpty = lock.newCondition();
        notFull =  lock.newCondition();
    }


    /**
     * 添加元素直到添加成功
     * @param e
     * @throws InterruptedException
     */
    public void put(E e) throws InterruptedException{
        lock.lockInterruptibly();
        try {
            while(queue.length == count){
                notFull.await();
            }
            addItem(e);
        }finally {
            lock.unlock();
        }
    }

    /**
     * 添加元素直到超时
     * @param e
     * @param timeout
     * @param timeUnit
     * @return
     * @throws InterruptedException
     */
    public boolean offer(E e, long timeout, TimeUnit timeUnit) throws InterruptedException{
        long timeNanos = timeUnit.toNanos(timeout);
        lock.lockInterruptibly();
        try {
            while(queue.length == count){
                if (timeNanos <= 0){
                    return false;
                }
                timeNanos = notFull.awaitNanos(timeNanos);
            }
            addItem(e);
            return true;
        }finally {
            lock.unlock();
        }
    }

    /**
     * 添加一个元素
     * @param e
     */
    private void addItem(E e) {
        queue[eIndex] = e;
        if (++eIndex == queue.length)
            eIndex = 0;
        count++;
        notEmpty.signal();
    }

    /**
     * 移除队列头元素，直到有元素
     * @return
     * @throws InterruptedException
     */
    public E take() throws InterruptedException{
        lock.lockInterruptibly();
        try {
            while(count == 0){
                notEmpty.await();
            }
            return delItem();
        }finally {
            lock.unlock();
        }
    }

    /**
     * 移除队列头元素，直到超时
     * @param timeout
     * @param timeUnit
     * @return
     * @throws InterruptedException
     */
    public E poll(long timeout, TimeUnit timeUnit) throws InterruptedException{
        long timeNanos = timeUnit.toNanos(timeout);
        lock.lockInterruptibly();
        try {
            while(count == 0){
                if (timeNanos <= 0){
                    return null;
                }
                timeNanos = notFull.awaitNanos(timeNanos);
            }
            return delItem();
        }finally {
            lock.unlock();
        }
    }

    /**
     * 删除队列头元素
     * @return
     */
    private E delItem() {
        E ret = (E) queue[sIndex];
        queue[sIndex] = null;
        --count;
        if (++sIndex == queue.length)
            sIndex = 0;

        notFull.signal();
        return ret;
    }



    public static void main(String[] args) throws Exception{
        MyBlockQueue<Integer> blockQueue = new MyBlockQueue<>(100);
        new Thread(()->{
            for (int i = 0;;++i){
                try {
                    blockQueue.put(i);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("入队移除");
                }
            }
        }).start();

        new Thread(()->{
            for (;;){
                try {
                    Integer ret = blockQueue.take();
                    System.out.println("接收到：  " + ret);
                } catch (InterruptedException e) {
                    System.out.println("接收异常");
                }
            }
        }).start();
    }
}
