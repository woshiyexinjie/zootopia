package com.helloxin.zootopia.monkey;

import com.helloxin.zootopia.monkey.crawler.MovieCrawler;
import com.helloxin.zootopia.mouse.dao.MoviePOMapper;
import com.helloxin.zootopia.mouse.model.MoviePO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * Created by nandiexin on 2019/2/3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MovieDaoTest {

    @Autowired
    private MoviePOMapper moviePOMapper;

    @Test
    public void test() throws Exception {

        MoviePO movie = new MoviePO();
        movie.setActors("HH");
        movie.setCategory("JJ");
        movie.setDateCreate(new Date());
        movie.setDateUpdate(new Date());
        movie.setDirector("II");
        movie.setDoubanDetail("weer");
        movie.setDuration("120");
        movie.setMovieName("rt");
        movie.setRegion("china");
        movie.setScore(9.0D);
        movie.setSource("douban");
        movie.setIsDelete((byte)0);
        moviePOMapper.insert(movie);
    }


}
