package entities;

import java.util.Date;
import java.util.List;

public class Location {

    private User user;
    private List<Movie> movieList;
    private Date locationDate;
    private Date returnDate;
    private Double value;

    public Location() {
    }

    public Location(User user, List<Movie> movieList, Date locationDate, Date returnDate, Double value) {
        this.user = user;
        this.movieList = movieList;
        this.locationDate = locationDate;
        this.returnDate = returnDate;
        this.value = value;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }

    public Date getLocationDate() {
        return locationDate;
    }

    public void setLocationDate(Date locationDate) {
        this.locationDate = locationDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
