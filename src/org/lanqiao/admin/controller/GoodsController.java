package org.lanqiao.admin.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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


@WebServlet(name = "goodscontroller.do", urlPatterns = { "/goodscontroller.do" })
public class GoodsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type=request.getParameter("type");
//		System.out.println(type);
		
		if (type!=null&&type.equals("cate")) {
			GoodService gs = new GoodServiceImpl();
			String cid = request.getParameter("cid");
			int pagesize =Integer.parseInt(request.getParameter("rows"));
			int pageindex=Integer.parseInt(request.getParameter("page"))+1;
//			PageInfo pageInfo =gs.goodslist(cid, pagesize, pageindex);
			PageInfo<Goods> pageInfo =gs.goodslist(cid, pagesize, pageindex);
			//easyui datagrid 分页的数据格式要求{total:1000 rows:datas}
			Map<String , Object> map = new HashMap<String, Object>();
			map.put("total", pageInfo.getTotalnumber());
			map.put("rows", pageInfo.getData());
			Gson gson = new Gson();
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(gson.toJson(map));		
		}else if (type!=null&&type.equals("remove")) {
			String gid = request.getParameter("gid");
			GoodService good = new GoodServiceImpl();
			good.removeGoods(gid);		
			if (gid!=null) {				
				response.getWriter().write("1");			
			}else {
				response.getWriter().write("0");
			}
		}else if (type!=null && type.equals("add")) {
			String gid = request.getParameter("gid");
			String gtitle=request.getParameter("gtitle");
			String gauthor =request.getParameter("gauthor");
			String gsaleprice =request.getParameter("gsaleprice");			
			String ginprice =request.getParameter("ginprice");
			String gdesc =request.getParameter("gdesc");
			String gimg =request.getParameter("gimg");
			String gclicks =request.getParameter("gclicks");
			String cid =request.getParameter("cid");
			String pid =request.getParameter("pid");
			Goods goods = new Goods(gid, gtitle, gauthor, Double.parseDouble(gsaleprice), Double.parseDouble(ginprice), gdesc, gimg, Integer.parseInt(gclicks), cid, pid);
			GoodService gs =new GoodServiceImpl();
			gs.insertGoods(goods);
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write("1");
			
		}else if (type!=null && type.equals("edit")) {
			String gid = request.getParameter("gid");
			String gtitle=request.getParameter("gtitle");
			String gauthor =request.getParameter("gauthor");
			String gsaleprice =request.getParameter("gsaleprice");			
			String ginprice =request.getParameter("ginprice");
			String gdesc =request.getParameter("gdesc");
			String gimg =request.getParameter("gimg");
			String gclicks =request.getParameter("gclicks");
			String cid =request.getParameter("cid");
			String pid =request.getParameter("pid");
			Goods goods = new Goods();
			GoodService gs =new GoodServiceImpl();
			goods.setGid(gid);
//			System.out.println(gid);
			goods.setGtitle(gtitle);
			goods.setGsaleprice(Double.parseDouble(gsaleprice));
			goods.setGinprice(Double.parseDouble(ginprice));
			goods.setGdesc(gdesc);
			goods.setGimg(gimg);
//			System.out.println(gimg);
			goods.setGclicks(Integer.parseInt(gclicks));
			goods.setGauthor(gauthor);
			goods.setCid(cid);
			goods.setPid(pid);
			gs.updateGoods(goods);
			response.getWriter().write("1");
			
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
