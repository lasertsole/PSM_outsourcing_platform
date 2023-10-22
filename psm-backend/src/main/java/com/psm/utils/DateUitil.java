package com.psm.utils;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DateUitil {
    public static String getByPattern(String pattern){//获取指定格式的日期和时间
        return new SimpleDateFormat(pattern).format(new Date());
    }
    public static String getByPattern(String pattern,Date date){
        return new SimpleDateFormat(pattern).format(date);
    }

    public static String getSystemDate(){//获取系统日期
        return getByPattern("yyyy-MM-dd");
    }

    public static String getSystemDate(Date date){
        return getByPattern("yyyy-MM-dd", date);
    }

    public static String getDefaultDate(){//获取中文日期
        return getByPattern("yyyy年MM月dd日");
    }

    public static String getDefaultDate(Date date){
        return getByPattern("yyyy年MM月dd日", date);
    }

    public static String getTime24(){//获取12小时制的时间
        return getByPattern("HH:mm:ss");
    }

    public static String getTime24(Date date){
        return getByPattern("HH:mm:ss", date);
    }

    public static String getSystemDateTime(){//获取系统日期和12小时制的时间
        return getByPattern("yyyy-MM-dd HH:mm:ss");
    }
    public static String getSystemDateTime(Date date){
        return getByPattern("yyyy-MM-dd HH:mm:ss", date);
    }

    public static String getDefaultDateTime(){//获取中文日期和12小时制的时间
        return getByPattern("yyyy年MM月dd日 HH:mm:ss");
    }

    public static String getDefaultDateTime(Date date){
        return getByPattern("yyyy年MM月dd日 HH:mm:ss", date);
    }

    public static String getWeekDay(){//获取星期几
        return getByPattern("E").replace("周", "星期");
    }

    public static String getWeekDay(Date date){
        return getByPattern("E", date).replace("周", "星期");
    }

    public static String getAllDateTime(){//获取全部时间
        return getByPattern("yyyy-MM-dd HH:mm:ss:SSS") + getWeekDay();
    }

    public static String getAllDateTime(Date date){
        return getByPattern("yyyy-MM-dd HH:mm:ss:SSS", date) + getWeekDay(date);
    }

    public static Date parse(String dateString){//将String转为默认格式的Date
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return simpleDateFormat.parse(dateString);
        }catch (ParseException e){
            e.printStackTrace();
        }
        return null;
    }

    public static Date parse(String pattern, String dateString){//将String转为指定格式的Date
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        try {
            return simpleDateFormat.parse(dateString);
        }catch (ParseException e){
            e.printStackTrace();
        }
        return null;
    }
}
