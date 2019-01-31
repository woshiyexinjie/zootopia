package com.helloxin.zootopia.monkey.crawler;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;


/**
 * Created by nandiexin on 2019/1/31.
 */
@Component
public class MovieCrawlerImpl implements MovieCrawler {


    private Logger logger = LoggerFactory.getLogger(getClass());


    @Override
    public void crawlerTodayMovieInfo(){
        logger.info("crawlerTodayMovieInfo start time ={}",new Date());




    }

}
