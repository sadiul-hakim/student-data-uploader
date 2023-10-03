package com.hakim.datauploder.model.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.hakim.datauploder.pojo.FeeSheet;
import com.hakim.datauploder.pojo.StudentFee;
import com.hakim.datauploder.util.JsonKeys;
import com.hakim.datauploder.util.JsonUtil;

import java.io.IOException;

public class FeeSheetSerializer extends JsonSerializer<FeeSheet> {
    @Override
    public void serialize(FeeSheet sheet, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException {

        jsonGenerator.writeStartObject();
        jsonGenerator.writeArrayFieldStart(JsonKeys.STUDENT_FEES);
        if (!sheet.getStudentFees().isEmpty()) {
            for (StudentFee studentFee : sheet.getStudentFees()) {
                jsonGenerator.writeStartObject();

                jsonGenerator.writeStringField(JsonKeys.STUDENT_ROLL, studentFee.getStudentRoll());
                JsonUtil.writeStringNumberMap(studentFee.getFees(), JsonKeys.FEES, jsonGenerator);

                jsonGenerator.writeEndObject();
            }
        }
        jsonGenerator.writeEndArray();
        jsonGenerator.writeEndObject();
    }
}
