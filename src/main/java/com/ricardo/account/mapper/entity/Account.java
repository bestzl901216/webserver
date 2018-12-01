package com.ricardo.account.mapper.entity;

import com.ricardo.common.BaseEntity;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Ricardo
 * @date 2018/12/1
 */
@Data
public class Account extends BaseEntity {

    private Integer id = 0;

    private String name = "";

    private String nickName = "";

    private String pwd = "";

    private String salt = "";

    /**
     * 用户所有角色值，用于shiro做角色权限的判断
     **/
    private Set<String> roles = new HashSet<>();

    /**
     * 用户所有权限值，用于shiro做资源权限的判断
     **/
    private Set<String> perms = new HashSet<>();

}
