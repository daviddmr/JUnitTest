package service;

import entity.Location;
import entity.Movie;
import entity.User;
import exception.MovieOutOfStockException;
import exception.VideoStoreException;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class CalculationRentValueTest {

    private LocationService locationService;

    private List<Movie> movieList;

    private Double locationAmount;

    @Before
    public void setup() {
        locationService = new LocationService();
    }

    @Test
    public void shouldCalculateRentValueConsideringDiscounts() throws VideoStoreException, MovieOutOfStockException {

        User user = new User("User 1");
        Location result = locationService.rentMovie(user, movieList);

        Assert.assertThat(result.getValue(), CoreMatchers.is(locationAmount));
    }


}
