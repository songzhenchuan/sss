package org.lanqiao.service.impl;

import java.util.List;

import org.lanqiao.dao.CategoryDao;
import org.lanqiao.dao.impl.CategoryImpl;
import org.lanqiao.entity.Category;
import org.lanqiao.service.CategoryService;

public class CategoryServiceImpl implements CategoryService {
	CategoryDao dao =null;
	public CategoryServiceImpl(){
		dao=new CategoryImpl();
	}
	
	@Override
	public List<Category> categoryList() {
		return dao.list();
	}

	@Override
	public Category getCate(String cid) {
		Category cates=dao.get(cid);
		return cates;
	}

}
