package service;

import entity.Location;
import entity.Movie;
import entity.User;
import org.junit.Assert;
import org.junit.Test;
import util.DataUtils;

import java.util.Date;

import static util.DataUtils.addDays;

class LocationService {

    Location rentMovie(User user, Movie movie) throws Exception {

        if (movie.getStock() == 0) {
            throw new Exception("Movie out of stock");
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
