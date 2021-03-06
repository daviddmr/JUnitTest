package services;

import builders.MovieBuilder;
import builders.UserBuilder;
import entities.Location;
import entities.Movie;
import entities.User;
import exceptions.MovieOutOfStockException;
import exceptions.VideoStoreException;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class CalculationRentValueTest {

    private LocationService locationService;

    @Parameterized.Parameter
    public List<Movie> movieList;

    @Parameterized.Parameter(value = 1)
    public Double locationAmount;

    @Parameterized.Parameter(value = 2)
    public String scenario;

    @Before
    public void setup() {
        locationService = new LocationService();
    }

    private static Movie movie1 = MovieBuilder.getMovie().build();
    private static Movie movie2 = MovieBuilder.getMovie().build();
    private static Movie movie3 = MovieBuilder.getMovie().build();
    private static Movie movie4 = MovieBuilder.getMovie().build();
    private static Movie movie5 = MovieBuilder.getMovie().build();
    private static Movie movie6 = MovieBuilder.getMovie().build();
    private static Movie movie7 = MovieBuilder.getMovie().build();

    @Parameterized.Parameters(name = "{2}")
    public static Collection<Object[]> getParameters() {
        return Arrays.asList(new Object[][]{
                {Arrays.asList(movie1, movie2), 8.0, "2 Movies - No discount"},
                {Arrays.asList(movie1, movie2, movie3), 11.0, "3 Movies - 25%"},
                {Arrays.asList(movie1, movie2, movie3, movie4), 13.0, "4 Movies - 50%"},
                {Arrays.asList(movie1, movie2, movie3, movie4, movie5), 14.0, "5 Movies - 75%"},
                {Arrays.asList(movie1, movie2, movie3, movie4, movie5, movie6), 14.0, "6 Movies - 100%"},
                {Arrays.asList(movie1, movie2, movie3, movie4, movie5, movie6, movie7), 18.0, "7 Movies - No discount"}
        });
    }

    @Test
    public void shouldCalculateRentValueConsideringDiscounts() throws VideoStoreException, MovieOutOfStockException {
        User user = UserBuilder.getUser().build();
        Location result = locationService.rentMovie(user, movieList);

        Assert.assertThat(result.getValue(), CoreMatchers.is(locationAmount));
    }
}
