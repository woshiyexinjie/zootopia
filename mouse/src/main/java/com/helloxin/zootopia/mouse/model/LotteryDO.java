package com.helloxin.zootopia.mouse.model;

import lombok.Data;

import java.util.Date;

/**
 * Created by nandiexin on 2019/5/23.
 */
@Data
public class LotteryDO {

    String id;

    String userId;

    String userName;

    Date dateCreate;

    Date dateUpdate;
}
