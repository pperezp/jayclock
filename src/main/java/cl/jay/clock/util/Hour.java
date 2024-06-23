package cl.jay.clock.util;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Hour {

    private Hour() {
        throw new IllegalStateException("Utility class");
    }

    private static int hourOfDay, minute, second, millisecond;

    private static void buildHour() {
        Calendar calendar = new GregorianCalendar();

        hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
        second = calendar.get(Calendar.SECOND);
        millisecond = calendar.get(Calendar.MILLISECOND);
    }

    public static String getCalendar() {
        buildHour();
        return Integer.toString(hourOfDay);
    }

    public static String getMinute() {
        buildHour();
        return change(Hour.minute);
    }

    public static String getSecond() {
        buildHour();
        return change(second);
    }

    public static String getCurrentHour() {
        buildHour();

        String minute = change(Hour.minute);
        String second = change(Hour.second);

        return hourOfDay + ":" + minute + (":") + second;
    }

    public static String change(int value) {
        if (value < 10) {
            return "0" + value;
        }

        return Integer.toString(value);
    }
}
