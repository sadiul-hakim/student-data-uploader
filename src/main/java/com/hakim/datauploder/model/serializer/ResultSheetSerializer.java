package com.hakim.datauploder.model.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.hakim.datauploder.pojo.ResultSheet;
import com.hakim.datauploder.pojo.StudentResult;
import com.hakim.datauploder.util.DateUtil;
import com.hakim.datauploder.util.JsonKeys;
import com.hakim.datauploder.util.JsonUtil;

import java.io.IOException;

public class ResultSheetSerializer extends JsonSerializer<ResultSheet> {
    @Override
    public void serialize(ResultSheet sheet, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException {

        jsonGenerator.writeStartObject();
        jsonGenerator.writeArrayFieldStart(JsonKeys.STUDENT_RESULTS);
        if (!sheet.getStudentResults().isEmpty()) {
            for (StudentResult studentResult : sheet.getStudentResults()) {
                jsonGenerator.writeStartObject();

                jsonGenerator.writeStringField(JsonKeys.STUDENT_ROLL, studentResult.getStudentRoll());
                JsonUtil.writeStringNumberMap(studentResult.getResult(), JsonKeys.RESULT, jsonGenerator);

                jsonGenerator.writeEndObject();
            }
        }
        jsonGenerator.writeEndArray();

        jsonGenerator.writeStringField(JsonKeys.DATE, DateUtil.format(sheet.getDate()));
        jsonGenerator.writeStringField(JsonKeys.EXAM_NAME, sheet.getExamName());

        jsonGenerator.writeEndObject();
    }
}
