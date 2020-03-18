package service;

import entity.Location;
import entity.Movie;
import entity.User;
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

    public static void main(String[] args) {
        //scenario
        LocationService locationService = new LocationService();
        User user = new User("");
        Movie movie = new Movie("Movie 1", 2, 5.0);

        //action
        Location location = locationService.rentMovie(user, movie);

        //verification
        System.out.println(location.getValue() == 5.0);
        System.out.println(DataUtils.isSameDate(location.getLocationDate(), new Date()));
        System.out.println(DataUtils.isSameDate(location.getReturnDate(), DataUtils.getDifferenceBetweenToDays(1)));

    }
}
