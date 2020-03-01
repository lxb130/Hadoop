package com.lxb.collection

object List {
  def main(args: Array[String]): Unit = {
    val language = "java" :: ("python" :: ("c++" :: Nil))

    println("第一个语言是" + language.head)
    println("去除第一个语言后的列表" + language.tail)
    println("查看列表是否为空" + language.isEmpty)
  }
}
