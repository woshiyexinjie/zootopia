package com.helloxin.zootopia.monkey.crawler;



import com.helloxin.zootopia.mouse.dao.MoviePOMapper;
import com.helloxin.zootopia.mouse.model.MoviePO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private MoviePOMapper moviePOMapper;




    static{
        System.setProperty("webdriver.chrome.driver","/usr/local/bin/chromedriver");
//        driver =new ChromeDriver();
    }


    @Override
    public void crawlerTodayMovieInfo(){

        logger.info("crawlerTodayMovieInfo start time ={}",new Date());


        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("user-agent='Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36'");

        DesiredCapabilities cap = DesiredCapabilities.chrome();
        cap.setCapability(ChromeOptions.CAPABILITY, options);

        WebDriver driver = new ChromeDriver();




        // 这里想看看电影网站
        driver.get("https://movie.douban.com/cinema/nowplaying/hangzhou/");

        List<WebElement> items = driver.findElement(By.className("lists")).findElements(By.className("list-item"));
        if(!CollectionUtils.isEmpty(items)){
            logger.info("要处理的数据 size={}",items.size());

            items.stream().forEach(x->{

                String movieName = x.getAttribute("data-title");
                String director =x.getAttribute("data-director");
                String actors =x.getAttribute("data-actors");
                String release =x.getAttribute("data-release");
                String duration =x.getAttribute("data-duration");
                String region =x.getAttribute("data-region");
                String score =x.getAttribute("data-score");
                String movieDetail= x.findElement(By.className("poster")).findElement(By.className("ticket-btn")).getAttribute("href");

                logger.info("movie movieNanme={},director={},actors={},release={},duration={},region={},score={},movieDetail={}",movieName,director,actors,release,duration,region,score,movieDetail);
                MoviePO moviePO = new MoviePO();
                moviePO.setActors(actors);
                moviePO.setCategory(null);
                moviePO.setDateCreate(new Date());
                moviePO.setDateUpdate(new Date());
                moviePO.setDirector(director);
                moviePO.setDoubanDetail(movieDetail);
                moviePO.setDuration(duration);
                moviePO.setMovieName(movieName);
                moviePO.setRegion(region);
                moviePO.setScore(Double.valueOf(score));
                moviePO.setSource("douban");
                moviePO.setIsDelete((byte)0);
                moviePOMapper.insert(moviePO);

                logger.info("insert movie id={}",moviePO.getId());

            });

        }else{
            logger.info("抓取到的豆瓣电影上映数据为空");
        }


        driver.close();

    }



}
