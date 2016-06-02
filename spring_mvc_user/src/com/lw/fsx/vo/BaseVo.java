package com.lw.fsx.vo;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;


/**
 * 所有表对象的基类
 * @author 
 *
 */
public class BaseVo implements java.io.Serializable{
	/**
	 * log4j日志记录
	 */
	private static final Log log = LogFactory.getLog(CommonDAO.class);
	
	/**
	 * 从hibernate的session工厂中得到一个session
	 * @return Session
	 */
	private Session getSession() {
		log.debug("getSession function");
		return HibernateSessionFactory.getSession();
	}

	/**
	 * 保存自己
	 */
	public void save() {
		Transaction tx = getSession().beginTransaction();
		try {
			getSession().save(this);
			tx.commit();
			getSession().clear();
		} catch (RuntimeException re) {
			tx.rollback();
			throw re;
		}
	}

	/**
	 * 删除自己
	 */
	public void delete() {
		Transaction tx = getSession().beginTransaction();
		try {
			getSession().delete(this);
			tx.commit();
			getSession().clear();
		} catch (RuntimeException re) {
			tx.rollback();
			throw re;
		}
	}

	/**
	 * 更新自己
	 */
	public void update() {
		Transaction tx = getSession().beginTransaction();
		try {
			getSession().merge(this);
			tx.commit();
			getSession().clear();
		} catch (RuntimeException re) {
			tx.rollback();
			throw re;
		}
	}

	/**
	 * 根据id查出对象
	 * @param <T> 泛型
	 * @param id 对象的id
	 * @return <T> T 对象
	 */
	public <T> T findById(Object id) {
		Class<T> clasz=(Class<T>)this.getClass();
		String objName = clasz.getName().substring(clasz.getName().indexOf(" ") + 1);
		try {
			Object instance = getSession().get(objName, (Serializable) id);
			getSession().clear();
			return (T)instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}
}
