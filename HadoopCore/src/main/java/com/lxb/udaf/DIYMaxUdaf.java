package com.lxb.udaf;

import org.apache.hadoop.hive.ql.exec.UDAF;
import org.apache.hadoop.hive.ql.exec.UDAFEvaluator;

public class DIYMaxUdaf extends UDAF {

	public static class MyEvaluator implements UDAFEvaluator {

		public int MaxValue = -Integer.MAX_VALUE;

		public MyEvaluator() {
			this.init();
		}

		@Override
		public void init() {
			MaxValue = -Integer.MIN_VALUE;
			;
		}

		public boolean iterate(int score) {
			if (score > MaxValue) {
				MaxValue = score;
			}
			return true;
		}

		public int terminatePartial() {
			return MaxValue;
		}

		public boolean merge(int terminateMaxValue) {
			if (terminateMaxValue > MaxValue) {
				MaxValue = terminateMaxValue;
			}
			return false;
		}

		public int terminate() {
			return MaxValue;
		}
	}
}
