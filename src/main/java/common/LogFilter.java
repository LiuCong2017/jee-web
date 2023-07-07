package common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

// LogFilter.java
public class LogFilter implements Filter {
    private final Logger logger = LoggerFactory.getLogger(LogFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 获取请求的路径和参数
        String uri = ((HttpServletRequest) request).getRequestURI();
        String params = request.getParameterMap().toString();
        // 获取当前时间
        Date date = new Date();
        // 记录日志信息
        logger.info("Request: " + uri + ", Params: " + params + ", Time: " + date);
        // 放行请求
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
