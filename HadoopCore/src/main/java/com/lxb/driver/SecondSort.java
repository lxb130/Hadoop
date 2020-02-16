package com.lxb.driver;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;


public class SecondSort {

	// 将之前的<key,value>封装成new key结构 ,即<<key,value>,value>;
	// 自定义partition，使用newkey中的key分区
	// 自定义分组，使相同newkey中的key的计数到同一组中去

	// NewKeyValuePair---对newkey的封装,实现WritetableComparable
	// SelfHashPartition--extends Partitioner 重写getPartiton
	// SelfGroupComparator 实现相同的new key中的key流式分配到同一组中去

	public static class KeyPairWritable implements
			WritableComparable<KeyPairWritable> {

		private String key1;
		private int key2;

		public KeyPairWritable() {

		}

		public KeyPairWritable(String key1, int key2) {
			this.set(key1, key2);
		}

		public void set(String key1, int key2) {
			this.key1 = key1;
			this.key2 = key2;
		}

		public String getKey1() {
			return key1;
		}

		public void setKey1(String key1) {
			this.key1 = key1;
		}

		public int getKey2() {
			return key2;
		}

		public void setKey2(int key2) {
			this.key2 = key2;
		}

		@Override
		public void write(DataOutput out) throws IOException {
			out.writeUTF(key1);
			out.writeInt(key2);
		}

		@Override
		public void readFields(DataInput in) throws IOException {
			this.key1 = in.readUTF();
			this.key2 = in.readInt();
		}

		@Override
		public int compareTo(KeyPairWritable o) {
			int compare = this.key1.compareToIgnoreCase(o.key1);
			if (compare != 0) {
				return compare;
			} else {
				return Integer.valueOf(o.key2).compareTo(
						Integer.valueOf(this.getKey2()));
			}

		}
	}

	public static class LineProcessMap extends
			Mapper<Object, Text, KeyPairWritable, IntWritable> {
		private static KeyPairWritable outPutKey = new KeyPairWritable();
		private static IntWritable outPutVal = new IntWritable();

		public void map(Object key, Text value, Context context)
				throws IOException, InterruptedException {
			String tempLine = value.toString();
			if (tempLine != null && tempLine.trim().length() != 0) {
				String[] columeArray = tempLine.split("\\s");
				outPutKey.set(columeArray[0], Integer.parseInt(columeArray[1]));
				outPutVal.set(Integer.parseInt(columeArray[1]));
				context.write(outPutKey, outPutVal);
			}
		}
	}

	public static class SecondPartiton extends
			Partitioner<KeyPairWritable, IntWritable> {

		@Override
		public int getPartition(KeyPairWritable key, IntWritable value,
				int numPartitions) {

			return (key.getKey1().hashCode() & Integer.MAX_VALUE)
					% numPartitions;
		}

	}

	public static class SecondSortGroupCompartor extends WritableComparator {
		protected SecondSortGroupCompartor() {
			super(KeyPairWritable.class, true);
		}

		public int compare(WritableComparable first, WritableComparable second) {

			if (first == null || second == null) {
				return 0;
			}
			KeyPairWritable newkey1 = (KeyPairWritable) first;
			KeyPairWritable newkey2 = (KeyPairWritable) second;
			return newkey1.getKey1().compareTo(newkey2.getKey1());

		}
	}

	public static class SortReduce extends
			Reducer<KeyPairWritable, IntWritable, Text, IntWritable> {
		private Text outputKey = new Text();

		public void reduce(KeyPairWritable keypair,
				Iterable<IntWritable> values, Context context)
				throws IOException, InterruptedException {
			outputKey.set(keypair.getKey1());
			for (IntWritable val : values) {
				context.write(outputKey, val);
			}
		}
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		// 得到集群配置参数
        Configuration conf = new Configuration();
        // 参数解析器
        GenericOptionsParser optionParser = new GenericOptionsParser(conf, args);
        String[] remainingArgs = optionParser.getRemainingArgs();
        if ((remainingArgs.length != 2)) {
            System.err
                    .println("Usage: yarn jar jar_path main_class_path -D参数列表 <in> <out>");
            System.exit(2);
        }
        // 设置到本次的job实例中
        Job job = Job.getInstance(conf, "天亮二次排序(标准版)");
        // 指定本次执行的主类是WordCount
        job.setJarByClass(SecondSort.class);
        // 指定map类
        job.setMapperClass(LineProcessMap.class);
        // 指定partition类
        job.setPartitionerClass(SecondPartiton.class);
        job.setGroupingComparatorClass(SecondSortGroupCompartor.class);
        // 指定reducer类
        job.setReducerClass(SortReduce.class);
        // 指定job输出的key和value的类型,如果map和reduce输出类型不完全相同，需要重新设置map的output的key和value的class类型
        job.setMapOutputKeyClass(KeyPairWritable.class);
        job.setMapOutputValueClass(IntWritable.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        // 指定输入数据的路径
        FileInputFormat.addInputPath(job, new Path(remainingArgs[0]));
        // 指定输出路径,并要求该输出路径一定是不存在的
        FileOutputFormat.setOutputPath(job, new Path(remainingArgs[1]));
        // 指定job执行模式，等待任务执行完成后，提交任务的客户端才会退出!
        System.exit(job.waitForCompletion(true) ? 0 : 1);
	}

}
