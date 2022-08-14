package hry.util;


import hry.otc.manage.remote.model.account.ExDigitalmoneyAccount;
import hry.otc.manage.remote.model.customer.person.AppPersonInfo;

import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.*;
import java.util.List;
import java.util.Properties;


/**
 * 配置数据源   
 * @Copyright © 北京互融时代软件有限公司
 * @email: zjjtv@gmail.com 
 * @author: zjj   
 * @date: 2018年8月10日 下午12:55:03 
 */
public class DbcontextHolder {
	
//	public static Statement statement = null;
	public static String URL = "";
	public static String USER = "";
	public static String PWD = "";
	static{
		try {
			InputStream insjdbc = DbcontextHolder.class.getClassLoader().getResourceAsStream("jdbc.properties");
			Properties jdbc = new Properties();
			jdbc.load(insjdbc);
			// 使用jdbc链接数据库
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			URL = jdbc.getProperty("jdbc2.url");
			USER = jdbc.getProperty("jdbc2.username");
			PWD = jdbc.getProperty("jdbc2.password");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	


	public static List<ExDigitalmoneyAccount> jdbcQueryAccountList(String sql) throws Exception{
		ResultSetMapper<ExDigitalmoneyAccount> resultSetMapper = new ResultSetMapper<ExDigitalmoneyAccount>();//
		// 获取数据库连接
		Connection connection = DriverManager.getConnection(URL, USER, PWD);
		//System.out.println(sql);
		Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		ResultSet resultSet = statement.executeQuery(sql);
		List<ExDigitalmoneyAccount> pojoList = resultSetMapper.mapRersultSetToObject(resultSet, ExDigitalmoneyAccount.class);
		close(resultSet,statement,connection);
		return pojoList;
	}
	
	public static List<AppPersonInfo> jdbcQueryAppPersonInfo(String sql){
		try {
			ResultSetMapper<AppPersonInfo> resultSetMapper = new ResultSetMapper<AppPersonInfo>();//
			// 获取数据库连接
			Connection connection = DriverManager.getConnection(URL, USER, PWD);
		//	System.out.println(sql);
			Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet resultSet = statement.executeQuery(sql);
			List<AppPersonInfo> pojoList = resultSetMapper.mapRersultSetToObject(resultSet, AppPersonInfo.class);
			close(resultSet,statement,connection);
			return pojoList;
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return null;
	}
	
	public static int jdbcQueryInteger(String sql) throws Exception{
		// 获取数据库连接
		Connection connection = DriverManager.getConnection(URL, USER, PWD);
		//System.out.println(sql);
		Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		ResultSet resultSet = statement.executeQuery(sql);
		int sqlcount = 0;
		if (resultSet.next()) {
			sqlcount = resultSet.getInt(1);
			System.out.println(sqlcount);
		}
		close(resultSet,statement,connection);
		return sqlcount;
	}
	
	public static BigDecimal jdbcQueryBigDecimal(String sql) throws Exception{
		// 获取数据库连接
		Connection connection = DriverManager.getConnection(URL, USER, PWD);
		//System.out.println(sql);
		Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		ResultSet resultSet = statement.executeQuery(sql);
		BigDecimal bigDecimal =null;
		if (resultSet.next()) {
			bigDecimal = (BigDecimal) resultSet.getObject(1);
		}
		close(resultSet,statement,connection);
		return bigDecimal;
	}
	
	public static void jdbcUpdateOrder(String sql) throws Exception{
		// 获取数据库连接
		Connection connection = DriverManager.getConnection(URL, USER, PWD);
		//System.out.println(sql);
		Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		statement.executeUpdate(sql);
		close(null,statement,connection);
	}
	
	private static void close(ResultSet resultSet,Statement statement, Connection connection) throws SQLException {
		if(resultSet != null){
			resultSet.close();
		}
		statement.close();
		connection.close();
	}
	public static void jdbcInsert(String sql) throws Exception{
		// 获取数据库连接
		Connection connection = DriverManager.getConnection(URL, USER, PWD);
		//System.out.println(sql);
		Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		statement.execute(sql);
		close(null,statement,connection);
	}
}
