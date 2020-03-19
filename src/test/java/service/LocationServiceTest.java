package service;

import entity.Location;
import entity.Movie;
import entity.User;
import exception.MovieOutOfStockException;
import exception.VideoStoreException;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;
import util.DataUtils;

import java.util.Date;

public class LocationServiceTest {

    //Used do detect more than one error in the same scope
    //Asserts are not able to recognize possible errors after the first
    @Rule
    public ErrorCollector errorCollector = new ErrorCollector();

    //Used at modern exception
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void test() throws Exception {
        //scenario
        LocationService locationService = new LocationService();
        User user = new User("");
        Movie movie = new Movie("Movie 1", 2, 5.0);

        //action
        Location location = locationService.rentMovie(user, movie);

        //verification
        Assert.assertEquals(5.0, location.getValue(), 0.1);
        Assert.assertThat(location.getValue(), CoreMatchers.is(CoreMatchers.equalTo(5.0)));
        errorCollector.checkThat(location.getValue(), CoreMatchers.is(CoreMatchers.equalTo(5.0)));

        Assert.assertTrue(DataUtils.isSameDate(location.getLocationDate(), new Date()));
        Assert.assertThat(DataUtils.isSameDate(location.getLocationDate(), new Date()), CoreMatchers.is(true));
        errorCollector.checkThat(DataUtils.isSameDate(location.getLocationDate(), new Date()), CoreMatchers.is(true));

        Assert.assertTrue(DataUtils.isSameDate(location.getReturnDate(), DataUtils.getDifferenceBetweenToDays(1)));
        Assert.assertThat(DataUtils.isSameDate(location.getReturnDate(), DataUtils.getDifferenceBetweenToDays(1)), CoreMatchers.is(true));
        errorCollector.checkThat(DataUtils.isSameDate(location.getReturnDate(), DataUtils.getDifferenceBetweenToDays(1)), CoreMatchers.is(true));
    }

    //Elegant exception
    @Test(expected = MovieOutOfStockException.class)
    public void testLocation_MovieWithoutStock() throws Exception {
        //scenario
        LocationService locationService = new LocationService();
        User user = new User("");
        Movie movie = new Movie("Movie 1", 0, 5.0);

        //action
        locationService.rentMovie(user, movie);
    }

    //Robust exception
    @Test
    public void testLocation_UserNullException() throws MovieOutOfStockException {
        //scenario
        LocationService locationService = new LocationService();
        Movie movie = new Movie("Movie 1", 2, 5.0);

        //action
        try {
            locationService.rentMovie(null, movie);
            //To avoid a false-positive
            Assert.fail();
        } catch (VideoStoreException e) {
            Assert.assertThat(e.getMessage(), CoreMatchers.is("User null"));
        }
    }

    //Modern exception
    @Test
    public void testLocation_MovieNullException() throws VideoStoreException, MovieOutOfStockException {
        //scenario
        LocationService locationService = new LocationService();
        User user = new User("");

        expectedException.expect(VideoStoreException.class);
        expectedException.expectMessage("Movie null");

        //action
        locationService.rentMovie(user, null);
    }
}
