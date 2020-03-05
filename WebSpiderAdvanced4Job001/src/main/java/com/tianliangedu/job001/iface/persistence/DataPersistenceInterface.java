package com.tianliangedu.job001.iface.persistence;

import java.sql.ResultSet;
import java.util.List;

import com.tianliangedu.job001.pojos.entity.NewsItemEntity;

/**
 * 数据持久化接口类，定义持久化接口方法
 * 
 * @author zel
 * @company 天亮教育
 *
 */
public interface DataPersistenceInterface {
	// 批量保存
	public boolean persist(List<NewsItemEntity> itemList);
	
	// 单条保存
	public boolean persist(NewsItemEntity itemList);
	
	public ResultSet getResultSet(String sql);
	
}
