package services;

import entities.Location;
import entities.Movie;
import entities.User;
import exceptions.MovieOutOfStockException;
import exceptions.VideoStoreException;
import matchers.CustomMatchers;
import org.hamcrest.CoreMatchers;
import org.junit.*;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;
import utils.DataUtils;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class LocationServiceTest {

    private LocationService locationService;

    //Used do detect more than one error in the same scope
    //Asserts are not able to recognize possible errors after the first
    @Rule
    public ErrorCollector errorCollector = new ErrorCollector();

    //Used at modern exception
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setup() {
        locationService = new LocationService();
    }

    @Test
    public void shouldRentAMovie() throws Exception {
        Assume.assumeFalse(DataUtils.checkWeekDay(new Date(), Calendar.SATURDAY));

        //scenario
        User user = new User("");
        List<Movie> movieList = Arrays.asList(new Movie("Movie 1", 2, 5.0));

        //action
        Location location = locationService.rentMovie(user, movieList);

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

        //Using Custom Matchers
        errorCollector.checkThat(location.getLocationDate(), CustomMatchers.isToday());
        errorCollector.checkThat(location.getReturnDate(), CustomMatchers.isTodayWithDifferenceOf(1));
    }

    /*Elegant exception
    Work well for a situation when just one specific exception will be triggered
    and when do not need the message*/
    @Test(expected = MovieOutOfStockException.class)
    public void shouldNotRentAMovieWithoutStock() throws Exception {
        //scenario
        User user = new User("");
        List<Movie> movieList = Arrays.asList(new Movie("Movie 1", 0, 5.0));

        //action
        locationService.rentMovie(user, movieList);
    }

    /*Robust exception
    Work well when is necessary have more control of the test and have to run
    something after the test. Util too when need the message*/
    @Test
    public void shouldNotRentAMovieWithoutUser() throws MovieOutOfStockException {
        //scenario
        List<Movie> movieList = Arrays.asList(new Movie("Movie 1", 2, 5.0));

        //action
        try {
            locationService.rentMovie(null, movieList);
            //To avoid a false-positive
            Assert.fail();
        } catch (VideoStoreException e) {
            Assert.assertThat(e.getMessage(), CoreMatchers.is("User null"));
        }
    }

    /*Modern exception
    Work well in almost every cases, but sometimes if want more control, the Robust exception is better.
    Util too when need the message*/
    @Test
    public void shouldNotRentAMovieWithoutMovie() throws VideoStoreException, MovieOutOfStockException {
        //scenario
        User user = new User("");

        expectedException.expect(VideoStoreException.class);
        expectedException.expectMessage("Movie null");

        //action
        locationService.rentMovie(user, null);
    }

    @Test
    public void shouldReturnOnlyOnMondayWhenRentAMovieOnSaturday() throws MovieOutOfStockException, VideoStoreException {
        Assume.assumeTrue(DataUtils.checkWeekDay(new Date(), Calendar.SATURDAY));

        User user = new User("User 1");
        List<Movie> movieList = Arrays.asList(
                new Movie("Movie 1", 2, 4.0),
                new Movie("Movie 2", 2, 4.0));

        Location result = locationService.rentMovie(user, movieList);

        Assert.assertThat(result.getReturnDate(), CustomMatchers.isMonday());
    }
}
