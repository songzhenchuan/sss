package org.lanqiao.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.entity.Goods;
import org.lanqiao.entity.PageInfo;
import org.lanqiao.service.GoodService;
import org.lanqiao.service.impl.GoodServiceImpl;

import com.google.gson.Gson;

@WebServlet(name = "admin.do", urlPatterns = { "/admin.do" })
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cid =request.getParameter("cid");
		String pageindex=request.getParameter("pageIndex");
		if (cid==null) {
			cid="1";
		}
		
		if (pageindex==null||pageindex=="0") {
			pageindex="0";
		}
		String pagesize=request.getParameter("pageSize");
		if (pagesize==null) {
			pagesize="10";
		}
		GoodService gs= new GoodServiceImpl();
		PageInfo<Goods> pageinfo =gs.goodslist(cid, Integer.parseInt(pagesize), Integer.parseInt(pageindex)+1);
		Gson gson =new Gson();
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(gson.toJson(pageinfo));
		System.out.println(gson.toJson(pageinfo));
//		request.setAttribute("pageinfo", pageinfo);
//		request.getRequestDispatcher("WEB-INF/list2.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
