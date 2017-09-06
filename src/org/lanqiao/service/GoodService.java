package org.lanqiao.service;

import org.lanqiao.entity.Goods;
import org.lanqiao.entity.PageInfo;

public interface GoodService {
	public PageInfo<Goods> goodslist(String cid,int pagesize,int pageindex);
	public int totalRecords(String cid);
	public Goods getBygimg(String gimg);
	public Goods getGoodsBygid(String gid);
	public void removeGoods(String gid);
	public void insertGoods(Goods goods);
	public void updateGoods(Goods goods);
}
