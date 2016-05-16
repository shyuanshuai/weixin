package com.yuanshuai.weixin.dao;

import java.util.List;

import org.hibernate.HibernateException;

/**
 * 通用增删改查接口
 * 
 * @author YuanShuai
 *
 */
public interface IBaseDao {

	public boolean insert(Object object) throws HibernateException;

	public boolean delete(Object object) throws HibernateException;

	public boolean modify(Object object) throws HibernateException;

	public Object findById(Class<?> obj, String id) throws HibernateException;

	public List<?> findAll(Class<?> obj) throws HibernateException;
}
