package com.helloxin.zootopia.mouse.dao;

import com.helloxin.zootopia.mouse.model.UserDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * Created by nandiexin on 2019/2/2.
 */
@Mapper
public interface LotteryDOMapper {

    @Select("select user_id as userId,user_name as userName from lottery where lottery_game=#{lotteryGame}")
    List<UserDO> selectAllByLotteryGame(@Param("lotteryGame")String lotteryGame);

    @Insert({
            "<script>",
            "insert into lottery(user_id,user_name,lottery_game) values",
            "<foreach collection='prizeList'  item='item' index='index' separator=','>",
            "(#{item.userId},#{item.userName},#{lotteryGame})",
            "</foreach>",
            "</script>"
    })
    void addLuckuUser(@Param("prizeList") List<UserDO> prizeList, @Param("lotteryGame")String lotteryGame);
}
