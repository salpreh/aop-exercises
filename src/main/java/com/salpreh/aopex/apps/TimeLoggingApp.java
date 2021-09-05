package com.salpreh.aopex.apps;

import java.util.function.Function;

import com.salpreh.aopex.services.TimeLoggingExecutions;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class TimeLoggingApp {
    public static void main(String[] args) {
        TimeLoggingExecutions tle = new TimeLoggingExecutions();
        tle.waitAndRun(2000, () -> log.info("Runnable exec!"));

        String sRes = tle.waitAndRun(1000, () -> "Supplier string!");
        log.info("Supplier exe result: '{}'", sRes);

        Function<Integer, Boolean> isPairF = i -> i%2 == 0;
        boolean fRes = tle.waitAndRun(1500, isPairF, 45);
        log.info("Function exe result: '{}'", fRes);
    }
}
