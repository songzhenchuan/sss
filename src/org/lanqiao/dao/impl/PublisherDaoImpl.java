package org.lanqiao.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;




import java.sql.SQLException;

import org.lanqiao.dao.PublisherDao;
import org.lanqiao.entity.Publisher;
import org.lanqiao.util.DBUtil;

public class PublisherDaoImpl implements PublisherDao {

	@Override
	public Publisher getBypid(String pid) {
		Publisher press = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			//1.获取链接
			conn = DBUtil.getConnection();
			
			//2.创建PreparedStatement对象
			String sql = "select * from t_publisher where pid=?";
			ps = conn.prepareStatement(sql);
		    ps.setString(1, pid);
			//3.执行操作
			rs = ps.executeQuery();
			
			//4.取数据

			
			if (rs.next()) {
				
				press =new Publisher(rs.getString("pid"), rs.getString("pname"));
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
		return press;
	}

	@Override
	public Publisher get(String pid) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Publisher publisher = null;
		try{
			//1获取连接
			conn = DBUtil.getConnection();
			//2通过连接创建PreparedStatement对象;
			String sql="select * from t_publisher where pid=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, pid);
			//3执行操作
			rs = ps.executeQuery();
			//4取数据;
			if(rs.next()){
				publisher = new Publisher(rs.getString("pid"), rs.getString("pname"));
			}
		}catch(Exception e){
			
		}
		finally{
			//5关闭对象
			try {
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return publisher;
	}

}
