package com.lxb;

import java.util.Arrays;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;

import scala.Tuple2;

/**
 * Hello world!
 *
 */
public class WordCount {

	public static void main(String[] args) {
		// spark参数对象化
		SparkConf conf = new SparkConf();
		// spark设置运行模式
		conf.setMaster("local[*]");
		conf.setAppName("wordCount");
		// 通过spark conf构建spark上下文对象
		JavaSparkContext sc = new JavaSparkContext(conf);
		// 利用textfile从文件系统中读入指定的文件返回一个rdd实例对象
		String path = null;
		if (args == null || args.length == 0) {
			path = "E:\\bigdata3\\Hadoop\\SparkCore\\sparkCore\\data\\data.txt";

		} else {
			path = args[0];
		}

		JavaRDD<String> lines = sc.textFile(path);

		// rdd的创建和初始化都是由sparkcontext来负责的，将内存中的集合或外部系统中的文件作为输入源
		// 将文件内容转化为一个rdd对象，内部元素为string类型，即一行一个string类型的集合

		// 对lines集合按空白符分割后，做flatmap操作，形成一个统一的单词rdd集合
		JavaRDD<String> words = lines
				.flatMap(new FlatMapFunction<String, String>() {
					private static final long serialVersionUID = 1L;

					@Override
					public Iterable<String> call(String s) throws Exception {

						return Arrays.asList(s.split("\\s+"));
					}
				});
		// 对像words集合k,v处理，形成<word,freq>结构，实际是<word,1>结构
		JavaPairRDD<String, Integer> ones = words
				.mapToPair(new PairFunction<String, String, Integer>() {
					private static final long serialVersionUID = 1L;

					@Override
					public Tuple2<String, Integer> call(String s)
							throws Exception {
						return new Tuple2<String, Integer>(s, 1);
					}
				});
		// 对象ones集合reduceByKey操作,即为group by key操作，相同的key做freq加和操作
		JavaPairRDD<String, Integer> counts = ones
				.reduceByKey(new Function2<Integer, Integer, Integer>() {
					private static final long serialVersionUID = 1L;

					@Override
					public Integer call(Integer i1, Integer i2)
							throws Exception {
						// TODO Auto-generated method stub
						return i1 + i2;
					}
				});
		// 将counts结果rdd对象最终以ArrayList形式返回
		List<Tuple2<String, Integer>> output = counts.collect();
		for (Tuple2<String, Integer> tuple2 : output) {
			System.out.println(tuple2._1 + "\t" + tuple2._2);
		}
		// 该spark上下文对象的stop操作，达到停止该spark任务的作用
		sc.stop();
	}
}
