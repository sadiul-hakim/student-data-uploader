package com.hakim.datauploder.model.deserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.hakim.datauploder.model.ExcelFileDetails;
import com.hakim.datauploder.model.SheetDetails;
import com.hakim.datauploder.util.JsonKeys;

public class ExcelFileDetailsDeserializer extends JsonDeserializer<ExcelFileDetails>{

    @Override
    public ExcelFileDetails deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JacksonException {
        ExcelFileDetails excelFileDetails = new ExcelFileDetails();

        JsonNode jsonNode = jsonParser.readValueAsTree();

        JsonNode sheetsNode = jsonNode.get(JsonKeys.SHEETS);
        List<SheetDetails> sheets = new ArrayList<>();
        if(sheetsNode != null && !sheetsNode.isEmpty()){
            for(JsonNode sheetNode : sheetsNode){
                int skipRow = sheetNode.get(JsonKeys.SKIP_ROW).asInt();
                String sheetName = sheetNode.get(JsonKeys.SHEET_NAME).asText();

                sheets.add(new SheetDetails(skipRow, sheetName));
            }
        }

        excelFileDetails.setSheets(sheets);

        return excelFileDetails;
    }
    
}
