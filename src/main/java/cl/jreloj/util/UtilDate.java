package cl.jreloj.util;

import java.util.Calendar;

public class UtilDate {

    private static String day, month, year;
    private static String dayName;
    private static Calendar calendar = Calendar.getInstance();

    public static String getDateAsString(boolean onlyNumbers, boolean dateWithSlash) {
        buildDate();
        if (onlyNumbers) {
            return Hour.change(Integer.parseInt(day)) + (dateWithSlash ? "/" : "-") + (calendar.get(Calendar.MONTH) + 1) + (dateWithSlash ? "/" : "-") + year;
        } else {
            return Hour.change(Integer.parseInt(day)) + (dateWithSlash ? "/" : "-") + month + (dateWithSlash ? "/" : "-") + year;
        }
    }

    public static String getDayName() {
        buildDate();
        return dayName;
    }

    private static void buildDate() {
        calendar = Calendar.getInstance();
        day = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
        month = Integer.toString(calendar.get(Calendar.MONTH) + 1);
        dayName = Integer.toString(calendar.get(Calendar.DAY_OF_WEEK));

        if (dayName.equalsIgnoreCase("1")) {
            dayName = "Domingo";
        } else if (dayName.equalsIgnoreCase("2")) {
            dayName = "Lunes";
        } else if (dayName.equalsIgnoreCase("3")) {
            dayName = "Martes";
        } else if (dayName.equalsIgnoreCase("4")) {
            dayName = "Miércoles";
        } else if (dayName.equalsIgnoreCase("5")) {
            dayName = "Jueves";
        } else if (dayName.equalsIgnoreCase("6")) {
            dayName = "Viernes";
        } else if (dayName.equalsIgnoreCase("7")) {
            dayName = "Sábado";
        }

        if (month.equalsIgnoreCase("1")) {
            month = "ene";
        } else if (month.equalsIgnoreCase("2")) {
            month = "feb";
        } else if (month.equalsIgnoreCase("3")) {
            month = "mar";
        } else if (month.equalsIgnoreCase("4")) {
            month = "abr";
        } else if (month.equalsIgnoreCase("5")) {
            month = "may";
        } else if (month.equalsIgnoreCase("6")) {
            month = "jun";
        } else if (month.equalsIgnoreCase("7")) {
            month = "jul";
        } else if (month.equalsIgnoreCase("8")) {
            month = "ago";
        } else if (month.equalsIgnoreCase("9")) {
            month = "sep";
        } else if (month.equalsIgnoreCase("10")) {
            month = "oct";
        } else if (month.equalsIgnoreCase("11")) {
            month = "nov";
        } else if (month.equalsIgnoreCase("12")) {
            month = "dic";
        }

        year = Integer.toString(calendar.get(Calendar.YEAR));
    }
}
