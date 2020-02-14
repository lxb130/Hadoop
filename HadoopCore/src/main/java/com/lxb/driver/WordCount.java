package com.lxb.driver;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import com.lxb.mapper.MyToKenizerMapper;
import com.lxb.reduce.IntSumReducer;

public class WordCount {

	public static void main(String[] args) throws Exception {
		// 得到集群配置参数
		Configuration conf = new Configuration();
		// 设置本次的任务实例
		Job job = Job.getInstance(conf, "WordCount实现");
		// 指定本次任务执行的主类
		job.setJarByClass(WordCount.class);
		// 指定mapper类
		job.setMapperClass(MyToKenizerMapper.class);
		// 指定combiner 要么不指定 若指定则指定为reduce类
		job.setCombinerClass(IntSumReducer.class);
		// 指定reduce类
		job.setReducerClass(IntSumReducer.class);
		// 指定输出的key数据类型
		job.setMapOutputKeyClass(Text.class);
		// 指定输出的value的数据类型
		job.setMapOutputValueClass(IntWritable.class);

		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}
