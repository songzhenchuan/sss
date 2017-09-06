package org.lanqiao.admin.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.entity.Goods;
import org.lanqiao.entity.User;

import com.google.gson.Gson;

/**
 * Servlet implementation class SearchdataController
 */
@WebServlet(name = "searchdatacontroller.do", urlPatterns = { "/searchdatacontroller.do" })
public class SearchdataController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String type = request.getParameter("type");
		if (type!=null&&type.equals("user")) {
			String keyword = java.net.URLDecoder.decode(request.getParameter("key"),"UTF-8");
			//根据key返回一个提示列表;
			List<User> list = search(keyword);
			Gson gson = new Gson();
			String json =  gson.toJson(list);
			response.setContentType("application/json");
			response.getWriter().write(json);				
		}else if (type!=null&&type.equals("goods")) {
			String keyword = java.net.URLDecoder.decode(request.getParameter("key"),"UTF-8");
			//根据key返回一个提示列表;
			List<Goods> list = searchgood(keyword);
			Gson gson = new Gson();
			String json =  gson.toJson(list);
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json");
			response.getWriter().write(json);	
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	public List<User> search(String key) {
		List<User> list =new ArrayList<User>();
		try {
			Connection conn = org.lanqiao.util.DBUtil.getConnection();
			String sql="select t.*, rownum rn from (select * from t_user where ULOGINID like ?) t where rownum<6";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "%"+ key +"%");
			ResultSet rs =  ps.executeQuery();
			User user = null;
			while(rs.next()){
				user = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9));
				list.add(user);
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	public List<Goods> searchgood(String key) {
		List<Goods> list =new ArrayList<Goods>();
		try {
			Connection conn = org.lanqiao.util.DBUtil.getConnection();
			String sql="select t.*, rownum rn from (select * from t_goods where GTITLE like ?) t where rownum<6";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "%"+ key +"%");
			ResultSet rs =  ps.executeQuery();
			Goods goods = null;
			while(rs.next()){
				goods = new Goods(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getDouble(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getString(9), rs.getString(10));
				list.add(goods);
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
}
