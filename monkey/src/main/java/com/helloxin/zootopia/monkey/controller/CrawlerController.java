package com.helloxin.zootopia.monkey.controller;

import com.helloxin.zootopia.monkey.crawler.MovieCrawler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.Instant;

/**
 * Created by nandiexin on 2019/2/27.
 */
@RestController
public class CrawlerController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MovieCrawler movieCrawler;

    @PostMapping("crawlerDouban")
    public void crawlerDoubanMovie() {

        Instant begin = Instant.now();
        logger.info("reportCurrentTime start time={}", begin);

        movieCrawler.crawlerTodayMovieInfo();

        Instant end = Instant.now();
        logger.info("reportCurrentTime end time={},duration={}", end, Duration.between(begin,end).getNano()/1000);
    }

    @GetMapping("test")
    public void test() {
        logger.info("test2");
    }
}
