package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		Enumeration<String> headerNames = request.getHeaderNames();
		while(headerNames.hasMoreElements()) {
			String header = headerNames.nextElement();
			String value = request.getHeader(header);
			System.out.println(header+":"+value);
		}

		System.out.println("获取单值参数name:" + request.getParameter("name"));

		String[] hobits = request.getParameterValues("hobits");
		System.out.println("获取具有多值的参数hobits: " + Arrays.asList(hobits));

		System.out.println("通过 getParameterMap 遍历所有的参数： ");
		Map<String, String[]> parameters = request.getParameterMap();

		Set<String> paramNames = parameters.keySet();
		for (String param : paramNames) {
			String[] value = parameters.get(param);
			System.out.println(param + ":" + Arrays.asList(value));
		}

		 response.setDateHeader("Expires", 0);
         response.setHeader("Cache-Control", "no-cache");
         response.setHeader("pragma", "no-cache");
         
		response.setContentType("text/html; charset=UTF-8");
//		response.setCharacterEncoding("UTF-8");
		PrintWriter pw = response.getWriter();
		pw.write("<h1>注册成功</h1>");
		
	}

}
