package com.lw.fsx.vo;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CommonDAO {
	/**
	 * log4j日志记录
	 */
	private static final Log log = LogFactory.getLog(CommonDAO.class);
	
	/**
	 * 从hibernate的session工厂中得到一个session
	 * @return Session
	 */
	public Session getSession() {
		log.debug("getSession function");
		return HibernateSessionFactory.getSession();
	}
	
	/**
	 * 根据一个HQL 语句进行查询<br/>
	 * 例如hql语句"from User where userName='abc'"
	 * @param <T> 泛型
	 * @param hql 被查询的hql语句
	 * @return List<T>
	 */
	public <T> List<T> findByHql(String hql) {
		log.debug("findByHql:"+hql);
		String queryString = hql;
		Query queryObject = getSession().createQuery(queryString);
		getSession().clear();
		return queryObject.list();
	}
	/**
	 * sql语句
	 * @param <T>
	 * @param sql
	 * @return
	 */
	public <T> List<T> findBySql(String sql) {
		log.debug("findByHql:"+sql);
		String queryString = sql;
		Query queryObject = getSession().createSQLQuery(queryString);
		getSession().clear();
		return queryObject.list();
	}

	/**
	 * 根据hql语句和参数进行查询。<br/>
	 * 例如 hql为from User where userId=:id and userName in(:nameList),<br/>
	 * 此时param应该有两个值，一个key为id，值为object，另一个key为userName，值为一个集合如list。
	 * @param <T>泛型
	 * @param hql 被查询的hql语句
	 * @param param 参数map
	 * @return List<T>
	 */
	public <T> List<T> findByHql(String hql, Map<String, Object> param) {
		log.debug("findByHql:"+hql);
		String queryString = hql;
		Query queryObject = getSession().createQuery(queryString);
		if (param != null && param.size() > 0) {
			for (Map.Entry<String, Object> entry : param.entrySet()) {
				if (entry.getValue() instanceof java.util.Collection) {
					//如果某个值是集合。例如in一个list。
					queryObject.setParameterList(entry.getKey(),
							(java.util.Collection) entry.getValue());
				} else {
					queryObject.setParameter(entry.getKey(), entry.getValue());
				}
			}
		}
		getSession().clear();
		return queryObject.list();
	}

	/**
	 * 根据hql语句和结果条数进行查询。<br/>
	 * 例如 count为2时则只得到结果集的前两个。
	 * @param <T> 泛型
	 * @param hql 被查询的hql语句
	 * @param count 结果条数
	 * @return List<T>
	 */
	public <T> List<T> findByHqlAsCount(String hql, Integer count) {
		log.debug("findByHqlAsCount:"+hql);
		String queryString = hql;
		Query queryObject = getSession().createQuery(queryString);
		queryObject.setMaxResults(count);
		getSession().clear();
		return queryObject.list();
	}

	/**
	 * 用于分页时的查询。根据hql语句和当前是第几页，每页显示的条数，得到符合的结果。
	 * <br/>例如 currPage为2，pageSize为10，则查出第11到第20条记录。
	 * @param <T> 泛型
	 * @param currPage 当前第几页
	 * @param pageSize 每页显示的个数
	 * @param hql 被查询的hql语句
	 * @return
	 */
	public <T> List<T> findPageByHql(int currPage, int pageSize, String hql) {
		log.debug("findPageByHql:"+hql);
		String queryString = hql;
		Query queryObject = getSession().createQuery(queryString);
		queryObject.setFirstResult((currPage - 1) * pageSize);
		queryObject.setMaxResults(pageSize);
		getSession().clear();
		return queryObject.list();
	}

	/**
	 * 执行一个hql语句，返回执行后所影响的列数
	 * @param hql 被执行的hql语句
	 * @return int 执行后所影响的列数
	 * @throws Exception 无法执行此hql，抛出的异常
	 */
	public int execute(String hql) throws Exception {
		log.debug("execute:"+hql);
		Transaction tx = getSession().beginTransaction();
		try {
			String queryString = hql;
			Query queryObject = getSession().createQuery(queryString);
			int a = queryObject.executeUpdate();
			tx.commit();
			getSession().clear();
			return a;
		} catch (Exception e) {
			tx.rollback();
			throw e;
		}
	}

	/**
	 * 删除某表中的某个列的值在value数组中的记录<br/>
	 * 例如 tableName为User，propertyName 为userName，value为{"a","b"}，<br/>
	 * 就是删除表User中所有userName为"a"或者为"b"的记录。
	 * @param tableName 表名
	 * @param propertyName 列名
	 * @param value 列的值数组
	 * @return int 返回删除所影响的列数
	 * @throws Exception 删除造成的异常
	 */
	public int deleteInObject(String tableName, String propertyName, Object[] value)
			throws Exception {
		log.debug("deleteInObject,tableName:"+tableName+",propertyName:"+propertyName);
		Transaction tx = getSession().beginTransaction();
		try {
			String queryString = "delete " + tableName + " a where a."
					+ propertyName + " in ( :idlist )";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameterList("idlist", value);
			int b = queryObject.executeUpdate();
			tx.commit();
			getSession().clear();
			return b;
		} catch (Exception e) {
			tx.rollback();
			throw e;
		}
	}

	/**
	 * 根据表名查出这个表下的所有记录
	 * @param <T> 泛型
	 * @param tableName 表名
	 * @return List<T>
	 */
	public <T> List<T> findAll(String tableName) {
		log.debug("findAll:"+tableName);
		String hql = "from " + tableName + " ";
		return findByHql(hql);
	}

	/**
	 * 根据某个列的值查询数据
	 * @param <T> 泛型
	 * @param tableName 表名
	 * @param propertyName 列名
	 * @param value 列的值
	 * @return List<T>
	 */
	public <T> List<T> findByProperty(String tableName, String propertyName,
			Object value) {
		String queryString = "from " + tableName+ " as model where model." + propertyName + "= ?";
		log.debug("findByProperty:"+queryString);
		Session session = getSession();
		Query queryObject = session.createQuery(queryString);
		queryObject.setParameter(0, value);
		getSession().clear();
		return queryObject.list();
	}

	/**
	 * 批量插入一些对象到表中
	 * @param objs 对象list
	 * @throws Exception 插入异常
	 */
	public void batchSave(List objs) throws Exception {
		log.debug("batchSave sum:"+objs.size());
		Session session = getSession();
		Transaction ts = session.beginTransaction();
		try {
			int i = 0;
			for (Object ob : objs) {
				session.save(ob);
				if (++i % 10 == 0) {
					session.flush();
					session.clear();
				}
			}
		} catch (Exception e) {
			throw e;
		} finally {
			ts.commit();
			session.close();
		}
	}
}
