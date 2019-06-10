package com.helloxin.zootopia.magpie;

import static org.junit.Assert.assertTrue;

import com.helloxin.zootopia.magpie.dao.UserDO;
import com.helloxin.zootopia.magpie.dao.UserRepository;
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
    private UserRepository userRepository;


    @Test
    public void test() throws Exception {

//        UserDO userDO = new UserDO();
//        userDO.setUserId("23423452354");
////        userDO.setUserName("xin");
//        userDO.setUserPhone("12312142444");
//        userDO.setPassword("hahaha");
//        userDO.setDateCreate(new Date());
//        userDO.setDateUpdate(new Date());
//        userRepository.save(userDO);


        UserDO userDO1 = userRepository.findByUserNameAndPassword("xin","hahaha");
        System.out.println(userDO1.toString());

    }

}
