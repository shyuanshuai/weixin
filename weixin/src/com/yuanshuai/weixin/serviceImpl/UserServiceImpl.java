package com.yuanshuai.weixin.serviceImpl;

import com.yuanshuai.weixin.beans.User;
import com.yuanshuai.weixin.dao.IUserDao;
import com.yuanshuai.weixin.service.IUserService;

public class UserServiceImpl implements IUserService {

	private IUserDao userDao;

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public boolean addUser(User user) {
		User obj = (User) userDao.findByOpenId(user.getOpenId());
		if (obj == null) {
			return userDao.insert(user);
		} else {
			return false;
		}
	}

	@Override
	public boolean adjustRobot(String openId) {
		User user = (User) userDao.findByOpenId(openId);
		if (user == null) {
			return false;
		} else {
			return user.getRobot();
		}
	}

	@Override
	public boolean enterRobot(String openId) {
		User user = (User) userDao.findByOpenId(openId);
		if (user == null) {
			return false;
		} else {
			user.setRobot(true);
			return userDao.modify(user);
		}
	}

	@Override
	public boolean exitRobot(String openId) {
		User user = (User) userDao.findByOpenId(openId);
		if (user == null) {
			return false;
		} else {
			user.setRobot(false);
			return userDao.modify(user);
		}
	}

	@Override
	public User findByOpenId(String openId) {
		User user = (User) userDao.findByOpenId(openId);
		return user;
	}
}
