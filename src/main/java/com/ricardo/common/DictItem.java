package com.ricardo.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ricardo
 * @date 2018/12/27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DictItem {

    private String label;
    private String value;

}
