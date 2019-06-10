package com.helloxin.zootopia.magpie.dao;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by nandiexin on 2019/4/4.
 */
@Entity
@Table(name="user")
@Data
public class UserDO {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private  String userId;

    private String userName;

    private String userPhone;

    private String password;

    private Date dateCreate;

    private Date dateUpdate;



}
