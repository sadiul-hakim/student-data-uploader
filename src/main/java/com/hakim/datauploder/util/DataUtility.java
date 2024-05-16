package com.hakim.datauploder.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataUtility {
    public static List<Map<Object, Object>> rowListToMap(List<List<Object>> rowList) {
        List<Map<Object, Object>> rows = new ArrayList<>();
        List<Object> headers = rowList.getFirst();
        for (int i = 1; i < rowList.size(); i++) {
            Map<Object, Object> rowData = new HashMap<>();
            List<Object> row = rowList.get(i);

            for (int j = 0; j < headers.size(); j++) {
                try {
                    rowData.put(headers.get(j), row.get(j));
                } catch (Exception ex) {
                    rowData.put(headers.get(j), "");
                }
            }
            rows.add(rowData);
        }
        return rows;
    }
}
