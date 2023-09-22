package com.hakim.datauploder.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.hakim.datauploder.model.deserializer.ExcelFileDetailsDeserializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExcelFileDetails implements Serializable{

    List<SheetDetails> sheets = new ArrayList<>(); // Information about each sheet

}
