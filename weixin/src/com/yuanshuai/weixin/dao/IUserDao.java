package com.yuanshuai.weixin.dao;

public interface IUserDao extends IBaseDao {

	public Object findByOpenId(String openId);
	
}
