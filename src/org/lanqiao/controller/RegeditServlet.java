package org.lanqiao.controller;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.entity.PasswordAnswer;
import org.lanqiao.entity.User;
import org.lanqiao.service.PasswordAnswerService;
import org.lanqiao.service.UserService;
import org.lanqiao.service.impl.PasswordAnswerServiceImpl;
import org.lanqiao.service.impl.UserServiceImpl;
import org.lanqiao.util.MailUtil;

@WebServlet(name = "regedit.do", urlPatterns = { "/regedit.do" })
public class RegeditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String code=request.getSession().getAttribute("code").toString().toLowerCase();
		String ucheckcode =request.getParameter("ucheckcode").toLowerCase();
		if (!code.equals(ucheckcode)) {
			request.getRequestDispatcher("WEB-INF/regedit.jsp").forward(request, response);
			return;
		}
		
		//处理注册
		String uloginid =request.getParameter("uname");
		String uemail =request.getParameter("uemail");
		String upassword =request.getParameter("upassword");
		String usex =request.getParameter("usex");
		String utel =request.getParameter("utel");
		String uaddress =request.getParameter("uaddress");
		String userid=UUID.randomUUID().toString();//生成主键值
		String ustateid="36D0F394FC6A45829385E0BE11208263";
		String uroleid="116F9526C319462780B9CA72F6BB9B41";//普通用户
		request.setAttribute("uemail", uemail);
//		36D0F394FC6A45829385E0BE11208263无效B5868B7A06E54DAEB19658343D3A2B28";//有效
		User user =new User(userid, uemail, uloginid, upassword, usex, uaddress, utel, ustateid, uroleid);
		UserService us =new UserServiceImpl();
		us.insertUser(user);
		//处理密码保护
		String select = request.getParameter("select");
		String sanswer = request.getParameter("sanswer");
		String ubackupemail=request.getParameter("ubackupemail");
		String answerid=UUID.randomUUID().toString();//生成主键值
		
		PasswordAnswer passwordAnswer =new PasswordAnswer(answerid, userid, select, sanswer, ubackupemail);
		PasswordAnswerService ps=new PasswordAnswerServiceImpl();
		ps.insertPasswordAnswer(passwordAnswer);
		
		//发送一封激活邮件;
//		MailUtil.sendMail("koudaibi@qq.com", "这是一封激活文件", "来自宋振川的激活文件");
//		MailUtil.sendMail("1969960508@qq.com", "这是一封激活文件", "来自宋振川的激活文件");
		
		MailUtil.sendMail(uemail, "这是一封激活文件", "来自java发送的激活文件,请点击http://localhost:8080/webproject/activation.do?userid="+userid+"，进行激活");
		request.getRequestDispatcher("WEB-INF/regsuccess.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
