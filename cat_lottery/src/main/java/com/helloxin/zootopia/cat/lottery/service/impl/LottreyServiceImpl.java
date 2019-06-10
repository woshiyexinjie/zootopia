package com.helloxin.zootopia.cat.lottery.service.impl;

import com.helloxin.zootopia.cat.lottery.dto.ElementDto;
import com.helloxin.zootopia.cat.lottery.service.LottreyService;
import com.helloxin.zootopia.mouse.dao.LotteryDOMapper;
import com.helloxin.zootopia.mouse.dao.UserDOMapper;
import com.helloxin.zootopia.mouse.model.UserDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
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

    final ThreadLocalRandom random = ThreadLocalRandom.current();

    //总共进行抽奖的集合
    final static List<ElementDto> candidateList = new ArrayList<>(5000);

    //已经中奖的集合
    final static List<ElementDto> prizeList = new ArrayList<>(1000);

    @Override
    public void afterPropertiesSet() throws Exception {

//        List<UserDO> userDOS = userDOMapper.selectAll();
//
//        if(userDOS.size()>0){
//            for (UserDO userDO:userDOS){
//                ElementDto elementDto = new ElementDto();
//                elementDto.setUserId(userDO.getUserId());
//                elementDto.setUserName(userDO.getUserName());
//                candidateList.add(elementDto);
//            }
//        }
        initAllElement();
    }

    @Override
    public List<ElementDto> getRandonLuckyUser(int num,String lotterGame,boolean isRepeat){
        //根据num获取指定数量的获奖人，这里不可重复抽奖
        logger.info("num={},lotterGame={}",num,lotterGame);

        Long begin = System.currentTimeMillis();

        List<ElementDto>  elist = new ArrayList<>();
        List<UserDO> prizeUser = new ArrayList<>();
        if(num >= candidateList.size()){
            return candidateList;
        }


        int size = candidateList.size();
        while (elist.size() <  num){
            int next = random.nextInt(size);
            ElementDto elementDto = candidateList.get(next);
            if(elist.contains(elementDto) ){
                continue;
            }
            if(isRepeat ==false && prizeList.contains(elementDto)){
                continue;
            }
            UserDO userDO = new UserDO();
            userDO.setUserId(elementDto.getUserId());
            userDO.setUserName(elementDto.getUserName());
            prizeUser.add(userDO);
            elist.add(elementDto);
            prizeList.add(elementDto);
        }
        //将中奖的人记录到数据库

        lotteryDOMapper.addLuckuUser(prizeUser,lotterGame);
        logger.info("duration={}",System.currentTimeMillis()-begin);

        return elist;

    }

    @Override
    public List<ElementDto> getAllLuckyUser(int num){
        //返回所有的参加抽奖的员工数量
        return candidateList;
    }


    //随机获取指定数量的员工
    @Override
    public List<ElementDto> getMaybeUser(int num,String lotterGame,boolean isRepeat){
        logger.info("getMaybeUser num={},lotterGame={},isRepeat={}",num,lotterGame,isRepeat);

        //可以重复的实现起来比较简单些

        if(num >= candidateList.size()){
            return candidateList;
        }
        if(isRepeat == true){
           return getMaybeUserRepeate(num,lotterGame);
        }else{
            return getMaybeUserNoRepeate(num,lotterGame);
        }


    }

    private List<ElementDto> getMaybeUserNoRepeate(int num, String lotterGame) {



        List<ElementDto> elist = new ArrayList<>();
        List<UserDO> prizeUser = new ArrayList<>();
        int size = candidateList.size();
        while (elist.size() <  num){
            int next = random.nextInt(size);
            ElementDto elementDto = candidateList.get(next);
            if(elist.contains(elementDto)){
                continue;
            }
//            if(){
//
//            }
            UserDO userDO = new UserDO();
            userDO.setUserId(elementDto.getUserId());
            userDO.setUserName(elementDto.getUserName());
            prizeUser.add(userDO);
            elist.add(elementDto);
            prizeList.add(elementDto);
        }
        //将中奖的人记录到数据库

        lotteryDOMapper.addLuckuUser(prizeUser,lotterGame);
        return elist;
    }

    private List<ElementDto> getMaybeUserRepeate(int num, String lotterGame) {

        List<ElementDto> elist = new ArrayList<>();
        List<UserDO> prizeUser = new ArrayList<>();
        int size = candidateList.size();
        while (elist.size() <  num){
            int next = random.nextInt(size);
            ElementDto elementDto = candidateList.get(next);
            if(elist.contains(elementDto)){
                continue;
            }
            UserDO userDO = new UserDO();
            userDO.setUserId(elementDto.getUserId());
            userDO.setUserName(elementDto.getUserName());
            prizeUser.add(userDO);
            elist.add(elementDto);
            prizeList.add(elementDto);
        }
        //将中奖的人记录到数据库

        lotteryDOMapper.addLuckuUser(prizeUser,lotterGame);
        return elist;
    }


    @Override
    public void initAllElement(){
        candidateList.clear();
        List<UserDO> userDOS = userDOMapper.selectAll();

        if(userDOS.size()>0){
            for (UserDO userDO:userDOS){
                ElementDto elementDto = new ElementDto();
                elementDto.setUserId(userDO.getUserId());
                elementDto.setUserName(userDO.getUserName());
                candidateList.add(elementDto);
            }
        }
    }

    @Override
    public void clearPrizeElement(){
        prizeList.clear();
    }


}
