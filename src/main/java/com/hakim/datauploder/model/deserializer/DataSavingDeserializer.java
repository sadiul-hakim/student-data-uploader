package com.hakim.datauploder.model.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.hakim.datauploder.pojo.DataSaving;
import com.hakim.datauploder.util.DataType;
import com.hakim.datauploder.util.JsonKeys;

import java.io.IOException;

public class DataSavingDeserializer extends JsonDeserializer<DataSaving> {
    @Override
    public DataSaving deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException, JacksonException {

        DataSaving dataSaving = new DataSaving();
        JsonNode jsonNode = parser.readValueAsTree();

        String dataTypeText = jsonNode.get(JsonKeys.DATA_TYPE).asText();
        DataType dataType = DataType.valueOf(dataTypeText);

        dataSaving.setDataType(dataType);

        return dataSaving;
    }
}
