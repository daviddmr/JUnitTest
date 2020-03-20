package matchers;

import java.util.Calendar;

public class CustomMatchers {

    public static WeekDayMatcher dayIs(Integer weekDay) {
        return new WeekDayMatcher(weekDay);
    }

    public static WeekDayMatcher isMonday() {
        return new WeekDayMatcher(Calendar.MONDAY);
    }

    public static DateDifferenceDaysMatcher isTodayWithDifferenceOf(Integer quantityOfDays) {
        return new DateDifferenceDaysMatcher(quantityOfDays);
    }

    public static DateDifferenceDaysMatcher isToday() {
        return new DateDifferenceDaysMatcher(0);
    }
}
