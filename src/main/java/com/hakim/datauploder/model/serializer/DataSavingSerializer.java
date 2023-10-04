package com.hakim.datauploder.model.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.hakim.datauploder.pojo.DataSaving;
import com.hakim.datauploder.util.JsonKeys;

import java.io.IOException;

public class DataSavingSerializer extends JsonSerializer<DataSaving> {
    @Override
    public void serialize(DataSaving dataSaving, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException {

        jsonGenerator.writeStartObject();

        if (dataSaving.getDataType() != null) {
            jsonGenerator.writeStringField(JsonKeys.DATA_TYPE, dataSaving.getDataType().name());
        }

        jsonGenerator.writeEndObject();
    }
}
