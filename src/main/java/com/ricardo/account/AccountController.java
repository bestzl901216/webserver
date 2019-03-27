package com.ricardo.account;

import com.ricardo.account.dto.AccountLoginDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Ricardo
 * @date 2018/12/2
 */
@Slf4j
@RestController
@RequestMapping("account")
public class AccountController {

    @PutMapping(value = "login")
    public Boolean login(@RequestBody @Valid AccountLoginDto dto) {
        log.info("enter method AccountController.login: name = [{}]", dto.getName());
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(dto.getName(), dto.getPwd());
        currentUser.login(token);
        return true;
    }

    @PutMapping("logout")
    public Boolean logout() {
        log.info("enter method AccountController.logout");
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        return true;
    }

}

