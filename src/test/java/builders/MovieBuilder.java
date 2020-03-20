package builders;

import entities.Movie;

public class MovieBuilder {

    private Movie movie;

    private MovieBuilder() {
    }

    public static MovieBuilder getMovie() {
        MovieBuilder builder = new MovieBuilder();
        builder.movie = new Movie();
        builder.movie.setName("Movie 1");
        builder.movie.setStock(2);
        builder.movie.setLocationPrice(4.0);

        return builder;
    }

    public Movie build() {
        return movie;
    }
}
