package org.lanqiao.service.impl;

import org.lanqiao.dao.OrderDetailDao;
import org.lanqiao.dao.impl.OrderDetailDaoImpl;
import org.lanqiao.entity.OrderDetail;
import org.lanqiao.service.OrderDetailService;

public class OrderDetailServiceImpl implements OrderDetailService {
	OrderDetailDao dao = new OrderDetailDaoImpl();
	@Override
	public void insertOrderDetail(OrderDetail orderDetail) {
		dao.insert(orderDetail);

	}

}
