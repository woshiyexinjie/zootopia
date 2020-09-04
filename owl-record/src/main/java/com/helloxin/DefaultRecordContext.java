package com.helloxin;

public class DefaultRecordContext implements RecordContext {

    private String operator;

    private String channel;

    private String requestData;

    private String action;

    private RecordCallback recordCallback;

    private long timeout;

    private int retryTimes;

    @Override
    public String getOperator() {
        return operator;
    }

    @Override
    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Override
    public String getChannel() {
        return channel;
    }

    @Override
    public void setChannel(String channel) {
        this.channel = channel;
    }

    @Override
    public String getRequestData() {
        return requestData;
    }

    @Override
    public void setRequestData(String requestData) {
        this.requestData = requestData;
    }

    @Override
    public String getAction() {
        return action;
    }

    @Override
    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public RecordCallback getRecordCallback() {
        return recordCallback;
    }

    @Override
    public void setRecordCallback(RecordCallback auditCallback) {
        this.recordCallback = auditCallback;
    }

    @Override
    public long getTimeout() {
        return timeout;
    }

    @Override
    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    @Override
    public int getRetryTimes() {
        return retryTimes;
    }

    @Override
    public void setRetryTimes(int retryTimes) {
        this.retryTimes = retryTimes;
    }
}
