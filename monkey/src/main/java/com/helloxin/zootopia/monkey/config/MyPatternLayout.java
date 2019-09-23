package com.helloxin.zootopia.monkey.config;

import ch.qos.logback.classic.PatternLayout;
import com.helloxin.zootopia.goose.queue.log.BoothConvert;

/**
 * Created by nandiexin on 2019/6/6.
 */
public class MyPatternLayout extends PatternLayout {
    static {
        defaultConverterMap.put("linkId", BoothConvert.class.getName());
    }
}