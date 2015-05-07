package com.github.greengerong.future.install;

/**
 * ***************************************
 * *
 * Auth: green gerong                     *
 * Date: 2015                             *
 * blog: http://greengerong.github.io/    *
 * github: https://github.com/greengerong *
 * *
 * ****************************************
 */
public class SleepUtils {
    public static final double FAILE_WEIGHT = 0.6;

    public static void randomSleep() throws InterruptedException {
        Thread.sleep((long) (Math.random() * 1000));
    }

    static boolean isSuccess() {
        return Math.random() <= FAILE_WEIGHT;
    }
}
