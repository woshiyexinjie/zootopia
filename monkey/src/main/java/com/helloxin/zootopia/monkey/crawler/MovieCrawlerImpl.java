package com.helloxin.zootopia.monkey.crawler;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.Date;
import java.util.List;


/**
 * Created by nandiexin on 2019/1/31.
 */
@Component
public class MovieCrawlerImpl implements MovieCrawler  {

    private Logger logger = LoggerFactory.getLogger(getClass());

    static WebDriver driver = null;

    static{
        System.setProperty("webdriver.chrome.driver","/usr/local/bin/chromedriver");
        driver =new ChromeDriver();
    }


    @Override
    public void crawlerTodayMovieInfo(){

        logger.info("crawlerTodayMovieInfo start time ={}",new Date());
        // 这里想看看电影网站
        driver.get("https://movie.douban.com/cinema/nowplaying/hangzhou/");

        List<WebElement> items = driver.findElements(By.className("list-item"));
        if(!CollectionUtils.isEmpty(items)){
            logger.info("要处理的数据 size={}",items.size());

        }else{
            logger.info("抓取到的豆瓣电影上映数据为空");
        }


        driver.close();

    }



}
