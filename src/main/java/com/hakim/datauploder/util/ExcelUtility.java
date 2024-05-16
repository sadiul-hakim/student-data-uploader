package com.hakim.datauploder.util;

import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExcelUtility {
    public static List<List<Object>> excelDataArray(String filePath, String sheetName) throws Exception {
        if (!(filePath.endsWith(".xls") || filePath.endsWith(".xlsx"))) {
            throw new RuntimeException("Invalid File Format");
        }

        Path path = Path.of(filePath);
        if (!Files.exists(path)) {
            throw new FileNotFoundException("File Does not exists");
        }

        try (FileInputStream fis = new FileInputStream(filePath)) {
            List<List<Object>> cells = new ArrayList<>();
            try (XSSFWorkbook workbook = (XSSFWorkbook) WorkbookFactory.create(fis)) {

                // This there is not sheet name given get the first sheet
                XSSFSheet sheet;
                if (sheetName.isEmpty()) {
                    sheet = workbook.getSheetAt(0);
                } else {
                    sheet = workbook.getSheet(sheetName);
                }

                for (Row row : sheet) {
                    List<Object> cellList = getCellValue(row);
                    cells.add(cellList);
                }
            }
            return cells;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static List<Map<Object, Object>> excelDataMap(String filePath, String sheetName) throws Exception {
        var lists = excelDataArray(filePath, sheetName);
        return DataUtility.rowListToMap(lists);
    }

    private static List<Object> getCellValue(Row row) {
        List<Object> cellList = new ArrayList<>();
        for (Cell cell : row) {

            switch (cell.getCellType()) {
                case CellType.STRING -> cellList.add(cell.getStringCellValue());
                case CellType.BOOLEAN -> cellList.add(cell.getBooleanCellValue());
                case CellType.NUMERIC, CellType.FORMULA -> {
                    if (DateUtil.isCellDateFormatted(cell)) {
                        cellList.add(cell.getLocalDateTimeCellValue());
                    } else {
                        cellList.add(cell.getNumericCellValue());
                    }
                }
                case CellType.BLANK -> cellList.add("");
                case CellType.ERROR -> cellList.add(cell.getErrorCellValue());
            }
        }
        return cellList;
    }
}
