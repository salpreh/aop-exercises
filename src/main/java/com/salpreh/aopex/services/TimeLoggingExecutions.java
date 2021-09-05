package com.salpreh.aopex.services;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Supplier;

import com.salpreh.aopex.annotations.LogTime;

public class TimeLoggingExecutions {

    @LogTime
    public void waitAndRun(long millis, Runnable r) {
        try {
            TimeUnit.MILLISECONDS.sleep(millis);
            r.run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @LogTime
    public <T> T waitAndRun(long millis, Supplier<T> s) {
        try {
            TimeUnit.MILLISECONDS.sleep(millis);

            return s.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

    @LogTime
    public <T, R> R waitAndRun(long millis, Function<T, R> f, T arg) {
        try {
            TimeUnit.MILLISECONDS.sleep(millis);

            return f.apply(arg);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }
}
