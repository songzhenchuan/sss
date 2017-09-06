package org.lanqiao.dao;

import org.lanqiao.entity.Publisher;

public interface PublisherDao {

	public Publisher getBypid(String pid);

	public Publisher get(String pid);
}
