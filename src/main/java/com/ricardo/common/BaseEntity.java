package com.ricardo.common;

import com.ricardo.account.mapper.entity.Account;
import com.ricardo.utils.DateUtil;
import lombok.Data;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import javax.persistence.Column;

/**
 * @author Ricardo
 * @date 2018/12/1
 */
@Data
public abstract class BaseEntity {

    @Column(name = "create_time", updatable = false)
    private Integer createTime;

    @Column(name = "create_user", updatable = false)
    private Integer createUser;

    @Column(name = "update_time", insertable = false)
    private Integer updateTime;

    @Column(name = "update_user", insertable = false)
    private Integer updateUser;

    public void addCreateInfo() {
        this.createTime = DateUtil.getCurrentSeconds();
        Subject currentUser = SecurityUtils.getSubject();
        Account account = (Account) currentUser.getPrincipal();
        this.createUser = account.getId();
    }

    public void addUpdateInfo() {
        this.updateTime = DateUtil.getCurrentSeconds();
        Subject currentUser = SecurityUtils.getSubject();
        Account account = (Account) currentUser.getPrincipal();
        this.updateUser = account.getId();
    }
}
