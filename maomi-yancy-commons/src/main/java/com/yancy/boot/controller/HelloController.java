package com.yancy.boot.controller;

import com.yancy.boot.mapper.account.entity.CapitalAccount;
import com.yancy.boot.util.RedisUtil;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@RestController
public class HelloController {
    //Redis工具类
    @Resource
    private RedisUtil redisUtil;

    @GetMapping("/home")
    public String home(){
        return "hello yancy";
    }
    //把数据存放进Redis
    @PostMapping("/setRedis")
    public void setRedis(@RequestBody CapitalAccount capitalAccount){
        Assert.notNull(capitalAccount,"为null");
        redisUtil.set(capitalAccount.getUserId().toString(),capitalAccount);
    }
    //从Redis拿出数据
    @GetMapping("/getRedis")
    public Object getRedis(String key){
       return redisUtil.get(key);
    }
}
