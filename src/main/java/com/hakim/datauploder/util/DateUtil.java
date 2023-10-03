package com.hakim.datauploder.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;


public class DateUtil {

    public static final DateTimeFormatter DATE_FIRST_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static final DateTimeFormatter MONTH_FIRST_FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    public static final DateTimeFormatter YEAR_FIRST_FORMATTER = DateTimeFormatter.ofPattern("yyyy/dd/MM");

    public static LocalDate stringToLocalDate(String date, DateTimeFormatter format){

        String[] dateArr = date.split("/");

        String dateText;
        String monthText;
        String yearText;
        if (format.equals(DATE_FIRST_FORMATTER)) {
            dateText = dateArr[0];
            monthText = dateArr[1];
            yearText = dateArr[2];
        } else if (format.equals(MONTH_FIRST_FORMATTER)) {
            monthText = dateArr[0];
            yearText = dateArr[1];
            dateText = dateArr[2];
        } else if (format.equals(YEAR_FIRST_FORMATTER)) {
            yearText = dateArr[0];
            dateText = dateArr[1];
            monthText = dateArr[2];
        } else {
            throw new RuntimeException("Invalid date format!");
        }

        return LocalDate.of(Integer.parseInt(yearText),Integer.parseInt(monthText),Integer.parseInt(dateText));
    }

    public static String format(LocalDate localDate){
        return localDate.format(DATE_FIRST_FORMATTER);
    }

    public static String format(LocalDateTime dateTime){
        return dateTime.format(DATE_FIRST_FORMATTER);
    }

    public static Integer[] getDateArray(String date,boolean includeDate){

        Map<String,Integer> months =new HashMap<>();
        months.put("Jan",1);
        months.put("Feb",2);
        months.put("Mar",3);
        months.put("Apr",4);
        months.put("May",5);
        months.put("June",6);
        months.put("July",7);
        months.put("Aug",8);
        months.put("Sep",9);
        months.put("Oct",10);
        months.put("Nov", 11);
        months.put("Dec",12);

        if(date == null || date.split(" ").length < 2) return null;
        String[] dateStringArray = date.split(" ");

        int index = includeDate ? 3 : 2;
        Integer[] dateLong = new Integer[index];

        if(includeDate){
            dateLong[0] = Integer.valueOf(dateStringArray[0]);
            dateLong[1] = months.get(dateStringArray[1]);
            dateLong[2] = Integer.valueOf(dateStringArray[2]);
        }else {
            dateLong[0] = months.get(dateStringArray[0]);
            dateLong[1] = Integer.valueOf(dateStringArray[1]);
        }

        return dateLong;
    }
}
