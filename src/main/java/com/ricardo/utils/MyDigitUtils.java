package com.ricardo.utils;

/**
 * @author Ricardo
 * @date 2019/3/31
 */
public class MyDigitUtils {

    private MyDigitUtils() {
        throw new RuntimeException("no constructor!!!");
    }

    public static void checkNaturalNumber(Integer i) {
        if (i == null) {
            throw new RuntimeException("对象为null");
        }
        if (i < 0) {
            throw new RuntimeException("非自然数");
        }
    }

    public static void checkPositiveInteger(Integer i) {
        if (i == null) {
            throw new RuntimeException("对象为null");
        }
        if (i <= 0) {
            throw new RuntimeException("非正整数");
        }
    }
}
