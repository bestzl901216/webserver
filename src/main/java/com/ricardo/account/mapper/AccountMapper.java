package com.ricardo.shiro.mapper;

import com.ricardo.shiro.mapper.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author Ricardo
 * @date 2018/12/1
 */
@Mapper
@Repository
public interface AccountMapper {

    @Select("select * from account where name = #{name}")
    Account findAccountByName(String name);

    @Select("select * from account where id = #{id}")
    Account findAccountById(Integer id);
}
