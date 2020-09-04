package com.helloxin;

import com.alibaba.fastjson.JSON;
import com.helloxin.exception.RecordExcetion;
import com.helloxin.param.IRecordParam;
import com.helloxin.param.RecordParam;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Slf4j(topic = "RECORD")
public class RecordInterceptor implements MethodInterceptor {

    @Autowired
    private RecordTemplate recordTemplate;
    @Autowired
    private IRecordParam iRecordParam;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        try {
            RecordContext recordContext = buildRecordParamContext(invocation);
            return recordTemplate.execute(recordContext);
        } catch (Exception e) {
            log.error("recordIntercor.invoke error, ", e);
            throw new RecordExcetion("执行审计异常");
        }
    }

    private RecordContext buildRecordParamContext(MethodInvocation invocation) {
        RecordContext recordContext = new DefaultRecordContext();
        //获取方法上的注解信息 RecordParam 可以选择参数
        RecordParam recordParam = invocation.getMethod().getAnnotation(RecordParam.class);
        //这里需要在切点未知进来 也可以注释掉 这样拿不到channel 和可以配置审计入参
        if (recordParam == null) {
            throw new RecordExcetion(invocation.getMethod() + "has no recordParam annotation.");
        }
        recordContext.setOperator(iRecordParam.operator());
        recordContext.setChannel(recordParam.channel());

        // 设置requestData
        if (StringUtils.isNotEmpty(recordParam.requestData())) {
            //使用逗号隔开的字符 这里使用StringUtils.split
            String[] dataIndexArr = StringUtils.split(recordParam.requestData(), ",");
            //获取参数信息
            Object[] paramValueArr = invocation.getArguments();
            List<Object> requestDataList = new ArrayList<Object>();
            Arrays.stream(dataIndexArr).forEach(index -> requestDataList.add(paramValueArr[Integer.parseInt(index.trim())]));
            recordContext.setRequestData(JSON.toJSONString(requestDataList));
        }
        recordContext.setAction(invocation.getMethod().getName());
        recordContext.setRecordCallback(new DefaultRecordCallback(invocation));
        return recordContext;
    }
}
