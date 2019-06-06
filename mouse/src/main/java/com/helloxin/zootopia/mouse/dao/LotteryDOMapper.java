package com.helloxin.zootopia.mouse.dao;

import com.helloxin.zootopia.mouse.model.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by nandiexin on 2019/2/2.
 */
@Mapper
public interface LotteryDOMapper {

    @Select("select user_id as userId,user_name as userName from lottery where lottery_game=#{lotteryGame}")
    List<UserDO> selectAllByLotteryGame(@Param("lotteryGame")String lotteryGame);
}
