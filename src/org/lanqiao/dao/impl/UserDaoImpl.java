package org.lanqiao.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.lanqiao.dao.UserDao;
import org.lanqiao.entity.PasswordAnswer;
import org.lanqiao.entity.User;
import org.lanqiao.util.DBUtil;

public class UserDaoImpl implements UserDao {

	@Override
	public void insert(User user) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			//1.获取链接
			conn = DBUtil.getConnection();
			
			//2.创建PreparedStatement对象
			String sql = "insert into t_user values(?,?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			
		    ps.setString(1, user.getUserid());
		    ps.setString(2, user.getUemail());
		    ps.setString(3, user.getUloginid());
		    ps.setString(4, user.getUpassword());
		    ps.setString(5, user.getUsex());
		    ps.setString(6, user.getUaddress());
		    ps.setString(7, user.getUtel());
		    ps.setString(8, user.getUstateid());
		    ps.setString(9, user.getUroleid());
			//3.执行操作
		    ps.executeUpdate();		
			//5.关闭流
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		} catch (Exception e) {
			
		}finally{
			
		}

	}

	public void getbyuserid(String userid) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			//1.获取链接
			conn = DBUtil.getConnection();
			System.out.println(userid);
			//2.创建PreparedStatement对象
			String sql = "update t_user set ustateid = 'B5868B7A06E54DAEB19658343D3A2B28' where userid = ?";
			ps = conn.prepareStatement(sql);
		    ps.setString(1, userid);
			//3.执行操作
			ps.executeUpdate();
			System.out.println("222");
			//5.关闭流
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		} catch (Exception e) {
			
		}finally{
			
		}
	}

	public User getUserByLoginId(String loginid) {
		User user = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			//1获取连接
			conn = DBUtil.getConnection();
			//2通过连接创建PreparedStatement对象;
			String sql="select * from t_user where uloginid=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, loginid);
			//3执行操作
			rs = ps.executeQuery();
			//4取数据;
			if(rs.next()){
				user =new User(rs.getString("userid"), rs.getString("uemail"), rs.getString("uloginid"), rs.getString("upassword"), rs.getString("usex"), rs.getString("uaddress"), rs.getString("utel"), rs.getString("ustateid"), rs.getString("uroleid"));
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
		return user;
	}

	public void update(PasswordAnswer passwordAnswer) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			//1.获取链接
			conn = DBUtil.getConnection();
			
			//2.创建PreparedStatement对象
			String sql = "update  t_passwordanswer set aquestion=?,answer=?,email,userid=? where answerid=?";
			ps = conn.prepareStatement(sql);
			
		    ps.setString(1, passwordAnswer.getAquestion());
		    ps.setString(2, passwordAnswer.getAnswer());
		    ps.setString(3, passwordAnswer.getUserid());
		    ps.setString(4, passwordAnswer.getAnswerid());
			//3.执行操作
		    ps.executeUpdate();		
			//5.关闭流
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		} catch (Exception e) {
			
		}finally{
			
		}
		
	}

	public void update(User user) {
//		System.out.println(user.getUserid());
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			//1.获取链接
			conn = DBUtil.getConnection();
			
			//2.创建PreparedStatement对象
			String sql = "update  t_user set uemail=?,uloginid=?,upassword=?,usex=?,uaddress=? ,utel=? ,ustateid=?,uroleid=? where userid=?";
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, user.getUemail());
			ps.setString(2, user.getUloginid());
			ps.setString(3, user.getUpassword());
			ps.setString(4, user.getUsex());
			ps.setString(5, user.getUaddress());
			ps.setString(6, user.getUtel());
			ps.setString(7, user.getUstateid());
			ps.setString(8, user.getUroleid());
			ps.setString(9, user.getUserid());
//			System.out.println(user.getUemail()+user.getUloginid()+user.getUpassword());
//			System.out.println(user.getUsex()+user.getUaddress()+user.getUtel());
//			System.out.println(user.getUstateid()+user.getUroleid()+user.getUserid());
			//3.执行操作
		    ps.executeUpdate();
//		    System.out.println("222");
			//5.关闭流
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		} catch (Exception e) {
			
		}finally{
			
		}
		
	}

	public List<User> list() {
		List<User> list =new ArrayList<User>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user=null;
		try{
			//1获取连接
			conn = DBUtil.getConnection();
			//2通过连接创建PreparedStatement对象;
			String sql="select * from t_user";
			ps = conn.prepareStatement(sql);
			//3执行操作
			rs = ps.executeQuery();
			//4取数据;
			while(rs.next()){
				user =new User(rs.getString("userid"), rs.getString("uemail"), rs.getString("uloginid"), rs.getString("upassword"), rs.getString("usex"), rs.getString("uaddress"), rs.getString("utel"), rs.getString("ustateid"), rs.getString("uroleid"));
				list.add(user);
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

	public void remove(String userid) {
//		System.out.println(userid);
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			//1.获取链接
			conn = DBUtil.getConnection();
			
			//2.创建PreparedStatement对象
			String sql = "delete from t_user where userid=?";
			ps = conn.prepareStatement(sql);
		    ps.setString(1, userid);
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
