package com.ricardo.account;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

/**
 * @author Ricardo
 * @date 2018/12/2
 */
@Slf4j
@RestController
@RequestMapping("account")
public class AccountController {

    @PostMapping("login")
    public Boolean login(@NotBlank String name, @NotBlank String pwd) {
        log.info("login user's name = [{}]", name);
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(name, pwd);
        currentUser.login(token);
        return true;
    }

    @GetMapping("logout")
    public Boolean login() {
        log.info("enter method logout");
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        return true;
    }
}
