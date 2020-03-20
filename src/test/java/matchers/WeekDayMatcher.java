package matchers;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.util.Date;

public class WeekDayMatcher extends TypeSafeMatcher<Date> {

    public WeekDayMatcher(Integer weekDay) {
    }

    protected boolean matchesSafely(Date date) {
        return false;
    }

    public void describeTo(Description description) {

    }
}
