package org.lanqiao.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.lanqiao.dao.GoodsDao;
import org.lanqiao.entity.Goods;
import org.lanqiao.entity.PageInfo;
import org.lanqiao.entity.Publisher;
import org.lanqiao.service.GoodService;
import org.lanqiao.service.impl.GoodServiceImpl;
import org.lanqiao.util.DBUtil;

public class GoodsDaoImpl implements GoodsDao {
	//获取某一类别商品总数
	@Override
	public int totalRecords(String cid){
		Connection conn =null;
		PreparedStatement ps =null;
		ResultSet rs =null;
//		System.out.println("!!!");
		int total = 0;
		try {
			//1获取连接
			conn=DBUtil.getConnection();
			//通过连接创建对象
			String sql="select count(*) from t_goods where cid=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, cid);
//			System.out.println("--"+cid);
			//执行操作
			rs =ps.executeQuery();
			//取数据
			
			if (rs.next()) {
				total=rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return total;
		
	}
	
	//获取某一类别商品数据
	@Override
	public PageInfo<Goods> list(String cid,int pagesize, int pageindex) {
		
		PageInfo<Goods> pageInfo =new PageInfo<Goods>();
		List<Goods> list =new ArrayList<Goods>();
		Connection conn =null;
		PreparedStatement ps=null;
		ResultSet rs =null;
		try {
			//获取链接
			conn=DBUtil.getConnection();
			//通过链接获取对象
			String sql="select t2.* from (select t1.* , rownum rn from (select * from t_goods where cid=?)t1 where rownum<=?)t2 where rn>=? ";
			ps=conn.prepareStatement(sql);
			ps.setString(1, cid);
			int endindex=pagesize * pageindex;
			int startindex =(pageindex-1) * pagesize+1;
			ps.setInt(2, endindex);
			ps.setInt(3, startindex);
			//执行操作
			rs =ps.executeQuery();
			//取数据
			Goods goods =null;
			while(rs.next()){
			goods =new Goods(rs.getString("gid"), rs.getString("gtitle"), rs.getString("gauthor"), rs.getDouble("gsaleprice"), rs.getDouble("ginprice"), rs.getString("gdesc"), rs.getString("gimg"), rs.getInt("gclicks"), rs.getString("cid"), rs.getString("pid"));
			list.add(goods);
			}
			//给pageinfo对象赋值
			pageInfo.setData(list);
			pageInfo.setIsfirstpage(pageindex==1);
			GoodService gs= new GoodServiceImpl();
			int totalnumber=gs.totalRecords(cid);
			int totalpage=totalnumber % pagesize==0 ? totalnumber/pagesize :totalnumber/pagesize+1;
			pageInfo.setIslastpage(totalpage==pageindex);
			pageInfo.setPageindex(pageindex);
			pageInfo.setPagesize(pagesize);
			pageInfo.setTotalnumber(totalnumber);
			pageInfo.setTotalpage(totalpage);
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
		return pageInfo;
	}

	@Override
	public Goods getBygimg(String gimg) {

		Goods goods = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			//1.获取链接
			conn = DBUtil.getConnection();
			
			//2.创建PreparedStatement对象
			String sql = "select * from t_goods where gimg=?";
			ps = conn.prepareStatement(sql);
		    ps.setString(1, gimg);
			//3.执行操作
			rs = ps.executeQuery();
			
			//4.取数据

			
			if (rs.next()) {
				
				goods =new Goods(rs.getString("gid"), rs.getString("gtitle"), rs.getString("gauthor"), rs.getDouble("gsaleprice"), rs.getDouble("ginprice"), rs.getString("gdesc"), rs.getString("gimg"), rs.getInt("gclicks"), rs.getString("cid"), rs.getString("pid"));
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
		return goods;
	}

	@Override
	public Goods get(String gid) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Goods goods = null;
		try{
			//1获取连接
			conn = DBUtil.getConnection();
			//2通过连接创建PreparedStatement对象;
			String sql="select * from t_goods where gid=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, gid);
			//3执行操作
			rs = ps.executeQuery();
			//4取数据;
			if(rs.next()){
				goods = new Goods(rs.getString("gid"), rs.getString("gtitle"), rs.getString("gauthor"), rs.getDouble("gsaleprice"), rs.getDouble("ginprice"), rs.getString("gdesc"), rs.getString("gimg"), rs.getInt("gclicks"), rs.getString("cid"), rs.getString("pid"));
			}
			//处理外键;
			org.lanqiao.dao.PublisherDao dao = new org.lanqiao.dao.impl.PublisherDaoImpl();
			Publisher publisher = dao.get(goods.getPid());
			goods.setPublisher(publisher);
		}catch(Exception e){}
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
		return goods;
	}

	@Override
	public void remove(String gid) {
//		System.out.println(gid);
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			//1.获取链接
			conn = DBUtil.getConnection();
			
			//2.创建PreparedStatement对象
			String sql = "delete from t_goods where gid=?";
			ps = conn.prepareStatement(sql);
		    ps.setString(1, gid);
			//3.执行操作
			ps.executeUpdate();

			//5.关闭流
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		} catch (Exception e) {
			
		}finally{
			
		}
	}

	@Override
	public void insert(Goods goods) {
		System.out.println(goods);
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			//1.获取链接
			conn = DBUtil.getConnection();
			
			//2.创建PreparedStatement对象
			String sql = "insert into t_goods values(?,?,?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			
		    ps.setString(1, goods.getGid());
		    ps.setString(2, goods.getGtitle());
		    ps.setString(3, goods.getGauthor());
		    ps.setDouble(4, goods.getGsaleprice());
		    ps.setDouble(5,goods.getGinprice() );
		    ps.setString(6, goods.getGdesc());
		    ps.setString(7,goods.getGimg());
		    ps.setInt(8, goods.getGclicks());
		    ps.setString(9, goods.getCid());
		    ps.setString(10, goods.getPid());
			//3.执行操作
		    ps.executeUpdate();	
			//5.关闭流
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		} catch (Exception e) {
			
		}finally{
			
		}
	}

	@Override
	public void updata(Goods goods) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			//1.获取链接
			conn = DBUtil.getConnection();
			
			//2.创建PreparedStatement对象
			String sql = "update  t_goods set gtitle=?,gauthor=?,gsaleprice=?,ginprice=?,gdesc=? ,gimg=? ,gclicks=?,cid=?,pid=? where gid=?";
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, goods.getGtitle());
			ps.setString(2, goods.getGauthor());
			ps.setDouble(3, goods.getGsaleprice());
			ps.setDouble(4, goods.getGinprice());
			ps.setString(5, goods.getGdesc());
			ps.setString(6, goods.getGimg());
			ps.setInt(7, goods.getGclicks());
			ps.setString(8, goods.getCid());
			ps.setString(9, goods.getPid());
			ps.setString(10,goods.getGid() );
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
}
