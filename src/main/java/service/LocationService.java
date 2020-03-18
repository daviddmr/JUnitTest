package service;

import entity.Location;
import entity.Movie;
import entity.User;
import org.junit.Assert;
import org.junit.Test;
import util.DataUtils;

import java.util.Date;

import static util.DataUtils.addDays;

public class LocationService {

    private Location rentMovie(User user, Movie movie) {
        Location location = new Location();
        location.setMovie(movie);
        location.setUser(user);
        location.setLocationDate(new Date());
        location.setValue(movie.getLocationPrice());

        //Entrega no dia seguinte
        Date dataEntrega = new Date();
        dataEntrega = addDays(dataEntrega, 1);
        location.setReturnDate(dataEntrega);

        //Salvando a location...
        //TODO adicionar método para salvar

        return location;
    }

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
