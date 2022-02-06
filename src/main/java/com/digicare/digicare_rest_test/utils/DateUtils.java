package com.digicare.digicare_rest_test.utils;

import java.util.Calendar;

public final class DateUtils {
    private DateUtils() {
    	
    }

    public static Calendar calendarFor(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day);
        return cal;
    }

}
