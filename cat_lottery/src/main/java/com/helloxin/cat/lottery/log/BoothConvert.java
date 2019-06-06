package com.helloxin.cat.lottery.log;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

/**
 * Created by nandiexin on 2019/6/6.
 */
public class BoothConvert extends ClassicConverter {

    @Override
    public String convert(ILoggingEvent event) {
        return "helloxin";
    }

}
