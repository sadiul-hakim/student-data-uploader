package com.hakim.datauploder.model.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.hakim.datauploder.pojo.ResultSheet;
import com.hakim.datauploder.pojo.StudentResult;
import com.hakim.datauploder.util.DateUtil;
import com.hakim.datauploder.util.JsonKeys;
import com.hakim.datauploder.util.JsonUtil;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultSheetDeserializer extends JsonDeserializer<ResultSheet> {
    @Override
    public ResultSheet deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException {

        ResultSheet resultSheet = new ResultSheet();

        JsonNode jsonNode = parser.readValueAsTree();
        JsonNode studentResultsNode = jsonNode.get(JsonKeys.STUDENT_RESULTS);

        List<StudentResult> studentResults = new ArrayList<>();
        if (studentResultsNode.isArray()) {
            for (JsonNode studentResult : studentResultsNode) {

                StudentResult result = new StudentResult();

                String roll = studentResult.get(JsonKeys.STUDENT_ROLL).asText();

                Map<String, Double> defaultVal = new HashMap<>();
                Map<String, Double> results = JsonUtil.readStringNumberMap(studentResult, JsonKeys.RESULT, defaultVal);

                result.setStudentRoll(roll);
                result.setResult(results);

                studentResults.add(result);
            }
        }

        String dateString = jsonNode.get(JsonKeys.DATE).asText();
        LocalDate date = DateUtil.stringToLocalDate(dateString,DateUtil.DATE_FIRST_FORMATTER);
        String examName = jsonNode.get(JsonKeys.EXAM_NAME).asText();

        resultSheet.setStudentResults(studentResults);
        resultSheet.setDate(date);
        resultSheet.setExamName(examName);

        return resultSheet;
    }
}
