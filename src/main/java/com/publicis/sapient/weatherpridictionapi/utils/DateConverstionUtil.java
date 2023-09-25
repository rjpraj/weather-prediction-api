package com.publicis.sapient.weatherpridictionapi.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverstionUtil {

    public static String convertToReadbleDate(long time) {
        System.out.println(time);
        long timestamp = time * 1000L; // Convert seconds to milliseconds
        Date date = new Date(timestamp);
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd, yyyy");
        String formattedDate = sdf.format(date);
        System.out.println(formattedDate);
        return formattedDate;
    }

}
