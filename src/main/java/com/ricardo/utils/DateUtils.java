package com.ricardo.utils;

/**
 * @author Ricardo
 * @date 2018/12/16
 */
public class DateUtils {

    private DateUtils() {
        throw new RuntimeException("no constructor!!!");
    }

    public static int getCurrentSeconds() {
        return (int) (System.currentTimeMillis() / 1000);
    }
}
