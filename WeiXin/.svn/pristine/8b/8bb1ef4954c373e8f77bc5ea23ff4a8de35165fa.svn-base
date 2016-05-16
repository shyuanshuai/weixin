package com.yuanshuai.weixin.daoImpl;

import java.util.List;

import com.yuanshuai.weixin.dao.IUserDao;

public class UserDaoImpl extends BaseDaoImpl implements IUserDao {

	@Override
	public Object findByOpenId(String openId) {
		String hql = "from User u where u.openId = ?";

		try {
			List<?> list = this.getHibernateTemplate().find(hql, openId);
			if (list.size() > 0) {
				return list.get(0);
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}

	}

}
