package service;

import entity.Location;
import entity.Movie;
import entity.User;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import util.DataUtils;

import java.util.Date;
import java.util.regex.Matcher;

public class LocationServiceTest {

    @Test
    public void test() {
        //scenario
        LocationService locationService = new LocationService();
        User user = new User("");
        Movie movie = new Movie("Movie 1", 2, 5.0);

        //action
        Location location = locationService.rentMovie(user, movie);

        //verification
        Assert.assertEquals(5.0, location.getValue(), 0.1);
        Assert.assertThat(location.getValue(), CoreMatchers.is(CoreMatchers.equalTo(5.0)));

        Assert.assertTrue(DataUtils.isSameDate(location.getLocationDate(), new Date()));
        Assert.assertThat(DataUtils.isSameDate(location.getLocationDate(), new Date()), CoreMatchers.is(true));

        Assert.assertTrue(DataUtils.isSameDate(location.getReturnDate(), DataUtils.getDifferenceBetweenToDays(1)));
        Assert.assertThat(DataUtils.isSameDate(location.getReturnDate(), DataUtils.getDifferenceBetweenToDays(1)), CoreMatchers.is(true));
    }
}
