package com.cubrid.common.core.util;

public class ThreadUtil {
	public static void sleep(long milisecond) {
		try {
			Thread.sleep(milisecond);
		} catch (Exception ignored) {
		}
	}
}
