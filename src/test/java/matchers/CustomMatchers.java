package matchers;

public class CustomMatchers {

    public static WeekDayMatcher dayIs(Integer weekDay) {
        return new WeekDayMatcher(weekDay);
    }
}
