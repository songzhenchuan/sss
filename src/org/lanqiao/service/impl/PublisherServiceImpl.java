package org.lanqiao.service.impl;

import org.lanqiao.dao.PublisherDao;
import org.lanqiao.dao.impl.PublisherDaoImpl;
import org.lanqiao.entity.Publisher;
import org.lanqiao.service.PublisherService;

public class PublisherServiceImpl implements PublisherService {
	private PublisherDao dao =null;
	public  PublisherServiceImpl() {
		dao=new PublisherDaoImpl();
	}
	@Override
	public Publisher getBypid(String pid){
//		System.out.println(dao);
		return dao.getBypid(pid);
	}
}
