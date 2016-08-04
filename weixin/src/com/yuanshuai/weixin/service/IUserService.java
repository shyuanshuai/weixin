package com.yuanshuai.weixin.service;

import com.yuanshuai.weixin.beans.User;

public interface IUserService {
	
	public boolean addUser(User user);
	
	public boolean adjustRobot(String openId);
	
	public boolean enterRobot(String openId);

	public boolean exitRobot(String openId);
	
	public User findByOpenId(String openId);

}
