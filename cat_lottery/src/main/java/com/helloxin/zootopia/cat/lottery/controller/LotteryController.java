package com.helloxin.zootopia.cat.lottery.controller;

import com.helloxin.zootopia.cat.lottery.dto.ElementDto;
import com.helloxin.zootopia.cat.lottery.service.LottreyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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



        return lottreyService.getRandonLuckyUser(num,lotteryGame,false);
    }

    @PostMapping("/initElement")
    public void initElement(@RequestParam("是否初始化全部样本 true 初始化，其他不处理") boolean initElement,
                                        @RequestParam("是否初始化全部样本 true 初始化，其他不处理") Boolean prizeClear){

        logger.info("getNextLottrry initElement={},prizeClear={}",initElement,prizeClear);

        if(true == initElement){
            lottreyService.initAllElement();
        }
        if(false == prizeClear){
            lottreyService.clearPrizeElement();
        }


    }

}
