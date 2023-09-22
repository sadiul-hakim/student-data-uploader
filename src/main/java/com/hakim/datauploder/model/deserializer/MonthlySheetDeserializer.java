package com.hakim.datauploder.model.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.hakim.datauploder.pojo.MonthlySheet;
import com.hakim.datauploder.pojo.Presence;
import com.hakim.datauploder.util.DateUtil;
import com.hakim.datauploder.util.JsonKeys;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MonthlySheetDeserializer extends JsonDeserializer<MonthlySheet> {
    @Override
    public MonthlySheet deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        MonthlySheet monthlySheet = new MonthlySheet();

        JsonNode jsonNode = jsonParser.readValueAsTree();
        JsonNode presenceNode = jsonNode.get(JsonKeys.PRESENCE_LIST);

        List<Presence> presenceList = new ArrayList<>();
        for(JsonNode node:presenceNode){

            Presence presence = new Presence();
            LocalDate localDate = DateUtil.stringToLocalDate(node.get(JsonKeys.DATE).asText(), DateUtil.DATE_FIRST_FORMATTER);

            List<Double> presentStudentsRoll = new ArrayList<>();
            for(JsonNode roll : node.get(JsonKeys.PRESENT_STUDENTS_ROLL)){
                presentStudentsRoll.add(roll.asDouble());
            }

            presence.setDate(localDate);
            presence.setPresentStudentsRoll(presentStudentsRoll);

            presenceList.add(presence);
        }

        monthlySheet.setPresenceList(presenceList);
        return monthlySheet;
    }
}
