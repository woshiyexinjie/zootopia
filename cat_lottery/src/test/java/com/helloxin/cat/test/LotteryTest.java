package com.helloxin.cat.test;

import com.helloxin.zootopia.cat.lottery.Application;
import com.helloxin.zootopia.cat.lottery.service.LottreyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Created by nandiexin on 2019/6/6.lotteryGame
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class LotteryTest {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private LottreyService lottreyService;

    @Test
    public void test() throws Exception {

        lottreyService.getRandonLuckyUser(1,"test-190606",false);

    }
}
