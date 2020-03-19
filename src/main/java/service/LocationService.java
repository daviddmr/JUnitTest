package service;

import entity.Location;
import entity.Movie;
import entity.User;
import exception.MovieOutOfStockException;
import exception.VideoStoreException;

import java.util.Date;
import java.util.List;

import static util.DataUtils.addDays;

class LocationService {

    Location rentMovie(User user, List<Movie> movieList) throws MovieOutOfStockException, VideoStoreException {

        if (user == null) {
            throw new VideoStoreException("User null");
        }

        if (movieList == null || movieList.isEmpty()) {
            throw new VideoStoreException("Movie null");
        }

        for (Movie movie : movieList) {
            if (movie.getStock() == 0) {
                throw new MovieOutOfStockException();
            }
        }

        Location location = new Location();
        location.setMovieList(movieList);
        location.setUser(user);
        location.setLocationDate(new Date());
        Double amount = 0d;

        int movieListSize = movieList.size();
        for (int i = 0; i < movieListSize; i++) {
            Movie movie = movieList.get(i);
            Double movieLocationPrice = movie.getLocationPrice();

            if (i == 2) {
                movieLocationPrice = movieLocationPrice * 0.75;
            } else if (i == 3) {
                movieLocationPrice = movieLocationPrice * 0.5;
            } else if (i == 4) {
                movieLocationPrice = movieLocationPrice * 0.25;
            }

            amount += movieLocationPrice;
        }
        location.setValue(amount);

        //Delivery next day
        Date deliveryDate = new Date();
        deliveryDate = addDays(deliveryDate, 1);
        location.setReturnDate(deliveryDate);

        return location;
    }
}
