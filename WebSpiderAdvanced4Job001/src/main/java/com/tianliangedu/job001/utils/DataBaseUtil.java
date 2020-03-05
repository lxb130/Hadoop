package com.tianliangedu.job001.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;

public class DataBaseUtil {
	private String driver;
	private String url;
	private String username;
	private String password;
	private Connection dbConn;

	public DataBaseUtil(String driver, String url, String username,
			String password) throws SQLException, ClassNotFoundException {
		super();
		this.driver = driver;
		this.url = url;
		this.username = username;
		this.password = password;

		this.init();
	}

	// 核心作用是拿到数据库的链接对象
	public void init() throws ClassNotFoundException, SQLException {
		// 第1步：注册驱动
		Class.forName(this.driver);
		// 第2步：数据库链接
		dbConn = DriverManager.getConnection(url, username, password);
	}

	public ResultSet getResultSetByStat(String query) throws SQLException {
		Statement stat = dbConn.createStatement();
		return stat.executeQuery(query);
	}

	public PreparedStatement getPreparedStatement(String sql)
			throws SQLException {
		return dbConn.prepareStatement(sql);
	}

	public Connection getDbConn() {
		return dbConn;
	}

	public void setDbConn(Connection dbConn) {
		this.dbConn = dbConn;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void close() throws SQLException {
		this.dbConn.close();
	}

	public static void main(String[] args) throws ClassNotFoundException,
			SQLException {
		// 声明相关链接数据库的变量
		String mysqlDriver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://192.168.1.15:3306/lixiaotian";
		String username = "lixiaotian";
		String password = "lixiaotian";

		DataBaseUtil dbUtil = new DataBaseUtil(mysqlDriver, url, username,
				password);

		// 第3步：获取执行sql的对象
		String sql = "select * from news_item_info where title= ?";
		// String sql = "select * from news_item_info";
		// PreparedStatement ps = dbConn.prepareStatement(sql);
		
		PreparedStatement ps = dbUtil.getPreparedStatement(sql);
		ps.setString(1, "123");
		ps.executeBatch();

		// 第6步：关闭连接
		dbUtil.close();
	}
}
