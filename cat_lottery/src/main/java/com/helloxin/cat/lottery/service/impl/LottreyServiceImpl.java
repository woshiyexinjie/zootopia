package com.helloxin.cat.lottery.service.impl;

import com.helloxin.cat.lottery.dto.ElementDto;
import com.helloxin.cat.lottery.service.LottreyService;
import com.helloxin.zootopia.mouse.dao.LotteryDOMapper;
import com.helloxin.zootopia.mouse.dao.UserDOMapper;
import com.helloxin.zootopia.mouse.model.UserDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by nandiexin on 2019/5/23.
 */
@Service
public class LottreyServiceImpl implements LottreyService,InitializingBean{

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserDOMapper userDOMapper;

    @Autowired
    private LotteryDOMapper lotteryDOMapper;


    //总共进行抽奖的集合
    final static List<ElementDto> prizeList = new ArrayList<>(5000);

    //已经中奖的集合
    final static List<ElementDto> existMap = new ArrayList<>(1000);

    @Override
    public void afterPropertiesSet() throws Exception {

        List<UserDO> userDOS = userDOMapper.selectAll();

        if(userDOS.size()>0){
            for (UserDO userDO:userDOS){
                ElementDto elementDto = new ElementDto();
                elementDto.setUserId(userDO.getUserId());
                elementDto.setUserName(userDO.getUserName());
                prizeList.add(elementDto);
            }
        }
    }

    @Override
    public List<ElementDto> getRandonLuckyUser(int num,String lotterGame){

        logger.info("num={},lotterGame={}",num,lotterGame);

        Long begin = System.currentTimeMillis();

        List<ElementDto>  elist = new ArrayList<>();
        if(num >= prizeList.size()){
            return prizeList;
        }

        Random random = new Random();
        int size = prizeList.size();
        while (elist.size() <  num){
            int next = random.nextInt(size);
            ElementDto elementDto = prizeList.get(next);
            if(elist.contains(elementDto)){
                continue;
            }
            elist.add(elementDto);
        }

        logger.info("duration={}",System.currentTimeMillis()-begin);

        return elist;

    }


    public static void main(String[] args) {

        Random random = new Random();
        for (int i =0;i<15;i++){
            System.out.println(random.nextInt(3));
        }


    }



}
