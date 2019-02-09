package com.helloxin.zootopia.monkey.schedul;

import com.helloxin.zootopia.monkey.crawler.MovieCrawler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;


/**
 * Created by nandiexin on 2019/2/3.
 */
@Component
public class MovieCrawlerSchedulJob {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MovieCrawler movieCrawler;

    @Scheduled(cron="5 5 5 * * *")
    public void reportCurrentTime() {

        Instant begin = Instant.now();
        logger.info("reportCurrentTime start time={}", begin);

        movieCrawler.crawlerTodayMovieInfo();

        Instant end = Instant.now();
        logger.info("reportCurrentTime end time={},duration={}", end, Duration.between(begin,end).getNano()/1000);
    }
}
