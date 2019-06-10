package com.helloxin.zootopia.swordfish.controller;

import com.helloxin.zootopia.swordfish.service.XinImproveSnowGenerate;
import com.helloxin.zootopia.swordfish.service.XinSnowWorkIdGenerate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * Created by nandiexin on 2019/4/4.
 */
@RestController
public class GenerateMachineController {

    final Logger logger = LoggerFactory.getLogger(this.getClass()) ;


    @Autowired
    private XinSnowWorkIdGenerate xinSnowWorkIdGenerate;

    @Autowired
    private XinImproveSnowGenerate xinImproveSnowGenerate;



    @RequestMapping("genarateId")
    public Long genarateId() {
        return xinSnowWorkIdGenerate.nextId();
    }


    @RequestMapping("imporveGenarateId")
    public Long imporveGenarateId() {

        return xinImproveSnowGenerate.nextId();
    }

    @RequestMapping("imporveGenarateIdWithYear")
    public String imporveGenarateIdWithYear() {
        return LocalDateTime.now().getYear()+""+xinImproveSnowGenerate.nextId();
    }

}
