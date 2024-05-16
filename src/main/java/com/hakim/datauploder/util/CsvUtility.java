package com.hakim.datauploder.util;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class CsvUtility {
    public static List<List<Object>> csvDataArray(String filePath) throws Exception {

        if (!filePath.endsWith(".csv")) {
            throw new RuntimeException("Invalid File Format");
        }

        Path path = Path.of(filePath);
        if (!Files.exists(path)) {
            throw new FileNotFoundException("File Does not exists");
        }

        try (Stream<String> lines = Files.lines(path)) {

            List<List<Object>> cells = new ArrayList<>();
            lines.forEach(line -> {
                Object[] rowArray = line.split(",");
                cells.add(Arrays.asList(rowArray));
            });

            return cells;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static List<Map<Object, Object>> csvDataMap(String filePath) throws Exception {

        var lists = csvDataArray(filePath);
        return DataUtility.rowListToMap(lists);
    }

    public static void writeToCsv(List<List<Object>> data, String filePath) throws Exception {

        StringBuilder builder = new StringBuilder();
        List<Object> headers = data.getFirst();

        appendList(headers, builder);
        builder.append("\n");

        for (int i = 1; i < data.size(); i++) {
            appendList(data.get(i), builder);
            builder.append("\n");
        }

        Path path = Path.of(filePath);
        Files.createFile(path);
        Files.writeString(path, builder.toString());
    }

    private static void appendList(List<Object> list, StringBuilder builder) {
        for (int i = 0; i < list.size(); i++) {
            builder.append(list.get(i));

            if (i < list.size() - 1) {
                builder.append(",");
            }
        }
    }
}
