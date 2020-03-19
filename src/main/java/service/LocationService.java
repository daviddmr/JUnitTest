package service;

import entity.Location;
import entity.Movie;
import entity.User;
import exception.MovieOutOfStockException;
import exception.VideoStoreException;
import org.junit.Assert;
import org.junit.Test;
import util.DataUtils;

import java.util.Date;

import static util.DataUtils.addDays;

class LocationService {

    Location rentMovie(User user, Movie movie) throws MovieOutOfStockException, VideoStoreException {

        if (movie.getStock() == 0) {
            throw new MovieOutOfStockException();
        }

        if (user == null) {
            throw new VideoStoreException("User null");
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
