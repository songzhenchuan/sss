package org.lanqiao.admin.controller;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.entity.Order;
import org.lanqiao.entity.User;
import org.lanqiao.service.OrderService;
import org.lanqiao.service.UserService;
import org.lanqiao.service.impl.OrderServiceImpl;
import org.lanqiao.service.impl.UserServiceImpl;

import com.google.gson.Gson;

@WebServlet(name = "useruontroller.do", urlPatterns = { "/useruontroller.do" })
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type =request.getParameter("type");
//		System.out.println(type);
		if (type!=null&&type.equals("list")) {
			UserService us =new UserServiceImpl();
			List<User> list = us.userList();
			//将数据转为JSON字符串
			Gson gson =new Gson();
			String json =gson.toJson(list);
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);			
		}else if (type!=null&&type.equals("order")) {
			OrderService os=new OrderServiceImpl();
			List<Order> list =os.orderList();
			Gson gson =new Gson();
			String json =gson.toJson(list);
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
		}
		else if (type!=null&&type.equals("remove")) {			
			UserService us =new UserServiceImpl();
			String userid =request.getParameter("userid");
			us.removeUser(userid);
			response.getWriter().write("1");
		}else if (type!=null&&type.equals("add")) {
			String uemail =request.getParameter("uemail");
			String uloginid =request.getParameter("uloginid");
			String upassword =request.getParameter("upassword");
			String utel =request.getParameter("utel");
			String uaddress =request.getParameter("uaddress");
			String usex =request.getParameter("usex");
			String userid=UUID.randomUUID().toString();//生成主键值
			String ustateid="36D0F394FC6A45829385E0BE11208263";
			String uroleid="116F9526C319462780B9CA72F6BB9B41";//普通用户
			User user =new User(userid, uemail, uloginid, upassword, usex, uaddress, utel, ustateid, uroleid);
			UserService us =new UserServiceImpl();
			us.insertUser(user);
			response.getWriter().write("1");
		}else if (type!=null&&type.equals("edit")) {
			String userid =request.getParameter("userid");
			String uemail =request.getParameter("uemail");
//			System.out.println(uemail);
			String uloginid =request.getParameter("uloginid");
			String upassword=request.getParameter("upassword");
			String utel=request.getParameter("utel");
			String uaddress=request.getParameter("uaddress");
			String  usex = request.getParameter("usex");
			String ustateid="36D0F394FC6A45829385E0BE11208263";
			String uroleid="116F9526C319462780B9CA72F6BB9B41";//普通用户
			UserService us = new UserServiceImpl();
			User user =new User();
			user.setUserid(userid);
			user.setUaddress(uaddress);
			user.setUemail(uemail);
			user.setUloginid(uloginid);
			user.setUtel(utel);
			user.setUpassword(upassword);
			user.setUsex(usex);
			user.setUstateid(ustateid);
			user.setUroleid(uroleid);
			us.updateUser(user);
			response.getWriter().write("1");
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
