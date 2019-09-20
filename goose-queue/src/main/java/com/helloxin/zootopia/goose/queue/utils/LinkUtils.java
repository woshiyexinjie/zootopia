package com.helloxin.zootopia.goose.queue.utils;

/**
 * Created by yexin on 2019/9/20.
 */
public class LinkUtils {


    private static final ThreadLocal<LinkBean> linkHolder = new ThreadLocal<>();

    public static void setlinkId(String linkId){
        linkHolder.get().setLinkId(linkId);
    }

    public static String getLinkId(){
         return  linkHolder.get().getLinkId();
    }



}
