package com.helloxin.zootopia.goose.queue.utils;

import java.util.UUID;

/**
 * Created by yexin on 2019/9/20.
 */
public class LinkUtils {


    private static final ThreadLocal<LinkBean> linkHolder = new ThreadLocal<>();

    //目前写了一个过滤器来拦截请求，所以如果没有；链路id，则会取一个
    static {
        LinkBean linkBean = new LinkBean();
        if(linkBean.getLinkId() == null){
            linkBean.setLinkId(UUID.randomUUID().toString());
        }
        linkHolder.set(linkBean);
    }


    //设置链路Id
    public static void setlinkId(String linkId){
        if(null == linkHolder.get()){
            linkHolder.set(new LinkBean());
        }
        linkHolder.get().setLinkId(linkId);
    }

    //返回链路Id，如果调用是不会存在没有的情况的
    public static String getLinkId(){
         return  linkHolder.get() == null ? "":linkHolder.get().getLinkId();
    }

}
