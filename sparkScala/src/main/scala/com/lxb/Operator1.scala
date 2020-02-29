package com.lxb


import org.apache.spark.{SparkConf, SparkContext}

object Operator1 {

  var conf = new SparkConf()
  conf.setAppName("key-value型算子练习")
  conf.setMaster("local[*]")
  var sc = new SparkContext(conf)

  def main(args: Array[String]): Unit = {
    joinTest()
    sc.stop()
  }

  def mapValue(): Unit = {
    var first = sc.parallelize(List(("张一", 1), ("张二", 2), ("张三", 3), ("张四", 4)), 2)

    var second = first.mapValues(x => x + 1)
    println(second.collect() toBuffer)
  }

  def combineByKeyTest(): Unit = {
    val initialScores = Array(("Fred", 88.0), ("Fred", 95.0), ("Fred", 91.0), ("Wilma", 93.0), ("Wilma", 95.0), ("Wilma", 98.0))
    val d1 = sc.parallelize(initialScores)
    type MVType = (Int, Double) //定义一个元组类型(科目计数器,分数)
    var d2 = d1.combineByKey(
      score => (1, score),
      (c1: MVType, newScore) => (c1._1 + 1, c1._2 + newScore),
      (c1: MVType, c2: MVType) => (c1._1 + c2._1, c1._2 + c2._2)
    ).map { case (name, (num, socre)) => (name, socre / num) }.collect
    println(d2.toBuffer)
  }

  def reduceByKey(): Unit = {
    val first = sc.parallelize(List("小米", "华为", "苹果", "小米", "三星", "诺基亚", "小米"))
    var second = first.map(x => (x, 1))
    println(second.collect().toBuffer)
    println(second.reduceByKey(_ + _).collect().toBuffer)
  }

  def joinTest(): Unit = {
    var first = sc.parallelize(List(("张一", 1), ("张二", 2), ("张三", 3), ("张四", 4)), 2)
    var second = sc.parallelize(List(("张一", 1), ("张二", 2), ("张三", 3), ("张四", 4)), 2)
    var result = first.join(second).collect()
    println(result.toList)
    println(result.toBuffer)
  }
}
