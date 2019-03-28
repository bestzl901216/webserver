package com.ricardo.utils;

import com.google.common.collect.Lists;
import com.ricardo.common.PageResult;
import org.apache.ibatis.session.RowBounds;

/**
 * @author Ricardo
 * @date 2018/12/16
 */
public class PageUtils {

    private PageUtils() {
        throw new RuntimeException("no constructors!!!");
    }

    public static RowBounds getRowBounds(Integer page, Integer size) {
        int limit = size == null || size < 1 ? 1 : size;
        int offset = page == null || page < 1 ? 0 : (page - 1) * limit;
        return new RowBounds(offset, limit);
    }

    public static <T> PageResult<T> getEmptyPageResult(Class<T> clazz) {
        return new PageResult<>(0, Lists.newArrayList());
    }

}
