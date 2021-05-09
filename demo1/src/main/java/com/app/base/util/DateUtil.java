package com.app.base.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil {

    public static Date getDate () {
        Date date = null;
        try {
            SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            isoFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        
            date = isoFormat.parse("2010-05-23T09:01:02");
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            return null;
        }
        return date;
    }
    
    public static void main(String[] args) {
        System.out.println(DateUtil.getDate());
        
        TimeZone utc = TimeZone.getTimeZone ("UTC");
        // 주어진 시간대에 맞게 현재 시각으로 초기화된 GregorianCalender 객체를 반환.
        Calendar cal = Calendar.getInstance ( utc );  
        
        System.out.println(cal.toString());
        Date date = cal.getTime();
        System.out.println(date);
        System.out.println(date.toGMTString());
        System.out.println(date.toString());
        
        java.sql.Date dd = null;
        
        System.out.println(TimeZone.getDefault());
        

    }
}
