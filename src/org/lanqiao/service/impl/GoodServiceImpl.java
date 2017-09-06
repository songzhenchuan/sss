package org.lanqiao.service.impl;

import org.lanqiao.dao.GoodsDao;
import org.lanqiao.dao.impl.GoodsDaoImpl;
import org.lanqiao.entity.Goods;
import org.lanqiao.entity.PageInfo;
import org.lanqiao.service.GoodService;

public class GoodServiceImpl implements GoodService {
	private GoodsDao dao =null;
	public GoodServiceImpl() {
		dao=new GoodsDaoImpl();
	}
	@Override
	public PageInfo<Goods> goodslist(String cid, int pagesize, int pageindex) {
		return dao.list(cid, pagesize, pageindex);
	}
	@Override
	public int totalRecords(String cid) {
		return dao.totalRecords(cid);
	}
	@Override
	public Goods getBygimg(String gimg) {
		
		return dao.getBygimg(gimg);
	}
	@Override
	public Goods getGoodsBygid(String gid) {

		return dao.get(gid);
	}
	@Override
	public void removeGoods(String gid) {
		 dao.remove(gid);
		
	}
	@Override
	public void insertGoods(Goods goods) {
		 dao.insert(goods);
		
	}
	@Override
	public void updateGoods(Goods goods) {
		dao.updata(goods);
		
	}
}
