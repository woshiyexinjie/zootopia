package com.helloxin.service;

import com.helloxin.commmon.CommonResult;
import com.helloxin.dao.RecordDO;

@FunctionalInterface
public interface RecordService {
    CommonResult<String> create(RecordDO recordDO);
}
