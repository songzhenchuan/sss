package org.lanqiao.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.entity.Category;
import org.lanqiao.entity.Goods;
import org.lanqiao.entity.News;
import org.lanqiao.entity.Publisher;
import org.lanqiao.entity.User;
import org.lanqiao.service.CategoryService;
import org.lanqiao.service.GoodService;
import org.lanqiao.service.NewsService;
import org.lanqiao.service.PublisherService;
import org.lanqiao.service.impl.CategoryServiceImpl;
import org.lanqiao.service.impl.GoodServiceImpl;
import org.lanqiao.service.impl.NewServiceImpl;
import org.lanqiao.service.impl.PublisherServiceImpl;


@WebServlet(name = "dispacher.do", urlPatterns = { "/dispacher.do" })
public class Dispacher extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type =request.getParameter("type");
		String id =request.getParameter("id");
		String gimg =request.getParameter("gimg");
		String pid =request.getParameter("pid");

		if (type!=null&&type.equals("news")&&id!=null) {		
			NewsService ns =new NewServiceImpl();
			News news=ns.getNewsById(id);

			request.setAttribute("news", news);			
			request.getRequestDispatcher("WEB-INF/title.jsp").forward(request, response);
		}else if (type!=null&&type.equals("goods")&&gimg!=null&&pid!=null) {
			
			GoodService gs= new GoodServiceImpl();
			Goods gimgs =gs.getBygimg(gimg);
			PublisherService ps =new PublisherServiceImpl();
			Publisher publisher =ps.getBypid(pid);
			request.setAttribute("publishers", publisher);

			CategoryService cs =new CategoryServiceImpl();
			String cid=gimgs.getCid();
			Category cate=cs.getCate(cid);			

			request.setAttribute("cate", cate);
			request.setAttribute("gimgs", gimgs);	
			request.getRequestDispatcher("WEB-INF/buy.jsp").forward(request, response);
		}else if (type!=null&&type.equals("regedit")){	
			request.getRequestDispatcher("WEB-INF/regedit.jsp").forward(request, response);
		}else if (type!=null&&type.equals("loginsuccess")) {//判断有cookie;
			Cookie[] cookies = request.getCookies();
			Cookie userCookie = null;
			for(Cookie c:cookies){
				if(c.getName().equals("uloginid")){
					userCookie = c;
					break;
				}
			}
			if(userCookie!=null){
				String uloginid = userCookie.getValue();
				//根据帐号找此用户;
				org.lanqiao.service.UserService us =new org.lanqiao.service.impl.UserServiceImpl();
				User user =us.getUserByLoginId(uloginid);			
				request.getSession().setAttribute("user", user);
			}
			request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
		}else if(type!=null && type.equals("final") ){
			request.getRequestDispatcher("/WEB-INF/my.jsp").forward(request, response);}else if (type!=null&&type.equals("final")) {
			request.getRequestDispatcher("WEB-INF/edit.jsp").forward(request, response);

		}else if (type!=null && type.equals("cart")) {
			
			request.getRequestDispatcher("WEB-INF/cart.jsp").forward(request, response);

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
