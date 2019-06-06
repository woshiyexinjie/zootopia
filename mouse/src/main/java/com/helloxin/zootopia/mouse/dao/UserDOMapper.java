package com.helloxin.zootopia.mouse.dao;

import com.helloxin.zootopia.mouse.model.MoviePO;
import com.helloxin.zootopia.mouse.model.UserDO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by nandiexin on 2019/2/2.
 */
@Mapper
public interface UserDOMapper {

    @Select("select user_id as userId,user_name as userName,user_phone as userPhone,password,date_create as dateCreate," +
            "date_update as dateUpdate from user limit 20")
    List<UserDO> selectAll();
}
