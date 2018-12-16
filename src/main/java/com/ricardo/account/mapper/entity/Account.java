package com.ricardo.account.mapper.entity;

import com.ricardo.common.BaseEntity;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.IdentityDialect;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Set;

/**
 * @author Ricardo
 * @date 2018/12/1
 */
@Data
@Table(name = "account")
public class Account extends BaseEntity {

    @Id
    @KeySql(dialect = IdentityDialect.MYSQL)
    private Integer id;

    private String name;

    private String nickName;

    private String pwd;

    private String salt;

    /**
     * 用户所有角色值，用于shiro做角色权限的判断
     **/
    @Transient
    private Set<String> roles;

    /**
     * 用户所有权限值，用于shiro做资源权限的判断
     **/
    @Transient
    private Set<String> perms;

}
