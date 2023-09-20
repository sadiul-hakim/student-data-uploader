package com.hakim.datauploder.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExcelFileDetails implements Serializable{

    List<SheetDetails> sheets = new ArrayList<>(); // Information about each sheet

}
