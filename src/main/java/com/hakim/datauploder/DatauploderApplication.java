package com.hakim.datauploder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.hakim.datauploder.model.deserializer.ExcelFileDetailsDeserializer;
import com.hakim.datauploder.model.serializer.ExcelFileDetailsSerializer;
import com.hakim.datauploder.pojo.ExcelFileDetails;
import com.hakim.datauploder.pojo.SheetDetails;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DatauploderApplication {

	private static final ObjectMapper mapper = new ObjectMapper();

	public static void main(String[] args) throws JsonProcessingException {

		SpringApplication.run(DatauploderApplication.class, args);
	}

}
