package com.tianliangedu.job001.persistence;

import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.helper.DataUtil;

import com.tianliangedu.job001.iface.persistence.DataPersistenceInterface;
import com.tianliangedu.job001.pojos.entity.NewsItemEntity;
import com.tianliangedu.job001.utils.DateUtil;
import com.tianliangedu.job001.utils.SystemConfigParas;
import com.tianliangedu.job001.utils.TransportClientUtil;

public class DataPersist4EsImpl implements DataPersistenceInterface {
	private static TransportClientUtil transportClientUtil;
	private static String indexName = "index_from_tc";
	private static String typeName = "type_from_tc";

	public DataPersist4EsImpl() {
		try {
			String es_cluster_name = SystemConfigParas.es_cluster_name;
			String hostname = SystemConfigParas.hostname;
			int admin_port = SystemConfigParas.admin_port;
			
			transportClientUtil = new TransportClientUtil(es_cluster_name,
					hostname, admin_port);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean persist(List<NewsItemEntity> itemList) {
		return true;
	}

	@Override
	public ResultSet getResultSet(String sql) {
		return null;
	}

	@Override
	public boolean persist(NewsItemEntity itemEntity) {
		try {
			Map<String, String> kvMap = new HashMap<String, String>();
			kvMap.put("title", itemEntity.getTitle());
			kvMap.put("source_url", itemEntity.getSourceURL());
			kvMap.put("post_time", itemEntity.getPostTimeString());
			kvMap.put("source_name", itemEntity.getSourceName());
			kvMap.put("body", itemEntity.getBody());
			kvMap.put("insert_time",
					DateUtil.formatDateToString(itemEntity.getInsertDate()));

			transportClientUtil.addOneDocument(indexName, typeName, kvMap);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static void main(String[] args) throws ParseException,
			ClassNotFoundException, SQLException {
		DataPersist4EsImpl mysqlPersist = new DataPersist4EsImpl();
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
