package com.helloxin.zootopia.goose.queue.filter;

import com.helloxin.zootopia.goose.queue.utils.LinkUtils;
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
        //set linkid
        String linkid = ((HttpServletRequest) request).getHeader("linkId");
        if(StringUtils.isEmpty(linkid)){
            linkid = UUID.randomUUID().toString();
        }
        LinkUtils.setlinkId(linkid);
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
