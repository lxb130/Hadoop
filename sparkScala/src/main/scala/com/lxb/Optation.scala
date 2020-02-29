package com.lxb

import org.apache.spark.{SparkConf, SparkContext}

object Optation {

  var conf = new SparkConf()
  conf.setAppName("算子练习")
  conf.setMaster("local[*]")
  var sc = new SparkContext(conf)

  def main(args: Array[String]): Unit = {

    distinctTest1()
    sc.stop()
  }

  def MapTest(): Unit = {
    val rdd1 = sc.parallelize(List(5, 6, 4, 7, 3, 8, 2, 9, 1, 10))
    var res = rdd1.map(_ * 2).sortBy(x => x, true)
    println(res.collect().toBuffer)
  }

  def MapTest1(): Unit = {
    val rdd1 = sc.parallelize(List("Hello", "World", "天亮教育", "大数据"), 2)

    var rdd2 = rdd1.map(_.length)
    println(rdd2.collect().toBuffer)
  }

  def MapTest2(): Unit = {
    var rdd1 = sc.parallelize(1 to 5, 2)
    var rdd2 = rdd1.map(1 to _)
    println(rdd2.collect().toBuffer)
  }

  def FlatMapTest(): Unit = {
    var rdd1 = sc.parallelize(Array("a b c", "d e f", "g h i"))
    var rdd2 = rdd1.flatMap(_.split(" "))
    println(rdd2.collect().toBuffer)
  }

  def FlatMapTest1(): Unit = {
    val rdd1 = sc.parallelize(List(List("a b c", "a b b"), List("e f g", "a f g"), List("h i j", "a a b")))
    var rdd2 = rdd1.flatMap(_.flatMap(_.split(" ")))
    println(rdd2.collect().toBuffer)
  }

  def MapPartitions(): Unit = {
    var rdd1 = sc.parallelize(Seq(1, 2, 3, 4, 5), 3)
    var rdd2 = rdd1.mapPartitions(part => {
      part.map(num => num * num)
    })
    println(rdd2.collect().toBuffer)
    println(rdd2.collect().toList)
    println(rdd2.collect().toSeq)
    println(rdd2.max())
    println(rdd2.min())
    println(rdd2.count())
  }

  def glomTest(): Unit = {
    var rdd1 = sc.parallelize(Seq(1, 2, 3, 4, 5, 6), 3)
    rdd1.glom().foreach(println)
    println(rdd1.glom().collect().toList)
    println(rdd1.glom().collect().toSeq)
    println(rdd1.glom().collect().toBuffer)
  }

  def unionTest(): Unit = {
    var a = sc.parallelize(Seq(1, 2, 3))
    var b = sc.parallelize(Seq(4, 5, 6))
    println(a.union(b).collect().toBuffer)
    println((a ++ b).collect().toBuffer)
    println((a union b).collect().toBuffer)
  }

  def GroupbyTest(): Unit = {
    val a = sc.parallelize(Seq(1, 2, 6, 4, 10, 100, 200, 300), 3)
    var b = a.groupBy(x => {
      if (x > 10) ">10" else "<10"
    })
    println(b.collect().toBuffer)
  }

  def filterTest(): Unit = {
    var a = sc.parallelize(1 to 21, 3)
    var b = a.filter(_ % 4 == 0)
    println(b.collect().toBuffer)
  }

  def distinctTest(): Unit = {
    var a = sc.parallelize(1 to 4, 2)
    println(a.collect().toBuffer)
    var b = sc.parallelize(3 to 6, 2)
    println(b.collect().toBuffer)
    println(a.union(b).collect().distinct.toBuffer)
  }

  def distinctTest1(): Unit = {
    var a = sc.parallelize(List("张三", "李四", "李四", "王五"), 2)
    println(a.distinct().collect().toBuffer)
  }
}
