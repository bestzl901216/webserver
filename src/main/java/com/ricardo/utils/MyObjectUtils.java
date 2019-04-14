package com.ricardo.utils;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;

/**
 * @author Ricardo
 * @date 2018/12/10
 */
@Slf4j
public class MyObjectUtils {

    private MyObjectUtils() {
        throw new RuntimeException("no constructor!!!");
    }

    /**
     * 创建目标类型实例，根据源实例的属性类型和名称，赋予相同属性值
     * @param object 数据源
     * @param clazz 目标类型
     * @return 目标类型对象
     * @throws NullPointerException 参数为null或者集合包含null时，抛出空指针异常
     */
    @Deprecated
    private static <T> T transform(Object object, Class<T> clazz) {
        if (object == null || clazz == null) {
            throw new NullPointerException("params is null");
        }
        if (object instanceof Collection) {
            throw new RuntimeException("obj can't be collection");
        }

        Class objClazz = object.getClass();
        T t;
        try {
            t = clazz.getConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("can't initial target");
        }
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            Field objField;
            try {
                objField = objClazz.getDeclaredField(field.getName());
            } catch (NoSuchFieldException e) {
                continue;
            }
            if (field.getType() == objField.getType()) {
                objField.setAccessible(true);
                field.setAccessible(true);
                try {
                    field.set(t, objField.get(object));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("can't access obj's field value");
                }
            }
        }
        return t;
    }

    /**
     * 创建目标类型实例集合，根据源实例的属性类型和名称，赋予相同属性值
     * @param objects 待转换的实例集合
     * @param clazz 目标类型
     * @return 目标类型实例集合
     * @throws NullPointerException 参数为null或者集合包含null时，抛出空指针异常
     */
    @Deprecated
    private static <T> List<T> batchTransform(List<?> objects, Class<T> clazz) {
        if (objects == null || clazz == null) {
            throw new NullPointerException("params is null");
        }
        List<T> result = Lists.newArrayList();
        for (Object object : objects) {
            result.add(transform(object, clazz));
        }
        return result;
    }
}
