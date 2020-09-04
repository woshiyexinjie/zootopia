package com.helloxin.service;

import com.helloxin.commmon.CommonResult;
import com.helloxin.dao.RecordDAO;
import com.helloxin.dao.RecordDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class RecordServiceImpl implements RecordService {

    @Autowired
    private RecordDAO recordDAO;

    @Override
    public CommonResult<String> create(RecordDO recordDO) {
        if (recordDO == null) {
            return  CommonResult.fail("recordDO is null.");
        }
        try {
            recordDAO.insert(recordDO);
        }catch (Exception e){
            log.error("recordDAO insert error",e);
            return CommonResult.fail("recordDAO insert error");
        }
        return  CommonResult.success(recordDO.getOperateMethod());
    }
}
