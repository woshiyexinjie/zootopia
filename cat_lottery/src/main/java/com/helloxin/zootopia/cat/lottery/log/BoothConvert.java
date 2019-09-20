package com.helloxin.zootopia.cat.lottery.log;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.helloxin.zootopia.cat.lottery.utils.LinkUtils;

import java.util.Objects;

/**
 * Created by nandiexin on 2019/6/6.
 */
public class BoothConvert extends ClassicConverter {

    @Override
    public String convert(ILoggingEvent event) {
        return LinkUtils.getLinkId() == null? "":LinkUtils.getLinkId();
    }

}
