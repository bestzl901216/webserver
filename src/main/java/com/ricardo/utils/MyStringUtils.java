package com.ricardo.utils;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @author Ricardo
 * @date 2019/3/27
 */
public class MyStringUtils {

    private MyStringUtils() {
        throw new RuntimeException("no constructor!!!");
    }

    public static List<Integer> split(String s) {
        List<Integer> result = Lists.newArrayList();
        if (StringUtils.isNotBlank(s)) {
            String[] temp = s.split(",");
            for (String e : temp) {
                result.add(Integer.valueOf(e));
            }
        }
        return result;
    }
}
