package com.ricardo.utils;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.List;
import java.util.Set;

/**
 * @author Ricardo
 * @date 2019/4/14
 */
public class DozerUtils {

    private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();

    public static <T> T map(Object source, Class<T> destinationClass) {
        if (destinationClass == null) {
            throw new RuntimeException("缺少关键参数");
        }
        if (source == null) {
            return null;
        }
        return mapper.map(source, destinationClass);
    }

    public static <T> Set<T> map(Set<?> source, Class<T> destinationClass) {
        Set<T> set = Sets.newHashSet();
        if (source != null) {
            for (Object sourceObject : source) {
                T destinationObject = map(sourceObject, destinationClass);
                set.add(destinationObject);
            }
        }
        return set;
    }

    public static <T> List<T> map(List<?> source, Class<T> destinationClass) {
        List<T> list = Lists.newArrayList();
        if (source != null) {
            for (Object sourceObject : source) {
                T destinationObject = map(sourceObject, destinationClass);
                list.add(destinationObject);
            }
        }
        return list;
    }
}
