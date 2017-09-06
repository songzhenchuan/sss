package org.lanqiao.test;

import java.sql.SQLException;

import org.junit.Test;
import org.lanqiao.dao.GoodsDao;
import org.lanqiao.dao.impl.GoodsDaoImpl;
import org.lanqiao.entity.Goods;
import org.lanqiao.entity.PageInfo;

public class Connectiontest {
	@Test
	public void testConnection() throws SQLException{
		/*Connection conn =DBUtil.getConnection();
		System.out.println(conn);*/
		GoodsDao dao = new GoodsDaoImpl();
		PageInfo<Goods> pageInfo = dao.list("1", 10, 1);
		System.out.println(pageInfo.getData());

	}
	
}
