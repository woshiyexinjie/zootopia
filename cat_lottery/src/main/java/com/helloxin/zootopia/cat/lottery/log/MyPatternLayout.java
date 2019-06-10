package com.helloxin.zootopia.cat.lottery.log;

import ch.qos.logback.classic.PatternLayout;

/**
 * Created by nandiexin on 2019/6/6.
 */
public class MyPatternLayout extends PatternLayout {
    static {
        defaultConverterMap.put("ip", BoothConvert.class.getName());
    }

}