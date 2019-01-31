package com.helloxin.zootopia.monkey;

import static org.junit.Assert.assertTrue;

import com.helloxin.zootopia.monkey.crawler.MovieCrawler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Unit test for simple App.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ApplicationTest {

    @Autowired
    private MovieCrawler movieCrawler;

    @Test
    public void test() throws Exception {

        movieCrawler.crawlerTodayMovieInfo();
    }


}
