package org.lanqiao.service;

import java.util.List;

import org.lanqiao.entity.PasswordAnswer;
import org.lanqiao.entity.User;



public interface UserService {
	public void insertUser(User user);
	public User login(String loginid,String password);
	public User getUserByLoginId(String loginid);
	public void getByuserid(String userid);
	public void updateUser(User user);
	public void updateUser(PasswordAnswer passwordAnswer);
	public List<User> userList();
	public void removeUser(String userid);
}
