package com.ricardo.utils;

import org.apache.ibatis.session.RowBounds;

/**
 * @author Ricardo
 * @date 2018/12/16
 */
public class PageUtil {

    public static RowBounds getRowBounds(Integer page, Integer size) {
        int limit = size == null || size < 1 ? 1 : size;
        int offset = page == null || page < 1 ? 0 : (page - 1) * limit;
        return new RowBounds(offset, limit);
    }
}
