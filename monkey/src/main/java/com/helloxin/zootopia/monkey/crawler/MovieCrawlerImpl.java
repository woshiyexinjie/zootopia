package com.helloxin.zootopia.monkey.crawler;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;


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

        driver.get("http://www.baidu.com");
        driver.close();




    }

}
