package com.helloxin.elephant.saas;

import org.springframework.stereotype.Component;

@Component
public class TenantContext {

    //使用 threadLocal 保存线程信息
    private ThreadLocal<TenantInfo> threadLocal = new ThreadLocal<>();

    public void setCurrentInfo(String tenant) {
        threadLocal.set(TenantInfo.builder()
                .tenant(tenant)
                .build());
    }

    public void clearCurrentInfo() {
        threadLocal.remove();
    }

    public String getTenantId() {
        if(threadLocal.get() == null){
            return null;
        }
        if (threadLocal.get().getTenant() == null) {
            return HeaderEnums.TENANT.getDefaultValue();
        }
        return threadLocal.get().getTenant();
    }
}