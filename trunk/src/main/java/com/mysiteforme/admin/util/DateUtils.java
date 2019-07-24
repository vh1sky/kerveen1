package com.mysiteforme.admin.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Iwen
 * @date 2019/6/25 10:00
 * @Version 1.0
 */
public class DateUtils {

    //获得系统时间的日期
    public static String getDate() {
        Calendar now = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(now.getTimeInMillis());
        return date;
    }

    //获得系统时间的日期
    public static String getDateDay() {
        Calendar now = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String date = sdf.format(now.getTimeInMillis());
        return date;
    }

    //将long转换为日期string
    public static String longToDateStr(long ldate) {
        Date date = new Date(ldate);
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sd.format(date);
    }

    //将Date类型转换为String
    public static String dateToString(Date date) {
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sd.format(date);
    }
}
