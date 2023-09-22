package com.hakim.datauploder.util;

import com.hakim.datauploder.pojo.ExcelFileDetails;
import com.hakim.datauploder.pojo.SheetDetails;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.*;

public class ExcelReader {
    private final XSSFWorkbook workbook;

    public ExcelReader(InputStream inputStream) throws IOException {
        workbook = (XSSFWorkbook) WorkbookFactory.create(inputStream);
    }

    public Map<String, List<String>> read(ExcelFileDetails details) throws IOException {
        Map<String, List<String>> data = new LinkedHashMap<>();

        List<SheetDetails> sheets = details.getSheets();

        for (SheetDetails sheetDetails : sheets) {
            List<String> headers = new ArrayList<>();
            XSSFSheet workbookSheet = workbook.getSheet(sheetDetails.getSheetName());

            for (int j = 0; j < sheetDetails.getSkipRow(); j++) {

                XSSFRow row = workbookSheet.getRow(j);

                for (int k = 0; k < row.getLastCellNum(); k++) {

                    XSSFCell cell = row.getCell(k);
                    if (cell.getCellType() == CellType.NUMERIC) {
                        LocalDateTime dateTime = cell.getLocalDateTimeCellValue();
                        String format = DateUtil.format(dateTime);
                        data.put(format, new ArrayList<>());
                        headers.add(format);
                    } else {
                        data.put(cell.getStringCellValue(), new ArrayList<>());
                        headers.add(cell.getStringCellValue());
                    }
                }
            }

            for (int i = sheetDetails.getSkipRow(); i <= workbookSheet.getLastRowNum(); i++) {
                XSSFRow row = workbookSheet.getRow(i);
                for (int k = 0; k < headers.size(); k++) {
                    XSSFCell cell = row.getCell(k);
                    List<String> rowData = data.get(headers.get(k));

                    if (cell.getCellType() == CellType.NUMERIC) {
                        rowData.add(cell.getNumericCellValue() + "");
                    } else if (cell.getCellType() == CellType.FORMULA) {
                        rowData.add(cell.getNumericCellValue() + "");
                    } else {
                        rowData.add(cell.getStringCellValue());
                    }
                    data.put(headers.get(k), rowData);
                }
            }
        }

        return data;
    }

    public List<List<String>> readRows(ExcelFileDetails details) throws IOException {
        List<List<String>> data = new ArrayList<>();

        List<SheetDetails> sheets = details.getSheets();

        for (SheetDetails sheet : sheets) {
            XSSFSheet workbookSheet = workbook.getSheet(sheet.getSheetName());

            for (Row row : workbookSheet) {

                List<String> rowData = new ArrayList<>();
                for (Cell cell : row) {
                    if (cell.getCellType() == CellType.NUMERIC) {
                        rowData.add(cell.getNumericCellValue() + "");
                    } else if (cell.getCellType() == CellType.FORMULA) {
                        rowData.add(cell.getNumericCellValue() + "");
                    } else {
                        rowData.add(cell.getStringCellValue());
                    }
                }

                data.add(rowData);
            }
        }

        return data;
    }
}
