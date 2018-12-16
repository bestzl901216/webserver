package com.ricardo.common;

import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author Ricardo
 * @date 2018/12/15
 */
public interface BaseMapper<T, PK> extends Mapper<T>, IdListMapper<T, PK> {
}
