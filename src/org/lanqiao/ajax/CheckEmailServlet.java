package org.lanqiao.ajax;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CheckEmailServlet
 */
@WebServlet(name = "CheckEmailServlet.do", urlPatterns = { "/CheckEmail.do" })
public class CheckEmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String email =request.getParameter("email");
	System.out.println(email);
	String name =request.getParameter("name");
	System.out.println("name:"+name);
	if (email.equals("759606780@qq.com")) {
		response.getWriter().write("1");
	}else {
		response.getWriter().write("0");
	}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
