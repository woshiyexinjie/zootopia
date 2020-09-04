package com.helloxin.audit;

import com.helloxin.param.IRecordParam;
import org.springframework.stereotype.Component;

@Component
public class MyParam implements IRecordParam {
    @Override
    public String operator() {
        //这里一般可以集成 登陆服务 或者 拿到用户信息的方式
        return "shuaixin";
    }
}
