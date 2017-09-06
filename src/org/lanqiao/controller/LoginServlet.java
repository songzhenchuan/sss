package org.lanqiao.controller;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.entity.User;


@WebServlet(name = "loginServlet.do", urlPatterns = { "/login.do" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		String uloginid = request.getParameter("uname");
		String upassword = request.getParameter("upassword");
		org.lanqiao.service.UserService us =new org.lanqiao.service.impl.UserServiceImpl();
		User user =us.login(uloginid,upassword);
		if(user!=null){			
			//验证通过
			request.getSession().setAttribute("user", user);
			//处理记住密码;
			String chk = request.getParameter("chk");
			if(chk!=null){ //用户选择记住密码;
				//直接将账号写入cookie;
				uloginid = java.net.URLEncoder.encode(uloginid,"gbk");
				Cookie cookie = new Cookie("uloginid",uloginid);
				javax.servlet.http.Cookie cs[] = request.getCookies();
				for(javax.servlet.http.Cookie c:cs){
					if(c.getName().equals("uloginid")){
						String cval = c.getValue();
						cval = URLDecoder.decode(cval,"gbk");

					}
				}
//				Cookie cookie =new Cookie("uloginid",uloginid);
				cookie.setMaxAge(60*60*24*7);
				response.addCookie(cookie);
			}
		}
		request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);	}

}
