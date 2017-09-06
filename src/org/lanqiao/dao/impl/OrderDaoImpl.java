package org.lanqiao.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.lanqiao.dao.OrderDao;
import org.lanqiao.entity.Order;
import org.lanqiao.util.DBUtil;

public class OrderDaoImpl implements OrderDao {

	@Override
	public void insert(Order order) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			//1.获取链接
			conn = DBUtil.getConnection();
			
			//2.创建PreparedStatement对象
			String sql = "insert into t_order values(?,?,?,?,?)";
			Date date =new Date();
			java.sql.Date date2=new java.sql.Date(date.getTime());
			ps = conn.prepareStatement(sql);
			
		    ps.setString(1, order.getOrderid());
		    ps.setString(2, order.getGid());
		    ps.setDouble(4, order.getTotalprice());
		    ps.setDate(5, date2);
		    ps.setString(3, order.getUserid());
			//3.执行操作
		    ps.executeUpdate();	
			//5.关闭流
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		} catch (Exception e) {
			
		}finally{
			
		}


	}

	public List<Order> list() {
		List<Order> list =new ArrayList<Order>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Order order=null;
		try{
			//1获取连接
			conn = DBUtil.getConnection();
			//2通过连接创建PreparedStatement对象;
			String sql="select * from t_order";
			ps = conn.prepareStatement(sql);
			//3执行操作
			rs = ps.executeQuery();
			//4取数据;
			while(rs.next()){
				order=new Order(rs.getString("orderid"), rs.getString("gid"), rs.getString("userid"), rs.getDouble("totalprice"), rs.getDate("orderdate"));
				list.add(order);
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
//		System.out.println(list.size());
		return list;
	}

}
