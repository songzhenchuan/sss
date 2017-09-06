package org.lanqiao.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.lanqiao.dao.CategoryDao;
import org.lanqiao.entity.Category;
import org.lanqiao.util.DBUtil;

public class CategoryImpl implements CategoryDao {

	@Override
	public List<Category> list() {
		List<Category> list =new ArrayList<Category>();
		Connection conn =null;
		PreparedStatement ps=null;
		ResultSet rs =null;
		String sql="select * from t_category order by orderby";
		try {
			conn=DBUtil.getConnection();
			ps=conn.prepareStatement(sql);
			rs =ps.executeQuery();
			Category news =null;
			while(rs.next()){
			news =new Category(rs.getString("cid"),rs.getString("cname"));
			list.add(news);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
			if (rs!=null) {
					rs.close();
			}
			if (ps!=null) {
				ps.close();
			}
			if (conn!=null) {
				conn.close();
			}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public Category get(String cid) {
		Category cate = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			//1.获取链接
			conn = DBUtil.getConnection();
			
			//2.创建PreparedStatement对象
			String sql = "select * from t_category where cid=?";
			ps = conn.prepareStatement(sql);
		    ps.setString(1, cid);
			//3.执行操作
			rs = ps.executeQuery();
			
			//4.取数据

			
			if (rs.next()) {
				
				cate =new Category(rs.getString("cid"), rs.getString("cname"));
//				list.add(goods);
			}
//			System.out.println(goods);
			
			//5.关闭流
			if(rs!=null) rs.close();
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		} catch (Exception e) {
			
		}finally{
			
		}
//		System.out.println(pageInfo);
		return cate;
	}

}
