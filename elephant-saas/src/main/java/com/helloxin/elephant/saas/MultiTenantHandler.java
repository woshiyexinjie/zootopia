package com.helloxin.elephant.saas;

import com.baomidou.mybatisplus.extension.plugins.tenant.TenantHandler;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.StringValue;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Component
public class MultiTenantHandler implements TenantHandler {

    @Value("${tenant.field:defaultTenant}")
    private String systemTenantId;


    @Value("${tenant.table.exclude}")
    private String excludeTable;

    @Autowired
    private TenantContext tenantContext;

    @Override
    public Expression getTenantId() {
        String tenantId = tenantContext.getTenantId();
        log.debug("tenantId {}", tenantId);
        if (tenantId == null) {
            return null;
        }
        return new StringValue(tenantId);
    }

    @Override
    public String getTenantIdColumn() {
        //自己写一个也行 只是要判断各种 比较麻烦
        return systemTenantId;
    }

    @Override
    public boolean doTableFilter(String tableName) {
        return this.getTenantId() == null
                || StringUtils.isEmpty(excludeTable)
                || Arrays.stream(StringUtils.split(excludeTable)).anyMatch(e -> e.equalsIgnoreCase(tableName));
    }
}