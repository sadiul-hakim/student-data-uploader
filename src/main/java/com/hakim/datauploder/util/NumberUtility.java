package com.hakim.datauploder.util;

import java.text.DecimalFormat;

public class NumberUtility {
    public static double format(double value, int decimalPoints) {

        String valueStr = String.valueOf(value);
        DecimalFormat format = new DecimalFormat(buildPattern(valueStr, decimalPoints));
        return  Double.parseDouble(format.format(value));
    }

    private static String buildPattern(String value, int decimalPoints) {
        int actualNumberLength = value.split("\\.")[0].length();
        return STR."\{"#".repeat(actualNumberLength)}.\{"#".repeat(Math.max(0, decimalPoints))}";
    }
}
