package com.helloxin.magpie.dao;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by nandiexin on 2019/4/4.
 */
public interface UserRepository extends JpaRepository<UserDO, Long> {

     public UserDO findByUserNameAndPassword(String userName,String password);

}
