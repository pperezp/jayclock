package cl.jay.clock.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    private DateUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static String getFormattedDate() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("EEE, dd'-'MMM'-'yyyy");
        return LocalDate.now().format(dateTimeFormatter);
    }
}
