package com.hakim.datauploder.model.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.hakim.datauploder.model.DataTypeModel;
import com.hakim.datauploder.pojo.DataSaving;
import com.hakim.datauploder.util.DataType;
import com.hakim.datauploder.util.JsonKeys;
import com.hakim.datauploder.util.ServiceHandler;



import java.io.IOException;

public class DataSavingDeserializer extends JsonDeserializer<DataSaving> {
    @Override
    public DataSaving deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException, JacksonException {

        DataSaving dataSaving = new DataSaving();
        JsonNode jsonNode = parser.readValueAsTree();

        long typeId = jsonNode.get(JsonKeys.DATA_TYPE).asLong();
        DataTypeModel dataTypeModel = ServiceHandler.dataTypeService.getById(typeId);
        DataType dataType = DataType.valueOf(dataTypeModel.getType());

        dataSaving.setDataType(dataType);

        return dataSaving;
    }
}
