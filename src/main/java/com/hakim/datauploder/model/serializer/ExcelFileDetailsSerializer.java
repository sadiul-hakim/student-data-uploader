package com.hakim.datauploder.model.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.hakim.datauploder.pojo.ExcelFileDetails;
import com.hakim.datauploder.pojo.SheetDetails;
import com.hakim.datauploder.util.JsonKeys;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.util.List;

@JsonComponent
public class ExcelFileDetailsSerializer extends JsonSerializer<ExcelFileDetails> {

    @Override
    public void serialize(ExcelFileDetails excelFileDetails, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        jsonGenerator.writeStartObject();

        List<SheetDetails> sheets = excelFileDetails.getSheets();

        jsonGenerator.writeArrayFieldStart(JsonKeys.SHEETS);
        for (SheetDetails sheet : sheets) {
            jsonGenerator.writeStartObject();

            jsonGenerator.writeNumberField(JsonKeys.SKIP_ROW, sheet.getSkipRow());
            jsonGenerator.writeStringField(JsonKeys.SHEET_NAME, sheet.getSheetName());
            jsonGenerator.writeStringField(JsonKeys.DATA_TYPE, sheet.getDataType());
            jsonGenerator.writeStringField(JsonKeys.MAIN_COLUMN, sheet.getMainColumn());

            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndArray();

        jsonGenerator.writeEndObject();
    }

}
