package com.helloxin.elephant.saas;

import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class RequestHeaderHolder {
    private RequestHeaderHolder() {
    }

    public static String getConfigTenant() {
        return getHeader((ServletRequestAttributes) RequestContextHolder.getRequestAttributes(),
                HeaderEnums.TENANT.getDefaultValue(), HeaderEnums.TENANT.getHeader());
    }

    private static String getHeader(ServletRequestAttributes requestAttributes, String defaultValue, String headerKey) {
        return getHeader(getRequest(requestAttributes), defaultValue, headerKey);
    }

    private static String getHeader(HttpServletRequest request, String defaultValue, String headerKey) {
        if(request == null) {
            return defaultValue;
        }
        String value = request.getHeader(headerKey);
        return StringUtils.isEmpty(value) ? defaultValue : value;
    }

    private static HttpServletRequest getRequest(ServletRequestAttributes requestAttributes) {
        return Optional.ofNullable(requestAttributes).map(ServletRequestAttributes::getRequest).orElse(null);
    }
}
