package com.hakim.datauploder.util;

import java.time.LocalDate;


public class DateUtil {
    
    public LocalDate stringToLocalDate(String date,String format){
        String[] dateArr = date.split("/");

        
        String dateText;
        String monthText;
        String yearText;    
        switch(format){
            case DateFormat.DATE_FIRST -> {

                dateText = dateArr[0];
                monthText = dateArr[1];
                yearText = dateArr[2];
            }
            case DateFormat.YEAR_FIRST -> {
                
                yearText = dateArr[0];
                monthText = dateArr[1];
                dateText = dateArr[2];
            }
            case DateFormat.MONTH_FIRST -> {

                monthText = dateArr[0];
                dateText = dateArr[1];
                yearText = dateArr[2];
            }
            default -> throw new RuntimeException("Invalid date format!");
        }

        return LocalDate.of(Integer.parseInt(yearText), Integer.parseInt(monthText), Integer.parseInt(dateText));
    }
}
