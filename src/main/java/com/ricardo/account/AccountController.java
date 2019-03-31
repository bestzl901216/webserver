package com.ricardo.account;

import com.ricardo.account.dto.AccountLoginDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "账号服务")
@Slf4j
@RestController
@RequestMapping("account")
public class AccountController {

    @ApiOperation("登录")
    @PutMapping(value = "login")
    public void login(@RequestBody @Valid AccountLoginDto dto) {
        log.info("enter method AccountController.login: name = [{}]", dto.getName());
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(dto.getName(), dto.getPwd());
        currentUser.login(token);
    }

    @ApiOperation("登出")
    @PutMapping("logout")
    public void logout() {
        log.info("enter method AccountController.logout");
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
    }

}

