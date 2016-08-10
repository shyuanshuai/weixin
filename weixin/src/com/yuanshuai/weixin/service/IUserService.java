package com.yuanshuai.weixin.service;

import com.yuanshuai.weixin.beans.User;

public interface IUserService {
	
	public boolean save(User user);
	
	public boolean getRoboted(String openId);
	
	public boolean updateRobotEnter(String openId);

	public boolean updateRobotExit(String openId);
	
	public User findByOpenId(String openId);

}
