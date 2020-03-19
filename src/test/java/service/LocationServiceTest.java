package service;

import entity.Location;
import entity.Movie;
import entity.User;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import util.DataUtils;

import java.util.Date;

public class LocationServiceTest {

    //Used do detect more than one error in the same scope
    //Asserts are not able to recognize possible errors after the first
    @Rule
    public ErrorCollector errorCollector = new ErrorCollector();

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

    @Test(expected = Exception.class)
    public void testMovieWithoutStockWithElegantException() throws Exception {
        //scenario
        LocationService locationService = new LocationService();
        User user = new User("");
        Movie movie = new Movie("Movie 1", 0, 5.0);

        //action
        locationService.rentMovie(user, movie);
    }

    @Test
    public void testMovieWithoutStockWithRobustException() {
        //scenario
        LocationService locationService = new LocationService();
        User user = new User("");
        Movie movie = new Movie("Movie 1", 2, 5.0);

        //action
        try {
            locationService.rentMovie(user, movie);
            //To avoid a false-positive
            Assert.fail("Should trigger a exception");
        } catch (Exception e) {
            Assert.assertThat(e.getMessage(), CoreMatchers.is("Movie out of stock"));
        }
    }
}
