package com.helloxin.elephant.saas.filter;

import com.helloxin.elephant.saas.RequestHeaderHolder;
import com.helloxin.elephant.saas.TenantContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@Component
@WebFilter("/*")
@Slf4j
@ConditionalOnProperty(prefix = "tenant.filter",value = "enable",havingValue = "true")
public class ElephantSaasFilter implements Filter{
    @Autowired
    private TenantContext tenantContext;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //do nothing
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        try {
            //设置租户信息
            tenantContext.setCurrentInfo(RequestHeaderHolder.getConfigTenant());
            chain.doFilter(req, resp);
        } finally {
            tenantContext.clearCurrentInfo();
        }
    }

    @Override
    public void destroy() {
        // do nothing
    }
}
