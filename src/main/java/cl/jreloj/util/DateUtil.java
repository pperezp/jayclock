package cl.jreloj.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    private DateUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static String getFormattedDate() {
        String pattern = "EEE, dd'-'MMM'-'yyyy";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        
        return LocalDate.now().format(dateTimeFormatter);
    }
}
