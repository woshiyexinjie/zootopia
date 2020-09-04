package com.helloxin;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInvocation;

@Slf4j
public class DefaultRecordCallback implements RecordCallback {

    private MethodInvocation invocation;

    public DefaultRecordCallback(MethodInvocation invocation) {
        this.invocation = invocation;
    }

    @Override
    public Object execute() throws Exception {
        Object result = null;
        try {
            result = invocation.proceed();
        } catch (Throwable e) {
            log.error("DefaultRecordCallback error ", e);
        }
        return result;
    }
}
