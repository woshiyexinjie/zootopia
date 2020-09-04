package com.helloxin;


public interface RecordContext {

    String getOperator();

    void setOperator(String operator);

    String getChannel();

    void setChannel(String channel);

    String getRequestData();

    void setRequestData(String requestData);

    String getAction();

    void setAction(String action);

    RecordCallback getRecordCallback();

    void setRecordCallback(RecordCallback auditCallback);

    long getTimeout();

    void setTimeout(long timeout);

    int getRetryTimes();

    void setRetryTimes(int retryTimes);
}
