package com.hakim.datauploder.model.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.hakim.datauploder.model.deserializer.DataSavingDeserializer;
import com.hakim.datauploder.model.serializer.DataSavingSerializer;
import com.hakim.datauploder.pojo.DataSaving;
import jakarta.persistence.AttributeConverter;

public class DataSavingConverter implements AttributeConverter<DataSaving, String> {

    private static final ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();

        SimpleModule module = new SimpleModule();
        module.addSerializer(DataSaving.class, new DataSavingSerializer());
        module.addDeserializer(DataSaving.class, new DataSavingDeserializer());
        mapper.registerModule(module);
    }

    @Override
    public String convertToDatabaseColumn(DataSaving attribute) {

        try {
            return mapper.writeValueAsString(attribute);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public DataSaving convertToEntityAttribute(String dbData) {

        try {
            return mapper.readValue(dbData, DataSaving.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
