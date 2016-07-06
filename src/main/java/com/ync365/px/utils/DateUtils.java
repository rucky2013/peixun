package com.ync365.px.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	
	static SimpleDateFormat dateFormatMonth = new SimpleDateFormat("yyyy-MM");
	static SimpleDateFormat dateFormatMonthWithoutYear = new SimpleDateFormat("MM");
	static SimpleDateFormat dateFormatYear = new SimpleDateFormat("yyyy");
	
	/**
	 * 得到yyyy-MM 格式日期
	 * @param dataMonth
	 * @return
	 */
	public static String getDateMonthString(Date dataMonth){
		if (null == dataMonth) {
			return "";
		}
		
		return dateFormatMonth.format(dataMonth);
	}
	
	/**
	 * 得到yyyy 格式日期(当前年)
	 * @param dataMonth
	 * @return
	 */
	public static String getDatYearString(Date dateYear){
		if (null == dateYear) {
			return "";
		}
		
		return dateFormatYear.format(dateYear);
	}
	
	
	/**
	 * 得到MM 格式日期(当前月份)
	 * @param dataMonth
	 * @return
	 */
	public static String getDateMonthWithoutYearString(Date dateYear){
		if (null == dateYear) {
			return "";
		}
		
		return dateFormatMonthWithoutYear.format(dateYear);
	}
	
	
}
