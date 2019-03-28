package com.ricardo.account;

import com.ricardo.account.mapper.entity.Account;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * @author Ricardo
 * @date 2019/2/13
 */
public class AccountUtils {

    private AccountUtils() {
        throw new RuntimeException("no constructors!!!");
    }

    public static Integer getCurrentUid() {
        Subject currentUser = SecurityUtils.getSubject();
        Account account = (Account) currentUser.getPrincipal();
        return account.getId();
    }
}
