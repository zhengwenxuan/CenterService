package cn.hjw.common;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 日期处理
 */
public class DateUtil {

	private static SimpleDateFormat[] dateFormats = null;
	private static String formatDate = "yyyy-MM-dd";
	private static String formatDatet = "yyyyMMddHHmmss";
	private static String formatDatetime = "yyyy-MM-dd HH:mm:ss";
	private static String oracleFormatDate = "yyyy-MM-dd";
	private static String oracleFormatDatetime = "yyyy-MM-dd HH24:mi:ss";
	private static String oracleFormatWeek = "yyyy-WW";
	private static String oracleFormatMonth = "yyyy-MM";
	private static String oracleFormatYear = "yyyy";

	static {
		final String[] possibleDateFormats = {
				"MM/dd/yyyy HH:mm:ss a",
				"EEE, dd MMM yyyy HH:mm:ss zzz", // RFC_822
				"EEE, dd MMM yyyy HH:mm:ss z", // RFC_822
				"EEE, dd MMM yyyy HH:mm zzzz",
				"yyyy-MM-dd'T'HH:mm:ssZ",
				"yyyy-MM-dd'T'HH:mm:sszzzz",
				"yyyy-MM-dd'T'HH:mm:ss z",
				"yyyy-MM-dd'T'HH:mm:ssz", // ISO_8601
				"yyyy-MM-dd'T'HH:mm:ss", "yyyy-MM-dd'T'HHmmss.SSSz",
				"yyyy-MM-dd'T'HH:mm:ss", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd",
				"yyyy/MM/dd", "yyyy.MM.dd", "yyyy'年'MM'月'dd'日'",
				"EEE,dd MMM yyyy HH:mm:ss zzz", // 容错
				"EEE, dd MMM yyyy HH:mm:ss", // RFC_822
				"dd MMM yyyy HH:mm:ss zzz", // 容错
				"dd MM yyyy HH:mm:ss zzz", // 容错
				"EEE, dd MM yyyy HH:mm:ss", // RFC_822
				"dd MM yyyy HH:mm:ss", // 容错
				"EEE MMM dd HH:mm:ss zzz yyyy" // bokee 的时间格式 Tue Mar 28
												// 02:25:45 CST 2006
		};

		dateFormats = new SimpleDateFormat[possibleDateFormats.length];
		TimeZone gmtTZ = TimeZone.getTimeZone("GMT");
		Locale locale = Locale.US;
		for (int i = 0; i < possibleDateFormats.length; i++) {
			dateFormats[i] = new SimpleDateFormat(possibleDateFormats[i],
					locale);
			dateFormats[i].setTimeZone(gmtTZ);
		}
	}

	/**
	 * 获取字符串的日期格式
	 * 
	 * @param strdate
	 *            格式串
	 * @return 格式化的格式串
	 */
	public static Date getDate(String strdate) {
		if (strdate == null)
			return null;
		strdate = strdate.trim();
		if (strdate.length() > 10) {

			if ((strdate.substring(strdate.length() - 5).indexOf("+") == 0 || strdate
					.substring(strdate.length() - 5).indexOf("-") == 0)
					&& strdate.substring(strdate.length() - 5).indexOf(":") == 2) {

				String sign = strdate.substring(strdate.length() - 5,
						strdate.length() - 4);

				strdate = strdate.substring(0, strdate.length() - 5) + sign
						+ "0" + strdate.substring(strdate.length() - 4);
			}

			String dateEnd = strdate.substring(strdate.length() - 6);
			if ((dateEnd.indexOf("-") == 0 || dateEnd.indexOf("+") == 0)
					&& dateEnd.indexOf(":") == 3) {

				if ("GMT".equals(strdate.substring(strdate.length() - 9,
						strdate.length() - 6))) {

				} else {
					String oldDate = strdate;
					String newEnd = dateEnd.substring(0, 3)
							+ dateEnd.substring(4);
					strdate = oldDate.substring(0, oldDate.length() - 6)
							+ newEnd;

				}
			}
		}
		int i = 0;
		Date result = null;
		while (i < dateFormats.length) {
			try {
				result = dateFormats[i].parse(strdate);
				break;
			} catch (java.text.ParseException eA) {
				i++;
			}
		}
		return result;
	}

	/**
	 * 日期格式判断
	 * 
	 * @param sDate
	 *            传过来的date
	 * @return 布尔
	 */
	public static boolean isValidDate(String sDate) {
		String datePattern1 = "\\d{4}-\\d{2}-\\d{2}";
		String datePattern2 = "^((\\d{2}(([02468][048])|([13579][26]))"
				+ "[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|"
				+ "(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?"
				+ "((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?("
				+ "(((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?"
				+ "((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";
		if ((sDate != null)) {
			Pattern pattern = Pattern.compile(datePattern1);
			Matcher match = pattern.matcher(sDate);
			if (match.matches()) {
				pattern = Pattern.compile(datePattern2);
				match = pattern.matcher(sDate);
				return match.matches();
			} else {
				return false;
			}
		}
		return false;
	}

	/**
	 * 获得在指定时间上增加指定分钟数后的时间
	 * 
	 * @param date
	 *            指定时间
	 * @param minute
	 *            指定分钟数
	 * @return 返回新的实践
	 */
	public static Date addMinute(Date date, int minute) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, minute);
		return calendar.getTime();
	}

	/**
	 * 根据时间加或减天数
	 * 
	 * @param date
	 *            指定时间
	 * @param day
	 *            天数
	 * @return 添加day后的时间
	 */
	public static Date addDay(Date date, Integer day) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, day);
		return calendar.getTime();
	}

	/**
	 * 指定格式字符串转日期
	 * 
	 * @param s
	 *            String类型日期
	 * @param pattern
	 *            格式
	 * @return 格式化的日期
	 */
	public static Date formatStr(String s, String pattern) {
		SimpleDateFormat formatter = new SimpleDateFormat(pattern,
				java.util.Locale.US);
		formatter.setLenient(false);
		Date date = null;
		try {
			date = formatter.parse(s);
		} catch (Exception ex) {
			System.out.println("String " + s + " is error");
		}
		return date;
	}

	/**
	 * 指定格式日期转字符串
	 * 
	 * @param dDate
	 *            date类型日期
	 * @param pattern
	 *            格式
	 * @return String类型日期
	 */
	public static String formatDate(Date dDate, String pattern) {
		SimpleDateFormat formatter = new SimpleDateFormat(pattern,
				java.util.Locale.US);
		String dateString = null;
		try {
			if (dDate != null) {
				formatter.setLenient(false);
				dateString = formatter.format(dDate);
			}
		} catch (Exception ex) {
			System.out.println("Date" + dDate + "to String is error");
		}
		return dateString;
	}

	/**
	 * 获取当前是周几
	 * 
	 * @return 周几
	 */
	public static String getWeek() {
		Calendar cld = Calendar.getInstance();
		int week = cld.get(Calendar.DAY_OF_WEEK);
		switch (week) {
		case 1:
			return "日";
		case 2:
			return "一";
		case 3:
			return "二";
		case 4:
			return "三";
		case 5:
			return "四";
		case 6:
			return "五";
		case 7:
			return "六";
		}
		return null;
	}

	/**
	 * 获取本周周一的日期
	 * 
	 * @Title: getMONDAY
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return 参数说明
	 * @return String 返回类型
	 * @throws
	 */
	public static String getMONDAY() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); // 获取本周一的日期
		return formatDate(cal.getTime(), formatDate);
	}

	/**
	 * 获取本周日的日期
	 * 
	 * @Title: getSUNDAY
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return 参数说明
	 * @return String 返回类型
	 * @throws
	 */
	public static String getSUNDAY() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		cal.add(Calendar.WEEK_OF_YEAR, 1);
		return formatDate(cal.getTime(), formatDate);
	}

	/**
	 * 获取本月第一天的日期
	 * 
	 * @Title: getMONTHFirst
	 * @Description: TODO(获取本月第一天的日期)
	 * @param @return 参数说明
	 * @return String 返回类型
	 * @throws
	 */
	public static String getMONTHFirst() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 0);
		cal.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		return formatDate(cal.getTime(), formatDate);
	}

	/**
	 * 获取本月最后一天的日期
	 * 
	 * @Title: getMONTHLast
	 * @Description: TODO(获取本月最后一天的日期)
	 * @param @return 参数说明
	 * @return String 返回类型
	 * @throws
	 */
	public static String getMONTHLast() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH,
				cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		return formatDate(cal.getTime(), formatDate);
	}

	/**
	 * 日期转化为标准的日期字符串,格式:YYYY-MM-DD HH:MM:SS
	 * 
	 * @param date
	 *            date类型日期
	 * @return 格式化后的日期
	 */
	public static String datetimeToString(Date date) {
		return formatDate(date, DateUtil.getFormatDatetime());
	}

	/**
	 * 日期转字符串 日期格式:YYYY-MM-DD
	 * 
	 * @param date
	 *            date类型日期
	 * @return 格式化后的日期
	 */
	public static String dateToString(Date date) {
		return formatDate(date, DateUtil.getFormatDate());
	}

	public static String getFormatDate() {
		return formatDate;
	}

	public static void setFormatDate(String formatDate) {
		DateUtil.formatDate = formatDate;
	}

	public static String getFormatDatetime() {
		return formatDatetime;
	}

	public static void setFormatDatetime(String formatDatetime) {
		DateUtil.formatDatetime = formatDatetime;
	}

	public static String getOracleFormatDate() {
		return oracleFormatDate;
	}

	public static void setOracleFormatDate(String oracleFormatDate) {
		DateUtil.oracleFormatDate = oracleFormatDate;
	}

	public static String getOracleFormatDatetime() {
		return oracleFormatDatetime;
	}

	public static void setOracleFormatDatetime(String oracleFormatDatetime) {
		DateUtil.oracleFormatDatetime = oracleFormatDatetime;
	}

	public static String getOracleFormatWeek() {
		return oracleFormatWeek;
	}

	public static void setOracleFormatWeek(String oracleFormatWeek) {
		DateUtil.oracleFormatWeek = oracleFormatWeek;
	}

	public static String getOracleFormatYear() {
		return oracleFormatYear;
	}

	public static void setOracleFormatYear(String oracleFormatYear) {
		DateUtil.oracleFormatYear = oracleFormatYear;
	}

	public static String getOracleFormatMonth() {
		return oracleFormatMonth;
	}

	public static void setOracleFormatMonth(String oracleFormatMonth) {
		DateUtil.oracleFormatMonth = oracleFormatMonth;
	}

	/**
	 * <b>日期取得</b><br>
	 * <p>
	 * 取得系统当前日期时间
	 * </p>
	 * 
	 * @return Date型
	 */
	public static Timestamp getNow() {
		Date date = new Date();
		Timestamp stamp = new Timestamp(date.getTime());
		return stamp;
	}

	/**
	 * <b>日期取得</b><br>
	 * <p>
	 * 取得系统当前日期时间
	 * </p>
	 * 
	 * @return Date型
	 */
	public static Date getDate() {
		Date date = new Date();
		return date;
	}

	public static String getStamp() {
		Date date = new Date();
		return new SimpleDateFormat(formatDatet).format(date);
	}

	public static String getCurrentTime() {
		Date date = new Date();
		return new SimpleDateFormat(formatDatetime).format(date);
	}

	/**
	 * 获取两个时间之前的分钟差
	 * 
	 * @Title: getTimeMin
	 * @Description: TODO( 获取两个时间之前的分钟差)
	 * @param @param d1
	 * @param @param d2
	 * @param @return 参数说明
	 * @return long 返回类型
	 * @throws
	 */
	public static long getTimeMin(Date d1, Date d2) {
		Calendar dateOne = Calendar.getInstance(), dateTwo = Calendar
				.getInstance();
		dateOne.setTime(d1); // 设置为当前系统时间
		dateTwo.setTime(d2);
		long timeOne = dateOne.getTimeInMillis();
		long timeTwo = dateTwo.getTimeInMillis();
		long second = (timeTwo - timeOne) / 1000;
		long minute = second / 60;
		if(second%60>0){
			minute+=1;
		}
		return minute;
	}
	
	
	/**
	 * 获取两个时间之前的分钟差
	 * 
	 * @Title: getTimeMin
	 * @Description: TODO( 获取两个时间之前的分钟差)
	 * @param @param d1
	 * @param @param d2
	 * @param @return 参数说明
	 * @return long 返回类型
	 * @throws
	 */
	public static long getTimeMin(String d1, String d2) {
		Date date1 = formatStr(d1, formatDatetime);
		Date date2 = formatStr(d2, formatDatetime);
		return getTimeMin(date1,date2);
	}

	public static void main(String[] args) throws Exception {
		long timeOne = 1443164400000L;
		long timeTwo = 1443165793000L;
		System.out.println(((timeTwo - timeOne) / 1000-13)%60);
	}
	
	

}
