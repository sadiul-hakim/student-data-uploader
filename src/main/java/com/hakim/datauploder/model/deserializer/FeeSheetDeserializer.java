package com.hakim.datauploder.model.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.hakim.datauploder.pojo.FeeSheet;
import com.hakim.datauploder.pojo.StudentFee;
import com.hakim.datauploder.util.DateUtil;
import com.hakim.datauploder.util.JsonKeys;
import com.hakim.datauploder.util.JsonUtil;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FeeSheetDeserializer extends JsonDeserializer<FeeSheet> {
    @Override
    public FeeSheet deserialize(JsonParser parser, DeserializationContext deserializationContext) throws IOException, JacksonException {

        FeeSheet feeSheet = new FeeSheet();

        JsonNode jsonNode = parser.readValueAsTree();
        JsonNode studentFeesNode = jsonNode.get(JsonKeys.STUDENT_FEES);

        List<StudentFee> studentFees = new ArrayList<>();
        if (studentFeesNode.isArray()) {
            for (JsonNode studentFee : studentFeesNode) {

                StudentFee fee = new StudentFee();

                String roll = studentFee.get(JsonKeys.STUDENT_ROLL).asText();
                Map<String, Double> defaultVal = new HashMap<>();
                Map<String, Double> fees = JsonUtil.readStringNumberMap(studentFee, JsonKeys.FEES, defaultVal);

                fee.setStudentRoll(roll);
                fee.setFees(fees);

                studentFees.add(fee);
            }
        }

        String dateString = jsonNode.get(JsonKeys.DATE).asText();
        LocalDate date = DateUtil.stringToLocalDate(dateString,DateUtil.DATE_FIRST_FORMATTER);

        feeSheet.setStudentFees(studentFees);
        feeSheet.setDate(date);

        return feeSheet;
    }
}
