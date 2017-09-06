package org.lanqiao.service.impl;

import java.util.List;

import org.lanqiao.dao.impl.UserDaoImpl;
import org.lanqiao.entity.PasswordAnswer;
import org.lanqiao.entity.User;
import org.lanqiao.service.UserService;

public class UserServiceImpl implements UserService {
	private UserDaoImpl dao=new UserDaoImpl();
	public UserServiceImpl() {
		dao=new UserDaoImpl();
	}
	@Override
	public void insertUser(User user) {
		dao.insert(user);
		
	}
	@Override
	public void getByuserid(String userid) {
		 dao.getbyuserid(userid);
	}
	@Override
	public User login(String loginid, String password) {
		User currentUser = dao.getUserByLoginId(loginid);
		User user = null;
//		System.out.println(currentUser);
		if(currentUser==null){//验证有没有此账号;
			return null;
		}
		//验证是否是有效的账户
//		System.out.println(currentUser.getUstateid());
		if (currentUser.getUstateid().equals("B5868B7A06E54DAEB19658343D3A2B28")) {		
			if(currentUser!=null){ //验证密码是否正确
//				System.out.println(currentUser.getUpassword());
				if(currentUser.getUpassword().equals(password)){
					user = currentUser;
					return user;
				}
			}
			
		}
		return null;
	}
	@Override
	public User getUserByLoginId(String loginid) {

		return dao.getUserByLoginId(loginid);
	}
	@Override
	public void updateUser(PasswordAnswer passwordAnswer) {
		dao.update(passwordAnswer);
		
	}
	@Override
	public void updateUser(User user) {
		dao.update(user);
		
	}
	@Override
	public List<User> userList() {
		return dao.list();
	}
	@Override
	public void removeUser(String userid) {
		dao.remove(userid);
		
	}
	
}
