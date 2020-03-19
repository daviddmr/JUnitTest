package service;

import entity.Location;
import entity.Movie;
import entity.User;
import exception.MovieOutOfStockException;
import exception.VideoStoreException;

import java.util.Date;

import static util.DataUtils.addDays;

class LocationService {

    Location rentMovie(User user, Movie movie) throws MovieOutOfStockException, VideoStoreException {

        if (user == null) {
            throw new VideoStoreException("User null");
        }

        if (movie == null) {
            throw new VideoStoreException("Movie null");
        }

        if (movie.getStock() == 0) {
            throw new MovieOutOfStockException();
        }

        Location location = new Location();
        location.setMovie(movie);
        location.setUser(user);
        location.setLocationDate(new Date());
        location.setValue(movie.getLocationPrice());

        //Delivery next day
        Date deliveryDate = new Date();
        deliveryDate = addDays(deliveryDate, 1);
        location.setReturnDate(deliveryDate);

        return location;
    }
}
