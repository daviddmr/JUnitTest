package service;

import entity.Location;
import entity.Movie;
import entity.User;
import exception.MovieOutOfStockException;
import exception.VideoStoreException;
import org.hamcrest.CoreMatchers;
import org.junit.*;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;
import util.DataUtils;

import java.util.Date;

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
        System.out.println("Before");
        locationService = new LocationService();
    }

    @After
    public void tearDown() {
        System.out.println("After");
    }

    @BeforeClass
    public static void setupClass() {
        System.out.println("Before class");
    }

    @AfterClass
    public static void tearDownClass() {
        System.out.println("After class");
    }

    @Test
    public void test() throws Exception {
        //scenario
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

    /*Elegant exception
    Work well for a situation when just one specific exception will be triggered
    and when do not need the message*/
    @Test(expected = MovieOutOfStockException.class)
    public void testLocation_MovieWithoutStock() throws Exception {
        //scenario
        User user = new User("");
        Movie movie = new Movie("Movie 1", 0, 5.0);

        //action
        locationService.rentMovie(user, movie);
    }

    /*Robust exception
    Work well when is necessary have more control of the test and have to run
    something after the test. Util too when need the message*/
    @Test
    public void testLocation_UserNullException() throws MovieOutOfStockException {
        //scenario
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

    /*Modern exception
    Work well in almost every cases, but sometimes if want more control, the Robust exception is better.
    Util too when need the message*/
    @Test
    public void testLocation_MovieNullException() throws VideoStoreException, MovieOutOfStockException {
        //scenario
        User user = new User("");

        expectedException.expect(VideoStoreException.class);
        expectedException.expectMessage("Movie null");

        //action
        locationService.rentMovie(user, null);
    }
}
