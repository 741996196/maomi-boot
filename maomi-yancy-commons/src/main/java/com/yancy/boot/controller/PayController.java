package com.yancy.boot.controller;

import com.yancy.boot.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PayController {
    @Autowired
    private PayService payService;

    @RequestMapping(value = "/pay", method = RequestMethod.GET)
    public void create(){
        this.payService.pay(1,2,10);
    }
}
