package com.ync365.px.utils;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public class ResultSetUtils {
	public static Long null2Long(Object obj){
		Long result = 0l;
		if (null == obj) {
			return result;
		}
		try {
			if (obj instanceof Long) {
				result = (Long) obj;
			} else {
				if (String.valueOf(obj).equals("null")) {
					return result;
				}
				result = Long.valueOf(String.valueOf(obj));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static Float null2Float(Object obj){
		Float result = 0.0f;
		if (null == obj) {
			return result;
		}
		
		try {
			if (obj instanceof Float) {
				result = (Float) obj;
			} else {
				if (String.valueOf(obj).equals("null")) {
					return result;
				}
				result = Float.valueOf(String.valueOf(obj));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public static Integer null2Integer(Object obj){
		Integer result = 0;
		if (null == obj) {
			return result;
		}
		
		try {
			if (obj instanceof Integer) {
				result = (Integer) obj;
			} else {
				if (String.valueOf(obj).equals("null")) {
					return result;
				}
				result = Integer.valueOf(String.valueOf(obj));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static Date null2Date(Object obj){
		Date result = null;
		
		try {
			result = (Date) obj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
