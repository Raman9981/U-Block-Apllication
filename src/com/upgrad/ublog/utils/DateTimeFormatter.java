package com.upgrad.ublog.utils;

import java.time.LocalDateTime;

/**
 * TODO: 4.13. Implement a method with the following signature.
 *  public static String format(LocalDateTime localDateTime)
 *  This method should convert the default date time to the human readable format[dd-MM-yyyy HH:mm:ss].
 */

public class DateTimeFormatter {
    public static  String format(LocalDateTime localDateTime){
        int day=localDateTime.getDayOfMonth();
        int month=localDateTime.getMonthValue();
        int year=localDateTime.getYear();
        int hr=localDateTime.getHour();
        int min=localDateTime.getMinute();
        int sec=localDateTime.getSecond();
        return day+"-"+month+"-"+year+" "+hr+":"+min+":"+sec;
    }
}
