package com.ricardo.utils;

/**
 * @author Ricardo
 * @date 2018/12/16
 */
public class DateUtil {

    public static int getCurrentSeconds() {
        return (int) (System.currentTimeMillis() / 1000);
    }
}
