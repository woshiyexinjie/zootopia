package com.helloxin.zootopia.dog.common.web;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by nandiexin on 2019/6/10.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {

    String msg;

    Integer code;

    T data;

    Boolean success;

    public static <T> Result<T> buildSuccess(T data){

        return new Result<T>("",ResultCode.HTTP_CUSCCESS,data,true);

    }

    public static <T>  Result buildFail(T data,String msg){

        return new Result<T>("",ResultCode.HTTP_FAIL,data,false);
    }


}
