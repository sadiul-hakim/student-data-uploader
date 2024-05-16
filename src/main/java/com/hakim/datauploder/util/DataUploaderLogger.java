package com.hakim.datauploder.util;

import java.util.logging.Level;
import java.util.logging.Logger;

public final class DataUploaderLogger {
    private DataUploaderLogger() {

    }

    private static final Logger LOGGER = Logger.getLogger(DataUploaderLogger.class.getName());

    public static void log(Level level, String message) {
        LOGGER.log(level, message);
    }
}
