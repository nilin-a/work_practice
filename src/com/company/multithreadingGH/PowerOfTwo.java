package com.company.multithreadingGH;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;
// потокобезопасную реализацию класса с неблокирующим методом степени 2
public class PowerOfTwo {
    private AtomicReference<BigInteger> current = new AtomicReference<BigInteger>(null);

    public BigInteger next() {
        BigInteger recent, next;
        do {
            recent = current.get();
            next = (recent == null) ? BigInteger.valueOf(1) : recent.shiftLeft(1);
        } while (!current.compareAndSet(recent, next));
        return next;
    }
}
