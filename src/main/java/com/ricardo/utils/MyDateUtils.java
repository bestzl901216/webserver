package com.ricardo.utils;

/**
 * @author Ricardo
 * @date 2018/12/16
 */
public class MyDateUtils {

    private MyDateUtils() {
        throw new RuntimeException("no constructor!!!");
    }

    public static int getCurrentSeconds() {
        return (int) (System.currentTimeMillis() / 1000);
    }
}
