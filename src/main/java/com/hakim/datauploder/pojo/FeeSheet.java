package com.hakim.datauploder.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeeSheet {
    private List<StudentFee> studentFees = new ArrayList<>();
}
