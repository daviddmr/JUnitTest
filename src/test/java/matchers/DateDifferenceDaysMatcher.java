package matchers;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import utils.DataUtils;

import java.util.Date;

public class DateDifferenceDaysMatcher extends TypeSafeMatcher<Date> {

    private Integer quantityOfDays;

    public DateDifferenceDaysMatcher(Integer quantityOfDays) {
        this.quantityOfDays = quantityOfDays;
    }

    @Override
    protected boolean matchesSafely(Date date) {
        return DataUtils.isSameDate(date, DataUtils.addDays(new Date(), quantityOfDays));
    }

    @Override
    public void describeTo(Description description) {

    }
}
