package com.ricardo.account.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author Ricardo
 * @date 2018/12/16
 */
@Data
public class AccountLoginDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String pwd;
}
