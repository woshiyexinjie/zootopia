package com.helloxin.zootopia.cat.lottery.service;

import com.helloxin.zootopia.cat.lottery.dto.ElementDto;

import java.util.List;

/**
 * Created by nandiexin on 2019/5/23.
 */
public interface LottreyService {

    /**
     * 抽取幸运观众
     * @param num
     * @param lotterGame
     * @param isRepeat true 可以重复 false 不可以重复
     * @return
     */
    List<ElementDto> getRandonLuckyUser(int num, String lotterGame, boolean isRepeat);

    /**
     * 获取全部的抽奖员工数据
     * @param num
     * @return
     */
    List<ElementDto> getAllLuckyUser(int num);

    //随机获取指定数量的员工

    /**
     * 获取可能中奖的员工
     * @param num
     * @param lotterGame
     * @param isRepeat true 可以重复 false 不可以重复
     * @return
     */
    List<ElementDto> getMaybeUser(int num, String lotterGame, boolean isRepeat);

    /**
     * 初始化所有的抽奖样本
     */
    void initAllElement();


    void initPrizeElement(String lotteryName);

    /**
     * 清除掉已经中奖的集合
     */
    void clearPrizeElement();
}
