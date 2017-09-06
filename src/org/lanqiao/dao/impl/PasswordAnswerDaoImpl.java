package org.lanqiao.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.lanqiao.dao.PasswordAnswerDao;
import org.lanqiao.entity.PasswordAnswer;
import org.lanqiao.util.DBUtil;

public class PasswordAnswerDaoImpl implements PasswordAnswerDao {

	@Override
	public void insert(PasswordAnswer passwordAnswer) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			//1.获取链接
			conn = DBUtil.getConnection();
			
			//2.创建PreparedStatement对象
			String sql = "insert into t_passwordanswer values(?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			
		    ps.setString(1, passwordAnswer.getAnswerid());
		    ps.setString(2, passwordAnswer.getUserid());
		    ps.setString(3, passwordAnswer.getAquestion());
		    ps.setString(4, passwordAnswer.getAnswer());
		    ps.setString(5, passwordAnswer.getEmail());

			//3.执行操作
//		    System.out.println(user.getUaddress());

		    ps.executeUpdate();		
			//5.关闭流
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		} catch (Exception e) {
			
		}finally{
			
		}

	}

}
