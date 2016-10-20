package com.util;
/**
 * 时间工具类
 * @author 林丹
 *
 */
public class TimeUtil {
	/**
	 * 計算時間間隔
	 * @param startTime 開始時間
	 * @return 返回格式：00:00:00
	 */
	public static String getInterval(long startTime) {
		long nowTime = System.currentTimeMillis();
		long passTime = nowTime - startTime;
		int totalsecond = (int) passTime / 1000;
		int min = totalsecond / 60 % 60;
		int hour = totalsecond / 60 / 60 % 60;
		int second = totalsecond % 60;
		return String.format("%02d:%02d:%02d", hour,min,second);

	}
}
