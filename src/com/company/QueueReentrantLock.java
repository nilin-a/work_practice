package com.company;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

// простейший многопоточный ограниченный буфер с использованием ReentrantLock
public class QueueReentrantLock<T> {
    private volatile int size = 0;
    private final Object[] content;
    private final int capacity;
    private int out;
    private int in;
    private final ReentrantLock locker = new ReentrantLock();
    private final Condition isEmpty = locker.newCondition();
    private final Condition isFull = locker.newCondition();

    public QueueReentrantLock(int capacity) {
        try {
            locker.lock();
            this.capacity = capacity;
            content = new Object[capacity];
            out = 0;
            in = 0;
        } finally {
            locker.unlock();
        }
    }

    private int cycleInc(int index) {
        return (++index == capacity)
                ? 0
                : index;
    }

    @SuppressWarnings("unchecked")
    public T get() throws InterruptedException {
        try {
            locker.lockInterruptibly();
            if (size == 0) {
                while (size < 1) {
                    isEmpty.await();
                }
            }
            final Object value = content[out];
            content[out] = null;
            if (size > 1) {
                out = cycleInc(out);
            }
            size--;
            isFull.signal();
            return (T) value;
        } finally {
            locker.unlock();
        }
    }

    public QueueReentrantLock<T> put(T value) throws InterruptedException {
        try {
            locker.lockInterruptibly();
            if (size == capacity) {
                while (size == capacity) {
                    isFull.await();
                }
            }
            if (size == 0) {
                content[in] = value;
            } else {
                in = cycleInc(in);
                content[in] = value;
            }
            size++;
            isEmpty.signal();
        } finally {
            locker.unlock();
        }
        return this;
    }

}
