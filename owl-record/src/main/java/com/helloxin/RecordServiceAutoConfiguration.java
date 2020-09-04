package com.helloxin;

import com.helloxin.service.RecordService;
import com.helloxin.service.RecordServiceImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.helloxin.dao")
@EnableConfigurationProperties(RecordProperties.class)
@ConditionalOnProperty(prefix = "spring.record", value = "enabled", matchIfMissing = true)
public class RecordServiceAutoConfiguration {

    @Autowired
    private RecordProperties recordProperties;

    @Bean
    public RecordService auditService() {
        return new RecordServiceImpl();
    }

    @Bean
    public RecordInterceptor recordInterceptor() {
        //执行审计的主要逻辑
        return new RecordInterceptor();
    }

    @Bean
    public RecordTemplate recordTemplate() {
        //默认的模版 设置
        return new DefaultRecordTemplate();
    }

    @Bean
    public DefaultPointcutAdvisor defaultPointcutAdvisor() {
        RecordInterceptor interceptor = recordInterceptor();
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(recordProperties.getExecution());

        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        advisor.setPointcut(pointcut);
        advisor.setAdvice(interceptor);
        return advisor;
    }

}
