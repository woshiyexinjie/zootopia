package com.helloxin.zootopia.magpie.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.stereotype.Service;

/**
 * Created by nandiexin on 2019/4/5.
 */
@Service
public class RedisServiceImpl implements RedisService,InitializingBean {

    final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    private RedisConnection connection = null;

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("redisConnection init");
        connection = redisConnectionFactory.getConnection();
        logger.info("redisConnection init finish");
    }

    @Override
    public Boolean setCache(String key, String value){
        Boolean result = connection.set(key.getBytes(), value.getBytes());
        return result;
    }

    @Override
    public String setCache(String key){
        byte[] bytes = connection.get(key.getBytes());
        return bytes.toString();
    }


    @Override
    public Long delCache(String key){
        Long result = connection.del(key.getBytes());
        return result;
    }















}
