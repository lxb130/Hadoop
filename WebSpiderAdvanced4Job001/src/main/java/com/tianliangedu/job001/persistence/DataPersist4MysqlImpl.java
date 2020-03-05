package com.tianliangedu.job001.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.tianliangedu.job001.iface.persistence.DataPersistenceInterface;
import com.tianliangedu.job001.pojos.entity.NewsItemEntity;
import com.tianliangedu.job001.utils.DataBaseUtil;
import com.tianliangedu.job001.utils.SystemConfigParas;

public class DataPersist4MysqlImpl implements DataPersistenceInterface {
	private DataBaseUtil dataBaseUtil;
	// public static String insertSql =
	// "insert into news_item_info(title,source_url,post_time,insert_time) values(?,?,?,?)";
	public static String insertSql = "insert into news_item_info_v2(title,source_url,post_time,source_name,body,insert_time) values(?,?,?,?,?,?)";

	public DataPersist4MysqlImpl() {
		try {
			dataBaseUtil = new DataBaseUtil(SystemConfigParas.db_driver,
					SystemConfigParas.db_url, SystemConfigParas.db_username,
					SystemConfigParas.db_password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean persist(List<NewsItemEntity> itemList) {
		if (itemList != null && itemList.size() > 0) {
			// 利用jdbc提供的execute batch的方法
			try {
				PreparedStatement ps = dataBaseUtil
						.getPreparedStatement(insertSql);
				for (NewsItemEntity itemEntity : itemList) {
					ps.setString(1, itemEntity.getTitle());
					ps.setString(2, itemEntity.getSourceURL());
					ps.setTimestamp(3, new java.sql.Timestamp(itemEntity
							.getPostDateObj().getTime()));
					ps.setTimestamp(4, new java.sql.Timestamp(itemEntity
							.getInsertDate().getTime()));

					// 加入到批量执行的集合中
					ps.addBatch();
				}
				ps.executeBatch();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

	@Override
	public ResultSet getResultSet(String sql) {
		try {
			return dataBaseUtil.getResultSetByStat(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean persist(NewsItemEntity itemEntity) {
		try {
			PreparedStatement ps = dataBaseUtil.getPreparedStatement(insertSql);
			ps.setString(1, itemEntity.getTitle());
			ps.setString(2, itemEntity.getSourceURL());
			ps.setTimestamp(3, new java.sql.Timestamp(itemEntity
					.getPostDateObj().getTime()));
			//插入新增的2个字段
			ps.setString(4, itemEntity.getSourceName());
			ps.setString(5, itemEntity.getBody());
			ps.setTimestamp(6, new java.sql.Timestamp(itemEntity
					.getInsertDate().getTime()));
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();

			return false;
		}

		return true;
	}

	public static void main(String[] args) throws ParseException,
			ClassNotFoundException, SQLException {
		DataPersist4MysqlImpl mysqlPersist = new DataPersist4MysqlImpl();
		// NewsItemEntity itemEntity = new NewsItemEntity("测试title",
		// "2018-07-27 15:04:06", "http://www.baidu.com/");
		//
		// List<NewsItemEntity> itemList = new ArrayList<NewsItemEntity>();
		// itemList.add(itemEntity);
		// itemList.add(itemEntity);
		// itemList.add(itemEntity);
		//
		// mysqlPersist.persist(itemList);
		//
		// System.out.println("done!");
	}

}
