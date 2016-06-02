package com.lw.fsx.util;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDriver;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.pool.ObjectPool;
import org.apache.commons.pool.impl.GenericObjectPool;

/**
 * JDBC工具类
 * @author 
 *
 */
public class JDBCUtil {
	//驱动名称
	private String dri = "com.mysql.jdbc.Driver";
	private String url = null;
	private String userName = null;
	private String password = null;
	private static JDBCUtil jdbcUtil = null;
	private static ObjectPool connectionPool = null;

	/**
	 * 私有构造函数，外部不能创建这个类。
	 */
	private JDBCUtil() {
		//加载配置文件。
		try {
			PropertiesUtil putil = new PropertiesUtil();
			url=putil.readValue("dbUrl");
			userName=putil.readValue("dbUserName");
			password=putil.readValue("dbUserPwd");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 得到一个实例
	 * @return JDBCUtil
	 */
	public static JDBCUtil getInstance() {
		synchronized (JDBCUtil.class) {
			if (jdbcUtil == null) {
				jdbcUtil = new JDBCUtil();
			}
		}
		return jdbcUtil;
	}
	
	/**
	 * 查询sql语句
	 * @param sql 被执行的sql语句
	 * @return List<Map<String,Object>>
	 * @throws SQLException 
	 */
	public List<Map<String,Object>> query(String sql) throws SQLException {
		List<Map<String,Object>> results = null;
		Connection conn = null;
		try {
			conn = getConnection();
			QueryRunner qr = new QueryRunner();
			results =qr.query(conn, sql, new MapListHandler());
		}  finally {
			Close(conn);
		}
		return results;
	}
	
	/**
	 * 根据参数查询sql语句
	 * @param sql sql语句
	 * @param param 参数
	 * @return
	 * @throws SQLException 
	 */
	public List<?> query(String sql, Object param) throws SQLException {
		List<?> results = null;
		Connection conn = null;
		try {
			conn = getConnection();
			QueryRunner qr = new QueryRunner();
			results = (List<?>) qr.query(conn, sql, param, new MapListHandler());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Close(conn);
		}
		return results;
	}

	public List<?> query(String sql, Class<?> module) throws SQLException {
		List<?> results = null;
		Connection conn = null;
		try {
			conn = getConnection();
			QueryRunner qr = new QueryRunner();
			results = (List<?>) qr.query(conn, sql, new BeanListHandler(module));
		}  finally {
			Close(conn);
		}
		return results;
	}

	public Object get_one(String sql, Class<?> module) throws SQLException {
		Object results = null;
		Connection conn = null;
		try {
			conn = getConnection();
			QueryRunner qr = new QueryRunner();
			results = (Object) qr.query(conn, sql, new BeanHandler(module));
		} finally {
			Close(conn);
		}
		return results;
	}

	/**
	 * 执行sql语句
	 * @param sql 被执行的sql语句
	 * @return 受影响的行
	 * @throws Exception
	 */
	public int execute(String sql) throws Exception {
		Connection conn = getConnection();
		int rows = 0;
		try {
			QueryRunner qr = new QueryRunner();
			rows = qr.update(conn, sql);
		} finally {
			Close(conn);
		}
		return rows;
	}

	/**
	 * 执行含参数的sql语句
	 * @param sql 被执行的sql语句
	 * @param params 参数
	 * @return 返回受影响的行
	 * @throws Exception
	 */
	public int execute(String sql, Object[] params) throws Exception {
		Connection conn = getConnection();
		int rows = 0;
		try {
			QueryRunner qr = new QueryRunner();
			rows = qr.update(conn, sql, params);
		} finally {
			Close(conn);
		}
		return rows;
	}

	/**
	 * 关闭连接
	 * @param conn
	 * @throws SQLException 
	 */
	public void Close(Connection conn) throws SQLException {
		if(conn!=null){
			conn.close();
		}
		DbUtils.closeQuietly(conn);
	}

	/**
	 * 启动连接池
	 */
	private void StartPool() {
		try {
			Class.forName(dri);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		if (connectionPool != null) {
			ShutdownPool();
		}
		try {
			connectionPool = new GenericObjectPool(null);
			ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(
					url, userName, password);
			PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(
					connectionFactory, connectionPool, null, "select 1",
					false, true);
			Class.forName("org.apache.commons.dbcp.PoolingDriver");
			PoolingDriver driver = (PoolingDriver) DriverManager
					.getDriver("jdbc:apache:commons:dbcp:");
			driver.registerPool("dbpoolMySql", poolableConnectionFactory.getPool());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 关闭连接池
	 */
	private static void ShutdownPool() {
		try {
			PoolingDriver driver = (PoolingDriver) DriverManager
					.getDriver("jdbc:apache:commons:dbcp:");
			driver.closePool("dbpoolMySql");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 得到一个连接
	 * @return
	 */
	public synchronized Connection getConnection() {
		Connection conn = null;
		try {
			if (connectionPool == null)
				StartPool();
			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:dbpoolMySql");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}
