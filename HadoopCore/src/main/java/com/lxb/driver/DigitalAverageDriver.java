package com.lxb.driver;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class DigitalAverageDriver {

	public static class MyTokenizerMapper extends
			Mapper<Object, Text, Text, IntWritable> {
		// 暂存每个传过来的词频计数，均为1,省掉重复申请空间
		private final static IntWritable result = new IntWritable(1);
		// 暂存每个传过来的词的值，省掉重复申请空间
		private Text word = new Text();

		// 核心map方法的具体实现,逐个<key,value>对去处理
		public void map(Object key, Text value, Context context)
				throws IOException, InterruptedException {
			// 用每行的字符串值初始化StringTokenizer
			StringTokenizer itr = new StringTokenizer(value.toString(), ",");
			// 循环取得每个空白符分隔出来的每个元素
			while (itr.hasMoreTokens()) {
				String ele = itr.nextToken();
				// 将取得出的每个元素放到word Text对象中
				if (ele.toString().matches("\\d+")) {
					// 输出时候，key是固定值，value就是这个数值本身
					word.set("我是数值类型");
					result.set(Integer.parseInt(ele));
					// 通过context对象，将map的输出逐个输出
					context.write(word, result);
				}
			}
		}
	}

	public static class IntSumReducerV2 extends
			Reducer<Text, IntWritable, Text, Text> {
		private Text result = new Text();

		// 核心reduce方法的具体实现,逐个<key,List(v1,v2)>去处理
		public void reduce(Text key, Iterable<IntWritable> values,
				Context context) throws IOException, InterruptedException {
			// 暂存每个key组中计算总和
			int sum = 0;
			double average = 0;
			int counter = 0;
			// 加强型for,依次获取迭代器中的每个元素值,即为一个一个的词频数值
			for (IntWritable val : values) {
				// 将key组中的每个词频数值sum到一起
				sum += val.get();
				counter++;
			}

			average = 1.0 * sum / counter;
			String[] eleArray = ("" + average).split("\\.");
			String formatResult = eleArray[0] + "."
					+ eleArray[1].substring(0, 2);
			result.set(formatResult);
			context.write(key, result);
		}
	}

	// 启动mr的driver方法
	public static void main(String[] args) throws Exception {
		// 得到集群配置参数
		Configuration conf = new Configuration();
		// 设置到本次的job实例中
		Job job = Job.getInstance(conf, "天亮WordCount");
		// 指定本次执行的主类是WordCount
		job.setJarByClass(DigitalAverageDriver.class);
		// 指定map类
		job.setMapperClass(MyTokenizerMapper.class);
		// 指定combiner类，要么不指定，如果指定，一般与reducer类相同
		// job.setCombinerClass(IntSumReducer.class);
		// 指定reducer类
		job.setReducerClass(IntSumReducerV2.class);
		// 指定job输出的key和value的类型,如果map和reduce输出类型不完全相同，需要重新设置map的output的key和value的class类型
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		// 指定输入数据的路径
		FileInputFormat.addInputPath(job, new Path(args[0]));
		// 指定输出路径,并要求该输出路径一定是不存在的
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		// 指定job执行模式，等待任务执行完成后，提交任务的客户端才会退出!
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}
