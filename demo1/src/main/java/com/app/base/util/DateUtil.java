package com.app.base.util;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

// 날짜 유틸
public class DateUtil {

    public static long getTimeInMillis () {
        Calendar cal = Calendar.getInstance ();
        return cal.getTimeInMillis();
    }
    
    public static long addTimeInMillis (int second) {
        Calendar cal = Calendar.getInstance ();
        cal.add(Calendar.SECOND, second);
        return cal.getTimeInMillis();
        
    }
    
    public static Date millisToDate(long millis) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(millis);
        Date date = cal.getTime();
        return date;
    }
    
}
