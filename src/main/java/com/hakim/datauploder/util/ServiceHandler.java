package com.hakim.datauploder.util;

import com.hakim.datauploder.service.DataTypeService;

public class ServiceHandler {
    public static final DataTypeService dataTypeService = SpringContext.getBean(DataTypeService.class);
}
