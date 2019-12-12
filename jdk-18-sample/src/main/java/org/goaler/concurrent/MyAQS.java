package org.goaler.concurrent;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class MyAQS extends AbstractQueuedSynchronizer {

    @Override
    protected boolean tryAcquire(int arg) {
        if (arg <= 0){
            throw new RuntimeException("参数必须大于0");
        }

        Thread currentThread = Thread.currentThread();
        int state = getState();
        if (state == 0){
            if (compareAndSetState(state, state + arg)){
                setExclusiveOwnerThread(currentThread);
                return true;
            }
        }else if (getExclusiveOwnerThread() == currentThread){
            setState(state + arg);
            return true;
        }
        return false;
    }

    @Override
    protected boolean tryRelease(int arg) {
        Thread currentThread = Thread.currentThread();

        if (getExclusiveOwnerThread() != currentThread){
            throw new RuntimeException("线程不是同步器持有者");
        }

        int t = getState() - arg;
        boolean free = false;
        if (t == 0){
            free = true;
            setExclusiveOwnerThread(null);
        }
        setState(t);

        return free;
    }

    @Override
    protected boolean isHeldExclusively() {
        return Thread.currentThread() == getExclusiveOwnerThread();
    }

    ConditionObject newCondition() {
        return new ConditionObject();
    }
}
