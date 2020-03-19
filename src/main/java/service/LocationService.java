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
        location.setValue(calculateDiscountOnPrice(movieList));

        //Delivery next day
        Date deliveryDate = new Date();
        deliveryDate = addDays(deliveryDate, 1);
        location.setReturnDate(deliveryDate);

        return location;
    }

    private Double calculateDiscountOnPrice(List<Movie> movieList) {
        Double amount = 0d;
        int movieListSize = movieList.size();
        for (int i = 0; i < movieListSize; i++) {
            Movie movie = movieList.get(i);
            Double movieLocationPrice = movie.getLocationPrice();

            switch (i) {
                case 2:
                    movieLocationPrice = movieLocationPrice * 0.75;
                    break;
                case 3:
                    movieLocationPrice = movieLocationPrice * 0.5;
                    break;
                case 4:
                    movieLocationPrice = movieLocationPrice * 0.25;
                    break;
                case 5:
                    movieLocationPrice = 0d;
                    break;
            }

            amount += movieLocationPrice;
        }
        return amount;
    }
}
