package cus.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	/*
	 * 获取N分钟后的日期
	 */
	public static Date getDateAfterNMins(int mins) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(new Date().getTime());
		c.add(Calendar.MINUTE, mins);// N分钟后的日期
		return new Date(c.getTimeInMillis());
	}

	/*
	 * 获取N天后的日期
	 */
	public static Date getDateAfterNDays(int days) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(new Date().getTime());
		c.add(Calendar.DATE, days);// N天后的日期
		return new Date(c.getTimeInMillis());
	}

	/*
	 * 获取日期
	 */
	public static Date getDateFromString(String dateString) {
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			date = sdf.parse(dateString);
		} catch (ParseException e) {
			date = new Date();
		}
		return date;
	}

	/*
	 * 获取时间字符串
	 */
	public static String getCurrentTime() {
		return getCurrentTime(null);
	}
	
	/*
	 * 获取时间字符串
	 */
	public static String getCurrentTime(String format) {
		if(format == null || "".equals(format)){
			format = "yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date = new Date();
		return sdf.format(date);
	}
	
	/*
	 * 获取当天0点
	 */
	public static Date getTodayZero() {
		Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 获得当前日期的一天
	 */
	public static Date getYesterday() {
		Date myDate = new Date();
		long myTime = (myDate.getTime() / 1000) - 60 * 60 * 24;
		myDate.setTime(myTime * 1000);
		return myDate;
	}
	
	public static String format(Date date, String dateFormat) {
		if (date == null)
			return null;
		return new SimpleDateFormat(dateFormat).format(date);
	}
	
	public static void main(String[] args) {
		System.out.println(getDateAfterNDays(2));
	}

}
