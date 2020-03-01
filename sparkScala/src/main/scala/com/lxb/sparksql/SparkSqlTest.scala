package com.lxb.sparksql

import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}

object SparkSqlTest {
  def main(args: Array[String]): Unit = {
    /*
      1.初始化spark conf
      2.初始化spark context
      3.初始化spark sql context
      4.做spark sql的df 工作
      5.spark context stop工作
    */
    val conf = new SparkConf()
    conf.setMaster("local[*]")
    conf.setAppName("SparkSql练习")
    val sc = new SparkContext(conf)
    val sQLContext = new SQLContext(sc)
    val jsonDf = sQLContext.read.json("E:\\data\\test.json")
    jsonDf.show()
    sc.stop()
  }
}
