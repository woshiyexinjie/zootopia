package com.helloxin;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.helloxin.commmon.CommonResult;
import com.helloxin.dao.RecordDO;
import com.helloxin.service.RecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j(topic = "RECORD")
public class DefaultRecordTemplate implements RecordTemplate {

    private static final long DEFAULT_TIMEOUT = 200L;
    private static final int DEFAULT_RETRY_TIMES = 0;
    private static final Integer TEXT_LENGTH_LIMIT = 10240;

    @Autowired
    private RecordService recordService;

    @Autowired
    private RecordProperties recordProperties;

    @Override
    public <T> T execute(RecordContext recordContext) throws Exception {
        //
        final long timeout = recordProperties.getTimeout() != null ? recordProperties.getTimeout() : DEFAULT_TIMEOUT;
        final int retryTimes = recordProperties.getRetryTimes() != null ? recordProperties.getRetryTimes() : DEFAULT_RETRY_TIMES;
        long startTime = System.currentTimeMillis();
        // 执行业务逻辑
        Object result = null;
        try {
            result = recordContext.getRecordCallback().execute();
        } catch (Exception e) {
            log.error("DefaultAuditTemplate.execute business code error,", e);
        }
        // 审计信息入库
        if (null != result) {
            try {
                int retry = 0;
                CommonResult<String> saveResult;
                while (retry <= retryTimes) {
                    retry++;
                    saveResult = saveRecord(recordContext, startTime, result);
                    if ((saveResult != null && saveResult.isSuccess()) || (System.currentTimeMillis() - startTime) > timeout) {
                        break;
                    }
                }
            } catch (Exception e) {
                log.error("DefaultAuditTemplate.execute record code error,", e);
            }
        }
        return (T) result;
    }

    private CommonResult<String> saveRecord(RecordContext recordContext, long startTime, Object result) {
        final Integer textLenghtLimit = recordProperties.getTextLimit() != null ? recordProperties.getTextLimit() : TEXT_LENGTH_LIMIT;
        String requestData = getLimitString(recordContext.getRequestData(), textLenghtLimit);
        // 响应数据信息
        String responseData = null;
        if (result != null) {
            responseData = JSON.toJSONString(result);
            getLimitString(responseData, textLenghtLimit);
        }
        RecordDO recordDO = RecordDO.builder()
                .operator(recordContext.getOperator())
                .operateMethod(recordContext.getAction())
                .requestData(requestData)
                .responseData(responseData)
                .responseTime(System.currentTimeMillis() - startTime)
                .channel(recordContext.getChannel())
                .build();
        return recordService.create(recordDO);
    }

    private String getLimitString(String requestData, Integer textLenghtLimit) {
        if (StringUtils.isNotBlank(requestData) && requestData.length() >= textLenghtLimit) {
            requestData = requestData.substring(0, textLenghtLimit);
        }
        return requestData;
    }
}
