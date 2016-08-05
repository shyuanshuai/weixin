package com.yuanshuai.weixin.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.yuanshuai.weixin.dao.IBaseDao;

/**
 * 通用增删改查实现类
 * 
 * @author YuanShuai
 *
 */
public class BaseDaoImpl extends HibernateDaoSupport implements IBaseDao {

	/**
	 * 通用新增方法
	 */
	@Override
	public boolean insert(Object object) throws HibernateException {
		try {
			this.getHibernateTemplate().save(object);
			return true;
		} catch (DataAccessException e) {
			logger.error(object.getClass().getName() + " 新增失败！", e);
			return false;
		}
	}

	/**
	 * 通用删除方法
	 */
	@Override
	public boolean delete(Object object) throws HibernateException {
		try {
			this.getHibernateTemplate().delete(object);
			return true;
		} catch (DataAccessException e) {
			logger.error(object.getClass().getName() + " 删除失败！", e);
			return false;
		}
	}

	/**
	 * 通用修改方法
	 */
	@Override
	public boolean modify(Object object) throws HibernateException {
		try {
			this.getHibernateTemplate().update(object);
			return true;
		} catch (DataAccessException e) {
			logger.error(object.getClass().getName() + " 更新失败！", e);
			return false;
		}
	}

	/**
	 * 通用根据ID查询方法
	 */
	@Override
	public Object findById(Class<?> obj, String id) throws HibernateException {

		return this.getHibernateTemplate().get(obj, id);
	}

	/**
	 * 通用查询全部方法
	 */
	@Override
	public List<?> findAll(Class<?> obj) throws HibernateException {

		return this.getHibernateTemplate().loadAll(obj);
	}

}
