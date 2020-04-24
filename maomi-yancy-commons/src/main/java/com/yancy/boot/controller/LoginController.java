package com.yancy.boot.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Api(value = "用户登录controller",tags = "登录操作接口")
@Controller
public class LoginController {

    @ApiOperation(value = "获取登录页面",notes = "没有问题")
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/success")
    public String success(){
        return "success";
    }

    @GetMapping("/unauthorized")
    public String unauthorized(){
        return "unauthorized";
    }
}
