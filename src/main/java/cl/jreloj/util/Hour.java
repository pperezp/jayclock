package cl.jreloj.util;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Hour {

    private static Calendar calendar;
    private static int hourOfDay, minute, second, millisecond;

    private static void buildHour() {
        calendar = new GregorianCalendar();

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
        switch (value) {
            case 0: {
                return "00";
            }
            case 1: {
                return "01";
            }
            case 2: {
                return "02";
            }
            case 3: {
                return "03";
            }
            case 4: {
                return "04";
            }
            case 5: {
                return "05";
            }
            case 6: {
                return "06";
            }
            case 7: {
                return "07";
            }
            case 8: {
                return "08";
            }
            case 9: {
                return "09";
            }

            default: {
                return Integer.toString(value);
            }
        }
    }
}
