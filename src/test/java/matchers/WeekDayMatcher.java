package matchers;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import utils.DataUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class WeekDayMatcher extends TypeSafeMatcher<Date> {

    private Integer weekDay;

    WeekDayMatcher(Integer weekDay) {
        this.weekDay = weekDay;
    }

    protected boolean matchesSafely(Date date) {
        return DataUtils.checkWeekDay(date, weekDay);
    }

    public void describeTo(Description description) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, weekDay);
        String weekDayString = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, new Locale("en", "CA"));
        description.appendText(weekDayString);
    }
}
