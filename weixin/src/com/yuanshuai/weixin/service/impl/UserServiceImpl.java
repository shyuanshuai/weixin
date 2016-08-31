package com.yuanshuai.weixin.service.impl;

import com.yuanshuai.weixin.beans.User;
import com.yuanshuai.weixin.dao.IUserDao;
import com.yuanshuai.weixin.service.IUserService;

public class UserServiceImpl implements IUserService {

	private IUserDao userDao;

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public boolean save(User user) {
		User obj = (User) userDao.findByOpenId(user.getOpenId());
		if (obj == null) {
			return userDao.insert(user);
		} else {
			return false;
		}
	}

	@Override
	public boolean getRoboted(String openId) {
		User user = (User) userDao.findByOpenId(openId);
		if (user == null) {
			return false;
		} else {
			return user.getRobot();
		}
	}

	@Override
	public boolean updateRobotEnter(String openId) {
		User user = (User) userDao.findByOpenId(openId);
		if (user == null) {
			return false;
		} else {
			user.setRobot(true);
			return userDao.modify(user);
		}
	}

	@Override
	public boolean updateRobotExit(String openId) {
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
