package com.helloxin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.record")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecordProperties {

    private String execution;

    private Integer timeout;

    private Integer retryTimes;

    private Integer textLimit;
}
