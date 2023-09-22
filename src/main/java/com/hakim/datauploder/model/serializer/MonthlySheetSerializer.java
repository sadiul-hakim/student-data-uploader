package com.hakim.datauploder.model.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.hakim.datauploder.pojo.MonthlySheet;
import com.hakim.datauploder.pojo.Presence;
import com.hakim.datauploder.util.DateUtil;
import com.hakim.datauploder.util.JsonKeys;
import com.hakim.datauploder.util.JsonUtil;

import java.io.IOException;
import java.util.List;

public class MonthlySheetSerializer extends JsonSerializer<MonthlySheet> {
    @Override
    public void serialize(MonthlySheet monthlySheet, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();

        List<Presence> presenceList = monthlySheet.getPresenceList();
        jsonGenerator.writeArrayFieldStart(JsonKeys.PRESENCE_LIST);
        for (Presence presence : presenceList) {
            jsonGenerator.writeStartObject();

            jsonGenerator.writeStringField(JsonKeys.DATE, DateUtil.format(presence.getDate()));
            JsonUtil.writeNumberList(presence.getPresentStudentsRoll(), JsonKeys.PRESENT_STUDENTS_ROLL, jsonGenerator);

            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndArray();

        jsonGenerator.writeEndObject();
    }
}
