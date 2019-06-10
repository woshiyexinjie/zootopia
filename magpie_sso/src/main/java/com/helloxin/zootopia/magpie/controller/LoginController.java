package com.helloxin.zootopia.magpie.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by nandiexin on 2019/4/4.
 */
@RestController
public class LoginController {

    final Logger logger = LoggerFactory.getLogger(this.getClass());


    @RequestMapping("login")
    public String login(String userName,String password){

        logger.info("login username={},password={}",userName,password);

        String token ="Hello xin!";

        return token;
    }
}
