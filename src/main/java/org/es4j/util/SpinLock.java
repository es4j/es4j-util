package org.es4j.util;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

@SuppressWarnings("all")
public class SpinLock {

    @SuppressWarnings("all")    
    private static   Unsafe unsafe  = null;
    private volatile int    _lock   = 0;
    private static   long   _offset = 0;

    static {

        try {
            Class<?> clazz = Unsafe.class;
            Field f;

            f = clazz.getDeclaredField("theUnsafe");

            f.setAccessible(true);
            unsafe = (Unsafe) f.get(clazz);
            _offset = unsafe.objectFieldOffset(SpinLock.class.getDeclaredField("_lock"));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public void lock() {
        while (!unsafe.compareAndSwapInt(this, _offset, 0, 1)) {
            try {
                //Thread.sleep(1);
                Thread.yield();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void unlock() {
        _lock = 0;
    }
}
