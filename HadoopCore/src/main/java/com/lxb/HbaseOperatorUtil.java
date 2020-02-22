package com.lxb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellScanner;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

public class HbaseOperatorUtil {
	// 用于链接hbase的连接器对象,类似于mysql jdbc的Connection
	public Connection connection;

	// 用hbase configuration初始化配置信息时会自动加载当前应用classpath下的hbase-site.xml
	public static Configuration configuration = HBaseConfiguration.create();

	/**
	 * 1、初始化connection
	 */
	public HbaseOperatorUtil(String zookeeperList) {
		configuration.set("hbase.zookeeper.quorum", zookeeperList);
		configuration.set("hbase.zookeeper.property.clientPort", "2181");
		configuration.set("zookeeper.znode.parent", "/hbase-unsecure");
		// 对connection初始化
		try {
			connection = ConnectionFactory.createConnection(configuration);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 2、创建表
	 * 
	 * @param args
	 * @throws IOException
	 */
	public void createTable(String tablename, String... columnFamilys)
			throws IOException {
		// 获取admin对象
		Admin admin = null;
		try {
			admin = connection.getAdmin();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 创建tablename对象描述表的名称信息
		TableName tname = TableName.valueOf(tablename);// bd17:mytable
		// 创建HTableDescriptor对象，描述表信息
		HTableDescriptor tDescriptor = new HTableDescriptor(tname);
		// 判断是否表已存在
		if (admin.tableExists(tname)) {
			System.out.println("表" + tablename + "已存在");
			return;
		}
		// 添加表列簇信息
		for (String cf : columnFamilys) {
			HColumnDescriptor famliy = new HColumnDescriptor(cf);
			tDescriptor.addFamily(famliy);
		}
		// 调用admin的createtable方法创建表
		admin.createTable(tDescriptor);
		System.out.println("表" + tablename + "创建成功");
	}

	// 查询数据
	public void getData(String table_Name) throws Exception {
		TableName tableName = TableName.valueOf(table_Name);
		Table table = connection.getTable(tableName);
		// 构建get对象
		List<Get> gets = new ArrayList<Get>();
		for (int i = 0; i < 5; i++) {
			Get get = new Get(Bytes.toBytes("rowkey_" + i));
			gets.add(get);
		}
		Result[] results = table.get(gets);
		for (Result result : results) {
			// 使用cell获取result里面的数据
			CellScanner cellScanner = result.cellScanner();
			while (cellScanner.advance()) {
				Cell cell = cellScanner.current();
				// 从单元格cell中把数据获取并输出
				// 使用 CellUtil工具类，从cell中把数据获取出来
				String famliy = Bytes.toString(CellUtil.cloneFamily(cell));
				String qualify = Bytes.toString(CellUtil.cloneQualifier(cell));
				String rowkey = Bytes.toString(CellUtil.cloneRow(cell));
				String value = Bytes.toString(CellUtil.cloneValue(cell));
				System.out.println("rowkey:" + rowkey + ",columnfamily:"
						+ famliy + ",qualify:" + qualify + ",value:" + value);
			}
		}
	}

	/**
	 * 批量添加map对象
	 * 
	 * @param tableName
	 * @param columnFamily
	 * @param columnQualifier
	 * @param keyValueMap
	 * @throws IOException
	 */
	public void putBatch(String tableName, String columnFamily,
			String columnQualifier, Map<String, Object> keyValueMap)
			throws IOException {
		Set<String> keySet = keyValueMap.keySet();
		for (String key : keySet) {
			Object value = keyValueMap.get(key);
			put(tableName, columnFamily, columnQualifier, key, value);
		}
	}

	/**
	 * 单条插入hbase数据
	 * 
	 * @param tableName
	 * @param columnFamily
	 * @param columnQualifier
	 * @param rowKey
	 * @param value
	 * @throws IOException
	 */
	public void put(String tableName, String columnFamily,
			String columnQualifier, String rowKey, Object value)
			throws IOException {
		/**
		 * 获取到相应的表对象
		 */
		TableName tableNameObj = TableName.valueOf(tableName);
		Table tableObj = connection.getTable(tableNameObj);
		/*
		 * 构造要存储的记录对象put
		 */
		Put put = new Put(Bytes.toBytes(rowKey));
		put.addColumn(Bytes.toBytes(columnFamily),
				Bytes.toBytes(columnQualifier), Bytes.toBytes(value.toString()));
		/**
		 * 实际发送写请求
		 */
		tableObj.put(put);
	}

	// 关闭连接
	public void cleanUp() throws Exception {
		connection.close();
	}

	public static void main(String[] args) throws Exception {
		/**
		 * 1、初始化connection 2、创建表 3、删除表 4、写入数据 5、读取数据 6、关闭client端
		 */
		String zookeeperList = "192.168.1.112";
		HbaseOperatorUtil hbaseReadData = new HbaseOperatorUtil(zookeeperList);
		hbaseReadData.createTable("weibo", "user", "content");
		hbaseReadData.getData("weibo");
		hbaseReadData.cleanUp();

	}
}

