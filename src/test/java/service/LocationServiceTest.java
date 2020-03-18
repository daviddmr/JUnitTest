package service;

import entity.Location;
import entity.Movie;
import entity.User;
import org.junit.Assert;
import org.junit.Test;
import util.DataUtils;

import java.util.Date;

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
        Assert.assertTrue(location.getValue() == 5.0);
        Assert.assertTrue(DataUtils.isSameDate(location.getLocationDate(), new Date()));
        Assert.assertTrue(DataUtils.isSameDate(location.getReturnDate(), DataUtils.getDifferenceBetweenToDays(1)));
    }
}
