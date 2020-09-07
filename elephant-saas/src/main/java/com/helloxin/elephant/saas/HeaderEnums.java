package com.helloxin.elephant.saas;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HeaderEnums {
    //目前只考虑租户字段 用于数据隔离
    TENANT("TENANT", "default_tenant");

    private String header;
    private String defaultValue;
}
