package com.helloxin.cat.lottery.controller;

import com.helloxin.cat.lottery.dto.ElementDto;
import com.helloxin.cat.lottery.service.LottreyService;
import com.helloxin.zootopia.mouse.dao.UserDOMapper;
import com.helloxin.zootopia.mouse.model.UserDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nandiexin on 2019/5/21.
 */
@RestController
public class LotteryController {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LottreyService lottreyService;

    @GetMapping("/nextLottrry")
    public List<ElementDto> getNextLottrry(@RequestParam Integer num, @RequestParam String lotteryGame){

        logger.info("getNextLottrry num={},lotteryGame={}",num,lotteryGame);



        return lottreyService.getRandonLuckyUser(num,lotteryGame);
    }

}
