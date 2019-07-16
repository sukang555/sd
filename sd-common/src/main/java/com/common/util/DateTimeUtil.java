package com.common.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.*;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

/**
 * @author sukang
 */
public class DateTimeUtil {
	
	
	public static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";


	public static  String transDateToStr(Date date,String format){
		return DateTimeFormatter
				.ofPattern(format)
				.withZone(ZoneId.systemDefault())
				.format(date.toInstant());
	}

	public static  String transDateToStr(Date date){
		return DateTimeFormatter
				.ofPattern(DEFAULT_FORMAT)
				.withZone(ZoneId.systemDefault())
				.format(date.toInstant());
	}

	
	
	public static String currentDate(String format){
		return DateTimeFormatter
				.ofPattern(StringUtils.isBlank(format) ? DEFAULT_FORMAT : format)
				.withZone(ZoneId.systemDefault())
				.format(LocalDateTime.now());
	}

    /**
     * 获取某日的开始时间  2019-06-21 00:00:00
     */
    public static Date getDayStartTime(LocalDate localDate){
        return Date.from(localDate.atStartOfDay(
                ZoneId.systemDefault()).toInstant());
    }


    /**
     * 获取某日的结束时间  2019-06-21 23:59:59
     */
    public static Date getDayEndTime(LocalDate localDate){
        return Date.from(localDate.atTime(
                23,59,59).atZone(
                ZoneId.systemDefault()).toInstant());
    }


    /**
     * 获取传入日期的所在月份的最后一天
     */
    public static LocalDate getEnDayOfMonth(Temporal localDate){
        return (LocalDate) localDate.with(TemporalAdjusters.lastDayOfMonth());
    }


    /**
     * 获取传入日期的所在周的最后一天
     */
    public static LocalDate getEndDayOfWeek(LocalDate localDate){

        if (localDate.getDayOfWeek().getValue() == 7){
            return localDate;
        }

        TemporalField temporalField = WeekFields.of(Locale.CHINA).dayOfWeek();
        return localDate.with(temporalField, 7)
                .plus(1, ChronoUnit.DAYS);
    }



    public static LocalDate transDateToLocalDate(Date date){
        ZonedDateTime zonedDateTime = date.toInstant().atZone(ZoneId.systemDefault());
        return LocalDate.from(zonedDateTime);
    }

    /**
     * 获取两个时间之间的自然月的数量
     * @param from 开始日期
     * @param to 结束日期
     * @return 数量(紧挨的两个月 返回为0) 同一个月返回为 -1;
     */
    public static long naturalMonths(LocalDate from, LocalDate to){

        LocalDate withFrom = from.with(TemporalAdjusters.lastDayOfMonth());
        LocalDate withTo = to.with(TemporalAdjusters.lastDayOfMonth());
        return ChronoUnit.MONTHS.between(withFrom,withTo) -1;
    }

    /**
     * 获取两个时间之间的自然天的数量
     * @param from 开始日期
     * @param to 结束日期
     * @return 数量(紧挨的两天 返回为0 ) 同一日返回-1
     */
    public static long naturalDays(LocalDate from, LocalDate to){


        return ChronoUnit.DAYS.between(from,to) - 1;
    }



    /**
     * @param fromDate 开始日期
     * @param toDate  结束日期
     * @return  两个日期之间的自然周的数量
     * 同一周返回为-1  紧挨着两个周的结果为0
     */
    public static long naturalWeeks(LocalDate fromDate,LocalDate toDate){


        //如果开始时间为周日则减去一天再操作

        if (fromDate.getDayOfWeek().getValue() == 7){
            fromDate = fromDate.plusDays(-1);
        }

        //开始日期所在周的最后一天+1
        LocalDate from = getEndDayOfWeek(fromDate).plusDays(1);
        TemporalField temporalField = WeekFields.of(Locale.CHINA).dayOfWeek();

        //如果结束时间为周日则减去一天在操作
        if (toDate.getDayOfWeek().getValue() == 7){
            toDate = toDate.plusDays(-1);
        }

        //结束日期所在的周的第一天
        LocalDate to = toDate.with(temporalField, 1).plusDays(1);
        return  ChronoUnit.WEEKS.between(from, to);
    }
	
}
