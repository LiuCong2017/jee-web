package kavin.jeeweb.common;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

// BaseServlet.java
//请求格式: http://localhost:8080/eimi/xxxServlet?method=list
public class BaseServlet extends HttpServlet {

    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求的路径
        String uri = req.getRequestURI();
        // 获取方法名
        String methodName = uri.substring(uri.lastIndexOf("/") + 1);
        try {
            // 通过反射调用对应的方法
            Method method = this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
