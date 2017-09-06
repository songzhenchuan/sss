package org.lanqiao.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.lanqiao.entity.User;
import org.lanqiao.service.UserService;
import org.lanqiao.service.impl.UserServiceImpl;

@WebServlet(name = "UserServlet.do", urlPatterns = { "/User.do" })
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type =request.getParameter("type");
		if (type!=null&&type.equals("modify")) {
			HttpSession session=request.getSession();
			User user=(User) session.getAttribute("user");

			String upassword =request.getParameter("upassword");
			String usex =request.getParameter("usex");
			String utel =request.getParameter("utel");
			String uaddress=request.getParameter("uaddress");
			user.setUpassword(upassword);
			user.setUsex(usex);
			user.setUtel(utel);
			user.setUtel(uaddress);
			UserService us =new UserServiceImpl();
			us.updateUser(user);
//			PasswordAnswer passwordAnswer=session
			
			request.getRequestDispatcher("WEB-INF/modifyuserinfo.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
