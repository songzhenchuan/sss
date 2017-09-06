package org.lanqiao.service.impl;

import java.util.List;

import org.lanqiao.dao.NewsDao;
import org.lanqiao.dao.impl.NewsDaoImpl;
import org.lanqiao.entity.News;
import org.lanqiao.service.NewsService;

public class NewServiceImpl implements NewsService{
	NewsDao dao=null;
	public NewServiceImpl(){
		dao=new NewsDaoImpl();
	}
	@Override
	public List<News> newsList(){
		return dao.list();
	}
	@Override
	public News getNewsById(String id) {
		News news=dao.get(id);
		return news;
	}
}
