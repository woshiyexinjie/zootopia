package com.helloxin.zootopia.goose.queue.log;

import ch.qos.logback.classic.PatternLayout;

/**
 * Created by nandiexin on 2019/6/6.
 */
public class MyPatternLayout extends PatternLayout {
    static {
        defaultConverterMap.put("linkId", BoothConvert.class.getName());
    }

}