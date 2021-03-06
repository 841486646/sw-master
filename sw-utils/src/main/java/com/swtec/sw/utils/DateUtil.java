package com.swtec.sw.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.swtec.sw.utils.enums.RespCode;
import com.swtec.sw.utils.exception.BizException;

/**
 * 
 * @author 时间公共类
 *	chengkang
 */
public class DateUtil {
	 /** 日期时间样式:yyyyMMddHHmmss. */
    public static final String DT_FMT_1 = "yyyyMMddHHmmss";
    /** 日期时间样式:MMdd. */
    public static final String DT_FMT_2 = "MMdd";
    /** 日期时间样式:yyyyMMdd. */
    public static final String DT_FMT_3 = "yyyyMMdd";
    /** 日期时间样式:HHmmss. */
    public static final String DT_FMT_4 = "HHmmss";
    /** 日期时间样式:yyyy-MM-dd HH:mm:ss. */
    public static final String DT_FMT_5 = "yyyy-MM-dd HH:mm:ss";
    /** 日期时间样式:yyyy-MM-dd. */
    public static final String DT_FMT_6 = "yyyy-MM-dd";
    /** 日期时间样式:HH:mm:ss. */
    public static final String DT_FMT_7 = "HH:mm:ss";
    /** 日期时间样式:HHmm. */
    public static final String DT_FMT_8 = "HHmm";
    /** 日期时间样式:HH:mm. */
    public static final String DT_FMT_9 = "HH:mm";
    /** 日期时间样式:yyyyMMddHHmm. */
    public static final String DT_FMT_10 = "yyyyMMddHHmm";
    /** 日期时间样式:yyyy-MM-dd HH:mm. */
    public static final String DT_FMT_11 = "yyyy-MM-dd HH:mm";

    /**
     * 日期时间对象转换成日期时间字符串.
     * @param dt 日期时间对象，为空则默认为当前日期时间
     * @param fmt 转换格式，为空则默认为“yyyyMMddHHmmss”
     * @return 日期时间字符串
     */
    public static String dt2Str(Date dt, String fmt)
    {
        String usedFmt = MyStringUtil.isBlank(fmt) ? "yyyyMMddHHmmss" : fmt;
        Date fmtDt = (null == dt ? new Date() : dt);
        SimpleDateFormat sdf = new SimpleDateFormat(usedFmt);
        return sdf.format(fmtDt);
    }

    /**
     * 日期时间字符串转换成日期时间对象.
     * @param dtStr 日期时间字符串，不能为空
     * @param fmt 格式字符串，不能为空
     * @return 日期时间对象，参数为空或转换异常时返回null
     */
    public static Date str2Dt(String dtStr, String fmt)
    {
        if (MyStringUtil.isBlank(dtStr) || MyStringUtil.isBlank(fmt))
        {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(fmt);
        try
        {
            return sdf.parse(dtStr);
        }
        catch (ParseException e)
        {
            return null;
        }
    }

    /**
     * 判断两个日期相隔天数.
     * @param from 起始日期
     * @param to 结束日期
     * @return 相隔的天数(无论from和to谁前谁后,计算后取绝对值)
     */
    public static long daysBetween(Date from, Date to)
    {
        String fd = dt2Str(from, DT_FMT_3);
        String td = dt2Str(to, DT_FMT_3);
        Date fromStart = str2Dt(fd + "000000", DT_FMT_1);
        Date toStart = str2Dt(td + "000000", DT_FMT_1);
        long fromStartTime = (null == fromStart ? System.currentTimeMillis() : fromStart.getTime());
        long toStartTime = (null == toStart ? System.currentTimeMillis() : toStart.getTime());
        long between = Math.abs((toStartTime - fromStartTime) / (1000 * 60 * 60 * 24));
        return between;
    }

    /**
     * 获得某天前后几天的日期。
     * 例如:2013/05/10，两天后是2013/05/12，两天前是2013/05/08
     * @param from 日期基准
     * @param day 计算天数(正数是from以后|day|天的日期，负数是from以前|day|天的日期)
     * @return form + day后的日期
     */
    public static Date afterNDays(Date from, int day)
    {
        Calendar now = Calendar.getInstance();
        now.setTime(from);
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
        return now.getTime();
    }

    /**
     * 获得某天的开始时间.
     * <br>
     * 例如:2013/05/10 其最后时间是 2013/05/10 00:00:00
     * <br>
     * @param day 日期
     * @return 某天的开始时间
     */
    public static Date dayStart(Date day)
    {
        if (null == day)
        {
            return null;
        }
        String dayStr = dt2Str(day, DT_FMT_3) + "000000";
        return str2Dt(dayStr, DT_FMT_1);
    }

    /**
     * 获得某天的最后时间.
     * <br>
     * 例如:2013/05/10 其最后时间是 2013/05/10 23:59:59
     * <br>
     * @param day 日期
     * @return 某天的最后时间
     */
    public static Date dayEnd(Date day)
    {
        if (null == day)
        {
            return null;
        }
        String dayStr = dt2Str(day, DT_FMT_3) + "235959";
        return str2Dt(dayStr, DT_FMT_1);
    }

    /**
     * 获得下一天的开始时间.
     * <br>
     * 例如:2013/05/10 其下一天的开始时间是 2013/05/11 00:00:00
     * <br>
     * @param day 日期基准
     * @return 下一天的开始时间
     */
    public static Date nextDayStart(Date day)
    {
        if (null == day)
        {
            return null;
        }
        Date nextDay = afterNDays(day, 1);
        String nextDayStr = dt2Str(nextDay, DT_FMT_3) + "000000";
        return str2Dt(nextDayStr, DT_FMT_1);
    }

    /**
     * 获得某天前后几天的日期。
     * 例如:2013/05/10，两天后是2013/05/12，两天前是2013/05/08
     * @param from 日期基准字符串
     * @param fmt 日期格式
     * @param day 计算天数(正数是from以后|day|天的日期，负数是from以前|day|天的日期)
     * @return form + day后的日期
     */
    public static String afterNDays(String from, String fmt, int day)
    {
        Date fromDate = str2Dt(from, fmt);
        if (null == fromDate)
        {
            return null;
        }
        Calendar now = Calendar.getInstance();
        now.setTime(fromDate);
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
        Date rsltDate = now.getTime();
        return dt2Str(rsltDate, fmt);
    }

    /**
     * 获得某时间若干分钟后的日期时间.
     * @param from 日期时间基准
     * @param min 计算分钟
     * @return from + min后的日期时间
     */
    public static Date afterNMin(Date from, int min)
    {
        Calendar now = Calendar.getInstance();
        now.setTime(from);
        now.set(Calendar.MINUTE, now.get(Calendar.MINUTE) + min);
        return now.getTime();
    }

    /**
     * 获得某日期的星期信息.
     * @param date 日期
     * @return 星期(星期日~星期六分别对应0~6)
     */
    public static int getWeekOfDate(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // 获得星期数是指一周中的第几天，由于是以星期日作为一周的第一天，因此需要-1得到正确的星期
        // 星期日为一周的第一天         SUN MON TUE WED THU FRI SAT 
        // DAY_OF_WEEK返回      1   2   3   4   5   6   7 
        // 星期一为一周的第一天          MON TUE WED THU FRI SAT SUN 
        // DAY_OF_WEEK返回      1   2   3   4   5   6   7 
        int intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        return intWeek;
    }

    /**
     * 获得某日期的星期信息字符串.
     * @param date 日期
     * @return 星期
     */
    public static String getWeekStrOfDate(Date date)
    {
        String[] weekDaysName = { "周日", "周一", "周二", "周三", "周四", "周五", "周六" };
        int week = getWeekOfDate(date);
        return weekDaysName[week];
    }

    /**
     * 获得某日期的星期信息字符串.
     * @param week 日期(星期日~星期六分别对应0~6)
     * @return 星期
     */
    public static String getWeekStrOfDate(int week)
    {
        String[] weekDaysName = { "周日", "周一", "周二", "周三", "周四", "周五", "周六" };
        return weekDaysName[week];
    }

    /**
     * 获得指定日期下周一的日期.
     * @param date 指定日期
     * @return 指定日期下周一的日期
     */
    public static Date getNextMonday(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int week = calendar.get(Calendar.DAY_OF_WEEK);
        if (week != Calendar.SUNDAY)
        {
            calendar.add(Calendar.WEEK_OF_MONTH, 1);
        }
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return calendar.getTime();
    }

    /**
     * 根据生日计算年龄.
     * @param birth 生日
     * @return 年龄，异常情况返回-1
     */
    public static Integer calAge(Date birth)
    {
        // 当前日期
        Calendar cal = Calendar.getInstance();
        if (null == birth || cal.before(birth))
        {
            return -1;
        }
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        // 生日
        cal.setTime(birth);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

        int age = yearNow - yearBirth;
        // 小于生日月时，年龄-1
        if (monthNow < monthBirth)
        {
            age--;
        }
        // 生日月一致时，判断生日日
        else if (monthNow == monthBirth)
        {
            // 小于生日日时，生日-1
            if (dayOfMonthNow < dayOfMonthBirth)
            {
                age--;
            }
        }
        else
        {
            // do nothing
        }
        return age;
    }
	/**
	 * 获取当前时间
	 * @return NowTime
	 */
	public static String nowDateTime(){
		Date date=new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowTime=format.format(date);
		return nowTime;
	}
	/**
	 * yyyy-MM-dd
	 * @return NowTime
	 */
	public static String nowDate(){
		Date date=new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		String nowTime=format.format(date);
		return nowTime;
	}
	/**
	 * String 类型时间转换成Date类型
	 * @param date
	 * @return
	 */
	public static Date dateTime(String date){
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date dateTime=null;
		try {
			 dateTime=sim.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new BizException(RespCode.DATE_CONVERSION_ERROR);
		}
		return dateTime;
	}
	public static Date stringDate(String date){
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
		Date dateTime=null;
		try {
			 dateTime=sim.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new BizException(RespCode.DATE_CONVERSION_ERROR);
		}
		return dateTime;
	}

}
