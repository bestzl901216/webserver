package com.ricardo.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Ricardo
 * @date 2018/12/16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> {

    private Integer total;

    private List<T> rows;
}
