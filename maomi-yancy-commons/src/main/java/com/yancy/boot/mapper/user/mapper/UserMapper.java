package com.yancy.boot.mapper.user.mapper;

import com.yancy.boot.mapper.user.entity.User;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


public interface UserMapper extends Mapper<User> {
//    @Select("select * from user")
    List<User> userAll();
}