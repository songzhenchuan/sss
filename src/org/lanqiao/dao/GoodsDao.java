package org.lanqiao.dao;

import org.lanqiao.entity.Goods;
import org.lanqiao.entity.PageInfo;

public interface GoodsDao {

	public PageInfo<Goods> list(String cid,int pagesize,int pageindex);

	public int totalRecords(String cid);
	
	public Goods getBygimg(String gimg);

	public Goods get(String gid);

	public void remove(String gid);

	public void insert(Goods goods);

	public void updata(Goods goods);
}
