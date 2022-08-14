/**
 * Copyright:  北京互融时代软件有限公司
 * @author:    Wu Shuiming
 * @version:   V1.0 
 * @Date:      2016年9月5日 下午3:22:58
 */
package hry.calculate.util;

import org.apache.log4j.Logger;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author Wu shuiming
 * @date 2016年9月5日 下午3:22:58
 */
public class DateUtil {
	private static Logger logger = Logger.getLogger(DateUtil.class);
	/**
	 * 
	 * 将String类型 转成 java.Sql 类型 
	 * 
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年9月5日 下午3:30:06
	 */
	public static Date getDateToString(String date){
		
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date sqlDate = new Date(sim.parse(date).getTime());
			return sqlDate;
		} catch (ParseException e) {
			e.printStackTrace();
			Date sqlDate2 = new Date(new java.util.Date().getTime());
			return sqlDate2;
		}
		
	}
	
	/**
	 * 将当前时间转成字符串类型   
	 * 
	 * @return
	 */
	public static String getNowDate(){
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
		String newDate = sim.format(new java.util.Date().getTime());
		return newDate ;
		
	}

	public static long getLongNowDate(){
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
		String newDate = sim.format(new java.util.Date().getTime());
		java.util.Date date2 = null;
		try {
			date2 = sim.parse(newDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date2.getTime();

	}
	/**
	 * 时间类型的字符串转换成date 
	 * 
	 * @param date
	 * @return
	 */
	public static java.util.Date StringToDate(String date){
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
		try {
			java.util.Date date2 = sim.parse(date);
			return date2;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 时间格式的字符你串转换成时间类型的字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String StringDateToString(String date){
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
		try {
			java.util.Date date2 = sim.parse(date);
			String format = sim.format(date2);
			return format;
		} catch (ParseException e) {
			e.printStackTrace();
			return getNowDate();
		}
	}
	
	
	
	public static void main(String[] args) {
		System.out.println(isCheackTime("09:36:33","09:39:50"));
	}

	/**
	 * 是否在两个日期之间
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static   boolean isDateInterval(String startTime,String endTime){
		long now=System.currentTimeMillis();
		long  str=  hry.util.date.DateUtil.stringToDate(startTime).getTime();
		long  end= hry.util.date.DateUtil.stringToDate(endTime).getTime();
		if(now<str){
			return  false;
		}if(now>end){
			return  false;
		}
		return  true;
	}

	public static boolean isCheackTime(String startTime,String endTime){
		SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(sdf.parse(startTime));
			Long star=	cal.getTimeInMillis();//开始时间
			cal.setTime(sdf.parse(endTime));
			Long end=	cal.getTimeInMillis();//结束时间
			cal.setTime(sdf.parse(sdf.format(new java.util.Date())));
			Long now =cal.getTimeInMillis();//当前时间
			if(star<now&&now<end){
				return true;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return  false;
	}

	public static int daysBetween(java.util.Date smdate, java.util.Date bdate) throws ParseException
	{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		smdate=sdf.parse(sdf.format(smdate));
		bdate=sdf.parse(sdf.format(bdate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days=(time2-time1)/(1000*3600*24);
		return Integer.parseInt(String.valueOf(between_days));
	}


}
