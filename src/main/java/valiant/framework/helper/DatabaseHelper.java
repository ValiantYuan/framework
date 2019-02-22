package valiant.framework.helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import valiant.util.CollectionUtil;
import valiant.util.PropsUtil;

/**
 * 数据库操作助手
 *
 * @author yuanq5
 *
 */
public final class DatabaseHelper {
	private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseHelper.class);
	private static final QueryRunner QUERY_RUNNER = new QueryRunner();
	private static final ThreadLocal<Connection> CONNECTION_HOLDER = new ThreadLocal<>();
	private static final BasicDataSource DATA_SOURCE;
	
	private static final String DRIVER;
	private static final String URL;
	private static final String USERNAME;
	private static final String PASSWORD;
	static {
		Properties conf = PropsUtil.loadProps("config.properties");
		DRIVER = conf.getProperty("jdbc.driver");
		URL = conf.getProperty("jdbc.url");
		USERNAME = conf.getProperty("jdbc.username");
		PASSWORD = conf.getProperty("jdbc.password");
		
		DATA_SOURCE = new BasicDataSource();
		DATA_SOURCE.setDriverClassName(DRIVER);
		DATA_SOURCE.setUrl(URL);
		DATA_SOURCE.setUsername(USERNAME);
		DATA_SOURCE.setPassword(PASSWORD);
//		try {
//			Class.forName(DRIVER);
//		} catch (ClassNotFoundException e) {
//			// TODO: handle exception
//			LOGGER.error("can not load jdbc driver", e);
//		}
	}
	
	/**
	 * 
	 * 获取数据库连接
	 * @return
	 */
	public static Connection getConnection() {
//		Connection connection = null;
		Connection connection = CONNECTION_HOLDER.get();
		if (connection == null) {
			try {
				connection = DATA_SOURCE.getConnection();
			} catch (SQLException e) {
				// TODO: handle exception
				LOGGER.error("get connection failure", e);
				throw new RuntimeException();
			} finally {
				CONNECTION_HOLDER.set(connection);
			}
			
		}
		return connection;
	}
	
	/**
	 * 关闭数据库连接
	 */
	
//	public static void closeConnection() {
//		Connection connection = CONNECTION_HOLDER.get();
//		if (connection != null) {
//			try {
//				connection.close();
//			} catch (SQLException e) {
//				// TODO: handle exception
//				LOGGER.error("close connection failure", e);
//				throw new RuntimeException();
//			} finally {
//				CONNECTION_HOLDER.remove();
//			}
//		}
//	}
	
	
	/**
	 * 查询实体列表，返回多条记录
	 * @param entityClass
	 * @param sql
	 * @param params
	 * @return
	 */
	public static <T> List<T> queryEntityList(Class<T> entityClass, String sql, Object... params) {
		List<T> entityList;
		try {
			Connection connection = getConnection();
			entityList = QUERY_RUNNER.query(connection, sql, new BeanListHandler<T>(entityClass), params);
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error("query entity list failure", e);
			throw new RuntimeException(e);
		} 
		return entityList;
	}
	
	/**
	 * 查询单条记录
	 * @param entityClass
	 * @param sql
	 * @param params
	 * @return
	 */
	public static <T> T queryEntity(Class<T> entityClass, String sql, Object... params) {
		T entity;
		try {
			Connection connection = getConnection();
			entity = QUERY_RUNNER.query(connection, sql, new BeanHandler<T>(entityClass), params);
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error("query entity failure", e);
			throw new RuntimeException(e);
		} 
		return entity;
	}
	
	/**
	 * 执行查询语句
	 * @param sql
	 * @param params
	 * @return
	 */
	public static List<Map<String, Object>> executeQurey(String sql, Object... params) {
		List<Map<String, Object>> result;
		try {
			Connection connection = getConnection();
			result = QUERY_RUNNER.query(connection, sql, new MapListHandler(), params);
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error("execute query failure", e);
			throw new RuntimeException(e);
		}
		return result;
	}
	
	/**
	 * 执行更新语句（包括 update、insert、delete）
	 * @param sql
	 * @param params
	 * @return
	 */
	public static int executeUpdate(String sql, Object... params) {
		int rows = 0;
		try {
			Connection connection = getConnection();
			rows = QUERY_RUNNER.update(connection, sql, params);
		} catch (SQLException e) {
			// TODO: handle exception
			LOGGER.error("execute udpate failure", e);
			throw new RuntimeException(e);
		} 
		return rows;
	}
	/**
	 * 插入实体
	 * @param entityClass
	 * @param fieldMap
	 * @return
	 */
	public static <T> boolean insertEntity(Class<T> entityClass, Map<String, Object> fieldMap) {
		if (CollectionUtil.isEmpty(fieldMap)) {
			LOGGER.error("can not insert entity: fieldMap is empty");
			return false;
		}
		
		String sql = "INSERT INTO " + getTableName(entityClass);
		StringBuilder columns = new StringBuilder("(");
		StringBuilder values = new StringBuilder("(");
		for (String fieldName : fieldMap.keySet()) {
			columns.append(fieldName).append(", ");
			values.append("?, ");
		}
		columns.replace(columns.lastIndexOf(", "), columns.length(), ")");
		values.replace(values.lastIndexOf(", "), values.length(), ")");
		sql += columns + " VALUES" + values;
		
		Object[] params = fieldMap.values().toArray();
		return executeUpdate(sql, params) == 1;
	}
	/**
	 * 更新实体
	 * @param entityClass
	 * @param id
	 * @param fieldMap
	 * @return
	 */
	public static <T> boolean updateEntity(Class<T> entityClass, long id, Map<String, Object> fieldMap) {
		if (CollectionUtil.isEmpty(fieldMap)) {
			LOGGER.error("can not update entity: fieldMap is empty");
			return false;
		}
		
		String sql = "UPDATE " + getTableName(entityClass) + " SET ";
		StringBuilder colunms = new StringBuilder();
		for (String fieldName : fieldMap.keySet()) {
			colunms.append(fieldName).append("=?, ");
		}
		sql += colunms.substring(0, colunms.lastIndexOf(", ")) + " WHERE id = ?";
		List<Object> paramList = new ArrayList<>();
		paramList.addAll(fieldMap.values());
		paramList.add(id);
		Object[] params = paramList.toArray();
		return executeUpdate(sql, params) == 1;
	}
	/**
	 * 删除实体
	 * @param entityClass
	 * @param id
	 * @return
	 */
	public static <T> boolean deleteEntity(Class<T> entityClass, long id) {
		String sql = "DELETE FROM " + getTableName(entityClass) + " WHERE id = ?";
		return executeUpdate(sql, id) == 1;
	}
	/**
	 * 获取实体名
	 * @param entityClass
	 * @return
	 */
	public static <T> String  getTableName(Class<T> entityClass) {
		return entityClass.getSimpleName();
	}
	/**
	 * 执行文件中中sql语句
	 * @param filePath
	 */
	public static void excuteSqlFile(String filePath) {
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		try {
				String sql;
				while ((sql = reader.readLine()) != null) {
					executeUpdate(sql);
				}
			} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error("execute sql file failure", e);
			throw new RuntimeException(e);
		}
	}
	/**
	 * 开启事务
	 */
	public static void beginTransaction() {
		Connection connection = getConnection();
		if (connection != null) {
			try {
				connection.setAutoCommit(false);
			} catch (SQLException e) {
				// TODO: handle exception
				LOGGER.error("begin transaction failure", e);
				throw new RuntimeException(e);
			} finally {
				CONNECTION_HOLDER.set(connection);
			}
		} 
	}
	/**
	 * 提交事务
	 */
	public static void commitTransaction() {
		Connection connection = getConnection();
		if (connection != null) {
			try {
				connection.commit();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				LOGGER.error("commit transaction failure", e);
				throw new RuntimeException(e);
			} finally {
				CONNECTION_HOLDER.remove();
			}
		}
	}
	
	public static void rollbackTransaction() {
		Connection connection = getConnection();
		if (connection != null) {
			try {
				connection.rollback();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				LOGGER.error("roll back transaction failure", e);
				throw new RuntimeException(e);
			} finally {
				CONNECTION_HOLDER.remove();
			}
		}		
	}
}
