package org.estonlabs.coinbase.client;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CbDateTimeUtils {
    private static DateTimeFormatter PRICE_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static String toPriceDateString(LocalDate date){
        return date.format(PRICE_DATE_FORMAT);
    }

    public static LocalDate toPriceDate(String d){
        return LocalDate.parse(d, PRICE_DATE_FORMAT);
    }
}
