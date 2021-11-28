package com.coinbase.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CbDateUtils {
    private final static DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");

    public static LocalDateTime toDateTime(String t){
        return LocalDateTime.parse(t, TIME_FORMAT);
    }
}
