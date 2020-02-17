package com.lxb.udaf;

import org.apache.hadoop.hive.ql.exec.UDAF;
import org.apache.hadoop.hive.ql.exec.UDAFEvaluator;

public class DIYMinUdaf extends UDAF {
	public static class MyEvalutor implements UDAFEvaluator {
		public int MinValue = Integer.MIN_VALUE;

		@Override
		public void init() {
			MinValue = Integer.MIN_VALUE;;
		}

		public boolean iterate(int score) {
			if (score < MinValue) {
				MinValue = score;
			}
			return true;
		}

		public int terminatePartial() {
			return MinValue;
		}

		public boolean merge(int terminateMinVal) {
			if (terminateMinVal < MinValue) {
				MinValue = terminateMinVal;
			}
			return true;
		}

		public int terminate() {
			return MinValue;
		}
	}
}
