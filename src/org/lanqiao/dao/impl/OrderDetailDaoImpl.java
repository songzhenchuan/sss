package org.lanqiao.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.lanqiao.dao.OrderDetailDao;
import org.lanqiao.entity.OrderDetail;
import org.lanqiao.util.DBUtil;

public class OrderDetailDaoImpl implements OrderDetailDao {

	@Override
	public void insert(OrderDetail orderDetail) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			//1.获取链接
			conn = DBUtil.getConnection();
			
			//2.创建PreparedStatement对象
			String sql = "insert into t_orderdetail values(?,?,?,?,?)";
//			Date date =new Date();
			ps = conn.prepareStatement(sql);
			
		    ps.setString(1, orderDetail.getOrderdetailid());
		    ps.setString(2, orderDetail.getGtitle());
		    ps.setDouble(3, orderDetail.getGsalprice());
		    ps.setDouble(4, orderDetail.getGnumber());
		    ps.setString(5, orderDetail.getOrderid());
			//3.执行操作
		    ps.executeUpdate();	
			//5.关闭流
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		} catch (Exception e) {
			
		}finally{
			
		}

	}


}
