package com.helloxin.zootopia.goose.queue.log;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.helloxin.zootopia.goose.queue.utils.LinkUtils;

/**
 * Created by nandiexin on 2019/6/6.
 */
public class BoothConvert extends ClassicConverter {

    @Override
    public String convert(ILoggingEvent event) {
        return LinkUtils.getLinkId() == null? "":LinkUtils.getLinkId();
    }

}
