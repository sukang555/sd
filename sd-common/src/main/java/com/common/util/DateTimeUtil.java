package com.common.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * @author sukang
 */
public class DateTimeUtil {
	
	
	private static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";


	public static  String transDateToStr(Date date,String format){
		return DateTimeFormatter.ofPattern(format).withZone(ZoneId.systemDefault()).format(date.toInstant());
	}

	public static  String transDateToStr(Date date){
		return DateTimeFormatter.ofPattern(DEFAULT_FORMAT).withZone(ZoneId.systemDefault())
				.format(date.toInstant());
	}

	
	
	public static String currentDate(String format){
		format = StringUtils.isBlank(format) ? DEFAULT_FORMAT : format;
		return DateTimeFormatter.ofPattern(format).withZone(ZoneId.systemDefault())
				.format(LocalDateTime.now());
	}
	
}
