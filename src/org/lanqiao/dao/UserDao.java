package org.lanqiao.dao;

import org.lanqiao.entity.User;

public interface UserDao {
public void insert(User user);
public User getUserByLoginId(String loginid);
}
