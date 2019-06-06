package com.helloxin.cat.lottery.service;

import com.helloxin.cat.lottery.dto.ElementDto;

import java.util.List;

/**
 * Created by nandiexin on 2019/5/23.
 */
public interface LottreyService {

    public List<ElementDto> getRandonLuckyUser(int num,String LotterGame);
}
