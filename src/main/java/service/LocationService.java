package service;

import entity.Location;
import entity.Movie;
import entity.User;

import java.util.Date;

import static util.DataUtils.addDays;

public class LocationService {

    public Location rentMovie(User user, Movie movie) {
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

    }
}
