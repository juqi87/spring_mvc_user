package com.lw.fsx.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间、日期辅助类。
 * @author 
 * @version 1.0
 */
public class DateUtil {
	
	/**
	 * 得到某一时刻的前一天的日期，格式yyyy-MM-dd。
	 * @param date 某一时刻
	 * @return String，如果传入的参数date为null，则直接返回null。
	 */
    public String getBefferDay(Date date){
    	if(date==null){
    		return null;
    	}
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date);
    	cal.add(Calendar.DATE,   -1);
    	String yesterday = new SimpleDateFormat( "yyyy-MM-dd").format(cal.getTime());
    	return yesterday;
    }
	
	/**
     * 得到当前所在自然月的第一天的开始,格式为长日期格式。例如：2012-03-01 00:00:00。
     * @return String
     */
    public String getActualMinimumStart(){
    	Calendar   c   =   Calendar.getInstance();
    	return formatDate(new Date(), "yyyy-M-")+c.getActualMinimum(Calendar.DATE)+" 00:00:00";
    }
	
	/**
	 * 根据日期得到星期几,得到数字。<br/>
	 * 7, 1, 2, 3, 4, 5, 6
	 * @param date 时间
	 * @return Integer 如：6,如果传入的参数date为null，则直接返回null。
	 */
	public Integer getWeekDayInt(Date date) {
		if(date==null){
			return null;
		}
		Integer dayNames[] = { 7, 1, 2, 3, 4, 5, 6 };
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayOfWeek < 0)
			dayOfWeek = 0;
		return dayNames[dayOfWeek];
	}
	
	/**
	 * 根据时间得到星期几。<br/>
	 * "星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"
	 * @param date 时间
	 * @return String 如：星期一，如果传入的参数date为null，则直接返回null。
	 */
	public String getWeekDay(Date date) {
		if(date==null){
			return null;
		}
		String dayNames[] = { "星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日" };
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayOfWeek < 0)
			dayOfWeek = 0;
		return dayNames[dayOfWeek];
	}
	
	/**
	 * 得到从开始时间到结束时间之间的秒数。
	 * @param startdate 开始时间
	 * @param enddate 结束时间
	 * @return int，开始时间和结束时间任何一个为null时，直接返回0。
	 */
	public int getSeconds(Date startdate, Date enddate) {
		if(startdate==null || enddate==null){
			return 0;
		}
		long time = enddate.getTime() - startdate.getTime();
		int totalS = new Long(time / 1000).intValue();
		return totalS;
	}
	
	/**
	 * 得到某个时间几天之后或者之前的时间。
	 * @param date 某个时间。
	 * @param days 几天，为正值表示之后，负值表示之前。
	 * @return Date 如果传入的参数date为null，则直接返回null。
	 */
	public Date getDateOverDays(Date date, int days) {
		if(date==null){
			return null ;
		}
		Date d2 = new Date();
		d2.setTime(date.getTime() + days * 24 * 60 * 60 * 1000);
		return d2;
	}
	
	/**
	 * 得到几年以后的此刻时间,例如你可以得到2年后的此时此刻的时间值。
	 * @param years 几年
	 * @return Date 
	 */
	public Date getAfterDateByYears(int years) {
		Calendar calValue = Calendar.getInstance();
		calValue.add(Calendar.YEAR, years);
		return new Date(calValue.getTime().getTime());
	}
	
	/**
	 * 得到某天的第一秒的时间。
	 * @param date	某天的某个时间
	 * @return Date 如果传入的参数date为null，则直接返回null。
	 */
	public Date getDayStart(Date date) {
		if (date == null) {
			return null;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return new Date(c.getTimeInMillis());

	}
	
	/**
	 * 得到某天的最后一秒的时间。
	 * @param date	某天的某个时间
	 * @return Date 如果传入的参数date为null，则直接返回null。
	 */
	public Date getDayEnd(Date date) {
		if (date == null) {
			return null;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		return new Date(c.getTimeInMillis());
	}
	
	/**
	 * 将yyyy-MM-dd HH:mm:ss字符串转换成日期<br/> 
	 * @param dateStr yyyy-MM-dd HH:mm:ss字符串
	 * @return Date 如果dateStr为null或者为""，直接返回null。
	 * @throws ParseException  字符串转换日期失败
	 */
	public Date toDate(String dateStr) throws ParseException {
		if (dateStr == null || "".equals(dateStr)) {
			return null;
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date d = new Date();
			d = sdf.parse(dateStr);
			return new Date(d.getTime());
		}
	}
	
	/**
	 * 将日期转换成长日期字符串 例如：2009-09-09 01:01:01
	 * @param date 源日期
	 * @return String 如果传入的参数date为null，则直接返回null。
	 */
	public String toLongDate(Date date) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return (null == date) ? null : df.format(date);
	}
	
	public DateUtil(){
	}
	
	/**
	 * 将日期转换成短日期字符串,例如：2009-09-09。
	 * @param date 日期
	 * @return String 如果传入的参数date为null，则直接返回null。
	 */
	public String toShortDate(Date date) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return (null == date) ? null : df.format(date);
	}
	
	/**
	 * 得到某个时间的时间戳yyyyMMddHHmmss。
	 * @param date 时间
	 * @return String 如果传入的参数date为null，则直接返回null。
	 */
	public String toTimeStamp(Date date) {
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		return (null == date) ? null : df.format(date);
	}
	
	/**
	 * 将日期按照一定的格式进行格式化为字符串。<br/>
	 * 例如想将时间格式化为2012-03-05 12:56 ,则只需要传入formate为yyyy-MM-dd HH:mm即可。
	 * @param date 日期
	 * @param formate 格式化格式，如：yyyy-MM-dd HH:mm
	 * @return String 格式后的日期字符串。如果传入的参数date为null，则直接返回null。
	 */
	public String formatDate(Date date, String formate) {
		DateFormat df = new SimpleDateFormat(formate);
		return (null == date) ? null : df.format(date);
	}
}
