package com.lxb.collection

object IteratorTest {
  def main(args: Array[String]): Unit = {
    val it1 = Iterator("java", "python", "php", "c++")
    val it2 = Iterator("java", "python", "php", "c++")
    val it3 = Iterator("java", "python", "php", "c++")
    val it4 = Iterator("java", "python", "php", "c++")
    val it5 = Iterator("java", "python", "php", "c++")

    while (it1.hasNext) {
      println(it1.next())
    }
    println("it2的最大值为" + it2.max)
    println("it3的最小值为" + it3.min)
    println("it4的size为" + it4.size)
    println("it5的length为" + it4.length)
  }
}
