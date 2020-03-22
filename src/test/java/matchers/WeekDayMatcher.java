package matchers;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import utils.DataUtils;

import java.util.Date;

public class WeekDayMatcher extends TypeSafeMatcher<Date> {

    private Integer weekDay;

    public WeekDayMatcher(Integer weekDay) {
        this.weekDay = weekDay;
    }

    protected boolean matchesSafely(Date date) {
        return DataUtils.checkWeekDay(date, weekDay);
    }

    public void describeTo(Description description) {

    }
}
