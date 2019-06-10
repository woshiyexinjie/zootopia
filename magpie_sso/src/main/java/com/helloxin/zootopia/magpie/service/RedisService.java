package com.helloxin.zootopia.magpie.service;

/**
 * Created by nandiexin on 2019/4/5.
 */
public interface RedisService {

    Boolean setCache(String key, String value);

    String setCache(String key);

    Long delCache(String key);
}
