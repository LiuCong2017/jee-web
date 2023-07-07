package servlet;

import com.alibaba.fastjson2.JSON;
import dao.OrderDAO;
import domain.dto.OrderParamsDTO;
import common.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Map;

public class OrderServlet  extends BaseServlet implements Serializable {
    private static final long serialVersionUID = 1L;

    public void list(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        OrderParamsDTO paramsDTO = null;
        Map<String, String[]> params = req.getParameterMap();
        for (Map.Entry<String, String[]> entry : params.entrySet()) {
            paramsDTO = JSON.parseObject(entry.getKey(), OrderParamsDTO.class);
        }
        OrderDAO orderDAO = new OrderDAO();
        String json = JSON.toJSONString(orderDAO.getAllOrderByQueryPage(paramsDTO));
        res.setContentType("application/json;charset=utf-8");
        PrintWriter out = res.getWriter();
        out.print(json);
        out.flush();

    }

}
