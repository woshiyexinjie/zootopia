package com.helloxin.zootopia.cat.lottery.filter;

import com.helloxin.zootopia.cat.lottery.utils.LinkUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by yexin on 2019/9/20.
 */
@WebFilter("/*")
@Component
public class LinkMonitorFilter implements Filter {

    final Logger logger = LoggerFactory.getLogger(LinkMonitorFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //这里放入全链路监控的id 暂时的做法是使用 uuid
        String action = ((HttpServletRequest) request).getContextPath();
        long beginTime = System.currentTimeMillis();

        //set linkid
        String linkid = ((HttpServletRequest) request).getHeader("linkId");
        if(StringUtils.isEmpty(linkid)){
            linkid = UUID.randomUUID().toString();
        }
        LinkUtils.setlinkId(linkid);
        chain.doFilter(request,response);
        logger.info("action ={} filter time ={} ms",action,System.currentTimeMillis()-beginTime);
    }

    @Override
    public void destroy() {

    }
}
