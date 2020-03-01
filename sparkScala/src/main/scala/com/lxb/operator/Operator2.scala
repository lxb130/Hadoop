package com.lxb.operator

import org.apache.spark.{SparkConf, SparkContext}

object Operator2 {

  var conf = new SparkConf()
  conf.setAppName("key-value型算子练习")
  conf.setMaster("local[*]")
  var sc = new SparkContext(conf)

  def main(args: Array[String]): Unit = {
    CollectAsMap()
    sc.stop()
  }

  def CollectAsMap(): Unit = {
    val first = sc.parallelize(List(("张一", 1), ("李二", 1), ("张一", 2), ("李二", 2), ("张一", 3), ("李二", 3)), 2)
    var second = first.collectAsMap()
    println(second.toMap)
  }


}
