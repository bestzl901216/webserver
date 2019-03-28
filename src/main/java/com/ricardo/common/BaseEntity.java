package com.ricardo.common;

import com.ricardo.account.AccountUtils;
import com.ricardo.utils.DateUtils;
import lombok.Data;

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
        this.createUser = AccountUtils.getCurrentUid();
        this.createTime = DateUtils.getCurrentSeconds();
    }

    public void addUpdateInfo() {
        this.updateUser = AccountUtils.getCurrentUid();
        this.updateTime = DateUtils.getCurrentSeconds();
    }
}
