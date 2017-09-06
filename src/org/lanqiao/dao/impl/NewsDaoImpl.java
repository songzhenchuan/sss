package org.lanqiao.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.lanqiao.dao.NewsDao;
import org.lanqiao.entity.News;
import org.lanqiao.util.DBUtil;

public class NewsDaoImpl  implements NewsDao{

	@Override
	public List<News> list() {
		List<News> list =new ArrayList<News>();
		Connection conn =null;
		PreparedStatement ps=null;
		ResultSet rs =null;
		String sql="select * from t_news";
		try {
			conn=DBUtil.getConnection();
			ps=conn.prepareStatement(sql);
			rs =ps.executeQuery();
			News news =null;
			while(rs.next()){
			news =new News(rs.getString("tid"),rs.getString("title"),rs.getString("tcontent"),rs.getDate("tpybdate"));
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
	public News get(String id) {
		News news = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			//1.获取链接
			conn = DBUtil.getConnection();
			
			//2.创建PreparedStatement对象
			String sql = "select * from t_news where tid=?";
			ps = conn.prepareStatement(sql);
		    ps.setString(1, id);
			//3.执行操作
			rs = ps.executeQuery();
			
			//4.取数据

			
			
			if (rs.next()) {
				
				news = new News(rs.getString("tid"), rs.getString("title"),rs.getString("tcontent"), rs.getDate("tpybdate"));			    
			}

			
			//5.关闭流
			if(rs!=null) rs.close();
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		} catch (Exception e) {
			
		}finally{
			
		}
		return news;
	}
}
