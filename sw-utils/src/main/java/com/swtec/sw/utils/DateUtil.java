package com.swtec.sw.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.swtec.sw.utils.enums.RespCode;
import com.swtec.sw.utils.exception.BizException;

/**
 * 
 * @author 时间公共类
 *	chengkang
 */
public class DateUtil {
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
