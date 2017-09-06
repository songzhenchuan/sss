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

@WebServlet(name = "list.do", urlPatterns = { "/list.do" })
public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cid =request.getParameter("cid");
		String pageindex=request.getParameter("pageindex");
		if (cid==null) {
			cid="1";
		}
		if (pageindex==null) {
			pageindex="1";
		}
		int pagesize=5;
		GoodService gs= new GoodServiceImpl();
		PageInfo<Goods> pageinfo =gs.goodslist(cid, pagesize, Integer.parseInt(pageindex));
		
		request.setAttribute("pageinfo", pageinfo);
		request.getRequestDispatcher("WEB-INF/list.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
