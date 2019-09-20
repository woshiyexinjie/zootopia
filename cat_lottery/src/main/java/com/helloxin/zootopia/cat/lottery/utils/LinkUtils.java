package com.helloxin.zootopia.cat.lottery.utils;

/**
 * Created by yexin on 2019/9/20.
 */
public class LinkUtils {

    //简单试一下
    private static final ThreadLocal<String> linkHolder = new ThreadLocal<>();

    public static void setlinkId(String linkId){
        linkHolder.set(linkId);
    }

    public static String getLinkId(){
         return  linkHolder.get();
    }



}
