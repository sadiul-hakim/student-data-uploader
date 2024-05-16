package com.hakim.datauploder.util;

import java.util.List;

import static java.lang.StringTemplate.STR;

public class Printer {
    public static void printRow(List<List<Object>> data){
        data.forEach((List<Object> list) -> {
            list.forEach(item -> System.out.print(STR."\{item},"));
            System.out.println();
        });
    }
}
